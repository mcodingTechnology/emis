package com.mcoding.emis.goods.mallGame.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.mallGame.bean.GameAndGiftJson;
import com.mcoding.emis.goods.mallGame.bean.MallgameActivityInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameGambling;
import com.mcoding.emis.goods.mallGame.bean.MallgameGamblingStack;
import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameGiftStakeResponse;
import com.mcoding.emis.goods.mallGame.bean.MallgameResponse;
import com.mcoding.emis.goods.mallGame.bean.MallgameResult;
import com.mcoding.emis.goods.mallGame.bean.MallgameStackGameAndGiftJson;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeRecord;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeStatus;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeWinnerInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameUserStakeResult;
import com.mcoding.emis.goods.mallGame.service.GamblingService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class GamblingController {

	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private GamblingService gamblingService;
	
	/**
	 * 后台押宝游戏管理——列表页面
	 * 
	 * @author Benson
	 */
	@RequestMapping("/gamblingPageView")
	public ModelAndView gamblingPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mallGame/gamblingList");
		return mav;
	}

	/**
	 * 后台押宝游戏管理——列表数据查询
	 * 
	 * @author Benson
	 */
	@RequestMapping("/queryGamblingData")
	@ResponseBody
	public PageView<MallgameGambling> queryGamblingData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		return gamblingService.queryGamblingData(request, iDisplayStart, iDisplayLength);
	}

	/**
	 * 后台游戏管理——游戏活动添加页面
	 * 
	 * @author Benson
	 */
	@RequestMapping("/addGamblingView")
	public ModelAndView addGamblingView(String id) {
		return gamblingService.addGamblingView(id);
	}

	/**
	 * 添加游戏
	 * 
	 * @author Benson
	 */
	@RequestMapping("/addGambling")
	@ResponseBody
	public CommonResult<String> addGambling(@RequestBody GameAndGiftJson gameAndPrizeJson) {
		MallgameGambling gambling = gameAndPrizeJson.getGambling();
		List<MallgameGift> gameGiftList = gameAndPrizeJson.getGameGiftList();
		return gamblingService.addGambling(gambling, gameGiftList);
	}

	/**
	 * 检查抽奖资格
	 * 
	 * @author Benson
	 */
	@RequestMapping("/gamblingApi/checkCanDraw")
	@ResponseBody
	public CommonResult<MallgameResult> checkCanDraw(HttpSession session, Integer gameid) {
		// String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		return gamblingService.checkCanDraw(openid, gameid);
	}

	/**
	 * 翻牌抽奖
	 * 
	 * @author Benson
	 */
	@RequestMapping("/gamblingApi/gamblingDraw")
	@ResponseBody
	public CommonResult<MallgameResult> gamblingDraw(HttpSession session, Integer gameid) {
		// String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		return gamblingService.gamblingDraw(openid, gameid);
	}

	/**
	 * 查询前几名的获奖名单
	 * 
	 * @author Benson
	 */
	@RequestMapping("/gamblingApi/getWinnerList")
	@ResponseBody
	public CommonResult<ArrayList<MallgameResult>> getWinnerList(Integer gameid, int limit) {
		return gamblingService.getWinnerList(gameid, limit);
	}

	/**
	 * 查询前几名的获奖名单
	 * 
	 * @author Benson
	 */
	@RequestMapping("/gamblingApi/getGamblingListByBrand")
	@ResponseBody
	public CommonResult<ArrayList<MallgameGambling>> getGamblingListByBrand(String brandCode) {
		return gamblingService.getGamblingListByBrand(brandCode);
	}

	/**
	 * 押宝游戏查询
	 * 
	 * @return
	 */
	@RequestMapping("/gamblingStakePageView")
	public ModelAndView gamblingStakePageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mallGame/gamblingStakeList");
		return mav;
	}

	/**
	 * 返回添加押宝游戏页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/addGamblingStakeView")
	public ModelAndView addGamblingStakeView(String id, String brandCode) {
		ModelAndView mav = new ModelAndView();
		if (StringHelper.isNotBlank(id)) {
			mav.addObject("edit", "edit");
			MallgameStakeInfor game = gamblingService.getGamblingStakeInfor(Integer.valueOf(id));
			brandCode = game.getBrandcode();
			mav.addObject("game", game);
		}
		mav.addObject("brandCode", brandCode);
		mav.setViewName("mallGame/addGamblingStake");
		return mav;
	}

	/**
	 * 添加押宝游戏
	 * 
	 * @param mg
	 * @param gift
	 * @return
	 */
	@RequestMapping(value = "/addGameblingStake", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<String> addGameblingStake(@RequestBody MallgameStackGameAndGiftJson gameAndGiftJson) {
		MallgameGambling mg = new MallgameGambling();
		MallgameGift gift = new MallgameGift();
		try {
			MallgameGamblingStack stackmg = gameAndGiftJson.getGambling();
			mg.setId(stackmg.getId().equals("") ? 0 : Integer.valueOf(stackmg.getId()));

			String brandcode = stackmg.getBrandcode();
			mg.setBrandcode(brandcode);
			mg.setGamestarttime(format.parse(stackmg.getGamestarttime()));
			mg.setGameendtime(format.parse(stackmg.getGameendtime()));
			mg.setGamename(stackmg.getGamename());
			mg.setStatus(1);
			mg.setExt("1");

			MallgameStakeGift stakeGift = gameAndGiftJson.getGift();
			gift.setId(stakeGift.getGiftid().equals("") ? 0 : Integer.valueOf(stakeGift.getGiftid()));
			gift.setCreatetime(new Date());
			gift.setIsproduct(1);
			gift.setNums(1);

			String productid = stakeGift.getProductid();
			Product product = gamblingService.getProductByProductId(productid);
			gift.setBrandcode(brandcode);
			gift.setProductid(Integer.valueOf(productid));
			gift.setProductname(product.getProductName());
			gift.setProductcoverimg(product.getProductCoverImg());
			gift.setCreatetime(new Date());
			gift.setGainpoint(Integer.valueOf(stakeGift.getGainpoint()));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		CommonResult<String> commonResult = gamblingService.addGamblingStack(mg, gift);

		return commonResult;

	}

	/**
	 * 查询押宝游戏
	 * 
	 * @param request
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping("/queryGamblingStakeData")
	@ResponseBody
	public PageView<MallgameActivityInfor> queryGamblingStakeData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		return gamblingService.queryGamblingStakeData(request, iDisplayStart, iDisplayLength);
	}

	/**
	 * 查询押宝游戏已经结束的列表
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/stakeApi/getEndList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public PageView<MallgameResponse> getEndList(HttpSession session,
			@RequestParam(required = true, value = "pageNo") String pageNo,
			@RequestParam(required = true, value = "pageSize") String pageSize) {
		int intPageNo = 1;
		int intPageSize = 5;
		try {
			intPageNo = Integer.valueOf(pageNo);
			intPageSize = Integer.valueOf(pageSize);
		} catch (NumberFormatException e) {
			intPageNo = 1;
			intPageSize = 5;
		}
		String openid = (String) session.getAttribute("openid");
		if (openid == null) {
			throw new NullPointerException("openid is null");
		}

		return gamblingService.getGamblingEndList(openid, intPageNo, intPageSize);
	}

	/**
	 * 查询正在进行的游戏列表
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/stakeApi/getGoingList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public PageView<MallgameResponse> getGoingList(HttpSession session,
			@RequestParam(required = true, value = "pageNo") String pageNo,
			@RequestParam(required = true, value = "pageSize") String pageSize) {
		int intPageNo = 1;
		int intPageSize = 5;
		try {
			intPageNo = Integer.valueOf(pageNo);
			intPageSize = Integer.valueOf(pageSize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			intPageNo = 1;
			intPageSize = 5;
		}
		String openid = (String) session.getAttribute("openid");
		if (openid == null) {
			throw new NullPointerException("openid is null");
		}

		return gamblingService.getGamblingGoingList(openid, intPageNo, intPageSize);
	}

	/**
	 * 查询用户参与的押宝游戏信息
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/stakeApi/getUserInfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public PageView<MallgameResponse> getUserInfo(HttpSession session,
			@RequestParam(required = true, value = "pageNo") String pageNo,
			@RequestParam(required = true, value = "pageSize") String pageSize) {
		int intPageNo = 1;
		int intPageSize = 5;
		try {
			intPageNo = Integer.valueOf(pageNo);
			intPageSize = Integer.valueOf(pageSize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			intPageNo = 1;
			intPageSize = 5;
		}
		String openid = (String) session.getAttribute("openid");
		if (openid == null) {
			throw new NullPointerException("openid is null");
		}

		return gamblingService.getGamblingUserInfo(openid, intPageNo, intPageSize);
	}

	/**
	 * 获取指定商品的押注情况列表
	 * 
	 * @param openid
	 * @param giftid
	 * @return
	 */
	@RequestMapping(value = "/stakeApi/getStakeListByGiftid", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<MallgameGiftStakeResponse> getGamblingListByGiftId(HttpSession session,
			@RequestParam(required = true, value = "pageNo") String pageNo,
			@RequestParam(required = true, value = "pageSize") String pageSize,
			@RequestParam(required = true, value = "giftid") String giftid) {
		int intPageNo = 1;
		int intPageSize = 5;
		try {
			intPageNo = Integer.valueOf(pageNo);
			intPageSize = Integer.valueOf(pageSize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			intPageNo = 1;
			intPageSize = 5;
		}

		String openid = (String) session.getAttribute("openid");
		if (openid == null) {
			throw new NullPointerException("openid is null");
		}
		return gamblingService.getGamblingListByGiftId(openid, giftid, intPageNo, intPageSize);
	}

	@RequestMapping(value = "/stakeApi/getGamblingWinnerByGiftId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<MallgameStakeWinnerInfor> getGamblingWinnerByGiftId(String giftid) {
		int intGiftid = -1;
		try {
			intGiftid = Integer.valueOf(giftid);
		} catch (NumberFormatException e) {
			intGiftid = -1;
		}
		return gamblingService.getGamblingWinnerByGiftId(intGiftid);
	}

	/**
	 * 查询指定游戏的状态接口
	 * 
	 * @param openid
	 * @param giftid
	 * @return
	 */
	@RequestMapping(value = "/stakeApi/getStakeStatus", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<MallgameStakeStatus> getGambligStakeStatus(HttpSession session,
			@RequestParam(required = true, value = "giftid") String giftid) {
		String openid = (String) session.getAttribute("openid");
		if (openid == null) {
			throw new NullPointerException("openid is null");
		}
		return gamblingService.getGambligStakeStatus(openid, giftid);
	}

	/**
	 * 押宝接口
	 * 
	 * @param openid
	 * @param giftid
	 * @param stakePoint
	 * @return
	 */
	@RequestMapping(value = "/stakeApi/gamblingStake", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<MallgameUserStakeResult> gamblingStake(HttpSession session,
			@RequestParam(required = true, value = "giftid") String giftid,
			@RequestParam(required = true, value = "stakePoint") String stakePoint) {
		String openid = (String) session.getAttribute("openid");
		if (openid == null) {
			throw new NullPointerException("openid is null");
		}

		int intStakePoint = 0;
		try {
			intStakePoint = Integer.valueOf(stakePoint);
		} catch (NumberFormatException e) {
			intStakePoint = 0;
			e.printStackTrace();
		}
		if (intStakePoint <= 0) {
			CommonResult<MallgameUserStakeResult> commResult = new CommonResult<>();
			commResult.setCode(-1);
			commResult.setData(null);
			commResult.setMsg("下注的积分值不符合要求");
			return commResult;
		} else if (StringUtils.isEmpty(giftid)) {
			CommonResult<MallgameUserStakeResult> commResult = new CommonResult<>();
			commResult.setCode(-1);
			commResult.setData(null);
			commResult.setMsg("giftid不能为空");
			return commResult;
		} else {
			return gamblingService.gamblingStake(openid, giftid, intStakePoint);
		}
	}

	@RequestMapping("/selectMallgamePageView")
	public ModelAndView selectMallgamePageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mallGame/selectMallgame");
		return mav;
	}

	@RequestMapping("/getMallgamePageView")
	public ModelAndView getMallgamePageView(HttpServletRequest request) {
		String[] selects = request.getParameterValues("selects");
		List<String> selectList = Arrays.asList(selects);

		String gameName = selectList.contains("0") ? "fp" : "yb";
		String giftBrandCode = selectList.contains("MRMJ") ? "MRMJ" : "JLD";

		ModelAndView mav = new ModelAndView();
		if (gameName.equals("fp")) {
			mav.setViewName("mallGame/gamblingList");
		} else {
			mav.addObject("brandCode", giftBrandCode);
			mav.setViewName("mallGame/gamblingStakeList");
		}

		return mav;
	}

	@RequestMapping("/getStakeRecordsPageView")
	public ModelAndView getStakeRecordsPageView(String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mallGame/stakeResultList");
		mav.addObject("gameId", id);
		return mav;
	}

	/**
	 * 分页查询押宝记录
	 * 
	 * @param gameId
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping(value = "/queryGamblingStakeRecordByPage")
	@ResponseBody
	public PageView<MallgameStakeRecord> queryGamblingStakeRecordByPage(String gameId, String iDisplayStart,
			String iDisplayLength) {
		PageView<MallgameStakeRecord> pageView = new PageView<>();
		try {
			pageView = gamblingService.queryGamblingStakeRecord(gameId, iDisplayStart, iDisplayLength);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}
	
	@RequestMapping("/mallGameResultListView")
	@ResponseBody
	public ModelAndView ruleEngineConfigListView(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mallGame/mallGameResultList");
		List<MallgameResult> productNameList = gamblingService.queryMallGameResultPrizeNameList();
		List<MallgameGambling> gameNameList = gamblingService.queryDistintGamblingGameNameList();
		mav.addObject("productNameList", productNameList);
		mav.addObject("gameNameList", gameNameList);
		return mav;
	}
	
	/**
	 * 后台管理，分页查询抽奖结果
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryMallGameResultByPage")
	@ResponseBody
	public PageView<MallgameResult> queryMallGameResultByPage(String iDisplayStart, String iDisplayLength,HttpServletRequest request){
		return gamblingService.queryMallGameResultByPage(iDisplayStart, iDisplayLength, request);
	}
}
