package com.deal.service.create.impl;

import com.deal.entity.create.ConferenceInfo;

public interface IConferenceHandleService{

	public boolean updateConfInfo(ConferenceInfo confInfo);

	public boolean updateConfStatusbyBc(String billingCode);

	public ConferenceInfo getConfInfoByBc(String billingCode);
}
