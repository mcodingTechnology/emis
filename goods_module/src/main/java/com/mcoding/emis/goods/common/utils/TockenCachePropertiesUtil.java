package com.mcoding.emis.goods.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * .properties公共调用
 * @author Moshow
 */
public class TockenCachePropertiesUtil {
	private static String properiesName = "accessToken_cache.properties";
	
	public void setProperiesName(String properiesName) {
		this.properiesName = properiesName;
	}
	public TockenCachePropertiesUtil() {
		this.properiesName = "wechat.properties";
	}
	public TockenCachePropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}
	public static String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = TockenCachePropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	

	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = TockenCachePropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public static void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = new FileInputStream(TockenCachePropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());
			p.load(is);
			os = new FileOutputStream(TockenCachePropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static String getAcessToken(){
		return readProperty("acessToken");
	}
	public static String getLastTime(){
		return readProperty("lastTime");
	}
	public static String getExpire(){
		return readProperty("expire");
	}
	
	/**
	 * 公共调用
	 * @author Leiming
	 */
	public static String getPropertiesValueByKey(String key){
		return readProperty(key);
	}

	public static void main(String[] args) {
		/*PropertiesUtil p = new PropertiesUtil("wechat.properties");
		p.writeProperty("appid", "123456");
		System.out.println(p.readProperty("appid"));*/
		
		System.out.println(TockenCachePropertiesUtil.getAcessToken());
		System.out.println(TockenCachePropertiesUtil.getLastTime());
		System.out.println(TockenCachePropertiesUtil.getExpire());
	
	}

}
