package com.deal.entity.support;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_support_authoried_relation")
public class SupportAuthoriedRelation implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long relationId;
	private SupportInfo supportInfo;
	private SupportAuthoriedInfo authoriedInfo;
	
	@Id @GeneratedValue
	@Column(name="relation_id",unique = true,nullable = false,length = 10)
	public long getRelationId()
	{
		return relationId;
	}
	public void setRelationId(long relationId)
	{
		this.relationId = relationId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="support_id",nullable=false)
	public SupportInfo getSupportInfo()
	{
		return supportInfo;
	}
	public void setSupportInfo(SupportInfo supportInfo)
	{
		this.supportInfo = supportInfo;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="authoried_id",nullable=false)
	public SupportAuthoriedInfo getAuthoriedInfo()
	{
		return authoriedInfo;
	}
	public void setAuthoriedInfo(SupportAuthoriedInfo authoriedInfo)
	{
		this.authoriedInfo = authoriedInfo;
	}
}
