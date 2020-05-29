package com.mcoding.emis.goods.mallGame.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.mallGame.bean.MallgameActivityInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameGambling;
import com.mcoding.emis.goods.mallGame.bean.MallgameGamblingExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeDetail;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeInfor;

public interface MallgameGamblingMapper {
	int countByExample(MallgameGamblingExample example);

	int deleteByExample(MallgameGamblingExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(MallgameGambling record);

	int insertSelective(MallgameGambling record);

	List<MallgameGambling> selectByExample(MallgameGamblingExample example);

	MallgameGambling selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") MallgameGambling record,
			@Param("example") MallgameGamblingExample example);

	int updateByExample(@Param("record") MallgameGambling record,
			@Param("example") MallgameGamblingExample example);

	int updateByPrimaryKeySelective(MallgameGambling record);

	int updateByPrimaryKey(MallgameGambling record);

	/* 查询押宝游戏活动 */
	List<MallgameGambling> queryGamblingByPage(Map<String, Object> param);

	int insertAndGetId(MallgameGambling record);

	List<MallgameStakeDetail> queryStakeGameByPage(Map<String, Object> param);

	List<MallgameStakeInfor> getGamblingStakeInfor(int gameid);

	List<MallgameActivityInfor> queryGamblingStakeInforByPage(
			Map<String, Object> param);

}