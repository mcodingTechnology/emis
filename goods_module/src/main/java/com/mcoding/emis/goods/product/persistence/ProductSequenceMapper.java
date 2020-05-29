package com.mcoding.emis.goods.product.persistence;

import java.util.List;

import com.mcoding.emis.goods.product.bean.ProductSequence;

public interface ProductSequenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSequence record);

    int insertSelective(ProductSequence record);

    ProductSequence selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSequence record);

    int updateByPrimaryKey(ProductSequence record);
    
    List<ProductSequence> getProSeqListByProductId(Integer productId);
    
    int deleteProSeqByProductId(Integer productId);
}