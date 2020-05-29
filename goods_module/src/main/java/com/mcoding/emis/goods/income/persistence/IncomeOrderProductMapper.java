package com.mcoding.emis.goods.income.persistence;

import com.mcoding.emis.goods.income.bean.IncomeOrderProduct;
import com.mcoding.emis.goods.income.bean.IncomeOrderProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IncomeOrderProductMapper {
    int countByExample(IncomeOrderProductExample example);

    int deleteByExample(IncomeOrderProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeOrderProduct record);

    int insertSelective(IncomeOrderProduct record);

    List<IncomeOrderProduct> selectByExample(IncomeOrderProductExample example);

    List<IncomeOrderProduct> selectByExampleByPage(IncomeOrderProductExample example);
    
    IncomeOrderProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeOrderProduct record, @Param("example") IncomeOrderProductExample example);

    int updateByExample(@Param("record") IncomeOrderProduct record, @Param("example") IncomeOrderProductExample example);

    int updateByPrimaryKeySelective(IncomeOrderProduct record);

    int updateByPrimaryKey(IncomeOrderProduct record);
}