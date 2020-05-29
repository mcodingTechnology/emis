package com.mcoding.emis.member.bean.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberJoinRecordExample implements IExample<MemberJoinRecord> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<MemberJoinRecord> pageView;

    public MemberJoinRecordExample() {
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
    public PageView<MemberJoinRecord> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<MemberJoinRecord> pageView) {
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

        public Criteria andMemberidIsNull() {
            addCriterion("memberId is null");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNotNull() {
            addCriterion("memberId is not null");
            return (Criteria) this;
        }

        public Criteria andMemberidEqualTo(Integer value) {
            addCriterion("memberId =", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotEqualTo(Integer value) {
            addCriterion("memberId <>", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThan(Integer value) {
            addCriterion("memberId >", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThanOrEqualTo(Integer value) {
            addCriterion("memberId >=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThan(Integer value) {
            addCriterion("memberId <", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThanOrEqualTo(Integer value) {
            addCriterion("memberId <=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidIn(List<Integer> values) {
            addCriterion("memberId in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotIn(List<Integer> values) {
            addCriterion("memberId not in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidBetween(Integer value1, Integer value2) {
            addCriterion("memberId between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotBetween(Integer value1, Integer value2) {
            addCriterion("memberId not between", value1, value2, "memberid");
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

        public Criteria andLeveidIsNull() {
            addCriterion("leveId is null");
            return (Criteria) this;
        }

        public Criteria andLeveidIsNotNull() {
            addCriterion("leveId is not null");
            return (Criteria) this;
        }

        public Criteria andLeveidEqualTo(Integer value) {
            addCriterion("leveId =", value, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidNotEqualTo(Integer value) {
            addCriterion("leveId <>", value, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidGreaterThan(Integer value) {
            addCriterion("leveId >", value, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidGreaterThanOrEqualTo(Integer value) {
            addCriterion("leveId >=", value, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidLessThan(Integer value) {
            addCriterion("leveId <", value, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidLessThanOrEqualTo(Integer value) {
            addCriterion("leveId <=", value, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidIn(List<Integer> values) {
            addCriterion("leveId in", values, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidNotIn(List<Integer> values) {
            addCriterion("leveId not in", values, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidBetween(Integer value1, Integer value2) {
            addCriterion("leveId between", value1, value2, "leveid");
            return (Criteria) this;
        }

        public Criteria andLeveidNotBetween(Integer value1, Integer value2) {
            addCriterion("leveId not between", value1, value2, "leveid");
            return (Criteria) this;
        }

        public Criteria andLevelnameIsNull() {
            addCriterion("levelName is null");
            return (Criteria) this;
        }

        public Criteria andLevelnameIsNotNull() {
            addCriterion("levelName is not null");
            return (Criteria) this;
        }

        public Criteria andLevelnameEqualTo(String value) {
            addCriterion("levelName =", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotEqualTo(String value) {
            addCriterion("levelName <>", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameGreaterThan(String value) {
            addCriterion("levelName >", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameGreaterThanOrEqualTo(String value) {
            addCriterion("levelName >=", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameLessThan(String value) {
            addCriterion("levelName <", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameLessThanOrEqualTo(String value) {
            addCriterion("levelName <=", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameLike(String value) {
            addCriterion("levelName like", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotLike(String value) {
            addCriterion("levelName not like", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameIn(List<String> values) {
            addCriterion("levelName in", values, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotIn(List<String> values) {
            addCriterion("levelName not in", values, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameBetween(String value1, String value2) {
            addCriterion("levelName between", value1, value2, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotBetween(String value1, String value2) {
            addCriterion("levelName not between", value1, value2, "levelname");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentId is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentId is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("parentId =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("parentId <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("parentId >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentId >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("parentId <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("parentId <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("parentId in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("parentId not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("parentId between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("parentId not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentnameIsNull() {
            addCriterion("parentName is null");
            return (Criteria) this;
        }

        public Criteria andParentnameIsNotNull() {
            addCriterion("parentName is not null");
            return (Criteria) this;
        }

        public Criteria andParentnameEqualTo(String value) {
            addCriterion("parentName =", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotEqualTo(String value) {
            addCriterion("parentName <>", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameGreaterThan(String value) {
            addCriterion("parentName >", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameGreaterThanOrEqualTo(String value) {
            addCriterion("parentName >=", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLessThan(String value) {
            addCriterion("parentName <", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLessThanOrEqualTo(String value) {
            addCriterion("parentName <=", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLike(String value) {
            addCriterion("parentName like", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotLike(String value) {
            addCriterion("parentName not like", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameIn(List<String> values) {
            addCriterion("parentName in", values, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotIn(List<String> values) {
            addCriterion("parentName not in", values, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameBetween(String value1, String value2) {
            addCriterion("parentName between", value1, value2, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotBetween(String value1, String value2) {
            addCriterion("parentName not between", value1, value2, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentlevelidIsNull() {
            addCriterion("parentLevelId is null");
            return (Criteria) this;
        }

        public Criteria andParentlevelidIsNotNull() {
            addCriterion("parentLevelId is not null");
            return (Criteria) this;
        }

        public Criteria andParentlevelidEqualTo(Integer value) {
            addCriterion("parentLevelId =", value, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidNotEqualTo(Integer value) {
            addCriterion("parentLevelId <>", value, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidGreaterThan(Integer value) {
            addCriterion("parentLevelId >", value, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentLevelId >=", value, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidLessThan(Integer value) {
            addCriterion("parentLevelId <", value, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidLessThanOrEqualTo(Integer value) {
            addCriterion("parentLevelId <=", value, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidIn(List<Integer> values) {
            addCriterion("parentLevelId in", values, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidNotIn(List<Integer> values) {
            addCriterion("parentLevelId not in", values, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidBetween(Integer value1, Integer value2) {
            addCriterion("parentLevelId between", value1, value2, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelidNotBetween(Integer value1, Integer value2) {
            addCriterion("parentLevelId not between", value1, value2, "parentlevelid");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameIsNull() {
            addCriterion("parentLevelName is null");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameIsNotNull() {
            addCriterion("parentLevelName is not null");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameEqualTo(String value) {
            addCriterion("parentLevelName =", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameNotEqualTo(String value) {
            addCriterion("parentLevelName <>", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameGreaterThan(String value) {
            addCriterion("parentLevelName >", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameGreaterThanOrEqualTo(String value) {
            addCriterion("parentLevelName >=", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameLessThan(String value) {
            addCriterion("parentLevelName <", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameLessThanOrEqualTo(String value) {
            addCriterion("parentLevelName <=", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameLike(String value) {
            addCriterion("parentLevelName like", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameNotLike(String value) {
            addCriterion("parentLevelName not like", value, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameIn(List<String> values) {
            addCriterion("parentLevelName in", values, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameNotIn(List<String> values) {
            addCriterion("parentLevelName not in", values, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameBetween(String value1, String value2) {
            addCriterion("parentLevelName between", value1, value2, "parentlevelname");
            return (Criteria) this;
        }

        public Criteria andParentlevelnameNotBetween(String value1, String value2) {
            addCriterion("parentLevelName not between", value1, value2, "parentlevelname");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andConfirmtimeIsNull() {
            addCriterion("confirmtime is null");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeIsNotNull() {
            addCriterion("confirmtime is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeEqualTo(Date value) {
            addCriterion("confirmtime =", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeNotEqualTo(Date value) {
            addCriterion("confirmtime <>", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeGreaterThan(Date value) {
            addCriterion("confirmtime >", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirmtime >=", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeLessThan(Date value) {
            addCriterion("confirmtime <", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeLessThanOrEqualTo(Date value) {
            addCriterion("confirmtime <=", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeIn(List<Date> values) {
            addCriterion("confirmtime in", values, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeNotIn(List<Date> values) {
            addCriterion("confirmtime not in", values, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeBetween(Date value1, Date value2) {
            addCriterion("confirmtime between", value1, value2, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeNotBetween(Date value1, Date value2) {
            addCriterion("confirmtime not between", value1, value2, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNull() {
            addCriterion("brand_code is null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNotNull() {
            addCriterion("brand_code is not null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeEqualTo(String value) {
            addCriterion("brand_code =", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotEqualTo(String value) {
            addCriterion("brand_code <>", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThan(String value) {
            addCriterion("brand_code >", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThanOrEqualTo(String value) {
            addCriterion("brand_code >=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThan(String value) {
            addCriterion("brand_code <", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThanOrEqualTo(String value) {
            addCriterion("brand_code <=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLike(String value) {
            addCriterion("brand_code like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotLike(String value) {
            addCriterion("brand_code not like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIn(List<String> values) {
            addCriterion("brand_code in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotIn(List<String> values) {
            addCriterion("brand_code not in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeBetween(String value1, String value2) {
            addCriterion("brand_code between", value1, value2, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotBetween(String value1, String value2) {
            addCriterion("brand_code not between", value1, value2, "brandCode");
            return (Criteria) this;
        }

        public Criteria andRegsonIsNull() {
            addCriterion("regson is null");
            return (Criteria) this;
        }

        public Criteria andRegsonIsNotNull() {
            addCriterion("regson is not null");
            return (Criteria) this;
        }

        public Criteria andRegsonEqualTo(String value) {
            addCriterion("regson =", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotEqualTo(String value) {
            addCriterion("regson <>", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonGreaterThan(String value) {
            addCriterion("regson >", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonGreaterThanOrEqualTo(String value) {
            addCriterion("regson >=", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonLessThan(String value) {
            addCriterion("regson <", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonLessThanOrEqualTo(String value) {
            addCriterion("regson <=", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonLike(String value) {
            addCriterion("regson like", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotLike(String value) {
            addCriterion("regson not like", value, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonIn(List<String> values) {
            addCriterion("regson in", values, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotIn(List<String> values) {
            addCriterion("regson not in", values, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonBetween(String value1, String value2) {
            addCriterion("regson between", value1, value2, "regson");
            return (Criteria) this;
        }

        public Criteria andRegsonNotBetween(String value1, String value2) {
            addCriterion("regson not between", value1, value2, "regson");
            return (Criteria) this;
        }

        public Criteria andGymroomIsNull() {
            addCriterion("gymRoom is null");
            return (Criteria) this;
        }

        public Criteria andGymroomIsNotNull() {
            addCriterion("gymRoom is not null");
            return (Criteria) this;
        }

        public Criteria andGymroomEqualTo(String value) {
            addCriterion("gymRoom =", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomNotEqualTo(String value) {
            addCriterion("gymRoom <>", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomGreaterThan(String value) {
            addCriterion("gymRoom >", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomGreaterThanOrEqualTo(String value) {
            addCriterion("gymRoom >=", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomLessThan(String value) {
            addCriterion("gymRoom <", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomLessThanOrEqualTo(String value) {
            addCriterion("gymRoom <=", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomLike(String value) {
            addCriterion("gymRoom like", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomNotLike(String value) {
            addCriterion("gymRoom not like", value, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomIn(List<String> values) {
            addCriterion("gymRoom in", values, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomNotIn(List<String> values) {
            addCriterion("gymRoom not in", values, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomBetween(String value1, String value2) {
            addCriterion("gymRoom between", value1, value2, "gymroom");
            return (Criteria) this;
        }

        public Criteria andGymroomNotBetween(String value1, String value2) {
            addCriterion("gymRoom not between", value1, value2, "gymroom");
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