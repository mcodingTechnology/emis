package com.mcoding.emis.goods.healthCriteria.persistence;

import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HealthCriteriaMapper {
    int countByExample(HealthCriteriaExample example);

    int deleteByExample(HealthCriteriaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HealthCriteria record);

    int insertSelective(HealthCriteria record);

    List<HealthCriteria> selectByExampleWithBLOBs(HealthCriteriaExample example);

    List<HealthCriteria> selectByExample(HealthCriteriaExample example);

    HealthCriteria selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HealthCriteria record, @Param("example") HealthCriteriaExample example);

    int updateByExampleWithBLOBs(@Param("record") HealthCriteria record, @Param("example") HealthCriteriaExample example);

    int updateByExample(@Param("record") HealthCriteria record, @Param("example") HealthCriteriaExample example);

    int updateByPrimaryKeySelective(HealthCriteria record);

    int updateByPrimaryKeyWithBLOBs(HealthCriteria record);

    int updateByPrimaryKey(HealthCriteria record);

	List<HealthCriteria> queryHealthCriteriaByPage(Map<String, Object> param);

	List<HealthCriteria> queryResultByTestitem(Map<String, Object> param);

	List<HealthCriteria> queryResultByIds(Map<String, Object> param);

	List<HealthCriteria> selectGroupBy();
}