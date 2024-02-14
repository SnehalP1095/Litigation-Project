package litigationAdditionalOwner;

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

import litigationManagement.CFOMethod;




public class performer 
{
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static List<WebElement> elementsList = null;
	
	public static String XmlFilePath = "E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx";
	
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
		test = extent.startTest("Litigation Logging In - Non Admin");
		test.log(LogStatus.PASS, "Test Passed = Verify firefox browser.");
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
	
	/*@Test(priority = 1)
	void NoticeOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice - Open Count Verification");
		
		
		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
		
		//test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
  
@Test(priority =3)
		void NoticeWithExistingData() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice With Existing Data verification");
		   MethodsPOM.NoticeWithExistingData(driver, test,workbook);
		  extent.endTest(test);
			extent.flush();
		}
	
@Test(priority =4)
   void NoticeWithInvalidData() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice With Invalid Data verification");
	
	
	     MethodsPOM.NoticeWithInvalidData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
	 
@Test(priority =5)
	   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Two Mandatory Fields verification");
		
		
		     MethodsPOM.NoticeWithTwoMandatoryData(driver, test, workbook);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	 
@Test(priority =6) 
	   void NoticeWithEmptyFields() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Empty Fields verification");
		
		
		     MethodsPOM.NoticeWithEmptyFields(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }

@Test(priority =7)
	void NoticeClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Summary -Clear button verification");
	
	
		MethodsPOM.NoticeClearBtn(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
	}
	@Test(priority =8)
	 void NoticeSendMailWithDoc() throws InterruptedException, IOException
	{
		     test = extent.startTest("Notice Summary-Send Mail With Document verification");
		
		
		     MethodsPOM.NoticeSendMailWithDoc(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	}
	 
@Test(priority =9)
	 void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
	 {
	     test = extent.startTest(" Notice Summary -Send Mail With Document Invalid Fields verification");


	     MethodsPOM.NoticeSendMailWithDocInvalidFields(driver, test);

	     extent.endTest(test);
	     extent.flush();
	 }
	@Test(priority =10)
		void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
		{
		     test = extent.startTest("Notice Summary -Send Mail With Document Empty Fields verification");
		
		
		     MethodsPOM.NoticeSendMailWithDocEmptyFields(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
		}
	  
@Test(priority =11)
	  	void NoticeUserAssignment() throws InterruptedException, IOException
	 	{
		     test = extent.startTest("Notice User Assignment  verification");
		
	         MethodsPOM.NoticeUserAssignment(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 	}
@Test(priority =12)
	void NoticeDeleteUserAssignment() throws InterruptedException, IOException
	{
     test = extent.startTest("Notice Delete User Assignment  verification");

     MethodsPOM.NoticeDeleteUserAssignment(driver, test);

     extent.endTest(test);
     extent.flush();
	}

@Test(priority =13)
	void CaseOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Open Count Verification");
		
		MethodsPOM.CaseOpen(driver, test, workbook, "Performer");
		
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
		void CaseWithClearBtn() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Summary - Clear button verification");
			
			
			MethodsPOM.CaseWithClearBtn(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}

@Test(priority =19)
void CaseUserAssignment() throws InterruptedException, IOException
	{
		test = extent.startTest("Case User Assignment verification");
	
	
		MethodsPOM.CaseUserAssignment(driver, test,workbook);
	
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =20)
	void CaseUserAssignmentDelete() throws InterruptedException, IOException
	{
		test = extent.startTest("Case User Assignment Delete Icon  verification");

		
		MethodsPOM.CaseUserAssignmentDelete(driver, test);

		extent.endTest(test);
		extent.flush();
	}
	
	
@Test(priority =20)
		void CloseNotice() throws InterruptedException, IOException
		{
		test = extent.startTest("Close Notice Count Verification");
		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice","performer a");
		extent.endTest(test);
		extent.flush();
		}

@Test(priority = 21)
		void CloseCase() throws InterruptedException, IOException
		{
		test = extent.startTest("Close Case Count Verification");
			
			MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case","performer a");
			
		extent.endTest(test);
			extent.flush();
		}

	@Test(priority = 21)
	void LinkNotice() throws InterruptedException, IOException
	{
		test = extent.startTest("Link Notice Verification");
		
		
		MethodsPOM.LinkDocument(driver, test, workbook, "Notice");
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =22)
		void LinkNoticeViewIcon() throws InterruptedException, IOException
		{
		     test = extent.startTest("Linked notice view icon  verification");
		
		

		 	MethodsPOM.LinkNoticeViewIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
		}
@Test(priority=23)
	  void LinkNoticeDeleteIcon() throws InterruptedException, IOException
		{
		     test = extent.startTest("Linked notice Delete icon  verification");
		
		

		 	MethodsPOM.LinkNoticeDeleteIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
		}
@Test(priority = 24)
	void LinkCase() throws InterruptedException, IOException
	{
		test = extent.startTest("Link Case Verification");

		
		MethodsPOM.LinkDocument(driver, test, workbook, "Case");
	
	extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =25)
	   void LinkCaseViewIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case view icon  verification");
		
		
		     MethodsPOM.LinkCaseViewIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	  }
	@Test(priority =26)
	   void LinkCaseDeleteIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case delete icon  verification");
		
		
		     MethodsPOM.LinkCaseDeleteIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	
	
@Test(priority = 27)
	void NoticeClosed() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice - Closed Count Verification");
		
		
		MethodsPOM.NoticeClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 28)
	void CaseClose() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Closed Count Verification");
	
		
		MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	

	
@Test(priority = 29)
 	void NoticeDocumentTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice Document verification");
 		
 		
 		MethodsPOM.NoticeDocument(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
	}
@Test(priority = 30)
	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Without Upload Document verification");
		
		
		MethodsPOM.NoticeWithoutUploadDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 31)
	void NoticeDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Search Fields verification");
		
		
		MethodsPOM.NoticeDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 32)
	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share with Invalid data verification");
		
		
		MethodsPOM.NoticeDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =33)
		void NoticeDocumentShareWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice Document Share without data verification");
	
			
			MethodsPOM.NoticeDocumentShareWithoutData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	
@Test(priority = 34)
	void NoticeDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share close button verification");
		
		
		MethodsPOM.NoticeDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 35)
void NoticeTaskActivityTab() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Task/Activity verification");
	
	
	MethodsPOM.TaskActivtity(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =36)
	void TaskActivtityDeleteResponse() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Delete Response verification");
		
		
		MethodsPOM.TaskActivtityDeleteResponse(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =37)
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

@Test(priority =39)
	void TaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Response Without data verification");
		
		
		MethodsPOM.TaskActivtityResponseWithoutStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 40)
	void TaskActivtityResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy  Response clear button verification");
		
		
		MethodsPOM.TaskActivtityResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 41)
void NoticeResponseTab() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Response tab verification");
	
	
	MethodsPOM.Response(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =42)
void ResponseExistingData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Response Existing Data verification");


	MethodsPOM.ResponseExistingData(driver, test,workbook);

	extent.endTest(test);
	extent.flush();
}

@Test(priority =43)
void NoticeResponseWithoutData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Response Without data verification");

	MethodsPOM.ResponseWithoutData(driver, test);
    extent.endTest(test);
	extent.flush();
}	

@Test(priority =44)
void ResponseClearBtn() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Response Clear button verification");
	MethodsPOM.ResponseClearBtn(driver, test);
     extent.endTest(test);
     extent.flush();
}



@Test(priority = 45)
void NoticePaymentLogTab() throws InterruptedException, IOException
{
	test = extent.startTest("Notice PaymentLog tab verification");
	
	
	MethodsPOM.PaymentLog(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 46)
void PaymentLogwithExistingData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Payment with existing data verification");
	MethodsPOM.PaymentLogExistingData(driver,test);
   extent.endTest(test);
    extent.flush();
}
@Test(priority = 47)
void PaymentLogwithInvalidData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Payment with Invalid data verification");
	
	
	MethodsPOM.PaymentLogwithInvalidData(driver,test);
	
	extent.endTest(test);
	extent.flush();
}


@Test(priority = 48)
void NoticePaymentWithoutData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Payment Without data verification");


	MethodsPOM.PaymentLogWithoutData(driver,test,workbook);

	extent.endTest(test);
	extent.flush();
}

@Test(priority = 49)
void NoticeExternalLawyerTab() throws InterruptedException, IOException
{
	test = extent.startTest("Notice ExternalLawyerRating tab verification");
	
	
	MethodsPOM.ExternalLawyerRating(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 50)
void ExternalLawyerWithoutRating() throws InterruptedException, IOException
{
	test = extent.startTest("Notice External Lawyer without rating verification");
	
	MethodsPOM.ExternalLawyerWithoutRating(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =51)
void CriteriaInvalidData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Criteria Invalid Data verification");
	
	MethodsPOM.CriteriaInvalidData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 52)
void CriteriaExistingData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Criteria Existing Data verification");
	
	MethodsPOM.CriteriaExistingData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =53)
void CriteriaWithoutData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Criteria Without Data verification");
	
	MethodsPOM.CriteriaWithoutData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}




@Test(priority = 54)
void NoticeAuditLogTab() throws InterruptedException, IOException
{
	test = extent.startTest("Notice AuditLog tab verification");
	
	
	MethodsPOM.AuditLog(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
	
@Test(priority = 55)
	void NoticeDocViewandDownload() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Summary Document verification");
	
		
		MethodsPOM.NoticeDocViewandDownload(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =56)
	void CaseDocumentTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document verification");
		
		
		MethodsPOM.Document(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 57)
 	void CaseWithoutUploadDocument() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Document -Without Upload File verification");
 		
 		
 		MethodsPOM.CaseWithoutUploadDocument(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	
@Test(priority =58)
	void CaseDocumentEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document with empty fields verification");
		
		
		MethodsPOM.CaseDocumentEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =59)
	void CaseDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Search Fields verification");
		
		
		MethodsPOM.CaseDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =60)
	void CaseDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share with Invalid data verification");
		
		
		MethodsPOM.CaseDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =61)
	void CaseDocumentShareWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share without data verification");
		
		
		MethodsPOM.CaseDocumentShareWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =62)
	void CaseDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share close button verification");
		
		
		MethodsPOM.CaseDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
 @Test(priority =63)
    void CaseSendMailWithDoc() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case Summary-Send Mail With Document verification");
 	
 	    MethodsPOM.CaseSendMailWithDoc(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
 @Test(priority =64)
	    void CaseSendMailWithDocInvalidFields() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case  Summary-Send Mail With Document Invalid Fields verification");
	 	
	 	
	 	    MethodsPOM.CaseSendMailWithDocInvalidFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
@Test(priority =65)
	    void CaseSendMailWithEmptyFields() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case  Summary-Send Mail With Empty Fields verification");
	 	
	 	
	 	    MethodsPOM.CaseSendMailWithEmptyFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
@Test(priority = 66)
 	void CaseTaskActivityTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Task/Activity verification");
 		
 		
 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
@Test(priority = 67)
	void CaseTaskActivityWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Task/Activtiy Without data verification");
		
		
		MethodsPOM.CaseTaskActivityWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 
@Test(priority =68)
	void CaseTaskActivitywithExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Task/Activty with existing data");
		
		
		MethodsPOM.CaseTaskActivitywithExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
 
@Test(priority = 69)
	void CaseTaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Task/Activtiy Response Without data verification");
		
		
		MethodsPOM.CaseTaskActivtityResponseWithoutStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =70)
	void CaseTaskActivtityResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Task/Activtiy  Response clear button verification");
		
		
		MethodsPOM.CaseTaskActivtityResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 71)
 	void CaseHearingTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Hearing verification");
 		
 		
 		MethodsPOM.CaseHearing(driver, test,workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
	//@Test(priority =72)
	void CaseExistingHearingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Existing Hearing Date Verification");
		
		
		MethodsPOM.CaseExistingHearingData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 	
	 @Test(priority= 73)
 	  void CaseWithoutHearingData() throws InterruptedException, IOException
 	  {
 		test = extent.startTest("Case without hearing data Verification");
 		
 		
 		MethodsPOM.CaseHearingWithoutData(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	  }
 	 
 @Test(priority =74)
 	   void CaseHearingInvalidDate() throws InterruptedException, IOException
 	   {
 	 	test = extent.startTest("Case Invalid Hearing Date Verification");
 	 	
 	 	
 	 	MethodsPOM.CaseHearingInvalidDate(driver, test);
 	 	
 	 	extent.endTest(test);
 	 	extent.flush();
 	   }
	@Test(priority =75)
 	   void CaseHearingClearBtn() throws InterruptedException, IOException
 	   {
 	 	test = extent.startTest("Case heraing clear button Verification");
 	 	
 	 	
 	 	MethodsPOM.CaseHearingClearBtn(driver, test);
 	 	
 	 	extent.endTest(test);
 	 	extent.flush();
 	   }
	@Test(priority = 76)
 	void CaseOrderTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Order verification");
 		
 		
 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
@Test(priority =77)
 		void CaseOrderExistingData() throws InterruptedException, IOException
 		{
 			test = extent.startTest("To check validation message displayed  for case order with existing data");

 		
 			MethodsPOM.CaseOrderWithExistingData(driver, test,workbook);
 		
 			extent.endTest(test);
 			extent.flush();
 		}
 @Test(priority =78)
		void CaseOrderWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("To check validation message displayed  for case order without data");

		
			MethodsPOM.CaseOrderWithoutData(driver, test);
		
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority =79)
	void CaseOrderwithClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Order with clear button");

	
		MethodsPOM.CaseOrderwithClearBtn(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}

	//@Test(priority = 80)
 	void CaseAdvocateBillTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case advocate bill verification");
 	
 		
 		MethodsPOM.AdvocateBill(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
@Test(priority = 80)
 	void StatusPayment() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Status/Payment verification");
 	
 		
 		MethodsPOM.StatusPayment(driver, test,workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
@Test(priority = 81)
 	void StatusPaymentWithExistingData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Status/Payment with existing data verification");
 	
 		
 		MethodsPOM.StatusPaymentWithExistingData(driver, test,workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
@Test(priority =82)
	void StatusPaymentWithoutdata() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status/Payment without data ");
		
		
		MethodsPOM.StatusPaymentWithoutdata(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =83)
void StatusPaymentwithInvaliddata() throws InterruptedException, IOException
{
	test = extent.startTest("Case Status/Payment with Invalid data ");
	
	
	MethodsPOM.StatusPaymentwithInvaliddata(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}
 	
@Test(priority =84)
	void CaseStatusAppealtoNextCourt() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status Appeal to Next Court");
		
		
		MethodsPOM.CaseStatusAppealtoNextCourt(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =85)
	void CaseStatuswithEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status With Empty Fields");
		
		MethodsPOM.CaseStatuswithEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 86)
 	void ExternalLawyer() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case External Lawyer verification");
 		
 		
 		MethodsPOM.ExternalLawyer(driver, test,1);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	@Test(priority =87)
	void CaseExternalLawyerWitoutRating() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - External Lawyer Without Rating");
		
		
		MethodsPOM.CaseExternalLawyerWitoutRating(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 	
	@Test(priority =88)
 		void CaseExternalLawyerCriteria() throws InterruptedException, IOException
 		{
 			test = extent.startTest("Case - External Lawyer Rating -Add New Criteria ");
 			
 			
 			MethodsPOM.CaseExternalLawyerCriteria(driver, test);
 			
 			extent.endTest(test);
 			extent.flush();
 		}
 	
  @Test(priority = 89)
 	void CaseExistingCriteria() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Criteria Existing Data verification");
 		
 		MethodsPOM.CaseExistingCriteria(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
 	@Test(priority =90)
 		void CaseCriteriaInvalidData() throws InterruptedException, IOException
 		{
 			test = extent.startTest("Case Criteria Invalid Data verification");
 			
 			MethodsPOM.CaseCriteriaInvalidData(driver, test);
 			
 			extent.endTest(test);
 			extent.flush();
 		}
 	
	@Test(priority =91)
 		void CaseCriteriaWithoutData() throws InterruptedException, IOException
 		{
 			test = extent.startTest("Case Criteria Without Data verification");
 			
 			MethodsPOM.CaseCriteriaWithoutData(driver, test);
 			
 			extent.endTest(test);
 			extent.flush();
 		}
	@Test(priority = 92)
	void Auditlog() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Audit Log verification");
		
		
		MethodsPOM.Auditlog(driver,test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 93)
	void TaskOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Open Count Verification");
	
		
		MethodsPOM.TaskOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 94)
	void TaskwithExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Task With existing data verification");
		
		
		MethodsPOM.TaskWithExistingData(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =95)
	void TaskwithTwoManadatoryFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Task With Two manadatory fields verification");
		
		
		MethodsPOM.TaskWithTwoMandatoryFields(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
 
@Test(priority =96)
			void TaskwithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Task Without  data verification");
				
				
				MethodsPOM.TaskwithoutData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
@Test(priority =97)
			void TaskwithClearBtn() throws InterruptedException, IOException
			{
				test = extent.startTest("Task Clear button verification");
				
				
				MethodsPOM.TaskwithClearBtn(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
@Test(priority =98)
void TaskShowDetailesClearBtn() throws InterruptedException, IOException
{
	test = extent.startTest("Task-Show Detailes Icon -Clear button verification");
	
	
	MethodsPOM.TaskShowDetailesClearBtn(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 99)
void TaskDelete() throws InterruptedException, IOException
{
	test = extent.startTest("Task Delete verification");
	
	
	MethodsPOM.TaskDelete(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
	
	@Test(priority = 100)
	void TaskClosed() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Closed Count Verification");
		
		
		MethodsPOM.TaskClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	 @Test(priority = 101)
		void ClosedTask() throws InterruptedException, IOException
		{
			test = extent.startTest(" Closed Task Count verification");
			
			
			MethodsPOM.CloseNoticeCase(driver, test, workbook,"Task","performer a");
			
			extent.endTest(test);
			extent.flush();
		}
	
	@Test(priority = 102)
	void WorkspaceFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Workspace - Notice - Multiple Filters verification");
		
		
		MethodPOM1.WorkspaceFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 103)
	void CaseWorkspaceFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Workspace - Case - Multiple Filters verification");
		
		
		MethodPOM1.CaseWorkspaceFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =104)
	void WorkspaceTaskFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Workspace - Task - Multiple Filters verification");
		
		
		MethodPOM1.WorkspaceTaskFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 105)
	void WorkspaceCaseHearingFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Workspace = Case Hearing = Search box  Filter verification");
		
		
		CFOMethod.WorkspaceCaseHearingFilter(driver, test,"ABC Mall, Thane");
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 106)
	void AdvancedSearchworkspace() throws InterruptedException, IOException
	{
		test = extent.startTest("My Workspace(Advanced Search)verification");
	
		
		MethodPOM1.AdvancedSearchWorkspace(driver, test, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}*/
	@Test(priority = 106)
	void MyDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Download and View Document Verification");
		
		
		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 108)
	void ShareCaseDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Share Case Document Verification");
	
		
		MethodsPOM.ShareCaseDocument(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
@Test(priority = 109)
		void ShareNoticeDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("My Document-Share Notice Document Verification");
		
			MethodsPOM.ShareNoticeDocument(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
@Test(priority =110)
		void ShareTaskDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("My Document-Share Task Document Verification");
		
			
			MethodsPOM.ShareTaskDocument(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}

	@Test(priority =111)
	void DocumentNoticeFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document Tab - Notice - Multiple Filters verification");
		
		
		MethodPOM1.DocumentNoticeFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 112)
	void DocumentCaseFilter() throws InterruptedException, IOException
	{
	test = extent.startTest(" My Document = Case = Multiple  Filters verification");
	
	
	MethodPOM1.DocumentCaseFilter(driver, test);
	
	extent.endTest(test);
	extent.flush();
	}
	
	@Test(priority = 113)
	void DocumentTaskFilter() throws InterruptedException, IOException
	{
	test = extent.startTest(" My Document = Task = Multiple  Filters verification");
	
	
	MethodPOM1.DocumentTaskFilter(driver, test);
	
	extent.endTest(test);
	extent.flush();
	}
	@Test(priority = 114)
	void AdvancedSearchDoc() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document(Advanced search)Report Verification");
	
		
		MethodsPOM.AdvancedSearchDocument(driver, test, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 115)
	void AdvancedSearchShareCaseDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Advance search-Share Case Document Verification");
	
		
		MethodsPOM.AdvancedSearchShareCaseDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =116)
			void AdvancedSearchShareNoticeDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Advance search-Share Notice Document Verification");
			
				
				MethodsPOM.AdvancedSearchShareNoticeDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
@Test(priority =117)
			void AdvancedSearchShareTaskDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Advance search-Share Task Document Verification");
			
				
				MethodsPOM.AdvancedSearchShareTaskDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
@Test(priority = 118) 		//Sever is blocking and not allowing to upload the file.
			void CriticalDocuments() throws InterruptedException, IOException
			{
				test = extent.startTest(" Critical Document Verification");
				
				MethodsPOM.CriticalDocuments(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
			
	@Test(priority = 119) 		//Sever is blocking and not allowing to upload the file.
			void CriticalDocuments1() throws InterruptedException, IOException
			{
				test = extent.startTest(" Critical Document Verification");
				
				MethodsPOM.CriticalDocuments1(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority = 120)
    void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("Reports excel count verification");

		
		MethodsPOM.MyReports(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
 @Test(priority = 121)
	void MoreReports() throws InterruptedException, IOException
	{
		test = extent.startTest("More Report-Reports excel  verification");
		
		
		MethodsPOM.MoreReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
 
	 @Test(priority = 122)
		void ReportFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("My Report - Notice - Multiple Filters verification");
			
			MethodPOM1.ReportFilter(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 123)
	void ReportCaseFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Report - Case - Multiple Filters verification");
		
		MethodPOM1.ReportCaseFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority =124)
	void ReportTaskFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("My Report = Task =  Filters verification");
		
		
		MethodPOM1.ReportTaskFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	 @Test(priority = 125)
		void AdvancedSearch() throws InterruptedException, IOException
		{
			test = extent.startTest("Advanced Search Reports excel  verification");
			
			
			MethodPOM1.AdvancedSearchReport(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
	
@Test(priority = 126)
	void MyReminder() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder verification");
	
		
		MethodsPOM.MyReminder(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority= 127)
	void ReminderWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder Without data verification");
		
		MethodsPOM.ReminderWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 128)
	void ImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Import Utility verification");

		
		MethodsPOM.ImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 129)
	void ImportUtilityWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Upload Empty File Import Utility verification");
		
		
		MethodsPOM.ImportUtilityWithoutData(driver,test);
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority = 130)
	void ImportUtilityInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Enter Invalid data in Upload File Import Utility verification");
		
		
		MethodsPOM.ImportUtilityInvalidData(driver,test);
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority = 131)
	void ImportUtilityTwoManadtoryFileds() throws InterruptedException, IOException
	{
		test = extent.startTest("Enter Two Manadtory fields in Upload File Import Utility verification");
		
		
		MethodsPOM.ImportUtilityTwoManadtoryFileds(driver,test);
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 132)
	void CaseUpdationImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation Import Utility verification");
		
		
		MethodsPOM.CaseUpdationImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 133)
	void CaseUpdationUploadEmtyFile() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation -Empty File Import Utility verification");
		
		
		MethodsPOM.CaseUpdationUploadEmtyFile(driver,test);
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 134)
	void CaseUpdationUploadInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation -Invalid Data Import Utility verification");
		
		MethodsPOM.CaseUpdationUploadInvalidData(driver,test);
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 135)
	void CaseUpdationUploadInvalidFile() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation -Invalid File Import Utility verification");
		
		
		MethodsPOM.CaseUpdationUploadInvalidFile(driver,test);
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 136)
	void NoticeUpdation() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Updation Import Utility verification");
		
		
		MethodsPOM.NoticeUpdation(driver,test);
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 137)
void NoticeUpdationUploadEmtyFile() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation-Empty File Import Utility verification");
	
	
	MethodsPOM.NoticeUpdationUploadEmtyFile(driver,test);
	extent.endTest(test);
	extent.flush();
}
@Test(priority =138)
void NoticeUpdationUploadInvalidData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation -Invalid Data Import Utility verification");
	
	
	MethodsPOM.NoticeUpdationUploadInvalidData(driver,test);
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 139)
void NoticeUpdationUploadInvalidFile() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation -Invalid File Import Utility verification");
	
	
	MethodsPOM.NoticeUpdationUploadInvalidFile(driver,test);
	extent.endTest(test);
	extent.flush();
}

		
	
	
	


	
	
	
	
	
	//@Test(priority = 2)
		void AdvocateBill() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			
			
			MethodsPOM.AdvocateBillTab(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	//@Test(priority = 3)
		void AdvocateBill1() throws InterruptedException, IOException
		{
			test = extent.startTest("Approver Assignment Log verification");
			
			MethodsPOM.ApproverAssignmentLog(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
//@Test(priority = 114)
				void HearingCalender() throws InterruptedException, IOException, AWTException
				{
					test = extent.startTest("Case Hearing Calender Verification");
				
					
					MethodsPOM.HearingCalender(driver, test,"Performer","Company admin");
					
					extent.endTest(test);
					extent.flush();
				}
	//@Test(priority = 115)
		void CaseHearing() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Hearing Count Verification");
			
			
			MethodsPOM.CaseHearing(driver, test,"Performer","Company admin");
			
			extent.endTest(test);
			extent.flush();
		}
/*@Test(priority = 40)
	void CustomerMgmt() throws InterruptedException, IOException
	{
		test = extent.startTest("City-Customer Management verification");
	
		
		MethodPOM1.CustomerMgmt(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 41)
	void CustomerMgmtCustomer() throws InterruptedException, IOException
	{
		test = extent.startTest("Customer Mgmt Customer-Customer Management verification");

		
		MethodPOM1.CustomerMgmtCustomer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 42)
	void CustomerMgmtPlanVisit() throws InterruptedException, IOException
	{
		test = extent.startTest("Customer Mgmt Plan Visit-Customer Management verification");
	
		
		MethodPOM1.CustomerMgmtPalnVisit(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}	
//	@Test(priority = 43)
	void UpdateCommitmentsafterremarks() throws InterruptedException, IOException
	{
		test = extent.startTest("Update Commitments after remarks-Customer Management verification");

		
		MethodPOM1.UpdateCommitmentsafterremarks(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 44)
	void UpdateCommitmentsStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Update Commitments Status - Customer Management verification");
	
		
		MethodPOM1.UpdateCommitmentsStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 45)
	void Report() throws InterruptedException, IOException
	{
		test = extent.startTest("Report-Customer Management verification");
	
		
		MethodPOM1.Report(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}*/
	






		
		@AfterMethod
		
		void Close()
		{
		  driver.close();
		}
	
}
	
	




