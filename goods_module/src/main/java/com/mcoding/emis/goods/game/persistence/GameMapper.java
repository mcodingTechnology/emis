package com.mcoding.emis.goods.game.persistence;

import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameExample;
import com.mcoding.emis.goods.game.bean.GamePrize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GameMapper {
    int countByExample(GameExample example);

    int deleteByExample(GameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Game record);

    int insertSelective(Game record);

    List<Game> selectByExample(GameExample example);

    Game selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Game record, @Param("example") GameExample example);

    int updateByExample(@Param("record") Game record, @Param("example") GameExample example);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
    
    /*查询游戏活动*/
    List<Game> queryGameByPage(Map<String, Object> param);

    void setGameProbabilityById(Game game);
}