package com.mcoding.emis.goods.sales.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.label.entity.UserTagsEntity;
import com.mcoding.emis.goods.sales.bean.MemberSales;
import com.mcoding.emis.goods.sales.bean.MemberSalesExample;
import com.mcoding.emis.goods.sales.bean.MemberSalesExample.Criteria;
import com.mcoding.emis.goods.sales.bean.MemberSalesLog;
import com.mcoding.emis.goods.sales.bean.MemberSalesSendBatch;
import com.mcoding.emis.goods.sales.bean.MemberSendSalesInfo;
import com.mcoding.emis.goods.sales.persistence.MemberSalesLogMapper;
import com.mcoding.emis.goods.sales.persistence.MemberSalesMapper;
import com.mcoding.emis.goods.sales.persistence.MemberSalesSendBatchMapper;
import com.mcoding.emis.goods.sales.service.MemberSalesService;
import com.mcoding.emis.goods.sales.tools.SendSalesMsgThread;
import com.mcoding.emis.goods.sms.service.SendSmsService;
import com.mcoding.emis.member.common.CommonResult;


@Service("memberSalesService")
public class MemberSalesServiceImpl implements MemberSalesService {
	@Resource
	private MemberSalesMapper memberSalesMapper;
	@Resource
	private MemberSalesLogMapper memberSalesLogMapper;
	@Resource
	private MemberSalesSendBatchMapper memberSalesSendBatchMapper;
	
	@Autowired
	protected SendSmsService sendSmsService;
	

	@Override
	public PageView<MemberSales> querySalesData(MemberSales memberSales, String iDisplayStart, String iDisplayLength, String pageNo, String pageSize) {
		MemberSalesExample memberSalesExample = new MemberSalesExample();
		Criteria cri = memberSalesExample.createCriteria();
		// 查询条件拼接
		if(memberSales.getSaleStartTime() != null){
			cri.andSalesStatrTimeGreaterThanOrEqualTo(memberSales.getSaleStartTime());
		}
		if(memberSales.getSaleEndTime() != null){
			cri.andSalesEndTimeLessThan(memberSales.getSaleEndTime());
		}
		if(StringUtils.isNotEmpty(memberSales.getSaleName())){
			cri.andSaleNameLike("%"+memberSales.getSaleName()+"%");
		}
		if(StringUtils.isNotEmpty(memberSales.getSaleStatus())){
			cri.andSaleStatusEqualTo(memberSales.getSaleStatus());
		}
		if(StringUtils.isNotEmpty(memberSales.getSaleSendMsgType())){
			cri.andSaleSendMsgTypeEqualTo(memberSales.getSaleSendMsgType());
		}
		// 分页
		PageView<MemberSales> tmpPageView;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
			tmpPageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			tmpPageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else {
			tmpPageView = new PageView<>(1, 10);
		}
		
		memberSalesExample.setPageView(tmpPageView);
		memberSalesExample.setOrderByClause(" sale_id desc");
        List<MemberSales> list = this.memberSalesMapper.querySalesDataByPage(memberSalesExample);
        tmpPageView.setQueryResult(list);
		return tmpPageView;
	}

	@Override
	public CommonResult<String> saveMemberSalesConfig(MemberSales memberSales) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			Date date = new Date();
			if(memberSales.getSaleId() != null){
				// 修改
				memberSales.setLastUpdateTime(date);
				this.memberSalesMapper.updateById(memberSales);
			}else{
				//  新增
				memberSales.setCreateTime(date);
				memberSales.setLastUpdateTime(date);
				memberSales.setSaleStatus("QY");
				this.memberSalesMapper.addMemberSales(memberSales);
			}
			result.setCode(0);
			result.setData("success");
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setData("error");
			result.setMsg("error");
		}
		return result;
	}

	@Override
	public MemberSales queryById(Integer id) {
		return this.memberSalesMapper.queryById(id);
	}

	@Override
	public CommonResult<String> deleteMemberSalesById(String saleId) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			this.memberSalesMapper.deleteById(saleId);
			result.setCode(0);
			result.setData("success");
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setData("error");
			result.setMsg("error");
		}
		return result;
	}

	@Override
	public CommonResult<String> disableOrEnabledMemberSalesById(String saleId, String saleStatus) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			this.memberSalesMapper.disableOrEnabledMemberSalesById(saleId,saleStatus);
			result.setCode(0);
			result.setData("success");
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setData("error");
			result.setMsg("error");
		}
		return result;
	}

	@Override
	public CommonResult<String> sendSalesMsg(String saleId, String memberTageIds) {
		CommonResult<String> result = new CommonResult<>();
		try {
			if(StringUtils.isEmpty(saleId)){
				result.setCode(2);
				result.setData("saleId is null");
				result.setMsg("saleId is null");
				return result;
			}
			if(StringUtils.isEmpty(memberTageIds)){
				result.setCode(3);
				result.setData("memberTageIds is null");
				result.setMsg("memberTageIds is null");
				return result;
			}
			List<String> list = Arrays.asList(memberTageIds.split(","));
			if(list != null && list.size() > 0){
				//1.根据营销活动id查询营销活动是否开启、营销活动发送方式
				MemberSales memberSales = this.memberSalesMapper.queryById(Integer.parseInt(saleId));
				if(memberSales != null){
					// 对比营销活动是否开启
					Date date = new Date();
					if(date.getTime() < memberSales.getSaleStartTime().getTime()){
						result.setCode(4);
						result.setData("营销活动未开始");
						result.setMsg("营销活动未开始");
						return result;
					}
					if(date.getTime() > memberSales.getSaleEndTime().getTime()){
						result.setCode(5);
						result.setData("营销活动已结束");
						result.setMsg("营销活动已结束");
						return result; 
					}
					//2.根据标签查询  如果营销活动开启则根据标签id查询
					UserTagsEntity entity = new UserTagsEntity();
					entity.setTagids(list);
					if(StringUtils.isNotEmpty(memberSales.getSaleSendMsgType()) && "SMS".equals(memberSales.getSaleSendMsgType())){
						entity.setMemberPhoneNotNull("Y");
					}else {
						entity.setMemberOpenIdNotNull("Y");
					}
					List<MemberSendSalesInfo> sendMemberList = this.memberSalesMapper.querySendMemberList(entity);
					if(sendMemberList != null && sendMemberList.size() > 0){
						//3.新增营销流水记录
						List<MemberSalesLog> memberSalesLogs = new ArrayList<MemberSalesLog>();
						for(MemberSendSalesInfo member : sendMemberList){
							MemberSalesLog memberSalesLog = new MemberSalesLog();
							memberSalesLog.setMemberId(member.getMemberId());
							memberSalesLog.setMemberOpenid(member.getOpenid());
							memberSalesLog.setMemberPhoneNumber(member.getMobilePhone());
							memberSalesLog.setSaleId(memberSales.getSaleId());
							memberSalesLog.setIsIntoAct("N");
							memberSalesLog.setCreateTime(date);
							memberSalesLog.setLastUpdateTime(date);
							memberSalesLog.setSaleSendMsgType(memberSales.getSaleSendMsgType());
							memberSalesLogs.add(memberSalesLog);
						}
						if(memberSalesLogs.size() > 0){
							this.memberSalesLogMapper.inserSaleLog(memberSalesLogs);
						}
						//4.新营销发送批次记录
						MemberSalesSendBatch batch = new MemberSalesSendBatch();
						batch.setSaleId(memberSales.getSaleId());
						batch.setInviteNum(sendMemberList.size());
						batch.setLastUpdateTime(date);
						batch.setSaleMsgSendTime(date);
						batch.setTagsId(memberTageIds);
						this.memberSalesSendBatchMapper.inserSaleSendBatch(batch);
						//5.根据会员信息异步发送营销信息
						ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
						for (MemberSendSalesInfo member : sendMemberList) {
							cachedThreadPool.submit(new SendSalesMsgThread(member,memberSales,sendSmsService));
						}
					}else {
						result.setCode(6);
						result.setData("未匹配到目标用户");
						result.setMsg("未匹配到目标用户");
						return result;
					}
				}
			}
			result.setCode(0);
			result.setData("success");
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setData("error");
			result.setMsg("error");
		}
		return result;
	}
}
