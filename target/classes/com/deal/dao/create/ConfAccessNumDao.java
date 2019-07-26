package com.deal.dao.create;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceAccessNumber;
import com.deal.entity.login.UserInfo;

@Component
public class ConfAccessNumDao extends BaseDao{

	public ConferenceAccessNumber getAccessNumber(String bridgeName, String language){
		String sqlStr = "from ConferenceAccessNumber t where t.isvalid=1 and t.bridgeName='"+ bridgeName + "' and t.language='" + language+ "' ";
		return (ConferenceAccessNumber)this.getSession().createQuery(sqlStr).uniqueResult();
	}
}
