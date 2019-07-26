package com.deal.dao.create;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferencePasswordInfo;

@Component
public class ConferencePasswordDao extends BaseDao {

	public ConferencePasswordInfo getPassword() {
		return (ConferencePasswordInfo)this.getSession().createQuery("from ConferencePasswordInfo where isUsed=0").setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	public void update(ConferencePasswordInfo passwordInfo) {
		this.getSession().update(passwordInfo);
	}

	public List<ConferencePasswordInfo> getPasswordList(){
		return (List<ConferencePasswordInfo>)this.getSession().createQuery("from ConferencePasswordInfo where isUsed=0").setFirstResult(0).setMaxResults(2).list();
	}

	public ConferencePasswordInfo getPwdByPasswordName(String chairmanPasscode){
		return (ConferencePasswordInfo)this.getSession().createQuery("from ConferencePasswordInfo where isUsed=1 and password=?").setParameter(0,chairmanPasscode).uniqueResult();
	}

	public int getUsingPasscodeNum(){
		 String sqlStr = "SELECT COUNT(1) from tb_conference_password_info a where a.is_use =0";
	     Query query = this.getSession().createSQLQuery(sqlStr);
	     return Integer.parseInt(query.list().get(0).toString());
	}
}
