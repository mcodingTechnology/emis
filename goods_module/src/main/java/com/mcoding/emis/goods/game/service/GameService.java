package com.mcoding.emis.goods.game.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface GameService extends BaseService<Game, String> {

	/**
	 * 后台游戏管理——游戏活动查询
	 */
	public PageView<Game> queryGameData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength,String pageNo,String pageSize);
	
	/**
	 * 摇一摇抽奖
	 */
//	public CommonResult<GameMemberResult> shakeDraw(String openid,Integer gameid,Boolean flag);
	
	/**添加游戏**/
	public CommonResult<String> addGame(Game game,List<GamePrize> gamePrizes);
	
	/**
	 * 摇一摇抽奖
	 */
	public CommonResult<GameMemberResult> shakeAndGetPrize(String openid,String brandCode,Integer gameid,String type);

	/**
	 * 摇一摇抽奖
	 * v2
	 */
	public CommonResult<GameMemberResult> shakeAndGetPrizeV2(String openid,String brandCode,Integer gameid,String type);

	public CommonResult<GameMemberResult> shakeAndGetTmallCard(String openid,String brandCode,Integer gameid,String type);

	/**
	 * 年会选服装
	 */
	public CommonResult<GameMemberResult> chooseClothing(String openid,String brandCode,Integer gameid,String type,String size);
	
	/**
	 * 摇一摇抽奖品
	 */
	public CommonResult<GameMemberResult> shakeGetPrize(HttpSession session,Integer gameid);

	public CommonResult<String> ckeckIsGameTime(Integer gameid);
	
	/**
	 * 批量生成中奖编码
	 */
	public CommonResult<String> addGamePrizeNumberByPrizes(Integer gameid);

	/**
	 * 摇一摇中优惠券
	 * @param openid
	 * @param gameid
	 * @return
	 */
	public CommonResult<GameMemberResult> shakeGetCard(String openid, int gameid);

	/**
	 * 领奖，形式是卡券
	 * @param gameMemberResultId
	 * @param openid
	 * @return
	 */
	public CommonResult<Card> accpetCardForGamePrize(int gameMemberResultId, String openid);
	
	/**
	 * 摇一摇抽积分
	 * @param gameid
	 * @return
	 */
	public CommonResult<GameMemberResult> shakeGetPoints(HttpSession session, Integer gameid);

	/**
	 * 腕力大赛专用，提交所选择的选手信息
	 * @param prizeid
	 * @param openid
	 */
	public CommonResult<GameMemberResult> submitPlayerInfo(String openid, Integer prizeid);

	/**
	 * 腕力大赛专用，开奖
	 * @author Benson
	 */
	public CommonResult<String> lotterGameAndSendWxMsg(String openid, Integer prizeid);

	public void setGameProbabilityById(String probability,Integer gameId);
	
}
