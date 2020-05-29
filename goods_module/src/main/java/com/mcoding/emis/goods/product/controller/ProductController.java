package com.mcoding.emis.goods.product.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.category.service.CategoryService;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductSequenceService;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.productCategory.service.ProductCategoryService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class ProductController {
	@Autowired
    private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private IncomeProductService incomeProductService;
	@Autowired
	private ProductSequenceService productSequenceService;

    /**
      * 跳转产品的页面
      * @return
      * @author Benson
    */
    @RequestMapping("/productList")
    public ModelAndView navigateProductManagerView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/productList");
        return mav;
    }

    /**
      * 产品数据列表查询
      * @param iDisplayStart
      * @param iDisplayLength
      * @param sSearch
      * @return
      * @author Benson
    */
    @RequestMapping("/queryProductData")
    @ResponseBody
    public PageView<Product> queryProductData(HttpServletRequest request,String iDisplayStart, String iDisplayLength,String sSearch,String pageNo,String pageSize) {
        String productType = request.getParameter("productType");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("productName", sSearch);
        param.put("brandCode", request.getParameter("brandCode"));
        param.put("isSale", request.getParameter("isSale"));
        param.put("isGift", request.getParameter("isGift"));
        param.put("isOpenDsicountPoint", request.getParameter("isOpenDsicountPoint"));
        //leiming添加商品类型过啦
        if(productType!=null && !productType.trim().equals("")){
        	param.put("productType", productType);
        }
        return productService.queryProductData(iDisplayStart, iDisplayLength, sSearch, param,pageNo,pageSize);
    }

    @RequestMapping("/queryProductDataByPage")
    @ResponseBody
    public PageView<Product> queryProductDataByPage(HttpServletRequest request,String iDisplayStart, String iDisplayLength,String sSearch,String pageNo,String pageSize) {
        return queryProductData(request,iDisplayStart,iDisplayLength,sSearch,pageNo,pageSize);
    }

    /**
     * 跳转添加产品的页面
     * @return
     * @author Benson
   */
   @RequestMapping("/addProductView")
   public ModelAndView navigateAddProductPointManagerView(String productId,String id) {
       ModelAndView mav = new ModelAndView();
       if(StringUtils.isNotBlank(productId)){
	    	Product product = productService.queryById(Integer.parseInt(productId));
	    	/*if(product.getOriginalPrice()!=null){
	    		product.setOriginalPrice(product.getOriginalPrice());
	    	}
	    	if(product.getDiscountPrice()!=null){
	    		product.setDiscountPrice(product.getDiscountPrice()/100);
	    	}*/
	       	mav.addObject("product", product);
	       	mav.addObject("edit","edit");
       }
       mav.setViewName("product/addProduct");
       return mav;
   }

   @RequestMapping("/getProdSeqByProductId")
   @ResponseBody
   public CommonResult<HashMap<String,Object>> getProdSeqByProductId(Integer productId){
	   return productSequenceService.getProSeqListByProductId(productId);
   }

   /**
    * 添加产品
    * @param product
    * @return
    * @author Benson
  */
   @RequestMapping("/addProduct")
   @ResponseBody
   public CommonResult<String> addProduct(HttpServletRequest request,Product product) {
	   CommonResult<String> result = new CommonResult<String>();
	   try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNotBlank(request.getParameter("presellStartTimeStr"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("presellStartTimeStr"));
				product.setPresellStartTime(startTime);
			}
			if(StringUtils.isNotBlank(request.getParameter("presellEndTimeStr"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("presellEndTimeStr"));
				product.setPresellEndTime(startTime);
			}
			if(StringUtils.isNotBlank(request.getParameter("seckillStartTimeStr"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("seckillStartTimeStr"));
				product.setSeckillStartTime(startTime);
			}
			if(StringUtils.isNotBlank(request.getParameter("seckillEndTimeStr"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("seckillEndTimeStr"));
				product.setSeckillEndTime(startTime);
			}
			if(StringUtils.isNotBlank(request.getParameter("timeLimitStartTimeStr"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("timeLimitStartTimeStr"));
				product.setTimeLimitStartTime(startTime);
			}
			result = productService.addObj(product);
	   } catch (ParseException e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setMsg(e.getMessage());
			result.setMsg("error");
		}
       return result;
   }

   /** z
    * 删除产品
    * @param id
    * @return
    * @author Benson
    */
   @RequestMapping("/deleteProduct")
   @ResponseBody
   public CommonResult<String> deleteProduct(String id){
       return productService.deleteObjById(id);
   }

   /**
    * 批量设置可兑换礼品
    * @return
    * @author Benson
    */
   @RequestMapping("/setGifts")
   @ResponseBody
   public CommonResult<String> setGifts(Integer productId[],Integer giftStatus,Integer isOpenDiscountPrice){
       return productService.setGifts(productId,giftStatus,isOpenDiscountPrice);
   }

   /**
    * 批量上下架
    * @return
    * @author Leiming
    */
   @RequestMapping("/batchGroundingOrNot")
   @ResponseBody
   public CommonResult<String> batchGroundingOrNot(Integer productId[],Integer isSale){
	   return productService.batchGroundingOrNot(productId,isSale);
   }

   /**
    * 更新价格
    * @return
    * @author Leiming
    */
   @RequestMapping("/updatePrice")
   @ResponseBody
   public CommonResult<String> updatePrice(Integer productId,Integer priceType,Integer price){
	   return productService.updatePrice(productId,priceType,price);
   }

   /**
    * 更新排序
    * @return
    * @author Leiming
    */
   @RequestMapping("/updateProductSequence")
   @ResponseBody
   public CommonResult<String> updateProductSequence(Integer productId,Integer productSequence){
	   return productService.updateProductSequence(productId, productSequence);
   }

   /**
    * 根据ids查询产品列表--微信接口
    * @author Leiming
    */
   @RequestMapping("/product/queryProductByIds")
   @ResponseBody
   public CommonResult<ArrayList<Product>> queryProductByIds(String ids) {
   	return productService.queryProductByIds(ids);
   }


   /**
     * 跳转套餐的页面
   */
   @RequestMapping("/productSetList")
   public ModelAndView toProductSetList() {
       ModelAndView mav = new ModelAndView();
       mav.setViewName("product/productSetList");
       return mav;
   }

   /**
    * 套餐数据列表查询
  */
  @RequestMapping("/querySetData")
  @ResponseBody
	public PageView<Product> querySetData(HttpServletRequest request, String iDisplayStart, String iDisplayLength, String sSearch,String pageNo,String pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isSet", "yes");
		param.put("brandCode", request.getParameter("brandCode"));
		return productService.queryProductData(iDisplayStart, iDisplayLength, sSearch, param,pageNo,pageSize);
	}

	/**
	 * 跳转套餐的页面
	 */
	@RequestMapping("/toUpdateProductSet")
	public ModelAndView toUpdateProductSet(Integer productId, String brandCode) {
		Product product = productService.querySetById(productId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("productId", productId);
		mav.addObject("brandCode", brandCode);
		mav.addObject("product", product);
		mav.setViewName("product/addProductSet");
		return mav;
	}

   /**
    * 编辑套餐子产品
    */
   @RequestMapping("/product/updateProductSet")
   @ResponseBody
   public CommonResult<String> updateProductSet(Integer id, String productSetId, String productSetNum) {
   	return productService.updateProductSet(id, productSetId, productSetNum);
   }
}
