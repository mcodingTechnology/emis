package com.mcoding.emis.goods.company.bean;

import java.util.List;

public class CompanyReq {

	private Company company;

	private List<CompanyAddress> addresses;

	private List<CompanyAddress> newAddresses;

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

	public List<CompanyAddress> getNewAddresses() {
		return newAddresses;
	}

	public void setNewAddresses(List<CompanyAddress> newAddresses) {
		this.newAddresses = newAddresses;
	}

}
