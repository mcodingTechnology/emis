package com.mcoding.emis.goods.cart.service;

import java.util.List;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.cart.bean.Cart;
import com.mcoding.emis.goods.common.service.BaseService;

/**
 * @author xuhui
 * Shopping Cart Service
 */
public interface CartService extends BaseService<Cart, String> {

	/**
	 * Add product to shopping cart
	 * @param cart
	 * @return
	 */
	public JsonResult<String> addProductToCart(Cart cart, String openid);

	/**
	 * Add product to shopping cart
	 * @param cart
	 * @return
	 */
	public JsonResult<String> addGiftMallProductToCart(Cart cart, String openid);
	
	/**
	 * Update product number for shopping cart with cartid
	 * @param cartId
	 * @return
	 */
	public JsonResult<String> updateProductNumForCartWithCartId(int cartId, int productNum, String openId);
	
	/**
	 * delete shopping cart (remove one & remove all)
	 * @param cartId
	 * @return
	 */
	public JsonResult<String> delShoppingCart(Integer cartId, String openId);
	
	/**
	 * get shopping cart list
	 * @return
	 */
	public JsonResult<List<Cart>> getCartList(String openId,String malltype);
	
//	public PageView<Order> queryOrderByPage(HttpServletRequest request,String iDisplayStart, String iDisplayLength,String sSearch,String startTime ,String endTime 
//    		,Integer status,String merchantId,Integer process,String payType,String province, String city, String area,String startDate,String endDate);
	
//	public CommonResult<Order> queryOrderDetail(Integer orderId);
}
