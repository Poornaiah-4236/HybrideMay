package com.Listeners;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {	
	private int min=0;
	private int max=3;
	@Override
	public boolean retry(ITestResult result) {
		if(min<max) {
			System.out.println("retring test case "+result.getName()+" "+min);
			min++;
			return true;
		}
		return false;
	}
}
