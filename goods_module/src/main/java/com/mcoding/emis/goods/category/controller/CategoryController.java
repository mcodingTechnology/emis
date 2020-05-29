package com.mcoding.emis.goods.category.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.category.bean.Category;
import com.mcoding.emis.goods.category.service.CategoryService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 防伪二维码管理
 * 
 * @author Benson
 */
@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 加载产品查找类型
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("queryCategoryByType")
	@ResponseBody
	public List<Category> queryCategoryByType(String type) {
		return categoryService.queryCategoryByType(type);
	}

	/**
	 * 跳转产品类目的页面
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/categoryList.html")
	public ModelAndView navigateProductPointManagerView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("category/categoryList");
		return mav;
	}

	/**
	 * 产品类目数据列表查询
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param sSearch
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/queryCategoryData")
	@ResponseBody
	public PageView<Category> queryCategoryData(String iDisplayStart, String iDisplayLength, String sSearch) {
		return categoryService.queryCategoryData(iDisplayStart, iDisplayLength, sSearch);
	}

	/**
	 * 跳转添加产品类目的页面
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/addCategoryView.html")
	public ModelAndView navigateAddCategoryManagerView(String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			Category category = categoryService.queryObjById(id).getData();
			mav.addObject("category", category);
		}
		mav.setViewName("category/addCategory");
		return mav;
	}

	/**
	 * 添加产品类目
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/addCategory")
	@ResponseBody
	public CommonResult<String> addCategory(Category category) {
		return categoryService.addObj(category);
	}

}
