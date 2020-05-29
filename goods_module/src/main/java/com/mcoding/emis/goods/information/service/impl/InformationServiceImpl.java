package com.mcoding.emis.goods.information.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.information.bean.Information;
import com.mcoding.emis.goods.information.bean.InformationExample;
import com.mcoding.emis.goods.information.persistence.InformationMapper;
import com.mcoding.emis.goods.information.service.InformationService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class InformationServiceImpl implements InformationService {
	
	@Autowired
	protected InformationMapper informationMapper;

	@Override
	public CommonResult<String> addObj(Information record) {
		this.informationMapper.insertSelective(record);
		Integer id = record.getId();
		
		CommonResult<String> result = new CommonResult<String>();
		result.setData(String.valueOf(id));
		result.setMsg("ok");
		result.setCode(0);
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.informationMapper.deleteByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(Information t) {
		this.informationMapper.updateByPrimaryKeySelective(t);
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		
		return result;
	}

	@Override
	public CommonResult<Information> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<Information>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Information>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Information> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<Information>> queryListByOpenId(String openId) {
		InformationExample ex = new InformationExample();
		ex.createCriteria().andReceiveropenidEqualTo(openId);
		ex.setOrderByClause("status DESC , createTime DESC");
		
		List<Information> list = this.informationMapper.selectByExample(ex);
		ArrayList<Information> tmp = new ArrayList<Information>(list.size());
		CollectionUtils.addAll(tmp, list.iterator());
		
		CommonResult<ArrayList<Information>> result = new CommonResult<ArrayList<Information>>();
		result.setData(tmp);
		result.setMsg("ok");
		
		return result;
	}

	@Override
	public CommonResult<String> setInformationReaded(Integer id, String openid) {
		Information information = new Information();
		information.setStatus(Information.STATUS_READED);
		information.setReadtime(new Date());
		
		InformationExample ex = new InformationExample();
		ex.createCriteria().andIdEqualTo(id).andReceiveropenidEqualTo(openid);
		
		this.informationMapper.updateByExampleSelective(information, ex);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public Boolean findNewInformations(String openid) {
		InformationExample ex = new InformationExample();
		ex.createCriteria()
		  .andReceiveropenidEqualTo(openid)
		  .andStatusEqualTo(Information.STATUS_UNREADED);
		
		int size = this.informationMapper.countByExample(ex);
		
		return size > 0;
		
	}

	@Override
	public CommonResult<ArrayList<Information>> queryListByExample(InformationExample ex) {
		
		List<Information> list = this.informationMapper.selectByExample(ex);
		ArrayList<Information> tmp = new ArrayList<Information>(list.size());
		CollectionUtils.addAll(tmp, list.iterator());
		
		CommonResult<ArrayList<Information>> result = new CommonResult<ArrayList<Information>>();
		result.setData(tmp);
		result.setMsg("ok");
		
		return result;
	}

}
