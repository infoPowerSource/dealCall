package com.deal.service.userLogin.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deal.dao.login.UserInfoDao;
import com.deal.entity.login.UserInfo;
import com.deal.service.userLogin.IUserLoginEmailVerifyService;
import com.deal.util.Md5Utils;


@Component
public class UserLoginEmailVerifyServiceImpl implements IUserLoginEmailVerifyService{
	private static Logger logger = LoggerFactory.getLogger(UserLoginEmailVerifyServiceImpl.class);
	private static final String CONNECT = "$";

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Value("${resetPwdFromEmail.link.timeOut}")
	private String timeOut;

	@Override
	public String saveGenerateMd5(UserInfo user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		Long userId = user.getUserId();
		String secretKey = UUID.randomUUID().toString(); // 密钥
		Timestamp outDate = new Timestamp(System.currentTimeMillis() + Integer.parseInt(timeOut) * 60 * 1000);// ${timeOut}分钟后过期
		long validDate = outDate.getTime();
		String key = userId + CONNECT + validDate + CONNECT + secretKey;
		String emailVerify = Md5Utils.MD5Encode(key);// 数字签名
		logger.info("userId:" + userId + ",key:" + key + ",emailVerify:" + emailVerify);
		saveUserInfo(user, validDate, emailVerify);
		return emailVerify;
	}

	private void saveUserInfo(UserInfo user, long validDate, String emailVerify){
		user.setEmailCheckTime(new Timestamp(validDate));
		user.setEmailVerify(emailVerify);
		userInfoDao.updateUser(user);
	}

	@Override
	public boolean isvalidTime(Timestamp outDateT){
		long outDate = outDateT.getTime();

		Date now = new Date();
		long nowTime = now.getTime();
		logger.info("outDate:" + outDate);

		boolean isvalid = nowTime <= outDate ? true : false;
		logger.info("isvalid:" + isvalid + ",outDate:" + outDate + ",nowTime:" + nowTime);
		return isvalid;
	}

	public static void main(String[] args) throws Exception, UnsupportedEncodingException{
		// T t = new T();
		// String result = t.generateMd5("123");
		// System.out.println(result);
		// //
		// t.isvalidTime(1497947867943L);
	}

}
