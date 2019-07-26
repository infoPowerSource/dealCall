package com.deal.dao.sms;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.sms.SMSSendTaskHistory;

@Component
public class SMSSendTaskHistoryDao extends BaseDao{

	public void save(SMSSendTaskHistory history) {
		this.getSession().save(history);
	}
}
