package com.mcoding.emis.goods.member.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.service.member.MemberAddressService;

/**
 * 用户收货地址管理
 * @author Administrator
 *
 */
@Controller("emisMemberAddress")
public class MemberAddressController {

	@Autowired
	private MemberAddressService memberAddressService;
	
    /** 
     * 后台会员管理——收货地址列表查询
     * @return 
     * @author Benson 
    */ 
    @RequestMapping("/memberAddressPageView")
    public ModelAndView memberListPageView(Integer mobilePhone) {
	    ModelAndView mav = new ModelAndView();
		mav.addObject("mobilePhone", mobilePhone);
	    mav.setViewName("member/memberAddressList");
	    return mav;
    }
	   
   /** 
	  * 后台会员管理——收货地址查询
	  * @return 
	  * @author Benson 
	*/ 
    @RequestMapping("/queryMemberAddressData")
    @ResponseBody
    public PageView<MemberAddress> queryMemberAddressData(String iDisplayStart, String iDisplayLength,
    		String sSearch,String mobilePhone,String pageNo) {
        return memberAddressService.queryMemberAddressData(iDisplayStart, iDisplayLength,
        		sSearch,mobilePhone,pageNo);
    }


	/**
	 * 获取收货地址
	 * @return
	 */
	@RequestMapping("/merriplusApi/getAddressInfo")
	@ResponseBody
	public JsonResult<MemberAddress> getAddressInfo(HttpSession session) {
//		String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return this.memberAddressService.queryByOpenId(openid);
	}

	/**
	 * 添加收货地址信息
	 * @param memberAddress
	 * @return
	 */
	@RequestMapping("/merriplusApi/addNewAddress")
	@ResponseBody
	public JsonResult<String> addNewAddress(@RequestBody MemberAddress memberAddress,HttpSession session) {

//		String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return this.memberAddressService.add(memberAddress,openid);
	}

	/**
	 * 修改收货地址信息
	 * @param memberAddress
	 * @return
	 */
	@RequestMapping("/merriplusApi/modifyAddress")
	@ResponseBody
	public JsonResult<String> modifyAddress(@RequestBody MemberAddress memberAddress,HttpSession session) {

//		String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return memberAddressService.update(memberAddress,openid);
	}

	/**
	 * 删除收货地址信息
	 * @return
	 */
	@RequestMapping("/merriplusApi/deleteAddress")
	@ResponseBody
	public JsonResult<String> deleteAddress(HttpSession session) {

//		String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		return memberAddressService.delete(openid);
	}

}
