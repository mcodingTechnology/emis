package com.mcoding.emis.goods.order.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample implements IExample<Order> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<Order> pageView;
    
    private String isPay;// 是否支付
    
    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    public String getIsPay() {
		return isPay;
	}
	public void setIsPay(String isPay) {
		this.isPay = isPay;
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

    @Override
    public PageView<Order> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<Order> pageView) {
        this.pageView = pageView;
    }

    public String toJson() throws JsonProcessingException {
        return JsonUtilsForMcoding.writeValueAsString(this);
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

        public Criteria andMemberidIsNull() {
            addCriterion("memberId is null");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNotNull() {
            addCriterion("memberId is not null");
            return (Criteria) this;
        }

        public Criteria andMemberidEqualTo(Integer value) {
            addCriterion("memberId =", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotEqualTo(Integer value) {
            addCriterion("memberId <>", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThan(Integer value) {
            addCriterion("memberId >", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThanOrEqualTo(Integer value) {
            addCriterion("memberId >=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThan(Integer value) {
            addCriterion("memberId <", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThanOrEqualTo(Integer value) {
            addCriterion("memberId <=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidIn(List<Integer> values) {
            addCriterion("memberId in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotIn(List<Integer> values) {
            addCriterion("memberId not in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidBetween(Integer value1, Integer value2) {
            addCriterion("memberId between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotBetween(Integer value1, Integer value2) {
            addCriterion("memberId not between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNull() {
            addCriterion("mobilePhone is null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNotNull() {
            addCriterion("mobilePhone is not null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneEqualTo(String value) {
            addCriterion("mobilePhone =", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotEqualTo(String value) {
            addCriterion("mobilePhone <>", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThan(String value) {
            addCriterion("mobilePhone >", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("mobilePhone >=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThan(String value) {
            addCriterion("mobilePhone <", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("mobilePhone <=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLike(String value) {
            addCriterion("mobilePhone like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotLike(String value) {
            addCriterion("mobilePhone not like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIn(List<String> values) {
            addCriterion("mobilePhone in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotIn(List<String> values) {
            addCriterion("mobilePhone not in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneBetween(String value1, String value2) {
            addCriterion("mobilePhone between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotBetween(String value1, String value2) {
            addCriterion("mobilePhone not between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openId is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openId is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openId =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openId <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openId >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openId >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openId <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openId <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openId like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openId not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openId in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openId not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openId between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openId not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Integer value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Integer value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Integer value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Integer value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Integer value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Integer> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Integer> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Integer value1, Integer value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andNumsIsNull() {
            addCriterion("nums is null");
            return (Criteria) this;
        }

        public Criteria andNumsIsNotNull() {
            addCriterion("nums is not null");
            return (Criteria) this;
        }

        public Criteria andNumsEqualTo(Integer value) {
            addCriterion("nums =", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotEqualTo(Integer value) {
            addCriterion("nums <>", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsGreaterThan(Integer value) {
            addCriterion("nums >", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("nums >=", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLessThan(Integer value) {
            addCriterion("nums <", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLessThanOrEqualTo(Integer value) {
            addCriterion("nums <=", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsIn(List<Integer> values) {
            addCriterion("nums in", values, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotIn(List<Integer> values) {
            addCriterion("nums not in", values, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsBetween(Integer value1, Integer value2) {
            addCriterion("nums between", value1, value2, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("nums not between", value1, value2, "nums");
            return (Criteria) this;
        }

        public Criteria andAddressidIsNull() {
            addCriterion("addressId is null");
            return (Criteria) this;
        }

        public Criteria andAddressidIsNotNull() {
            addCriterion("addressId is not null");
            return (Criteria) this;
        }

        public Criteria andAddressidEqualTo(Integer value) {
            addCriterion("addressId =", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidNotEqualTo(Integer value) {
            addCriterion("addressId <>", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidGreaterThan(Integer value) {
            addCriterion("addressId >", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidGreaterThanOrEqualTo(Integer value) {
            addCriterion("addressId >=", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidLessThan(Integer value) {
            addCriterion("addressId <", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidLessThanOrEqualTo(Integer value) {
            addCriterion("addressId <=", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidIn(List<Integer> values) {
            addCriterion("addressId in", values, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidNotIn(List<Integer> values) {
            addCriterion("addressId not in", values, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidBetween(Integer value1, Integer value2) {
            addCriterion("addressId between", value1, value2, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidNotBetween(Integer value1, Integer value2) {
            addCriterion("addressId not between", value1, value2, "addressid");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("payType is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("payType is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(String value) {
            addCriterion("payType =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(String value) {
            addCriterion("payType <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(String value) {
            addCriterion("payType >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(String value) {
            addCriterion("payType >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(String value) {
            addCriterion("payType <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(String value) {
            addCriterion("payType <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLike(String value) {
            addCriterion("payType like", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotLike(String value) {
            addCriterion("payType not like", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<String> values) {
            addCriterion("payType in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<String> values) {
            addCriterion("payType not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(String value1, String value2) {
            addCriterion("payType between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(String value1, String value2) {
            addCriterion("payType not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaystatusIsNull() {
            addCriterion("payStatus is null");
            return (Criteria) this;
        }

        public Criteria andPaystatusIsNotNull() {
            addCriterion("payStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPaystatusEqualTo(String value) {
            addCriterion("payStatus =", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotEqualTo(String value) {
            addCriterion("payStatus <>", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusGreaterThan(String value) {
            addCriterion("payStatus >", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusGreaterThanOrEqualTo(String value) {
            addCriterion("payStatus >=", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusLessThan(String value) {
            addCriterion("payStatus <", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusLessThanOrEqualTo(String value) {
            addCriterion("payStatus <=", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusLike(String value) {
            addCriterion("payStatus like", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotLike(String value) {
            addCriterion("payStatus not like", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusIn(List<String> values) {
            addCriterion("payStatus in", values, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotIn(List<String> values) {
            addCriterion("payStatus not in", values, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusBetween(String value1, String value2) {
            addCriterion("payStatus between", value1, value2, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotBetween(String value1, String value2) {
            addCriterion("payStatus not between", value1, value2, "paystatus");
            return (Criteria) this;
        }

        public Criteria andOrderbrandIsNull() {
            addCriterion("orderBrand is null");
            return (Criteria) this;
        }

        public Criteria andOrderbrandIsNotNull() {
            addCriterion("orderBrand is not null");
            return (Criteria) this;
        }

        public Criteria andOrderbrandEqualTo(String value) {
            addCriterion("orderBrand =", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandNotEqualTo(String value) {
            addCriterion("orderBrand <>", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandGreaterThan(String value) {
            addCriterion("orderBrand >", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandGreaterThanOrEqualTo(String value) {
            addCriterion("orderBrand >=", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandLessThan(String value) {
            addCriterion("orderBrand <", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandLessThanOrEqualTo(String value) {
            addCriterion("orderBrand <=", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandLike(String value) {
            addCriterion("orderBrand like", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandNotLike(String value) {
            addCriterion("orderBrand not like", value, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandIn(List<String> values) {
            addCriterion("orderBrand in", values, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandNotIn(List<String> values) {
            addCriterion("orderBrand not in", values, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandBetween(String value1, String value2) {
            addCriterion("orderBrand between", value1, value2, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderbrandNotBetween(String value1, String value2) {
            addCriterion("orderBrand not between", value1, value2, "orderbrand");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceIsNull() {
            addCriterion("orderPayResource is null");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceIsNotNull() {
            addCriterion("orderPayResource is not null");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceEqualTo(String value) {
            addCriterion("orderPayResource =", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceNotEqualTo(String value) {
            addCriterion("orderPayResource <>", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceGreaterThan(String value) {
            addCriterion("orderPayResource >", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceGreaterThanOrEqualTo(String value) {
            addCriterion("orderPayResource >=", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceLessThan(String value) {
            addCriterion("orderPayResource <", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceLessThanOrEqualTo(String value) {
            addCriterion("orderPayResource <=", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceLike(String value) {
            addCriterion("orderPayResource like", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceNotLike(String value) {
            addCriterion("orderPayResource not like", value, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceIn(List<String> values) {
            addCriterion("orderPayResource in", values, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceNotIn(List<String> values) {
            addCriterion("orderPayResource not in", values, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceBetween(String value1, String value2) {
            addCriterion("orderPayResource between", value1, value2, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andOrderpayresourceNotBetween(String value1, String value2) {
            addCriterion("orderPayResource not between", value1, value2, "orderpayresource");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addTime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addTime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addTime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addTime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addTime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addTime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addTime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addTime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addTime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addTime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addTime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addTime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("payTime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("payTime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterion("payTime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("payTime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("payTime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payTime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterion("payTime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("payTime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterion("payTime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("payTime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("payTime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("payTime not between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("sendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("sendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("sendTime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("sendTime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("sendTime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sendTime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("sendTime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("sendTime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("sendTime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("sendTime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("sendTime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("sendTime not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIsNull() {
            addCriterion("receiveTime is null");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIsNotNull() {
            addCriterion("receiveTime is not null");
            return (Criteria) this;
        }

        public Criteria andReceivetimeEqualTo(Date value) {
            addCriterion("receiveTime =", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotEqualTo(Date value) {
            addCriterion("receiveTime <>", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeGreaterThan(Date value) {
            addCriterion("receiveTime >", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receiveTime >=", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLessThan(Date value) {
            addCriterion("receiveTime <", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLessThanOrEqualTo(Date value) {
            addCriterion("receiveTime <=", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIn(List<Date> values) {
            addCriterion("receiveTime in", values, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotIn(List<Date> values) {
            addCriterion("receiveTime not in", values, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeBetween(Date value1, Date value2) {
            addCriterion("receiveTime between", value1, value2, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotBetween(Date value1, Date value2) {
            addCriterion("receiveTime not between", value1, value2, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReturntimeIsNull() {
            addCriterion("returnTime is null");
            return (Criteria) this;
        }

        public Criteria andReturntimeIsNotNull() {
            addCriterion("returnTime is not null");
            return (Criteria) this;
        }

        public Criteria andReturntimeEqualTo(Date value) {
            addCriterion("returnTime =", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeNotEqualTo(Date value) {
            addCriterion("returnTime <>", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeGreaterThan(Date value) {
            addCriterion("returnTime >", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("returnTime >=", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeLessThan(Date value) {
            addCriterion("returnTime <", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeLessThanOrEqualTo(Date value) {
            addCriterion("returnTime <=", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeIn(List<Date> values) {
            addCriterion("returnTime in", values, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeNotIn(List<Date> values) {
            addCriterion("returnTime not in", values, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeBetween(Date value1, Date value2) {
            addCriterion("returnTime between", value1, value2, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeNotBetween(Date value1, Date value2) {
            addCriterion("returnTime not between", value1, value2, "returntime");
            return (Criteria) this;
        }

        public Criteria andThirdnoIsNull() {
            addCriterion("thirdNo is null");
            return (Criteria) this;
        }

        public Criteria andThirdnoIsNotNull() {
            addCriterion("thirdNo is not null");
            return (Criteria) this;
        }

        public Criteria andThirdnoEqualTo(String value) {
            addCriterion("thirdNo =", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoNotEqualTo(String value) {
            addCriterion("thirdNo <>", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoGreaterThan(String value) {
            addCriterion("thirdNo >", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoGreaterThanOrEqualTo(String value) {
            addCriterion("thirdNo >=", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoLessThan(String value) {
            addCriterion("thirdNo <", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoLessThanOrEqualTo(String value) {
            addCriterion("thirdNo <=", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoLike(String value) {
            addCriterion("thirdNo like", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoNotLike(String value) {
            addCriterion("thirdNo not like", value, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoIn(List<String> values) {
            addCriterion("thirdNo in", values, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoNotIn(List<String> values) {
            addCriterion("thirdNo not in", values, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoBetween(String value1, String value2) {
            addCriterion("thirdNo between", value1, value2, "thirdno");
            return (Criteria) this;
        }

        public Criteria andThirdnoNotBetween(String value1, String value2) {
            addCriterion("thirdNo not between", value1, value2, "thirdno");
            return (Criteria) this;
        }

        public Criteria andTradenoIsNull() {
            addCriterion("tradeNo is null");
            return (Criteria) this;
        }

        public Criteria andTradenoIsNotNull() {
            addCriterion("tradeNo is not null");
            return (Criteria) this;
        }

        public Criteria andTradenoEqualTo(String value) {
            addCriterion("tradeNo =", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotEqualTo(String value) {
            addCriterion("tradeNo <>", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoGreaterThan(String value) {
            addCriterion("tradeNo >", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoGreaterThanOrEqualTo(String value) {
            addCriterion("tradeNo >=", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoLessThan(String value) {
            addCriterion("tradeNo <", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoLessThanOrEqualTo(String value) {
            addCriterion("tradeNo <=", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoLike(String value) {
            addCriterion("tradeNo like", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotLike(String value) {
            addCriterion("tradeNo not like", value, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoIn(List<String> values) {
            addCriterion("tradeNo in", values, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotIn(List<String> values) {
            addCriterion("tradeNo not in", values, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoBetween(String value1, String value2) {
            addCriterion("tradeNo between", value1, value2, "tradeno");
            return (Criteria) this;
        }

        public Criteria andTradenoNotBetween(String value1, String value2) {
            addCriterion("tradeNo not between", value1, value2, "tradeno");
            return (Criteria) this;
        }

        public Criteria andOutnoIsNull() {
            addCriterion("outNo is null");
            return (Criteria) this;
        }

        public Criteria andOutnoIsNotNull() {
            addCriterion("outNo is not null");
            return (Criteria) this;
        }

        public Criteria andOutnoEqualTo(String value) {
            addCriterion("outNo =", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoNotEqualTo(String value) {
            addCriterion("outNo <>", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoGreaterThan(String value) {
            addCriterion("outNo >", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoGreaterThanOrEqualTo(String value) {
            addCriterion("outNo >=", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoLessThan(String value) {
            addCriterion("outNo <", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoLessThanOrEqualTo(String value) {
            addCriterion("outNo <=", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoLike(String value) {
            addCriterion("outNo like", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoNotLike(String value) {
            addCriterion("outNo not like", value, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoIn(List<String> values) {
            addCriterion("outNo in", values, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoNotIn(List<String> values) {
            addCriterion("outNo not in", values, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoBetween(String value1, String value2) {
            addCriterion("outNo between", value1, value2, "outno");
            return (Criteria) this;
        }

        public Criteria andOutnoNotBetween(String value1, String value2) {
            addCriterion("outNo not between", value1, value2, "outno");
            return (Criteria) this;
        }

        public Criteria andIsprintIsNull() {
            addCriterion("isPrint is null");
            return (Criteria) this;
        }

        public Criteria andIsprintIsNotNull() {
            addCriterion("isPrint is not null");
            return (Criteria) this;
        }

        public Criteria andIsprintEqualTo(String value) {
            addCriterion("isPrint =", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintNotEqualTo(String value) {
            addCriterion("isPrint <>", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintGreaterThan(String value) {
            addCriterion("isPrint >", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintGreaterThanOrEqualTo(String value) {
            addCriterion("isPrint >=", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintLessThan(String value) {
            addCriterion("isPrint <", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintLessThanOrEqualTo(String value) {
            addCriterion("isPrint <=", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintLike(String value) {
            addCriterion("isPrint like", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintNotLike(String value) {
            addCriterion("isPrint not like", value, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintIn(List<String> values) {
            addCriterion("isPrint in", values, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintNotIn(List<String> values) {
            addCriterion("isPrint not in", values, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintBetween(String value1, String value2) {
            addCriterion("isPrint between", value1, value2, "isprint");
            return (Criteria) this;
        }

        public Criteria andIsprintNotBetween(String value1, String value2) {
            addCriterion("isPrint not between", value1, value2, "isprint");
            return (Criteria) this;
        }

        public Criteria andPresentstatusIsNull() {
            addCriterion("presentStatus is null");
            return (Criteria) this;
        }

        public Criteria andPresentstatusIsNotNull() {
            addCriterion("presentStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPresentstatusEqualTo(String value) {
            addCriterion("presentStatus =", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusNotEqualTo(String value) {
            addCriterion("presentStatus <>", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusGreaterThan(String value) {
            addCriterion("presentStatus >", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusGreaterThanOrEqualTo(String value) {
            addCriterion("presentStatus >=", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusLessThan(String value) {
            addCriterion("presentStatus <", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusLessThanOrEqualTo(String value) {
            addCriterion("presentStatus <=", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusLike(String value) {
            addCriterion("presentStatus like", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusNotLike(String value) {
            addCriterion("presentStatus not like", value, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusIn(List<String> values) {
            addCriterion("presentStatus in", values, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusNotIn(List<String> values) {
            addCriterion("presentStatus not in", values, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusBetween(String value1, String value2) {
            addCriterion("presentStatus between", value1, value2, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andPresentstatusNotBetween(String value1, String value2) {
            addCriterion("presentStatus not between", value1, value2, "presentstatus");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNull() {
            addCriterion("orderType is null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNotNull() {
            addCriterion("orderType is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeEqualTo(String value) {
            addCriterion("orderType =", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotEqualTo(String value) {
            addCriterion("orderType <>", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThan(String value) {
            addCriterion("orderType >", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThanOrEqualTo(String value) {
            addCriterion("orderType >=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThan(String value) {
            addCriterion("orderType <", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThanOrEqualTo(String value) {
            addCriterion("orderType <=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLike(String value) {
            addCriterion("orderType like", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotLike(String value) {
            addCriterion("orderType not like", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIn(List<String> values) {
            addCriterion("orderType in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotIn(List<String> values) {
            addCriterion("orderType not in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeBetween(String value1, String value2) {
            addCriterion("orderType between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotBetween(String value1, String value2) {
            addCriterion("orderType not between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNull() {
            addCriterion("memberName is null");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNotNull() {
            addCriterion("memberName is not null");
            return (Criteria) this;
        }

        public Criteria andMembernameEqualTo(String value) {
            addCriterion("memberName =", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotEqualTo(String value) {
            addCriterion("memberName <>", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThan(String value) {
            addCriterion("memberName >", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThanOrEqualTo(String value) {
            addCriterion("memberName >=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThan(String value) {
            addCriterion("memberName <", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThanOrEqualTo(String value) {
            addCriterion("memberName <=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLike(String value) {
            addCriterion("memberName like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotLike(String value) {
            addCriterion("memberName not like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameIn(List<String> values) {
            addCriterion("memberName in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotIn(List<String> values) {
            addCriterion("memberName not in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameBetween(String value1, String value2) {
            addCriterion("memberName between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotBetween(String value1, String value2) {
            addCriterion("memberName not between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andIncomestatusIsNull() {
            addCriterion("incomeStatus is null");
            return (Criteria) this;
        }

        public Criteria andIncomestatusIsNotNull() {
            addCriterion("incomeStatus is not null");
            return (Criteria) this;
        }

        public Criteria andIncomestatusEqualTo(Integer value) {
            addCriterion("incomeStatus =", value, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusNotEqualTo(Integer value) {
            addCriterion("incomeStatus <>", value, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusGreaterThan(Integer value) {
            addCriterion("incomeStatus >", value, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("incomeStatus >=", value, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusLessThan(Integer value) {
            addCriterion("incomeStatus <", value, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusLessThanOrEqualTo(Integer value) {
            addCriterion("incomeStatus <=", value, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusIn(List<Integer> values) {
            addCriterion("incomeStatus in", values, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusNotIn(List<Integer> values) {
            addCriterion("incomeStatus not in", values, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusBetween(Integer value1, Integer value2) {
            addCriterion("incomeStatus between", value1, value2, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andIncomestatusNotBetween(Integer value1, Integer value2) {
            addCriterion("incomeStatus not between", value1, value2, "incomestatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusIsNull() {
            addCriterion("returnStatus is null");
            return (Criteria) this;
        }

        public Criteria andReturnstatusIsNotNull() {
            addCriterion("returnStatus is not null");
            return (Criteria) this;
        }

        public Criteria andReturnstatusEqualTo(String value) {
            addCriterion("returnStatus =", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusNotEqualTo(String value) {
            addCriterion("returnStatus <>", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusGreaterThan(String value) {
            addCriterion("returnStatus >", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusGreaterThanOrEqualTo(String value) {
            addCriterion("returnStatus >=", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusLessThan(String value) {
            addCriterion("returnStatus <", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusLessThanOrEqualTo(String value) {
            addCriterion("returnStatus <=", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusLike(String value) {
            addCriterion("returnStatus like", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusNotLike(String value) {
            addCriterion("returnStatus not like", value, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusIn(List<String> values) {
            addCriterion("returnStatus in", values, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusNotIn(List<String> values) {
            addCriterion("returnStatus not in", values, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusBetween(String value1, String value2) {
            addCriterion("returnStatus between", value1, value2, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andReturnstatusNotBetween(String value1, String value2) {
            addCriterion("returnStatus not between", value1, value2, "returnstatus");
            return (Criteria) this;
        }

        public Criteria andCardidIsNull() {
            addCriterion("cardId is null");
            return (Criteria) this;
        }

        public Criteria andCardidIsNotNull() {
            addCriterion("cardId is not null");
            return (Criteria) this;
        }

        public Criteria andCardidEqualTo(Integer value) {
            addCriterion("cardId =", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotEqualTo(Integer value) {
            addCriterion("cardId <>", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidGreaterThan(Integer value) {
            addCriterion("cardId >", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardId >=", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLessThan(Integer value) {
            addCriterion("cardId <", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLessThanOrEqualTo(Integer value) {
            addCriterion("cardId <=", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidIn(List<Integer> values) {
            addCriterion("cardId in", values, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotIn(List<Integer> values) {
            addCriterion("cardId not in", values, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidBetween(Integer value1, Integer value2) {
            addCriterion("cardId between", value1, value2, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotBetween(Integer value1, Integer value2) {
            addCriterion("cardId not between", value1, value2, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardcodeIsNull() {
            addCriterion("cardCode is null");
            return (Criteria) this;
        }

        public Criteria andCardcodeIsNotNull() {
            addCriterion("cardCode is not null");
            return (Criteria) this;
        }

        public Criteria andCardcodeEqualTo(String value) {
            addCriterion("cardCode =", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeNotEqualTo(String value) {
            addCriterion("cardCode <>", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeGreaterThan(String value) {
            addCriterion("cardCode >", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeGreaterThanOrEqualTo(String value) {
            addCriterion("cardCode >=", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeLessThan(String value) {
            addCriterion("cardCode <", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeLessThanOrEqualTo(String value) {
            addCriterion("cardCode <=", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeLike(String value) {
            addCriterion("cardCode like", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeNotLike(String value) {
            addCriterion("cardCode not like", value, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeIn(List<String> values) {
            addCriterion("cardCode in", values, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeNotIn(List<String> values) {
            addCriterion("cardCode not in", values, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeBetween(String value1, String value2) {
            addCriterion("cardCode between", value1, value2, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardcodeNotBetween(String value1, String value2) {
            addCriterion("cardCode not between", value1, value2, "cardcode");
            return (Criteria) this;
        }

        public Criteria andCardtypenameIsNull() {
            addCriterion("cardTypeName is null");
            return (Criteria) this;
        }

        public Criteria andCardtypenameIsNotNull() {
            addCriterion("cardTypeName is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypenameEqualTo(String value) {
            addCriterion("cardTypeName =", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotEqualTo(String value) {
            addCriterion("cardTypeName <>", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameGreaterThan(String value) {
            addCriterion("cardTypeName >", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameGreaterThanOrEqualTo(String value) {
            addCriterion("cardTypeName >=", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameLessThan(String value) {
            addCriterion("cardTypeName <", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameLessThanOrEqualTo(String value) {
            addCriterion("cardTypeName <=", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameLike(String value) {
            addCriterion("cardTypeName like", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotLike(String value) {
            addCriterion("cardTypeName not like", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameIn(List<String> values) {
            addCriterion("cardTypeName in", values, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotIn(List<String> values) {
            addCriterion("cardTypeName not in", values, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameBetween(String value1, String value2) {
            addCriterion("cardTypeName between", value1, value2, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotBetween(String value1, String value2) {
            addCriterion("cardTypeName not between", value1, value2, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andFeereduceIsNull() {
            addCriterion("feeReduce is null");
            return (Criteria) this;
        }

        public Criteria andFeereduceIsNotNull() {
            addCriterion("feeReduce is not null");
            return (Criteria) this;
        }

        public Criteria andFeereduceEqualTo(Integer value) {
            addCriterion("feeReduce =", value, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceNotEqualTo(Integer value) {
            addCriterion("feeReduce <>", value, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceGreaterThan(Integer value) {
            addCriterion("feeReduce >", value, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceGreaterThanOrEqualTo(Integer value) {
            addCriterion("feeReduce >=", value, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceLessThan(Integer value) {
            addCriterion("feeReduce <", value, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceLessThanOrEqualTo(Integer value) {
            addCriterion("feeReduce <=", value, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceIn(List<Integer> values) {
            addCriterion("feeReduce in", values, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceNotIn(List<Integer> values) {
            addCriterion("feeReduce not in", values, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceBetween(Integer value1, Integer value2) {
            addCriterion("feeReduce between", value1, value2, "feereduce");
            return (Criteria) this;
        }

        public Criteria andFeereduceNotBetween(Integer value1, Integer value2) {
            addCriterion("feeReduce not between", value1, value2, "feereduce");
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

        public Criteria andDeliverynameIsNull() {
            addCriterion("deliveryName is null");
            return (Criteria) this;
        }

        public Criteria andDeliverynameIsNotNull() {
            addCriterion("deliveryName is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverynameEqualTo(String value) {
            addCriterion("deliveryName =", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameNotEqualTo(String value) {
            addCriterion("deliveryName <>", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameGreaterThan(String value) {
            addCriterion("deliveryName >", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryName >=", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameLessThan(String value) {
            addCriterion("deliveryName <", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameLessThanOrEqualTo(String value) {
            addCriterion("deliveryName <=", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameLike(String value) {
            addCriterion("deliveryName like", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameNotLike(String value) {
            addCriterion("deliveryName not like", value, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameIn(List<String> values) {
            addCriterion("deliveryName in", values, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameNotIn(List<String> values) {
            addCriterion("deliveryName not in", values, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameBetween(String value1, String value2) {
            addCriterion("deliveryName between", value1, value2, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliverynameNotBetween(String value1, String value2) {
            addCriterion("deliveryName not between", value1, value2, "deliveryname");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoIsNull() {
            addCriterion("deliveryOrderNo is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoIsNotNull() {
            addCriterion("deliveryOrderNo is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoEqualTo(String value) {
            addCriterion("deliveryOrderNo =", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoNotEqualTo(String value) {
            addCriterion("deliveryOrderNo <>", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoGreaterThan(String value) {
            addCriterion("deliveryOrderNo >", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryOrderNo >=", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoLessThan(String value) {
            addCriterion("deliveryOrderNo <", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoLessThanOrEqualTo(String value) {
            addCriterion("deliveryOrderNo <=", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoLike(String value) {
            addCriterion("deliveryOrderNo like", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoNotLike(String value) {
            addCriterion("deliveryOrderNo not like", value, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoIn(List<String> values) {
            addCriterion("deliveryOrderNo in", values, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoNotIn(List<String> values) {
            addCriterion("deliveryOrderNo not in", values, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoBetween(String value1, String value2) {
            addCriterion("deliveryOrderNo between", value1, value2, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andDeliveryordernoNotBetween(String value1, String value2) {
            addCriterion("deliveryOrderNo not between", value1, value2, "deliveryorderno");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andRegsonIsNull() {
            addCriterion("regson is null");
            return (Criteria) this;
        }

        public Criteria andRegsonIsNotNull() {
            addCriterion("regson is not null");
            return (Criteria) this;
        }

        public Criteria andRegsonEqualTo(String value) {
            addCriterion("regson =", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotEqualTo(String value) {
            addCriterion("regson <>", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonGreaterThan(String value) {
            addCriterion("regson >", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonGreaterThanOrEqualTo(String value) {
            addCriterion("regson >=", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonLessThan(String value) {
            addCriterion("regson <", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonLessThanOrEqualTo(String value) {
            addCriterion("regson <=", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonLike(String value) {
            addCriterion("regson like", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotLike(String value) {
            addCriterion("regson not like", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonIn(List<String> values) {
            addCriterion("regson in", values, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotIn(List<String> values) {
            addCriterion("regson not in", values, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonBetween(String value1, String value2) {
            addCriterion("regson between", value1, value2, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotBetween(String value1, String value2) {
            addCriterion("regson not between", value1, value2, "regson");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIsNull() {
            addCriterion("receiverPhone is null");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIsNotNull() {
            addCriterion("receiverPhone is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneEqualTo(String value) {
            addCriterion("receiverPhone =", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotEqualTo(String value) {
            addCriterion("receiverPhone <>", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneGreaterThan(String value) {
            addCriterion("receiverPhone >", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneGreaterThanOrEqualTo(String value) {
            addCriterion("receiverPhone >=", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLessThan(String value) {
            addCriterion("receiverPhone <", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLessThanOrEqualTo(String value) {
            addCriterion("receiverPhone <=", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLike(String value) {
            addCriterion("receiverPhone like", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotLike(String value) {
            addCriterion("receiverPhone not like", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIn(List<String> values) {
            addCriterion("receiverPhone in", values, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotIn(List<String> values) {
            addCriterion("receiverPhone not in", values, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneBetween(String value1, String value2) {
            addCriterion("receiverPhone between", value1, value2, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotBetween(String value1, String value2) {
            addCriterion("receiverPhone not between", value1, value2, "receiverphone");
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

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("ext3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andIssendtipIsNull() {
            addCriterion("isSendTip is null");
            return (Criteria) this;
        }

        public Criteria andIssendtipIsNotNull() {
            addCriterion("isSendTip is not null");
            return (Criteria) this;
        }

        public Criteria andIssendtipEqualTo(String value) {
            addCriterion("isSendTip =", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipNotEqualTo(String value) {
            addCriterion("isSendTip <>", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipGreaterThan(String value) {
            addCriterion("isSendTip >", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipGreaterThanOrEqualTo(String value) {
            addCriterion("isSendTip >=", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipLessThan(String value) {
            addCriterion("isSendTip <", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipLessThanOrEqualTo(String value) {
            addCriterion("isSendTip <=", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipLike(String value) {
            addCriterion("isSendTip like", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipNotLike(String value) {
            addCriterion("isSendTip not like", value, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipIn(List<String> values) {
            addCriterion("isSendTip in", values, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipNotIn(List<String> values) {
            addCriterion("isSendTip not in", values, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipBetween(String value1, String value2) {
            addCriterion("isSendTip between", value1, value2, "issendtip");
            return (Criteria) this;
        }

        public Criteria andIssendtipNotBetween(String value1, String value2) {
            addCriterion("isSendTip not between", value1, value2, "issendtip");
            return (Criteria) this;
        }

        public Criteria andMalltypeIsNull() {
            addCriterion("mallType is null");
            return (Criteria) this;
        }

        public Criteria andMalltypeIsNotNull() {
            addCriterion("mallType is not null");
            return (Criteria) this;
        }
        
        public Criteria andMalltypeEqualTo(String value) {
            addCriterion("mallType =", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeNotEqualTo(String value) {
            addCriterion("mallType <>", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeGreaterThan(String value) {
            addCriterion("mallType >", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeGreaterThanOrEqualTo(String value) {
            addCriterion("mallType >=", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeLessThan(String value) {
            addCriterion("mallType <", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeLessThanOrEqualTo(String value) {
            addCriterion("mallType <=", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeLike(String value) {
            addCriterion("mallType like", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeNotLike(String value) {
            addCriterion("mallType not like", value, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeIn(List<String> values) {
            addCriterion("mallType in", values, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeNotIn(List<String> values) {
            addCriterion("mallType not in", values, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeBetween(String value1, String value2) {
            addCriterion("mallType between", value1, value2, "malltype");
            return (Criteria) this;
        }

        public Criteria andMalltypeNotBetween(String value1, String value2) {
            addCriterion("mallType not between", value1, value2, "malltype");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyIsNull() {
            addCriterion("plusMoney is null");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyIsNotNull() {
            addCriterion("plusMoney is not null");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyEqualTo(Integer value) {
            addCriterion("plusMoney =", value, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyNotEqualTo(Integer value) {
            addCriterion("plusMoney <>", value, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyGreaterThan(Integer value) {
            addCriterion("plusMoney >", value, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("plusMoney >=", value, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyLessThan(Integer value) {
            addCriterion("plusMoney <", value, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyLessThanOrEqualTo(Integer value) {
            addCriterion("plusMoney <=", value, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyIn(List<Integer> values) {
            addCriterion("plusMoney in", values, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyNotIn(List<Integer> values) {
            addCriterion("plusMoney not in", values, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyBetween(Integer value1, Integer value2) {
            addCriterion("plusMoney between", value1, value2, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPlusmoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("plusMoney not between", value1, value2, "plusmoney");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceIsNull() {
            addCriterion("preferentialPrice is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceIsNotNull() {
            addCriterion("preferentialPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceEqualTo(Integer value) {
            addCriterion("preferentialPrice =", value, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceNotEqualTo(Integer value) {
            addCriterion("preferentialPrice <>", value, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceGreaterThan(Integer value) {
            addCriterion("preferentialPrice >", value, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("preferentialPrice >=", value, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceLessThan(Integer value) {
            addCriterion("preferentialPrice <", value, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceLessThanOrEqualTo(Integer value) {
            addCriterion("preferentialPrice <=", value, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceIn(List<Integer> values) {
            addCriterion("preferentialPrice in", values, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceNotIn(List<Integer> values) {
            addCriterion("preferentialPrice not in", values, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceBetween(Integer value1, Integer value2) {
            addCriterion("preferentialPrice between", value1, value2, "preferentialprice");
            return (Criteria) this;
        }

        public Criteria andPreferentialpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("preferentialPrice not between", value1, value2, "preferentialprice");
            return (Criteria) this;
        }
        
        public Criteria andIsPay(List<String> values) {
            addCriterion("payStatus in", values, "payStatus");
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