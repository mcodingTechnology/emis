package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="会员表")
public class Member implements Serializable {
	
//	############以下，非自动生成###############
	public static final String GENDER_MAN = "男";
	public static final String GENDER_WOMAN = "女";
	
	public static final Integer IS_REFERRALNOTIC_DEFAULT = 0;//非下线
	public static final Integer IS_REFERRALNOTIC_NO_POST = 1;//已成为下线，未推送
	public static final Integer IS_REFERRALNOTIC_POST =  2;//已推送

    @ApiModelProperty("订单总金额")
    private Integer orderSumPrice;

    @ApiModelProperty("上级用户")
    private String parentName;

    @ApiModelProperty("下线数量")
    private String childrenMember;

    @ApiModelProperty("下级数量")
    private String childrenDealer;

    @ApiModelProperty("上级的等级id")
    private Integer parentLevelId;
//	############ end           ###############
	
    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("上级用户id")
    private Integer parentMemberId;

    private String levelName;

    private Integer levelId;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("姓名")
    private String fullName;

    @ApiModelProperty("微信OpenId")
    private String openid;

    private Integer channelsId;

    @ApiModelProperty("会员类型")
    private String memberType;

    private String regionProvince;

    private String regionCity;

    private String regionArea;

    private String birthday;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("注册来源")
    private String enrollChannel;

    @ApiModelProperty("身份证号码")
    private String governmentId;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("邮递地址")
    private String deliveryAddress;

    @ApiModelProperty("邮编")
    private String zipCode;

    @ApiModelProperty("品牌编号")
    private String brandCode;

    @ApiModelProperty("校验码")
    private String accessToken;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("完善个人资料选填问题：您关注的问题")
    private String concerns;

    @ApiModelProperty("完善个人资料选填问题：职位")
    private String position;

    @ApiModelProperty("完善个人资料选填问题：您还想关注谁的健康")
    private String concernsPerson;

    @ApiModelProperty("完善个人资料选填问题：您想关注的其他健康问题")
    private String healthProblem;

    @ApiModelProperty("工作症状")
    private String activitySymptom;

    @ApiModelProperty("预留字段1 身高")
    private String ext1;

    @ApiModelProperty("预留字段2 体重")
    private String ext2;

    @ApiModelProperty("签到游戏中是否已领取奖品 已领取")
    private String ext3;

    @ApiModelProperty("会员积分总额")
    private Integer pointSum;

    @ApiModelProperty("关联表mr_company的Id")
    private Integer companyId;

    @ApiModelProperty("是否提交过健康报告，0为未提交，1为已提交")
    private Integer isHealthCheck;

    @ApiModelProperty("成为下线后是否已推送模板消息 0非下线；1 未推送；2 已推送")
    private Integer isReferralNotice;

    @ApiModelProperty("运动类型，1不运动，2健美增肌，3其他运动")
    private Integer exerciseType;

    @ApiModelProperty("呢称")
    private String nickName;

    @ApiModelProperty("头像")
    private String headimgurl;

    @ApiModelProperty("会员标签")
    private String tags;
    
    private String memberCount;

    private static final long serialVersionUID = 1L;

    
    
    public String getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getParentMemberId() {
        return parentMemberId;
    }

    public void setParentMemberId(Integer parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getChannelsId() {
        return channelsId;
    }

    public void setChannelsId(Integer channelsId) {
        this.channelsId = channelsId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType == null ? null : memberType.trim();
    }

    public String getRegionProvince() {
        return regionProvince;
    }

    public void setRegionProvince(String regionProvince) {
        this.regionProvince = regionProvince == null ? null : regionProvince.trim();
    }

    public String getRegionCity() {
        return regionCity;
    }

    public void setRegionCity(String regionCity) {
        this.regionCity = regionCity == null ? null : regionCity.trim();
    }

    public String getRegionArea() {
        return regionArea;
    }

    public void setRegionArea(String regionArea) {
        this.regionArea = regionArea == null ? null : regionArea.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEnrollChannel() {
        return enrollChannel;
    }

    public void setEnrollChannel(String enrollChannel) {
        this.enrollChannel = enrollChannel == null ? null : enrollChannel.trim();
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId == null ? null : governmentId.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
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

    public String getConcerns() {
        return concerns;
    }

    public void setConcerns(String concerns) {
        this.concerns = concerns == null ? null : concerns.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getConcernsPerson() {
        return concernsPerson;
    }

    public void setConcernsPerson(String concernsPerson) {
        this.concernsPerson = concernsPerson == null ? null : concernsPerson.trim();
    }

    public String getHealthProblem() {
        return healthProblem;
    }

    public void setHealthProblem(String healthProblem) {
        this.healthProblem = healthProblem == null ? null : healthProblem.trim();
    }

    public String getActivitySymptom() {
        return activitySymptom;
    }

    public void setActivitySymptom(String activitySymptom) {
        this.activitySymptom = activitySymptom == null ? null : activitySymptom.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public Integer getPointSum() {
        return pointSum;
    }

    public void setPointSum(Integer pointSum) {
        this.pointSum = pointSum;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getIsHealthCheck() {
        return isHealthCheck;
    }

    public void setIsHealthCheck(Integer isHealthCheck) {
        this.isHealthCheck = isHealthCheck;
    }

    public Integer getIsReferralNotice() {
        return isReferralNotice;
    }

    public void setIsReferralNotice(Integer isReferralNotice) {
        this.isReferralNotice = isReferralNotice;
    }

    public Integer getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Integer exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildrenMember() {
        return childrenMember;
    }

    public void setChildrenMember(String childrenMember) {
        this.childrenMember = childrenMember;
    }

    public String getChildrenDealer() {
        return childrenDealer;
    }

    public void setChildrenDealer(String childrenDealer) {
        this.childrenDealer = childrenDealer;
    }

    public Integer getOrderSumPrice() {
        return orderSumPrice;
    }

    public void setOrderSumPrice(Integer orderSumPrice) {
        this.orderSumPrice = orderSumPrice;
    }

    public Integer getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(Integer parentLevelId) {
        this.parentLevelId = parentLevelId;
    }
}