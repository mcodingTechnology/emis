package com.mcoding.emis.goods.wechat.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.wechat.service.WechatService;
import com.mcoding.emis.goods.wechat.utils.EmisOauthUrlUtils;
import com.mcoding.emis.member.service.member.WeixinUserService;

/**
 * SSO控制器
 * @author Benson
 */
@Controller
public class SSOController {
	private User user;
	@Autowired
	private UserService userService;
//	@Autowired
//	private UserMapper userMapper;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private WechatService wechatService;
	@Autowired
	protected StoreWxRefService storeWxRefService;
	
	/*@RequestMapping("api/sso_login.html")
	public ModelAndView sso_login(String openid,String model,String view,ModelAndView mav){
		mav=new ModelAndView();
//		user=userMapper.queryUserByOpenId(openid);
		user = this.userServic
		mav.setViewName(model+"/"+view);
		return mav;
	}*/
	
	
	/**
	  * 跳转微信页面时，获取OpenId校验是否已授权过
	 * 测试地址：http://v.merryplus.com/api/getWechatCode.html?actionPage=personal
	 * @author Benson
	 */
	@RequestMapping("api/getWechatCode.html")
	public ModelAndView wechatCheckAuthorize(String actionPage, HttpServletRequest request) {
//		return wechatService.wechatCheckAuthorize(actionPage);
		String portStr = request.getServerPort() == 80 ?"":":" + request.getServerPort();
		String domain = request.getScheme() + "://" + request.getServerName() + portStr + request.getContextPath();
		
		String url = domain + "/front/wechatPersonalCenterView.html";
		try {
			url = EmisOauthUrlUtils.createOauthUrlForOpenid(url, null, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:" + url);
		return view;
	}
	
	/**
	 * 微信网页授权：设置scope=snsapi_userbase获取微信openid后，查询微信用户是否已保存，并跳转到对应的页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatCheckWxUser.html")
	public ModelAndView wechatCheckWxUser(String accesstoken,HttpServletResponse response,
			HttpSession session,String state,String code,String openid,String brandCode){
		return wechatService.wechatCheckWxUser(accesstoken,response, session, state, code, openid,brandCode);
	}

	/**
	 * 微信网页授权：设置scope=snsapi_userbase获取微信openid后，查询微信用户是否已保存，并跳转到对应的页面
	 * @author Benson
	 * brandCode 可带参数值包括品牌|shareId|注册来源
	 */
	@RequestMapping("api/wechatCheckWxUser2.html")
	public ModelAndView wechatCheckWxUser2(HttpServletResponse response,
			HttpSession session,String state,String code,String brandCode,String gameid,String shareId){
		return wechatService.wechatCheckWxUser2(response, session, state, code, brandCode, shareId);
	}
	
	/**
	 * 微信网页授权：设置scope=snsapi_userbase获取微信openid后，查询微信用户是否已保存，并跳转到对应的页面
	 * @author Benson
	 */
	@RequestMapping("api/getWxUserShare.html")
	public ModelAndView getWxUserShare(HttpServletResponse response,
			HttpSession session, String state, String code, String[] getWxUserShare){
		return wechatService.getWxUserShare(response, session, state, code, getWxUserShare);
		}
	
	/**
	 * 微信网页授权：用户授权点击确认后，成功获取openid，新增一个新的微信用户，跳转注册页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatJump.html")
	public ModelAndView wechatJump(String accesstoken,HttpSession session,String url,String state,
			String code,String openid,String brandCode){
		return wechatService.wechatJump(accesstoken, session, url, state, code, openid, brandCode);
	}
	
	/**
	  * 外部调用：接口微信网页授权第一步：用户同意授权，获取code
	 * @author Benson
	 */
	@RequestMapping("api/apiWechatAuthorize.html")
	public ModelAndView apiWechatAuthorize(String url,String state,String openid,String model,ModelAndView mav) throws Exception {
		mav=new ModelAndView();
		System.out.println("state========="+state);
		if (state==null||state=="") {
			state="merriplus";
		}
		//重定向到指定的URL
		String domain = getDomain();
		String redirect_uri=URLEncoder.encode(domain+"/api/getAWxUser.html?openid="+openid+"&url="+url,"utf-8");
		mav.setViewName("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+getAppid()+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state="+state+"#wechat_redirect");
		System.out.println(mav.getViewName());
		return mav;
	}
	
	/**
	 * 外部调用：微信网页授权：成功获取openid并返回，重定向对应的页面
	 * @author Benson
	 */
	@RequestMapping("api/getWxOpenId.html")
	public ModelAndView getWxOpenId(String accesstoken,HttpSession session,String url,String state,String code,String openid){
		return wechatService.getWxOpenId(accesstoken, session, url, state, code, openid);
	}
	
	/**
	 * 外部调用：微信网页授权：用户授权点击确认后，成功获取openid，获取微信用户信息，重定向对应的页面
	 * URL:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc29d38541362f295&redirect_uri=http://v.merryplus.com/api/getAWxUser.html?param=http://1111.merryplus.com|MRMJ|base&response_type=code&scope=snsapi_base&state=123#wechat_redirect
	 * 需要获取详细微信会员信息去掉|base，改为userInfo即可
	 * @author Benson
	 */
	@RequestMapping("api/getAWxUser.html")
	public ModelAndView getAWxUser(String accesstoken,HttpSession session,
			String param,String url,
			String state,String code,String shareopenid,String brandCode){
		return wechatService.getAWxUser(accesstoken, session, param, url, state, code, shareopenid,brandCode);
	}
	
	/**
	 * 微信分享页指定跳转页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatShareLink.html")
	public ModelAndView wechatShareLink(HttpServletRequest request,HttpSession session,ModelAndView mav) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+request.getParameter("openid"));
		System.out.println("gameid=============="+request.getParameter("gameid"));
		session.setAttribute("openid", request.getParameter("openid"));
		mav.setViewName("redirect:" + getDomain() + "/activity/we_20150807_qagame/index.html?gameid="+request.getParameter("gameid"));
		
		//mav.setViewName("wechat/wechatTest");
		return mav;
	}
	
	/**
	 * 微信摇一摇抽奖活动，首页指定跳转页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatShakeJump.html")
	public ModelAndView wechatShakeJump(HttpServletRequest request,HttpSession session,
			ModelAndView mav,String gamepath) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+request.getParameter("openid"));
		System.out.println("openid=========="+session.getAttribute("openid"));
		System.out.println("gamepath=========="+gamepath);
		if(StringHelper.isBlank(gamepath)){
			mav.setViewName("redirect:" + getDomain() + "/activity/we_20150908_qagame/index.html?gameid="+request.getParameter("gameid"));
		}else {
			if(request.getParameter("brandCode").equals("JLD")){
				mav.setViewName("redirect:http://v.gymmaxer.com/activity/"+gamepath+"/index.html?gameid="+request.getParameter("gameid"));
			}else {
				mav.setViewName("redirect:" + getDomain() + "/activity/"+gamepath+"/index.html?gameid="+request.getParameter("gameid"));
			}
		}
		return mav;
	}
	
	/**
	 * 微信测试页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatTest.html")
	public ModelAndView wechatTest(HttpServletRequest request,HttpSession session,
			String openid,ModelAndView mav,String malltype, String brandCode) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+openid);

		session.setAttribute("openid", openid);
		session.setAttribute("mallType", malltype);
		session.setAttribute("brandCode", brandCode);
		
		if(malltype!=null){
			mav.setViewName("redirect:" + getDomain() + "/"+malltype+"/index.html");
		}else {
			mav.setViewName("redirect:" + getDomain() + "/wMall/index.html");
		}
		
		
		//mav.setViewName("wechat/wechatTest");
		return mav;
	}
	
	/**
	 * 微信测试页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatTestGMX.html")
	public ModelAndView wechatTestGMX(HttpServletRequest request,HttpSession session,
			String openid,ModelAndView mav,String malltype) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+openid);

		session.setAttribute("openid", openid);
		session.setAttribute("mallType", malltype);
		System.out.println("=========111====="+this.getDomain());
		if(malltype!=null){
			mav.setViewName("redirect:" + this.getDomain() + "/"+malltype+"/member_index.html");
		}else {
			mav.setViewName("redirect:" + this.getDomain() + "/gMall/member_index.html");
		}
		
		
		//mav.setViewName("wechat/wechatTest");
		return mav;
	}
	
	/**
	 * 微信测试页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatTest2.html")
	public ModelAndView wechatTest2(HttpServletRequest request,HttpSession session,String openid,ModelAndView mav) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+openid);

		session.setAttribute("openid", openid);
		mav.setViewName("redirect:"+this.getDomain()+"/activity/we_20150723_qagame/index.html?gameid=1");
		
		//mav.setViewName("wechat/wechatTest");
		return mav;
	}
	
	/**
	 * 微信测试页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatTest3.html")
	public ModelAndView wechatTest3(HttpServletRequest request,HttpSession session,
			String openid,String state,ModelAndView mav) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+openid);

		session.setAttribute("openid", openid);
		session.setAttribute("state", state);
		mav.setViewName("redirect:"+this.getDomain()+"/wMall/didi_coupons.html");
		
		//mav.setViewName("wechat/wechatTest");
		return mav;
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
