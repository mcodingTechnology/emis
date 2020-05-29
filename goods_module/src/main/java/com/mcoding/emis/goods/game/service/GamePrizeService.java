package com.mcoding.emis.goods.game.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏奖品Service
 * @author Administrator
 *
 */
@Service
public interface GamePrizeService extends BaseService<GamePrize, String> {

	/**
	 * 后台游戏管理——游戏奖品查询
	 */
	public PageView<GamePrize> queryGamePrizeData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
	 /**跳转添加奖品的页面*/ 
	public ModelAndView addGamePrizeView(String id);
	
	/**根据条件查询奖品列表**/
	public CommonResult<ArrayList<GamePrize>> queryListObj(GamePrize gamePrize);

	/***根据条件随机查询一条记录**/
	public GamePrize queryRandom(int gameid, int type);

}
