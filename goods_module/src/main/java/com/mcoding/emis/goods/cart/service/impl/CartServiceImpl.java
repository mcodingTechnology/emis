package com.mcoding.emis.goods.cart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.cart.bean.Cart;
import com.mcoding.emis.goods.cart.bean.CartExample;
import com.mcoding.emis.goods.cart.persistence.CartMapper;
import com.mcoding.emis.goods.cart.service.CartService;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * @author xuhui
 * Shopping Cart Service Impl
 */
@Service
public class CartServiceImpl implements CartService {

    private static final Logger log = Logger.getLogger(CartServiceImpl.class);

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private IncomeProductService incomeProductService;

	@Override
	public CommonResult<String> addObj(Cart t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(Cart t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<Cart> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<Cart>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Cart>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Cart> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResult<String> addProductToCart(Cart cart,String openid) {
		JsonResult<String> jsonResult = new JsonResult<String>();
//		Member member = memberService.queryMemberByOpenid(openid);
		Product product = productMapper.queryById(cart.getProductid());
//		if(member!=null){
//			if(member.getParentMemberId()!=null){
//				IncomeProduct incomeProduct = incomeProductService.queryIncomeProductByChannelIdAndProductId(cart.getProductid(), member.getChannelsId()).getData();
//				cart.setProductprice(incomeProduct.getMicromallprice());
//			}else{
//			}
//		}
		cart.setProductprice(product.getDiscountPrice());
		try {
			if(cart != null){
				CartExample ex = new CartExample();
				ex.createCriteria()
				  .andProductidEqualTo(cart.getProductid())
				  .andOpenidEqualTo(openid)
				  .andStatusEqualTo(Cart.STATUS_VAILD);
				
				List<Cart> list = this.cartMapper.selectByExample(ex);
				if(list.size() > 0){
					if(cart.getExt()!=null&&cart.getExt().equals("1")){//限购商品，不可重复加入购物车
						jsonResult.setStatus("02");
						jsonResult.setMsg("限购商品，不可重复加入购物车");
						return jsonResult;
					}else {
						Cart tmp = new Cart();
						tmp.setCartid(list.get(0).getCartid());
						tmp.setProductnum(list.get(0).getProductnum() + cart.getProductnum());
						tmp.setUpdatetime(new Date());
						cartMapper.updateByPrimaryKeySelective(tmp);
					}
					
					
				}else{
					cart.setOpenid(openid);
					cart.setCreatetime(new Date());
					cart.setUpdatetime(new Date());
					cart.setStatus(0);
					cartMapper.insert(cart);
				}
				
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				
			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("参数不能为空");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public JsonResult<String> addGiftMallProductToCart(Cart cart,String openid) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		try {
			if(cart != null){
				CartExample ex = new CartExample();
				ex.createCriteria()
						.andProductidEqualTo(cart.getProductid())
						.andOpenidEqualTo(openid)
						.andStatusEqualTo(Cart.STATUS_VAILD)
						.andGiftbuytypeEqualTo(cart.getGiftBuyType());

				List<Cart> list = this.cartMapper.selectByExample(ex);
				if(list.size() > 0){
					if(cart.getExt()!=null&&cart.getExt().equals("1")){//限购商品，不可重复加入购物车
						jsonResult.setStatus("02");
						jsonResult.setMsg("限购商品，不可重复加入购物车");
						return jsonResult;
					}else {
						Cart tmp = new Cart();
						tmp.setCartid(list.get(0).getCartid());
						tmp.setProductnum(list.get(0).getProductnum() + cart.getProductnum());
						tmp.setUpdatetime(new Date());
						cartMapper.updateByPrimaryKeySelective(tmp);
					}
				}else{
					cart.setOpenid(openid);
					cart.setCreatetime(new Date());
					cart.setUpdatetime(new Date());
					cart.setStatus(0);
					cartMapper.insert(cart);
				}

				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");

			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("参数不能为空");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public JsonResult<String> updateProductNumForCartWithCartId(int cartId, int productNum,String openId) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		try {
			if(cartId != 0) {
//				int result = cartMapper.updateProductNumByCartId(cartId, productNum, openId);
				CartExample ex = new CartExample();
				ex.createCriteria().andOpenidEqualTo(openId).andCartidEqualTo(cartId);
				
				Cart cart = new Cart();
				cart.setProductnum(productNum);
				cart.setStatus(Cart.STATUS_VAILD);
				int result = cartMapper.updateByExampleSelective(cart, ex);
				
				if(result != 0){
					jsonResult.setStatus("00");
					jsonResult.setMsg("操作成功");					
				} else {
					jsonResult.setStatus("03");
					jsonResult.setMsg("操作失败");
				}
			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("参数不能为空");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public JsonResult<String> delShoppingCart(Integer cartId,String openId) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		if(openId == null){
			throw new NullPointerException();
		}
		try {
			
			CartExample ex = new CartExample();
			CartExample.Criteria cri = ex.createCriteria();
			cri.andOpenidEqualTo(openId);
			
			int result = 0;
			if (cartId != null && cartId != 0) {
				cri.andCartidEqualTo(cartId);
			}
			
			Cart cart = new Cart();
			cart.setStatus(Cart.STATUS_UNVAILD);
			result = this.cartMapper.updateByExampleSelective(cart, ex);
			
			if(result != 0){
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
			} else {
				jsonResult.setStatus("03");
				jsonResult.setMsg("操作失败");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}

	@Override
	public JsonResult<List<Cart>> getCartList(String openId,String malltype) {
		JsonResult<List<Cart>> jsonResult = new JsonResult<List<Cart>>();
		List<Cart> cartList = new ArrayList<Cart>();
		try {
//			cartList = cartMapper.getCartList(openId);
			CartExample ex = new CartExample();
			ex.createCriteria().andOpenidEqualTo(openId).andStatusEqualTo(Cart.STATUS_VAILD).andMalltypeEqualTo(malltype);
			
			cartList = this.cartMapper.selectByExample(ex);
			List<Cart> list = new ArrayList<Cart>();
			//购物车商品的总数量
			int cartSize=0;
			for (Cart cart : cartList) {
				Product product = productMapper.queryById(cart.getProductid());
				cartSize+=cart.getProductnum();
				cart.setExt(product.getExt());
				cart.setExt1(product.getExt1());
				list.add(cart);
			}
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			jsonResult.setSize(cartSize);
			jsonResult.setData(list);
			
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
	
}

