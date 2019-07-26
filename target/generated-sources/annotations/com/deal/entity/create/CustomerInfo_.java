package com.deal.entity.create;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerInfo.class)
public abstract class CustomerInfo_ {

	public static volatile SingularAttribute<CustomerInfo, Long> custId;
	public static volatile SingularAttribute<CustomerInfo, String> custAreacode;
	public static volatile SingularAttribute<CustomerInfo, String> custContryCode;
	public static volatile SingularAttribute<CustomerInfo, ConferenceInfo> confInfo;
	public static volatile SingularAttribute<CustomerInfo, String> custEmail;
	public static volatile SingularAttribute<CustomerInfo, String> custName;
	public static volatile SingularAttribute<CustomerInfo, Integer> custType;
	public static volatile SingularAttribute<CustomerInfo, String> custTel;

}

