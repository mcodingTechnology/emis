package com.mcoding.emis.goods.purse.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetail;
import com.mcoding.emis.goods.purse.service.MemberPurseDetailService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class MemberPurseDetailController {
	@Autowired
    protected MemberPurseDetailService memberPurseDetailService;
    
    
    @RequestMapping("/addMemberPurseDetail")
	@ResponseBody
	public CommonResult<String> addMemberPurseDetail(HttpServletRequest request, MemberPurseDetail memberPurseDetail)
			throws ParseException {
		return memberPurseDetailService.addMemberPurseDetail(memberPurseDetail);
	}
    
    @RequestMapping("/purse/getMemberPurseDetail")
	@ResponseBody
	public PageView<MemberPurseDetail> getMemberPurseDetail(Integer pageNo,Integer pageSize,
			HttpSession session,String brandCode) {
		String openid = (String) session.getAttribute("openid");
		PageView<MemberPurseDetail> jsonResult = memberPurseDetailService.getMemberPurseDetail(pageNo,pageSize,openid);
		return jsonResult;
	}
}