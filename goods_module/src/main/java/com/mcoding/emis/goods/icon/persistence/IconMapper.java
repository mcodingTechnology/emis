package com.mcoding.emis.goods.icon.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.icon.bean.Icon;
import com.mcoding.emis.goods.icon.bean.IconExample;

public interface IconMapper {
    int countByExample(IconExample example);

    int deleteByExample(IconExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Icon record);

    int insertSelective(Icon record);

    List<Icon> selectByExample(IconExample example);

    Icon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Icon record, @Param("example") IconExample example);

    int updateByExample(@Param("record") Icon record, @Param("example") IconExample example);

    int updateByPrimaryKeySelective(Icon record);

    int updateByPrimaryKey(Icon record);

    List<Icon> selectByExampleByPage(IconExample example);
}