package com.deal.dao.login.syncboss;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.entity.login.UserSiteInfo;

@Component
public class UserSiteDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void saveSite(UserSiteInfo userSiteInfo){
		this.getSession().save(userSiteInfo);
	}

	public void updateSite(UserSiteInfo userSiteInfo){
		this.getSession().merge(userSiteInfo);
	}

	public void delSitebyId(long siteId){
		this.getSession().createQuery("delete from UserSiteInfo where siteId=?").setParameter(0, siteId).executeUpdate();
	}

	public List<UserSiteInfo> getAllSite(){
		return this.getSession().createCriteria(UserSiteInfo.class).list();
	}

	public UserSiteInfo getSiteById(long siteId){
		return (UserSiteInfo) this.getSession().createQuery("from UserSiteInfo where siteId=?").setParameter(0, siteId).uniqueResult();
	}

}
