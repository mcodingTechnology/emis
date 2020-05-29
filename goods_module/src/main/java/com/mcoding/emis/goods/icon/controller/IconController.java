package com.mcoding.emis.goods.icon.controller;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.icon.bean.Icon;
import com.mcoding.emis.goods.icon.service.IconService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class IconController {

	@Autowired
	protected IconService iconService;
	
	/**
	 * 转到级别列表的页面
	 * @return
	 */
	@RequestMapping("iconListView.html")
	public ModelAndView levelListView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("icon/iconList");
		return mav;
	}
	
	/**
	 * 查询所有的级别
	 * @return
	 */
	@RequestMapping("/icon/getIconList")
    @ResponseBody
	public PageView<Icon> getMemeberLevelList(String iDisplayStart, String iDisplayLength,String iconCode){
		return this.iconService.queryObjByPage(iDisplayStart, iDisplayLength);
	}
	
	/** 
     * 跳转添加图标的页面
     * @return 
     * @author Benson 
   */ 
   @RequestMapping("/icon/addIconView.html")
   public ModelAndView addOrEditIconView(String iconId) {
	   ModelAndView mav = new ModelAndView();
       if(StringUtils.isNotBlank(iconId)){
    	   CommonResult<Icon> icon = this.iconService.queryObjById(iconId);
    	   mav.addObject("icon", icon.getData());
	       mav.addObject("edit","edit");
       }
	   mav.setViewName("icon/addIcon");
       return mav;
   }
   
   /** 
    * 添加图片
    * @return 
    * @author icon 
  */ 
   @RequestMapping("/icon/addOrEditIcon")
   @ResponseBody
   public CommonResult<String> addOrEditBanner(Icon icon) {
	   if(icon!=null && icon.getId()!=null){
		   return this.iconService.modifyObj(icon);
	   }
	   icon.setCreateTime(new Date());
	   return this.iconService.addObj(icon);
   }
   
   
	
	/**
	 * 删除
	 * @param iconId
	 * @return
	 */
	@RequestMapping("/icon/deleteIcon")
    @ResponseBody
	public JsonResult<String> deleteBanner(int iconId){
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		try {
			this.iconService.deleteObjById(String.valueOf(iconId));
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");	
			jsonResult.setData("ok");
			
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
}
