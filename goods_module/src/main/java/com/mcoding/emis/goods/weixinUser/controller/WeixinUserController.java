package com.mcoding.emis.goods.weixinUser.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;
import com.mcoding.emis.goods.sign.persistence.SigninStatisticsMapper;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.service.member.WeixinUserService;

@Controller
public class WeixinUserController {
	@Autowired
	private WeixinUserService weixinUserService;
	
	@Autowired
	private SigninStatisticsMapper signinStatisticsMapper;
	

	/** 
	  * 微商城API---获取微信会员信息
	  * @return JsonResult<String>
	  * @author Benson
	*/ 
	@RequestMapping("/merriplusApi/getWxuserInfo")
	@ResponseBody
	public JsonResult<WeixinUser> getWxuserInfo(HttpSession session,String brandCode) {
		String brandCodeInSession = (String) session.getAttribute("brandCode");
		String openid = (String) session.getAttribute("openid");
		
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}
		
		JsonResult<WeixinUser> weixinUserResult = new JsonResult<>();
		try {

			weixinUserResult = this.weixinUserService.getWxuserInfo(openid, brandCodeInSession);
			WeixinUser weixinUser = weixinUserResult.getData();
			SigninStatistics signinStatistics = signinStatisticsMapper.getLatelyRecodeByOpenid(openid);
			if(signinStatistics!=null){
				//判断当前日期与最新签到日期相隔天数
				int lastsignandnowday = DateHelper.daysBetween(signinStatistics.getUpdatetime(),new Date());
				if(lastsignandnowday > 1){
					weixinUser.setAllSignnum(0);
				}else{
					weixinUser.setAllSignnum(signinStatistics.getSignnum());
				}
			}
			
			weixinUserResult.setData(weixinUser);
		} catch (Exception e) {
			weixinUserResult.setMsg(e.getMessage());
			weixinUserResult.setStatus("error");
		}
		
		return weixinUserResult;
		
//		return weixinUserService.getWxuserInfo(session,brandCode);
	}
	
	/**
     * 查询会员的所有下级
     * **/
    @RequestMapping("merriplusApi/getWxMemberAllys")
    @ResponseBody
    public JsonResult<ArrayList<WeixinUser>> getWxMemberAllys(HttpSession session,Integer pageNo,Integer pageSize) {
    	String openid = (String)session.getAttribute("openid");
    	return weixinUserService.getMemberAllys(openid,pageNo,pageSize);
    }
}
