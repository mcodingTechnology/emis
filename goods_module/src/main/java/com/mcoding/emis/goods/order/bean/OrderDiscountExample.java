package com.mcoding.emis.goods.order.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDiscountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDiscountExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNull() {
            addCriterion("discount_type is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNotNull() {
            addCriterion("discount_type is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeEqualTo(Short value) {
            addCriterion("discount_type =", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotEqualTo(Short value) {
            addCriterion("discount_type <>", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThan(Short value) {
            addCriterion("discount_type >", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("discount_type >=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThan(Short value) {
            addCriterion("discount_type <", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThanOrEqualTo(Short value) {
            addCriterion("discount_type <=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIn(List<Short> values) {
            addCriterion("discount_type in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotIn(List<Short> values) {
            addCriterion("discount_type not in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeBetween(Short value1, Short value2) {
            addCriterion("discount_type between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotBetween(Short value1, Short value2) {
            addCriterion("discount_type not between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andPromoDescIsNull() {
            addCriterion("promo_desc is null");
            return (Criteria) this;
        }

        public Criteria andPromoDescIsNotNull() {
            addCriterion("promo_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPromoDescEqualTo(String value) {
            addCriterion("promo_desc =", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescNotEqualTo(String value) {
            addCriterion("promo_desc <>", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescGreaterThan(String value) {
            addCriterion("promo_desc >", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescGreaterThanOrEqualTo(String value) {
            addCriterion("promo_desc >=", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescLessThan(String value) {
            addCriterion("promo_desc <", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescLessThanOrEqualTo(String value) {
            addCriterion("promo_desc <=", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescLike(String value) {
            addCriterion("promo_desc like", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescNotLike(String value) {
            addCriterion("promo_desc not like", value, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescIn(List<String> values) {
            addCriterion("promo_desc in", values, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescNotIn(List<String> values) {
            addCriterion("promo_desc not in", values, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescBetween(String value1, String value2) {
            addCriterion("promo_desc between", value1, value2, "promoDesc");
            return (Criteria) this;
        }

        public Criteria andPromoDescNotBetween(String value1, String value2) {
            addCriterion("promo_desc not between", value1, value2, "promoDesc");
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

        public Criteria andPreferentialPriceIsNull() {
            addCriterion("preferential_price is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceIsNotNull() {
            addCriterion("preferential_price is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceEqualTo(Integer value) {
            addCriterion("preferential_price =", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceNotEqualTo(Integer value) {
            addCriterion("preferential_price <>", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceGreaterThan(Integer value) {
            addCriterion("preferential_price >", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("preferential_price >=", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceLessThan(Integer value) {
            addCriterion("preferential_price <", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceLessThanOrEqualTo(Integer value) {
            addCriterion("preferential_price <=", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceIn(List<Integer> values) {
            addCriterion("preferential_price in", values, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceNotIn(List<Integer> values) {
            addCriterion("preferential_price not in", values, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceBetween(Integer value1, Integer value2) {
            addCriterion("preferential_price between", value1, value2, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("preferential_price not between", value1, value2, "preferentialPrice");
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
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

        public Criteria andGiftProductNameIsNull() {
            addCriterion("gift_product_name is null");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameIsNotNull() {
            addCriterion("gift_product_name is not null");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameEqualTo(String value) {
            addCriterion("gift_product_name =", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameNotEqualTo(String value) {
            addCriterion("gift_product_name <>", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameGreaterThan(String value) {
            addCriterion("gift_product_name >", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("gift_product_name >=", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameLessThan(String value) {
            addCriterion("gift_product_name <", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameLessThanOrEqualTo(String value) {
            addCriterion("gift_product_name <=", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameLike(String value) {
            addCriterion("gift_product_name like", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameNotLike(String value) {
            addCriterion("gift_product_name not like", value, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameIn(List<String> values) {
            addCriterion("gift_product_name in", values, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameNotIn(List<String> values) {
            addCriterion("gift_product_name not in", values, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameBetween(String value1, String value2) {
            addCriterion("gift_product_name between", value1, value2, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNameNotBetween(String value1, String value2) {
            addCriterion("gift_product_name not between", value1, value2, "giftProductName");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumIsNull() {
            addCriterion("gift_product_num is null");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumIsNotNull() {
            addCriterion("gift_product_num is not null");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumEqualTo(Integer value) {
            addCriterion("gift_product_num =", value, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumNotEqualTo(Integer value) {
            addCriterion("gift_product_num <>", value, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumGreaterThan(Integer value) {
            addCriterion("gift_product_num >", value, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("gift_product_num >=", value, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumLessThan(Integer value) {
            addCriterion("gift_product_num <", value, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumLessThanOrEqualTo(Integer value) {
            addCriterion("gift_product_num <=", value, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumIn(List<Integer> values) {
            addCriterion("gift_product_num in", values, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumNotIn(List<Integer> values) {
            addCriterion("gift_product_num not in", values, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumBetween(Integer value1, Integer value2) {
            addCriterion("gift_product_num between", value1, value2, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andGiftProductNumNotBetween(Integer value1, Integer value2) {
            addCriterion("gift_product_num not between", value1, value2, "giftProductNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumIsNull() {
            addCriterion("require_purchase_num is null");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumIsNotNull() {
            addCriterion("require_purchase_num is not null");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumEqualTo(Integer value) {
            addCriterion("require_purchase_num =", value, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumNotEqualTo(Integer value) {
            addCriterion("require_purchase_num <>", value, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumGreaterThan(Integer value) {
            addCriterion("require_purchase_num >", value, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("require_purchase_num >=", value, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumLessThan(Integer value) {
            addCriterion("require_purchase_num <", value, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumLessThanOrEqualTo(Integer value) {
            addCriterion("require_purchase_num <=", value, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumIn(List<Integer> values) {
            addCriterion("require_purchase_num in", values, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumNotIn(List<Integer> values) {
            addCriterion("require_purchase_num not in", values, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumBetween(Integer value1, Integer value2) {
            addCriterion("require_purchase_num between", value1, value2, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andRequirePurchaseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("require_purchase_num not between", value1, value2, "requirePurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumIsNull() {
            addCriterion("actual_purchase_num is null");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumIsNotNull() {
            addCriterion("actual_purchase_num is not null");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumEqualTo(Integer value) {
            addCriterion("actual_purchase_num =", value, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumNotEqualTo(Integer value) {
            addCriterion("actual_purchase_num <>", value, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumGreaterThan(Integer value) {
            addCriterion("actual_purchase_num >", value, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_purchase_num >=", value, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumLessThan(Integer value) {
            addCriterion("actual_purchase_num <", value, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumLessThanOrEqualTo(Integer value) {
            addCriterion("actual_purchase_num <=", value, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumIn(List<Integer> values) {
            addCriterion("actual_purchase_num in", values, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumNotIn(List<Integer> values) {
            addCriterion("actual_purchase_num not in", values, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumBetween(Integer value1, Integer value2) {
            addCriterion("actual_purchase_num between", value1, value2, "actualPurchaseNum");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_purchase_num not between", value1, value2, "actualPurchaseNum");
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