package model;

import java.util.ArrayList;
import java.util.List;

public class IdcardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IdcardExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idCard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idCard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idCard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idCard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idCard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idCard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idCard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idCard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idCard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idCard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idCard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idCard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idCard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idCard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andTodateIsNull() {
            addCriterion("toDate is null");
            return (Criteria) this;
        }

        public Criteria andTodateIsNotNull() {
            addCriterion("toDate is not null");
            return (Criteria) this;
        }

        public Criteria andTodateEqualTo(String value) {
            addCriterion("toDate =", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateNotEqualTo(String value) {
            addCriterion("toDate <>", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateGreaterThan(String value) {
            addCriterion("toDate >", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateGreaterThanOrEqualTo(String value) {
            addCriterion("toDate >=", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateLessThan(String value) {
            addCriterion("toDate <", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateLessThanOrEqualTo(String value) {
            addCriterion("toDate <=", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateLike(String value) {
            addCriterion("toDate like", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateNotLike(String value) {
            addCriterion("toDate not like", value, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateIn(List<String> values) {
            addCriterion("toDate in", values, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateNotIn(List<String> values) {
            addCriterion("toDate not in", values, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateBetween(String value1, String value2) {
            addCriterion("toDate between", value1, value2, "todate");
            return (Criteria) this;
        }

        public Criteria andTodateNotBetween(String value1, String value2) {
            addCriterion("toDate not between", value1, value2, "todate");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationIsNull() {
            addCriterion("signingAndIssuingOrganization is null");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationIsNotNull() {
            addCriterion("signingAndIssuingOrganization is not null");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationEqualTo(String value) {
            addCriterion("signingAndIssuingOrganization =", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationNotEqualTo(String value) {
            addCriterion("signingAndIssuingOrganization <>", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationGreaterThan(String value) {
            addCriterion("signingAndIssuingOrganization >", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationGreaterThanOrEqualTo(String value) {
            addCriterion("signingAndIssuingOrganization >=", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationLessThan(String value) {
            addCriterion("signingAndIssuingOrganization <", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationLessThanOrEqualTo(String value) {
            addCriterion("signingAndIssuingOrganization <=", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationLike(String value) {
            addCriterion("signingAndIssuingOrganization like", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationNotLike(String value) {
            addCriterion("signingAndIssuingOrganization not like", value, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationIn(List<String> values) {
            addCriterion("signingAndIssuingOrganization in", values, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationNotIn(List<String> values) {
            addCriterion("signingAndIssuingOrganization not in", values, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationBetween(String value1, String value2) {
            addCriterion("signingAndIssuingOrganization between", value1, value2, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andSigningandissuingorganizationNotBetween(String value1, String value2) {
            addCriterion("signingAndIssuingOrganization not between", value1, value2, "signingandissuingorganization");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathIsNull() {
            addCriterion("frontIdCardImagePath is null");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathIsNotNull() {
            addCriterion("frontIdCardImagePath is not null");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathEqualTo(String value) {
            addCriterion("frontIdCardImagePath =", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathNotEqualTo(String value) {
            addCriterion("frontIdCardImagePath <>", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathGreaterThan(String value) {
            addCriterion("frontIdCardImagePath >", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathGreaterThanOrEqualTo(String value) {
            addCriterion("frontIdCardImagePath >=", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathLessThan(String value) {
            addCriterion("frontIdCardImagePath <", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathLessThanOrEqualTo(String value) {
            addCriterion("frontIdCardImagePath <=", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathLike(String value) {
            addCriterion("frontIdCardImagePath like", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathNotLike(String value) {
            addCriterion("frontIdCardImagePath not like", value, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathIn(List<String> values) {
            addCriterion("frontIdCardImagePath in", values, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathNotIn(List<String> values) {
            addCriterion("frontIdCardImagePath not in", values, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathBetween(String value1, String value2) {
            addCriterion("frontIdCardImagePath between", value1, value2, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andFrontidcardimagepathNotBetween(String value1, String value2) {
            addCriterion("frontIdCardImagePath not between", value1, value2, "frontidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathIsNull() {
            addCriterion("backIdCardImagePath is null");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathIsNotNull() {
            addCriterion("backIdCardImagePath is not null");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathEqualTo(String value) {
            addCriterion("backIdCardImagePath =", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathNotEqualTo(String value) {
            addCriterion("backIdCardImagePath <>", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathGreaterThan(String value) {
            addCriterion("backIdCardImagePath >", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathGreaterThanOrEqualTo(String value) {
            addCriterion("backIdCardImagePath >=", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathLessThan(String value) {
            addCriterion("backIdCardImagePath <", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathLessThanOrEqualTo(String value) {
            addCriterion("backIdCardImagePath <=", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathLike(String value) {
            addCriterion("backIdCardImagePath like", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathNotLike(String value) {
            addCriterion("backIdCardImagePath not like", value, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathIn(List<String> values) {
            addCriterion("backIdCardImagePath in", values, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathNotIn(List<String> values) {
            addCriterion("backIdCardImagePath not in", values, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathBetween(String value1, String value2) {
            addCriterion("backIdCardImagePath between", value1, value2, "backidcardimagepath");
            return (Criteria) this;
        }

        public Criteria andBackidcardimagepathNotBetween(String value1, String value2) {
            addCriterion("backIdCardImagePath not between", value1, value2, "backidcardimagepath");
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