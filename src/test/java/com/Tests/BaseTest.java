package com.Tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

	public void findBrokenLinks() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Navigate to your target website
		driver.get("https://example.com");

		// 1. Collect all elements with the 'a' anchor tag
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links found on the page: " + links.size());

		int brokenLinksCount = 0;

		// 2. Iterate through each link
		for (WebElement element : links) {
			String url = element.getAttribute("href");

			// Filter out empty links or button actions
			if (url == null || url.isEmpty() || url.startsWith("javascript:")) {
				System.out.println("Skipping empty or unconfigured link.");
				continue;
			}

			// 3 & 4. Establish a quick connection and verify status code
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("HEAD"); // Fetch headers only for speed
				connection.setConnectTimeout(3000); // Set a 3-second timeout limit
				connection.connect();
				int responseCode = connection.getResponseCode();
				if (responseCode >= 400) {
					System.err.println("[BROKEN LINK] " + url + " ---> Response Code: " + responseCode);
					brokenLinksCount++;
				} else {
					System.out.println("[VALID LINK] " + url + " ---> Response Code: " + responseCode);
				}
				connection.disconnect();

			} catch (IOException e) {
				System.err.println("[ERROR] Could not connect to URL: " + url + " - " + e.getMessage());
				brokenLinksCount++;
			}
		}

		System.out.println("\n--- Audit Complete ---");
		System.out.println("Total broken links identified: " + brokenLinksCount);

		driver.quit();
	}
}
