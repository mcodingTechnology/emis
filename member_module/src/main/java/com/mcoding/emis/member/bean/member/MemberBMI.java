package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="mr_member_bmi")
public class MemberBMI implements Serializable {
    private Integer id;

    @ApiModelProperty("模块ID 1-8从左至右")
    private Integer formId;

    @ApiModelProperty("openid")
    private String openid;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("身高")
    private String height;

    @ApiModelProperty("体重")
    private String weight;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("运动类型")
    private String sportType;

    @ApiModelProperty("腕围")
    private String wristCircumference;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType == null ? null : sportType.trim();
    }

    public String getWristCircumference() {
        return wristCircumference;
    }

    public void setWristCircumference(String wristCircumference) {
        this.wristCircumference = wristCircumference == null ? null : wristCircumference.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}