package com.mcoding.emis.goods.game.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameQuestionExample() {
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

        public Criteria andQuestiontypeIsNull() {
            addCriterion("questionType is null");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeIsNotNull() {
            addCriterion("questionType is not null");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeEqualTo(String value) {
            addCriterion("questionType =", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotEqualTo(String value) {
            addCriterion("questionType <>", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeGreaterThan(String value) {
            addCriterion("questionType >", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeGreaterThanOrEqualTo(String value) {
            addCriterion("questionType >=", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeLessThan(String value) {
            addCriterion("questionType <", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeLessThanOrEqualTo(String value) {
            addCriterion("questionType <=", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeLike(String value) {
            addCriterion("questionType like", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotLike(String value) {
            addCriterion("questionType not like", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeIn(List<String> values) {
            addCriterion("questionType in", values, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotIn(List<String> values) {
            addCriterion("questionType not in", values, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeBetween(String value1, String value2) {
            addCriterion("questionType between", value1, value2, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotBetween(String value1, String value2) {
            addCriterion("questionType not between", value1, value2, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleIsNull() {
            addCriterion("questionTitle is null");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleIsNotNull() {
            addCriterion("questionTitle is not null");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleEqualTo(String value) {
            addCriterion("questionTitle =", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotEqualTo(String value) {
            addCriterion("questionTitle <>", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleGreaterThan(String value) {
            addCriterion("questionTitle >", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleGreaterThanOrEqualTo(String value) {
            addCriterion("questionTitle >=", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleLessThan(String value) {
            addCriterion("questionTitle <", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleLessThanOrEqualTo(String value) {
            addCriterion("questionTitle <=", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleLike(String value) {
            addCriterion("questionTitle like", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotLike(String value) {
            addCriterion("questionTitle not like", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleIn(List<String> values) {
            addCriterion("questionTitle in", values, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotIn(List<String> values) {
            addCriterion("questionTitle not in", values, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleBetween(String value1, String value2) {
            addCriterion("questionTitle between", value1, value2, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotBetween(String value1, String value2) {
            addCriterion("questionTitle not between", value1, value2, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andOption1IsNull() {
            addCriterion("option1 is null");
            return (Criteria) this;
        }

        public Criteria andOption1IsNotNull() {
            addCriterion("option1 is not null");
            return (Criteria) this;
        }

        public Criteria andOption1EqualTo(String value) {
            addCriterion("option1 =", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1NotEqualTo(String value) {
            addCriterion("option1 <>", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1GreaterThan(String value) {
            addCriterion("option1 >", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1GreaterThanOrEqualTo(String value) {
            addCriterion("option1 >=", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1LessThan(String value) {
            addCriterion("option1 <", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1LessThanOrEqualTo(String value) {
            addCriterion("option1 <=", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1Like(String value) {
            addCriterion("option1 like", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1NotLike(String value) {
            addCriterion("option1 not like", value, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1In(List<String> values) {
            addCriterion("option1 in", values, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1NotIn(List<String> values) {
            addCriterion("option1 not in", values, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1Between(String value1, String value2) {
            addCriterion("option1 between", value1, value2, "option1");
            return (Criteria) this;
        }

        public Criteria andOption1NotBetween(String value1, String value2) {
            addCriterion("option1 not between", value1, value2, "option1");
            return (Criteria) this;
        }

        public Criteria andOption2IsNull() {
            addCriterion("option2 is null");
            return (Criteria) this;
        }

        public Criteria andOption2IsNotNull() {
            addCriterion("option2 is not null");
            return (Criteria) this;
        }

        public Criteria andOption2EqualTo(String value) {
            addCriterion("option2 =", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2NotEqualTo(String value) {
            addCriterion("option2 <>", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2GreaterThan(String value) {
            addCriterion("option2 >", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2GreaterThanOrEqualTo(String value) {
            addCriterion("option2 >=", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2LessThan(String value) {
            addCriterion("option2 <", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2LessThanOrEqualTo(String value) {
            addCriterion("option2 <=", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2Like(String value) {
            addCriterion("option2 like", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2NotLike(String value) {
            addCriterion("option2 not like", value, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2In(List<String> values) {
            addCriterion("option2 in", values, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2NotIn(List<String> values) {
            addCriterion("option2 not in", values, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2Between(String value1, String value2) {
            addCriterion("option2 between", value1, value2, "option2");
            return (Criteria) this;
        }

        public Criteria andOption2NotBetween(String value1, String value2) {
            addCriterion("option2 not between", value1, value2, "option2");
            return (Criteria) this;
        }

        public Criteria andOption3IsNull() {
            addCriterion("option3 is null");
            return (Criteria) this;
        }

        public Criteria andOption3IsNotNull() {
            addCriterion("option3 is not null");
            return (Criteria) this;
        }

        public Criteria andOption3EqualTo(String value) {
            addCriterion("option3 =", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3NotEqualTo(String value) {
            addCriterion("option3 <>", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3GreaterThan(String value) {
            addCriterion("option3 >", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3GreaterThanOrEqualTo(String value) {
            addCriterion("option3 >=", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3LessThan(String value) {
            addCriterion("option3 <", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3LessThanOrEqualTo(String value) {
            addCriterion("option3 <=", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3Like(String value) {
            addCriterion("option3 like", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3NotLike(String value) {
            addCriterion("option3 not like", value, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3In(List<String> values) {
            addCriterion("option3 in", values, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3NotIn(List<String> values) {
            addCriterion("option3 not in", values, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3Between(String value1, String value2) {
            addCriterion("option3 between", value1, value2, "option3");
            return (Criteria) this;
        }

        public Criteria andOption3NotBetween(String value1, String value2) {
            addCriterion("option3 not between", value1, value2, "option3");
            return (Criteria) this;
        }

        public Criteria andOption4IsNull() {
            addCriterion("option4 is null");
            return (Criteria) this;
        }

        public Criteria andOption4IsNotNull() {
            addCriterion("option4 is not null");
            return (Criteria) this;
        }

        public Criteria andOption4EqualTo(String value) {
            addCriterion("option4 =", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4NotEqualTo(String value) {
            addCriterion("option4 <>", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4GreaterThan(String value) {
            addCriterion("option4 >", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4GreaterThanOrEqualTo(String value) {
            addCriterion("option4 >=", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4LessThan(String value) {
            addCriterion("option4 <", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4LessThanOrEqualTo(String value) {
            addCriterion("option4 <=", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4Like(String value) {
            addCriterion("option4 like", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4NotLike(String value) {
            addCriterion("option4 not like", value, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4In(List<String> values) {
            addCriterion("option4 in", values, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4NotIn(List<String> values) {
            addCriterion("option4 not in", values, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4Between(String value1, String value2) {
            addCriterion("option4 between", value1, value2, "option4");
            return (Criteria) this;
        }

        public Criteria andOption4NotBetween(String value1, String value2) {
            addCriterion("option4 not between", value1, value2, "option4");
            return (Criteria) this;
        }

        public Criteria andOption5IsNull() {
            addCriterion("option5 is null");
            return (Criteria) this;
        }

        public Criteria andOption5IsNotNull() {
            addCriterion("option5 is not null");
            return (Criteria) this;
        }

        public Criteria andOption5EqualTo(String value) {
            addCriterion("option5 =", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5NotEqualTo(String value) {
            addCriterion("option5 <>", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5GreaterThan(String value) {
            addCriterion("option5 >", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5GreaterThanOrEqualTo(String value) {
            addCriterion("option5 >=", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5LessThan(String value) {
            addCriterion("option5 <", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5LessThanOrEqualTo(String value) {
            addCriterion("option5 <=", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5Like(String value) {
            addCriterion("option5 like", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5NotLike(String value) {
            addCriterion("option5 not like", value, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5In(List<String> values) {
            addCriterion("option5 in", values, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5NotIn(List<String> values) {
            addCriterion("option5 not in", values, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5Between(String value1, String value2) {
            addCriterion("option5 between", value1, value2, "option5");
            return (Criteria) this;
        }

        public Criteria andOption5NotBetween(String value1, String value2) {
            addCriterion("option5 not between", value1, value2, "option5");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andExtIsNull() {
            addCriterion("ext is null");
            return (Criteria) this;
        }

        public Criteria andExtIsNotNull() {
            addCriterion("ext is not null");
            return (Criteria) this;
        }

        public Criteria andExtEqualTo(String value) {
            addCriterion("ext =", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotEqualTo(String value) {
            addCriterion("ext <>", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThan(String value) {
            addCriterion("ext >", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThanOrEqualTo(String value) {
            addCriterion("ext >=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThan(String value) {
            addCriterion("ext <", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThanOrEqualTo(String value) {
            addCriterion("ext <=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLike(String value) {
            addCriterion("ext like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotLike(String value) {
            addCriterion("ext not like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtIn(List<String> values) {
            addCriterion("ext in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotIn(List<String> values) {
            addCriterion("ext not in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtBetween(String value1, String value2) {
            addCriterion("ext between", value1, value2, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotBetween(String value1, String value2) {
            addCriterion("ext not between", value1, value2, "ext");
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

        public Criteria andGameidIsNull() {
            addCriterion("gameId is null");
            return (Criteria) this;
        }

        public Criteria andGameidIsNotNull() {
            addCriterion("gameId is not null");
            return (Criteria) this;
        }

        public Criteria andGameidEqualTo(Integer value) {
            addCriterion("gameId =", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotEqualTo(Integer value) {
            addCriterion("gameId <>", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidGreaterThan(Integer value) {
            addCriterion("gameId >", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidGreaterThanOrEqualTo(Integer value) {
            addCriterion("gameId >=", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLessThan(Integer value) {
            addCriterion("gameId <", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLessThanOrEqualTo(Integer value) {
            addCriterion("gameId <=", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidIn(List<Integer> values) {
            addCriterion("gameId in", values, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotIn(List<Integer> values) {
            addCriterion("gameId not in", values, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidBetween(Integer value1, Integer value2) {
            addCriterion("gameId between", value1, value2, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotBetween(Integer value1, Integer value2) {
            addCriterion("gameId not between", value1, value2, "gameid");
            return (Criteria) this;
        }

        public Criteria andRightreplyIsNull() {
            addCriterion("rightReply is null");
            return (Criteria) this;
        }

        public Criteria andRightreplyIsNotNull() {
            addCriterion("rightReply is not null");
            return (Criteria) this;
        }

        public Criteria andRightreplyEqualTo(String value) {
            addCriterion("rightReply =", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyNotEqualTo(String value) {
            addCriterion("rightReply <>", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyGreaterThan(String value) {
            addCriterion("rightReply >", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyGreaterThanOrEqualTo(String value) {
            addCriterion("rightReply >=", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyLessThan(String value) {
            addCriterion("rightReply <", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyLessThanOrEqualTo(String value) {
            addCriterion("rightReply <=", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyLike(String value) {
            addCriterion("rightReply like", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyNotLike(String value) {
            addCriterion("rightReply not like", value, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyIn(List<String> values) {
            addCriterion("rightReply in", values, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyNotIn(List<String> values) {
            addCriterion("rightReply not in", values, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyBetween(String value1, String value2) {
            addCriterion("rightReply between", value1, value2, "rightreply");
            return (Criteria) this;
        }

        public Criteria andRightreplyNotBetween(String value1, String value2) {
            addCriterion("rightReply not between", value1, value2, "rightreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyIsNull() {
            addCriterion("wrongReply is null");
            return (Criteria) this;
        }

        public Criteria andWrongreplyIsNotNull() {
            addCriterion("wrongReply is not null");
            return (Criteria) this;
        }

        public Criteria andWrongreplyEqualTo(String value) {
            addCriterion("wrongReply =", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyNotEqualTo(String value) {
            addCriterion("wrongReply <>", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyGreaterThan(String value) {
            addCriterion("wrongReply >", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyGreaterThanOrEqualTo(String value) {
            addCriterion("wrongReply >=", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyLessThan(String value) {
            addCriterion("wrongReply <", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyLessThanOrEqualTo(String value) {
            addCriterion("wrongReply <=", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyLike(String value) {
            addCriterion("wrongReply like", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyNotLike(String value) {
            addCriterion("wrongReply not like", value, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyIn(List<String> values) {
            addCriterion("wrongReply in", values, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyNotIn(List<String> values) {
            addCriterion("wrongReply not in", values, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyBetween(String value1, String value2) {
            addCriterion("wrongReply between", value1, value2, "wrongreply");
            return (Criteria) this;
        }

        public Criteria andWrongreplyNotBetween(String value1, String value2) {
            addCriterion("wrongReply not between", value1, value2, "wrongreply");
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