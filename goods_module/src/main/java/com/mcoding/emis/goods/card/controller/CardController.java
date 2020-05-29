package com.mcoding.emis.goods.card.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;
import com.mcoding.emis.goods.card.bean.CardTypeProductRef;
import com.mcoding.emis.goods.card.controller.vo.CardStatusVO;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.card.service.CardService;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.card.service.impl.CardServiceImpl;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 卡券控制器
 * 
 * @author hzy
 *
 */
@Controller
public class CardController {

	@Autowired
	protected CardService cardService;

	@Autowired
	protected CardTypeService cardTypeService;

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected ProductService productService;
	
	@Autowired
	protected CardMapper cardMapper;

	/**
	 * 检查卡码是否有效
	 * 
	 * @param cardCode
	 * @return
	 */
	@RequestMapping("/merriplusApi/isCardAvailable")
	@ResponseBody
	public JsonResult<String> isCardAvailable(String cardCode) {
		JsonResult<String> result = new JsonResult<String>();
		if (StringUtils.isBlank(cardCode)) {
			result.setStatus("error");
			result.setMsg("提交的参数有异常");
			return result;
		}

		try {
			CommonResult<Card> isAvailable = this.cardService.checkCardStatus(cardCode);
			if (isAvailable.getCode() == CardServiceImpl.CARD_CODE_NORMAL) {
				// 如果未兑换
				result.setStatus("00");
				result.setMsg(isAvailable.getMsg());

			} else if (isAvailable.getCode() == CardServiceImpl.CARD_CODE_EXCHANGED_NO_ORDER) {
				// 如果已兑换，但是没有订单
				CommonResult<CardType> cardType = this.cardTypeService.queryObjByCardCode(cardCode);
				// result.setData(String.valueOf(cardType.getData().getProductid()));
				// result.setData(data);

			} else if (isAvailable.getCode() == CardServiceImpl.CARD_CODE_EXCHANGED_WITH_ORDER) {
				// 如果已兑换，且有订单
				OrderExample example = new OrderExample();
				example.createCriteria().andCardidEqualTo(isAvailable.getData().getId());
				Order order = this.orderService.queryListByExample(example).getData().get(0);
				result.setData(String.valueOf(order.getId()));
			}

			result.setStatus(String.valueOf(isAvailable.getCode()));
			result.setMsg(String.valueOf(isAvailable.getMsg()));

		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}

		return result;
	}

	/**
	 * 兑换卡券
	 * 
	 * @return
	 */
	@RequestMapping("/merriplusApi/exchangeCard")
	@ResponseBody
	public JsonResult<CardStatusVO> exchangeCard(String cardCode, HttpSession session) {
		JsonResult<CardStatusVO> result = new JsonResult<CardStatusVO>();
		// String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		if (StringUtils.isBlank(openid)) {
			result.setStatus("error");
			result.setMsg("获取用户openid失败，请重新进入公众号");
			return result;
		}

		if (StringUtils.isBlank(cardCode)) {
			result.setStatus("error");
			result.setMsg("券码不能为空");
			return result;
		}

		try {
			CardStatusVO cardStatusVO = new CardStatusVO();
			result.setData(cardStatusVO);
			result.setStatus("00");
			result.setMsg("ok");

			// 1、检查该卡的类型
			CommonResult<CardType> cardType = this.cardTypeService.queryObjByCardCode(cardCode);
			cardStatusVO.setCardType(cardType.getData());

			if (cardType != null && cardType.getData() != null && cardType.getData().getProductList() != null
					&& cardType.getData().getProductList().size() != 0) {

				cardStatusVO.setProductList(cardType.getData().getProductList());

			} else if (cardType != null && cardType.getData() != null && cardType.getData().getProductid() != null) {
				// 兼容旧版卡券
				Product product = this.productService.queryById(cardType.getData().getProductid());
				CardTypeProductRef ref = new CardTypeProductRef();

				ref.setProductid(product.getProductId());
				ref.setProductName(product.getProductName());
				ref.setProductNo(product.getProductNo());
				ref.setProductCoverImg(product.getProductCoverImg());
				ref.setProductcount(1);
				ref.setProductCode(product.getProductCode());
				ref.setProductLabel(product.getProductLabel());

				List<CardTypeProductRef> productList = new ArrayList<CardTypeProductRef>();
				productList.add(ref);

				cardStatusVO.setProductList(productList);
			}

			// 2、检查该卡的状态
			CommonResult<Card> cardStatus = this.cardService.checkCardStatus(cardCode);
			cardStatusVO.setCardStatus(cardStatus.getCode());
			result.setMsg(cardStatus.getMsg());

			// 3、根据卡的状态进行处理
			// 3.1 如果卡被其他人领了，就返回异常
			if (cardStatus.getData() != null && cardStatus.getData().getOpenid() != null
					&& StringUtils.isNotBlank(cardStatus.getData().getOpenid())
					&& !cardStatus.getData().getOpenid().equals(openid)) {
				result.setStatus("-1");
				result.setMsg("该卡券已被另一个用户领取");
				return result;
			}

			if (cardStatus.getCode() == CardServiceImpl.CARD_CODE_NORMAL) {
				// 3.2 如果卡未兑换,就进行兑换
				this.cardService.exchangeCard(cardCode, openid);

			} else if (cardStatus.getCode() == CardServiceImpl.CARD_CODE_EXCHANGED_NO_ORDER) {
				// 3.3 如果已兑换，但是没有创建订单

			} else if (cardStatus.getCode() == CardServiceImpl.CARD_CODE_EXCHANGED_WITH_ORDER
					|| cardStatus.getCode() == CardServiceImpl.CARD_CODE_USED) {
				// 3.4 如果已兑换，而且创建订单
				OrderExample example = new OrderExample();
				OrderExample.Criteria cri1 = example.createCriteria()
						.andCardcodeEqualTo(cardStatus.getData().getCardcode());
				OrderExample.Criteria cri2 = example.createCriteria()
						.andCardidEqualTo(cardStatus.getData().getCardtypeid());
				example.or(cri2);

				Order order = this.orderService.queryListByExample(example).getData().get(0);
				cardStatusVO.setOrder(order);

			} else {
				result.setStatus("error");
			}

		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 核销卡券
	 * 
	 * @param cardCode
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping("/merriplusApi/consumeCard.html")
	 * 
	 * @ResponseBody public JsonResult<String> consumeCard(String cardCode,
	 * HttpSession session){ JsonResult<String> result = new
	 * JsonResult<String>(); String
	 * openid=(String)session.getAttribute("openid");
	 * 
	 * if(StringUtils.isBlank(openid)){ result.setStatus("error");
	 * result.setMsg("获取用户openid失败，请重新进入公众号"); return result; }
	 * 
	 * if(StringUtils.isBlank(cardCode)){ result.setStatus("error");
	 * result.setMsg("券码不能为空"); return result; }
	 * 
	 * try{ CommonResult<String> cr = this.cardService.consumeCard(cardCode,
	 * openid);
	 * 
	 * if(cr.getCode() != 0){ result.setStatus("error");
	 * result.setMsg(cr.getMsg()); }else{ result.setStatus("00");
	 * result.setMsg("ok"); }
	 * 
	 * CommonResult<CardType> cardType =
	 * this.cardTypeService.queryObjByCardCode(cardCode);
	 * result.setData(String.valueOf(cardType.getData().getProductid()));
	 * 
	 * }catch(Exception e){ result.setStatus("error");
	 * result.setMsg(e.getMessage()); e.printStackTrace(); }
	 * 
	 * return result; }
	 */

	@RequestMapping("/card/findByPage")
	@ResponseBody
	public PageView<Card> findByPage(int cardTypeId, HttpServletRequest request) {
		String iDisplayStart = request.getParameter("iDisplayStart") == null ? "0"
				: request.getParameter("iDisplayStart").toString();
		String iDisplayLength = request.getParameter("iDisplayLength") == null ? "10"
				: request.getParameter("iDisplayLength").toString();

		PageView<Card> pageView = null;
		try {
			CardExample ex = new CardExample();
			ex.createCriteria().andCardtypeidEqualTo(cardTypeId);
			pageView = this.cardService.queryObjByExampleByPage(ex, iDisplayStart, iDisplayLength);

		} catch (Exception e) {
			e.printStackTrace();
			pageView = new PageView<Card>();
			pageView.setQueryResult(null);
		}

		return pageView;
	}

	/******** 卡券后台管理 ******/
	/**
	 * 跳转卡券列表的页面
	 * 
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/cardPageView.html")
	public ModelAndView cardPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("card/cardList");
		return mav;
	}

	@RequestMapping("/merriplusApi/isCardAvailableForOrder")
	@ResponseBody
	public JsonResult<List<Card>> getAvailableCardForOrder(@RequestBody CardType[] cardList, int orderPrice, HttpServletRequest request) {
		JsonResult<List<Card>> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		try {
			String openId = (String)request.getSession().getAttribute("openid");
			List<Card> codeList = new ArrayList<>();
			
//			String appid = WxMpServiceUtils.currentWxMpConfigStorage().getAppId();
//			String appsecrect = WxMpServiceUtils.currentWxMpConfigStorage().getSecret();
			
			
//			String accessToken = WeixinUtil.getJSSDKAccessToken(appid, appsecrect);  //获取微信jssdk---access_token
//			WxJsapiSignature jsSign = wxMpService.createJsapiSignature(url);
			
			WxMpService wxMpService = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String accessToken = wxMpService.getAccessToken();
			
//			String accessToken = WxMpServiceUtils.getWxMpServiceFromThreadLocal().getAccessToken();
			
			
			for(int i=0; i<cardList.length; i++){
				CardTypeExample example = new CardTypeExample();
				example.createCriteria().andCardidEqualTo(cardList[i].getCardid());
				
				CommonResult<ArrayList<CardType>> cardTypelist = this.cardTypeService.queryObjByExample(example);
				CardType cardType = cardTypelist.getData().get(0);
				
				if (cardType.getLeastcost() > orderPrice) {
					continue;
				}
				
				String code = WeixinUtil.encryptCardCode(accessToken, cardList[i].getEncryptCode());
				CardExample cardExample = new CardExample();
				cardExample.createCriteria().andCardcodeEqualTo(code);
				List<Card> list = this.cardMapper.selectByExample(cardExample);
				
				Card card = null;
				if(list == null || list.size() == 0){
					card = new Card();
					card.setCardtypeid(cardType.getId());
					card.setCardtypename(cardType.getName());
					card.setCardcode(code);
					card.setCreatetime(new Date());
					card.setOpenid(openId);
					card.setIsvalid(CardType.IS_VALID_YES);
					card.setExt1(Card.CARD_STATUS_EXCAHGED);
					
					this.cardService.addObj(card);
				}else {
					card = list.get(0);
				}
				
				card.setCardType(cardType);
				codeList.add(card);
			}
			
			result.setData(codeList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}
		
		return result;

	}
	
	 /** 
	   * 跳转特权卡的页面
	   * @return 
	   * @author Benson 
	   */ 
	  @RequestMapping("/verifyPrivilegeCardView.html")
	  public ModelAndView verifyPrivilegeCardView(String id) {
		  ModelAndView mav = new ModelAndView();
		  if(StringHelper.isNotBlank(id)){
			  mav.addObject("edit","edit");
		  }
		  mav.setViewName("card/verifyCard");
		  return mav;
	  }
	  
	  /**
		 * 兑换卡券
		 * 
		 * @return
		 */
		@RequestMapping("/card/verifyPrivilegeCard")
		@ResponseBody
		public JsonResult<List<Card>> verifyPrivilegeCard(@RequestParam(value = "cardcode") String[] cardcode,@RequestParam(value = "userId",required = false) String userId) {
			return cardService.verifyPrivilegeCard(cardcode,userId);
		}

}
