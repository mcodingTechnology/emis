package com.mcoding.emis.goods.wechat.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 微信JS签名
 * @author moshow
 */
public class WxJSSignture implements Serializable{

	private static final long serialVersionUID = 1L;

	//时间戳
	private String timestamp;
	
	//随机字符串
	private String  nonceStr;
	
	private String  signature;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
}