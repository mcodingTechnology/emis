package com.mcoding.emis.goods.game.persistence;

import com.mcoding.emis.goods.game.bean.GameHelp;
import com.mcoding.emis.goods.game.bean.GameHelpExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GameHelpMapper {
    int countByExample(GameHelpExample example);

    int deleteByExample(GameHelpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameHelp record);

    int insertSelective(GameHelp record);

    List<GameHelp> selectByExample(GameHelpExample example);

    GameHelp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameHelp record, @Param("example") GameHelpExample example);

    int updateByExample(@Param("record") GameHelp record, @Param("example") GameHelpExample example);

    int updateByPrimaryKeySelective(GameHelp record);

    int updateByPrimaryKey(GameHelp record);
    
    /*查询游戏规则说明*/
    List<GameHelp> queryGameHelpByPage(Map<String, Object> param);
}