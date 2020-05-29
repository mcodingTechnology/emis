package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String goodsName;
	private String goodsPict;
	private String userIntegral;
	private String totalIntegral;
	private String goingIntegral;
	private String gift;
	private String winner;
	private String activityState;
	private int resultId;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPict() {
		return goodsPict;
	}

	public void setGoodsPict(String goodsPict) {
		this.goodsPict = goodsPict;
	}

	public String getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(String userIntegral) {
		this.userIntegral = userIntegral;
	}

	public String getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(String totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public String getGoingIntegral() {
		return goingIntegral;
	}

	public void setGoingIntegral(String goingIntegral) {
		this.goingIntegral = goingIntegral;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getActivityState() {
		return activityState;
	}

	public void setActivityState(String activityState) {
		this.activityState = activityState;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

}
