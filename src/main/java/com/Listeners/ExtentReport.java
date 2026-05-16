package com.Listeners;

import java.io.File;
import java.sql.Time;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;

import com.DriverManagement.DriverManagers;
//import com.Drivers.DriverManagers;
import com.Utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport implements ITestListener {
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		Time time = new Time(System.currentTimeMillis());
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"TestResult"+File.separator+"extent-report"+time.getTime()+".html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "Localhost");
		System.out.println("Calling on start");
		//extent.setSystemInfo(System., null);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
		//test.get().fail("");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
		  Object testClass = result.getInstance();
	        // Get driver from test class
	        WebDriver driver = ((DriverManagers) testClass).getDriver();
	        //Capture screenshot
	        String testName = result.getName();
	        String path = ScreenshotUtil.captureScreenshot(driver, testName);
	        System.out.println("Screenshot taken: " + path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test skipped");
	}

}
