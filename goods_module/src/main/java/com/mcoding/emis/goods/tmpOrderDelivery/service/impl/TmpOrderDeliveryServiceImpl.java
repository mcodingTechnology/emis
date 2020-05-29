package com.mcoding.emis.goods.tmpOrderDelivery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.tmpOrderDelivery.bean.TmpOrderDelivery;
import com.mcoding.emis.goods.tmpOrderDelivery.persistence.TmpOrderDeliveryMapper;
import com.mcoding.emis.goods.tmpOrderDelivery.service.TmpOrderDeliveryService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class TmpOrderDeliveryServiceImpl implements TmpOrderDeliveryService {
	private static final Logger log = Logger
			.getLogger(TmpOrderDeliveryServiceImpl.class);
	@Autowired
	private TmpOrderDeliveryMapper tmpOrderDeliveryMapper;
	
	CommonResult<String> commonResultString = new CommonResult<String>();

	@Override
	public CommonResult<String> addObj(TmpOrderDelivery t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(TmpOrderDelivery t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<TmpOrderDelivery> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<TmpOrderDelivery>> queryListObj(
			String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<TmpOrderDelivery>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<TmpOrderDelivery> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<TmpOrderDelivery> queryTmpOrderDeliveryData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
			PageView<TmpOrderDelivery> pageView = new PageView<TmpOrderDelivery>(iDisplayStart, iDisplayLength);
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("pageView", pageView);
	        List<TmpOrderDelivery> tmpOrderDeliveryResults = tmpOrderDeliveryMapper.queryTmpOrderDeliveryByPage(param);
	        pageView.setQueryResult(tmpOrderDeliveryResults);
	        return pageView;
	}

	

}
