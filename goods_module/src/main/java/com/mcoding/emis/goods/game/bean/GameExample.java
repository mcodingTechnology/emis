package com.mcoding.emis.goods.game.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andGamenameIsNull() {
            addCriterion("gameName is null");
            return (Criteria) this;
        }

        public Criteria andGamenameIsNotNull() {
            addCriterion("gameName is not null");
            return (Criteria) this;
        }

        public Criteria andGamenameEqualTo(String value) {
            addCriterion("gameName =", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameNotEqualTo(String value) {
            addCriterion("gameName <>", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameGreaterThan(String value) {
            addCriterion("gameName >", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameGreaterThanOrEqualTo(String value) {
            addCriterion("gameName >=", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameLessThan(String value) {
            addCriterion("gameName <", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameLessThanOrEqualTo(String value) {
            addCriterion("gameName <=", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameLike(String value) {
            addCriterion("gameName like", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameNotLike(String value) {
            addCriterion("gameName not like", value, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameIn(List<String> values) {
            addCriterion("gameName in", values, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameNotIn(List<String> values) {
            addCriterion("gameName not in", values, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameBetween(String value1, String value2) {
            addCriterion("gameName between", value1, value2, "gamename");
            return (Criteria) this;
        }

        public Criteria andGamenameNotBetween(String value1, String value2) {
            addCriterion("gameName not between", value1, value2, "gamename");
            return (Criteria) this;
        }

        public Criteria andGametypeIsNull() {
            addCriterion("gameType is null");
            return (Criteria) this;
        }

        public Criteria andGametypeIsNotNull() {
            addCriterion("gameType is not null");
            return (Criteria) this;
        }

        public Criteria andGametypeEqualTo(Integer value) {
            addCriterion("gameType =", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeNotEqualTo(Integer value) {
            addCriterion("gameType <>", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeGreaterThan(Integer value) {
            addCriterion("gameType >", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("gameType >=", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeLessThan(Integer value) {
            addCriterion("gameType <", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeLessThanOrEqualTo(Integer value) {
            addCriterion("gameType <=", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeIn(List<Integer> values) {
            addCriterion("gameType in", values, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeNotIn(List<Integer> values) {
            addCriterion("gameType not in", values, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeBetween(Integer value1, Integer value2) {
            addCriterion("gameType between", value1, value2, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeNotBetween(Integer value1, Integer value2) {
            addCriterion("gameType not between", value1, value2, "gametype");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeIsNull() {
            addCriterion("gameStartTime is null");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeIsNotNull() {
            addCriterion("gameStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeEqualTo(Date value) {
            addCriterionForJDBCDate("gameStartTime =", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("gameStartTime <>", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("gameStartTime >", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("gameStartTime >=", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeLessThan(Date value) {
            addCriterionForJDBCDate("gameStartTime <", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("gameStartTime <=", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeIn(List<Date> values) {
            addCriterionForJDBCDate("gameStartTime in", values, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("gameStartTime not in", values, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("gameStartTime between", value1, value2, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("gameStartTime not between", value1, value2, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeIsNull() {
            addCriterion("gameEndTime is null");
            return (Criteria) this;
        }

        public Criteria andGameendtimeIsNotNull() {
            addCriterion("gameEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andGameendtimeEqualTo(Date value) {
            addCriterionForJDBCDate("gameEndTime =", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("gameEndTime <>", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("gameEndTime >", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("gameEndTime >=", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeLessThan(Date value) {
            addCriterionForJDBCDate("gameEndTime <", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("gameEndTime <=", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeIn(List<Date> values) {
            addCriterionForJDBCDate("gameEndTime in", values, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("gameEndTime not in", values, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("gameEndTime between", value1, value2, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("gameEndTime not between", value1, value2, "gameendtime");
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

        public Criteria andPrizerangeIsNull() {
            addCriterion("prizeRange is null");
            return (Criteria) this;
        }

        public Criteria andPrizerangeIsNotNull() {
            addCriterion("prizeRange is not null");
            return (Criteria) this;
        }

        public Criteria andPrizerangeEqualTo(String value) {
            addCriterion("prizeRange =", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeNotEqualTo(String value) {
            addCriterion("prizeRange <>", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeGreaterThan(String value) {
            addCriterion("prizeRange >", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeGreaterThanOrEqualTo(String value) {
            addCriterion("prizeRange >=", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeLessThan(String value) {
            addCriterion("prizeRange <", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeLessThanOrEqualTo(String value) {
            addCriterion("prizeRange <=", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeLike(String value) {
            addCriterion("prizeRange like", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeNotLike(String value) {
            addCriterion("prizeRange not like", value, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeIn(List<String> values) {
            addCriterion("prizeRange in", values, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeNotIn(List<String> values) {
            addCriterion("prizeRange not in", values, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeBetween(String value1, String value2) {
            addCriterion("prizeRange between", value1, value2, "prizerange");
            return (Criteria) this;
        }

        public Criteria andPrizerangeNotBetween(String value1, String value2) {
            addCriterion("prizeRange not between", value1, value2, "prizerange");
            return (Criteria) this;
        }

        public Criteria andRandomlengthIsNull() {
            addCriterion("randomLength is null");
            return (Criteria) this;
        }

        public Criteria andRandomlengthIsNotNull() {
            addCriterion("randomLength is not null");
            return (Criteria) this;
        }

        public Criteria andRandomlengthEqualTo(Integer value) {
            addCriterion("randomLength =", value, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthNotEqualTo(Integer value) {
            addCriterion("randomLength <>", value, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthGreaterThan(Integer value) {
            addCriterion("randomLength >", value, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("randomLength >=", value, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthLessThan(Integer value) {
            addCriterion("randomLength <", value, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthLessThanOrEqualTo(Integer value) {
            addCriterion("randomLength <=", value, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthIn(List<Integer> values) {
            addCriterion("randomLength in", values, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthNotIn(List<Integer> values) {
            addCriterion("randomLength not in", values, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthBetween(Integer value1, Integer value2) {
            addCriterion("randomLength between", value1, value2, "randomlength");
            return (Criteria) this;
        }

        public Criteria andRandomlengthNotBetween(Integer value1, Integer value2) {
            addCriterion("randomLength not between", value1, value2, "randomlength");
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