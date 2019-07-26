package com.deal.entity.support;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SupportInfo.class)
public abstract class SupportInfo_ {

	public static volatile SingularAttribute<SupportInfo, String> supportEmail;
	public static volatile SingularAttribute<SupportInfo, Timestamp> createTime;
	public static volatile SingularAttribute<SupportInfo, String> partyPassword;
	public static volatile SingularAttribute<SupportInfo, String> meetmeBillingcode;
	public static volatile SingularAttribute<SupportInfo, Long> supportId;
	public static volatile SingularAttribute<SupportInfo, String> supportTel;
	public static volatile SingularAttribute<SupportInfo, String> chairmanPasscode;
	public static volatile SingularAttribute<SupportInfo, String> bridgeName;
	public static volatile SingularAttribute<SupportInfo, Integer> isValid;
	public static volatile SingularAttribute<SupportInfo, String> supportName;

}

