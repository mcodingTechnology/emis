package com.mcoding.emis.goods.company.bean;

import java.io.Serializable;
import java.util.List;

public class CompanyResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private int companyId;

	private String companyName;

	private List<String> addresses;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

}
