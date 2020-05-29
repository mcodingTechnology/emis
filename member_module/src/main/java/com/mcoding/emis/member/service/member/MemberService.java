package com.mcoding.emis.member.service.member;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public interface MemberService {

	CommonResult<String> addObj(Member t);

	CommonResult<String> modifyObj(Member t);

	CommonResult<Member> queryObjById(String id);

	// 根据手机号判断是否已是会员
	// public ModelAndView getMemberPoint(String id_mobileNum_input, String
	// id_securityCode_input, String barCode,
	// String id_barCode_input, HttpServletRequest request, HttpSession session,
	// String brandCode);

	// 注册或修改会员
	// public CommonResult<Member> registerAndEditMember(HttpSession session,
	// Member t, String id_SMSCode_input);

	// 查询会员资料用手机号码
	// public Member queryMemberByPhone(String phone);

	public Member selectByPhoneAndBrandCode(String mobilePhone, String brandCode);

	// 查询会员资料
	public Member queryMemberByOpenid(String openid, String brandCode);

	// 产品积分查询和会员积分累加
	// public Product memberProductPoint(String accessToken, String
	// id_mobileNum_input, String productCode,
	// String id_securityCode_input, String brandCode);

	// 会员注册
	// public String memberRegister(String id_mobileNum_input, String
	// brandCode);

	public CommonResult<Member> registerAndEditMember(String openId, Member member);

	// 后台会员管理列表
	public PageView<Member> queryMemberData(String mobilePhone, String fullName, String enrollChannel, String brandCode, String openid,String memberId,
			String iDisplayStart, String iDisplayLength,String sSearch,String pageNo,String pageSize);

	// 后台经销商管理列表
	public PageView<Member> queryDealerData(Map<String, Object> param, String iDisplayStart, String iDisplayLength,String sSearch);

	// 根据手机号码删除会员
	public CommonResult<String> deleteObjById(String teplId, String brandCode);

	public int updateByPrimaryKey(Member member);

	public int updateByPrimaryKeySelective(Member member);

	public CommonResult<Member> loadRegisterDetail(String mobilePhone, String brandCode);

	Member queryMemberByOpenid(String openid);

	// public JsonResult<Member> securityCodePoint(HttpSession session, String
	// id_mobileNum_input,
	// String id_securityCode_input, String barCode, String id_barCode_input,
	// String brandCode);

	/**
	 * 根据openid添加或修改用户
	 * 
	 * @param openId
	 * @param parentOpenId
	 */
	public Member addOrEditMemberByOpenId(String openId, String parentOpenId, String channelsId, String brandCode,
			String companyid, String malltype);

	/**
	 * 根据 微信用户 添加或修改用户
	 * 
	 * @param wxMpUser
	 * @param parentOpenId
	 */
	public Member addOrEditMemberByWxMpUser(WxMpUser wxMpUser, String parentOpenId, Integer channelsId,
			String brandCode);

	// 更新与删除重复会员数据
	public void updateAndDeleteRepeatMemberInfo(Member member);

//	public String updateMemberPointSum();

	// 会员注册
	public CommonResult<Member> memberRegist(String openid, Member member);

	// 短信验证
//	public CommonResult<Member> checkSMScode(HttpSession session, String smscode);

	// 校验是否会员
	public CommonResult<Member> checkIsMember(String mobilePhone, String brandCode);

	/*** 根据openid查询用户是否有填写手机号码 ***/
	public CommonResult<Member> checkMemberIsRegisted(String openid);

	// 查找还没有推送消息的会员
	public List<Member> queryMembersByIsRefferal();

	public void addMemberPointsum(int memberId, int point);
	
	public List<Member> queryAllMember(String brandCode) ;
	
	public CommonResult<String> deleteObjById(String id);

	/***
	 * 查询完善会员资料百分率
	 * */
	public CommonResult<String>  getMemberInfoPercent(String openid,String brandCode);

	public List<Member> getMemberByChannelId(Integer channelsId);

	/***
	 * 查询天猫会员权益
	 * **/
	public JsonResult<Map<String, String>> getTmallMemberRight(Member member) throws IOException;

}
