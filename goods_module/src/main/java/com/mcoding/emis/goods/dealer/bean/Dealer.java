package com.mcoding.emis.goods.dealer.bean;

import java.io.Serializable;
import java.util.Date;

public class Dealer implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String dealername;

    private String dealerno;

    private String dealerstorename;

    private String dealerstoreurl;

    private String brandcode;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDealername() {
        return dealername;
    }

    public void setDealername(String dealername) {
        this.dealername = dealername == null ? null : dealername.trim();
    }

    public String getDealerno() {
        return dealerno;
    }

    public void setDealerno(String dealerno) {
        this.dealerno = dealerno == null ? null : dealerno.trim();
    }

    public String getDealerstorename() {
        return dealerstorename;
    }

    public void setDealerstorename(String dealerstorename) {
        this.dealerstorename = dealerstorename == null ? null : dealerstorename.trim();
    }

    public String getDealerstoreurl() {
        return dealerstoreurl;
    }

    public void setDealerstoreurl(String dealerstoreurl) {
        this.dealerstoreurl = dealerstoreurl == null ? null : dealerstoreurl.trim();
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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}