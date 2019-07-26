package com.deal.dao.sms;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.sms.SMSSendHistoryRecord;

@Component
public class SMSSendHistoryRecordDao extends BaseDao{

	public void save(SMSSendHistoryRecord smsHistory){
		this.getSession().save(smsHistory);
	}
}
