package com.mcoding.emis.goods.sign.controller;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameAndPrizeJson;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.sign.bean.SigninLog;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mcoding.emis.goods.sign.service.SignLogService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class SignLogController {
	@Autowired
	private SignLogService signLogService;
	@Autowired
	private MemberService memberService;

	/**
	 * 后台会员管理——会员签到明细列表查询
	 * @return mav
	 * @author Benson
	 */
	@RequestMapping("/signinLogPageView")
	public ModelAndView SigninLogPageView(String openid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("openid",openid);
		mav.setViewName("signin/signinLogList");
		return mav;
	}

	/**
	 * 跳转补签的页面
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/retroactivePageView")
	public ModelAndView retroactivePageView(String openid) {
		ModelAndView mav = new ModelAndView();
		Member member = memberService.queryMemberByOpenid(openid);
		mav.addObject("openid", openid);
		mav.addObject("member", member);
		mav.setViewName("signin/retroactive");
		return mav;
	}

	/**
	 * 后台会员管理——会员签到统计查询
	 * @return PageView
	 * @author Benson
	 */
	@RequestMapping("/querySigninLogData")
	@ResponseBody
	public PageView<SigninLog> querySigninLogData(String iDisplayStart, String iDisplayLength,String openid) {
		return signLogService.querySigninLogData(iDisplayStart, iDisplayLength,openid);
	}

	/**
	 * 添加游戏
	 *
	 * @author Benson
	 */
	@RequestMapping("/retroactive")
	@ResponseBody
	public CommonResult<String> retroactive(String starttime, String endtime,String openid) {
		return signLogService.retroactive(starttime, endtime,openid);
	}
}
