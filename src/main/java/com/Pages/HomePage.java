package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
	WebDriver driver;
	private By name=By.xpath("//div[text()='Products']");
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	public void validateHome(String value) {
		String title=driver.findElement(name).getText();
		Assert.assertEquals(title, value);
	}
}
