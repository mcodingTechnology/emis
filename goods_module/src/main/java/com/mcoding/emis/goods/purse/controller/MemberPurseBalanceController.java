package com.mcoding.emis.goods.purse.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.common.utils.StringUtil;
import com.mcoding.emis.goods.giftMall.bean.GiftExchange;
import com.mcoding.emis.goods.giftMall.persistence.GiftExchangeMapper;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.purse.baen.MemberPurseBalance;
import com.mcoding.emis.goods.purse.service.MemberPurseBalanceService;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.MessageUtil;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;

@Controller
public class MemberPurseBalanceController {
	@Autowired
    protected MemberPurseBalanceService memberPurseBalanceService;
	@Autowired
	private OrderService orderService;
	@Autowired
	protected CardService cardService;
	@Autowired
	protected CardTypeService cardTypeService;
	@Autowired
	protected CardMapper cardMapper;
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	@Autowired
	private MemberService memberService;
	@Autowired
	protected MemberPointService memberPointService;
	@Autowired
	private GiftExchangeMapper giftExchangeMapper;
	@Autowired
	private StoreWxRefService storeWxRefService;
	@Autowired
	protected ProductService productService;
	
	@Autowired
    private OrderProductsMapper orderProductsMapper;
	@Autowired
	private ProductMapper productMapper;
    
    
    @RequestMapping("/purse/addMemberPurseBalance")
	@ResponseBody
	public CommonResult<String> addMemberPurseBalance(HttpServletRequest request, MemberPurseBalance memberPurseBalance)
			throws ParseException {
		return memberPurseBalanceService.addMemberPurseBalance(memberPurseBalance);
	}
    
    @RequestMapping("/purse/queryMemberPurseBalance")
	@ResponseBody
	public CommonResult<MemberPurseBalance> queryMemberPurseBalance(HttpServletRequest request, HttpSession session)
			throws ParseException {
    	String openid=(String)session.getAttribute("openid");
		return memberPurseBalanceService.queryMemberPurseBalance(openid);
	}
    
    @ResponseBody
	@RequestMapping("/purse/myPursePay")
	public CommonResult<String> wechatPay(HttpServletRequest request,HttpSession session){
    	CommonResult<String> result = new CommonResult<>();
		try {
			// String openid=(String)session.getAttribute("openid");
			String outNo = request.getParameter("outno");
			if(StringUtils.isEmpty(outNo)){
				result.setCode(-1);
				result.setMsg("订单号不能为空");
				return result;
			}
			Order bean= orderService.getOrderByOutNo(outNo).getData();
			if(bean.getPaystatus().equals("待支付")){
				if("JLD".equals(bean.getOrderbrand())){
					//5、判断用户是否第一次购买，若是则设为待发消息状态
					int buytimes = orderService.isFirstTimeOrder(bean.getOpenid(), "2016-09-27");
					if(buytimes == 0){
						bean.setExt3("N");
					}
					//6、查询订单中是否有某些特定商品，若是则设为待发消息状态
					List<Product> productList = productService.getProductsByOrderId(bean.getId());
					if(CollectionUtils.isNotEmpty(productList)) {
						bean.setIssendtip("N");
					}
				}
				System.out.println("微信支付成功回调,刷新订单状态！");
				bean.setPaytime(new Date());
				bean.setPaystatus(Order.PAY_STATUS_PAID);
				bean.setTradeno(bean.getOutno());
				orderService.modifyObj(bean);

				//加钱购的订单扣除对于的积分与更新积分记录
				if(bean.getPlusmoney()!=null && bean.getPlusmoney()>0){
					boolean resultd= updateGiftMallOrder(bean);
					if(resultd==false){
						result.setCode(-1);
						result.setMsg("会员积分不足，扣除积分失败");
						return result;
					}
				}
				
				System.out.println(bean.getFee());
				System.out.println("订单编号========"+bean.getOutno());
				// 根据订单编号查询订单产品  修改产品库存
				updateProuductsQuotaNum(bean);
				
				// 扣减我的钱包余额以及记录流水
				MemberPurseBalance memberPurseBalance = new MemberPurseBalance();
				memberPurseBalance.setBalance(bean.getFee());
				memberPurseBalance.setAction("C");
				memberPurseBalance.setOpenId(bean.getOpenid());
				memberPurseBalance.setRemake("支付订单:"+bean.getOutno()+"消费"+bean.getFee()/100+"元。");
				memberPurseBalanceService.addMemberPurseBalance(memberPurseBalance);
			}
			//支付成功后，核销卡券
			if(StringHelper.isNotBlank(bean.getCardcode())){
				System.out.println("=========================核销卡券");
				CommonResult<CardType> cardType = this.cardTypeService.queryObjByCardCode(bean.getCardcode());
				boolean isSuccess = true;
				if(CardType.CARD_TYPE_CASH.equals(cardType.getData().getCardtype())){
					//代金券的话，就到微信那里核销
					String appid = getAppid();
					String appsecrect =getAppSecret();
					String access_token = WeixinUtil.getJSSDKAccessToken(appid, appsecrect);
					isSuccess = WeixinUtil.consumeCardByCode(access_token, bean.getCardcode());
					System.out.println("【微信卡券核销】 + isSuccess: " + isSuccess);
				}
				
				if(isSuccess){
					this.cardService.consumeCardForOrder(bean.getCardcode(), bean.getOpenid(), bean.getId());
				}
			}
			//发送模板消息
			sendOrderTemplateMsg(bean);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
		return result;
	}
    
    /***
	 * 订单支付，发送模板消息
	 * **/
	public void sendOrderTemplateMsg(Order bean) throws Exception {
		String url = null;String templateMsg = null;
		String color = null;String remark=null;
		if(bean.getOrderbrand().equals("MRMJ")){
			color = "#f37b1d";
			if("giftMall".equals(bean.getMalltype())){
				templateMsg = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_PAY_POINT;
				url = getDomain() + "/GiftMall/order_detail.html?type=gift&orderId="+bean.getId();
			}else{
				templateMsg = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_PAY;
				if(StringHelper.isBlank(bean.getCardcode())){
					url = getDomain() + "/wMall/order_detail.html?orderId="+bean.getId();
				}else{
					url = getDomain() + "/wMall/order_detail.html?orderId="+bean.getId()+"&cardType=ACTIVITY&cardType=ACTIVITY";
				}
			}
		}else {
			color = "#E73828";
			if("giftMall_gmx".equals(bean.getMalltype())){
				templateMsg = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_PAY_POINT;
				url = getDomain() + "/GiftMall_GMX/order_detail.html?type=gift&orderId="+bean.getId();
			}else{
				templateMsg = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_PAY;
				if(StringHelper.isBlank(bean.getCardcode())){
					url = getDomain()+"/gMall/order_detail.html?orderId="+bean.getId();
				}else{
					url = getDomain()+"/gMall/order_detail.html?orderId="+bean.getId()+"&cardType=ACTIVITY&cardType=ACTIVITY";
				}
			}
		}
		//推送用户支付成功的模板消息
		if("giftMall".equals(bean.getMalltype()) || "giftMall_gmx".equals(bean.getMalltype())){//积分商城
			wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(templateMsg,bean.getOpenid(), bean.getFee()+"积分 "+" + "+bean.getPlusmoney() / 100 + "元", "点击后查看详情",
					bean.getRegson() + bean.getAddress() + "," + bean.getReceiver() + "," + bean.getReceiverphone(), bean.getOutno(),
					null, null, url);

		}else{//微商城
			wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(templateMsg,bean.getOpenid(), bean.getFee() / 100 + "元", "点击后查看详情",
					bean.getRegson() + bean.getAddress() + "," + bean.getReceiver() + "," + bean.getReceiverphone(), bean.getOutno(),
					null, null, url);
		}

		//判断是否完善资料，推送微社区的模板
		if("wMall".equals(bean.getMalltype()) || "gMall".equals(bean.getMalltype()) || "giftMall_gmx".equals(bean.getMalltype())){
			Member member = memberService.queryMemberByOpenid(bean.getOpenid());
			//是否已完善资料
			if(member!=null){
				//已完善
				if(StringUtils.isNotBlank(member.getMobilePhone())){
					url ="http://v.gymmaxer.com/sns_module/mobileview/community/index.html";
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_READY_DELIVER, bean.getOpenid(), null ,
							bean.getOutno(), DateUtil.dateTimeFormatStr(bean.getPaytime()), bean.getFee()/100 + "元", null, null, null,url,null);
					member.setUpdateTime(new Date());
				}else{
					remark = "点击进入【个人中心】，完善信息可以赢100积分，享好礼";
					url =getDomain() +  "gMall/member_index.html";
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_READY_DELIVER, bean.getOpenid(), null ,
							bean.getOutno(), DateUtil.dateTimeFormatStr(bean.getPaytime()), bean.getFee()/100 + "元", null, null, remark,url,null);
					member.setUpdateTime(new Date());
				}
			}
		}

	}
	public boolean updateGiftMallOrder(Order order){
		Member member = memberService.queryMemberByOpenid(order.getOpenid());
		//1.扣除会员对应的订单积分,并更新会员积分余额
		if(member.getPointSum()!=0){
			if(member.getPointSum() - order.getFee()>=0){
				member.setPointSum(member.getPointSum() - order.getFee());
				memberService.modifyObj(member);

				//2.记录兑换礼品所消耗的积分
				GiftExchange giftExchange = new GiftExchange();
				giftExchange.setBrandcode(order.getOrderbrand());
				giftExchange.setConsumepoint(order.getFee());
				giftExchange.setCreatetime(new Date());
				giftExchange.setOrderid(order.getId());
				giftExchangeMapper.insert(giftExchange);

				//3.若是积分商城兑换礼品，新增会员积分信息
				MemberPoint memberPoint = new MemberPoint();
				memberPoint.setOpenid(order.getOpenid());
				memberPoint.setBrandCode(order.getOrderbrand());
				memberPoint.setAddDate(new Date());
				memberPoint.setMobilePhone(member.getMobilePhone());
				memberPoint.setPointsType("E");
				memberPoint.setRelatedTransactionNo("giftmall");
				memberPoint.setPoints(-order.getFee());
				this.memberPointService.insert(memberPoint);

				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	private String getDomain(){
		Store store =  StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		return domain;
	}
	private void updateProuductsQuotaNum(Order bean) {
		// bean.setOutno("20171130110745241");
		List<OrderProducts> opList = new ArrayList<OrderProducts>();
		if(StringUtils.isNotEmpty(bean.getOutno())){
			opList = orderProductsMapper.getOrderProductsByOutNo(bean.getOutno());
			// 更新产品库存
			for(OrderProducts orderProducts : opList){
				if(orderProducts.getProductid() != null){
					Product t = new Product();
					t.setProductId(orderProducts.getProductid());
					t.setQuotaNum(orderProducts.getNums());
					productMapper.updateProductQuotaNum(t);
				}
			}
		}
	}
	private String getAppid(){
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		String appid = this.storeWxRefService.queryWxAppidByStoreId(storeId);
		return appid;
	}
	
	private String getAppSecret(){
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		String appSecret = this.storeWxRefService.queryWxAppSecretByStoreId(storeId);
		return appSecret;
	}
}