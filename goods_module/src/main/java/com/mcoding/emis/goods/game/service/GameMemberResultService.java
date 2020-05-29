package com.mcoding.emis.goods.game.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface GameMemberResultService extends BaseService<GameMemberResult, String> {

	/**
	 * 后台游戏管理——游戏答题和抽奖结果查询
	 * @return
	 */
	public PageView<GameMemberResult> queryGameMemberResultData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
	public CommonResult<GameMemberResult> updateGameMemberResult(HttpSession session,GameMemberResult gameMemberResult);
	
	public CommonResult<GameMemberResult> checkMemberResult(HttpSession session,Integer gameid);
	
	public CommonResult<GameMemberResult> updateMemberAddressInfo(HttpSession session,GameMemberResult gameMemberResult);
	
	public CommonResult<GameMemberResult> updateMemberResult(String openid,GameMemberResult gameMemberResult);

	public CommonResult<GameMemberResult> getMemberResultInfo(HttpSession session,Integer gameid);
	
	public CommonResult<GameMemberResult> answerAndGetPrize(HttpSession session,GameMemberResult gameMemberResult);

	public CommonResult<GameMemberResult> checkGameAndMember(HttpSession session,Integer gameid);

	public CommonResult<GameMemberResult> flowRecharge(HttpSession session,String mobilePhone);

	public CommonResult<GameMemberResult> addOrUpdateMemberResult(GameMemberResult gameMemberResult);

	public ModelAndView gameMemberResultPageView();
	
	public CommonResult<ArrayList<GameMemberResult>> getMemberResultList(String openid,Integer gameid);
}
