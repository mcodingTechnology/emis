package com.mcoding.emis.goods.wechat.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.securityQrcode.service.SecurityQrcodeService;
import com.mcoding.emis.goods.wechat.service.WechatWebService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 微信前端H5网页控制器
 * 
 * @author Benson 2015年3月24日 下午10:35:29
 */

@Controller
public class WechatWebController {

	@Autowired
	private WechatWebService wechatWebService;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberPointService memberPointService;
	@Autowired
	protected SecurityQrcodeService securityQrcodeService;

	/**
	 * 跳转微信个人注册页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/wechatRegisterView")
	public ModelAndView toNavigateWechatRegisterView(String brandCode) {
		ModelAndView mav = new ModelAndView();
		if (brandCode.equals("MRMJ")) {
			mav.setViewName("frontPageView/wechat/register");
		} else {
			mav.setViewName("frontPageView/gymmax/wechat/register");
		}
		return mav;
	}

	/**
	 * 跳转微信个人资料页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/wechatPersonalDetailView")
	public ModelAndView wechatPersonalDetailView(String mobilePhone, String brandCode) {
//		return wechatWebService.wechatPersonalDetailView(mobilePhone, brandCode);
		ModelAndView mav = new ModelAndView();
		Member member = memberService.selectByPhoneAndBrandCode(mobilePhone,brandCode);
		mav.addObject("member", member);
		countPercent(member,mav,brandCode);//统计完成率
		
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		list1.add("40kg以下");
		for (int i = 40; i <= 100; i++) {
			list1.add(i+"kg");
		}
		list1.add("100kg以上");
		list2.add("100cm以下");
		for (int i = 100; i <= 200; i++) {
			list2.add(i+"cm");
		}
		list2.add("200cm以上");
		mav.addObject("list1", list1);
		mav.addObject("list2", list2);
		return mav;
	}
	
	public ModelAndView countPercent(Member member,ModelAndView mav,String brandCode){
		//统计完善资料完成率
		ArrayList<String> taglist = new ArrayList<String>();
		if(brandCode.equals("MRMJ")){
			if(StringHelper.isNotBlank(member.getConcerns())){
				taglist.add(member.getConcerns());
			}
			if(StringHelper.isNotBlank(member.getConcernsPerson())){
				taglist.add(member.getConcernsPerson());
			}
			if(StringHelper.isNotBlank(member.getPosition())){
				taglist.add(member.getPosition());
			}
			if(StringHelper.isNotBlank(member.getHealthProblem())){
				taglist.add(member.getHealthProblem());
			}
			mav.setViewName("frontPageView/wechat/editPersonalInfo");
		}else {
			if(StringHelper.isNotBlank(member.getConcerns())){
				taglist.add(member.getConcerns());
			}
			if(StringHelper.isNotBlank(member.getConcernsPerson())){
				taglist.add(member.getConcernsPerson());
			}
			if(StringHelper.isNotBlank(member.getExt1())){
				taglist.add(member.getExt1());
			}
			if(StringHelper.isNotBlank(member.getExt2())){
				taglist.add(member.getExt2());
			}
			mav.setViewName("frontPageView/gymmax/wechat/editPersonalInfo");
		}
		switch (taglist.size()) {
		case 0:
			mav.addObject("tagsPercent", 0);
			break;
		case 1:
			mav.addObject("tagsPercent", 25);
			break;
		case 2:
			mav.addObject("tagsPercent", 50);
			break;
		case 3:
			mav.addObject("tagsPercent", 75);
			break;
		case 4:
			mav.addObject("tagsPercent", 100);
			break;
		default:
			mav.addObject("tagsPercent", 0);
			break;
		}
		return mav;
	}

	/**
	 * 跳转微信个人中心页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/wechatPersonalCenterView")
	public ModelAndView wechatPersonalCenterView(HttpSession session, String openid, String brandCode) {
		return weixinUserService.wechatPersonalCenterView(session, openid, brandCode);
	}

	/**
	 * 跳转微信我要积分页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/wechatPointView")
	public ModelAndView wechatPointView(HttpServletRequest request, String SecurityQrcode, String brandCode) {
//		return wechatWebService.wechatPointView(request, SecurityQrcode, brandCode);
		String fullPath=BasePath.getFullPath(request);
		ModelAndView mav = new ModelAndView();
		try {
			Map<String, String> data = wechatWebService.getSignture(fullPath, brandCode);
			mav.addAllObjects(data);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		
		mav.addObject("SecurityQrcode",SecurityQrcode);
		//跳转极智构
		if(brandCode.equals("MRMJ")){
			mav.setViewName("frontPageView/wechat/integration");
		}else {
			mav.setViewName("frontPageView/gymmax/wechat/integration");
		}
		return mav;
		
	}

	/**
	 * 跳转微信积分明细页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 * @throws ParseException
	 */
	@RequestMapping("/front/wechatPointDetailView")
	public ModelAndView wechatPointDetailView(String mobilePhone, String brandCode) {
		ModelAndView mav = new ModelAndView();
		List<Product> productList = wechatWebService.wechatPointDetailView(mobilePhone, brandCode);
		mav.addObject("productPointList", productList);
		
		Member member = this.memberService.selectByPhoneAndBrandCode(mobilePhone, brandCode);
		mav.addObject("pointSum", member.getPointSum());
		
		if(brandCode.equals("MRMJ")){
			mav.setViewName("frontPageView/wechat/integrationDetails");
		}else {
			mav.setViewName("frontPageView/gymmax/wechat/integrationDetails");
		}
		return mav;
		
	}

	/**
	 * 跳转微信——完善个人资料页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/registerDetail")
	public ModelAndView registerDetail() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/wechat/registerDetail");
		return mav;
	}

	/**
	 * 跳转微信——完善资料成功页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/congratulation")
	public ModelAndView congratulation() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/wechat/congratulation");
		return mav;
	}

	/**
	 * 完善个人资料提交
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/registerDetailSubmit")
	@ResponseBody
	public CommonResult<String> registerDetailSubmit(HttpSession session, Member t) {
		return wechatWebService.registerDetailSubmit(session, t);
	}

	/**
	 * 微信完善资料——加载完善资料
	 */
	@RequestMapping("/front/loadRegisterDetail")
	@ResponseBody
	public CommonResult<Member> loadRegisterDetail(String mobilePhone, String brandCode) {
		return memberService.loadRegisterDetail(mobilePhone, brandCode);
	}

	/**
	 * 微信个人中心——注册会员
	 */
	@RequestMapping("/front/wechatRegister")
	@ResponseBody
	public CommonResult<Member> wechatRegister(HttpSession session, Member member, String id_SMSCode_input) {
		CommonResult<Member> result = new CommonResult<Member>();

		String smsCodeInSession = (String) session.getAttribute("SMSCode");
		if (StringUtils.isBlank(smsCodeInSession) || StringUtils.isBlank(id_SMSCode_input)
				|| !smsCodeInSession.equals(id_SMSCode_input)) {
			result.setCode(3);
			result.setData(null);
			result.setMsg("验证码错误，请重新输入");
			return result;
		}

		try {
			String openid = session.getAttribute("openid").toString();
			return memberService.registerAndEditMember(openid, member);

		} catch (Exception e) {
			result.setCode(1);
			result.setData(null);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 微信个人中心——发送短信
	 */
	@RequestMapping("/front/sendSMS")
	@ResponseBody
	public CommonResult<String> sendSMS(HttpSession session, String phone, String brandCode) {
		return wechatWebService.sendSMS(session, phone, brandCode);
	}

	/**
	 * 微信个人中心编辑——验证短信
	 */
	@RequestMapping("/front/checkSMSCode")
	@ResponseBody
	public CommonResult<String> checkSMSCode(HttpSession session, String SMSCode) {
		return wechatWebService.checkSMSCode(session, SMSCode);
	}

	/**
	 * 微信我要积分——防伪码校验与积分
	 */
	@RequestMapping("/front/wechatCodeAndPoint")
	@ResponseBody
	public JsonResult<Member> wechatCodeAndPoint(HttpSession session, String openid, String securityCode,
			String id_barCode_input, String mobilePhone, String brandCode) {
		JsonResult<Member> jsonResult = new JsonResult<Member>();

		if (StringUtils.isBlank(securityCode) && StringUtils.isBlank(id_barCode_input)) {
			// 防伪码为空，请重新输入防伪码
			jsonResult.setStatus("1");
			jsonResult.setMsg("检测不到防伪码，请进行扫码，或者手动输入防伪码");
			return jsonResult;
		}
		brandCode = (String)session.getAttribute("brandCode");

		String barCode = null;
		if (StringUtils.isNotBlank(securityCode) && StringUtils.isNotBlank(id_barCode_input)) {
			barCode = id_barCode_input;

		} else {
			// 1.先判断防伪码是否已积分
			MemberPoint memberPoint = this.memberPointService.selectByFakeCheckCode(securityCode);
			if (memberPoint != null) {
				jsonResult.setStatus("5");
				jsonResult.setData(null);
				jsonResult.setMsg("产品已积过分，不可重复积分");
				return jsonResult;
			}

			CommonResult<String> checkCode = null;
			try {
				checkCode = securityQrcodeService.checkQrcode(securityCode);// 校验防伪码
				
			} catch (Exception e) {
				jsonResult.setStatus("2");
				jsonResult.setData(null);
				jsonResult.setMsg("系统错误，请联系管理员");
				return jsonResult;
			}
			if (checkCode == null) {
				jsonResult.setStatus("2");
				jsonResult.setData(null);
				jsonResult.setMsg("系统错误，请联系管理员");
				return jsonResult;
			}

			if (checkCode.getMsg().equals("01")) {
				jsonResult.setStatus("1");
				jsonResult.setData(null);
				jsonResult.setMsg("<p>咦？好像有点不对劲。</p>您的产品是正品，但积分时出了问题。请您把产品的条形码（以数字69开头）、防伪码（含二维码和数字）各拍一张清晰的照片，传给微信客服，我们尽快为您解决。");
				return jsonResult;
			}

			if (checkCode.getMsg().equals("07")) {
				jsonResult.setStatus("7");
				jsonResult.setData(null);
				jsonResult.setMsg("防伪码错误，请重新输入");
				return jsonResult;

			} else if (checkCode.getMsg().equals("06")) {
				jsonResult.setStatus("6");
				jsonResult.setData(null);
				jsonResult.setMsg("防伪码查询失败，请重新扫码或刷新页面");
				return jsonResult;

			} else if (!checkCode.getMsg().equals("00")) {
				jsonResult.setStatus("2");
				jsonResult.setData(null);
				jsonResult.setMsg("系统错误，请联系管理员");
				return jsonResult;
			}
			
			barCode = checkCode.getData();
		}
		
		if (StringUtils.isBlank(mobilePhone)) {
			/*if (StringUtils.isBlank(openid) && session.getAttribute("openid") == null) {
				jsonResult.setStatus("1");
				jsonResult.setMsg("找不到会员信息，无法积分，请输入电话号码");
				return jsonResult;
			}*/
			openid = (String) session.getAttribute("openid");

			Member member = memberService.queryMemberByOpenid(openid, brandCode);// 查询openid对应的会员
			mobilePhone = member.getMobilePhone();// 获取会员手机号码
		}else{
			//2.再判断手机是否已注册
			Member member = this.memberService.selectByPhoneAndBrandCode(mobilePhone, brandCode);
			if(member==null){ //未注册
//				memberService.memberRegister(mobilePhone,brandCode);//以手机号码注册
				String openId = (String) session.getAttribute("openid");
				
				Member tmp = new Member();
				tmp.setMobilePhone(mobilePhone);
				memberService.registerAndEditMember(openId, tmp);
			}
		}
		
		try {
			return this.securityQrcodeService.addPointFromQrcode(barCode, brandCode, mobilePhone, securityCode);
		} catch (Exception e) {
			jsonResult.setStatus("2");
			jsonResult.setData(null);
			jsonResult.setMsg("网络信号不好或出现异常情况，请刷新或关闭界面，重新打开");
			return jsonResult;
		}

	}

	/**
	 * 微信我要积分——修改个人资料
	 */
	@RequestMapping("/front/wechatEditPersonalInfo")
	@ResponseBody
	public CommonResult<String> wechatEditPersonalInfo(Member member) {
		return wechatWebService.wechatEditPersonalInfo(member);
	}

	/**
	 * 微信我要积分——修改个人资料与完善资料
	 */
	@RequestMapping("/front/wechatEditPersonalInfoAndRegisterDetail")
	@ResponseBody
	public CommonResult<String> wechatEditPersonalInfoAndRegisterDetail(HttpSession session, Member member) {
		return wechatWebService.wechatEditPersonalInfoAndRegisterDetail(session, member);
	}

	/**
	 * BIG生活——跳转微信完善个人资料页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/GMXWRegisterDetail")
	public ModelAndView GMXWRegisterDetail() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/gymmax/wechat/registerDetail");
		return mav;
	}

	/**
	 * BIG生活——跳转微信完善资料成功页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/GMXCongratulation")
	public ModelAndView GMXCongratulation() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/gymmax/wechat/congratulation");
		return mav;
	}

	/**
	 * 跳转品牌区分提示页面
	 * 
	 * @return ModelAndView
	 * @author Benson
	 */
	@RequestMapping("/front/brandTipView")
	public ModelAndView brandTipView(String brandCode) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("brandCode", brandCode);
		mav.setViewName("frontPageView/gymmax/wechat/brandTip");
		return mav;
	}

}
