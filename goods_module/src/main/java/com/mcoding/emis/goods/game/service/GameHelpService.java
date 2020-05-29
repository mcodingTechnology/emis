package com.mcoding.emis.goods.game.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.game.bean.GameHelp;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface GameHelpService extends BaseService<GameHelp, String> {

	/**
	 * 后台游戏管理——游戏规则说明查询
	 */
	public PageView<GameHelp> queryGameHelpData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
	/**添加游戏**/
	public CommonResult<String> addGameHelp(GameHelp game);
	
	/**根据游戏id查询游戏说明*/
	public CommonResult<GameHelp> queryGameHelpByGameid(Integer gameid,String type);
}
