package com.deal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 集群休眠
 * 
 * @author zhipeng.xu
 *
 */
@Service
public class AvoidUtil{
	public static final Logger logger = LoggerFactory.getLogger(AvoidUtil.class);

	public void avoidClusterError(){
		double random = Math.random();
		try{
			long time = (long) (random * 20000);
			logger.info("sleep " + time + " milliseconds");
			Thread.sleep(time);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}
