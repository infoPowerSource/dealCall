package com.deal.dao.support;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.support.GalleryConfInfo;
import com.google.common.collect.Lists;

@Component
public class GalleryConfDao extends BaseDao{

	public void save(GalleryConfInfo galInfo){
		this.getSession().save(galInfo);
	}

	public List<Object> getDataByBcAndPhone(String bc, String phone){
		String hql = "from GalleryConfInfo where confBc=:confBc and custPhone=:custPhone and confEndTime is null";
		Session session = this.getOpenSession();
		Query query = this.getSession().createQuery(hql);
		query.setParameter("confBc", bc);
		query.setParameter("custPhone", phone);
		List<Object> galleryList=Lists.newArrayList();
		galleryList=(List<Object>)query.list();
		session.close();
		return galleryList;
	}
	
	public Integer updateGalleryEndTime(String bc, String phone,Timestamp endTime){
		String hql = "update GalleryConfInfo set confEndTime=:confEndTime where confBc=:confBc and custPhone=:custPhone and confEndTime is null";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("confBc", bc);
		query.setParameter("custPhone", phone);
		query.setParameter("confEndTime", endTime);
		return query.executeUpdate();
	}
}
