package com.deal.entity.support;

import com.deal.entity.create.ConferenceInfo;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SupportHandlerRecord.class)
public abstract class SupportHandlerRecord_ {

	public static volatile SingularAttribute<SupportHandlerRecord, Long> recordId;
	public static volatile SingularAttribute<SupportHandlerRecord, Timestamp> createTime;
	public static volatile SingularAttribute<SupportHandlerRecord, String> outboundConfig;
	public static volatile SingularAttribute<SupportHandlerRecord, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<SupportHandlerRecord, SupportInfo> supportInfo;
	public static volatile SingularAttribute<SupportHandlerRecord, Integer> handlerType;
	public static volatile SingularAttribute<SupportHandlerRecord, Integer> confType;

}

