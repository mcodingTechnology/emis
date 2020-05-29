package com.mcoding.emis.goods.game.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.bean.GameAnswerJson;
import com.mcoding.emis.goods.game.bean.GameQuestion;
import com.mcoding.emis.goods.game.service.GameQuestionService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class GameQuestionController {
	
	@Autowired
	private GameQuestionService gameQuestionService;
	/** 
     * 后台游戏管理——游戏题目列表查询
     * @author Benson 
    */ 
    @RequestMapping("/gameQuestionPageView.html")
    public ModelAndView gameQuestionPageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("game/gameQuestionList");
	    return mav;
    }
	   
   /** 
	  * 后台游戏管理——游戏题目查询
	  * @author Benson 
	*/ 
    @RequestMapping("/queryGameQuestionData")
    @ResponseBody
    public PageView<GameQuestion> queryGameQuestionData(HttpServletRequest request,
    		String iDisplayStart, String iDisplayLength) {
        return gameQuestionService.queryGameQuestionData(request,iDisplayStart, iDisplayLength);
    }
    
    /** 
     * 跳转添加游戏题目的页面
     * @return 
     * @author Benson 
   */ 
   @RequestMapping("/addGameQuestionView.html")
   public ModelAndView addGameQuestionView(String id) {
       return gameQuestionService.addGameQuestionView(id);
   }
   
   /** 
    * 添加游戏题目
    * @param GameQuestion
    * @return 
    * @author Benson 
  */ 
   @RequestMapping("/addGameQuestion")
   @ResponseBody
   public CommonResult<String> addGameQuestion(GameQuestion gameQuestion) {
       return gameQuestionService.addObj(gameQuestion);
   }
   
   /** 
	  * 后台游戏管理——根据id查询游戏题目
	  * @author Benson 
	*/ 
  @RequestMapping("/merriplusApi/queryGameQuestionById")
  @ResponseBody
  public CommonResult<GameQuestion> queryGameQuestionById(String id) {
      return gameQuestionService.queryObjById(id);
  }
  
  	/** 
	  * 后台游戏管理——根据id删除游戏题目
	  * @return 
	  * @author Benson 
	*/ 
	 @RequestMapping("/deleteGameQuestion")
	 @ResponseBody
	 public CommonResult<String> deleteGameQuestion(String teplId) {
	     return gameQuestionService.deleteObjById(teplId);
	 }
	 
		 /** 
		  * 前端接口——随机从题库中抽取5道题，A题库抽3道题，B题库抽2道题
		  * @author Benson 
		*/ 
	  @RequestMapping("/merriplusApi/getRandomQuestion")
	  @ResponseBody
	  public CommonResult<GameAnswerJson> getRandomQuestion(HttpSession session) {
	      return gameQuestionService.getRandomQuestion(session);
	  }
  
	  /** 
	  * 前端接口——获取所有题目
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/getQuestionsByGameid")
	@ResponseBody
	public CommonResult<GameAnswerJson> getQuestionsByGameid(HttpSession session,Integer gameid) {
	   return gameQuestionService.getQuestionsByGameid(session,gameid);
	}
}
