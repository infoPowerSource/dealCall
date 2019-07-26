package com.deal.entity.colony;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ColonyHandle.class)
public abstract class ColonyHandle_ {

	public static volatile SingularAttribute<ColonyHandle, String> billingCode;
	public static volatile SingularAttribute<ColonyHandle, Timestamp> colonyTime;
	public static volatile SingularAttribute<ColonyHandle, String> colonyIp;

}

