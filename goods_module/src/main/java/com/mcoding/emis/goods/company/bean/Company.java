package com.mcoding.emis.goods.company.bean;

import java.io.Serializable;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String companycode;

	private String companyname;

	private Integer companytype;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode == null ? null : companycode.trim();
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname == null ? null : companyname.trim();
	}

	public Integer getCompanytype() {
		return companytype;
	}

	public void setCompanytype(Integer companytype) {
		this.companytype = companytype;
	}
}