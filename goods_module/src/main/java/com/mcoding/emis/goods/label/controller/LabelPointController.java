package com.mcoding.emis.goods.label.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.label.entity.LabelEntity;
import com.mcoding.emis.goods.label.service.LabelPointService;
import com.mcoding.emis.member.common.CommonResult;


@Controller
public class LabelPointController {
	
	
	public Logger log = LoggerFactory.getLogger(LabelPointController.class);
	
	@Resource
	public LabelPointService labelPointService;
	
	
	
	
	/**
	 * 查询标签
	 * @param request
	 * @return
	 */
	 @RequestMapping(value = "/queryLabelPoinListByPage")
	 @ResponseBody
	public  PageView<LabelEntity>queryLabelPoinListByPage(HttpServletRequest request,String iDisplayStart,String iDisplayLength){
		return labelPointService.queryLabelPointDataByPage(request, iDisplayStart, iDisplayLength);
		
	}
	 
	   /**
	    * 添加
	    * @param req
	    * @return
	    */
	   @RequestMapping(value = "/addLabel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	   @ResponseBody
	 public CommonResult<String> addLabel(@RequestBody LabelEntity req) {
		   CommonResult<String> commonResult = new CommonResult<>();
		   try {
			    labelPointService.addLabel(req);
				commonResult.setCode(0);
				commonResult.setMsg("success");
			} catch (Exception e) {
				commonResult.setCode(-1);
				commonResult.setMsg("fail: " + e.getMessage());
				e.printStackTrace();
			}
			return commonResult;
	 }
	   
	   @ResponseBody
		@RequestMapping(value = "/updateLabel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		public CommonResult<String> updateLabel(@RequestBody LabelEntity req) {
			return labelPointService.updateLabel(req);
		}
	   
	    @ResponseBody
		@RequestMapping(value = "/deleteLabel")
		public CommonResult<String> deleteLabel(int tagsId) {
			return labelPointService.deleteLabelById(tagsId);
		}
	   
	    @ResponseBody
		@RequestMapping(value = "/addUserTags")
	    public CommonResult<String> addUserTags(Integer productId[],String fullName,String openid , int memberId) {
	    	return labelPointService.addUserTags(productId,fullName,openid,memberId);
		}
	   
}
