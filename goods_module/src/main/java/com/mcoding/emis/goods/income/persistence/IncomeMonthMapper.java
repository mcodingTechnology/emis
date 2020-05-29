package com.mcoding.emis.goods.income.persistence;

import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IncomeMonthMapper {
    int countByExample(IncomeMonthExample example);

    int deleteByExample(IncomeMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeMonth record);

    int insertSelective(IncomeMonth record);

    List<IncomeMonth> selectByExample(IncomeMonthExample example);

    IncomeMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeMonth record, @Param("example") IncomeMonthExample example);

    int updateByExample(@Param("record") IncomeMonth record, @Param("example") IncomeMonthExample example);

    int updateByPrimaryKeySelective(IncomeMonth record);

    int updateByPrimaryKey(IncomeMonth record);

	List<IncomeMonth> selectByExampleByPage(IncomeMonthExample example);

	int insertList(@Param("incomeMonthList")List<IncomeMonth> incomeMonthList);
}