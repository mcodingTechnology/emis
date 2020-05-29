package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;

@ApiModel(value="加盟商级别")
public class MemberLevel implements Serializable {
//	#########以下非自动生成#############
	public static final Integer PRIORITY_LEVEL_1 = 100;

	public static final Object PRIORITY_LEVEL_2 = 200;

	public static final Object PRIORITY_LEVEL_3 = 300;

	public static final Object PRIORITY_LEVEL_4 = 400;

    public static final Integer MEMBER_LEVEL_TYPE_MEMBER = 1;

    public static final Integer MEMBER_LEVEL_TYPE_DISTRIBUTOR = 2;
//	##########end#############	
    private Integer id;

    @ApiModelProperty("级别名称")
    private String name;

    @ApiModelProperty("级别优先级")
    private Integer priority;

    @ApiModelProperty("级别下，享受的折扣")
    private Double discount;

    @ApiModelProperty("加盟费，保证金")
    private Integer deposit;

    @ApiModelProperty("信用限度")
    private Integer creditlimit;

    @ApiModelProperty("上一级的id")
    private Integer parentid;

    @ApiModelProperty("是否有效")
    private Byte isvaild;

    @ApiModelProperty("会员等级类型 1.会员等级 2.微商等级")
    private Integer type;

    @ApiModelProperty("消费总金额")
    private Integer consumption;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(Integer creditlimit) {
        this.creditlimit = creditlimit;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Byte getIsvaild() {
        return isvaild;
    }

    public void setIsvaild(Byte isvaild) {
        this.isvaild = isvaild;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }
}