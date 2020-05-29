package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameStakeRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private int gameId;

	private String fullName;

	private String userImg;

	private String openId;

	private String stakePoint;

	private String isWinner = "0";

	private String isOrder = "0";

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getStakePoint() {
		return stakePoint;
	}

	public void setStakePoint(String stakePoint) {
		this.stakePoint = stakePoint;
	}

	public String getIsWinner() {
		return isWinner;
	}

	public void setIsWinner(String isWinner) {
		this.isWinner = isWinner;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

}
