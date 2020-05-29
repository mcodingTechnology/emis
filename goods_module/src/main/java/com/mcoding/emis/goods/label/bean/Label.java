package com.mcoding.emis.goods.label.bean;

import java.io.Serializable;

public class Label implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer type;

	private Integer correlationId = -1;

	private String mark;

	private Integer hit = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(Integer correlationId) {
		this.correlationId = correlationId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark == null ? null : mark.trim();
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}
}