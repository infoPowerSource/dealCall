package com.deal.dao.report;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceRadioHistoryInfo;

@Component
public class ConferenceRadioHistoryDao extends BaseDao{

	public void save(ConferenceRadioHistoryInfo radioHitory){
		this.getSession().save(radioHitory);
	}

}
