package com.Hooks;
import org.openqa.selenium.WebDriver;

import com.DriverManagement.DriverManagers;
import com.DriverManagement.WebDriverInit;
import com.configuration.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
public class hooks {
	WebDriver driver;
	@Before
	//@Test
	public void SetUp() {
		String browswer=ConfigProperties.getProperty("browser");
		String url=ConfigProperties.getProperty("url");
		driver=WebDriverInit.init(browswer);		
		driver.get(url);
	}
	@After
	public void TearDown() {
		driver.quit();
		DriverManagers.unload();
		System.out.println("Quit Driver");
	}
	

}
