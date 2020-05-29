package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.common.CommonResult;

public interface MemberAddressService {
	
	CommonResult<MemberAddress> queryObjById(String id);

	/**
	 * 查询用户的收货地址
	 * @return
	 */
	JsonResult<MemberAddress> queryByOpenId(String openid);
	
	/**
	 * 添加收货地址
	 * @param memberAddress
	 * @return
	 */
	JsonResult<String> add(MemberAddress memberAddress, String openid);
	
	/**
	 * 修改收货地址
	 * @param memberAddres
	 * @return
	 */
	JsonResult<String> update(MemberAddress memberAddres, String openid);
	
	/**
	 * 删除收货地址
	 * @return
	 */
	JsonResult<String> delete(String openid);
	
	/**
	 * 查询所有用户的收货地址
	 * @return
	 */
	PageView<MemberAddress> queryMemberAddressData(String iDisplayStart, String iDisplayLength,
			String sSearch,String mobilePhone,String pageNo);

}
