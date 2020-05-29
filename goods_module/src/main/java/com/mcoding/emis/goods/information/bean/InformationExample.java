package com.mcoding.emis.goods.information.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InformationExample() {
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

        public Criteria andSenderIsNull() {
            addCriterion("sender is null");
            return (Criteria) this;
        }

        public Criteria andSenderIsNotNull() {
            addCriterion("sender is not null");
            return (Criteria) this;
        }

        public Criteria andSenderEqualTo(String value) {
            addCriterion("sender =", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotEqualTo(String value) {
            addCriterion("sender <>", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThan(String value) {
            addCriterion("sender >", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThanOrEqualTo(String value) {
            addCriterion("sender >=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThan(String value) {
            addCriterion("sender <", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThanOrEqualTo(String value) {
            addCriterion("sender <=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLike(String value) {
            addCriterion("sender like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotLike(String value) {
            addCriterion("sender not like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderIn(List<String> values) {
            addCriterion("sender in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotIn(List<String> values) {
            addCriterion("sender not in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderBetween(String value1, String value2) {
            addCriterion("sender between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotBetween(String value1, String value2) {
            addCriterion("sender not between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidIsNull() {
            addCriterion("senderMemeberId is null");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidIsNotNull() {
            addCriterion("senderMemeberId is not null");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidEqualTo(Integer value) {
            addCriterion("senderMemeberId =", value, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidNotEqualTo(Integer value) {
            addCriterion("senderMemeberId <>", value, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidGreaterThan(Integer value) {
            addCriterion("senderMemeberId >", value, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidGreaterThanOrEqualTo(Integer value) {
            addCriterion("senderMemeberId >=", value, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidLessThan(Integer value) {
            addCriterion("senderMemeberId <", value, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidLessThanOrEqualTo(Integer value) {
            addCriterion("senderMemeberId <=", value, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidIn(List<Integer> values) {
            addCriterion("senderMemeberId in", values, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidNotIn(List<Integer> values) {
            addCriterion("senderMemeberId not in", values, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidBetween(Integer value1, Integer value2) {
            addCriterion("senderMemeberId between", value1, value2, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andSendermemeberidNotBetween(Integer value1, Integer value2) {
            addCriterion("senderMemeberId not between", value1, value2, "sendermemeberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidIsNull() {
            addCriterion("receiverMemberId is null");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidIsNotNull() {
            addCriterion("receiverMemberId is not null");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidEqualTo(Integer value) {
            addCriterion("receiverMemberId =", value, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidNotEqualTo(Integer value) {
            addCriterion("receiverMemberId <>", value, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidGreaterThan(Integer value) {
            addCriterion("receiverMemberId >", value, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiverMemberId >=", value, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidLessThan(Integer value) {
            addCriterion("receiverMemberId <", value, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidLessThanOrEqualTo(Integer value) {
            addCriterion("receiverMemberId <=", value, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidIn(List<Integer> values) {
            addCriterion("receiverMemberId in", values, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidNotIn(List<Integer> values) {
            addCriterion("receiverMemberId not in", values, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidBetween(Integer value1, Integer value2) {
            addCriterion("receiverMemberId between", value1, value2, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceivermemberidNotBetween(Integer value1, Integer value2) {
            addCriterion("receiverMemberId not between", value1, value2, "receivermemberid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidIsNull() {
            addCriterion("receiverOpenId is null");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidIsNotNull() {
            addCriterion("receiverOpenId is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidEqualTo(String value) {
            addCriterion("receiverOpenId =", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidNotEqualTo(String value) {
            addCriterion("receiverOpenId <>", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidGreaterThan(String value) {
            addCriterion("receiverOpenId >", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidGreaterThanOrEqualTo(String value) {
            addCriterion("receiverOpenId >=", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidLessThan(String value) {
            addCriterion("receiverOpenId <", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidLessThanOrEqualTo(String value) {
            addCriterion("receiverOpenId <=", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidLike(String value) {
            addCriterion("receiverOpenId like", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidNotLike(String value) {
            addCriterion("receiverOpenId not like", value, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidIn(List<String> values) {
            addCriterion("receiverOpenId in", values, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidNotIn(List<String> values) {
            addCriterion("receiverOpenId not in", values, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidBetween(String value1, String value2) {
            addCriterion("receiverOpenId between", value1, value2, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andReceiveropenidNotBetween(String value1, String value2) {
            addCriterion("receiverOpenId not between", value1, value2, "receiveropenid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andReadtimeIsNull() {
            addCriterion("readTime is null");
            return (Criteria) this;
        }

        public Criteria andReadtimeIsNotNull() {
            addCriterion("readTime is not null");
            return (Criteria) this;
        }

        public Criteria andReadtimeEqualTo(Date value) {
            addCriterion("readTime =", value, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeNotEqualTo(Date value) {
            addCriterion("readTime <>", value, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeGreaterThan(Date value) {
            addCriterion("readTime >", value, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("readTime >=", value, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeLessThan(Date value) {
            addCriterion("readTime <", value, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeLessThanOrEqualTo(Date value) {
            addCriterion("readTime <=", value, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeIn(List<Date> values) {
            addCriterion("readTime in", values, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeNotIn(List<Date> values) {
            addCriterion("readTime not in", values, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeBetween(Date value1, Date value2) {
            addCriterion("readTime between", value1, value2, "readtime");
            return (Criteria) this;
        }

        public Criteria andReadtimeNotBetween(Date value1, Date value2) {
            addCriterion("readTime not between", value1, value2, "readtime");
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