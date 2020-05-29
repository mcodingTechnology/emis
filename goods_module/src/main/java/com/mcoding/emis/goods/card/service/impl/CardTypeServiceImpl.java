package com.mcoding.emis.goods.card.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.persistence.CardTypeMapper;
import com.mcoding.emis.goods.card.persistence.CardTypeProductRefMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.card.utils.CardUtils;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class CardTypeServiceImpl implements CardTypeService {
	
	@Autowired
	protected CardTypeMapper cardTypeMapper;

	@Autowired
	protected CardMapper cardMapper;
	
	@Autowired
	protected CardTypeProductRefMapper cardTypeProductRefMapper;
	
	@Autowired
	protected CardService cardService;

	@Override
	public CommonResult<String> addObj(CardType t) {
		this.cardTypeMapper.insertSelective(t);
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
	public CommonResult<String> modifyObj(CardType t) {
		this.cardTypeMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(null);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<CardType> queryObjById(String id) {
		CardType cardType = this.cardTypeMapper.selectByPrimaryKey(Integer.valueOf(id));
		CommonResult<CardType> result = new CommonResult<CardType>();
		result.setCode(0);
		result.setData(cardType);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<CardType>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<CardType>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<CardType> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		
		PageView<CardType> pageView = new PageView<CardType>(iDisplayStart, iDisplayLength);
		
		CardTypeExample ex = new CardTypeExample();
		ex.setPageView(pageView);
		ex.setOrderByClause("id DESC");
		
		List<CardType> list = this.cardTypeMapper.selectByExampleByPage(ex);
		
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public PageView<CardType> queryObjByPage(String iDisplayStart, String iDisplayLength,String pageNo,String pageSize,String cardName,String prefix) {

		PageView<CardType> pageView = null;
		if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			pageView = new PageView<>(iDisplayStart, iDisplayLength);
		}else {
			pageView = new PageView<>(pageNo, pageSize);
		}
		
		CardTypeExample ex = new CardTypeExample();
		ex.setPageView(pageView);
		CardTypeExample.Criteria criteria = ex.createCriteria();
		if (StringUtils.isNotBlank(prefix)) {
			criteria.andCodeLike("%" + prefix + "%");
		}
		if (StringUtils.isNotBlank(cardName)) {
			criteria.andNameLike("%" + cardName + "%");
		}

		ex.setOrderByClause("id DESC");

		List<CardType> list = this.cardTypeMapper.selectByExampleByPage(ex);

		//TODO 临时这么干
		for (CardType cardType : list) {
			cardType.setCardquantity(cardMapper.getCardSurplusById(cardType.getId()));
		}

		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public CommonResult<String> addCardTypeCount(int cardTypeId, int count) {
		CardType cardType = this.cardTypeMapper.selectByPrimaryKey(cardTypeId);
		int originCount = cardType.getCardcount();
		int totalCount = originCount + count;
		
		CardType tmp = new CardType();
		tmp.setCardcount(totalCount);
		this.cardTypeMapper.updateByPrimaryKeySelective(tmp);
		
		this.cardService.addCardListByCardType(cardType, count);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Transactional
	@Override
	public CommonResult<String> addCardTypeAndCard(CardType cardType) {
		//1、先把卡库存设置为0
		Integer cardCount = cardType.getCardcount();
		cardType.setCardcount(0);
		cardType.setCode(CardUtils.getFixLenthString(4));
		cardType.setCardquantity(cardCount);
		cardType.setCreatetime(new Date());
		this.cardTypeMapper.insertSelective(cardType);
		
		if(!cardType.getCardtype().equals("CASH")){
			
			if(cardType.getProductList() ==null || cardType.getProductList().size()==0){
				throw new NullPointerException("优惠券关联的产品列表不能为空");
			}
			for(int i=0; i<cardType.getProductList().size(); i++){
	//			CardTypeProductRef ref = new CardTypeProductRef();
	//			ref.setCardtypeid(cardType.getId());
	//			if(cardType.getProductList().get(i).getProductId() == null || cardType.getProductList().get(i).getProductId()==0){
	//				throw new NullPointerException("优惠券关联的产品id不能为空");
	//			}
	//			ref.setProductid(cardType.getProductList().get(i).getProductId());
	//			ref.setProductname(cardType.getProductList().get(i).getProductName());
				cardType.getProductList().get(i).setCardtypeid(cardType.getId());
				cardType.getProductList().get(i).setProductcount(1);
				this.cardTypeProductRefMapper.insertSelective(cardType.getProductList().get(i));
			}
			
			//2、生成需要数量的卡的数量
			this.cardService.addCardListByCardType(cardType, cardCount);
		}	
			//3、成功后添加卡的数量
			CardType tmp = new CardType();
			tmp.setId(cardType.getId());
			tmp.setCardcount(cardCount);
			this.cardTypeMapper.updateByPrimaryKeySelective(tmp);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> addCardTypeQuantityById(int cardTypeId) {
		this.cardTypeMapper.addQuantityForCardById(cardTypeId);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> disableCardType(int cardTypeId) {
		//1、禁用卡券
		CardType cardType = new CardType();
		cardType.setId(cardTypeId);
		cardType.setIsvalid(CardType.IS_VALID_NO);
		
		this.modifyObj(cardType);
		
		//2、禁用卡券下面的卡码
		CardExample ex = new CardExample();
		ex.createCriteria()
		  .andCardtypeidEqualTo(cardTypeId);
		
		Card card = new Card();
		card.setIsvalid(CardType.IS_VALID_NO);
		
		this.cardService.modifyObjByExample(ex, card);
		return null;
	}
	
	@Override
	public CommonResult<CardType> queryObjByCardCode(String cardCode){
		CardType cardType = this.cardTypeMapper.selectByCardCode(cardCode);
		
		CommonResult<CardType> result = new CommonResult<CardType>();
		result.setData(cardType);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<CardType>> queryObjByExample(CardTypeExample example) {
		List<CardType> cardTypeList = this.cardTypeMapper.selectByExample(example);
		ArrayList<CardType> tmp = new ArrayList<CardType>();
		CollectionUtils.addAll(tmp, cardTypeList.iterator());
		
		CommonResult<ArrayList<CardType>> result = new CommonResult<ArrayList<CardType>>();
		result.setCode(0);
		result.setData(tmp);
		result.setMsg("ok");
		
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> addPrivilegeCardTypeAndCard(CardType cardType) {
		//校验卡号前缀是否有重复的
		CardTypeExample example = new CardTypeExample();
		example.createCriteria().andCodeEqualTo(cardType.getCardFirstLetter());
		List<CardType> list = cardTypeMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(list)){
			JsonResult<String> result = new JsonResult<String>();
			result.setStatus("01");
			result.setMsg("卡券前缀编号已存在");
			return result;
		}

		//1、先把卡库存设置为0
		Integer cardCount = cardType.getCardcount();
		cardType.setCardcount(0);
//		cardType.setCode(cardType.getCardFirstLetter()+CardUtils.getFixLenthString(4));
		cardType.setCode(cardType.getCardFirstLetter());
		cardType.setCardquantity(cardCount);
		cardType.setCreatetime(new Date());
		this.cardTypeMapper.insertSelective(cardType);
		
		//2、生成需要数量的卡的数量
		this.cardService.addPrivilegeCardListByCardType(cardType, cardCount);
		//3、成功后添加卡的数量
		CardType tmp = new CardType();
		tmp.setId(cardType.getId());
		tmp.setCardcount(cardCount);
		this.cardTypeMapper.updateByPrimaryKeySelective(tmp);

		JsonResult<String> result = new JsonResult<String>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(null);
		return result;
	}

}
