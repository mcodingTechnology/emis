package com.mcoding.emis.goods.game.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.bean.GameHelp;
import com.mcoding.emis.goods.game.service.GameHelpService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class GameHelpController {
	
	@Autowired
	private GameHelpService gameHelpService;

	/** 
     * 后台游戏管理——游戏活动列表页面
     * @author Benson 
    */ 
    @RequestMapping("/GameHelpPageView.html")
    public ModelAndView gameHelpPageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("game/gameHelpList");
	    return mav;
    }
    
	/** 
	  * 后台游戏管理——游戏题目查询
	  * @author Benson 
	*/ 
   @RequestMapping("/queryGameHelpData")
   @ResponseBody
   public PageView<GameHelp> queryGameHelpData(HttpServletRequest request,
   		String iDisplayStart, String iDisplayLength) {
       return gameHelpService.queryGameHelpData(request,iDisplayStart, iDisplayLength);
   }
   
   /** 
    * 后台游戏管理——游戏活动添加页面
    * @author Benson 
   */ 
   @RequestMapping("/addGameHelpView.html")
   public ModelAndView addGameHelpView(String id) {
	    ModelAndView mav = new ModelAndView();
	    if(StringUtils.isNotBlank(id)){
	    	GameHelp gameHelp = gameHelpService.queryObjById(id).getData();
	       	mav.addObject("gamehelp", gameHelp);
	    	mav.addObject("edit","edit");
	    }
	    mav.setViewName("game/addGameHelp");
	    return mav;
   }
   
   /** 
    * 添加游戏说明
    * @author Benson 
  */ 
   @RequestMapping("/addGameHelp")
   @ResponseBody
   public CommonResult<String> addGameHelp(GameHelp gameHelp) {
       return gameHelpService.addGameHelp(gameHelp);
   }
   
   /** 
	  * 根据游戏id查询游戏说明
	  * @author Benson 
	  */ 
	 @RequestMapping("/gameApi/queryGameHelpByGameid")
	 @ResponseBody
	 public CommonResult<GameHelp> queryGameHelpByGameid(Integer gameid,String type) {
		 return gameHelpService.queryGameHelpByGameid(gameid,type);
	 }

}
