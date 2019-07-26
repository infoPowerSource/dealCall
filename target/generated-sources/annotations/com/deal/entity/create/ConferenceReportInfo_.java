package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceReportInfo.class)
public abstract class ConferenceReportInfo_ {

	public static volatile SingularAttribute<ConferenceReportInfo, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceReportInfo, String> reportUrl;
	public static volatile SingularAttribute<ConferenceReportInfo, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<ConferenceReportInfo, Integer> reportLanguage;
	public static volatile SingularAttribute<ConferenceReportInfo, Integer> isValid;
	public static volatile SingularAttribute<ConferenceReportInfo, String> reportName;
	public static volatile SingularAttribute<ConferenceReportInfo, Long> reportId;

}

