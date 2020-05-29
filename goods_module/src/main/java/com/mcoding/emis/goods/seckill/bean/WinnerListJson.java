package com.mcoding.emis.goods.seckill.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WinnerListJson implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer seckillId;
	
	private Integer productId;
	
	private String productName;
	
	private String productConvert;

	private Date startTime;
	
	private String startTimeStr;
	private List<SeckillResult> winnerList;

	public List<SeckillResult> getWinnerList() {
		return winnerList;
	}

	public void setWinnerList(List<SeckillResult> winnerList) {
		this.winnerList = winnerList;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductConvert() {
		return productConvert;
	}

	public void setProductConvert(String productConvert) {
		this.productConvert = productConvert;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Integer seckillId) {
		this.seckillId = seckillId;
	}

	public String getStartTimeStr() {
		return getTimeStr(this.getStartTime());
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
    
	private String getTimeStr(Date time){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(time);
	}
}
