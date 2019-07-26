package com.deal.entity.sms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_sms_template")
public class SMSTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	private long templateId;
	private int templateType;
	private String templateContent;
	private int templateLanguage=0;
	private int isvalid;
	
	@Id @GeneratedValue
	@Column(name="template_id",unique=true,nullable=false,length=20)
	public long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}
	
	@Column(name="template_type",nullable=false)
	public int getTemplateType() {
		return templateType;
	}
	public void setTemplateType(int templateType) {
		this.templateType = templateType;
	}
	
	@Column(name="template_content",nullable=false,length=2500)
	@Type(type="text")
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	
	@Column(name="template_language",nullable = false)
	public int getTemplateLanguage() {
		return templateLanguage;
	}
	public void setTemplateLanguage(int templateLanguage) {
		this.templateLanguage = templateLanguage;
	}
	
	@Column(name="is_valid",nullable=false)
	public int getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(int isvalid) {
		this.isvalid = isvalid;
	}
	
}
