package com.mcoding.emis.goods.order.bean;

import java.util.List;

public class E3SearchReturnJson {

	private E3PageReturnJson page;
	
	private List<E3OrderListGetJson> orderListGets;

	public E3PageReturnJson getPage() {
		return page;
	}

	public void setPage(E3PageReturnJson page) {
		this.page = page;
	}

	public List<E3OrderListGetJson> getOrderListGets() {
		return orderListGets;
	}

	public void setOrderListGets(List<E3OrderListGetJson> orderListGets) {
		this.orderListGets = orderListGets;
	}
	
}
