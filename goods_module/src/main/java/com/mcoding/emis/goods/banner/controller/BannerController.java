package com.mcoding.emis.goods.banner.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.banner.bean.Banner;
import com.mcoding.emis.goods.banner.service.BannerService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class BannerController {

	@Autowired
	protected BannerService bannerService;
	
	/**
	 * 转到级别列表的页面
	 * @return
	 */
	@RequestMapping("bannerListView.html")
	public ModelAndView levelListView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("banner/bannerList");
		return mav;
	}
	
	/**
	 * 查询所有的级别
	 * @return
	 */
	@RequestMapping("/banner/getBannerList")
    @ResponseBody
	public PageView<Banner> getMemeberLevelList(String iDisplayStart, String iDisplayLength){
		return this.bannerService.queryObjByPage(iDisplayStart, iDisplayLength);
	}
	
	/** 
     * 跳转添加产品广告的页面
     * @return 
     * @author Benson 
   */ 
   @RequestMapping("/banner/addBannerView.html")
   public ModelAndView addOrEditBannerView(String bannerId) {
	   ModelAndView mav = new ModelAndView();
       if(StringUtils.isNotBlank(bannerId)){
    	   CommonResult<Banner> banner = this.bannerService.queryObjById(bannerId);
    	   mav.addObject("banner", banner.getData());
	       mav.addObject("edit","edit");
       }
	   mav.setViewName("banner/addBanner");
       return mav;
   }
   
   /** 
    * 添加产品
    * @param productPoint
    * @return 
    * @author Benson 
  */ 
   @RequestMapping("/banner/addOrEditBanner")
   @ResponseBody
   public CommonResult<String> addOrEditBanner(Banner banner) {
	   if(banner!=null && banner.getId()!=null){
		   return this.bannerService.modifyObj(banner);
	   }
	   banner.setCreatetime(new Date());
	   return this.bannerService.addObj(banner);
   }
   
   
	
	/**
	 * 增加
	 * @param banner
	 * @return
	 */
	@RequestMapping("/banner/createBanner")
    @ResponseBody
	public JsonResult<String> createBanner(@RequestBody Banner banner){
		JsonResult<String> jsonResult = new JsonResult<String>();
		if(banner == null){
			
			jsonResult.setStatus("error");
			jsonResult.setMsg("参数不能为空");
			return jsonResult;
		}
		
		if(banner.getBrandcode() == null || banner.getImg()==null || 
				banner.getLink() == null || banner.getOrderno() == null){
			
			jsonResult.setStatus("01");
			jsonResult.setMsg("参数不能为空");
			return jsonResult;
		}
		
		try {
			CommonResult<String> result = this.bannerService.addObj(banner);
			
			if(result.getCode() ==0){
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");	
				jsonResult.setData(result.getData());
			}else{
				jsonResult.setStatus("01");
				jsonResult.setMsg("操作失败");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 启用/禁用 
	 * @param bannerId
	 * @param isValid
	 * @return
	 */
	@RequestMapping("/banner/enableBanner")
    @ResponseBody
	public JsonResult<String> enableBanner(int bannerId, int isValid){
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		if(isValid != Banner.IS_VALID_INTEGER_NO && isValid != Banner.IS_VALID_INTEGER_YES){
			jsonResult.setStatus("01");
			jsonResult.setMsg("参数异常");
			return jsonResult;
		}
		
		Banner banner = new Banner();
		banner.setId(bannerId);
		banner.setIsvalid(isValid);
		
		try {
			this.bannerService.modifyObj(banner);
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
	
	/**
	 * 删除
	 * @param bannerId
	 * @return
	 */
	@RequestMapping("/banner/deleteBanner")
    @ResponseBody
	public JsonResult<String> deleteBanner(int bannerId){
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		try {
			this.bannerService.deleteObjById(String.valueOf(bannerId));
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
	
	@RequestMapping("/banner/updateBanner")
    @ResponseBody
	public JsonResult<String> updateBanner(@RequestBody Banner banner){
		JsonResult<String> jsonResult = new JsonResult<String>();
		if(banner.getId() ==null || banner.getId() <= 0 ){
			jsonResult.setStatus("error");
			jsonResult.setMsg("参数有误");
			return jsonResult;
		}
		
		try {
			CommonResult<String> result = this.bannerService.modifyObj(banner);
			
			if(result.getCode() ==0){
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");					
			}else{
				jsonResult.setStatus("01");
				jsonResult.setMsg("操作失败");	
			}
			
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 分页查询所有的数据
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping("/banner/getBannerListByPage")
    @ResponseBody
	public PageView<Banner> getBannerListByPage(String iDisplayStart, String iDisplayLength){
		PageView<Banner> pageView = null;
		try {
        	pageView = this.bannerService.queryObjByPage(iDisplayStart, iDisplayLength);
		} catch (Exception e) {
			pageView = new PageView<Banner>(iDisplayStart, iDisplayLength);
			pageView.setQueryResult(null);
			e.printStackTrace();
		}
        return pageView;
	}
	
	/**
	 * 微信客户端查询
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/getBannerListForUser")
    @ResponseBody
	public JsonResult<List<Banner>> getBannerListForUser(HttpSession session, HttpServletRequest request,
			String brandCode,String malltype){
		JsonResult<List<Banner>> jsonResult = new JsonResult<List<Banner>>();
		String brandCodeInSession = (String) request.getSession().getAttribute("brandCode");
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}
		
		if (session.getAttribute("mallType") != null) {
			malltype = session.getAttribute("mallType").toString();
		}
		
		try {
			if(StringUtils.isBlank(brandCode)){
				//默认为极智构
				brandCode = (String) session.getAttribute("brandCode");
				if (StringUtils.isBlank(brandCode)) {
					throw new NullPointerException("brandCode can not be null");
				}
			}
			List<Banner> list = this.bannerService.queryAvailableBanner(brandCode,malltype);
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");	
			jsonResult.setData(list);
			
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
}
