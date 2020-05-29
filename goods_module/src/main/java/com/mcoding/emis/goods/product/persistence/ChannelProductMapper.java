package com.mcoding.emis.goods.product.persistence;


import java.util.List;

import com.mcoding.emis.goods.common.persistence.BaseMapper;
import com.mcoding.emis.goods.product.bean.ChannelProduct;
import com.mcoding.emis.goods.product.bean.ChannelProductExample;
public interface ChannelProductMapper extends BaseMapper<ChannelProduct> {

	public List<ChannelProduct> queryChannelProductData(ChannelProductExample channelProductExample);

	public int addChannelProduct(ChannelProduct channelProduct);

	public ChannelProduct queryById(Integer id);

	public int updateChannelProductId(ChannelProduct channelProduct);

	public int deleteChannelProduct(String id);

}
