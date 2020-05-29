package com.mcoding.emis.goods.game.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.bean.GamePrizeExample;

public interface GamePrizeMapper {
    int countByExample(GamePrizeExample example);

    int deleteByExample(GamePrizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GamePrize record);

    int insertSelective(GamePrize record);

    List<GamePrize> selectByExample(GamePrizeExample example);

    GamePrize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GamePrize record, @Param("example") GamePrizeExample example);

    int updateByExample(@Param("record") GamePrize record, @Param("example") GamePrizeExample example);

    int updateByPrimaryKeySelective(GamePrize record);

    int updateByPrimaryKey(GamePrize record);
    
    /*查询游戏答题和抽奖结果*/
    List<GamePrize> queryGamePrizeByPage(Map<String, Object> param);

    GamePrize queryRandom(int gameid, int type);
}