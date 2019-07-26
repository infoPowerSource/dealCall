package com.deal.entity.sms;

import com.deal.entity.create.ConferenceInfo;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SMSSendRecord.class)
public abstract class SMSSendRecord_ {

	public static volatile SingularAttribute<SMSSendRecord, String> content;
	public static volatile SingularAttribute<SMSSendRecord, Timestamp> sentTime;
	public static volatile SingularAttribute<SMSSendRecord, String> receiver;
	public static volatile SingularAttribute<SMSSendRecord, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<SMSSendRecord, Integer> smsType;
	public static volatile SingularAttribute<SMSSendRecord, Integer> ifSuccess;
	public static volatile SingularAttribute<SMSSendRecord, Long> smsId;

}

