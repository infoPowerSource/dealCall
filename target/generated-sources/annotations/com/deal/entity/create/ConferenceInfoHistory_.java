package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceInfoHistory.class)
public abstract class ConferenceInfoHistory_ {

	public static volatile SingularAttribute<ConferenceInfoHistory, String> confCreater;
	public static volatile SingularAttribute<ConferenceInfoHistory, Long> recordId;
	public static volatile SingularAttribute<ConferenceInfoHistory, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceInfoHistory, Integer> confHandleStatus;
	public static volatile SingularAttribute<ConferenceInfoHistory, Integer> confStatus;
	public static volatile SingularAttribute<ConferenceInfoHistory, Timestamp> beginTime;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> accountBillingcode;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> confBillingcode;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> createrTel;
	public static volatile SingularAttribute<ConferenceInfoHistory, Timestamp> endTime;
	public static volatile SingularAttribute<ConferenceInfoHistory, Integer> confDuration;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> partyPassword;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> accountCode;
	public static volatile SingularAttribute<ConferenceInfoHistory, Long> confId;
	public static volatile SingularAttribute<ConferenceInfoHistory, Integer> confConfig;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> chairmanPassword;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> confName;
	public static volatile SingularAttribute<ConferenceInfoHistory, Integer> tapedMark;
	public static volatile SingularAttribute<ConferenceInfoHistory, String> bridgeName;

}

