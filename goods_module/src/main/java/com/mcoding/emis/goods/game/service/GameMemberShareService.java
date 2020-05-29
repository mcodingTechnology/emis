package com.mcoding.emis.goods.game.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.game.bean.GameMemberShare;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface GameMemberShareService extends BaseService<GameMemberShare, String> {

	/**
	 * 分享后更新会员分享数据
	 * @param game
	 * @param openid
	 * @return
	 */
	public CommonResult<GameMemberShare> updateGameMemberShareRecord(HttpSession session, Integer gameid,String shareOpenid);
}
