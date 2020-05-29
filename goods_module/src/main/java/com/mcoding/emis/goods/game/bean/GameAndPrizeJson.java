package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.List;

public class GameAndPrizeJson implements Serializable{
	private static final long serialVersionUID = 1L;
	private Game game;
	private List<GamePrize> gamePrizes;
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public List<GamePrize> getGamePrizes() {
		return gamePrizes;
	}
	public void setGamePrizes(List<GamePrize> gamePrizes) {
		this.gamePrizes = gamePrizes;
	}
	
	

}
