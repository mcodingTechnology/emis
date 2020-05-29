package com.mcoding.emis.goods.income.component.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.emis.goods.income.component.IncomeCalculateStrategy;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberLevelService;

@Component("incomeCalculateProfitStrategy")
public class IncomeCalculateProfitStrategy implements IncomeCalculateStrategy{
	
	@Autowired
	protected MemberLevelService memberLevelService;
	@Autowired
	protected IncomeProductService incomeProductService;
	@Autowired
	protected OrderProductsMapper orderProductsMapper;
	
	@Override
	public int calculateProductIncomeForMember(OrderProducts product, Member member, boolean isEqualToOrderBuyer) {
		if(product.getProductid() == null){
			product = this.orderProductsMapper.selectByPrimaryKey(product.getId());
		}
		if(member.getChannelsId()==null){
			member.setChannelsId(1);
		}
		
		int income = this.incomeProductService.getIncomeByProductAndLevel(product.getProductid(), member.getLevelId(), member.getChannelsId());
		MemberLevel childLevel = this.memberLevelService.queryChildLevel(member.getLevelId()).getData();
		while (isEqualToOrderBuyer) {
			if (childLevel == null || childLevel.getId() == null || childLevel.getId() <= 0) {
				break;
			}
			income = income + this.incomeProductService.getIncomeByProductAndLevel(product.getProductid(), childLevel.getId(), member.getChannelsId());
			childLevel = this.memberLevelService.queryChildLevel(childLevel.getId()).getData();
		}
		
		return income;
	}

	@Override
	public int calculateProductPointForMember(OrderProducts product, Member member, boolean isEqualToOrderBuyer) {
		if(product.getProductid() == null){
			product = this.orderProductsMapper.selectByPrimaryKey(product.getId());
		}
		if(member.getChannelsId()==null){
			member.setChannelsId(1);
		}

		int point = this.incomeProductService.getPointByProductAndLevel(product.getProductid(), member.getLevelId(), member.getChannelsId());
		
		MemberLevel childLevel = this.memberLevelService.queryChildLevel(member.getLevelId()).getData();
		//如果本人就是订单购买者，就要加上包括其下下级的佣金
		while (isEqualToOrderBuyer) {
			if (childLevel == null || childLevel.getId() == null || childLevel.getId() <= 0) {
				break;
			}
			point = point + this.incomeProductService.getPointByProductAndLevel(product.getProductid(), childLevel.getId(), member.getChannelsId());
			childLevel = this.memberLevelService.queryChildLevel(childLevel.getId()).getData();
		}
		return point;
	}

	@Override
	public int calculateOrderIncomeForMember(Order order, Member member,Integer parentMemberIdTmp) {
		int income=0;
		income = this.incomeProductService.getIncomeByOrderAndLevel(order.getId(), member.getMemberId(), member.getLevelId(),
				member.getChannelsId(),parentMemberIdTmp);
		return income;
	}

	@Override
	public int calculateOrderIncomeForMemberJoin(Order order, Member member,
			Integer parentMemberIdTmp,Integer orderMemberid) {
		int income=0;
		income = this.incomeProductService.getIncomeByOrderAndLevelMemberJoin(order.getId(), member.getMemberId(), member.getLevelId(),
				member.getChannelsId(),parentMemberIdTmp,orderMemberid);
		return income;
	}

	@Override
	public int calculateOrderPointForMember(Order order, Member member, Integer parentMemberIdTmp) {
		int point = 0;
		point = this.incomeProductService.getPointByOrderAndLevel(order.getId(), member.getMemberId(), member.getLevelId(),
				member.getChannelsId(),parentMemberIdTmp);
		return point;
	}

	@Override
	public int calculateOrderPointForMemberJoin(Order order, Member member, Integer parentMemberIdTmp, Integer orderMemberid) {
		int point = 0;
		point = this.incomeProductService.getPointByOrderAndLevelMemberJoin(order.getId(), member.getMemberId(), member.getLevelId(),
				member.getChannelsId(),parentMemberIdTmp,orderMemberid);
		return point;
	}

}
