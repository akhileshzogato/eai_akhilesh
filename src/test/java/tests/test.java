package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.relevantcodes.rextendsreports.ExtentReports;

//import junit.framework.Assert;

public class test {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,60);
		    
	}

	@Test
	public void test1() throws InterruptedException {
		//login 
		driver.get("https://www.gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("akhilesh@zogato.com");
		//clicking on next button
		driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
		
		//checking welcome page
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1/content[.='Welcome']")));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password@123");
		//clicking on next
		driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='Compose' and @role='button']")));
		
		//compose an email and add label
		driver.findElement(By.xpath("//div[.='Compose' and @role='button']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='to']")));
		
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("akhilesh@zogato.com");
		driver.findElement(By.xpath("//textarea[@name='subjectbox']")).sendKeys("Assignment");
		
		
		//email is sent and tagged with a label
		driver.findElement(By.id("//div[@aria-label='More options']")).click();
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@aria-label='More options']")))
			.click(driver.findElement(By.xpath("//div[@role='menuitemcheckbox']//div[contains(.,'Social')]"))).build().perform();
		
		driver.findElement(By.xpath("//div[.='Send' and @role='button']")).click();
		//Verification of send email
		driver.findElement(By.xpath("//div[@data-tooltip='Inbox']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Social']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='UI']//tr[td//span[.='Assignment']]")));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}			
  
}
