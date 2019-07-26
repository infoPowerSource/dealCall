package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceReportHistoryInfo.class)
public abstract class ConferenceReportHistoryInfo_ {

	public static volatile SingularAttribute<ConferenceReportHistoryInfo, Long> recordId;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, String> reportUrl;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, Long> confInfo;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, Integer> reportLanguage;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, Integer> isValid;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, String> reportName;
	public static volatile SingularAttribute<ConferenceReportHistoryInfo, Long> reportId;

}

