package com.mcoding.emis.goods.collectionProduct.service;

import java.util.ArrayList;

import com.mcoding.emis.goods.collectionProduct.bean.CollectionProduct;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.member.common.CommonResult;

public interface CollectionProductService extends BaseService<CollectionProduct, String> {

	public int countCollectionByOpenIdAndProductId(String openId, int productId);

	public CommonResult<String> deleteObjByOpenIdAndProductId(String openId, int productId);

	public CommonResult<ArrayList<CollectionProduct>> queryCollectionProductListByOpenId(String openId);

}
