package com.Tests;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.DriverManagement.DriverManagers;
import com.Pages.LoginPage;
import com.TestData.ExcelReader;
import com.TestData.ReadTestData;

public class LoginTest extends BaseTest {
public Logger log=LogManager.getLogger(LoginTest.class);
	@Test
	public void loginTest() {
		LoginPage login=new LoginPage(DriverManagers.getDriver());
		
		System.out.println("This is login test");
		log.info("Login functionality validated successfully");
	}
	@Test
	public void getLoginData() throws IOException {
		List<String> str=ExcelReader.getColumnValues("Sheet1","Username");
	
		//String user=ReadTestData.getTestData("Username", "Sheet1");
		System.out.println(str);
		try {
		}catch(Exception e) {
			log.error("", e);;
			   throw e;
		}
		
	}
}
