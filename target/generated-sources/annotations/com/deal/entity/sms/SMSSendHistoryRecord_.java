package com.deal.entity.sms;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SMSSendHistoryRecord.class)
public abstract class SMSSendHistoryRecord_ {

	public static volatile SingularAttribute<SMSSendHistoryRecord, String> content;
	public static volatile SingularAttribute<SMSSendHistoryRecord, Long> id;
	public static volatile SingularAttribute<SMSSendHistoryRecord, Long> confId;
	public static volatile SingularAttribute<SMSSendHistoryRecord, Timestamp> sentTime;
	public static volatile SingularAttribute<SMSSendHistoryRecord, String> receiver;
	public static volatile SingularAttribute<SMSSendHistoryRecord, Integer> smsType;
	public static volatile SingularAttribute<SMSSendHistoryRecord, Integer> ifSuccess;
	public static volatile SingularAttribute<SMSSendHistoryRecord, Long> smsId;

}

