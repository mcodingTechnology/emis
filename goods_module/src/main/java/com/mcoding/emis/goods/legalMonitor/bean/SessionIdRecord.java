package com.mcoding.emis.goods.legalMonitor.bean;

import java.util.Date;

public class SessionIdRecord {
	private String sessionId;
	private Date lastTime;// 上次访问的时间
	private Integer count; // 以该sessionid访问接口的次数
	private int second;// 距离上次访问的秒数
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	@Override
	public String toString() {
		return "IpRecord [sessionId=" + sessionId + ", lastTime=" + lastTime + ", count="
				+ count + ", second=" + second + "]";
	}
}
