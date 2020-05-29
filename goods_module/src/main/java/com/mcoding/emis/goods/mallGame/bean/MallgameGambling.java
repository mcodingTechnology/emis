package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;
import java.util.Date;

public class MallgameGambling implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String gamename;

	private Date gamestarttime;

	private Date gameendtime;

	private String prizerange;

	private String brandcode;

	private Integer daydrawnum;

	private String ext;

	private String ext1;

	private Date createtime;

	private Date updatetime;

	private Integer status;

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

	public String getPrizerange() {
		return prizerange;
	}

	public void setPrizerange(String prizerange) {
		this.prizerange = prizerange == null ? null : prizerange.trim();
	}

	public String getBrandcode() {
		return brandcode;
	}

	public void setBrandcode(String brandcode) {
		this.brandcode = brandcode == null ? null : brandcode.trim();
	}

	public Integer getDaydrawnum() {
		return daydrawnum;
	}

	public void setDaydrawnum(Integer daydrawnum) {
		this.daydrawnum = daydrawnum;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}