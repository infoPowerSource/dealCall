package com.deal.service.report;

import java.util.List;
import java.util.Map;

import com.deal.entity.create.ConferenceReportInfo;

public interface IMeetingReportService{

	public List<ConferenceReportInfo> getReportByConfId(String confId);

	public Map<String, Object> getReportDataByConfId(String confId);

}
