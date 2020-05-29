package com.mcoding.emis.goods.incomeratio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IncomeRatio implements Serializable {
    private Integer id;

    private Integer dealerId;
    
    private String dealerName;

    private BigDecimal firstPrize;

    private BigDecimal perpetualAward;

    private String isValid;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    
    public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public BigDecimal getFirstPrize() {
        return firstPrize;
    }

    public void setFirstPrize(BigDecimal firstPrize) {
        this.firstPrize = firstPrize;
    }

    public BigDecimal getPerpetualAward() {
        return perpetualAward;
    }

    public void setPerpetualAward(BigDecimal perpetualAward) {
        this.perpetualAward = perpetualAward;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}