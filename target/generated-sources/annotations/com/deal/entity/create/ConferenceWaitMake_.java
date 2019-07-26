package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceWaitMake.class)
public abstract class ConferenceWaitMake_ {

	public static volatile SingularAttribute<ConferenceWaitMake, ConferenceWaitMakeId> id;
	public static volatile SingularAttribute<ConferenceWaitMake, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceWaitMake, Short> ifMake;
	public static volatile SingularAttribute<ConferenceWaitMake, Timestamp> generationTime;
	public static volatile SingularAttribute<ConferenceWaitMake, String> taskResult;
	public static volatile SingularAttribute<ConferenceWaitMake, Timestamp> makeTime;

}

