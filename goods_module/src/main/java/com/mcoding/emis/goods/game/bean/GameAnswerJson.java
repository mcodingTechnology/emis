package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class GameAnswerJson implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<GameQuestion> gameQuestionList;
	
    private String headimgurl;//非字段值，头像
    private String nickname;//非字段值，昵称
	public ArrayList<GameQuestion> getGameQuestionList() {
		return gameQuestionList;
	}
	public void setGameQuestionList(ArrayList<GameQuestion> gameQuestionList) {
		this.gameQuestionList = gameQuestionList;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
