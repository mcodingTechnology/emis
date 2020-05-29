package com.mcoding.emis.goods.wechat.bean;

import java.util.Date;

public class TemplateMessageRecord {
	
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_INCOME = "GMX_income";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_PAY = "GMX_pay";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_PAY_POINT = "GMX_pay_point";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_DELIVERY = "GMX_delivery";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_PRIZE_NOTIFY = "GMX_prize_notify";
	public final static String TEMPLATE_MESSAGE_TYPE_INCOME = "income";
	public final static String TEMPLATE_MESSAGE_TYPE_PAY = "pay";
	public final static String TEMPLATE_MESSAGE_TYPE_PAY_POINT = "pay_point";
	public final static String TEMPLATE_MESSAGE_TYPE_DELIVERY = "delivery";
	public final static String TEMPLATE_MESSAGE_TYPE_EXCHANGE = "exchange";
	public final static String TEMPLATE_MESSAGE_TYPE_STAKE = "stake";
	public final static String TEMPLATE_MESSAGE_TYPE_PRESENT_GET = "present_get";
	public final static String TEMPLATE_MESSAGE_TYPE_PRESENT_NOT_GET = "present_not_get";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_PRESENT_GET = "GMX_present_get";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_PRESENT_NOT_GET = "GMX_present_not_get";
	public final static String TEMPLATE_MESSAGE_TYPE_RECEIVE_ACT_GIFT = "receive_act_gift";
	public final static String TEMPLATE_MESSAGE_TYPE_BECOME_REFERRAL = "become_referral";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_BECOME_REFERRAL = "GMX_become_referral";
	public final static String TEMPLATE_MESSAGE_TYPE_POINT = "point";
	public final static String TEMPLATE_MESSAGE_TYPE_GMX_POINT = "GMX_point";
	public final static String TEMPLATE_MESSAGE_TYPE_SIGN_POINT = "sign_point";
	public final static String TEMPLATE_MESSAGE_TYPE_READY_DELIVER = "ready_deliver";
	public final static String TEMPLATE_MESSAGE_TYPE_PRODUCT_TIPS = "product_tips";
	public final static String TEMPLATE_MESSAGE_TYPE_BECOME_FRANCHISEE = "become_franchisee";

	public final static short TEMPLATE_MESSAGE_STATUS_SUCCESS = 0;
	public final static short TEMPLATE_MESSAGE_STATUS_NOT_SEND = 1;
	public final static short TEMPLATE_MESSAGE_STATUS_FAILED = 2;
	
    private Integer id;

    private Integer templateMessageId;

    private String openid;

    private String nickName;

    private Short status;

    private String keyword1;

    private String keyword2;

    private String keyword3;

    private String keyword4;

    private String keyword5;

    private String content;

    private String url;

    private Integer orderId;

    private Date createdtime;

    private Date updatedtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateMessageId() {
        return templateMessageId;
    }

    public void setTemplateMessageId(Integer templateMessageId) {
        this.templateMessageId = templateMessageId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1 == null ? null : keyword1.trim();
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2 == null ? null : keyword2.trim();
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3 == null ? null : keyword3.trim();
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4 == null ? null : keyword4.trim();
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5 == null ? null : keyword5.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(Date updatedtime) {
        this.updatedtime = updatedtime;
    }
}