package com.mcoding.emis.goods.legalMonitor.service.impl;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.legalMonitor.bean.SessionIdRecord;
import com.mcoding.emis.goods.legalMonitor.service.LegalMonitorService;

@Service
public class LegalMonitorServiceImpl implements LegalMonitorService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static Map<String, SessionIdRecord> sessionIdMap = new HashMap<String, SessionIdRecord>();

	/**
	 * 判断是否从外部调用接口
	 */
	@Override
	public boolean isOutsideInvoking(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		logger.info("|||||||||| referer is :" + referer);
		Store store = StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		logger.info("domain============================"+domain);
		if (null == referer
				|| (!referer.contains(domain) 
						&& !referer.contains(domain))) {
			System.out.println("is outside invoking");
			return true;
		}
		System.out.println("is not outside invoking");
		return false;
	}

	/**
	 * 判断请求的时间间隔是否少于既定秒数
	 */
	@Override
	public boolean isOverFrequency(int frequency, HttpServletRequest request) {
		String sessionId = null;
//		String realIP = request.getHeader("x-forwarded-for");
//		if (realIP != null && realIP.length() != 0) {
//			sessionId = realIP;
//		} else {
//			sessionId = request.getRemoteAddr();
//		}
		sessionId = request.getSession().getId();
		putIntoSessionIdMap(sessionId);
		if(sessionIdMap.get(sessionId).getCount() == 1){
			return false;
		}
		if (sessionIdMap.get(sessionId).getSecond() < frequency) {
			return true;
		}
		return false;
	}
	
	private void putIntoSessionIdMap(String sessionId){
		SessionIdRecord ipRecord = null;
		if (sessionIdMap.get(sessionId) == null) {
			ipRecord = new SessionIdRecord();
			ipRecord.setSessionId(sessionId);
			ipRecord.setLastTime(new Date());
			ipRecord.setCount(1);
			ipRecord.setSecond(0);
			sessionIdMap.put(sessionId, ipRecord);
		} else {
			ipRecord = sessionIdMap.get(sessionId);
			long lastTime = ipRecord.getLastTime().getTime();
			Date now = new Date();
			int second = (int) (new Date().getTime() - lastTime) / 1000;
			ipRecord.setLastTime(now);
			ipRecord.setSecond(second);
			ipRecord.setCount(ipRecord.getCount() + 1);
		}
		System.out.println(sessionIdMap);
	}
	
	private void method(HttpServletRequest request){
		String referer = request.getHeader("Referer");
		logger.info("|||||||||| referer is :" + referer);
		Store store = StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		
		if (null == referer
				|| (!referer.contains(domain) 
						&& !referer.contains(domain))) {
			System.out.println("有问题");
		}
		
		System.out.println(request.getSession().getId());
		String ServletPath = request.getServletPath();
		String host = request.getHeader("Host");
		System.out.println(ServletPath);
		System.out.println(host);
		System.out.println(referer);
		
		String realIP = request.getHeader("x-forwarded-for");
        if (realIP != null && realIP.length() != 0) {
        	System.out.print("你的IP地址是realIP：" + realIP);
        } else {
            System.out.print("你的IP地址是remoteAddr：" + request.getRemoteAddr());
        }
		
		Enumeration<String> headerName = request.getHeaderNames();
		while (headerName.hasMoreElements()) {
			String name = headerName.nextElement();
			System.out.println(name + " : " + request.getHeader(name));
		}
		String ip = request.getRemoteAddr();
		String ua = request.getHeader("user-agent").toLowerCase();
		
		
//		if(ua.indexOf("micromessenger")>0){
//			System.out.println("是微信");
//			System.out.println("唔喺啊哇嗰嘀嘢喺啫喱嚟咖咋哈嘛");
//		}else{
//			System.out.println("不是微信");
//			System.out.println("唔喺啊哇嗰嘀嘢喺啫喱嚟咖咋哈嘛");
//		}
//		if (ipMap.get(ip) == null) {
//			ipMap.put(ip, 1);
//		} else {
//			ipMap.put(ip, ipMap.get(ip) + 1);
//		}
//		return ipMap.toString();
	
		
	}
	
	/*	@RequestMapping("/front/getIp.html")
	@ResponseBody
	public String getIp(HttpSession session, HttpServletRequest request) {
		String result = null;
		if(legalMonitorService.isOverFrequency(40, request)){
			result = "over frequency";
		}else{
			result = "not over frequency";
		}
		if(legalMonitorService.isOutsideInvoking(request)){
			result += "  is outside invoking";
		}else{
			result += "  is not outside invoking";
		}
		result += request.getSession().getId();
		return result;
	}*/
}
