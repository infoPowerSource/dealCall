package com.deal.entity.sms;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class ResultNote {
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ResultNote() {
	}

}
