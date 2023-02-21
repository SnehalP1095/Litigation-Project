package demoLogin;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import licensePerformer.LiPerformerPOM;
import litigationManagement.CFOMethod;

public class Login1 {
	
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static List<WebElement> elementsList = null;
	
	
	


		public static void progress(WebDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait(driver, 180);
			try
			{
				Thread.sleep(300);
				wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
			}
			catch(Exception e)
			{
				
			}
		}
		
		public static void BrowserSetup(String URL)
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();		
			

			driver.manage().window().maximize();			//Set window size to maximum.

		}
		
//		public static XSSFSheet ReadExcel() throws IOException
//		{
//			//String workingDir = System.getProperty("user.dir");
//			fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Snehal\\ComplianceLatest\\ComplianceLatest\\TestData\\LitigationSheet.xlsx");
//			
//			workbook = new XSSFWorkbook(fis);
//			sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
//			return sheet;
//		}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationMgmt.html",true);
		test = extent.startTest("Verify OpenBrowser");
		test.log(LogStatus.INFO, "Browser test is initiated");
		
//		XSSFSheet sheet = ReadExcel();
//		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
//		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		login.Login.BrowserSetup("https://login.teamleaseregtech.com/Login.aspx");					//Method of Login class to set browser.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
		
		
	}
	@Test(priority = 1)
	void correctUnPassword() throws InterruptedException, IOException
	{
		test = extent.startTest("Correct Username Password verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(3000);
		LoginMethod.correctUnPassword(driver);
		
		extent.endTest(test);
		extent.flush();
	}
	
	// @Test(priority = 2)
				void correctPassword() throws InterruptedException, IOException
				{
					test = extent.startTest("Incorrect Username verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.correctPassword(driver);
					
					extent.endTest(test);
					extent.flush();
				}
				
		//		@Test(priority = 3)
				void correctUsername() throws InterruptedException, IOException
				{
					test = extent.startTest("Incorrect Password verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.correctUsername(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
		//	 @Test(priority = 4)
				void IncorrectUsernamePassword() throws InterruptedException, IOException
				{
					test = extent.startTest("Incorrect username password verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.IncorrectUsernamePassword(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
	//			 @Test(priority = 5)
				void forgotPassword() throws InterruptedException, IOException
				{
					test = extent.startTest("Forgot Password verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.forgotPassword(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
		//		 @Test(priority = 6)
				void AccountLocked() throws InterruptedException, IOException
				{
					test = extent.startTest("Account Locked verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.forgotPassword(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
		//	 @Test(priority =7)
				void GmailAccount() throws InterruptedException, IOException
				{
					test = extent.startTest("Gmail Account  verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.Google(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
		//		@Test(priority = 8)
				void LoginHelp() throws InterruptedException, IOException
				{
					test = extent.startTest("Login help  verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					Thread.sleep(3000);
					LoginMethod.LoginHelp(driver, test, "Cfo-");
					
					extent.endTest(test);
					extent.flush();
				}
	

}
