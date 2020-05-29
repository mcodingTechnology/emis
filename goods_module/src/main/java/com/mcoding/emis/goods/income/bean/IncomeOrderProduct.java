package com.mcoding.emis.goods.income.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="income_order_product")
public class IncomeOrderProduct implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("分销商id")
    private Integer memberId;

    @ApiModelProperty("分销商名称")
    private String memberName;

    @ApiModelProperty("分销商openid")
    private String openid;

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("产品id")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品数量")
    private Integer productCount;

    @ApiModelProperty("收入单id")
    private Integer incomeOrderId;

    @ApiModelProperty("产品收入")
    private Integer income;

    @ApiModelProperty("产品获取的积分")
    private Integer point;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getIncomeOrderId() {
        return incomeOrderId;
    }

    public void setIncomeOrderId(Integer incomeOrderId) {
        this.incomeOrderId = incomeOrderId;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}