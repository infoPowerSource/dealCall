package com.deal.entity.gnet;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnetSsMailSendingTask.class)
public abstract class GnetSsMailSendingTask_ {

	public static volatile SingularAttribute<GnetSsMailSendingTask, String> mailTitle;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Timestamp> createTime;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> senderAddress;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Timestamp> updateTime;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Long> ssMailRecipientId;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> mailContentExt;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Short> isHandled;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Short> type;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> receiverAddress;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Long> id;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> senderName;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Short> sendFrom;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Long> ssMailId;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Short> ssSource;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Short> tgnetSsMailSendingTaskype;
	public static volatile SingularAttribute<GnetSsMailSendingTask, Short> resultCode;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> receiverName;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> mailContent;
	public static volatile SingularAttribute<GnetSsMailSendingTask, String> postfixAddress;

}

