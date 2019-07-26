package com.deal.dao.login;

import java.util.List;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.login.UserInfo;

@Component
public class UserInfoDao extends BaseDao{

	public void save(UserInfo user){
		this.getSession().save(user);
	}

	public void delUserbyId(String userId){
		this.getSession().createQuery("delete from UserInfo where userId=?").setParameter(0, userId).executeUpdate();
	}

	public List<UserInfo> getAllUser(){
		return this.getSession().createCriteria(UserInfo.class).list();
	}

	public void updateUser(UserInfo user){
		this.getSession().merge(user);
	}

	public UserInfo getUserByUmsId(String userId){
		return (UserInfo) this.getSession().createQuery("from UserInfo where userId=:userId").setParameter("userId", Long.valueOf(userId)).uniqueResult();
	}

	public UserInfo getValidUserByUmsId(String userId){
		return (UserInfo) this.getSession().createQuery("from UserInfo where userStatus=1 and userId=:userId").setParameter("userId", Long.valueOf(userId)).uniqueResult();
	}
	
	public UserInfo getUserInfoByBillcode(String userBillingcode){
		return (UserInfo) this.getSession().createQuery("from UserInfo where userStatus=1 and userBillingCode=:userBillingcode").setParameter("userBillingcode", userBillingcode).uniqueResult();
	}

}
