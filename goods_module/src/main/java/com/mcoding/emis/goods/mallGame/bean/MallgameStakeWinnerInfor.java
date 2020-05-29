package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameStakeWinnerInfor implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private int userPoint;
	private String userImg;
	private String openId;
	private int resultId;
	private int orderNum;
	private int isOrder;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(int isOrder) {
		this.isOrder = isOrder;
	}

}
