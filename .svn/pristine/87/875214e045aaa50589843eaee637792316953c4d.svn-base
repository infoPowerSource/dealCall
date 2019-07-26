package com.deal.service.support;

import java.sql.Timestamp;
import java.util.List;

import com.deal.entity.conference.ConfQueryDTO;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceSupportForm;
import com.deal.entity.create.ConferenceSupportTaskInfo;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.create.CustomerSupportForm;
import com.deal.entity.support.SupportHandlerRecord;
import com.deal.entity.support.SupportInfo;

public interface ISupportService
{
	public List<SupportInfo> getSupportListByEmail(String email);
	
	public SupportInfo getSupportInfoByEmail(String email,String bridgeName);

	public List<ConferenceSupportForm> getAllConferenceSupport();
	
	public List<CustomerSupportForm> getConferenceCustomerList(Long confId);

	public Integer updateSupportStatusOpen(Long supportID);

	public Integer updateSupportStatusHold(Long supportID);

	public Integer updateSupportStatusClose(Long supportID);

	public Integer updateSupportStatusToWaitClose(Long supportID);
	
	public Integer updateSupportStatusNew(Long supportID);

	public void saveHandlerRecord(SupportHandlerRecord handlerRecord);

	public Integer callProfessional(Long confId,int calltype,Long supportID);
	
	public ConferenceInfo getConferenceInfo(Long confId);
	
	public CustomerInfo getCustomerInfo(Long custId);

	public Integer getConferenceSupportNotOpenList();
	
	public List<ConferenceSupportTaskInfo> getAllNotOpenTask(String bridgeName);

	public Integer updateSupportTelNum(String email,String bridgeName,String telNum);

	public List<ConfQueryDTO> getConfNumForOp(Timestamp beginTime, Timestamp endTime);
}
