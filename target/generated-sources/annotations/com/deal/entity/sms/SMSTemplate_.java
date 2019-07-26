package com.deal.entity.sms;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SMSTemplate.class)
public abstract class SMSTemplate_ {

	public static volatile SingularAttribute<SMSTemplate, Long> templateId;
	public static volatile SingularAttribute<SMSTemplate, Integer> templateLanguage;
	public static volatile SingularAttribute<SMSTemplate, Integer> templateType;
	public static volatile SingularAttribute<SMSTemplate, String> templateContent;
	public static volatile SingularAttribute<SMSTemplate, Integer> isvalid;

}

