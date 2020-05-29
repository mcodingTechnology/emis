package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;
import java.util.Date;

public class OrderDiscount implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer orderId;

    private String openid;

    private Short discountType;

    private String promoDesc;

    private Integer ruleType;

    private Integer preferentialPrice;

    private Integer promoMinLimit;

    private Integer productId;

    private String productName;

    private Integer giftProductId;

    private String giftProductName;

    private Integer giftProductNum;

    private Integer requirePurchaseNum;

    private Integer actualPurchaseNum;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Short getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Short discountType) {
        this.discountType = discountType;
    }

    public String getPromoDesc() {
        return promoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        this.promoDesc = promoDesc == null ? null : promoDesc.trim();
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(Integer preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public Integer getPromoMinLimit() {
        return promoMinLimit;
    }

    public void setPromoMinLimit(Integer promoMinLimit) {
        this.promoMinLimit = promoMinLimit;
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

    public Integer getGiftProductId() {
        return giftProductId;
    }

    public void setGiftProductId(Integer giftProductId) {
        this.giftProductId = giftProductId;
    }

    public String getGiftProductName() {
        return giftProductName;
    }

    public void setGiftProductName(String giftProductName) {
        this.giftProductName = giftProductName == null ? null : giftProductName.trim();
    }

    public Integer getGiftProductNum() {
        return giftProductNum;
    }

    public void setGiftProductNum(Integer giftProductNum) {
        this.giftProductNum = giftProductNum;
    }

    public Integer getRequirePurchaseNum() {
        return requirePurchaseNum;
    }

    public void setRequirePurchaseNum(Integer requirePurchaseNum) {
        this.requirePurchaseNum = requirePurchaseNum;
    }

    public Integer getActualPurchaseNum() {
        return actualPurchaseNum;
    }

    public void setActualPurchaseNum(Integer actualPurchaseNum) {
        this.actualPurchaseNum = actualPurchaseNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}