package com.mcoding.emis.goods.cart.controller;

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

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.cart.bean.Cart;
import com.mcoding.emis.goods.cart.service.CartService;
import com.mcoding.emis.goods.legalMonitor.service.LegalMonitorService;


/**
 * @author xuhui
 * Shopping Cart Controller
 */
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private LegalMonitorService legalMonitorService;
	
	/**
	 * 添加商品到购物车
	 * @param cart
	 * @return
	 */
	@RequestMapping("/merriplusApi/addProductToCart")
	@ResponseBody
	public JsonResult<String> addProduct(@RequestBody Cart cart,HttpServletRequest request,HttpSession session) {
		/*if (legalMonitorService.isOverFrequency(3, request) || legalMonitorService.isOutsideInvoking(request)){
			return new JsonResult<String>(HttpServletRequestLimitResult.RETURN_ILLEGAL_CODE, HttpServletRequestLimitResult.RETURN_ILLEGAL_MSG, null, 0);
		}*/
    	String openid=(String)session.getAttribute("openid");
		if (StringUtils.isNotBlank(openid)) {
			cart.setOpenid(openid);
		}
		
		String malltype = (String) session.getAttribute("mallType");
		if (StringUtils.isNotBlank(malltype)) {
			cart.setMalltype(malltype);
		}
		
	    return cartService.addProductToCart(cart,openid);
	}

	/**
	 * 积分商城——添加商品到购物车
	 * @param cart
	 * @return
	 */
	@RequestMapping("/merriplusApi/addGiftMallProductToCart")
	@ResponseBody
	public JsonResult<String> addGiftMallProductToCart(@RequestBody Cart cart,HttpServletRequest request,HttpSession session) {
		/*if (legalMonitorService.isOverFrequency(3, request) || legalMonitorService.isOutsideInvoking(request)){
			return new JsonResult<String>(HttpServletRequestLimitResult.RETURN_ILLEGAL_CODE, HttpServletRequestLimitResult.RETURN_ILLEGAL_MSG, null, 0);
		}*/
		String openid=(String)session.getAttribute("openid");
		if (StringUtils.isNotBlank(openid)) {
			cart.setOpenid(openid);
		}

		String malltype = (String) session.getAttribute("mallType");
		if (StringUtils.isNotBlank(malltype)) {
			cart.setMalltype(malltype);
		}

		return cartService.addGiftMallProductToCart(cart,openid);
	}
	
	/**
	 * 修改购物车中产品的数量
	 * @param cartId
	 * @param productNum
	 * @return
	 */
	@RequestMapping("/merriplusApi/updateProductNumForCartWithCartId")
	@ResponseBody
	public JsonResult<String> updateProductNumForCartWithCartId(int cartId, int productNum,HttpSession session) {

    	String openid=(String)session.getAttribute("openid");
//		String openid = "owDHujk459J6poWZk3M9A2iVCYd8";
		return cartService.updateProductNumForCartWithCartId(cartId, productNum, openid);
	}
	
	/**
	 * 修改整个购物车的产品
	 * @param cartList
	 * @return
	 */
	@RequestMapping("/merriplusApi/updateAllProductInCart")
	@ResponseBody
	public JsonResult<String> updateAllProductInCart(@RequestBody Cart[] cartList, HttpSession session){
		JsonResult<String> result = new JsonResult<String>();
		if(cartList==null || cartList.length == 0){
			result.setData("ok");
			result.setMsg("操作成功");
			result.setStatus("00");
			result.setSize(0);
			return result;
		}
		try{
			String openid=(String)session.getAttribute("openid");
			this.cartService.delShoppingCart(0, openid);
			
			for(int i=0; i<cartList.length; i++){
//				this.cartService.addProductToCart(cartList.get(i), openid);
				Cart cart = cartList[i];
				this.cartService.updateProductNumForCartWithCartId(cart.getCartid(), cart.getProductnum(), openid);
			}
			
			result.setData("ok");
			result.setMsg("操作成功");
			result.setStatus("00");
			result.setSize(0);
			
		}catch(Exception e){
			result.setMsg("操作失败");
			result.setStatus("error");
			result.setSize(0);
		}
		
		return result;
	}
	
	/**
	 * 根据购物车ID删除商品或者清空整个购物车
	 * @param cartId
	 * @return
	 */
	@RequestMapping("/merriplusApi/delShoppingCart")
	@ResponseBody
	public JsonResult<String> delShoppingCart(@RequestParam(value="cartId", required=false) Integer cartId, HttpSession session) {

    	String openid=(String)session.getAttribute("openid");
//		String openid = "owDHujk459J6poWZk3M9A2iVCYd8";
		return cartService.delShoppingCart(cartId,openid);
	}
	
	/**
	 * 获取购物车列表
	 * @return
	 */
	@RequestMapping("/merriplusApi/getCartList")
	@ResponseBody
	public JsonResult<List<Cart>> getCartList(HttpSession session,String malltype) {
		
		if (session.getAttribute("mallType") != null) {
			malltype = session.getAttribute("mallType").toString();
		}

    	String openid=(String)session.getAttribute("openid");
		//TODO
//		String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		return cartService.getCartList(openid,malltype);
	}
	
}
