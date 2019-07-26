package com.deal.entity.create;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConferenceWaitMakeId implements java.io.Serializable {

	// Fields

	private Long	conferenceId;
	private Integer	timerType;

	// Constructors

	/** default constructor */
	public ConferenceWaitMakeId() {
	}

	/** full constructor */
	public ConferenceWaitMakeId(Long conferenceId, Integer timerType) {
		this.conferenceId = conferenceId;
		this.timerType = timerType;
	}

	// Property accessors

	@Column(name = "conference_id", nullable = false)
	public Long getConferenceId() {
		return this.conferenceId;
	}

	public void setConferenceId(Long conferenceId) {
		this.conferenceId = conferenceId;
	}

	@Column(name = "timer_type", nullable = false)
	public Integer getTimerType() {
		return this.timerType;
	}

	public void setTimerType(Integer timerType) {
		this.timerType = timerType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConferenceWaitMakeId))
			return false;
		ConferenceWaitMakeId castOther = (ConferenceWaitMakeId) other;

		return ((this.getConferenceId() == castOther.getConferenceId()) || (this.getConferenceId() != null && castOther.getConferenceId() != null && this.getConferenceId().equals(castOther.getConferenceId()))) && ((this.getTimerType() == castOther.getTimerType()) || (this.getTimerType() != null && castOther.getTimerType() != null && this.getTimerType().equals(castOther.getTimerType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getConferenceId() == null ? 0 : this.getConferenceId().hashCode());
		result = 37 * result + (getTimerType() == null ? 0 : this.getTimerType().hashCode());
		return result;
	}

}
