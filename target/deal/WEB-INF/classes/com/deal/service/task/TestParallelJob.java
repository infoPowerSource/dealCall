package com.deal.service.task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.entity.create.ConferenceInfoForm;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.login.UserAllInfo;
import com.deal.service.create.IConferenceService;
import com.deal.util.DateFormatUtil;

import net.fortuna.ical4j.model.ValidationException;

/**
 * 压力测试
 * 
 * @author zhipeng.xu
 *
 */
@Component("testJob")
public class TestParallelJob{
	@Autowired
	IConferenceService confService;
	private static long telNo=2320000000L;

	public synchronized void execute(){
        int confNum=100;
		UserAllInfo userInfo = new UserAllInfo();
		userInfo.setBridgeName("summit2");
//		userInfo.setUserDisplayName("wu_923_b_08");
//		userInfo.setUserBillingcode("61950725");
//		userInfo.setUserCustomerCode("00631721");
		userInfo.setUserDisplayName("dong.zheng");
		userInfo.setUserBillingcode("6135655");
		userInfo.setUserCustomerCode("150023");
//		userInfo.setUserDisplayName("性能测试");
//		userInfo.setUserBillingcode("6135684");
//		userInfo.setUserCustomerCode("150023");


		ConferenceInfoForm confForm = new ConferenceInfoForm();
		Timestamp beginTime = new Timestamp(System.currentTimeMillis());
		confForm.setBeginTime(DateFormatUtil.timestamps2StringWithoutSecond(DateFormatUtil.addMinutesInTimestamp(beginTime, 5)));
		confForm.setDuration("03:00");
		confForm.setConfStatus(0);
		confForm.setConfig("1");
		confForm.setTapedMark("on");
		

		for(int i=0;i<confNum;i++){
			confForm.setConfName("小辉压力测试" + i);
		    List<CustomerInfo> cusList = new ArrayList<>();
			CustomerInfo cust = new CustomerInfo();
			createCustList(cusList,i);
			createExportList(cusList,i);
            confForm.setCustInfo(cusList);
            try{
			confService.addConferenceInfo(confForm, userInfo);
            }catch(ValidationException e){
            	e.printStackTrace();
            }
		}
	}

	private void  createCustList(List<CustomerInfo> cusList,int groupNum){
		for(int i=0;i<10;i++){
			CustomerInfo cust=new CustomerInfo();
			cust.setCustName(groupNum+"组"+i+"个咨询客户");
			cust.setCustTel(generateTelNo());
			cust.setCustType(1);
			cust.setCustContryCode("86");
			cust.setCustAreacode("区号");
			cusList.add(cust);
		}
	}

	private void createExportList(List<CustomerInfo> cusList,int groupNum){
		for(int i=0;i<2;i++){
			CustomerInfo cust=new CustomerInfo();
			cust.setCustEmail("ceshi@quanshi.com");
			cust.setCustName(groupNum+"组"+i+"个专家");
			cust.setCustTel(generateTelNo());
			cust.setCustAreacode("区号");
			cust.setCustContryCode("86");
			cust.setCustType(0);
			cusList.add(cust);
		}
	}

	private synchronized static String generateTelNo(){
           telNo=telNo+1;
	   return String.valueOf(telNo); 
	}
}
