package com.Listeners;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
		extent = getInstance();
	}

	public static void setTest(ExtentTest testlog) {
		test.set(testlog);
	}

	public static ExtentTest getTest() {
		return test.get();
	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static void createInstance() {
		Time time = new Time(System.currentTimeMillis());
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator
				+ "TestResult" + File.separator + "extent-report" + time.getTime() + ".html");
		spark.config().setReportName("Name of the report");
		spark.config().setDocumentTitle("Doccuments");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "Localhost");
		System.out.println("Calling on start");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.get().pass("Test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

}
