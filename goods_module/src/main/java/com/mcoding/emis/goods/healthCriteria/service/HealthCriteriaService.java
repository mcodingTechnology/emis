package com.mcoding.emis.goods.healthCriteria.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 
 * @author Leiming
 *
 */
public interface HealthCriteriaService extends BaseService<HealthCriteria, String> {

	public CommonResult<ArrayList<HealthCriteria>> getData();

	/**发样检测评分标准列表查询*/
	public PageView<HealthCriteria> queryHealthCriteriaData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength);
	
	/**获取所有检测项目*/
	public List<HealthCriteria> queryHealthCriteriaList();

	/**发样检测添加评分标准*/
	public ModelAndView addHealthCriteriaView(String id);

	/**根据检测项目名称获取检测项目*/
	public List<HealthCriteria> queryResultByTestitem(
			String testitem);

	/**通过ids查询列表*/
	public List<HealthCriteria> queryHealthCriteriaByIds(String ids);

	/**通过ids查询列表--微信接口*/
	public CommonResult<ArrayList<HealthCriteria>> queryHealthCriteriaByIds2(
			String ids);

}
