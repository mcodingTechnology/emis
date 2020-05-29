package com.mcoding.emis.goods.product.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.product.bean.ProductSequence;
import com.mcoding.emis.member.common.CommonResult;

@Service
public interface ProductSequenceService extends BaseService<ProductSequence, String>{
	
	public CommonResult<HashMap<String,Object>> getProSeqListByProductId(Integer productId);
}
