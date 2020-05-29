package com.mcoding.emis.goods.price.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.mcoding.emis.goods.price.bean.PriceSet;
import com.mcoding.emis.goods.price.bean.PriceSetExample;

public interface PriceSetMapper {
    int countByExample(PriceSetExample example);

    int deleteByExample(PriceSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PriceSet record);

    int insertSelective(PriceSet record);

    List<PriceSet> selectByExample(PriceSetExample example);

    PriceSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PriceSet record, @Param("example") PriceSetExample example);

    int updateByExample(@Param("record") PriceSet record, @Param("example") PriceSetExample example);

    int updateByPrimaryKeySelective(PriceSet record);

    int updateByPrimaryKey(PriceSet record);

    List<PriceSet> selectByExampleByPage(PriceSetExample example);
    
    List<PriceSet> selectPriceSet(@Param("dealerId") Integer dealerId, @Param("productIds") List<Integer> productIds);
}