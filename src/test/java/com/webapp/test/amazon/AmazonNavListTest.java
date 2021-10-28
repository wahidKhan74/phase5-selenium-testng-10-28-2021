package com.webapp.test.amazon;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonNavListTest {
	
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
	void testMobileNavLink() {
		// find mobile link
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(2)"));
		assertEquals(true, mobileLink.isDisplayed());
		assertEquals(true, mobileLink.isEnabled());
		// perform a click
		mobileLink.click();
		// evaluate test
		String expected ="Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}

	@Test
	void testFashionNavLink() {
		// find mobile link
		WebElement fashionLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(5)"));
		assertEquals(true, fashionLink.isDisplayed());
		assertEquals(true, fashionLink.isEnabled());
		// perform a click
		fashionLink.click();
		// evaluate test
		String expected ="Amazon Fashion: Clothing, Footwear and Accessories online for Men, Women and Kids";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}

}
