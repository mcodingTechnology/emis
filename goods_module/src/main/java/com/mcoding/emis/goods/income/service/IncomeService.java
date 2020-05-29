package com.mcoding.emis.goods.income.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

public interface IncomeService extends BaseService<Income, IncomeExample> {

	/**
	 * 增加佣金或积分
	 * @param memberId
	 * @param comminssion
	 * @param point
	 * @return
	 */
	public CommonResult<String> addCommissionToIncome(Integer memberId, Integer comminssion, Integer point);
	
	/**
	 * 添加代发送的佣金
	 * @param memberId
	 * @param income
	 */
	public void addIncomeUnsend(int memberId, int income);
	
	/**
	 * 为分销商添加一条收入统计的记录
	 * @param member
	 */
	public Income addIncomeForMember(Member member);
	

}
