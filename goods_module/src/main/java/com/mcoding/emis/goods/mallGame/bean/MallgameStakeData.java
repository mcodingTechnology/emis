package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameStakeData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userPoint;
	private String userImg;
	private String gameId;
	private String openId;
	private String winner;
	private String resultId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(String userPoint) {
		this.userPoint = userPoint;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

}
