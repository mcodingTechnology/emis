package com.mcoding.emis.goods.order.bean;

public class E3OrderReturnJson {

	//订单编号
	private String order_sn;
	//交易号
	private String deal_code;
	//付款状态
	private String pay_status;
	//订单状态（0-未确认； 1-已确认； 3-已作废；5-已完成）
	private String order_status;
	//物流状态（0-初始 1-预分配缺货 处理中 2-已完成预分配 3-已通 知配货 4-拣货中(已分配拣货任 务) 5-已完成拣货 6-已发货 7已出库 9-取消）
	private String shipping_status;
	//来源类型
	private String lylx;
	//快递公司代码
	private String shipping_code;
	//物流单号
	private String shipping_sn;
	//快递费
	private String shipping_fee;
	//商店代码
	private String sd_code;
	//最后更新时间 "2015-09-10 18:17:36"
	private String last_update;
	
	
	
	
	
	
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getDeal_code() {
		return deal_code;
	}
	public void setDeal_code(String deal_code) {
		this.deal_code = deal_code;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getShipping_status() {
		return shipping_status;
	}
	public void setShipping_status(String shipping_status) {
		this.shipping_status = shipping_status;
	}
	public String getLylx() {
		return lylx;
	}
	public void setLylx(String lylx) {
		this.lylx = lylx;
	}
	public String getShipping_code() {
		return shipping_code;
	}
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	public String getShipping_sn() {
		return shipping_sn;
	}
	public void setShipping_sn(String shipping_sn) {
		this.shipping_sn = shipping_sn;
	}
	public String getShipping_fee() {
		return shipping_fee;
	}
	public void setShipping_fee(String shipping_fee) {
		this.shipping_fee = shipping_fee;
	}
	public String getSd_code() {
		return sd_code;
	}
	public void setSd_code(String sd_code) {
		this.sd_code = sd_code;
	}
	public String getLast_update() {
		return last_update;
	}
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
	
	
	

}
