package com.mcoding.emis.goods.purse.baen;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员钱包交易明细表")
public class MemberPurseDetail implements Serializable {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("openid")
    private String openId;

    @ApiModelProperty("余额操作，A：充值，C：消费")
    private String balanceAction;

    @ApiModelProperty("余额描述")
    private String balanceRemark;

    @ApiModelProperty("余额来源，WXCZ：充值，DDXF")
    private String source;

    @ApiModelProperty("金额值,单位：分")
    private Integer balance;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("最后修改时间")
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

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

    public String getBalanceAction() {
        return balanceAction;
    }

    public void setBalanceAction(String balanceAction) {
        this.balanceAction = balanceAction == null ? null : balanceAction.trim();
    }

    public String getBalanceRemark() {
        return balanceRemark;
    }

    public void setBalanceRemark(String balanceRemark) {
        this.balanceRemark = balanceRemark == null ? null : balanceRemark.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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