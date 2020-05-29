package com.mcoding.emis.goods.income.persistence;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.income.bean.IncomeProductExample;

public interface IncomeProductMapper {
    int countByExample(IncomeProductExample example);

    int deleteByExample(IncomeProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeProduct record);

    int insertSelective(IncomeProduct record);

    List<IncomeProduct> selectByExample(IncomeProductExample example);

    IncomeProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeProduct record, @Param("example") IncomeProductExample example);

    int updateByExample(@Param("record") IncomeProduct record, @Param("example") IncomeProductExample example);

    int updateByPrimaryKeySelective(IncomeProduct record);

    int updateByPrimaryKey(IncomeProduct record);
    
    Map<String, Object> sumIncomeProductProfitForOrder(Map<String,Integer> params);

    Map<String, Object> sumIncomeProductPointForOrder(Map<String,Integer> params);

    List<IncomeProduct> queryListByPage(Map<String, Object> param);
    
    List<ChannelDealer> selectUserTagsListById();
}