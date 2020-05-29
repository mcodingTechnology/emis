package com.mcoding.emis.goods.label.service.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.label.entity.LabelEntity;
import com.mcoding.emis.goods.label.entity.UserTagsEntity;
import com.mcoding.emis.goods.label.persistence.LabelPointMapper;
import com.mcoding.emis.goods.label.service.LabelPointService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class LabelPointSerciceImpl implements LabelPointService{

	@Resource
	private LabelPointMapper labelPoinMapper;
	
	@Override
	public PageView<LabelEntity> queryLabelPointDataByPage(HttpServletRequest request, String iDisplayStart, String iDisplayLength) {
		PageView<LabelEntity> pageView = new PageView<LabelEntity>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);
		String tagsName = request.getParameter("tagsName");
		if(StringUtils.isNotEmpty(tagsName)){
			param.put("tagsName", "%"+tagsName+"%");
		}
		List<LabelEntity> healthCriterias = labelPoinMapper.selectLabelByPageListByPage(param);
		pageView.setQueryResult(healthCriterias);
		return pageView;
	}

	
	@Override
	@Transactional
	public CommonResult<String> addLabel(LabelEntity req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			 Date date=new Date();
			 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=format.format(date);
			 Date date1 = format.parse(time);
			 req.setCreateTime(date1);
			labelPoinMapper.insertLabel(req);
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
	public CommonResult<LabelEntity> selectLabelById(int tagsId) {
		
		CommonResult<LabelEntity> commonResult = new CommonResult<LabelEntity>();
		try {
			LabelEntity com = labelPoinMapper.selectLabelById(tagsId);
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
	public CommonResult<String> updateLabel(LabelEntity req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			Date date=new Date();
			 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=format.format(date);
			 Date date1 = format.parse(time);
			 req.setLastUpdateTime(date1);
			labelPoinMapper.updateLabel(req);
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
	public CommonResult<String> deleteLabelById(int tagsId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			labelPoinMapper.deleteLabelById(tagsId);
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
	public CommonResult<String> addUserTags(Integer productId[],String fullName,String openid , int memberId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			 Date date=new Date();
			 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=format.format(date);
			 Date date1 = format.parse(time);
			labelPoinMapper.deleteUserTagsById(memberId); //删除重复id
			for (Integer id : productId) {
				UserTagsEntity t = new UserTagsEntity();
				t.setTagid(id);
				t.setOpenid(openid);
				t.setObjId(memberId);
				t.setCreatetime(date1);
				labelPoinMapper.insertUserTags(t);
			}
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

    //根据Id给会员用户贴标
	@Override
	public CommonResult<Member> UserTagsEntityById(int memberId){
		CommonResult<Member> commonResult = new CommonResult<Member>();
		try {
			Member com = labelPoinMapper.selectmemberById(memberId);
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

	
	//根据会员id查询 贴过的标签
	@Override
	public CommonResult<ArrayList<UserTagsEntity>> selectUserTagsById(int memberId) {
		CommonResult <ArrayList<UserTagsEntity>> commonResult = new CommonResult<ArrayList<UserTagsEntity>>();
		try {
			int objId = memberId;
			ArrayList<UserTagsEntity> com = labelPoinMapper.selectUserTagsListById(objId);
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


	
	
	
	
	
	
	
}
