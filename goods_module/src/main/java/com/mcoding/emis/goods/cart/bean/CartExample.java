package com.mcoding.emis.goods.cart.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CartExample() {
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

        public Criteria andCartidIsNull() {
            addCriterion("cartId is null");
            return (Criteria) this;
        }

        public Criteria andCartidIsNotNull() {
            addCriterion("cartId is not null");
            return (Criteria) this;
        }

        public Criteria andCartidEqualTo(Integer value) {
            addCriterion("cartId =", value, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidNotEqualTo(Integer value) {
            addCriterion("cartId <>", value, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidGreaterThan(Integer value) {
            addCriterion("cartId >", value, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cartId >=", value, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidLessThan(Integer value) {
            addCriterion("cartId <", value, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidLessThanOrEqualTo(Integer value) {
            addCriterion("cartId <=", value, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidIn(List<Integer> values) {
            addCriterion("cartId in", values, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidNotIn(List<Integer> values) {
            addCriterion("cartId not in", values, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidBetween(Integer value1, Integer value2) {
            addCriterion("cartId between", value1, value2, "cartid");
            return (Criteria) this;
        }

        public Criteria andCartidNotBetween(Integer value1, Integer value2) {
            addCriterion("cartId not between", value1, value2, "cartid");
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

        public Criteria andProductidIsNull() {
            addCriterion("productId is null");
            return (Criteria) this;
        }

        public Criteria andProductidIsNotNull() {
            addCriterion("productId is not null");
            return (Criteria) this;
        }

        public Criteria andProductidEqualTo(Integer value) {
            addCriterion("productId =", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotEqualTo(Integer value) {
            addCriterion("productId <>", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThan(Integer value) {
            addCriterion("productId >", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThanOrEqualTo(Integer value) {
            addCriterion("productId >=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThan(Integer value) {
            addCriterion("productId <", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThanOrEqualTo(Integer value) {
            addCriterion("productId <=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidIn(List<Integer> values) {
            addCriterion("productId in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotIn(List<Integer> values) {
            addCriterion("productId not in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidBetween(Integer value1, Integer value2) {
            addCriterion("productId between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotBetween(Integer value1, Integer value2) {
            addCriterion("productId not between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNull() {
            addCriterion("productName is null");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNotNull() {
            addCriterion("productName is not null");
            return (Criteria) this;
        }

        public Criteria andProductnameEqualTo(String value) {
            addCriterion("productName =", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotEqualTo(String value) {
            addCriterion("productName <>", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThan(String value) {
            addCriterion("productName >", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThanOrEqualTo(String value) {
            addCriterion("productName >=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThan(String value) {
            addCriterion("productName <", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThanOrEqualTo(String value) {
            addCriterion("productName <=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLike(String value) {
            addCriterion("productName like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotLike(String value) {
            addCriterion("productName not like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameIn(List<String> values) {
            addCriterion("productName in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotIn(List<String> values) {
            addCriterion("productName not in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameBetween(String value1, String value2) {
            addCriterion("productName between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotBetween(String value1, String value2) {
            addCriterion("productName not between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductintroduceIsNull() {
            addCriterion("productIntroduce is null");
            return (Criteria) this;
        }

        public Criteria andProductintroduceIsNotNull() {
            addCriterion("productIntroduce is not null");
            return (Criteria) this;
        }

        public Criteria andProductintroduceEqualTo(String value) {
            addCriterion("productIntroduce =", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceNotEqualTo(String value) {
            addCriterion("productIntroduce <>", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceGreaterThan(String value) {
            addCriterion("productIntroduce >", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceGreaterThanOrEqualTo(String value) {
            addCriterion("productIntroduce >=", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceLessThan(String value) {
            addCriterion("productIntroduce <", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceLessThanOrEqualTo(String value) {
            addCriterion("productIntroduce <=", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceLike(String value) {
            addCriterion("productIntroduce like", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceNotLike(String value) {
            addCriterion("productIntroduce not like", value, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceIn(List<String> values) {
            addCriterion("productIntroduce in", values, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceNotIn(List<String> values) {
            addCriterion("productIntroduce not in", values, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceBetween(String value1, String value2) {
            addCriterion("productIntroduce between", value1, value2, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductintroduceNotBetween(String value1, String value2) {
            addCriterion("productIntroduce not between", value1, value2, "productintroduce");
            return (Criteria) this;
        }

        public Criteria andProductnumIsNull() {
            addCriterion("productNum is null");
            return (Criteria) this;
        }

        public Criteria andProductnumIsNotNull() {
            addCriterion("productNum is not null");
            return (Criteria) this;
        }

        public Criteria andProductnumEqualTo(Integer value) {
            addCriterion("productNum =", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotEqualTo(Integer value) {
            addCriterion("productNum <>", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumGreaterThan(Integer value) {
            addCriterion("productNum >", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("productNum >=", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumLessThan(Integer value) {
            addCriterion("productNum <", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumLessThanOrEqualTo(Integer value) {
            addCriterion("productNum <=", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumIn(List<Integer> values) {
            addCriterion("productNum in", values, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotIn(List<Integer> values) {
            addCriterion("productNum not in", values, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumBetween(Integer value1, Integer value2) {
            addCriterion("productNum between", value1, value2, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotBetween(Integer value1, Integer value2) {
            addCriterion("productNum not between", value1, value2, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductpriceIsNull() {
            addCriterion("productPrice is null");
            return (Criteria) this;
        }

        public Criteria andProductpriceIsNotNull() {
            addCriterion("productPrice is not null");
            return (Criteria) this;
        }

        public Criteria andProductpriceEqualTo(Integer value) {
            addCriterion("productPrice =", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceNotEqualTo(Integer value) {
            addCriterion("productPrice <>", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceGreaterThan(Integer value) {
            addCriterion("productPrice >", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("productPrice >=", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceLessThan(Integer value) {
            addCriterion("productPrice <", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceLessThanOrEqualTo(Integer value) {
            addCriterion("productPrice <=", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceIn(List<Integer> values) {
            addCriterion("productPrice in", values, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceNotIn(List<Integer> values) {
            addCriterion("productPrice not in", values, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceBetween(Integer value1, Integer value2) {
            addCriterion("productPrice between", value1, value2, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("productPrice not between", value1, value2, "productprice");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgIsNull() {
            addCriterion("productCoverImg is null");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgIsNotNull() {
            addCriterion("productCoverImg is not null");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgEqualTo(String value) {
            addCriterion("productCoverImg =", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotEqualTo(String value) {
            addCriterion("productCoverImg <>", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgGreaterThan(String value) {
            addCriterion("productCoverImg >", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgGreaterThanOrEqualTo(String value) {
            addCriterion("productCoverImg >=", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgLessThan(String value) {
            addCriterion("productCoverImg <", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgLessThanOrEqualTo(String value) {
            addCriterion("productCoverImg <=", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgLike(String value) {
            addCriterion("productCoverImg like", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotLike(String value) {
            addCriterion("productCoverImg not like", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgIn(List<String> values) {
            addCriterion("productCoverImg in", values, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotIn(List<String> values) {
            addCriterion("productCoverImg not in", values, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgBetween(String value1, String value2) {
            addCriterion("productCoverImg between", value1, value2, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotBetween(String value1, String value2) {
            addCriterion("productCoverImg not between", value1, value2, "productcoverimg");
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

        public Criteria andGiftbuytypeIsNull() {
            addCriterion("giftBuyType is null");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeIsNotNull() {
            addCriterion("giftBuyType is not null");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeEqualTo(Integer value) {
            addCriterion("giftBuyType =", value, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeNotEqualTo(Integer value) {
            addCriterion("giftBuyType <>", value, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeGreaterThan(Integer value) {
            addCriterion("giftBuyType >", value, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("giftBuyType >=", value, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeLessThan(Integer value) {
            addCriterion("giftBuyType <", value, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeLessThanOrEqualTo(Integer value) {
            addCriterion("giftBuyType <=", value, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeIn(List<Integer> values) {
            addCriterion("giftBuyType in", values, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeNotIn(List<Integer> values) {
            addCriterion("giftBuyType not in", values, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeBetween(Integer value1, Integer value2) {
            addCriterion("giftBuyType between", value1, value2, "giftbuytype");
            return (Criteria) this;
        }

        public Criteria andGiftbuytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("giftBuyType not between", value1, value2, "giftbuytype");
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