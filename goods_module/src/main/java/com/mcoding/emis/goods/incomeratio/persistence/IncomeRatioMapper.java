package com.mcoding.emis.goods.incomeratio.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.incomeratio.bean.IncomeRatio;
import com.mcoding.emis.goods.incomeratio.bean.IncomeRatioExample;

public interface IncomeRatioMapper {
    int countByExample(IncomeRatioExample example);

    int deleteByExample(IncomeRatioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRatio record);

    int insertSelective(IncomeRatio record);

    List<IncomeRatio> selectByExample(IncomeRatioExample example);

    IncomeRatio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeRatio record, @Param("example") IncomeRatioExample example);

    int updateByExample(@Param("record") IncomeRatio record, @Param("example") IncomeRatioExample example);

    int updateByPrimaryKeySelective(IncomeRatio record);

    int updateByPrimaryKey(IncomeRatio record);

    List<IncomeRatio> selectByExampleByPage(IncomeRatioExample example);
}