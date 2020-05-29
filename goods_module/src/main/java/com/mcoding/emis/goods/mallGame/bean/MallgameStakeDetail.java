package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameStakeDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String gameId;
	private String gameName;
	private String giftId;
	private String goodsName;
	private String goodsPict;
	private String totalIntegral;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getGoodsPict() {
		return goodsPict;
	}

	public void setGoodsPict(String goodsPict) {
		this.goodsPict = goodsPict;
	}

	public String getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(String totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

}
