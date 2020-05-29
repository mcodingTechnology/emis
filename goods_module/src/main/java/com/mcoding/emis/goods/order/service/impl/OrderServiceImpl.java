package com.mcoding.emis.goods.order.service.impl;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.persistence.CardTypeMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.cart.service.CartService;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.giftMall.bean.GiftExchange;
import com.mcoding.emis.goods.giftMall.persistence.GiftExchangeMapper;
import com.mcoding.emis.goods.information.bean.Information;
import com.mcoding.emis.goods.information.service.InformationService;
import com.mcoding.emis.goods.mallGame.bean.MallgameResult;
import com.mcoding.emis.goods.mallGame.bean.MallgameResultExample;
import com.mcoding.emis.goods.mallGame.persistence.MallgameResultMapper;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderAndProducts;
import com.mcoding.emis.goods.order.bean.OrderDiscount;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderDiscountMapper;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfig;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConstant;
import com.mcoding.emis.goods.ruleengine.persistence.RuleEngineConfigMapper;
import com.mcoding.emis.goods.seckill.bean.Seckill;
import com.mcoding.emis.goods.seckill.bean.SeckillResult;
import com.mcoding.emis.goods.seckill.persistence.SeckillMapper;
import com.mcoding.emis.goods.seckill.persistence.SeckillResultMapper;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberAddressService;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;

    private static final Logger log = Logger.getLogger(OrderServiceImpl.class);
    private static final int MAX_CALLCOUNT = 10;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderProductsMapper orderProductsMapper;
    
    @Autowired
    private MemberAddressService memberAddressService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private WeixinUserService weixinUserService;
	
	@Autowired
	protected InformationService informationService;
	
	@Autowired
	protected CardService cardService; 
	
	@Autowired
	protected CardMapper cardMapper;
	@Autowired
	private CardTypeMapper cardTypeMapper;

//    @Autowired
//    private MemberMapper memberMapper;
    @Autowired
    private GiftExchangeMapper giftExchangeMapper;
    @Autowired
    private ProductMapper productMapper;
//    @Autowired
//    private WeixinUserMapper weixinUserMapper;
    @Autowired
    private MallgameResultMapper gameResultMapper;
//    @Autowired
//    private MemberPointMapper memberPointMapper;
    @Autowired
    private MemberPointService memberPointService;
    @Autowired
    private SeckillResultMapper seckillResultMapper;
    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private OrderDiscountMapper orderDiscountMapper;
    @Autowired
	private RuleEngineConfigMapper ruleEngineConfigMapper;
	
	@Override
	public CommonResult<String> addObj(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(Order t) {
		orderMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<Order> queryObjById(String id) {
		Order order = this.orderMapper.selectByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<Order> result = new CommonResult<Order>();
		result.setData(order);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<Order>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Order>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Order> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Order> queryOrderByPage(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength, String sSearch,
			String status,String merchantId, Integer process, String payType,
			String province, String city, String area, String startDate,
			String endDate,String pageNo,String pageSize) throws ParseException {
		
		PageView<Order> pageView ;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
			pageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			pageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else {
			pageView = new PageView<>(1, 10);
		}
		
		OrderExample ex = new OrderExample();
		OrderExample.Criteria cri = ex.createCriteria();
		
		if(StringUtils.isNotBlank(sSearch)){
			cri.andOutnoEqualTo(sSearch);
		}
		if(StringUtils.isNotBlank(startDate)){
			cri.andAddtimeGreaterThanOrEqualTo(DateUtil.StrFormatDateTime(startDate));
		}
		
		if(StringUtils.isNotBlank(endDate)){
			cri.andAddtimeLessThanOrEqualTo(DateUtil.StrFormatDateTime(endDate));
		}
		//搜索订单状态
		if(StringUtils.isNotBlank(status)){
			cri.andPaystatusEqualTo(status);
		}
		//搜索支付类型
		if(StringUtils.isNotBlank(payType)){
			cri.andPaytypeEqualTo(payType);
		}
		//搜索订单编号
		String outNo = request.getParameter("outNo");
		if(StringUtils.isNotBlank(outNo)){
			cri.andOutnoEqualTo(outNo);
		}
		//搜索交易号
		String thirdNo = request.getParameter("thirdNo");
		if(StringUtils.isNotBlank(thirdNo)){
			cri.andThirdnoEqualTo(thirdNo);
		}
		//搜索手机号码
		String mobilePhone = request.getParameter("telPhone");
		if(StringUtils.isNotBlank(mobilePhone)){
			cri.andMobilephoneEqualTo(mobilePhone);
		}
		//搜索收货人手机号码
		String reciverPhone = request.getParameter("reciverPhone");
		if(StringUtils.isNotBlank(reciverPhone)){
			cri.andReceiverphoneEqualTo(reciverPhone);
		}
		//搜索会员名称
		String name = request.getParameter("yourName");
		if(StringUtils.isNotBlank(name)){
			cri.andReceiverEqualTo(name);
		}
		//搜索会员openid
		String openid = request.getParameter("openid");
		if(StringUtils.isNotBlank(openid)){
			cri.andOpenidEqualTo(openid);
		}
		//搜索物流单号
		String deliveryorderno = request.getParameter("deliveryorderno");
		if(StringUtils.isNotBlank(deliveryorderno)){
			cri.andDeliveryordernoEqualTo(deliveryorderno);
		}
		ex.setPageView(pageView);
		ex.setOrderByClause("addTime DESC");
        
        List<Order> orderList = orderMapper.queryOrderByPage(ex);
        pageView.setQueryResult(orderList);
        return pageView;
	}

	@Override
	public CommonResult<Order> queryOrderDetail(Integer orderId) {
		CommonResult<Order> result = new CommonResult<Order>();
        try {
        	Order order = orderMapper.selectByPrimaryKey(orderId);
            result.setCode(0);
            result.setData(order);
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("查询订单列表信息出错:", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
        }
        return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonResult<Order> creategetOrder(HttpSession session,OrderAndProducts oad) {
		JsonResult<Order> jsonResult = new JsonResult<Order>();
		
		try {
			String openid=(String)session.getAttribute("openid");
			
			Map<String,Object> commitOrderMap = (Map<String, Object>) session.getAttribute(RuleEngineConstant.ORDER_COMMIT_MAP_IN_SESSION);
			Order order = oad.getOrderInfo();
			Integer productTotalPrice=order.getFee();
			Integer preferentialPrice=0;
			Integer payFreight=order.getFreight();
			Integer totalNums = 0;
			List<OrderProducts> op = new ArrayList<OrderProducts>();
			if(null != commitOrderMap){
				List<OrderProducts> orderProdutsList = (List<OrderProducts>) commitOrderMap.get(RuleEngineConstant.MAP_ORDER_PRODUCT_LIST);
		    	List<OrderProducts> giftList = (List<OrderProducts>) commitOrderMap.get(RuleEngineConstant.MAP_ORDER_GIFT_LIST);
		    	productTotalPrice = (Integer) commitOrderMap.get(RuleEngineConstant.MAP_PRODUCT_TOTAL_PRICE);
		    	preferentialPrice = (Integer) commitOrderMap.get(RuleEngineConstant.MAP_PREFERENTIAL_PRICE);
//		    	Integer freight = (Integer) commitOrderMap.get(RuleEngineConstant.MAP_FREIGHT);
		    	payFreight = (Integer) commitOrderMap.get(RuleEngineConstant.MAP_PAY_REEIGHT);
		    	String orderType = (String) commitOrderMap.get(RuleEngineConstant.MAP_ORDER_TYPE);
				
		    	if(RuleEngineConstant.ORDER_TYPE_ALREADY.equals(orderType)){
		    		OrderAndProducts oaps = (OrderAndProducts) commitOrderMap.get(RuleEngineConstant.MAP_ALREADY_ORDER_INFO);
		    		op = oaps.getOrderProductsInfo();
		    	}else{
//		    		op.addAll(orderProdutsList);
//			    	op.addAll(giftList);
		    		for(OrderProducts pro : orderProdutsList){
		    			totalNums += pro.getNums();
		    			op.add(pro);
		    		}
		    		for(OrderProducts gift : giftList){
		    			totalNums += gift.getNums();
		    			gift.setPrice(0);
		    			op.add(gift);
		    		}
		    	}
			}else {
				op.addAll(oad.getOrderProductsInfo());
				totalNums = order.getNums();
			}
			
			Member member = this.memberService.queryMemberByOpenid(openid);
			// 会员才有秒杀之类的才会限购购买数量
			if (member.getChannelsId() == 5) {
				//限购商品判断是否通过
				jsonResult = isLimitProductOrder(op, openid, order);
				if(jsonResult.getStatus().equals("05")){
					return jsonResult;
				}
			}
			
	    	//使用微信卡券产生优惠金额
			Integer cardId = order.getCardid();
			Integer cardReduceCost = 0;
			if(null != cardId){
//				Card card = cardMapper.selectByPrimaryKey(cardId);
				CardType cardType = cardTypeMapper.selectByPrimaryKey(cardId);
				if(null != cardType.getReducecost()){
					cardReduceCost = cardType.getReducecost();
				}
			}
			//使用券码兑换产品
			if(cardId==null && null != order.getCardcode()){
				CardExample cardeExample = new CardExample();
				CardExample.Criteria cri = cardeExample.createCriteria();
				cri.andCardcodeEqualTo(order.getCardcode());
				List<Card> list = cardMapper.selectByExample(cardeExample);
				if(list.size()>0){
					CardType cardType = cardTypeMapper.selectByPrimaryKey(list.get(0).getCardtypeid());
					if(null != cardType.getReducecost()){
						cardReduceCost = cardType.getReducecost();
						productTotalPrice = productTotalPrice + cardReduceCost;
					}
				}
			}
			if(payFreight == null){
				payFreight = 0;
			}
			Integer fee = productTotalPrice-cardReduceCost+payFreight;
			order.setFee(fee);
			order.setNums(totalNums);
	    	order.setFreight(payFreight);
	    	order.setFeereduce(cardReduceCost);
	    	order.setPreferentialprice(preferentialPrice);
	    	
			
			if (!Order.PRESENT_STATUS_GIFT_PRESENTED.equals(order.getPresentstatus())) {

				/*if (order.getAddressid() == null || order.getAddressid() == 0) {
					jsonResult.setStatus("01");
					jsonResult.setMsg("订单地址不能为空");
					return jsonResult;
				}*/
				
				order.setAddress(oad.getAddressInfo().getAddress());
				order.setRegson(oad.getAddressInfo().getRegson());
				order.setReceiver(oad.getAddressInfo().getName());
				order.setReceiverphone(oad.getAddressInfo().getPhone());
			} else {
				// 好友赠送的时候，地址为空
				oad.setAddressInfo(null);
				order.setAddressid(null);
			}
			
//			List<OrderProducts> op = oad.getOrderProductsInfo();
			// 1、先将订单信息入库
			order.setAddtime(new Date());
			order.setOpenid(openid);
			order.setOutno(DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS"));

			if (member != null) {
				order.setMemberid(member.getMemberId());
				order.setMembername(member.getFullName());
			}
			for(OrderProducts orderProducts : op){
				if(orderProducts.getProductid() != null){
					Product product = productMapper.queryById(orderProducts.getProductid());
					if(order.getSumFee() == null){
						order.setSumFee(0);	
					}
					order.setSumFee((product.getOriginalPrice() * orderProducts.getNums())+order.getSumFee());
				}
			}
			int orderResut = orderMapper.insertSelective(order);
			int orderId = order.getId();

			// 2、再将对应的产品入库
			for (OrderProducts orderProducts : op) {
				orderProducts.setOrderid(orderId);
				orderProductsMapper.insert(orderProducts);
			}
			
			Date date = new Date();
			//订单优惠价格信息入库
			List<OrderDiscount> odPromoPriceList = oad.getOrderPreferentialInfo();
			if(odPromoPriceList!=null){
				for(OrderDiscount od : odPromoPriceList){
					od.setOpenid(openid);
					od.setDiscountType((short) 1);
					od.setOrderId(orderId);
					od.setCreateTime(date);
					od.setUpdateTime(date);
					orderDiscountMapper.insertSelective(od);
				}
			}
			
			
			//订单赠品信息入库
			List<OrderDiscount> odGiftList = oad.getOrderGiftInfo();
			if(odGiftList!=null){
				for(OrderDiscount od : odGiftList){
					od.setOpenid(openid);
					od.setDiscountType((short)2);
					od.setOrderId(orderId);
					od.setCreateTime(date);
					od.setUpdateTime(date);
					orderDiscountMapper.insertSelective(od);
				}
			}
			
			
			// 3、清空购物车
			cartService.delShoppingCart(null, openid);

			// 4、插入通知消息
			Information info = new Information();
			info.setReceiveropenid(openid);

			/*if("JLD".equals(order.getOrderbrand())){
				//5、判断用户是否第一次购买，若是则设为待发消息状态
				int times = this.isFirstTimeOrder(order.getOpenid(), "2016-09-27");
				if(times == 0){
					order.setIssendtip("N");
					this.modifyObj(order);
				}
				//6、查询订单中是否有某些特定商品，若是则设为待发消息状态
				List<Product> productList = productMapper.getProductsByOrderId(order.getId());
				if(CollectionUtils.isNotEmpty(productList)) {
					order.setExt3("N");
					this.modifyObj(order);
				}
			}*/

			if (member != null) {
				info.setReceivermemberid(member.getMemberId());
			}
			info.setCreatetime(new Date());
			info.setType(Information.INFORMATION_TYPE_ORDER);
			info.setStatus(Information.STATUS_UNREADED);
			info.setContent("你买了" + op.size() + "个商品");
			info.setTitle("购物消息");
			this.informationService.addObj(info);
			System.out.println("outNo===============" + order.getOutno());
			
			session.removeAttribute(RuleEngineConstant.ORDER_COMMIT_MAP_IN_SESSION);
			
			if (orderResut != 0) {
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				jsonResult.setData(order);
			}
			log.info("订单生成成功：ourderNo:"+order.getOutno()+",fee:"+fee+",总购买数量："+totalNums+",cardId:"+cardId);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
	
	/***
	 * 限购商品的判断规则
	 * ***/
	public JsonResult<Order> isLimitProductOrder(List<OrderProducts> op,String openid, Order order){
		JsonResult<Order> jsonResult = new JsonResult<Order>();

		for (OrderProducts orderProducts : op) {
			Product product = productMapper.queryById(orderProducts.getProductid());
			//限购商品
			if(product.getExt()!=null&&product.getExt().equals("1")){
				//1、库存不足
				if(product.getQuotaNum()!=null&&product.getQuotaNum()==0){
					jsonResult.setStatus("05");
					jsonResult.setMsg(product.getProductName()+"，已被秒完，库存不足");
					return jsonResult;
				}else{
					// 2、限购商品有库存
					// 判断是否已购买过
					// 更新限购商品兑换量和扣减库存
					JsonResult<Integer> result = this.isOverProductQuota(openid, product.getProductId());
					if(result.getStatus().equals("01")){
						jsonResult.setStatus("05");
						jsonResult.setMsg(product.getProductName()+"，您已购买过此商品，不能重复购买");
						return jsonResult;
					}
					product.setExchangeNum(product.getExchangeNum()+orderProducts.getNums());
					if(product.getQuotaNum()!=null&&product.getQuotaNum()>0){
						product.setQuotaNum(product.getQuotaNum()-1);
					}
					productMapper.updateBySelect(product);

					//若是0元的限购商品，则订单状态设为“待发货”
					if(product.getDiscountPrice()== 0){
						order.setPaystatus(Order.PAY_STATUS_PAID);
					}
				}
			}else{
				//非限购商品，订单状态设为“未支付”
				order.setPaystatus(Order.PAY_STATUS_CREATED);
			}

		}
		
		jsonResult.setStatus("00");
		jsonResult.setMsg("成功通过");
		return jsonResult;
	}
	
	@Override
	public JsonResult<List<Order>> getOrderProductsByStatus(String status,String openid,String malltype) throws Exception {
		JsonResult<List<Order>> jsonResult = new JsonResult<List<Order>>();
		List<Order> orderList = new ArrayList<Order>();
		List<Order> resultObj = new ArrayList<Order>();
		try {
			status = URLDecoder.decode(status, "utf-8");
			
			OrderExample ex = new OrderExample();
			OrderExample.Criteria cri = ex.createCriteria();
			cri.andOpenidEqualTo(openid);
//			cri.andOrdertypeIsNull();
			cri.andMalltypeEqualTo(malltype);
			
			if (status != null && status.length() > 0) {
				if(status.equals("全部")){
					//查询全部
					status = null;
					
				}else if(Order.PRESENT_STATUS_GIFT_PRESENTED.equals(status)){
					cri.andPresentstatusEqualTo(Order.PRESENT_STATUS_GIFT_PRESENTED);
					
				}else{
					cri.andPaystatusEqualTo(status);
				}
				
				ex.setOrderByClause("addTime DESC");
				orderList = orderMapper.selectByExample(ex);
				
				for (Order order : orderList) {
					//根据订单id到mr_order_products表
					List<OrderProducts> opList = new ArrayList<OrderProducts>();
					opList = orderProductsMapper.getOrderProductsByOrderId(order.getId());
					List<String> imgList = new ArrayList<String>();
					for (OrderProducts orderProducts : opList) {
						imgList.add(orderProducts.getProductcoverimg());
					}
					order.setProductImg(imgList);
					resultObj.add(order);
				}
				
				jsonResult.setData(resultObj);
				jsonResult.setSize(resultObj.size());
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("参数不能为空");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			throw new Exception("兑换订单查询失败");
		}
		return jsonResult;
	}
	
	@Override
	public PageView<OrderAndProducts> getOrderProductsByStatusPage(String iDisplayStart, String iDisplayLength,String payStatus, String openid, String mallType){
		PageView<Order> pageView = new PageView<Order>(iDisplayStart, iDisplayLength);
		List<Order> orderList = new ArrayList<Order>();
		List<OrderAndProducts> resultObj = new ArrayList<OrderAndProducts>();
		PageView returnPageView = new PageView(iDisplayStart, iDisplayLength);
		try {
			payStatus = URLDecoder.decode(payStatus, "utf-8");
			
			OrderExample ex = new OrderExample();
			OrderExample.Criteria cri = ex.createCriteria();
			cri.andOpenidEqualTo(openid);
//			cri.andOrdertypeIsNull();
			cri.andMalltypeEqualTo(mallType);
			
			if (payStatus != null && payStatus.length() > 0) {
				if(payStatus.equals("全部")){
					//查询全部
					cri.andPaystatusNotEqualTo("已取消");
				}else if(Order.PRESENT_STATUS_GIFT_PRESENTED.equals(payStatus)){//待接受，排除已取消和退换货
					cri.andPresentstatusEqualTo(Order.PRESENT_STATUS_GIFT_PRESENTED);
					cri.andPaystatusNotEqualTo("已取消");
					cri.andReturnstatusIsNull();
				}else if(Order.ORDER_STATUS_RETURN.equals(payStatus)){//退换货
					cri.andReturnstatusIsNotNull();
				}else if(Order.PAY_STATUS_CREATED.equals(payStatus)){ //待支付待接受
					cri.andPaystatusEqualTo(payStatus);
				}else{//待发货、已发货、已完成  已接受
					cri.andPaystatusEqualTo(payStatus);
					cri.andReturnstatusIsNull();
					/*OrderExample.Criteria cri2 = ex.createCriteria();
					cri2.andPresentstatusEqualTo(Order.PRESENT_STATUS_GIFT_RECEIVED);
					ex.or(cri2);*/
				}
				ex.setPageView(pageView);
				ex.setOrderByClause("addTime DESC");
				orderList = orderMapper.queryOrderByPage(ex);
				
				for (Order order : orderList) {
					OrderAndProducts oap = new OrderAndProducts();
					oap.setOrderInfo(order);
					
					//根据订单id到mr_order_products表
					List<OrderProducts> opList = orderProductsMapper.getOrderProductsByOrderId(order.getId());
					List<String> imgList = new ArrayList<String>();
					for (OrderProducts orderProducts : opList) {
						if(StringUtils.isEmpty(orderProducts.getProductcoverimg())){
							orderProducts.setProductcoverimg("");
						}
						if(!"赠品".equals(orderProducts.getProducttype())){
							imgList.add(orderProducts.getProductcoverimg());
						}
					}
					order.setProductImg(imgList);
					oap.setOrderProductsInfo(opList);
					resultObj.add(oap);
				}
				returnPageView = pageView;
				returnPageView.setQueryResult(resultObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return returnPageView;
	}


	@Override
	public JsonResult<OrderAndProducts> getOrderInfoByOrderId(int orderId,String openid) {
		JsonResult<OrderAndProducts> jsonResult = new JsonResult<OrderAndProducts>();
		OrderAndProducts oap = new OrderAndProducts();
		try {
			if (orderId != 0) {
				
				//获取订单信息
//				Order order = orderMapper.getOrderProductsByOrderId(openid, orderId);
				Order order = orderMapper.selectByPrimaryKey(orderId);

				//根据订单id到mr_order_products表
				List<OrderProducts> opList = new ArrayList<OrderProducts>();
				opList = orderProductsMapper.getOrderProductsByOrderId(orderId);
				
				//获取地址
//				MemberAddress ma = memberAddressMapper.queryMrAddressBykey(openid);
//				CommonResult<MemberAddress> ma = this.memberAddressMapper.queryById(String.valueOf(order.getAddressid());
//				if(order.getAddressid() != null){
//					CommonResult<MemberAddress> ma = this.memberAddressService.queryObjById(String.valueOf(order.getAddressid()));
//					oap.setAddressInfo(ma.getData());
//				}
				MemberAddress ma = new MemberAddress();
				ma.setAddress(order.getAddress());
				ma.setRegson(order.getRegson());
				ma.setName(order.getReceiver());
				ma.setPhone(order.getReceiverphone());
				
				oap.setAddressInfo(ma);
				
				oap.setOrderProductsInfo(opList);
				oap.setOrderInfo(order);
				
//				WeixinUser weixinUser = weixinUserMapper.selectByOpenid(order.getOpenid(), order.getOrderbrand());
				WeixinUser weixinUser = this.weixinUserService.selectByOpenid(order.getOpenid());
				if(weixinUser!=null){
					if(weixinUser.getShareId()!=null){
//						Member parentMember = memberMapper.queryMemberByOpenid(weixinUser.getShareId());
						Member parentMember = this.memberService.queryMemberByOpenid(weixinUser.getShareId());
						oap.setParentMember(parentMember);

					}
//					Member member = memberMapper.queryMemberByOpenid(weixinUser.getOpenid());
					Member member = this.memberService.queryMemberByOpenid(weixinUser.getOpenid());
					oap.setMember(member);
					oap.setWeixinUser(weixinUser);
				}

				jsonResult.setData(oap);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("参数不能为空");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	/*@Override
	public JsonResult<String> updateOrder(int orderId,String status) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		try {
			Order o = new Order();
			o.setId(orderId);
			o.setPaystatus(status);
			this.orderMapper.updateByPrimaryKeySelective(o);
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}*/

	@Override
	public List<Order> getIncomeableOrder() {
//		Calendar.getInstance().
		OrderExample ex = new OrderExample();
		ex.createCriteria()
 		  .andIncomestatusEqualTo(Order.INCOME_STATUS_AVAILABLE)
 		  .andPaystatusEqualTo(Order.PAY_STATUS_PAID)
 		  .andReturnstatusIsNull();
		
		
		List<Order> list = this.orderMapper.selectByExample(ex);
		return list;
	}

	@Override
	public CommonResult<ArrayList<Order>> queryListByExample(OrderExample ex) {
		CommonResult<ArrayList<Order>> result = new CommonResult<ArrayList<Order>>();
		try{
			List<Order> list = this.orderMapper.selectByExample(ex);
			ArrayList<Order> tmp = new ArrayList<Order>();
			CollectionUtils.addAll(tmp, list.iterator());
			result.setData(tmp);
			result.setCode(0);
			result.setMsg("ok");
			
		}catch(Exception e){
			result.setData(null);
			result.setCode(-1);
			result.setMsg(e.getMessage());
		}
		
		return result;
	}

	@Override
	public CommonResult<String> finishOrder(int orderId) {
		CommonResult<String> result = new CommonResult<String>();
		try{
			OrderExample ex = new OrderExample();
			ex.createCriteria()
			  .andIdEqualTo(orderId)
			  .andPaystatusEqualTo(Order.PAY_STATUS_SENT); //只有已发货的，才能确认收货
			
			Order order = new Order();
			order.setPaystatus(Order.PAY_STATUS_FINISHED);
			order.setReceivetime(new Date());
			
			this.orderMapper.updateByExampleSelective(order, ex);
			
			result.setData("ok");
			result.setCode(0);
			result.setMsg("ok");
			
		}catch(Exception e){
			result.setData("error");
			result.setCode(-1);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@Override
	public CommonResult<ArrayList<Order>> queryObjByExample(OrderExample example) {
		if(example == null){
			example = new OrderExample();
		}
		
		List<Order> list = this.orderMapper.selectByExample(example);
		ArrayList<Order> tmp = new ArrayList<Order>();
		CollectionUtils.addAll(tmp, list.iterator());
		
		CommonResult<ArrayList<Order>> result = new CommonResult<ArrayList<Order>>();
		result.setData(tmp);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> batchSendOrder(String[] orderId) {
		CommonResult<String> result = new CommonResult<String>();
		String[] orderIds = orderId;
         for(int i=0; i<orderIds.length; i++){
        	Order o =orderMapper.selectByPrimaryKey(Integer.valueOf(orderId[i]));
        	o.setPaystatus(o.PAY_STATUS_SENT);
			o.setSendtime(new Date());
        	o.setId(Integer.valueOf(orderId[i]));
        	orderMapper.updateByPrimaryKeySelective(o);
          }   
         result.setCode(0);
         result.setData("发货成功");
         result.setMsg("ok");
		return result;
	}
	
	@Override
	public CommonResult<String> batchReceivingOrder(String[] orderId) {
		CommonResult<String> result = new CommonResult<String>();
		String[] orderIds = orderId;
         for(int i=0; i<orderIds.length; i++){
        	if(StringUtils.isNotBlank(orderId[i])){
        		Order o =orderMapper.selectByPrimaryKey(Integer.valueOf(orderId[i]));
            	o.setPaystatus(o.PAY_STATUS_FINISHED);
            	o.setId(Integer.valueOf(orderId[i]));
            	o.setReceivetime(new Date());
            	orderMapper.updateByPrimaryKeySelective(o);
        	}
          }   
         result.setCode(0);
         result.setData("收货成功");
         result.setMsg("ok");
		return result;
	}
	
	@Transactional
	@Override
	public JsonResult<Order> creategetOrderPointMall(OrderAndProducts oad,Integer isgift,String openid) throws Exception {
		JsonResult<Order> jsonResult = new JsonResult<Order>();
		try {
			Member member = this.memberService.queryMemberByOpenid(openid);
			if(member==null){
				jsonResult.setStatus("01");
				jsonResult.setMsg("会员不存在，请重新登录");
				return jsonResult;
			}
			Order order = oad.getOrderInfo();
			if (order.getAddressid() == null || order.getAddressid() == 0) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("订单地址不能为空");
				return jsonResult;
			}
			/*if ((order.getFee() == null || order.getFee() == 0) && isgift==null) {
				jsonResult.setStatus("03");
				jsonResult.setMsg("积分总额不能为空或0");
				return jsonResult;
			}*/
			MallgameResultExample example2 = new MallgameResultExample();
			MallgameResultExample.Criteria criteria2 = example2.createCriteria();
			if(order.getResultid()!=null){
				if(order.getOrdertype().equals(Order.ORDER_TYPE_KILL)){
					SeckillResult seckillResult = seckillResultMapper.selectByPrimaryKey(order.getResultid());
					if(seckillResult!=null&& seckillResult.getOrderid()!=0){
						jsonResult.setStatus("04");
						jsonResult.setMsg("奖品已被领取过，不能重复提交");
						return jsonResult;
					}
				}else {
					//禁止抽中的奖品重复提交领取
					criteria2.andIdEqualTo(order.getResultid());
					criteria2.andIsorderEqualTo(1);
					List<MallgameResult> mallgameResults = gameResultMapper.selectByExample(example2);
					if(mallgameResults.size()>0){
						jsonResult.setStatus("04");
						jsonResult.setMsg("奖品已被领取过，不能重复提交");
						return jsonResult;
					}
				}
			}
			
			order.setAddress(oad.getAddressInfo().getAddress());
			order.setRegson(oad.getAddressInfo().getRegson());
			order.setReceiver(oad.getAddressInfo().getName());
			order.setReceiverphone(oad.getAddressInfo().getPhone());
		
			List<OrderProducts> op = oad.getOrderProductsInfo();
			int orderMoney = 0;
			int orderFee = 0;
			//判断是否存在限购商品，及库存不足
			for (OrderProducts orderProducts : op) {
				Product product = productMapper.queryById(orderProducts.getProductid());
				Integer isOpenDsicountPoint = product.getIsOpenDsicountPoint();
				//开启会员日积分优惠
				if(isOpenDsicountPoint!=null && isOpenDsicountPoint.intValue()==1){
					//判断用户选择了积分加钱还是全额积分,giftbuytype 0全额积分 1加钱购
					if(orderProducts.getGiftBuyType()!=null && orderProducts.getGiftBuyType()==0){
						//全额积分
						orderFee+=(product.getDiscountPoint().intValue()*orderProducts.getNums());
					}else{
						if(product.getDiscountGiftPlusPoint()!=null && product.getDiscountPointMoney()!=null){
							orderFee+=(product.getDiscountGiftPlusPoint().intValue()*orderProducts.getNums());
							orderMoney+=(product.getDiscountPointMoney().intValue()*orderProducts.getNums());
						}
					}
				}else{
					if(orderProducts.getGiftBuyType()!=null && orderProducts.getGiftBuyType()==0){
						if(product.getGiftPoint()!=null){
							orderFee+=(product.getGiftPoint().intValue()*orderProducts.getNums());
						}
					}else{
						if(product.getGiftPlusPoint()!=null && product.getGiftPointMoney()!=null){
							orderFee+=(product.getGiftPlusPoint().intValue()*orderProducts.getNums());
							orderMoney+=(product.getGiftPointMoney()*orderProducts.getNums());
						}
					}
				}
				if(product.getQuotaNum()!=null&&product.getQuotaNum()==0){
					jsonResult.setStatus("05");
					jsonResult.setMsg(product.getProductName()+"，已被秒完，库存不足");
					return jsonResult;
				}
			}
			//奖品抽奖不需要积分
			if("giftgameorder".equals(order.getOrdertype()) || "stakegameorder".equals(order.getOrdertype())
					|| "kill".equals(order.getOrdertype())){
				orderFee = 0;
			}else{
				//加钱购订单
				if(orderMoney!=0){
					order.setPaystatus(Order.PAY_STATUS_CREATED);
					order.setOrdertype(Order.ORDER_TYPE_PLUS);
					order.setPlusmoney(orderMoney);
				}
			}
			// 1、先将订单信息入库
			order.setAddtime(new Date());
			order.setOpenid(openid);
			order.setOutno(DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS"));
			order.setFee(orderFee);

			if (member != null) {
				if(order.getResultid()==null && member.getPointSum()!=0){
					if(member.getPointSum() - order.getFee()<0){
						jsonResult.setStatus("02");
						jsonResult.setMsg("积分不足");
						return jsonResult;
					}
				}
				order.setMemberid(member.getMemberId());
				order.setMembername(member.getFullName());
				order.setMobilephone(member.getMobilePhone());
			}

			int orderResut = orderMapper.insertSelective(order);
			int orderId = order.getId();

			// 2、再将对应的产品入库
			for (OrderProducts orderProducts : op) {
				//更新产品库的兑换量
				Product product = productMapper.queryById(orderProducts.getProductid());
				product.setProductId(orderProducts.getProductid());
				product.setExchangeNum(product.getExchangeNum()+orderProducts.getNums());
				
				//是否限购商品
				if(product.getExt()!=null && product.getExt().equals("1")){
					if(product.getQuotaNum()!=null && product.getQuotaNum()>0){
						product.setQuotaNum(product.getQuotaNum()-1);
					}
				}
				
				productMapper.updateBySelect(product);
				
				orderProducts.setOrderid(orderId);
				orderProductsMapper.insert(orderProducts);
			}
			
			// 3、清空购物车
			cartService.delShoppingCart(null, openid);

			// 4、插入通知消息
			Information info = new Information();
			info.setReceiveropenid(openid);

			//加钱购时不需要扣除积分
			if(order.getResultid()==null && order.getPlusmoney()!=null && order.getPlusmoney()!=0){
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				jsonResult.setData(order);
				return jsonResult;
			}
			
			//5.扣除会员对应的订单积分,并更新会员积分余额
			if(order.getResultid()==null && member.getPointSum()!=0){
				if(order.getFee()!=null && member.getPointSum() - order.getFee()>=0){
					member.setPointSum(member.getPointSum() - order.getFee());
//					memberMapper.updateByPrimaryKeySelective(member);
					this.memberService.modifyObj(member);
				}else{
					jsonResult.setStatus("02");
					jsonResult.setMsg("积分不足");
					return jsonResult;
				}
				
				//6.记录兑换礼品所消耗的积分
				GiftExchange giftExchange = new GiftExchange();
				giftExchange.setBrandcode(order.getOrderbrand());
				giftExchange.setConsumepoint(order.getFee());
				giftExchange.setCreatetime(new Date());
				giftExchange.setOrderid(orderId);
				giftExchangeMapper.insert(giftExchange);
			}
			
			//7.若是游戏兑换的订单，则更新已兑换信息
			if(isgift!=null){
				//新增：秒杀游戏提交订单
				if(order.getOrdertype().equals(Order.ORDER_TYPE_KILL)){
					SeckillResult seckillResult = new SeckillResult();
					seckillResult.setUpdatetime(new Date());
					Product product = productMapper.queryById(oad.getOrderProductsInfo().get(0).getProductid());
					seckillResult.setProductid(product.getProductId());
					seckillResult.setProductname(product.getProductName());
					seckillResult.setNum(order.getNums());
					seckillResult.setOrderid(orderId);
					seckillResult.setId(order.getResultid());
					seckillResultMapper.updateByPrimaryKeySelective(seckillResult);
				}else {
					example2.clear();
					example2.createCriteria().andIdEqualTo(order.getResultid());
					List<MallgameResult> mallgameResults = gameResultMapper.selectByExample(example2);
					
					Integer gameid = null;
					if(mallgameResults.size()>0){
						gameid = mallgameResults.get(0).getGameid();
					}
					
					MallgameResultExample example = new MallgameResultExample();
					MallgameResultExample.Criteria criteria = example.createCriteria();
					criteria.andOpenidEqualTo(openid);
					criteria.andGameidEqualTo(gameid);
					criteria.andIslotteryEqualTo(1);
					criteria.andProductidIsNotNull();
					criteria.andIsorderIsNull();
					//查询是否有奖品未处理
					List<MallgameResult> list= gameResultMapper.selectByExample(example);
					MallgameResult gameMallgameResult = list.get(0);
					gameMallgameResult.setIsorder(1);
					gameResultMapper.updateByPrimaryKeySelective(gameMallgameResult);
				}
			}else {
				//8.若是积分商城兑换礼品，新增会员积分信息
				MemberPoint memberPoint = new MemberPoint();
				memberPoint.setOpenid(openid);
				memberPoint.setBrandCode(order.getOrderbrand());
				memberPoint.setAddDate(new Date());
				memberPoint.setMobilePhone(member.getMobilePhone());
				memberPoint.setPointsType("E");
				memberPoint.setRelatedTransactionNo("giftmall");
				memberPoint.setPoints(-order.getFee());
//				memberPointMapper.insert(memberPoint);
				this.memberPointService.insert(memberPoint);
			}
			
			if (member != null) {
				info.setReceivermemberid(member.getMemberId());
			}
			info.setCreatetime(new Date());
			info.setType(Information.INFORMATION_TYPE_ORDER);
			info.setStatus(Information.STATUS_UNREADED);
			info.setContent("你买了" + op.size() + "个商品");
			info.setTitle("购物消息");
			this.informationService.addObj(info);
			System.out.println("outNo===============" + order.getOutno());
			if (orderResut != 0) {
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				jsonResult.setData(order);
				
				Store store = StoreUtils.getStoreFromThreadLocal();
				String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
				
				//发送模板消息
				String keyword1 = "";
				String keyword2 = "";
				String keyword3 = "";
				String keyword4 = "";
				String url = "";
				if (order.getOrdertype()!=null && order.getOrdertype().equals(Order.ORDER_TYPE_KILL)) {
					try {
						log.info("+++++ORDER_TYPE_KILL sending msg +++++");
						SeckillResult secResult = seckillResultMapper.selectByPrimaryKey(order.getResultid());
						Seckill seckill = seckillMapper.selectByPrimaryKey(secResult.getSeckillid());
						Product product = productMapper.queryById(oad.getOrderProductsInfo().get(0).getProductid());
						keyword1 = product.getProductName();
						keyword2 = seckill.getNeedpoint()+"";
						keyword3 = member.getPointSum()+"";
						keyword4 = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
						url = domain +"/GiftMall/order_detail.html?type=gift&orderId="+order.getId();
						wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(
								TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_EXCHANGE, openid, keyword1, keyword2, keyword3,
								keyword4, "", null, url);
					} catch (Exception e) {
						log.info("+++++ORDER_TYPE_KILL sending msg error +++++");
						e.printStackTrace();
					}
				}else{
					
					String templateMsg = null;
					if("giftMall".equals(order.getMalltype())){
						templateMsg = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_PAY_POINT;
						url = domain + "/GiftMall/order_detail.html?type=gift&orderId="+order.getId();
					}else{
						templateMsg = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_PAY_POINT;
						url = domain + "/GiftMall_GMX/order_detail.html?type=gift&orderId="+order.getId();
					}
					log.info("+++++积分兑换 发送模板消息 +++++");
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(templateMsg,order.getOpenid(), order.getFee()+"积分 ", "点击后查看详情",
							order.getRegson() + order.getAddress() + "," + order.getMembername() + "," + order.getReceiverphone(), order.getOutno(),
							null, null, url);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			throw new Exception("订单提交异常");
		}
		return jsonResult;
	}
	
	@Override
	public JsonResult<Order> payPoints(String orderNo,String openid) throws Exception {
		JsonResult<Order> jsonResult = new JsonResult<Order>();
		
		try {
			//OrderExample example = new OrderExample();
			//OrderExample.Criteria criteria = example.createCriteria();
			//criteria.andOutnoEqualTo(orderNo);
			//Order order = this.orderMapper.selectByExample(example).get(0);
			Order order = this.orderMapper.selectByPrimaryKey(Integer.valueOf(orderNo));
			
			/*// 1、更新订单
			order.setPaystatus(Order.PAY_STATUS_PAID);
			order.setPaytime(new Date());

			this.orderMapper.updateByPrimaryKey(order);*/
			
			Member member = this.memberService.queryMemberByOpenid(openid);
		
			// 2、插入通知消息
			Information info = new Information();
			info.setReceiveropenid(openid);
			
			//3.扣除会员对应的订单积分,并更新会员积分余额
			if(member.getPointSum()!=0){
				if(member.getPointSum() - order.getFee()>=0){
					member.setPointSum(member.getPointSum() - order.getFee());
//					memberMapper.updateByPrimaryKeySelective(member);
					this.memberService.modifyObj(member);
				}else{
					jsonResult.setStatus("02");
					jsonResult.setMsg("积分不足");
					return jsonResult;
				}
				
				//4.记录兑换礼品所消耗的积分
				GiftExchange giftExchange = new GiftExchange();
				giftExchange.setBrandcode(order.getOrderbrand());
				giftExchange.setConsumepoint(order.getFee());
				giftExchange.setCreatetime(new Date());
				giftExchange.setOrderid(order.getId());
				giftExchangeMapper.insert(giftExchange);
			}
			//积分商城兑换礼品，新增会员积分信息
			MemberPoint memberPoint = new MemberPoint();
			memberPoint.setOpenid(openid);
			memberPoint.setBrandCode(order.getOrderbrand());
			memberPoint.setAddDate(new Date());
			memberPoint.setMobilePhone(member.getMobilePhone());
			memberPoint.setPointsType("E");
			memberPoint.setRelatedTransactionNo("giftmall");
			memberPoint.setPoints(-order.getFee());
//			memberPointMapper.insert(memberPoint);
			this.memberPointService.insert(memberPoint);
			
			if (member != null) {
				info.setReceivermemberid(member.getMemberId());
			}
			info.setCreatetime(new Date());
			info.setType(Information.INFORMATION_TYPE_ORDER);
			info.setStatus(Information.STATUS_UNREADED);
			info.setContent("你买了" + order.getNums() + "个商品");
			info.setTitle("购物消息");
			this.informationService.addObj(info);
			System.out.println("outNo===============" + order.getOutno());
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			jsonResult.setData(order);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			throw new Exception("扣除积分异常");
		}
		return jsonResult;
	}
	
	@Override
	public JsonResult<Order> creategetOrderPointMall2(OrderAndProducts oad,Integer isgift,String openid) throws Exception {
		JsonResult<Order> jsonResult = new JsonResult<Order>();
		
		try {
			Order order = oad.getOrderInfo();
			if (order.getAddressid() == null || order.getAddressid() == 0) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("订单地址不能为空");
				return jsonResult;
			}
			/*if ((order.getFee() == null || order.getFee() == 0) && isgift==null) {
				jsonResult.setStatus("03");
				jsonResult.setMsg("积分总额不能为空或0");
				return jsonResult;
			}*/
			
			order.setAddress(oad.getAddressInfo().getAddress());
			order.setRegson(oad.getAddressInfo().getRegson());
			order.setReceiver(oad.getAddressInfo().getName());
			order.setReceiverphone(oad.getAddressInfo().getPhone());
			
			List<OrderProducts> op = oad.getOrderProductsInfo();
			int orderMoney = 0;
			int orderFee = 0;
			//判断是否存在限购商品，及库存不足
			for (OrderProducts orderProducts : op) {
				Product product = productMapper.queryById(orderProducts.getProductid());
				Integer isOpenDsicountPoint = product.getIsOpenDsicountPoint();
				//开启会员日积分优惠
				if(isOpenDsicountPoint!=null && isOpenDsicountPoint.intValue()==1){
					//判断用户选择了积分加钱还是全额积分,giftbuytype 0全额积分 1加钱购
					if(orderProducts.getGiftBuyType()==0){
						//全额积分
						orderFee+=(product.getDiscountPoint().intValue()*orderProducts.getNums());
					}else{
						if(product.getDiscountGiftPlusPoint()!=null && product.getDiscountPointMoney()!=null){
							orderFee+=(product.getDiscountGiftPlusPoint().intValue()*orderProducts.getNums());
							orderMoney+=(product.getDiscountPointMoney().intValue()*orderProducts.getNums());
						}

					}
				}else{
					if(orderProducts.getGiftBuyType()==0){
						if(product.getGiftPoint()!=null){
							orderFee+=(product.getGiftPoint().intValue()*orderProducts.getNums());
						}
					}else{
						if(product.getGiftPlusPoint()!=null && product.getGiftPointMoney()!=null){
							orderFee+=(product.getGiftPlusPoint().intValue()*orderProducts.getNums());
							orderMoney+=(product.getGiftPointMoney()*orderProducts.getNums());
						}
					}
				}
				if(product.getQuotaNum()!=null&&product.getQuotaNum()==0){
					jsonResult.setStatus("05");
					jsonResult.setMsg(product.getProductName()+"，已被秒完，库存不足");
					return jsonResult;
				}
			}
			
			// 1、先将订单信息入库
			order.setAddtime(new Date());
			order.setOpenid(openid);
			order.setOutno(DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS"));
			order.setPaystatus(Order.PAY_STATUS_CREATED);
			order.setOrdertype(Order.ORDER_TYPE_PLUS);
			order.setFee(orderFee);
			if(orderMoney!=0){
				order.setPlusmoney(orderMoney);
			}

			Member member = this.memberService.queryMemberByOpenid(openid);
			if (member != null) {
				if(member.getPointSum()!=0){
					if(member.getPointSum() - order.getFee()<0){
						jsonResult.setStatus("02");
						jsonResult.setMsg("积分不足");
						return jsonResult;
					}
				}
				order.setMemberid(member.getMemberId());
				order.setMembername(member.getFullName());
				order.setMobilephone(member.getMobilePhone());
			}
			
			int orderResut = orderMapper.insertSelective(order);
			int orderId = order.getId();
			
			// 2、再将对应的产品入库
			for (OrderProducts orderProducts : op) {
				//更新产品库的兑换量
				Product product = productMapper.queryById(orderProducts.getProductid());
				product.setProductId(orderProducts.getProductid());
				product.setExchangeNum(product.getExchangeNum()+orderProducts.getNums());
				
				//是否限购商品
				if(product.getExt()!=null&&product.getExt().equals("1")){
					if(product.getQuotaNum()!=null&&product.getQuotaNum()>0){
						product.setQuotaNum(product.getQuotaNum()-1);
					}
				}
				
				productMapper.updateBySelect(product);
				
				orderProducts.setOrderid(orderId);
				orderProductsMapper.insert(orderProducts);
			}
			
			// 3、清空购物车
			cartService.delShoppingCart(null, openid);
			
			if (orderResut != 0) {
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				jsonResult.setData(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			throw new Exception("订单提交异常");
		}
		return jsonResult;
	}

	@Override
	public JsonResult<Integer> isOverProductQuota(String openid,Integer productid) {
		JsonResult<Integer> jsonResult = new JsonResult<Integer>();
		try {
			String startTimes="";
			String endTimes="";
			Integer recNum = null;
			List<RuleEngineConfig> rulesList = ruleEngineConfigMapper.queryRulesByProductId(productid);
			if(rulesList.size()>0){
				//商品符合规则引擎
				for(RuleEngineConfig rec : rulesList){
					if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_TIME_LIMIT_PROMOPRICE){
						//5,限时促销价
						Date startTime = rec.getStartTime();
						Date endTime = rec.getEndTime();
						Date now = new Date();
						if(now.after(startTime) && now.before(endTime)){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
							startTimes=sdf.format(startTime); 
							endTimes=sdf.format(endTime); 
							recNum = rec.getRestrictionNum();
						}
					}
				}
				
			}
			Map<String, Object> param = new HashMap<String, Object>();
        	param.put("openId", openid);
        	param.put("productId", productid);
        	if(StringUtils.isNotEmpty(endTimes)){
        		param.put("endTime", endTimes);
        	}
        	if(StringUtils.isNotEmpty(startTimes)){
        		param.put("startTime", startTimes);
        	}
        	//筛选此产品的订单
			Integer count = orderMapper.isOverProductQuotas(param);
			
			// 查询商品的限购数量
			Product product = productMapper.queryById(productid);
			Integer productCount = 0;
			if(product != null && (StringUtils.isNotEmpty(product.getExt1()))){
				productCount = Integer.parseInt(product.getExt1());
			}
			if(recNum != null){
				productCount = recNum;
			}
			if(count == null){
				count = 0;
			}
			if (count>=productCount) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("用户已购买过此限购商品，不可重复购买");
				jsonResult.setSize(count);
				jsonResult.setData(null);
				return jsonResult;
			}else{
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				jsonResult.setData(count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
	
	@Override
	public List<Order> getIDList(int id) {
		
		return orderMapper.getIDList(id);
	}

	@Override
	public JsonResult<List<OrderDiscount>> getOrderDiscountInfo(String openid, String orderId) {
		JsonResult<List<OrderDiscount>> jsonResult = new JsonResult<List<OrderDiscount>>();
		
		try {
			Map<String, Object> param = new HashMap<String, Object>();
        	param.put("openid", openid);
        	param.put("orderId", orderId);
			List<OrderDiscount> orderDiscountList = orderDiscountMapper.getOrderDiscountInfo(param);
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			jsonResult.setSize(orderDiscountList.size());
			jsonResult.setData(orderDiscountList);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public JsonResult<Order> getOrderByOutNo(String outNo) {
		JsonResult<Order> jsonResult = new JsonResult<Order>();

		try {
			OrderExample example = new OrderExample();
			OrderExample.Criteria criteria = example.createCriteria();
			criteria.andOutnoEqualTo(outNo);
			List<Order> list = orderMapper.selectByExample(example);
			if(list.size() > 0){
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				jsonResult.setData(list.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public int isFirstTimeOrder(String openid, String startdate) {
		return orderMapper.isFirstTimeOrder(openid, startdate);
	}

}
