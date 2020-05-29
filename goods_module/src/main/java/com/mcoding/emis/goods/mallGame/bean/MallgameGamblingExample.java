package com.mcoding.emis.goods.mallGame.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallgameGamblingExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public MallgameGamblingExample() {
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
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

		public Criteria andGamestarttimeIsNull() {
			addCriterion("gameStartTime is null");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeIsNotNull() {
			addCriterion("gameStartTime is not null");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeEqualTo(Date value) {
			addCriterion("gameStartTime =", value, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeNotEqualTo(Date value) {
			addCriterion("gameStartTime <>", value, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeGreaterThan(Date value) {
			addCriterion("gameStartTime >", value, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeGreaterThanOrEqualTo(Date value) {
			addCriterion("gameStartTime >=", value, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeLessThan(Date value) {
			addCriterion("gameStartTime <", value, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeLessThanOrEqualTo(Date value) {
			addCriterion("gameStartTime <=", value, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeIn(List<Date> values) {
			addCriterion("gameStartTime in", values, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeNotIn(List<Date> values) {
			addCriterion("gameStartTime not in", values, "gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeBetween(Date value1, Date value2) {
			addCriterion("gameStartTime between", value1, value2,
					"gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGamestarttimeNotBetween(Date value1, Date value2) {
			addCriterion("gameStartTime not between", value1, value2,
					"gamestarttime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeIsNull() {
			addCriterion("gameEndTime is null");
			return (Criteria) this;
		}

		public Criteria andGameendtimeIsNotNull() {
			addCriterion("gameEndTime is not null");
			return (Criteria) this;
		}

		public Criteria andGameendtimeEqualTo(Date value) {
			addCriterion("gameEndTime =", value, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeNotEqualTo(Date value) {
			addCriterion("gameEndTime <>", value, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeGreaterThan(Date value) {
			addCriterion("gameEndTime >", value, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("gameEndTime >=", value, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeLessThan(Date value) {
			addCriterion("gameEndTime <", value, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeLessThanOrEqualTo(Date value) {
			addCriterion("gameEndTime <=", value, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeIn(List<Date> values) {
			addCriterion("gameEndTime in", values, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeNotIn(List<Date> values) {
			addCriterion("gameEndTime not in", values, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeBetween(Date value1, Date value2) {
			addCriterion("gameEndTime between", value1, value2, "gameendtime");
			return (Criteria) this;
		}

		public Criteria andGameendtimeNotBetween(Date value1, Date value2) {
			addCriterion("gameEndTime not between", value1, value2,
					"gameendtime");
			return (Criteria) this;
		}

		public Criteria andPrizerangeIsNull() {
			addCriterion("prizeRange is null");
			return (Criteria) this;
		}

		public Criteria andPrizerangeIsNotNull() {
			addCriterion("prizeRange is not null");
			return (Criteria) this;
		}

		public Criteria andPrizerangeEqualTo(String value) {
			addCriterion("prizeRange =", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeNotEqualTo(String value) {
			addCriterion("prizeRange <>", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeGreaterThan(String value) {
			addCriterion("prizeRange >", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeGreaterThanOrEqualTo(String value) {
			addCriterion("prizeRange >=", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeLessThan(String value) {
			addCriterion("prizeRange <", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeLessThanOrEqualTo(String value) {
			addCriterion("prizeRange <=", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeLike(String value) {
			addCriterion("prizeRange like", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeNotLike(String value) {
			addCriterion("prizeRange not like", value, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeIn(List<String> values) {
			addCriterion("prizeRange in", values, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeNotIn(List<String> values) {
			addCriterion("prizeRange not in", values, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeBetween(String value1, String value2) {
			addCriterion("prizeRange between", value1, value2, "prizerange");
			return (Criteria) this;
		}

		public Criteria andPrizerangeNotBetween(String value1, String value2) {
			addCriterion("prizeRange not between", value1, value2, "prizerange");
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

		public Criteria andDaydrawnumIsNull() {
			addCriterion("dayDrawNum is null");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumIsNotNull() {
			addCriterion("dayDrawNum is not null");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumEqualTo(Integer value) {
			addCriterion("dayDrawNum =", value, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumNotEqualTo(Integer value) {
			addCriterion("dayDrawNum <>", value, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumGreaterThan(Integer value) {
			addCriterion("dayDrawNum >", value, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumGreaterThanOrEqualTo(Integer value) {
			addCriterion("dayDrawNum >=", value, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumLessThan(Integer value) {
			addCriterion("dayDrawNum <", value, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumLessThanOrEqualTo(Integer value) {
			addCriterion("dayDrawNum <=", value, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumIn(List<Integer> values) {
			addCriterion("dayDrawNum in", values, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumNotIn(List<Integer> values) {
			addCriterion("dayDrawNum not in", values, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumBetween(Integer value1, Integer value2) {
			addCriterion("dayDrawNum between", value1, value2, "daydrawnum");
			return (Criteria) this;
		}

		public Criteria andDaydrawnumNotBetween(Integer value1, Integer value2) {
			addCriterion("dayDrawNum not between", value1, value2, "daydrawnum");
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

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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