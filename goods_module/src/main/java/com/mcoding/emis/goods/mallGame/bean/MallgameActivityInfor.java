package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameActivityInfor implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String gameName;

	private String startTime;

	private String endTime;

	private int gameStatus;

	private int nums;

	private String brandCode;

	private int productId;

	private String productName;

	private String productImg;

	private int gainPoint;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

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

	public int getGainPoint() {
		return gainPoint;
	}

	public void setGainPoint(int gainPoint) {
		this.gainPoint = gainPoint;
	}

}
