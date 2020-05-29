package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;
import java.util.List;

public class E3OrderJson implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private E3Order orderLists;
	private List<E3Item> items;
	
	public E3Order getOrderLists() {
		return orderLists;
	}
	public void setOrderLists(E3Order orderLists) {
		this.orderLists = orderLists;
	}
	public List<E3Item> getItems() {
		return items;
	}
	public void setItems(List<E3Item> items) {
		this.items = items;
	}
	
	
}
