package com.mcoding.emis.goods.sales.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="营销表")
public class MemberSales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319628654009577790L;
	
	@ApiModelProperty("营销活动id")
    private Integer saleId;
	
	@ApiModelProperty("营销活动名称")
	private String saleName;
	
	@ApiModelProperty("营销活动标题")
	private String saleTitle;
	
	@ApiModelProperty("营销活动类型，(YXTS:营销推送，DSF:第三方活动)")
	private String saleType;
	
	@ApiModelProperty("营销活动开始时间")
	private Date saleStartTime;
	
	@ApiModelProperty("营销活动结束时间")
	private Date saleEndTime;
	
	@ApiModelProperty("营销信息发送方式,(SMS:短信，WX:微信)")
	private String saleSendMsgType;
	
	@ApiModelProperty("说明")
	private String remake;
	
	@ApiModelProperty("营销活动状态，(TY:停用，QX:启用，SX:失效)")
	private String saleStatus;
	
	@ApiModelProperty("微信公众号appid")
	private String wxAppid;
	
	@ApiModelProperty("微信模板消息id")
	private String wxMsgId;
	
	@ApiModelProperty("第三方活动发起者")
	private String initiatorBy;
	
	@ApiModelProperty("第三方活动链接")
	private String initiatorHref;
	
	@ApiModelProperty("营销活动内容1")
	private String saleContent1;
	
	@ApiModelProperty("营销活动内容2")
	private String saleContent2;
	
	@ApiModelProperty("营销活动内容3")
	private String saleContent3;

	@ApiModelProperty("创建时间")
	private Date createTime;
	
	@ApiModelProperty("最后修改时间")
	private Date lastUpdateTime;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getSaleTitle() {
		return saleTitle;
	}

	public void setSaleTitle(String saleTitle) {
		this.saleTitle = saleTitle;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public Date getSaleStartTime() {
		return saleStartTime;
	}

	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	public Date getSaleEndTime() {
		return saleEndTime;
	}

	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	public String getSaleSendMsgType() {
		return saleSendMsgType;
	}

	public void setSaleSendMsgType(String saleSendMsgType) {
		this.saleSendMsgType = saleSendMsgType;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getWxAppid() {
		return wxAppid;
	}

	public void setWxAppid(String wxAppid) {
		this.wxAppid = wxAppid;
	}

	public String getWxMsgId() {
		return wxMsgId;
	}

	public void setWxMsgId(String wxMsgId) {
		this.wxMsgId = wxMsgId;
	}

	public String getInitiatorBy() {
		return initiatorBy;
	}

	public void setInitiatorBy(String initiatorBy) {
		this.initiatorBy = initiatorBy;
	}

	public String getInitiatorHref() {
		return initiatorHref;
	}

	public void setInitiatorHref(String initiatorHref) {
		this.initiatorHref = initiatorHref;
	}

	public String getSaleContent1() {
		return saleContent1;
	}

	public void setSaleContent1(String saleContent1) {
		this.saleContent1 = saleContent1;
	}

	public String getSaleContent2() {
		return saleContent2;
	}

	public void setSaleContent2(String saleContent2) {
		this.saleContent2 = saleContent2;
	}

	public String getSaleContent3() {
		return saleContent3;
	}

	public void setSaleContent3(String saleContent3) {
		this.saleContent3 = saleContent3;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}