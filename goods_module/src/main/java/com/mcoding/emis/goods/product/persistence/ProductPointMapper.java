package com.mcoding.emis.goods.product.persistence;

import com.mcoding.emis.goods.product.bean.ProductPoint;

public interface ProductPointMapper {
    int deleteByPrimaryKey(Integer pointid);

    int insert(ProductPoint record);

    int insertSelective(ProductPoint record);

    ProductPoint selectByPrimaryKey(Integer pointid);

    int updateByPrimaryKeySelective(ProductPoint record);

    int updateByPrimaryKey(ProductPoint record);
}