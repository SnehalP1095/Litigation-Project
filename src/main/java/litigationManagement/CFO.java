package litigationManagement;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.MethodsPOM;


public class CFO {
	
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
			sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
			return sheet;
		}
		
		@BeforeTest
	
		void setBrowser() throws Exception
		{
			String workingDir = System.getProperty("user.dir");
			extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationCFO.html",true);
			test = extent.startTest("Verify OpenBrowser");
			
			
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
		
			test = extent.startTest("Litigation Logging In - CFO");
			//test.log(LogStatus.INFO, "Logging into system");
			
	
			XSSFSheet sheet = ReadExcel();
			Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
			Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
			String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
			
			Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
			Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
			String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
			
			driver = login.Login.UserLogin(uname,password,"cfo");		//Method of Login class to login user.
			
			test.log(LogStatus.PASS, "Test Passed.");
			extent.endTest(test);
			extent.flush();
		}
		

		
	// @Test(priority = 2)
			void DashBoardFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("All Filters verification");
				test.log(LogStatus.INFO, "Test Initiated");
				
				Thread.sleep(3000);
				CFOMethod.DashBoardFilter(driver, test, "Cfo-");
				
				extent.endTest(test);
				extent.flush();
			}
		
	//@Test(priority = 3)
		void CaseNoticeStageGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Notice Stage Graph Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeStageGraph(driver, test,"cfo -");
			
			extent.endTest(test);
			extent.flush();
		}
	//	@Test(priority = 4)
		void CaseNoticeTypeGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Notice Type Graph Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"cfo -");
			
			extent.endTest(test);
			extent.flush();
		}
	//@Test(priority = 5)
		void RiskSummaryGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("Risk Graph Count Verification");
			test.log(LogStatus.INFO, "Test Initiated");
			Thread.sleep(3000);
			CFOMethod.RiskSummaryGraph(driver, test,"cfo -");
			
			extent.endTest(test);
			extent.flush();
		}
// @Test(priority = 6)
        void DepartmentSummaryGraph() throws InterruptedException, IOException
        {
	       test = extent.startTest("Department Graph Count Verification");
	       test.log(LogStatus.INFO, "Test Initiated");
	       Thread.sleep(3000);
	       CFOMethod.DepartmentSummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
 //  @Test(priority = 7)
        void LocationSummaryGraph() throws InterruptedException, IOException
        {
	       test = extent.startTest("Location Graph Count Verification");
	       test.log(LogStatus.INFO, "Test Initiated");
	       Thread.sleep(3000);
	       CFOMethod.LocationSummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
       
//@Test(priority = 8)
        void CategorySummaryGraph() throws InterruptedException, IOException
        {
	       test = extent.startTest("Category Graph Count Verification");
	       test.log(LogStatus.INFO, "Test Initiated");
	       Thread.sleep(3000);
	       CFOMethod.CategorySummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
        
  

    	
    	void NoticeOpen() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Open Count verification");
    		
    		
    		CFOMethod.NoticeOpen(driver, test, workbook, "CFO -");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
// @Test(priority =3)
     	void CaseOpen() throws InterruptedException, IOException
     	{
     		test = extent.startTest("Case - Open Count verification");
     		
     		
     		CFOMethod.CaseOpen(driver, test, workbook, "CFO -");
     		
     		extent.endTest(test);
     		extent.flush();
     	}
//	@Test(priority = 11)
    			void TaskOpen() throws InterruptedException, IOException
    			{
    				test = extent.startTest("Task - Open Count verification");
    				
    				
    				CFOMethod.TaskOpen(driver, test, workbook, "CFO");
    				
    				extent.endTest(test);
    				extent.flush();
    			}
      	
	//@Test(priority = 12)
    	void NoticeClosed() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Closed Count verification");
    		
    		
    		CFOMethod.NoticeClosed(driver, test, workbook, "Company Admin");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority = 13)
    	void CaseClose() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Closed Count verification");
    		
    		
    		CFOMethod.CaseClosed(driver, test, workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority =14)
		void LinkNotice() throws InterruptedException, IOException
		{
			test = extent.startTest("Link Notice Verification");
			
			
			CFOMethod.LinkDocument(driver, test, workbook, "Notice");
			
			extent.endTest(test);
			extent.flush();
		}
	//@Test(priority = 15)
		void LinkCase() throws InterruptedException, IOException
		{
			test = extent.startTest("Link Case Verification");
		
			
			CFOMethod.LinkDocument(driver, test, workbook, "Case");
		
		extent.endTest(test);
			extent.flush();
		}
	 	
	   
 	//@Test(priority = 16)
    	void CloseNotice() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Close Notice Count verification");
    		
    		CFOMethod.CloseNoticeCase(driver, test, workbook,"Notice");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//	@Test(priority = 17)
			void CloseCase() throws InterruptedException, IOException
			{
			test = extent.startTest("Close Case Count Verification");
				
				
				CFOMethod.CloseNoticeCase(driver, test, workbook,"Case");
				
			extent.endTest(test);
				extent.flush();
			}
	  
	//@Test(priority = 18)
			void TaskClosed() throws InterruptedException, IOException
			{
				test = extent.startTest("Task - Closed Count verification");
				
				
				CFOMethod.TaskClosed(driver, test, workbook, "CFO");
				
				extent.endTest(test);
				extent.flush();
			}

    	
  //	@Test(priority = 19)
    	void NoticeDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Document verification");
    		
    		
    		CFOMethod.NoticeDocument(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
 //@Test(priority = 20)
    	void NoticeTaskActivity() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice TaskActivtiy verification");
    		
    		
    		CFOMethod.TaskActivtity(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority = 21)
    	void NoticeResponse() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Response verification");
    		
    		
    		CFOMethod.Response(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    	
	//@Test(priority = 22)
    	void NoticePayment() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Payment verification");
    		
    		
    		CFOMethod.PaymentLog(driver,test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
  	//@Test(priority = 23)
    	void NoticeExternalLawyer() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Lawyer verification");
    		
    		CFOMethod.ExternalLawyer(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority = 24)
    	void NoticeAuditLog() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Audit Log verification");
    	
    		
    		CFOMethod.AuditLog(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}		
	///@Test(priority =25)
    	void CaseDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Document Tab");
    		
    		
    		CFOMethod.Document(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//	@Test(priority =26)
    	void CaseTaskActivity() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Task/Activty Tab");
    		
    		
    		CFOMethod.TaskActivity1(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority =27)
    	void CaseHearingcfo() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - CaseHearing Tab");
    		
    		
    		CFOMethod.CaseHearing(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority =28)
    	void CaseOrder() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Case Order Tab");
    	
    		
    		CFOMethod.CaseOrder(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//	@Test(priority =29)
    	void CaseStatusPayment() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Status/Payment Tab");
    		
    		
    		CFOMethod.StatusPayment(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority =30)
    	void CaseExternalLawyerRating() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - External Lawyer Rating");
    		
    		
    		CFOMethod.CaseExternalLawyer(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//	@Test(priority =31)
    	void CaseAuditLog() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Audit Log Tab");
    		
    		
    		CFOMethod.Auditlog(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    

 // @Test(priority = 32)
			void MyDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Download and View Document");
			
				
				CFOMethod.MyDocument(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
		
	
		
	// @Test(priority = 33)
				void MyReports() throws InterruptedException, IOException
				{
					test = extent.startTest("Reports -excel count verification");
					
					CFOMethod.MyReports(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	        
		//@Test(priority = 34)
				void MoreReports() throws InterruptedException, IOException
				{
					test = extent.startTest("More Report-Reports excel  verification");
					
					
					CFOMethod.MoreReport(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				
			//	@Test(priority =38)
				void MyReminder() throws InterruptedException, IOException
				{
					test = extent.startTest("My Reminder verification");
					
					CFOMethod.MyReminder(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
			//@Test(priority = 39)
				void ImportUtility() throws InterruptedException, IOException
				{
					test = extent.startTest("Import Utility verification");
					
					
					CFOMethod.ImportUtility(driver,test);
					extent.endTest(test);
					extent.flush();
				}
	//@Test(priority = 35)
			void AdvancedSearch() throws InterruptedException, IOException
			{
				test = extent.startTest("Workspace-Advanced Search Reports excel  verification");
				
				
				CFOMethod.AdvancedSearchWorkspace(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		//@Test(priority = 36)
			void AdvancedSearchDoc() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document(Advanced search) -Download and View Document");
				
				
				CFOMethod.AdvancedSearchDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		@Test(priority = 37)
			void AdvancedSearchreport() throws InterruptedException, IOException
			{
				test = extent.startTest("Advanced SearchReports excel  verification");
			
				
				CFOMethod.AdvancedSearchReport(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
				
		//@Test(priority = 40)
			void Masters() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Legal Entity  verification");
					
					CFOMethod.LegalEntity(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 41)
				void Masters1() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Law Firm verification");
					
					
					CFOMethod.LawFirm(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
//	@Test(priority = 42)
				void Masters2() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - 	User  verification");
					
					
					CFOMethod.User(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 42)
				void Masters3() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Opponent  verification");
					
					
					CFOMethod.Opponent(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
	//	@Test(priority = 43)
				void Masters4() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Court  verification");
					
					
					CFOMethod.Court(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//			@Test(priority = 44)
				void Masters5() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Case/NoticeType  verification");
					
					
					CFOMethod.CaseNoticeType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 45)
				void Masters6() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Payment Type  verification");
					
					
					CFOMethod.PaymentType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 46)
				void Masters7() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Custom Parameter  verification");
				
					
					CFOMethod.customParameter(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 47)
				void Masters8() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Case Stage  verification");
				
					
					CFOMethod.CaseStage(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//		@Test(priority = 48)
				void Masters9() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Document Type  verification");
					
					
					CFOMethod.DocumentType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 49)
				void Masters10() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Rating Criteria  verification");
				
					
					CFOMethod.RatingCriteria(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
	//		@Test(priority = 50)
				void Masters12() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Notice Stage  verification");
					
					
					CFOMethod.NoticeStage(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	//	@Test(priority = 51)
				void Masters11() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - UserReassignment  verification");
					
					
					CFOMethod.UserReassignment(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
	//@Test(priority = 52)
				void Masters13() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Mail Authorization  verification");
					
					CFOMethod.MailAuthorization(driver,test);
					
					extent.endTest(test);
					extent.flush();
				}
				
		//@Test(priority = 53)
				void HearingCalender() throws InterruptedException, IOException
				{
					test = extent.startTest("Hearing Calender verification");
				
					
					CFOMethod.HearingCalender(driver, test,"Performer","Cfo");
					
					extent.endTest(test);
					extent.flush();
				}
				
				
				
				//@Test(priority = 54)
				void Draft() throws InterruptedException, IOException
				{
					test = extent.startTest("Draft Count verification");
					//test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.Draft(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				
	///			@Test(priority = 55)
				void CaseHearing() throws InterruptedException, IOException
				{
					test = extent.startTest("Case Hearing Count Verification");
					//test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.CaseHearing(driver, test,"Performer","Case Hearing-");
					
					extent.endTest(test);
					extent.flush();
				}
	//	    	@Test(priority = 56)
		    	void NoticeDocViewandDownload() throws InterruptedException, IOException
		    	{
		    		test = extent.startTest("Notice Document verification");
		    		//test.log(LogStatus.INFO, "Test Initiated");
		    		
		    		CFOMethod.NoticeDocViewandDownload(driver, test);
		    		
		    		extent.endTest(test);
		    		extent.flush();
		    	}
				
			
			
				
				
//		      @Test(priority = 57)
				void WorkspaceFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("Workspace-All Filters verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.WorkspaceFilter(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
			
//				@Test(priority = 58)
				void DocumentFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("My Document- All Filters verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.DocumentFilter(driver, test, "cfo");
					
					extent.endTest(test);
					extent.flush();
				}
				
//			 @Test(priority = 59)
					void ReportFilter() throws InterruptedException, IOException
					{
						test = extent.startTest("My Report - All Filters verification");
						test.log(LogStatus.INFO, "Test Initiated");
						
						CFOMethod.ReportFilter(driver, test);
						
						extent.endTest(test);
						extent.flush();
					}
		
		 
		


		

		


	
//			@AfterTest()	
//			
//			void chromeclose() throws InterruptedException
//			{
//				Thread.sleep(5000);
//			  driver.close();
//			}

}
