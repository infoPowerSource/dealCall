package com.deal.entity.task;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_attemper_task")
public class AttemperTask implements java.io.Serializable {


	private Integer		attemperId;
	private String		attemperIp;
	private Timestamp	attemperTime;


	public AttemperTask() {
	}

	public AttemperTask(Integer attemperId, String attemperIp) {
		this.attemperId = attemperId;
		this.attemperIp = attemperIp;
	}

	public AttemperTask(Integer attemperId, String attemperIp, Timestamp attemperTime) {
		this.attemperId = attemperId;
		this.attemperIp = attemperIp;
		this.attemperTime = attemperTime;
	}

	@Id
	@Column(name = "attemper_id", unique = true, nullable = false)
	public Integer getAttemperId() {
		return this.attemperId;
	}

	public void setAttemperId(Integer attemperId) {
		this.attemperId = attemperId;
	}

	@Column(name = "attemper_ip", nullable = false, length = 50)
	public String getAttemperIp() {
		return this.attemperIp;
	}

	public void setAttemperIp(String attemperIp) {
		this.attemperIp = attemperIp;
	}

	@Column(name = "attemper_time", length = 19)
	public Timestamp getAttemperTime() {
		return this.attemperTime;
	}

	public void setAttemperTime(Timestamp attemperTime) {
		this.attemperTime = attemperTime;
	}

}
