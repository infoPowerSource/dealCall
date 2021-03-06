package com.deal.dao.create;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.party.PartyInfo;
import com.google.common.collect.Lists;

@Component
public class CustomerDao extends BaseDao{
	public void save(CustomerInfo custInfo){
		this.getSession().save(custInfo);
	}

	@SuppressWarnings("unchecked")
	public List<PartyInfo> getPartyInfo(){
		return (List<PartyInfo>) this.getSession().createQuery("from PartyInfo where isMake=0 and partyStatus=0").list();
	}

	/**
	 * 批量更新专家信息表
	 * 
	 * @param updateList
	 */
	public void update(List<PartyInfo> updateList){
		for(PartyInfo partyInfo : updateList){
			this.getSession().update(partyInfo);
		}
	}

	/**
	 * 根据会议Id 查询该会议下的参会人
	 * 
	 * @param confId
	 * @return
	 */
	public List<CustomerInfo> getCustomerInfo(String confId){
		return (List<CustomerInfo>) this.getSession().createQuery("from CustomerInfo where confInfo.confId=" + confId + "").list();
	}

	/**
	 * 根据手机号查询是否存在该参会人
	 * 
	 * @param phone
	 * @param 
	 * @return
	 */
	public List<CustomerInfo> getPartyByPhoneAndName(String phone, Long confId){
		return (List<CustomerInfo>) this.getSession().createQuery("from CustomerInfo where custTel='" + phone + "' and confInfo.confId = '" + confId + "'").list();
	}

	/**
	 * 根据姓名查询是否存在该参会人
	 * 
	 * @param username
	 * @param 
	 * @return
	 */
	public List<CustomerInfo> getPartyByName(String username, Long confId){
		return (List<CustomerInfo>) this.getSession().createQuery("from CustomerInfo where custName='" + username + "' and confInfo.confId = '" + confId + "'").list();
	}

	public List<CustomerInfo> getCustList(String confId){

		return (List<CustomerInfo>) this.getSession().createQuery("from CustomerInfo where confInfo.confId=:confId").setParameter("confId",Long.valueOf(confId)).list();
	}

	public void deleteById(long custId){
		this.getSession().createQuery("delete from CustomerInfo where custId=:custId").setParameter("custId", custId).executeUpdate();
	}

	public List<CustomerInfo> getCustomerInfo(Long confId, int isHost){
		String sqlStr = "from CustomerInfo t where t.confInfo.confId ='" + confId + "' and custType=" + isHost + "";
		List<CustomerInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	public List<CustomerInfo> getHost(String phoneNationalNumber, int isHost, Long confId){
		return (List<CustomerInfo>) this.getSession()
				.createQuery("from CustomerInfo where custTel='" + phoneNationalNumber + "' and custType=" + isHost + " and confInfo.confId='" + confId + "'").list();
	}

	public void updateEntity(CustomerInfo beforeCust){
		this.getSession().merge(beforeCust);
	}

	public CustomerInfo getHostInfo(String queryPhone, int isHost, Long confId){
		Session session =this.getOpenSession();
		CustomerInfo custInfo=new CustomerInfo();
		custInfo=(CustomerInfo)session.createQuery("from CustomerInfo where custTel='" + queryPhone + "'and custType=" + isHost + " and confInfo.confId='" + confId + "'").uniqueResult();
		session.close();
		return custInfo;
	}

	public List<CustomerInfo> getCustListForMonit(String confId){
		Session session=this.getOpenSession();
		List<CustomerInfo> custList=Lists.newArrayList();
		custList=(List<CustomerInfo>)session.createQuery("from CustomerInfo where confInfo.confId=" + confId + "").list();
		session.close();
		return custList;
	}

	public List<CustomerInfo> getCustInfoForMonit(Long confId, int isHost){
		Session session=this.getOpenSession();
		List<CustomerInfo> list=Lists.newArrayList();
		String sqlStr = "from CustomerInfo t where t.confInfo.confId ='" + confId + "' and custType=" + isHost + "";
		list = session.createQuery(sqlStr).list();
		session.close();
		return list;
	}

	public void saveForMonit(CustomerInfo customer){
		Session session=this.getOpenSession();
		session.saveOrUpdate(customer);
		this.closeSession(session);
	}

	public List<Object> getConfCustList(String name, String phone, String email){
		Criteria criteria = this.getSession().createCriteria(CustomerInfo.class);
		ProjectionList projList = Projections.projectionList(); 
		if(!StringUtils.isEmpty(name)){
			criteria.add(Restrictions.like("custName","%"+name+"%"));
		}
		if(!StringUtils.isEmpty(phone)){
			criteria.add(Restrictions.like("custTel", "%"+phone+"%"));
		}
		if(!StringUtils.isEmpty(email)){
			criteria.add(Restrictions.like("custEmail", "%"+email+"%"));
		}
		projList.add(Projections.property("custName"));  
		projList.add(Projections.property("custTel"));
		projList.add(Projections.property("custEmail"));
		projList.add(Projections.property("custContryCode"));
		projList.add(Projections.property("custAreacode"));
		criteria.setProjection(Projections.distinct(projList));
		List<Object> custList=criteria.setFirstResult(0).setMaxResults(5).list();
		return custList;
	}

	public List<CustomerInfo> getThrMonConfCustList(Long confId){
		String sqlStr = "from CustomerInfo t where t.confInfo.confId =:confId";
		List<CustomerInfo> list=this.getSession().createQuery(sqlStr).setParameter("confId", confId).list();
		return list;
	}

	public List<CustomerInfo> getCustListByLongId(Long confID){
		String sqlStr = "from CustomerInfo t where t.confInfo.confId =:confId";
		List<CustomerInfo> list=this.getSession().createQuery(sqlStr).setParameter("confId", confID).list();
		return list;
	}

	public CustomerInfo getCustomerById(Long custId){
		return (CustomerInfo)this.getSession().createQuery("from CustomerInfo t where t.custId=:custId").setParameter("custId", custId).uniqueResult();
	}
}
