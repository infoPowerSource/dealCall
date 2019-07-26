package com.deal.dao.mss;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.mss.MSSSendTaskHistory;

@Component
public class MailSendTaskHistoryDao extends BaseDao{

	public void save(MSSSendTaskHistory historyTask) {
		this.getSession().save(historyTask);
	}
}
