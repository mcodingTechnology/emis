package com.mcoding.emis.goods.order.bean;

import java.util.List;

public class E3AddOrderRequestJson {
	private int total;
	private List<E3Order> orderLists;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<E3Order> getOrderLists() {
		return orderLists;
	}
	public void setOrderLists(List<E3Order> orderLists) {
		this.orderLists = orderLists;
	}
	
}
