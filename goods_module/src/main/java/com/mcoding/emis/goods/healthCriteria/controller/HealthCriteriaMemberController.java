package com.mcoding.emis.goods.healthCriteria.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaMember;
import com.mcoding.emis.goods.healthCriteria.service.HealthCriteriaMemberService;
import com.mcoding.emis.goods.healthCriteria.service.HealthCriteriaService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * 
 * @author Leiming
 *
 */
@Controller
public class HealthCriteriaMemberController {

	@Autowired
	private HealthCriteriaMemberService healthCriteriaMemberService;

	@Autowired
	private HealthCriteriaService healthCriteriaService;

	@Autowired
	private MemberService memberService;

	/**
	 * 发样检测结果--列表查询
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/healthCriteriaMemberPageView.html")
	public ModelAndView healthCriteriaMemberPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("healthCriteria/healthCriteriaMemberList");
		return mav;
	}

	/**
	 * 发样检测结果--列表查询
	 * 
	 * @author Leiming
	 */
	@RequestMapping("/queryHealthCriteriaMemberData")
	@ResponseBody
	public PageView<HealthCriteriaMember> queryHealthCriteriaMemberData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		return healthCriteriaMemberService.queryHealthCriteriaMemberData(request, iDisplayStart, iDisplayLength);
	}

	/**
	 * 列表方式获取所有检测项目结果集
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/queryHealthCriteriaList")
	@ResponseBody
	public List<HealthCriteria> queryHealthCriteriaList() {
		return healthCriteriaService.queryHealthCriteriaList();
	}

	/**
	 * 跳转添加检测结果的页面
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/addHealthCriteriaMemberView.html")
	public ModelAndView addHealthCriteriaMemberView(String id) {
		return healthCriteriaMemberService.addHealthCriteriaMemberView(id);
	}

	/**
	 * 根据healthcriteriaid查询检测项目
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/queryResultByHealthcriteriaid")
	@ResponseBody
	public CommonResult<HealthCriteria> queryResultByHealthcriteriaid(String healthcriteriaid) {
		return healthCriteriaService.queryObjById(healthcriteriaid);
	}

	/**
	 * 根据检测姓名名称查询检测项目可能的结果
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/queryResultByTestitem")
	@ResponseBody
	public List<HealthCriteria> queryResultByTestitem(String testitem) {
		return healthCriteriaService.queryResultByTestitem(testitem);
	}

	/**
	 * 添加检测结果
	 * 
	 * @param GameQuestion
	 * @return
	 * @author Leiming
	 * @throws ParseException
	 */
	@RequestMapping("/addHealthCriteriaMember")
	@ResponseBody
	public CommonResult<String> addHealthCriteriaMember(HealthCriteriaMember healthCriteriaMember) {
		Date createDate = new Date();
		if (healthCriteriaMember.getId() == null) {
			healthCriteriaMember.setCreatedate(createDate);
		} else {
			CommonResult<HealthCriteriaMember> a = healthCriteriaMemberService
					.queryObjById(healthCriteriaMember.getId().toString());
			healthCriteriaMember.setCreatedate(a.getData().getCreatedate());
			healthCriteriaMember.setResultStatus(1);
		}
		healthCriteriaMember.setUpdatedate(createDate);

		return healthCriteriaMemberService.addObj(healthCriteriaMember);
	}

	/**
	 * 发样检测结果——根据id删除检测结果
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/deleteHealthCriteriaMember")
	@ResponseBody
	public CommonResult<String> deletehealtHCriteriaMember(String teplId) {
		return healthCriteriaMemberService.deleteObjById(teplId);
	}

	/**
	 * 根据手机号码查询检测报告
	 * 
	 * @return
	 * @author Leiming
	 */
	@RequestMapping("/healthCriteriaMember/queryResultByPhone")
	@ResponseBody
	public CommonResult<ArrayList<HealthCriteriaMember>> queryResultByPhone(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		CommonResult<ArrayList<HealthCriteriaMember>> result = healthCriteriaMemberService.getData(phone);
		if (result != null) {
			if (result.getData() != null) {
				ArrayList<HealthCriteriaMember> list = new ArrayList<HealthCriteriaMember>();
				if (result.getData().size() > 0) {
					list.add(result.getData().get(0));
					result.setData(list);
				}
			}
		}
		return result;
	}

	/**
	 * 根据手机号码分页查询检测报告列表
	 * 
	 * @return
	 * 
	 * @author wuzetao
	 */
	@RequestMapping(value = "/healthCriteriaMember/queryResultByPhoneByPage")
	@ResponseBody
	public PageView<HealthCriteriaMember> queryResultByPhoneByPage(@RequestParam(required = true) int pageNo,
			@RequestParam(required = true) int pageSize, @RequestParam(required = true) String phone) {
		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;
		PageView<HealthCriteriaMember> result = healthCriteriaMemberService
				.queryResultByPhoneByPage(String.valueOf(iDisplayStart), String.valueOf(iDisplayLength), phone);
		return result;
	}

	/**
	 * 根据openid查询用户是否有填写手机号码
	 * 
	 * @author Benson
	 */
	@RequestMapping(value = "healthCriteriaMember/checkMemberIsRegisted")
	@ResponseBody
	public CommonResult<Member> checkMemberIsRegisted(HttpSession session) {
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		return memberService.checkMemberIsRegisted(openid);
	}

	/**
	 * 根据Id查询检测报告
	 * 
	 * @param id
	 * @return
	 * 
	 * @author wuzetao
	 */
	/*@RequestMapping(value = "/healthCriteriaMember/queryResultById")
	@ResponseBody
	public CommonResult<HealthResultResp> queryResultById(@RequestParam(required = true) int id) {
		return healthCriteriaMemberService.queryResultById(id);
	}*/

	/**
	 * 前端添加记录
	 * 
	 * @param session
	 * @param memberName
	 * @param mobilePhone
	 * 
	 * @author wuzetao
	 * @return
	 */
	/*@RequestMapping(value = "/healthCriteriaMember/addHealthCriteriaRecord.html")
	@ResponseBody
	public CommonResult<Integer> addHealthCriteriaRecord(HttpSession session,
			@RequestParam(required = true) String memberName, @RequestParam(required = true) String mobilePhone) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			String openid = (String) session.getAttribute("openid");
			// openid = "o_3tTs89qBuItrW4lROoUJBOVTAQ";
			if (StringUtils.isEmpty(openid)) {
				throw new NullPointerException("openId is null ...");
			} else {
				int resultCode = healthCriteriaMemberService.addHealthCriteriaMemberInfor(openid, memberName,
						mobilePhone);

				if (resultCode == -1) {
					commonResult.setCode(0);
					commonResult.setMsg("添加记录失败，用户余额次数不足或者已经存在未完成记录");
					commonResult.setData(resultCode);
				} else {
					commonResult.setCode(0);
					commonResult.setMsg("添加记录成功");
					commonResult.setData(resultCode);
				}
			}
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("添加记录失败: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}*/

	/**
	 * 获取发样最新一条记录状态
	 * 
	 * @param session
	 * @param mobilePhone
	 * @return
	 */
	@RequestMapping(value = "/healthCriteriaMember/getHealthCriteriaLastResult.html")
	@ResponseBody
	public CommonResult<Integer> getHealthCriteriaResult(HttpSession session,
			@RequestParam(required = true) String mobilePhone) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			String openid = (String) session.getAttribute("openid");
			// openid = "o_3tTs89qBuItrW4lROoUJBOVTAQ";
			if (StringUtils.isEmpty(openid)) {
				throw new NullPointerException("openId is null ...");
			} else {
				int resultStatus = healthCriteriaMemberService.queryHealthCriteriaLastResultStatus(openid, mobilePhone);
				commonResult.setCode(0);
				commonResult.setMsg("success");
				commonResult.setData(resultStatus);
			}
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			commonResult.setData(null);
			e.printStackTrace();
		}
		return commonResult;
	}
}
