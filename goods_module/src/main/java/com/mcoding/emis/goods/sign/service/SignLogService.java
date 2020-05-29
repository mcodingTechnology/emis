package com.mcoding.emis.goods.sign.service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.sign.bean.SigninLog;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;
import com.mcoding.emis.member.common.CommonResult;

import java.util.Date;

public interface SignLogService  extends BaseService<SigninLog, String> {

    public PageView<SigninLog> querySigninLogData(String iDisplayStart,String iDisplayLength,String openid);

    public CommonResult<String> retroactive(String starttime, String endtime, String openid);
}
