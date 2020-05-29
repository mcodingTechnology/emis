package com.mcoding.emis.goods.sales.persistence;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.goods.sales.bean.MemberSalesLog;
import com.mcoding.emis.goods.sales.bean.MemberSalesLogExample;
import com.mcoding.emis.goods.sales.bean.MemberSalesSendBatch;

public interface MemberSalesSendBatchMapper extends IMapper<MemberSalesLog, MemberSalesLogExample> {

	public int inserSaleSendBatch(MemberSalesSendBatch batch);


	
}