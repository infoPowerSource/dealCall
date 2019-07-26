package com.deal.service.ums;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deal.entity.ums.UmsUserDTO;


public class IUmsServiceTest
{
	
	@Autowired
	IUmsService umsService;
	
	@Test
	//@Ignore
	public void testLogin() {
		UmsUserDTO umsUserDto = new UmsUserDTO();
		
		umsUserDto.setUserName("wu_1107_02@qs.com");
		umsUserDto.setPassword("11111111");
		umsUserDto.setProductID("60000");
		//umsUserDto.setSiteID("72385");
		try {
			int returncode = umsService.checkLoginName(umsUserDto);
			System.out.println(returncode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
