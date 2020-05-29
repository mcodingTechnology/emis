package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.Date;

public class GamePrize implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**优惠券**/
	public static final Integer GAME_PRIZE_TYPE_CARD = 2;
	/**积分**/
	public static final Integer GAME_PRIZE_TYPE_POINT = 3;
	/**奖品**/
	public static final Integer GAME_PRIZE_TYPE_OBJECT = 1;
	
    private Integer id;

    private String prizename;
    
    private String prizeimg;

    private Integer prizelevel;

    private Integer lotterypercent;

    private Integer prizetype;

    private String brandcode;

    private Date createtime;

    private String gamename;

    private Integer gainedprizenum;

    private Integer prizenum;

    private String ext;

    private String ext1;

    private Integer gameid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizename() {
        return prizename;
    }

    public void setPrizename(String prizename) {
        this.prizename = prizename == null ? null : prizename.trim();
    }
    
    public String getPrizeimg() {
		return prizeimg;
	}

	public void setPrizeimg(String prizeimg) {
		this.prizeimg = prizeimg;
	}

	public Integer getPrizelevel() {
        return prizelevel;
    }

    public void setPrizelevel(Integer prizelevel) {
        this.prizelevel = prizelevel;
    }

    public Integer getLotterypercent() {
        return lotterypercent;
    }

    public void setLotterypercent(Integer lotterypercent) {
        this.lotterypercent = lotterypercent;
    }

    public Integer getPrizetype() {
        return prizetype;
    }

    public void setPrizetype(Integer prizetype) {
        this.prizetype = prizetype;
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

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename == null ? null : gamename.trim();
    }

    public Integer getGainedprizenum() {
        return gainedprizenum;
    }

    public void setGainedprizenum(Integer gainedprizenum) {
        this.gainedprizenum = gainedprizenum;
    }

    public Integer getPrizenum() {
        return prizenum;
    }

    public void setPrizenum(Integer prizenum) {
        this.prizenum = prizenum;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }
}