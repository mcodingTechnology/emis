package com.mcoding.emis.goods.wechat.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mcoding.emis.goods.common.utils.SHA1;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.wechat.bean.WxJSCardSignture;
import com.mcoding.emis.goods.wechat.service.WechatService;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.goods.wechat.utils.WxCardSign;
import com.mcoding.emis.goods.wechat.utils.WxJSsign;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 微信模块控制器
 * @author Benson
 */
@Controller
public class WechatController {

	@Autowired
	private WechatService wechatService;
	@Autowired
	protected CardService cardService;
	@Autowired
	protected CardTypeService cardTypeService;
	@Autowired
	protected CardMapper cardMapper;
	@Autowired
	protected StoreWxRefService storeWxRefService;
	
	/**关注成功**/
	private static final int WECHAT_USER_FOCUSE_SUCCESS = 0;
	
	/**尚未关注**/
	private static final int WECHAT_USER_FOCUSE_NO = 1;
	
//	public static final String IMGAGE_FOCUS_MRMJ = PropertiesUtil.getDomain()+"/resources/images/mrmj_wechat_miniqrcode.png";
//	public static final String IMAGE_FOCUS_GMX = PropertiesUtil.getGMXdomain() + "/resources/images/gymmax_miniqrcode.png";
	
	//商户相关资料,从配置文件读取
//	String appid = PropertiesUtil.getAppid();
//	String appsecret = PropertiesUtil.getAppsecret();
//	String partner = PropertiesUtil.getPartnerid();
//	String partnerkey = PropertiesUtil.getPartnerkey();
	

	/**
	 * 微信分享
	 * @author zhengyh
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping("api/wechatShare")
	@ResponseBody
	public CommonResult<Hashtable> wechatShare(HttpServletRequest request,String fullPath, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException{
		String brandCode=(String) request.getSession().getAttribute("brandCode");
		String openid=(String)session.getAttribute("openid");
		Map<String, String> params = new Hashtable<>();
		request = com.mcoding.emis.goods.common.utils.HttpRequestParser.parse(fullPath);
		Map<String, String> map = request.getParameterMap();
		if(request.getParameterMap()!=null){
			for (Map.Entry<String, String> entry : map.entrySet()) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		return wechatService.wechatShare(brandCode,fullPath,openid,params);

	}
	
	/**
	 * 微信分享
	 * @author zhengyh
	 * @throws Exception 
	 */    
	@RequestMapping("api/wechatShare2")
    @ResponseBody
	public CommonResult<Hashtable> wechatShare2(String fullPath, ModelAndView mav,HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
		String brandCode=(String) request.getSession().getAttribute("brandCode");
		System.out.println("==============================wechatShare2==brandcode==="+brandCode);
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
		String openid=(String)session.getAttribute("openid");
		return wechatService.wechatShare2(brandCode, fullPath, openid);
	}
	
	/**
	 * 微信分享
	 * @author zhengyh
	 * @throws Exception 
	 */    
	@RequestMapping("api/wechatShare3")
	@ResponseBody
	public CommonResult<Hashtable> wechatShare3(String fullPath, ModelAndView mav,HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
		String brandCode=(String) request.getSession().getAttribute("brandCode");
		System.out.println("==============================wechatShare3==brandcode==="+brandCode);
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
		String openid=(String)session.getAttribute("openid");
		return wechatService.wechatShare3(brandCode, fullPath, openid);
	}

	/**
	 * 获取微信JSSDK配置
	 * @author Benson
	 */
	@RequestMapping("api/wechatJSConfigParams")
	@ResponseBody
	public CommonResult<Hashtable> wechatJSConfigParams(String fullPath){
		return wechatService.wechatJSConfigParams(fullPath);

	}

	/**
	 * 微信授权获取用户信息
	 * @author Benson
	 * @throws Exception
	 */
	@RequestMapping("api/wechatAuthorizeUserInfo")
	@ResponseBody
	public ModelAndView wechatAuthorizeUserInfo(String fullPath,String memberid,HttpServletRequest request) throws Exception{
		Map<String, String> params = new Hashtable<>();
		request = com.mcoding.emis.goods.common.utils.HttpRequestParser.parse(fullPath);
		if(request.getParameter("memberid")!=null){
			memberid = request.getParameter("memberid");
			params.put("memberid",memberid);
		}
		return wechatService.wechatAuthorizeUserInfo(fullPath,params);
	}

	/**
	 * 微信分享
	 * @author zhengyh
	 * @throws Exception
	 */
	@RequestMapping("api/createWechatShareUrl.html")
	@ResponseBody
	public CommonResult<Hashtable> createWechatShareUrl(String targetUrl, ModelAndView mav,HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
		String brandCode=(String) request.getSession().getAttribute("brandCode");
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
		String openid=(String)session.getAttribute("openid");
		return wechatService.createWechatShareUrl(brandCode, targetUrl, openid);
	}
	
	/**
	 * 微信分享跳转页面
	 * @author zhengyh
	 */
	@RequestMapping("api/wechatShareJump")
	public ModelAndView wechatShareJump(String brandCode, HttpServletRequest request,HttpSession session,String openid,ModelAndView mav) throws Exception {
		mav=new ModelAndView();

		System.out.println("微信分享跳转页面brandCode|shareId："+brandCode);
		mav.setViewName("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+getAppid()+"&redirect_uri="+getDomain()+"/api/wechatCheckWxUser2.html?brandCode="+brandCode+"&response_type=code&scope=snsapi_userinfo&state="+getDomain() + "/api/wechatTest.html#wechat_redirect");
		
		return mav;
	}
	
	/**
	 * 微信分享跳转页面
	 * @author zhengyh
	 */
	@RequestMapping("api/wechatShareJumpGMX")
	public ModelAndView wechatShareJumpGMX(String brandCode, HttpServletRequest request,HttpSession session,String openid,ModelAndView mav) throws Exception {
		mav=new ModelAndView();
		
		System.out.println("微信分享跳转页面brandCode|shareId："+brandCode);
		mav.setViewName("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+getAppid()+"&redirect_uri="+getDomain()+"/api/wechatCheckWxUser2.html?brandCode="+brandCode+"&response_type=code&scope=snsapi_userinfo&state="+getDomain() + "/api/wechatTestGMX.html#wechat_redirect");
		
		return mav;
	}
	
	@RequestMapping("api/wechatTopay.html")
	public ModelAndView wechatTopay(HttpServletRequest request,String packageParams) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println("支付页面跳转ing......");

		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		//String jsapi_ticket = wxMpService.getJsapiTicket(false);
		//System.out.println("jsapi_ticket==="+jsapi_ticket);
		//获取完整的URL地址
		String fullPath=BasePath.getFullPath(request);
		WxJsapiSignature signature = wxMpService.createJsapiSignature(fullPath);
       // Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
        System.out.println("timestamp==="+signature.getTimestamp());
        System.out.println("nonceStr==="+signature.getNonceStr());
        System.out.println("signature==="+signature.getSignature());
        mav.addObject("timestamp", signature.getTimestamp());
        mav.addObject("nonceStr", signature.getNonceStr());
        mav.addObject("signature", signature.getSignature());
		
		mav.addObject("packageParams", packageParams);
		mav.setViewName("wechat/wechatTopay");
		return mav;
	}

	
	/**
	 * 前端调用 ——获取卡券列表接口
	 * @param request
	 * @return WxJSCardSignture
	 * @throws Exception
	 */
	@RequestMapping("api/getWechatCardList")
	@ResponseBody
	public JsonResult<WxJSCardSignture> getWechatCardList(HttpServletRequest request, @RequestParam(required=true)String cardId) throws Exception {
		JsonResult<WxJSCardSignture> commonResult = new JsonResult<WxJSCardSignture>();
		String brandCode = (String) request.getSession().getAttribute("brandCode");
		
		WxJSCardSignture wxJSCardSignture = new WxJSCardSignture();
		cardId = request.getParameter("cardId");
		String appid = getAppid();
		String appsecrect =getAppSecret();
        if(StringUtils.isBlank(cardId)){
//        	cardId = "pwDHujscIkFVB_GLZrMveyTHQzeA";
        	commonResult.setStatus("error");
	        commonResult.setMsg("cardId 不能为空");
        }
        String cardType = "CASH";
        System.out.println("cardId==="+cardId);
		try {
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapi_ticket = wxMpService.getJsapiTicket(false);
			System.out.println("jsapi_ticket==="+jsapi_ticket);
			
			String nonce_str = WxJSsign.create_nonce_str();
	        String timestamp = WxJSsign.create_timestamp();
	        //获取完整的URL地址
//			String fullPath=BasePath.getFullPath(request);
	        String fullPath = request.getParameter("url");
			
	        System.out.println("jsapi_ticket==="+jsapi_ticket);
	        System.out.println("timestamp==="+ timestamp);
	        System.out.println("nonceStr==="+ nonce_str);
	        System.out.println("fullPath==="+ fullPath);
			
	        Map<String, String> jsApiSign = WxJSsign.signForJsSdk(nonce_str, jsapi_ticket, timestamp, fullPath);
	        System.out.println("jsapiSignature==="+jsApiSign.get("signature"));
	        
	        String wxCardTicket = WeixinUtil.getCardTicket(brandCode);
	        System.out.println("wxCardTicket==="+wxCardTicket);
	        
	        //Map<String, String> cardSign = WxJSsign.signForCard(wxCardTicket, timestamp, cardId, cardCode, openid, nonce_str);
	        WxCardSign signer = new WxCardSign();
	        signer.AddData(wxCardTicket);
	        signer.AddData(appid);
	        signer.AddData(timestamp);
	        signer.AddData(cardId);
	        signer.AddData(nonce_str);
	        signer.AddData(cardType);
	        
	        System.out.println("wxCardTicket==="+wxCardTicket);
	        System.out.println("timestamp==="+timestamp);
	        System.out.println("cardId==="+cardId);
	        System.out.println("cardType==="+cardType);
	        System.out.println("nonce_str==="+nonce_str);
	        System.out.println("appid==="+appid);
	        System.out.println("cardSign==="+signer.GetSignature());
	        
	        wxJSCardSignture.setAppId(appid);
	        wxJSCardSignture.setCardId(cardId);
	        wxJSCardSignture.setCardSign(signer.GetSignature());
	        wxJSCardSignture.setCardType(cardType);
	        wxJSCardSignture.setNonceStr(nonce_str);
	        wxJSCardSignture.setTimestamp(timestamp);
	        wxJSCardSignture.setSignature(jsApiSign.get("signature"));
	        
	        System.out.println("wxJSCardSignture=================="+wxJSCardSignture);
	        commonResult.setStatus("00");
	        commonResult.setData(wxJSCardSignture);
	        commonResult.setMsg("success");
		} catch (Exception e) {
			e.getStackTrace();
			commonResult.setStatus("error");
	        commonResult.setData(null);
	        commonResult.setMsg("fail");
		}
//		System.out.println("getCode=================="+commonResult.getCode());
		System.out.println("getMsg=================="+commonResult.getMsg());
		return commonResult;
	}
	
	@RequestMapping("api/getWeixinJsParam")
	@ResponseBody
	public JsonResult<Map<String, String>> getWeixinJsParam(@RequestParam(required=true) String url){
		JsonResult<Map<String, String>> result = new JsonResult<>();
		result.setStatus("00");
        result.setMsg("ok");
		try {
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapiTicket = wxMpService.getJsapiTicket();
			WxJsapiSignature jsSign = wxMpService.createJsapiSignature(url);
			
			Map<String, String> jsapiParams = new Hashtable<>();
			
			jsapiParams.put("appId", getAppid());
			jsapiParams.put("timestamp", String.valueOf(jsSign.getTimestamp()));
			jsapiParams.put("nonceStr", jsSign.getNonceStr());
			jsapiParams.put("signature", jsSign.getSignature());
			jsapiParams.put("ticket", jsapiTicket);
			
			result.setData(jsapiParams);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg("ok");
		}
		
		return result;
	}
	
	@RequestMapping("api/getWeixinJsParamForCard")
	@ResponseBody
	public JsonResult<WxJSCardSignture> getWeixinJsParamForCard(@RequestParam(required=true) String url, HttpServletRequest request) {
		JsonResult<WxJSCardSignture> result = new JsonResult<>();
		String brandCode = (String) request.getSession().getAttribute("brandCode");
		try {
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			WxJsapiSignature jsSign = wxMpService.createJsapiSignature(url);
			
			String wxCardTicket = WeixinUtil.getCardTicket(brandCode);

			WxCardSign signer = new WxCardSign();
	        signer.AddData(wxCardTicket);
	        signer.AddData(getAppid());
	        signer.AddData(String.valueOf(jsSign.getTimestamp()));
	        signer.AddData(jsSign.getNonceStr());
	        String cardSign = signer.GetSignature();
	        
	        WxJSCardSignture wxJSCardSignture = new WxJSCardSignture();
	        wxJSCardSignture.setAppId(getAppid());
	        wxJSCardSignture.setCardSign(cardSign);
	        wxJSCardSignture.setNonceStr(jsSign.getNonceStr());
	        wxJSCardSignture.setTimestamp(String.valueOf(jsSign.getTimestamp()));
	        wxJSCardSignture.setSignature(jsSign.getSignature());


	        result.setStatus("00");
	        result.setMsg("ok");
	        result.setData(wxJSCardSignture);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg(e.getMessage());
			result.setData(null);
		}
        
        return result;
	}
	
	/**
	 * 获取卡券 卡码
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("api/getCardCode")
	@ResponseBody
	public JsonResult<List<String>> getCardCode(HttpServletRequest request, @RequestBody CardType[] cardList) {
		JsonResult<List<String>> result = new JsonResult<List<String>>();
		String openId = (String)request.getSession().getAttribute("openid");
		//TODO删除测试代码
//		String appid = "wxc29d38541362f295";
//		String appsecrect = "c9da3fc852d9fe9aa3440a9622834ad4";
		
		String appid =getAppid();
		String appsecrect = getAppSecret();
		
		String js_accessToken = WeixinUtil.getJSSDKAccessToken(appid, appsecrect);  //获取微信jssdk---access_token
		System.out.println("token:" + js_accessToken);
		
		List<String> codeList = new ArrayList<String>();
		
		try {
			for(int i=0; i<cardList.length; i++){
				System.out.println("cardId:"+cardList[i].getCardid()+",eCode:" + cardList[i].getEncryptCode());
				
				String code = WeixinUtil.encryptCardCode(js_accessToken, cardList[i].getEncryptCode());
				codeList.add(code);
				
				System.out.println("cardId:"+cardList[i].getCardid()+",code:" + code);
				CardExample cardExample = new CardExample();
				cardExample.createCriteria().andCardcodeEqualTo(code);
				int count = this.cardMapper.countByExample(cardExample);
				
				if(count == 0){
					CardTypeExample example = new CardTypeExample();
					example.createCriteria().andCardidEqualTo(cardList[i].getCardid());
					
					CommonResult<ArrayList<CardType>> list = this.cardTypeService.queryObjByExample(example);
					CardType cardType = list.getData().get(0);
					
					Card card = new Card();
					card.setCardtypeid(cardType.getId());
					card.setCardtypename(cardType.getName());
					card.setCardcode(code);
					card.setCreatetime(new Date());
					card.setOpenid(openId);
					card.setIsvalid(CardType.IS_VALID_YES);
					card.setExt1(Card.CARD_STATUS_EXCAHGED);
					
					this.cardService.addObj(card);
				}
				
			}
			
			result.setStatus("00");
			result.setData(codeList);
			result.setMsg("ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 根据openid判断微信用户是否已关注
	 * @param request
	 * @return
	 */
	@RequestMapping("/wechatApi/isWechatUserFocused")
	@ResponseBody
	public CommonResult<String> isWechatUserFocused(HttpServletRequest request){
		CommonResult<String> result = new CommonResult<String>();
		System.out.println("server_name="+request.getServerName());
		System.out.println("brandcode="+request.getSession().getAttribute("brandCode"));
		String imgUrl = null;
		try{
			String openid = (String) request.getSession().getAttribute("openid");
			String brandCode = (String) request.getSession().getAttribute("brandCode");
			System.out.println("openid="+openid);
			if(openid==null){
				result.setCode(7);
				result.setMsg("openid不能为空");
				System.out.println("msg=openid不能为空");
				return result;
			}
			
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());


			if("MRMJ".equals(brandCode)){
				imgUrl = getDomain()+"/resources/images/mrmj_wechat_miniqrcode.png";
			}else if("JLD".equals(brandCode)){
				imgUrl = getDomain() + "/resources/images/gymmax_miniqrcode.png";;
			}
			if(!wxMpService.getUserService().userInfo(openid, "zh_CN").getSubscribe()){
				result.setCode(WECHAT_USER_FOCUSE_NO);
				result.setData(imgUrl);
				result.setMsg("对不起，您尚未关注");
				System.out.println("msg=对不起，您尚未关注");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);//操作失败
			result.setData(null);
			result.setMsg(e.getMessage());
			return result;
		}
		result.setCode(WECHAT_USER_FOCUSE_SUCCESS);//正常时间范围并且操作成功
		System.out.println("msg="+"ok");
		result.setData(imgUrl);
		result.setMsg("ok");
		return result;
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
