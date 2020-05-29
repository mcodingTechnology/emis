package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;
import java.util.List;

public class GameAndGiftJson implements Serializable{
	private static final long serialVersionUID = 1L;
	private MallgameGambling gambling;
	private List<MallgameGift> gameGiftList;
	public MallgameGambling getGambling() {
		return gambling;
	}
	public void setGambling(MallgameGambling gambling) {
		this.gambling = gambling;
	}
	public List<MallgameGift> getGameGiftList() {
		return gameGiftList;
	}
	public void setGameGiftList(List<MallgameGift> gameGiftList) {
		this.gameGiftList = gameGiftList;
	}
	

}
