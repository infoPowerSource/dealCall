package com.deal.entity.gnet;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;

import org.hibernate.annotations.Table;

@Entity(name = "gnet_ss_mail_sending_task")
public class GnetSsMailSendingTask implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long		id;
	private Timestamp	createTime;
	private Short		isHandled;
	private Short		resultCode;
	private Timestamp	updateTime;
	private String		postfixAddress;
	private String		senderName;
	private String		receiverName;
	private String		senderAddress;
	private String		receiverAddress;
	private String		mailTitle;
	private String		mailContent;
	private Long		ssMailId;
	private Long		ssMailRecipientId;
	private Short		ssSource= 0;
	private String		mailContentExt;
	private Short		type;
	private Short		tgnetSsMailSendingTaskype=0;
	private Short		sendFrom=0;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "create_time", nullable = false, length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "is_handled", nullable = false)
	public Short getIsHandled() {
		return this.isHandled;
	}

	public void setIsHandled(Short isHandled) {
		this.isHandled = isHandled;
	}

	@Column(name = "result_code", nullable = false)
	public Short getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(Short resultCode) {
		this.resultCode = resultCode;
	}

	@Column(name = "update_time", nullable = false, length = 0)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "postfix_address", nullable = false, length = 63)
	public String getPostfixAddress() {
		return this.postfixAddress;
	}

	public void setPostfixAddress(String postfixAddress) {
		this.postfixAddress = postfixAddress;
	}

	@Column(name = "sender_name", nullable = false, length = 100)
	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	@Column(name = "receiver_name", nullable = false, length = 150)
	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Column(name = "sender_address", nullable = false, length = 320)
	public String getSenderAddress() {
		return this.senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	@Column(name = "receiver_address", nullable = false, length = 320)
	public String getReceiverAddress() {
		return this.receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	@Column(name = "mail_title", nullable = false, length = 500)
	public String getMailTitle() {
		return this.mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	@Column(name = "mail_content", nullable = false)
	public String getMailContent() {
		return this.mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	@Column(name = "ss_mail_id", nullable = false)
	public Long getSsMailId() {
		return this.ssMailId;
	}

	public void setSsMailId(Long ssMailId) {
		this.ssMailId = ssMailId;
	}

	@Column(name = "ss_mail_recipient_id", nullable = false)
	public Long getSsMailRecipientId() {
		return this.ssMailRecipientId;
	}

	public void setSsMailRecipientId(Long ssMailRecipientId) {
		this.ssMailRecipientId = ssMailRecipientId;
	}

	@Column(name = "ss_source", nullable = false)
	public Short getSsSource() {
		return this.ssSource;
	}

	public void setSsSource(Short ssSource) {
		this.ssSource = ssSource;
	}

	@Column(name="mail_content_ext")
	public String getMailContentExt() {
		return this.mailContentExt;
	}

	public void setMailContentExt(String mailContentExt) {
		this.mailContentExt = mailContentExt;
	}

	@Column(name = "type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "tgnet_ss_mail_sending_taskype")
	public Short getTgnetSsMailSendingTaskype() {
		return this.tgnetSsMailSendingTaskype;
	}

	public void setTgnetSsMailSendingTaskype(Short tgnetSsMailSendingTaskype) {
		this.tgnetSsMailSendingTaskype = tgnetSsMailSendingTaskype;
	}

	@Column(name = "send_from")
	public Short getSendFrom() {
		return this.sendFrom;
	}

	public void setSendFrom(Short sendFrom) {
		this.sendFrom = sendFrom;
	}

}
