package com.mcoding.emis.goods.sales.service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.sales.bean.MemberSalesLog;

public interface MemberSalesLogService {

	public PageView<MemberSalesLog> querySalesLogData(MemberSalesLog memberSales, String iDisplayStart, String iDisplayLength,
			String pageNo, String pageSize);

}
