package com.mcoding.emis.goods.productCategory.persistence;

import java.util.List;

import com.mcoding.emis.goods.productCategory.bean.ProductCategory;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
    
    List<ProductCategory> selectByProductId(Integer productId);
    
    List<ProductCategory> getProductCategoryByType(Integer categoryId);
    
    int deleteByProductId(Integer productId);
    
}