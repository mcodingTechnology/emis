package com.mcoding.emis.goods.securityQrcode.service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

/**
 * Created by libing on 2014-06-02  09:02.
 */
public interface SecurityQrcodeService {

	//校验防伪码真伪
	public CommonResult<String> checkQrcode(String securityQrcode);
	
	public JsonResult<Member> addPointFromQrcode(String barCode, String brandCode, String mobilephone, String securityCode);

	public CommonResult<String> validateQrcode(String phone);
}
