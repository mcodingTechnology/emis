package com.mcoding.emis.goods.income.component.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.emis.goods.income.component.IncomeCalculateStrategy;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.service.member.MemberLevelService;

@Component("incomeCalculateCommonStrategy")
public class IncomeCalculateCommonStrategy implements IncomeCalculateStrategy {

	@Autowired
	protected MemberLevelService memberLevelService;
	
	@Autowired
	protected OrderProductsMapper orderProductsMapper;

	@Override
	public int calculateProductIncomeForMember(OrderProducts product, Member member, boolean isEqualToOrderBuyer) {
		Integer levelId = member.getLevelId();
		if(member.getChannelsId()==null){
			member.setChannelsId(1);
		}

		if (levelId == null) {
			throw new NullPointerException("产品佣金计算失败，因为用户levelId为空");
		}
		if(product.getId() == null){
			throw new NullPointerException("产品id为空，无法查询出产品价格");
		}
		if(product.getPrice() == null){
			//如果产品中没有价格，则需要查库
			product = this.orderProductsMapper.selectByPrimaryKey(product.getId());
		}
		MemberLevel parentLevel = memberLevelService.queryObjById(levelId);

		// 2、添加佣金明细
		Double discount = parentLevel.getDiscount();
		Integer income = (int) (product.getPrice() * discount * 10 / 100);
		return income;
	}

	@Override
	public int calculateProductPointForMember(OrderProducts product, Member member, boolean isEqualToOrderBuyer) {
		return 0;
	}

	@Override
	public int calculateOrderIncomeForMember(Order order, Member member,Integer parentMemberIdTmp) {
		Integer levelId = member.getLevelId();

		if (levelId == null) {
			throw new NullPointerException("产品佣金计算失败，因为用户levelId为空");
		}
		MemberLevel parentLevel = memberLevelService.queryObjById(levelId);

		// 2、添加佣金明细
		Double discount = parentLevel.getDiscount();
		Integer income = (int) (order.getFee() * discount * 10 / 100);
		return income;
	}

	@Override
	public int calculateOrderIncomeForMemberJoin(Order order, Member member,
			Integer parentMemberIdTmp, Integer orderMemberid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateOrderPointForMember(Order order, Member member, Integer parentMemberIdTmp) {
		return 0;
	}

	@Override
	public int calculateOrderPointForMemberJoin(Order order, Member member, Integer parentMemberIdTmp, Integer orderMemberid) {
		return 0;
	}


}
