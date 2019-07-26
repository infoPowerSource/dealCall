package com.deal.dao.sms;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.sms.SMSTemplate;

@Component
public class SMSTemplateDao extends BaseDao {

	public SMSTemplate getTemplateBysmsType(int mailType) {
		return (SMSTemplate) this.getSession().createQuery("from SMSTemplate where templateType=?").setParameter(0,mailType).uniqueResult();
	}

	public List<SMSTemplate> getTempateListByType(int mailType){
		return (List<SMSTemplate>) this.getSession().createQuery("from SMSTemplate where templateType=:type").setParameter("type",mailType).list();
	}

	public SMSTemplate getTempByTypeAndLanguage(int type, int language){
		return (SMSTemplate) this.getSession().createQuery("from SMSTemplate where templateType=:type and templateLanguage=:language").setParameter("type",type).setParameter("language", language).uniqueResult();
	}
}
