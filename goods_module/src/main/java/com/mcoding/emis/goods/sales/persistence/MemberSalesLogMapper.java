package com.mcoding.emis.goods.sales.persistence;

import java.util.List;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.goods.sales.bean.MemberSalesLog;
import com.mcoding.emis.goods.sales.bean.MemberSalesLogExample;
import com.mcoding.emis.goods.sales.bean.MemberSalesSendBatch;
import com.mcoding.emis.goods.sales.bean.MemberSalesSendBatchExample;

public interface MemberSalesLogMapper extends IMapper<MemberSalesSendBatch, MemberSalesSendBatchExample> {

	public int inserSaleLog( List<MemberSalesLog> list);

	public List<MemberSalesLog> querySalesLogDataByPage(MemberSalesLogExample memberSalesExample);

}