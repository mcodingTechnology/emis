package com.mcoding.emis.goods.product.bean;

import java.io.Serializable;
import java.util.Date;

/**  
* 产品积分表
* @author Benson 
* 2015年3月13日 下午1:04:45  
*/   

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String PRODUCT_LABEL_INTERIOR = "内购商品";
	public static final String PRODUCT_LABEL_PERSONALINTERIOR = "个人内购商品";
	public static final String PRODUCT_LABEL_LIMIT = "限购商品";

	//产品编号
	private Integer ProductId;
	
	//产品名称
	private String productName;
	
	//产品标签
	private String productLabel;
	
	//产品内容
	private String productContent;
	
	//产品码
	private String productCode;
	
	//产品货号
	private String productNo;
	
	//条形码
	private String barCode;

	//产品功能介绍
	private String productIntroduce;
	
	//广告语
	private String slogan;
	
	//产品封面图片
	private String productCoverImg;
	
	//积分值
	private Integer productPoint;
	
	//是否积过分 0 未积 1 已积
	private Integer isPointed;
	
	//产品类型 默认：商品 ，其他：赠品
	private String productType;
	
    //积分时间
    private Date addDate;    

    //创建时间
    private Date createTime;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

    //更新时间
    private Date updateTime;
    
    //品牌编码
    private String brandCode;
    
    //原价
    private Integer originalPrice;
    //折扣价
    private Integer discountPrice;
    //微商价
    private Integer microMallPrice;
    //摘要
    private String productSummary;
    //是否上架
    private int isSale;
    //天猫URL
    private String tmallUrl;
    //京东URL
    private String jdUrl;
    //一号店URL
    private String yhdUrl;
    //微商城产品封面图片
    private String microproductcoverimg;
    //微商城产品详情图片
    private String microproductcontent;
    //微商城产品滚动图片1
    private String microproductrollimg1;
    //微商城产品滚动图片2
    private String microproductrollimg2;
    //微商城产品滚动图片3
    private String microproductrollimg3;
    //微商城产品滚动图片4
    private String microproductrollimg4;
    //微商城产品滚动图片5
    private String microproductrollimg5;
    //是否礼品 0是 1否
    private Integer isGift;
    //兑换礼品所需积分
    private Integer giftPoint;
	//积分加钱购所需积分
	private Integer giftPlusPoint;
    //礼品活动优惠积分
    private Integer discountPoint;
    //销量
    private Integer saleNum;
    //兑换量
    private Integer exchangeNum;
    //是否开启优惠积分 0不开 1开启
    private Integer isOpenDsicountPoint;
    //限购商品数量
    private Integer quotaNum;
    //备注 是否参与限购
    private String ext;
    //备注1 限购份数
    private String ext1;
    //产品广告图
    private String productAdImg;
    //是否显示在微商城首页 0不显示 1显示
    private String productAdType;
    //商品排序
    private Integer productSequence;
    //是否套餐 yes是套餐  no不是套餐
    private String isSet;
    //是否在官网上展示 0不展示 1展示
    private int isShowInOfficialWeb;
    
    private String pointSum;//非字段值，积分总额
    
    private Integer sort[];//非字段值，产品类目排序
    private Integer categoryByneed;//非字段值，按需求查找类目id
    private Integer categoryBypeople;//非字段值，按人群查找类目id
    private Integer categoryByingredient;//非字段值，按成分查找类目id
    private String[] productCategoryIdneed;//非字段值，按需求查找产品类目id
    private String[] productCategoryIdpeople;//非字段值，按人群查找产品类目id
    private String[] productCategoryIdingredient;//非字段值，按成分查找产品类目id
    private String[] productCategoryIdpreferential;//非字段值，按成分查找产品类目id
    private String[] productCategoryIdingredientJLD;//非字段值，按成分查找产品类目id
    private String[] productCategoryIdfunction;//非字段值，按成分查找产品类目id
    
    //非字段值，是否存在产品 0是产品 1产品不存在
    private String isProduct;
    
    //非字段值，productAdType为0时的排序
    private Integer sequenceTypeZero;
    //非字段值，productAdType为1时的排序
    private Integer sequenceTypeOne;
    //非字段值，用于与mr_product_sequence关联查询时
    private Integer sequence;
    
    private String preferredProductId;//推荐产品
    
    private Integer giftPointMoney;//兑换礼品所需积分加钱购

	private Integer discountGiftPlusPoint;//兑换礼品所需积分加钱购
    
    private Integer discountPointMoney;//会员日优惠积分

    //非字段，记录套餐子产品id
    private String productSetId;
    //非字段，记录套餐子产品数量
    private String productSetNum;
    //非字段，记录购买商品数
    private Integer purchaseNum;
    //单位
    private String unit;
    private String busiScenario;
    
 // 预售开始时间
    private Date presellEndTime;
    // 预售结束时间
    private Date presellStartTime;
    // 预售定金金额
    private Integer presellDownPayment;
    // 秒杀开始时间
    private Date seckillStartTime;
    // 秒杀结束时间
    private Date seckillEndTime;
    // 限时团购开始时间
    private Date timeLimitStartTime;
    // 限时团购结束时间
    private Date timeLimitEndTime;
    // 团购人数
    private Integer bulkNumber;
    // 团购打折数
    private String bulkDiscountNumber;
    
    
    // 价格设置 - 起订量
    private Integer minimumQuantity;
    
    
	public Date getPresellEndTime() {
		return presellEndTime;
	}

	public void setPresellEndTime(Date presellEndTime) {
		this.presellEndTime = presellEndTime;
	}

	public Date getPresellStartTime() {
		return presellStartTime;
	}

	public void setPresellStartTime(Date presellStartTime) {
		this.presellStartTime = presellStartTime;
	}

	public Integer getPresellDownPayment() {
		return presellDownPayment;
	}

	public void setPresellDownPayment(Integer presellDownPayment) {
		this.presellDownPayment = presellDownPayment;
	}

	public Date getSeckillStartTime() {
		return seckillStartTime;
	}

	public void setSeckillStartTime(Date seckillStartTime) {
		this.seckillStartTime = seckillStartTime;
	}

	public Date getSeckillEndTime() {
		return seckillEndTime;
	}

	public void setSeckillEndTime(Date seckillEndTime) {
		this.seckillEndTime = seckillEndTime;
	}

	public Date getTimeLimitStartTime() {
		return timeLimitStartTime;
	}

	public void setTimeLimitStartTime(Date timeLimitStartTime) {
		this.timeLimitStartTime = timeLimitStartTime;
	}

	public Date getTimeLimitEndTime() {
		return timeLimitEndTime;
	}

	public void setTimeLimitEndTime(Date timeLimitEndTime) {
		this.timeLimitEndTime = timeLimitEndTime;
	}

	public Integer getBulkNumber() {
		return bulkNumber;
	}

	public void setBulkNumber(Integer bulkNumber) {
		this.bulkNumber = bulkNumber;
	}

	public String getBulkDiscountNumber() {
		return bulkDiscountNumber;
	}

	public void setBulkDiscountNumber(String bulkDiscountNumber) {
		this.bulkDiscountNumber = bulkDiscountNumber;
	}

	public void setMicroMallPrice(Integer microMallPrice) {
		this.microMallPrice = microMallPrice;
	}

	public String getBusiScenario() {
		return busiScenario;
	}

	public void setBusiScenario(String busiScenario) {
		this.busiScenario = busiScenario;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getProductId() {
		return ProductId;
	}

	public void setProductId(Integer productId) {
		ProductId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getProductPoint() {
		return productPoint;
	}

	public void setProductPoint(Integer productPoint) {
		this.productPoint = productPoint;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsPointed() {
		return isPointed;
	}

	public void setIsPointed(Integer isPointed) {
		this.isPointed = isPointed;
	}

	public String getPointSum() {
		return pointSum;
	}

	public void setPointSum(String pointSum) {
		this.pointSum = pointSum;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getIsProduct() {
		return isProduct;
	}

	public void setIsProduct(String isProduct) {
		this.isProduct = isProduct;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductLabel() {
		return productLabel;
	}

	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}


	public Integer getCategoryByneed() {
		return categoryByneed;
	}

	public void setCategoryByneed(Integer categoryByneed) {
		this.categoryByneed = categoryByneed;
	}

	public Integer getCategoryBypeople() {
		return categoryBypeople;
	}

	public void setCategoryBypeople(Integer categoryBypeople) {
		this.categoryBypeople = categoryBypeople;
	}

	public Integer getCategoryByingredient() {
		return categoryByingredient;
	}

	public void setCategoryByingredient(Integer categoryByingredient) {
		this.categoryByingredient = categoryByingredient;
	}

	public String[] getProductCategoryIdneed() {
		return productCategoryIdneed;
	}

	public void setProductCategoryIdneed(String[] productCategoryIdneed) {
		this.productCategoryIdneed = productCategoryIdneed;
	}

	public String[] getProductCategoryIdpeople() {
		return productCategoryIdpeople;
	}

	public void setProductCategoryIdpeople(String[] productCategoryIdpeople) {
		this.productCategoryIdpeople = productCategoryIdpeople;
	}

	public String[] getProductCategoryIdingredient() {
		return productCategoryIdingredient;
	}

	public void setProductCategoryIdingredient(String[] productCategoryIdingredient) {
		this.productCategoryIdingredient = productCategoryIdingredient;
	}

	public Integer[] getSort() {
		return sort;
	}

	public void setSort(Integer[] sort) {
		this.sort = sort;
	}

	public String getProductIntroduce() {
		return productIntroduce;
	}

	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getProductCoverImg() {
		return productCoverImg;
	}

	public void setProductCoverImg(String productCoverImg) {
		this.productCoverImg = productCoverImg;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}

	public String[] getProductCategoryIdingredientJLD() {
		return productCategoryIdingredientJLD;
	}

	public void setProductCategoryIdingredientJLD(
			String[] productCategoryIdingredientJLD) {
		this.productCategoryIdingredientJLD = productCategoryIdingredientJLD;
	}

	public String[] getProductCategoryIdfunction() {
		return productCategoryIdfunction;
	}

	public void setProductCategoryIdfunction(String[] productCategoryIdfunction) {
		this.productCategoryIdfunction = productCategoryIdfunction;
	}

	public int getIsSale() {
		return isSale;
	}

	public void setIsSale(int isSale) {
		this.isSale = isSale;
	}

	public String getTmallUrl() {
		return tmallUrl;
	}

	public void setTmallUrl(String tmallUrl) {
		this.tmallUrl = tmallUrl;
	}

	public String getJdUrl() {
		return jdUrl;
	}

	public void setJdUrl(String jdUrl) {
		this.jdUrl = jdUrl;
	}

	public String getYhdUrl() {
		return yhdUrl;
	}

	public void setYhdUrl(String yhdUrl) {
		this.yhdUrl = yhdUrl;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getMicroproductcoverimg() {
		return microproductcoverimg;
	}

	public void setMicroproductcoverimg(String microproductcoverimg) {
		this.microproductcoverimg = microproductcoverimg;
	}

	public String getMicroproductcontent() {
		return microproductcontent;
	}

	public void setMicroproductcontent(String microproductcontent) {
		this.microproductcontent = microproductcontent;
	}

	public String getMicroproductrollimg1() {
		return microproductrollimg1;
	}

	public void setMicroproductrollimg1(String microproductrollimg1) {
		this.microproductrollimg1 = microproductrollimg1;
	}

	public String getMicroproductrollimg2() {
		return microproductrollimg2;
	}

	public void setMicroproductrollimg2(String microproductrollimg2) {
		this.microproductrollimg2 = microproductrollimg2;
	}

	public String getMicroproductrollimg3() {
		return microproductrollimg3;
	}

	public void setMicroproductrollimg3(String microproductrollimg3) {
		this.microproductrollimg3 = microproductrollimg3;
	}

	public String getMicroproductrollimg4() {
		return microproductrollimg4;
	}

	public void setMicroproductrollimg4(String microproductrollimg4) {
		this.microproductrollimg4 = microproductrollimg4;
	}

	public String getMicroproductrollimg5() {
		return microproductrollimg5;
	}

	public void setMicroproductrollimg5(String microproductrollimg5) {
		this.microproductrollimg5 = microproductrollimg5;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getMicroMallPrice() {
		return microMallPrice;
	}

	public void setMicroMallPrice1(Integer microMallPrice) {
		this.microMallPrice = microMallPrice;
	}

	public Integer getIsGift() {
		return isGift;
	}

	public void setIsGift(Integer isGift) {
		this.isGift = isGift;
	}

	public Integer getGiftPoint() {
		return giftPoint;
	}

	public void setGiftPoint(Integer giftPoint) {
		this.giftPoint = giftPoint;
	}

	public Integer getDiscountPoint() {
		return discountPoint;
	}

	public void setDiscountPoint(Integer discountPoint) {
		this.discountPoint = discountPoint;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getExchangeNum() {
		return exchangeNum;
	}

	public void setExchangeNum(Integer exchangeNum) {
		this.exchangeNum = exchangeNum;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public Integer getIsOpenDsicountPoint() {
		return isOpenDsicountPoint;
	}

	public void setIsOpenDsicountPoint(Integer isOpenDsicountPoint) {
		this.isOpenDsicountPoint = isOpenDsicountPoint;
	}

	public String getProductAdImg() {
		return productAdImg;
	}

	public void setProductAdImg(String productAdImg) {
		this.productAdImg = productAdImg;
	}

	public String getProductAdType() {
		return productAdType;
	}

	public void setProductAdType(String productAdType) {
		this.productAdType = productAdType;
	}

	public Integer getQuotaNum() {
		return quotaNum;
	}

	public void setQuotaNum(Integer quotaNum) {
		this.quotaNum = quotaNum;
	}

	public Integer getSequenceTypeZero() {
		return sequenceTypeZero;
	}

	public void setSequenceTypeZero(Integer sequenceTypeZero) {
		this.sequenceTypeZero = sequenceTypeZero;
	}

	public Integer getSequenceTypeOne() {
		return sequenceTypeOne;
	}

	public void setSequenceTypeOne(Integer sequenceTypeOne) {
		this.sequenceTypeOne = sequenceTypeOne;
	}

	public String getPreferredProductId() {
		return preferredProductId;
	}

	public void setPreferredProductId(String preferredProductId) {
		this.preferredProductId = preferredProductId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getProductSequence() {
		return productSequence;
	}

	public void setProductSequence(Integer productSequence) {
		this.productSequence = productSequence;
	}

	public Integer getGiftPointMoney() {
		return giftPointMoney;
	}

	public void setGiftPointMoney(Integer giftPointMoney) {
		this.giftPointMoney = giftPointMoney;
	}

	public Integer getDiscountPointMoney() {
		return discountPointMoney;
	}

	public void setDiscountPointMoney(Integer discountPointMoney) {
		this.discountPointMoney = discountPointMoney;
	}

	public String getProductSetId() {
		return productSetId;
	}

	public void setProductSetId(String productSetId) {
		this.productSetId = productSetId;
	}

	public String getIsSet() {
		return isSet;
	}

	public void setIsSet(String isSet) {
		this.isSet = isSet;
	}

	public String getProductSetNum() {
		return productSetNum;
	}

	public void setProductSetNum(String productSetNum) {
		this.productSetNum = productSetNum;
	}

	public Integer getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Integer getGiftPlusPoint() {
		return giftPlusPoint;
	}

	public void setGiftPlusPoint(Integer giftPlusPoint) {
		this.giftPlusPoint = giftPlusPoint;
	}

	public Integer getDiscountGiftPlusPoint() {
		return discountGiftPlusPoint;
	}

	public void setDiscountGiftPlusPoint(Integer discountGiftPlusPoint) {
		this.discountGiftPlusPoint = discountGiftPlusPoint;
	}

	public int getIsShowInOfficialWeb() {
		return isShowInOfficialWeb;
	}

	public void setIsShowInOfficialWeb(int isShowInOfficialWeb) {
		this.isShowInOfficialWeb = isShowInOfficialWeb;
	}

	public String[] getProductCategoryIdpreferential() {
		return productCategoryIdpreferential;
	}

	public void setProductCategoryIdpreferential(String[] productCategoryIdpreferential) {
		this.productCategoryIdpreferential = productCategoryIdpreferential;
	}

	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

}