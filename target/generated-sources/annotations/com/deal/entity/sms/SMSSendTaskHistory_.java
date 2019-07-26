package com.deal.entity.sms;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SMSSendTaskHistory.class)
public abstract class SMSSendTaskHistory_ {

	public static volatile SingularAttribute<SMSSendTaskHistory, Long> recordId;
	public static volatile SingularAttribute<SMSSendTaskHistory, String> smsContent;
	public static volatile SingularAttribute<SMSSendTaskHistory, Integer> smsType;
	public static volatile SingularAttribute<SMSSendTaskHistory, Timestamp> sendTime;
	public static volatile SingularAttribute<SMSSendTaskHistory, Timestamp> finishTime;
	public static volatile SingularAttribute<SMSSendTaskHistory, Long> smsId;
	public static volatile SingularAttribute<SMSSendTaskHistory, String> receive;

}

