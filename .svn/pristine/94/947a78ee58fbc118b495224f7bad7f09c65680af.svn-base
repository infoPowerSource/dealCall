package com.deal.service.userLogin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import com.deal.entity.login.UserInfo;

public interface IUserLoginEmailVerifyService{

	public String saveGenerateMd5(UserInfo user) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	public boolean isvalidTime(Timestamp outDateT);
}
