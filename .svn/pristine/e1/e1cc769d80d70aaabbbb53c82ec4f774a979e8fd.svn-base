package com.deal.entity.mss;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tb_email_template")
public class MSSTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	private long templateId;
	private int templateType;
	private String emailTitle;
	private String templateContent;
	private int mailTemplateLanguage = 0;
	private int isValid = 0;

	@Id
	@GeneratedValue
	@Column(name="template_id", unique = true, nullable = false, length = 20)
	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	@Column(name="template_type", nullable = false)
	public int getTemplateType() {
		return templateType;
	}

	public void setTemplateType(int templateType) {
		this.templateType = templateType;
	}

	@Column(name="email_title", length = 100, nullable = false)
	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	@Column(name="template_content", nullable = false, length = 2500)
	@Type(type="text")
	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	@Column(name="mail_template_language", nullable = false)
	public int getMailTemplateLanguage() {
		return mailTemplateLanguage;
	}

	public void setMailTemplateLanguage(int mailTemplateLanguage) {
		this.mailTemplateLanguage = mailTemplateLanguage;
	}
	
	@Column(name="is_valid",nullable=false)
	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
