package com.deal.entity.mss;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MSSTemplate.class)
public abstract class MSSTemplate_ {

	public static volatile SingularAttribute<MSSTemplate, Long> templateId;
	public static volatile SingularAttribute<MSSTemplate, Integer> mailTemplateLanguage;
	public static volatile SingularAttribute<MSSTemplate, Integer> templateType;
	public static volatile SingularAttribute<MSSTemplate, String> templateContent;
	public static volatile SingularAttribute<MSSTemplate, String> emailTitle;
	public static volatile SingularAttribute<MSSTemplate, Integer> isValid;

}

