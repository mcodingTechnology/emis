package com.mcoding.emis.goods.income.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value="渠道商实体")
public class ChannelDealer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	
	public Integer id ; //id
	
	public String dealerName; //渠道名称
	
	public String dealerState; //状态
	
	public String dealerType; //类型
	
	public Date createTime; //创建时间

	public Date lastUpdateTime; //修改时间
	
	public Integer memberId; //会员Id

	public String fullName; //会员名称用来jsp显示
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerState() {
		return dealerState;
	}

	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}

	public String getDealerType() {
		return dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	

	
	
}
