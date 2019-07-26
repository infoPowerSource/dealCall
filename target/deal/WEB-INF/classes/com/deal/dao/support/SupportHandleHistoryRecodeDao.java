package com.deal.dao.support;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.support.SuppportHandleHistoryRecord;

@Component
public class SupportHandleHistoryRecodeDao extends BaseDao{

	public void save(SuppportHandleHistoryRecord historyRecode){
		this.getSession().save(historyRecode);
	}
}
