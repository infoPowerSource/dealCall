package com.deal.dao.create;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.CustomerInfoHistory;

@Component
public class CustomerHistoryDao extends BaseDao{

	public void save(CustomerInfoHistory custHistory){
		this.getSession().save(custHistory);
	}
}
