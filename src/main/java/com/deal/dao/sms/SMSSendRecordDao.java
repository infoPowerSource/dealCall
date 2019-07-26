package com.deal.dao.sms;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.sms.SMSSendRecord;
import com.deal.entity.support.SupportHandlerRecord;

@Component
public class SMSSendRecordDao extends BaseDao {

	public void save(SMSSendRecord recode) {
		this.getSession().save(recode);
	}

	public List<SMSSendRecord> getThrMonSmsSendRecordList(long confId){
		String sqlStr = "from SMSSendRecord t where t.confInfo.confId =:confId";
		List<SMSSendRecord> list=this.getSession().createQuery(sqlStr).setParameter("confId", confId).list();
		return list;
	}

	public void deleteById(Long id){
		this.getSession().createQuery("delete from SMSSendRecord where smsId=:smsId").setParameter("smsId", id).executeUpdate();
	}

}
