package com.deal.entity.create;

import com.deal.entity.mss.MSSSendRecord;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceInfo.class)
public abstract class ConferenceInfo_ {

	public static volatile SingularAttribute<ConferenceInfo, Timestamp> createTime;
	public static volatile SetAttribute<ConferenceInfo, MSSSendRecord> radioSendRecord;
	public static volatile SingularAttribute<ConferenceInfo, Integer> confStatus;
	public static volatile SingularAttribute<ConferenceInfo, String> confBillingcode;
	public static volatile SingularAttribute<ConferenceInfo, String> accountBillingcode;
	public static volatile SingularAttribute<ConferenceInfo, Timestamp> endTime;
	public static volatile SetAttribute<ConferenceInfo, ConferenceReportInfo> confReports;
	public static volatile SingularAttribute<ConferenceInfo, Integer> confDuration;
	public static volatile SingularAttribute<ConferenceInfo, String> accountCode;
	public static volatile SingularAttribute<ConferenceInfo, Integer> ifLimitParty;
	public static volatile SingularAttribute<ConferenceInfo, String> chairmanPassword;
	public static volatile SingularAttribute<ConferenceInfo, Integer> tapedMark;
	public static volatile SingularAttribute<ConferenceInfo, String> bridgeName;
	public static volatile SingularAttribute<ConferenceInfo, String> confCreater;
	public static volatile SingularAttribute<ConferenceInfo, Integer> confHandleStatus;
	public static volatile SingularAttribute<ConferenceInfo, Timestamp> beginTime;
	public static volatile SingularAttribute<ConferenceInfo, Integer> ifMail;
	public static volatile SingularAttribute<ConferenceInfo, String> createrTel;
	public static volatile SingularAttribute<ConferenceInfo, String> partyPassword;
	public static volatile SetAttribute<ConferenceInfo, CustomerInfo> custRelation;
	public static volatile SingularAttribute<ConferenceInfo, Long> confId;
	public static volatile SingularAttribute<ConferenceInfo, Integer> confConfig;
	public static volatile SingularAttribute<ConferenceInfo, String> confName;
	public static volatile SingularAttribute<ConferenceInfo, Integer> ifSms;
	public static volatile SetAttribute<ConferenceInfo, ConferenceRadioInfo> confRadios;

}

