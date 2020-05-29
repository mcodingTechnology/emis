package com.mcoding.emis.goods.healthCriteria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.goods.healthCriteria.service.HealthCriteriaService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 发样检测结果
 * 
 * @author Leiming
 *
 */
@Controller
public class HealthCriteriaController {

	@Autowired
	private HealthCriteriaService healthCriteriaService;

	/**
	 * 评分标准列表查询
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/healthCriteriaPageView.html")
	public ModelAndView healthCriteriaPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("healthCriteria/healthCriteriaList");
		return mav;
	}

	/**
	 * 列表查询
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/queryHealthCriteriaData")
	@ResponseBody
	public PageView<HealthCriteria> queryHealthCriteriaData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		return healthCriteriaService.queryHealthCriteriaData(request, iDisplayStart, iDisplayLength);
	}

	/**
	 * 列表查询
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/queryHealthCriteriaByIds")
	@ResponseBody
	public List<HealthCriteria> queryHealthCriteriaByIds(String ids) {
		return healthCriteriaService.queryHealthCriteriaByIds(ids);
	}

	/**
	 * 列表查询--微信接口
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/healthCriteria/queryHealthCriteriaByIds2")
	@ResponseBody
	public CommonResult<ArrayList<HealthCriteria>> queryHealthCriteriaByIds2(String ids) {
		return healthCriteriaService.queryHealthCriteriaByIds2(ids);
	}

	/**
	 * 跳转添加检测结果的页面
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/addHealthCriteriaView")
	public ModelAndView addHealthCriteriaView(String id) {
		return healthCriteriaService.addHealthCriteriaView(id);
	}

	/**
	 * 添加检测结果
	 * 
	 * @param HealthCriteria
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/addHealthCriteria")
	@ResponseBody
	public CommonResult<String> addHealthCriteria(HealthCriteria healthCriteria) {
		return healthCriteriaService.addObj(healthCriteria);
	}

	/**
	 * 根据id删除检测结果
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/deleteHealthCriteria")
	@ResponseBody
	public CommonResult<String> deletehealtHCriteria(String teplId) {
		return healthCriteriaService.deleteObjById(teplId);
	}
}
