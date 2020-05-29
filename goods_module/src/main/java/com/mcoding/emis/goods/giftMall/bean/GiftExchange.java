package com.mcoding.emis.goods.giftMall.bean;

import java.io.Serializable;
import java.util.Date;

public class GiftExchange implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer orderid;

    private Integer consumepoint;

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

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getConsumepoint() {
        return consumepoint;
    }

    public void setConsumepoint(Integer consumepoint) {
        this.consumepoint = consumepoint;
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