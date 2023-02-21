package demoLogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import licensePerformer.LiPerformerPOM;
import login.LoginPOM;

public class LoginMethod {
	

	private static WebElement litigation = null;		
	//public static WebDriver driver = null;				//WebDriver instance created
	public static WebElement upload = null;				//WebElement to get upload button
	
	
	 public static void correctUnPassword(WebDriver driver) throws InterruptedException, IOException
		
	 {

		 
		 //WebDriverWait wait= new WebDriverWait(driver,30);
//		 WebElement UsernamePass  =driver.findElement(By.xpath("//*[@id='txtemail']"));
//		 UsernamePass.sendKeys("ganesh123@avantis.info");
				
	    login.LoginPOM.setUname(driver).sendKeys("ganesh123@avantis.info");		//Sent username to input box 
		Thread.sleep(500);
		login.LoginPOM.setPassword(driver).sendKeys("admin@123");	//Sent password to input box
		LoginPOM.clickSubmit(driver).click();				//Clicked on Sign-in button
		
	 }
	
	
		
		  public static void correctPassword(WebDriver driver) throws InterruptedException, IOException
			
			{
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			
			LoginPOM.setUname(driver).sendKeys("ganesh12@avantis.info");		//Sent username to input box 
			Thread.sleep(500);
			LoginPOM.setPassword(driver).sendKeys("admin@123");	//Sent password to input box
			LoginPOM.clickSubmit(driver).click();				//Clicked on Sign-in button
			
		}
		  public static void correctUsername(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
			
			{
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			
			LoginPOM.setUname(driver).sendKeys("ganesh123@avantis.info");		//Sent username to input box 
			Thread.sleep(500);
			LoginPOM.setPassword(driver).sendKeys("admin13");	//Sent password to input box
			LoginPOM.clickSubmit(driver).click();				//Clicked on Sign-in button
			
		}
		  public static void IncorrectUsernamePassword(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
			
			{
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			
			LoginPOM.setUname(driver).sendKeys("companyadmin");		//Sent username to input box 
			Thread.sleep(500);
			LoginPOM.setPassword(driver).sendKeys("admin123");	//Sent password to input box
			LoginPOM.clickSubmit(driver).click();				//Clicked on Sign-in button
			
		}
		  public static void forgotPassword(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
			
			{
		
			
			LoginPOM.ClickForgotPass(driver).click();
			
			LoginPOM.ClickEmailid(driver).sendKeys("");
			LoginPOM.ClickSubmit(driver).click();
			LoginPOM.ClickBackButton(driver).click();
		}
		  public static void AccountLocked(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
			
			{
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			
			LoginPOM.ClickAccountLocked(driver).click();
			
			LoginPOM.ClickEmailid(driver).sendKeys("");
			LoginPOM.ClickSubmit(driver).click();
			LoginPOM.ClickBackButton(driver).click();
		}
		  public static void Google(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
			
			{
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			
			LoginPOM.ClickGoogle(driver).click();
			
			LoginPOM.ClickEmailid(driver).sendKeys("");
			LoginPOM.ClickSubmit(driver).click();
			LoginPOM.ClickBackButton(driver).click();
		}
		  public static void LoginHelp(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
			
			{
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			
			LoginPOM.ClickLoginHelp(driver).click();
			
			
		}
}
	
	
		


