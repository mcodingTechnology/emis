package com.mcoding.emis.goods.sms.service;


public interface SendSmsService {
	
	static String state="0";//信息状态，0待发或1已发
	static String smsCode="";//动态模板ID，由平台配置
	static String Sms_XML="";//动态模板内容参数，需指定格式字符串，有明确的分隔符，可以为XML或key|value模式
	static String sendtype="1";//1、普通模式，2、动态模式
	static String Username="admin";//账号 （测试环境admin）
	static String Password="123456";//密码（测试环境123456）
	static String Biztype="";//业务类型，用于绑定通道0
	static String Send_dt="";//定时发送日期时间格式：yyyy-mm-dd hh24:mi:ss
	
	public String sendSMS(String message, String phone);

	public String batchSend(String url, String account, String pswd, String mobile, String msg, boolean needstatus,
			String extno) throws Exception;
}