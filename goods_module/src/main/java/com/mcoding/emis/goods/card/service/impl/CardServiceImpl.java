package com.mcoding.emis.goods.card.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mcoding.emis.goods.card.bean.CardPrivilegeCheckRecord;
import com.mcoding.emis.goods.card.service.CardPrivilegeCheckRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.card.utils.CardUtils;
import com.mcoding.emis.goods.common.utils.StringUtil;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class CardServiceImpl implements CardService {
	
	/**券码为正常状态**/
	public static final int CARD_CODE_NORMAL = 0;
	/**券码不能为空**/
	public static final int CARD_CODE_NULL = 1;
	/**该券不存在**/
	public static final int CARD_CODE_NOT_EXIST = 2;
	/**该券已过期**/
	public static final int CARD_CODE_EXPIRED = 3;
	/**该券已被禁用**/
	public static final int CARD_CODE_DISABLE = 4;
	/**该券已被兑换且未创建订单**/
	public static final int CARD_CODE_EXCHANGED_NO_ORDER = 5;
	/**该券已被兑换且创建订单，但是未支付**/
	public static final int CARD_CODE_EXCHANGED_WITH_ORDER = 6;
	/**该券已被兑换且创建订单，且已支付**/
	public static final int CARD_CODE_USED = 7;
	/**未知状态**/
	private static final int CARD_CODE_UN_KNOWN = 8;
	
	/**特权卡单张未使用状态**/
	private static final String PRIVILEGE_CARD_SINGLE_UN_EXCHAGED = "SINGLE_UN_EXCHAGED";
	/**特权卡多张未使用状态**/
	private static final String PRIVILEGE_CARD_MANY_UN_EXCHAGED = "MANY_UN_EXCHAGED";
	/**特权卡单张使用状态**/
	private static final String PRIVILEGE_CARD_SINGLE_EXCHAGED = "SINGLE_USED";
	/**特权卡多张使用状态**/
	private static final String PRIVILEGE_CARD_MANY_EXCHAGED = "MANY_USED";
	
	@Autowired
	protected CardMapper cardMapper;
	
	@Autowired
	protected CardTypeService cardTypeService;
	
	@Autowired
	protected OrderMapper orderMapper;

	@Autowired
	protected CardPrivilegeCheckRecordService cardPrivilegeCheckRecordService;

	@Override
	public CommonResult<String> addObj(Card t) {
		this.cardMapper.insertSelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(null);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(Card t) {
		this.cardMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(null);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<Card> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<Card>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Card>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Card> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> addCardListByCardType(CardType cardType, int cardCount) {
		List<Card> cardList = new ArrayList<Card>();
		
		Set<String> randomCodeList = CardUtils.getCodeSetForLength(6, cardCount);
		Iterator<String> iterator = randomCodeList.iterator();
		
		Date now = new Date();
		while(iterator.hasNext()){
			String randomCode = iterator.next();
			Card card = new Card();
			card.setCardtypeid(cardType.getId());
			card.setCreatetime(now);
			card.setCardtypename(cardType.getName());
			
			String cardCode = cardType.getCode() + randomCode;
			card.setCardcode(cardCode);
			card.setIsvalid(CardType.IS_VALID_YES);
			card.setExt1(Card.CARD_STATUS_UN_EXCAHGED);
			cardList.add(card);
		}
		
		this.cardMapper.insertList(cardList);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public PageView<Card> queryObjByExampleByPage(CardExample example, String iDisplayStart, String iDisplayLength) {
		PageView<Card> pageView = new PageView<Card>(iDisplayStart, iDisplayLength);
		example.setPageView(pageView);;
		
		List<Card> cardList = this.cardMapper.selectByExampleByPage(example);
		pageView.setPageSize(cardList.size());
		pageView.setQueryResult(cardList);
		
		return pageView;
	}

	/**
	 * 核销卡券
	 */
	@Override
	public CommonResult<String> consumeCard(String cardCode, String openid) {
		CommonResult<String> result = new CommonResult<String>();
		
		//1、检查是否看可用
		CommonResult<Card> cardStatus = this.checkCardStatus(cardCode);
		if(CARD_CODE_NORMAL != cardStatus.getCode() 
				&& CARD_CODE_EXCHANGED_NO_ORDER != cardStatus.getCode()){ 
			//如果不可用返回状态
			result.setCode(cardStatus.getCode());
			result.setMsg(cardStatus.getMsg());
			return result;
		}
		
		//如果未兑换，或者兑换但没有生成订单，才能核销
		
		//2、如果可用，就更新状态
		Card tmp = new Card();
		tmp.setOpenid(openid);
		tmp.setUsetime(new Date());
		tmp.setExt1(Card.CARD_STATUS_USED); //更新已使用
		
		CardExample ex = new CardExample();
		ex.createCriteria()
		  .andCardcodeEqualTo(cardCode);
		
		this.cardMapper.updateByExampleSelective(tmp, ex);
		
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObjByExample(CardExample ex, Card card) {
		card.setId(null);
		this.cardMapper.updateByExampleSelective(card, ex);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}
	
	@Override
	public CommonResult<Card> checkCardStatus(String cardCode) {
		CommonResult<Card> result = new CommonResult<Card>();
		if(StringUtils.isBlank(cardCode)){
			result.setCode(CARD_CODE_NULL);
			result.setData(null);
			result.setMsg("券码不能为空");
			return result;
		}
		
		CardExample cardEx = new CardExample();
		cardEx.createCriteria()
		  .andCardcodeEqualTo(cardCode);
		
		List<Card> cardList = this.cardMapper.selectByExample(cardEx);
		
		if(cardList == null || cardList.size() == 0){
			result.setCode(CARD_CODE_NOT_EXIST);
			result.setData(null);
			result.setMsg("该券不存在");
			return result;
		}
		Card card = cardList.get(0);
		CardType cardType = this.cardTypeService.queryObjById(card.getCardtypeid().toString()).getData();
		
		Date now = new Date();
		if(cardType.getEndtime()!=null && cardType.getEndtime().getTime() < now.getTime()){
			
			result.setCode(CARD_CODE_EXPIRED);
			result.setData(card);
			result.setMsg("该券已过期");
			return result;
		}
		
		if(!CardType.IS_VALID_YES.equals(card.getIsvalid())){
			result.setCode(CARD_CODE_DISABLE);
			result.setData(card);
			result.setMsg("该券已被禁用");
			return result;
		}
		
		if(card.getExt1() == null){
			//如果没有被兑换过
			result.setCode(CARD_CODE_NORMAL);
			result.setData(card);
			result.setMsg("ok");
			return result;
		}
		
		//根据卡券状态
		switch (card.getExt1()) {
			
		case Card.CARD_STATUS_UN_EXCAHGED:
			//如果没有被兑换过
			result.setCode(CARD_CODE_NORMAL);
			result.setData(card);
			result.setMsg("ok");
			return result;
			
		case Card.CARD_STATUS_EXCAHGED:
            //该券已被兑换,检查是否已经生成订单
			OrderExample orderEx = new OrderExample();
			OrderExample.Criteria cri1 = orderEx.createCriteria().andCardidEqualTo(card.getId());
			OrderExample.Criteria cri2 = orderEx.createCriteria().andCardcodeEqualTo(cardCode);
			
			orderEx.or(cri2);
			
			//TODO
			int count = this.orderMapper.countByExample(orderEx);
			if(count > 0){
				result.setCode(CARD_CODE_EXCHANGED_WITH_ORDER);
				result.setData(card);
				result.setMsg("该券已被兑换，但未使用,但创建了订单");
			}else{
				result.setCode(CARD_CODE_EXCHANGED_NO_ORDER);
				result.setData(card);
				result.setMsg("该券已被兑换，但未使用");
			}
			
			return result;
			
		case Card.CARD_STATUS_USED:
			result.setCode(CARD_CODE_USED);
			result.setData(card);
			result.setMsg("该券已被兑换且使用");
			return result;
			
		default:
			result.setCode(CARD_CODE_UN_KNOWN);
			result.setData(card);
			result.setMsg("未知，请联系客服");
			return result;
		}
	}

	@Override
	public CommonResult<String> exchangeCard(String cardCode, String openid) {
		CommonResult<String> result = new CommonResult<String>();

//		CommonResult<Card> cardStatus = this.checkCardStatus(cardCode);
		/*if(CARD_CODE_NORMAL != cardStatus.getCode()){
			//如果不可用返回状态
			result.setCode(cardStatus.getCode());
			result.setMsg(cardStatus.getMsg());
			return result;
		}*/

		// 2、如果可用，就更新状态
		Card tmp = new Card();
//		tmp.setId(cardStatus.getData().getId());
		tmp.setOpenid(openid);
		tmp.setUsetime(new Date());
		tmp.setExt1(Card.CARD_STATUS_EXCAHGED);//更新为已兑换
		
//		this.cardMapper.updateByPrimaryKeySelective(tmp);
		
		CardExample example = new CardExample();
		example.createCriteria().andCardcodeEqualTo(cardCode);
		this.cardMapper.updateByExampleSelective(tmp, example);
		
		result.setCode(0);
		result.setMsg("ok");
		return result;

	}

	@Override
	public CommonResult<String> consumeCardForOrder(String cardCode, String openid, Integer orderId) {
		CommonResult<String> result = new CommonResult<String>();
		
		CardExample ex = new CardExample();
		ex.createCriteria().andCardcodeEqualTo(cardCode);
		
		Card card = this.cardMapper.selectByExample(ex).get(0);
		
		//2、如果可用，就更新状态
		Card tmp = new Card();
		tmp.setOpenid(openid);
		tmp.setUsetime(new Date());
		tmp.setExt1(Card.CARD_STATUS_USED); // 更新已使用
		tmp.setExt2(String.valueOf(orderId));
		this.cardMapper.updateByExampleSelective(tmp, ex);
        
		
		Order order = new Order();
		order.setId(orderId);
		order.setCardid(card.getId());
		order.setCardcode(cardCode);
		order.setCardtypename(card.getCardtypename());
		this.orderMapper.updateByPrimaryKeySelective(order);
		
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> addPrivilegeCardListByCardType(
			CardType cardType, int cardCount) {
		List<Card> cardList = new ArrayList<Card>();
		
		Set<String> randomCodeList = CardUtils.getCodeSetForLength(6, cardCount);
		Iterator<String> iterator = randomCodeList.iterator();
		
		Date now = new Date();
		while(iterator.hasNext()){
			String randomCode = iterator.next();
			Card card = new Card();
			card.setCardtypeid(cardType.getId());
			card.setCreatetime(now);
			card.setCardtypename(cardType.getName());
			
			String cardCode = cardType.getCode() + randomCode;
			card.setCardcode(cardCode);
			card.setIsvalid(CardType.IS_VALID_YES);
			card.setExt1(Card.CARD_STATUS_SINGLE_UN_EXCAHGED);
			card.setExt2(Card.CARD_STATUS_MANY_UN_EXCAHGED);
			cardList.add(card);
		}
		
		this.cardMapper.insertList(cardList);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public JsonResult<List<Card>> verifyPrivilegeCard(String[] cardcode,String user) {
		JsonResult<List<Card>> result = new JsonResult<List<Card>>();
		if (cardcode.length==0) {
			result.setStatus("error");
			result.setMsg("券码不能为空");
			return result;
		}
		try {
			// 1、若是单张特权卡，检查该卡的状态
			if(cardcode.length == 1){
				CardExample example = new CardExample();
				CardExample.Criteria criteria = example.createCriteria();
				criteria.andCardcodeEqualTo(cardcode[0]);
				List<Card> cardlist = cardMapper.selectByExample(example);
				
				if(cardlist.size() >0){
					Card card = cardlist.get(0);
					if(card.getExt1().equals(PRIVILEGE_CARD_SINGLE_UN_EXCHAGED)){
						//可兑换
						card.setExt1(PRIVILEGE_CARD_SINGLE_EXCHAGED);
						card.setUsetime(new Date());
						cardMapper.updateByPrimaryKeySelective(card);

						//添加特权卡核销记录
						CardType cardType = this.cardTypeService.queryObjById(card.getCardtypeid().toString()).getData();
						if (CardType.CARD_TYPE_PRIVILEGE.equals(cardType.getCardtype())) {
							if (StringUtils.isEmpty(user)) {
								result.setStatus("error");
								result.setMsg("请输入客户id");
								return result;
							}
						}
						CardPrivilegeCheckRecord record = new CardPrivilegeCheckRecord();
						record.setCreateTime(new Date());
						record.setBrandCode(cardType.getExt2());
						record.setCardId(card.getId());
						record.setCardName(cardType.getName());
						record.setCodePrefix(cardType.getCode());
						record.setCardTypeId(card.getCardtypeid());
						record.setCode(card.getCardcode());
						record.setCodeSuffix(card.getCardcode().replace(cardType.getCode(),""));
						record.setUser(user);
						cardPrivilegeCheckRecordService.addObj(record);
						
						result.setData(null);
						result.setStatus("00");
						result.setMsg("已兑换成功，该卡为"+card.getCardtypename()+"。");
					}else {
						result.setData(null);
						result.setStatus("01");
						result.setMsg("特权卡已被兑换过，不可重复兑换。该卡为"+card.getCardtypename()+"。");
					}
				}else {
					result.setData(null);
					result.setStatus("01");
					result.setMsg("此特权卡号码不存在，请重新输入。");
				}
			} else if(cardcode.length == 5) {
				//判断输入卡号的是否有重复
			    if(StringUtil.compareStrArray(cardcode)==true){
				   result.setData(null);
					result.setStatus("01");
					result.setMsg("特权卡号码不能重复，请检查清楚后重新输入。");
					return result;
			    }
			    //String cardStr = "";
				for (String code : cardcode) {
					CardExample example = new CardExample();
					CardExample.Criteria criteria = example.createCriteria();
					criteria.andCardcodeEqualTo(code);
					List<Card> cardlist = cardMapper.selectByExample(example);
					if(cardlist.size() ==0){
						result.setData(null);
						result.setStatus("01");
						result.setMsg("卡号："+code+"，此特权卡号码不存在，请重新输入。");
						return result;
					}
				}
				//查询卡号类型是否相同或是否已被兑换
				List<Card> cardlists = cardMapper.verifyCard(Arrays.asList(cardcode));
				System.out.println(cardlists); 
				if(cardlists.size() >0){
					result.setData(cardlists);
					result.setStatus("02");
					result.setMsg("错误");
					return result;
				}else {
					//批量更新特权卡
					cardMapper.batchUpdate(Arrays.asList(cardcode));
					result.setData(null);
					result.setStatus("00");
					result.setMsg("五张特权卡已兑换成功。【特权】下单立减100元超大优惠，订单不满100元全免");
				}
			}
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

}
