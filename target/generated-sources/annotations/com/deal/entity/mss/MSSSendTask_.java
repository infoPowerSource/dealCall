package com.deal.entity.mss;

import com.deal.entity.create.ConferenceInfo;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MSSSendTask.class)
public abstract class MSSSendTask_ {

	public static volatile SingularAttribute<MSSSendTask, String> emailReceiver;
	public static volatile SingularAttribute<MSSSendTask, Integer> emailType;
	public static volatile SingularAttribute<MSSSendTask, Long> emailId;
	public static volatile SingularAttribute<MSSSendTask, String> emailSender;
	public static volatile SingularAttribute<MSSSendTask, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<MSSSendTask, String> emailContent;
	public static volatile SingularAttribute<MSSSendTask, Timestamp> sendTime;
	public static volatile SingularAttribute<MSSSendTask, String> emailContentExt;
	public static volatile SingularAttribute<MSSSendTask, String> receiveName;
	public static volatile SingularAttribute<MSSSendTask, Integer> isHandle;
	public static volatile SingularAttribute<MSSSendTask, String> emailTitle;

}

