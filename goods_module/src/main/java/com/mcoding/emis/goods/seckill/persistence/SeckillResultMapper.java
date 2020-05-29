package com.mcoding.emis.goods.seckill.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.seckill.bean.Seckill;
import com.mcoding.emis.goods.seckill.bean.SeckillResult;
import com.mcoding.emis.goods.seckill.bean.SeckillResultExample;

public interface SeckillResultMapper {
    int countByExample(SeckillResultExample example);

    int deleteByExample(SeckillResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeckillResult record);

    int insertSelective(SeckillResult record);

    List<SeckillResult> selectByExample(SeckillResultExample example);

    SeckillResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeckillResult record, @Param("example") SeckillResultExample example);

    int updateByExample(@Param("record") SeckillResult record, @Param("example") SeckillResultExample example);

    int updateByPrimaryKeySelective(SeckillResult record);

    int updateByPrimaryKey(SeckillResult record);

	SeckillResult getByIdAndOpenid(Map<String, Object> param);

	List<SeckillResult> selectBySeckillIdByPage(Map<String, Object> param);

	List<SeckillResult> selectBySeckillId(Map<String, Object> param);

	int setEndById(Integer seckillId);

	int getOrderNum(Integer seckillId);
}