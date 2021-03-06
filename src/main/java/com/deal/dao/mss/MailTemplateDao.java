package com.deal.dao.mss;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.mss.MSSTemplate;

@Component
public class MailTemplateDao extends BaseDao{

	public MSSTemplate getTemplateBymailType(int mailType) {
		return (MSSTemplate) this.getSession().createQuery("from MSSTemplate where templateType=:templateType").setParameter("templateType",mailType).uniqueResult();
	}

}
