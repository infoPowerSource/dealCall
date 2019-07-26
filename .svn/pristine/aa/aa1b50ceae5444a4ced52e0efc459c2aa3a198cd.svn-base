package com.deal.dao.create;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.CustomerInfo;
import com.deal.util.Consts;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ConferenceDao extends BaseDao{
	public void save(ConferenceInfo confInfo){
		this.getSession().save(confInfo);
	}

	public ConferenceInfo getConfInfoById(String confId){
		return (ConferenceInfo) this.getSession().createQuery("from ConferenceInfo where confId=?").setParameter(0, Long.valueOf(confId)).uniqueResult();
	}

	/**
	 * 根据客户billingcode和日期查询会议信息
	 *
	 * @param billingCode
	 * @param beginTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return
	 */
	public List<ConferenceInfo> getConfByCreateAndTimeWithPage(String billingCode, Timestamp beginTime, Timestamp endTime, int pageNum, int pageSize){
		String sqlStr = "SELECT a.* from tb_conference_info a where a.conf_status !=3 and a.account_billingcode= :billingCode"
				+ " and a.begin_time>= :beginTime and a.begin_time<= :endIime" + " ORDER BY a.begin_time DESC,a.conf_id ASC";
		List<ConferenceInfo> list = this.getSession().createSQLQuery(sqlStr).addEntity(ConferenceInfo.class).setParameter("billingCode", billingCode)
				.setParameter("beginTime", beginTime).setParameter("endIime", endTime).setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		return list;
	}

	/**
	 * 根据客户billingcode和日期查询会议信息
	 *
	 * @param billingCode
	 * @param beginTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return
	 */
	public List<ConferenceInfo> getConfByCreateAndTime(String billingCode, Timestamp beginTime, Timestamp endTime){
		String sqlStr = "from ConferenceInfo t where t.beginTime>='" + beginTime + "' and t.beginTime<='" + endTime + "' and t.accountBillingcode ='" + billingCode
				+ "' and t.confStatus!=3 ORDER BY t.confStatus DESC,t.beginTime ";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	public void update(ConferenceInfo confInfo){
		this.getSession().update(confInfo);
	}

	public ConferenceInfo getConfInfoByBillingCode(String userBillingCode){
		return (ConferenceInfo) this.getSession().createQuery("from ConferenceInfo where accountBillingcode=:accountBillingcode")
				.setParameter("accountBillingcode", userBillingCode).uniqueResult();
	}

	public ConferenceInfo getConfInfoByBc(String billingCode){
		return (ConferenceInfo) this.getSession().createQuery("from ConferenceInfo where confBillingcode=:confBillingcode").setParameter("confBillingcode", billingCode)
				.uniqueResult();
	}

	/**
	 * 根据会议billingCode 查询会议实体信息
	 *
	 * @param BillingCode
	 * @return
	 */
	public ConferenceInfo getConfByBillingCode(String billingCode){
		Session session = this.getOpenSession();
		ConferenceInfo confInfo = new ConferenceInfo();
		confInfo = (ConferenceInfo) session.createQuery("from ConferenceInfo where confBillingcode=?").setParameter(0, billingCode).uniqueResult();
		session.close();
		return confInfo;
	}

	/**
	 * 根据日期查询即将开始的会议信息
	 *
	 * @param time
	 */
	public List<ConferenceInfo> getConfByDate(String time){
		String sqlStr = "from ConferenceInfo t where t.beginTime='" + time + "' ";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	/**
	 * 根据acm传入的billingCode 判断是否是主动服务会议
	 *
	 * @param billingCode
	 * @return
	 */
	public ConferenceInfo getMeeting(String billingCode){
		Session session = this.getOpenSession();
		ConferenceInfo confInfo = new ConferenceInfo();
		confInfo = (ConferenceInfo) session.createQuery("from ConferenceInfo where confBillingcode=?").setParameter(0, billingCode).uniqueResult();
		session.close();
		return confInfo;
	}

	public List<ConferenceInfo> getEndConf(String timeEnd, int type){
		String sqlStr = "from ConferenceInfo t where t.endTime<='" + timeEnd + "' and t.confStatus!=" + type + " and t.confStatus!=3";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	public List<Timestamp> geAllConfTodoWithOutTime(String billingCode, Timestamp beginTime){
		String sqlStr = "select distinct t.begin_time from tb_conference_info t where conf_status !=3 and t.account_billingcode='" + billingCode + "' ";
		List<Timestamp> list = this.getSession().createSQLQuery(sqlStr).list();
		return list;
	}

	public List<ConferenceInfo> getEndConfList(){
		String sqlStr = "from ConferenceInfo t where confHandleStatus=0 and confStatus in(2,3)";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setFirstResult(0).setMaxResults(5000).list();
		return list;
	}

	public ConferenceInfo getConfInfoForMonitById(String confId){
		Session session = this.getOpenSession();
		ConferenceInfo confInfo = new ConferenceInfo();
		confInfo = (ConferenceInfo) session.createQuery("from ConferenceInfo where confId=?").setParameter(0, Long.valueOf(confId)).uniqueResult();
		session.close();
		return confInfo;
	}

	public void updateForMonit(ConferenceInfo conf){
		Session session = this.getOpenSession();
		String hql = "update ConferenceInfo set confStatus=? where confId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, conf.getConfStatus());
		query.setParameter(1, conf.getConfId());
		query.executeUpdate();
		session.close();
	}

	public List<ConferenceInfo> getUnendConfListAfterTime4Page(String billingCode, Timestamp beginTime, int pageNum, int pageSize){
		String sqlStr = "from ConferenceInfo a where a.confStatus in(0,1,4,5) and a.accountBillingcode= :billingCode" + " and a.beginTime >= :beginTime"
				+ " ORDER BY a.beginTime ASC,a.confId ASC";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setParameter("billingCode", billingCode).setParameter("beginTime", beginTime)
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		return list;
	}

	public List<ConferenceInfo> getSearchConfList(String keyword, String billingCode, int pageNum, int pageSize){
		String sqlStr = "SELECT a.* from tb_conference_info a where (a.conf_name like :confName" + " OR (a.conf_id in (select b.conf_id from tb_conference_customer_info b"
				+ " where CONCAT(b.cust_name,b.cust_tel,b.cust_email) like :cust))" + ") and a.conf_status !=3 and a.account_billingcode= :billingCode"
				+ " ORDER BY a.begin_time DESC,a.conf_id ASC";
		List<ConferenceInfo> list = this.getSession().createSQLQuery(sqlStr).addEntity(ConferenceInfo.class).setParameter("confName", "%" + keyword + "%")
				.setParameter("cust", "%" + keyword + "%").setParameter("billingCode", billingCode).setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		return list;
	}

	public List<ConferenceInfo> getConfListBeforeThrMon(Timestamp beforeThrMonTime){
		String sqlStr = "from ConferenceInfo t where t.endTime <='" + beforeThrMonTime + "' ORDER BY t.endTime ";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	public void deleteById(long confId){
		this.getSession().createQuery("delete from ConferenceInfo where confId=:confId").setParameter("confId", confId).executeUpdate();
	}

	public int getSearchConfListNum(String keyword, String billingCode){
		String sqlStr = "SELECT COUNT(1) from tb_conference_info a where (a.conf_name like :confName" + " OR (a.conf_id in (select b.conf_id from tb_conference_customer_info b"
				+ " where CONCAT(b.cust_name,b.cust_tel,b.cust_email) like :cust))" + ") and a.conf_status !=3 and a.account_billingcode= :billingCode";
		Query query = this.getSession().createSQLQuery(sqlStr).setParameter("confName", "%" + keyword + "%").setParameter("cust", "%" + keyword + "%").setParameter("billingCode",
				billingCode);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public List<ConferenceInfo> getSearchConfListWithTime(String keyword, String billingCode, Timestamp beginTime, Timestamp endTime, int pageNum, int pageSize){
		String sqlStr = "SELECT a.* from tb_conference_info a where (a.conf_name like :confName" + " OR (a.conf_id in (select b.conf_id from tb_conference_customer_info b"
				+ " where CONCAT(b.cust_name,b.cust_tel,b.cust_email) like :cust))" + ") and a.conf_status !=3 and a.account_billingcode= :billingCode"
				+ " and a.begin_time>= :beginTime and a.begin_time<= :endIime" + " ORDER BY a.begin_time DESC,a.conf_id ASC";
		List<ConferenceInfo> list = this.getSession().createSQLQuery(sqlStr).addEntity(ConferenceInfo.class).setParameter("confName", "%" + keyword + "%")
				.setParameter("cust", "%" + keyword + "%").setParameter("billingCode", billingCode).setParameter("beginTime", beginTime).setParameter("endIime", endTime)
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		return list;
	}

	public int getSearchConfListNumForTime(String billingCode, Timestamp beginTime, Timestamp endTime){
		String sqlStr = "SELECT COUNT(1) from tb_conference_info a where a.conf_status !=3 and a.account_billingcode= :billingCode"
				+ " and a.begin_time>= :beginTime and a.begin_time<= :endIime";
		Query query = this.getSession().createSQLQuery(sqlStr).setParameter("billingCode", billingCode).setParameter("beginTime", beginTime).setParameter("endIime", endTime);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public int getSearchConfListNumWithTime(String keyword, String billingCode, Timestamp beginTime, Timestamp endTime){
		String sqlStr = "SELECT COUNT(1) from tb_conference_info a where (a.conf_name like :confName" + " OR (a.conf_id in (select b.conf_id from tb_conference_customer_info b"
				+ " where CONCAT(b.cust_name,b.cust_tel,b.cust_email) like :cust))" + ") and a.conf_status !=3 and a.account_billingcode= :billingCode"
				+ " and a.begin_time>= :beginTime and a.begin_time<= :endIime";
		Query query = this.getSession().createSQLQuery(sqlStr).setParameter("confName", "%" + keyword + "%").setParameter("cust", "%" + keyword + "%")
				.setParameter("billingCode", billingCode).setParameter("beginTime", beginTime).setParameter("endIime", endTime);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public int getOnlineConfNum(String billingCode, Timestamp beginTime){
		String sqlStr = "SELECT COUNT(1) from tb_conference_info a where a.begin_time>='" + beginTime + "' and a.conf_status in(0,1,4,5) and a.account_billingcode='" + billingCode
				+ "'";
		Query query = this.getSession().createSQLQuery(sqlStr);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public int getEndConfNum(String billingCode, Timestamp beginTime){
		String sqlStr = "SELECT COUNT(1) from tb_conference_info a where a.begin_time<='" + beginTime + "' and a.conf_status=2 and a.account_billingcode='" + billingCode + "'";
		Query query = this.getSession().createSQLQuery(sqlStr);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public List<ConferenceInfo> getEndConfListByTime(String billingCode, Timestamp beginTime, int pageNum, int pageSize){
		String sqlStr = "from ConferenceInfo t where t.beginTime <= :beginTime and t.accountBillingcode =:billingCode and t.confStatus=2 ORDER BY t.beginTime DESC,t.confId";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setParameter("billingCode", billingCode).setParameter("beginTime", beginTime)
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		return list;
	}

	public List<ConferenceInfo> getOnlineConfListByTime(String billingCode, int beginNum, Timestamp beginTime){
		String sqlStr = "from ConferenceInfo t where t.beginTime>='" + beginTime + "' and t.accountBillingcode ='" + billingCode
				+ "' and t.confStatus in(0,1,4,5) ORDER BY t.beginTime ";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setFirstResult(beginNum).setMaxResults(Consts.APP_CONF_LIST_COUNT_FOR_EACHPAGE).list();
		return list;
	}

	public List<ConferenceInfo> getEndConfListByTime(String billingCode, int beginNum, Timestamp beginTime){
		String sqlStr = "from ConferenceInfo t where t.beginTime<= '" + beginTime + "' and t.accountBillingcode ='" + billingCode
				+ "' and t.confStatus =2 ORDER BY t.beginTime DESC";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setFirstResult(beginNum).setMaxResults(Consts.APP_CONF_LIST_COUNT_FOR_EACHPAGE).list();
		return list;
	}

	public List<Object> getConfCustList(String name, String phone, String email, String userBc){
		Criteria criteriaconf = this.getSession().createCriteria(ConferenceInfo.class);
		ProjectionList projList = Projections.projectionList();
		if(!StringUtils.isEmpty(userBc)){
			criteriaconf.add(Restrictions.eq("accountBillingcode", userBc));
		}
		Criteria criteriaCust = criteriaconf.createCriteria("custRelation", "cu");
		if(!StringUtils.isEmpty(name)){
			criteriaCust.add(Restrictions.like("cu.custName", "%" + name + "%"));
		}
		if(!StringUtils.isEmpty(phone)){
			criteriaCust.add(Restrictions.like("cu.custTel", "%" + phone + "%"));
		}
		if(!StringUtils.isEmpty(email)){
			criteriaCust.add(Restrictions.like("cu.custEmail", "%" + email + "%"));
		}
		projList.add(Projections.property("cu.custName"));
		projList.add(Projections.property("cu.custTel"));
		projList.add(Projections.property("cu.custEmail"));
		projList.add(Projections.property("cu.custContryCode"));
		projList.add(Projections.property("cu.custAreacode"));
		criteriaCust.setProjection(Projections.distinct(projList));
		List<Object> custList = criteriaCust.setFirstResult(0).setMaxResults(5).list();
		return custList;
	}

	public void updateConfStatusByBc(String billingCode){
		Session session = this.getOpenSession();
		String hql = "update ConferenceInfo set confStatus=1 where confBillingcode=:confBillingcode";
		Query query = session.createQuery(hql);
		query.setParameter("confBillingcode", billingCode);
		query.executeUpdate();
		session.close();
	}

	/**
	 * 根据账号billingCode获取最后一场预约的会议配置
	 *
	 * @param billingCode
	 * @return
	 */
	public List<ConferenceInfo> getLastConf(String billingCode){
		String sqlStr = "from ConferenceInfo where accountBillingcode=:accountBillingcode ORDER BY createTime DESC";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setParameter("accountBillingcode", billingCode).setFirstResult(0).setMaxResults(1).list();
		return list;
	}

	public ConferenceInfo getConfById(Long confID){
		String sqlStr = "from ConferenceInfo where confId=:confId";
		return (ConferenceInfo) this.getSession().createQuery(sqlStr).setParameter("confId", confID).uniqueResult();
	}

	/**
	 * 根据会议id 和离线型type 判断任务池是否有离线型任务
	 * 
	 * @param confId
	 * @return
	 */
	public List<ConferenceInfo> getConfNumByTime(Timestamp beginTime, Timestamp endTime){
		String sqlStr = "from ConferenceInfo t where t.beginTime < :endTime and t.beginTime >=:beginTime order by t.beginTime";
		List<ConferenceInfo> list = this.getSession().createQuery(sqlStr).setParameter("beginTime", beginTime).setParameter("endTime", endTime).list();
		return list;
	}
}
