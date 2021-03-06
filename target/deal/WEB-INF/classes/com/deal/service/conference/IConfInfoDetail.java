package com.deal.service.conference;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.deal.entity.create.AppConferenceDetail;
import com.deal.entity.create.ConferenceDetail;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.CustomerInfo;

public interface IConfInfoDetail{
	public ConferenceDetail getConfDetail(String billingCode, Timestamp beginTime, Timestamp endTime);

	public String getAcmPhone(CustomerInfo custInfo);

	public ConferenceDetail getUnendConfDetailAfterTime4Page(String billingCode,Timestamp beginTime, int pageNum, int pageSize);

	public List<Object> getConfCustList(String name, String phone, String email, String userbillingCode);

	public Map<String, Integer> getOnlineAndEndConfNum(String billingCode);

	List<ConferenceInfo> getConfEndList(String billingCode, int pageNum, int pageSize);
	
	public List<AppConferenceDetail> getAppOnlineConfList(String billingCode,String currPage, Timestamp beginTime);
	
	public List<AppConferenceDetail> getAppEndConfList(String billingCode,String currPage, Timestamp beginTime);
}
