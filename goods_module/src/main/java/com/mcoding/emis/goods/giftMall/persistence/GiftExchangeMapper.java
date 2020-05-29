package com.mcoding.emis.goods.giftMall.persistence;

import com.mcoding.emis.goods.giftMall.bean.GiftExchange;
import com.mcoding.emis.goods.giftMall.bean.GiftExchangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftExchangeMapper {
    int countByExample(GiftExchangeExample example);

    int deleteByExample(GiftExchangeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GiftExchange record);

    int insertSelective(GiftExchange record);

    List<GiftExchange> selectByExample(GiftExchangeExample example);

    GiftExchange selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GiftExchange record, @Param("example") GiftExchangeExample example);

    int updateByExample(@Param("record") GiftExchange record, @Param("example") GiftExchangeExample example);

    int updateByPrimaryKeySelective(GiftExchange record);

    int updateByPrimaryKey(GiftExchange record);
}