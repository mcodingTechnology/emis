package com.mcoding.emis.goods.income.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="income_order")
public class IncomeOrder implements Serializable{

	/** 正向的收入，也就是增长的收入，新订单的收入**/
	public static final String TYPE_INCREASE_INCOME = "1";
	/** 反向的收入，也就是减少的收入，新退货单的扣除的收入**/
	public static final String TYPE_DECREASE_INCOME = "2";

    private Integer id;

    private Integer memberid;

    private String membername;

    private String openid;

    private Integer orderid;

    @ApiModelProperty("佣金-单位：分")
    private Integer commission;

    private Date createtime;

    private Date updatetime;

    @ApiModelProperty("购买订单的用户的id")
    private Integer ordermemberid;

    @ApiModelProperty("购买订单的用户的名称")
    private String ordermembername;

    @ApiModelProperty("订单的总费用")
    private Integer orderfee;

    @ApiModelProperty("订单的创建时间")
    private Date ordertime;

    @ApiModelProperty("订单编号")
    private String ext1;

    @ApiModelProperty("收入类型：1订单产生收入，2退货单产生的负收入")
    private String ext2;

    @ApiModelProperty("结算状态 标识：  1 已结算   2未结算")
    private String ext3;

    @ApiModelProperty("返利积分")
    private Integer point;

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

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
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

    public Integer getOrdermemberid() {
        return ordermemberid;
    }

    public void setOrdermemberid(Integer ordermemberid) {
        this.ordermemberid = ordermemberid;
    }

    public String getOrdermembername() {
        return ordermembername;
    }

    public void setOrdermembername(String ordermembername) {
        this.ordermembername = ordermembername == null ? null : ordermembername.trim();
    }

    public Integer getOrderfee() {
        return orderfee;
    }

    public void setOrderfee(Integer orderfee) {
        this.orderfee = orderfee;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}