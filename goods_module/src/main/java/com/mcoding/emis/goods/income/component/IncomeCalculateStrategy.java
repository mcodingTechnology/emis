package com.mcoding.emis.goods.income.component;

import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.member.bean.member.Member;

/**
 * 拥挤计算接口
 * @author hzy
 *
 */
public interface IncomeCalculateStrategy {
	
	/**
	 * 为用户计算每一个产品的佣金
	 * @param product
	 * @param member
	 * @return
	 */
	public int calculateProductIncomeForMember(OrderProducts product, Member member, boolean isEqualToOrderBuyer);

	/**
	 * 为用户计算每一个产品的返利积分
	 * @param product
	 * @param member
	 * @return
	 */
	public int calculateProductPointForMember(OrderProducts product, Member member, boolean isEqualToOrderBuyer);
	
	/**
	 * 为用户计算每一个订单的佣金
	 * @param order
	 * @param member
	 * @return
	 */
	public int calculateOrderIncomeForMember(Order order, Member member,Integer parentMemberIdTmp);
	
	/**
	 * 微商自己购买时，为用户计算每一个订单的佣金
	 * @param order
	 * @param member
	 * @return
	 */
	public int calculateOrderIncomeForMemberJoin(Order order, Member member,Integer parentMemberIdTmp,Integer orderMemberid);

	/**
	 * 为用户计算每一个订单的返利积分
	 * @param order
	 * @param member
	 * @return
	 */
	public int calculateOrderPointForMember(Order order, Member member,Integer parentMemberIdTmp);

	/**
	 * 微商自己购买时，为用户计算每一个订单的返利积分
	 * @param order
	 * @param member
	 * @return
	 */
	public int calculateOrderPointForMemberJoin(Order order, Member member,Integer parentMemberIdTmp,Integer orderMemberid);
}
