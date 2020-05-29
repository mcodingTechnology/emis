package com.mcoding.emis.goods.card.persistence;

import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CardTypeMapper {
    int countByExample(CardTypeExample example);

    int deleteByExample(CardTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardType record);

    int insertSelective(CardType record);

    List<CardType> selectByExample(CardTypeExample example);
    
    List<CardType> selectByExampleByPage(CardTypeExample example);

    CardType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardType record, @Param("example") CardTypeExample example);

    int updateByExample(@Param("record") CardType record, @Param("example") CardTypeExample example);

    int updateByPrimaryKeySelective(CardType record);

    int updateByPrimaryKey(CardType record);
    
    int addQuantityForCardById(Integer id);

	CardType selectByCardCode(String cardCode);
}