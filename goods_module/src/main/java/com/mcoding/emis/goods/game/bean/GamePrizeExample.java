package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GamePrizeExample implements Serializable{
	private static final long serialVersionUID = 1L;
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GamePrizeExample() {
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

        public Criteria andPrizeimgIsNull() {
            addCriterion("prizeImg is null");
            return (Criteria) this;
        }

        public Criteria andPrizeimgIsNotNull() {
            addCriterion("prizeImg is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeimgEqualTo(String value) {
            addCriterion("prizeImg =", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgNotEqualTo(String value) {
            addCriterion("prizeImg <>", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgGreaterThan(String value) {
            addCriterion("prizeImg >", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgGreaterThanOrEqualTo(String value) {
            addCriterion("prizeImg >=", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgLessThan(String value) {
            addCriterion("prizeImg <", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgLessThanOrEqualTo(String value) {
            addCriterion("prizeImg <=", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgLike(String value) {
            addCriterion("prizeImg like", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgNotLike(String value) {
            addCriterion("prizeImg not like", value, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgIn(List<String> values) {
            addCriterion("prizeImg in", values, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgNotIn(List<String> values) {
            addCriterion("prizeImg not in", values, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgBetween(String value1, String value2) {
            addCriterion("prizeImg between", value1, value2, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizeimgNotBetween(String value1, String value2) {
            addCriterion("prizeImg not between", value1, value2, "prizeimg");
            return (Criteria) this;
        }

        public Criteria andPrizelevelIsNull() {
            addCriterion("prizeLevel is null");
            return (Criteria) this;
        }

        public Criteria andPrizelevelIsNotNull() {
            addCriterion("prizeLevel is not null");
            return (Criteria) this;
        }

        public Criteria andPrizelevelEqualTo(Integer value) {
            addCriterion("prizeLevel =", value, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelNotEqualTo(Integer value) {
            addCriterion("prizeLevel <>", value, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelGreaterThan(Integer value) {
            addCriterion("prizeLevel >", value, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("prizeLevel >=", value, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelLessThan(Integer value) {
            addCriterion("prizeLevel <", value, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelLessThanOrEqualTo(Integer value) {
            addCriterion("prizeLevel <=", value, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelIn(List<Integer> values) {
            addCriterion("prizeLevel in", values, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelNotIn(List<Integer> values) {
            addCriterion("prizeLevel not in", values, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelBetween(Integer value1, Integer value2) {
            addCriterion("prizeLevel between", value1, value2, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andPrizelevelNotBetween(Integer value1, Integer value2) {
            addCriterion("prizeLevel not between", value1, value2, "prizelevel");
            return (Criteria) this;
        }

        public Criteria andLotterypercentIsNull() {
            addCriterion("lotteryPercent is null");
            return (Criteria) this;
        }

        public Criteria andLotterypercentIsNotNull() {
            addCriterion("lotteryPercent is not null");
            return (Criteria) this;
        }

        public Criteria andLotterypercentEqualTo(Integer value) {
            addCriterion("lotteryPercent =", value, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentNotEqualTo(Integer value) {
            addCriterion("lotteryPercent <>", value, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentGreaterThan(Integer value) {
            addCriterion("lotteryPercent >", value, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("lotteryPercent >=", value, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentLessThan(Integer value) {
            addCriterion("lotteryPercent <", value, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentLessThanOrEqualTo(Integer value) {
            addCriterion("lotteryPercent <=", value, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentIn(List<Integer> values) {
            addCriterion("lotteryPercent in", values, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentNotIn(List<Integer> values) {
            addCriterion("lotteryPercent not in", values, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentBetween(Integer value1, Integer value2) {
            addCriterion("lotteryPercent between", value1, value2, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andLotterypercentNotBetween(Integer value1, Integer value2) {
            addCriterion("lotteryPercent not between", value1, value2, "lotterypercent");
            return (Criteria) this;
        }

        public Criteria andPrizetypeIsNull() {
            addCriterion("prizeType is null");
            return (Criteria) this;
        }

        public Criteria andPrizetypeIsNotNull() {
            addCriterion("prizeType is not null");
            return (Criteria) this;
        }

        public Criteria andPrizetypeEqualTo(Integer value) {
            addCriterion("prizeType =", value, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeNotEqualTo(Integer value) {
            addCriterion("prizeType <>", value, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeGreaterThan(Integer value) {
            addCriterion("prizeType >", value, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("prizeType >=", value, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeLessThan(Integer value) {
            addCriterion("prizeType <", value, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeLessThanOrEqualTo(Integer value) {
            addCriterion("prizeType <=", value, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeIn(List<Integer> values) {
            addCriterion("prizeType in", values, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeNotIn(List<Integer> values) {
            addCriterion("prizeType not in", values, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeBetween(Integer value1, Integer value2) {
            addCriterion("prizeType between", value1, value2, "prizetype");
            return (Criteria) this;
        }

        public Criteria andPrizetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("prizeType not between", value1, value2, "prizetype");
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

        public Criteria andGainedprizenumIsNull() {
            addCriterion("gainedPrizeNum is null");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumIsNotNull() {
            addCriterion("gainedPrizeNum is not null");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumEqualTo(Integer value) {
            addCriterion("gainedPrizeNum =", value, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumNotEqualTo(Integer value) {
            addCriterion("gainedPrizeNum <>", value, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumGreaterThan(Integer value) {
            addCriterion("gainedPrizeNum >", value, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("gainedPrizeNum >=", value, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumLessThan(Integer value) {
            addCriterion("gainedPrizeNum <", value, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumLessThanOrEqualTo(Integer value) {
            addCriterion("gainedPrizeNum <=", value, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumIn(List<Integer> values) {
            addCriterion("gainedPrizeNum in", values, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumNotIn(List<Integer> values) {
            addCriterion("gainedPrizeNum not in", values, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumBetween(Integer value1, Integer value2) {
            addCriterion("gainedPrizeNum between", value1, value2, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andGainedprizenumNotBetween(Integer value1, Integer value2) {
            addCriterion("gainedPrizeNum not between", value1, value2, "gainedprizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumIsNull() {
            addCriterion("prizeNum is null");
            return (Criteria) this;
        }

        public Criteria andPrizenumIsNotNull() {
            addCriterion("prizeNum is not null");
            return (Criteria) this;
        }

        public Criteria andPrizenumEqualTo(Integer value) {
            addCriterion("prizeNum =", value, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumNotEqualTo(Integer value) {
            addCriterion("prizeNum <>", value, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumGreaterThan(Integer value) {
            addCriterion("prizeNum >", value, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("prizeNum >=", value, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumLessThan(Integer value) {
            addCriterion("prizeNum <", value, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumLessThanOrEqualTo(Integer value) {
            addCriterion("prizeNum <=", value, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumIn(List<Integer> values) {
            addCriterion("prizeNum in", values, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumNotIn(List<Integer> values) {
            addCriterion("prizeNum not in", values, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumBetween(Integer value1, Integer value2) {
            addCriterion("prizeNum between", value1, value2, "prizenum");
            return (Criteria) this;
        }

        public Criteria andPrizenumNotBetween(Integer value1, Integer value2) {
            addCriterion("prizeNum not between", value1, value2, "prizenum");
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