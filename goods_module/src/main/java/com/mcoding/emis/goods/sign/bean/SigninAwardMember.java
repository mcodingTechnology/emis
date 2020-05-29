package com.mcoding.emis.goods.sign.bean;

import java.io.Serializable;

public class SigninAwardMember implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String openid;

    private String phone;

    private String brandcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }
}