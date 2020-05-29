package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;
import java.util.Date;

public class MallgameGift implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer gameid;

    private Integer isproduct;

    private Integer gainpoint;

    private Integer productid;

    private String productname;

    private String productcoverimg;

    private Integer nums;

    private Integer gainnums;

    private String brandcode;

    private String ext;

    private String ext1;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getIsproduct() {
        return isproduct;
    }

    public void setIsproduct(Integer isproduct) {
        this.isproduct = isproduct;
    }

    public Integer getGainpoint() {
        return gainpoint;
    }

    public void setGainpoint(Integer gainpoint) {
        this.gainpoint = gainpoint;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductcoverimg() {
        return productcoverimg;
    }

    public void setProductcoverimg(String productcoverimg) {
        this.productcoverimg = productcoverimg == null ? null : productcoverimg.trim();
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getGainnums() {
        return gainnums;
    }

    public void setGainnums(Integer gainnums) {
        this.gainnums = gainnums;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
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
}