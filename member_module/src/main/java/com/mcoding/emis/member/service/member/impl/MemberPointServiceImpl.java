package com.mcoding.emis.member.service.member.impl;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.MemberPointExample;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.EmisMemberPointService;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("memberPointServiceFromEmis")
public class MemberPointServiceImpl implements MemberPointService {

	@Resource
	protected EmisMemberPointService emisMemberPointService;

	@Resource(name = "memberServiceFromEmis")
	protected MemberService memberService;

	@Override
	public CommonResult<String> addObj(MemberPoint t) {
		this.emisMemberPointService.addObj(t);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.emisMemberPointService.deleteObjById(Integer.valueOf(id));

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public PageView<MemberPoint> queryMemberPointData(String iDisplayStart, String iDisplayLength, String mobilePhone, String pointsType,
			String fakeCheckCode, String brandCode) {
		PageView<MemberPoint> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		try {
			MemberPointExample example = new MemberPointExample();
			MemberPointExample.Criteria cri = example.createCriteria();
			if (StringUtils.isNotBlank(mobilePhone)) {
				cri.andMobilePhoneLike(mobilePhone + "%");
			}
			if (StringUtils.isNotBlank(pointsType)) {
				cri.andPointsTypeEqualTo(pointsType);
			}
			if (StringUtils.isNotBlank(fakeCheckCode)) {
				cri.andFakeCheckCodeEqualTo(fakeCheckCode);
			}
			if (StringUtils.isNotBlank(brandCode)) {
				cri.andBrandCodeEqualTo(brandCode);
			}
			example.setOrderByClause("addDate desc");
			example.setPageView(pageView);
			pageView = this.emisMemberPointService.queryObjByPage(example);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageView;
	}

	@Override
	public void insert(MemberPoint memberPoint) {
//		this.emisMemberPointService.modifyObj(memberPoint);
		this.emisMemberPointService.addObj(memberPoint);
	}

	@Override
	public JsonResult<MemberPoint> getMemberPointDetail(int pageNo, int pageSize, String openid, String brandCode) {
		JsonResult<MemberPoint> jsonResult = new JsonResult<MemberPoint>();

		try {
			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
			MemberPointExample memberPointExample = new MemberPointExample();
			MemberPointExample.Criteria criteria = memberPointExample.createCriteria();
			criteria.andMobilePhoneEqualTo(member.getMobilePhone())
					.andBrandCodeEqualTo(brandCode);

			MemberPointExample.Criteria criteria1 = memberPointExample.createCriteria();
			criteria1.andOpenidEqualTo(openid).andBrandCodeEqualTo(brandCode);
			memberPointExample.or(criteria1);
			memberPointExample.setOrderByClause("memberPointId DESC");
			PageView<MemberPoint> tmpPageView = new PageView<>(pageNo, pageSize);
			memberPointExample.setPageView(tmpPageView);
			tmpPageView = this.emisMemberPointService.queryObjByPage(memberPointExample);
			MemberPoint mp = new MemberPoint();
			Integer pointSum = member.getPointSum();
			mp.setAllPoints(pointSum);
			mp.setEnabledPoints(pointSum);
			
			if (pageNo <= tmpPageView.getPageCount()) {
				mp.setList(tmpPageView);
			}
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			jsonResult.setSize(tmpPageView.getQueryResult().size());
			jsonResult.setData(mp);

		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public MemberPoint queryMemberPointByLastProduct(String mobliePhone, String brandCode, String pointType) {
		MemberPointExample example = new MemberPointExample();
		example.createCriteria().andMobilePhoneEqualTo(mobliePhone).andBrandCodeEqualTo(brandCode)
				.andPointsTypeEqualTo(pointType);

		example.setOrderByClause("addDate asc");
		List<MemberPoint> list = this.emisMemberPointService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int countSecurityQrcodeAndPoint(String mobilePhone, String brandCode) {
		// SELECT COUNT(*) from mr_member_point where mobilePhone=#{param1} and
		// pointsType='A'
		// and date_format(addDate,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')
		// and brandCode=#{param2}
		Date today = DateUtils.truncate(new Date(), Calendar.DATE);
		Date tomorrow = DateUtils.addDays(today, 1);

		MemberPointExample example = new MemberPointExample();
		example.createCriteria().andMobilePhoneEqualTo(mobilePhone).andBrandCodeEqualTo(brandCode)
				.andPointsTypeEqualTo("A").andAddDateGreaterThanOrEqualTo(today).andAddDateLessThan(tomorrow);

		List<MemberPoint> list = this.emisMemberPointService.queryAllObjByExample(example);
		return list != null ? list.size() : 0;
	}

	@Override
	public MemberPoint selectByFakeCheckCode(String fakeCheckCode) {
		MemberPointExample example = new MemberPointExample();
		example.createCriteria().andFakeCheckCodeEqualTo(fakeCheckCode);
		List<MemberPoint> list = this.emisMemberPointService.queryAllObjByExample(example);
		MemberPoint memberPoint = null;
		if(list.size()>0){
			memberPoint = list.get(0);
		}
		return memberPoint;
	}

	@Override
	public String updateMemberPointSum() {
		List<Member> members = this.memberService.queryAllMember("JLD");
		System.out.println("=========获取所有会员信息=========");
		for (Member member : members) {
			if (StringUtils.isNotBlank(member.getMobilePhone())) {
				// Integer pointSum
				// =memberPointMapper.countMemberPointByMobile(member.getMobilePhone(),
				// member.getBrandCode());
				Integer pointSum = this.emisMemberPointService
						.sumMemberPointByMobileAndBrandCode(member.getMobilePhone(), member.getBrandCode());
				if (pointSum != null) {
					if (!member.getPointSum().equals(pointSum)) {
						member.setPointSum(pointSum);
						// memberMapper.updateByPrimaryKeySelective(member);
						this.memberService.modifyObj(member);
					}
				}
			}
		}
		return "success";
	}

	@Override
	public boolean updateAndAddMemberPoint(Member member, int point, String pointType, String pointStatus) {
		boolean flag = false;
		try {
			// member.setPointSum(member.getPointSum()+point);
			// memberMapper.updateByPrimaryKeySelective(member);

			// 插入积分记录
			MemberPoint memberPoint = new MemberPoint();
			memberPoint.setPointsType(pointType);
			memberPoint.setRelatedTransactionNo(pointStatus);
			memberPoint.setMobilePhone(member.getMobilePhone());
			memberPoint.setAddDate(new Date());
			memberPoint.setPoints(point);
			memberPoint.setOpenid(member.getOpenid());
			memberPoint.setBrandCode(member.getBrandCode());
//			memberPointMapper.insertSelective(memberPoint);
			this.emisMemberPointService.addObj(memberPoint);

			// 更新会员积分总额
			// this.mem.addMemberPointsum(member.getMemberId(),point);
			this.memberService.addMemberPointsum(member.getMemberId(), point);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public Integer sumMemberPointByMobileAndBrandCode(String mobilePhone, String brandCode) {
		return this.emisMemberPointService.sumMemberPointByMobileAndBrandCode(mobilePhone, brandCode);
	}

	@Override
	public CommonResult<String> deleteByPhone(String mobilePhone, String brandCode) {
		this.emisMemberPointService.deleteByPhone(mobilePhone, brandCode);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public JsonResult<MemberPoint> getClearAndMemberPoints(String openid) {
		JsonResult<MemberPoint> jsonResult = new JsonResult<MemberPoint>();

		try {
			if(StringUtils.isBlank(openid)){
				jsonResult.setMsg("openid不能为空");
				jsonResult.setStatus("01");
				return jsonResult;
			}
			HashMap<Object, String> param = new HashMap<Object, String>();
			Member member = memberService.queryMemberByOpenid(openid);
			if(member!= null){
				if(StringUtils.isBlank(member.getMobilePhone())){
					jsonResult.setMsg("该会员未完善资料");
					jsonResult.setStatus("01");
					return jsonResult;
				}
				param.put("mobilePhone",member.getMobilePhone());
				param.put("openid",openid);
				param.put("brandCode",member.getBrandCode());
			}
			MemberPoint result = emisMemberPointService.getClearAndMemberPoints(param);
			if(result==null){
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				return jsonResult;
			}
			if(result.getFacecodepoint()==null){
				result.setFacecodepoint(0);
				result.setClearPoint(0);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				return jsonResult;
			}
			if(result.getGiftpoint()==null){
				result.setGiftpoint(0);
			}

			Integer facecodepoint = result.getFacecodepoint();
			Integer giftpoint = result.getGiftpoint();
			Integer clearPoint = facecodepoint + giftpoint;
			if(clearPoint <0){
				clearPoint = 0;
			}
			result.setClearPoint(clearPoint);

			jsonResult.setData(result);
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
		}catch (Exception e){
			e.printStackTrace();
		}
		return jsonResult;
	}

	@Override
	public JsonResult<String> cleanLastYearMemberPoint() {
		try {
			List<Member> memberList = memberService.queryAllMember("JLD");
			for (Member member : memberList) {
				if(StringUtils.isNotBlank(member.getOpenid())){
					JsonResult<MemberPoint> jsonResult =  getClearAndMemberPoints(member.getOpenid());
					if(jsonResult.getData()!=null){
						Integer clearPoint = jsonResult.getData().getClearPoint();
                        if(clearPoint > 0){
                            updateAndAddMemberPoint(member,-clearPoint,"E","CLEARPOINT");
                        }
					}
				}
			}
            JsonResult<String> jsonResult = new JsonResult<>();
			jsonResult.setData(null);
            jsonResult.setStatus("00");
            jsonResult.setMsg("清理积分成功");
            return jsonResult;

		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
