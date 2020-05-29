package com.mcoding.emis.goods.game.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameAndPrizeJson;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.bean.GameMemberResultExample;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.persistence.GameMemberResultMapper;
import com.mcoding.emis.goods.game.service.GamePrizeService;
import com.mcoding.emis.goods.game.service.GameService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private GamePrizeService gamePrizeService;
	
	@Autowired
	private GameMemberResultMapper gameMemberResultMapper;

	/**
	 * 后台游戏管理——游戏活动列表页面
	 * 
	 * @author Benson
	 */
	@RequestMapping("/gamePageView.html")
	public ModelAndView gamePageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("game/gameList");
		return mav;
	}

	/**
	 * 后台游戏管理——游戏题目查询
	 * 
	 * @author Benson
	 */
	@RequestMapping("/queryGameData")
	@ResponseBody
	public PageView<Game> queryGameData(HttpServletRequest request, String iDisplayStart, String iDisplayLength,String pageNo,String pageSize) {
		return gameService.queryGameData(request, iDisplayStart, iDisplayLength,pageNo,pageSize);
	}

	/**
	 * 后台游戏管理——游戏活动添加页面
	 * 
	 * @author Benson
	 */
	@RequestMapping("/addGameView.html")
	public ModelAndView addGameView(String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			Game game = gameService.queryObjById(id).getData();
			mav.addObject("game", game);

			GamePrize gamePrize = new GamePrize();
			gamePrize.setGameid(game.getId());
			List<GamePrize> gamePrizes = gamePrizeService.queryListObj(gamePrize).getData();
			mav.addObject("gamePrizes", gamePrizes);
			mav.addObject("edit", "edit");
		}
		mav.setViewName("game/addGame");
		return mav;
	}

	/**
	 * 添加游戏
	 * 
	 * @author Benson
	 */
	@RequestMapping("/addGame")
	@ResponseBody
	public CommonResult<String> addGame(@RequestBody GameAndPrizeJson gameAndPrizeJson) {
		Game game = gameAndPrizeJson.getGame();
		List<GamePrize> gamePrizes = gameAndPrizeJson.getGamePrizes();
		return gameService.addGame(game, gamePrizes);
	}

	/**
	 * 摇一摇抽奖
	 * 
	 *            顺序领取开关 true 从1-N等奖顺序领取 ；false 随概率领取
	 * @author Benson
	 */
	/*@RequestMapping("/merriplusApi/shakeDraw")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeDraw(HttpSession session, Integer gameid, Boolean flag) {
		// String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		return gameService.shakeDraw(openid, gameid, flag);
	}*/

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 后台游戏管理——根据id查询游戏
	 * 
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/queryGameById")
	@ResponseBody
	public CommonResult<Game> queryGameById(String id) {
		return gameService.queryObjById(id);
	}

	/**
	 * 摇一摇抽奖
	 * 
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/shakeAndGetPrize")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeAndGetPrize(HttpSession session, Integer gameid, String type) {
		String openid = null;String brandCode = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		if(session.getAttribute("brandCode")!=null){
			brandCode = (String) session.getAttribute("brandCode");
		}
		return gameService.shakeAndGetPrize(openid,brandCode, gameid, type);
	}

	/**
	 * 摇一摇抽奖
	 * version 2
	 * @author hexianhua
	 */
	@RequestMapping("/merriplusApi/shakeAndGetPrize_v2")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeAndGetPrizeV2(HttpServletRequest request,HttpSession session, Integer gameid, String type) {
		String brandCode = null;
		String openid = request.getParameter("openId");
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		if(session.getAttribute("brandCode")!=null){
			brandCode = (String) session.getAttribute("brandCode");
		}
		return gameService.shakeAndGetPrizeV2(openid,brandCode, gameid, type);
	}

	/**
	 * 摇一摇抽天猫优惠券
	 *
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/shakeAndGetTmallCard")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeAndGetTmallCard(HttpSession session, Integer gameid, String type) {
		String openid = null;String brandCode = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		if(session.getAttribute("brandCode")!=null){
			brandCode = (String) session.getAttribute("brandCode");
		}
		return gameService.shakeAndGetTmallCard(openid,brandCode, gameid, type);
	}

	/**
	 * 年会选服装
	 *
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/chooseClothing")
	@ResponseBody
	public CommonResult<GameMemberResult> chooseClothing(HttpSession session, Integer gameid, String type,String size) {
		String openid = null;String brandCode = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		if(session.getAttribute("brandCode")!=null){
			brandCode = (String) session.getAttribute("brandCode");
		}
		return gameService.chooseClothing(openid,brandCode, gameid, type,size);
	}
	
	/**
	 * 摇一摇抽优惠券
	 * @param session
	 * @param gameid
	 * @return
	 */
	@RequestMapping("/merriplusApi/shakeGetCard")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeGetCard(HttpSession session, int gameid) {
		String openid = (String) session.getAttribute("openid");
		if (StringUtils.isBlank(openid)) {
			return new CommonResult<GameMemberResult>(1, "找不到用户信息，请刷新后重试", null);
		}
		String brandCode = (String) session.getAttribute("brandCode");
		if (StringUtils.isBlank(brandCode)) {
			return new CommonResult<GameMemberResult>(1, "找不到游戏信息，请刷新后重试", null);
		}
		CommonResult<GameMemberResult> result = new CommonResult<>();
		try {
			result = gameService.shakeGetCard(openid, gameid);
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/merriplusApi/accpetCardForGamePrize")
	@ResponseBody
	public CommonResult<Card> accpetCardForGamePrize(int gameMemberResultId, HttpSession session){
		String openid = (String) session.getAttribute("openid");
		if (StringUtils.isBlank(openid)) {
			return new CommonResult<Card>(1, "找不到用户信息，请刷新后重试", null);
		}
		
		CommonResult<Card> result = new CommonResult<>();
		try {
			result = gameService.accpetCardForGamePrize(gameMemberResultId, openid);
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/merriplusApi/getMemberResultForGame")
	@ResponseBody
	public CommonResult<PageView<GameMemberResult>> getMemberResultForGame(int gameId, @RequestParam(defaultValue="1") int pageNo, @RequestParam(defaultValue="10")int pageSize){
		GameMemberResultExample gameMemberResultExample = new GameMemberResultExample();
		gameMemberResultExample.createCriteria()
		                       .andGameidEqualTo(gameId)
		                       .andPrizeidIsNotNull();
		PageView<GameMemberResult> pageView = new PageView<GameMemberResult>(pageNo, pageSize);
		gameMemberResultExample.setPageView(pageView);
		
		CommonResult<PageView<GameMemberResult>> result = new CommonResult<>();
		try {
//			List<GameMemberResult> list = this.gameMemberResultMapper.selectByExampleByPage(gameMemberResultExample);
			pageView.setQueryResult(this.gameMemberResultMapper.selectByExampleByPage(gameMemberResultExample));
			result.setData(pageView);
			result.setCode(0);
			result.setMsg("ok");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 摇一摇抽奖
	 * 
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/shakeGetPrize")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeGetPrize(HttpSession session, Integer gameid) {
		return gameService.shakeGetPrize(session, gameid);
	}

	/**
	 * 判断游戏是否在活动时间
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/merriplusApi/ckeckIsGameTime")
	@ResponseBody
	public CommonResult<String> ckeckIsGameTime(Integer gameid) {
		return gameService.ckeckIsGameTime(gameid);
	}

	/**
	 * 批量生成中奖编码
	 * 
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/addGamePrizeNumberByPrizes")
	@ResponseBody
	public CommonResult<String> addGamePrizeNumberByPrizes(Integer gameid) {
		return gameService.addGamePrizeNumberByPrizes(gameid);
	}
	
	/**
	 * 摇一摇抽积分
	 * 
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/shakeGetPoints")
	@ResponseBody
	public CommonResult<GameMemberResult> shakeGetPoints(HttpSession session, Integer gameid) {
		return gameService.shakeGetPoints(session, gameid);
	}

	/**
	 * 腕力大赛专用，提交所选择的选手信息
	 *
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/submitPlayerInfo")
	@ResponseBody
	public CommonResult<GameMemberResult> submitPlayerInfo(HttpSession session, Integer prizeid) {
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return gameService.submitPlayerInfo(openid, prizeid);
	}

	/**
	 * 腕力大赛专用，开奖
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/lotterGameAndSendWxMsg")
	@ResponseBody
	public CommonResult<String> lotterGameAndSendWxMsg(HttpSession session, Integer prizeid) {
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return gameService.lotterGameAndSendWxMsg(openid, prizeid);
	}

	@RequestMapping("/merriplusApi/setGameProbabilityById")
	@ResponseBody
	public CommonResult<String> setGameProbabilityById(String probability,Integer gameId,String password){
		CommonResult<String> result = new CommonResult<>();
		//操作密码：password-gymmaxer-666-zxcvbnm123456789
		if (StringUtils.isBlank(password)) {
			result.setData(null);
			result.setCode(400);
			result.setMsg("抱歉，无权操作");
			return result;
		}

		if (!"password-gymmaxer-666-zxcvbnm123456789".equals(password)) {
			result.setData(null);
			result.setCode(400);
			result.setMsg("密码错误，抱歉，无权操作");
			return result;
		}

		gameService.setGameProbabilityById(probability,gameId);

		result.setData(null);
		result.setCode(0);
		result.setMsg("success");
		return result;
	}
}
