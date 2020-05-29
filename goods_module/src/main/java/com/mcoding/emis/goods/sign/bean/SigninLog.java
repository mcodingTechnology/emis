package com.mcoding.emis.goods.sign.bean;

import java.io.Serializable;
import java.util.Date;

public class SigninLog  implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String openid;

    private String membername;

    private Integer memberid;

    private Integer signintegral;

    private Date signtime;

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

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getSignintegral() {
        return signintegral;
    }

    public void setSignintegral(Integer signintegral) {
        this.signintegral = signintegral;
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }
}