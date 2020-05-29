package com.mcoding.emis.goods.label.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.emis.goods.label.entity.LabelEntity;
import com.mcoding.emis.goods.label.entity.UserTagsEntity;
import com.mcoding.emis.goods.label.service.LabelPointService;
import com.mcoding.emis.member.bean.member.Member;


@Controller
public class LabelModelViewPointController {

	@Resource
	public LabelPointService labelPointService;
	
	//标签列表 地址
	@RequestMapping(value = "/labelPointList.html")
	public ModelAndView labelPointList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("label/labelPointList");
		return mav;
	}
	
	@RequestMapping(value = "/addLabel.html")
	public ModelAndView addLabel() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("label/addLabel");
		return mav;
	}
	
	
	@RequestMapping(value = "/updateLabelPageView.html")
	public ModelAndView updateLabelPage(int tagsId) {
		ModelAndView mav = new ModelAndView();
		LabelEntity label = labelPointService.selectLabelById(tagsId).getData();
		
		
		mav.addObject("label", label);
		mav.setViewName("label/updateLabel");
		
		return mav;
	}
	
	
	//查询会员id
	@RequestMapping(value = "/userTagsList.html")
	public ModelAndView userTagsList(int memberId) {
		ModelAndView mav = new ModelAndView();
		Member label = labelPointService.UserTagsEntityById(memberId).getData(); //查询要贴标签的会员
		ArrayList<UserTagsEntity> labe2 = labelPointService.selectUserTagsById(memberId).getData();//根据会员id查询 贴过的标签
		
		mav.addObject("label", label);
		mav.addObject("labe2", labe2);
		mav.setViewName("label/userTagsList");
		return mav;
	}
}
