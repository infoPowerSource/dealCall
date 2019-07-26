package com.deal.dao.create;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceInfoHistory;

@Component
public class ConferenceHistoryDao extends BaseDao{

	public void save(ConferenceInfoHistory confHistory){
		this.getSession().save(confHistory);
	}

}
