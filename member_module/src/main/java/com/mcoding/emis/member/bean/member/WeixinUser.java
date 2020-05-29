package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="微信用户表")
public class WeixinUser implements Serializable {
	
//	############## 以下非自动生成，并不要覆盖######################
	public static final String SEX_MAN = "1";
    public static final String SEX_WOMAN = "2";
    private Integer allSignnum;//非字段值，连续签到次数总和
    
    public Integer getAllSignnum() {
		return allSignnum;
	}

	public void setAllSignnum(Integer allSignnum) {
		this.allSignnum = allSignnum;
	}
//	######################end  ################

    @ApiModelProperty("会员ID")
    private Integer wxuserid;

    @ApiModelProperty("微信OpenId")
    private String openid;

    private String shareId;

    private String mobilephone;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("是否订阅")
    private String subscribe;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("头像")
    private String headimgurl;

    @ApiModelProperty("关注事件")
    private Date subscribetime;

    @ApiModelProperty("公众号")
    private String unionid;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createtime;

    @ApiModelProperty("更新时间")
    private Date updatetime;

    @ApiModelProperty("品牌编号")
    private String brandCode;

    @ApiModelProperty("第一次关注的时间")
    private Date firstSubscribeTime;

    private static final long serialVersionUID = 1L;

    public Integer getWxuserid() {
        return wxuserid;
    }

    public void setWxuserid(Integer wxuserid) {
        this.wxuserid = wxuserid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId == null ? null : shareId.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe == null ? null : subscribe.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Date getSubscribetime() {
        return subscribetime;
    }

    public void setSubscribetime(Date subscribetime) {
        this.subscribetime = subscribetime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public Date getFirstSubscribeTime() {
        return firstSubscribeTime;
    }

    public void setFirstSubscribeTime(Date firstSubscribeTime) {
        this.firstSubscribeTime = firstSubscribeTime;
    }
}