package com.mcoding.emis.goods.game.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.game.bean.GameQuestion;
import com.mcoding.emis.goods.game.bean.GameQuestionExample;

public interface GameQuestionMapper {
    int countByExample(GameQuestionExample example);

    int deleteByExample(GameQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameQuestion record);

    int insertSelective(GameQuestion record);

    List<GameQuestion> selectByExample(GameQuestionExample example);

    GameQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameQuestion record, @Param("example") GameQuestionExample example);

    int updateByExample(@Param("record") GameQuestion record, @Param("example") GameQuestionExample example);

    int updateByPrimaryKeySelective(GameQuestion record);

    int updateByPrimaryKey(GameQuestion record);
    
    /*查询游戏题目*/
    List<GameQuestion> queryGameQuestionByPage(Map<String, Object> param);
    
    /*随机查询5道题*/
    ArrayList<GameQuestion> getRadomQuestion();
}