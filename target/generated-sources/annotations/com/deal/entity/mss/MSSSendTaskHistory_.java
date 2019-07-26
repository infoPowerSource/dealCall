package com.deal.entity.mss;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MSSSendTaskHistory.class)
public abstract class MSSSendTaskHistory_ {

	public static volatile SingularAttribute<MSSSendTaskHistory, Long> recordId;
	public static volatile SingularAttribute<MSSSendTaskHistory, Long> emailId;
	public static volatile SingularAttribute<MSSSendTaskHistory, Integer> emailType;
	public static volatile SingularAttribute<MSSSendTaskHistory, String> emilReceiver;
	public static volatile SingularAttribute<MSSSendTaskHistory, String> emailSender;
	public static volatile SingularAttribute<MSSSendTaskHistory, String> emailContent;
	public static volatile SingularAttribute<MSSSendTaskHistory, Timestamp> sendTime;
	public static volatile SingularAttribute<MSSSendTaskHistory, String> emailContentExt;
	public static volatile SingularAttribute<MSSSendTaskHistory, Timestamp> finishTime;
	public static volatile SingularAttribute<MSSSendTaskHistory, String> emailTitle;

}

