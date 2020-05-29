package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;
import java.util.List;

import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.bean.member.WeixinUser;

public class OrderAndProducts implements Serializable {

	private static final long serialVersionUID = 1L;

	private Order orderInfo;
	private MemberAddress addressInfo;
	private WeixinUser weixinUser;
	private List<OrderProducts> orderProductsInfo;
	private List<OrderDiscount> orderPreferentialInfo;
	private List<OrderDiscount> orderGiftInfo;
	private Member member;
	private Member parentMember;

	public Order getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(Order orderInfo) {
		this.orderInfo = orderInfo;
	}

	public MemberAddress getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(MemberAddress addressInfo) {
		this.addressInfo = addressInfo;
	}

	public List<OrderProducts> getOrderProductsInfo() {
		return orderProductsInfo;
	}

	public void setOrderProductsInfo(List<OrderProducts> orderProductsInfo) {
		this.orderProductsInfo = orderProductsInfo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<OrderDiscount> getOrderPreferentialInfo() {
		return orderPreferentialInfo;
	}

	public void setOrderPreferentialInfo(List<OrderDiscount> orderPreferentialInfo) {
		this.orderPreferentialInfo = orderPreferentialInfo;
	}

	public List<OrderDiscount> getOrderGiftInfo() {
		return orderGiftInfo;
	}

	public void setOrderGiftInfo(List<OrderDiscount> orderGiftInfo) {
		this.orderGiftInfo = orderGiftInfo;
	}

	public WeixinUser getWeixinUser() {
		return weixinUser;
	}

	public void setWeixinUser(WeixinUser weixinUser) {
		this.weixinUser = weixinUser;
	}

	public Member getParentMember() {
		return parentMember;
	}

	public void setParentMember(Member parentMember) {
		this.parentMember = parentMember;
	}
}
