package com.deal.entity.create;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name="tb_conference_customer_info")
public class CustomerInfo implements Serializable {
	private static final long serialVersionUID = -6757449009366537835L;
	private long custId;
	private String custName;
	private String custEmail;
	private String custContryCode;
	private String custAreacode;
	private String custTel;
	private int custType;
	@JsonIgnore
	private ConferenceInfo confInfo;
	
	@Id @GeneratedValue
	@Column(name="cust_id",unique=true,nullable=false,length=10)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Column(name="cust_name",length=20,nullable=false)
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	@Column(name="cust_email",length=50)
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	
	@Column(name="cust_areacode",length=10)
	public String getCustAreacode() {
		return custAreacode;
	}
	public void setCustAreacode(String custAreacode) {
		this.custAreacode = custAreacode;
	}
	
	@Column(name="cust_tel",length=20,nullable=false)
	public String getCustTel() {
		return custTel;
	}
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}
	
	@Column(name="cust_type",length=20,nullable=false)
	public int getCustType() {
		return custType;
	}
	public void setCustType(int custType) {
		this.custType = custType;
	}
	
	@Column(name="cust_contry_code",length=10)
	public String getCustContryCode() {
		return custContryCode;
	}
	public void setCustContryCode(String custContryCode) {
		this.custContryCode = custContryCode;
	}
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name="conf_id", nullable=false)
	public ConferenceInfo getConfInfo() {
		return confInfo;
	}
	public void setConfInfo(ConferenceInfo confInfo) {
		this.confInfo = confInfo;
	}

}
