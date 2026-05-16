package com.Runner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.Listeners.ExtentReport;

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
public class Runner extends AbstractTestNGCucumberTests  {
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
