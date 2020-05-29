package com.mcoding.emis.goods.icon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.icon.bean.Icon;
import com.mcoding.emis.goods.icon.bean.IconExample;
import com.mcoding.emis.goods.icon.persistence.IconMapper;
import com.mcoding.emis.goods.icon.service.IconService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class IconServiceImpl implements IconService {

	
	@Autowired
	protected IconMapper iconMapper;

	@Override
	public CommonResult<String> addObj(Icon t) {
		
		IconExample ex = new IconExample();
		IconExample.Criteria cri = ex.createCriteria();
		cri.andIconCodeEqualTo(t.getIconCode());
		cri.andIsValidEqualTo("1");
		List<Icon> list = this.iconMapper.selectByExampleByPage(ex);
		if(CollectionUtils.isNotEmpty(list)) {
			CommonResult<String> result = new CommonResult<String>();
			result.setCode(1);
			result.setMsg("图标编码不能重复");
			return result;
		}
		
		t.setIsValid("1");
		this.iconMapper.insertSelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(String.valueOf(t.getId()));
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		
		Icon t = new Icon();
		t.setId(Integer.valueOf(id));
		t.setIsValid("0");
		t.setUpdateTime(new Date());
		this.iconMapper.updateByPrimaryKeySelective(t);
		
//		this.iconMapper.deleteByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(Icon t) {
		t.setUpdateTime(new Date());
		this.iconMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<Icon> queryObjById(String id) {
		Icon icon = this.iconMapper.selectByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<Icon> result = new CommonResult<Icon>();
		result.setCode(0);
		result.setData(icon);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<Icon>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Icon>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Icon> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		PageView<Icon> pageView = new PageView<Icon>(iDisplayStart, iDisplayLength);
		
		IconExample ex = new IconExample();
		IconExample.Criteria cri = ex.createCriteria();
		cri.andIsValidEqualTo("1");
		ex.setPageView(pageView);
		ex.setOrderByClause("create_time DESC");
		
		List<Icon> list = this.iconMapper.selectByExampleByPage(ex);
		pageView.setQueryResult(list);
		
		return pageView;
	}

}