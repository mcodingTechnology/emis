package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;

import java.util.HashMap;

public interface MemberPointService {
	
	public CommonResult<String> addObj(MemberPoint t);
	
	public CommonResult<String> deleteObjById(String id);

	//后台会员积分管理列表
//	public PageView<MemberPoint> queryMemberPointData(HttpServletRequest request,String memberPointId, String iDisplayStart, String iDisplayLength,String sSearch);
	public PageView<MemberPoint> queryMemberPointData(String iDisplayStart, String iDisplayLength, String mobilePhone, String pointsType,
			String fakeCheckCode, String brandCode);

	public void insert(MemberPoint memberPoint);
	
	/**微商城API***/
	/**获取会员积分明细的接口***/
	public JsonResult<MemberPoint> getMemberPointDetail(int pageNo,int pageSize,String openid,String brandCode);
	
	/**
	 * 获取会员最有一次防伪 扫码积分的数据
	 * @param mobliePhone
	 * @return
	 */
	public MemberPoint queryMemberPointByLastProduct(String mobliePhone, String brandCode, String pointType);

	/**
	 * 查询防伪码积分次数
	 * @param mobilePhone
	 * @param brandCode
	 * @return
	 */
	public int countSecurityQrcodeAndPoint(String mobilePhone, String brandCode);

	public MemberPoint selectByFakeCheckCode(String fakeCheckCode);

	boolean updateAndAddMemberPoint(Member member, int point, String pointType, String pointStatus);
	
	public String updateMemberPointSum();
	
	public Integer sumMemberPointByMobileAndBrandCode(String mobilePhone,String brandCode);
	
	/**
	 * 根据手机和品牌删除会员积分
	 * @param mobilePhone
	 * @return
	 */
	public CommonResult<String> deleteByPhone(String mobilePhone,String brandCode);

	//查询即将清零积分、剩余积分
	public JsonResult<MemberPoint> getClearAndMemberPoints(String openid);

	//清除去年的无用积分
	public JsonResult<String> cleanLastYearMemberPoint();

}
