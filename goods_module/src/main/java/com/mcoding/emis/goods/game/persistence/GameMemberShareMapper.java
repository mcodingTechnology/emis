package com.mcoding.emis.goods.game.persistence;

import com.mcoding.emis.goods.game.bean.GameMemberShare;
import com.mcoding.emis.goods.game.bean.GameMemberShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameMemberShareMapper {
    int countByExample(GameMemberShareExample example);

    int deleteByExample(GameMemberShareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameMemberShare record);

    int insertSelective(GameMemberShare record);

    List<GameMemberShare> selectByExample(GameMemberShareExample example);

    GameMemberShare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameMemberShare record, @Param("example") GameMemberShareExample example);

    int updateByExample(@Param("record") GameMemberShare record, @Param("example") GameMemberShareExample example);

    int updateByPrimaryKeySelective(GameMemberShare record);

    int updateByPrimaryKey(GameMemberShare record);
}