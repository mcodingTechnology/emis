package com.mcoding.emis.goods.healthCriteria.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaMember;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaMemberExample;
import com.mcoding.emis.goods.healthCriteria.bean.HealthRecommendProduct;

public interface HealthCriteriaMemberMapper {
	int countByExample(HealthCriteriaMemberExample example);

	int deleteByExample(HealthCriteriaMemberExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(HealthCriteriaMember record);

	int insertSelective(HealthCriteriaMember record);

	List<HealthCriteriaMember> selectByExampleWithBLOBs(HealthCriteriaMemberExample example);

	List<HealthCriteriaMember> selectByExample(HealthCriteriaMemberExample example);

	HealthCriteriaMember selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") HealthCriteriaMember record,
			@Param("example") HealthCriteriaMemberExample example);

	int updateByExampleWithBLOBs(@Param("record") HealthCriteriaMember record,
			@Param("example") HealthCriteriaMemberExample example);

	int updateByExample(@Param("record") HealthCriteriaMember record,
			@Param("example") HealthCriteriaMemberExample example);

	int updateByPrimaryKeySelective(HealthCriteriaMember record);

	int updateByPrimaryKeyWithBLOBs(HealthCriteriaMember record);

	int updateByPrimaryKey(HealthCriteriaMember record);

	/* 查询检测结果 */
	List<HealthCriteriaMember> queryHealthCriteriaMemberByPage(Map<String, Object> param);

	// 根据手机号码分页查询检测报告
	List<HealthCriteriaMember> queryResultByPhoneByPage(Map<String, Object> param);

	// 根据多个Id查询推荐产品
	List<HealthRecommendProduct> queryRecommendProductByIds(Map<String, Object> param);

	// 查询发样检测最新一条记录
	HealthCriteriaMember queryHealthCriteriaMemberLastRecord(Map<String, Object> param);
}