package companyadmin;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.MethodsPOM;

public class CompanyCount 
{
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
		fis = new FileInputStream("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx");
	
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(4);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//CompanyAdminResults.html",true);
		test = extent.startTest("Compliance  Logging In - Company Admin");
		test.log(LogStatus.PASS, "Test Passed = Verify chrome browser.");
		extent.endTest(test);
		extent.flush();
	}
	
	@BeforeMethod
	
	void Login() throws InterruptedException, IOException
	{
		
		XSSFSheet sheet = ReadExcel();
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		
		
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"company");		//Method of Login class to login user.
		
	}
	
	

	
	//@Test(priority =0)
	void UserLogReport() throws InterruptedException, IOException, AWTException
	{
		test = extent.startTest("User Log Report Verification");
		
		
		CompanyMethods.UserLogReport(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority =1)
	void SelectMultipleUsers() throws InterruptedException, IOException, AWTException
	{
		test = extent.startTest("Select Multiple User  Verification");
		
		
		CompanyMethods.SelectMultipleUsers(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority =2)
	void UserFilter() throws InterruptedException, IOException, AWTException
	{
		test = extent.startTest("User filter Verification");
		
		
		CompanyMethods.UserFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority =3)
	void ExportButton() throws InterruptedException, IOException, AWTException
	{
		test = extent.startTest("Export Button  Verification");
		
		
		CompanyMethods.ExportButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
	@AfterMethod
	
	void Close()
	{
	  driver.close();
	}

}
