package com.mcoding.emis.goods.game.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.service.GameMemberResultService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class GameMemberResultController {
	
	@Autowired
	private GameMemberResultService gameMemberResultService;

	/** 
     * 后台游戏管理——游戏会员答题抽奖结果列表查询
     * @author Benson 
    */ 
    @RequestMapping("/gameMemberResultPageView.html")
    public ModelAndView gameMemberResultPageView() {
	    return gameMemberResultService.gameMemberResultPageView();
    }
    
	/** 
	  * 后台游戏管理——游戏题目查询
	  * @author Benson 
	*/ 
   @RequestMapping("/queryGameMemberResultData")
   @ResponseBody
   public PageView<GameMemberResult> queryGameMemberResultData(HttpServletRequest request,
   		String iDisplayStart, String iDisplayLength) {
       return gameMemberResultService.queryGameMemberResultData(request,iDisplayStart, iDisplayLength);
   }
   
   /** 
	  * 前端接口——更新游戏结果
	  * @author Benson 
	*/ 
   @RequestMapping("/merriplusApi/updateGameMemberResult")
   @ResponseBody
   public CommonResult<GameMemberResult> updateGameMemberResult(HttpSession session,@RequestBody GameMemberResult gameMemberResult) {
     return gameMemberResultService.updateGameMemberResult(session,gameMemberResult);
   }
   
   /** 
	  * 前端接口——根据openid获取用户答题结果和抽奖结果
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/checkMemberResult")
	@ResponseBody
	public CommonResult<GameMemberResult> checkMemberResult(HttpSession session,Integer gameid) {
	   return gameMemberResultService.checkMemberResult(session,gameid);
	}
	
	/** 
	 * 前端接口——根据openid获取用户答题结果和抽奖结果
	 * @author Benson 
	 */ 
	@RequestMapping("/merriplusApi/checkGameAndMember")
	@ResponseBody
	public CommonResult<GameMemberResult> checkGameAndMember(HttpSession session,Integer gameid) {
		return gameMemberResultService.checkGameAndMember(session,gameid);
	}
	
	/** 
	  * 前端接口——更新收货人信息
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/updateMemberAddressInfo")
	@ResponseBody
	public CommonResult<GameMemberResult> updateMemberAddressInfo(HttpSession session,
			GameMemberResult gameMemberResult) {
	   return gameMemberResultService.updateMemberAddressInfo(session,gameMemberResult);
	}
	
	/** 
	 * 前端接口——更新领奖记录
	 * @author Benson 
	 */ 
	@RequestMapping("/merriplusApi/updateMemberResult")
	@ResponseBody
	public CommonResult<GameMemberResult> updateMemberResult(HttpSession session,
			GameMemberResult gameMemberResult) {
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
			gameMemberResult.setOpenid(openid);
		}
		return gameMemberResultService.updateMemberResult(openid,gameMemberResult);
	}
	
	/** 
	  * 前端接口——获取会员答题抽奖结果
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/getMemberResultInfo")
	@ResponseBody
	public CommonResult<GameMemberResult> getMemberResultInfo(HttpSession session,Integer gameid) {
	   return gameMemberResultService.getMemberResultInfo(session,gameid);
	}
	
	/** 
	  * 前端接口——答题与获取奖品的接口
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/answerAndGetPrize")
	@ResponseBody
	public CommonResult<GameMemberResult> answerAndGetPrize(HttpSession session,@RequestBody GameMemberResult gameMemberResult) {
	   return gameMemberResultService.answerAndGetPrize(session,gameMemberResult);
	}
	
	/** 
	  * 流量充值的接口
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/flowRecharge")
	@ResponseBody
	public CommonResult<GameMemberResult> flowRecharge(HttpSession session,String mobilePhone) {
	   return gameMemberResultService.flowRecharge(session,mobilePhone);
	}
	
	/** 
	  * 前端接口——更新收货人信息
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/addOrUpdateMemberAddressInfo")
	@ResponseBody
	public CommonResult<GameMemberResult> addOrUpdateMemberResult(HttpSession session,
			@RequestBody GameMemberResult gameMemberResult) {
		String openid = (String) session.getAttribute("openid");
//		String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
		System.out.println("=================获取到openid================"+openid);
//		String openid = "o_3tTs1dJ4cdFw9n9muKBmoGRdaw_1";
		gameMemberResult.setOpenid(openid);
	   return gameMemberResultService.addOrUpdateMemberResult(gameMemberResult);
	}
	
	/** 
	  * 前端接口——获取会员所有抽奖结果
	  * @author Benson 
	*/ 
	@RequestMapping("/merriplusApi/getMemberResultList")
	@ResponseBody
	public CommonResult<ArrayList<GameMemberResult>> getMemberResultList(HttpSession session,HttpServletRequest request,Integer gameid) {
//		String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return gameMemberResultService.getMemberResultList(openid,gameid);
	}
	
	
}
