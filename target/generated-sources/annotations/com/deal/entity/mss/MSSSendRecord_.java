package com.deal.entity.mss;

import com.deal.entity.create.ConferenceInfo;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MSSSendRecord.class)
public abstract class MSSSendRecord_ {

	public static volatile SingularAttribute<MSSSendRecord, String> emailReceiver;
	public static volatile SingularAttribute<MSSSendRecord, Timestamp> sentTime;
	public static volatile SingularAttribute<MSSSendRecord, Integer> emailType;
	public static volatile SingularAttribute<MSSSendRecord, Long> emailId;
	public static volatile SingularAttribute<MSSSendRecord, String> emailSender;
	public static volatile SingularAttribute<MSSSendRecord, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<MSSSendRecord, String> emailContent;
	public static volatile SingularAttribute<MSSSendRecord, Timestamp> sendTime;
	public static volatile SingularAttribute<MSSSendRecord, Short> ifSuccess;
	public static volatile SingularAttribute<MSSSendRecord, String> emailProject;

}

