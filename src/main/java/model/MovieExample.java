package model;

import java.util.ArrayList;
import java.util.List;

public class MovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieExample() {
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

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andHrefIsNull() {
            addCriterion("href is null");
            return (Criteria) this;
        }

        public Criteria andHrefIsNotNull() {
            addCriterion("href is not null");
            return (Criteria) this;
        }

        public Criteria andHrefEqualTo(String value) {
            addCriterion("href =", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotEqualTo(String value) {
            addCriterion("href <>", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThan(String value) {
            addCriterion("href >", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThanOrEqualTo(String value) {
            addCriterion("href >=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThan(String value) {
            addCriterion("href <", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThanOrEqualTo(String value) {
            addCriterion("href <=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLike(String value) {
            addCriterion("href like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotLike(String value) {
            addCriterion("href not like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefIn(List<String> values) {
            addCriterion("href in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotIn(List<String> values) {
            addCriterion("href not in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefBetween(String value1, String value2) {
            addCriterion("href between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotBetween(String value1, String value2) {
            addCriterion("href not between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andImgsrcIsNull() {
            addCriterion("imgSrc is null");
            return (Criteria) this;
        }

        public Criteria andImgsrcIsNotNull() {
            addCriterion("imgSrc is not null");
            return (Criteria) this;
        }

        public Criteria andImgsrcEqualTo(String value) {
            addCriterion("imgSrc =", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcNotEqualTo(String value) {
            addCriterion("imgSrc <>", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcGreaterThan(String value) {
            addCriterion("imgSrc >", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcGreaterThanOrEqualTo(String value) {
            addCriterion("imgSrc >=", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcLessThan(String value) {
            addCriterion("imgSrc <", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcLessThanOrEqualTo(String value) {
            addCriterion("imgSrc <=", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcLike(String value) {
            addCriterion("imgSrc like", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcNotLike(String value) {
            addCriterion("imgSrc not like", value, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcIn(List<String> values) {
            addCriterion("imgSrc in", values, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcNotIn(List<String> values) {
            addCriterion("imgSrc not in", values, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcBetween(String value1, String value2) {
            addCriterion("imgSrc between", value1, value2, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andImgsrcNotBetween(String value1, String value2) {
            addCriterion("imgSrc not between", value1, value2, "imgsrc");
            return (Criteria) this;
        }

        public Criteria andJinumberIsNull() {
            addCriterion("jiNumber is null");
            return (Criteria) this;
        }

        public Criteria andJinumberIsNotNull() {
            addCriterion("jiNumber is not null");
            return (Criteria) this;
        }

        public Criteria andJinumberEqualTo(String value) {
            addCriterion("jiNumber =", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberNotEqualTo(String value) {
            addCriterion("jiNumber <>", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberGreaterThan(String value) {
            addCriterion("jiNumber >", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberGreaterThanOrEqualTo(String value) {
            addCriterion("jiNumber >=", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberLessThan(String value) {
            addCriterion("jiNumber <", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberLessThanOrEqualTo(String value) {
            addCriterion("jiNumber <=", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberLike(String value) {
            addCriterion("jiNumber like", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberNotLike(String value) {
            addCriterion("jiNumber not like", value, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberIn(List<String> values) {
            addCriterion("jiNumber in", values, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberNotIn(List<String> values) {
            addCriterion("jiNumber not in", values, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberBetween(String value1, String value2) {
            addCriterion("jiNumber between", value1, value2, "jinumber");
            return (Criteria) this;
        }

        public Criteria andJinumberNotBetween(String value1, String value2) {
            addCriterion("jiNumber not between", value1, value2, "jinumber");
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