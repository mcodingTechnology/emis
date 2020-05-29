package com.mcoding.emis.goods.income.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="income_day")
public class IncomeDay implements Serializable {
    private Integer id;

    private Integer memberid;

    private String membername;

    private String openid;

    @ApiModelProperty("佣金-单位：分")
    private Integer commission;

    @ApiModelProperty("订单总额 分为单位")
    private Integer orderfee;

    private Date createtime;

    private Date updatetime;

    @ApiModelProperty("返利积分")
    private Integer point;

    private Integer sumOrderFee;//业绩总额
    private Integer sumCommission;//收入总额
    private String countYearMonth;//统计年月

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getOrderfee() {
        return orderfee;
    }

    public void setOrderfee(Integer orderfee) {
        this.orderfee = orderfee;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getSumOrderFee() {
        return sumOrderFee;
    }

    public void setSumOrderFee(Integer sumOrderFee) {
        this.sumOrderFee = sumOrderFee;
    }

    public Integer getSumCommission() {
        return sumCommission;
    }

    public void setSumCommission(Integer sumCommission) {
        this.sumCommission = sumCommission;
    }

    public String getCountYearMonth() {
        return countYearMonth;
    }

    public void setCountYearMonth(String countYearMonth) {
        this.countYearMonth = countYearMonth;
    }
}