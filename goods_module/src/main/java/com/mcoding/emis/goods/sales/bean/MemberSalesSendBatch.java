package com.mcoding.emis.goods.sales.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="营销发送批次表")
public class MemberSalesSendBatch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319628654009577790L;
	@ApiModelProperty("批次id")
	private Integer batchId;
	
	@ApiModelProperty("营销活动id")
    private Integer saleId;
	
	@ApiModelProperty("邀请人数")
	private Integer inviteNum;
	
	@ApiModelProperty("目标用户标签")
	private String tagsId;
	
	@ApiModelProperty("公众号id")
	private String wxAppId;
	
	@ApiModelProperty("营销信息发送时间")
	private Date saleMsgSendTime;
	
	@ApiModelProperty("最后修改时间")
	private Date lastUpdateTime;

	
	public String getTagsId() {
		return tagsId;
	}

	public void setTagsId(String tagsId) {
		this.tagsId = tagsId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public Date getSaleMsgSendTime() {
		return saleMsgSendTime;
	}

	public void setSaleMsgSendTime(Date saleMsgSendTime) {
		this.saleMsgSendTime = saleMsgSendTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	
	
}