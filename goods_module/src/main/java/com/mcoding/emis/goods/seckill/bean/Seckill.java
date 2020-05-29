package com.mcoding.emis.goods.seckill.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class Seckill implements Serializable{
	private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String brandcode;

    private Integer productid;

    private String productname;

    private String productconvert;

    private Integer sku;

    private Integer needpoint;

    private Integer ordernum;

    private String status;

    private Date starttime;

    private Date endtime;

    private Date createtime;

    private Date updatetime;

    private String startTimeStr;

    private String endTimeStr;

    private String createTimeStr;

    private String updateTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductconvert() {
        return productconvert;
    }

    public void setProductconvert(String productconvert) {
        this.productconvert = productconvert == null ? null : productconvert.trim();
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getNeedpoint() {
        return needpoint;
    }

    public void setNeedpoint(Integer needpoint) {
        this.needpoint = needpoint;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

	public String getStartTimeStr() {
		if(StringUtils.isNotBlank(startTimeStr)){
			return startTimeStr;
		}
		startTimeStr = getTimeStr(this.getStarttime());
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		if(StringUtils.isNotBlank(endTimeStr)){
			return endTimeStr;
		}
		endTimeStr = getTimeStr(this.getEndtime());
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getCreateTimeStr() {
		if(StringUtils.isNotBlank(createTimeStr)){
			return createTimeStr;
		}
		createTimeStr = getTimeStr(this.getCreatetime());
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		if(StringUtils.isNotBlank(updateTimeStr)){
			return updateTimeStr;
		}
		updateTimeStr = getTimeStr(this.getUpdatetime());
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
    
	private String getTimeStr(Date time){
		if(time == null){
			time = new Date();
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(time);
	}
    
}