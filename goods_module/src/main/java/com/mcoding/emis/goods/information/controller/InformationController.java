package com.mcoding.emis.goods.information.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.information.bean.Information;
import com.mcoding.emis.goods.information.bean.InformationExample;
import com.mcoding.emis.goods.information.service.InformationService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class InformationController {
	
	@Autowired
	protected InformationService informationService;

	@RequestMapping("/merriplusApi/getInformationForMemeber")
	@ResponseBody
	public JsonResult<List<Information>> getInformationForMemeber(Integer type, Integer id, HttpSession session) {
		JsonResult<List<Information>> result = new JsonResult<List<Information>>();

		String openid = (String) session.getAttribute("openid");
		try{
			InformationExample ex = new InformationExample();
			ex.setOrderByClause("readTime ASC, createTime DESC");
			InformationExample.Criteria cri = ex.createCriteria();
			cri.andReceiveropenidEqualTo(openid);
			
			if(type!=null && !type.equals(0)){
				cri.andTypeEqualTo(type);
			}
			
			if(id!=null){
				cri.andIdEqualTo(id);
			}
			CommonResult<ArrayList<Information>> informationList = this.informationService.queryListByExample(ex);
			
			result.setData(informationList.getData());
			result.setMsg("操作成功");
			result.setSize(informationList.getData().size());
			result.setStatus("00");
			
		}catch(Exception e) {
			result.setData(null);
			result.setMsg(e.getMessage());
			result.setStatus("error");
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("/merriplusApi/readInformation")
	@ResponseBody
	public JsonResult<String> readInformation(int informationId, HttpSession session) {
		JsonResult<String> result = new JsonResult<String>();
		String openid = (String) session.getAttribute("openid");
		
		try{
			this.informationService.setInformationReaded(informationId, openid);
			result.setData("ok");
			result.setMsg("操作成功");
			result.setSize(0);
			result.setStatus("00");
		}catch(Exception e){
			result.setData(null);
			result.setMsg(e.getMessage());
			result.setStatus("error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 是否有新消息
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/hasNewInformation")
	@ResponseBody
	public JsonResult<Boolean> hasNewInformation(String type, HttpSession session) {
		JsonResult<Boolean> result = new JsonResult<Boolean>();
		String openid = (String) session.getAttribute("openid");
		
		try{
			if(StringHelper.isNotBlank(openid)){
				boolean hasNewInformations = this.informationService.findNewInformations(openid);
			    result.setData(hasNewInformations);
				result.setMsg("操作成功");
				result.setSize(0);
				result.setStatus("00");
			}else {
				result.setData(null);
				result.setMsg("openid为空");
				result.setStatus("01");
			}
		}catch(Exception e){
			result.setData(null);
			result.setMsg(e.getMessage());
			result.setStatus("error");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 是否有新消息
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/getNewInformation")
	@ResponseBody
	public JsonResult<List<Information>> getNewInformation(Integer type, HttpSession session) {
		JsonResult<List<Information>> result = new JsonResult<List<Information>>();
		String openid = (String) session.getAttribute("openid");
		
		try{
		    InformationExample ex = new InformationExample();
		    InformationExample.Criteria cri = ex.createCriteria();
		    
		    cri.andStatusEqualTo(Information.STATUS_UNREADED);
		    cri.andReceiveropenidEqualTo(openid);
		    if(type!=null && !type.equals(0)){
		    	cri.andTypeEqualTo(type);
		    }
			
		    CommonResult<ArrayList<Information>> list = this.informationService.queryListByExample(ex);
		    result.setData(list.getData());
			result.setMsg("操作成功");
			result.setSize(0);
			result.setStatus("00");
			
		}catch(Exception e){
			result.setData(null);
			result.setMsg(e.getMessage());
			result.setStatus("error");
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("toInformationListView")
	public ModelAndView toInformationList(){
		return new ModelAndView("information/informationList");
	}
	
	@RequestMapping("toAddInformationView")
	public ModelAndView	toAddInformationView(Integer id) {
		ModelAndView view = new ModelAndView("information/addInformation");
		if (id != null && id !=0) {
			CommonResult<Information> information = this.informationService.queryObjById(id.toString());
			view.addObject("information", information.getData());
		}
		
		return view;
	}
	
	/***
	 * 添加公告消息
	 * @param information
	 * @return
	 */
	@RequestMapping("addNewPublicInformation")
	@ResponseBody
	public JsonResult<String> addNewPublicInformation(@RequestBody Information information){
		JsonResult<String> result = new JsonResult<>();
		if (StringUtils.isBlank(information.getTitle())) {
			result.setStatus("error");
			result.setMsg("标题不能为空");
			return result;
		}
		
		if (StringUtils.isBlank(information.getContent())) {
			result.setStatus("error");
			result.setMsg("内容不能为空");
			return result;
		}
		
		information.setSender("system");
		information.setType(Information.INFORMATION_TYPE_PUBLIC);
		information.setStatus(Information.STATUS_UNREADED);
		
		if (information.getId() != null && information.getId()!=0) {
			this.informationService.modifyObj(information);
		}else{
			this.informationService.addObj(information);
		}
		
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}
	
	/**
	 * 获取公告消息
	 * @return
	 */
	@RequestMapping("merriplusApi/getPublicInformation")
	@ResponseBody
	public JsonResult<List<Information>> getPublicInformation(){
		InformationExample example = new InformationExample();
		example.createCriteria()
		       .andTypeEqualTo(Information.INFORMATION_TYPE_PUBLIC)
		       .andStatusEqualTo(Information.STATUS_UNREADED);
		
		CommonResult<ArrayList<Information>> list = this.informationService.queryListByExample(example);
		JsonResult<List<Information>> result = new JsonResult<>();
		result.setStatus("00");
		result.setData(list.getData());
		result.setMsg("ok");
		return result;
	}
}
