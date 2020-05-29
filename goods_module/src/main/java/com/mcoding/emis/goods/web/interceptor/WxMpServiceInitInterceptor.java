package com.mcoding.emis.goods.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcoding.base.ui.utils.StoreUtils;

public class WxMpServiceInitInterceptor extends HandlerInterceptorAdapter{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		logger.info("=============uri===="+uri);
		
//		String brandCode = (String) request.getSession().getAttribute("brandCode");
//		String mallType = (String) request.getSession().getAttribute("mallType");
		String brandCode = StoreUtils.getStoreFromThreadLocal().getStoreName();
		String mallType = null;
		if (uri.contains("GiftMall/")) {
			//如果是极智构 积分商城
//			WxMpService wxMpService = SpringContextUtils.getBean("wxService");
//			WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("wxConfig");
//			
//			WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
			if(StringUtils.isBlank(brandCode)){
				brandCode = "MRMJ";
			}
			if (StringUtils.isBlank(mallType)) {
				mallType = "giftMall";
			}
			
		}else if (uri.contains("wMall/")) {
			//如果是极智构 微商城
//			WxMpService wxMpService = SpringContextUtils.getBean("wxService");
//			WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("wxConfig");
//			
//			WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
			if(StringUtils.isBlank(brandCode)){
				brandCode = "MRMJ";
			}
			if (StringUtils.isBlank(mallType)) {
				mallType = "wMall";
			}
			
		}else if (uri.contains("GiftMall_GMX/")) {
			//如果是BIG生活 积分商城
//			WxMpService wxMpService = SpringContextUtils.getBean("wxGMXService");
//			WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("GMXwxConfig");
//			
//			WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
			if(StringUtils.isBlank(brandCode)){
				brandCode = "JLD";
			}
			if (StringUtils.isBlank(mallType)) {
				mallType = "giftMall_gmx";
			}
		}else if (uri.contains("gMall/")) {
			//如果是BIG生活 微商城
//			WxMpService wxMpService = SpringContextUtils.getBean("wxGMXService");
//			WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("GMXwxConfig");
//			
//			WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
			if(StringUtils.isBlank(brandCode)){
				brandCode = "JLD";
			}
			if (StringUtils.isBlank(mallType)) {
				mallType = "gMall";
			}
		}else {
			String serverName = request.getServerName();
			logger.info("=============serverName===="+serverName);
			if(serverName.contains("merryplus.com")){
//				WxMpService wxMpService = SpringContextUtils.getBean("wxService");
//				WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("wxConfig");
//				
//				WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
				if(StringUtils.isBlank(brandCode)){
					brandCode = "MRMJ";
				}
			}else if(serverName.contains("gymmaxer.com")){
//				WxMpService wxMpService = SpringContextUtils.getBean("wxGMXService");
//				WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("GMXwxConfig");
//				
//				WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
				if(StringUtils.isBlank(brandCode)){
					brandCode = "JLD";
				}
			}else if(serverName.contains("xiaotiantian.gymmaxer.com")){
//				WxMpService wxMpService = SpringContextUtils.getBean("wxXTTService");
//				WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("XTTwxConfig");
//				
//				WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
				if(StringUtils.isBlank(brandCode)){
					brandCode = "XTT";
				}
			}else{
//				WxMpService wxMpService = SpringContextUtils.getBean("wxService");
//				WxMpConfigStorage wxMpConfigStorage = SpringContextUtils.getBean("wxConfig");
//				
//				WxMpServiceUtils.initWxMpSetting(wxMpService, wxMpConfigStorage);
				if(StringUtils.isBlank(brandCode)){
					brandCode = StringUtils.defaultIfBlank((String)request.getSession().getAttribute("brandCode"), "MRMJ");
				}
				if (StringUtils.isBlank(mallType)) {
					mallType = StringUtils.defaultIfBlank((String)request.getSession().getAttribute("mallType"), "wMall");
				}
			}
		}
		
		if (StringUtils.isBlank(brandCode)) {
			brandCode = (String) request.getSession().getAttribute("brandCode");
		}
		if (StringUtils.isBlank(mallType)) {
            mallType = (String) request.getSession().getAttribute("mallType");
		}
		
		request.getSession().setAttribute("brandCode", brandCode);
		request.getSession().setAttribute("mallType",mallType);
		logger.debug("set brandCode in session:" + request.getSession().getAttribute("brandCode") + ", set malltype in session:" + request.getSession().getAttribute("mallType"));
		
		return super.preHandle(request, response, handler);
	} 
	
}
