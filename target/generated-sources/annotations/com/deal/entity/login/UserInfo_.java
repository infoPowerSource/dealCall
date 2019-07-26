package com.deal.entity.login;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserInfo.class)
public abstract class UserInfo_ {

	public static volatile SingularAttribute<UserInfo, String> userBillingCode;
	public static volatile SingularAttribute<UserInfo, Timestamp> createTime;
	public static volatile SingularAttribute<UserInfo, Integer> pcode1InTone;
	public static volatile SingularAttribute<UserInfo, Timestamp> emailCheckTime;
	public static volatile SingularAttribute<UserInfo, String> pcIp;
	public static volatile SingularAttribute<UserInfo, Integer> pcode1OutTone;
	public static volatile SingularAttribute<UserInfo, String> userEmail;
	public static volatile SingularAttribute<UserInfo, String> userCustomerCode;
	public static volatile SingularAttribute<UserInfo, String> telVerify;
	public static volatile SingularAttribute<UserInfo, UserSiteInfo> siteInfo;
	public static volatile SingularAttribute<UserInfo, String> emailDisplayName;
	public static volatile SingularAttribute<UserInfo, Long> id;
	public static volatile SingularAttribute<UserInfo, Integer> pcode2OutTone;
	public static volatile SingularAttribute<UserInfo, String> pcode2Mode;
	public static volatile SingularAttribute<UserInfo, Long> userId;
	public static volatile SingularAttribute<UserInfo, String> emailVerify;
	public static volatile SingularAttribute<UserInfo, Integer> userStatus;
	public static volatile SingularAttribute<UserInfo, Integer> pcode2InTone;
	public static volatile SingularAttribute<UserInfo, String> bridgeName;
	public static volatile SingularAttribute<UserInfo, Timestamp> lastLoginTime;

}

