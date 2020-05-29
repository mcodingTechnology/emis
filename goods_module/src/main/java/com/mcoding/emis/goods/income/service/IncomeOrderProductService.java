package com.mcoding.emis.goods.income.service;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.goods.income.bean.IncomeOrderProduct;
import com.mcoding.emis.goods.income.bean.IncomeOrderProductExample;

public interface IncomeOrderProductService extends BaseService<IncomeOrderProduct, IncomeOrderProductExample> {
	
	/***
	 * 根据佣金记录查询明细
	 * @param incomeOrderProduct
	 * @return
	 */
	public List<IncomeOrderProduct> queryByIncomeOrderId(int incomeOrderProduct);
	
}