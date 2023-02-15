package litigationExternalLawyer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import licensePerformer.LiPerformerPOM;
import litigationAdditionalOwner.MethodPOM1;
import litigationAdditionalOwner.MethodsPOM;

public class ExternalLawyer 

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
		fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Snehal\\ComplianceLatest\\ComplianceLatest\\TestData\\LitigationSheet.xlsx");
	
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(9);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationExternalLawyer.html",true);
		test = extent.startTest("Verify OpenBrowser");
		test.log(LogStatus.INFO, "Browser test is initiated");
		
		XSSFSheet sheet = ReadExcel();
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 1)
	void Login() throws InterruptedException, IOException
	{
		test = extent.startTest("Litigation Logging In - External Lawyer");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"company");		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	

//	 @Test(priority = 2)
		void NoticeOpen() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice - Open Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.NoticeOpen(driver, test, workbook, "Performer");
			
			test.log(LogStatus.PASS, "Test Passed.");
			extent.endTest(test);
			extent.flush();
		}
//	 @Test(priority = 3)
		void CaseOpen() throws InterruptedException, IOException
		{
			test = extent.startTest("Case - Open Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.CaseOpen(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
//	 @Test(priority = 4)
		void CloseNotice() throws InterruptedException, IOException
		{
			test = extent.startTest("Close Notice Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
		
			MethodPOM.CloseNoticeCase(driver, test, workbook,"Notice");
				extent.endTest(test);
			extent.flush();
		}
//	 @Test(priority = 5)
		void CloseCase() throws InterruptedException, IOException
		{
		test = extent.startTest("Close Case Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.CloseNoticeCase(driver, test, workbook,"Case");
			
		extent.endTest(test);
			extent.flush();
		}
//		@Test(priority = 6)
		void LinkNotice() throws InterruptedException, IOException
		{
			test = extent.startTest("Link Notice Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.LinkDocument(driver, test, workbook, "Notice");
			
			extent.endTest(test);
			extent.flush();
		}
//	@Test(priority = 7)
		void LinkCase() throws InterruptedException, IOException
		{
			test = extent.startTest("Link Case Verification");
		test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.LinkDocument(driver, test, workbook, "Case");
		
		extent.endTest(test);
			extent.flush();
		}
//		@Test(priority = 8)
		void NoticeClosed() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice - Closed Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.NoticeClosed(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
//		@Test(priority = 9)
		void CaseClose() throws InterruptedException, IOException
		{
			test = extent.startTest("Case - Closed Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.CaseClosed(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
//		@Test(priority = 10)
		void TaskOpen() throws InterruptedException, IOException
		{
			test = extent.startTest("Task - Open Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.TaskOpen(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
		
//		@Test(priority = 11)
		void TaskClosed() throws InterruptedException, IOException
		{
			test = extent.startTest("Task - Closed Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.TaskClosed(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
//		@Test(priority = 12)
		void NoticeDocViewandDownload() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice Document verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM.NoticeDocViewandDownload(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
 //	 @Test(priority = 13)
			void CaseDocumentTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Notice Document verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.Document(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}		 
 	 
// 	 @Test(priority = 14)
			void CaseTaskActivityTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activity verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.TaskActivity1(driver, test,workbook,"Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 15)
			void CaseHearingTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Hearing verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.CaseHearing(driver, test,workbook,"Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 16)
			void CaseOrderTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Order verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.CaseOrder(driver, test,workbook,"Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 17)
			void CaseAdvocateBillTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Case advocate bill verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.AdvocateBill(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 18)
			void StatusPayment() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Status/Payment verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.StatusPayment(driver, test,workbook,"Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 19)
			void Auditlog() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Audit Log verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.Auditlog(driver,test);
				
				extent.endTest(test);
				extent.flush();
			}
//		@Test(priority = 20)
			void AdvancedSearchworkspace() throws InterruptedException, IOException
			{
				test = extent.startTest("Advanced Search Reports excel  verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.AdvancedSearchWorkspace(driver, test, "Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 21)
			void MyDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("Advanced search -Download and View Document");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.MyDocument(driver, test, workbook, "Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 22)
			void AdvancedSearchDoc() throws InterruptedException, IOException
			{
				test = extent.startTest("Download and View Document");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.AdvancedSearchDocument(driver, test,"Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//			@Test(priority = 23)
		    void MyReports() throws InterruptedException, IOException
			{
				test = extent.startTest("Reports excel count verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.MyReports(driver, test, workbook, "Performer");
				
				extent.endTest(test);
				extent.flush();
			}
//		    @Test(priority = 24)
			void MoreReports() throws InterruptedException, IOException
			{
				test = extent.startTest("More Report-Reports excel  verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.MoreReport(driver, test, "Company Admin");
				
				extent.endTest(test);
				extent.flush();
			}
//		  @Test(priority = 25)
			void AdvancedSearch() throws InterruptedException, IOException
			{
				test = extent.startTest("Advanced SearchReports excel  verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.AdvancedSearchReport(driver, test, "Company Admin");
				
				extent.endTest(test);
				extent.flush();
			}
//		    @Test(priority = 26)
			void MyReminder() throws InterruptedException, IOException
			{
				test = extent.startTest("My Reminder verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.MyReminder(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
//		    @Test(priority = 27)
			void ImportUtility() throws InterruptedException, IOException
			{
				test = extent.startTest("Import Utility verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodsPOM.ImportUtility(driver,test);
				extent.endTest(test);
				extent.flush();
			}
		    
//			@Test(priority = 28)
			void CaseAdvocateBill() throws InterruptedException, IOException
			{
				test = extent.startTest("Advocate bill verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodPOM.AdvocateBillTab(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
//			 @Test(priority = 20)
			void WorkspaceFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("All Filters verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodsPOM.WorkspaceFilter(driver, test, "Company Admin");
				
				extent.endTest(test);
				extent.flush();
			}
//		 @Test(priority = 21)
			void DocumentFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("All Filters verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodsPOM.DocumentFilter(driver, test, "Company Admin");
				
				extent.endTest(test);
				extent.flush();
			}
//		 @Test(priority = 22)
			void ReportFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("All Filters verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				MethodsPOM.ReportFilter(driver, test, "Company Admin");
				
				extent.endTest(test);
				extent.flush();
			}
}
