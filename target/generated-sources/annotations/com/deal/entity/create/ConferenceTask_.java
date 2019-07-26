package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceTask.class)
public abstract class ConferenceTask_ {

	public static volatile SingularAttribute<ConferenceTask, Long> taskId;
	public static volatile SingularAttribute<ConferenceTask, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceTask, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<ConferenceTask, Integer> isFinish;
	public static volatile SingularAttribute<ConferenceTask, Integer> taskType;
	public static volatile SingularAttribute<ConferenceTask, Timestamp> handleTime;

}

