package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="mr_member_join_record")
public class MemberJoinRecord implements Serializable {
	
	public static final String STATUS_APPLY = "100"; //申请
	public static final String STATUS_CONFIRM = "101"; //通过
	
    @ApiModelProperty("请求id")
    private Integer id;

    @ApiModelProperty("会员ID")
    private Integer memberid;

    @ApiModelProperty("微信openId")
    private String openid;

    @ApiModelProperty("会员名称")
    private String membername;

    @ApiModelProperty("会员级别Id")
    private Integer leveid;

    @ApiModelProperty("会员级别名称")
    private String levelname;

    @ApiModelProperty("上级会员Id")
    private Integer parentid;

    @ApiModelProperty("上级会员ID")
    private String parentname;

    @ApiModelProperty("上级会员级别ID")
    private Integer parentlevelid;

    @ApiModelProperty("上级会员级别名称")
    private String parentlevelname;

    @ApiModelProperty("请求状态")
    private String status;

    @ApiModelProperty("创建时间")
    private Date createtime;

    @ApiModelProperty("审核时间")
    private Date confirmtime;

    @ApiModelProperty("品牌")
    private String brandCode;

    @ApiModelProperty("所在区域")
    private String regson;

    @ApiModelProperty("所在健身房")
    private String gymroom;

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

    public Integer getLeveid() {
        return leveid;
    }

    public void setLeveid(Integer leveid) {
        this.leveid = leveid;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname == null ? null : levelname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname == null ? null : parentname.trim();
    }

    public Integer getParentlevelid() {
        return parentlevelid;
    }

    public void setParentlevelid(Integer parentlevelid) {
        this.parentlevelid = parentlevelid;
    }

    public String getParentlevelname() {
        return parentlevelname;
    }

    public void setParentlevelname(String parentlevelname) {
        this.parentlevelname = parentlevelname == null ? null : parentlevelname.trim();
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

    public Date getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getRegson() {
        return regson;
    }

    public void setRegson(String regson) {
        this.regson = regson;
    }

    public String getGymroom() {
        return gymroom;
    }

    public void setGymroom(String gymroom) {
        this.gymroom = gymroom;
    }
}