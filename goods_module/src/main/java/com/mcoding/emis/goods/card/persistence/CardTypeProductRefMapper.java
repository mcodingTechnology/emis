package com.mcoding.emis.goods.card.persistence;

import com.mcoding.emis.goods.card.bean.CardTypeProductRef;
import com.mcoding.emis.goods.card.bean.CardTypeProductRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardTypeProductRefMapper {
    int countByExample(CardTypeProductRefExample example);

    int deleteByExample(CardTypeProductRefExample example);

    int deleteByPrimaryKey(Integer refid);

    int insert(CardTypeProductRef record);

    int insertSelective(CardTypeProductRef record);

    List<CardTypeProductRef> selectByExample(CardTypeProductRefExample example);

    CardTypeProductRef selectByPrimaryKey(Integer refid);

    int updateByExampleSelective(@Param("record") CardTypeProductRef record, @Param("example") CardTypeProductRefExample example);

    int updateByExample(@Param("record") CardTypeProductRef record, @Param("example") CardTypeProductRefExample example);

    int updateByPrimaryKeySelective(CardTypeProductRef record);

    int updateByPrimaryKey(CardTypeProductRef record);
}