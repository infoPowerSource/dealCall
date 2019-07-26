package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceRadioInfo.class)
public abstract class ConferenceRadioInfo_ {

	public static volatile SingularAttribute<ConferenceRadioInfo, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceRadioInfo, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<ConferenceRadioInfo, String> fileName;
	public static volatile SingularAttribute<ConferenceRadioInfo, Long> radioId;
	public static volatile SingularAttribute<ConferenceRadioInfo, Integer> isValid;
	public static volatile SingularAttribute<ConferenceRadioInfo, String> fileUrl;

}

