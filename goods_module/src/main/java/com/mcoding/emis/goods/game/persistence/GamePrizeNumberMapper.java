package com.mcoding.emis.goods.game.persistence;

import com.mcoding.emis.goods.game.bean.GamePrizeNumber;
import com.mcoding.emis.goods.game.bean.GamePrizeNumberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GamePrizeNumberMapper {
    int countByExample(GamePrizeNumberExample example);

    int deleteByExample(GamePrizeNumberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GamePrizeNumber record);

    int insertSelective(GamePrizeNumber record);

    List<GamePrizeNumber> selectByExample(GamePrizeNumberExample example);

    GamePrizeNumber selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GamePrizeNumber record, @Param("example") GamePrizeNumberExample example);

    int updateByExample(@Param("record") GamePrizeNumber record, @Param("example") GamePrizeNumberExample example);

    int updateByPrimaryKeySelective(GamePrizeNumber record);

    int updateByPrimaryKey(GamePrizeNumber record);
}