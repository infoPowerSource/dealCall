package com.deal.service.support;

import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.CustomerInfo;

public interface ISupportHandleService{

	public void saveGalleryConfInf(ConferenceInfo conference, CustomerInfo customer);

	public boolean getDataBybcAndPhone(String billingCode, String phone);

	public void updateGalleryConfEndTime(ConferenceInfo conference, CustomerInfo customer);
}
