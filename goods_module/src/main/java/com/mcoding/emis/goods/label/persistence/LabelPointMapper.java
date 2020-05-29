package com.mcoding.emis.goods.label.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.goods.label.entity.LabelEntity;
import com.mcoding.emis.goods.label.entity.UserTagsEntity;

public interface LabelPointMapper {

	/**
	 * List
	 * @param labelEntity
	 * @return
	 */
	List<LabelEntity> selectLabelByPageListByPage(Map<String, Object> param);
	
	
	/**
	 * 添加标签
	 * @param req
	 * @return
	 */
	int insertLabel(LabelEntity req);
	
	
	/**
	 * 根据id 查询
	 * @param warehouseId
	 * @return
	 */
	LabelEntity selectLabelById(int tagsId);
	
	
	int updateLabel(LabelEntity labelEntity);
	
	
	int deleteLabelById(int tagsId);
	
	
	//添加贴标
	int insertUserTags(UserTagsEntity userTagsEntity);
	
	
	//查询标签
	List<LabelEntity> UserTagsEntityList();
	
	
	//查询会员id
	Member selectmemberById(int memberId);
	
	
	//根据会员id查询 贴过的标签
	ArrayList<UserTagsEntity> selectUserTagsListById(int memberId);
	
	
	//删除重复的Id
	int deleteUserTagsById(int openid);
	
}
