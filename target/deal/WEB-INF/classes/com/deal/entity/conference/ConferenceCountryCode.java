package com.deal.entity.conference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_country_code")
public class ConferenceCountryCode implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String country;
	
	@Id @GeneratedValue
	@Column(name="id",unique=true,nullable=false,length=11)
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	@Column(name="code",nullable=false,length=10)
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	@Column(name="country",nullable=false,length=20)
	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}
}
