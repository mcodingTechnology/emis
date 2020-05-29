package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameStakeStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userPointSum;
	private int remainerIntegral;
	private int totalIntegral;

	public int getUserPointSum() {
		return userPointSum;
	}

	public void setUserPointSum(int userPointSum) {
		this.userPointSum = userPointSum;
	}

	public int getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(int totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public int getRemainerIntegral() {
		return remainerIntegral;
	}

	public void setRemainerIntegral(int remainerIntegral) {
		this.remainerIntegral = remainerIntegral;
	}

}
