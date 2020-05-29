package com.mcoding.emis.goods.income.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.service.ChannelDealerService;
import com.mcoding.emis.goods.label.controller.LabelPointController;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class ChannelDealerController {
	public Logger log = LoggerFactory.getLogger(LabelPointController.class);
	
	@Resource
	public ChannelDealerService channelDealerService;  
	
	
	
	 /** 
     * 跳转到渠道商管理页面
     * @return 
     * @author Benson 
     */ 
   @RequestMapping("/ChanneldealerPageView.html")
   public ModelAndView ChanneldealerPageView() {
       ModelAndView mav = new ModelAndView();
       mav.setViewName("channel/channelDealerList");
       return mav;
   }
   /**
    * 跳转到添加渠道商jsp页面
    * @return
    */
   @RequestMapping(value = "/addchannel.html")
	public ModelAndView addchannel(HttpServletRequest request,String iDisplayStart,String iDisplayLength) {
		ModelAndView mav = new ModelAndView();
		/*PageView<Member>labe2=channelDealerService.queryMemberListByPage(request,iDisplayStart,iDisplayLength); //查询会员
		mav.addObject("labe2", labe2);*/
		mav.setViewName("channel/addchannel");
		return mav;
	}
   
   /**
    * 根据id查询渠道商 
    * @param tagsId
    * @return
    */
   @RequestMapping(value = "/updatechannel.html")
	public ModelAndView updatechannel(int tagsId) {
		ModelAndView mav = new ModelAndView();
		ChannelDealer label = channelDealerService.selectChannelById(tagsId).getData();
		mav.addObject("label", label);
		mav.setViewName("channel/updateChannel");
		
		return mav;
	}
   
	/**
	 * 分页查询渠道商
	 * @param request
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping(value = "/queryChanneldealerListByPage")
	@ResponseBody
	public  PageView<ChannelDealer>queryChanneldealerListByPage(HttpServletRequest request,String iDisplayStart,String iDisplayLength){
		return channelDealerService.queryChanneldealerListByPage(request, iDisplayStart, iDisplayLength);
	}
	
	    /**
	    * 添加
	    * @param req
	    * @return
	    */
	   @ResponseBody
	   @RequestMapping(value = "/addchannel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	 public CommonResult<String> addchannel(@RequestBody ChannelDealer req) {
		   CommonResult<String> commonResult = new CommonResult<>();
		   try {
			   channelDealerService.addchannel(req);
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
	 @RequestMapping(value = "/updateChannel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	 public CommonResult<String> updateChannel(@RequestBody ChannelDealer req) {
	 		return channelDealerService.updateChannel(req);
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "/deleteChannel")
	 public CommonResult<String> deleteChannel(int tagsId) {
			return channelDealerService.deleteChannel(tagsId);
	 }
	 
		/**
		 * 分页查询会员
		 * @param request
		 * @param iDisplayStart
		 * @param iDisplayLength
		 * @return
		 */
		@RequestMapping(value = "/queryMemberListByPage")
		@ResponseBody
		public  PageView<Member>queryMemberListByPage(HttpServletRequest request,String iDisplayStart,String iDisplayLength){
			return channelDealerService.queryMemberListByPage(request, iDisplayStart, iDisplayLength);
		}
	 
}
