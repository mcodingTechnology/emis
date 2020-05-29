package com.mcoding.emis.goods.product.bean;

import java.io.Serializable;
import java.util.List;

public class ProductList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Product> proList;

	public List<Product> getProList() {
		return proList;
	}

	public void setProList(List<Product> proList) {
		this.proList = proList;
	}

}
