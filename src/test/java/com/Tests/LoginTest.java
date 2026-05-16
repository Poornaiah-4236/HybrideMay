package com.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.DriverManagement.DriverManagers;
import com.Pages.LoginPage;
import com.TestData.ReadTestData;

public class LoginTest {

	@Test
	public void loginTest() {
		LoginPage login=new LoginPage(DriverManagers.getDriver());
		//login.LoginValidation(null, null);
		System.out.println("This is login test");
	}
	@Test
	public void getLoginData() throws IOException {
		String user=ReadTestData.getTestData("Username", "Sheet1");
		System.out.println(user);
	}
}
