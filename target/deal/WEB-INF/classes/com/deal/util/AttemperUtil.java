package com.deal.util;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deal.dao.task.AttemperTaskDAO;
import com.deal.entity.task.AttemperTask;
/**
 * 调度处理器
 * @author zhipeng.xu
 *2017-06-19
 */
@Service
public class AttemperUtil{
	public static final Logger logger = LoggerFactory.getLogger(AttemperUtil.class);
	@Autowired
	AttemperTaskDAO attemperTaskDAO;
	@Value("${max_time_return}")
	private String max_time_return;

	String host = "";

	public AttemperUtil() {
		try{
			// 取得服务器ip
			InetAddress hostAddress = InetAddress.getLocalHost();
			logger.info("-------" + hostAddress.getHostAddress() + "------");
			this.host = hostAddress.getHostAddress().toString();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public boolean check(Integer attemperId){
		try{

			AttemperTask attemperTask = attemperTaskDAO.findById(attemperId);

			String nowtime = DateFormatUtil.getTimeAsDate();

			if(attemperTask == null) {
				attemperTask = new AttemperTask();

				attemperTask.setAttemperId(attemperId);
				attemperTask.setAttemperTime(new Timestamp(System.currentTimeMillis()));
				attemperTask.setAttemperIp(this.host);

				attemperTaskDAO.save(attemperTask);
				return true;
			}
			String attemperHost = attemperTask.getAttemperIp();

			// 如果上次修改是本调度
			if(attemperHost.equals(this.host)) {
				attemperTask.setAttemperTime(new Timestamp(System.currentTimeMillis()));
				attemperTaskDAO.attachDirty(attemperTask);
				return true;
			}
			// 如果上次修改不是本调度
			else{
				String changeTime = DateFormatUtil.getChangeMinute(nowtime, 0 - Integer.valueOf(this.max_time_return));
				Date lastTime = DateFormatUtil.Str2Date1(changeTime);
				Date lastModifyTime = attemperTask.getAttemperTime();
				// 如果上次修改时间 晚于最大调度时间
				if(lastTime.after(lastModifyTime)) {
					attemperTask.setAttemperTime(new Timestamp(System.currentTimeMillis()));
					attemperTask.setAttemperIp(this.host);
					attemperTaskDAO.attachDirty(attemperTask);
					return true;
				}else{
					return false;
				}

			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
