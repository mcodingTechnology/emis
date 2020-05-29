package com.mcoding.emis.goods.productCategory.service;

import java.util.List;

import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.productCategory.bean.ProductCategory;
import com.mcoding.emis.member.common.CommonResult;

/**
 * Created by libing on 2014-06-02  09:02.
 */
public interface ProductCategoryService extends BaseService<ProductCategory, String> {
	public CommonResult<String> addObj(ProductCategory t);
	
	public List<ProductCategory> selectByProductId(Integer productId);
	
    List<ProductCategory> getProductCategoryByType(Integer categoryId);
}
