package com.mcoding.emis.goods.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.bean.member.MemberLevelExample;
import com.mcoding.emis.member.service.member.MemberLevelService;

@Controller("emisMemberLevelController")
public class MemberLevelController {
	@Autowired
    private MemberLevelService memberLevelService;
	
	/**
	 * 转到级别列表的页面
	 * @return
	 */
	@RequestMapping("/memberLevel/levelListView")
	public ModelAndView levelListView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberLevel/memberLevelList");
		return mav;
	}
	
	/**
	 * 查询所有的级别
	 * @return
	 */
	@RequestMapping("/memberLevel/getMemeberLevelList")
    @ResponseBody
	public PageView<MemberLevel> getMemeberLevelList(String iDisplayStart, String iDisplayLength){
//		JsonResult<List<MemeberLevel>> jsonResult = new JsonResult<List<MemeberLevel>>();
//		try {
//			CommonResult<ArrayList<MemeberLevel>> result = memberLevelService.queryListAllObj();
//			jsonResult.setData(result.getData());
//			jsonResult.setSize(result.getData().size());
//			
//			jsonResult.setStatus("00");
//			jsonResult.setMsg("操作成功");
//		} catch (Exception e) {
//			jsonResult.setStatus("error");
//			jsonResult.setMsg(e.getMessage());
//		}
		MemberLevelExample example = new MemberLevelExample();
		PageView<MemberLevel> pageview = new PageView<>(iDisplayStart, iDisplayLength);
		example.setPageView(pageview);
		
		return this.memberLevelService.queryObjByPage(example);
		
//		return this.memberLevelService.queryObjByPage(iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 获取级别详细信息
	 * @param levelId
	 * @return
	 */
	@RequestMapping("/memberLevel/getLevelInfoByLevelId")
    @ResponseBody
	public JsonResult<MemberLevel> getLevelInfoByLevelId(int levelId){
		JsonResult<MemberLevel> jsonResult = new JsonResult<MemberLevel>();
		if(levelId <= 0 ){
			
			jsonResult.setStatus("error");
			jsonResult.setMsg("参数有误");
			return jsonResult;
		}
		
		try {
			MemberLevel result = memberLevelService.queryObjById(levelId);
			jsonResult.setData(result);
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 创建级别
	 * @param memeberLevel
	 * @return
	 */
	@RequestMapping("/memberLevel/createLevel")
    @ResponseBody
	public JsonResult<MemberLevel> createLevel(@RequestBody MemberLevel memeberLevel){
		JsonResult<MemberLevel> jsonResult = new JsonResult<MemberLevel>();
		if(memeberLevel == null){
			
			jsonResult.setStatus("error");
			jsonResult.setMsg("参数不能为空");
			return jsonResult;
		}
		
		if(memeberLevel.getName() == null || memeberLevel.getName().trim().length() == 0){
			jsonResult.setStatus("01");
			
			jsonResult.setMsg("级别名称不能为空");
			return jsonResult;
		}
		
		if(memeberLevel.getPriority() == null){
			jsonResult.setStatus("01");
			
			jsonResult.setMsg("级别优先级不能为空");
			return jsonResult;
		}
		
		try {
			memberLevelService.addObj(memeberLevel);
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 更新级别
	 * @param memeberLevel
	 * @return
	 */
	@RequestMapping("/memberLevel/updateLevel")
    @ResponseBody
	public JsonResult<MemberLevel> updateLevel(@RequestBody MemberLevel memeberLevel){
		JsonResult<MemberLevel> jsonResult = new JsonResult<MemberLevel>();
		if(memeberLevel.getId() ==null || memeberLevel.getId() <= 0 ){
			jsonResult.setStatus("error");
			jsonResult.setMsg("参数有误");
			return jsonResult;
		}
		
		try {
			memberLevelService.modifyObj(memeberLevel);
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 删除级别（软删除）
	 * @param memeberLevel
	 * @return
	 */
	@RequestMapping("/memberLevel/deleteLevel")
    @ResponseBody
	public JsonResult<MemberLevel> deleteLevel(int levelId){
		JsonResult<MemberLevel> jsonResult = new JsonResult<MemberLevel>();
		if(levelId <= 0 ){
			jsonResult.setStatus("01");
			jsonResult.setStatus("error");
			jsonResult.setMsg("参数有误");
			return jsonResult;
		}
		
		try {
			memberLevelService.deleteObjById(levelId);
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	
}
