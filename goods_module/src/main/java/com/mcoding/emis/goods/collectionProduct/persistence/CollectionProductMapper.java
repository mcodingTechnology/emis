package com.mcoding.emis.goods.collectionProduct.persistence;

import com.mcoding.emis.goods.collectionProduct.bean.CollectionProduct;
import com.mcoding.emis.goods.collectionProduct.bean.CollectionProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectionProductMapper {
    int countByExample(CollectionProductExample example);

    int deleteByExample(CollectionProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CollectionProduct record);

    int insertSelective(CollectionProduct record);

    List<CollectionProduct> selectByExample(CollectionProductExample example);
    
    List<CollectionProduct> selectByExampleByPage(CollectionProductExample example);

    CollectionProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CollectionProduct record, @Param("example") CollectionProductExample example);

    int updateByExample(@Param("record") CollectionProduct record, @Param("example") CollectionProductExample example);

    int updateByPrimaryKeySelective(CollectionProduct record);

    int updateByPrimaryKey(CollectionProduct record);
}