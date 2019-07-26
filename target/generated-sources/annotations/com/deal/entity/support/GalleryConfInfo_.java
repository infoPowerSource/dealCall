package com.deal.entity.support;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GalleryConfInfo.class)
public abstract class GalleryConfInfo_ {

	public static volatile SingularAttribute<GalleryConfInfo, Long> id;
	public static volatile SingularAttribute<GalleryConfInfo, Timestamp> createTime;
	public static volatile SingularAttribute<GalleryConfInfo, Long> confId;
	public static volatile SingularAttribute<GalleryConfInfo, String> confBc;
	public static volatile SingularAttribute<GalleryConfInfo, String> custPhone;
	public static volatile SingularAttribute<GalleryConfInfo, Timestamp> confEndTime;
	public static volatile SingularAttribute<GalleryConfInfo, String> custName;

}

