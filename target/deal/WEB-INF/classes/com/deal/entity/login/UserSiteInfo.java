package com.deal.entity.login;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tb_login_user_site_info")
public class UserSiteInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private long siteId;
//	private int plateformId;
	private long contractId;
	private String companyName;
	private String siteUrl;
	private String logoSavePath;
	private int passwordLength;
	private int language = 2;// 0:英文,2:中文
	
	@Id @GeneratedValue
	@Column(name = "id", unique = true, nullable = false, length = 20)
	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	@Column(name = "site_id", unique = true, nullable = false, length = 20)
	public long getSiteId(){
		return siteId;
	}

	public void setSiteId(long siteId){
		this.siteId = siteId;
	}

//	@Column(name = "plateform_id", nullable = false)
//	public int getPlateformId(){
//		return plateformId;
//	}
//
//	public void setPlateformId(int plateformId){
//		this.plateformId = plateformId;
//	}

	@Column(name = "contract_id", nullable = false)
	public long getContractId(){
		return contractId;
	}

	public void setContractId(long contractId){
		this.contractId = contractId;
	}

	@Column(name = "company_name", length = 200, nullable = true)
	public String getCompanyName(){
		return companyName;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	@Column(name = "site_url", length = 100, nullable = false)
	public String getSiteUrl(){
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl){
		this.siteUrl = siteUrl;
	}

	@Column(name = "logo_save_path", length = 200, nullable = true)
	public String getLogoSavePath(){
		return logoSavePath;
	}

	public void setLogoSavePath(String logoSavePath){
		this.logoSavePath = logoSavePath;
	}

	@Column(name = "password_length", nullable = false)
	public int getPasswordLength(){
		return passwordLength;
	}

	public void setPasswordLength(int passwordLength){
		this.passwordLength = passwordLength;
	}

	@Column(name = "language", nullable = false)
	public int getLanguage(){
		return language;
	}

	public void setLanguage(int language){
		this.language = language;
	}
}
