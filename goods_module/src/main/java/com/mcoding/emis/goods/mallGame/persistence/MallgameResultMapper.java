package com.mcoding.emis.goods.mallGame.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.mallGame.bean.MallgameGambling;
import com.mcoding.emis.goods.mallGame.bean.MallgameResult;
import com.mcoding.emis.goods.mallGame.bean.MallgameResultExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeData;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeRecord;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeResultInfor;

public interface MallgameResultMapper {
	int countByExample(MallgameResultExample example);

	int deleteByExample(MallgameResultExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(MallgameResult record);

	int insertSelective(MallgameResult record);

	List<MallgameResult> selectByExample(MallgameResultExample example);

	MallgameResult selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") MallgameResult record,
			@Param("example") MallgameResultExample example);

	int updateByExample(@Param("record") MallgameResult record, @Param("example") MallgameResultExample example);

	int updateByPrimaryKeySelective(MallgameResult record);

	int updateByPrimaryKey(MallgameResult record);

	List<MallgameResult> getWinnerList(int gameid, int limit);

	List<MallgameStakeResultInfor> queryUserStakeGameInforByPage(Map<String, Object> param);

	List<MallgameResult> getWinner(int gameid);

	String getGoingIntegral(int gameid);

	List<MallgameStakeData> queryStakeByPage(Map<String, Object> param);

	List<String> queryStakeOpenIdByPage(Map<String, Object> param);

	void updateLotteryByOpenIdAndGameId(Map<String, Object> param);

	List<MallgameStakeRecord> queryResultRecordByPage(Map<String, Object> param);

	List<MallgameResult> queryMallGameResultByPage(MallgameResultExample example);
	
	/**
	 * 查询所有奖品名称
	 * @return
	 */
	List<MallgameResult> queryMallGameResultProductNameList();
	
	/**
	 * 查询所有的游戏名称
	 * @return
	 */
	List<MallgameGambling> queryDistintGamblingGameNameList();
}