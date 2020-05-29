package com.mcoding.emis.goods.income.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeProductExample implements IExample<IncomeProduct> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<IncomeProduct> pageView;

    public IncomeProductExample() {
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
    public PageView<IncomeProduct> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<IncomeProduct> pageView) {
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

        public Criteria andChannelsidIsNull() {
            addCriterion("channelsId is null");
            return (Criteria) this;
        }

        public Criteria andChannelsidIsNotNull() {
            addCriterion("channelsId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelsidEqualTo(Integer value) {
            addCriterion("channelsId =", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotEqualTo(Integer value) {
            addCriterion("channelsId <>", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidGreaterThan(Integer value) {
            addCriterion("channelsId >", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("channelsId >=", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidLessThan(Integer value) {
            addCriterion("channelsId <", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidLessThanOrEqualTo(Integer value) {
            addCriterion("channelsId <=", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidIn(List<Integer> values) {
            addCriterion("channelsId in", values, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotIn(List<Integer> values) {
            addCriterion("channelsId not in", values, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidBetween(Integer value1, Integer value2) {
            addCriterion("channelsId between", value1, value2, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotBetween(Integer value1, Integer value2) {
            addCriterion("channelsId not between", value1, value2, "channelsid");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceIsNull() {
            addCriterion("microMallPrice is null");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceIsNotNull() {
            addCriterion("microMallPrice is not null");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceEqualTo(Integer value) {
            addCriterion("microMallPrice =", value, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceNotEqualTo(Integer value) {
            addCriterion("microMallPrice <>", value, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceGreaterThan(Integer value) {
            addCriterion("microMallPrice >", value, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("microMallPrice >=", value, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceLessThan(Integer value) {
            addCriterion("microMallPrice <", value, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceLessThanOrEqualTo(Integer value) {
            addCriterion("microMallPrice <=", value, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceIn(List<Integer> values) {
            addCriterion("microMallPrice in", values, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceNotIn(List<Integer> values) {
            addCriterion("microMallPrice not in", values, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceBetween(Integer value1, Integer value2) {
            addCriterion("microMallPrice between", value1, value2, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andMicromallpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("microMallPrice not between", value1, value2, "micromallprice");
            return (Criteria) this;
        }

        public Criteria andLevel1IsNull() {
            addCriterion("level1 is null");
            return (Criteria) this;
        }

        public Criteria andLevel1IsNotNull() {
            addCriterion("level1 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel1EqualTo(Integer value) {
            addCriterion("level1 =", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotEqualTo(Integer value) {
            addCriterion("level1 <>", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1GreaterThan(Integer value) {
            addCriterion("level1 >", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1GreaterThanOrEqualTo(Integer value) {
            addCriterion("level1 >=", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1LessThan(Integer value) {
            addCriterion("level1 <", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1LessThanOrEqualTo(Integer value) {
            addCriterion("level1 <=", value, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1In(List<Integer> values) {
            addCriterion("level1 in", values, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotIn(List<Integer> values) {
            addCriterion("level1 not in", values, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1Between(Integer value1, Integer value2) {
            addCriterion("level1 between", value1, value2, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel1NotBetween(Integer value1, Integer value2) {
            addCriterion("level1 not between", value1, value2, "level1");
            return (Criteria) this;
        }

        public Criteria andLevel2IsNull() {
            addCriterion("level2 is null");
            return (Criteria) this;
        }

        public Criteria andLevel2IsNotNull() {
            addCriterion("level2 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel2EqualTo(Integer value) {
            addCriterion("level2 =", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotEqualTo(Integer value) {
            addCriterion("level2 <>", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2GreaterThan(Integer value) {
            addCriterion("level2 >", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2GreaterThanOrEqualTo(Integer value) {
            addCriterion("level2 >=", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2LessThan(Integer value) {
            addCriterion("level2 <", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2LessThanOrEqualTo(Integer value) {
            addCriterion("level2 <=", value, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2In(List<Integer> values) {
            addCriterion("level2 in", values, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotIn(List<Integer> values) {
            addCriterion("level2 not in", values, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2Between(Integer value1, Integer value2) {
            addCriterion("level2 between", value1, value2, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel2NotBetween(Integer value1, Integer value2) {
            addCriterion("level2 not between", value1, value2, "level2");
            return (Criteria) this;
        }

        public Criteria andLevel3IsNull() {
            addCriterion("level3 is null");
            return (Criteria) this;
        }

        public Criteria andLevel3IsNotNull() {
            addCriterion("level3 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel3EqualTo(Integer value) {
            addCriterion("level3 =", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotEqualTo(Integer value) {
            addCriterion("level3 <>", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3GreaterThan(Integer value) {
            addCriterion("level3 >", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3GreaterThanOrEqualTo(Integer value) {
            addCriterion("level3 >=", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3LessThan(Integer value) {
            addCriterion("level3 <", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3LessThanOrEqualTo(Integer value) {
            addCriterion("level3 <=", value, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3In(List<Integer> values) {
            addCriterion("level3 in", values, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotIn(List<Integer> values) {
            addCriterion("level3 not in", values, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3Between(Integer value1, Integer value2) {
            addCriterion("level3 between", value1, value2, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel3NotBetween(Integer value1, Integer value2) {
            addCriterion("level3 not between", value1, value2, "level3");
            return (Criteria) this;
        }

        public Criteria andLevel4IsNull() {
            addCriterion("level4 is null");
            return (Criteria) this;
        }

        public Criteria andLevel4IsNotNull() {
            addCriterion("level4 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel4EqualTo(Integer value) {
            addCriterion("level4 =", value, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4NotEqualTo(Integer value) {
            addCriterion("level4 <>", value, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4GreaterThan(Integer value) {
            addCriterion("level4 >", value, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4GreaterThanOrEqualTo(Integer value) {
            addCriterion("level4 >=", value, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4LessThan(Integer value) {
            addCriterion("level4 <", value, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4LessThanOrEqualTo(Integer value) {
            addCriterion("level4 <=", value, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4In(List<Integer> values) {
            addCriterion("level4 in", values, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4NotIn(List<Integer> values) {
            addCriterion("level4 not in", values, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4Between(Integer value1, Integer value2) {
            addCriterion("level4 between", value1, value2, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel4NotBetween(Integer value1, Integer value2) {
            addCriterion("level4 not between", value1, value2, "level4");
            return (Criteria) this;
        }

        public Criteria andLevel1pointIsNull() {
            addCriterion("level1Point is null");
            return (Criteria) this;
        }

        public Criteria andLevel1pointIsNotNull() {
            addCriterion("level1Point is not null");
            return (Criteria) this;
        }

        public Criteria andLevel1pointEqualTo(Integer value) {
            addCriterion("level1Point =", value, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointNotEqualTo(Integer value) {
            addCriterion("level1Point <>", value, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointGreaterThan(Integer value) {
            addCriterion("level1Point >", value, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointGreaterThanOrEqualTo(Integer value) {
            addCriterion("level1Point >=", value, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointLessThan(Integer value) {
            addCriterion("level1Point <", value, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointLessThanOrEqualTo(Integer value) {
            addCriterion("level1Point <=", value, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointIn(List<Integer> values) {
            addCriterion("level1Point in", values, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointNotIn(List<Integer> values) {
            addCriterion("level1Point not in", values, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointBetween(Integer value1, Integer value2) {
            addCriterion("level1Point between", value1, value2, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel1pointNotBetween(Integer value1, Integer value2) {
            addCriterion("level1Point not between", value1, value2, "level1point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointIsNull() {
            addCriterion("level2Point is null");
            return (Criteria) this;
        }

        public Criteria andLevel2pointIsNotNull() {
            addCriterion("level2Point is not null");
            return (Criteria) this;
        }

        public Criteria andLevel2pointEqualTo(Integer value) {
            addCriterion("level2Point =", value, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointNotEqualTo(Integer value) {
            addCriterion("level2Point <>", value, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointGreaterThan(Integer value) {
            addCriterion("level2Point >", value, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointGreaterThanOrEqualTo(Integer value) {
            addCriterion("level2Point >=", value, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointLessThan(Integer value) {
            addCriterion("level2Point <", value, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointLessThanOrEqualTo(Integer value) {
            addCriterion("level2Point <=", value, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointIn(List<Integer> values) {
            addCriterion("level2Point in", values, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointNotIn(List<Integer> values) {
            addCriterion("level2Point not in", values, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointBetween(Integer value1, Integer value2) {
            addCriterion("level2Point between", value1, value2, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel2pointNotBetween(Integer value1, Integer value2) {
            addCriterion("level2Point not between", value1, value2, "level2point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointIsNull() {
            addCriterion("level3Point is null");
            return (Criteria) this;
        }

        public Criteria andLevel3pointIsNotNull() {
            addCriterion("level3Point is not null");
            return (Criteria) this;
        }

        public Criteria andLevel3pointEqualTo(Integer value) {
            addCriterion("level3Point =", value, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointNotEqualTo(Integer value) {
            addCriterion("level3Point <>", value, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointGreaterThan(Integer value) {
            addCriterion("level3Point >", value, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointGreaterThanOrEqualTo(Integer value) {
            addCriterion("level3Point >=", value, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointLessThan(Integer value) {
            addCriterion("level3Point <", value, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointLessThanOrEqualTo(Integer value) {
            addCriterion("level3Point <=", value, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointIn(List<Integer> values) {
            addCriterion("level3Point in", values, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointNotIn(List<Integer> values) {
            addCriterion("level3Point not in", values, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointBetween(Integer value1, Integer value2) {
            addCriterion("level3Point between", value1, value2, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel3pointNotBetween(Integer value1, Integer value2) {
            addCriterion("level3Point not between", value1, value2, "level3point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointIsNull() {
            addCriterion("level4Point is null");
            return (Criteria) this;
        }

        public Criteria andLevel4pointIsNotNull() {
            addCriterion("level4Point is not null");
            return (Criteria) this;
        }

        public Criteria andLevel4pointEqualTo(Integer value) {
            addCriterion("level4Point =", value, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointNotEqualTo(Integer value) {
            addCriterion("level4Point <>", value, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointGreaterThan(Integer value) {
            addCriterion("level4Point >", value, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointGreaterThanOrEqualTo(Integer value) {
            addCriterion("level4Point >=", value, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointLessThan(Integer value) {
            addCriterion("level4Point <", value, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointLessThanOrEqualTo(Integer value) {
            addCriterion("level4Point <=", value, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointIn(List<Integer> values) {
            addCriterion("level4Point in", values, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointNotIn(List<Integer> values) {
            addCriterion("level4Point not in", values, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointBetween(Integer value1, Integer value2) {
            addCriterion("level4Point between", value1, value2, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel4pointNotBetween(Integer value1, Integer value2) {
            addCriterion("level4Point not between", value1, value2, "level4point");
            return (Criteria) this;
        }

        public Criteria andLevel5IsNull() {
            addCriterion("level5 is null");
            return (Criteria) this;
        }

        public Criteria andLevel5IsNotNull() {
            addCriterion("level5 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel5EqualTo(Integer value) {
            addCriterion("level5 =", value, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5NotEqualTo(Integer value) {
            addCriterion("level5 <>", value, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5GreaterThan(Integer value) {
            addCriterion("level5 >", value, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5GreaterThanOrEqualTo(Integer value) {
            addCriterion("level5 >=", value, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5LessThan(Integer value) {
            addCriterion("level5 <", value, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5LessThanOrEqualTo(Integer value) {
            addCriterion("level5 <=", value, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5In(List<Integer> values) {
            addCriterion("level5 in", values, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5NotIn(List<Integer> values) {
            addCriterion("level5 not in", values, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5Between(Integer value1, Integer value2) {
            addCriterion("level5 between", value1, value2, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel5NotBetween(Integer value1, Integer value2) {
            addCriterion("level5 not between", value1, value2, "level5");
            return (Criteria) this;
        }

        public Criteria andLevel6IsNull() {
            addCriterion("level6 is null");
            return (Criteria) this;
        }

        public Criteria andLevel6IsNotNull() {
            addCriterion("level6 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel6EqualTo(Integer value) {
            addCriterion("level6 =", value, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6NotEqualTo(Integer value) {
            addCriterion("level6 <>", value, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6GreaterThan(Integer value) {
            addCriterion("level6 >", value, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6GreaterThanOrEqualTo(Integer value) {
            addCriterion("level6 >=", value, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6LessThan(Integer value) {
            addCriterion("level6 <", value, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6LessThanOrEqualTo(Integer value) {
            addCriterion("level6 <=", value, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6In(List<Integer> values) {
            addCriterion("level6 in", values, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6NotIn(List<Integer> values) {
            addCriterion("level6 not in", values, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6Between(Integer value1, Integer value2) {
            addCriterion("level6 between", value1, value2, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel6NotBetween(Integer value1, Integer value2) {
            addCriterion("level6 not between", value1, value2, "level6");
            return (Criteria) this;
        }

        public Criteria andLevel7IsNull() {
            addCriterion("level7 is null");
            return (Criteria) this;
        }

        public Criteria andLevel7IsNotNull() {
            addCriterion("level7 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel7EqualTo(Integer value) {
            addCriterion("level7 =", value, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7NotEqualTo(Integer value) {
            addCriterion("level7 <>", value, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7GreaterThan(Integer value) {
            addCriterion("level7 >", value, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7GreaterThanOrEqualTo(Integer value) {
            addCriterion("level7 >=", value, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7LessThan(Integer value) {
            addCriterion("level7 <", value, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7LessThanOrEqualTo(Integer value) {
            addCriterion("level7 <=", value, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7In(List<Integer> values) {
            addCriterion("level7 in", values, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7NotIn(List<Integer> values) {
            addCriterion("level7 not in", values, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7Between(Integer value1, Integer value2) {
            addCriterion("level7 between", value1, value2, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel7NotBetween(Integer value1, Integer value2) {
            addCriterion("level7 not between", value1, value2, "level7");
            return (Criteria) this;
        }

        public Criteria andLevel8IsNull() {
            addCriterion("level8 is null");
            return (Criteria) this;
        }

        public Criteria andLevel8IsNotNull() {
            addCriterion("level8 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel8EqualTo(Integer value) {
            addCriterion("level8 =", value, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8NotEqualTo(Integer value) {
            addCriterion("level8 <>", value, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8GreaterThan(Integer value) {
            addCriterion("level8 >", value, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8GreaterThanOrEqualTo(Integer value) {
            addCriterion("level8 >=", value, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8LessThan(Integer value) {
            addCriterion("level8 <", value, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8LessThanOrEqualTo(Integer value) {
            addCriterion("level8 <=", value, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8In(List<Integer> values) {
            addCriterion("level8 in", values, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8NotIn(List<Integer> values) {
            addCriterion("level8 not in", values, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8Between(Integer value1, Integer value2) {
            addCriterion("level8 between", value1, value2, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel8NotBetween(Integer value1, Integer value2) {
            addCriterion("level8 not between", value1, value2, "level8");
            return (Criteria) this;
        }

        public Criteria andLevel9IsNull() {
            addCriterion("level9 is null");
            return (Criteria) this;
        }

        public Criteria andLevel9IsNotNull() {
            addCriterion("level9 is not null");
            return (Criteria) this;
        }

        public Criteria andLevel9EqualTo(Integer value) {
            addCriterion("level9 =", value, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9NotEqualTo(Integer value) {
            addCriterion("level9 <>", value, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9GreaterThan(Integer value) {
            addCriterion("level9 >", value, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9GreaterThanOrEqualTo(Integer value) {
            addCriterion("level9 >=", value, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9LessThan(Integer value) {
            addCriterion("level9 <", value, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9LessThanOrEqualTo(Integer value) {
            addCriterion("level9 <=", value, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9In(List<Integer> values) {
            addCriterion("level9 in", values, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9NotIn(List<Integer> values) {
            addCriterion("level9 not in", values, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9Between(Integer value1, Integer value2) {
            addCriterion("level9 between", value1, value2, "level9");
            return (Criteria) this;
        }

        public Criteria andLevel9NotBetween(Integer value1, Integer value2) {
            addCriterion("level9 not between", value1, value2, "level9");
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