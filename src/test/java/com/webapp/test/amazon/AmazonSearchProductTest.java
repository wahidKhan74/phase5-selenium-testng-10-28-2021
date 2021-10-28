package com.webapp.test.amazon;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonSearchProductTest {

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
	void testAmazonProductSearch() {
		// search product
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("iphone 12 max pro");
		searchBox.submit();

		// 5. perform test
		String expectedTitle = "Amazon.in : iphone 12 max pro";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);
	}
	
}
