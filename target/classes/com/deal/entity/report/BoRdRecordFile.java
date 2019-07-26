package com.deal.entity.report;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会议录音实体
 * 
 * @author zhipeng.xu
 *
 */
@Entity
@Table(name = "bo_rd_recordfile")
public class BoRdRecordFile implements Serializable
{
	private Integer id;
	private String billingcode;
	private Date bgntime;
	private Date endtime;
	private String filename;
	private String filepath;
	private Integer ifsend;
	private String ifhk;
	private String xnum;
	private Date updatetime;

	@Column(name = "updatetime")
	public Date getUpdatetime()
	{
		return updatetime;
	}

	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}

	@Column(name = "wavLocalPath")
	public String getWavLocalPath()
	{
		return wavLocalPath;
	}

	public void setWavLocalPath(String wavLocalPath)
	{
		this.wavLocalPath = wavLocalPath;
	}

	@Column(name = "zipLocalPath")
	public String getZipLocalPath()
	{
		return zipLocalPath;
	}

	public void setZipLocalPath(String zipLocalPath)
	{
		this.zipLocalPath = zipLocalPath;
	}

	@Column(name = "ifDeleted",nullable = false)
	public Integer getIfDeleted()
	{
		return ifDeleted;
	}

	public void setIfDeleted(Integer ifDeleted)
	{
		this.ifDeleted = ifDeleted;
	}

	private String wavLocalPath;
	private String zipLocalPath;
	private Integer ifDeleted;
	private String mp3LocalPath;
	@Column(name = "mp3LocalPath",nullable = false)
	public String getMp3LocalPath(){
		return mp3LocalPath;
	}

	public void setMp3LocalPath(String mp3LocalPath){
		this.mp3LocalPath = mp3LocalPath;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID",unique = true,nullable = false,length = 10)
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "billingCode",nullable = false)
	public String getBillingcode()
	{
		return billingcode;
	}

	public void setBillingcode(String billingcode)
	{
		this.billingcode = billingcode;
	}

	@Column(name = "bgnTime")
	public Date getBgntime()
	{
		return bgntime;
	}

	public void setBgntime(Date bgntime)
	{
		this.bgntime = bgntime;
	}

	@Column(name = "endTime")
	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	@Column(name = "filename",nullable = false)
	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	@Column(name = "filepath",nullable = false)
	public String getFilepath()
	{
		return filepath;
	}

	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

	@Column(name = "ifSend",nullable = false)
	public Integer getIfsend()
	{
		return ifsend;
	}

	public void setIfsend(Integer ifsend)
	{
		this.ifsend = ifsend;
	}

	@Column(name = "ifHK",nullable = false)
	public String getIfhk()
	{
		return ifhk;
	}

	public void setIfhk(String ifhk)
	{
		this.ifhk = ifhk;
	}

	@Column(name = "xnum",nullable = false)
	public String getXnum()
	{
		return xnum;
	}

	public void setXnum(String xnum)
	{
		this.xnum = xnum;
	}
}