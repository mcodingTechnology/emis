package com.mcoding.emis.goods.healthCriteria.bean;

import java.io.Serializable;

public class HealthRecommendProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private int productId;

	private String productName;

	private float originalPrice;

	private float discountPrice;

	private float microMallPrice;

	private String productImg;

	private String productAdImg;

	private String productIntroduce;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public float getMicroMallPrice() {
		return microMallPrice;
	}

	public void setMicroMallPrice(float microMallPrice) {
		this.microMallPrice = microMallPrice;
	}

	public String getProductAdImg() {
		return productAdImg;
	}

	public void setProductAdImg(String productAdImg) {
		this.productAdImg = productAdImg;
	}

	public String getProductIntroduce() {
		return productIntroduce;
	}

	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}

}
