package com.mcoding.emis.goods.sign.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;
import com.mcoding.emis.goods.sign.service.SignStatisticsService;

@Controller
public class SignStatisticsController {
	@Autowired
    private SignStatisticsService signStatisticsService;
	
	/**
     * 会员签到
     * @param brandCode
     * @return
     */
    @RequestMapping("/merriplusApi/memberSignin")
    @ResponseBody
    public JsonResult<SigninStatistics> memberSignin(HttpSession session,String brandCode) {
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		
		String brandCodeInSession = (String) session.getAttribute("brandCode");
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}
		
    	return signStatisticsService.memberSignin(openid,brandCode);
    }
	
	/**
     * 会员签到后判断是否连续签到一定天数获奖
     * @param brandCode
     * @return
     */
    @RequestMapping("/merriplusApi/afterSignCanGetAward")
    @ResponseBody
    public JsonResult<HashMap<String, Object>> afterSignCanGetAward(HttpSession session,String brandCode,Integer gameid) throws Exception {
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		String brandCodeInSession = (String) session.getAttribute("brandCode");
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}
		
		return signStatisticsService.afterSignCanGetAward(openid, brandCode, gameid);
    }
    
    /** 
     * 后台会员管理——会员签到统计列表查询
     * @return 
     * @author Benson 
    */ 
    @RequestMapping("/memberSigninPageView")
    public ModelAndView memberSigninPageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("signin/signinList");
	    return mav;
    }
	   
   /** 
	  * 后台会员管理——会员签到统计查询
	  * @return 
	  * @author Benson 
	*/ 
    @RequestMapping("/queryMemberSigninData")
    @ResponseBody
    public PageView<SigninStatistics> queryMemberSigninData(String iDisplayStart, String iDisplayLength,
    		String sSearch,HttpServletRequest request,String pageNo) {
    	String openid = request.getParameter("openid");
    	String signnum = request.getParameter("signnum");
    	String memberName = request.getParameter("memberName");

        return signStatisticsService.queryMemberSigninData(iDisplayStart, iDisplayLength,
        		sSearch,openid,signnum,memberName);
    }
}
