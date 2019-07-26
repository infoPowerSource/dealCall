package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_conference_support")
public class ConferenceSupportTaskInfo implements Serializable{
	
	private static final long serialVersionUID = 11L;
	//任务池自增id
	private Long supportID;
	//会议类型  （0:交易 1：掉线 2：大方 3：重要））
	private int confType;
	//任务状态（-1、已取消；0、新任务；1、已领取；2、已完成; 3、完成待外呼成功）
	private int status;
	//任务创建时间
	private Timestamp createTime;
	//任务修改时间
	private Timestamp updateTime;
	//会议id
	private Long confID;
	//private ConferenceInfo confInfo;
	
	@Id
	@GeneratedValue
	@Column(name = "supportID", unique = true, nullable = false, length = 10)
	public Long getSupportID(){
		return supportID;
	}
	public void setSupportID(Long supportID)
	{
		this.supportID = supportID;
	}
	
	@Column(name = "createTime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "updateTime")
	public Timestamp getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime)
	{
		this.updateTime = updateTime;
	}
	
	@Column(name = "status", length = 10)
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	@Column(name = "confType", length = 10)
	public int getConfType()
	{
		return confType;
	}
	public void setConfType(int confType)
	{
		this.confType = confType;
	}

	@Column(name = "confID", length = 10)
	public Long getConfID()
	{
		return confID;
	}
	public void setConfID(Long confID)
	{
		this.confID = confID;
	}
	/*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="conf_id", nullable=false)
	public ConferenceInfo getConfInfo()
	{
		return confInfo;
	}
	public void setConfInfo(ConferenceInfo confInfo)
	{
		this.confInfo = confInfo;
	}*/
}
