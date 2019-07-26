package com.deal.entity.mss;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MSSSendHistoryRecord.class)
public abstract class MSSSendHistoryRecord_ {

	public static volatile SingularAttribute<MSSSendHistoryRecord, String> emailReceiver;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Long> id;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Long> confId;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Timestamp> sentTime;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Integer> emailType;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Long> emailId;
	public static volatile SingularAttribute<MSSSendHistoryRecord, String> emailSender;
	public static volatile SingularAttribute<MSSSendHistoryRecord, String> emailContent;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Timestamp> sendTime;
	public static volatile SingularAttribute<MSSSendHistoryRecord, Short> ifSuccess;
	public static volatile SingularAttribute<MSSSendHistoryRecord, String> emailProject;

}

