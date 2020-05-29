package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameStakeResultInfor implements Serializable {
	private static final long serialVersionUID = 1L;
	private int gameId;
	private int giftId;
	private int gameStatus;
	private int userGainPoint;
	private int totalPoint;
	private String productName;
	private String productCoverimg;
	private int resultId;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getUserGainPoint() {
		return userGainPoint;
	}

	public void setUserGainPoint(int userGainPoint) {
		this.userGainPoint = userGainPoint;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCoverimg() {
		return productCoverimg;
	}

	public void setProductCoverimg(String productCoverimg) {
		this.productCoverimg = productCoverimg;
	}

	public int getGiftId() {
		return giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

}
