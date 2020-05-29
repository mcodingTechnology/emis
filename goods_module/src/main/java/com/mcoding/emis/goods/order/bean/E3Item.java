package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;

public class E3Item implements Serializable{

	private static final long serialVersionUID = 1L;
	//款号
	private String sku_sn;
	//订单编号
	private String order_sn;
	//商品货号
	private String goods_sn;
	//商品名称
	private String goods_name;
	//颜色代码 可null
	private String color_code;
	//尺码代码 可null
	private String size_code;
	//颜色:绿色 尺码:35 可null
	private String goods_attr;
	//商品的成交价，退款时用到
	private String transaction_price;
	//商品单价(折前) 可null
	private String market_price;
	//商品单价(折后)
	private String goods_price;
	//折扣（具体金额）
	private String discount;
	//数量
	private int goods_number;
	//是否礼品（ 0:正常商品;1:礼品;）
	private int is_gift;
	
	public String getSku_sn() {
		return sku_sn;
	}
	public void setSku_sn(String sku_sn) {
		this.sku_sn = sku_sn;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getGoods_sn() {
		return goods_sn;
	}
	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getColor_code() {
		return color_code;
	}
	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}
	public String getSize_code() {
		return size_code;
	}
	public void setSize_code(String size_code) {
		this.size_code = size_code;
	}
	public String getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}
	public String getTransaction_price() {
		return transaction_price;
	}
	public void setTransaction_price(String transaction_price) {
		this.transaction_price = transaction_price;
	}
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public int getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}
	public int getIs_gift() {
		return is_gift;
	}
	public void setIs_gift(int is_gift) {
		this.is_gift = is_gift;
	}
	
}
