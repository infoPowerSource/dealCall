package com.deal.entity.support;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_gallery_conf_info")
public class GalleryConfInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long Id;
	private long confId;
	private String custName;
	private String custPhone;
	private Timestamp createTime;
    private String confBc;
    private Timestamp confEndTime;
    
	@Id @GeneratedValue
	@Column(name="id",unique = true,nullable = false,length = 10)
	public long getId(){
		return Id;
	}
	public void setId(long id){
		Id = id;
	}
	@Column(name="conf_id",length=20,nullable=false)
	public long getConfId(){
		return confId;
	}
	public void setConfId(long confId){
		this.confId = confId;
	}
	@Column(name="cust_name",length=30)
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}
	@Column(name="cust_phone",length=20,nullable=false)
	public String getCustPhone(){
		return custPhone;
	}
	public void setCustPhone(String custPhone){
		this.custPhone = custPhone;
	}
	@Column(name="create_time",nullable=false)
	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}
	@Column(name="conf_bc",length=20,nullable=false)
	public String getConfBc(){
		return confBc;
	}
	public void setConfBc(String confBc){
		this.confBc = confBc;
	}
	@Column(name="conf_end_time",nullable=true)
	public Timestamp getConfEndTime(){
		return confEndTime;
	}
	public void setConfEndTime(Timestamp confEndTime){
		this.confEndTime = confEndTime;
	}
}
