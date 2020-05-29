package com.mcoding.emis.goods.common.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class GetOpenidUtil {
	
	public static String getOpenid(){
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		return (String) session.getAttribute("openid");
	}
}
