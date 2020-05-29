package com.mcoding.emis.goods.dealer.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mcoding.base.core.PageView;

public class DealerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<Dealer> pageView;
    
    public PageView<Dealer> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<Dealer> pageView) {
		this.pageView = pageView;
	}

	public DealerExample() {
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

        public Criteria andDealernameIsNull() {
            addCriterion("dealerName is null");
            return (Criteria) this;
        }

        public Criteria andDealernameIsNotNull() {
            addCriterion("dealerName is not null");
            return (Criteria) this;
        }

        public Criteria andDealernameEqualTo(String value) {
            addCriterion("dealerName =", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameNotEqualTo(String value) {
            addCriterion("dealerName <>", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameGreaterThan(String value) {
            addCriterion("dealerName >", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameGreaterThanOrEqualTo(String value) {
            addCriterion("dealerName >=", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameLessThan(String value) {
            addCriterion("dealerName <", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameLessThanOrEqualTo(String value) {
            addCriterion("dealerName <=", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameLike(String value) {
            addCriterion("dealerName like", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameNotLike(String value) {
            addCriterion("dealerName not like", value, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameIn(List<String> values) {
            addCriterion("dealerName in", values, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameNotIn(List<String> values) {
            addCriterion("dealerName not in", values, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameBetween(String value1, String value2) {
            addCriterion("dealerName between", value1, value2, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernameNotBetween(String value1, String value2) {
            addCriterion("dealerName not between", value1, value2, "dealername");
            return (Criteria) this;
        }

        public Criteria andDealernoIsNull() {
            addCriterion("dealerNo is null");
            return (Criteria) this;
        }

        public Criteria andDealernoIsNotNull() {
            addCriterion("dealerNo is not null");
            return (Criteria) this;
        }

        public Criteria andDealernoEqualTo(String value) {
            addCriterion("dealerNo =", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoNotEqualTo(String value) {
            addCriterion("dealerNo <>", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoGreaterThan(String value) {
            addCriterion("dealerNo >", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoGreaterThanOrEqualTo(String value) {
            addCriterion("dealerNo >=", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoLessThan(String value) {
            addCriterion("dealerNo <", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoLessThanOrEqualTo(String value) {
            addCriterion("dealerNo <=", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoLike(String value) {
            addCriterion("dealerNo like", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoNotLike(String value) {
            addCriterion("dealerNo not like", value, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoIn(List<String> values) {
            addCriterion("dealerNo in", values, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoNotIn(List<String> values) {
            addCriterion("dealerNo not in", values, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoBetween(String value1, String value2) {
            addCriterion("dealerNo between", value1, value2, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealernoNotBetween(String value1, String value2) {
            addCriterion("dealerNo not between", value1, value2, "dealerno");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameIsNull() {
            addCriterion("dealerStoreName is null");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameIsNotNull() {
            addCriterion("dealerStoreName is not null");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameEqualTo(String value) {
            addCriterion("dealerStoreName =", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameNotEqualTo(String value) {
            addCriterion("dealerStoreName <>", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameGreaterThan(String value) {
            addCriterion("dealerStoreName >", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameGreaterThanOrEqualTo(String value) {
            addCriterion("dealerStoreName >=", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameLessThan(String value) {
            addCriterion("dealerStoreName <", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameLessThanOrEqualTo(String value) {
            addCriterion("dealerStoreName <=", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameLike(String value) {
            addCriterion("dealerStoreName like", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameNotLike(String value) {
            addCriterion("dealerStoreName not like", value, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameIn(List<String> values) {
            addCriterion("dealerStoreName in", values, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameNotIn(List<String> values) {
            addCriterion("dealerStoreName not in", values, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameBetween(String value1, String value2) {
            addCriterion("dealerStoreName between", value1, value2, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstorenameNotBetween(String value1, String value2) {
            addCriterion("dealerStoreName not between", value1, value2, "dealerstorename");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlIsNull() {
            addCriterion("dealerStoreUrl is null");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlIsNotNull() {
            addCriterion("dealerStoreUrl is not null");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlEqualTo(String value) {
            addCriterion("dealerStoreUrl =", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlNotEqualTo(String value) {
            addCriterion("dealerStoreUrl <>", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlGreaterThan(String value) {
            addCriterion("dealerStoreUrl >", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlGreaterThanOrEqualTo(String value) {
            addCriterion("dealerStoreUrl >=", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlLessThan(String value) {
            addCriterion("dealerStoreUrl <", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlLessThanOrEqualTo(String value) {
            addCriterion("dealerStoreUrl <=", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlLike(String value) {
            addCriterion("dealerStoreUrl like", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlNotLike(String value) {
            addCriterion("dealerStoreUrl not like", value, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlIn(List<String> values) {
            addCriterion("dealerStoreUrl in", values, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlNotIn(List<String> values) {
            addCriterion("dealerStoreUrl not in", values, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlBetween(String value1, String value2) {
            addCriterion("dealerStoreUrl between", value1, value2, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andDealerstoreurlNotBetween(String value1, String value2) {
            addCriterion("dealerStoreUrl not between", value1, value2, "dealerstoreurl");
            return (Criteria) this;
        }

        public Criteria andBrandcodeIsNull() {
            addCriterion("brandCode is null");
            return (Criteria) this;
        }

        public Criteria andBrandcodeIsNotNull() {
            addCriterion("brandCode is not null");
            return (Criteria) this;
        }

        public Criteria andBrandcodeEqualTo(String value) {
            addCriterion("brandCode =", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeNotEqualTo(String value) {
            addCriterion("brandCode <>", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeGreaterThan(String value) {
            addCriterion("brandCode >", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeGreaterThanOrEqualTo(String value) {
            addCriterion("brandCode >=", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeLessThan(String value) {
            addCriterion("brandCode <", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeLessThanOrEqualTo(String value) {
            addCriterion("brandCode <=", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeLike(String value) {
            addCriterion("brandCode like", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeNotLike(String value) {
            addCriterion("brandCode not like", value, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeIn(List<String> values) {
            addCriterion("brandCode in", values, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeNotIn(List<String> values) {
            addCriterion("brandCode not in", values, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeBetween(String value1, String value2) {
            addCriterion("brandCode between", value1, value2, "brandcode");
            return (Criteria) this;
        }

        public Criteria andBrandcodeNotBetween(String value1, String value2) {
            addCriterion("brandCode not between", value1, value2, "brandcode");
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