package com.mcoding.emis.goods.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
 
/**
 * IP工具类
 * @author geeksun
 * 2011-9-23
 */
public class IpUtil {
	
    /**
     * 获取到客户端IP地址
     * 
     * @author fengpeng E-mail: fengpeng@lietou.com
     * @version CreateTime：2012-3-19 下午05:40:29
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = null;
        
        ip = request.getHeader("Cdn-Src-Ip");
        if(ip != null && !"".equals(ip))
        {
            return ip;
        }
        
        ip = request.getHeader("x-forwarded-for");
        if(ip != null && ip.indexOf(',') > 0)
        {
            String[] tmp = ip.split("[,]");
            for(int i = 0; tmp != null && i < tmp.length; i++)
            {
                if(tmp[i] != null && tmp[i].length() > 0 && !"unknown".equalsIgnoreCase(tmp[i]))
                {
                    ip = tmp[i];
                    break;
                }
            }
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
 
    /**
     * @param request IP
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
     
    /**
     * @return 本机IP
     * @throws SocketException
     */
    public static String getRealIp() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
 
        Enumeration<NetworkInterface> netInterfaces = 
            NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() 
                        && !ip.isLoopbackAddress() 
                        && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() 
                        && !ip.isLoopbackAddress() 
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
     
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
     
    public static void main(String[] args) {
		try {
			System.out.println(IpUtil.getRealIp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}