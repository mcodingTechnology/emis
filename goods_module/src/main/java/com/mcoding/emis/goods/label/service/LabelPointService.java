package com.mcoding.emis.goods.label.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.company.bean.Warehouse;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.goods.label.entity.LabelEntity;
import com.mcoding.emis.goods.label.entity.UserTagsEntity;
import com.mcoding.emis.member.common.CommonResult;

public interface LabelPointService {


   /***
    * 分页查询标签
    * @param label
    * @param iDisplayStart
    * @param iDisplayLength
    * @return
    */
	public PageView<LabelEntity> queryLabelPointDataByPage(HttpServletRequest request,String iDisplayStart,
			String iDisplayLength);
	
	//添加
	public CommonResult<String> addLabel(LabelEntity req);
	
	
	
	//根据id 查询
	public  CommonResult<LabelEntity> selectLabelById(int tagsId);
	
	
	//修改
	public CommonResult<String> updateLabel(LabelEntity req);
	
	
	//删除
	public CommonResult<String> deleteLabelById(int tagsId);
	
	
	//添加贴标
	public CommonResult<String> addUserTags(Integer productId[],String fullName,String openid , int memberId);
	
	
	//根据id查询会员
	public CommonResult<Member> UserTagsEntityById(int memberId);
	
	//根据会员id查询 贴过的标签
	public  CommonResult <ArrayList<UserTagsEntity>> selectUserTagsById(int memberId);
	
}
