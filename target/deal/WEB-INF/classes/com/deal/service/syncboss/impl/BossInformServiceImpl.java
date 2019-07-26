package com.deal.service.syncboss.impl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.deal.dao.login.syncboss.AccessNumDao;
import com.deal.entity.create.ConferenceAccessNumber;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.syncboss.SyncBossConfInfoParams;
import com.deal.entity.syncboss.SyncBossConfInfoResult;
import com.deal.service.syncboss.IBossInformService;
import com.deal.util.Consts;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

@Service
public class BossInformServiceImpl implements IBossInformService{
	private static Logger logger = LoggerFactory.getLogger(BossInformServiceImpl.class);
	
	@Value("${deal.resvConfInBossUrl}")
	private String resvConfInBossUrl;
	@Value("${deal.getAccessFromBossUrl}")
	private String getAccessFromBossUrl;
	@Autowired
	private AccessNumDao accessNumDao;
	@Autowired
    protected RestTemplate restTemplate;

	@Override
	public SyncBossConfInfoResult syncResvConfInBoss(ConferenceInfo confInfo){
		SyncBossConfInfoParams resvConfParams=new SyncBossConfInfoParams();
		resvConfParams.setConfBillingCode(confInfo.getConfBillingcode());
		resvConfParams.setConfName(confInfo.getConfName());
		resvConfParams.setHostPassword(confInfo.getChairmanPassword());
		resvConfParams.setGuestPassword(confInfo.getPartyPassword());
		resvConfParams.setConfBeginTime(confInfo.getBeginTime().getTime());
		resvConfParams.setConfEndTime(confInfo.getEndTime().getTime());
		if(confInfo.getConfStatus()==3){
			resvConfParams.setConfStatus("2");
		}else{
			resvConfParams.setConfStatus("1");
		}
		resvConfParams.setParentBillingCode(confInfo.getAccountBillingcode());
		Client client = Client.create();
		WebResource webResource = client.resource(resvConfInBossUrl);
		logger.info("同步Boss会议 url:" + resvConfInBossUrl);
		webResource.accept(MediaType.APPLICATION_JSON);
		String description = "";
		ClientResponse response = null;
		SyncBossConfInfoResult result = new SyncBossConfInfoResult();
		try{
			response = webResource.entity(resvConfParams, MediaType.APPLICATION_JSON).post(ClientResponse.class);
			description = response.getEntity(String.class);
			logger.info("同步Boss会议 status==" + response.getStatus());
			if(response.getStatus() == Status.OK.getStatusCode() || response.getStatus() == Status.CREATED.getStatusCode()){
				result.setStatus((short) 0);
			}else{
				result.setStatus((short) 1);
			}
			result.setDescription(description);
		}catch (UniformInterfaceException e){
			logger.error("同步Boss会议 post UniformInterface error info:");
			e.printStackTrace();
		}catch (ClientHandlerException e){
			logger.error("同步Boss会议 post ClientHandler error info:");
			e.printStackTrace();
		}finally{
			logger.info("同步Boss会议 post client.destroy()");
			client.destroy();
		}
		logger.info("同步Boss会议 信息完成,会议ID:" + confInfo.getConfId());
		return result;
	}
	

	@Override
	public void syncAccessFromBoss(String bridgeName){
		
		//http://192.168.17.111:8080/core-service/api/accessNumbers/getByType/1?accessNumberContent=summit7&accessType=2
		
		String restUrl =getAccessFromBossUrl+"?accessNumberContent="+bridgeName+"&accessType=2";
		logger.info("从Boss获取接入号 url:" + restUrl);
		
		try{
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(restUrl, String.class);
			String tmpInfo = responseEntity.getBody();
			JSONObject jsonObj= new JSONObject(tmpInfo);
			JSONArray accessList = jsonObj.getJSONArray("national400Access");
			//如果不为空，则先清除数据库中保存的
			if(accessList != null){
				accessNumDao.delAccessNum(bridgeName);
			}
			for (int i = 0; i < accessList.length(); i++){
				JSONObject jsonItem = accessList.getJSONObject(i);
                // 获取每一个json对象的值
				ConferenceAccessNumber accessinfo = new ConferenceAccessNumber();
				accessinfo.setAccessId(jsonItem.getInt("id"));
				accessinfo.setIsvalid(jsonItem.getInt("accessStatus"));
				accessinfo.setBridgeName(jsonItem.getString("accessNumberContent"));
				accessinfo.setLanguage(jsonItem.getString("accessNumberLanguage"));
				accessinfo.setTelNum(jsonItem.getString("accessNumber"));
				accessNumDao.saveAccessNum(accessinfo);
			}
            
		}catch (Exception ex) {
            ex.printStackTrace();
            logger.error("从Boss获取接入号 发生异常,bridgeName:" + bridgeName);
            logger.error(ex.getMessage(), ex);
        }		
	}
}
