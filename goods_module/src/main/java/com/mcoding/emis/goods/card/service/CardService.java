package com.mcoding.emis.goods.card.service;

import java.util.List;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.member.common.CommonResult;

public interface CardService extends BaseService<Card, String> {
	
	/**
	 * 根据查询条件修改
	 * @param ex
	 * @param card
	 * @return
	 */
	public CommonResult<String> modifyObjByExample(CardExample ex, Card card);
	
	/**
	 * 核销卡券
	 * @param cardCode
	 * @param openid
	 * @return
	 */
	public CommonResult<String> consumeCard(String cardCode, String openid);
	
	/**
	 * 核销卡券
	 * @param cardCode
	 * @param openid
	 * @return
	 */
	public CommonResult<String> consumeCardForOrder(String cardCode, String openid, Integer orderId);
	
	/**
	 * 兑换卡券
	 * @param cardCode
	 * @param openid
	 * @return
	 */
	public CommonResult<String> exchangeCard(String cardCode, String openid);
	
	/**
	 * 为相关的卡券添加卡码
	 * @param cardType
	 * @param cardCount
	 * @return
	 */
	public CommonResult<String> addCardListByCardType(CardType cardType, int cardCount);

	/**
	 * 根据分页把数据查出来
	 * @param example
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	public PageView<Card> queryObjByExampleByPage(CardExample example, String iDisplayStart, String iDisplayLength);
	
	/**
	 * 检查卡码是否可用
	 * @param cardId
	 * @return
	 */
	public CommonResult<Card> checkCardStatus(String cardCode);
	
	/**
	 * 增加对应数量的特权卡券码
	 * @param cardType
	 * @param cardCount
	 * @return
	 */
	public CommonResult<String> addPrivilegeCardListByCardType(CardType cardType, int cardCount);
	
	/**
	 * 校验特权卡
	 * @param cardType
	 * @param cardCount
	 * @return
	 */
	public JsonResult<List<Card>> verifyPrivilegeCard(String[] cardcode,String user);
	

}
