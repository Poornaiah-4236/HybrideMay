package com.DriverManagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.configuration.ConfigProperties;

public class DriverManagers {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
			
		return driver.get();
	}

	public static void setDriver(WebDriver driverInstance) {		
		
		driver.set(driverInstance);
		
	}

	public static void unload() {
		driver.remove();
	}

}
