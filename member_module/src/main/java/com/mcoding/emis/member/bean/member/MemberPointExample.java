package com.mcoding.emis.member.bean.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberPointExample implements IExample<MemberPoint> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<MemberPoint> pageView;

    public MemberPointExample() {
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

    @Override
    public PageView<MemberPoint> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<MemberPoint> pageView) {
        this.pageView = pageView;
    }

    public String toJson() throws JsonProcessingException {
        return JsonUtilsForMcoding.writeValueAsString(this);
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

        public Criteria andMemberPointIdIsNull() {
            addCriterion("memberPointId is null");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdIsNotNull() {
            addCriterion("memberPointId is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdEqualTo(Integer value) {
            addCriterion("memberPointId =", value, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdNotEqualTo(Integer value) {
            addCriterion("memberPointId <>", value, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdGreaterThan(Integer value) {
            addCriterion("memberPointId >", value, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("memberPointId >=", value, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdLessThan(Integer value) {
            addCriterion("memberPointId <", value, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdLessThanOrEqualTo(Integer value) {
            addCriterion("memberPointId <=", value, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdIn(List<Integer> values) {
            addCriterion("memberPointId in", values, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdNotIn(List<Integer> values) {
            addCriterion("memberPointId not in", values, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdBetween(Integer value1, Integer value2) {
            addCriterion("memberPointId between", value1, value2, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andMemberPointIdNotBetween(Integer value1, Integer value2) {
            addCriterion("memberPointId not between", value1, value2, "memberPointId");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNull() {
            addCriterion("fullName is null");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNotNull() {
            addCriterion("fullName is not null");
            return (Criteria) this;
        }

        public Criteria andFullNameEqualTo(String value) {
            addCriterion("fullName =", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotEqualTo(String value) {
            addCriterion("fullName <>", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThan(String value) {
            addCriterion("fullName >", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("fullName >=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThan(String value) {
            addCriterion("fullName <", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThanOrEqualTo(String value) {
            addCriterion("fullName <=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLike(String value) {
            addCriterion("fullName like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotLike(String value) {
            addCriterion("fullName not like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameIn(List<String> values) {
            addCriterion("fullName in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotIn(List<String> values) {
            addCriterion("fullName not in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameBetween(String value1, String value2) {
            addCriterion("fullName between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotBetween(String value1, String value2) {
            addCriterion("fullName not between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("mobilePhone is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("mobilePhone is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("mobilePhone =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("mobilePhone <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("mobilePhone >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("mobilePhone >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("mobilePhone <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("mobilePhone <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("mobilePhone like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("mobilePhone not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("mobilePhone in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("mobilePhone not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("mobilePhone between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("mobilePhone not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andPointsTypeIsNull() {
            addCriterion("pointsType is null");
            return (Criteria) this;
        }

        public Criteria andPointsTypeIsNotNull() {
            addCriterion("pointsType is not null");
            return (Criteria) this;
        }

        public Criteria andPointsTypeEqualTo(String value) {
            addCriterion("pointsType =", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeNotEqualTo(String value) {
            addCriterion("pointsType <>", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeGreaterThan(String value) {
            addCriterion("pointsType >", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pointsType >=", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeLessThan(String value) {
            addCriterion("pointsType <", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeLessThanOrEqualTo(String value) {
            addCriterion("pointsType <=", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeLike(String value) {
            addCriterion("pointsType like", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeNotLike(String value) {
            addCriterion("pointsType not like", value, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeIn(List<String> values) {
            addCriterion("pointsType in", values, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeNotIn(List<String> values) {
            addCriterion("pointsType not in", values, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeBetween(String value1, String value2) {
            addCriterion("pointsType between", value1, value2, "pointsType");
            return (Criteria) this;
        }

        public Criteria andPointsTypeNotBetween(String value1, String value2) {
            addCriterion("pointsType not between", value1, value2, "pointsType");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeIsNull() {
            addCriterion("fakeCheckCode is null");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeIsNotNull() {
            addCriterion("fakeCheckCode is not null");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeEqualTo(String value) {
            addCriterion("fakeCheckCode =", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeNotEqualTo(String value) {
            addCriterion("fakeCheckCode <>", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeGreaterThan(String value) {
            addCriterion("fakeCheckCode >", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fakeCheckCode >=", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeLessThan(String value) {
            addCriterion("fakeCheckCode <", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeLessThanOrEqualTo(String value) {
            addCriterion("fakeCheckCode <=", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeLike(String value) {
            addCriterion("fakeCheckCode like", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeNotLike(String value) {
            addCriterion("fakeCheckCode not like", value, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeIn(List<String> values) {
            addCriterion("fakeCheckCode in", values, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeNotIn(List<String> values) {
            addCriterion("fakeCheckCode not in", values, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeBetween(String value1, String value2) {
            addCriterion("fakeCheckCode between", value1, value2, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andFakeCheckCodeNotBetween(String value1, String value2) {
            addCriterion("fakeCheckCode not between", value1, value2, "fakeCheckCode");
            return (Criteria) this;
        }

        public Criteria andPointsIsNull() {
            addCriterion("points is null");
            return (Criteria) this;
        }

        public Criteria andPointsIsNotNull() {
            addCriterion("points is not null");
            return (Criteria) this;
        }

        public Criteria andPointsEqualTo(Integer value) {
            addCriterion("points =", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotEqualTo(Integer value) {
            addCriterion("points <>", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThan(Integer value) {
            addCriterion("points >", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("points >=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThan(Integer value) {
            addCriterion("points <", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThanOrEqualTo(Integer value) {
            addCriterion("points <=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsIn(List<Integer> values) {
            addCriterion("points in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotIn(List<Integer> values) {
            addCriterion("points not in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsBetween(Integer value1, Integer value2) {
            addCriterion("points between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotBetween(Integer value1, Integer value2) {
            addCriterion("points not between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoIsNull() {
            addCriterion("relatedTransactionNo is null");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoIsNotNull() {
            addCriterion("relatedTransactionNo is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoEqualTo(String value) {
            addCriterion("relatedTransactionNo =", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoNotEqualTo(String value) {
            addCriterion("relatedTransactionNo <>", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoGreaterThan(String value) {
            addCriterion("relatedTransactionNo >", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoGreaterThanOrEqualTo(String value) {
            addCriterion("relatedTransactionNo >=", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoLessThan(String value) {
            addCriterion("relatedTransactionNo <", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoLessThanOrEqualTo(String value) {
            addCriterion("relatedTransactionNo <=", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoLike(String value) {
            addCriterion("relatedTransactionNo like", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoNotLike(String value) {
            addCriterion("relatedTransactionNo not like", value, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoIn(List<String> values) {
            addCriterion("relatedTransactionNo in", values, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoNotIn(List<String> values) {
            addCriterion("relatedTransactionNo not in", values, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoBetween(String value1, String value2) {
            addCriterion("relatedTransactionNo between", value1, value2, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andRelatedTransactionNoNotBetween(String value1, String value2) {
            addCriterion("relatedTransactionNo not between", value1, value2, "relatedTransactionNo");
            return (Criteria) this;
        }

        public Criteria andAddDateIsNull() {
            addCriterion("addDate is null");
            return (Criteria) this;
        }

        public Criteria andAddDateIsNotNull() {
            addCriterion("addDate is not null");
            return (Criteria) this;
        }

        public Criteria andAddDateEqualTo(Date value) {
            addCriterion("addDate =", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotEqualTo(Date value) {
            addCriterion("addDate <>", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThan(Date value) {
            addCriterion("addDate >", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThanOrEqualTo(Date value) {
            addCriterion("addDate >=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThan(Date value) {
            addCriterion("addDate <", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThanOrEqualTo(Date value) {
            addCriterion("addDate <=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateIn(List<Date> values) {
            addCriterion("addDate in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotIn(List<Date> values) {
            addCriterion("addDate not in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateBetween(Date value1, Date value2) {
            addCriterion("addDate between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotBetween(Date value1, Date value2) {
            addCriterion("addDate not between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNull() {
            addCriterion("brandCode is null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNotNull() {
            addCriterion("brandCode is not null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeEqualTo(String value) {
            addCriterion("brandCode =", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotEqualTo(String value) {
            addCriterion("brandCode <>", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThan(String value) {
            addCriterion("brandCode >", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThanOrEqualTo(String value) {
            addCriterion("brandCode >=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThan(String value) {
            addCriterion("brandCode <", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThanOrEqualTo(String value) {
            addCriterion("brandCode <=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLike(String value) {
            addCriterion("brandCode like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotLike(String value) {
            addCriterion("brandCode not like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIn(List<String> values) {
            addCriterion("brandCode in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotIn(List<String> values) {
            addCriterion("brandCode not in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeBetween(String value1, String value2) {
            addCriterion("brandCode between", value1, value2, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotBetween(String value1, String value2) {
            addCriterion("brandCode not between", value1, value2, "brandCode");
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

        public Criteria andDealIsNull() {
            addCriterion("deal is null");
            return (Criteria) this;
        }

        public Criteria andDealIsNotNull() {
            addCriterion("deal is not null");
            return (Criteria) this;
        }

        public Criteria andDealEqualTo(String value) {
            addCriterion("deal =", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotEqualTo(String value) {
            addCriterion("deal <>", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealGreaterThan(String value) {
            addCriterion("deal >", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealGreaterThanOrEqualTo(String value) {
            addCriterion("deal >=", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealLessThan(String value) {
            addCriterion("deal <", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealLessThanOrEqualTo(String value) {
            addCriterion("deal <=", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealLike(String value) {
            addCriterion("deal like", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotLike(String value) {
            addCriterion("deal not like", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealIn(List<String> values) {
            addCriterion("deal in", values, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotIn(List<String> values) {
            addCriterion("deal not in", values, "deal");
            return (Criteria) this;
        }

        public Criteria andDealBetween(String value1, String value2) {
            addCriterion("deal between", value1, value2, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotBetween(String value1, String value2) {
            addCriterion("deal not between", value1, value2, "deal");
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