package com.mcoding.emis.goods.information.service;

import java.util.ArrayList;

import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.information.bean.Information;
import com.mcoding.emis.goods.information.bean.InformationExample;
import com.mcoding.emis.member.common.CommonResult;

public interface InformationService extends BaseService<Information, String>{
	
	/**
	 * 根据Openid 查询出用户的所有消息
	 * @param openId
	 * @return
	 */
	public CommonResult<ArrayList<Information>> queryListByOpenId(String openId);
	
	/**
	 * 通过消息id，设置消息为已读
	 * @param id
	 * @param openid 
	 * @return
	 */
	public CommonResult<String> setInformationReaded(Integer id, String openid);
	
	/**
	 * 查是否有新消息
	 * @param openid
	 * @return
	 */
	public Boolean findNewInformations(String openid);
	
	/**
	 * 根据查询调价查询消息列表
	 * @param ex
	 * @return
	 */
	public CommonResult<ArrayList<Information>> queryListByExample(InformationExample ex);

}
