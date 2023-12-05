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
		test = extent.startTest("Verify OpenBrowser");
		
		
		test.log(LogStatus.PASS, "Test Passed");
		extent.endTest(test);
		extent.flush();
	}
	
	@BeforeMethod()
	void Login() throws InterruptedException, IOException
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
	

	
//@Test(priority = 0)
	void HearingCalender() throws InterruptedException, IOException, AWTException
	{
		test = extent.startTest("Case Hearing Calender Verification");
		
		
		MethodsPOM.HearingCalender(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
 //@Test(priority =1)
 	void NoticeOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice - Open Count Verification");
 		
 		
 		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
 		
 		test.log(LogStatus.PASS, "Test Passed.");
 		extent.endTest(test);
 		extent.flush();
 	}
//@Test(priority =2)
		void NoticeWithExistingData() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice With Existing Data verification");
		   MethodsPOM.NoticeWithExistingData(driver, test);
		  extent.endTest(test);
			extent.flush();
		}
	
//@Test(priority =3)
     void NoticeWithInvalidData() throws InterruptedException, IOException
    {
	     test = extent.startTest("Notice With Invalid Data verification");
	
	
	     MethodsPOM.NoticeWithInvalidData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
   }
//@Test(priority =4)
	   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Two Mandatory Fields verification");
		
		
		     MethodsPOM.NoticeWithTwoMandatoryData(driver, test, workbook);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	 
//@Test(priority =5) 
	   void NoticeWithEmptyFields() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Empty Fields verification");
		
		
		     MethodsPOM.NoticeWithEmptyFields(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
//@Test(priority =6)
	   void NoticeSendMailWithDoc() throws InterruptedException, IOException
	  {
	 	     test = extent.startTest("Notice Summary-Send Mail With Document verification");
	 	
	 	
	 	      MethodsPOM.NoticeSendMailWithDoc(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }
	// @Test(priority =7)
	   void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
	  {
	 	     test = extent.startTest(" Notice Summary -Send Mail With Document Invalid Fields verification");
	 	
	 	
	 	      MethodsPOM.NoticeSendMailWithDocInvalidFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }
	 @Test(priority =8)
	   void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
	  {
	 	     test = extent.startTest("Notice Summary -Send Mail With Document Empty Fields verification");
	 	
	 	
	 	      MethodsPOM.NoticeSendMailWithDocEmptyFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }
	 @Test(priority =9)
	   void NoticeUserAssignment() throws InterruptedException, IOException
	  {
	 	     test = extent.startTest("Notice User Assignment  verification");
	 	
	 	
	 	      MethodsPOM.NoticeUserAssignment(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }

	   
	 @Test(priority =10)
	 	void LinkNotice() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Link Notice Verification");
	 		
	 		
	 		MethodsPOM.LinkDocument(driver, test, workbook, "Notice");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	   
	  @Test(priority =11)
	   void LinkNoticeViewIcon() throws InterruptedException, IOException
	  {
	 	     test = extent.startTest("Linked notice view icon  verification");
	 	
	 	
	 	      MethodsPOM.LinkNoticeViewIcon(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }
	 @Test(priority =12)
	   void LinkNoticeDeleteIcon() throws InterruptedException, IOException
	  {
	 	     test = extent.startTest("Linked notice Delete icon  verification");
	 	
	 	
	 	      MethodsPOM.LinkNoticeDeleteIcon(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }
	  
	 @Test(priority =13)
	     	void CaseOpen() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Case - Open Count verification");
	     		
	     		
	     		MethodsPOM.CaseOpen(driver, test, workbook, "CFO -");
	     		
	     		extent.endTest(test);
	     		extent.flush();
	     	}
	 @Test(priority =14)
	 	void CaseExistingData() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case with Existing Data verification");
	 		
	 		
	 		MethodsPOM.CaseExistingData(driver, test, workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority =15)
	 	void CaseWithInvalidData() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case with Invalid Data verification");
	 		
	 		
	 		MethodsPOM.CaseWithInvalidData(driver, test, workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority =16)
	   	void CaseWithTwoFieldsData() throws InterruptedException, IOException
	   	{
	   		test = extent.startTest("Case with Two Manadatory fields verification");
	   		
	   		
	   		MethodsPOM.CaseWithTwoFieldsData(driver, test);
	   		
	   		extent.endTest(test);
	   		extent.flush();
	   	}
	 @Test(priority =17)
	   	void CaseWithEmptyFields() throws InterruptedException, IOException
	   	{
	   		test = extent.startTest("Case with Empty fields verification");
	   		
	   		
	   		MethodsPOM.CaseWithEmptyFields(driver, test);
	   		
	   		extent.endTest(test);
	   		extent.flush();
	   	}
	   	
	 @Test(priority =18)
	     			void TaskOpen() throws InterruptedException, IOException
	     			{
	     				test = extent.startTest("Task - Open Count verification");
	     				
	     				
	     				MethodsPOM.TaskOpen(driver, test, workbook, "CFO");
	     				
	     				extent.endTest(test);
	     				extent.flush();
	     			}
	@Test(priority = 19)
		void TaskwithExistingData() throws InterruptedException, IOException
		{
			test = extent.startTest("Task With existing data verification");
			
			
			MethodsPOM.TaskWithExistingData(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
	 @Test(priority =20)
	     			void TaskwithTwoManadatoryFields() throws InterruptedException, IOException
	     			{
	     				test = extent.startTest("Task With Two manadatory fields verification");
	     				
	     				
	     				MethodsPOM.TaskWithTwoMandatoryFields(driver, test, workbook);
	     				
	     				extent.endTest(test);
	     				extent.flush();
	     			}
	    @Test(priority = 21)
	 			void TaskwithoutData() throws InterruptedException, IOException
	 			{
	 				test = extent.startTest("Task Without  data verification");
	 				
	 				
	 				MethodsPOM.TaskwithoutData(driver, test);
	 				
	 				extent.endTest(test);
	 				extent.flush();
	 			}
	 			
	 		 @Test(priority = 22)
	 			void TaskDelete() throws InterruptedException, IOException
	 			{
	 				test = extent.startTest("Task Delete verification");
	 				
	 				
	 				MethodsPOM.TaskDelete(driver, test);
	 				
	 				extent.endTest(test);
	 				extent.flush();
	 			}
	    	
	  @Test(priority = 23)
	     	void NoticeClosed() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Notice - Closed Count verification");
	     		
	     		
	     		MethodsPOM.NoticeClosed(driver, test, workbook, "Company Admin");
	     		
	     		extent.endTest(test);
	     		extent.flush();
	     	}
	@Test(priority =24)
		void CaseClose() throws InterruptedException, IOException
		{
			test = extent.startTest("Case - Closed Count Verification");
			
			MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
			
			extent.endTest(test);
			extent.flush();
		}
	 	
	 @Test(priority = 25)
	 		void LinkCase() throws InterruptedException, IOException
	 		{
	 			test = extent.startTest("Link Case Verification");
	 		
	 			
	 			MethodsPOM.LinkDocument(driver, test, workbook, "Case");
	 		
	 		extent.endTest(test);
	 			extent.flush();
	 		}
	 	
	  @Test(priority =26)
	 	   void LinkCaseViewIcon() throws InterruptedException, IOException
	 	  {
	 		     test = extent.startTest("Linked case view icon  verification");
	 		
	 		
	 		      MethodsPOM.LinkCaseViewIcon(driver, test);
	 		
	 		     extent.endTest(test);
	 		     extent.flush();
	 	 }
	  @Test(priority =27)
	 	   void LinkCaseDeleteIcon() throws InterruptedException, IOException
	 	  {
	 		     test = extent.startTest("Linked case delete icon  verification");
	 		
	 		
	 		      MethodsPOM.LinkCaseDeleteIcon(driver, test);
	 		
	 		     extent.endTest(test);
	 		     extent.flush();
	 	 }
	 	 	
	 	   
	 @Test(priority = 28)
	     	void CloseNotice() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Close Notice Count verification");
	     		
	     		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
	     		
	     		extent.endTest(test);
	     		extent.flush();
	     	}
	 @Test(priority = 29)
	 			void CloseCase() throws InterruptedException, IOException
	 			{
	 			test = extent.startTest("Close Case Count Verification");
	 				
	 				
	 				MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
	 				
	 			extent.endTest(test);
	 				extent.flush();
	 			}
	 	  
	 @Test(priority = 30)
	 			void TaskClosed() throws InterruptedException, IOException
	 			{
	 				test = extent.startTest("Task - Closed Count verification");
	 				
	 				
	 				MethodsPOM.TaskClosed(driver, test, workbook, "CFO");
	 				
	 				extent.endTest(test);
	 				extent.flush();
	 			}
	 	@Test(priority = 31)
	     	void NoticeDocument() throws InterruptedException, IOException
	     	{
	     		test = extent.startTest("Notice Document verification");
	     		
	     		
	     		MethodsPOM.NoticeDocument(driver, test);
	     		
	     		extent.endTest(test);
	     		extent.flush();

	     	}
	  
	 	@Test(priority = 32)
	 	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice Without Upload Document verification");
	 		
	 		
	 		MethodsPOM.NoticeWithoutUploadDocument(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 	@Test(priority = 33)
	 	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice Document Share with Invaid data verification");
	 		
	 		
	 		MethodsPOM.NoticeDocumentShareInvalidData(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority = 34)
	 	void NoticeDocumentShareWithoutData() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice Document Share without data verification");
	 		
	 		
	 		MethodsPOM.NoticeDocumentShareWithoutData(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 	@Test(priority = 35)
	 	void NoticeTaskActivity() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice TaskActivtiy verification");
	 		
	 		
	 		MethodsPOM.TaskActivtity(driver, test,workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority = 36)
	 void TaskActivtityDeleteResponse() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Task/Activtiy Delete Response verification");
	 	
	 	
	 	MethodsPOM.TaskActivtityDeleteResponse(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 37)
	 void TaskActivtityExistingData() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Task/Activtiy with existing data verification");
	 	
	 	
	 	MethodsPOM.TaskActivtityExistingData(driver, test,workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority =38)
	 void TaskActivtityWithoutData() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Task/Activtiy Without data verification");
	 	
	 	
	 	MethodsPOM.TaskActivtityWithoutData(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 39)
	 void TaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Task/Activtiy Response Without data verification");
	 	
	 	
	 	MethodsPOM.TaskActivtityResponseWithoutStatus(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority =40)
	 void NoticeResponse() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Response verification");
	 	
	 	
	 	MethodsPOM.Response(driver, test,workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority =41)
	 void ResponseExistingData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Response Existing Data verification");


	 MethodsPOM.ResponseExistingData(driver, test,workbook);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority =42)
	 void NoticeResponseWithoutData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Response Without data verification");


	 MethodsPOM.ResponseWithoutData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 43)
	 void NoticePayment() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Payment verification");
	 	
	 	
	 	MethodsPOM.PaymentLog(driver,test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 44)
	 void PaymentLogwithExistingData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Payment with existing data verification");


	 MethodsPOM.PaymentLogExistingData(driver,test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 45)
	 void NoticePaymentWithoutData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Payment Without data verification");


	 MethodsPOM.PaymentLogWithoutData(driver,test,workbook);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 46)
	 void NoticeExternalLawyer() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Lawyer verification");
	 	
	 	MethodsPOM.ExternalLawyerRating(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 47)
	 void CriteriaExistingData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Criteria Existing Data verification");

	 MethodsPOM.CriteriaExistingData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 48)
	 void CriteriaInvalidData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Criteria Invalid Data verification");

	 MethodsPOM.CriteriaInvalidData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 49)
	 void CriteriaWithoutData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Notice Criteria Without Data verification");

	 MethodsPOM.CriteriaWithoutData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 50)
	 void NoticeAuditLog() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Notice Audit Log verification");

	 	
	 	MethodsPOM.AuditLog(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }		
	 @Test(priority =51)
	 void CaseDocument() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case - Document Tab");
	 	
	 	
	 	MethodsPOM.Document(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 52)
	 void CaseWithoutUploadDocument() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case Without Upload Document verification");
	 	
	 	
	 	MethodsPOM.CaseWithoutUploadDocument(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 53)
	 void CaseDocumentEmptyFields() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Case Document with empty fields verification");


	 MethodsPOM.CaseDocumentEmptyFields(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }

	 @Test(priority = 54)
	 void CaseDocumentSearchFields() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Case Document Search Fields verification");


	 MethodsPOM.CaseDocumentSearchFields(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority = 55)
	 void CaseDocumentShareInvalidData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Case Document Share with Invaid data verification");


	 MethodsPOM.CaseDocumentShareInvalidData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority =56)
	 void CaseDocumentShareWithoutData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Case Document Share without data verification");


	 MethodsPOM.CaseDocumentShareWithoutData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority =57)
	 void CaseSendMailWithDoc() throws InterruptedException, IOException
	 {
	 	     test = extent.startTest("Case Summary-Send Mail With Document verification");
	 	
	 	
	 	      MethodsPOM.CaseSendMailWithDoc(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }

	 @Test(priority =58)
	 void CaseSendMailWithDocInvalidFields() throws InterruptedException, IOException
	 {
	 	     test = extent.startTest("Case  Summary-Send Mail With Document Invalid Fields verification");
	 	
	 	
	 	      MethodsPOM.CaseSendMailWithDocInvalidFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	 }

@Test(priority = 59)
	 	void CaseTaskActivityTab() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Task/Activity verification");
	 		
	 		
	 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}

	 @Test(priority = 60)
	 	void CaseTaskActivityWithoutData() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Task/Activtiy Without data verification");
	 		
	 		
	 		MethodsPOM.CaseTaskActivityWithoutData(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority =61)
	 void CaseTaskActivitywithExistingData() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case - Task/Activty with existing data");
	 	
	 	
	 	MethodsPOM.CaseTaskActivitywithExistingData(driver, test,workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }

	 @Test(priority = 62)
	 	void CaseTaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Task/Activtiy Response Without data verification");
	 		
	 		
	 		MethodsPOM.CaseTaskActivtityResponseWithoutStatus(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 	@Test(priority =63)
	 	void CaseHearingcfo() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case - CaseHearing Tab");
	 		
	 		
	 		MethodsPOM.CaseHearing(driver, test,workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	
	 @Test(priority= 64)
	 void CaseWithoutHearingData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Case without hearing data Verification");


	 MethodsPOM.CaseHearingWithoutData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority =65)
	 void CaseHearingInvalidDate() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case Invalid Hearing Date Verification");
	 	
	 	
	 	MethodsPOM.CaseHearingInvalidDate(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	@Test(priority = 66)
	 	void CaseOrderTab() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Order verification");
	 		
	 		
	 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority =67)
	 void CaseOrderExistingData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Case Order with Duplicate data");


	 MethodsPOM.CaseOrderWithExistingData(driver, test,workbook);

	 extent.endTest(test);
	 extent.flush();
	 }

	 @Test(priority =68)
	 void CaseOrderWithoutData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("Without data Order tab");


	 MethodsPOM.CaseOrderWithoutData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 @Test(priority =69)
	 void CaseStatusPayment() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case - Status/Payment Tab");
	 	
	 	
	 	MethodsPOM.StatusPayment(driver, test,workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }
	
	 @Test(priority =70)
	 void StatusPaymentWithoutdata() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Case Status/Payment without data ");
	 	
	 	
	 	MethodsPOM.StatusPaymentWithoutdata(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 }

	 	@Test(priority =71)
	 	void CaseStatuswithEmptyFields() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case Status With Empty Fields");
	 		
	 		
	 		MethodsPOM.CaseStatuswithEmptyFields(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
		@Test(priority =72)
	 	void ExternalLawyer() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case External Lawyer verification");
	 		
	 		
	 		MethodsPOM.ExternalLawyer(driver, test,1);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 	@Test(priority =73)
	 	void CaseExternalLawyerCriteria() throws InterruptedException, IOException
	 	{
	 	test = extent.startTest("Case - External Lawyer Rating -Add New Criteria ");
	 	
	 	
	 	MethodsPOM.CaseExternalLawyerCriteria(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 	}

	 @Test(priority = 74)
	 	void CaseExistingCriteria() throws InterruptedException, IOException
	 	{
	 	test = extent.startTest("Case Criteria Existing Data verification");
	 	
	 	MethodsPOM.CaseExistingCriteria(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 	}
	 @Test(priority = 75)
	 	void CaseCriteriaInvalidData() throws InterruptedException, IOException
	 	{
	 	test = extent.startTest("Case Criteria Invalid Data verification");
	 	
	 	MethodsPOM.CaseCriteriaInvalidData(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 	}
	 @Test(priority = 76)
	 	void CaseCriteriaWithoutData() throws InterruptedException, IOException
	 	{
	 	test = extent.startTest("Case Criteria Without Data verification");
	 	
	 	MethodsPOM.CaseCriteriaWithoutData(driver, test);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 	}
	 @Test(priority =77)
	 	void CaseAuditLog() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Case - Audit Log Tab");
	 		
	 		
	 		MethodsPOM.Auditlog(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority = 78)
	 	void MyDocument() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("My Document-Download and View Document");
	 	
	 		
	 		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 	
	 @Test(priority = 79)
	 	void ShareCaseDocument() throws InterruptedException, IOException
	 	{
	 	test = extent.startTest("My Document-Share Case Document Verification");
	 	
	 	
	 	MethodsPOM.ShareCaseDocument(driver, test, workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 	}
	 @Test(priority = 80)
	 	void ShareNoticeDocument() throws InterruptedException, IOException
	 	{
	 	test = extent.startTest("My Document-Share Notice Document Verification");
	 	
	 	
	 	MethodsPOM.ShareNoticeDocument(driver, test, workbook);
	 	
	 	extent.endTest(test);
	 	extent.flush();
	 	}
	 @Test(priority =81)
	 		void ShareTaskDocument() throws InterruptedException, IOException
	 		{
	 			test = extent.startTest("My Document-Share Task Document Verification");


	 			MethodsPOM.ShareTaskDocument(driver, test, workbook);

	 			extent.endTest(test);
	 			extent.flush();
	 		}
		@Test(priority = 82)
		void MyReports() throws InterruptedException, IOException
		{
			test = extent.startTest("Reports -excel count verification");
			
			
			MethodsPOM.MyReports(driver, test, workbook, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 83)
		void MoreReports() throws InterruptedException, IOException
		{
			test = extent.startTest("More Report-Reports excel  verification");
			
			
			MethodsPOM.MoreReport(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
	 @Test(priority =0)
	 	void MyReminder() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("My Reminder verification");
	 		
	 		MethodsPOM.MyReminder(driver, test, workbook);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	 @Test(priority =1)
	 void ReminderWithoutData() throws InterruptedException, IOException
	 {
	 test = extent.startTest("My Reminder Without data verification");

	 MethodsPOM.ReminderWithoutData(driver, test);

	 extent.endTest(test);
	 extent.flush();
	 }
	 	
	 @Test(priority = 2)
	 	void ImportUtility() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Import Utility verification");
	 		
	 		
	 		MethodsPOM.ImportUtility(driver,test);
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	  @Test(priority = 3)
	 void ImportUtilityWithoutData() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Upload Empty File Import Utility verification");
	 	MethodsPOM.ImportUtilityWithoutData(driver,test);
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 4)
	 void ImportUtilityInvalidData() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Enter Invalid data in Upload File Import Utility verification");


	 	MethodsPOM.ImportUtilityInvalidData(driver,test);
	 	extent.endTest(test);
	 	extent.flush();
	 }
	 @Test(priority = 5)
	 void ImportUtilityTwoManadtoryFileds() throws InterruptedException, IOException
	 {
	 	test = extent.startTest("Enter Two Manadtory fields in Upload File Import Utility verification");


	 	MethodsPOM.ImportUtilityTwoManadtoryFileds(driver,test);
	 	extent.endTest(test);
	 	extent.flush();
	 }
	
 @AfterMethod
	 
	 void Close()
	 {
		 driver.close(); 
	 }


}
