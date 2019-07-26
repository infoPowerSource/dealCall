package com.deal.service.userLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.deal.entity.login.CheckFindBackNameResult;
import com.deal.entity.login.UmsResult;
import com.deal.entity.login.UserAllInfo;
import com.deal.entity.login.UserInfo;
import com.deal.entity.login.UserLoginInfo;
import com.deal.entity.ums.UmsResponseDTO;

public interface IUserLoginService{
//	public void addCookie(String rempwd, String name, String password);

//	public boolean checkVC(String verifycode, HttpSession session, String type);

//	public boolean checkForgetPassVC(String verifycode, HttpSession session, String type);
	
	public boolean checkFindBackTelPassVC(String verifycode, HttpSession session, String type);

	public UserAllInfo getUserAllInfo(UmsResponseDTO res);

//	public int checkIsLoginVerifyCode(HttpSession session, int type);

	public UmsResult updatePass(UserLoginInfo user, HttpSession session);

	public boolean resetPass(UserLoginInfo user, HttpSession session);

	public CheckFindBackNameResult checkFindBackName(String name, HttpSession session);

	// @return 0:失败 ;1:成功
	public int saveVerifySMS(String name, String umsiId, HttpSession session);

	// @return 0:失败 ;1:成功
	public int saveSendMail(String name, CheckFindBackNameResult cfbnResult, HttpSession session);

	public boolean executeCheckSiteResult(HttpServletRequest request, String url, UserAllInfo userAllInfo);

	public int checkEmailVer(UserLoginInfo user);
	
	public UserInfo getUserInfo(String umsId);
	
	public String saveGenerateEmailVerify(UserInfo userInfo);

//	public void updateUserIp(String ipAddr, UserAllInfo userAllInfo);
	
	public void updateSession(HttpServletRequest request, String userIdStr);

	public String getIpAddr(HttpServletRequest request);
}
