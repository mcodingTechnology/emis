package com.mcoding.emis.member.bean.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberExample implements IExample<Member> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<Member> pageView;

    public MemberExample() {
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
    public PageView<Member> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<Member> pageView) {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("memberId is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("memberId is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("memberId =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("memberId <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("memberId >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("memberId >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("memberId <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("memberId <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("memberId in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("memberId not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("memberId between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("memberId not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdIsNull() {
            addCriterion("parentMemberId is null");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdIsNotNull() {
            addCriterion("parentMemberId is not null");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdEqualTo(Integer value) {
            addCriterion("parentMemberId =", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdNotEqualTo(Integer value) {
            addCriterion("parentMemberId <>", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdGreaterThan(Integer value) {
            addCriterion("parentMemberId >", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentMemberId >=", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdLessThan(Integer value) {
            addCriterion("parentMemberId <", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("parentMemberId <=", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdIn(List<Integer> values) {
            addCriterion("parentMemberId in", values, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdNotIn(List<Integer> values) {
            addCriterion("parentMemberId not in", values, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("parentMemberId between", value1, value2, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parentMemberId not between", value1, value2, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNull() {
            addCriterion("levelName is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("levelName is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("levelName =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("levelName <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("levelName >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("levelName >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("levelName <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("levelName <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("levelName like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("levelName not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("levelName in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("levelName not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("levelName between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("levelName not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNull() {
            addCriterion("levelId is null");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNotNull() {
            addCriterion("levelId is not null");
            return (Criteria) this;
        }

        public Criteria andLevelIdEqualTo(Integer value) {
            addCriterion("levelId =", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotEqualTo(Integer value) {
            addCriterion("levelId <>", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThan(Integer value) {
            addCriterion("levelId >", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("levelId >=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThan(Integer value) {
            addCriterion("levelId <", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("levelId <=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIn(List<Integer> values) {
            addCriterion("levelId in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotIn(List<Integer> values) {
            addCriterion("levelId not in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("levelId between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("levelId not between", value1, value2, "levelId");
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

        public Criteria andChannelsIdIsNull() {
            addCriterion("channelsId is null");
            return (Criteria) this;
        }

        public Criteria andChannelsIdIsNotNull() {
            addCriterion("channelsId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelsIdEqualTo(Integer value) {
            addCriterion("channelsId =", value, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdNotEqualTo(Integer value) {
            addCriterion("channelsId <>", value, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdGreaterThan(Integer value) {
            addCriterion("channelsId >", value, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("channelsId >=", value, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdLessThan(Integer value) {
            addCriterion("channelsId <", value, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdLessThanOrEqualTo(Integer value) {
            addCriterion("channelsId <=", value, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdIn(List<Integer> values) {
            addCriterion("channelsId in", values, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdNotIn(List<Integer> values) {
            addCriterion("channelsId not in", values, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdBetween(Integer value1, Integer value2) {
            addCriterion("channelsId between", value1, value2, "channelsId");
            return (Criteria) this;
        }

        public Criteria andChannelsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("channelsId not between", value1, value2, "channelsId");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIsNull() {
            addCriterion("memberType is null");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIsNotNull() {
            addCriterion("memberType is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTypeEqualTo(String value) {
            addCriterion("memberType =", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotEqualTo(String value) {
            addCriterion("memberType <>", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeGreaterThan(String value) {
            addCriterion("memberType >", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeGreaterThanOrEqualTo(String value) {
            addCriterion("memberType >=", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLessThan(String value) {
            addCriterion("memberType <", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLessThanOrEqualTo(String value) {
            addCriterion("memberType <=", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLike(String value) {
            addCriterion("memberType like", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotLike(String value) {
            addCriterion("memberType not like", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIn(List<String> values) {
            addCriterion("memberType in", values, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotIn(List<String> values) {
            addCriterion("memberType not in", values, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeBetween(String value1, String value2) {
            addCriterion("memberType between", value1, value2, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotBetween(String value1, String value2) {
            addCriterion("memberType not between", value1, value2, "memberType");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceIsNull() {
            addCriterion("regionProvince is null");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceIsNotNull() {
            addCriterion("regionProvince is not null");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceEqualTo(String value) {
            addCriterion("regionProvince =", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotEqualTo(String value) {
            addCriterion("regionProvince <>", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceGreaterThan(String value) {
            addCriterion("regionProvince >", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("regionProvince >=", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceLessThan(String value) {
            addCriterion("regionProvince <", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceLessThanOrEqualTo(String value) {
            addCriterion("regionProvince <=", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceLike(String value) {
            addCriterion("regionProvince like", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotLike(String value) {
            addCriterion("regionProvince not like", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceIn(List<String> values) {
            addCriterion("regionProvince in", values, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotIn(List<String> values) {
            addCriterion("regionProvince not in", values, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceBetween(String value1, String value2) {
            addCriterion("regionProvince between", value1, value2, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotBetween(String value1, String value2) {
            addCriterion("regionProvince not between", value1, value2, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionCityIsNull() {
            addCriterion("regionCity is null");
            return (Criteria) this;
        }

        public Criteria andRegionCityIsNotNull() {
            addCriterion("regionCity is not null");
            return (Criteria) this;
        }

        public Criteria andRegionCityEqualTo(String value) {
            addCriterion("regionCity =", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotEqualTo(String value) {
            addCriterion("regionCity <>", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityGreaterThan(String value) {
            addCriterion("regionCity >", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityGreaterThanOrEqualTo(String value) {
            addCriterion("regionCity >=", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityLessThan(String value) {
            addCriterion("regionCity <", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityLessThanOrEqualTo(String value) {
            addCriterion("regionCity <=", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityLike(String value) {
            addCriterion("regionCity like", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotLike(String value) {
            addCriterion("regionCity not like", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityIn(List<String> values) {
            addCriterion("regionCity in", values, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotIn(List<String> values) {
            addCriterion("regionCity not in", values, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityBetween(String value1, String value2) {
            addCriterion("regionCity between", value1, value2, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotBetween(String value1, String value2) {
            addCriterion("regionCity not between", value1, value2, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionAreaIsNull() {
            addCriterion("regionArea is null");
            return (Criteria) this;
        }

        public Criteria andRegionAreaIsNotNull() {
            addCriterion("regionArea is not null");
            return (Criteria) this;
        }

        public Criteria andRegionAreaEqualTo(String value) {
            addCriterion("regionArea =", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotEqualTo(String value) {
            addCriterion("regionArea <>", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaGreaterThan(String value) {
            addCriterion("regionArea >", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaGreaterThanOrEqualTo(String value) {
            addCriterion("regionArea >=", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaLessThan(String value) {
            addCriterion("regionArea <", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaLessThanOrEqualTo(String value) {
            addCriterion("regionArea <=", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaLike(String value) {
            addCriterion("regionArea like", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotLike(String value) {
            addCriterion("regionArea not like", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaIn(List<String> values) {
            addCriterion("regionArea in", values, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotIn(List<String> values) {
            addCriterion("regionArea not in", values, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaBetween(String value1, String value2) {
            addCriterion("regionArea between", value1, value2, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotBetween(String value1, String value2) {
            addCriterion("regionArea not between", value1, value2, "regionArea");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelIsNull() {
            addCriterion("enrollChannel is null");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelIsNotNull() {
            addCriterion("enrollChannel is not null");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelEqualTo(String value) {
            addCriterion("enrollChannel =", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelNotEqualTo(String value) {
            addCriterion("enrollChannel <>", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelGreaterThan(String value) {
            addCriterion("enrollChannel >", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelGreaterThanOrEqualTo(String value) {
            addCriterion("enrollChannel >=", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelLessThan(String value) {
            addCriterion("enrollChannel <", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelLessThanOrEqualTo(String value) {
            addCriterion("enrollChannel <=", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelLike(String value) {
            addCriterion("enrollChannel like", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelNotLike(String value) {
            addCriterion("enrollChannel not like", value, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelIn(List<String> values) {
            addCriterion("enrollChannel in", values, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelNotIn(List<String> values) {
            addCriterion("enrollChannel not in", values, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelBetween(String value1, String value2) {
            addCriterion("enrollChannel between", value1, value2, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andEnrollChannelNotBetween(String value1, String value2) {
            addCriterion("enrollChannel not between", value1, value2, "enrollChannel");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdIsNull() {
            addCriterion("governmentId is null");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdIsNotNull() {
            addCriterion("governmentId is not null");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdEqualTo(String value) {
            addCriterion("governmentId =", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdNotEqualTo(String value) {
            addCriterion("governmentId <>", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdGreaterThan(String value) {
            addCriterion("governmentId >", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("governmentId >=", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdLessThan(String value) {
            addCriterion("governmentId <", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdLessThanOrEqualTo(String value) {
            addCriterion("governmentId <=", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdLike(String value) {
            addCriterion("governmentId like", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdNotLike(String value) {
            addCriterion("governmentId not like", value, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdIn(List<String> values) {
            addCriterion("governmentId in", values, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdNotIn(List<String> values) {
            addCriterion("governmentId not in", values, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdBetween(String value1, String value2) {
            addCriterion("governmentId between", value1, value2, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGovernmentIdNotBetween(String value1, String value2) {
            addCriterion("governmentId not between", value1, value2, "governmentId");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNull() {
            addCriterion("deliveryAddress is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNotNull() {
            addCriterion("deliveryAddress is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressEqualTo(String value) {
            addCriterion("deliveryAddress =", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotEqualTo(String value) {
            addCriterion("deliveryAddress <>", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThan(String value) {
            addCriterion("deliveryAddress >", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryAddress >=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThan(String value) {
            addCriterion("deliveryAddress <", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThanOrEqualTo(String value) {
            addCriterion("deliveryAddress <=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLike(String value) {
            addCriterion("deliveryAddress like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotLike(String value) {
            addCriterion("deliveryAddress not like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIn(List<String> values) {
            addCriterion("deliveryAddress in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotIn(List<String> values) {
            addCriterion("deliveryAddress not in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressBetween(String value1, String value2) {
            addCriterion("deliveryAddress between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotBetween(String value1, String value2) {
            addCriterion("deliveryAddress not between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("zipCode is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("zipCode is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("zipCode =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("zipCode <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("zipCode >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("zipCode >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("zipCode <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("zipCode <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("zipCode like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("zipCode not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("zipCode in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("zipCode not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("zipCode between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("zipCode not between", value1, value2, "zipCode");
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

        public Criteria andAccessTokenIsNull() {
            addCriterion("access_token is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("access_token is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("access_token =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("access_token <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("access_token >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("access_token >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("access_token <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("access_token <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("access_token like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("access_token not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("access_token in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("access_token not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("access_token between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("access_token not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andConcernsIsNull() {
            addCriterion("concerns is null");
            return (Criteria) this;
        }

        public Criteria andConcernsIsNotNull() {
            addCriterion("concerns is not null");
            return (Criteria) this;
        }

        public Criteria andConcernsEqualTo(String value) {
            addCriterion("concerns =", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsNotEqualTo(String value) {
            addCriterion("concerns <>", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsGreaterThan(String value) {
            addCriterion("concerns >", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsGreaterThanOrEqualTo(String value) {
            addCriterion("concerns >=", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsLessThan(String value) {
            addCriterion("concerns <", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsLessThanOrEqualTo(String value) {
            addCriterion("concerns <=", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsLike(String value) {
            addCriterion("concerns like", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsNotLike(String value) {
            addCriterion("concerns not like", value, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsIn(List<String> values) {
            addCriterion("concerns in", values, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsNotIn(List<String> values) {
            addCriterion("concerns not in", values, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsBetween(String value1, String value2) {
            addCriterion("concerns between", value1, value2, "concerns");
            return (Criteria) this;
        }

        public Criteria andConcernsNotBetween(String value1, String value2) {
            addCriterion("concerns not between", value1, value2, "concerns");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonIsNull() {
            addCriterion("concernsPerson is null");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonIsNotNull() {
            addCriterion("concernsPerson is not null");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonEqualTo(String value) {
            addCriterion("concernsPerson =", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonNotEqualTo(String value) {
            addCriterion("concernsPerson <>", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonGreaterThan(String value) {
            addCriterion("concernsPerson >", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonGreaterThanOrEqualTo(String value) {
            addCriterion("concernsPerson >=", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonLessThan(String value) {
            addCriterion("concernsPerson <", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonLessThanOrEqualTo(String value) {
            addCriterion("concernsPerson <=", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonLike(String value) {
            addCriterion("concernsPerson like", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonNotLike(String value) {
            addCriterion("concernsPerson not like", value, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonIn(List<String> values) {
            addCriterion("concernsPerson in", values, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonNotIn(List<String> values) {
            addCriterion("concernsPerson not in", values, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonBetween(String value1, String value2) {
            addCriterion("concernsPerson between", value1, value2, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andConcernsPersonNotBetween(String value1, String value2) {
            addCriterion("concernsPerson not between", value1, value2, "concernsPerson");
            return (Criteria) this;
        }

        public Criteria andHealthProblemIsNull() {
            addCriterion("healthProblem is null");
            return (Criteria) this;
        }

        public Criteria andHealthProblemIsNotNull() {
            addCriterion("healthProblem is not null");
            return (Criteria) this;
        }

        public Criteria andHealthProblemEqualTo(String value) {
            addCriterion("healthProblem =", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemNotEqualTo(String value) {
            addCriterion("healthProblem <>", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemGreaterThan(String value) {
            addCriterion("healthProblem >", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemGreaterThanOrEqualTo(String value) {
            addCriterion("healthProblem >=", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemLessThan(String value) {
            addCriterion("healthProblem <", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemLessThanOrEqualTo(String value) {
            addCriterion("healthProblem <=", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemLike(String value) {
            addCriterion("healthProblem like", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemNotLike(String value) {
            addCriterion("healthProblem not like", value, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemIn(List<String> values) {
            addCriterion("healthProblem in", values, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemNotIn(List<String> values) {
            addCriterion("healthProblem not in", values, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemBetween(String value1, String value2) {
            addCriterion("healthProblem between", value1, value2, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andHealthProblemNotBetween(String value1, String value2) {
            addCriterion("healthProblem not between", value1, value2, "healthProblem");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomIsNull() {
            addCriterion("activitySymptom is null");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomIsNotNull() {
            addCriterion("activitySymptom is not null");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomEqualTo(String value) {
            addCriterion("activitySymptom =", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomNotEqualTo(String value) {
            addCriterion("activitySymptom <>", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomGreaterThan(String value) {
            addCriterion("activitySymptom >", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomGreaterThanOrEqualTo(String value) {
            addCriterion("activitySymptom >=", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomLessThan(String value) {
            addCriterion("activitySymptom <", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomLessThanOrEqualTo(String value) {
            addCriterion("activitySymptom <=", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomLike(String value) {
            addCriterion("activitySymptom like", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomNotLike(String value) {
            addCriterion("activitySymptom not like", value, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomIn(List<String> values) {
            addCriterion("activitySymptom in", values, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomNotIn(List<String> values) {
            addCriterion("activitySymptom not in", values, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomBetween(String value1, String value2) {
            addCriterion("activitySymptom between", value1, value2, "activitySymptom");
            return (Criteria) this;
        }

        public Criteria andActivitySymptomNotBetween(String value1, String value2) {
            addCriterion("activitySymptom not between", value1, value2, "activitySymptom");
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

        public Criteria andPointSumIsNull() {
            addCriterion("pointSum is null");
            return (Criteria) this;
        }

        public Criteria andPointSumIsNotNull() {
            addCriterion("pointSum is not null");
            return (Criteria) this;
        }

        public Criteria andPointSumEqualTo(Integer value) {
            addCriterion("pointSum =", value, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumNotEqualTo(Integer value) {
            addCriterion("pointSum <>", value, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumGreaterThan(Integer value) {
            addCriterion("pointSum >", value, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pointSum >=", value, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumLessThan(Integer value) {
            addCriterion("pointSum <", value, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumLessThanOrEqualTo(Integer value) {
            addCriterion("pointSum <=", value, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumIn(List<Integer> values) {
            addCriterion("pointSum in", values, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumNotIn(List<Integer> values) {
            addCriterion("pointSum not in", values, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumBetween(Integer value1, Integer value2) {
            addCriterion("pointSum between", value1, value2, "pointSum");
            return (Criteria) this;
        }

        public Criteria andPointSumNotBetween(Integer value1, Integer value2) {
            addCriterion("pointSum not between", value1, value2, "pointSum");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("companyId is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("companyId is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("companyId =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("companyId <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("companyId >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("companyId >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("companyId <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("companyId <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("companyId in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("companyId not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("companyId between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("companyId not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckIsNull() {
            addCriterion("isHealthCheck is null");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckIsNotNull() {
            addCriterion("isHealthCheck is not null");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckEqualTo(Byte value) {
            addCriterion("isHealthCheck =", value, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckNotEqualTo(Byte value) {
            addCriterion("isHealthCheck <>", value, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckGreaterThan(Byte value) {
            addCriterion("isHealthCheck >", value, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckGreaterThanOrEqualTo(Byte value) {
            addCriterion("isHealthCheck >=", value, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckLessThan(Byte value) {
            addCriterion("isHealthCheck <", value, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckLessThanOrEqualTo(Byte value) {
            addCriterion("isHealthCheck <=", value, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckIn(List<Byte> values) {
            addCriterion("isHealthCheck in", values, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckNotIn(List<Byte> values) {
            addCriterion("isHealthCheck not in", values, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckBetween(Byte value1, Byte value2) {
            addCriterion("isHealthCheck between", value1, value2, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsHealthCheckNotBetween(Byte value1, Byte value2) {
            addCriterion("isHealthCheck not between", value1, value2, "isHealthCheck");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeIsNull() {
            addCriterion("isReferralNotice is null");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeIsNotNull() {
            addCriterion("isReferralNotice is not null");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeEqualTo(Byte value) {
            addCriterion("isReferralNotice =", value, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeNotEqualTo(Byte value) {
            addCriterion("isReferralNotice <>", value, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeGreaterThan(Byte value) {
            addCriterion("isReferralNotice >", value, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeGreaterThanOrEqualTo(Byte value) {
            addCriterion("isReferralNotice >=", value, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeLessThan(Byte value) {
            addCriterion("isReferralNotice <", value, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeLessThanOrEqualTo(Byte value) {
            addCriterion("isReferralNotice <=", value, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeIn(List<Byte> values) {
            addCriterion("isReferralNotice in", values, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeNotIn(List<Byte> values) {
            addCriterion("isReferralNotice not in", values, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeBetween(Byte value1, Byte value2) {
            addCriterion("isReferralNotice between", value1, value2, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andIsReferralNoticeNotBetween(Byte value1, Byte value2) {
            addCriterion("isReferralNotice not between", value1, value2, "isReferralNotice");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeIsNull() {
            addCriterion("exerciseType is null");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeIsNotNull() {
            addCriterion("exerciseType is not null");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeEqualTo(Integer value) {
            addCriterion("exerciseType =", value, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeNotEqualTo(Integer value) {
            addCriterion("exerciseType <>", value, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeGreaterThan(Integer value) {
            addCriterion("exerciseType >", value, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exerciseType >=", value, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeLessThan(Integer value) {
            addCriterion("exerciseType <", value, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("exerciseType <=", value, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeIn(List<Integer> values) {
            addCriterion("exerciseType in", values, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeNotIn(List<Integer> values) {
            addCriterion("exerciseType not in", values, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeBetween(Integer value1, Integer value2) {
            addCriterion("exerciseType between", value1, value2, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andExerciseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("exerciseType not between", value1, value2, "exerciseType");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nickName is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nickName is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nickName =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nickName <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nickName >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nickName >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nickName <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nickName <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nickName like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nickName not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nickName in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nickName not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nickName between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nickName not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIsNull() {
            addCriterion("headimgurl is null");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIsNotNull() {
            addCriterion("headimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlEqualTo(String value) {
            addCriterion("headimgurl =", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotEqualTo(String value) {
            addCriterion("headimgurl <>", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlGreaterThan(String value) {
            addCriterion("headimgurl >", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("headimgurl >=", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLessThan(String value) {
            addCriterion("headimgurl <", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLessThanOrEqualTo(String value) {
            addCriterion("headimgurl <=", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLike(String value) {
            addCriterion("headimgurl like", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotLike(String value) {
            addCriterion("headimgurl not like", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIn(List<String> values) {
            addCriterion("headimgurl in", values, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotIn(List<String> values) {
            addCriterion("headimgurl not in", values, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlBetween(String value1, String value2) {
            addCriterion("headimgurl between", value1, value2, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotBetween(String value1, String value2) {
            addCriterion("headimgurl not between", value1, value2, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
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