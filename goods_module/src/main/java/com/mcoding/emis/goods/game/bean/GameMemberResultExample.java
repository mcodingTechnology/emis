package com.mcoding.emis.goods.game.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mcoding.base.core.PageView;

public class GameMemberResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<GameMemberResult> pageView;

    public GameMemberResultExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public PageView<GameMemberResult> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<GameMemberResult> pageView) {
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

        public Criteria andRightnumIsNull() {
            addCriterion("rightNum is null");
            return (Criteria) this;
        }

        public Criteria andRightnumIsNotNull() {
            addCriterion("rightNum is not null");
            return (Criteria) this;
        }

        public Criteria andRightnumEqualTo(Integer value) {
            addCriterion("rightNum =", value, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumNotEqualTo(Integer value) {
            addCriterion("rightNum <>", value, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumGreaterThan(Integer value) {
            addCriterion("rightNum >", value, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rightNum >=", value, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumLessThan(Integer value) {
            addCriterion("rightNum <", value, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumLessThanOrEqualTo(Integer value) {
            addCriterion("rightNum <=", value, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumIn(List<Integer> values) {
            addCriterion("rightNum in", values, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumNotIn(List<Integer> values) {
            addCriterion("rightNum not in", values, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumBetween(Integer value1, Integer value2) {
            addCriterion("rightNum between", value1, value2, "rightnum");
            return (Criteria) this;
        }

        public Criteria andRightnumNotBetween(Integer value1, Integer value2) {
            addCriterion("rightNum not between", value1, value2, "rightnum");
            return (Criteria) this;
        }

        public Criteria andGaintitleIsNull() {
            addCriterion("gainTitle is null");
            return (Criteria) this;
        }

        public Criteria andGaintitleIsNotNull() {
            addCriterion("gainTitle is not null");
            return (Criteria) this;
        }

        public Criteria andGaintitleEqualTo(String value) {
            addCriterion("gainTitle =", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleNotEqualTo(String value) {
            addCriterion("gainTitle <>", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleGreaterThan(String value) {
            addCriterion("gainTitle >", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleGreaterThanOrEqualTo(String value) {
            addCriterion("gainTitle >=", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleLessThan(String value) {
            addCriterion("gainTitle <", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleLessThanOrEqualTo(String value) {
            addCriterion("gainTitle <=", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleLike(String value) {
            addCriterion("gainTitle like", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleNotLike(String value) {
            addCriterion("gainTitle not like", value, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleIn(List<String> values) {
            addCriterion("gainTitle in", values, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleNotIn(List<String> values) {
            addCriterion("gainTitle not in", values, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleBetween(String value1, String value2) {
            addCriterion("gainTitle between", value1, value2, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andGaintitleNotBetween(String value1, String value2) {
            addCriterion("gainTitle not between", value1, value2, "gaintitle");
            return (Criteria) this;
        }

        public Criteria andIslotteryIsNull() {
            addCriterion("isLottery is null");
            return (Criteria) this;
        }

        public Criteria andIslotteryIsNotNull() {
            addCriterion("isLottery is not null");
            return (Criteria) this;
        }

        public Criteria andIslotteryEqualTo(Integer value) {
            addCriterion("isLottery =", value, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryNotEqualTo(Integer value) {
            addCriterion("isLottery <>", value, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryGreaterThan(Integer value) {
            addCriterion("isLottery >", value, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryGreaterThanOrEqualTo(Integer value) {
            addCriterion("isLottery >=", value, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryLessThan(Integer value) {
            addCriterion("isLottery <", value, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryLessThanOrEqualTo(Integer value) {
            addCriterion("isLottery <=", value, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryIn(List<Integer> values) {
            addCriterion("isLottery in", values, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryNotIn(List<Integer> values) {
            addCriterion("isLottery not in", values, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryBetween(Integer value1, Integer value2) {
            addCriterion("isLottery between", value1, value2, "islottery");
            return (Criteria) this;
        }

        public Criteria andIslotteryNotBetween(Integer value1, Integer value2) {
            addCriterion("isLottery not between", value1, value2, "islottery");
            return (Criteria) this;
        }

        public Criteria andLotterynumIsNull() {
            addCriterion("lotteryNum is null");
            return (Criteria) this;
        }

        public Criteria andLotterynumIsNotNull() {
            addCriterion("lotteryNum is not null");
            return (Criteria) this;
        }

        public Criteria andLotterynumEqualTo(Integer value) {
            addCriterion("lotteryNum =", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumNotEqualTo(Integer value) {
            addCriterion("lotteryNum <>", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumGreaterThan(Integer value) {
            addCriterion("lotteryNum >", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumGreaterThanOrEqualTo(Integer value) {
            addCriterion("lotteryNum >=", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumLessThan(Integer value) {
            addCriterion("lotteryNum <", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumLessThanOrEqualTo(Integer value) {
            addCriterion("lotteryNum <=", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumIn(List<Integer> values) {
            addCriterion("lotteryNum in", values, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumNotIn(List<Integer> values) {
            addCriterion("lotteryNum not in", values, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumBetween(Integer value1, Integer value2) {
            addCriterion("lotteryNum between", value1, value2, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumNotBetween(Integer value1, Integer value2) {
            addCriterion("lotteryNum not between", value1, value2, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andPrizeidIsNull() {
            addCriterion("prizeId is null");
            return (Criteria) this;
        }

        public Criteria andPrizeidIsNotNull() {
            addCriterion("prizeId is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeidEqualTo(Integer value) {
            addCriterion("prizeId =", value, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidNotEqualTo(Integer value) {
            addCriterion("prizeId <>", value, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidGreaterThan(Integer value) {
            addCriterion("prizeId >", value, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("prizeId >=", value, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidLessThan(Integer value) {
            addCriterion("prizeId <", value, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidLessThanOrEqualTo(Integer value) {
            addCriterion("prizeId <=", value, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidIn(List<Integer> values) {
            addCriterion("prizeId in", values, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidNotIn(List<Integer> values) {
            addCriterion("prizeId not in", values, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidBetween(Integer value1, Integer value2) {
            addCriterion("prizeId between", value1, value2, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizeidNotBetween(Integer value1, Integer value2) {
            addCriterion("prizeId not between", value1, value2, "prizeid");
            return (Criteria) this;
        }

        public Criteria andPrizenameIsNull() {
            addCriterion("prizeName is null");
            return (Criteria) this;
        }

        public Criteria andPrizenameIsNotNull() {
            addCriterion("prizeName is not null");
            return (Criteria) this;
        }

        public Criteria andPrizenameEqualTo(String value) {
            addCriterion("prizeName =", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameNotEqualTo(String value) {
            addCriterion("prizeName <>", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameGreaterThan(String value) {
            addCriterion("prizeName >", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameGreaterThanOrEqualTo(String value) {
            addCriterion("prizeName >=", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameLessThan(String value) {
            addCriterion("prizeName <", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameLessThanOrEqualTo(String value) {
            addCriterion("prizeName <=", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameLike(String value) {
            addCriterion("prizeName like", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameNotLike(String value) {
            addCriterion("prizeName not like", value, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameIn(List<String> values) {
            addCriterion("prizeName in", values, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameNotIn(List<String> values) {
            addCriterion("prizeName not in", values, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameBetween(String value1, String value2) {
            addCriterion("prizeName between", value1, value2, "prizename");
            return (Criteria) this;
        }

        public Criteria andPrizenameNotBetween(String value1, String value2) {
            addCriterion("prizeName not between", value1, value2, "prizename");
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

        public Criteria andMemberaddressIsNull() {
            addCriterion("memberAddress is null");
            return (Criteria) this;
        }

        public Criteria andMemberaddressIsNotNull() {
            addCriterion("memberAddress is not null");
            return (Criteria) this;
        }

        public Criteria andMemberaddressEqualTo(String value) {
            addCriterion("memberAddress =", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressNotEqualTo(String value) {
            addCriterion("memberAddress <>", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressGreaterThan(String value) {
            addCriterion("memberAddress >", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressGreaterThanOrEqualTo(String value) {
            addCriterion("memberAddress >=", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressLessThan(String value) {
            addCriterion("memberAddress <", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressLessThanOrEqualTo(String value) {
            addCriterion("memberAddress <=", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressLike(String value) {
            addCriterion("memberAddress like", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressNotLike(String value) {
            addCriterion("memberAddress not like", value, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressIn(List<String> values) {
            addCriterion("memberAddress in", values, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressNotIn(List<String> values) {
            addCriterion("memberAddress not in", values, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressBetween(String value1, String value2) {
            addCriterion("memberAddress between", value1, value2, "memberaddress");
            return (Criteria) this;
        }

        public Criteria andMemberaddressNotBetween(String value1, String value2) {
            addCriterion("memberAddress not between", value1, value2, "memberaddress");
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

        public Criteria andMemberphoneIsNull() {
            addCriterion("memberPhone is null");
            return (Criteria) this;
        }

        public Criteria andMemberphoneIsNotNull() {
            addCriterion("memberPhone is not null");
            return (Criteria) this;
        }

        public Criteria andMemberphoneEqualTo(String value) {
            addCriterion("memberPhone =", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneNotEqualTo(String value) {
            addCriterion("memberPhone <>", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneGreaterThan(String value) {
            addCriterion("memberPhone >", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneGreaterThanOrEqualTo(String value) {
            addCriterion("memberPhone >=", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneLessThan(String value) {
            addCriterion("memberPhone <", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneLessThanOrEqualTo(String value) {
            addCriterion("memberPhone <=", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneLike(String value) {
            addCriterion("memberPhone like", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneNotLike(String value) {
            addCriterion("memberPhone not like", value, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneIn(List<String> values) {
            addCriterion("memberPhone in", values, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneNotIn(List<String> values) {
            addCriterion("memberPhone not in", values, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneBetween(String value1, String value2) {
            addCriterion("memberPhone between", value1, value2, "memberphone");
            return (Criteria) this;
        }

        public Criteria andMemberphoneNotBetween(String value1, String value2) {
            addCriterion("memberPhone not between", value1, value2, "memberphone");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
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