package com.mcoding.emis.goods.sales.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="营销流水表")
public class MemberSalesLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319628654009577790L;
	@ApiModelProperty("流水id")
	private Integer saleLogId;
	
	@ApiModelProperty("营销活动id")
    private Integer saleId;
	
	@ApiModelProperty("营销活动名称")
	private String saleName;
	
	@ApiModelProperty("营销信息发送方式,(SMS:短信，WX:微信)")
	private String saleSendMsgType;
	
	@ApiModelProperty("会员id")
	private Integer memberId;
	
	@ApiModelProperty("会员手机号码")
	private String memberPhoneNumber;
	
	@ApiModelProperty("会员openid")
	private String memberOpenid;
	
	@ApiModelProperty("是否参加了活动")
	private String isIntoAct;
	
	@ApiModelProperty("营销信息发送时间")
	private Date createTime;
	
	@ApiModelProperty("最后修改时间")
	private Date lastUpdateTime;

	public Integer getSaleLogId() {
		return saleLogId;
	}

	public void setSaleLogId(Integer saleLogId) {
		this.saleLogId = saleLogId;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public String getSaleSendMsgType() {
		return saleSendMsgType;
	}

	public void setSaleSendMsgType(String saleSendMsgType) {
		this.saleSendMsgType = saleSendMsgType;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public String getMemberOpenid() {
		return memberOpenid;
	}

	public void setMemberOpenid(String memberOpenid) {
		this.memberOpenid = memberOpenid;
	}

	public String getIsIntoAct() {
		return isIntoAct;
	}

	public void setIsIntoAct(String isIntoAct) {
		this.isIntoAct = isIntoAct;
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

	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	
	
}