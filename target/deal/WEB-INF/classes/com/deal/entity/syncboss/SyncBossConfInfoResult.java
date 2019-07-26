package com.deal.entity.syncboss;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SyncBossConfInfoResult{
	/**
	 * 返回状态 0 成功  1 失败
	 */
	private Short status;
	
	/**
	 * 返回结果描述信息
	 */
	private String description;

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
