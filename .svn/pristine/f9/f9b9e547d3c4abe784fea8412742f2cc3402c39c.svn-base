package com.deal.entity.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 监控中 参会者状态实体
 * 
 * @author zhipeng.xu
 *
 */
@Entity
@Table(name = "tb_party_status_make")
public class PartyInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String partyId;
	private Integer partyRole;
	private Integer partyStatus;
	private Integer isMake;
	private String createTime;
	private String updateTime;
	private Integer partyTime;
	private Long confId;
	private String partyName;
	private String partyPhone;
	
	@Column(name = "party_phone",length = 20)
	public String getPartyPhone()
	{
		return partyPhone;
	}

	public void setPartyPhone(String partyPhone)
	{
		this.partyPhone = partyPhone;
	}

	@Column(name = "party_name",length = 20)
	public String getPartyName()
	{
		return partyName;
	}

	public void setPartyName(String partyName)
	{
		this.partyName = partyName;
	}

	@Column(name = "conf_Id",length = 10,nullable = false)
	public Long getConfId()
	{
		return confId;
	}

	public void setConfId(Long confId)
	{
		this.confId = confId;
	}

	@Column(name = "party_time",nullable = false,length = 10)
	public Integer getPartyTime()
	{
		return partyTime;
	}

	public void setPartyTime(Integer partyTime)
	{
		this.partyTime = partyTime;
	}

	@Id
	@Column(name = "party_id",unique = true,nullable = false,length = 32)
	public String getPartyId()
	{
		return partyId;
	}

	public void setPartyId(String partyId)
	{
		this.partyId = partyId;
	}

	@Column(name = "party_role",length = 2,nullable = false)
	public Integer getPartyRole()
	{
		return partyRole;
	}

	public void setPartyRole(Integer partyRole)
	{
		this.partyRole = partyRole;
	}

	@Column(name = "party_status",length = 2,nullable = false)
	public Integer getPartyStatus()
	{
		return partyStatus;
	}

	public void setPartyStatus(Integer partyStatus)
	{
		this.partyStatus = partyStatus;
	}

	@Column(name = "is_make",length = 2,nullable = false)
	public Integer getIsMake()
	{
		return isMake;
	}

	public void setIsMake(Integer isMake)
	{
		this.isMake = isMake;
	}

	@Column(name = "createTime",nullable = false)
	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	@Column(name = "updateTime",nullable = false)
	public String getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}
}
