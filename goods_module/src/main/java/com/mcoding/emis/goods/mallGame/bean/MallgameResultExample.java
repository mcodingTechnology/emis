package com.mcoding.emis.goods.mallGame.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mcoding.base.core.PageView;

public class MallgameResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<MallgameResult> pageView;
    
    public PageView<MallgameResult> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<MallgameResult> pageView) {
		this.pageView = pageView;
	}

	public MallgameResultExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andGameidIsNull() {
            addCriterion("gameid is null");
            return (Criteria) this;
        }

        public Criteria andGameidIsNotNull() {
            addCriterion("gameid is not null");
            return (Criteria) this;
        }

        public Criteria andGameidEqualTo(Integer value) {
            addCriterion("gameid =", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotEqualTo(Integer value) {
            addCriterion("gameid <>", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidGreaterThan(Integer value) {
            addCriterion("gameid >", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidGreaterThanOrEqualTo(Integer value) {
            addCriterion("gameid >=", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLessThan(Integer value) {
            addCriterion("gameid <", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLessThanOrEqualTo(Integer value) {
            addCriterion("gameid <=", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidIn(List<Integer> values) {
            addCriterion("gameid in", values, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotIn(List<Integer> values) {
            addCriterion("gameid not in", values, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidBetween(Integer value1, Integer value2) {
            addCriterion("gameid between", value1, value2, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotBetween(Integer value1, Integer value2) {
            addCriterion("gameid not between", value1, value2, "gameid");
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

        public Criteria andNicknameIsNull() {
            addCriterion("nickName is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickName is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickName =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickName <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickName >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickName >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickName <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickName <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickName like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickName not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickName in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickName not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickName between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickName not between", value1, value2, "nickname");
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

        public Criteria andIsorderIsNull() {
            addCriterion("isOrder is null");
            return (Criteria) this;
        }

        public Criteria andIsorderIsNotNull() {
            addCriterion("isOrder is not null");
            return (Criteria) this;
        }

        public Criteria andIsorderEqualTo(Integer value) {
            addCriterion("isOrder =", value, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderNotEqualTo(Integer value) {
            addCriterion("isOrder <>", value, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderGreaterThan(Integer value) {
            addCriterion("isOrder >", value, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("isOrder >=", value, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderLessThan(Integer value) {
            addCriterion("isOrder <", value, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderLessThanOrEqualTo(Integer value) {
            addCriterion("isOrder <=", value, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderIn(List<Integer> values) {
            addCriterion("isOrder in", values, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderNotIn(List<Integer> values) {
            addCriterion("isOrder not in", values, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderBetween(Integer value1, Integer value2) {
            addCriterion("isOrder between", value1, value2, "isorder");
            return (Criteria) this;
        }

        public Criteria andIsorderNotBetween(Integer value1, Integer value2) {
            addCriterion("isOrder not between", value1, value2, "isorder");
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

        public Criteria andProductcoverimgIsNull() {
            addCriterion("productCoverImg is null");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgIsNotNull() {
            addCriterion("productCoverImg is not null");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgEqualTo(String value) {
            addCriterion("productCoverImg =", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotEqualTo(String value) {
            addCriterion("productCoverImg <>", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgGreaterThan(String value) {
            addCriterion("productCoverImg >", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgGreaterThanOrEqualTo(String value) {
            addCriterion("productCoverImg >=", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgLessThan(String value) {
            addCriterion("productCoverImg <", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgLessThanOrEqualTo(String value) {
            addCriterion("productCoverImg <=", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgLike(String value) {
            addCriterion("productCoverImg like", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotLike(String value) {
            addCriterion("productCoverImg not like", value, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgIn(List<String> values) {
            addCriterion("productCoverImg in", values, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotIn(List<String> values) {
            addCriterion("productCoverImg not in", values, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgBetween(String value1, String value2) {
            addCriterion("productCoverImg between", value1, value2, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andProductcoverimgNotBetween(String value1, String value2) {
            addCriterion("productCoverImg not between", value1, value2, "productcoverimg");
            return (Criteria) this;
        }

        public Criteria andGiftidIsNull() {
            addCriterion("giftId is null");
            return (Criteria) this;
        }

        public Criteria andGiftidIsNotNull() {
            addCriterion("giftId is not null");
            return (Criteria) this;
        }

        public Criteria andGiftidEqualTo(Integer value) {
            addCriterion("giftId =", value, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidNotEqualTo(Integer value) {
            addCriterion("giftId <>", value, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidGreaterThan(Integer value) {
            addCriterion("giftId >", value, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidGreaterThanOrEqualTo(Integer value) {
            addCriterion("giftId >=", value, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidLessThan(Integer value) {
            addCriterion("giftId <", value, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidLessThanOrEqualTo(Integer value) {
            addCriterion("giftId <=", value, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidIn(List<Integer> values) {
            addCriterion("giftId in", values, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidNotIn(List<Integer> values) {
            addCriterion("giftId not in", values, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidBetween(Integer value1, Integer value2) {
            addCriterion("giftId between", value1, value2, "giftid");
            return (Criteria) this;
        }

        public Criteria andGiftidNotBetween(Integer value1, Integer value2) {
            addCriterion("giftId not between", value1, value2, "giftid");
            return (Criteria) this;
        }

        public Criteria andGainpointIsNull() {
            addCriterion("gainPoint is null");
            return (Criteria) this;
        }

        public Criteria andGainpointIsNotNull() {
            addCriterion("gainPoint is not null");
            return (Criteria) this;
        }

        public Criteria andGainpointEqualTo(Integer value) {
            addCriterion("gainPoint =", value, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointNotEqualTo(Integer value) {
            addCriterion("gainPoint <>", value, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointGreaterThan(Integer value) {
            addCriterion("gainPoint >", value, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointGreaterThanOrEqualTo(Integer value) {
            addCriterion("gainPoint >=", value, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointLessThan(Integer value) {
            addCriterion("gainPoint <", value, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointLessThanOrEqualTo(Integer value) {
            addCriterion("gainPoint <=", value, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointIn(List<Integer> values) {
            addCriterion("gainPoint in", values, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointNotIn(List<Integer> values) {
            addCriterion("gainPoint not in", values, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointBetween(Integer value1, Integer value2) {
            addCriterion("gainPoint between", value1, value2, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andGainpointNotBetween(Integer value1, Integer value2) {
            addCriterion("gainPoint not between", value1, value2, "gainpoint");
            return (Criteria) this;
        }

        public Criteria andNumsIsNull() {
            addCriterion("nums is null");
            return (Criteria) this;
        }

        public Criteria andNumsIsNotNull() {
            addCriterion("nums is not null");
            return (Criteria) this;
        }

        public Criteria andNumsEqualTo(Integer value) {
            addCriterion("nums =", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotEqualTo(Integer value) {
            addCriterion("nums <>", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsGreaterThan(Integer value) {
            addCriterion("nums >", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("nums >=", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLessThan(Integer value) {
            addCriterion("nums <", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLessThanOrEqualTo(Integer value) {
            addCriterion("nums <=", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsIn(List<Integer> values) {
            addCriterion("nums in", values, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotIn(List<Integer> values) {
            addCriterion("nums not in", values, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsBetween(Integer value1, Integer value2) {
            addCriterion("nums between", value1, value2, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("nums not between", value1, value2, "nums");
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

        public Criteria andDrawdateIsNull() {
            addCriterion("drawDate is null");
            return (Criteria) this;
        }

        public Criteria andDrawdateIsNotNull() {
            addCriterion("drawDate is not null");
            return (Criteria) this;
        }

        public Criteria andDrawdateEqualTo(Date value) {
            addCriterionForJDBCDate("drawDate =", value, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("drawDate <>", value, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateGreaterThan(Date value) {
            addCriterionForJDBCDate("drawDate >", value, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("drawDate >=", value, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateLessThan(Date value) {
            addCriterionForJDBCDate("drawDate <", value, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("drawDate <=", value, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateIn(List<Date> values) {
            addCriterionForJDBCDate("drawDate in", values, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("drawDate not in", values, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("drawDate between", value1, value2, "drawdate");
            return (Criteria) this;
        }

        public Criteria andDrawdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("drawDate not between", value1, value2, "drawdate");
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