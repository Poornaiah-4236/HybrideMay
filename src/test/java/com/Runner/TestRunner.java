package com.Runner;
import java.net.MalformedURLException;
import java.util.Properties;

import org.junit.runners.Parameterized.Parameter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.DriverManagement.DriverManagers;
import com.DriverManagement.HubDrivers;
import com.Listeners.ExtentReport;
import com.configuration.ConfigProperties;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features = "src/test/resources/features/Login.feature", 
		glue = {"com.stepDefinitions","com.Hooks" },
		plugin = { "pretty",
		"html:target/cucumber-reports.html" }, 
		dryRun = false, 
		tags = "@Smoke")
@Listeners(ExtentReport.class)
public class TestRunner extends AbstractTestNGCucumberTests  {
	
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
	@BeforeClass
	@Parameters("browser")    
    public void setBrowser(@Optional("edge") String browser) throws MalformedURLException {
        System.setProperty("browser", browser);        
        ConfigProperties.prop.setProperty("browser", browser);
       // DriverManagers.setDriver(HubDrivers.hubDriver(browser));
    }
    
}
