package com.mcoding.emis.goods.dealer.persistence;

import com.mcoding.emis.goods.dealer.bean.Dealer;
import com.mcoding.emis.goods.dealer.bean.DealerExample;
import com.mcoding.emis.goods.game.bean.Game;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DealerMapper {
    int countByExample(DealerExample example);

    int deleteByExample(DealerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dealer record);

    int insertSelective(Dealer record);

    List<Dealer> selectByExample(DealerExample example);

    Dealer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dealer record, @Param("example") DealerExample example);

    int updateByExample(@Param("record") Dealer record, @Param("example") DealerExample example);

    int updateByPrimaryKeySelective(Dealer record);

    int updateByPrimaryKey(Dealer record);
    
    /*查询经销商*/
    List<Dealer> queryDealerByPage(Map<String, Object> param);
}