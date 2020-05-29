package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class E3Order  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//条数
	private int line;
	//订单编号
	private String order_sn;
	//会员名称
	private String user_name;
	//收货人姓名
	private String consignee;
	//收货人的国家
	private String country_name;
	//收货人的省份
	private String province_name;
	//收货人的城市
	private String city_name;
	//收货人的地区
	private String district_name;
	//收货人的详细地址
	private String address;
	//收货人的邮政编码  可null
	private String zipcode;
	//收货人的电话号码  可null
	private String tel;
	//收货人的手机号码（电话号码和手机号码两个必须有一个不能为空）
	private String mobile;
	//邮箱  可null
	private String email;
	//订单生成时间  可null
	private Date add_time;
	//订单确认时间  可null
	private Date confirm_time;
	//订单支付时间  可null
	private Date pay_time;
	//店铺代号
	private String sd_code;
	//订单状态（0，未确认；1，已确认；2，已取消；3，无效；4，退货；5，完成）
	private int order_status;
	//配送状态。0-初始1-预分配缺货处理中 2-已完成预分配 3-已通知配货4-拣货中(已分配拣货任务) 5-已完成拣货 6-已发货 7-已出库 9-取消
	private int shipping_status;
	//付款状态（ 0:未付款;1:已付款 2，已付款；3，已结算）
	private int pay_status;
	//支付方式代码
	private String pay_code;
	//快递单号
	private String shipping_sn;
	//快递单号
	private String shipping_code;
	//运费
	private String shipping_fee;
	//商品总金额
	private String goods_amount;
	//订单总金额
	private String total_fee;
	//已付款金额
	private String money_paid;
	//应付款金额
	private String order_amount;
	//折扣金额
	private String discount;
	//最后更新时间
	private String last_update_time;
	//交易类型 1：款到发货 2：货到付款 3：担保交易
	private String trans_type;
	//外部交易号
	private String order_out_sn;
	//关联退单编号
	private String relating_return_sn;
	//退款金额
	private String return_fee;
	//是否已退款
	private String is_return;
	//商品总数量
	private String goods_count;
	//积分
	private Integer integral;
	//积分金额
	private Integer integral_money;
	
	private List<E3Item> items;



	public E3Order() {
		
	}
	
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Date getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public String getSd_code() {
		return sd_code;
	}

	public void setSd_code(String sd_code) {
		this.sd_code = sd_code;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public int getShipping_status() {
		return shipping_status;
	}

	public void setShipping_status(int shipping_status) {
		this.shipping_status = shipping_status;
	}

	public int getPay_status() {
		return pay_status;
	}

	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}

	public String getPay_code() {
		return pay_code;
	}

	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}

	public String getShipping_sn() {
		return shipping_sn;
	}

	public void setShipping_sn(String shipping_sn) {
		this.shipping_sn = shipping_sn;
	}

	public String getShipping_code() {
		return shipping_code;
	}

	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}


	public String getShipping_fee() {
		return shipping_fee;
	}

	public void setShipping_fee(String shipping_fee) {
		this.shipping_fee = shipping_fee;
	}

	public String getGoods_amount() {
		return goods_amount;
	}

	public void setGoods_amount(String goods_amount) {
		this.goods_amount = goods_amount;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getMoney_paid() {
		return money_paid;
	}

	public void setMoney_paid(String money_paid) {
		this.money_paid = money_paid;
	}

	public String getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getOrder_out_sn() {
		return order_out_sn;
	}

	public void setOrder_out_sn(String order_out_sn) {
		this.order_out_sn = order_out_sn;
	}

	public String getRelating_return_sn() {
		return relating_return_sn;
	}

	public void setRelating_return_sn(String relating_return_sn) {
		this.relating_return_sn = relating_return_sn;
	}

	public String getReturn_fee() {
		return return_fee;
	}

	public void setReturn_fee(String return_fee) {
		this.return_fee = return_fee;
	}

	public String getIs_return() {
		return is_return;
	}

	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}

	public String getGoods_count() {
		return goods_count;
	}

	public void setGoods_count(String goods_count) {
		this.goods_count = goods_count;
	}

	public List<E3Item> getItems() {
		return items;
	}

	public void setItems(List<E3Item> items) {
		this.items = items;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getIntegral_money() {
		return integral_money;
	}

	public void setIntegral_money(Integer integral_money) {
		this.integral_money = integral_money;
	}

	//转换 由微商order转换到E3Order
	public E3Order(Order order) {
		if(order != null){
			//TODO 转换
/*			//条数
			this.line = "";
			//订单编号
			this.order_sn = order.getOutno();
			//会员名称
			this.user_name = "";
			//收货人姓名
			this.consignee = order.getReceiver();
			//收货人的国家
			this.country_name = "";
			//收货人的省份
			this.province_name = "";
			//收货人的城市
			this.city_name = "";
			//收货人的地区
			this.district_name = "";
			//收货人的详细地址
			this.address = order.getAddress();
			//收货人的邮政编码  可null
			this.zipcode = "";
			//收货人的电话号码  可null
			this.tel = "";
			//收货人的手机号码（电话号码和手机号码两个必须有一个不能为空）
			this.mobile = order.getReceiverphone();
			//邮箱  可null
			this.email = "";
			//订单生成时间  可null
			this.add_time = "";
			//订单确认时间  可null
			this.confirm_time = "";
			//订单支付时间  可null
			this.pay_time = "";
			//店铺代号
			this.sd_code = "015";
			//订单状态（0，未确认；1，已确认；2，已取消；3，无效；4，退货；5，完成）
			this.order_status = 1;
			//配送状态。0-初始1-预分配缺货处理中 2-已完成预分配 3-已通知配货4-拣货中(已分配拣货任务) 5-已完成拣货 6-已发货 7-已出库 9-取消
			this.shipping_status = 0;
			//付款状态（ 0:未付款,1:付款中 2，已付款；3，已结算）
			this.pay_status = 2;
			//支付方式代码
			this.pay_code = "weixin";
			//快递单号
			this.shipping_sn = "";
			//快递单号
			this.shipping_code = "";
			//运费
			this.shipping_fee = "";
			//商品总金额
			this.goods_amount = "";
			//订单总金额
			this.total_fee = "";
			//已付款金额
			this.money_paid = "";
			//应付款金额
			this.order_amount = "";
			//折扣金额
			this.discount = "";
			//最后更新时间
			this.last_update_time = "";
			//交易类型 1：款到发货 2：货到付款 3：担保交易
			this.trans_type = "";
			//外部交易号
			this.order_out_sn = "";
			//关联退单编号
			this.relating_return_sn = "";
			//退款金额
			this.return_fee = "";
			//是否已退款
			this.is_return = "";
			//商品总数量
			this.goods_count = "";
			
			
			this.items = new ArrayList<E3Item>();
			//TODO 订单商品转换
			*/
		}
	}
	
	
	//校验订单信息是否符合E3的要求
	public boolean verification(){

		//订单编号
		if(this.order_sn == null || this.order_sn.length() == 0){
			return false;
		}

		//会员名称
		if(this.user_name == null || this.user_name.length() == 0){
			return false;
		}

		//收货人姓名
		if(this.consignee == null || this.consignee.length() == 0){
			return false;
		}

		//收货人的国家
		if(this.country_name == null || this.country_name.length() == 0){
			return false;
		}

		//收货人的省份
		if(this.province_name == null || this.province_name.length() == 0){
			return false;
		}

		//收货人的城市
		if(this.city_name == null || this.city_name.length() == 0){
			return false;
		}

		//收货人的地区
		if(this.district_name == null || this.district_name.length() == 0){
			return false;
		}

		//收货人的详细地址
		if(this.address == null || this.address.length() == 0){
			return false;
		}

		//收货人的手机号码
		if(this.mobile == null || this.mobile.length() == 0){
			return false;
		}

		//店铺代号
		if(this.sd_code == null || this.sd_code.length() == 0){
			return false;
		}

		//订单状态
		if(this.order_status != 1){
			return false;
		}

		//配送状态
		if(this.shipping_status != 0){
			return false;
		}

		//付款状态
		if(this.pay_status != 2){
			return false;
		}

		//支付方式代码
		if(this.pay_code == null || this.pay_code.length() == 0){
			return false;
		}

		//商品总金额
		if(this.goods_amount == null || this.goods_amount.length() == 0){
			return false;
		}

		//订单总金额
		if(this.total_fee == null || this.total_fee.length() == 0){
			return false;
		}

		//已付款金额
		if(this.money_paid == null || this.money_paid.length() == 0){
			return false;
		}

		//应付款金额
		if(this.order_amount == null || this.order_amount.length() == 0){
			return false;
		}

		//折扣金额
		if(this.discount == null || this.discount.length() == 0){
			return false;
		}

		//交易类型
		if(this.trans_type == null || this.trans_type.length() == 0){
			return false;
		}

		//商品总数量
		if(this.goods_count == null || this.goods_count.length() == 0){
			return false;
		}

		
		if(this.items == null || (this.items.size()+ "").equals(this.goods_count)){
			return false;
		}
		
		//TODO 检验items内部商品
		
		return true;
	}
}
