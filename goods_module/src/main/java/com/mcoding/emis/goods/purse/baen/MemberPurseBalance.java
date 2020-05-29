package com.mcoding.emis.goods.purse.baen;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员钱包余额表")
public class MemberPurseBalance implements Serializable {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("openid")
    private String openId;

    @ApiModelProperty("余额，单位：分")
    private Integer balance;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("最后修改时间")
    private Date lastUpdateTime;
    
    @ApiModelProperty("操作，A:新增;C:消费")
    private String action;
    
    @ApiModelProperty("描述")
    private String remake;

    private static final long serialVersionUID = 1L;

    
    
    public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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