package com.mcoding.emis.goods.mallGame.util;

import java.util.LinkedHashMap;
import java.util.List;

import com.mcoding.emis.goods.mallGame.bean.MallgameResult;
import com.mcoding.emis.goods.mallGame.bean.MallgameResultExample;
import com.mcoding.emis.goods.mallGame.persistence.MallgameResultMapper;

/**
 * 押宝游戏结果工具类
 * 
 * @author wuzetao
 * 
 */
public class MallgameStakeResultUtil {

	/**
	 * 获取openID和用户押注积分的映射表
	 * 
	 * @param gameResultMapper
	 * @param gameId
	 * @return
	 */
	public static LinkedHashMap<String, Integer> getOpenIdAndStakePointMap(
			MallgameResultMapper gameResultMapper, int gameId) {
		MallgameResultExample userExample = new MallgameResultExample();
		com.mcoding.emis.goods.mallGame.bean.MallgameResultExample.Criteria userCriteria = userExample
				.createCriteria();
		userCriteria.andGameidEqualTo(gameId);
		List<MallgameResult> gameResults = gameResultMapper
				.selectByExample(userExample);

		// 有序的openId和下注积分map
		LinkedHashMap<String, Integer> openIdMapStakePoint = new LinkedHashMap<>();
		for (MallgameResult gameResult : gameResults) {
			String userOpenid = gameResult.getOpenid();
			int stakePoint = gameResult.getGainpoint();
			openIdMapStakePoint.put(userOpenid, stakePoint);
		}
		return openIdMapStakePoint;
	}

}
