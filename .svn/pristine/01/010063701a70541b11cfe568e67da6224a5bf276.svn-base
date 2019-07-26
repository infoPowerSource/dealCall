package com.deal.entity.support;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_support_authoried_info")
public class SupportAuthoriedInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long authoriedId;
	private String authoriedName;
	private int isValid;
	
	@Id @GeneratedValue
	@Column(name="authoried_id",unique = true,nullable = false,length = 10)
	public long getAuthoriedId()
	{
		return authoriedId;
	}
	public void setAuthoriedId(long authoriedId)
	{
		this.authoriedId = authoriedId;
	}
	
	@Column(name="authoried_name",length=30,nullable=false)
	public String getAuthoriedName()
	{
		return authoriedName;
	}
	public void setAuthoriedName(String authoriedName)
	{
		this.authoriedName = authoriedName;
	}
	
	@Column(name="is_valid",nullable=false)
	public int getIsValid()
	{
		return isValid;
	}
	public void setIsValid(int isValid)
	{
		this.isValid = isValid;
	}
}
