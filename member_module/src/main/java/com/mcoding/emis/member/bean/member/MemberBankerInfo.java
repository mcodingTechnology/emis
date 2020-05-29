package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="加盟商银行信息")
public class MemberBankerInfo implements Serializable {
    private Integer id;

    @ApiModelProperty("member表id")
    private Integer memberid;

    @ApiModelProperty("银行名称")
    private String bankname;

    @ApiModelProperty("开户行")
    private String subbranch;

    @ApiModelProperty("银行卡号")
    private String bankcardnum;

    @ApiModelProperty("真实姓名")
    private String realname;

    @ApiModelProperty("地址")
    private String loaction;

    @ApiModelProperty("身份证号码")
    private String identity;

    @ApiModelProperty("证件类型")
    private String type;

    @ApiModelProperty("创建时间")
    private Date createtime;

    @ApiModelProperty("更新时间")
    private Date updatetime;

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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch == null ? null : subbranch.trim();
    }

    public String getBankcardnum() {
        return bankcardnum;
    }

    public void setBankcardnum(String bankcardnum) {
        this.bankcardnum = bankcardnum == null ? null : bankcardnum.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getLoaction() {
        return loaction;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction == null ? null : loaction.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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