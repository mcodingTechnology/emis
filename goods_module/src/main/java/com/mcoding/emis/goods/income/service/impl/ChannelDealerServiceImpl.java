package com.mcoding.emis.goods.income.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.persistence.ChannelDealerMapper;
import com.mcoding.emis.goods.income.service.ChannelDealerService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class  ChannelDealerServiceImpl implements ChannelDealerService{
	private static final Logger log = Logger.getLogger(IncomeProductServiceImpl.class);
	
	
	@Autowired
	protected ChannelDealerMapper channelDealerMapper;
	
	
	
	 
	@Override
	public PageView<ChannelDealer> queryChanneldealerListByPage(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		PageView<ChannelDealer> pageView = new PageView<ChannelDealer>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);
		String dealerName = request.getParameter("dealerName");
		if(StringUtils.isNotEmpty(dealerName)){
			param.put("dealerName", "%"+dealerName+"%");
		}
		 List<ChannelDealer> channelDealerProducts = channelDealerMapper.queryChannelListByPage(param);
	     pageView.setQueryResult(channelDealerProducts);
	     return pageView;
	}
	@Override
	public CommonResult<String> addchannel(ChannelDealer req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			 Date date=new Date();
			 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=format.format(date);
			 Date date1 = format.parse(time);
			 req.setCreateTime(date1); //创建时间
			 req.setLastUpdateTime(date1); //修改时间
			 
			 channelDealerMapper.addChannel(req);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	public CommonResult<ChannelDealer> selectChannelById(int tagsId) {
		CommonResult<ChannelDealer> commonResult = new CommonResult<ChannelDealer>();
		try {
			int id = tagsId;
			ChannelDealer com = channelDealerMapper.selectChannelById(id);
			commonResult.setCode(0);
			commonResult.setData(com);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public CommonResult<String> updateChannel(ChannelDealer req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			 Date date=new Date();
			 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=format.format(date);
			 Date date1 = format.parse(time);
			 req.setLastUpdateTime(date1);
			channelDealerMapper.updateChannel(req);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> deleteChannel(int tagsId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			int id = tagsId ;
			channelDealerMapper.deleteChannelById(id);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}
	
	@Override
	public PageView<Member> queryMemberListByPage(HttpServletRequest request, String iDisplayStart,String iDisplayLength) {
		PageView<Member> pageView = new PageView<Member>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);
		String fullName = request.getParameter("fullName");
		if(StringUtils.isNotEmpty(fullName)){
			param.put("fullName", "%"+fullName+"%");
		}
		List<Member> channelDealerProducts = channelDealerMapper.queryMemberListByPage(param);
	    pageView.setQueryResult(channelDealerProducts);
	    return pageView;
	}

}
