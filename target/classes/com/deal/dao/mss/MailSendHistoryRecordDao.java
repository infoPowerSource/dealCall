package com.deal.dao.mss;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deal.dao.common.BaseDao;
import com.deal.entity.mss.MSSSendHistoryRecord;

@Component
public class MailSendHistoryRecordDao extends BaseDao{

	public void save(MSSSendHistoryRecord historyRecord){
		this.getSession().save(historyRecord);
	}
}
