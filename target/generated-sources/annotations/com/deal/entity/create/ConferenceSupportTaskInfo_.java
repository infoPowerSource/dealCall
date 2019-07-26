package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferenceSupportTaskInfo.class)
public abstract class ConferenceSupportTaskInfo_ {

	public static volatile SingularAttribute<ConferenceSupportTaskInfo, Long> supportID;
	public static volatile SingularAttribute<ConferenceSupportTaskInfo, Timestamp> createTime;
	public static volatile SingularAttribute<ConferenceSupportTaskInfo, Long> confID;
	public static volatile SingularAttribute<ConferenceSupportTaskInfo, Integer> status;
	public static volatile SingularAttribute<ConferenceSupportTaskInfo, Timestamp> updateTime;
	public static volatile SingularAttribute<ConferenceSupportTaskInfo, Integer> confType;

}

