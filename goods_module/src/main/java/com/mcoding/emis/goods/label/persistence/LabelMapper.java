package com.mcoding.emis.goods.label.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.label.bean.Label;
import com.mcoding.emis.goods.label.bean.LabelExample;

public interface LabelMapper {

	int countByExample(LabelExample example);

	int deleteByExample(LabelExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Label record);

	int insertSelective(Label record);

	List<Label> selectByExample(LabelExample example);

	Label selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Label record, @Param("example") LabelExample example);

	int updateByExample(@Param("record") Label record, @Param("example") LabelExample example);

	int updateByPrimaryKeySelective(Label record);

	int updateByPrimaryKey(Label record);

	int addOneHit(Map<String, Object> param);

	List<String> selectMarksByCorrelationId(int corrId);

	List<Label> selectHitWithTypeByPage(Map<String, Object> param);

	List<Label> selectLabelsWithMarkByPage(Map<String, Object> param);

	List<String> selectLabelsByMemberId(Map<String, Object> param);

}