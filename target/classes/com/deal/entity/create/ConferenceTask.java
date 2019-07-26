package com.deal.entity.create;

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
@Table(name="tb_conference_task")
public class ConferenceTask implements Serializable
{
	private static final long serialVersionUID = -3856059906430950002L;
	private long taskId;
	private int taskType;
	private Timestamp createTime;
	private Timestamp handleTime;
	private int isFinish;
	private ConferenceInfo confInfo;
	
	@Id @GeneratedValue
	@Column(name="task_id",unique = true,nullable = false,length = 10)
	public long getTaskId()
	{
		return taskId;
	}
	public void setTaskId(long taskId)
	{
		this.taskId = taskId;
	}
	
	@Column(name="task_type",nullable=false)
	public int getTaskType()
	{
		return taskType;
	}
	public void setTaskType(int taskType)
	{
		this.taskType = taskType;
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
	
	@Column(name="handle_time")
	public Timestamp getHandleTime()
	{
		return handleTime;
	}
	public void setHandleTime(Timestamp handleTime)
	{
		this.handleTime = handleTime;
	}
	
	@Column(name="is_finish",nullable=false)
	public int getIsFinish()
	{
		return isFinish;
	}
	public void setIsFinish(int isFinish)
	{
		this.isFinish = isFinish;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="conf_id", nullable=false)
	public ConferenceInfo getConfInfo()
	{
		return confInfo;
	}
	public void setConfInfo(ConferenceInfo confInfo)
	{
		this.confInfo = confInfo;
	}
}
