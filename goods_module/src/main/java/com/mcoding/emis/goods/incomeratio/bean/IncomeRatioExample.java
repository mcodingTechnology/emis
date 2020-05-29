package com.mcoding.emis.goods.incomeratio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mcoding.base.core.PageView;

public class IncomeRatioExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<IncomeRatio> pageView;

    public IncomeRatioExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public PageView<IncomeRatio> getPageView() {
        return pageView;
    }

    public void setPageView(PageView<IncomeRatio> pageView) {
        this.pageView = pageView;
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

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        private static final long serialVersionUID = 1L;

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

        public Criteria andDealerIdIsNull() {
            addCriterion("dealer_id is null");
            return (Criteria) this;
        }

        public Criteria andDealerIdIsNotNull() {
            addCriterion("dealer_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealerIdEqualTo(Integer value) {
            addCriterion("dealer_id =", value, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdNotEqualTo(Integer value) {
            addCriterion("dealer_id <>", value, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdGreaterThan(Integer value) {
            addCriterion("dealer_id >", value, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dealer_id >=", value, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdLessThan(Integer value) {
            addCriterion("dealer_id <", value, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdLessThanOrEqualTo(Integer value) {
            addCriterion("dealer_id <=", value, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdIn(List<Integer> values) {
            addCriterion("dealer_id in", values, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdNotIn(List<Integer> values) {
            addCriterion("dealer_id not in", values, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdBetween(Integer value1, Integer value2) {
            addCriterion("dealer_id between", value1, value2, "dealerId");
            return (Criteria) this;
        }

        public Criteria andDealerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dealer_id not between", value1, value2, "dealerId");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeIsNull() {
            addCriterion("first_prize is null");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeIsNotNull() {
            addCriterion("first_prize is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeEqualTo(BigDecimal value) {
            addCriterion("first_prize =", value, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeNotEqualTo(BigDecimal value) {
            addCriterion("first_prize <>", value, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeGreaterThan(BigDecimal value) {
            addCriterion("first_prize >", value, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("first_prize >=", value, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeLessThan(BigDecimal value) {
            addCriterion("first_prize <", value, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("first_prize <=", value, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeIn(List<BigDecimal> values) {
            addCriterion("first_prize in", values, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeNotIn(List<BigDecimal> values) {
            addCriterion("first_prize not in", values, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_prize between", value1, value2, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andFirstPrizeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_prize not between", value1, value2, "firstPrize");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardIsNull() {
            addCriterion("perpetual_award is null");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardIsNotNull() {
            addCriterion("perpetual_award is not null");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardEqualTo(BigDecimal value) {
            addCriterion("perpetual_award =", value, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardNotEqualTo(BigDecimal value) {
            addCriterion("perpetual_award <>", value, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardGreaterThan(BigDecimal value) {
            addCriterion("perpetual_award >", value, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("perpetual_award >=", value, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardLessThan(BigDecimal value) {
            addCriterion("perpetual_award <", value, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardLessThanOrEqualTo(BigDecimal value) {
            addCriterion("perpetual_award <=", value, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardIn(List<BigDecimal> values) {
            addCriterion("perpetual_award in", values, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardNotIn(List<BigDecimal> values) {
            addCriterion("perpetual_award not in", values, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("perpetual_award between", value1, value2, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andPerpetualAwardNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("perpetual_award not between", value1, value2, "perpetualAward");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNull() {
            addCriterion("is_valid is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("is_valid is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(String value) {
            addCriterion("is_valid =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(String value) {
            addCriterion("is_valid <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(String value) {
            addCriterion("is_valid >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(String value) {
            addCriterion("is_valid >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(String value) {
            addCriterion("is_valid <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(String value) {
            addCriterion("is_valid <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLike(String value) {
            addCriterion("is_valid like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotLike(String value) {
            addCriterion("is_valid not like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<String> values) {
            addCriterion("is_valid in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<String> values) {
            addCriterion("is_valid not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(String value1, String value2) {
            addCriterion("is_valid between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(String value1, String value2) {
            addCriterion("is_valid not between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

    public static class Criteria extends GeneratedCriteria implements Serializable {
        private static final long serialVersionUID = 1L;

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        private static final long serialVersionUID = 1L;

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