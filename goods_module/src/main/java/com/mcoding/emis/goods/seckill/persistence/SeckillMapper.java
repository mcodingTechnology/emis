package com.mcoding.emis.goods.seckill.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.seckill.bean.Seckill;
import com.mcoding.emis.goods.seckill.bean.SeckillExample;

public interface SeckillMapper {
    int countByExample(SeckillExample example);

    int deleteByExample(SeckillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    List<Seckill> selectByExample(SeckillExample example);

    Seckill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Seckill record, @Param("example") SeckillExample example);

    int updateByExample(@Param("record") Seckill record, @Param("example") SeckillExample example);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
//自定义
    List<Seckill> selectByToday(Map<String, Object> param);
    
    List<Seckill> selectEndByToday(Map<String, Object> param);
    
    List<Seckill> querySeckillByPage(Map<String, Object> param);
}