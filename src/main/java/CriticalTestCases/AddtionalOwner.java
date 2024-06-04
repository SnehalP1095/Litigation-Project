package CriticalTestCases;

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
import litigationAdditionalOwner.performer;
import litigationManagement.CFOMethod;

public class AddtionalOwner
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
		
		fis = new FileInputStream(performer.XmlFilePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);                        //Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{   
		String workingDir = System.getProperty("user.dir");
		System.out.println(workingDir);
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationPerformer.html",true);
		test = extent.startTest("Verify ChomeBrowser ");
		
		
		test.log(LogStatus.PASS, "Test Passed");
		extent.endTest(test);
		extent.flush();
	}
	
	@BeforeMethod()
	void Login() throws Exception
	{
     
		XSSFSheet sheet = ReadExcel();
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		

		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		 c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		System.out.println(password);
		
		driver = login.Login.UserLogin(uname,password, "Litigation");     //Method of Login class to login user Performer.

	}
	

	

 @Test(priority =1)
 	void NoticeOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice - Open Count Verification");
 		
 		
 		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
 		
 		test.log(LogStatus.PASS, "Test Passed.");
 		extent.endTest(test);
 		extent.flush();
 	}

	 @Test(priority =2)
	     	void CaseOpen() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Case - Open Count verification");
	     		
	     		
	     		MethodsPOM.CaseOpen(driver, test, workbook, "CFO -");
	     		
	     		extent.endTest(test);
	     		extent.flush();
	     	}
	
	   	
	 @Test(priority =3)
	     			void TaskOpen() throws InterruptedException, IOException
	     			{
	     				test = extent.startTest("Task - Open Count verification");
	     				
	     				
	     				MethodsPOM.TaskOpen(driver, test, workbook, "CFO");
	     				
	     				extent.endTest(test);
	     				extent.flush();
	     			}

	    	
	  @Test(priority = 4)
	     	void NoticeClosed() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Notice - Closed Count verification");
	     		
	     		
	     		MethodsPOM.NoticeClosed(driver, test, workbook, "Company Admin");
	     		
	     		extent.endTest(test);
	     		extent.flush();
	     	}
	@Test(priority =5)
		void CaseClose() throws InterruptedException, IOException
		{
			test = extent.startTest("Case - Closed Count Verification");
			
			MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
	 	
	
	 	   
	 @Test(priority = 6)
	     	void CloseNotice() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Close Notice Count verification");
	     		
	     		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice","performer a");
	     		
	     		extent.endTest(test);
	     		extent.flush();
	     	}
	 @Test(priority = 7)
	 			void CloseCase() throws InterruptedException, IOException
	 			{
	 			test = extent.startTest("Close Case Count Verification");
	 				
	 				
	 				MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case","performer a");
	 				
	 			extent.endTest(test);
	 				extent.flush();
	 			}
	 	  
	 @Test(priority = 8)
	 			void TaskClosed() throws InterruptedException, IOException
	 			{
	 				test = extent.startTest("Task - Closed Count verification");
	 				
	 				
	 				MethodsPOM.TaskClosed(driver, test, workbook, "CFO");
	 				
	 				extent.endTest(test);
	 				extent.flush();
	 			}
	 
	 @Test(priority = 9)
		void ClosedTask() throws InterruptedException, IOException
		{
			test = extent.startTest(" Closed Task Count verification");
			
			
			MethodsPOM.CloseNoticeCase(driver, test, workbook, "Task","performer a");
			
			extent.endTest(test);
			extent.flush();
		}
	 	@Test(priority = 10)
	     	void NoticeDocument() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Notice Document verification");
	     		
	     		
	     		MethodsPOM.NoticeDocument(driver, test);
	     		
	     		extent.endTest(test);
	     		extent.flush();

	     	}
	  
	
	 	@Test(priority = 11)
	 	void NoticeTaskActivity() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice- TaskActivity verification");
	 		
	 		
	 		MethodsPOM.TaskActivtity(driver, test,workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 	@Test(priority =11)
	    void TaskActivtityDeleteResponse() throws InterruptedException, IOException
	    {
		 test = extent.startTest("Notice Task/Activity Delete Response verification");
		
		
		 MethodsPOM.TaskActivtityDeleteResponse(driver, test);
		
		 extent.endTest(test);
		 extent.flush();
	  } 
	
	 @Test(priority =12)
	 void NoticeResponse() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice- Response verification");
	 	
	 	
	 	MethodsPOM.Response(driver, test,workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	
	 @Test(priority =12)
	 void NoticePayment() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice- Payment verification");
	 	
	 	
	 	MethodsPOM.PaymentLog(driver,test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	
	 @Test(priority = 13)
	 void NoticeExternalLawyer() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice - External Lawyer verification");
	 	
	 	MethodsPOM.ExternalLawyerRating(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	
	 @Test(priority = 14)
	 void NoticeAuditLog() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Audit Log verification");

	 	
	 	MethodsPOM.AuditLog(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }		
	 @Test(priority =15)
	 void CaseDocument() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case - Document Tab");
	 	
	 	
	 	MethodsPOM.Document(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	

@Test(priority =16)
	 	void CaseTaskActivityTab() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Task/Activity verification");
	 		
	 		
	 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}

	
	 	@Test(priority =17)
	 	void CaseHearingcfo() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case - CaseHearing Tab");
	 		
	 		
	 		MethodsPOM.CaseHearing(driver, test,workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	
	
	@Test(priority = 18)
	 	void CaseOrderTab() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Order verification");
	 		
	 		
	 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	
	 @Test(priority =19)
	 void CaseStatusPayment() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case - Status/Payment Tab");
	 	
	 	
	 	MethodsPOM.StatusPayment(driver, test,workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }

		@Test(priority =20)
	 	void ExternalLawyer() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case External Lawyer verification");
	 		
	 		
	 		MethodsPOM.ExternalLawyer(driver, test,1);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 
	
	 @Test(priority =21)
	 	void CaseAuditLog() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case - Audit Log Tab");
	 		
	 		
	 		MethodsPOM.Auditlog(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority =22)
	 	void MyDocument() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("My Document-Download and View Document");
	 	
	 		
	 		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}

		@Test(priority =23)
		void MyReports() throws InterruptedException, IOException
		{
			test = extent.startTest("Reports -excel count verification");
			
			
			MethodsPOM.MyReports(driver, test, workbook, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 24)
		void MoreReports() throws InterruptedException, IOException
		{
			test = extent.startTest("More Report-Reports excel  verification");
			
			
			MethodsPOM.MoreReport(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
	 @Test(priority =25)
	 	void MyReminder() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("My Reminder verification");
	 		
	 		MethodsPOM.MyReminder(driver, test, workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}

	 	
	 @Test(priority = 26)
	 	void ImportUtility() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Import Utility verification");
	 		
	 		
	 		MethodsPOM.ImportUtility(driver,test);
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 
	 @Test(priority = 27)
		void CaseUpdationImportUtility() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Updation Import Utility verification");
			
			
			CFOMethod.CaseUpdationImportUtility(driver,test);
			extent.endTest(test);
			extent.flush();
		}

	@Test(priority = 28)
	void NoticeUpdation() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Updation Import Utility verification");
		
		
		CFOMethod.NoticeUpdation(driver,test);
		extent.endTest(test);
		extent.flush();
	}

 @AfterMethod
	 
	 void Close()
	 {
		 driver.close(); 
	 }


}
