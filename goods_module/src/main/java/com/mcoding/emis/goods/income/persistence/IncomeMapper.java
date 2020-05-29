package com.mcoding.emis.goods.income.persistence;

import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IncomeMapper {
	int countByExample(IncomeExample example);

    int deleteByExample(IncomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    List<Income> selectByExample(IncomeExample example);

    Income selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByExample(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);
    
    List<Income> selectByExampleByPage(IncomeExample example);
    
    int addCommissionToIncome(Map<String, Integer> params);
}