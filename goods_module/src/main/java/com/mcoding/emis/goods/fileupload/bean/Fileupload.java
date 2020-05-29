package com.mcoding.emis.goods.fileupload.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.emis.goods.common.utils.jackson.CustomDateSerializer;

public class Fileupload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//show ziduan 
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	private Integer id;

	private Date upDatatime;

	private Integer upUserId;

	private String upType;

	private String upFullpath;

	private String upTitle;

	private String isShare;


	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpDatatime() {
		return upDatatime;
	}

	public void setUpDatatime(Date upDatatime) {
		this.upDatatime = upDatatime;
	}

	public Integer getUpUserId() {
		return upUserId;
	}

	public void setUpUserId(Integer upUserId) {
		this.upUserId = upUserId;
	}

	public String getUpType() {
		return upType;
	}

	public void setUpType(String upType) {
		this.upType = upType;
	}

	public String getUpFullpath() {
		return upFullpath;
	}

	public void setUpFullpath(String upFullpath) {
		this.upFullpath = upFullpath;
	}

	public String getUpTitle() {
		return upTitle;
	}

	public void setUpTitle(String upTitle) {
		this.upTitle = upTitle;
	}

}