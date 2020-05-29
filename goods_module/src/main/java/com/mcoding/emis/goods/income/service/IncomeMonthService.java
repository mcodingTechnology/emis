package com.mcoding.emis.goods.income.service;

import java.util.Date;
import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeMonthExample;
import com.mcoding.emis.member.bean.member.Member;

public interface IncomeMonthService extends BaseService<IncomeMonth, IncomeMonthExample> {
	
	/**
	 * 统计某个时间段内的 佣金总况，包括佣金，积分，销售额
	 * @param member
	 * @param start
	 * @param end
	 * @return
	 */
	public IncomeMonth countMonthIncome(Member member, Date start, Date end);
	
	/**
	 * 批量审核佣金
	 * @param ids
	 * @param isCheck true为审核，false为取消审核
	 */
	public void checkIncomeMonth(List<Integer> ids, boolean isCheck);

	/**
	 * 删除该月份的记录，并重新插入
	 * @param incomeMonthList
	 * @param monthStr
	 */
	public void updateIncome(List<IncomeMonth> incomeMonthList, String monthStr);
}