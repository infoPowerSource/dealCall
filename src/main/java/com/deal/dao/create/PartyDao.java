package com.deal.dao.create;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.party.PartyInfo;
import com.deal.util.DateFormatUtil;
import com.google.common.collect.Lists;

@Component
public class PartyDao extends BaseDao{
	public void save(PartyInfo partyInfo){
		Session session=this.getOpenSession();
		session.saveOrUpdate(partyInfo);
		this.closeSession(session);
	}
	
	public PartyInfo getPartyById(PartyInfo partyInfo){
		PartyInfo partyinfo=new PartyInfo();
		Session session=this.getOpenSession();
		partyinfo=(PartyInfo)session.createQuery("from PartyInfo where partyId=?").setParameter(0, partyInfo.getPartyId()).uniqueResult();
		session.close();
		return partyinfo;
	}

	public void updateParty(PartyInfo partyInfo){
		Session session=this.getOpenSession();
		String sql="update PartyInfo set partyName=:partyName,partyPhone=:partyPhone,isMake=:isMake,partyTime=:partyTime,updateTime=:updateTime,partyStatus=:partyStatus where partyId=:partyId";
		Query query = session.createQuery(sql);
		query.setParameter("partyName", partyInfo.getPartyName());
		query.setParameter("partyPhone", partyInfo.getPartyPhone());
		query.setParameter("isMake", partyInfo.getIsMake());
		query.setParameter("partyTime", partyInfo.getPartyTime());
		query.setParameter("updateTime", partyInfo.getUpdateTime());
		query.setParameter("partyStatus", partyInfo.getPartyStatus());
		query.setParameter("partyId", partyInfo.getPartyId());
		query.executeUpdate();
		session.close();
	}

	public List<PartyInfo> getPartyList(){
		String sqlStr = "from PartyInfo t where t.partyRole=0 and t.partyStatus=0 and t.isMake in(0,2) ";
		Session session=this.getOpenSession();
		List<PartyInfo> list=Lists.newArrayList();
	    list = session.createQuery(sqlStr).list();
	    session.close();
		return list;
	}

	public List<PartyInfo> getInfoByPhoneAndConfId(Long confId, String clientCode){
		String sqlStr = "from PartyInfo where partyPhone like '%" + clientCode.substring(clientCode.length() - 8, clientCode.length()) + "' and confId='" + confId + "'";
		Session session=this.getOpenSession();
		List<PartyInfo> list=Lists.newArrayList();
	    list = session.createQuery(sqlStr).list();
	    session.close();
		return list;
	}

	public PartyInfo getPartyById(String partyId){
		PartyInfo partyinfo=new PartyInfo();
		Session session=this.getOpenSession();
		partyinfo=(PartyInfo) session.createQuery("from PartyInfo where partyId=?").setParameter(0,partyId).uniqueResult();
		session.close();
		return partyinfo;
	}

	public void updateAll(List<PartyInfo> mapParty){
		Session session=this.getOpenSession();
		for(PartyInfo partyInfo : mapParty){
			String hql = "update PartyInfo set isMake=:isMake,partyTime=:partyTime,updateTime=:updateTime where partyId=:partyId";
			Query query = session.createQuery(hql);
			query.setParameter("isMake", partyInfo.getIsMake());
			query.setParameter("partyTime", partyInfo.getPartyTime());
			query.setParameter("updateTime", partyInfo.getUpdateTime());
			query.setParameter("partyId", partyInfo.getPartyId());
			query.executeUpdate();
		}
		session.close();
	}

	public void updateForMonit(PartyInfo partyInfo){
		Session session=this.getOpenSession();
		String hql = "update PartyInfo set partyStatus=:partyStatus,updateTime=:updateTime where partyId=:partyId";
		Query query = session.createQuery(hql);
		query.setParameter("partyStatus", partyInfo.getPartyStatus());
		query.setParameter("updateTime", partyInfo.getUpdateTime());
		query.setParameter("partyId", partyInfo.getPartyId());
		query.executeUpdate();
		session.close();
	}

}
