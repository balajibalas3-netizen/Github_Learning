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

public class WithInvalidEmailAndLinkedin {
	
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
	public void withInvalidEmailandLinkedin() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement header = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h4[contains(text(),'Create new Company Contacts')]")
	            )
	    );

	    String actualText = header.getText().trim();
	    System.out.println("Actual Header: " + actualText);

	    Assert.assertEquals(actualText, "Create new Company Contacts");
	    
	    
	    WebElement dropdown = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//span[@aria-labelledby='select2-companycontacts-company_id-container']")
	            )
	    );
	    dropdown.click();

	    WebElement searchBox = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//input[@class='select2-search__field']")
	            )
	    );
        
	    searchBox.sendKeys("Test techno");

	    WebElement option = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//li[contains(text(),'Test techno')]")
	            )
	    );
	    option.click();
	    
	    
	    WebElement txtFirstName = driver.findElement(By.id("companycontacts-first_name"));
	    txtFirstName.sendKeys("Balaji");
	    
	    WebElement txtLastName = driver.findElement(By.id("companycontacts-last_name"));
	    txtLastName.sendKeys("Subramani");
	    
	    WebElement txtEmail = driver.findElement(By.id("companycontacts-email"));
	    txtEmail.sendKeys("balajigmail.com");
	    
	    WebElement txtPhoneNumber = driver.findElement(By.id("companycontacts-phone"));
	    txtPhoneNumber.sendKeys("9876543210");
	    
	    WebElement txtDesignation = driver.findElement(By.id("companycontacts-designation"));
	    txtDesignation.sendKeys("Software Test Engineer");
	    
	    WebElement txtLinkedin = driver.findElement(By.id("companycontacts-linkedin"));
	    txtLinkedin.sendKeys("balajisubramani");
        
        WebElement txtNotes = driver.findElement(By.id("companycontacts-notes"));
	    txtNotes.sendKeys("Created Successfully");
	    
	    driver.findElement(By.xpath("//button[@class='btn btn-primary'][text()='Save']")).click();
	    
	    WebElement getInvalidEmailErrMsg = driver.findElement(By.xpath("//div[contains(text(),'Email is not a valid email address.')]"));
	    String actualInvalidEmaillErrMsg = getInvalidEmailErrMsg.getText().trim();
	    System.out.println(actualInvalidEmaillErrMsg);
	    
	    Assert.assertEquals(actualInvalidEmaillErrMsg, "Email is not a valid email address.");
	    
	    String actualInvalidLinkedinErrMsg = driver.findElement
	    		(By.xpath("//div[contains(text(),'LinkedIn is not a valid URL.')]")).getText().trim();
	    
        System.out.println(actualInvalidLinkedinErrMsg);
        Assert.assertEquals(actualInvalidLinkedinErrMsg, "LinkedIn is not a valid URL.");
	}
	
	@AfterMethod
	public void TakesScreensho() throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\User\\eclipse-workspaceFW\\TechnoTackle_CC_Balaji\\target\\Screenshots\\Technoform3.png");
		FileUtils.copyFile(source, destination);
	}
	
	@AfterClass
	public void quitBrowser() {

		driver.quit();
	}

}
