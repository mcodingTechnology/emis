package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable{
	private static final long serialVersionUID = 1L;

    /**线上摇一摇抽奖活动**/
    public static final Integer GAME_TYPE_ONLINE_SHAKE = 1;
    /**现场摇一摇抽奖活动**/
    public static final Integer GAME_TYPE_OFFLINE_SHAKE = 3;
    private Integer id;

    private String gamename;

    private Integer gametype;

    private Date gamestarttime;

    private Date gameendtime;

    private String brandcode;

    private Date createtime;

    private String prizerange;
    
    private Integer randomlength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename == null ? null : gamename.trim();
    }

    public Integer getGametype() {
        return gametype;
    }

    public void setGametype(Integer gametype) {
        this.gametype = gametype;
    }

    public Date getGamestarttime() {
        return gamestarttime;
    }

    public void setGamestarttime(Date gamestarttime) {
        this.gamestarttime = gamestarttime;
    }

    public Date getGameendtime() {
        return gameendtime;
    }

    public void setGameendtime(Date gameendtime) {
        this.gameendtime = gameendtime;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPrizerange() {
        return prizerange;
    }

    public void setPrizerange(String prizerange) {
        this.prizerange = prizerange == null ? null : prizerange.trim();
    }

	public Integer getRandomlength() {
		return randomlength;
	}

	public void setRandomlength(Integer randomlength) {
		this.randomlength = randomlength;
	}

}