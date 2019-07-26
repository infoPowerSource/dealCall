package com.deal.dao.create;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceBillingcodeInfo;
import com.deal.entity.create.ConferencePasswordInfo;

@Component
public class ConferenceBillingcodeDao extends BaseDao {

	public ConferenceBillingcodeInfo getBillingCode() {
		return (ConferenceBillingcodeInfo)this.getSession().createQuery("from ConferenceBillingcodeInfo where isUsed=0").setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	public void update(ConferenceBillingcodeInfo billingcodeInfo) {
		this.getSession().update(billingcodeInfo);
	}
	
	public ConferenceBillingcodeInfo getEntityByBc(String confBillingcode){
		return (ConferenceBillingcodeInfo)this.getSession().createQuery("from ConferenceBillingcodeInfo where isUsed=1 and billingcode=?").setParameter(0,confBillingcode).uniqueResult();
	}

	public int getUsingbillingNum(){
		 String sqlStr = "SELECT COUNT(1) from tb_conference_billingcode_info a where a.is_used =0";
	     Query query = this.getSession().createSQLQuery(sqlStr);
	     return Integer.parseInt(query.list().get(0).toString());
	}

}
