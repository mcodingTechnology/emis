package com.mcoding.emis.goods.game.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.bean.GameMemberResultExample;

public interface GameMemberResultMapper {
    int countByExample(GameMemberResultExample example);

    int deleteByExample(GameMemberResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameMemberResult record);

    int insertSelective(GameMemberResult record);

    List<GameMemberResult> selectByExample(GameMemberResultExample example);

    GameMemberResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameMemberResult record, @Param("example") GameMemberResultExample example);

    int updateByExample(@Param("record") GameMemberResult record, @Param("example") GameMemberResultExample example);

    int updateByPrimaryKeySelective(GameMemberResult record);

    int updateByPrimaryKey(GameMemberResult record);
    
    /*查询游戏答题和抽奖结果*/
    List<GameMemberResult> queryGameMemberResultByPage(Map<String, Object> param);
    
    /*查询抽奖结果*/
    List<GameMemberResult> queryGameMemberResultList(Map<String, Object> param);

    /*查询抽奖结果*/
    List<GameMemberResult> queryGameMemberResultListV2(Map<String, Object> param);

    List<GameMemberResult> selectByExampleByPage(GameMemberResultExample example);
    
}