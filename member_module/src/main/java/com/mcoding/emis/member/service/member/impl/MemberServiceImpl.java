package com.mcoding.emis.member.service.member.impl;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.http.HttpPostClient;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.bean.member.MemberExample.Criteria;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.bean.member.MemberLevelExample;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;
import com.mcoding.emis.member.service.member.EmisMemberService;
import com.mcoding.emis.member.service.member.MemberLevelService;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

import me.chanjar.weixin.mp.bean.result.WxMpUser;


@Service("memberServiceFromEmis")
public class MemberServiceImpl implements MemberService {

	@Resource(name="emisMemberService")
	protected EmisMemberService memberFromEmisService;

//	@Resource(name="memberService")
//	protected com.mcoding.base.member.service.member.MemberService memberService;

	@Autowired
	protected WeixinUserService weixinUserService;

	@Autowired
	protected WeixinUserService emisWeixinUserService;

	@Autowired
	protected MemberPointService memberPointService;

	@Autowired
	protected MemberLevelService memberLevelService;

	@Autowired
	protected MemberFromEmisMapper memberFromEmisMapper;

	@Override
	public CommonResult<String> addObj(Member t) {
		this.memberFromEmisService.addObj(t);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
        result.setData("ok");
        result.setMsg("ok");
        return result;
	}

	@Override
	public CommonResult<String> modifyObj(Member t) {
		this.memberFromEmisService.modifyObj(t);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
        result.setData("ok");
        result.setMsg("ok");
        return result;
	}

	@Override
	public CommonResult<Member> queryObjById(String id) {
		CommonResult<Member> result = new CommonResult<Member>();
		result.setCode(0);
        result.setData(this.memberFromEmisService.queryObjById(Integer.valueOf(id)));
        result.setMsg("ok");
        return result;
	}

	@Override
	public Member selectByPhoneAndBrandCode(String mobilePhone, String brandCode) {
		MemberExample example = new MemberExample();
		example.createCriteria().andMobilePhoneEqualTo(mobilePhone).andBrandCodeEqualTo(brandCode);

		List<Member> list = this.memberFromEmisService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Member queryMemberByOpenid(String openid, String brandCode) {
		MemberExample example = new MemberExample();
		example.createCriteria()
               .andOpenidEqualTo(openid)
               .andBrandCodeEqualTo(brandCode);

		List<Member> list = this.memberFromEmisService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public CommonResult<Member> registerAndEditMember(String openId, Member member) {
		Member tmp = this.queryMemberByOpenid(openId);
		if (tmp == null) {
			member.setOpenid(openId);
			this.addObj(member);
		}else{
			member.setMemberId(tmp.getMemberId());
			this.modifyObj(member);
		}
		tmp = this.queryObjById(member.getMemberId().toString()).getData();

		CommonResult<Member> result = new CommonResult<>();
		result.setCode(0);
        result.setData(tmp);
        result.setMsg("ok");
        return result;
	}

	@Override
	public PageView<Member> queryMemberData(String mobilePhone, String fullName, String enrollChannel, String brandCode,
			String openid,String memberId ,String iDisplayStart, String iDisplayLength, String sSearch,String pageNo,String pageSize) {
		MemberExample memberExample = new MemberExample();
		Criteria cri = memberExample.createCriteria();

		if (StringUtils.isNotBlank(sSearch)) mobilePhone = sSearch;
		if (StringUtils.isNotBlank(mobilePhone)) cri.andMobilePhoneLike("%"+mobilePhone + "%");
		if (StringUtils.isNotBlank(fullName)) cri.andFullNameLike("%"+fullName+"%");
        if (StringUtils.isNotBlank(enrollChannel)) cri.andEnrollChannelEqualTo(enrollChannel);
        if (StringUtils.isNotBlank(brandCode)) cri.andBrandCodeEqualTo(brandCode);
        if (StringUtils.isNotBlank(openid)) cri.andOpenidEqualTo(openid);
        if (StringUtils.isNotBlank(memberId)) cri.andMemberIdEqualTo(Integer.valueOf(memberId));


        PageView<Member> tmpPageView;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
			tmpPageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			tmpPageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else {
			tmpPageView = new PageView<>(1, 10);
		}

        memberExample.setPageView(tmpPageView);
		memberExample.setOrderByClause(" memberId desc");
        tmpPageView = this.memberFromEmisService.queryObjByPage(memberExample);

		return tmpPageView;
	}

	@Override
	public PageView<Member> queryDealerData( Map<String, Object> param, String iDisplayStart, String iDisplayLength, String sSearch) {
		PageView<Member> pageView = new PageView<Member>(iDisplayStart, iDisplayLength);
		param.put("pageView", pageView);
		List<Member> list = this.memberFromEmisMapper.queryDealerDataByPage(param);
		/*for (Member member : list){
			Map<String, Object> map = this.memberFromEmisMapper.countByParentId(member.getMemberId());
			member.setChildrenMember( String.valueOf(map.get("childrenMember")));
			member.setChildrenDealer( String.valueOf(map.get("childrenDealer")));
		}*/
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public CommonResult<String> deleteObjById(String teplId, String brandCode) {
		CommonResult<String> result = new CommonResult<String>();
        try {
    		Member member = selectByPhoneAndBrandCode(teplId.trim(),brandCode);
    		//删除微信会员
    		if(member!=null && member.getOpenid()!=null){
    			WeixinUser weixinUser = emisWeixinUserService.selectByOpenid(member.getOpenid());
    			emisWeixinUserService.deleteObjById(weixinUser.getWxuserid());
    		}
			//删除本地会员
        	this.deleteObjById(member.getMemberId().toString());

        	//删除积分记录
        	memberPointService.deleteByPhone(teplId, brandCode);

            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");

        } catch (Exception e) {
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }

        return result;
	}

	@Override
	public int updateByPrimaryKey(Member member) {
		this.modifyObj(member);
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Member member) {
		this.modifyObj(member);
		return 0;
	}

	@Override
	public Member queryMemberByOpenid(String openid) {
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria().andOpenidEqualTo(openid);

		List<Member> list = this.memberFromEmisService.queryAllObjByExample(memberExample);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		return list.get(0);
	}

	@Override
	public Member addOrEditMemberByOpenId(String openid, String parentOpenId, String channelsId, String brandCode,
			String companyId, String malltype) {
		Date now = new Date();
//		Member parent = this.memberMapper.queryMemberByOpenidAndBrandCode(parentOpenId, brandCode);
		Member parent = null;
		if (StringUtils.isNotBlank(parentOpenId)) {
			parent = this.queryMemberByOpenid(parentOpenId);
		}
		WeixinUser weixinUserNew = this.weixinUserService.selectByOpenid(openid);
		if(weixinUserNew == null){
			//如果没有该用户就添加一条新消息

			weixinUserNew = new WeixinUser();
			weixinUserNew.setOpenid(openid);
			weixinUserNew.setBrandCode(brandCode);
			weixinUserNew.setCreatetime(now);
			weixinUserNew.setUpdatetime(now);
			if(StringUtils.isNotBlank(parentOpenId) && parent !=null && parent.getLevelId()!=null){
				weixinUserNew.setShareId(parentOpenId);
			}
			this.weixinUserService.addObj(weixinUserNew);

		}else{
			//如果已经存在的，检查是否有上级，已经是否通过上级的连接进来
			if(StringUtils.isBlank(weixinUserNew.getShareId())
					&& StringUtils.isNotBlank(parentOpenId) && parent !=null && parent.getLevelId()!=null){
				//更新上级的连接
				WeixinUser tmp = new WeixinUser();
				tmp.setWxuserid(weixinUserNew.getWxuserid());
				tmp.setShareId(parentOpenId);
				tmp.setUpdatetime(now);
				this.weixinUserService.modifyObj(tmp);
			}
		}

		System.out.println("channelsId========================"+channelsId);
		System.out.println("parentOpenId========================"+parentOpenId);
		Member member = this.queryMemberByOpenid(openid);
		if(member == null){
			member = new Member();
			member.setOpenid(openid);
			if(companyId !=null){
				member.setCompanyId(Integer.parseInt(companyId));
			}
			if(channelsId!=null){
				member.setChannelsId(Integer.valueOf(channelsId));
			}
			member.setBrandCode(brandCode);
			member.setCreateTime(new Date());
			member.setUpdateTime(now);
			member.setEnrollChannel(malltype);
			member.setMemberType("M");
			member.setNickName(weixinUserNew.getNickname());
			member.setHeadimgurl(weixinUserNew.getHeadimgurl());
			if(StringUtils.isNotBlank(parentOpenId) && parent !=null && parent.getLevelId()!=null){
				member.setParentMemberId(parent.getMemberId());
				member.setIsReferralNotice(Member.IS_REFERRALNOTIC_NO_POST);
			}
			this.memberFromEmisService.addObj(member);
		}else {
			if(member.getParentMemberId() == null
					&& StringUtils.isNotBlank(parentOpenId)
					&& member.getLevelId() == null
				    && parent !=null && parent.getLevelId()!=null){
				System.out.println("==========updateMember==============");
				Member tmp = new Member();
				tmp.setMemberId(member.getMemberId());
				tmp.setParentMemberId(parent.getMemberId());
				tmp.setIsReferralNotice(Member.IS_REFERRALNOTIC_NO_POST);
				tmp.setNickName(weixinUserNew.getNickname());
				tmp.setHeadimgurl(weixinUserNew.getHeadimgurl());
				if(companyId !=null){
					tmp.setCompanyId(Integer.parseInt(companyId));
				}
				if(channelsId!=null){
					tmp.setChannelsId(Integer.valueOf(channelsId));
				}
				tmp.setUpdateTime(now);
				this.memberFromEmisService.modifyObj(tmp);
			}
		}

		return member;

	}

	@Override
	public void updateAndDeleteRepeatMemberInfo(Member member) {
		try {
			//根据手机号码 or openid 查询是否有多条会员信息
//			this.selectByPhoneAndBrandCode(mem, brandCode)
//			List<Member> list =  memberMapper.queryMemberByPhoneOrOpenidAndBrandCode(member.getMobilePhone(),member.getOpenid(),member.getBrandCode());
			MemberExample example = new MemberExample();
			if(member.getMobilePhone()!=null){
				example.createCriteria()
				       .andMobilePhoneEqualTo(member.getMobilePhone());
				example.or()
				       .andOpenidEqualTo(member.getOpenid());
			}else{
				example.createCriteria()
				.andOpenidEqualTo(member.getOpenid());
			}


			List<Member> list = this.memberFromEmisService.queryAllObjByExample(example);


			//有多条会员信息，则查询根据手机号码的会员查询对应的积分信息，并更新到openid的会员中
			if(list.size()>1){
				example.clear();
				example.createCriteria()
				       .andMobilePhoneEqualTo(member.getMobilePhone());
				example.setOrderByClause("createtime asc");
				Member member2 = this.memberFromEmisService.queryAllObjByExample(example).get(0);
//				Member member2 = memberMapper.selectByPhoneAndBrandCode(member.getMobilePhone(),member.getBrandCode());

				example.clear();
				example.createCriteria()
				       .andOpenidEqualTo(member.getOpenid());
				Member member3 = this.memberFromEmisService.queryAllObjByExample(example).get(0);
				member3.setActivitySymptom(member2.getActivitySymptom());
				member3.setPointSum(member2.getPointSum()+member3.getPointSum());
				member3.setConcerns(member2.getConcerns());
				member3.setConcernsPerson(member2.getConcernsPerson());
				member3.setEnrollChannel(member2.getEnrollChannel());
				member3.setExt1(member2.getExt1());
				member3.setExt2(member2.getExt2());
				member3.setHealthProblem(member2.getHealthProblem());
				member3.setPosition(member2.getPosition());
				member3.setMobilePhone(member2.getMobilePhone());
				member3.setMemberType(member2.getMemberType());
				member3.setBirthday(member2.getBirthday());
				member3.setUpdateTime(new Date());
				member3.setParentMemberId(member2.getParentMemberId());
				member3.setLevelId(member2.getLevelId());
				member3.setLevelName(member2.getLevelName());

//				memberMapper.updateByPrimaryKeySelective(member3);
				this.memberFromEmisService.modifyObj(member3);
				//删除旧会员信息
//				memberMapper.deleteByPrimaryKey(member2.getMemberId());
				this.memberFromEmisService.deleteObjById(member2.getMemberId());
			}

			//有一条会员信息则会员信息即可
			else if(list.size()==1){
				member.setUpdateTime(new Date());
//				memberMapper.updateByPrimaryKeySelective(member);
				this.modifyObj(member);
			}else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public CommonResult<Member> memberRegist(String openid, Member t) {
		CommonResult<Member> result = new CommonResult<Member>();
		//TransactionStatus status = transactionManager.getTransaction(def);
		Date date = new Date();
		try {
			//手机号码和品牌查询会员
			MemberExample memberExample = new MemberExample();
			memberExample.createCriteria()
			             .andMobilePhoneEqualTo(t.getMobilePhone())
			             .andBrandCodeEqualTo(t.getBrandCode());
			memberExample.setOrderByClause("createtime asc");
    		Member member = this.memberFromEmisService.queryAllObjByExample(memberExample).get(0);
    		//未注册
    		if(member==null){
    			//判断是否从微商城上注册只有openid的会员
    			memberExample.clear();
    			memberExample.createCriteria().andOpenidEqualTo(openid);
    			memberExample.setOrderByClause("createtime asc");
    			Member member2 = this.memberFromEmisService.queryAllObjByExample(memberExample).get(0);
//    			Member member2 = memberMapper.queryMemberByOpenid(openid);
    			if(member2!=null){
    				//更新会员信息
    				member2.setFullName(t.getFullName());
    				member2.setGender(t.getGender());
    				member2.setBirthday(t.getBirthday());
    				member2.setMobilePhone(t.getMobilePhone());
    				member2.setUpdateTime(date);
    				member2.setOpenid(t.getOpenid());
//					memberMapper.updateByPrimaryKey(member2);
					this.memberFromEmisService.modifyObj(member2);
    			}else {
    				//新增本地新会员
	    			t.setMemberType("P");
	    			t.setEnrollChannel("WEIXIN");
	    			t.setCreateTime(date);
	    			t.setUpdateTime(date);
	    			t.setOpenid(t.getOpenid());
	    			t.setPointSum(0);
//	    			memberMapper.insert(t);
	    			this.memberFromEmisService.addObj(t);
				}

    		}else {
    			member.setOpenid(openid);
	    		updateAndDeleteRepeatMemberInfo(member);
    			//更新会员信息
				member.setMemberType("P");
    			member.setFullName(t.getFullName());
    			member.setEnrollChannel("SCANCODE");
    			member.setGender(t.getGender());
    			member.setBirthday(t.getBirthday());
    			member.setMobilePhone(t.getMobilePhone());
    			member.setUpdateTime(date);
				member.setOpenid(t.getOpenid());
//				memberMapper.updateByPrimaryKey(member);
				this.memberFromEmisService.modifyObj(member);
			}
    		//transactionManager.commit(status);
    		result.setCode(0);
            result.setData(t);
            result.setMsg("ok");
        } catch (Exception e) {
        	//transactionManager.rollback(status);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
        }

        return result;
	}

	@Override
	public CommonResult<Member> checkIsMember(String mobilePhone, String brandCode) {
		CommonResult<Member> result = new CommonResult<Member>();
		try {
			//手机号码和品牌查询会员
//			Member member = memberMapper.selectByPhoneAndBrandCode(mobilePhone,brandCode);
			MemberExample example = new MemberExample();
			example.createCriteria().andMobilePhoneEqualTo(mobilePhone).andBrandCodeEqualTo(brandCode);

//			Member member = this.memberFromEmisService.queryAllObjByExample(example).get(0);
			List<Member> list = this.memberFromEmisService.queryAllObjByExample(example);
			//未注册
			if(CollectionUtils.isEmpty(list)){
				result.setCode(0);
	            result.setData(null);
	            result.setMsg("ok");
			}else {
	            result.setCode(1);
	            result.setData(list.get(0));
	            result.setMsg("已注册");
			}

		} catch (Exception e) {
            result.setCode(2);
            result.setData(null);
            result.setMsg(e.getMessage());
        }
		return result;

	}

	@Override
	public CommonResult<Member> checkMemberIsRegisted(String openid) {
		CommonResult<Member> result = new CommonResult<Member>();
		try {
//			Member member = memberMapper.queryMemberByOpenid(openid);
			MemberExample example = new MemberExample();
			example.createCriteria().andOpenidEqualTo(openid);
			Member member = this.memberFromEmisService.queryAllObjByExample(example).get(0);
			if (member != null && member.getMobilePhone()!=null) {
				result.setCode(1);
				result.setData(member);
				result.setMsg("已注册");
			}else{
				result.setCode(0);
				result.setData(null);
				result.setMsg("尚未注册");
			}
			return result;
		} catch (Exception e) {
			result.setCode(2);
			result.setData(null);
			result.setMsg(e.getMessage());
		}
		return null;
	}

	@Override
	public CommonResult<Member> loadRegisterDetail(String mobilePhone, String brandCode) {
		CommonResult<Member> result = new CommonResult<Member>();
		try {
//    		Member member = memberMapper.selectByPhoneAndBrandCode(mobilePhone,brandCode);
			MemberExample ex  = new MemberExample();
			ex.createCriteria().andMobilePhoneEqualTo(mobilePhone).andBrandCodeEqualTo(brandCode);

			Member member = this.memberFromEmisService.queryAllObjByExample(ex).get(0);
			result.setCode(0);
			result.setData(member);
	        result.setMsg("ok");
		} catch (Exception e) {
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
        }

		return result;
	}

	@Override
	public Member addOrEditMemberByWxMpUser(WxMpUser wxMpUser, String parentOpenId, Integer channelsId,
			String brandCode) {
		Date now = new Date();
		String openid = wxMpUser.getOpenId();

		Member parent = null;
		if (StringUtils.isNotBlank(parentOpenId)) {
			parent =this.queryMemberByOpenid(parentOpenId);
			channelsId = parent.getChannelsId();
		}

		WeixinUser weixinUserNew = this.weixinUserService.selectByOpenid(openid);
		if(weixinUserNew == null){
			//如果没有该用户就添加一条新消息

			weixinUserNew = new WeixinUser();
			weixinUserNew.setOpenid(openid);
			weixinUserNew.setBrandCode(brandCode);
			weixinUserNew.setCreatetime(now);
			weixinUserNew.setUpdatetime(now);
			weixinUserNew.setCity(wxMpUser.getCity());
			weixinUserNew.setCountry(wxMpUser.getCountry());
			weixinUserNew.setHeadimgurl(wxMpUser.getHeadImgUrl());
			if(StringUtils.isNotBlank(wxMpUser.getNickname())){
				weixinUserNew.setNickname(wxMpUser.getNickname());
			}else{
				weixinUserNew.setNickname("未知");
			}
			weixinUserNew.setProvince(wxMpUser.getProvince());
			weixinUserNew.setSex(wxMpUser.getSex());
			if(wxMpUser.getSubscribe()!=null){
				weixinUserNew.setSubscribe(wxMpUser.getSubscribe()? "1" : "0");
			}

			if(wxMpUser.getSubscribe()!=null && wxMpUser.getSubscribe() &&
					wxMpUser.getSubscribeTime() != null){
				weixinUserNew.setSubscribetime(new Date(wxMpUser.getSubscribeTime() * 1000));
			}

			if(StringUtils.isNotBlank(parentOpenId) && parent !=null
					&& parent.getLevelId()!=null){
				weixinUserNew.setShareId(parentOpenId);
			}
			this.weixinUserService.addObj(weixinUserNew);

		}else{
			WeixinUser tmp = new WeixinUser();
			tmp.setWxuserid(weixinUserNew.getWxuserid());

			tmp.setCity(wxMpUser.getCity());
			tmp.setCountry(wxMpUser.getCountry());
			tmp.setHeadimgurl(wxMpUser.getHeadImgUrl());
			tmp.setNickname(wxMpUser.getNickname());
			tmp.setProvince(wxMpUser.getProvince());
			tmp.setSex(wxMpUser.getSex());
			if(wxMpUser.getSubscribe()!=null){
				tmp.setSubscribe(wxMpUser.getSubscribe()? "1" : "0");
				if(wxMpUser.getSubscribeTime()!=null){
					tmp.setSubscribetime(new Date(wxMpUser.getSubscribeTime() * 1000));
				}
			}
			tmp.setUpdatetime(now);

			//如果已经存在的，检查是否有上级，已经是否通过上级的连接进来
			if(StringUtils.isBlank(weixinUserNew.getShareId())
					&& StringUtils.isNotBlank(parentOpenId)
					&& parent !=null
					&& parent.getLevelId()!=null){
				//更新上级的连接
				tmp.setShareId(parentOpenId);
			}

			this.weixinUserService.modifyObj(tmp);
		}


		Member member = this.queryMemberByOpenid(openid);
		if(member == null){
			member = new Member();
			member.setOpenid(openid);
			member.setChannelsId(channelsId);
			member.setBrandCode(brandCode);
			member.setCreateTime(new Date());
			member.setUpdateTime(now);
			if(StringUtils.isNotBlank(member.getNickName())){
				member.setNickName(wxMpUser.getNickname());
			}else{
				member.setNickName("未知");
			}
			member.setHeadimgurl(wxMpUser.getHeadImgUrl());
			member.setFullName(wxMpUser.getNickname());

			if(brandCode!=null&&brandCode.equals("MRMJ")){
				member.setEnrollChannel("wMall");
			}else {
				member.setEnrollChannel("gMall");
			}
			member.setMemberType("M");
			member.setRegionCity(wxMpUser.getCity());
			member.setRegionProvince(wxMpUser.getProvince());
			member.setRegionArea(wxMpUser.getCountry());
			member.setGender(wxMpUser.getSex());
			if(StringUtils.isNotBlank(parentOpenId)
					&& parent.getLevelId()!=null){
				member.setParentMemberId(parent.getMemberId());
				member.setIsReferralNotice(Member.IS_REFERRALNOTIC_NO_POST);
			}
			this.memberFromEmisService.addObj(member);
		}else {

			Member tmp = new Member();
			tmp.setMemberId(member.getMemberId());
			tmp.setUpdateTime(now);

			tmp.setRegionCity(wxMpUser.getCity());
			tmp.setRegionProvince(wxMpUser.getProvince());
			tmp.setRegionArea(wxMpUser.getCountry());
			tmp.setNickName(wxMpUser.getNickname());
			tmp.setHeadimgurl(wxMpUser.getHeadImgUrl());
			if(StringUtils.isBlank(member.getFullName())){
				tmp.setFullName(wxMpUser.getNickname());
			}

			if(member.getParentMemberId() == null
					&& StringUtils.isNotBlank(parentOpenId)
					&& member.getLevelId() == null
					&& parent.getLevelId()!=null){
				tmp.setParentMemberId(parent.getMemberId());
				tmp.setChannelsId(channelsId);
				tmp.setIsReferralNotice(Member.IS_REFERRALNOTIC_NO_POST);
			}
			if(member.getIsReferralNotice()==Member.IS_REFERRALNOTIC_NO_POST){
				tmp.setIsReferralNotice(Member.IS_REFERRALNOTIC_NO_POST);
			}

			this.updateByPrimaryKeySelective(tmp);
		}

		return member;
	}

	@Override
	public List<Member> queryMembersByIsRefferal() {
		MemberExample example = new MemberExample();
		example.createCriteria().andIsReferralNoticeEqualTo(Integer.valueOf(1).byteValue());
		return this.memberFromEmisService.queryAllObjByExample(example);
	}

	@Override
	public void addMemberPointsum(int memberId, int point) {
		this.memberFromEmisService.addMemberPointsum(memberId, point);
	}

	@Override
	public List<Member> queryAllMember(String brandCode) {
		MemberExample example = new MemberExample();
		if(StringUtils.isNotBlank(brandCode)){
			MemberExample.Criteria criteria = example.createCriteria();
			criteria.andBrandCodeEqualTo(brandCode);
		}
		return this.memberFromEmisService.queryAllObjByExample(example);
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.memberFromEmisService.deleteObjById(Integer.valueOf(id));

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String>  getMemberInfoPercent(String openid,String brandCode) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			MemberExample example = new MemberExample();
			example.createCriteria().andOpenidEqualTo(openid);
			Member member = this.memberFromEmisService.queryAllObjByExample(example).get(0);
			double count = 0;
			if (member != null) {
				if(StringUtils.isNotBlank(member.getConcerns())){
					count++;
				}
				if(StringUtils.isNotBlank(member.getPosition())){
					count++;
				}
				if(StringUtils.isNotBlank(member.getConcernsPerson())){
					count++;
				}
				if(StringUtils.isNotBlank(member.getHealthProblem())){
					count++;
				}
				String percent = null;
				//获取格式化对象
				NumberFormat nt = NumberFormat.getPercentInstance();
				nt.setMaximumFractionDigits(2);
				if("MRMJ".equals(brandCode)){
					percent = nt.format(count / 4 );
				}else{
					percent = nt.format(count / 2);
				}
				result.setCode(1);
				result.setData(percent);
				result.setMsg("查询成功");
			}else{
				result.setCode(0);
				result.setData(null);
				result.setMsg("查询失败");
			}
			return result;
		} catch (Exception e) {
			result.setCode(2);
			result.setData(null);
			result.setMsg(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Member> getMemberByChannelId(Integer channelsId) {
		MemberExample example = new MemberExample();
		example.createCriteria().andChannelsIdEqualTo(channelsId).andLevelIdIsNotNull();
		List<Member> memberlist = this.memberFromEmisService.queryAllObjByExample(example);
		return memberlist;
	}

	@Override
	public JsonResult<Map<String, String>> getTmallMemberRight(Member member) throws IOException {
		JsonResult<Map<String, String>> result = new JsonResult<>();
		Map<String, String> map = new HashedMap();

		NameValuePair mobilephone = new NameValuePair();
		mobilephone.setName("mobilephone");
		mobilephone.setValue(member.getMobilePhone());
		String url = "http://v.gymmaxer.com/baisheng_module/orderInE3/front/getMemberConsumption";
		JsonResult<Integer> consumption = HttpPostClient.send(url, new NameValuePair[]{mobilephone}, JsonResult.class);

		MemberLevelExample memberLevelExample = new MemberLevelExample();
		MemberLevelExample.Criteria criteria = memberLevelExample.createCriteria();
		criteria.andTypeEqualTo(MemberLevel.MEMBER_LEVEL_TYPE_MEMBER).andConsumptionLessThanOrEqualTo(consumption.getData());
		memberLevelExample.setOrderByClause("priority DESC");
		List<MemberLevel> list = memberLevelService.queryAllObjByExample(memberLevelExample);
		if(list!=null && list.size() >0){
			map.put("consumption", String.valueOf(consumption.getData()));
			map.put("level", String.valueOf(list.get(0).getPriority()));
			map.put("levelName", String.valueOf(list.get(0).getName()));
		}else{
			map.put("consumption", "0");
			map.put("level", "0");
		}
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(map);
		return result;
	}


	public static void main(String[] args) {
//		//这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
//		double percent = 50.5D / 150D;
//		//输出一下，确认你的小数无误
//		System.out.println("小数：" + percent);
//		//获取格式化对象
//		NumberFormat nt = NumberFormat.getPercentInstance();
//		//设置百分数精确度2即保留两位小数
//		nt.setMinimumFractionDigits(2);
//		//最后格式化并输出
//		System.out.println("百分数：" + nt.format(percent));
//       Boolean falseBol = null;
//       System.out.println(falseBol ? "true" : "false");
	}
}
