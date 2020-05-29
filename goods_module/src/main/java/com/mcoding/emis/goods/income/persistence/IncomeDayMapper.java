package com.mcoding.emis.goods.income.persistence;

import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeDay;
import com.mcoding.emis.goods.income.bean.IncomeDayExample;
import com.mcoding.emis.goods.income.bean.IncomeExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IncomeDayMapper {
    int countByExample(IncomeDayExample example);

    int deleteByExample(IncomeDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeDay record);

    int insertSelective(IncomeDay record);

    List<IncomeDay> selectByExample(IncomeDayExample example);
    
    List<IncomeDay> selectByExampleByPage(IncomeDayExample example);

    IncomeDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeDay record, @Param("example") IncomeDayExample example);

    int updateByExample(@Param("record") IncomeDay record, @Param("example") IncomeDayExample example);

    int updateByPrimaryKeySelective(IncomeDay record);

    int updateByPrimaryKey(IncomeDay record);
    
    int addCommissionToIncome(Map<String, Object> params);

    /***
     * 统计每个人的月收入列表
     * **/
    public List<IncomeDay> queryMonthOrderFeeList();
}