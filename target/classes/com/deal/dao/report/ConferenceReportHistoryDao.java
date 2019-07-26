package com.deal.dao.report;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceReportHistoryInfo;

@Component
public class ConferenceReportHistoryDao extends BaseDao{

	public void save(ConferenceReportHistoryInfo reportHistory){
		this.getSession().save(reportHistory);
	}
}
