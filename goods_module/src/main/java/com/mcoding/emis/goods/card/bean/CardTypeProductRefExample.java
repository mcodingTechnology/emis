package com.mcoding.emis.goods.card.bean;

import java.util.ArrayList;
import java.util.List;

public class CardTypeProductRefExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardTypeProductRefExample() {
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

        public Criteria andRefidIsNull() {
            addCriterion("refId is null");
            return (Criteria) this;
        }

        public Criteria andRefidIsNotNull() {
            addCriterion("refId is not null");
            return (Criteria) this;
        }

        public Criteria andRefidEqualTo(Integer value) {
            addCriterion("refId =", value, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidNotEqualTo(Integer value) {
            addCriterion("refId <>", value, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidGreaterThan(Integer value) {
            addCriterion("refId >", value, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidGreaterThanOrEqualTo(Integer value) {
            addCriterion("refId >=", value, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidLessThan(Integer value) {
            addCriterion("refId <", value, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidLessThanOrEqualTo(Integer value) {
            addCriterion("refId <=", value, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidIn(List<Integer> values) {
            addCriterion("refId in", values, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidNotIn(List<Integer> values) {
            addCriterion("refId not in", values, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidBetween(Integer value1, Integer value2) {
            addCriterion("refId between", value1, value2, "refid");
            return (Criteria) this;
        }

        public Criteria andRefidNotBetween(Integer value1, Integer value2) {
            addCriterion("refId not between", value1, value2, "refid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidIsNull() {
            addCriterion("cardTypeId is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeidIsNotNull() {
            addCriterion("cardTypeId is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeidEqualTo(Integer value) {
            addCriterion("cardTypeId =", value, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidNotEqualTo(Integer value) {
            addCriterion("cardTypeId <>", value, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidGreaterThan(Integer value) {
            addCriterion("cardTypeId >", value, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardTypeId >=", value, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidLessThan(Integer value) {
            addCriterion("cardTypeId <", value, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidLessThanOrEqualTo(Integer value) {
            addCriterion("cardTypeId <=", value, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidIn(List<Integer> values) {
            addCriterion("cardTypeId in", values, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidNotIn(List<Integer> values) {
            addCriterion("cardTypeId not in", values, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidBetween(Integer value1, Integer value2) {
            addCriterion("cardTypeId between", value1, value2, "cardtypeid");
            return (Criteria) this;
        }

        public Criteria andCardtypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("cardTypeId not between", value1, value2, "cardtypeid");
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

        public Criteria andProductcountIsNull() {
            addCriterion("productCount is null");
            return (Criteria) this;
        }

        public Criteria andProductcountIsNotNull() {
            addCriterion("productCount is not null");
            return (Criteria) this;
        }

        public Criteria andProductcountEqualTo(Integer value) {
            addCriterion("productCount =", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountNotEqualTo(Integer value) {
            addCriterion("productCount <>", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountGreaterThan(Integer value) {
            addCriterion("productCount >", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("productCount >=", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountLessThan(Integer value) {
            addCriterion("productCount <", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountLessThanOrEqualTo(Integer value) {
            addCriterion("productCount <=", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountIn(List<Integer> values) {
            addCriterion("productCount in", values, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountNotIn(List<Integer> values) {
            addCriterion("productCount not in", values, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountBetween(Integer value1, Integer value2) {
            addCriterion("productCount between", value1, value2, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountNotBetween(Integer value1, Integer value2) {
            addCriterion("productCount not between", value1, value2, "productcount");
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