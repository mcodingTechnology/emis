package com.mcoding.emis.goods.game.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.game.bean.GameAnswerJson;
import com.mcoding.emis.goods.game.bean.GameQuestion;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏题目Service
 * @author Administrator
 *
 */
@Service
public interface GameQuestionService extends BaseService<GameQuestion, String> {

	/**
	 * 后台游戏管理——游戏题目查询
	 * @return
	 */
	public PageView<GameQuestion> queryGameQuestionData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
	/** 
	  * 前端接口——随机从题库中抽取5道题，A题库抽3道题，B题库抽2道题
	*/ 
	public CommonResult<GameAnswerJson> getRandomQuestion(HttpSession session);
	
	/**游戏问题添加页面**/
	public ModelAndView addGameQuestionView(String id);
	
	/**获取游戏的所有问题**/
	public CommonResult<GameAnswerJson> getQuestionsByGameid(HttpSession session,Integer gameid);
}
