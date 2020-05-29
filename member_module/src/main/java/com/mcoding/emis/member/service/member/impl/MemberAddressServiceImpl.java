package com.mcoding.emis.member.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.bean.member.MemberAddressExample;
import com.mcoding.emis.member.bean.member.MemberAddressExample.Criteria;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.EmisMemberAddressService;
import com.mcoding.emis.member.service.member.MemberAddressService;

@Service("MemberAddressServiceFromEmis")
public class MemberAddressServiceImpl  implements MemberAddressService{
	
	@Resource
	protected EmisMemberAddressService emisMemberAddressService;

	@Override
	public CommonResult<MemberAddress> queryObjById(String id) {
		
		MemberAddress address = this.emisMemberAddressService.queryObjById(Integer.valueOf(id));
		
		CommonResult<MemberAddress> result = new CommonResult<MemberAddress>();
		result.setCode(0);
		result.setData(address);
		result.setMsg("ok");
		return result;
	}

	@Override
	public JsonResult<MemberAddress> queryByOpenId(String openid) {
		MemberAddressExample example = new MemberAddressExample();
		example.createCriteria().andOpenidEqualTo(openid);
		example.setOrderByClause("id desc");
		
		List<MemberAddress> list = this.emisMemberAddressService.queryAllObjByExample(example);
		
		
		JsonResult<MemberAddress> result = new JsonResult<MemberAddress>();
		if (CollectionUtils.isNotEmpty(list)) {
			result.setData(list.get(0));
			result.setStatus("00");
			result.setMsg("操作成功");
		}else {
			result.setStatus("03");
			result.setMsg("操作失败");
		}
		return result;
	}

	@Transactional
	@Override
	public JsonResult<String> add(MemberAddress memberAddress, String openid) {
		JsonResult<String> result = new JsonResult<String>();
		try {
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andOpenidEqualTo(openid);
			
			this.emisMemberAddressService.deleteObjByExample(example);
			memberAddress.setOpenid(openid);
			this.emisMemberAddressService.addObj(memberAddress);
			
			result.setStatus("00");
			result.setMsg("操作成功");

		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	@Override
	public JsonResult<String> update(MemberAddress memberAddres, String openid) {
		JsonResult<String> result = new JsonResult<String>();
		
		try {
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andOpenidEqualTo(openid);
			
//			this.emisMemberAddressMapper.updateByExampleSelective(memberAddres, example);
			this.emisMemberAddressService.modifyObj(memberAddres, example);
			result.setStatus("00");
			result.setMsg("操作成功");
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@Override
	public JsonResult<String> delete(String openid) {
		JsonResult<String> result = new JsonResult<String>();
		try {
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andOpenidEqualTo(openid);
			this.emisMemberAddressService.deleteObjByExample(example);
			result.setMsg("操作成功");
			result.setStatus("00");
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@Override
	public PageView<MemberAddress> queryMemberAddressData(String iDisplayStart, String iDisplayLength, String sSearch,
			String mobilePhone, String pageNo) {
		PageView<MemberAddress> tmpPageView = new PageView<>(iDisplayStart, iDisplayLength);
		MemberAddressExample example = new MemberAddressExample();
		Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(sSearch)) {
			mobilePhone = sSearch;
		}
		if (StringUtils.isNotBlank(mobilePhone)) {
			cri.andPhoneLike(mobilePhone + "%");
		}
		
		example.setPageView(tmpPageView);
		PageView<MemberAddress> list = this.emisMemberAddressService.queryObjByPage(example);
		
		PageView<MemberAddress> pageView = new PageView<MemberAddress>(iDisplayStart, iDisplayLength);
		pageView.setRowCount(list.getRowCount());
        pageView.setQueryResult(list.getQueryResult());
        return pageView;
	}

}
