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
			fis = new FileInputStream("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx");
			
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
			return sheet;
		}
		
		@BeforeTest
	
		void setBrowser() throws Exception
		{
			String workingDir = System.getProperty("user.dir");
			extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationCFO.html",true);
			//test = extent.startTest("Verify OpenBrowser");
			test = extent.startTest("Litigation Logging In - CFO");
			
			
//			XSSFSheet sheet = ReadExcel();
//			Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
//			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//			String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
//			
//			login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
			
			test.log(LogStatus.PASS, "Test Passed.");
			extent.endTest(test);
			extent.flush();
		}
		
		
		@BeforeMethod
	
		void Login() throws InterruptedException, IOException
		{
		
			//test = extent.startTest("Litigation Logging In - CFO");
			//test.log(LogStatus.INFO, "Logging into system");
			
			

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
			
			driver = login.Login.UserLogin(uname,password,"cfo");		//Method of Login class to login user.
			
//			test.log(LogStatus.PASS, "Test Passed.");
//			extent.endTest(test);
//			extent.flush();
		}
		

		
	// @Test(priority = 2)
			void DashBoardFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("All Filters verification");
				
				Thread.sleep(3000);
				CFOMethod.DashBoardFilter(driver, test, "Cfo-");
				
				extent.endTest(test);
				extent.flush();
			}
		

	//@Test(priority = 2)
		void CaseNoticeTypeGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice = Case Notice Type Graph Count Verification");
			
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"cfo -");
			
			extent.endTest(test);
			extent.flush();
		}
	//@Test(priority = 3)
			void CaseNoticeStageGraph() throws InterruptedException, IOException
			{
				test = extent.startTest("Notice = Case Notice Stage Graph Count Verification");
				
				
				Thread.sleep(3000);
				CFOMethod.CaseNoticeStageGraph(driver, test,"cfo -");
				
				extent.endTest(test);
				extent.flush();
			}
	//@Test(priority = 4)
		void RiskSummaryGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("select Notice Filter =(High Risk) Risk Graph Count Verification");

			Thread.sleep(3000);
			CFOMethod.RiskSummaryGraph(driver, test,"cfo -");
			
			extent.endTest(test);
			extent.flush();
		}
   //@Test(priority = 5)
        void DepartmentSummaryGraph() throws InterruptedException, IOException
        {
	       test = extent.startTest("Notice = Department Graph Count Verification");
	      
	       Thread.sleep(3000);
	       CFOMethod.DepartmentSummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
   //@Test(priority = 6)
        void LocationSummaryGraph() throws InterruptedException, IOException
        {
	       test = extent.startTest("Notice = Location Graph Count Verification");
	      
	       Thread.sleep(3000);
	       CFOMethod.LocationSummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
       
  //@Test(priority = 7)
        void CategorySummaryGraph() throws InterruptedException, IOException
        {
	       test = extent.startTest("Notice = Category Graph Count Verification");
	      
	       Thread.sleep(3000);
	       CFOMethod.CategorySummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }

 // @Test(priority = 8)
    void InwardDefendantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice =Less than a year = Inward/Defendant = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendantAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
   @Test(priority = 9)
    void ComplainantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = Less than a year = Complainant = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.ComplainantAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
   @Test(priority = 10)
    void ApplicantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = Less than a year = Applicant = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.ApplicantAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
    @Test(priority = 11)
    void OutwardPlaintiffAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = Less than a year = Outward/Plaintiff = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.OutwardPlaintiffAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
   @Test(priority = 12)
    void PetitionerAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = Less than a year  =Petitioner = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.PetitionerAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
    @Test(priority = 13)
    void RespondentAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = Less than a year  =Respondent = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.RespondentAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
   @Test(priority = 14)
    void ComplainantAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = 1 to 2 Years = Complainant = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.ComplainantAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
   @Test(priority = 15)
    void InwardDefendentAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = 1 to 2 Years = Inward/Defendent = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendentAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
 @Test(priority = 16)
    void OutwardPlaintiffAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = 1 to 2 Years =Outward/Plaintiff= Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.OutwardPlaintiffAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
    @Test(priority = 17)
    void RespondentAgeingGraph1to2yearsAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = 1 to 2 Years =Respondent= Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.RespondentAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
  // @Test(priority = 18)
    void InwardDefendentAgeingGraph2to3years() throws InterruptedException, IOException
    {
         test = extent.startTest("Notice = 2 to 3 Years =Inward/Defendent= Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendentAgeingGraph2to3years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
  
 
   @Test(priority =19)
	void CaseNoticeTypeGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest("Case = Case Notice Type Graph Count Verification");
		
		
		Thread.sleep(3000);
		CFOMethod.CaseNoticeTypeGraph1(driver, test,"cfo -");
		
		extent.endTest(test);
		extent.flush();
	}
   
   @Test(priority = 20)
   void CaseNoticeStageGraph1() throws InterruptedException, IOException
    {
 	test = extent.startTest("Case = Case Notice Stage Graph Count Verification");
 	
 	
 	Thread.sleep(3000);
 	CFOMethod.CaseNoticeStageGraph1(driver, test,"cfo -");
 	
 	extent.endTest(test);
 	extent.flush();
   }
    
    @Test(priority = 21)
	void RiskSummaryGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest("Case = Risk Graph Count Verification");

		Thread.sleep(3000);
		CFOMethod.RiskSummaryGraph1(driver, test,"cfo -");
		
		extent.endTest(test);
		extent.flush();
	}
    
   @Test(priority = 22)
    void DepartmentSummaryGraph1() throws InterruptedException, IOException
    {
       test = extent.startTest("Case = Department Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.DepartmentSummaryGraph1(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
    
    @Test(priority = 23)
    void LocationSummaryGraph1() throws InterruptedException, IOException
    {
       test = extent.startTest("Case = Location Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.LocationSummaryGraph1(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
    
    
  @Test(priority = 24)
    void CategorySummaryGraph1() throws InterruptedException, IOException
    {
       test = extent.startTest("Case = Category Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.CategorySummaryGraph1(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
    
    @Test(priority = 25)
    void InwardDefendantAgeingGraphCase() throws InterruptedException, IOException
    {
         test = extent.startTest("Case =Less than a year = Inward/Defendant = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendantAgeingGraphCase(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
 @Test(priority = 26)
    void OutwardPlaintiffAgeingGraphCase() throws InterruptedException, IOException
    {
         test = extent.startTest("Case = Less than a year = Outward/Plaintiff = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.OutwardPlaintiffAgeingGraphCase(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
   @Test(priority = 27)
   void PetitionerAgeingGraphCase() throws InterruptedException, IOException
    {
      test = extent.startTest("Case = Less than a year = Petitioner = Ageing Graph Count Verification");

       Thread.sleep(3000);
       CFOMethod.PetitionerAgeingGraphCase(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }

        
  

    // @Test(priority =28)
    	void NoticeOpen() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Open Count verification");
    		
    		
    		CFOMethod.NoticeOpen(driver, test, workbook, "CFO -");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    @Test(priority =29)
 	void NoticeWithExistingData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice With Existing Data verification");
 		
 		
 		CFOMethod.NoticeWithExistingData(driver, test, workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	
  @Test(priority =30)
     void NoticeWithInvalidData() throws InterruptedException, IOException
    {
	     test = extent.startTest("Notice With Invalid Data verification");
	
	
	      CFOMethod.NoticeWithInvalidData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
   }
   @Test(priority =31)
   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice With Two Mandatory Fields verification");
	
	
	      CFOMethod.NoticeWithTwoMandatoryData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
 @Test(priority =32) 
   void NoticeWithEmptyFields() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice With Empty Fields verification");
	
	
	      CFOMethod.NoticeWithEmptyFields(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }

 @Test(priority =33)
   void NoticeClearBtn() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice -Clear button verification");
	
	
	      CFOMethod.NoticeClearBtn(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
   @Test(priority =34)
   void NoticeSendMailWithDoc() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice -Send Mail With Document verification");
	
	
	      CFOMethod.NoticeSendMailWithDoc(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
   @Test(priority =35)
   void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice -Send Mail With Document Invalid Fields verification");
	
	
	      CFOMethod.NoticeSendMailWithDocInvalidFields(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
   @Test(priority =36)
   void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice -Send Mail With Document Empty Fields verification");
	
	
	      CFOMethod.NoticeSendMailWithDocEmptyFields(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
   @Test(priority =37)
   void NoticeUserAssignment() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice User Assignment  verification");
	
	
	      CFOMethod.NoticeUserAssignment(driver, test,workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
   @Test(priority =38)
   void NoticeUserAssignmentDelete() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice User Assignment Delete Icon  verification");
	
	
	      CFOMethod.NoticeUserAssignmentDelete(driver, test,workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
   
   @Test(priority =39)
	void LinkNotice() throws InterruptedException, IOException
	{
		test = extent.startTest("Link Notice Verification");
		
		
		CFOMethod.LinkDocument(driver, test, workbook, "Notice");
		
		extent.endTest(test);
		extent.flush();
	}
   
  @Test(priority =40)
   void LinkNoticeViewIcon() throws InterruptedException, IOException
  {
	     test = extent.startTest("Linked notice view icon  verification");
	
	
	      CFOMethod.LinkNoticeViewIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
   @Test(priority =41)
   void LinkNoticeDeleteIcon() throws InterruptedException, IOException
  {
	     test = extent.startTest("Linked notice Delete icon  verification");
	
	
	      CFOMethod.LinkNoticeDeleteIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
  
    @Test(priority =29)
     	void CaseOpen() throws InterruptedException, IOException
     	{
     		test = extent.startTest("Case - Open Count verification");
     		
     		
     		CFOMethod.CaseOpen(driver, test, workbook, "CFO -");
     		
     		extent.endTest(test);
     		extent.flush();
     	}
    @Test(priority =30)
 	void CaseExistingData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case with Existing Data verification");
 		
 		
 		CFOMethod.CaseExistingData(driver, test, workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
    @Test(priority =31)
 	void CaseWithInvalidData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case with Invalid Data verification");
 		
 		
 		CFOMethod.CaseWithInvalidData(driver, test, workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
    @Test(priority =32)
   	void CaseWithTwoFieldsData() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case with Two Manadatory fields verification");
   		
   		
   		CFOMethod.CaseWithTwoFieldsData(driver, test);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
    @Test(priority =33)
   	void CaseWithEmptyFields() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case with Empty fields verification");
   		
   		
   		CFOMethod.CaseWithEmptyFields(driver, test);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
    @Test(priority =34)
   	void CaseWithClearBtn() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case with Clear button verification");
   		
   		
   		CFOMethod.CaseWithClearBtn(driver, test);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
    @Test(priority =35)
    void CaseSendMailWithDoc() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case -Send Mail With Document verification");
 	
 	
 	      CFOMethod.CaseSendMailWithDoc(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
    
    @Test(priority =36)
    void CaseSendMailWithDocInvalidFields() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case -Send Mail With Document Invalid Fields verification");
 	
 	
 	      CFOMethod.CaseSendMailWithDocInvalidFields(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
    @Test(priority =37)
    void CaseSendMailWithDocEmptyFields() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case -Send Mail With Document Empty Fields verification");
 	
 	
 	      CFOMethod.CaseSendMailWithDocEmptyFields(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
    
    
    @Test(priority =38)
   	void CaseUserAssignment() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case User Assignment verification");
   		
   		
   		CFOMethod.CaseUserAssignment(driver, test,workbook);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
    @Test(priority =39)
    void CaseUserAssignmentDelete() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case User Assignment Delete Icon  verification");
 	
 	
 	      CFOMethod.CaseUserAssignmentDelete(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
    
    
    
         @Test(priority = 30)
    			void TaskOpen() throws InterruptedException, IOException
    			{
    				test = extent.startTest("Task - Open Count verification");
    				
    				
    				CFOMethod.TaskOpen(driver, test, workbook, "CFO");
    				
    				extent.endTest(test);
    				extent.flush();
    			}
      	
 @Test(priority = 31)
    	void NoticeClosed() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Closed Count verification");
    		
    		
    		CFOMethod.NoticeClosed(driver, test, workbook, "Company Admin");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
 @Test(priority = 32)
    	void CaseClose() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Closed Count verification");
    		
    		
    		CFOMethod.CaseClosed(driver, test, workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	
	@Test(priority = 34)
		void LinkCase() throws InterruptedException, IOException
		{
			test = extent.startTest("Link Case Verification");
		
			
			CFOMethod.LinkDocument(driver, test, workbook, "Case");
		
		extent.endTest(test);
			extent.flush();
		}
	
	  @Test(priority =35)
	   void LinkCaseViewIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case view icon  verification");
		
		
		      CFOMethod.LinkCaseViewIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	  @Test(priority =36)
	   void LinkCaseDeleteIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case delete icon  verification");
		
		
		      CFOMethod.LinkCaseDeleteIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	 	
	   
	@Test(priority = 35)
    	void CloseNotice() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Close Notice Count verification");
    		
    		CFOMethod.CloseNoticeCase(driver, test, workbook,"Notice");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority = 36)
			void CloseCase() throws InterruptedException, IOException
			{
			test = extent.startTest("Close Case Count Verification");
				
				
				CFOMethod.CloseNoticeCase(driver, test, workbook,"Case");
				
			extent.endTest(test);
				extent.flush();
			}
	  
	@Test(priority = 37)
			void TaskClosed() throws InterruptedException, IOException
			{
				test = extent.startTest("Task - Closed Count verification");
				
				
				CFOMethod.TaskClosed(driver, test, workbook, "CFO");
				
				extent.endTest(test);
				extent.flush();
			}

    	
	//@Test(priority = 38)
    	void NoticeDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Document verification");
    		
    		
    		CFOMethod.NoticeDocument(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority = 39)
	void NoticeDocumentEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document with empty fields verification");
		
		
		CFOMethod.NoticeDocumentEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 40)
	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Without Upload Document verification");
		
		
		CFOMethod.NoticeWithoutUploadDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 41)
	void NoticeDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Search Fields verification");
		
		
		CFOMethod.NoticeDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 42)
	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share with Invaid data verification");
		
		
		CFOMethod.NoticeDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 43)
	void NoticeDocumentShareWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share without data verification");
		
		
		CFOMethod.NoticeDocumentShareWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 44)
	void NoticeDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share close button verification");
		
		
		CFOMethod.NoticeDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
	
 @Test(priority = 45)
    	void NoticeTaskActivity() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice TaskActivtiy verification");
    		
    		
    		CFOMethod.TaskActivtity(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
 @Test(priority = 46)
	void TaskActivtityDeleteResponse() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Delete Response verification");
		
		
		CFOMethod.TaskActivtityDeleteResponse(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 @Test(priority = 47)
	void TaskActivtityExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy with existing data verification");
		
		
		CFOMethod.TaskActivtityExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
 @Test(priority = 48)
	void TaskActivtityWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Without data verification");
		
		
		CFOMethod.TaskActivtityWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 @Test(priority = 49)
	void TaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Response Without data verification");
		
		
		CFOMethod.TaskActivtityResponseWithoutStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 
 @Test(priority = 50)
	void TaskActivtityResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy  Response clear button verification");
		
		
		CFOMethod.TaskActivtityResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =51)
    	void NoticeResponse() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Response verification");
    		
    		
    		CFOMethod.Response(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	//@Test(priority =52)
	void ResponseExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Existing Data verification");
		
		
		CFOMethod.ResponseExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority =53)
	void NoticeResponseWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Without data verification");
		
		
		CFOMethod.ResponseWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =54)
	void ResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Clear button verification");
		
		
		CFOMethod.ResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
    	
	@Test(priority = 55)
    	void NoticePayment() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Payment verification");
    		
    		
    		CFOMethod.PaymentLog(driver,test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority = 56)
	void PaymentLogwithExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment with existing data verification");
		
		
		CFOMethod.PaymentLogwithExistingData(driver,test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 57)
	void NoticePaymentWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment Without data verification");
		
		
		CFOMethod.PaymentLogWithoutData(driver,test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
  	@Test(priority = 57)
    	void NoticeExternalLawyer() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Lawyer verification");
    		
    		CFOMethod.ExternalLawyer(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
  	@Test(priority = 59)
	void CriteriaInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Invalid Data verification");
		
		CFOMethod.CriteriaInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 60)
	void CriteriaWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Without Data verification");
		
		CFOMethod.CriteriaWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
  	
	@Test(priority = 61)
    	void NoticeAuditLog() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Audit Log verification");
    	
    		
    		CFOMethod.AuditLog(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}		
	@Test(priority =44)
    	void CaseDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Document Tab");
    		
    		
    		CFOMethod.Document(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority = 45)
		void CaseWithoutUploadDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Without Upload Document verification");
			
			
			CFOMethod.CaseWithoutUploadDocument(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 46)
	void CaseDocumentEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document with empty fields verification");
		
		
		CFOMethod.CaseDocumentEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 47)
	void CaseDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Search Fields verification");
		
		
		CFOMethod.CaseDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 48)
	void CaseDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share with Invaid data verification");
		
		
		CFOMethod.CaseDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =49)
	void CaseDocumentShareWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share without data verification");
		
		
		CFOMethod.CaseDocumentShareWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 50)
	void CaseDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share close button verification");
		
		
		CFOMethod.CaseDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
		@Test(priority =45)
    	void CaseTaskActivity() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Task/Activty Tab");
    		
    		
    		CFOMethod.TaskActivity1(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
		
		 @Test(priority = 46)
			void CaseTaskActivityWithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activtiy Without data verification");
				
				
				CFOMethod.CaseTaskActivityWithoutData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		@Test(priority =47)
    	void CaseTaskActivitywithExistingData() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Task/Activty with existing data");
    		
    		
    		CFOMethod.CaseTaskActivitywithExistingData(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
		
		 @Test(priority = 48)
			void CaseTaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activtiy Response Without data verification");
				
				
				CFOMethod.CaseTaskActivtityResponseWithoutStatus(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		 
		 @Test(priority = 49)
			void CaseTaskActivtityResponseClearBtn() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activtiy  Response clear button verification");
				
				
				CFOMethod.CaseTaskActivtityResponseClearBtn(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority =46)
    	void CaseHearingcfo() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - CaseHearing Tab");
    		
    		
    		CFOMethod.CaseHearing(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority =46)
	void CaseExistingHearingDate() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Existing Hearing Date Verification");
		
		
		CFOMethod.CaseExistingHearingDate(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
   @Test(priority= 47)
  void CaseWithoutHearingData() throws InterruptedException, IOException
  {
	test = extent.startTest("Case without hearing data Verification");
	
	
	CFOMethod.CaseHearingWithoutData(driver, test);
	
	extent.endTest(test);
	extent.flush();
  }
   @Test(priority =2)
   void CaseHearingInvalidDate() throws InterruptedException, IOException
   {
 	test = extent.startTest("Case Invalid Hearing Date Verification");
 	
 	
 	CFOMethod.CaseHearingInvalidDate(driver, test,workbook);
 	
 	extent.endTest(test);
 	extent.flush();
   }
   @Test(priority =2)
   void CaseHearingClearBtn() throws InterruptedException, IOException
   {
 	test = extent.startTest("Case heraing clear button Verification");
 	
 	
 	CFOMethod.CaseHearingClearBtn(driver, test,workbook);
 	
 	extent.endTest(test);
 	extent.flush();
   }
@Test(priority =47)
    	void CaseOrder() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Case Order Tab");
    	
    		
    		CFOMethod.CaseOrder(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
		@Test(priority =48)
    	void CaseStatusPayment() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Status/Payment Tab");
    		
    		
    		CFOMethod.StatusPayment(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
   @Test(priority =49)
    	void CaseExternalLawyerRating() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - External Lawyer Rating");
    		
    		
    		CFOMethod.CaseExternalLawyer(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority =50)
    	void CaseAuditLog() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Audit Log Tab");
    		
    		
    		CFOMethod.Auditlog(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    

 @Test(priority = 51)
			void MyDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Download and View Document");
			
				
				CFOMethod.MyDocument(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
		
	
		
	 @Test(priority = 52)
				void MyReports() throws InterruptedException, IOException
				{
					test = extent.startTest("Reports -excel count verification");
					
					CFOMethod.MyReports(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	        
		@Test(priority = 53)
				void MoreReports() throws InterruptedException, IOException
				{
					test = extent.startTest("More Report-Reports excel  verification");
					
					
					CFOMethod.MoreReport(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				
	@Test(priority =54)
				void MyReminder() throws InterruptedException, IOException
				{
					test = extent.startTest("My Reminder verification");
					
					CFOMethod.MyReminder(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
	 @Test(priority = 55)
				void ImportUtility() throws InterruptedException, IOException
				{
					test = extent.startTest("Import Utility verification");
					
					
					CFOMethod.ImportUtility(driver,test);
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 56)
			void AdvancedSearch() throws InterruptedException, IOException
			{
				test = extent.startTest("Workspace-Advanced Search Reports excel  verification");
				
				
				CFOMethod.AdvancedSearchWorkspace(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority = 57)
			void AdvancedSearchDoc() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document(Advanced search) -Download and View Document");
				
				
				CFOMethod.AdvancedSearchDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		@Test(priority = 58)
			void AdvancedSearchreport() throws InterruptedException, IOException
			{
				test = extent.startTest("Advanced SearchReports excel  verification");
			
				
				CFOMethod.AdvancedSearchReport(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
				
		@Test(priority = 59)
			void Masters() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Legal Entity  verification");
					
					CFOMethod.LegalEntity(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 60)
				void Masters1() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Law Firm verification");
					
					
					CFOMethod.LawFirm(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 61)
				void Masters2() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - 	User  verification");
					
					
					CFOMethod.User(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 62)
				void Masters3() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Opponent  verification");
					
					
					CFOMethod.Opponent(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
	@Test(priority = 63)
				void Masters4() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Court  verification");
					
					
					CFOMethod.Court(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 64)
				void Masters5() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Case/NoticeType  verification");
					
					
					CFOMethod.CaseNoticeType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
@Test(priority = 65)
				void Masters6() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Payment Type  verification");
					
					
					CFOMethod.PaymentType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 66)
				void Masters7() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Custom Parameter  verification");
				
					
					CFOMethod.customParameter(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 67)
				void Masters8() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Case Stage  verification");
				
					
					CFOMethod.CaseStage(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority = 68)
				void Masters9() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Document Type  verification");
					
					
					CFOMethod.DocumentType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority = 69)
				void Masters10() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Rating Criteria  verification");
				
					
					CFOMethod.RatingCriteria(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
		@Test(priority = 70)
				void Masters12() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Notice Stage  verification");
					
					
					CFOMethod.NoticeStage(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority = 71)
				void Masters11() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - UserReassignment  verification");
					
					
					CFOMethod.UserReassignment(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 72)
				void Masters13() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Mail Authorization  verification");
					
					CFOMethod.MailAuthorization(driver,test);
					
					extent.endTest(test);
					extent.flush();
				}
				
	@Test(priority = 73)
				void HearingCalender() throws InterruptedException, IOException
				{
					test = extent.startTest("Hearing Calender verification");
				
					
					CFOMethod.HearingCalender(driver, test,"Performer","Cfo");
					
					extent.endTest(test);
					extent.flush();
				}
				
				
				
		//@Test(priority = 74)
				void Draft() throws InterruptedException, IOException
				{
					test = extent.startTest("Draft Count verification");
					//test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.Draft(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				
		@Test(priority = 75)
				void CaseHearing() throws InterruptedException, IOException
				{
					test = extent.startTest("Case Hearing Count Verification");
					//test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.CaseHearing(driver, test,"Performer","Case Hearing-");
					
					extent.endTest(test);
					extent.flush();
				}
	    	@Test(priority = 76)
		    	void NoticeDocViewandDownload() throws InterruptedException, IOException
		    	{
		    		test = extent.startTest("Notice Document verification");
		    		//test.log(LogStatus.INFO, "Test Initiated");
		    		
		    		CFOMethod.NoticeDocViewandDownload(driver, test);
		    		
		    		extent.endTest(test);
		    		extent.flush();
		    	}
				
			
			
				
				
//		      @Test(priority = 63)
				void WorkspaceFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("Workspace-All Filters verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.WorkspaceFilter(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
			
//				@Test(priority = 64)
				void DocumentFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("My Document- All Filters verification");
					test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.DocumentFilter(driver, test, "cfo");
					
					extent.endTest(test);
					extent.flush();
				}
				
//			 @Test(priority = 65)
					void ReportFilter() throws InterruptedException, IOException
					{
						test = extent.startTest("My Report - All Filters verification");
						test.log(LogStatus.INFO, "Test Initiated");
						
						CFOMethod.ReportFilter(driver, test);
						
						extent.endTest(test);
						extent.flush();
					}
		
		 
		

					 @AfterMethod
					 
					 void Close()
					 {
						 driver.close(); 
					 }

		

		


	
//			@AfterTest()	
//			
//			void chromeclose() throws InterruptedException
//			{
//				Thread.sleep(5000);
//			  driver.close();
//			}

}
