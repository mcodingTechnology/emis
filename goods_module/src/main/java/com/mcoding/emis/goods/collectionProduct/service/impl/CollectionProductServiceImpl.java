package com.mcoding.emis.goods.collectionProduct.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.collectionProduct.bean.CollectionProduct;
import com.mcoding.emis.goods.collectionProduct.bean.CollectionProductExample;
import com.mcoding.emis.goods.collectionProduct.persistence.CollectionProductMapper;
import com.mcoding.emis.goods.collectionProduct.service.CollectionProductService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class CollectionProductServiceImpl implements CollectionProductService {
	
	@Autowired
	protected CollectionProductMapper collectionProductMapper;

	@Override
	public CommonResult<String> addObj(CollectionProduct t) {
		int code = collectionProductMapper.insertSelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(code);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(CollectionProduct t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<CollectionProduct> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<CollectionProduct>> queryListObj(
			String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<CollectionProduct>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<CollectionProduct> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countCollectionByOpenIdAndProductId(String openId, int productId) {
		CollectionProductExample ex = new CollectionProductExample();
		ex.createCriteria().andOpenidEqualTo(openId).andProductidEqualTo(productId);
		
		int size = collectionProductMapper.countByExample(ex);
		return size;
	}

	@Override
	public CommonResult<String> deleteObjByOpenIdAndProductId(String openId, int productId) {
		CollectionProductExample ex = new CollectionProductExample();
		ex.createCriteria().andOpenidEqualTo(openId).andProductidEqualTo(productId);
		
		collectionProductMapper.deleteByExample(ex);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		
		return result;
		
	}

	@Override
	public CommonResult<ArrayList<CollectionProduct>> queryCollectionProductListByOpenId(String openId) {
		CollectionProductExample ex = new CollectionProductExample();
		ex.createCriteria().andOpenidEqualTo(openId);
		
		List<CollectionProduct> tmp = this.collectionProductMapper.selectByExample(ex);
		ArrayList<CollectionProduct> list = new ArrayList<CollectionProduct>();
		for(int i=0; i<tmp.size(); i++){
			list.add(tmp.get(i));
		}
		
		CommonResult<ArrayList<CollectionProduct>> result = new CommonResult<ArrayList<CollectionProduct>>();
		result.setCode(0);
		result.setData(list);
		result.setMsg("ok");
		
		return result;
	}

}
