package com.mcoding.emis.goods.information.bean;

import java.io.Serializable;
import java.util.Date;

public class Information implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 未读状态 **/
	public static final Integer STATUS_UNREADED = 0; 
	/** 已读状态 **/
	public static final Integer STATUS_READED = 1;
	/** 订单消息 100 **/
	public static final Integer INFORMATION_TYPE_ORDER = 100;
	/** 活动消息 200 **/
	public static final Integer INFORMATION_TYPE_ACTIVITY = 200;
	/** 优惠消息 300 **/
	public static final Integer INFORMATION_TYPE_COUPON = 300;
	/**公告消息**/
	public static final Integer INFORMATION_TYPE_PUBLIC = 400;
	
    private Integer id;

    private String sender;

    private Integer sendermemeberid;

    private Integer receivermemberid;

    private String receiveropenid;

    private String title;

    private String content;

    private Integer status;

    private Integer type;

    private Date createtime;

    private Date readtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public Integer getSendermemeberid() {
        return sendermemeberid;
    }

    public void setSendermemeberid(Integer sendermemeberid) {
        this.sendermemeberid = sendermemeberid;
    }

    public Integer getReceivermemberid() {
        return receivermemberid;
    }

    public void setReceivermemberid(Integer receivermemberid) {
        this.receivermemberid = receivermemberid;
    }

    public String getReceiveropenid() {
        return receiveropenid;
    }

    public void setReceiveropenid(String receiveropenid) {
        this.receiveropenid = receiveropenid == null ? null : receiveropenid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReadtime() {
        return readtime;
    }

    public void setReadtime(Date readtime) {
        this.readtime = readtime;
    }
}