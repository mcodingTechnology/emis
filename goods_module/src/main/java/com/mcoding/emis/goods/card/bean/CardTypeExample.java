package com.mcoding.emis.goods.card.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mcoding.base.core.PageView;

public class CardTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<CardType> pageView;

    public CardTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public PageView<CardType> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<CardType> pageView) {
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

        public Criteria andCardidIsNull() {
            addCriterion("cardId is null");
            return (Criteria) this;
        }

        public Criteria andCardidIsNotNull() {
            addCriterion("cardId is not null");
            return (Criteria) this;
        }

        public Criteria andCardidEqualTo(String value) {
            addCriterion("cardId =", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotEqualTo(String value) {
            addCriterion("cardId <>", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidGreaterThan(String value) {
            addCriterion("cardId >", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidGreaterThanOrEqualTo(String value) {
            addCriterion("cardId >=", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLessThan(String value) {
            addCriterion("cardId <", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLessThanOrEqualTo(String value) {
            addCriterion("cardId <=", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLike(String value) {
            addCriterion("cardId like", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotLike(String value) {
            addCriterion("cardId not like", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidIn(List<String> values) {
            addCriterion("cardId in", values, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotIn(List<String> values) {
            addCriterion("cardId not in", values, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidBetween(String value1, String value2) {
            addCriterion("cardId between", value1, value2, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotBetween(String value1, String value2) {
            addCriterion("cardId not between", value1, value2, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNull() {
            addCriterion("cardType is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNotNull() {
            addCriterion("cardType is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeEqualTo(String value) {
            addCriterion("cardType =", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotEqualTo(String value) {
            addCriterion("cardType <>", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThan(String value) {
            addCriterion("cardType >", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThanOrEqualTo(String value) {
            addCriterion("cardType >=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThan(String value) {
            addCriterion("cardType <", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThanOrEqualTo(String value) {
            addCriterion("cardType <=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLike(String value) {
            addCriterion("cardType like", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotLike(String value) {
            addCriterion("cardType not like", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeIn(List<String> values) {
            addCriterion("cardType in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotIn(List<String> values) {
            addCriterion("cardType not in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeBetween(String value1, String value2) {
            addCriterion("cardType between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotBetween(String value1, String value2) {
            addCriterion("cardType not between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardquantityIsNull() {
            addCriterion("cardQuantity is null");
            return (Criteria) this;
        }

        public Criteria andCardquantityIsNotNull() {
            addCriterion("cardQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andCardquantityEqualTo(Integer value) {
            addCriterion("cardQuantity =", value, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityNotEqualTo(Integer value) {
            addCriterion("cardQuantity <>", value, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityGreaterThan(Integer value) {
            addCriterion("cardQuantity >", value, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardQuantity >=", value, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityLessThan(Integer value) {
            addCriterion("cardQuantity <", value, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityLessThanOrEqualTo(Integer value) {
            addCriterion("cardQuantity <=", value, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityIn(List<Integer> values) {
            addCriterion("cardQuantity in", values, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityNotIn(List<Integer> values) {
            addCriterion("cardQuantity not in", values, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityBetween(Integer value1, Integer value2) {
            addCriterion("cardQuantity between", value1, value2, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardquantityNotBetween(Integer value1, Integer value2) {
            addCriterion("cardQuantity not between", value1, value2, "cardquantity");
            return (Criteria) this;
        }

        public Criteria andCardcountIsNull() {
            addCriterion("cardCount is null");
            return (Criteria) this;
        }

        public Criteria andCardcountIsNotNull() {
            addCriterion("cardCount is not null");
            return (Criteria) this;
        }

        public Criteria andCardcountEqualTo(Integer value) {
            addCriterion("cardCount =", value, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountNotEqualTo(Integer value) {
            addCriterion("cardCount <>", value, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountGreaterThan(Integer value) {
            addCriterion("cardCount >", value, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardCount >=", value, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountLessThan(Integer value) {
            addCriterion("cardCount <", value, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountLessThanOrEqualTo(Integer value) {
            addCriterion("cardCount <=", value, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountIn(List<Integer> values) {
            addCriterion("cardCount in", values, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountNotIn(List<Integer> values) {
            addCriterion("cardCount not in", values, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountBetween(Integer value1, Integer value2) {
            addCriterion("cardCount between", value1, value2, "cardcount");
            return (Criteria) this;
        }

        public Criteria andCardcountNotBetween(Integer value1, Integer value2) {
            addCriterion("cardCount not between", value1, value2, "cardcount");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNull() {
            addCriterion("isValid is null");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNotNull() {
            addCriterion("isValid is not null");
            return (Criteria) this;
        }

        public Criteria andIsvalidEqualTo(Integer value) {
            addCriterion("isValid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(Integer value) {
            addCriterion("isValid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(Integer value) {
            addCriterion("isValid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("isValid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(Integer value) {
            addCriterion("isValid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(Integer value) {
            addCriterion("isValid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<Integer> values) {
            addCriterion("isValid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<Integer> values) {
            addCriterion("isValid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(Integer value1, Integer value2) {
            addCriterion("isValid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(Integer value1, Integer value2) {
            addCriterion("isValid not between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeIsNull() {
            addCriterion("encrypt_code is null");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeIsNotNull() {
            addCriterion("encrypt_code is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeEqualTo(String value) {
            addCriterion("encrypt_code =", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeNotEqualTo(String value) {
            addCriterion("encrypt_code <>", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeGreaterThan(String value) {
            addCriterion("encrypt_code >", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("encrypt_code >=", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeLessThan(String value) {
            addCriterion("encrypt_code <", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeLessThanOrEqualTo(String value) {
            addCriterion("encrypt_code <=", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeLike(String value) {
            addCriterion("encrypt_code like", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeNotLike(String value) {
            addCriterion("encrypt_code not like", value, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeIn(List<String> values) {
            addCriterion("encrypt_code in", values, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeNotIn(List<String> values) {
            addCriterion("encrypt_code not in", values, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeBetween(String value1, String value2) {
            addCriterion("encrypt_code between", value1, value2, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andEncryptCodeNotBetween(String value1, String value2) {
            addCriterion("encrypt_code not between", value1, value2, "encryptCode");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTimetypeIsNull() {
            addCriterion("timeType is null");
            return (Criteria) this;
        }

        public Criteria andTimetypeIsNotNull() {
            addCriterion("timeType is not null");
            return (Criteria) this;
        }

        public Criteria andTimetypeEqualTo(String value) {
            addCriterion("timeType =", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotEqualTo(String value) {
            addCriterion("timeType <>", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeGreaterThan(String value) {
            addCriterion("timeType >", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeGreaterThanOrEqualTo(String value) {
            addCriterion("timeType >=", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLessThan(String value) {
            addCriterion("timeType <", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLessThanOrEqualTo(String value) {
            addCriterion("timeType <=", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLike(String value) {
            addCriterion("timeType like", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotLike(String value) {
            addCriterion("timeType not like", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeIn(List<String> values) {
            addCriterion("timeType in", values, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotIn(List<String> values) {
            addCriterion("timeType not in", values, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeBetween(String value1, String value2) {
            addCriterion("timeType between", value1, value2, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotBetween(String value1, String value2) {
            addCriterion("timeType not between", value1, value2, "timetype");
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

        public Criteria andBegintimeIsNull() {
            addCriterion("beginTime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("beginTime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterion("beginTime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterion("beginTime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterion("beginTime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("beginTime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterion("beginTime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterion("beginTime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterion("beginTime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterion("beginTime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterion("beginTime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterion("beginTime not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andFixedtermIsNull() {
            addCriterion("fixedTerm is null");
            return (Criteria) this;
        }

        public Criteria andFixedtermIsNotNull() {
            addCriterion("fixedTerm is not null");
            return (Criteria) this;
        }

        public Criteria andFixedtermEqualTo(Integer value) {
            addCriterion("fixedTerm =", value, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermNotEqualTo(Integer value) {
            addCriterion("fixedTerm <>", value, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermGreaterThan(Integer value) {
            addCriterion("fixedTerm >", value, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermGreaterThanOrEqualTo(Integer value) {
            addCriterion("fixedTerm >=", value, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermLessThan(Integer value) {
            addCriterion("fixedTerm <", value, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermLessThanOrEqualTo(Integer value) {
            addCriterion("fixedTerm <=", value, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermIn(List<Integer> values) {
            addCriterion("fixedTerm in", values, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermNotIn(List<Integer> values) {
            addCriterion("fixedTerm not in", values, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermBetween(Integer value1, Integer value2) {
            addCriterion("fixedTerm between", value1, value2, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedtermNotBetween(Integer value1, Integer value2) {
            addCriterion("fixedTerm not between", value1, value2, "fixedterm");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeIsNull() {
            addCriterion("fixedBeginTime is null");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeIsNotNull() {
            addCriterion("fixedBeginTime is not null");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeEqualTo(Date value) {
            addCriterion("fixedBeginTime =", value, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeNotEqualTo(Date value) {
            addCriterion("fixedBeginTime <>", value, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeGreaterThan(Date value) {
            addCriterion("fixedBeginTime >", value, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fixedBeginTime >=", value, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeLessThan(Date value) {
            addCriterion("fixedBeginTime <", value, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeLessThanOrEqualTo(Date value) {
            addCriterion("fixedBeginTime <=", value, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeIn(List<Date> values) {
            addCriterion("fixedBeginTime in", values, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeNotIn(List<Date> values) {
            addCriterion("fixedBeginTime not in", values, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeBetween(Date value1, Date value2) {
            addCriterion("fixedBeginTime between", value1, value2, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andFixedbegintimeNotBetween(Date value1, Date value2) {
            addCriterion("fixedBeginTime not between", value1, value2, "fixedbegintime");
            return (Criteria) this;
        }

        public Criteria andLeastcostIsNull() {
            addCriterion("leastCost is null");
            return (Criteria) this;
        }

        public Criteria andLeastcostIsNotNull() {
            addCriterion("leastCost is not null");
            return (Criteria) this;
        }

        public Criteria andLeastcostEqualTo(Integer value) {
            addCriterion("leastCost =", value, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostNotEqualTo(Integer value) {
            addCriterion("leastCost <>", value, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostGreaterThan(Integer value) {
            addCriterion("leastCost >", value, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostGreaterThanOrEqualTo(Integer value) {
            addCriterion("leastCost >=", value, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostLessThan(Integer value) {
            addCriterion("leastCost <", value, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostLessThanOrEqualTo(Integer value) {
            addCriterion("leastCost <=", value, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostIn(List<Integer> values) {
            addCriterion("leastCost in", values, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostNotIn(List<Integer> values) {
            addCriterion("leastCost not in", values, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostBetween(Integer value1, Integer value2) {
            addCriterion("leastCost between", value1, value2, "leastcost");
            return (Criteria) this;
        }

        public Criteria andLeastcostNotBetween(Integer value1, Integer value2) {
            addCriterion("leastCost not between", value1, value2, "leastcost");
            return (Criteria) this;
        }

        public Criteria andReducecostIsNull() {
            addCriterion("reduceCost is null");
            return (Criteria) this;
        }

        public Criteria andReducecostIsNotNull() {
            addCriterion("reduceCost is not null");
            return (Criteria) this;
        }

        public Criteria andReducecostEqualTo(Integer value) {
            addCriterion("reduceCost =", value, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostNotEqualTo(Integer value) {
            addCriterion("reduceCost <>", value, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostGreaterThan(Integer value) {
            addCriterion("reduceCost >", value, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostGreaterThanOrEqualTo(Integer value) {
            addCriterion("reduceCost >=", value, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostLessThan(Integer value) {
            addCriterion("reduceCost <", value, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostLessThanOrEqualTo(Integer value) {
            addCriterion("reduceCost <=", value, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostIn(List<Integer> values) {
            addCriterion("reduceCost in", values, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostNotIn(List<Integer> values) {
            addCriterion("reduceCost not in", values, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostBetween(Integer value1, Integer value2) {
            addCriterion("reduceCost between", value1, value2, "reducecost");
            return (Criteria) this;
        }

        public Criteria andReducecostNotBetween(Integer value1, Integer value2) {
            addCriterion("reduceCost not between", value1, value2, "reducecost");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount not between", value1, value2, "discount");
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

        public Criteria andDefaultDetailIsNull() {
            addCriterion("default_detail is null");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailIsNotNull() {
            addCriterion("default_detail is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailEqualTo(String value) {
            addCriterion("default_detail =", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotEqualTo(String value) {
            addCriterion("default_detail <>", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailGreaterThan(String value) {
            addCriterion("default_detail >", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailGreaterThanOrEqualTo(String value) {
            addCriterion("default_detail >=", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailLessThan(String value) {
            addCriterion("default_detail <", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailLessThanOrEqualTo(String value) {
            addCriterion("default_detail <=", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailLike(String value) {
            addCriterion("default_detail like", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotLike(String value) {
            addCriterion("default_detail not like", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailIn(List<String> values) {
            addCriterion("default_detail in", values, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotIn(List<String> values) {
            addCriterion("default_detail not in", values, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailBetween(String value1, String value2) {
            addCriterion("default_detail between", value1, value2, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotBetween(String value1, String value2) {
            addCriterion("default_detail not between", value1, value2, "defaultDetail");
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

        public Criteria andExt3IsNull() {
            addCriterion("ext3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt4IsNull() {
            addCriterion("ext4 is null");
            return (Criteria) this;
        }

        public Criteria andExt4IsNotNull() {
            addCriterion("ext4 is not null");
            return (Criteria) this;
        }

        public Criteria andExt4EqualTo(String value) {
            addCriterion("ext4 =", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotEqualTo(String value) {
            addCriterion("ext4 <>", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThan(String value) {
            addCriterion("ext4 >", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThanOrEqualTo(String value) {
            addCriterion("ext4 >=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThan(String value) {
            addCriterion("ext4 <", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThanOrEqualTo(String value) {
            addCriterion("ext4 <=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Like(String value) {
            addCriterion("ext4 like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotLike(String value) {
            addCriterion("ext4 not like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4In(List<String> values) {
            addCriterion("ext4 in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotIn(List<String> values) {
            addCriterion("ext4 not in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Between(String value1, String value2) {
            addCriterion("ext4 between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotBetween(String value1, String value2) {
            addCriterion("ext4 not between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt5IsNull() {
            addCriterion("ext5 is null");
            return (Criteria) this;
        }

        public Criteria andExt5IsNotNull() {
            addCriterion("ext5 is not null");
            return (Criteria) this;
        }

        public Criteria andExt5EqualTo(String value) {
            addCriterion("ext5 =", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotEqualTo(String value) {
            addCriterion("ext5 <>", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5GreaterThan(String value) {
            addCriterion("ext5 >", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5GreaterThanOrEqualTo(String value) {
            addCriterion("ext5 >=", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5LessThan(String value) {
            addCriterion("ext5 <", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5LessThanOrEqualTo(String value) {
            addCriterion("ext5 <=", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5Like(String value) {
            addCriterion("ext5 like", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotLike(String value) {
            addCriterion("ext5 not like", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5In(List<String> values) {
            addCriterion("ext5 in", values, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotIn(List<String> values) {
            addCriterion("ext5 not in", values, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5Between(String value1, String value2) {
            addCriterion("ext5 between", value1, value2, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotBetween(String value1, String value2) {
            addCriterion("ext5 not between", value1, value2, "ext5");
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