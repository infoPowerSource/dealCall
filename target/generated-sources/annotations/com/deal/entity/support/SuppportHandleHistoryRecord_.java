package com.deal.entity.support;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SuppportHandleHistoryRecord.class)
public abstract class SuppportHandleHistoryRecord_ {

	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Long> recordId;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Long> id;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Timestamp> createTime;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, String> outboundConfig;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Long> confInfo;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Long> supportId;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Integer> handlerType;
	public static volatile SingularAttribute<SuppportHandleHistoryRecord, Integer> confType;

}

