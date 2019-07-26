package com.deal.entity.sms;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MtMsg")
@XmlType(propOrder = { "phone", "contents","businesscode","billingcode" })
public class Note {
	private String	phone;
	private String	contents;
	private String	businesscode;	
	private String	billingcode;
	
	public Note() {
	}

	public Note(String phone, String contents,String businesscode, String billingcode) {
		super();
		this.phone = phone;
		this.contents = contents;
		this.businesscode = businesscode;
		this.billingcode = billingcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getBusinesscode() {
		return businesscode;
	}

	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}

	public String getBillingcode() {
		return billingcode;
	}

	public void setBillingcode(String billingcode) {
		this.billingcode = billingcode;
	}

}