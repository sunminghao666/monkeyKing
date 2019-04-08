package com.monkey.db.base.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PwbDictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PwbDictionaryExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDicTypeIsNull() {
            addCriterion("DIC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDicTypeIsNotNull() {
            addCriterion("DIC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDicTypeEqualTo(String value) {
            addCriterion("DIC_TYPE =", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotEqualTo(String value) {
            addCriterion("DIC_TYPE <>", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeGreaterThan(String value) {
            addCriterion("DIC_TYPE >", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_TYPE >=", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLessThan(String value) {
            addCriterion("DIC_TYPE <", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLessThanOrEqualTo(String value) {
            addCriterion("DIC_TYPE <=", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLike(String value) {
            addCriterion("DIC_TYPE like", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotLike(String value) {
            addCriterion("DIC_TYPE not like", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeIn(List<String> values) {
            addCriterion("DIC_TYPE in", values, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotIn(List<String> values) {
            addCriterion("DIC_TYPE not in", values, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeBetween(String value1, String value2) {
            addCriterion("DIC_TYPE between", value1, value2, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotBetween(String value1, String value2) {
            addCriterion("DIC_TYPE not between", value1, value2, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelIsNull() {
            addCriterion("DIC_TYPE_LABEL is null");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelIsNotNull() {
            addCriterion("DIC_TYPE_LABEL is not null");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelEqualTo(String value) {
            addCriterion("DIC_TYPE_LABEL =", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelNotEqualTo(String value) {
            addCriterion("DIC_TYPE_LABEL <>", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelGreaterThan(String value) {
            addCriterion("DIC_TYPE_LABEL >", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_TYPE_LABEL >=", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelLessThan(String value) {
            addCriterion("DIC_TYPE_LABEL <", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelLessThanOrEqualTo(String value) {
            addCriterion("DIC_TYPE_LABEL <=", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelLike(String value) {
            addCriterion("DIC_TYPE_LABEL like", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelNotLike(String value) {
            addCriterion("DIC_TYPE_LABEL not like", value, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelIn(List<String> values) {
            addCriterion("DIC_TYPE_LABEL in", values, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelNotIn(List<String> values) {
            addCriterion("DIC_TYPE_LABEL not in", values, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelBetween(String value1, String value2) {
            addCriterion("DIC_TYPE_LABEL between", value1, value2, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicTypeLabelNotBetween(String value1, String value2) {
            addCriterion("DIC_TYPE_LABEL not between", value1, value2, "dicTypeLabel");
            return (Criteria) this;
        }

        public Criteria andDicCodeIsNull() {
            addCriterion("DIC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDicCodeIsNotNull() {
            addCriterion("DIC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDicCodeEqualTo(String value) {
            addCriterion("DIC_CODE =", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotEqualTo(String value) {
            addCriterion("DIC_CODE <>", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeGreaterThan(String value) {
            addCriterion("DIC_CODE >", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_CODE >=", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLessThan(String value) {
            addCriterion("DIC_CODE <", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLessThanOrEqualTo(String value) {
            addCriterion("DIC_CODE <=", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLike(String value) {
            addCriterion("DIC_CODE like", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotLike(String value) {
            addCriterion("DIC_CODE not like", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeIn(List<String> values) {
            addCriterion("DIC_CODE in", values, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotIn(List<String> values) {
            addCriterion("DIC_CODE not in", values, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeBetween(String value1, String value2) {
            addCriterion("DIC_CODE between", value1, value2, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotBetween(String value1, String value2) {
            addCriterion("DIC_CODE not between", value1, value2, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicValueIsNull() {
            addCriterion("DIC_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDicValueIsNotNull() {
            addCriterion("DIC_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDicValueEqualTo(String value) {
            addCriterion("DIC_VALUE =", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotEqualTo(String value) {
            addCriterion("DIC_VALUE <>", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueGreaterThan(String value) {
            addCriterion("DIC_VALUE >", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_VALUE >=", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLessThan(String value) {
            addCriterion("DIC_VALUE <", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLessThanOrEqualTo(String value) {
            addCriterion("DIC_VALUE <=", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLike(String value) {
            addCriterion("DIC_VALUE like", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotLike(String value) {
            addCriterion("DIC_VALUE not like", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueIn(List<String> values) {
            addCriterion("DIC_VALUE in", values, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotIn(List<String> values) {
            addCriterion("DIC_VALUE not in", values, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueBetween(String value1, String value2) {
            addCriterion("DIC_VALUE between", value1, value2, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotBetween(String value1, String value2) {
            addCriterion("DIC_VALUE not between", value1, value2, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicExplainIsNull() {
            addCriterion("DIC_EXPLAIN is null");
            return (Criteria) this;
        }

        public Criteria andDicExplainIsNotNull() {
            addCriterion("DIC_EXPLAIN is not null");
            return (Criteria) this;
        }

        public Criteria andDicExplainEqualTo(String value) {
            addCriterion("DIC_EXPLAIN =", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainNotEqualTo(String value) {
            addCriterion("DIC_EXPLAIN <>", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainGreaterThan(String value) {
            addCriterion("DIC_EXPLAIN >", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_EXPLAIN >=", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainLessThan(String value) {
            addCriterion("DIC_EXPLAIN <", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainLessThanOrEqualTo(String value) {
            addCriterion("DIC_EXPLAIN <=", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainLike(String value) {
            addCriterion("DIC_EXPLAIN like", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainNotLike(String value) {
            addCriterion("DIC_EXPLAIN not like", value, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainIn(List<String> values) {
            addCriterion("DIC_EXPLAIN in", values, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainNotIn(List<String> values) {
            addCriterion("DIC_EXPLAIN not in", values, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainBetween(String value1, String value2) {
            addCriterion("DIC_EXPLAIN between", value1, value2, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicExplainNotBetween(String value1, String value2) {
            addCriterion("DIC_EXPLAIN not between", value1, value2, "dicExplain");
            return (Criteria) this;
        }

        public Criteria andDicStatusIsNull() {
            addCriterion("DIC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDicStatusIsNotNull() {
            addCriterion("DIC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDicStatusEqualTo(String value) {
            addCriterion("DIC_STATUS =", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusNotEqualTo(String value) {
            addCriterion("DIC_STATUS <>", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusGreaterThan(String value) {
            addCriterion("DIC_STATUS >", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_STATUS >=", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusLessThan(String value) {
            addCriterion("DIC_STATUS <", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusLessThanOrEqualTo(String value) {
            addCriterion("DIC_STATUS <=", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusLike(String value) {
            addCriterion("DIC_STATUS like", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusNotLike(String value) {
            addCriterion("DIC_STATUS not like", value, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusIn(List<String> values) {
            addCriterion("DIC_STATUS in", values, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusNotIn(List<String> values) {
            addCriterion("DIC_STATUS not in", values, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusBetween(String value1, String value2) {
            addCriterion("DIC_STATUS between", value1, value2, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicStatusNotBetween(String value1, String value2) {
            addCriterion("DIC_STATUS not between", value1, value2, "dicStatus");
            return (Criteria) this;
        }

        public Criteria andDicOrderIsNull() {
            addCriterion("DIC_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andDicOrderIsNotNull() {
            addCriterion("DIC_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andDicOrderEqualTo(Integer value) {
            addCriterion("DIC_ORDER =", value, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderNotEqualTo(Integer value) {
            addCriterion("DIC_ORDER <>", value, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderGreaterThan(Integer value) {
            addCriterion("DIC_ORDER >", value, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("DIC_ORDER >=", value, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderLessThan(Integer value) {
            addCriterion("DIC_ORDER <", value, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderLessThanOrEqualTo(Integer value) {
            addCriterion("DIC_ORDER <=", value, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderIn(List<Integer> values) {
            addCriterion("DIC_ORDER in", values, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderNotIn(List<Integer> values) {
            addCriterion("DIC_ORDER not in", values, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderBetween(Integer value1, Integer value2) {
            addCriterion("DIC_ORDER between", value1, value2, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("DIC_ORDER not between", value1, value2, "dicOrder");
            return (Criteria) this;
        }

        public Criteria andDicChannelIsNull() {
            addCriterion("DIC_CHANNEL is null");
            return (Criteria) this;
        }

        public Criteria andDicChannelIsNotNull() {
            addCriterion("DIC_CHANNEL is not null");
            return (Criteria) this;
        }

        public Criteria andDicChannelEqualTo(String value) {
            addCriterion("DIC_CHANNEL =", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelNotEqualTo(String value) {
            addCriterion("DIC_CHANNEL <>", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelGreaterThan(String value) {
            addCriterion("DIC_CHANNEL >", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_CHANNEL >=", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelLessThan(String value) {
            addCriterion("DIC_CHANNEL <", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelLessThanOrEqualTo(String value) {
            addCriterion("DIC_CHANNEL <=", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelLike(String value) {
            addCriterion("DIC_CHANNEL like", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelNotLike(String value) {
            addCriterion("DIC_CHANNEL not like", value, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelIn(List<String> values) {
            addCriterion("DIC_CHANNEL in", values, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelNotIn(List<String> values) {
            addCriterion("DIC_CHANNEL not in", values, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelBetween(String value1, String value2) {
            addCriterion("DIC_CHANNEL between", value1, value2, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andDicChannelNotBetween(String value1, String value2) {
            addCriterion("DIC_CHANNEL not between", value1, value2, "dicChannel");
            return (Criteria) this;
        }

        public Criteria andInsertcodeIsNull() {
            addCriterion("INSERTCODE is null");
            return (Criteria) this;
        }

        public Criteria andInsertcodeIsNotNull() {
            addCriterion("INSERTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andInsertcodeEqualTo(String value) {
            addCriterion("INSERTCODE =", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeNotEqualTo(String value) {
            addCriterion("INSERTCODE <>", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeGreaterThan(String value) {
            addCriterion("INSERTCODE >", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeGreaterThanOrEqualTo(String value) {
            addCriterion("INSERTCODE >=", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeLessThan(String value) {
            addCriterion("INSERTCODE <", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeLessThanOrEqualTo(String value) {
            addCriterion("INSERTCODE <=", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeLike(String value) {
            addCriterion("INSERTCODE like", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeNotLike(String value) {
            addCriterion("INSERTCODE not like", value, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeIn(List<String> values) {
            addCriterion("INSERTCODE in", values, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeNotIn(List<String> values) {
            addCriterion("INSERTCODE not in", values, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeBetween(String value1, String value2) {
            addCriterion("INSERTCODE between", value1, value2, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInsertcodeNotBetween(String value1, String value2) {
            addCriterion("INSERTCODE not between", value1, value2, "insertcode");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNull() {
            addCriterion("INSERTTIME is null");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNotNull() {
            addCriterion("INSERTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInserttimeEqualTo(Date value) {
            addCriterion("INSERTTIME =", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotEqualTo(Date value) {
            addCriterion("INSERTTIME <>", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThan(Date value) {
            addCriterion("INSERTTIME >", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("INSERTTIME >=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThan(Date value) {
            addCriterion("INSERTTIME <", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThanOrEqualTo(Date value) {
            addCriterion("INSERTTIME <=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeIn(List<Date> values) {
            addCriterion("INSERTTIME in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotIn(List<Date> values) {
            addCriterion("INSERTTIME not in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeBetween(Date value1, Date value2) {
            addCriterion("INSERTTIME between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotBetween(Date value1, Date value2) {
            addCriterion("INSERTTIME not between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSystemChannelIsNull() {
            addCriterion("SYSTEM_CHANNEL is null");
            return (Criteria) this;
        }

        public Criteria andSystemChannelIsNotNull() {
            addCriterion("SYSTEM_CHANNEL is not null");
            return (Criteria) this;
        }

        public Criteria andSystemChannelEqualTo(String value) {
            addCriterion("SYSTEM_CHANNEL =", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelNotEqualTo(String value) {
            addCriterion("SYSTEM_CHANNEL <>", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelGreaterThan(String value) {
            addCriterion("SYSTEM_CHANNEL >", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelGreaterThanOrEqualTo(String value) {
            addCriterion("SYSTEM_CHANNEL >=", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelLessThan(String value) {
            addCriterion("SYSTEM_CHANNEL <", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelLessThanOrEqualTo(String value) {
            addCriterion("SYSTEM_CHANNEL <=", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelLike(String value) {
            addCriterion("SYSTEM_CHANNEL like", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelNotLike(String value) {
            addCriterion("SYSTEM_CHANNEL not like", value, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelIn(List<String> values) {
            addCriterion("SYSTEM_CHANNEL in", values, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelNotIn(List<String> values) {
            addCriterion("SYSTEM_CHANNEL not in", values, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelBetween(String value1, String value2) {
            addCriterion("SYSTEM_CHANNEL between", value1, value2, "systemChannel");
            return (Criteria) this;
        }

        public Criteria andSystemChannelNotBetween(String value1, String value2) {
            addCriterion("SYSTEM_CHANNEL not between", value1, value2, "systemChannel");
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