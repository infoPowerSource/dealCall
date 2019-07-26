package com.deal.entity.login;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "tb_login_user_info")
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;

	// 以下放UserAllInfo中
	private long userId;
	// private String siteId;
	private String userCustomerCode;
	private String userBillingCode;
	// private String userMobileNumber;

	// 定义： 0-停用；1-启用；2-删除；
	// 操作： type=create/enable/update 是 1; type=disable是 0; type=delete是2
	private int userStatus;
	private Timestamp createTime;
	private UserSiteInfo siteInfo;
	//plateformName
	private String bridgeName = "";
	private String pcIp;
	private Timestamp lastLoginTime;
	//@propType = 2
	private int pcode1InTone;
	private int pcode1OutTone;
	private String pcode2Mode;
	private int pcode2InTone;
	private int pcode2OutTone;
	
	// 以下不放UserAllInfo中
	private String telVerify;
	private Timestamp emailCheckTime;
	private String emailVerify;
	private String emailDisplayName;
	private String userEmail;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false, length = 20)
	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	@Column(name = "user_id", unique = true, nullable = false, length = 20)
	public long getUserId(){
		return userId;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}

	@Column(name = "user_customercode", nullable = false, length = 20)
	public String getUserCustomerCode(){
		return userCustomerCode;
	}

	public void setUserCustomerCode(String userCustomerCode){
		this.userCustomerCode = userCustomerCode;
	}

	@Column(name = "user_billingcode", unique = true, nullable = false, length = 20)
	public String getUserBillingCode(){
		return userBillingCode;
	}

	public void setUserBillingCode(String userBillingCode){
		this.userBillingCode = userBillingCode;
	}

	// @Column(name = "user_mobileNumber", nullable = true, length = 20)
	// public String getUserMobileNumber(){
	// return userMobileNumber;
	// }
	//
	// public void setUserMobileNumber(String userMobileNumber){
	// this.userMobileNumber = userMobileNumber;
	// }

	@Column(name = "telVerify", nullable = true, length = 4)
	public String getTelVerify(){
		return telVerify;
	}

	public void setTelVerify(String telVerify){
		this.telVerify = telVerify;
	}

	@Column(name = "user_status", nullable = false)
	public int getUserStatus(){
		return userStatus;
	}

	public void setUserStatus(int userStatus){
		this.userStatus = userStatus;
	}

	@Column(name = "create_time", nullable = false)
	public Timestamp getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	// @Column(name = "site_id", nullable = false, length = 20)
	// public String getSiteId(){
	// return String.valueOf(siteInfo.getSiteId());
	// }
	//
	// public void setSiteId(String siteId){
	//// this.siteId = siteId;
	// }

	@Column(name = "email_checkTime", nullable = true)
	public Timestamp getEmailCheckTime(){
		return emailCheckTime;
	}

	public void setEmailCheckTime(Timestamp emailCheckTime){
		this.emailCheckTime = emailCheckTime;
	}

	@Column(name = "emailVerify", nullable = true, length = 32)
	public String getEmailVerify(){
		return emailVerify;
	}

	public void setEmailVerify(String emailVerify){
		this.emailVerify = emailVerify;
	}

	@Column(name = "emailDisplayName", nullable = true, length = 50)
	public String getEmailDisplayName(){
		return emailDisplayName;
	}

	public void setEmailDisplayName(String emailDisplayName){
		this.emailDisplayName = emailDisplayName;
	}

	@Column(name = "userEmail", nullable = true, length = 50)
	public String getUserEmail(){
		return userEmail;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	@Column(name = "bridgeName", nullable = false, length = 32)
	public String getBridgeName(){
		return bridgeName;
	}

	public void setBridgeName(String bridgeName){
		this.bridgeName = bridgeName;
	}
	
	@Column(name = "pc_ip", nullable = true, length = 20)
	public String getPcIp(){
		return pcIp;
	}

	public void setPcIp(String pcIp){
		this.pcIp = pcIp;
	}
	
	@Column(name = "lastlogin_time", nullable = true)
	public Timestamp getLastLoginTime(){
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "pcode1InTone", nullable = false, columnDefinition="INT default -1")
	public int getPcode1InTone(){
		return pcode1InTone;
	}

	public void setPcode1InTone(int pcode1InTone){
		this.pcode1InTone = pcode1InTone;
	}

	@Column(name = "pcode1OutTone", nullable = false, columnDefinition="INT default -1")
	public int getPcode1OutTone(){
		return pcode1OutTone;
	}

	public void setPcode1OutTone(int pcode1OutTone){
		this.pcode1OutTone = pcode1OutTone;
	}

	@Column(name = "pcode2Mode", nullable = false, length = 10)
	public String getPcode2Mode(){
		return pcode2Mode;
	}

	public void setPcode2Mode(String pcode2Mode){
		this.pcode2Mode = pcode2Mode;
	}

	@Column(name = "pcode2InTone", nullable = false, columnDefinition="INT default -1")
	public int getPcode2InTone(){
		return pcode2InTone;
	}

	public void setPcode2InTone(int pcode2InTone){
		this.pcode2InTone = pcode2InTone;
	}

	@Column(name = "pcode2OutTone", nullable = false, columnDefinition="INT default -1")
	public int getPcode2OutTone(){
		return pcode2OutTone;
	}

	public void setPcode2OutTone(int pcode2OutTone){
		this.pcode2OutTone = pcode2OutTone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id", nullable = false)
	public UserSiteInfo getSiteInfo(){
		return siteInfo;
	}

	public void setSiteInfo(UserSiteInfo siteInfo){
		this.siteInfo = siteInfo;
	}
}
