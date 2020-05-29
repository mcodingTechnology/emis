package com.mcoding.emis.goods.income.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.mcoding.emis.member.bean.member.MemberBankerInfo;

@ApiModel(value="income")
public class Income implements Serializable {
	
//	##########以下不是自动生成的代码，请不要覆盖###############
	
	/**个人资料未完善**/
	public static final String STATUS_UNCOMPLETE = "uncomplete";
	/**已完善**/
	public static final String STATUS_COMPLETED = "completed";
    
    private MemberBankerInfo memberBankerInfo;
    
    public MemberBankerInfo getMemberBankerInfo() {
		return memberBankerInfo;
	}

	public void setMemberBankerInfo(MemberBankerInfo memberBankerInfo) {
		this.memberBankerInfo = memberBankerInfo;
	}
	
//	#########################
	
    private Integer id;

    private Integer memberid;

    private String membername;

    @ApiModelProperty("级别id")
    private Integer levelId;

    @ApiModelProperty("级别名称")
    private String levelName;

    @ApiModelProperty("渠道id")
    private Integer channelId;

    private String openid;

    @ApiModelProperty("历史累计的佣金")
    private Integer commission;

    @ApiModelProperty("当前该发送的佣金")
    private Integer incomeUnsend;

    @ApiModelProperty("当前分销商状态")
    private String status;

    private Date createtime;

    private Date updatetime;

    @ApiModelProperty("返利总积分")
    private Integer totalpoint;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getIncomeUnsend() {
        return incomeUnsend;
    }

    public void setIncomeUnsend(Integer incomeUnsend) {
        this.incomeUnsend = incomeUnsend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Integer getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(Integer totalpoint) {
        this.totalpoint = totalpoint;
    }
}