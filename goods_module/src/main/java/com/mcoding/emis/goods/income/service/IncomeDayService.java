package com.mcoding.emis.goods.income.service;

import java.util.Date;
import java.util.List;

import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.income.bean.IncomeDay;

public interface IncomeDayService extends BaseService<IncomeDay, String> {
	
	/**
	 * 添加佣金到当天的佣金记录和日积分返利
	 * @param memberId
	 * @param commission
	 * @param point
	 * @param today
	 */
	public void addCommissionToIncomeForDay(Integer memberId, Integer commission,Integer point, Integer orderFee, Date today);

	public List<IncomeDay> queryLastDaysData(int lastDays, String openid);
	
	public List<IncomeDay> queryData(Date startDate, Date endDate, String openid);

	/***
	 * 统计每个人的月收入列表
	 * **/
	public List<IncomeDay> queryMonthOrderFeeList();

}
