package com.mcoding.emis.goods.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.cart.bean.Cart;
import com.mcoding.emis.goods.cart.bean.CartExample;
import com.mcoding.emis.goods.cart.persistence.CartMapper;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.income.persistence.IncomeProductMapper;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.bean.ProductSequence;
import com.mcoding.emis.goods.product.bean.SetProducts;
import com.mcoding.emis.goods.product.bean.SetProductsExample;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.product.persistence.ProductSequenceMapper;
import com.mcoding.emis.goods.product.persistence.SetProductsMapper;
import com.mcoding.emis.goods.product.service.ProductSequenceService;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.productCategory.bean.ProductCategory;
import com.mcoding.emis.goods.productCategory.persistence.ProductCategoryMapper;
import com.mcoding.emis.goods.productCategory.service.ProductCategoryService;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetailExample.Criteria;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	private static final int MAX_CALLCOUNT = 10;

	@Autowired
	private DefaultTransactionDefinition def;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private IncomeProductMapper incomeProductMapper;
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductSequenceService productSequenceService;
	@Autowired
	private ProductSequenceMapper productSequenceMapper;
	@Autowired
	private SetProductsMapper setProductsMapper;
	@Autowired
	private IncomeProductService incomeProductService;
	@Autowired
    private CartMapper cartMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xingrun.pms.common.service.BaseService#addObj(java.io.Serializable)
	 */
	@Override
	public CommonResult<String> addObj(Product t) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			Date date = new Date();
			if (t.getGiftPointMoney() == null || "".equals(t.getGiftPointMoney())) {
				t.setGiftPointMoney(0);
			}
			if (t.getGiftPlusPoint() == null || "".equals(t.getGiftPlusPoint())) {
				t.setGiftPlusPoint(0);
			}
			if (t.getDiscountPointMoney() == null || "".equals(t.getDiscountPointMoney())) {
				t.setDiscountPointMoney(0);
			}
			if (t.getDiscountGiftPlusPoint() == null || "".equals(t.getDiscountGiftPlusPoint())) {
				t.setDiscountGiftPlusPoint(0);
			}
			if (t.getProductId() == null) {
				Product productByProductCode = queryByProductCode(t.getProductCode());
				if (productByProductCode != null) {
					result.setCode(2);
					result.setData("");
					result.setMsg("产品编码已经存在，请重新输入");
					return result;
				}
				Product productByBarCode = queryByBarCode(t.getBarCode());
				if (productByBarCode != null) {
					result.setCode(2);
					result.setData("");
					result.setMsg("条形码已经存在，请重新输入");
					return result;
				}

				t.setIsGift(1);
				t.setIsOpenDsicountPoint(0);
				t.setBarCode(t.getBarCode().trim());
				t.setProductCode(t.getProductCode().trim());
				// t.setProductSummary(StringUtil.trimStyle(t.getProductSummary()));
				// 新增产品积分
				t.setCreateTime(date);
				t.setUpdateTime(date);

				productMapper.insertSelective(t);
				Product product = productMapper.queryProduct(t);
				IncomeProduct incomeProduct = new IncomeProduct();
				incomeProduct.setProductid(product.getProductId());
				incomeProduct.setChannelsid(1);
				incomeProduct.setMicromallprice(0);
				incomeProduct.setLevel1(0);
				incomeProduct.setLevel2(0);
				incomeProduct.setLevel3(0);
				incomeProduct.setLevel4(0);
				incomeProduct.setLevel1point(0);
				incomeProduct.setLevel2point(0);
				incomeProduct.setLevel3point(0);
				incomeProduct.setLevel4point(0);
				incomeProduct.setCreatetime(new Date());
				incomeProductService.addObj(incomeProduct);
			} else {
				t.setBarCode(t.getBarCode().trim());
				t.setProductCode(t.getProductCode().trim());
				// 修改产品积分
				t.setUpdateTime(date);
				productMapper.updateBySelect(t);
				// 更新购物车价格
				if(t.getDiscountPrice() != null){
					CartExample example = new CartExample();
					Cart cart = new Cart();
					cart.setProductprice(t.getDiscountPrice());
					CartExample.Criteria criteria = example.createCriteria();
					criteria.andProductidEqualTo(t.getProductId());
					this.cartMapper.updateByExampleSelective(cart, example);
				}
				
			}
			// 先删除产品类目
			productCategoryMapper.deleteByProductId(t.getProductId());
			// 新增产品类目
			if (t.getProductCategoryIdneed() != null) {
				for (String id : t.getProductCategoryIdneed()) {
					ProductCategory category = new ProductCategory();
					category.setCategoryId(Integer.parseInt(id));
					category.setProductId(t.getProductId());
					category.setProductName(t.getProductName());
					productCategoryService.addObj(category);
				}
			}

			if (t.getProductCategoryIdpeople() != null) {
				for (String id : t.getProductCategoryIdpeople()) {
					ProductCategory category = new ProductCategory();
					category.setCategoryId(Integer.parseInt(id));
					category.setProductId(t.getProductId());
					category.setProductName(t.getProductName());
					productCategoryService.addObj(category);
				}
			}

			if (t.getProductCategoryIdingredient() != null) {
				for (String id : t.getProductCategoryIdingredient()) {
					ProductCategory category = new ProductCategory();
					category.setCategoryId(Integer.parseInt(id));
					category.setProductId(t.getProductId());
					category.setProductName(t.getProductName());
					productCategoryService.addObj(category);
				}
			}

			if (t.getProductCategoryIdpreferential() != null) {
				for (String id : t.getProductCategoryIdpreferential()) {
					ProductCategory category = new ProductCategory();
					category.setCategoryId(Integer.parseInt(id));
					category.setProductId(t.getProductId());
					category.setProductName(t.getProductName());
					productCategoryService.addObj(category);
				}
			}

			if (t.getProductCategoryIdingredientJLD() != null) {
				for (String id : t.getProductCategoryIdingredientJLD()) {
					ProductCategory category = new ProductCategory();
					category.setCategoryId(Integer.parseInt(id));
					category.setProductId(t.getProductId());
					category.setProductName(t.getProductName());
					productCategoryService.addObj(category);
				}
			}

			if (t.getProductCategoryIdfunction() != null) {
				for (String id : t.getProductCategoryIdfunction()) {
					ProductCategory category = new ProductCategory();
					category.setCategoryId(Integer.parseInt(id));
					category.setProductId(t.getProductId());
					category.setProductName(t.getProductName());
					productCategoryService.addObj(category);
				}
			}

			if (StringUtils.isNotBlank(t.getProductAdType())) {
				// 先删除原有的排序记录
				productSequenceMapper.deleteProSeqByProductId(t.getProductId());
				String[] productAdTypeArr = t.getProductAdType().split(",");
				for (int i = 0; i < productAdTypeArr.length; i++) {
					ProductSequence ps = new ProductSequence();
					ps.setProductId(t.getProductId());
					ps.setCreateTime(date);
					ps.setUpdateTime(date);
					if ("0".equals(productAdTypeArr[i])) {
						ps.setProductAdType(Integer.valueOf(productAdTypeArr[i]));
						ps.setSequence(t.getSequenceTypeZero());
					}
					if ("1".equals(productAdTypeArr[i])) {
						ps.setProductAdType(Integer.valueOf(productAdTypeArr[i]));
						ps.setSequence(t.getSequenceTypeOne());
					}
					productSequenceMapper.insertSelective(ps);

				}
			}
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			log.error("增加产品失败：", e);
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			// 删除产品
			productMapper.delete(id);

			// 删除产品类目
			productCategoryMapper.deleteByProductId(Integer.parseInt(id));

			// 删除产品排序
			productSequenceMapper.deleteProSeqByProductId(Integer.parseInt(id));
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Override
	public CommonResult<String> modifyObj(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<Product> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<Product>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Product>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Product> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Product> queryProductData(String iDisplayStart, String iDisplayLength, String sSearch,
			Map<String, Object> param, String pageNo, String pageSize) {
		PageView<Product> pageView;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
			pageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			pageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else {
			pageView = new PageView<>(1, 10);
		}

		if (StringUtils.isNotBlank(iDisplayLength) && iDisplayLength.equals("all")) {
		} else {
			param.put("pageView", pageView);
		}
		List<Product> products = productMapper.queryAllProductByPage(param);
		for (Product t : products) {
			SetProductsExample example = new SetProductsExample();
			SetProductsExample.Criteria cri = example.createCriteria();
			cri.andSetIdEqualTo(t.getProductId());
			List<SetProducts> childProductList = setProductsMapper.selectByExample(example);
			String childProMsg = "";
			for (SetProducts one : childProductList) {
				String productName = productMapper.queryById(one.getProductId()).getProductName();
				childProMsg += productName + " x " + one.getNum() + "<br>";
			}
			t.setProductContent(childProMsg);
		}
		pageView.setQueryResult(products);
		return pageView;
	}

	@Override
	public Product queryById(Integer productId) {
		return productMapper.queryById(productId);
	}

	@Override
	public Product querySetById(Integer productId) {
		Product product = productMapper.queryById(productId);
		if (product.getIsSet().equals("yes")) {
			SetProductsExample example = new SetProductsExample();

			SetProductsExample.Criteria cri = example.createCriteria();
			cri.andSetIdEqualTo(productId);
			List<SetProducts> setList = setProductsMapper.selectByExample(example);
			String[] ids = new String[setList.size()];
			String[] nums = new String[setList.size()];
			for (int i = 0; i < setList.size(); i++) {
				ids[i] = setList.get(i).getProductId() + "";
				nums[i] = setList.get(i).getNum() + "";
			}
			String productSetId = org.apache.commons.lang.StringUtils.join(ids, ",");
			String productSetNum = org.apache.commons.lang.StringUtils.join(nums, ",");
			product.setProductSetId(productSetId);
			product.setProductSetNum(productSetNum);
		}
		return product;
	}

	@Override
	public List<Product> getProductsByOrderId(Integer orderId) {
		return productMapper.getProductsByOrderId(orderId);
	}

	@Override
	public Product queryByChannelsId(Integer productId, Integer channelsId) {
		return productMapper.queryByChannelsId(productId, channelsId);
	}

	@Override
	public Product queryByBarCode(String barCode) {
		return productMapper.queryByBarCode(barCode);
	}

	@Override
	public Product queryByProductCode(String productCode) {
		return productMapper.queryByProductCode(productCode);
	}

	@Override
	public List<Product> queryByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return productMapper.queryByCategoryId(categoryId);
	}

	@Override
	public List<Product> getProductListBySearch(String categoryId, String productName, String brandCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryId", categoryId);
		param.put("productName", productName);
		param.put("brandCode", brandCode);
		List<Product> list = productMapper.getProductListBySearch(param);
		return list;
	}

	@Override
	public CommonResult<String> setGifts(Integer[] productId, Integer giftStatus, Integer isOpenDiscountPrice) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			for (Integer id : productId) {
				Product t = new Product();
				t.setProductId(id);
				t.setUpdateTime(new Date());
				if (giftStatus != null) {
					t.setIsGift(giftStatus);
				}
				if (isOpenDiscountPrice != null) {
					t.setIsOpenDsicountPoint(isOpenDiscountPrice);
				}
				productMapper.updateBySelect(t);
			}
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Override
	public CommonResult<String> batchGroundingOrNot(Integer[] productId, Integer isSale) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			for (Integer id : productId) {
				Product t = new Product();
				t.setProductId(id);
				t.setUpdateTime(new Date());
				if (isSale != null) {
					t.setIsSale(isSale);
				}
				productMapper.updateBySelect(t);
			}
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Override
	public CommonResult<String> updatePrice(Integer productId, Integer priceType, Integer price) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			Product t = new Product();
			t.setProductId(productId);
			t.setUpdateTime(new Date());
			if (priceType != null) {
				if (priceType.intValue() == 1) {
					t.setOriginalPrice(price);
				}
				if (priceType.intValue() == 2) {
					t.setDiscountPrice(price);
				}
			}
			productMapper.updateBySelect(t);
			// 更新购物车价格
			CartExample example = new CartExample();
			Cart cart = new Cart();
			cart.setProductprice(price);
			CartExample.Criteria criteria = example.createCriteria();
			criteria.andProductidEqualTo(productId);
			this.cartMapper.updateByExampleSelective(cart, example);
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Override
	public CommonResult<String> addProduct(Product product, IncomeProduct incomeProduct) {
		CommonResult<String> result = new CommonResult<String>();
		CommonResult<String> checkProduct = new CommonResult<String>();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			// 1.新增产品信息
			checkProduct = addObj(product);
			// 新增成功后
			if (checkProduct.getCode() == 0) {
				if (incomeProduct.getId() == null) {
					incomeProduct.setProductid(product.getProductId());
					incomeProductMapper.insertSelective(incomeProduct);
				} else {
					incomeProductMapper.updateByPrimaryKeySelective(incomeProduct);
				}
				result.setCode(0);
				result.setData("ok");
				result.setMsg("ok");
			}
			transactionManager.commit(status);

		} catch (Exception e) {
			transactionManager.rollback(status);
			log.error("增加产品失败：", e);
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@Override
	public CommonResult<ArrayList<Product>> queryProductByIds(String ids) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", ids);
		List<Product> products = new ArrayList<Product>();
		if (ids != null && !"".trim().equals(ids)) {
			products = productMapper.queryResultByIds(param);
		}
		CommonResult<ArrayList<Product>> result = new CommonResult<ArrayList<Product>>();
		result.setData((ArrayList) products);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public List<Product> queryAll() {
		return productMapper.queryAll();
	}

	@Override
	public CommonResult<String> updateProductSequence(Integer productId, Integer productSequence) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			Product t = productMapper.queryById(productId);
			Integer seqCount = productMapper.queryProductSeqCount(productSequence);
			if(seqCount == 0){
				t.setUpdateTime(new Date());
				t.setProductSequence(productSequence);
				productMapper.updateBySelect(t);
				result.setCode(0);
				result.setData("ok");
				result.setMsg("ok");
			}else {
				result.setCode(2);
				if(t.getProductSequence() != null){
					result.setData(t.getProductSequence().toString());
				}else {
					result.setData("");
				}
				result.setMsg("产品排序重复");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Override
	public List<Product> getProductListByParam(Map<String, Object> param) {
		return productMapper.getProductListBySearch(param);
	}

	@Override
	public CommonResult<String> updateProductSet(Integer id, String setIds, String setNums) {
		Product t = productMapper.queryById(id);
		if (t.getIsSet().equals("yes") && setIds.length() > 0 && setNums.length() > 0) { // 是套餐
			List<SetProducts> setList = new ArrayList<SetProducts>();
			String productSetIds[] = setIds.split(",");
			String productSetNums[] = setNums.split(",");
			for (int i = 0; i < productSetIds.length; i++) {
				SetProducts setProducts = new SetProducts();
				setProducts.setSetId(t.getProductId());
				setProducts.setProductId(Integer.valueOf(productSetIds[i]));
				setProducts.setNum(Integer.valueOf(productSetNums[i]));
				setList.add(setProducts);
			}

			Map<Integer, Integer> idNumMap = new HashMap<Integer, Integer>();
			for (SetProducts one : setList) {
				if (idNumMap.get(one.getProductId()) == null) {
					idNumMap.put(one.getProductId(), one.getNum());
				} else {
					idNumMap.put(one.getProductId(), idNumMap.get(one.getProductId()) + one.getNum());
				}
			}
			Set<Integer> mapSet = idNumMap.keySet();
			Iterator<Integer> it = mapSet.iterator();
			setList.clear();
			while (it.hasNext()) {
				int oneId = it.next();
				SetProducts setProducts = new SetProducts();
				setProducts.setSetId(t.getProductId());
				setProducts.setProductId(oneId);
				setProducts.setNum(idNumMap.get(oneId));
				setList.add(setProducts);
			}
			SetProductsExample example = new SetProductsExample();
			SetProductsExample.Criteria cri = example.createCriteria();
			cri.andSetIdEqualTo(t.getProductId());
			setProductsMapper.deleteByExample(example);
			setProductsMapper.insertProductSets(setList);
			return new CommonResult<String>(0, "success", "success");
		}
		return new CommonResult<String>(1, "failed", "failed");
	}

}
