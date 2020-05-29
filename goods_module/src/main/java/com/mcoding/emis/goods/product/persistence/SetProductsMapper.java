package com.mcoding.emis.goods.product.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.product.bean.SetProducts;
import com.mcoding.emis.goods.product.bean.SetProductsExample;

public interface SetProductsMapper {
    int countByExample(SetProductsExample example);

    int deleteByExample(SetProductsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SetProducts record);

    int insertSelective(SetProducts record);

    List<SetProducts> selectByExample(SetProductsExample example);

    SetProducts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SetProducts record, @Param("example") SetProductsExample example);

    int updateByExample(@Param("record") SetProducts record, @Param("example") SetProductsExample example);

    int updateByPrimaryKeySelective(SetProducts record);

    int updateByPrimaryKey(SetProducts record);

	int insertProductSets(List<SetProducts> setList);
}