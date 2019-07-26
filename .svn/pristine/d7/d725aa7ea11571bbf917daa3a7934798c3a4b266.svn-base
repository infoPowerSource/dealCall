package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_conference_info_history")
public class ConferenceInfoHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long recordId;
	private long confId;
	private String confName;
	private String confCreater;
	private Timestamp beginTime;
	private int confDuration; // 单位分钟
	private Timestamp endTime;
	private int confConfig;
	private int tapedMark;
	private int confStatus;
	private int confHandleStatus;
	private String confBillingcode;
	private String chairmanPassword;
	private String partyPassword;
	private String accountBillingcode;
	private String accountCode;
	private Timestamp createTime;
	private String createrTel;
	private String bridgeName;

	@Id
	@GeneratedValue
	@Column(name = "record_id", unique = true, nullable = false, length = 20)
	public long getRecordId(){
		return recordId;
	}

	public void setRecordId(long recordId){
		this.recordId = recordId;
	}

	@Column(name = "conf_id", nullable = false, length = 20)
	public long getConfId(){
		return confId;
	}

	public void setConfId(long confId){
		this.confId = confId;
	}

	@Column(name = "account_code", length = 20, nullable = false)
	public String getAccountCode(){
		return accountCode;
	}

	public void setAccountCode(String accountCode){
		this.accountCode = accountCode;
	}

	@Column(name = "create_tel", length = 20)
	public String getCreaterTel(){
		return createrTel;
	}

	public void setCreaterTel(String createrTel){
		this.createrTel = createrTel;
	}

	@Column(name="conf_name", length = 100,nullable = false)
	public String getConfName(){
		return confName;
	}

	public void setConfName(String confName){
		this.confName = confName;
	}

	@Column(name = "begin_time")
	public Timestamp getBeginTime(){
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime){
		this.beginTime = beginTime;
	}

	@Column(name = "conf_duration", length = 10)
	public int getConfDuration(){
		return confDuration;
	}

	public void setConfDuration(int confDuration){
		this.confDuration = confDuration;
	}

	@Column(name = "conf_config", length = 6)
	public int getConfConfig(){
		return confConfig;
	}

	public void setConfConfig(int confConfig){
		this.confConfig = confConfig;
	}

	@Column(name = "end_time")
	public Timestamp getEndTime(){
		return endTime;
	}

	public void setEndTime(Timestamp endTime){
		this.endTime = endTime;
	}

	@Column(name = "conf_status", nullable = false)
	public int getConfStatus(){
		return confStatus;
	}

	public void setConfStatus(int confStatus){
		this.confStatus = confStatus;
	}

	@Column(name = "conf_handle_status", nullable = false)
	public int getConfHandleStatus(){
		return confHandleStatus;
	}

	public void setConfHandleStatus(int confHandleStatus){
		this.confHandleStatus = confHandleStatus;
	}

	@Column(name = "conf_billingcode", nullable = false)
	public String getConfBillingcode(){
		return confBillingcode;
	}

	public void setConfBillingcode(String confBillingcode){
		this.confBillingcode = confBillingcode;
	}

	@Column(name = "chairman_password", length = 20, nullable = false)
	public String getChairmanPassword(){
		return chairmanPassword;
	}

	public void setChairmanPassword(String chairmanPassword){
		this.chairmanPassword = chairmanPassword;
	}

	@Column(name = "party_password", length = 20, nullable = false)
	public String getPartyPassword(){
		return partyPassword;
	}

	public void setPartyPassword(String partyPassword){
		this.partyPassword = partyPassword;
	}

	@Column(name = "create_time", nullable = false)
	public Timestamp getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Timestamp creaateTime){
		this.createTime = creaateTime;
	}

	@Column(name = "taped_mark", length = 3)
	public int getTapedMark(){
		return tapedMark;
	}

	public void setTapedMark(int tapedMark){
		this.tapedMark = tapedMark;
	}

	@Column(name = "conf_creater", length = 20, nullable = false)
	public String getConfCreater(){
		return confCreater;
	}

	public void setConfCreater(String confCreater){
		this.confCreater = confCreater;
	}

	@Column(name = "account_billingcode", length = 20, nullable = false)
	public String getAccountBillingcode(){
		return accountBillingcode;
	}

	public void setAccountBillingcode(String accountBillingcode){
		this.accountBillingcode = accountBillingcode;
	}

	@Column(name = "bridge_name", length = 10, nullable = false)
	public String getBridgeName(){
		return bridgeName;
	}

	public void setBridgeName(String bridgeName){
		this.bridgeName = bridgeName;
	}
}
