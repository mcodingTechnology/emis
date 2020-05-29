package com.mcoding.emis.goods.company.bean;

import java.io.Serializable;
import java.util.List;

public class CompanyApiResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String companyName;

	private List<CompanyApiAdressResp> addresses;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<CompanyApiAdressResp> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CompanyApiAdressResp> addresses) {
		this.addresses = addresses;
	}

}
