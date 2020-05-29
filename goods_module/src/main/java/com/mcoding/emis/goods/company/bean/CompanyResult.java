package com.mcoding.emis.goods.company.bean;

import java.io.Serializable;
import java.util.List;

public class CompanyResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Company company;

	private List<CompanyAddress> addresses;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<CompanyAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CompanyAddress> addresses) {
		this.addresses = addresses;
	}

}
