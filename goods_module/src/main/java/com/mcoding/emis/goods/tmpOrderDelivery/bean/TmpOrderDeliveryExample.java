package com.mcoding.emis.goods.tmpOrderDelivery.bean;

import java.util.ArrayList;
import java.util.List;

public class TmpOrderDeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TmpOrderDeliveryExample() {
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

        public Criteria andOrdernoIsNull() {
            addCriterion("orderNo is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("orderNo is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("orderNo =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("orderNo <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("orderNo >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("orderNo >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("orderNo <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("orderNo <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("orderNo like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("orderNo not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("orderNo in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("orderNo not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("orderNo between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("orderNo not between", value1, value2, "orderno");
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

        public Criteria andIsupdateIsNull() {
            addCriterion("isUpdate is null");
            return (Criteria) this;
        }

        public Criteria andIsupdateIsNotNull() {
            addCriterion("isUpdate is not null");
            return (Criteria) this;
        }

        public Criteria andIsupdateEqualTo(Integer value) {
            addCriterion("isUpdate =", value, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateNotEqualTo(Integer value) {
            addCriterion("isUpdate <>", value, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateGreaterThan(Integer value) {
            addCriterion("isUpdate >", value, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("isUpdate >=", value, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateLessThan(Integer value) {
            addCriterion("isUpdate <", value, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateLessThanOrEqualTo(Integer value) {
            addCriterion("isUpdate <=", value, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateIn(List<Integer> values) {
            addCriterion("isUpdate in", values, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateNotIn(List<Integer> values) {
            addCriterion("isUpdate not in", values, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateBetween(Integer value1, Integer value2) {
            addCriterion("isUpdate between", value1, value2, "isupdate");
            return (Criteria) this;
        }

        public Criteria andIsupdateNotBetween(Integer value1, Integer value2) {
            addCriterion("isUpdate not between", value1, value2, "isupdate");
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