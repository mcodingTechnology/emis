package com.mcoding.emis.goods.wechat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
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
import com.mcoding.emis.goods.wechat.utils.GetWxOrderno;
import com.mcoding.emis.goods.wechat.utils.MessageUtil;
import com.mcoding.emis.goods.wechat.utils.RequestHandler;
import com.mcoding.emis.goods.wechat.utils.TenpayUtil;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.goods.wechat.utils.WxJSsign;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;

import me.chanjar.weixin.mp.api.WxMpService;

@Controller("EMISWechatPayController")
public class WechatPayController {
	
	@Value("#{sysConfig['mode.debug']}")
	private Boolean openPennyToBuy;
	
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
	protected MemberPurseBalanceService memberPurseBalanceService;
	
	@Autowired
    private OrderProductsMapper orderProductsMapper;
	@Autowired
	private ProductMapper productMapper;
	
//	boolean openPennyToBuy = false;
	
	
	
	/**
	 * 微信支付
	 * @author Leiming
	 * 改用html页面
	 */    
	@RequestMapping("api/wechatYespay2.html")
	public ModelAndView wechatYespay2(ModelAndView mav,HttpServletRequest request,HttpServletResponse response, HttpSession session){
		mav=new ModelAndView();
		System.out.println("=============微信支付html");
		//网页授权后获取传递的参数	
		String orderNo = request.getParameter("orderNo");
		String fee =null;
		if(openPennyToBuy==false){
			fee = request.getParameter("fee");
		}else{
			fee = "1";
		}
		//openPennyToBuy == false : fee = "1" ? request.getParameter("fee");
//		String fee = request.getParameter("fee");
//		String fee = "1";
		String gift = "";
		if(null != request.getParameter("gift")){
			gift = request.getParameter("gift");
		}
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(fee);
		String redirectUrl = getDomain() + "/wMall/pay.html?orderNo="+orderNo+"&fee="+fee+"&gift="+gift;
		mav.setViewName("redirect:" + redirectUrl);
		return mav;
	}
	
	     /**
		 * 积分商城微信支付
		 * @author Leiming
		 */    
		@RequestMapping("api/wechatpayMoney.html")
		public ModelAndView wechatpayMoney(ModelAndView mav,HttpServletRequest request,HttpServletResponse response, HttpSession session){
			System.out.println("========跳转支付页面=======");
			mav=new ModelAndView();
			//网页授权后获取传递的参数	
			String orderNo = request.getParameter("orderNo");//;orderid
			String malltype = request.getParameter("malltype");
//			String fee = request.getParameter("fee");
			Order order = orderService.queryObjById(orderNo).getData();
			String fee = null;
			if(openPennyToBuy==false){
				fee = order.getPlusmoney().toString();
			}else{
				fee = "1";
			}
//			String fee = "1";
			String gift = "";
			if(null != request.getParameter("gift")){
				gift = request.getParameter("gift");
			}
			//金额转化为分为单位
			float sessionmoney = Float.parseFloat(fee);
			String redirectUrl = null;
			if("giftMall".equals(malltype)){
				redirectUrl = getDomain() + "/GiftMall/order_pay.html?orderNo="+orderNo+"&fee="+fee+"&gift="+gift;
			}else{
				redirectUrl = getDomain() + "/GiftMall_GMX/order_pay.html?orderNo="+orderNo+"&fee="+fee+"&gift="+gift;
			}
			mav.setViewName("redirect:" + redirectUrl);
			return mav;
		}
		
		/**
		 * 微信支付---跳转到支付页面
		 * @author Benson
		 * 适用于html静态页面
		 */    
		@RequestMapping("api/wechatGotoPay.html")
		public ModelAndView wechatGotoPay(ModelAndView mav,HttpServletRequest request,HttpServletResponse response, HttpSession session){
			mav=new ModelAndView();
			System.out.println(getDomain()+"=============微信支付html");
			//网页授权后获取传递的参数	
			String orderNo = request.getParameter("orderNo"); 	
			String payhtml = request.getParameter("payhtml");
			String fee = null;
			if(openPennyToBuy==false){
				fee = request.getParameter("fee");
			}else{
				fee = "1";
			}
//			String fee = request.getParameter("fee");
//			String fee = "1";
			String gift = "";
			if(null != request.getParameter("gift")){
				gift = request.getParameter("gift");
			}
			//金额转化为分为单位
			//float sessionmoney = Float.parseFloat(fee);
			//String finalmoney = String.format("%.2f", sessionmoney);
			//finalmoney = finalmoney.replace(".", "");
			String redirectUrl = null;
			if("MRMJ".equals(session.getAttribute("brandCode"))){
				redirectUrl = getDomain() +payhtml+".html?orderNo="+orderNo;
			}else if("JLD".equals(session.getAttribute("brandCode"))){
				redirectUrl = getDomain() +payhtml+".html?orderNo="+orderNo;
			}
			mav.setViewName("redirect:" + redirectUrl);
			return mav;
		}
		
	/**
	 * 微信支付
	 * @author Benson
	 */    
	@RequestMapping("api/wechatYespay3")
	@ResponseBody
	public CommonResult<Hashtable> wechatYespay3(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		//网页授权后获取传递的参数	
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		Hashtable<String, String> map = new Hashtable<>();
		String orderNo = request.getParameter("orderNo"); 	
		String orderType = request.getParameter("orderType");
		System.out.println("orderNo------------------"+orderNo);
		String openid=(String)session.getAttribute("openid");
		Order order = orderService.queryObjById(orderNo).getData();
		String fee = null;
		if(openPennyToBuy==false){
			if("plusgo".equals(order.getOrdertype())){
	    		fee = order.getPlusmoney().toString();
	    	}else {
	    		fee = order.getFee().toString();
			}
		}else{
			fee = "1";
		}
		
		System.out.println("fee--------------"+fee);
		String gift = "";
		if(null != request.getParameter("gift")){
			gift = request.getParameter("gift");
		}
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(fee);

		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;


		//商户号
		String mch_id = getMchId();
		//子商户号  非必输
//				String sub_mch_id="";
		//设备号   非必输
		//String device_info="";
		//随机数
		String nonce_str = strReq;
		//商品描述（根据情况修改）
		String body = "wxMall";
		//附加数据
		String attach = openid;
		//商户订单号
		String out_trade_no = order.getOutno();
		int intMoney = Integer.parseInt(fee);

		//总金额以分为单位，不带小数点
		int total_fee = intMoney;
		//订单生成的机器 IP
		//String spbill_create_ip = request.getRemoteAddr();
		String spbill_create_ip = request.getRemoteAddr();
		if("0:0:0:0:0:0:0:1".equals(spbill_create_ip)){
			spbill_create_ip="112.124.117.209";
		}
		//订 单 生 成 时 间   非必输
//				String time_start ="";
		//订单失效时间      非必输
//				String time_expire = "";
		//商品标记   非必输
//				String goods_tag = "";

		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url =  getDomain() + "api/wechatReturn";
		String trade_type = "JSAPI";
		//非必输
//				String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", getAppid());
		packageParams.put("attach", attach);
		packageParams.put("body", body);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("notify_url", notify_url);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("openid", openid);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		//这里写的金额为1 分到时修改
		System.out.println("finalmoney---------------------------"+fee);
		packageParams.put("total_fee", fee);
//				packageParams.put("total_fee", "finalmoney");   
		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(getAppid(), getAppSecret(), getPartnerKey());

		String sign = reqHandler.createSign(packageParams);
		String xml="<xml>"+
				"<appid>"+getAppid()+"</appid>"+
				"<attach>"+attach+"</attach>"+
				"<body><![CDATA["+body+"]]></body>"+
				"<mch_id>"+mch_id+"</mch_id>"+
				"<nonce_str>"+nonce_str+"</nonce_str>"+
				"<notify_url>"+notify_url+"</notify_url>"+
				"<openid>"+openid+"</openid>"+
				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
//金额，这里写的1 分到时修改
				"<total_fee>"+fee+"</total_fee>"+
//						"<total_fee>"+finalmoney+"</total_fee>"+
				"<trade_type>"+trade_type+"</trade_type>"+
				"<sign>"+sign+"</sign>"+
				"</xml>";
		System.out.println("wechatYespay->sign->"+sign);
		System.out.println("wechatYespay->packageParams->"+packageParams);
		System.out.println("wechatYespay->xml->"+xml);

		String allParameters = "";
		try {
			allParameters =  reqHandler.genPackage(packageParams);

		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id="";
		Map<String, String> prepayMap = GetWxOrderno.getPayNo(createOrderURL, xml);
		prepay_id = prepayMap.get("prepay_id");
		nonce_str = prepayMap.get("nonce_str");
		/*if(prepay_id.equals("")){
			//request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
			mav.setViewName("redirect:http://waimai.0085.com/api/wechatError.html?errorCode=prepayiderror");
			System.out.println("!!!prepay_id_error!!!");
			return mav;
		}*/

		WxMpService wxMpService = getWxMpService();
		String jsapi_ticket = wxMpService.getJsapiTicket(false);
		System.out.println("jsapi_ticket---------"+jsapi_ticket);

		//获取完整的URL地址
		String fullPath=BasePath.getFullPath(request);
		Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
		System.out.println("timestamp--------"+data.get("timestamp"));
		System.out.println("nonceStr-------"+data.get("nonceStr"));
		System.out.println("signature---------"+data.get("signature"));

		map.put("appId", getAppid());
		map.put("timestamp", data.get("timestamp"));
		map.put("nonceStr", data.get("nonceStr"));
		map.put("signature", data.get("signature"));
		//js-SDK微信验证-end

		//js-SDK微信支付
		SortedMap<String, String> packageParams2 = new TreeMap<String, String>();
		packageParams2.put("appId", getAppid());
		packageParams2.put("timeStamp", data.get("timestamp"));
		packageParams2.put("nonceStr", nonce_str);
		packageParams2.put("package", "prepay_id="+prepay_id);
		packageParams2.put("signType", "MD5");

		String paySign = reqHandler.createSign(packageParams2);
		map.put("paySign", paySign);
		map.put("prepay_id", prepay_id);
		map.put("nonceStr2", nonce_str);
		map.put("orderNo", orderNo);
		map.put("orderType", orderType);
		map.put("gift", gift);
		map.put("fee", fee);

		result.setData(map);
		result.setCode(0);
		result.setMsg(openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
//		mav.setView(new RedirectView(PropertiesUtil.getDomain()+"/api/wechatTopay.html?packageParams="+str));
//		return mav;
	}
	
	/**
	 * 钱包充值
	 */    
	@RequestMapping("api/walletSupplement")
	@ResponseBody
	public CommonResult<Hashtable> walletSupplement(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		//网页授权后获取传递的参数	
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		Hashtable<String, String> map = new Hashtable<>();
		String openid=(String)session.getAttribute("openid");
		String fee = request.getParameter("fee");
		
		System.out.println("fee--------------"+fee);
		String gift = "";
		if(null != request.getParameter("gift")){
			gift = request.getParameter("gift");
		}
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(fee);

		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;


		//商户号
		String mch_id = getMchId();
		//子商户号  非必输
//				String sub_mch_id="";
		//设备号   非必输
		//String device_info="";
		//随机数
		String nonce_str = strReq;
		//商品描述（根据情况修改）
		String body = "wxMall";
		//附加数据
		String attach = openid;
		int intMoney = Integer.parseInt(fee);

		//总金额以分为单位，不带小数点
		int total_fee = intMoney;
		//订单生成的机器 IP
		//String spbill_create_ip = request.getRemoteAddr();
		String spbill_create_ip = request.getRemoteAddr();
		if("0:0:0:0:0:0:0:1".equals(spbill_create_ip)){
			spbill_create_ip="112.124.117.209";
		}
		//订 单 生 成 时 间   非必输
//				String time_start ="";
		//订单失效时间      非必输
//				String time_expire = "";
		//商品标记   非必输
//				String goods_tag = "";

		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url =  getDomain() + "api/wechatPay2";
		String trade_type = "JSAPI";
		//非必输
//				String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", getAppid());
		packageParams.put("attach", attach);
		packageParams.put("body", body);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("notify_url", notify_url);
		packageParams.put("out_trade_no", currTime);
		packageParams.put("openid", openid);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		//这里写的金额为1 分到时修改
		System.out.println("finalmoney---------------------------"+fee);
		packageParams.put("total_fee", fee);
//				packageParams.put("total_fee", "finalmoney");   
		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(getAppid(), getAppSecret(), getPartnerKey());

		String sign = reqHandler.createSign(packageParams);
		String xml="<xml>"+
				"<appid>"+getAppid()+"</appid>"+
				"<attach>"+attach+"</attach>"+
				"<body><![CDATA["+body+"]]></body>"+
				"<mch_id>"+mch_id+"</mch_id>"+
				"<nonce_str>"+nonce_str+"</nonce_str>"+
				"<notify_url>"+notify_url+"</notify_url>"+
				"<openid>"+openid+"</openid>"+
				"<out_trade_no>"+currTime+"</out_trade_no>"+
				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
//金额，这里写的1 分到时修改
				"<total_fee>"+fee+"</total_fee>"+
//						"<total_fee>"+finalmoney+"</total_fee>"+
				"<trade_type>"+trade_type+"</trade_type>"+
				"<sign>"+sign+"</sign>"+
				"</xml>";
		System.out.println("wechatYespay->sign->"+sign);
		System.out.println("wechatYespay->packageParams->"+packageParams);
		System.out.println("wechatYespay->xml->"+xml);

		String allParameters = "";
		try {
			allParameters =  reqHandler.genPackage(packageParams);

		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id="";
		Map<String, String> prepayMap = GetWxOrderno.getPayNo(createOrderURL, xml);
		prepay_id = prepayMap.get("prepay_id");
		nonce_str = prepayMap.get("nonce_str");
		/*if(prepay_id.equals("")){
			//request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
			mav.setViewName("redirect:http://waimai.0085.com/api/wechatError.html?errorCode=prepayiderror");
			System.out.println("!!!prepay_id_error!!!");
			return mav;
		}*/

		WxMpService wxMpService = getWxMpService();
		String jsapi_ticket = wxMpService.getJsapiTicket(false);
		System.out.println("jsapi_ticket---------"+jsapi_ticket);

		//获取完整的URL地址
		String fullPath=BasePath.getFullPath(request);
		Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
		System.out.println("timestamp--------"+data.get("timestamp"));
		System.out.println("nonceStr-------"+data.get("nonceStr"));
		System.out.println("signature---------"+data.get("signature"));

		map.put("appId", getAppid());
		map.put("timestamp", data.get("timestamp"));
		map.put("nonceStr", data.get("nonceStr"));
		map.put("signature", data.get("signature"));
		//js-SDK微信验证-end

		//js-SDK微信支付
		SortedMap<String, String> packageParams2 = new TreeMap<String, String>();
		packageParams2.put("appId", getAppid());
		packageParams2.put("timeStamp", data.get("timestamp"));
		packageParams2.put("nonceStr", nonce_str);
		packageParams2.put("package", "prepay_id="+prepay_id);
		packageParams2.put("signType", "MD5");

		String paySign = reqHandler.createSign(packageParams2);
		map.put("paySign", paySign);
		map.put("prepay_id", prepay_id);
		map.put("nonceStr2", nonce_str);
//		map.put("orderNo", orderNo);
//		map.put("orderType", orderType);
		map.put("gift", gift);
		map.put("fee", fee);

		result.setData(map);
		result.setCode(0);
		result.setMsg(openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
//		mav.setView(new RedirectView(PropertiesUtil.getDomain()+"/api/wechatTopay.html?packageParams="+str));
//		return mav;
	}

	/**
	 * 微信支付
	 * @author Leiming
	 */
	@RequestMapping("api/wechatpayNew")
	@ResponseBody
	public CommonResult<Hashtable> wechatpayNew(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		//网页授权后获取传递的参数
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		Hashtable<String, String> map = new Hashtable<>();
		String orderId = request.getParameter("orderId");
		System.out.println("orderId------------------"+orderId);
		String openid=(String)session.getAttribute("openid");
		Order order = orderService.queryObjById(orderId).getData();
		String fee = null;
		if(openPennyToBuy==false){
			if("plusgo".equals(order.getOrdertype())){
				fee = order.getPlusmoney().toString();
			}else {
				fee = order.getFee().toString();
			}
		}else{
			fee = "1";
		}

		System.out.println("fee--------------"+fee);
		String gift = "";
		if(null != request.getParameter("gift")){
			gift = request.getParameter("gift");
		}
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(fee);

		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		//商户号
		String mch_id = getMchId();
		//随机数
		String nonce_str = strReq;
		//商品描述（根据情况修改）
		String body = "wxMall";
		//附加数据
		String attach = openid;
		//商户订单号
		String out_trade_no = order.getOutno();
		int intMoney = Integer.parseInt(fee);

		//总金额以分为单位，不带小数点
		int total_fee = intMoney;
		String spbill_create_ip = request.getRemoteAddr();
		if("0:0:0:0:0:0:0:1".equals(spbill_create_ip)){
			spbill_create_ip="112.124.117.209";
		}
		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url =  getDomain() + "api/wechatReturn";
		String trade_type = "JSAPI";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", getAppid());
		packageParams.put("attach", attach);
		packageParams.put("body", body);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("notify_url", notify_url);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("openid", openid);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("total_fee", fee);
		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(getAppid(), getAppSecret(), getPartnerKey());

		String sign = reqHandler.createSign(packageParams);
		String xml="<xml>"+
				"<appid>"+getAppid()+"</appid>"+
				"<attach>"+attach+"</attach>"+
				"<body><![CDATA["+body+"]]></body>"+
				"<mch_id>"+mch_id+"</mch_id>"+
				"<nonce_str>"+nonce_str+"</nonce_str>"+
				"<notify_url>"+notify_url+"</notify_url>"+
				"<openid>"+openid+"</openid>"+
				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
				"<total_fee>"+fee+"</total_fee>"+
				"<trade_type>"+trade_type+"</trade_type>"+
				"<sign>"+sign+"</sign>"+
				"</xml>";
		System.out.println("wechatYespay->xml->"+xml);
		try {
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String prepay_id="";
			Map<String, String> prepayMap = GetWxOrderno.getPayNo(createOrderURL, xml);
			prepay_id = prepayMap.get("prepay_id");
			nonce_str = prepayMap.get("nonce_str");
			System.out.println("prepay_id--------"+prepay_id);
			if(StringHelper.isBlank(prepay_id)){
				result.setData(null);
				result.setCode(1);
				result.setMsg("微信支付接口请求失败，请重新尝试");
				return result;
			}

			long timestamp = System.currentTimeMillis() / 1000;
			//js-SDK微信支付
			SortedMap<String, String> packageParams2 = new TreeMap<String, String>();
			packageParams2.put("appId", getAppid());
			packageParams2.put("timeStamp", String.valueOf(timestamp));
			packageParams2.put("nonceStr", nonce_str);
			packageParams2.put("package", "prepay_id="+prepay_id);
			packageParams2.put("signType", "MD5");

			String paySign = reqHandler.createSign(packageParams2);
			map.put("paySign", paySign);
			map.put("prepay_id", prepay_id);
			map.put("timestamp", String.valueOf(timestamp));
			map.put("nonceStr", nonce_str);
			map.put("orderId", orderId);
			map.put("gift", gift);
			map.put("fee", fee);

			result.setData(map);
			result.setCode(0);
			result.setMsg(openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
//		mav.setView(new RedirectView(PropertiesUtil.getDomain()+"/api/wechatTopay.html?packageParams="+str));
//		return mav;
	}
	

	/**
	 * ⑤微信支付回调刷新订单状态
	 * @author zhengyh
	 */
	@ResponseBody
	@RequestMapping("api/wechatReturn")
	public String wechatPay(HttpServletRequest request){
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			Order bean= orderService.getOrderByOutNo(requestMap.get("out_trade_no").toString()).getData();
			if(bean.getPaystatus().equals("待支付")){
				System.err.println("【wechatReturn微信回调】->"+requestMap.toString());
				if(("SUCCESS".equals(requestMap.get("result_code")))&&("SUCCESS".equals(requestMap.get("return_code")))){

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
					System.err.println("【wechatReturn微信回调-out_trade_no】->"+requestMap.get("out_trade_no"));
					bean.setPaytime(new Date());
					bean.setPaystatus(Order.PAY_STATUS_PAID);
					bean.setThirdno(requestMap.get("transaction_id"));
					bean.setTradeno(bean.getOutno());
					orderService.modifyObj(bean);

					//加钱购的订单扣除对于的积分与更新积分记录
					if(bean.getPlusmoney()!=null && bean.getPlusmoney()>0){
						boolean result= updateGiftMallOrder(bean);
						if(result==false){
							return "会员积分不足，扣除积分失败";
						}
					}
					
					System.out.println(bean.getFee());
					System.out.println("订单编号========"+bean.getOutno());
					
					// 根据订单编号查询订单产品  修改产品库存
					updateProuductsQuotaNum(bean);
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

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
		return "success";
	}
	
	/**
	 * ⑤钱包充值公众号支付回调
	 */
	@ResponseBody
	@RequestMapping("api/wechatPay2")
	public String wechatPay2(HttpServletRequest request){
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			if(("SUCCESS".equals(requestMap.get("result_code")))){
				String totalFee = requestMap.get("total_fee");
				String openId = requestMap.get("openid");
				System.out.println("totalFee--"+totalFee+"  openId---"+openId);
				MemberPurseBalance memberPurseBalance = new MemberPurseBalance();
				memberPurseBalance.setOpenId(openId);
				memberPurseBalance.setBalance(Integer.valueOf(totalFee));
				memberPurseBalance.setAction("A");
				memberPurseBalance.setRemake("钱包充值");
				memberPurseBalanceService.addMemberPurseBalance(memberPurseBalance);
			}

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return "success";
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
	
	private String getMchId(){
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		return this.storeWxRefService.queryWxAccountByStoreId(storeId).getMchId();
	}
	
	private String getPartnerKey() {
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		return this.storeWxRefService.queryWxAccountByStoreId(storeId).getMchKey();
	}
	
	private WxMpService getWxMpService(){
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		return this.storeWxRefService.queryWxMpServiceByStoreId(storeId);
	}
}
