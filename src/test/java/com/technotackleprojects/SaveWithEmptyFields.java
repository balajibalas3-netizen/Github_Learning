package com.technotackleprojects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SaveWithEmptyFields {
	
static WebDriver driver;
	
	@BeforeClass
	public void browserLaunch() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		driver = new ChromeDriver(options);
		driver.get("https://dev2.technotackle.in/deals/web/site/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	
	@Test(priority = 1)
	public void enterLoginCredentials() {

		driver.findElement(By.name("LoginForm[username]")).sendKeys("Testing@gmail.com");
		
		driver.findElement(By.name("LoginForm[password]")).sendKeys("12345678");
		
		driver.findElement(By.name("login-button")).click();
		
		driver.findElement(By.xpath("//a[text()='return to dashboard']")).click();
		
        driver.findElement(By.name("LoginForm[username]")).sendKeys("Testing@gmail.com");
		
		driver.findElement(By.name("LoginForm[password]")).sendKeys("12345678");
		
		driver.findElement(By.name("login-button")).click();
		
	}
	
	@Test(priority = 2)
	public void enterIntoFormSession() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement contacts = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[contains(@href,'company-contacts')]")
	            )
	    );

	    contacts.click();
	    
	    driver.findElement(By.xpath("//a[@class='btn btn-primary'][@title='Create new Company Contacts']")).click();
	}
	
	@Test(priority = 3)
	public void withEmptyFields() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement header = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h4[contains(text(),'Create new Company Contacts')]")
	            )
	    );

	    String actualText = header.getText().trim();
	    System.out.println("Actual Header: " + actualText);

	    Assert.assertEquals(actualText, "Create new Company Contacts");
	    
	    driver.findElement(By.xpath("//button[@class='btn btn-primary'][text()='Save']")).click();
	    
	    WebElement getCompanyErrMsg = driver.findElement(By.xpath("//div[contains(text(),'Company cannot be blank.')]"));
	    String actualCompanyErrMsg = getCompanyErrMsg.getText();
	    System.out.println(actualCompanyErrMsg.trim());
	    
	    Assert.assertEquals(actualCompanyErrMsg, "Company cannot be blank.");
	    
	    
	    WebElement getFirstNameErrMsg = driver.findElement(By.xpath("//div[contains(text(),'First Name cannot be blank.')]"));
	    String actualFristNameErrMsg = getFirstNameErrMsg.getText().trim();
	    System.out.println(actualFristNameErrMsg);
	    
	    Assert.assertEquals(actualFristNameErrMsg, "First Name cannot be blank.");
	    
	    WebElement getEmailErrMsg = driver.findElement(By.xpath("//div[contains(text(),'Email cannot be blank.')]"));
	    String actualEmailErrMsg = getEmailErrMsg.getText().trim();
	    System.out.println(actualEmailErrMsg);
	    
	    Assert.assertEquals(actualEmailErrMsg, "Email cannot be blank.");
	    
	    WebElement getPhoneErrMsg = driver.findElement(By.xpath("//div[contains(text(),'Phone cannot be blank.')]"));
	    String actualPhoneErrMsg = getPhoneErrMsg.getText().trim();
	    System.out.println(actualPhoneErrMsg);
	    
	    Assert.assertEquals(actualPhoneErrMsg, "Phone cannot be blank.");
	    
	    
	}
	
	@AfterMethod
	public void TakesScreensho() throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\User\\eclipse-workspaceFW\\TechnoTackle_CC_Balaji\\target\\Screenshots\\Technoform2.png");
		FileUtils.copyFile(source, destination);
	}
	
	@AfterClass
	public void quitBrowser() {

		driver.quit();
	}

}

