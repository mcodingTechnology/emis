package com.mcoding.emis.goods.region.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.region.bean.RegionGroble;
import com.mcoding.emis.goods.region.bean.RegionGrobleExample;

public interface RegionGrobleMapper {
    int countByExample(RegionGrobleExample example);

    int deleteByExample(RegionGrobleExample example);

    int deleteByPrimaryKey(Integer regionId);

    int insert(RegionGroble record);

    int insertSelective(RegionGroble record);

    List<RegionGroble> selectByExample(RegionGrobleExample example);

    RegionGroble selectByPrimaryKey(Integer regionId);

    int updateByExampleSelective(@Param("record") RegionGroble record, @Param("example") RegionGrobleExample example);

    int updateByExample(@Param("record") RegionGroble record, @Param("example") RegionGrobleExample example);

    int updateByPrimaryKeySelective(RegionGroble record);

    int updateByPrimaryKey(RegionGroble record);
}