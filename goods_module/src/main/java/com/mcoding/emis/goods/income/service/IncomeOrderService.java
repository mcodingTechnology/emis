package com.mcoding.emis.goods.income.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.income.bean.IncomeOrder;
import com.mcoding.emis.goods.income.bean.IncomeOrderExample;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturn;

public interface IncomeOrderService extends BaseService<IncomeOrder, String>{

	/**
	 * 根据订单，添加收入明细
	 * @param order
	 */
	void addIncreaseIncomeOrderByOrder(Order order);

	/**
	 * 根据订单，添加返利积分明细
	 * @param order
	 */
	void addPointOrderByOrder(Order order);
	
	/**
	 * 根据退换货订单，添加退费明细
	 * @param orderReturn
	 */
	void addDecreaseIncomeOrderByOrder(OrderReturn orderReturn);
	
	/**
	 * 根据查询条件，查询收入列表
	 * @param ex
	 * @return
	 */
	List<IncomeOrder> queryIncomeOrderByExample(IncomeOrderExample ex);
	
	/**
	 * 根据订单计算预计佣金
	 * @param order
	 */
	void countIncomeOrderByOrder(Order order) throws Exception;
	
	/**
	 * 根据月佣金表结算月份更改佣金表佣金结算标识
	 * 
	 */
	int updateByPrimaryext3(int months);
	
	
	/**
	 * 佣金报表导出
	 * 
	 */
	List<Map<String, Object>> querycommissionForExportExcel(Map<String,Object> param);
	
	/**
	 * 根据月份查询当月佣金明细
	 * 
	 */
	PageView<IncomeOrder> queryObjByPage(IncomeOrderExample incomeOrderExample);
	    
	/**
	 * 查询上月佣金
	 */
	int selectSumCommission();


	/**
	 * 查询销售总额，收入总额，最高收入
	 * **/
	Map<String, Object> getSumSaleAndIncome(String openid);

	Map<String, Object> selectSumByExample(String startDate,String endDate,Integer memberId);

	
}
