package com.deal.util;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deal.dao.task.ColonyDAO;
import com.deal.entity.colony.ColonyHandle;

/**
 * 集群调度器
 * 
 * @author zhipeng.xu 2017-07-04
 */
@Service
public class ColonyUtil{
	public static final Logger logger = LoggerFactory.getLogger(ColonyUtil.class);
	@Autowired
	ColonyDAO colonyDAO;

	String host = "";

	public ColonyUtil() {
		try{
			// 取得服务器ip
			InetAddress hostAddress = InetAddress.getLocalHost();
			logger.info("-------" + hostAddress.getHostAddress() + "------");
			this.host = hostAddress.getHostAddress().toString();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public boolean check(String billingCode){
		Lock lock = new ReentrantLock();
		try{
			lock.lock();
			ColonyHandle colony = colonyDAO.findById(billingCode);
			if(colony == null) {
				colony = new ColonyHandle();
				colony.setBillingCode(billingCode);
				colony.setColonyTime(new Timestamp(System.currentTimeMillis()));
				colony.setColonyIp(this.host);
				colonyDAO.save(colony);
				return true;
			}
			String colonyHost = colony.getColonyIp();
			// 如果上次修改是本服务器
			if(colonyHost.equals(this.host)) {
				colony.setColonyTime(new Timestamp(System.currentTimeMillis()));
				colonyDAO.update(colony);
				return true;
			}
			// 如果上次修改不是本服务器
			else{
				return false;
			}
		}catch (Exception e){
			logger.error("集群测试bug " + e.toString());
			e.printStackTrace();
			return false;
		}finally{
			lock.unlock();
		}
	}
}
