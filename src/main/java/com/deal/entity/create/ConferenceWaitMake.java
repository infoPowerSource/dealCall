package com.deal.entity.create;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "tb_conference_wait_make")
public class ConferenceWaitMake implements java.io.Serializable {

	// Fields

	private ConferenceWaitMakeId	id;
	private Timestamp					generationTime;
	private Short						ifMake;
	private Timestamp					makeTime;
	private String						taskResult;
	private Timestamp					createTime;

	// Constructors

	/** default constructor */
	public ConferenceWaitMake() {
	}

	/** minimal constructor */
	public ConferenceWaitMake(ConferenceWaitMakeId id, Short ifMake, Timestamp createTime) {
		this.id = id;
		this.ifMake = ifMake;
		this.createTime = createTime;
	}

	/** full constructor */
	public ConferenceWaitMake(ConferenceWaitMakeId id, Timestamp generationTime, Short ifMake, Timestamp makeTime, String taskResult, Timestamp createTime) {
		this.id = id;
		this.generationTime = generationTime;
		this.ifMake = ifMake;
		this.makeTime = makeTime;
		this.taskResult = taskResult;
		this.createTime = createTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "conferenceId", column = @Column(name = "conference_id", nullable = false)), @AttributeOverride(name = "timerType", column = @Column(name = "timer_type", nullable = false)) })
	public ConferenceWaitMakeId getId() {
		return this.id;
	}

	public void setId(ConferenceWaitMakeId id) {
		this.id = id;
	}

	@Column(name = "generation_time", length = 0)
	public Timestamp getGenerationTime() {
		return this.generationTime;
	}

	public void setGenerationTime(Timestamp generationTime) {
		this.generationTime = generationTime;
	}

	@Column(name = "if_make", nullable = false)
	public Short getIfMake() {
		return this.ifMake;
	}

	public void setIfMake(Short ifMake) {
		this.ifMake = ifMake;
	}

	@Column(name = "make_time", length = 0)
	public Timestamp getMakeTime() {
		return this.makeTime;
	}

	public void setMakeTime(Timestamp makeTime) {
		this.makeTime = makeTime;
	}

	@Column(name = "task_result", length = 200)
	public String getTaskResult() {
		return this.taskResult;
	}

	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}

	@Column(name = "create_time", nullable = false, length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
