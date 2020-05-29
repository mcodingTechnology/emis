package com.mcoding.emis.goods.price.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.persistence.ChannelDealerMapper;
import com.mcoding.emis.goods.price.bean.PriceSet;
import com.mcoding.emis.goods.price.bean.PriceSetExample;
import com.mcoding.emis.goods.price.persistence.PriceSetMapper;
import com.mcoding.emis.goods.price.service.PriceSetService;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class PriceSetServiceImpl implements PriceSetService {
	@Autowired
    protected PriceSetMapper priceSetMapper;
	
	@Autowired
    protected ProductMapper productMapper;
	
	@Autowired
    protected ChannelDealerMapper channelDealerMapper;

	@Override
	public CommonResult<String> addObj(PriceSet t) {
		CommonResult<String> result = new CommonResult<String>();

		if (t.getProductId() == null  || t.getDealerId() == null) {
			result.setCode(1);
			result.setMsg("代理id或产品id不能为空");
			return result;
		}
		
		List<Integer> productIds = new ArrayList<Integer>();
		productIds.add(t.getProductId());
		List<PriceSet> priceSetList = priceSetMapper.selectPriceSet(t.getDealerId(), productIds);
		if (CollectionUtils.isNotEmpty(priceSetList)){
			result.setCode(1);
			result.setMsg("该代理的这个产品已经存在价格");
			return result;
		}
		
		String productName = productMapper.queryById(t.getProductId()).getProductName();
		t.setProductName(productName);
		String dealerName = channelDealerMapper.selectChannelById(t.getDealerId()).getDealerName();
		t.setDealerName(dealerName);
		
		t.setStatus("1");
		this.priceSetMapper.insertSelective(t);
		
		result.setCode(0);
		result.setData(String.valueOf(t.getId()));
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		
		String status = this.priceSetMapper.selectByPrimaryKey(Integer.valueOf(id)).getStatus();
		
		PriceSet t = new PriceSet();
		t.setId(Integer.valueOf(id));
		if ("0".equals(status)) {
			t.setStatus("1");
		} else {
			t.setStatus("0");
		}
		t.setUpdateTime(new Date());
		this.priceSetMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(PriceSet t) {
		
		String productName = productMapper.queryById(t.getProductId()).getProductName();
		t.setProductName(productName);
		String dealerName = channelDealerMapper.selectChannelById(t.getDealerId()).getDealerName();
		t.setDealerName(dealerName);
		
		t.setUpdateTime(new Date());
		this.priceSetMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<PriceSet> queryObjById(String id) {
		PriceSet priceSet = this.priceSetMapper.selectByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<PriceSet> result = new CommonResult<PriceSet>();
		result.setCode(0);
		result.setData(priceSet);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<PriceSet>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<PriceSet>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<PriceSet> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		PageView<PriceSet> pageView = new PageView<PriceSet>(iDisplayStart, iDisplayLength);
		
		PriceSetExample ex = new PriceSetExample();
//		PriceSetExample.Criteria cri = ex.createCriteria();
//		cri.andStatusEqualTo("1");
		ex.setPageView(pageView);
		ex.setOrderByClause("create_time DESC");
		
		List<PriceSet> list = this.priceSetMapper.selectByExampleByPage(ex);
		pageView.setQueryResult(list);
		
		return pageView;
	}

    
}