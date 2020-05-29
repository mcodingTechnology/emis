package com.mcoding.emis.goods.sign.service;

import java.util.HashMap;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.sign.bean.SigninLog;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;

public interface SignStatisticsService  extends BaseService<SigninStatistics, String> {
	
	//根据openid查询 “最近日期”的会员签到统计记录
	SigninStatistics getLatelyRecodeByOpenid(String openid);
	
	//会员签到
	public JsonResult<SigninStatistics> memberSignin(String openid,String brandCode);
	
	//会员签到统计查询
//	public PageView<SigninStatistics> queryMemberSigninData(String iDisplayStart, String iDisplayLength,
//    		String sSearch,HttpServletRequest request,String pageNo);
	public PageView<SigninStatistics> queryMemberSigninData(String iDisplayStart, String iDisplayLength, String sSearch,
			String openid, String signnum,String memberName);

	//判断签到后能不能获奖
	JsonResult<HashMap<String, Object>> afterSignCanGetAward(String openid,
			String brandCode,Integer gameid) throws Exception;

	//补签签到记录和会员签到积分记录
	JsonResult<SigninStatistics> retroactiveRecord(SigninLog signinLog);

}
