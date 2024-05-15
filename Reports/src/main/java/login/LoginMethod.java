package login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import litigationAdditionalOwner.performerPOM;

public class LoginMethod {
	
	
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static List<WebElement> elementsList = null;
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		//String workingDir = System.getProperty("user.dir");
		fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Snehal\\ComplianceLatest\\ComplianceLatest\\TestData\\LitigationSheet.xlsx");
		
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(12);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@Parameters("browser")
	@BeforeTest

	public static void BrowserSetup(String URL,String browser)
	{

		if(browser.equalsIgnoreCase("chrome"))
		  {
			  WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();		
		  }
		  else if(browser.equalsIgnoreCase("edges"))
		  {
			  WebDriverManager.edgedriver().setup();
			  driver = new EdgeDriver();				
				
		  }
		  else if(browser.equalsIgnoreCase("FireFox"))
		  {
			  WebDriverManager.firefoxdriver().setup();
			   driver = new FirefoxDriver();
		  }
		driver.manage().window().maximize();			//Set window size to maximum.
		driver.get(URL);								//Set the URL of WebApplication.
	}
	
	@Test
	
	void setBrowser() throws Exception
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationCFO.html",true);
		test = extent.startTest("Verify OpenBrowser");
		test.log(LogStatus.INFO, "Browser test is initiated");
		
		XSSFSheet sheet = ReadExcel();
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		//login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		
		BrowserSetup(URL,"browser");
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test(priority = 1)
	
	void Login() throws InterruptedException, IOException
	{
	
		test = extent.startTest("Litigation Logging In - NonAdmin");
		//test.log(LogStatus.INFO, "Logging into system");
		

		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin1(uname,password,"cfo");		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	
	
	
 @Test(priority = 2)
	
	void CorrectPassword() throws InterruptedException, IOException
	{
		
		LoginPOM.Clickdiffuser(driver).click();
    	 XSSFSheet sheet = ReadExcel();
		test = extent.startTest("Litigation Logging In - Correct Password");
		//test.log(LogStatus.INFO, "Logging into system");
		
		
		Row row1 = sheet.getRow(3);						//Selected 1st index row (Second row)
		Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(4);						//Selected 2nd index row (Third row)
		Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin1(uname,password,"cfo");		//Method of Login class to login user.
		
		 Thread.sleep(3000);
		 String msg1 = LoginPOM.Clickreadmsg(driver).getText();
			if(msg1.contains("Please enter valid username or password."))
			{
				test.log(LogStatus.PASS, "Message Displayed" +msg1);
			}
			else
			{
				test.log(LogStatus.FAIL,"Message Displayed -" +msg1);
			}
		
		
		
		extent.endTest(test);
		extent.flush();
	}
   @Test(priority = 3)
 	
 	void CorrectUsername() throws InterruptedException, IOException
 	{
 	
 		test = extent.startTest("Litigation Logging In - Correct Username");
 		
		XSSFSheet sheet = ReadExcel();
 		Row row1 = sheet.getRow(5);						//Selected 1st index row (Second row)
 		Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
 		String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
 		
 		Row row2 = sheet.getRow(6);						//Selected 2nd index row (Third row)
 		Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
 		String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
 		
 		driver = login.Login.UserLogin1(uname,password,"cfo");		//Method of Login class to login user.
 		
 		 Thread.sleep(3000);
		 String msg1 = LoginPOM.Clickreadmsg(driver).getText();
			if(msg1.contains("Please enter valid username or password."))
			{
				test.log(LogStatus.PASS, "Message Displayed- " +msg1);
			}
			else
			{
				test.log(LogStatus.FAIL,"Message Displayed- " +msg1);
			}
 		
 		
 		extent.endTest(test);
 		extent.flush();
 		
 		// LoginPOM.Clickdiffuser(driver).click();
 	}
     
    @Test(priority = 4)
		void forgotPassword() throws InterruptedException, IOException
		{
    	 
    	
    	
			test = extent.startTest("Forgot Password verification");
			test.log(LogStatus.PASS, "Please enter email Id.");
			
			Thread.sleep(3000);
			driver = login.Login.forgotPassword();
			
//			 Thread.sleep(5000);
//			 String msg1 = LoginPOM.ClickreadMsg(driver).getText();
//				if(msg1.contains("Please enter email Id."))
//				{
//					test.log(LogStatus.PASS, "Message Displayed- " +msg1);
//				}
//				else
//				{
//					test.log(LogStatus.FAIL,"Message Displayed- " +msg1);
//				}
			extent.endTest(test);
			extent.flush();
		}
     
    @Test(priority = 5)
		void AccountLocked() throws InterruptedException, IOException
		{
    	 
    	 
    	
			test = extent.startTest("Account Locked verification");
			test.log(LogStatus.PASS, "Please Enter User Name/ Email ID.");
			
			Thread.sleep(3000);
			login.Login.AccountLocked(driver, test, "Cfo-");
			
//			 Thread.sleep(5000);
//			 String msg1 = LoginPOM.ClickreadMsg(driver).getText();
//				if(msg1.contains("Please Enter User Name/ Email ID."))
//				{
//					test.log(LogStatus.PASS, "Message Displayed- " +msg1);
//				}
//				else
//				{
//					test.log(LogStatus.FAIL,"Message Displayed- " +msg1);
//				}
			
			extent.endTest(test);
			extent.flush();
		}
	 @Test(priority =6)
				void GmailAccount() throws InterruptedException, IOException
				{
		    
					test = extent.startTest("Gmail Account  verification");
					test.log(LogStatus.PASS, "Google button work successfully");
					
					Thread.sleep(3000);
					login.Login.Google(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority = 7)
				void LoginHelp() throws InterruptedException, IOException
				{
					driver.navigate().back();
					test = extent.startTest("Login help  verification");
					test.log(LogStatus.PASS, "Login help link  work successfully");
					
					Thread.sleep(3000);
					login.Login.LoginHelp(driver, test, "Cfo-");
					Thread.sleep(2000);
					driver.navigate().back();
					extent.endTest(test);
					extent.flush();
				}
     
     //@AfterTest
 	void driverclose() throws InterruptedException
 	{
    	 Thread.sleep(2000);
 	   driver.close();
 	}
	
	
	

}
