package com.deal.entity.sms;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SMSSendTask.class)
public abstract class SMSSendTask_ {

	public static volatile SingularAttribute<SMSSendTask, String> smsContent;
	public static volatile SingularAttribute<SMSSendTask, Integer> smsType;
	public static volatile SingularAttribute<SMSSendTask, Timestamp> sendTime;
	public static volatile SingularAttribute<SMSSendTask, Timestamp> finishTime;
	public static volatile SingularAttribute<SMSSendTask, Integer> isHandle;
	public static volatile SingularAttribute<SMSSendTask, String> receive;
	public static volatile SingularAttribute<SMSSendTask, Long> smsId;

}

