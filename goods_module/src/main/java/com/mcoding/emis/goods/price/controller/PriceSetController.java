package com.mcoding.emis.goods.price.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.price.bean.PriceSet;
import com.mcoding.emis.goods.price.service.PriceSetService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class PriceSetController {
	@Autowired
	protected PriceSetService priceSetService;

	/**
	 * 转到级别列表的页面
	 * 
	 * @return
	 */
	@RequestMapping("priceSetListView.html")
	public ModelAndView levelListView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("price/priceSetList");
		return mav;
	}

	/**
	 * 查询所有的级别
	 * 
	 * @return
	 */
	@RequestMapping("/price/getPriceSetList")
	@ResponseBody
	public PageView<PriceSet> getMemeberLevelList(String iDisplayStart, String iDisplayLength) {
		return this.priceSetService.queryObjByPage(iDisplayStart, iDisplayLength);
	}

	/**
	 * 跳转添加页面
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/price/addPriceSetView.html")
	public ModelAndView addOrEditPriceSetView(String priceSetId) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(priceSetId)) {
			CommonResult<PriceSet> priceSet = this.priceSetService.queryObjById(priceSetId);
			mav.addObject("priceSet", priceSet.getData());
			mav.addObject("edit", "edit");
		}
		mav.setViewName("price/addPriceSet");
		return mav;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @author
	 */
	@RequestMapping("/price/addOrEditPriceSet")
	@ResponseBody
	public CommonResult<String> addOrEditBanner(PriceSet priceSet) {
		if (priceSet != null && priceSet.getId() != null) {
			return this.priceSetService.modifyObj(priceSet);
		}
		priceSet.setCreateTime(new Date());
		return this.priceSetService.addObj(priceSet);
	}

	/**
	 * 删除
	 * 
	 * @param priceSetId
	 * @return
	 */
	@RequestMapping("/price/deletePriceSet")
	@ResponseBody
	public JsonResult<String> deleteBanner(int priceSetId) {
		JsonResult<String> jsonResult = new JsonResult<String>();

		try {
			this.priceSetService.deleteObjById(String.valueOf(priceSetId));
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