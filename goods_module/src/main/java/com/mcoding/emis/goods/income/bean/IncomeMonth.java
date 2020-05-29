package com.mcoding.emis.goods.income.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="佣金月结算表")
public class IncomeMonth implements Serializable {
	
//	#########以下非自动生成，请不要覆盖############

	/**会员资料未完善**/
	public static final String STATUS_UNCOMPLETE_INFO = "0";
	/**未审核**/
	public static final String STATUS_UNCHECK = "1";
	/**已审核(未发红包)**/
	public static final String STATUS_CHECKED = "2";
	/**已发红包**/
	public static final String STATUS_REDPACK_SENT = "3";
	/**已收红包**/
	public static final String STATUS_REDPACK_RECEIVED = "4";
	/**未收完所有红包**/
	public static final String STATUS_REDPACK_UNCOMPLETE = "5";
	/**红包发送异常**/
	public static final String STATUS_REDPACK_SENT_EXCEPTION = "6";
//	####################
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("会员id")
    private Integer memberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("正式姓名")
    private String realName;

    @ApiModelProperty("身份证号")
    private String identity;

    private String openid;

    private String brandCode;

    private Integer channelId;

    @ApiModelProperty("会员级别id")
    private Integer levelId;

    @ApiModelProperty("会员级别名称")
    private String levelName;

    private Integer orderFee;

    @ApiModelProperty("结算佣金")
    private Integer income;

    @ApiModelProperty("结算积分")
    private Integer point;

    @ApiModelProperty("结算状态，0资料未完善，1未审核，2已审核，3已发红包，4已收红包")
    private String status;

    @ApiModelProperty("月份")
    private String month;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Integer getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Integer orderFee) {
        this.orderFee = orderFee;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}