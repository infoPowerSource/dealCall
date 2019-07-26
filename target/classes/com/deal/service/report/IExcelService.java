package com.deal.service.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.report.ConfDetailCDR;

public interface IExcelService
{
	/**
	 * 写入Excel数据
	 * 
	 * @param confDetailCDRList
	 * 
	 * @param recordList
	 */
	public void writeExcelReport(List<ConfDetailCDR> confDetailCDRList)
			throws IOException;

}
