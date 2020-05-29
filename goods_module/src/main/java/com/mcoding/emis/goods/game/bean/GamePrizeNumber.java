package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.Date;

public class GamePrizeNumber implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer prizeid;

    private Integer prizenumber;

    private Integer isrecieve;

    private Date createTime;

    private Date updateTime;

    private String prizeCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrizeid() {
        return prizeid;
    }

    public void setPrizeid(Integer prizeid) {
        this.prizeid = prizeid;
    }

    public Integer getPrizenumber() {
        return prizenumber;
    }

    public void setPrizenumber(Integer prizenumber) {
        this.prizenumber = prizenumber;
    }

    public Integer getIsrecieve() {
        return isrecieve;
    }

    public void setIsrecieve(Integer isrecieve) {
        this.isrecieve = isrecieve;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }
}