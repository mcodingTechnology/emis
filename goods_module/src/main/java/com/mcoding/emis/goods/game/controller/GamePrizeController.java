package com.mcoding.emis.goods.game.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.service.GamePrizeService;
import com.mcoding.emis.member.common.CommonResult;

import java.util.ArrayList;

@Controller
public class GamePrizeController {
	@Autowired
	private GamePrizeService gamePrizeService;
	
	/** 
     * 后台游戏管理——游戏奖品
     * @author Benson 
    */ 
    @RequestMapping("/gamePrizePageView.html")
    public ModelAndView gamePrizePageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("game/gamePrizeList");
	    return mav;
    }
    
    /** 
	  * 后台游戏管理——游戏奖品查询
	  * @author Benson 
	*/ 
   @RequestMapping("/queryGamePrizeData")
   @ResponseBody
   public PageView<GamePrize> queryGamePrizeData(HttpServletRequest request,
   		String iDisplayStart, String iDisplayLength) {
       return gamePrizeService.queryGamePrizeData(request,iDisplayStart, iDisplayLength);
   }
    
    /** 
	  * 后台游戏管理——根据id查询游戏奖品
	  * @author Benson 
	*/ 
	 @RequestMapping("/merriplusApi/queryGamePrizeById")
	 @ResponseBody
	 public CommonResult<GamePrize> queryGamePrizeById(String id) {
	     return gamePrizeService.queryObjById(id);
	 }
	 
	 
	   /** 
	     * 跳转添加奖品的页面
	     * @return 
	     * @author Benson 
	   */ 
	   @RequestMapping("/addGamePrizeView.html")
	   public ModelAndView addGamePrizeView(String id) {
	       return gamePrizeService.addGamePrizeView(id);
	   }
	   
	   /** 
	    * 添加奖品
	    * @param gamePrize
	    * @return 
	    * @author Benson 
	  */ 
	   @RequestMapping("/addGamePrize")
	   @ResponseBody
	   public CommonResult<String> addGamePrize(GamePrize gamePrize) {
	       return gamePrizeService.addObj(gamePrize);
	   }

		/**
		 * 前端使用---根据gameid查询所有奖品
		 * @author Benson
		 */
		@RequestMapping("/merriplusApi/queryGamePrizesByGameid")
		@ResponseBody
		public CommonResult<ArrayList<GamePrize>> queryGamePrizesByGameid(Integer gameid) {
			GamePrize gamePrize = new GamePrize();
			gamePrize.setGameid(gameid);
			return gamePrizeService.queryListObj(gamePrize);
		}


}
