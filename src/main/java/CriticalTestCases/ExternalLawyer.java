package CriticalTestCases;

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
	
		fis = new FileInputStream("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx");
	
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(9);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationExternalLawyer.html",true);
		test = extent.startTest("Litigation Logging In - External Lawyer");
		
		test.log(LogStatus.PASS, "Test Passed = Verify Chrome browser.");
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
		Cell c = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"company");		//Method of Login class to login user.
		
	}
	

@Test(priority = 0)
		void NoticeOpen() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice - Open Count Verification");
			
			
			MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
			
			
			extent.endTest(test);
			extent.flush();
		}



@Test(priority = 1)
			void CaseOpen() throws InterruptedException, IOException
			{
				test = extent.startTest("Case - Open Count Verification");
				
				
				MethodsPOM.CaseOpen(driver, test, workbook, "Performer");
				
				extent.endTest(test);
				extent.flush();
			}


@Test(priority = 2)
				void CloseNotice() throws InterruptedException, IOException
				{
					test = extent.startTest("Close Notice Count Verification");
				
				
					MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
						extent.endTest(test);
					extent.flush();
				}
@Test(priority = 3)
				void CloseCase() throws InterruptedException, IOException
				{
				test = extent.startTest("Close Case Count Verification");
					
					
				MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
					
				extent.endTest(test);
					extent.flush();
				}

@Test(priority = 4)
				void NoticeClosed() throws InterruptedException, IOException
				{
					test = extent.startTest("Notice - Closed Count Verification");
			
					
					MethodsPOM.NoticeClosed(driver, test, workbook, "Performer");
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 5)
				void CaseClose() throws InterruptedException, IOException
				{
					test = extent.startTest("Case - Closed Count Verification");
			
					
					MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 6)
				void TaskOpen() throws InterruptedException, IOException
				{
					test = extent.startTest("Task - Open Count Verification");
					
					MethodsPOM.TaskOpen(driver, test, workbook, "Performer");
					
					extent.endTest(test);
					extent.flush();
				}
		
		 			
		 @Test(priority = 7)
		 			void TaskDelete() throws InterruptedException, IOException
		 			{
		 				test = extent.startTest("Task Delete verification");
		 				
		 				
		 				MethodsPOM.TaskDelete(driver, test);
		 				
		 				extent.endTest(test);
		 				extent.flush();
		 			}
				
			//@Test(priority = 8)
				void TaskClosed() throws InterruptedException, IOException
				{
					test = extent.startTest("Task - Closed Count Verification");
					
					
					MethodsPOM.TaskClosed(driver, test, workbook, "Performer");
					
					extent.endTest(test);
					extent.flush();
				}
			@Test(priority = 0)
 			void ClosedTask() throws InterruptedException, IOException
 			{
 				test = extent.startTest(" Closed Task Count verification");
 				
 				
 				MethodsPOM.CloseNoticeCase(driver, test, workbook, "Task");
 				
 				extent.endTest(test);
 				extent.flush();
 			}
	
		  @Test(priority = 9)
			void NoticeDocumentTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Notice Document verification");
				
				
				MethodsPOM.NoticeDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	

	@Test(priority = 10)
		void NoticeTaskActivityTab() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice Task/Activity verification");
			
			
			MethodsPOM.TaskActivtity(driver, test,workbook);
			
			extent.endTest(test);
			extent.flush();
		}

		@Test(priority = 11)
			void NoticeResponseTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Notice Response tab verification");
				
				
				MethodsPOM.Response(driver, test,workbook);
				
				extent.endTest(test);
				extent.flush();
			}

	
	@Test(priority = 12)
			void NoticePaymentLogTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Notice PaymentLog tab verification");
				
				
				MethodsPOM.PaymentLog(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}

	

		@Test(priority = 13)
				void NoticeAuditLogTab() throws InterruptedException, IOException
				{
					test = extent.startTest("Notice AuditLog tab verification");
				
				
					MethodsPOM.AuditLog(driver, test);
				
					extent.endTest(test);
					extent.flush();
			}
		@Test(priority = 14)
						void CaseDocumentTab() throws InterruptedException, IOException
						{
							test = extent.startTest("Case Document verification");
							
							
							MethodsPOM.Document(driver, test);
							
							extent.endTest(test);
							extent.flush();
						}
	
			 	 
			 @Test(priority = 15)
						void CaseTaskActivityTab() throws InterruptedException, IOException
						{
							test = extent.startTest("Case Task/Activity verification");
					
							
							MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
							
							extent.endTest(test);
							extent.flush();
						}
			 
		
				@Test(priority = 16)
				void CaseHearingTab() throws InterruptedException, IOException
				{
					test = extent.startTest("Case Hearing verification");
				
					
					MethodsPOM.CaseHearing(driver, test,workbook);
					
					extent.endTest(test);
					extent.flush();
				}
			
		
			@Test(priority = 17)
			void CaseOrderTab() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Order verification");
				
				
				MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
				
				extent.endTest(test);
				extent.flush();
			}
		

// @Test(priority = 66)
	void CaseAdvocateBillTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Case advocate bill verification");
			
		MethodsPOM.AdvocateBill(driver, test);
				
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 18)
	void StatusPayment() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status/Payment verification");
			
				
		MethodsPOM.StatusPayment(driver, test,workbook);
				
		extent.endTest(test);
		extent.flush();
	}
	

	@Test(priority = 19)
			void Auditlog() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Audit Log verification");
	
				
				MethodsPOM.Auditlog(driver,test);
				
				extent.endTest(test);
				extent.flush();
			}
	




@Test(priority = 20)
void MyReports() throws InterruptedException, IOException
{
	test = extent.startTest("Reports excel count verification");
	
	
	MethodsPOM.MyReports(driver, test, workbook, "Performer");
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 21)
void MoreReports() throws InterruptedException, IOException
{
	test = extent.startTest("More Report-Reports excel  verification");
	
	
	MethodsPOM.MoreReport(driver, test, "Company Admin");
	
	extent.endTest(test);
	extent.flush();

}
@Test(priority = 22)
void MyReminder() throws InterruptedException, IOException
{
	test = extent.startTest("My Reminder verification");

	
	MethodsPOM.MyReminder(driver, test, workbook);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 23)
void ImportUtility() throws InterruptedException, IOException
{
	test = extent.startTest("Import Utility verification");
	
	
	MethodsPOM.ImportUtility(driver,test);
	extent.endTest(test);
	extent.flush();
}



//@Test(priority = 83)
void CaseAdvocateBill() throws InterruptedException, IOException
{
	test = extent.startTest("Advocate bill verification");
	
	
	MethodsPOM.AdvocateBillTab(driver, test);
	
	extent.endTest(test);
	extent.flush();
}


@AfterMethod

void Close()
{
	 driver.close(); 
}
}

