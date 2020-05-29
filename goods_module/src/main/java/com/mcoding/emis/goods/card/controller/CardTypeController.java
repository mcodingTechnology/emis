package com.mcoding.emis.goods.card.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;
import com.mcoding.emis.goods.card.bean.CardTypeExample.Criteria;
import com.mcoding.emis.goods.card.service.CardTypeService;
import com.mcoding.emis.goods.common.utils.DateEditor;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 卡券类型控制器
 * @author hzy
 *
 */
@Controller
public class CardTypeController {
	
	@Autowired
	protected CardTypeService cardTypeService;
	
	/** 
	    * 跳转添加卡券的页面
	    * @return 
	    * @author Benson 
	  */ 
	  @RequestMapping("/addCardTypeView.html")
	  public ModelAndView addCardTypeView(String id) {
	      ModelAndView mav = new ModelAndView();
	      if(StringHelper.isNotBlank(id)){
	    	  
	     	  mav.addObject("edit","edit");
	      }
	      mav.setViewName("card/addCardType");
	      return mav;
	  }
	
	/**
	 * 添加 优惠券
	 * @param cardType
	 * @return
	 */
	@RequestMapping("/cardType/createCardType")
    @ResponseBody
	public JsonResult<String> createCardType(@RequestBody CardType cardType){
		JsonResult<String> result = new JsonResult<String>();
		try{
			System.out.println(cardType.getCardtype());
			//特权卡
			if(cardType.getCardtype().equals(CardType.CARD_TYPE_PRIVILEGE)){
				return this.cardTypeService.addPrivilegeCardTypeAndCard(cardType);
			}else {
				this.cardTypeService.addCardTypeAndCard(cardType);
			}
			result.setStatus("00");
			result.setMsg("ok");
			result.setData(null);
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
	@InitBinder
    protected void initBinder(HttpServletRequest request,  
                                  ServletRequestDataBinder binder) throws Exception {  
        //对于需要转换为Date类型的属性，使用DateEditor进行处理  
        binder.registerCustomEditor(Date.class, new DateEditor());  
    }  
	
	/**
	 * 增加优惠券的数量（现在还存在问题，先不开放）
	 * @param cardId
	 * @param count
	 * @return
	 */
	@RequestMapping("/cardType/addCardTypeCount")
    @ResponseBody
	public JsonResult<String> addCardTypeCount(int cardTypeId, int count){
		JsonResult<String> result = new JsonResult<String>();
		
		try{
			this.cardTypeService.addCardTypeCount(cardTypeId, count);
			result.setStatus("00");
			result.setMsg("ok");
			
		}catch(Exception e){
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 禁用卡券
	 * @param cardTypeId
	 * @return
	 */
	@RequestMapping("/cardType/disableCardType")
    @ResponseBody
	public JsonResult<String> disableCardType(int cardTypeId){
		JsonResult<String> result = new JsonResult<String>();
		try{
			this.cardTypeService.disableCardType(cardTypeId);
			result.setStatus("00");
			result.setMsg("ok");
		
		}catch(Exception e){
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/**
	 * 获取该卡类型下，所有的卡号
	 * @return
	 */
	/*@RequestMapping("/cardType/getAllCardNoByCardType.html")
    @ResponseBody
	public JsonResult<List<String>> getAllCardNoByCardType(int cardTypeId){
		JsonResult<List<String>> result = new JsonResult<List<String>>();
		try{
//			List<String> cardNoList = this.cardTypeService.getAllCardNo(cardTypeId, 1).getData();
			result.setData(cardNoList);
			result.setSize(cardNoList.size());
			result.setStatus("00");
			result.setMsg("ok");
			
		}catch(Exception e){
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}*/
	
	/** 
     * 跳转卡券类型列表的页面
     * @return 
     * @author Benson 
   */ 
   @RequestMapping("/cardTypePageView.html")
   public ModelAndView cardTypePageView() {
       ModelAndView mav = new ModelAndView();
       mav.setViewName("card/cardTypeList");
       return mav;
   }
   
	
	@RequestMapping("/cardType/findByPage")
    @ResponseBody
	public PageView<CardType> findByPage(HttpServletRequest request,@RequestParam(value = "cardName",required = false) String cardName,
										 @RequestParam(value = "codePrefix",required = false) String codePrefix,
										 @RequestParam(value = "sSearch",required = false) String sSearch){
		String iDisplayStart = request.getParameter("iDisplayStart") == null ? "0" : request.getParameter("iDisplayStart").toString();
		String iDisplayLength = request.getParameter("iDisplayLength") == null ? "10" : request.getParameter("iDisplayLength").toString();
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		if (StringUtils.isNotBlank(sSearch)) {
			cardName = sSearch;
		}

		PageView<CardType> pageView = null;
		try{
			pageView = this.cardTypeService.queryObjByPage(iDisplayStart, iDisplayLength,pageNo,pageSize,cardName,codePrefix);
		}catch(Exception e){
			e.printStackTrace();
			pageView = new PageView<CardType>();
			pageView.setQueryResult(null);
		}
		
		return pageView;
		
	}
	
	@RequestMapping("/merriplusApi/getCardTypeByOrderFee")
    @ResponseBody
	public JsonResult<List<CardType>> getCardTypeByOrderFee(int orderFee,HttpSession session){
		System.out.println("orderFee============="+orderFee);
		CardTypeExample example = new CardTypeExample();
		
		Criteria cri1 = example.createCriteria();
		cri1.andIsvalidEqualTo(CardType.IS_VALID_YES);
		cri1.andCardtypeEqualTo(CardType.CARD_TYPE_CASH);
		cri1.andLeastcostLessThanOrEqualTo(orderFee);
		cri1.andExt2EqualTo(session.getAttribute("brandCode").toString());
		
		JsonResult<List<CardType>> result = new JsonResult<List<CardType>>();
		try {
			CommonResult<ArrayList<CardType>> cardTypeList = this.cardTypeService.queryObjByExample(example);
			result.setStatus("00");
			result.setData(cardTypeList.getData());
			result.setMsg("ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
		       
	} 
}
