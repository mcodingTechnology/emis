package com.mcoding.emis.goods.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.service.WxUserTagsService;
import com.mcoding.emis.goods.wechat.utils.EmisWxUtils;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringUtil;
import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.goods.sms.service.SendSmsService;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberJoinRecord;
import com.mcoding.emis.member.bean.member.MemberJoinRecordExample;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberJoinRecordService;
import com.mcoding.emis.member.service.member.MemberLevelService;
import com.mcoding.emis.member.service.member.MemberService;

@Controller
public class MemberJoinRecordController {

	@Autowired
	protected MemberJoinRecordService memberJoinRecordService;

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected MemberLevelService memberLevelService;

	@Autowired
	protected IncomeService incomeService;
	
	@Autowired
	protected SendSmsService sendSmsService;
	
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;

	@Autowired
	private WxUserTagsService wxUserTagsService;

	@Autowired
	private StoreWxRefService storeWxRefService;

	/**
	 * 转到级别列表的页面
	 * 
	 * @return
	 */
	@RequestMapping("/memberJoinRecord/memberJoinRecordListView.html")
	public ModelAndView levelListView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberJoinRecord/memberJoinRecordList");
		return mav;
	}

    /**
     * 转到指定加盟商的销售数据页面
     *
     * @return
     */
    @RequestMapping("/memberJoinRecord/saleDataDetailView.html")
    public ModelAndView saleDataDetailView(String id,String name,String memberId) {
		try {
			name = new String(name.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("id",id);
        mav.addObject("name",name);
        mav.addObject("memberId",memberId);
        mav.setViewName("memberJoinRecord/saleDataDetail");
        return mav;
    }

    /**
     * 转到指定加盟商的下级页面
     *
     * @return
     */
    @RequestMapping("/memberJoinRecord/belowLevelDetailView.html")
    public ModelAndView belowLevelDetailView(String id,String name,String memberId) {
		try {
			name = new String(name.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("id",id);
        mav.addObject("name",name);
        mav.addObject("memberId",memberId);
        mav.setViewName("memberJoinRecord/belowLevelDetail");
        return mav;
    }

    /**
     * 转到指定加盟商的下线页面
     *
     * @return
     */
    @RequestMapping("/memberJoinRecord/belowLineDetailView.html")
    public ModelAndView belowLineDetailView(String id,String name,String memberId) {
        try {
            name = new String(name.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("id",id);
        mav.addObject("name",name);
        mav.addObject("memberId",memberId);
        mav.setViewName("memberJoinRecord/belowLineDetail");
        return mav;
    }

	@ApiOperation(httpMethod="GET", value="获取加盟商列表(使用iDisplayStart分页方式)")
	@RequestMapping("/memberJoinRecord/queryMemberJoinRecordData")
	@ResponseBody
	public PageView<MemberJoinRecord> queryMemberData(
			@RequestParam(value = "joinName", required=false) String joinName,
			@RequestParam(value = "startDate", required=false) String startDate,
			@RequestParam(value = "endDate", required=false) String endDate,
			@RequestParam(value = "brandCode", required=false) String brandCode,
			@RequestParam(value = "status", required=false) Integer status,
			@RequestParam(value = "level", required=false) Integer level,
			@RequestParam(value = "sSearch", required=false) String sSearch,
			@RequestParam(value = "parentId", required=false) String parentId,
			@RequestParam(defaultValue="0") String iDisplayStart,
			@RequestParam(defaultValue="10") String iDisplayLength) {
		if (StringUtils.isNotEmpty(sSearch)) joinName = sSearch;
		return memberJoinRecordService.queryMemberJoinRecordData(joinName,startDate,endDate,brandCode,status,level,iDisplayStart,iDisplayLength,parentId);
	}

	@ApiOperation(httpMethod="GET", value="获取加盟商列表（使用pageNo分页方式）")
	@RequestMapping("/memberJoinRecord/queryMemberJoinRecordDataByPage")
	@ResponseBody
	public PageView<MemberJoinRecord> queryMemberJoinRecordDataByPage(
		  @RequestParam(value = "joinName", required=false) String joinName,
		  @RequestParam(value = "startDate", required=false) String startDate,
		  @RequestParam(value = "endDate", required=false) String endDate,
		  @RequestParam(value = "brandCode", required=false) String brandCode,
		  @RequestParam(value = "status", required=false) Integer status,
		  @RequestParam(value = "level", required=false) Integer level,
		  @RequestParam(value = "parentId", required=false) String parentId,
		  @RequestParam(defaultValue="1") Integer pageNo,
		  @RequestParam(defaultValue="20") Integer pageSize) {
		return memberJoinRecordService.queryMemberJoinRecordDataByPage(joinName,startDate,endDate,brandCode,status,level,pageNo,pageSize,parentId);
	}

	/**
	 * 查询所有的级别
	 * 
	 * @return
	 */
	@RequestMapping("/memberJoinRecord/getMemberJoinRecordData")
	@ResponseBody
	public JsonResult<PageView<MemberJoinRecord>> getMemberJoinRecordData(int pageNo, int pageSize) {
		JsonResult<PageView<MemberJoinRecord>> jsonResult = new JsonResult<PageView<MemberJoinRecord>>();
		try {
			
			PageView<MemberJoinRecord> pageView = new PageView<>(pageNo, pageSize);
			MemberJoinRecordExample example = new MemberJoinRecordExample();
			example.setPageView(pageView);
			
			PageView<MemberJoinRecord> result = memberJoinRecordService.queryObjByPage(example);

			jsonResult.setData(result);
			jsonResult.setSize(result.getRowCount());
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 检查是否能有效申请加盟
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/merriplusApi/isAvailableForMemberJoinRecord")
	@ResponseBody
	public JsonResult<Boolean> isAvailableForMemberJoinRecord(Integer parentId, HttpSession session) {

		JsonResult<Boolean> jsonResult = new JsonResult<Boolean>();
		jsonResult.setData(false);

		try {
			String openId = (String) session.getAttribute("openid");
			Member member = memberService.queryMemberByOpenid(openId);
			if (member == null || StringUtils.isBlank(member.getMobilePhone())) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("该用户资料未完善");
				return jsonResult;
			}

			int memberId = member.getMemberId();

			CommonResult<MemberJoinRecord> recordTmp = memberJoinRecordService.queryObjByMemberId(memberId);
			if (recordTmp.getData() != null) {
				if (!MemberJoinRecord.STATUS_CONFIRM.equals(recordTmp.getData().getStatus())) {
					jsonResult.setStatus("02");
					jsonResult.setMsg("该用户已经申请过加盟，正在审核。");
					return jsonResult;
				} else {
					jsonResult.setStatus("021");
					jsonResult.setMsg("该用户已经申请过加盟，审核已通过，不用再申请。");
					return jsonResult;
				}

			}

			if (parentId != null && parentId > 0) {
				CommonResult<Member> parent = memberService.queryObjById(String.valueOf(parentId));
				if (parent.getData() == null) {
					jsonResult.setStatus("03");
					jsonResult.setMsg("加盟的上级用户不存在");
					return jsonResult;
				}

				MemberLevel parentLevel = memberLevelService
						.queryObjById(parent.getData().getLevelId());
				if (parentLevel == null) {
					jsonResult.setStatus("04");
					jsonResult.setMsg("加盟的上级用户的级别不存在");
					return jsonResult;
				}

				CommonResult<MemberLevel> childLevel = memberLevelService
						.queryChildLevel(parentLevel.getId());
				if (childLevel.getData() == null) {
					jsonResult.setStatus("05");
					jsonResult.setMsg("加盟的上级用户的级别不存在下级");
					return jsonResult;
				}
			}

		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			return jsonResult;
		}

		jsonResult.setStatus("00");
		jsonResult.setMsg("ok");
		jsonResult.setData(true);
		return jsonResult;
	}

	/**
	 * 创建加盟申请
	 * @param memberJoinRecord
	 * @return
	 */
	@RequestMapping("/merriplusApi/createMemberJoinRecordByParent")
	@ResponseBody
	public JsonResult<String> createMemberJoinRecordByParent(@RequestBody MemberJoinRecord memberJoinRecord, HttpSession session) {
		String openId = (String) session.getAttribute("openid");
		JsonResult<String> jsonResult = new JsonResult<String>();

		try {
			Member member = memberService.queryMemberByOpenid(openId);
			if (member == null) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("该用户不存在");
				return jsonResult;
			}

			int memberId = member.getMemberId();
			CommonResult<MemberJoinRecord> recordTmp = memberJoinRecordService.queryObjByMemberId(memberId);
			if (recordTmp.getData() != null) {
				jsonResult.setStatus("02");
				jsonResult.setMsg("该用户已经申请过");
				return jsonResult;
			}

			Integer parentId = member.getParentMemberId();
			if (parentId ==null || parentId <= 0) {
				jsonResult.setStatus("03");
				jsonResult.setMsg("加盟的上级用户不能为空");
				return jsonResult;
			}

			CommonResult<Member> parent = memberService.queryObjById(String.valueOf(parentId));
			if (parent.getData() == null) {
				jsonResult.setStatus("03");
				jsonResult.setMsg("加盟的上级用户不存在");
				return jsonResult;
			}

			MemberLevel parentLevel = memberLevelService
					.queryObjById(parent.getData().getLevelId());
			if (parentLevel == null) {
				jsonResult.setStatus("04");
				jsonResult.setMsg("加盟的上级用户的级别不存在");
				return jsonResult;
			}

			CommonResult<MemberLevel> childLevel = memberLevelService.queryChildLevel(parentLevel.getId());
			if (childLevel.getData() == null) {
				jsonResult.setStatus("05");
				jsonResult.setMsg("加盟的上级用户的级别不存在下级");
				return jsonResult;
			}

			MemberJoinRecord record = new MemberJoinRecord();
			record.setOpenid(openId);
			record.setMemberid(memberId);
			record.setMembername(member.getFullName());
			record.setLeveid(childLevel.getData().getId());
			record.setLevelname(childLevel.getData().getName());
			record.setParentid(parentId);
			record.setParentname(parent.getData().getFullName());
			record.setParentlevelid(parentLevel.getId());
			record.setParentlevelname(parentLevel.getName());
			record.setCreatetime(new Date());
			record.setGymroom(memberJoinRecord.getGymroom());
			record.setRegson(memberJoinRecord.getRegson());
			record.setStatus(MemberJoinRecord.STATUS_APPLY);
			
			String brandCode = (String) session.getAttribute("brandCode");
			record.setBrandCode(brandCode);

			memberJoinRecordService.addObj(record);

			if (record == null) {

				jsonResult.setStatus("error");
				jsonResult.setMsg("该用户的申请记录不存在");
				return jsonResult;
			}

			member.setMemberId(record.getMemberid());
			member.setLevelId(record.getLeveid());
			member.setLevelName(record.getLevelname());
			member.setParentMemberId(record.getParentid());

			memberService.modifyObj(member);

			Income income = new Income();
			income.setMemberid(record.getMemberid());
			income.setMembername(record.getMembername());
			income.setOpenid(record.getOpenid());
			income.setCreatetime(new Date());
			income.setUpdatetime(new Date());
			income.setCommission(0);

			this.incomeService.addObj(income);

			record.setStatus(MemberJoinRecord.STATUS_APPLY);
			record.setConfirmtime(new Date());
			memberJoinRecordService.modifyObj(record);


			String url = null;
			member = memberService.queryObjById(String.valueOf(record.getMemberid())).getData();
			String domain = GetStoreDomain.getDomain(member.getBrandCode());
			if("JLD".equals(member.getBrandCode())){
				url = domain+"/gMall/index.html";
			}else{
				url = domain+"/wMall/index.html";
			}
			//1.发送模板消息通知该会员成为下线
			String memberfirst = "恭喜您，通过【"+ parent.getData().getFullName()+"】的邀请，成为我们的健康家人";
			wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_BECOME_REFERRAL, member.getOpenid(), memberfirst,
					DateUtil.dateTimeFormatStr(new Date()), null, null, null, null, url);

			jsonResult.setMsg("恭喜您，申请成功,请耐心等待审核通过");
			jsonResult.setStatus("00");

			//给加盟商微信用户打标签
			AccountConfig account = EmisWxUtils.getWxAccountConfig(member.getBrandCode());
			Store store = StoreUtils.getStoreFromThreadLocal();
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());
			wxUserTagsService.makeWxuserTags(member, wxMpService);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg("申请失败，请联系管理员");
		}

		return jsonResult;
	}

	/**
	 * 提交加盟申请
	 * @param levelId
	 * @return
	 */
	@RequestMapping("/merriplusApi/createMemberJoinRecord")
	@ResponseBody
	public JsonResult<String> createMemberJoinRecord(int levelId, HttpSession session) {

		String openId = (String) session.getAttribute("openid");
		JsonResult<String> jsonResult = new JsonResult<String>();

		if (levelId <= 0) {
			jsonResult.setStatus("01");
			jsonResult.setMsg("加盟的的级别不能为空");
			return jsonResult;
		}

		try {
			// 1 找出会员信息
			Member member = memberService.queryMemberByOpenid(String.valueOf(openId));
			if (member == null) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("该用户不存在");
				return jsonResult;
			}

			int memberId = member.getMemberId();
			Integer parentMemberId = member.getParentMemberId();

			// 2 检查会员是否已经申请过
			CommonResult<MemberJoinRecord> recordTmp = memberJoinRecordService.queryObjByMemberId(memberId);
			if (recordTmp.getData() != null) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("该用户已经申请过");
				return jsonResult;
			}

			// 3 找出会员级别
			MemberLevel childLevel = memberLevelService.queryObjById(levelId);
			if (childLevel == null) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("加盟的上级用户的级别不存在下级");
				return jsonResult;
			}

			MemberJoinRecord record = new MemberJoinRecord();
			if (parentMemberId != null && parentMemberId > 0) {
				CommonResult<Member> parent = this.memberService.queryObjById(String.valueOf(parentMemberId));
				MemberLevel parentLevel = memberLevelService
						.queryObjById(parent.getData().getLevelId());
				record.setParentid(parentMemberId);
				record.setParentname(parent.getData().getFullName());
				record.setParentlevelid(parentLevel.getId());
				record.setParentlevelname(parentLevel.getName());
			}

			record.setMemberid(memberId);
			record.setOpenid(openId);
			record.setMembername(member.getFullName());
			record.setLeveid(childLevel.getId());
			record.setLevelname(childLevel.getName());
			record.setCreatetime(new Date());
			record.setStatus(MemberJoinRecord.STATUS_APPLY);

			String brandCode = (String) session.getAttribute("brandCode");
			record.setBrandCode(brandCode);
			memberJoinRecordService.addObj(record);

		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 通过申请
	 * @param recordId
	 * @return
	 */
	@RequestMapping("/memberJoinRecord/confirmMemeberJoin")
	@ResponseBody
	public JsonResult<MemberJoinRecord> confirmMemeberJoin(int recordId,HttpSession session) {

		JsonResult<MemberJoinRecord> jsonResult = new JsonResult<MemberJoinRecord>();
		try {
			MemberJoinRecord record = memberJoinRecordService.queryObjById(recordId);
			if (record == null) {

				jsonResult.setStatus("error");
				jsonResult.setMsg("该用户的申请记录不存在");
				return jsonResult;
			}

			Member member = new Member();
			member.setMemberId(record.getMemberid());
			member.setLevelId(record.getLeveid());
			member.setLevelName(record.getLevelname());
			member.setParentMemberId(record.getParentid());

			memberService.modifyObj(member);

			Income income = new Income();
			income.setMemberid(record.getMemberid());
			income.setMembername(record.getMembername());
			income.setOpenid(record.getOpenid());
			income.setCreatetime(new Date());
			income.setUpdatetime(new Date());
			income.setCommission(0);
			
			this.incomeService.addObj(income);

			record.setStatus(MemberJoinRecord.STATUS_CONFIRM);
			record.setConfirmtime(new Date());
			memberJoinRecordService.modifyObj(record);

			/*String first = "恭喜加盟！";
			String keywork = "极智构恭喜您成功加盟，成为健康达人";
			wxMpTemplateMsgUtil.sendWxMpTemplateMessageType("become_shoper", record.getOpenid(), first, keywork,
					DateUtil.dateTimeFormatStr(new Date()), null, null, null, null, null, "#f37b1d");*/
			String url = null;
			member = memberService.queryObjById(String.valueOf(record.getMemberid())).getData();
			String domain = GetStoreDomain.getDomain(member.getBrandCode());
			if("JLD".equals(member.getBrandCode())){
				url = domain+"/gMall/index.html";
			}else{
				url = domain+"/wMall/index.html";
			}

			//给加盟商微信用户打标签
			Store store = StoreUtils.getStoreFromThreadLocal();
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());
			wxUserTagsService.makeWxuserTags(member, wxMpService);

			//1.发送模板消息通知该会员成为下线
			String memberfirst = "恭喜您，通过【"+ member.getFullName()+"】的邀请，成为我们的健康家人";
			wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_BECOME_FRANCHISEE, member.getOpenid(), memberfirst,
					DateUtil.dateTimeFormatStr(new Date()), null, null, null, null, url);
			/*wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_BECOME_FRANCHISEE, member.getOpenid(), null,
					memberfirst, DateUtil.dateTimeFormatStr(new Date()), null, null, null, url);*/

			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	/**
	 * 通过申请
	 * @param mobilePhone
	 * @return
	 */
	@RequestMapping("/merriplusApi/sendSmsVaild")
	@ResponseBody
	public JsonResult<String> sendSmsVaild(String mobilePhone, HttpSession session) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		String openId = (String) session.getAttribute("openid");
		;

		Member member = memberService.queryMemberByOpenid(openId);
		String phone = member.getMobilePhone();

		if (phone == null || phone.trim().length() == 0) {
			if (mobilePhone == null || mobilePhone.trim().length() == 0) {
				jsonResult.setStatus("01");
				jsonResult.setMsg("电话号码为空无法发验证码");
				jsonResult.setData(null);
				return jsonResult;
			}

			phone = mobilePhone;
		}

		String code = StringUtil.GetRadomCode();
		String message = "您好，您申请加盟的验证码是:" + code + ", 请验证。谢谢！";

		try {
//			SMSUtil.sendSMS(message, phone);
			sendSmsService.sendSMS(message, phone);
		} catch (Exception e) {
			jsonResult.setStatus("02");
			jsonResult.setMsg("系统繁忙，暂时没法发送验证码，请稍候再试");
			jsonResult.setData(e.getMessage());
			return jsonResult;
		}

		jsonResult.setStatus("00");
		jsonResult.setMsg("ok");
		jsonResult.setData(code);
		return jsonResult;
	}

    @ApiOperation(httpMethod="POST", value="获取某加盟商下线的接口(有两种分页的方式，分别是iDisplayStart和pageNo)")
    @RequestMapping("/memberJoinRecord/queryMemberBelowLineDataByPage")
    @ResponseBody
    public PageView<Member> queryMemberBelowLineDataByPage(
            @RequestParam(value = "memberId") Integer memberId,
            @RequestParam(value = "memberName", required=false) String memberName,
            @RequestParam(value = "startDate", required=false) String startDate,
            @RequestParam(value = "endDate", required=false) String endDate,
            @RequestParam(value = "openId", required=false) String openId,
            @RequestParam(value = "sSearch", required=false) String sSearch,
            @RequestParam(value="pageNo",required=false) String pageNo,
            @RequestParam(value="pageSize",required=false) String pageSize,
            @RequestParam(value="iDisplayStart",required=false) String iDisplayStart,
            @RequestParam(value="iDisplayLength",required=false) String iDisplayLength) {

        if (StringUtils.isNotEmpty(sSearch)) memberName = sSearch;
        return memberJoinRecordService.queryMemberBelowLineDataByPage(memberId,memberName,startDate,endDate,openId,pageNo,pageSize,iDisplayStart,iDisplayLength);
    }
}
