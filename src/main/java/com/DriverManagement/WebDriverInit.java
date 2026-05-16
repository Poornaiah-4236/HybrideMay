package com.DriverManagement;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class WebDriverInit {
	
	public static WebDriver init(String browswer ) {
		 WebDriver driver = null;

	        try {
				switch (browswer) {
				    case "chrome":
				        driver = new ChromeDriver();
				        break;
				    case "firefox":
				        driver = new FirefoxDriver();
				        break;
				    case "edge":
				        driver = new EdgeDriver();
				        break;

				    default:
				        throw new RuntimeException("Invalid browser: " + browswer);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        DriverManagers.setDriver(driver);;
	        return DriverManagers.getDriver();
		
	}

}
