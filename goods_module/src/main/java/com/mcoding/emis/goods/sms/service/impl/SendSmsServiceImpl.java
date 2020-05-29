package com.mcoding.emis.goods.sms.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.common.api.EsbApi;
import com.mcoding.emis.goods.common.utils.gson.DateTimeUtil;
import com.mcoding.emis.goods.sms.bean.SendSmsRecord;
import com.mcoding.emis.goods.sms.bean.SendSmsRecordExample;
import com.mcoding.emis.goods.sms.persistence.SendSmsRecordMapper;
import com.mcoding.emis.goods.sms.service.SendSmsService;

@Service
public class SendSmsServiceImpl implements SendSmsService {
	@Autowired
	private SendSmsRecordMapper sendSmsRecordMapper;

	private int makeRecord(SendSmsRecord record) {
		return sendSmsRecordMapper.insertSelective(record);
	}

	private boolean isOver10TimesToday(String phone) {
		SendSmsRecordExample ex = new SendSmsRecordExample();
		SendSmsRecordExample.Criteria cri = ex.createCriteria();
		cri.andPhoneEqualTo(phone);
		Date start = DateTimeUtil.getDate(DateTimeUtil.formatDate(new Date()) + " 00:00:00");
		Date end = DateTimeUtil.getDate(DateTimeUtil.formatDate(new Date(new Date().getTime() + 86400000)) + " 00:00:00");
		cri.andCreateTimeBetween(start, end);
		List<SendSmsRecord> recordList = sendSmsRecordMapper.selectByExample(ex);
		if (recordList != null && recordList.size() >= 10) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public  String sendSMS(String message, String phone) {
		try {
			if (!isOver10TimesToday(phone)) {// 若今天之内发送不超过10条短信则可以继续
				// 获取access_token
				String accessToken = EsbApi.getAccessToken(false);

				StringBuffer sb = new StringBuffer(
						"http://esb.by-health.com/ESBServer/smsApi/sendSms?");
				sb.append("access_token=" + accessToken);
				sb.append("&content=" + URLEncoder.encode(message, "utf-8"));
				sb.append("&phone=" + phone);
				sb.append("&state=" + state);
				sb.append("&smsCode=" + smsCode);
				sb.append("&Sms_XML=" + Sms_XML);
				sb.append("&sendType=" + sendtype);
				sb.append("&Username=" + Username);
				sb.append("&Password=" + Password);
				sb.append("&bizType=" + Biztype);
				sb.append("&Send_dt=" + Send_dt);
				URL url = new URL(sb.toString());
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setRequestMethod("POST");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));

				// 记录发送短信
				SendSmsRecord record = new SendSmsRecord();
				record.setMessage(message);
				record.setPhone(phone);
				record.setStatus((short) 0);
				record.setCreateTime(new Date());
				makeRecord(record);
				
				return in.readLine();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sendSMS2(String message, String phone) {
		try {
				// 获取access_token
				String accessToken = EsbApi.getAccessToken(false);

				StringBuffer sb = new StringBuffer(
						"http://esb.by-health.com/ESBServer/smsApi/sendSms?");
				sb.append("access_token=" + accessToken);
				sb.append("&content=" + URLEncoder.encode(message, "utf-8"));
				sb.append("&phone=" + phone);
				sb.append("&state=" + state);
				sb.append("&smsCode=" + smsCode);
				sb.append("&Sms_XML=" + Sms_XML);
				sb.append("&sendType=" + sendtype);
				sb.append("&Username=" + Username);
				sb.append("&Password=" + Password);
				sb.append("&bizType=" + Biztype);
				sb.append("&Send_dt=" + Send_dt);
				URL url = new URL(sb.toString());
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setRequestMethod("POST");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));

				return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public static void main(String[] args){
		sendSMS2("亲爱的BIG生活用户，你好。系统查询到你在后台有恶意积分的行为，目前你的积分已经被冻结，经过后台审核后，会再累积合法积分到你的账户，非常感谢对于BIG生活的支持。","13268349409");
	}

	public String batchSend(String url, String account, String pswd, String mobile, String msg,
			boolean needstatus, String extno) throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("account", account),
					new NameValuePair("pswd", pswd), 
					new NameValuePair("mobile", mobile),
					new NameValuePair("needstatus", String.valueOf(needstatus)), 
					new NameValuePair("msg", msg),
					new NameValuePair("extno", extno), 
				});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}
	}
}
