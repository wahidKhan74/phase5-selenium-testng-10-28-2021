package com.webapp.test.group;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

public class BrowserGroupTest {
	
	final String amazonURL = "https://www.amazon.in/";
	final String facebookURL ="https://www.facebook.com/";
	
	final String chromePath = "drivers/chromedriver";
	final String firefoxPath ="drivers/geckodriver";
	
	WebDriver driverOne;
	WebDriver driverTwo;
	WebDriverWait wait;
	
	// test group for chrome
	@Test(groups="ChromeOnly")
	public void lauchChromeTest() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		driverOne = new ChromeDriver();
		driverOne.get(amazonURL);
	}
	
	@Test(groups="ChromeOnly",dependsOnMethods="lauchChromeTest", priority=0)
	public void testAmazonHomePageTitle() {
		// 5. perform test
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driverOne.getTitle();
		assertEquals(expectedTitle, actualTitle);
	}
	
	@Test(groups="ChromeOnly",dependsOnMethods="lauchChromeTest", priority=1)
	void testAmazonHomePageSourceURl() {
		assertEquals(amazonURL, driverOne.getCurrentUrl());
		driverOne.close();
	}
	
	// test group for firefox
	@Test(groups="FirefoxOnly")
	public void lauchFireFoxTest() {
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		driverTwo = new FirefoxDriver();
		wait = new WebDriverWait(driverTwo, Duration.ofSeconds(40));
		driverTwo.get(facebookURL);
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods = "lauchFireFoxTest", priority=0)
	public void testFaceBookHomePage() {
		String expected ="Facebook - Log In or Sign Up";
		assertEquals(driverTwo.getTitle(), expected);
	
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods = "lauchFireFoxTest", priority=0)
	void testLogin() {
		driverTwo.findElement(By.cssSelector("#email")).sendKeys("abc@gmail.com");
		driverTwo.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("abc@123");
		driverTwo.findElement(By.name("login")).submit();
		
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", errorMsg.getText());
		
		WebElement errorMsg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"error_box\"]/div[2]")));
		assertEquals("Invalid username or password", errorMsg2.getText());
		driverTwo.close();
	}
	
}	
