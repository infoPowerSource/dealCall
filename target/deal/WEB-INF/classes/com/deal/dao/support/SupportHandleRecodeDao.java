package com.deal.dao.support;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.support.SupportHandlerRecord;

@Component
public class SupportHandleRecodeDao extends BaseDao{

	public List<SupportHandlerRecord> getThrMonSupportHandlerList(long confId){
		String sqlStr = "from SupportHandlerRecord t where t.confInfo.confId =:confId";
		List<SupportHandlerRecord> list=this.getSession().createQuery(sqlStr).setParameter("confId", confId).list();
		return list;
	}
	public void deleteById(Long id){
		this.getSession().createQuery("delete from SupportHandlerRecord where recordId=:recordId").setParameter("recordId", id).executeUpdate();
	}
	
	public void saveHandlerRecord(SupportHandlerRecord recode){
		this.getSession().save(recode);
	}
}
