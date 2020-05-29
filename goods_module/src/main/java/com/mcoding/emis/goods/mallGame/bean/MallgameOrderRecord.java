package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameOrderRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private String gameid;
	private String gamename;
	private String giftid;
	private String productid;
	private String productname;
	private String winner;
	private String openid;
	private String isOrder;

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getGiftid() {
		return giftid;
	}

	public void setGiftid(String giftid) {
		this.giftid = giftid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

}
