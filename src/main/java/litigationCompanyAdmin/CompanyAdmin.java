package litigationCompanyAdmin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.MethodPOM1;
import litigationAdditionalOwner.MethodsPOM;

public class CompanyAdmin 
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
		sheet = workbook.getSheetAt(4);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationCompanyAdmin.html",true);
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
		test = extent.startTest("Litigation Logging In - Company Admin");
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


	
	

//	@Test(priority = 2)
	void CaseNoticeStageGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Notice Stage Graph Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseNoticeStageGraph(driver, test,"Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 3)
	void CaseNoticeTypeGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Notice Type Graph Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseNoticeTypeGraph(driver, test,"Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 4)
	
	void RiskSummaryGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Risk Summary Graph Count Verification");
	     test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.RiskSummaryGraph(driver, test,"Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
//    @Test(priority = 5)
	
	void DepartmentSummaryGraph() throws InterruptedException, IOException
	{
		test = extent.startTest(" Department Summary Graph Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.DepartmentSummaryGraph(driver, test,"Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
//  @Test(priority = 6)
	
	void LocationSummaryGraph() throws InterruptedException, IOException
	{
		test = extent.startTest(" Location Summary Graph Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.LocationSummaryGraph(driver, test,"Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
 //   @Test(priority = 7)
	
  	void CategorySummaryGraph() throws InterruptedException, IOException
  	{
  		test = extent.startTest(" Category Summary Graph Count Verification");
  		test.log(LogStatus.INFO, "Test Initiated");
  		
  		MethodsPOM.CategorySummaryGraph(driver, test,"Company Admin");
  		
  		extent.endTest(test);
  		extent.flush();
  	}
    
 @Test(priority = 5)
 	void NoticeOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice - Open Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
 		
 		test.log(LogStatus.PASS, "Test Passed.");
 		extent.endTest(test);
 		extent.flush();
 	}

	//@Test(priority = 6)
 	void CaseOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case - Open Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.CaseOpen(driver, test, workbook, "Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
 	
// 	@Test(priority = 7)
 	void CloseNotice() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Close Notice Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 	
 		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
 			extent.endTest(test);
 		extent.flush();
 	}
 	
// 	@Test(priority = 8)
 	void CloseCase() throws InterruptedException, IOException
 	{
 	test = extent.startTest("Close Case Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
 		
 	extent.endTest(test);
 		extent.flush();
 	}

 	
 	

// 	@Test(priority = 8)
 	void LinkNotice() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Link Notice Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.LinkDocument(driver, test, workbook, "Notice");
 		
 		extent.endTest(test);
 		extent.flush();
 	}

// 	@Test(priority = 9)
 	void LinkCase() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Link Case Verification");
 	test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.LinkDocument(driver, test, workbook, "Case");
 	
 	extent.endTest(test);
 		extent.flush();
 	}
 	
// 	@Test(priority = 10)
 	void NoticeClosed() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice - Closed Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.NoticeClosed(driver, test, workbook, "Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 11)
 	void CaseClose() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case - Closed Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
 	
 		
 		
 	
//	@Test(priority = 13)
 	void TaskOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Task - Open Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.TaskOpen(driver, test, workbook, "Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
// 	@Test(priority = 14)
 	void TaskClosed() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Task - Closed Count Verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.TaskClosed(driver, test, workbook, "Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}

// 	@Test(priority = 16)
 	void CaseDocumentTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice Document verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.Document(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 17)
 	void CaseTaskActivityTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Task/Activity verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 18)
 	void CaseHearingTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Hearing verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.CaseHearing(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 19)
 	void CaseOrderTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Order verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 20)
 	void CaseAdvocateBillTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case advocate bill verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.AdvocateBill(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 21)
 	void StatusPayment() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Status/Payment verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.StatusPayment(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 22)
 	void ExternalLawyer() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case External Lawyer verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.ExternalLawyer(driver, test,1);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
// 	@Test(priority = 23)
 	void Auditlog() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Audit Log verification");
 		test.log(LogStatus.INFO, "Test Initiated");
 		
 		MethodsPOM.Auditlog(driver,test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	
//	@Test(priority = 16)
	void AdvancedSearchworkspace() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Reports excel  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.AdvancedSearchWorkspace(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 17)
	void MyDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Download and View Document");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}

//	@Test(priority = 18)
	void AdvancedSearchDoc() throws InterruptedException, IOException
	{
		test = extent.startTest("Download and View Document");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.AdvancedSearchDocument(driver, test, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 19)
	void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("Reports -excel count verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MyReports(driver, test, workbook, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
    
//	@Test(priority = 20)
	void MoreReports() throws InterruptedException, IOException
	{
		test = extent.startTest("More Report-Reports excel  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MoreReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
//    @Test(priority = 21)
	void AdvancedSearch() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced SearchReports excel  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.AdvancedSearchReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 22)
	void MyReminder() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MyReminder(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 23)
	void ImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Import Utility verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.ImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}
	

  	
	
//	@Test(priority = 24)
	void Masters() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Legal Entity  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.LegalEntity(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 25)
	void Masters1() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Law Firm verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.LawFirm(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 26)
	void Masters2() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - 	User  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.User(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 27)
	void Masters3() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Opponent  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.Opponent(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 28)
	void Masters4() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Court  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.Court(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 29)
	void Masters5() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case/NoticeType  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseNoticeType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 30)
	void Masters6() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Payment Type  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.PaymentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 31)
	void Masters7() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Custom Parameter  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.customParameter(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 32)
	void Masters8() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case Stage  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseStage(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 33)
	void Masters9() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Document Type  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.DocumentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 34)
	void Masters10() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Rating Criteria  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.RatingCriteria(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 34)
	void Masters11() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Rating Criteria  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.PageAuthorization(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 34)
	void Masters12() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Advocate Bill Approver  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.AdvocateBillApprover(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 35)
	void Masters13() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - UserReassignment  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.UserReassignment(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
//	@Test(priority = 36)
	void Masters14() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Notice Stage  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.NoticeStage(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 37)
	void Masters15() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Mail Authorization  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MailAuthorization(driver);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
	
	
	

//	@Test(priority = 38)
	void CaseHearing() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Hearing Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseHearing(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 39)
	void HearingCalender() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Hearing Calender Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.HearingCalender(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 40)
	void NoticeDocViewandDownload() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.NoticeDocViewandDownload(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 23)
		void CaseAdvocateBill() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodsPOM.AdvocateBillTab(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
//		@Test(priority = 24)
		void CaseAdvocateBill1() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodsPOM.ApproverAssignmentLog(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
//	@Test(priority = 40)
	void CustomerMgmt() throws InterruptedException, IOException
	{
		test = extent.startTest("City-Customer Management verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.CustomerMgmt(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 41)
	void CustomerMgmtCustomer() throws InterruptedException, IOException
	{
		test = extent.startTest("Customer Mgmt Customer-Customer Management verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.CustomerMgmtCustomer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 42)
	void CustomerMgmtPlanVisit() throws InterruptedException, IOException
	{
		test = extent.startTest("Customer Mgmt Plan Visit-Customer Management verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.CustomerMgmtPalnVisit(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}	
//	@Test(priority = 43)
	void UpdateCommitmentsafterremarks() throws InterruptedException, IOException
	{
		test = extent.startTest("Update Commitments after remarks-Customer Management verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.UpdateCommitmentsafterremarks(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 44)
	void UpdateCommitmentsStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Update Commitments Status - Customer Management verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.UpdateCommitmentsStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 45)
	void Report() throws InterruptedException, IOException
	{
		test = extent.startTest("Report-Customer Management verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodPOM1.Report(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	 @Test(priority = 41)
		void DashBoardFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("All Filters verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM1.DashBoardFilter(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
//	 @Test(priority = 42)
		void WorkspaceFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("All Filters verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM1.WorkspaceFilter(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
//	 @Test(priority = 43)
		void DocumentFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("All Filters verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM1.DocumentFilter(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
//	 @Test(priority = 44)
		void ReportFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("All Filters verification");
			test.log(LogStatus.INFO, "Test Initiated");
			
			MethodPOM1.ReportFilter(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
	
	 
	
	

	

	

	

	

	 

	



    
    
	

	

}
