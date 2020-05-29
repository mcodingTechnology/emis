package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;
import java.util.List;

public class MallgameGiftStakeResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private int isOrder = 0;
	private int activityStatus = 0;
	private String openId;
	private String userName;
	private String userImg;
	private String userGoodsPoint;
	private String userPoint;
	private String goodsId;
	private String goodsImg;
	private String goodsName;
	private String startTime;
	private String endTime;
	private String needPoint;
	private String nowPoint;
	private String listCount;
	private List<MallgameStakeData> list;

	public int getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(int isOrder) {
		this.isOrder = isOrder;
	}

	public int getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(int activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getUserGoodsPoint() {
		return userGoodsPoint;
	}

	public void setUserGoodsPoint(String userGoodsPoint) {
		this.userGoodsPoint = userGoodsPoint;
	}

	public String getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(String userPoint) {
		this.userPoint = userPoint;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getNeedPoint() {
		return needPoint;
	}

	public void setNeedPoint(String needPoint) {
		this.needPoint = needPoint;
	}

	public String getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(String nowPoint) {
		this.nowPoint = nowPoint;
	}

	public String getListCount() {
		return listCount;
	}

	public void setListCount(String listCount) {
		this.listCount = listCount;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<MallgameStakeData> list) {
		this.list = list;
	}

}
