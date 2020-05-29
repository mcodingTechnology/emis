package com.mcoding.emis.goods.game.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.emis.goods.game.bean.GameMemberShare;
import com.mcoding.emis.goods.game.service.GameMemberShareService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class GameMemberShareController {

	@Autowired
	private GameMemberShareService gameMemberShareService;

	/**
	 * 分享后更新会员分享数据
	 * @author Benson
	 */
	@RequestMapping("/gameApi/updateGameMemberShareRecord")
	@ResponseBody
	public CommonResult<GameMemberShare> updateGameMemberShareRecord(HttpSession session, Integer gameid,String shareOpenid) {
		return gameMemberShareService.updateGameMemberShareRecord(session, gameid,shareOpenid);
	}

}
