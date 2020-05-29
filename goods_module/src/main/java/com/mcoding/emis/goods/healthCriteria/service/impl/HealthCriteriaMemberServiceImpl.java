package com.mcoding.emis.goods.healthCriteria.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.deploy.shared.ModuleType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.member.persistence.member.MemberMapper;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.healthCriteria.bean.HealthAdviceResult;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaMember;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaMemberExample;
import com.mcoding.emis.goods.healthCriteria.bean.HealthRecommendProduct;
import com.mcoding.emis.goods.healthCriteria.bean.HealthResultResp;
import com.mcoding.emis.goods.healthCriteria.persistence.HealthCriteriaMapper;
import com.mcoding.emis.goods.healthCriteria.persistence.HealthCriteriaMemberMapper;
import com.mcoding.emis.goods.healthCriteria.service.HealthCriteriaMemberService;
import com.mcoding.emis.goods.sms.service.SendSmsService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * 
 * @author Leiming
 *
 */
@Service
public class HealthCriteriaMemberServiceImpl implements HealthCriteriaMemberService {

	private static final Logger log = Logger.getLogger(HealthCriteriaMemberService.class);
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	protected HealthCriteriaMemberMapper healthCriteriaMemberMapper;

	@Autowired
	protected HealthCriteriaMapper healthCriteriaMapper;

	@Autowired
	protected SendSmsService sendSmsService;

//	@Autowired
//	private InvestigationKeywordMapper keywordMapper;

	@Autowired
	private MemberService memberService;
//	private MemberMapper memberMapper;

//	@Autowired
//	private MemberScoreService scoreService;

//	@Autowired
//	private MemberRechargeMapper rechargeMapper;

	@Override
	public CommonResult<String> addObj(HealthCriteriaMember t) {
		CommonResult<String> result = new CommonResult<String>();

		try {
			Member member = this.memberService.selectByPhoneAndBrandCode(t.getMobilephone(), "MRMJ");
			if (t.getId() == null) {
				// 新增
				t.setResultStatus(1);
				healthCriteriaMemberMapper.insert(t);
			} else {
				// 修改
				t.setOpenid(null);
				t.setResultStatus(1);
				healthCriteriaMemberMapper.updateByPrimaryKeySelective(t);
			}

			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			log.error("增加失败：", e);
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}
		if (t.getId() == null) {
			try {
				// SMSUtil.sendSMS("亲爱的极智构客户，您的发丝已完成解密，请到极智构微信回复S查看[丝解密]报告。您还可在报告中获取完整数据的下载方法。如需任何帮助，请在微信联系我们。",
				// t.getMobilephone());
				sendSmsService.sendSMS("亲爱的极智构客户，您的发丝已完成解密，请到极智构微信回复S查看[丝解密]报告。您还可在报告中获取完整数据的下载方法。如需任何帮助，请在微信联系我们。",
						t.getMobilephone());
			} catch (Exception e) {
				result.setCode(1);
				result.setData("ok");
				result.setMsg("增加成功，但是发送短信失败:");
			}
		}
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.healthCriteriaMemberMapper.deleteByPrimaryKey(Integer.valueOf(id));

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(HealthCriteriaMember t) {
		this.healthCriteriaMemberMapper.updateByPrimaryKeySelective(t);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<HealthCriteriaMember> queryObjById(String id) {
		HealthCriteriaMember bean = this.healthCriteriaMemberMapper.selectByPrimaryKey(Integer.valueOf(id));

		CommonResult<HealthCriteriaMember> result = new CommonResult<HealthCriteriaMember>();
		result.setCode(0);
		result.setData(bean);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<HealthCriteriaMember>> getData(String phone) {

		HealthCriteriaMemberExample example = new HealthCriteriaMemberExample();
		HealthCriteriaMemberExample.Criteria cri = example.createCriteria();
		cri.andMobilephoneEqualTo(phone);
		example.setOrderByClause(" updateDate DESC");
		List<HealthCriteriaMember> list = healthCriteriaMemberMapper.selectByExample(example);

		ArrayList<HealthCriteriaMember> tmp = new ArrayList<HealthCriteriaMember>();
		CollectionUtils.addAll(tmp, list.iterator());

		CommonResult<ArrayList<HealthCriteriaMember>> result = new CommonResult<ArrayList<HealthCriteriaMember>>();
		result.setData(tmp);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<HealthCriteriaMember>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<HealthCriteriaMember>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<HealthCriteriaMember> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<HealthCriteriaMember> queryHealthCriteriaMemberData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<HealthCriteriaMember> pageView = new PageView<HealthCriteriaMember>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);

		String sSearch = request.getParameter("sSearch");
		if (StringUtils.isNotEmpty(sSearch)) {
			param.put("memberName", sSearch);
		}

		String mobilePhone = request.getParameter("mobilePhone");
		if (StringUtils.isNotEmpty(mobilePhone)) {
			param.put("mobilePhone", mobilePhone);
		}

		String memberName = request.getParameter("memberName");
		if (StringUtils.isNotEmpty(memberName)) {
			param.put("memberName", memberName);
		}

		String resultStatus = request.getParameter("resultStatus");
		if (StringUtils.isNotEmpty(resultStatus)) {
			param.put("resultStatus", resultStatus);
		}

		List<HealthCriteriaMember> healthCriteriaMembers = healthCriteriaMemberMapper
				.queryHealthCriteriaMemberByPage(param);
		pageView.setQueryResult(healthCriteriaMembers);
		return pageView;
	}

	@Override
	public ModelAndView addHealthCriteriaMemberView(String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			HealthCriteriaMember healthCriteriaMember = queryObjById(id).getData();
			mav.addObject("healthCriteriaMember", healthCriteriaMember);
			mav.addObject("edit", "edit");
		}
		// HealthCriteriaExample healthCriteriaExample = new
		// HealthCriteriaExample();
		// HealthCriteriaExample.Criteria cri =
		// healthCriteriaExample.createCriteria();
		// List<HealthCriteria> healthCriteriaList =
		// healthCriteriaMapper.selectByExample(healthCriteriaExample);
		List<HealthCriteria> healthCriteriaList = healthCriteriaMapper.selectGroupBy();
		mav.addObject("healthCriteriaList", healthCriteriaList);
		mav.setViewName("healthCriteria/addHealthCriteriaMember");
		return mav;
	}

	@Override
	public PageView<HealthCriteriaMember> queryResultByPhoneByPage(String iDisplayStart, String iDisplayLength,
			String phone) {
		PageView<HealthCriteriaMember> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<>();
		param.put("pageView", pageView);
		param.put("phone", phone);

		List<HealthCriteriaMember> healthCriterias = healthCriteriaMemberMapper.queryResultByPhoneByPage(param);
		pageView.setQueryResult(healthCriterias);

		return pageView;
	}

	/*@Override
	public CommonResult<HealthResultResp> queryResultById(int id) {
		CommonResult<HealthResultResp> commonResult = new CommonResult<>();

		List<HealthAdviceResult> dietaryAdvs = new ArrayList<>();
		List<HealthAdviceResult> exercisesAdvs = new ArrayList<>();
		List<HealthAdviceResult> healthAdvs = new ArrayList<>();
		try {
			HealthResultResp resultResp = new HealthResultResp();
			HealthCriteriaMember healthCriteriaMember = healthCriteriaMemberMapper.selectByPrimaryKey(id);

			Set<String> marks = new HashSet<>();
			marks.add(healthCriteriaMember.getHealthMark1());
			marks.add(healthCriteriaMember.getHealthMark2());
			marks.add(healthCriteriaMember.getHealthMark3());
			marks.add(healthCriteriaMember.getHealthMark4());
			marks.add(healthCriteriaMember.getHealthMark5());
			marks.add(healthCriteriaMember.getHealthMark6());

			for (String mark : marks) {
				if (StringUtils.isNotEmpty(mark)) {
					List<InvestigationKeyword> keywords = keywordMapper.queryKeywordByMark(mark);
					if (!CollectionUtils.isEmpty(keywords)) {
						InvestigationKeyword keyword = keywords.get(0);

						// 饮食建议
						HealthAdviceResult dietaryAdv = new HealthAdviceResult();
						dietaryAdv.setAdviceImgUrl(keyword.getDietaryImgUrl());
						dietaryAdv.setAdviceDesc(keyword.getDietaryAdv());
						dietaryAdvs.add(dietaryAdv);

						// 运动建议
						HealthAdviceResult exercisesAdv = new HealthAdviceResult();
						exercisesAdv.setAdviceImgUrl(keyword.getExercisesImgUrl());
						exercisesAdv.setAdviceDesc(keyword.getExercisesAdv());
						exercisesAdvs.add(exercisesAdv);

						// 综合建议
						HealthAdviceResult healthAdv = new HealthAdviceResult();
						healthAdv.setAdviceImgUrl(keyword.getHealthImgUrl());
						healthAdv.setAdviceDesc(keyword.getHealthAdv());
						healthAdvs.add(healthAdv);

					}
				}
			}

			String productIds = healthCriteriaMember.getProductid();
			if (!StringUtils.isEmpty(productIds)) {
				Map<String, Object> param = new HashMap<>();
				param.put("ids", productIds.split(","));

				List<HealthRecommendProduct> recommendProducts = healthCriteriaMemberMapper
						.queryRecommendProductByIds(param);
				resultResp.setRecommendProducts(recommendProducts);
			}

			resultResp.setDietaryAdvs(dietaryAdvs);
			resultResp.setSportAdvs(exercisesAdvs);
			resultResp.setHealthAdvs(healthAdvs);

			healthCriteriaMember.setProductid(null);
			healthCriteriaMember.setHealthMark1(null);
			healthCriteriaMember.setHealthMark2(null);
			healthCriteriaMember.setHealthMark3(null);
			healthCriteriaMember.setHealthMark4(null);
			healthCriteriaMember.setHealthMark5(null);
			healthCriteriaMember.setHealthMark6(null);
			resultResp.setResult(healthCriteriaMember);

			commonResult.setData(resultResp);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}*/

	/*@Override
	@Transactional
	public int addHealthCriteriaMemberInfor(String openid, String memberName, String mobilePhone) {
		// 结果码
		int resultCode = -1;
		// 查询会员充值记录
		MemberRecharge memberRecharge = getMemberRemainingNumByMemberIdAndTestType(openid, TestType.HAIR);
		if (memberRecharge != null) {
			int remainingNum = memberRecharge.getRemainingNum();
			int resultStatus = getHealthCriteriaLastResultStatus(openid, mobilePhone);
			// 判断剩余次数大于1，并且不存在待完成的订单
			if ((remainingNum - 1) >= 0 && resultStatus != 0) {
				// 减少一次充值记录
				memberRecharge.setRemainingNum(remainingNum - 1);
				memberRecharge.setUpdateTime(new Date());
				rechargeMapper.updateByPrimaryKeySelective(memberRecharge);

				// 插入一条新记录
				HealthCriteriaMember hcMem = new HealthCriteriaMember();
				hcMem.setOpenid(openid);
				hcMem.setMembername(memberName);
				hcMem.setMobilephone(mobilePhone);
				hcMem.setResultStatus(0);
				hcMem.setScore(String.valueOf(0));
				hcMem.setCreatedate(new Date());
				hcMem.setUpdatedate(new Date());

				healthCriteriaMemberMapper.insertSelective(hcMem);
				// 插入记录成功返回0，否则返回-1
				resultCode = 0;
			}
		}
		return resultCode;
	}*/

	@Override
	public int queryHealthCriteriaLastResultStatus(String openid, String mobilePhone) {
		return getHealthCriteriaLastResultStatus(openid, mobilePhone);
	}

	/**
	 * 获取发样检测结果状态
	 * 
	 * @param openid
	 * @param mobilePhone
	 * @return
	 */
	private int getHealthCriteriaLastResultStatus(String openid, String mobilePhone) {
		Map<String, Object> param = new HashMap<>();
		param.put("phone", mobilePhone);
		param.put("openid", openid);
		HealthCriteriaMember records = healthCriteriaMemberMapper.queryHealthCriteriaMemberLastRecord(param);
		// 若不存在记录，则返回2
		return records != null ? records.getResultStatus() : 2;
	}

	/**
	 * 根据会员ID和检测类型查询充值记录
	 * 
	 * @param memberId
	 * @param testType
	 * @return
	 */
	/*private MemberRecharge getMemberRemainingNumByMemberIdAndTestType(String openid, TestType testType) {
		MemberRechargeExample example = new MemberRechargeExample();
		MemberRechargeExample.Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openid);
		criteria.andTestTypeEqualTo(testType.getValue());
		List<MemberRecharge> results = rechargeMapper.selectByExample(example);

		return CollectionUtils.isNotEmpty(results) ? results.get(0) : null;
	}*/

}
