package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;

public class MallgameUserStakeResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code=-1;
	private String description;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
