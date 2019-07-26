package com.deal.entity.task;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AttemperTask.class)
public abstract class AttemperTask_ {

	public static volatile SingularAttribute<AttemperTask, String> attemperIp;
	public static volatile SingularAttribute<AttemperTask, Timestamp> attemperTime;
	public static volatile SingularAttribute<AttemperTask, Integer> attemperId;

}

