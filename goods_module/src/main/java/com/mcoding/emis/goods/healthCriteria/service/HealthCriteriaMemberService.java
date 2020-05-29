package com.mcoding.emis.goods.healthCriteria.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaMember;
import com.mcoding.emis.goods.healthCriteria.bean.HealthResultResp;
import com.mcoding.emis.member.common.CommonResult;

public interface HealthCriteriaMemberService extends BaseService<HealthCriteriaMember, String> {

	public CommonResult<ArrayList<HealthCriteriaMember>> getData(String phone);

	/*** 获取所有 */
	public PageView<HealthCriteriaMember> queryHealthCriteriaMemberData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);

	/*** 检测结果添加页面 */
	public ModelAndView addHealthCriteriaMemberView(String id);

	/** 根据手机号码查询检测结果 *//*
						 * public HealthCriteriaMember queryResultByPhone(String
						 * phoneNo);
						 */

	public PageView<HealthCriteriaMember> queryResultByPhoneByPage(String iDisplayStart, String iDisplayLength,
			String phone);

	/**
	 * 根据id查询检测记录
	 * 
	 * @param id
	 * @return
	 */
//	public CommonResult<HealthResultResp> queryResultById(int id);

	/**
	 * (前端使用)添加用户发样检测记录，-1为添加失败，0为添加成功
	 * 
	 * @param hcMem
	 * @return
	 */
//	public int addHealthCriteriaMemberInfor(String openid, String memberName, String mobilePhone);

	/**
	 * 获取发样检测状态，0为正在进行中，1为已经完成
	 * 
	 * @return
	 */
	public int queryHealthCriteriaLastResultStatus(String openid, String mobilePhone);
}
