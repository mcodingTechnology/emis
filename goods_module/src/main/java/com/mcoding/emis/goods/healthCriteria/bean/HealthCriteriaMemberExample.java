package com.mcoding.emis.goods.healthCriteria.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthCriteriaMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HealthCriteriaMemberExample() {
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("score like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("score not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andDetermineIsNull() {
            addCriterion("determine is null");
            return (Criteria) this;
        }

        public Criteria andDetermineIsNotNull() {
            addCriterion("determine is not null");
            return (Criteria) this;
        }

        public Criteria andDetermineEqualTo(String value) {
            addCriterion("determine =", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotEqualTo(String value) {
            addCriterion("determine <>", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineGreaterThan(String value) {
            addCriterion("determine >", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineGreaterThanOrEqualTo(String value) {
            addCriterion("determine >=", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineLessThan(String value) {
            addCriterion("determine <", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineLessThanOrEqualTo(String value) {
            addCriterion("determine <=", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineLike(String value) {
            addCriterion("determine like", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotLike(String value) {
            addCriterion("determine not like", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineIn(List<String> values) {
            addCriterion("determine in", values, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotIn(List<String> values) {
            addCriterion("determine not in", values, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineBetween(String value1, String value2) {
            addCriterion("determine between", value1, value2, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotBetween(String value1, String value2) {
            addCriterion("determine not between", value1, value2, "determine");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceIsNull() {
            addCriterion("additionalAdvice is null");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceIsNotNull() {
            addCriterion("additionalAdvice is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceEqualTo(String value) {
            addCriterion("additionalAdvice =", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceNotEqualTo(String value) {
            addCriterion("additionalAdvice <>", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceGreaterThan(String value) {
            addCriterion("additionalAdvice >", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceGreaterThanOrEqualTo(String value) {
            addCriterion("additionalAdvice >=", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceLessThan(String value) {
            addCriterion("additionalAdvice <", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceLessThanOrEqualTo(String value) {
            addCriterion("additionalAdvice <=", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceLike(String value) {
            addCriterion("additionalAdvice like", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceNotLike(String value) {
            addCriterion("additionalAdvice not like", value, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceIn(List<String> values) {
            addCriterion("additionalAdvice in", values, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceNotIn(List<String> values) {
            addCriterion("additionalAdvice not in", values, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceBetween(String value1, String value2) {
            addCriterion("additionalAdvice between", value1, value2, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andAdditionaladviceNotBetween(String value1, String value2) {
            addCriterion("additionalAdvice not between", value1, value2, "additionaladvice");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidIsNull() {
            addCriterion("healthCriteriaId is null");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidIsNotNull() {
            addCriterion("healthCriteriaId is not null");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidEqualTo(String value) {
            addCriterion("healthCriteriaId =", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidNotEqualTo(String value) {
            addCriterion("healthCriteriaId <>", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidGreaterThan(String value) {
            addCriterion("healthCriteriaId >", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidGreaterThanOrEqualTo(String value) {
            addCriterion("healthCriteriaId >=", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidLessThan(String value) {
            addCriterion("healthCriteriaId <", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidLessThanOrEqualTo(String value) {
            addCriterion("healthCriteriaId <=", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidLike(String value) {
            addCriterion("healthCriteriaId like", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidNotLike(String value) {
            addCriterion("healthCriteriaId not like", value, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidIn(List<String> values) {
            addCriterion("healthCriteriaId in", values, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidNotIn(List<String> values) {
            addCriterion("healthCriteriaId not in", values, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidBetween(String value1, String value2) {
            addCriterion("healthCriteriaId between", value1, value2, "healthcriteriaid");
            return (Criteria) this;
        }

        public Criteria andHealthcriteriaidNotBetween(String value1, String value2) {
            addCriterion("healthCriteriaId not between", value1, value2, "healthcriteriaid");
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

        public Criteria andProductidEqualTo(String value) {
            addCriterion("productId =", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotEqualTo(String value) {
            addCriterion("productId <>", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThan(String value) {
            addCriterion("productId >", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThanOrEqualTo(String value) {
            addCriterion("productId >=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThan(String value) {
            addCriterion("productId <", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThanOrEqualTo(String value) {
            addCriterion("productId <=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLike(String value) {
            addCriterion("productId like", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotLike(String value) {
            addCriterion("productId not like", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidIn(List<String> values) {
            addCriterion("productId in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotIn(List<String> values) {
            addCriterion("productId not in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidBetween(String value1, String value2) {
            addCriterion("productId between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotBetween(String value1, String value2) {
            addCriterion("productId not between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andDetailedreportIsNull() {
            addCriterion("detailedReport is null");
            return (Criteria) this;
        }

        public Criteria andDetailedreportIsNotNull() {
            addCriterion("detailedReport is not null");
            return (Criteria) this;
        }

        public Criteria andDetailedreportEqualTo(String value) {
            addCriterion("detailedReport =", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportNotEqualTo(String value) {
            addCriterion("detailedReport <>", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportGreaterThan(String value) {
            addCriterion("detailedReport >", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportGreaterThanOrEqualTo(String value) {
            addCriterion("detailedReport >=", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportLessThan(String value) {
            addCriterion("detailedReport <", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportLessThanOrEqualTo(String value) {
            addCriterion("detailedReport <=", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportLike(String value) {
            addCriterion("detailedReport like", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportNotLike(String value) {
            addCriterion("detailedReport not like", value, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportIn(List<String> values) {
            addCriterion("detailedReport in", values, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportNotIn(List<String> values) {
            addCriterion("detailedReport not in", values, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportBetween(String value1, String value2) {
            addCriterion("detailedReport between", value1, value2, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andDetailedreportNotBetween(String value1, String value2) {
            addCriterion("detailedReport not between", value1, value2, "detailedreport");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNull() {
            addCriterion("updateDate is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("updateDate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Date value) {
            addCriterion("updateDate =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Date value) {
            addCriterion("updateDate <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Date value) {
            addCriterion("updateDate >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("updateDate >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Date value) {
            addCriterion("updateDate <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Date value) {
            addCriterion("updateDate <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Date> values) {
            addCriterion("updateDate in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Date> values) {
            addCriterion("updateDate not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Date value1, Date value2) {
            addCriterion("updateDate between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Date value1, Date value2) {
            addCriterion("updateDate not between", value1, value2, "updatedate");
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

        public Criteria andHealthMark1IsNull() {
            addCriterion("health_mark1 is null");
            return (Criteria) this;
        }

        public Criteria andHealthMark1IsNotNull() {
            addCriterion("health_mark1 is not null");
            return (Criteria) this;
        }

        public Criteria andHealthMark1EqualTo(String value) {
            addCriterion("health_mark1 =", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1NotEqualTo(String value) {
            addCriterion("health_mark1 <>", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1GreaterThan(String value) {
            addCriterion("health_mark1 >", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1GreaterThanOrEqualTo(String value) {
            addCriterion("health_mark1 >=", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1LessThan(String value) {
            addCriterion("health_mark1 <", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1LessThanOrEqualTo(String value) {
            addCriterion("health_mark1 <=", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1Like(String value) {
            addCriterion("health_mark1 like", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1NotLike(String value) {
            addCriterion("health_mark1 not like", value, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1In(List<String> values) {
            addCriterion("health_mark1 in", values, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1NotIn(List<String> values) {
            addCriterion("health_mark1 not in", values, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1Between(String value1, String value2) {
            addCriterion("health_mark1 between", value1, value2, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark1NotBetween(String value1, String value2) {
            addCriterion("health_mark1 not between", value1, value2, "healthMark1");
            return (Criteria) this;
        }

        public Criteria andHealthMark2IsNull() {
            addCriterion("health_mark2 is null");
            return (Criteria) this;
        }

        public Criteria andHealthMark2IsNotNull() {
            addCriterion("health_mark2 is not null");
            return (Criteria) this;
        }

        public Criteria andHealthMark2EqualTo(String value) {
            addCriterion("health_mark2 =", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2NotEqualTo(String value) {
            addCriterion("health_mark2 <>", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2GreaterThan(String value) {
            addCriterion("health_mark2 >", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2GreaterThanOrEqualTo(String value) {
            addCriterion("health_mark2 >=", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2LessThan(String value) {
            addCriterion("health_mark2 <", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2LessThanOrEqualTo(String value) {
            addCriterion("health_mark2 <=", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2Like(String value) {
            addCriterion("health_mark2 like", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2NotLike(String value) {
            addCriterion("health_mark2 not like", value, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2In(List<String> values) {
            addCriterion("health_mark2 in", values, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2NotIn(List<String> values) {
            addCriterion("health_mark2 not in", values, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2Between(String value1, String value2) {
            addCriterion("health_mark2 between", value1, value2, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark2NotBetween(String value1, String value2) {
            addCriterion("health_mark2 not between", value1, value2, "healthMark2");
            return (Criteria) this;
        }

        public Criteria andHealthMark3IsNull() {
            addCriterion("health_mark3 is null");
            return (Criteria) this;
        }

        public Criteria andHealthMark3IsNotNull() {
            addCriterion("health_mark3 is not null");
            return (Criteria) this;
        }

        public Criteria andHealthMark3EqualTo(String value) {
            addCriterion("health_mark3 =", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3NotEqualTo(String value) {
            addCriterion("health_mark3 <>", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3GreaterThan(String value) {
            addCriterion("health_mark3 >", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3GreaterThanOrEqualTo(String value) {
            addCriterion("health_mark3 >=", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3LessThan(String value) {
            addCriterion("health_mark3 <", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3LessThanOrEqualTo(String value) {
            addCriterion("health_mark3 <=", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3Like(String value) {
            addCriterion("health_mark3 like", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3NotLike(String value) {
            addCriterion("health_mark3 not like", value, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3In(List<String> values) {
            addCriterion("health_mark3 in", values, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3NotIn(List<String> values) {
            addCriterion("health_mark3 not in", values, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3Between(String value1, String value2) {
            addCriterion("health_mark3 between", value1, value2, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark3NotBetween(String value1, String value2) {
            addCriterion("health_mark3 not between", value1, value2, "healthMark3");
            return (Criteria) this;
        }

        public Criteria andHealthMark4IsNull() {
            addCriterion("health_mark4 is null");
            return (Criteria) this;
        }

        public Criteria andHealthMark4IsNotNull() {
            addCriterion("health_mark4 is not null");
            return (Criteria) this;
        }

        public Criteria andHealthMark4EqualTo(String value) {
            addCriterion("health_mark4 =", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4NotEqualTo(String value) {
            addCriterion("health_mark4 <>", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4GreaterThan(String value) {
            addCriterion("health_mark4 >", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4GreaterThanOrEqualTo(String value) {
            addCriterion("health_mark4 >=", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4LessThan(String value) {
            addCriterion("health_mark4 <", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4LessThanOrEqualTo(String value) {
            addCriterion("health_mark4 <=", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4Like(String value) {
            addCriterion("health_mark4 like", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4NotLike(String value) {
            addCriterion("health_mark4 not like", value, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4In(List<String> values) {
            addCriterion("health_mark4 in", values, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4NotIn(List<String> values) {
            addCriterion("health_mark4 not in", values, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4Between(String value1, String value2) {
            addCriterion("health_mark4 between", value1, value2, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark4NotBetween(String value1, String value2) {
            addCriterion("health_mark4 not between", value1, value2, "healthMark4");
            return (Criteria) this;
        }

        public Criteria andHealthMark5IsNull() {
            addCriterion("health_mark5 is null");
            return (Criteria) this;
        }

        public Criteria andHealthMark5IsNotNull() {
            addCriterion("health_mark5 is not null");
            return (Criteria) this;
        }

        public Criteria andHealthMark5EqualTo(String value) {
            addCriterion("health_mark5 =", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5NotEqualTo(String value) {
            addCriterion("health_mark5 <>", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5GreaterThan(String value) {
            addCriterion("health_mark5 >", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5GreaterThanOrEqualTo(String value) {
            addCriterion("health_mark5 >=", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5LessThan(String value) {
            addCriterion("health_mark5 <", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5LessThanOrEqualTo(String value) {
            addCriterion("health_mark5 <=", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5Like(String value) {
            addCriterion("health_mark5 like", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5NotLike(String value) {
            addCriterion("health_mark5 not like", value, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5In(List<String> values) {
            addCriterion("health_mark5 in", values, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5NotIn(List<String> values) {
            addCriterion("health_mark5 not in", values, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5Between(String value1, String value2) {
            addCriterion("health_mark5 between", value1, value2, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark5NotBetween(String value1, String value2) {
            addCriterion("health_mark5 not between", value1, value2, "healthMark5");
            return (Criteria) this;
        }

        public Criteria andHealthMark6IsNull() {
            addCriterion("health_mark6 is null");
            return (Criteria) this;
        }

        public Criteria andHealthMark6IsNotNull() {
            addCriterion("health_mark6 is not null");
            return (Criteria) this;
        }

        public Criteria andHealthMark6EqualTo(String value) {
            addCriterion("health_mark6 =", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6NotEqualTo(String value) {
            addCriterion("health_mark6 <>", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6GreaterThan(String value) {
            addCriterion("health_mark6 >", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6GreaterThanOrEqualTo(String value) {
            addCriterion("health_mark6 >=", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6LessThan(String value) {
            addCriterion("health_mark6 <", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6LessThanOrEqualTo(String value) {
            addCriterion("health_mark6 <=", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6Like(String value) {
            addCriterion("health_mark6 like", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6NotLike(String value) {
            addCriterion("health_mark6 not like", value, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6In(List<String> values) {
            addCriterion("health_mark6 in", values, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6NotIn(List<String> values) {
            addCriterion("health_mark6 not in", values, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6Between(String value1, String value2) {
            addCriterion("health_mark6 between", value1, value2, "healthMark6");
            return (Criteria) this;
        }

        public Criteria andHealthMark6NotBetween(String value1, String value2) {
            addCriterion("health_mark6 not between", value1, value2, "healthMark6");
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

        public Criteria andResultStatusIsNull() {
            addCriterion("result_status is null");
            return (Criteria) this;
        }

        public Criteria andResultStatusIsNotNull() {
            addCriterion("result_status is not null");
            return (Criteria) this;
        }

        public Criteria andResultStatusEqualTo(Integer value) {
            addCriterion("result_status =", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusNotEqualTo(Integer value) {
            addCriterion("result_status <>", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusGreaterThan(Integer value) {
            addCriterion("result_status >", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("result_status >=", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusLessThan(Integer value) {
            addCriterion("result_status <", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusLessThanOrEqualTo(Integer value) {
            addCriterion("result_status <=", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusIn(List<Integer> values) {
            addCriterion("result_status in", values, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusNotIn(List<Integer> values) {
            addCriterion("result_status not in", values, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusBetween(Integer value1, Integer value2) {
            addCriterion("result_status between", value1, value2, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("result_status not between", value1, value2, "resultStatus");
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