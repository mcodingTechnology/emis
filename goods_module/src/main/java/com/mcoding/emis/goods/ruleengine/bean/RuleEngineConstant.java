package com.mcoding.emis.goods.ruleengine.bean;

public class RuleEngineConstant {

	public static final Integer SUCCESS_CODE = 0;
	public static final String SUCCESS_DESC = "操作成功";
	public static final Integer ERROR_CODE = 1;
	public static final String ERROR_DESC = "处理失败";
	
	public static final String ORDER_TYPE_SINGLE = "single";
	public static final String ORDER_TYPE_CART = "cart";
	public static final String ORDER_TYPE_MANY = "many";
	public static final String ORDER_TYPE_ORDERNOW = "ordernow";
	public static final String ORDER_TYPE_ALREADY = "already";
	
	public static final String ORDER_COMMIT_MAP_IN_SESSION = "commitOrderInfoMap";
	//匹配规则返回的map中的总价
	public static final String MAP_PRODUCT_TOTAL_PRICE = "productTotalPrice";
	//购买的商品列表
	public static final String MAP_ORDER_PRODUCT_LIST = "orderProductList";
	
	public static final String MAP_ALREADY_ORDER_INFO = "orderInfo";
	
	public static final String MAP_ORDER_GIFT_LIST = "giftList";
	public static final String MAP_PREFERENTIAL_PRICE = "preferentialPrice";
	//邮费，后台配置
	public static final String MAP_FREIGHT = "freight";
	//订单应付邮费
	public static final String MAP_PAY_REEIGHT = "payFreight";
	public static final String MAP_ORDER_TYPE = "orderType";
	
	//ruleType:全场满减
	public static final Integer RULE_TYPE_OVERALL_PROMOMINLIMIT_RETURNBACK = 1;
	//ruleType:全场满赠
	public static final Integer RULE_TYPE_OVERALL_PROMOMINLIMIT_GIFT = 2;
	//ruleType:买多分促销价
	public static final Integer RULE_TYPE_PURCHASE_MULIT_PROMOPRICE = 3;
	//ruleType:买多分送赠品
	public static final Integer RULE_TYPE_PURCHASE_MULTI_GIFT = 4;
	//ruleType:限时促销价
	public static final Integer RULE_TYPE_TIME_LIMIT_PROMOPRICE = 5;
	//ruleType:限时送赠品
	public static final Integer RULE_TYPE_TIME_LIMIT_GIFT = 6;
	//ruleType:累加多份送赠品,暂时无用
	public static final Integer RULE_TYPE_ACCU_MULIT_GIFT = 7;
	//ruleType:全场满额包邮
	public static final Integer RULE_TYPE_OVERALL_FREE_FREIGHT = 8;
	//ruleType:单品满额包邮
	public static final Integer RULE_TYPE_PRODUCT_FREE_FREIGHT = 9;
	//ruleType:单品满额包邮
	public static final Integer RULE_TYPE_HASPARENT_OVERALL_FREE_FREIGHT = 11;
			
}
