package com.mcoding.emis.goods.seckill.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class SeckillResult implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer seckillid;

    private String nickname;

    private String headimgurl;

    private String openid;

    private Integer num;

    private Integer productid;

    private Integer orderid;

    private String productname;

    private Date createtime;

    private Date updatetime;

    private String createTimeStr;

    private String updateTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeckillid() {
        return seckillid;
    }

    public void setSeckillid(Integer seckillid) {
        this.seckillid = seckillid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
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
    
	public String getCreateTimeStr() {
		if(StringUtils.isNotBlank(createTimeStr)){
			return createTimeStr;
		}
		return getTimeStr(this.getCreatetime());
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		if(StringUtils.isNotBlank(updateTimeStr)){
			return updateTimeStr;
		}
		return getTimeStr(this.getUpdatetime());
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