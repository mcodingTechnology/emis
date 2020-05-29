package com.mcoding.emis.goods.sales.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="会员发送信息")
public class MemberSendSalesInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319628654009577790L;
	
	@ApiModelProperty("会员id")
    private Integer memberId;
	
	@ApiModelProperty("openid")
	private String openid;
	
	@ApiModelProperty("手机号码")
	private String mobilePhone;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
}