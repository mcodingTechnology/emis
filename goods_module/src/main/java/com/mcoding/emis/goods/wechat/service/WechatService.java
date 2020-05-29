package com.mcoding.emis.goods.wechat.service;


import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 微信服务类
 * @author Moshow
 */
public interface WechatService{

    public String processWechat(HttpServletRequest request);
    
    public String processPOST(HttpServletRequest request);
    
    //跳转微信页面时，获取OpenId校验是否已授权过
    public ModelAndView wechatCheckAuthorize(String actionPage);
    
    //微信网页授权：设置scope=snsapi_userbase获取微信openid后，查询微信用户是否已保存，并跳转到对应的页面
    public ModelAndView wechatCheckWxUser(String accesstoken,HttpServletResponse response,
    		HttpSession session,String state,String code,String openid,String brandCode);
    
    //微信网页授权：用户授权点击确认后，成功获取openid，新增一个新的微信用户，跳转注册页面
    public ModelAndView wechatJump(String accesstoken,HttpSession session,String url,
    		String state,String code,String openid,String brandCode);
    
    //用于通用的微信分享接口
    public CommonResult<Hashtable> wechatShare2(String brandCode,String fullPath,String openid);
    
    public CommonResult<Hashtable> wechatShare3(String brandCode,String fullPath,String openid);

    public CommonResult<Hashtable> wechatShare(String brandCode, String fullPath, String openid,Map<String, String> params);

    public CommonResult<Hashtable> wechatJSConfigParams(String fullPath);

    public CommonResult<Hashtable> createWechatShareUrl(String brandCode,String targetUrl,String openid);

    public ModelAndView getAWxUser(String accesstoken,HttpSession session,String param,String url,
    		String state,String code, String shareopenid,String brandCode);

    public ModelAndView getWxOpenId(String accesstoken,HttpSession session,String url,
    		String state,String code,String openid);
    
    public String updateWxUserinfo(String brandCode,String openid);
    
    public ModelAndView wechatCheckWxUser2(HttpServletResponse response,
			HttpSession session, String state, String code, String brandCode, String shareId);

    public ModelAndView getWxUserShare(HttpServletResponse response,
			HttpSession session, String state, String code, String[] getWxUserShare);

    public WechatReply getWxReply(String keyword);

    public String getOpenid(String code);

    public WechatReply getWxReply(String keyword, String brandCode);

    public ModelAndView wechatAuthorizeUserInfo(String fullPath,Map<String, String> params) throws Exception;
}
