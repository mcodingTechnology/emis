package com.mcoding.emis.goods.product.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.persistence.IncomeProductMapper;
import com.mcoding.emis.goods.product.bean.ChannelProduct;
import com.mcoding.emis.goods.product.bean.ChannelProductExample;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.bean.ChannelProductExample.Criteria;
import com.mcoding.emis.goods.product.persistence.ChannelProductMapper;
import com.mcoding.emis.goods.product.service.ChannelProductService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class ChannelProductServiceImpl implements ChannelProductService{

	@Resource
	private ChannelProductMapper channelProductMapper;
	@Resource
	private IncomeProductMapper incomeProductMapper;
	@Override
	public PageView<ChannelProduct> queryChannelProductData(ChannelProduct channelProduct, String iDisplayStart,
			String iDisplayLength, String pageNo, String pageSize) {
		ChannelProductExample channelProductExample = new ChannelProductExample();
		Criteria cri = channelProductExample.createCriteria();
		// 查询条件拼接
		
		// 分页
		PageView<ChannelProduct> tmpPageView;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
			tmpPageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			tmpPageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else {
			tmpPageView = new PageView<>(1, 10);
		}
		
		channelProductExample.setPageView(tmpPageView);
		channelProductExample.setOrderByClause(" id desc");
        List<ChannelProduct> list = this.channelProductMapper.queryChannelProductData(channelProductExample);
        tmpPageView.setQueryResult(list);
		return tmpPageView;
	}
	@Override
	public CommonResult<String> addChannelProduct(ChannelProduct channelProduct) {
		CommonResult<String> commonResult = new CommonResult<String>();
		try {
			Date date = new Date();
			if(channelProduct.getId() != null){
				// 修改
				channelProduct.setLastUpdateTime(date);
				this.channelProductMapper.updateChannelProductId(channelProduct);
			}else{
				// 新增
				channelProduct.setCreateTime(date);
				channelProduct.setLastUpdateTime(date);
				this.channelProductMapper.addChannelProduct(channelProduct);
			}
			commonResult.setCode(0);
			commonResult.setData("success");
			commonResult.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setCode(-1);
			commonResult.setData("error");
			commonResult.setMsg("error");
		}
		return commonResult;
	}
	@Override
	public ChannelProduct queryById(Integer id) {
		return this.channelProductMapper.queryById(id);
	}
	@Override
	public CommonResult<String> deleteChannelProduct(String id) {
		CommonResult<String> commonResult = new CommonResult<String>();
		try {
			if(StringUtils.isEmpty(id)){
				commonResult.setCode(1);
				commonResult.setData("id为空");
				commonResult.setMsg("id为空");
				return commonResult;
			}
			this.channelProductMapper.deleteChannelProduct(id);
			commonResult.setCode(0);
			commonResult.setData("success");
			commonResult.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setCode(-1);
			commonResult.setData("error");
			commonResult.setMsg("error");
		}
		return commonResult;
	}
	@Override
	public PageView<ChannelDealer> queryAllChannelDealer(ChannelDealer req,String iDisplayStart,String iDisplayLength) {
		PageView<ChannelDealer> pageView = new PageView<ChannelDealer>(iDisplayStart, iDisplayLength);
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pageView", pageView);
			List<ChannelDealer>  com = incomeProductMapper.selectUserTagsListById();
		    pageView.setQueryResult(com);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}


}