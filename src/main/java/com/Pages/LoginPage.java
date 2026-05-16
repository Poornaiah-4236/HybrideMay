package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	private By userName=By.id("user-name");
	private By password=By.xpath("//input[@id='password']");
	private By loginButton=By.xpath("//input[@id='login-button']");
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	public void LoginValidation(String _userName,String _password) throws InterruptedException {
		driver.findElement(userName).sendKeys(_userName);
		Thread.sleep(5000);
		driver.findElement(password).sendKeys(_password);
		driver.findElement(loginButton).click();
	}
}
