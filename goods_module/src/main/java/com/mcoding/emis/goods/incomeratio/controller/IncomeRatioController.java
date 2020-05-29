package com.mcoding.emis.goods.incomeratio.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.incomeratio.bean.IncomeRatio;
import com.mcoding.emis.goods.incomeratio.service.IncomeRatioService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class IncomeRatioController {
	@Autowired
	protected IncomeRatioService incomeRatioService;

	/**
	 * 转到级别列表的页面
	 * 
	 * @return
	 */
	@RequestMapping("incomeRatioListView.html")
	public ModelAndView levelListView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("income/incomeRatioList");
		return mav;
	}

	/**
	 * 查询所有的级别
	 * 
	 * @return
	 */
	@RequestMapping("/income/getIncomeRatioList")
	@ResponseBody
	public PageView<IncomeRatio> getMemeberLevelList(String iDisplayStart, String iDisplayLength) {
		return this.incomeRatioService.queryObjByPage(iDisplayStart, iDisplayLength);
	}

	/**
	 * 跳转添加页面
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/income/addIncomeRatio.html")
	public ModelAndView addOrEditIncomeRatioView(String incomeRatioId) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(incomeRatioId)) {
			CommonResult<IncomeRatio> IncomeRatio = this.incomeRatioService.queryObjById(incomeRatioId);
			mav.addObject("IncomeRatio", IncomeRatio.getData());
			mav.addObject("edit", "edit");
		}
		mav.setViewName("income/addIncomeRatio");
		return mav;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @author
	 */
	@RequestMapping("/income/addOrEditIncomeRatio")
	@ResponseBody
	public CommonResult<String> addOrEditBanner(IncomeRatio incomeRatio) {
		if (incomeRatio != null && incomeRatio.getId() != null) {
			return this.incomeRatioService.modifyObj(incomeRatio);
		}
		incomeRatio.setCreateTime(new Date());
		return this.incomeRatioService.addObj(incomeRatio);
	}

	/**
	 * 删除
	 * 
	 * @param incomeRatioId
	 * @return
	 */
	@RequestMapping("/income/deleteIncomeRatio")
	@ResponseBody
	public JsonResult<String> deleteBanner(int incomeRatioId) {
		JsonResult<String> jsonResult = new JsonResult<String>();

		try {
			this.incomeRatioService.deleteObjById(String.valueOf(incomeRatioId));
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			jsonResult.setData("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}

		return jsonResult;
	}
}