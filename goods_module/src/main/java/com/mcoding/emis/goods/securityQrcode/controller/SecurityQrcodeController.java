package com.mcoding.emis.goods.securityQrcode.controller;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.securityQrcode.service.SecurityQrcodeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.goods.securityQrcode.service.impl.MemberPointDealerBatchUpdater;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 防伪二维码管理
 * 
 * @author BensonCheung
 */
@Controller
public class SecurityQrcodeController {
    @Autowired
    private SecurityQrcodeService securityQrcodeService;
    
    @Autowired
    protected StoreWxRefService storeWxRefService;
//    @Autowired
//    private WechatWebService wechatWebService;
    
	/** 
	  * 重定向到指定的路径
	  * @return 
	  * @author Benson 
	*/ 
	@RequestMapping("/c/")
	public ModelAndView redirectToQrcodeURL(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		//获取URL的第一个参数
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";  
		int i =0 ;
		for (String key : params.keySet()) {
			if(i==0){
				queryString += key ;  
			}
			i++;
        }
		String fullUrlPath = BasePath.getFullPath(request);
		System.out.println(BasePath.getBasePath(request));
		//BIG生活防伪
		if(fullUrlPath.contains("gymmaxer")==true){
			mav.setViewName("redirect:"+BasePath.getBasePath(request) + "/gMall/member_scancode.html?securityQrcode="+queryString);
		}else {
			//极智构防伪
			mav.setViewName("redirect:"+BasePath.getBasePath(request) + "/wMall/integration.html?securityQrcode="+queryString);
		}
		return mav;
	}
	
	/** 
	  * 跳转极智构扫码主页的方法
	  * <p>方法说明:</p> 
	  * @param SecurityQrcode
	  * @return ModelAndView 
	  * @author BensonCheung 
	  * @date 2015-3-11 上午9:55:58
	*/ 
	@RequestMapping("/front/indexView")
	public ModelAndView toNavigateIndexView(String SecurityQrcode) {
		System.out.println(SecurityQrcode);
		ModelAndView mav = new ModelAndView();
		mav.addObject("securityQrcode", SecurityQrcode);
		mav.setViewName("frontPageView/securityQrcode/index");
		return mav;
	}
	
	/** 
	  * 跳转BIG生活扫码主页的方法
	  * <p>方法说明:</p> 
	  * @param SecurityQrcode
	  * @return ModelAndView 
	  * @author BensonCheung 
	  * @date 2015-3-11 上午9:55:58
	*/ 
	@RequestMapping("/front/indexViewGymmax")
	public ModelAndView indexViewGymmax(String SecurityQrcode) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("securityQrcode", SecurityQrcode);
		mav.setViewName("frontPageView/gymmax/securityQrcode/indexGymmax");
		return mav;
	}
	
	/** 
	  * 跳转扫码主页的方法
	  * <p>方法说明:</p> 
	  * @param SecurityQrcode
	  * @return ModelAndView 
	  * @author BensonCheung 
	  * @date 2015-3-11 上午9:55:58
	*/ 
	@RequestMapping("/front/wxScane")
	public ModelAndView wxScane(String SecurityQrcode,HttpServletRequest request) {
		System.out.println(SecurityQrcode);
		ModelAndView mav = new ModelAndView();
		mav.addObject("securityQrcode", SecurityQrcode);
		
		//获取微信JSSDK签名，用于调用微信扫一扫
		String brandCode = (String) request.getSession().getAttribute("brandCode");
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
//		wechatWebService.getSignture(request, mav, brandCode);
		
		mav.setViewName("frontPageView/securityQrcode/wxScane");
		return mav;
	}

	/** 
	  * 扫码时校验防伪二维码的 方法 
	  * <p>方法说明:</p> 
	  * @param securityQrcode
	  * @return ModelAndView 
	  * @author BensonCheung 
	  * @date 2015-3-11 上午9:56:13
	*/ 
	@RequestMapping("/front/checkQrcode")
	@ResponseBody
	public CommonResult<String> toNavigateQrcodeView(HttpServletRequest request, String securityQrcode) {
		return securityQrcodeService.checkQrcode(securityQrcode);
	}
	
	/** 
	  * 跳转积分成功页面
	  * @return ModelAndView 
	  * @author BensonCheung 
	  * @date 2015-3-11 上午9:55:58
	*/ 
	@RequestMapping("/front/result")
	public ModelAndView toNavigateResultView(HttpServletRequest request,String msg,Member member) {
		ModelAndView mav = new ModelAndView();
		try {
			System.out.println("=====================准备跳转积分页面"+msg);
			//获取微信JSSDK签名，用于调用微信扫一扫
//			String scheme = request.getScheme();
//			String serverName = request.getServerName();
//			int serverPort = request.getServerPort();
//			String requestUri = request.getRequestURI();
			
//			WxJsapiSignature jsapiSignature = this.storeWxRefService.getJsapiSignature(scheme, serverName, serverPort, requestUri);
			String queryString = request.getQueryString(); 
			//获取URL地址
			StringBuffer url = request.getRequestURL();
			String fullPath=null;
			//URL地址+参数串
			if(queryString!=null){
				fullPath = url +"?"+ queryString;
			}else {
				fullPath= url.toString();
			}
			
			Integer storeId = (Integer) request.getSession().getAttribute("storeId");
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(storeId);
			WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(fullPath);
			mav.addObject("timestamp", jsapiSignature.getTimestamp());
	        mav.addObject("nonceStr", jsapiSignature.getNonceStr());
	        mav.addObject("signature", jsapiSignature.getSignature());
			
		    if (msg.equals("0")) {
				mav.addObject("isPointed", 0);//返回积分状态
				mav.addObject("productName", member.getExt2());
			}else if(msg.equals("3")){
				mav.addObject("isPointed", 1);
				mav.addObject("isGift",1);//赠品
				
			}else if(msg.equals("5")){
				mav.addObject("isPointed", 1);
				mav.addObject("isGift",0);//非赠品
			}
		    mav.addObject("pointSum", member.getPointSum());
		    mav.addObject("point", member.getExt1());
		    if(member.getFullName()!=null&&member.getFullName().equals("null")){
		    	mav.addObject("fullName",null);
		    }else {
		    	mav.addObject("fullName", member.getFullName());
			}
		    mav.addObject("mobilePhone", member.getMobilePhone());
		    if(member.getBrandCode().equals("MRMJ")){
		    	mav.setViewName("frontPageView/securityQrcode/result");
		    }else {
		    	mav.setViewName("frontPageView/gymmax/securityQrcode/resultGymmax");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return mav;
	}
	
	/** 
	  * 显示错误信息页面
	  * <p>方法说明:</p> 
	  * @return ModelAndView
	  * @author BensonCheung 
	  * @date 2015-3-11 上午9:55:58
	*/ 
	@RequestMapping("/front/errorPage")
	public ModelAndView toNavigateErrorPageView(String msg,String brandCode) {
		ModelAndView mav = new ModelAndView();
		if(msg.equals("1")){
			mav.addObject("errorMessage", "07");
		}else{
			mav.addObject("errorMessage", "08");
		}
		if(brandCode.equals("MRMJ")){
			mav.setViewName("frontPageView/securityQrcode/help");
		}else {
			mav.setViewName("frontPageView/gymmax/securityQrcode/helpGymmax");
		}
		return mav;
	}

	/***
	 * 校验积分是否异常，不存在该产品
	 * **/
	@RequestMapping("/validateQrcode")
	public  CommonResult<String> validateQrcode(String phone){
		return securityQrcodeService.validateQrcode(phone);
	}

	@RequestMapping("/front/updateQrcodeDealer")
	@ResponseBody
	public JsonResult<String> updateQrcodeDealer(){
		AccountConfig accountConfig = storeWxRefService
				.queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());

		MemberPointDealerBatchUpdater.getInstance(accountConfig).start();

		JsonResult<String> result = new JsonResult<>();
		result.setData("ok");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
}
