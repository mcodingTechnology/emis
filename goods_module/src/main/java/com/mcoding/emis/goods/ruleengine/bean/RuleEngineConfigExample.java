package com.mcoding.emis.goods.ruleengine.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mcoding.base.core.PageView;

public class RuleEngineConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<RuleEngineConfig> pageView;
    
    public PageView<RuleEngineConfig> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<RuleEngineConfig> pageView) {
		this.pageView = pageView;
	}

    public RuleEngineConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNull() {
            addCriterion("rule_name is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("rule_name =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("rule_name <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("rule_name >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("rule_name >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("rule_name <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("rule_name <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("rule_name like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("rule_name not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("rule_name in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("rule_name not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("rule_name between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("rule_name not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleTypeIsNull() {
            addCriterion("rule_type is null");
            return (Criteria) this;
        }

        public Criteria andRuleTypeIsNotNull() {
            addCriterion("rule_type is not null");
            return (Criteria) this;
        }

        public Criteria andRuleTypeEqualTo(Integer value) {
            addCriterion("rule_type =", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeNotEqualTo(Integer value) {
            addCriterion("rule_type <>", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeGreaterThan(Integer value) {
            addCriterion("rule_type >", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_type >=", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeLessThan(Integer value) {
            addCriterion("rule_type <", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rule_type <=", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeIn(List<Integer> values) {
            addCriterion("rule_type in", values, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeNotIn(List<Integer> values) {
            addCriterion("rule_type not in", values, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeBetween(Integer value1, Integer value2) {
            addCriterion("rule_type between", value1, value2, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_type not between", value1, value2, "ruleType");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andOrigPriceIsNull() {
            addCriterion("orig_price is null");
            return (Criteria) this;
        }

        public Criteria andOrigPriceIsNotNull() {
            addCriterion("orig_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrigPriceEqualTo(Integer value) {
            addCriterion("orig_price =", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceNotEqualTo(Integer value) {
            addCriterion("orig_price <>", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceGreaterThan(Integer value) {
            addCriterion("orig_price >", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("orig_price >=", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceLessThan(Integer value) {
            addCriterion("orig_price <", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceLessThanOrEqualTo(Integer value) {
            addCriterion("orig_price <=", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceIn(List<Integer> values) {
            addCriterion("orig_price in", values, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceNotIn(List<Integer> values) {
            addCriterion("orig_price not in", values, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceBetween(Integer value1, Integer value2) {
            addCriterion("orig_price between", value1, value2, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("orig_price not between", value1, value2, "origPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceIsNull() {
            addCriterion("promo_price is null");
            return (Criteria) this;
        }

        public Criteria andPromoPriceIsNotNull() {
            addCriterion("promo_price is not null");
            return (Criteria) this;
        }

        public Criteria andPromoPriceEqualTo(Integer value) {
            addCriterion("promo_price =", value, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceNotEqualTo(Integer value) {
            addCriterion("promo_price <>", value, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceGreaterThan(Integer value) {
            addCriterion("promo_price >", value, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("promo_price >=", value, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceLessThan(Integer value) {
            addCriterion("promo_price <", value, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceLessThanOrEqualTo(Integer value) {
            addCriterion("promo_price <=", value, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceIn(List<Integer> values) {
            addCriterion("promo_price in", values, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceNotIn(List<Integer> values) {
            addCriterion("promo_price not in", values, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceBetween(Integer value1, Integer value2) {
            addCriterion("promo_price between", value1, value2, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPromoPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("promo_price not between", value1, value2, "promoPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIsNull() {
            addCriterion("purchase_num is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIsNotNull() {
            addCriterion("purchase_num is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumEqualTo(Integer value) {
            addCriterion("purchase_num =", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotEqualTo(Integer value) {
            addCriterion("purchase_num <>", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumGreaterThan(Integer value) {
            addCriterion("purchase_num >", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_num >=", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLessThan(Integer value) {
            addCriterion("purchase_num <", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_num <=", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIn(List<Integer> values) {
            addCriterion("purchase_num in", values, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotIn(List<Integer> values) {
            addCriterion("purchase_num not in", values, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumBetween(Integer value1, Integer value2) {
            addCriterion("purchase_num between", value1, value2, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_num not between", value1, value2, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdIsNull() {
            addCriterion("gift_product_id is null");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdIsNotNull() {
            addCriterion("gift_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdEqualTo(Integer value) {
            addCriterion("gift_product_id =", value, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdNotEqualTo(Integer value) {
            addCriterion("gift_product_id <>", value, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdGreaterThan(Integer value) {
            addCriterion("gift_product_id >", value, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gift_product_id >=", value, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdLessThan(Integer value) {
            addCriterion("gift_product_id <", value, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("gift_product_id <=", value, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdIn(List<Integer> values) {
            addCriterion("gift_product_id in", values, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdNotIn(List<Integer> values) {
            addCriterion("gift_product_id not in", values, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdBetween(Integer value1, Integer value2) {
            addCriterion("gift_product_id between", value1, value2, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gift_product_id not between", value1, value2, "giftProductId");
            return (Criteria) this;
        }

        public Criteria andGiftNumIsNull() {
            addCriterion("gift_num is null");
            return (Criteria) this;
        }

        public Criteria andGiftNumIsNotNull() {
            addCriterion("gift_num is not null");
            return (Criteria) this;
        }

        public Criteria andGiftNumEqualTo(Integer value) {
            addCriterion("gift_num =", value, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumNotEqualTo(Integer value) {
            addCriterion("gift_num <>", value, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumGreaterThan(Integer value) {
            addCriterion("gift_num >", value, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("gift_num >=", value, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumLessThan(Integer value) {
            addCriterion("gift_num <", value, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumLessThanOrEqualTo(Integer value) {
            addCriterion("gift_num <=", value, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumIn(List<Integer> values) {
            addCriterion("gift_num in", values, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumNotIn(List<Integer> values) {
            addCriterion("gift_num not in", values, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumBetween(Integer value1, Integer value2) {
            addCriterion("gift_num between", value1, value2, "giftNum");
            return (Criteria) this;
        }

        public Criteria andGiftNumNotBetween(Integer value1, Integer value2) {
            addCriterion("gift_num not between", value1, value2, "giftNum");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitIsNull() {
            addCriterion("promo_min_limit is null");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitIsNotNull() {
            addCriterion("promo_min_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitEqualTo(Integer value) {
            addCriterion("promo_min_limit =", value, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitNotEqualTo(Integer value) {
            addCriterion("promo_min_limit <>", value, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitGreaterThan(Integer value) {
            addCriterion("promo_min_limit >", value, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("promo_min_limit >=", value, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitLessThan(Integer value) {
            addCriterion("promo_min_limit <", value, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitLessThanOrEqualTo(Integer value) {
            addCriterion("promo_min_limit <=", value, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitIn(List<Integer> values) {
            addCriterion("promo_min_limit in", values, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitNotIn(List<Integer> values) {
            addCriterion("promo_min_limit not in", values, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitBetween(Integer value1, Integer value2) {
            addCriterion("promo_min_limit between", value1, value2, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andPromoMinLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("promo_min_limit not between", value1, value2, "promoMinLimit");
            return (Criteria) this;
        }

        public Criteria andReturnBackIsNull() {
            addCriterion("return_back is null");
            return (Criteria) this;
        }

        public Criteria andReturnBackIsNotNull() {
            addCriterion("return_back is not null");
            return (Criteria) this;
        }

        public Criteria andReturnBackEqualTo(Integer value) {
            addCriterion("return_back =", value, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackNotEqualTo(Integer value) {
            addCriterion("return_back <>", value, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackGreaterThan(Integer value) {
            addCriterion("return_back >", value, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_back >=", value, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackLessThan(Integer value) {
            addCriterion("return_back <", value, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackLessThanOrEqualTo(Integer value) {
            addCriterion("return_back <=", value, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackIn(List<Integer> values) {
            addCriterion("return_back in", values, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackNotIn(List<Integer> values) {
            addCriterion("return_back not in", values, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackBetween(Integer value1, Integer value2) {
            addCriterion("return_back between", value1, value2, "returnBack");
            return (Criteria) this;
        }

        public Criteria andReturnBackNotBetween(Integer value1, Integer value2) {
            addCriterion("return_back not between", value1, value2, "returnBack");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(Integer value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(Integer value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(Integer value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(Integer value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(Integer value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<Integer> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<Integer> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(Integer value1, Integer value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(Integer value1, Integer value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNull() {
            addCriterion("brand_code is null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNotNull() {
            addCriterion("brand_code is not null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeEqualTo(String value) {
            addCriterion("brand_code =", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotEqualTo(String value) {
            addCriterion("brand_code <>", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThan(String value) {
            addCriterion("brand_code >", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThanOrEqualTo(String value) {
            addCriterion("brand_code >=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThan(String value) {
            addCriterion("brand_code <", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThanOrEqualTo(String value) {
            addCriterion("brand_code <=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLike(String value) {
            addCriterion("brand_code like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotLike(String value) {
            addCriterion("brand_code not like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIn(List<String> values) {
            addCriterion("brand_code in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotIn(List<String> values) {
            addCriterion("brand_code not in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeBetween(String value1, String value2) {
            addCriterion("brand_code between", value1, value2, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotBetween(String value1, String value2) {
            addCriterion("brand_code not between", value1, value2, "brandCode");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}