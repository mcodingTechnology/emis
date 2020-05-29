package com.mcoding.emis.goods.card.persistence;

import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {
    int countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);
    
    int insertList(List<Card> cardList);

    List<Card> selectByExample(CardExample example);

    Card selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

	List<Card> selectByExampleByPage(CardExample example);
	
	//批量更新特权卡
	int batchUpdate(List<String> list);
	
	//批量校验特权卡是否类型相同或被兑换过
	List<Card> verifyCard(List<String> list);

    /**
     * 获取卡的剩余数量
     * @param id
     * @return
     */
    int getCardSurplusById(Integer id);
}