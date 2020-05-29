package com.mcoding.emis.goods.wechat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.sms.SmsManager;
import com.mcoding.comp.sms.utils.Supplier;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.common.utils.StringUtil;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.securityQrcode.service.SecurityQrcodeService;
import com.mcoding.emis.goods.sms.service.SendSmsService;
import com.mcoding.emis.goods.wechat.bean.response.NewsMessage;
import com.mcoding.emis.goods.wechat.bean.response.TextMessage;
import com.mcoding.emis.goods.wechat.service.WechatService;
import com.mcoding.emis.goods.wechat.service.WechatWebService;
import com.mcoding.emis.goods.wechat.utils.MessageUtil;
import com.mcoding.emis.goods.wechat.utils.WxJSsign;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.MemberPointExample;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.EmisMemberPointService;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;


/**
 * 微信网页版实现类
 * @author Benson
 */
@Service
public class WechatWebServiceImpl implements WechatWebService {

	private static final Logger log = Logger.getLogger(WechatService.class);
	
	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private SecurityQrcodeService securityQrcodeService;
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberPointService memberPointService;
	@Autowired
	private EmisMemberPointService emisMemberPointService;
	@Autowired
	protected WeixinUserService weixinUserService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreWxRefService storeWxRefService;
	
	private NewsMessage newsMessage;
	
	private TextMessage textMessage;
	
    @Autowired
    private DefaultTransactionDefinition def;

    @Autowired
    private PlatformTransactionManager transactionManager;
	
	@Autowired
	protected SendSmsService sendSmsService;

	@Override
	public List<Product> wechatPointDetailView(String mobilePhone, String brandCode){
		
		List<Product> productList = new ArrayList<Product>();
		try {
//			List<MemberPoint> memberPoints = this.memberPointService.getPointListByPhoneAndBrandCode(mobilePhone, brandCode);
			
			MemberPointExample example = new MemberPointExample();
			example.createCriteria().andMobilePhoneEqualTo(mobilePhone).andBrandCodeEqualTo(brandCode);
			List<MemberPoint> memberPoints = this.emisMemberPointService.queryAllObjByExample(example);
			
			//获取会员积分总额
			//获取会员积分明细信息
			for (int i = 0; i < memberPoints.size(); i++) {
				MemberPoint memberPoint = memberPoints.get(i);
				Product product = new Product();
				product.setProductPoint(memberPoint.getPoints());//积分值
				product.setAddDate(memberPoint.getAddDate());//积分时间
				
				//积分奖励
				if(memberPoint.getPointsType().equals("B")){
					product.setProductName("完善会员资料积分奖励");
				}else if(memberPoint.getPointsType().equals("C")){
					product.setProductName("会员签到积分奖励");
				}else{
					//防伪码不空
					if(memberPoint.getFakeCheckCode()!=null && !memberPoint.getFakeCheckCode().trim().equals("")){
						//获取relatedTransactionNo判断是否为产品码
						String relatedTransactionNo=memberPoint.getRelatedTransactionNo();
						Product product2 = productService.queryByBarCode(relatedTransactionNo);
						if(product2!=null){
							product.setProductName(product2.getProductName());
						}
					}
				}
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}

	@Override
	public Map<String, String> getSignture(String fullPath, String brandCode) throws WxErrorException {
		
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		String jsapi_ticket = wxMpService.getJsapiTicket(false);
		System.out.println("jsapi_ticket===" + jsapi_ticket);

		// 获取完整的URL地址
		Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
		return data;
	}


	@Override
	public CommonResult<String> wechatEditPersonalInfo(Member member) {
		CommonResult<String> result = new CommonResult<String>();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
			//会员资料修改成功
    		Member oldMember = this.memberService.selectByPhoneAndBrandCode( member.getMobilePhone(),member.getBrandCode());
			oldMember.setFullName(member.getFullName());
			oldMember.setGender(member.getGender());
			oldMember.setBirthday(member.getBirthday());
			oldMember.setBrandCode(member.getBrandCode());
			this.memberService.modifyObj(oldMember);
			transactionManager.commit(status);
			result.setData(oldMember.getOpenid());
			result.setCode(0);
            result.setMsg("ok");
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("修改会员资料数据异常：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg("会员资料更新失败");
        }

        return result;
	}

	@Override
	public CommonResult<String> sendSMS(HttpSession session,String phone,String brandCode) {
		String  code = StringUtil.GetRadomCode();
		CommonResult<String> result = new CommonResult<String>();
		String message=null;
		if(brandCode.equals("MRMJ")){
			message = "尊敬的用户：您的验证码为："+code+"，请及时使用。--BIG生活馆+";
		}else {
			message = "尊敬的用户：您的验证码为："+code+"，请及时使用。--BIG生活馆";
		}
		try {
			
			SmsManager chuanglan = SmsManager.getInstance(Supplier.XI_DING);
			chuanglan.sendSingleMsgWithoutReport(phone, message);
			
			//sendSmsService.sendSMS(message, phone);
			session.setAttribute("SMSCode", code);
			result.setData(code);
			result.setCode(0);
            result.setMsg("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("短信发送异常：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommonResult<Member> birthdayClick(Member member) {
		CommonResult<Member> result = new CommonResult<Member>();
		try {
			result.setData(member);
			result.setCode(0);
	        result.setMsg("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("会员信息有异常：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String processPOST(HttpServletRequest request) {
		//user=null;
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 事件类型
			String event = requestMap.get("Event");

			//初始化文本消息
			textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			//初始化图文消息
			newsMessage = new NewsMessage();
			//公众号
			newsMessage.setToUserName(fromUserName);
			//FromUser就是openId
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			
			//获取user
			//user=userMapper.queryUserByOpenId(fromUserName);
			//用户交互||签到
			//WechatUserSign(user,fromUserName);
			System.out.println("msgType:"+msgType);
			System.out.println("event:"+event);
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)&&event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				respMessage=requestMap.toString();
				return respMessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	@Override
	public CommonResult<String> checkSMSCode(HttpSession session, String SMSCode) {
		CommonResult<String> result = new CommonResult<String>();
		String SessionSMSCode = null;
		if(session.getAttribute("SMSCode")!=null){
			SessionSMSCode = session.getAttribute("SMSCode").toString();
		}
		//验证码不正确
		if(SMSCode==null||!SMSCode.equals(SessionSMSCode)){
			log.error("验证码错误");
            result.setCode(3);
            result.setData(null);
            result.setMsg("验证码错误，请重新输入");
		}else {
			result.setCode(0);
            result.setData("");
            result.setMsg("ok");
		}
		return result;
	}

	@Override
	public CommonResult<String> wechatEditPersonalInfoAndRegisterDetail(
			HttpSession session,Member member) {
		CommonResult<String> result = new CommonResult<String>();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
    		//封装tags
    		String concerns =member.getConcerns();
    		String concernsPerson =member.getConcernsPerson();
    		String position =member.getPosition();
    		String healthProblem =member.getHealthProblem();
    		/*ArrayList<String> taglist = new ArrayList<String>();
    		taglist.add(concerns);
    		taglist.add(concernsPerson);
    		taglist.add(position);
    		taglist.add(healthProblem);
    		String tags = StringHelper.listToString(taglist);
    		if(StringHelper.isNotBlank(tags)){
    			tags = tags.substring(0,tags.length()-1);//去除最后的逗号
    		}*/
    		//修改会员资料
    		Member oldMember = null;
    		oldMember = memberService.selectByPhoneAndBrandCode(member.getMobilePhone(),member.getBrandCode());
    		oldMember.setMobilePhone(member.getMobilePhone());
			oldMember.setFullName(member.getFullName());
			oldMember.setGender(member.getGender());
			oldMember.setBirthday(member.getBirthday());
			oldMember.setConcerns(concerns);
			oldMember.setConcernsPerson(concernsPerson);
			oldMember.setPosition(position);
			oldMember.setHealthProblem(healthProblem);
			oldMember.setExt1(member.getExt1());
			oldMember.setExt2(member.getExt2());
			
			MemberPointExample example = new MemberPointExample();
			example.createCriteria()
			       .andMobilePhoneEqualTo(member.getMobilePhone())
			       .andPointsTypeEqualTo("B")
			       .andBrandCodeEqualTo(member.getBrandCode());
			List<MemberPoint> pointList = this.emisMemberPointService.queryAllObjByExample(example);
			
			
//			MemberPoint memberPoint = this.memberPointService.selectByPhoneAndTypeAndBrandCode(member.getMobilePhone(),"B",member.getBrandCode());
			if(CollectionUtils.isEmpty(pointList)){ //未有积分奖励
				//新增本地会员积分
				MemberPoint memberPoint = new MemberPoint();
				Date date = new Date();
				memberPoint.setAddDate(date);
				memberPoint.setMobilePhone(member.getMobilePhone());
				memberPoint.setPoints(100);
				memberPoint.setPointsType("B");
				memberPoint.setOpenid(oldMember.getOpenid());
				memberPoint.setBrandCode(member.getBrandCode());
				memberPoint.setRelatedTransactionNo("memberRegisterAward");
				memberPointService.insert(memberPoint);
				
				//累加会员积分总额
				oldMember.setPointSum(oldMember.getPointSum()+100);
				
				result.setData(oldMember.getOpenid());
				result.setCode(2);
	            result.setMsg("积分成功");
			}else { //会员完善资料奖励已积过分
    			result.setData(oldMember.getOpenid());
				result.setCode(0);
	            result.setMsg("ok");
			}
			this.memberService.modifyObj(oldMember);
			transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("修改会员资料数据异常：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg("会员资料更新失败");
        }

        return result;
	}

	@Override
	public CommonResult<String> registerDetailSubmit(HttpSession session,
			Member t) {
		CommonResult<String> result = new CommonResult<String>();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
    		//System.out.println("openidBenson============"+session.getAttribute("openid"));
    		if(session.getAttribute("openid")!=null){
    			String openid = (String) session.getAttribute("openid");
//    			String openid = "2314";
    			Member member = memberService.queryMemberByOpenid(openid,t.getBrandCode());
    			String concerns =t.getConcerns();
	    		String concernsPerson =t.getConcernsPerson();
	    		String position =t.getPosition();
	    		String healthProblem =t.getHealthProblem();
	    		ArrayList<String> taglist = new ArrayList<String>();
	    		taglist.add(concerns);
	    		taglist.add(concernsPerson);
	    		taglist.add(position);
	    		taglist.add(healthProblem);
	    		String tags = StringHelper.listToString(taglist);
	    		if(StringHelper.isNotBlank(tags)){
	    			tags = tags.substring(0,tags.length()-1);//去除最后的逗号
	    		}
    			
	    		//更新本地会员数据
	    		member.setConcerns(concerns);
    			member.setConcernsPerson(concernsPerson);
    			if(position!=null){
    				member.setPosition(position);
    			}
    			if(healthProblem!=null){
    				member.setHealthProblem(healthProblem);
    			}
    			member.setExt1(t.getExt1());
    			member.setExt2(t.getExt2());
    			
//    			MemberPoint ismemberPoint = memberPointMapper.selectByPhoneAndTypeAndBrandCode(member.getMobilePhone(),"B",member.getBrandCode());
    			String mobilePhone = null;
    			if(StringHelper.isBlank(member.getMobilePhone())){
    				mobilePhone = t.getMobilePhone();
    				member.setMobilePhone(mobilePhone);
    			}else {
    				mobilePhone = member.getMobilePhone();
				}
    			
    			MemberPointExample example = new MemberPointExample();
    			example.createCriteria()
    			       .andMobilePhoneEqualTo(mobilePhone)
    			       .andPointsTypeEqualTo("B")
    			       .andBrandCodeEqualTo(member.getBrandCode());
    			List<MemberPoint> pointList = this.emisMemberPointService.queryAllObjByExample(example);
    			
    			if(CollectionUtils.isEmpty(pointList)){ //未有积分奖励
    				member.setPointSum(member.getPointSum()+100);//注册完善资料奖励积分
	    			//新增本地会员积分
	    			MemberPoint memberPoint = new MemberPoint();
					Date date = new Date();
					memberPoint.setAddDate(date);
					memberPoint.setMobilePhone(mobilePhone);
					memberPoint.setPoints(100);
					memberPoint.setPointsType("B");
					memberPoint.setBrandCode(t.getBrandCode());
					memberPoint.setRelatedTransactionNo("memberRegisterAward");
					memberPoint.setOpenid(openid);
					memberPointService.insert(memberPoint);
					result.setData("");
					result.setCode(0);
					result.setMsg("ok");
    			}else {
    				result.setData("");
					result.setCode(2);
					result.setMsg("禁止重复提交");
				}
    			memberService.updateByPrimaryKey(member);
				transactionManager.commit(status);
				
    		}else {
    			result.setData("");
				result.setCode(1);
				result.setMsg("获取不到微信会员信息");
			}
				
		} catch (Exception e) {
			transactionManager.rollback(status);
			result.setCode(1);
			result.setData(null);
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Map<String,Object> getWXSDKSigntureJson(HttpServletRequest request,String brandCode) {
		Map<String,Object> jsonObject = new HashMap<String, Object>();
		try {
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapi_ticket;
				jsapi_ticket = wxMpService.getJsapiTicket(false);
			System.out.println("jsapi_ticket==="+jsapi_ticket);
			
			//获取完整的URL地址
			String fullPath=BasePath.getFullPath(request);
	        Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
	        jsonObject.put("timestamp", data.get("timestamp"));
	        jsonObject.put("nonceStr", data.get("nonceStr"));
	        jsonObject.put("signature", data.get("signature"));
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
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
	
	private String getDomain(){
		Store store =  StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		return domain;
	}

}
