package com.mcoding.emis.goods.product.service;


import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.product.bean.ChannelProduct;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.member.common.CommonResult;



public interface ChannelProductService{

	public PageView<ChannelProduct> queryChannelProductData(ChannelProduct channelProduct, String iDisplayStart,
			String iDisplayLength, String pageNo, String pageSize);

	public CommonResult<String> addChannelProduct(ChannelProduct channelProduct);

	public ChannelProduct queryById(Integer id);

	public CommonResult<String> deleteChannelProduct(String id);

	public PageView<ChannelDealer> queryAllChannelDealer(ChannelDealer req,String iDisplayStart,String iDisplayLength);
	
	
	
}
