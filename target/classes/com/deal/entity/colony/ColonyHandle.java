package com.deal.entity.colony;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_colony_handle")
public class ColonyHandle implements java.io.Serializable {


	private String 		billingCode;
	private String		colonyIp;
	private Timestamp	colonyTime;


	public ColonyHandle() {
	}

	public ColonyHandle(String billingCode, String colonyIp) {
		this.billingCode = billingCode;
		this.colonyIp = colonyIp;
	}

	public ColonyHandle(String billingCode, String colonyIp, Timestamp colonyTime) {
		this.billingCode = billingCode;
		this.colonyIp = colonyIp;
		this.colonyTime = colonyTime;
	}

	@Id
	@Column(name = "conf_billingCode", unique = true, nullable = false)
	public String getBillingCode() {
		return this.billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	@Column(name = "colony_ip", nullable = false, length = 50)
	public String getColonyIp() {
		return this.colonyIp;
	}

	public void setColonyIp(String colonyIp) {
		this.colonyIp = colonyIp;
	}

	@Column(name = "colony_time", length = 19)
	public Timestamp getColonyTime() {
		return this.colonyTime;
	}

	public void setColonyTime(Timestamp colonyTime) {
		this.colonyTime = colonyTime;
	}

}
