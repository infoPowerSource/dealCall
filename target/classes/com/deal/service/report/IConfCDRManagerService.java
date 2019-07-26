package com.deal.service.report;

import org.json.JSONObject;

import com.deal.entity.report.ConfDetailInput;
import com.deal.entity.report.ConfInput;


public interface IConfCDRManagerService
{
	public JSONObject getConfList(ConfInput confInput);

	public JSONObject getConfDetail(ConfDetailInput confDetailInput);
}
