package com.mcoding.emis.goods.sales.service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.sales.bean.MemberSales;
import com.mcoding.emis.member.common.CommonResult;

public interface MemberSalesService {

	public PageView<MemberSales> querySalesData(MemberSales memberSales, String iDisplayStart, String iDisplayLength, String pageNo, String pageSize);

	public CommonResult<String> saveMemberSalesConfig(MemberSales memberSales);

	public MemberSales queryById(Integer id);

	public CommonResult<String> deleteMemberSalesById(String saleId);

	public CommonResult<String> disableOrEnabledMemberSalesById(String saleId, String saleStatus);

	public CommonResult<String> sendSalesMsg(String saleId, String memberTageIds);

}
