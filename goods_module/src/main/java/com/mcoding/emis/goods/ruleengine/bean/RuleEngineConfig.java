package com.mcoding.emis.goods.ruleengine.bean;

import java.io.Serializable;
import java.util.Date;

public class RuleEngineConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String ruleName;

	private Integer ruleType;

	private Integer productId;

	private Integer origPrice;

	private Integer promoPrice;

	private Integer purchaseNum;

	private Integer giftProductId;

	private Integer giftNum;

	private Integer promoMinLimit;

	private Integer returnBack;

	private Integer freight;
	
	private Integer restrictionNum; // 用户购买数量限制

	private Integer stockRestrictionNum;// 库存总购买数量限制

	private Date startTime;

	private Date endTime;

	private String brandCode;

	private Short status;

	private String ext1;

	private Date createTime;

	private Date updateTime;

	// 非字段，规则类型名称
	private String ruleTypeName;
	// 非字段，商品名称
	private String productName;
	// 非字段，赠品名称
	private String giftProductName;
	
	
	
	
	public Integer getRestrictionNum() {
		return restrictionNum;
	}
	public void setRestrictionNum(Integer restrictionNum) {
		this.restrictionNum = restrictionNum;
	}
	public Integer getStockRestrictionNum() {
		return stockRestrictionNum;
	}
	public void setStockRestrictionNum(Integer stockRestrictionNum) {
		this.stockRestrictionNum = stockRestrictionNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public Integer getRuleType() {
		return ruleType;
	}
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getOrigPrice() {
		return origPrice;
	}
	public void setOrigPrice(Integer origPrice) {
		this.origPrice = origPrice;
	}
	public Integer getPromoPrice() {
		return promoPrice;
	}
	public void setPromoPrice(Integer promoPrice) {
		this.promoPrice = promoPrice;
	}
	public Integer getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public Integer getGiftProductId() {
		return giftProductId;
	}
	public void setGiftProductId(Integer giftProductId) {
		this.giftProductId = giftProductId;
	}
	public Integer getGiftNum() {
		return giftNum;
	}
	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}
	public Integer getPromoMinLimit() {
		return promoMinLimit;
	}
	public void setPromoMinLimit(Integer promoMinLimit) {
		this.promoMinLimit = promoMinLimit;
	}
	public Integer getReturnBack() {
		return returnBack;
	}
	public void setReturnBack(Integer returnBack) {
		this.returnBack = returnBack;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
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
	public String getRuleTypeName() {
		return ruleTypeName;
	}
	public void setRuleTypeName(String ruleTypeName) {
		this.ruleTypeName = ruleTypeName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getGiftProductName() {
		return giftProductName;
	}
	public void setGiftProductName(String giftProductName) {
		this.giftProductName = giftProductName;
	}

	

}