package com.deal.entity.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_conference_report_info")
public class ConferenceReportInfo implements Serializable
{
	private static final long serialVersionUID = 5468994542968141387L;
	private long reportId;
	private String reportName;
	private String reportUrl;
	private int reportLanguage;
	private Timestamp createTime;
	private int isValid;
	@JsonIgnore
	private ConferenceInfo confInfo;
	
	@Id @GeneratedValue
	@Column(name="report_id",unique = true,nullable = false,length = 10)
	public long getReportId()
	{
		return reportId;
	}
	public void setReportId(long reportId)
	{
		this.reportId = reportId;
	}
	
	@Column(name="report_name",length=50,nullable=false)
	public String getReportName()
	{
		return reportName;
	}
	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}
	
	@Column(name="report_url",length=200,nullable=false)
	public String getReportUrl()
	{
		return reportUrl;
	}
	public void setReportUrl(String reportUrl)
	{
		this.reportUrl = reportUrl;
	}
	
	@Column(name="report_language",nullable=false)
	public int getReportLanguage()
	{
		return reportLanguage;
	}
	public void setReportLanguage(int reportLanguage)
	{
		this.reportLanguage = reportLanguage;
	}
	
	@Column(name="create_time",nullable=false)
	public Timestamp getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Timestamp createTime)
	{
		this.createTime = createTime;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="conf_id")
	public ConferenceInfo getConfInfo()
	{
		return confInfo;
	}
	public void setConfInfo(ConferenceInfo confInfo)
	{
		this.confInfo = confInfo;
	}
}
