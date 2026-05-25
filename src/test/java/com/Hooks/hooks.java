package com.Hooks;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

import com.DriverManagement.DriverManagers;
import com.DriverManagement.HubDrivers;
import com.DriverManagement.WebDriverInit;
import com.configuration.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
public class hooks {
	@Before
	public void SetUp(Scenario scenerio) throws MalformedURLException {		
		String browser=System.getProperty("browser","firefox");
		String url=ConfigProperties.getProperty("url");
		WebDriverInit.init(browser);		
		//DriverManagers.setDriver(HubDrivers.hubDriver(browser));
		DriverManagers.getDriver().get(url);
		DriverManagers.getDriver().manage().window().maximize();
		DriverManagers.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	@After
	public void TearDown(Scenario scenario) {
		WebDriver driver = DriverManagers.getDriver();
        if (driver == null) {
            System.out.println("Driver is NULL");
            return;
        }
        if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());
            System.out.println("Screenshot captured for failed scenario");
        }
		DriverManagers.unload();
		System.out.println("Quit Driver");
	}
	

}
