package com.mcoding.emis.goods.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;
import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeMonthExample;
import com.mcoding.emis.goods.income.persistence.IncomeMapper;
import com.mcoding.emis.goods.income.persistence.IncomeMonthMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberBankerInfo;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberBankerInfoService;
import com.mcoding.emis.member.service.member.MemberService;

@Controller
public class MemberBankerInfoController {
	@Autowired
	private MemberBankerInfoService memberBankerInfoService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private IncomeMapper incomeMapper;
	@Autowired
	private IncomeMonthMapper incomeMonthMapper;
	
	/**
	 * 接口，新增加盟商银行信息
	 */
	@RequestMapping(value="/front/insertBankerInfo")
	@ResponseBody
	CommonResult<String> insertBankerInfo(@RequestBody MemberBankerInfo memberBankerInfo,HttpSession session,HttpServletRequest request) {
		String openid = (String) session.getAttribute("openid");
		Member member = memberService.queryMemberByOpenid(openid);
		memberBankerInfo.setMemberid(member.getMemberId());
		String aa = request.getParameter("bankName");
		CommonResult<String> result = memberBankerInfoService.insertBankerInfo(memberBankerInfo);
		IncomeExample incomeExample = new IncomeExample();
		incomeExample.createCriteria().andMemberidEqualTo(member.getMemberId());
		Income tmp = new Income();
		tmp.setStatus(Income.STATUS_COMPLETED);
		this.incomeMapper.updateByExampleSelective(tmp, incomeExample);
		
		IncomeMonthExample incomeMonthExample = new IncomeMonthExample();
		incomeMonthExample.createCriteria().andMemberIdEqualTo(member.getMemberId());
		IncomeMonth tmpIncomeMonth = new IncomeMonth();
		tmpIncomeMonth.setRealName(memberBankerInfo.getRealname());
		tmpIncomeMonth.setIdentity(memberBankerInfo.getIdentity());
		this.incomeMonthMapper.updateByExampleSelective(tmpIncomeMonth, incomeMonthExample);
		
		return result;
	}

	/**
	 * 接口，修改加盟商银行信息
	 */
	@RequestMapping("/front/updateBankerInfo")
	@ResponseBody
	CommonResult<String> updateBankerInfo(HttpSession session, @RequestBody MemberBankerInfo memberBankerInfo) {
		String openid = (String) session.getAttribute("openid");
		Member member = memberService.queryMemberByOpenid(openid);
		memberBankerInfo.setMemberid(member.getMemberId());
		return memberBankerInfoService.updateBankerInfo(memberBankerInfo);
	}

	/**
	 * 接口，获取加盟商银行信息
	 */
	@RequestMapping("/front/getBankerInfo")
	@ResponseBody
	CommonResult<MemberBankerInfo> getBankerInfo(HttpSession session) {
		String openid = (String) session.getAttribute("openid");
		Member member = memberService.queryMemberByOpenid(openid);
		if(null == member){
			return new CommonResult(0, "success", null);
		}
		return memberBankerInfoService.getBankerInfoByMemberId(member.getMemberId());
	}

}
