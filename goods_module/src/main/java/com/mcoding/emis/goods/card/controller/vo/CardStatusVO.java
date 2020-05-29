package com.mcoding.emis.goods.card.controller.vo;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeProductRef;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.product.bean.Product;

/**
 * 卡状态vo
 * @author hzy
 *
 */
public class CardStatusVO {
	
	private Integer cardStatus;
	private Order order;
	private CardType cardType;
	private List<CardTypeProductRef> productList;
	
	public Integer getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public List<CardTypeProductRef> getProductList() {
		return productList;
	}
	public void setProductList(List<CardTypeProductRef> productList) {
		this.productList = productList;
	}
}
