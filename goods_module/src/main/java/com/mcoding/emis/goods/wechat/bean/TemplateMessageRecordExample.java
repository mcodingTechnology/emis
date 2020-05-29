package com.mcoding.emis.goods.wechat.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemplateMessageRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TemplateMessageRecordExample() {
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

        public Criteria andTemplateMessageIdIsNull() {
            addCriterion("template_message_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdIsNotNull() {
            addCriterion("template_message_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdEqualTo(Integer value) {
            addCriterion("template_message_id =", value, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdNotEqualTo(Integer value) {
            addCriterion("template_message_id <>", value, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdGreaterThan(Integer value) {
            addCriterion("template_message_id >", value, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_message_id >=", value, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdLessThan(Integer value) {
            addCriterion("template_message_id <", value, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdLessThanOrEqualTo(Integer value) {
            addCriterion("template_message_id <=", value, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdIn(List<Integer> values) {
            addCriterion("template_message_id in", values, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdNotIn(List<Integer> values) {
            addCriterion("template_message_id not in", values, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdBetween(Integer value1, Integer value2) {
            addCriterion("template_message_id between", value1, value2, "templateMessageId");
            return (Criteria) this;
        }

        public Criteria andTemplateMessageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("template_message_id not between", value1, value2, "templateMessageId");
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

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
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

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andKeyword1IsNull() {
            addCriterion("keyword1 is null");
            return (Criteria) this;
        }

        public Criteria andKeyword1IsNotNull() {
            addCriterion("keyword1 is not null");
            return (Criteria) this;
        }

        public Criteria andKeyword1EqualTo(String value) {
            addCriterion("keyword1 =", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1NotEqualTo(String value) {
            addCriterion("keyword1 <>", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1GreaterThan(String value) {
            addCriterion("keyword1 >", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1GreaterThanOrEqualTo(String value) {
            addCriterion("keyword1 >=", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1LessThan(String value) {
            addCriterion("keyword1 <", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1LessThanOrEqualTo(String value) {
            addCriterion("keyword1 <=", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1Like(String value) {
            addCriterion("keyword1 like", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1NotLike(String value) {
            addCriterion("keyword1 not like", value, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1In(List<String> values) {
            addCriterion("keyword1 in", values, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1NotIn(List<String> values) {
            addCriterion("keyword1 not in", values, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1Between(String value1, String value2) {
            addCriterion("keyword1 between", value1, value2, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword1NotBetween(String value1, String value2) {
            addCriterion("keyword1 not between", value1, value2, "keyword1");
            return (Criteria) this;
        }

        public Criteria andKeyword2IsNull() {
            addCriterion("keyword2 is null");
            return (Criteria) this;
        }

        public Criteria andKeyword2IsNotNull() {
            addCriterion("keyword2 is not null");
            return (Criteria) this;
        }

        public Criteria andKeyword2EqualTo(String value) {
            addCriterion("keyword2 =", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2NotEqualTo(String value) {
            addCriterion("keyword2 <>", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2GreaterThan(String value) {
            addCriterion("keyword2 >", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2GreaterThanOrEqualTo(String value) {
            addCriterion("keyword2 >=", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2LessThan(String value) {
            addCriterion("keyword2 <", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2LessThanOrEqualTo(String value) {
            addCriterion("keyword2 <=", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2Like(String value) {
            addCriterion("keyword2 like", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2NotLike(String value) {
            addCriterion("keyword2 not like", value, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2In(List<String> values) {
            addCriterion("keyword2 in", values, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2NotIn(List<String> values) {
            addCriterion("keyword2 not in", values, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2Between(String value1, String value2) {
            addCriterion("keyword2 between", value1, value2, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword2NotBetween(String value1, String value2) {
            addCriterion("keyword2 not between", value1, value2, "keyword2");
            return (Criteria) this;
        }

        public Criteria andKeyword3IsNull() {
            addCriterion("keyword3 is null");
            return (Criteria) this;
        }

        public Criteria andKeyword3IsNotNull() {
            addCriterion("keyword3 is not null");
            return (Criteria) this;
        }

        public Criteria andKeyword3EqualTo(String value) {
            addCriterion("keyword3 =", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3NotEqualTo(String value) {
            addCriterion("keyword3 <>", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3GreaterThan(String value) {
            addCriterion("keyword3 >", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3GreaterThanOrEqualTo(String value) {
            addCriterion("keyword3 >=", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3LessThan(String value) {
            addCriterion("keyword3 <", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3LessThanOrEqualTo(String value) {
            addCriterion("keyword3 <=", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3Like(String value) {
            addCriterion("keyword3 like", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3NotLike(String value) {
            addCriterion("keyword3 not like", value, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3In(List<String> values) {
            addCriterion("keyword3 in", values, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3NotIn(List<String> values) {
            addCriterion("keyword3 not in", values, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3Between(String value1, String value2) {
            addCriterion("keyword3 between", value1, value2, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword3NotBetween(String value1, String value2) {
            addCriterion("keyword3 not between", value1, value2, "keyword3");
            return (Criteria) this;
        }

        public Criteria andKeyword4IsNull() {
            addCriterion("keyword4 is null");
            return (Criteria) this;
        }

        public Criteria andKeyword4IsNotNull() {
            addCriterion("keyword4 is not null");
            return (Criteria) this;
        }

        public Criteria andKeyword4EqualTo(String value) {
            addCriterion("keyword4 =", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4NotEqualTo(String value) {
            addCriterion("keyword4 <>", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4GreaterThan(String value) {
            addCriterion("keyword4 >", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4GreaterThanOrEqualTo(String value) {
            addCriterion("keyword4 >=", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4LessThan(String value) {
            addCriterion("keyword4 <", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4LessThanOrEqualTo(String value) {
            addCriterion("keyword4 <=", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4Like(String value) {
            addCriterion("keyword4 like", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4NotLike(String value) {
            addCriterion("keyword4 not like", value, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4In(List<String> values) {
            addCriterion("keyword4 in", values, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4NotIn(List<String> values) {
            addCriterion("keyword4 not in", values, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4Between(String value1, String value2) {
            addCriterion("keyword4 between", value1, value2, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword4NotBetween(String value1, String value2) {
            addCriterion("keyword4 not between", value1, value2, "keyword4");
            return (Criteria) this;
        }

        public Criteria andKeyword5IsNull() {
            addCriterion("keyword5 is null");
            return (Criteria) this;
        }

        public Criteria andKeyword5IsNotNull() {
            addCriterion("keyword5 is not null");
            return (Criteria) this;
        }

        public Criteria andKeyword5EqualTo(String value) {
            addCriterion("keyword5 =", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5NotEqualTo(String value) {
            addCriterion("keyword5 <>", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5GreaterThan(String value) {
            addCriterion("keyword5 >", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5GreaterThanOrEqualTo(String value) {
            addCriterion("keyword5 >=", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5LessThan(String value) {
            addCriterion("keyword5 <", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5LessThanOrEqualTo(String value) {
            addCriterion("keyword5 <=", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5Like(String value) {
            addCriterion("keyword5 like", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5NotLike(String value) {
            addCriterion("keyword5 not like", value, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5In(List<String> values) {
            addCriterion("keyword5 in", values, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5NotIn(List<String> values) {
            addCriterion("keyword5 not in", values, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5Between(String value1, String value2) {
            addCriterion("keyword5 between", value1, value2, "keyword5");
            return (Criteria) this;
        }

        public Criteria andKeyword5NotBetween(String value1, String value2) {
            addCriterion("keyword5 not between", value1, value2, "keyword5");
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

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNull() {
            addCriterion("createdTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNotNull() {
            addCriterion("createdTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeEqualTo(Date value) {
            addCriterion("createdTime =", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotEqualTo(Date value) {
            addCriterion("createdTime <>", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThan(Date value) {
            addCriterion("createdTime >", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createdTime >=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThan(Date value) {
            addCriterion("createdTime <", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("createdTime <=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIn(List<Date> values) {
            addCriterion("createdTime in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotIn(List<Date> values) {
            addCriterion("createdTime not in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeBetween(Date value1, Date value2) {
            addCriterion("createdTime between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("createdTime not between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIsNull() {
            addCriterion("updatedTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIsNotNull() {
            addCriterion("updatedTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeEqualTo(Date value) {
            addCriterion("updatedTime =", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotEqualTo(Date value) {
            addCriterion("updatedTime <>", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeGreaterThan(Date value) {
            addCriterion("updatedTime >", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatedTime >=", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeLessThan(Date value) {
            addCriterion("updatedTime <", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("updatedTime <=", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIn(List<Date> values) {
            addCriterion("updatedTime in", values, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotIn(List<Date> values) {
            addCriterion("updatedTime not in", values, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeBetween(Date value1, Date value2) {
            addCriterion("updatedTime between", value1, value2, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("updatedTime not between", value1, value2, "updatedtime");
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