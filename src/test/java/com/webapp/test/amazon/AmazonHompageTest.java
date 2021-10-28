package com.webapp.test.amazon;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonHompageTest {

	// 1. Formulate a test url
	String siteUrl = "https://www.amazon.in/";
	String driverPath = "drivers/chromedriver";
	WebDriver driver;
	
	@BeforeMethod
	void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(siteUrl);
	}
	
	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	@Test
	void testAmazonHomePageTitle() {
		// 5. perform test
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);
	}
	
	@Test
	void testAmazonHomePageSourceURl() {
		assertEquals(siteUrl, driver.getCurrentUrl());
	}
}
