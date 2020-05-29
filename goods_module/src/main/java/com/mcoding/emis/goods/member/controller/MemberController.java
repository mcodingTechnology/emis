package com.mcoding.emis.goods.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.member.web.controller.test.Test;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.sms.SmsManager;
import com.mcoding.comp.sms.utils.Supplier;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.common.utils.StringUtil;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.bean.GameMemberResultExample;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.bean.GamePrizeExample;
import com.mcoding.emis.goods.game.persistence.GameMemberResultMapper;
import com.mcoding.emis.goods.game.persistence.GamePrizeMapper;
import com.mcoding.emis.goods.securityQrcode.service.SecurityQrcodeService;
import com.mcoding.emis.goods.sms.service.SendSmsService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.bean.member.MemberLevelExample;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;
import com.mcoding.emis.member.service.member.EmisMemberService;
import com.mcoding.emis.member.service.member.MemberLevelService;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;
import com.mcoding.emis.member.utils.EmojiFilter;

/**
 * 防伪二维码管理
 *
 * @author Benson
 */
@Controller("emisMemberController")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberPointService memberPointService;

	@Autowired
	private MemberLevelService memberLevelService;
//
//	@Autowired
//	private ProductService productService;

	@Autowired
	protected WeixinUserService weixinUserService;

	@Autowired
	protected SecurityQrcodeService securityQrcodeService;

	@Autowired
	protected SendSmsService sendSmsService;

	@Autowired
	protected StoreWxRefService storeWxRefService;

	@Resource(name="emisMemberService")
	protected EmisMemberService memberFromEmisService;

	@Autowired
	protected WxRedpackSendRecordService wxRedpackSendRecordService;

	@Autowired
	protected GameMemberResultMapper gameMemberResultMapper;

	@Autowired
	protected GamePrizeMapper gamePrizeMapper;

	@Autowired
	protected MemberFromEmisMapper memberFromEmisMapper;

	private Logger log = Logger.getLogger(MemberController.class);

	/**
	 * 会员登陆
	 *
	 * @param SecurityQrcode
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/front/memberLogin")
	public ModelAndView toNavigateQrcodeView(String SecurityQrcode) {
		System.out.println(SecurityQrcode);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/member/memberLogin");
		return mav;
	}

	/**
	 * 微信个人中心---会员注册跳转页面
	 *
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/front/memberRegisterView")
	public ModelAndView toNavigateMemberRegisterView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/member/memberRegister");
		return mav;
	}

	/**
	 * 微信个人中心---会员注册
	 *
	 * @return CommResult<String>
	 * @author Benson
	 */
	@RequestMapping("/front/memberRegister")
	public CommonResult<String> memberRegister(Member member) {
		String openId = member.getOpenid();
		Member tmp = this.memberService.queryMemberByOpenid(openId);
		if (tmp == null) {
			return memberService.addObj(member);
		}

		member.setMemberId(tmp.getMemberId());
		memberService.updateByPrimaryKeySelective(member);

		CommonResult<String> r = new CommonResult<String>();
		r.setCode(0);
		r.setMsg("ok");
		r.setData("ok");
		return r;

	}

	/**
	 * 微信个人中心---查询会员资料跳转页面
	 *
	 * @return Member
	 * @author Benson
	 */
	@RequestMapping("/front/memberDetail")
	public ModelAndView toMemberDetailView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frontPageView/member/memberDetail");
		return mav;
	}

	/**
	 * 微信个人中心---查询会员资料
	 *
	 * @return Member
	 * @author Benson
	 */
	@RequestMapping("/merriplusApi/getMemberDetail")
	@ResponseBody
	public JsonResult<Member> getMemberDetail(HttpSession session, HttpServletRequest request) {
		JsonResult<Member> jsonResult = new JsonResult<Member>();

		Member member;
		String openId = (String) session.getAttribute("openid");

		if(StringHelper.isNotBlank(request.getParameter("openid")) && StringHelper.isBlank(openId)){
			session.setAttribute("openid",request.getParameter("openid"));
		}
		String id = request.getParameter("id");
		try {
			if (StringHelper.isNotBlank(id)) {
				member = memberService.queryObjById(id).getData();
			} else {
				member = memberService.queryMemberByOpenid(openId);
			}
			// 默认渠道ID为1
			Integer channelsId = 5;

			jsonResult.setStatus("00");
			if (member != null) {
				//查询这个人的上级信息
				//TODO 应该进行关联查询找出这个人的上级信息，此处简单操作再查询一次，后面需更正
				if (member.getParentMemberId() != null && member.getParentMemberId() != 0) {
					Member parentMember = memberService.queryObjById(String.valueOf(member.getParentMemberId())).getData();
					if (parentMember.getLevelId() != null && parentMember.getLevelId() != 0) {
						member.setParentLevelId(parentMember.getLevelId());
					}
				}
				jsonResult.setData(member);
				jsonResult.setSize(1);
				jsonResult.setMsg("操作成功");
				if (member.getChannelsId() != null) {
					channelsId = member.getChannelsId();
				}
			} else {
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setMsg("操作成功");
			}
			// 存入session
			request.getSession().setAttribute("channelsId", channelsId);
			request.getSession().setAttribute("levelId", member.getLevelId());
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 完善个人资料
	 *
	 * @param member
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/updateOrAddMemberDetail")
	@ResponseBody
	public JsonResult<String> updateOrAddMemberDetail(@RequestBody Member member, HttpSession session) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		String openId = null;
		if (session.getAttribute("openid") != null) {
			openId = (String) session.getAttribute("openid");
		}
		String brandCode = (String) session.getAttribute("brandCode");
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
		try {
			Member memberResult = memberService.queryMemberByOpenid(openId);
			member.setBrandCode(brandCode);
			member.setOpenid(openId);
			member.setMemberId(memberResult.getMemberId());
			// 调用校验手机号码的接口
			memberService.updateAndDeleteRepeatMemberInfo(member);

			jsonResult.setStatus("00");
			jsonResult.setData("ok");
			jsonResult.setSize(0);
			jsonResult.setMsg("操作成功");

		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	/***
	 * 输入手机和短信内容，发送短信
	 * @Param phone
	 * @Param massege
	 * **/
	@RequestMapping("/smsApi/sendSMS")
	public String sendSMSByPhone(String phone,String massege){
		return sendSmsService.sendSMS(massege, phone);
	}

	/**
	 * 完善会员资料-获取验证码
	 *
	 * @param session
	 * @param phone
	 * @param brandCode
	 * @return
	 */
	@RequestMapping("/merriplusApi/getUpdateSMSCode")
	@ResponseBody
	public CommonResult<String> getUpdateSMSCode(HttpSession session, String phone, String brandCode) {
//		return memberService.getUpdateSMSCode(session, phone, brandCode);
		String  code = StringUtil.GetRadomCode();
		CommonResult<String> result = new CommonResult<String>();
		String message=null;
		
		message = "尊敬的用户：您的验证码为："+code+"，请及时使用。";
		
		try {
			String url = "http://sapi.253.com/msg/";
			String account = "vipgzxd_xd_banne";
			String pswd = "FDsdf3@!er";
			String mobile = phone;
			String msg = message;
			boolean needstatus = true;
			String extno = null;
			System.out.println(message);
			String returnString = this.sendSmsService.batchSend(url, account, pswd, mobile, msg, needstatus, extno);
			System.out.println(returnString);
		      
//			SmsManager chuanglan = SmsManager.getInstance(Supplier.XI_DING);
//			chuanglan.sendSingleMsgWithoutReport(phone, message);
			
			//sendSmsService.sendSMS(message, phone);
			session.setAttribute("SMSCode", code);
			result.setData(code);
			result.setCode(0);
            result.setMsg("ok");
		} catch (Exception e) {
			log.error("短信发送异常：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 完善资料-验证码完善个人资料
	 *
	 * @param member
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/updateMemberWithSMSCode")
	@ResponseBody
	public CommonResult<String> updateMemberWithSMSCode(HttpServletRequest request, String smsCode,Member member,
			HttpSession session) {
		/*
		 * try { String fullName = new
		 * String(request.getParameter("fullName").getBytes("ISO8859-1"),"UTF-8"
		 * ); System.out.println("fullName:------->"+fullName);
		 * System.out.println("fullName in member:"+member.getFullName()); }
		 * catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
//		return memberService.updateMemberWithSMSCode(member, session, smsCode);
//
		CommonResult<String> result = new CommonResult<String>();
//      String openId = "o_3tTs1dJ4cdFw9n9muKBmoGRdaw";
		try {
			String openId = null;
			if(session.getAttribute("openid")!=null){
				openId = (String) session.getAttribute("openid");
			}
			String brandCode = (String) session.getAttribute("brandCode");
			if (StringUtils.isBlank(brandCode)) {
				throw new NullPointerException("brandCode can not be null");
			}

			String SMSCode=null;
			if(session.getAttribute("SMSCode")!=null){
				SMSCode = session.getAttribute("SMSCode").toString();
			}

			Member origMemberInfo = this.memberService.queryMemberByOpenid(openId);
			String memberPhone = origMemberInfo.getMobilePhone();
			if(StringUtils.isBlank(memberPhone)){
				//有验证码，验证码不正确
				if(StringUtils.isNotBlank(SMSCode) && !SMSCode.equals(smsCode)){
					log.error("验证码错误");
					result.setCode(2);
					result.setData(null);
					result.setMsg("验证码错误，请重新输入");
					return result;
				}
			}
			String mobilePhone = member.getMobilePhone();
			if(mobilePhone!=null){
				MemberExample example = new MemberExample();
				example.clear();
				example.createCriteria()
				       .andMobilePhoneEqualTo(mobilePhone)
				       .andBrandCodeEqualTo(brandCode);
				List<Member> list = this.memberFromEmisService.queryAllObjByExample(example);
				Member member2 = null;
				if(list.size()>0){
					member2 = list.get(0);
					//判断已有次手机号码的会员是否存在openid
					if(!openId.equals(member2.getOpenid()) && member2.getOpenid()!=null){
						log.error("会员手机号码已存在");
						session.removeAttribute("SMSCode");
						result.setCode(2);
						result.setData(null);
						result.setMsg("您输入的手机号码已存在，请重新输入");
						return result;
					}
				}
			}

			member.setBrandCode(brandCode);
			member.setOpenid(openId);
			member.setMemberId(origMemberInfo.getMemberId());
			this.memberService.updateAndDeleteRepeatMemberInfo(member);

			result.setCode(0);;
			result.setData("ok");
			result.setMsg("操作成功");
			log.info("个人资料修改成功：memberId"+origMemberInfo.getMemberId()+",brandCode:"+member.getBrandCode()+",fullName:"+member.getFullName());
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("error");
			result.setMsg(e.getMessage());
			log.info("个人资料修改失败!"+e.getMessage());
		}
		return result;
	}

	/**
	 * 微信个人中心---查询会员资料
	 *
	 * @param SecurityQrcode
	 * @return Member
	 * @author Benson
	 */
	/*
	 * @RequestMapping("/front/queryMemberByPhone") public Member
	 * queryMemberByPhone(String phone) { return
	 * memberService.queryMemberByPhone(phone); }
	 */

	/**
	 * 根据手机号码判断是否已注册，否则注册会员与获取对应的产品积分
	 *
	 * @param phone
	 * @return
	 * @author Benson
	 */
	/*
	 * @RequestMapping("/front/getMemberPoint") public ModelAndView
	 * getMemberPoint(String id_mobileNum_input, String id_securityCode_input,
	 * String barCode, String id_barCode_input, HttpServletRequest request,
	 * HttpSession session, String brandCode) {
	 *
	 * return memberService.getMemberPoint(id_mobileNum_input,
	 * id_securityCode_input, barCode, id_barCode_input, request, session,
	 * brandCode); }
	 */

	/**
	 * 防伪码积分
	 **/
	@RequestMapping("/front/securityCodePoint")
	@ResponseBody
	public JsonResult<Member> securityCodePoint(HttpSession session, String id_mobileNum_input,
			String id_securityCode_input, String barCode, String brandCode) {
		JsonResult<Member> jsonResult = new JsonResult<Member>();
		if(StringUtils.isBlank(id_mobileNum_input)){
			jsonResult.setStatus("2");
			jsonResult.setMsg("手机号码不能为空");
			return jsonResult;
		}

		if(StringUtils.isBlank(barCode)){
			//防伪码为正品，查询不到对应的产品
			jsonResult.setStatus("1");
			jsonResult.setMsg("检测不到防伪码，请进行扫码，或者手工输入防伪码");
			return jsonResult;
		}

		/*if (StringUtils.isBlank(barCode) && StringUtils.isNotBlank(id_barCode_input)) {
			barCode = id_barCode_input;
		}*/

		try {
			return this.securityQrcodeService.addPointFromQrcode(barCode, brandCode, id_mobileNum_input, id_securityCode_input);

		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg("查询异常，请刷新重试");
			return jsonResult;
		}
	}

	/*
	 * =======================================以下是后台api==========================
	 * ===========================
	 */

	/**
	 * 后台会员管理——会员列表查询
	 *
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/memberListPageView")
	public ModelAndView memberListPageView(String mobilePhone) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mobilePhone",mobilePhone);
		mav.setViewName("member/memberList");
		return mav;
	}

	/**
	 * 后台会员管理——会员列表查询
	 *
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/queryMemberData")
	@ResponseBody
	public PageView<Member> queryMemberData(HttpServletRequest request, String MemberId, String iDisplayStart,
											String iDisplayLength, String sSearch, String pageNo,String pageSize) {
		String mobilePhone = request.getParameter("mobilePhone");
		String fullName = request.getParameter("fullName");
		String enrollChannel = request.getParameter("enrollChannel");
		String brandCode = request.getParameter("brandCode");
		String openid = request.getParameter("openid");
		String memberId = request.getParameter("memberId");

		return memberService.queryMemberData(mobilePhone, fullName, enrollChannel, brandCode, openid,memberId, iDisplayStart, iDisplayLength, sSearch,pageNo,pageSize);
	}

	@RequestMapping("/queryMemberDataByPage")
	@ResponseBody
	public PageView<Member> queryMemberDataByPage(HttpServletRequest request, String MemberId, String iDisplayStart,
											String iDisplayLength, String sSearch, String pageNo,String pageSize) {
		return queryMemberData(request,MemberId,iDisplayStart,iDisplayLength,sSearch,pageNo,pageSize);
	}

	/**
	 * 后台会员管理——会员列表查询
	 *
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/dealerListPageView")
	public ModelAndView dealerListPageView(String nickName) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("nickName",nickName);
		mav.setViewName("memberJoinRecord/dealerList");
		return mav;
	}

	/**
	 * 后台会员管理——会员列表查询
	 *
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/queryDealerListData")
	@ResponseBody
	public PageView<Member> queryDealerData(HttpServletRequest request,String iDisplayStart,
											String iDisplayLength, String sSearch, String pageNo) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fullName",request.getParameter("fullName"));
		param.put("channelsId",request.getParameter("channelsId"));
		if(StringUtils.isNotBlank(request.getParameter("brandCode"))){
			param.put("brandCode",request.getParameter("brandCode"));
		}else {
			param.put("brandCode","JLD");
		}

		return memberService.queryDealerData(param, iDisplayStart, iDisplayLength, sSearch);
	}

	/**
	 * 后台会员管理——根据手机号码删除会员（同步删除CRM会员）
	 *
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/deleteMember")
	@ResponseBody
	public CommonResult<String> deleteMember(String teplId, String brandCode) {

		return memberService.deleteObjById(teplId, brandCode);
	}

	/**
	 * 同步会员和积分数据
	 **/
	@RequestMapping("updateMemberPointSum")
	@ResponseBody
	public String updateMemberPointSum() {
		return this.memberPointService.updateMemberPointSum();
	}

	/**
	 * 注册会员
	 */
	@RequestMapping("/front/memberRegist")
	@ResponseBody
	public CommonResult<Member> memberRegist(HttpSession session, @RequestBody Member member) {
		// String openid = "o_3tTs2cpntAT7wb3h2feU-u4LdE";
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		return memberService.memberRegist(openid, member);
	}

	/**
	 * 校验短信验证码
	 */
	@RequestMapping("/front/checkSMScode")
	@ResponseBody
	public CommonResult<Member> checkSMScode(HttpSession session, String smscode) {
//		return memberService.checkSMScode(session, smscode);
		CommonResult<Member> result = new CommonResult<Member>();
		String SMSCode=null;
		if(session.getAttribute("SMSCode")!=null){
			SMSCode = session.getAttribute("SMSCode").toString();
		}
		//验证码不正确
		if(SMSCode==null||!SMSCode.equals(smscode)){
			log.error("验证码错误");
            result.setCode(1);
            result.setData(null);
            result.setMsg("验证码错误，请重新输入");
		}else {
			result.setCode(0);
            result.setData(null);
            result.setMsg("正确通过");
		}
		return result;
	}

	/**
	 * 校验是否会员
	 */
	@RequestMapping("/front/checkIsMember")
	@ResponseBody
	public CommonResult<Member> checkIsMember(String mobilePhone, String brandCode) {
		return memberService.checkIsMember(mobilePhone, brandCode);
	}

	/**
	 * 活动---校验手机是否已被使用
	 *
	 * @param member
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping("/merriplusApi/checkPhoneExist")
	 *
	 * @ResponseBody public JsonResult<String> checkPhoneExist(HttpSession
	 * session,Member member) { String openId =
	 * (String)session.getAttribute("openid"); // String openId =
	 * "o_3tTs1dJ4cdFw9n9muKBmoGRdaw_1"; member.setOpenid(openId); return
	 * memberService.checkPhoneExist(member); }
	 */

	/**
	 * 活动---校验会员是否已经提交过资料
	 *
	 * @param member
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping("/merriplusApi/checkMemberInfo")
	 *
	 * @ResponseBody public JsonResult<Member> checkMemberInfo(HttpSession
	 * session,Member member) { String openId =
	 * (String)session.getAttribute("openid"); // String openId =
	 * "o_3tTs1dJ4cdFw9n9muKBmoGRdaw_1"; member.setOpenid(openId); return
	 * memberService.checkMemberInfo(member); }
	 */

	/**
	 * 活动---提交会员资料
	 *
	 * @param member
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping("/merriplusApi/updateOrAddActivityMember")
	 *
	 * @ResponseBody public JsonResult<String>
	 * updateOrAddActivityMember(@RequestBody Member member, HttpSession
	 * session) { String openId = (String)session.getAttribute("openid"); //
	 * String openId = "o_3tTs1dJ4cdFw9n9muKBmoGRdaw_1";
	 * member.setOpenid(openId); return
	 * memberService.updateOrAddActivityMember(member); }
	 */

	/**
	 * 校验是否完善资料
	 */
	@RequestMapping("front/checkIsReady")
	@ResponseBody
	public CommonResult<Member> checkIsReady(HttpSession session) {
		CommonResult<Member> result = new CommonResult<Member>();
		// String openId = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openId = null;
		if (session.getAttribute("openid") != null) {
			openId = (String) session.getAttribute("openid");
		}
		try {
			Member member = memberService.queryMemberByOpenid(openId);
			result.setCode(0);
			result.setData(member);
			result.setMsg("操作成功");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 微信测试页面
	 * @author Benson
	 */
	@RequestMapping("api/wechatTest")
	public ModelAndView wechatTest(HttpServletRequest request,HttpSession session,
			String openid,ModelAndView mav,String malltype, String brandCode) throws Exception {
		mav=new ModelAndView();
		System.out.println("终于等到你，我亲爱的openid，么么哒："+openid);

		session.setAttribute("openid", openid);
		session.setAttribute("mallType", malltype);
		session.setAttribute("brandCode", brandCode);

		String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(malltype!=null){
			mav.setViewName("redirect:" + domain + "/"+malltype+"/index");
		}else {
			mav.setViewName("redirect:" + domain + "/wMall/index");
		}

		Test test = SpringContextHolder.getOneBean(Test.class);
		Integer memberId = SpringContextHolder.getOneBean(com.mcoding.base.member.service.member.MemberService.class).queryByOpenId(openid).getId();
		test.setMemberInSession(memberId, session);
		return mav;
	}

	/***
	 * 查询完善会员资料的百分率
	 * @param session
	 * **/
	@RequestMapping("front/getMemberInfoPercent")
	@ResponseBody
	public CommonResult<String> getMemberInfoPercent(HttpSession session){
		String openid = (String) session.getAttribute("openid");
		String brandCode = (String) session.getAttribute("brandCode");
		return memberService.getMemberInfoPercent(openid,brandCode);
	}

	/***
	 * 查询天猫会员权益
	 * @param session
	 * **/
	@RequestMapping("front/getTmallMemberRight")
	@ResponseBody
	public JsonResult<Map<String, String>> getTmallMemberRight(HttpSession session) throws IOException {
		JsonResult<Map<String, String>> result = new JsonResult<>();
		String openid = (String) session.getAttribute("openid");
		String brandCode = (String) session.getAttribute("brandCode");
		Member member = memberService.queryMemberByOpenid(openid, brandCode);
		if (member == null || StringUtils.isBlank(member.getMobilePhone())) {
			result.setStatus("01");
			result.setMsg("请先完善个人资料");
			return result;
		}
		return this.memberService.getTmallMemberRight(member);
	}

	/***
	 *  领取天猫会员权益积分
	 * @param session
	 * **/
	@RequestMapping("front/getTmallMemberPoint")
	@ResponseBody
	public JsonResult<Integer> getTmallMemberPoint(HttpSession session, Integer level) throws IOException {
		JsonResult<Map<String, String>> resultmap = new JsonResult<>();
		JsonResult<Integer> result = new JsonResult<>();
		String openid = (String) session.getAttribute("openid");
		String brandCode = (String) session.getAttribute("brandCode");
		Member member = memberService.queryMemberByOpenid(openid, brandCode);
		if (member == null || StringUtils.isBlank(member.getMobilePhone())) {
			result.setStatus("01");
			result.setMsg("请先完善个人资料");
			return result;
		}
		//校验是否达到领取条件
		resultmap = this.memberService.getTmallMemberRight(member);
		if(level !=null && level <=(Integer.valueOf(resultmap.getData().get("level")))){
			GamePrizeExample gamePrizeExample = new GamePrizeExample();
			gamePrizeExample.createCriteria()
					.andPrizelevelEqualTo(level).andGameidEqualTo(52);
			List<GamePrize> gamePrizeList = this.gamePrizeMapper.selectByExample(gamePrizeExample);
			if(gamePrizeList.size() >0) {
				GamePrize gamePrize = gamePrizeList.get(0);
				GameMemberResultExample gameMemberResultExample = new GameMemberResultExample();
				GameMemberResultExample.Criteria criteria1 = gameMemberResultExample.createCriteria();
				criteria1.andGameidEqualTo(gamePrize.getGameid()).andOpenidEqualTo(openid).andPrizeidEqualTo(gamePrize.getId());
				List<GameMemberResult> gameMemberResults = gameMemberResultMapper.selectByExample(gameMemberResultExample);
				if(gameMemberResults.size() > 0){
					result.setStatus("01");
					result.setMsg("已领取，不可重复领取");
					result.setData(null);
					return  result;
				}
			}else{
				result.setStatus("01");
				result.setMsg("您的级别不足，不可领取");
				result.setData(null);
				return  result;
			}

			MemberLevelExample example = new MemberLevelExample();
			MemberLevelExample.Criteria criteria = example.createCriteria();
			criteria.andPriorityEqualTo(level);
			List<MemberLevel> list = memberLevelService.queryAllObjByExample(example);
			if(list.size() > 0){
				Integer consumption = list.get(0).getCreditlimit();
				memberPointService.updateAndAddMemberPoint(member, consumption, "C", "tmallMemberPoint");

				//增加领取记录
				GamePrize gamePrize = gamePrizeList.get(0);
				GameMemberResult gameMemberResult = new GameMemberResult();
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setGaintitle(String.valueOf(level));
				gameMemberResult.setPrizeid(gamePrize.getId());
				gameMemberResult.setIslottery(1);
				gameMemberResult.setLotterynum(1);
				gameMemberResult.setBrandcode(gamePrize.getBrandcode());
				gameMemberResult.setPrizename(gamePrize.getPrizename());
				gameMemberResult.setCreatetime(new Date());
				gameMemberResult.setUpdatetime(new Date());
				gameMemberResult.setGamename(gamePrize.getGamename());
				gameMemberResult.setGameid(gamePrize.getGameid());
				if (member != null) {
					gameMemberResult.setMembername(member.getFullName());
					gameMemberResult.setNickname(member.getNickName());
				}
				gameMemberResultMapper.insertSelective(gameMemberResult);

				result.setStatus("00");
				result.setMsg("领取成功");
				result.setData(consumption);
			}
		}else{
			result.setStatus("01");
			result.setMsg("并不具备领取条件");
			result.setData(null);
		}
		return result;
	}


	/***
	 *  领取天猫会员权益积分
	 * @param session
	 * **/
	@RequestMapping("front/gainedTmallMemberPoints")
	@ResponseBody
	public JsonResult<List<GameMemberResult>> gainedTmallMemberPoints(HttpSession session) throws IOException {
		JsonResult<List<GameMemberResult>> result = new JsonResult<>();
		String openid = (String) session.getAttribute("openid");
		GameMemberResultExample gameMemberResultExample = new GameMemberResultExample();
		GameMemberResultExample.Criteria criteria1 = gameMemberResultExample.createCriteria();
		criteria1.andGameidEqualTo(52).andOpenidEqualTo(openid);
		List<GameMemberResult> gameMemberResults = gameMemberResultMapper.selectByExample(gameMemberResultExample);
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(gameMemberResults);
		return  result;
	}

	/**
	 * 内部员工填写真实姓名
	 *
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/insideInputRealName")
	@ResponseBody
	public JsonResult<String> insideInputRealName(HttpSession session,@RequestParam(name = "realName") String realName) {
		JsonResult<String> result = new JsonResult<String>();
		String openid = (String) session.getAttribute("openid");

		//realName 判空，转码，验证realName是否为空
		if (StringUtils.isNotBlank(realName)) {
			try {
				realName = new String(realName.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			result.setStatus("01");
			result.setMsg("请输入您的真实姓名");
			result.setData(null);
			return result;
		}

		//验证realName的正确性   有无特殊字符
		realName = EmojiFilter.filterEmoji(realName);

		//验证是否已经填写过
		String real = memberFromEmisMapper.selectRealNameByOpenId(openid);
		if (StringUtils.isNotBlank(real)) {
			result.setStatus("02");
			result.setMsg("亲，您已经登记过了哦，您登记的真实姓名为："+realName);
			result.setData(null);
			return result;
		}

		memberFromEmisMapper.updateRealName(openid,realName);

		result.setStatus("00");
		result.setMsg("ok");
		result.setData(null);
		return result;
	}
	
	 public static void main(String args[]) { 
	        System.out.println("Hello World!"); 
	        String message=null;
	        String phone="13631690487";
	        message = "尊敬的用户：您的验证码为：18928984438，请及时使用。--BIG生活馆+";
			
			SmsManager chuanglan = SmsManager.getInstance(Supplier.XI_DING);
			try {
				chuanglan.sendSingleMsgWithoutReport(phone, message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	    } 
	 
	 	/** 
	     * 跳转修改会员等级页面
	     * @return 
	     * @author  
	   */ 
	   @RequestMapping("/merriplusApi/editMemberLevelView.html")
	   public ModelAndView editMemberLevelView(String memberId) {
		   ModelAndView mav = new ModelAndView();
	       if(StringUtils.isNotBlank(memberId)){
	    	   Member member = memberService.queryObjById(memberId).getData();
	    	   mav.addObject("member", member);
		       mav.addObject("edit","edit");
	       }
		   mav.setViewName("label/editMemberLevel");
	       return mav;
	   }
	   
	   /** 
	    * 修改会员
	    * @return 
	    * @author  
	  */ 
	   @RequestMapping("/member/editMember")
	   @ResponseBody
	   public CommonResult<String> editMember(Member Member) {
		   if(Member!=null && Member.getMemberId()!=null){
			   return this.memberService.modifyObj(Member);
		   }
		   return this.memberService.addObj(Member);
	   }
}