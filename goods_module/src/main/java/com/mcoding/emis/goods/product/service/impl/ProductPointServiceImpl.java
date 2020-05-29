package com.mcoding.emis.goods.product.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.ProductPoint;
import com.mcoding.emis.goods.product.service.ProductPointService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class ProductPointServiceImpl implements ProductPointService{
	private static final Logger log = Logger.getLogger(ProductPointServiceImpl.class);
    private static final int MAX_CALLCOUNT = 10;
    
    @Autowired
    private DefaultTransactionDefinition def;
    @Autowired
    private PlatformTransactionManager transactionManager;
	@Override
	public CommonResult<String> addObj(ProductPoint t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> modifyObj(ProductPoint t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<ProductPoint> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<ArrayList<ProductPoint>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<PageView<ProductPoint>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageView<ProductPoint> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}


}
