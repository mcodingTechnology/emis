package com.mcoding.emis.goods.wechat.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 微信JS签名
 * @author moshow
 */
public class WxJSCardSignture implements Serializable{

	private static final long serialVersionUID = 1L;
	//APPID
	private String appId;
	
	//卡券签名
	private String cardSign;
	
	//卡券id
	private String cardId;
	
	//卡券类型,代金券：CASH；团购券：GROUPON；折扣券：DISCOUNT；礼品券：GIFT；优惠券：GENERAL_COUPON
	private String cardType;
	
	//时间戳
	private String timestamp;
	
	//随机字符串
	private String  nonceStr;
	
	//js签名
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCardSign() {
		return cardSign;
	}

	public void setCardSign(String cardSign) {
		this.cardSign = cardSign;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	
}