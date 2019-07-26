package com.deal.dao.create;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.conference.ConferenceCountryCode;

@Component
public class ConferenceCountryCodeDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public List<ConferenceCountryCode> getCountryCode(){
		String sqlStr = "from ConferenceCountryCode order by id";
		return (List<ConferenceCountryCode>)this.getSession().createQuery(sqlStr).list();
	}
}
