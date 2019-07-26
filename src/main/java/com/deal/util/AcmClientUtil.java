package com.deal.util;

import com.gnet.acmw.client.AcmClient;
import com.gnet.acmw.client.IConfAccount;
import com.gnet.acmw.client.IConfMgm;

public class AcmClientUtil {
	private static AcmClient acmClient;
	private static IConfAccount iConfAccount;
	private static IConfMgm iConfMgm;
	
   private static AcmClient getInstance() {
		if (acmClient == null) {
			acmClient = AcmClient.getInstance();

			return acmClient;
		}

		return acmClient;
	}

	public static IConfAccount getIconfAccountInstance() {
		if (iConfAccount != null) {
			return iConfAccount;
		}

		if (acmClient == null) {
			acmClient = getInstance();
		}

		iConfAccount = acmClient.getConfAccount();

		return iConfAccount;
	}

	public static IConfMgm getConfMgmInstance() {
		if (iConfMgm != null) {
			return iConfMgm;
		}

		if (acmClient == null) {
			acmClient = AcmClientUtil.getInstance();
		}

		iConfMgm = acmClient.getConfMgm();

		return iConfMgm;
	}

	public static String getAcmVersion() {
		if (acmClient == null) {
			acmClient = AcmClientUtil.getInstance();
		}

		return acmClient.getVersion();
	}
}
