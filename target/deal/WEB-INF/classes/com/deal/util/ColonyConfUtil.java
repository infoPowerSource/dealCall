package com.deal.util;

import java.net.InetAddress;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deal.dao.task.ColonyDAO;
import com.deal.entity.colony.ColonyHandle;

/**
 * 预约会议 集群分配
 * 
 * @author zhipeng.xu 2017-07-04
 */
@Service
public class ColonyConfUtil{
	public static final Logger logger = LoggerFactory.getLogger(ColonyConfUtil.class);
	@Autowired
	ColonyDAO colonyDAO;

	String host = "";

	public ColonyConfUtil() {
		try{
			// 取得服务器ip
			InetAddress hostAddress = InetAddress.getLocalHost();
			logger.info("-------" + hostAddress.getHostAddress() + "------");
			this.host = hostAddress.getHostAddress().toString();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void check(String billingCode){
		try{
			ColonyHandle colony = colonyDAO.findById(billingCode);
			if(colony == null) {
				colony = new ColonyHandle();
				colony.setBillingCode(billingCode);
				colony.setColonyTime(new Timestamp(System.currentTimeMillis()));
				colony.setColonyIp(this.host);
				colonyDAO.save(colony);
			}else{
				colony.setColonyTime(new Timestamp(System.currentTimeMillis()));
				colonyDAO.update(colony);
			}
		}catch (Exception e){
			logger.error("预约返回失败  " + e.toString());
			e.printStackTrace();
		}
	}
}
