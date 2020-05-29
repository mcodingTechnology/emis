package com.mcoding.emis.goods.card.service;

import java.util.ArrayList;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.member.common.CommonResult;

public interface CardTypeService extends BaseService<CardType, String> {
	
	/**
	 * 创建卡的类型和对应的卡
	 * @param cardType
	 * @return
	 */
	public CommonResult<String> addCardTypeAndCard(CardType cardType);

	/**
	 * 给原有的卡增加投放量
	 * @param cardId
	 * @param count
	 * @return
	 */
	public CommonResult<String> addCardTypeCount(int cardId, int count);
	
	/**
	 * 消费一张卡后，修改使用卡的数字
	 * @param cardTypeId
	 * @return
	 */
	public CommonResult<String> addCardTypeQuantityById(int cardTypeId);
	
	/**
	 * 禁用某类卡券
	 * @param cardTypeId
	 * @return
	 */
	public CommonResult<String> disableCardType(int cardTypeId);

	/**
	 * 根据券码查询出卡券类型
	 * @param cardCode
	 * @return
	 */
	public CommonResult<CardType> queryObjByCardCode(String cardCode);
	
	/**
	 * 根据查询条件 得出卡券类型
	 * @param example
	 * @return
	 */
	public CommonResult<ArrayList<CardType>> queryObjByExample(CardTypeExample example);
	
	/**
	 * 创建特权卡的类型和对应的券码
	 * @param cardType
	 * @return
	 */
	public JsonResult<String> addPrivilegeCardTypeAndCard(CardType cardType);

//	/**
//	 * 获取卡券的卡码
//	 * @param cardTypeId
//	 * @param startNo
//	 * @param endNo
//	 * @return
//	 */
//	public CommonResult<ArrayList<String>> getAllCardNo(int cardTypeId, int startNo, Integer endNo);
//	
//	/**
//	 * 获取卡券的卡码
//	 * @param cardTypeId
//	 * @param startNo
//	 * @param endNo
//	 * @return
//	 */
//	public CommonResult<ArrayList<String>> getAllCardNo(int cardTypeId, int startNo);

	PageView<CardType> queryObjByPage(String iDisplayStart, String iDisplayLength,String pageNo,String pageSize,String cardName,String prefix);

}
