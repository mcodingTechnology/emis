package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="会员地址表")
public class MemberAddress implements Serializable {
    @ApiModelProperty("会员地址ID")
    private Integer id;

    @ApiModelProperty("会员openId")
    private String openid;

    @ApiModelProperty("会员Id")
    private Integer memberid;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("其他信息")
    private String other;

    private String regson;

    private static final long serialVersionUID = 1L;

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

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getRegson() {
        return regson;
    }

    public void setRegson(String regson) {
        this.regson = regson == null ? null : regson.trim();
    }
}