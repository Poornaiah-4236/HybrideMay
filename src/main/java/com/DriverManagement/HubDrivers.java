package com.DriverManagement;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HubDrivers {
	public static RemoteWebDriver driver;
	static URL gridUrl = null;

	public static WebDriver hubDriver(String browser) throws MalformedURLException {
		gridUrl = new URL("http://localhost:4444/wd/hub");
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--remote-allow-origins=*");
			//gridUrl = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(gridUrl, options);
			options.setCapability("platformName", "Windows");
			System.out.println("Page Title: " + driver.getTitle());
			break;
		case "edge":
			EdgeOptions edgeoptions = new EdgeOptions();
			edgeoptions.addArguments("--disable-notifications");
			edgeoptions.addArguments("--remote-allow-origins=*");
			//gridUrl = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(gridUrl, edgeoptions);
			edgeoptions.setCapability("platformName", "Windows");
			System.out.println("Page Title: " + driver.getTitle());
			break;
		case "firefox":
			FirefoxOptions  fireoptions = new FirefoxOptions();
			fireoptions.addArguments("--disable-notifications");
			fireoptions.addArguments("--remote-allow-origins=*");
			//gridUrl = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(gridUrl, fireoptions);
			fireoptions.setCapability("platformName", "Windows");
			System.out.println("Page Title: " + driver.getTitle());
			break;
		}
		return driver;
	}

}
