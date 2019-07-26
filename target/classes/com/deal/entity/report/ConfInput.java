package com.deal.entity.report;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 计费CDR会议接口参数 对象
 * @author zhipeng.xu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfInput {
	private GetConfInput input; //会议接口参数对象
	private String[] filter; //输出项过滤安数组
	public GetConfInput getInput() {
		return input;
	}
	public void setInput(GetConfInput input) {
		this.input = input;
	}
	public String[] getFilter() {
		return filter;
	}
	public void setFilter(String[] filter) {
		this.filter = filter;
	}
}
