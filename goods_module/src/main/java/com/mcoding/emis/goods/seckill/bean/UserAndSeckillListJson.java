package com.mcoding.emis.goods.seckill.bean;

import java.io.Serializable;
import java.util.List;

public class UserAndSeckillListJson implements Serializable{
	private static final long serialVersionUID = 2L;
	private List<Seckill> seckillList;
    private String headimgUrl;//非字段值，头像

    private String nickName;//非字段值，昵称
    
    private Integer pointSum;//非字段值，积分余额

	public List<Seckill> getSeckillList() {
		return seckillList;
	}

	public void setSeckillList(List<Seckill> seckillList) {
		this.seckillList = seckillList;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getPointSum() {
		return pointSum;
	}

	public void setPointSum(Integer pointSum) {
		this.pointSum = pointSum;
	}
}
