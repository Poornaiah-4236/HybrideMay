package com.stepDefinitions;

import org.testng.Assert;

import com.DriverManagement.DriverManagers;
import com.Listeners.ExtentReport;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginDefination {
	public ExtentTest test;
	@When("user enters {string} and {string}")
	public void user_is_on_login_page(String username, String password) throws InterruptedException {
		LoginPage login=new LoginPage(DriverManagers.getDriver());
		test=ExtentReport.extent.createTest("Login Page Functionality");
		login.LoginValidation(username, password);
		test.pass("Login functionality added successfully");
	    System.out.println("Username: " + username);
	    System.out.println("Password: " + password);
	    
	    Thread.sleep(60000);
	}
	@Then("user should see {string}")
	public void user_should_see(String string) throws InterruptedException {
		HomePage home=new HomePage(DriverManagers.getDriver());
		home.validateHome(string);
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println(string);
	   test=ExtentReport.extent.createTest("Home Page Functionality");
	   //Assert.assertFalse(true, string);
	   Thread.sleep(60000);
	}
	
}
