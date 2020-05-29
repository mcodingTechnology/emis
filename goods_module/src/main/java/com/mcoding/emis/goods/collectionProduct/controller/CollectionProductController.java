package com.mcoding.emis.goods.collectionProduct.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.collectionProduct.bean.CollectionProduct;
import com.mcoding.emis.goods.collectionProduct.service.CollectionProductService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * 产品收藏的控制器
 * @author hzy
 *
 */
@Controller
public class CollectionProductController {
	
	@Autowired
	protected CollectionProductService collectionProductService;

	@Autowired
	protected MemberService memberService;
	
	@Autowired
	protected ProductService productService;
	
	@RequestMapping("/merriplusApi/getCollectionProductList")
    @ResponseBody
	public JsonResult<ArrayList<CollectionProduct>> getCollectionProductList(HttpSession session){
        JsonResult<ArrayList<CollectionProduct>> jsonResult = new JsonResult<ArrayList<CollectionProduct>>();
		
//		String openid = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openid = null;
		if(session.getAttribute("openid")!=null){
			openid = (String) session.getAttribute("openid");
		}
		CommonResult<ArrayList<CollectionProduct>> list = this.collectionProductService.queryCollectionProductListByOpenId(openid);
		
		jsonResult.setData(list.getData());
		jsonResult.setMsg("操作成功");
		jsonResult.setSize(list.getData().size());
		jsonResult.setStatus("00");
		
		return jsonResult;
	}
	
	@RequestMapping("/merriplusApi/addCollectionProduct")
    @ResponseBody
	public JsonResult<String> addCollectionProduct(int productId, HttpSession session){
		JsonResult<String> jsonResult = new JsonResult<String>();
		
//		String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
		String openId = null;
		if(session.getAttribute("openid")!=null){
			openId = (String) session.getAttribute("openid");
		}
		try{
			Product product = productService.queryById(productId);
			if(product == null){
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setStatus("error");
				jsonResult.setMsg("操作失败，产品不存在");
				return jsonResult;
			}
			
			int size = collectionProductService.countCollectionByOpenIdAndProductId(openId, productId);
			if(size > 0){
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				return jsonResult;
			}
			
			Member member = memberService.queryMemberByOpenid(openId);
			CollectionProduct collection = new CollectionProduct();
			collection.setOpenid(openId);
			collection.setProductid(product.getProductId());
			collection.setProductname(product.getProductName());
			collection.setCollecttime(new Date());
			
			if(member != null){
				collection.setMemberid(member.getMemberId());
				collection.setMembername(member.getFullName());
			}
			
			collectionProductService.addObj(collection);
			jsonResult.setData(null);
			jsonResult.setSize(0);
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			
		}catch(Exception e){
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
		
	}
	
	@RequestMapping("/merriplusApi/removeCollectionProduct")
    @ResponseBody
	public JsonResult<String> removeCollectionProduct(int productId, HttpSession session){
        JsonResult<String> jsonResult = new JsonResult<String>();
		
		String openId = (String)session.getAttribute("openid");
		try{
			Product product = productService.queryById(productId);
			if(product == null){
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setStatus("error");
				jsonResult.setMsg("操作失败，产品不存在");
				return jsonResult;
			}
			
			int size = collectionProductService.countCollectionByOpenIdAndProductId(openId, productId);
			if(size == 0){
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				return jsonResult;
			}
			
			collectionProductService.deleteObjByOpenIdAndProductId(openId, productId);
			jsonResult.setData(null);
			jsonResult.setSize(0);
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			
		}catch(Exception e){
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	

	@RequestMapping("/merriplusApi/isCollectionProduct")
    @ResponseBody
	public JsonResult<Boolean> isCollectionProduct(int productId, HttpSession session){
        JsonResult<Boolean> jsonResult = new JsonResult<Boolean>();
		
//		String openId = "oaD2Ms8kSwaj-9hWQDeFCS1cGLzg";
		String openId = null;
		if(session.getAttribute("openid")!=null){
			openId = (String) session.getAttribute("openid");
		}
		try{
			int size = collectionProductService.countCollectionByOpenIdAndProductId(openId, productId);
			
			if(size > 0){
				jsonResult.setData(true);
				jsonResult.setSize(0);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
			}else{
				jsonResult.setData(false);
				jsonResult.setSize(0);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
			}
		}catch(Exception e){
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		
		
		return jsonResult;
	}

}
