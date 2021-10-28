package com.webapp.test.facebook;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FacebookLoginTest {
	
	String siteUrl = "https://www.facebook.com/";
	String driverPath = "drivers/chromedriver";
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		driver.get(siteUrl);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	@Test
	void testLogin() {
		driver.findElement(By.cssSelector("#email")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("abc@123");
		driver.findElement(By.name("login")).submit();
		
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", errorMsg.getText());
		
		WebElement errorMsg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"error_box\"]/div[2]")));
		assertEquals("Invalid username or password", errorMsg2.getText());
	}

	@Test
	void testLoginFieldsDisplayed() {
		//find email field
		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
		WebElement submitButton = driver.findElement(By.name("login"));
		
		assertTrue(emailInput.isDisplayed());
		assertTrue(passwordInput.isDisplayed());
		assertTrue(submitButton.isDisplayed());
		
	}
	
}
