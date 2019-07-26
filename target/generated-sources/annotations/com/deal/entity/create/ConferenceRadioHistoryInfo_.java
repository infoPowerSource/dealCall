package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceRadioHistoryInfo.class)
public abstract class ConferenceRadioHistoryInfo_ {

	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, Long> recordId;
	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, Long> confInfo;
	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, String> fileName;
	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, Long> radioId;
	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, Integer> isValid;
	public static volatile SingularAttribute<ConferenceRadioHistoryInfo, String> fileUrl;

}

