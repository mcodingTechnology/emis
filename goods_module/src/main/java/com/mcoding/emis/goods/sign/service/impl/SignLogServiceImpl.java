package com.mcoding.emis.goods.sign.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.sign.bean.SigninLog;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;
import com.mcoding.emis.goods.sign.persistence.SigninLogMapper;
import com.mcoding.emis.goods.sign.persistence.SigninStatisticsMapper;
import com.mcoding.emis.goods.sign.service.SignLogService;
import com.mcoding.emis.goods.sign.service.SignStatisticsService;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.persistence.member.EmisMemberPointMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;

@Service
public class SignLogServiceImpl implements SignLogService{
	private static final Logger log = Logger.getLogger(SignLogServiceImpl.class);
    private static final int MAX_CALLCOUNT = 10;
    
    @Autowired
    private DefaultTransactionDefinition def;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private SigninLogMapper signinLogMapper;
    @Autowired
    private SigninStatisticsMapper signinStatisticsMapper;
	@Autowired
	private EmisMemberPointMapper memberPointMapper;
	@Autowired
	private SignStatisticsService signStatisticsService;

	@Override
	public CommonResult<String> addObj(SigninLog t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> modifyObj(SigninLog t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<SigninLog> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<ArrayList<SigninLog>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<PageView<SigninLog>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageView<SigninLog> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		return null;
	}

	@Override
	public PageView<SigninLog> querySigninLogData(String iDisplayStart, String iDisplayLength, String openid) {
		PageView<SigninLog> pageView = new PageView<SigninLog>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);
		param.put("openid",openid);
		List<SigninLog> signinLogList = signinLogMapper.querySigninLogDataByPage(param);
		pageView.setQueryResult(signinLogList);
		return pageView;
	}

	@Override
	public CommonResult<String> retroactive(String starttime, String endtime, String openid) {
		CommonResult<String> result = new CommonResult<String>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			Date date = new Date();
			Date startDate = DateHelper.convertStrToDate(starttime);
			Date endDate = DateHelper.convertStrToDate(endtime);
			param.put("starttime",starttime);
			param.put("endtime",endtime);
			param.put("openid",openid);
			//1.1.删除日期内的签到记录
			this.signinLogMapper.deleteByDate(param);

			//1.2 删除日期内的会员签到积分记录
			param.put("relatedTransactionNo","signin");
			this.memberPointMapper.deleteByParam(param);

			//1.3 删除日期内的签到统计记录
			this.signinStatisticsMapper.deleteByParam(param);

			//查看最新开始签到记录
			SigninLog lastSigninLog = signinLogMapper.getLatelyRecodeByOpenid(openid);

			//2.新增所选日期的签到记录、会员签到积分记录、会员总积分
			int days = DateHelper.daysBetween(startDate,endDate);
			List<Date> listDate = DateHelper.getDatesBetweenTwoDate(startDate, endDate);
			List<SigninLog> signinLogList = null;
			for (Date signDate :listDate){
				SigninLog signinLog = new SigninLog();
				signinLog.setMemberid(lastSigninLog.getMemberid());
				signinLog.setMembername(lastSigninLog.getMembername());
				signinLog.setBrandcode(lastSigninLog.getBrandcode());
				signinLog.setOpenid(lastSigninLog.getOpenid());
				signinLog.setSigntime(signDate);
				this.signStatisticsService.retroactiveRecord(signinLog);
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
		return result;
	}
}
