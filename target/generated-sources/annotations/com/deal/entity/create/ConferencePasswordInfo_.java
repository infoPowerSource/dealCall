package com.deal.entity.create;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConferencePasswordInfo.class)
public abstract class ConferencePasswordInfo_ {

	public static volatile SingularAttribute<ConferencePasswordInfo, Integer> isUsed;
	public static volatile SingularAttribute<ConferencePasswordInfo, Integer> passwordLength;
	public static volatile SingularAttribute<ConferencePasswordInfo, Long> passwordId;
	public static volatile SingularAttribute<ConferencePasswordInfo, String> password;
	public static volatile SingularAttribute<ConferencePasswordInfo, Timestamp> useTime;

}

