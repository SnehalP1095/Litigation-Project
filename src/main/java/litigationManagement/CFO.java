package litigationManagement;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.MethodsPOM;
import litigationAdditionalOwner.performerPOM;
import performer.OverduePOM;


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
			test = extent.startTest("Litigation Logging In - CFO");
			
			
			test.log(LogStatus.PASS, "Test Passed = Verify Open Chrome Browser");
			extent.endTest(test);
			extent.flush();
		}
		
		
		@BeforeMethod
	
		void Login() throws Exception
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
			
			driver = login.Login.UserLogin(uname,password,"cfo");		//Method of Login class to login user.
			

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
			
			
//@Test(priority = 1)
			void HearingCalender() throws InterruptedException, IOException, AWTException
			{
				test = extent.startTest("Hearing Calender verification");
			
				
				CFOMethod.HearingCalender(driver, test,"Performer","Cfo");
				
				extent.endTest(test);
				extent.flush();
			}
			
		

//@Test(priority = 2)
		void CaseNoticeTypeGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("Select Notice Filter  = Case Notice Type Graph Count Verification");
			
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(3000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
			 Thread.sleep(3000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 
			 js.executeScript("window.scrollBy(0,100)");
			 
			 Thread.sleep(4000);
	    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseNoticeTypeOutwardPlaintiff(driver).getText());	//Reading Notice Open count.
	    	int	InwardDefendent = Integer.parseInt(performerPOM.CaseNoticeTypeInwardDefendent(driver).getText());	//Reading Notice Open count.
	    	int	Complinant = Integer.parseInt(performerPOM.CaseNoticeTypeComplinant(driver).getText());	//Reading Notice Open count.
	    	int	Respondent = Integer.parseInt(performerPOM.CaseNoticeTypeRespondent(driver).getText());	//Reading Notice Open count.
	    	int	Applicant = Integer.parseInt(performerPOM.CaseNoticeTypeApplicant(driver).getText());	//Reading Notice Open count.
	    	int	Petitioner = Integer.parseInt(performerPOM.CaseNoticeTypePetitioner(driver).getText());	//Reading Notice Open count.
			
	    	Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"Outward/Plaintiff Type",OutwardPlaintiff);
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"Inward/Defendent Type",InwardDefendent);
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"Complinant Type",Complinant);
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"Respondent Type",Respondent);
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"Applicant Type",Applicant);
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeGraph(driver, test,"Petitioner Type",Petitioner);
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
			
			
			extent.endTest(test);
			extent.flush();
		}
@Test(priority = 3)
		void CaseNoticeTypeFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Notice type summary graph Filter Verification");
			
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeFilter(driver, test,"CaseNoticeTypeSummaryGraph");
			extent.endTest(test);
			extent.flush();
		}
		
//@Test(priority = 4)
			void CaseNoticeStageGraph() throws InterruptedException, IOException
			{
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,900)");
				
				Thread.sleep(5000);
				performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
				Thread.sleep(5000);
				performerPOM.clickDashboardNoticeFilter(driver).click();
		  
		   	
				Thread.sleep(5000);
				performerPOM.clickDashboardApplyBtn(driver).click();
				
				String StageName =performerPOM.StageName(driver).getText();
				test = extent.startTest("Select Notice Filter = "+StageName+" Stage = Case Notice Stage Graph Count Verification");
				
				CFOMethod.CaseNoticeStageGraph(driver, test,"cfo -");
				
				extent.endTest(test);
				extent.flush();
			}
			
@Test(priority = 5)
			void CaseNoticeStageFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Notice stage summary graph Filter Verification");
				
				
				Thread.sleep(3000);
				CFOMethod.CaseNoticeTypeFilter(driver, test,"CaseNoticeStageHearingGraph");
				
				extent.endTest(test);
				extent.flush();
			}
	        

//@Test(priority =4)
		void RiskSummaryGraph() throws InterruptedException, IOException
		{
			test = extent.startTest("Select Notice Filter = Risk Graph Count Verification");
			
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,950)");
			

         	Thread.sleep(2000);
		    int	HighRisk = Integer.parseInt(performerPOM.RiskSummaryHigh(driver).getText());	//Reading Notice Open count.
	    	int	MediumRisk = Integer.parseInt(performerPOM.RiskSummaryMedium(driver).getText());	//Reading Notice Open count.
	    	int	LowRisk = Integer.parseInt(performerPOM.RiskSummaryLow(driver).getText());	//Reading Notice Open count.
	    	int	NotApplicableRisk = Integer.parseInt(performerPOM.RiskSummaryNotApplicable(driver).getText());	//Reading Notice Open count.
	    	
			
	    	Thread.sleep(3000);
			CFOMethod.RiskSummaryGraph(driver, test,"High Risk",HighRisk);
			Thread.sleep(3000);
			CFOMethod.RiskSummaryGraph(driver, test,"Medium Risk",MediumRisk);
			Thread.sleep(3000);
			CFOMethod.RiskSummaryGraph(driver, test,"Low Risk",LowRisk);
			Thread.sleep(3000);
			CFOMethod.RiskSummaryGraph(driver, test,"Not Applicable Risk",NotApplicableRisk);
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
		
			extent.endTest(test);
			extent.flush();
		}
@Test(priority =5)
		void RiskSummaryFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("Risk summary graph Filter Verification");
			
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeFilter(driver, test,"RiskSummaryHigh");
			
			extent.endTest(test);
			extent.flush();
		}
//@Test(priority = 6)
        void DepartmentSummaryGraph() throws InterruptedException, IOException
        {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("window.scrollBy(0,800)");
        
    		Thread.sleep(5000);
    		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
    	
    		Thread.sleep(5000);
    		performerPOM.clickDashboardNoticeFilter(driver).click();
      
       	
    		Thread.sleep(5000);
    		performerPOM.clickDashboardApplyBtn(driver).click();
     	
    		 js.executeScript("window.scrollBy(0,950)");
    	
    		 String DeptName =performerPOM.DepartName(driver).getText();
    		 test = extent.startTest("Select Notice Filter ="+DeptName+" Department - Department Summary Graph Count Verification");
    		
	      
	       Thread.sleep(3000);
	       CFOMethod.DepartmentSummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
@Test(priority = 7)
		void DeptSummaryFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("Department summary graph Filter Verification");
			
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeFilter(driver, test,"DepartmentSummaryGraph");
			
			extent.endTest(test);
			extent.flush();
		}
//@Test(priority = 8)
        void LocationSummaryGraph() throws InterruptedException, IOException
        {
        	
        	
        	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,800)");
			
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
		   Thread.sleep(5000);
		   performerPOM.clickDashboardApplyBtn(driver).click();
		   
		   js.executeScript("window.scrollBy(0,1300)");
        	
        	  String LocationName =performerPOM.LocationName(driver).getText();
      		test = extent.startTest("Select Notice Filter = "+LocationName+" Location- Location Summary Graph Count Verification");
      		
	      
	       Thread.sleep(3000);
	       CFOMethod.LocationSummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
@Test(priority =8)
		void LocationSummaryFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("Location summary graph Filter Verification");
			
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeFilter(driver, test,"LocationSummaryGraph");
			
			extent.endTest(test);
			extent.flush();
		}
       
//@Test(priority = 10)
        void CategorySummaryGraph() throws InterruptedException, IOException
        {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,800)");
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
			Thread.sleep(5000);
			performerPOM.clickDashboardApplyBtn(driver).click();
			
		   	js.executeScript("window.scrollBy(0,1700)");
        	
        	
        	
        	Thread.sleep(2000);
      		String CategoryName =performerPOM.CategoryName(driver).getText();
        	test = extent.startTest("Select Notice Filter ="+CategoryName+" Category - Category Summary Graph Count Verification");
	      
	       Thread.sleep(3000);
	       CFOMethod.CategorySummaryGraph(driver, test,"cfo -");
	
	       extent.endTest(test);
	       extent.flush();
        }
        
    
@Test(priority =9)
		void CategorySummaryFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("Category summary graph Filter Verification");
			
			
			Thread.sleep(3000);
			CFOMethod.CaseNoticeTypeFilter(driver, test,"CategorySummaryGraph");
			
			extent.endTest(test);
			extent.flush();
		}
       

//@Test(priority = 12)
    void InwardDefendantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter =Less than a year = Inward/Defendant Type = Ageing Graph Count Verification");
         

          Thread.sleep(3000);
          CFOMethod.InwardDefendantAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
    
 @Test(priority = 10)
   void AgeingGraphFilter() throws InterruptedException, IOException
	{
	 test = extent.startTest("Less than a year  = Ageing Graph filter Verification");
     
     JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,800)");
     	
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3700)");
		
		
	 	Thread.sleep(3000);
	 	CFOMethod.AgeingGraphFilter(driver, test,"Inward/Defendent");
	 	Thread.sleep(3000);
	 	CFOMethod.AgeingGraphFilter(driver, test,"Complainant");
		Thread.sleep(3000);
	 	CFOMethod.AgeingGraphFilter(driver, test,"Applicant");
		Thread.sleep(3000);
		CFOMethod.AgeingGraphFilter(driver, test,"Outward/Plaintiff");
		Thread.sleep(3000);
		CFOMethod.AgeingGraphFilter(driver, test,"Petitioner");
		Thread.sleep(3000);
		CFOMethod.AgeingGraphFilter(driver, test,"Respondent");
		
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
		extent.endTest(test);
		extent.flush();

	}
    
//@Test(priority = 14)
    void ComplainantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = Less than a year = Complainant Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.ComplainantAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority = 0)
    void ApplicantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = Less than a year = Applicant Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.ApplicantAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
    
//   @Test(priority = 16)
    void OutwardPlaintiffAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = Less than a year = Outward/Plaintiff Type= Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.OutwardPlaintiffAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority = 17)
    void PetitionerAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = Less than a year  =Petitioner Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.PetitionerAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority = 18)
    void RespondentAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = Less than a year  =Respondent Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.RespondentAgeingGraph(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority =19)
    void ComplainantAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = 1 to 2 Years = Complainant Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.ComplainantAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority = 20)
    void InwardDefendentAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = 1 to 2 Years = Inward/Defendent Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendentAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
 // @Test(priority = 21)
    void OutwardPlaintiffAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = 1 to 2 Years =Outward/Plaintiff Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.OutwardPlaintiffAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
// @Test(priority =22)
    void RespondentAgeingGraph1to2yearsAgeingGraph1to2years() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = 1 to 2 Years =Respondent Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.RespondentAgeingGraph1to2years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
@Test(priority = 23)
    void AgeingGraph1TO2YearrFilter() throws InterruptedException, IOException
 	{
 	   test = extent.startTest(" 1 to 2 year Ageing graph = Multiple filter Verification");
       JavascriptExecutor js = (JavascriptExecutor) driver;
      	js.executeScript("window.scrollBy(0,800)");
      	
 		 Thread.sleep(3000);
 		js.executeScript("window.scrollBy(0,3700)");
 		
 		
 	 	Thread.sleep(3000);
 	 	CFOMethod.AgeingGraph1to2Filter(driver, test,"Complianant");
 	 	Thread.sleep(3000);
 	 	CFOMethod.AgeingGraph1to2Filter(driver, test,"Inward/Defendent");
 		Thread.sleep(3000);
 	 	CFOMethod.AgeingGraph1to2Filter(driver, test,"Outward/Plaintiff");
 		Thread.sleep(3000);
 		CFOMethod.AgeingGraph1to2Filter(driver, test,"Respondent");
 		
 		
 		
 		Thread.sleep(3000);
 		OverduePOM.clickDashboard(driver).click();
     
 		extent.endTest(test);
 		extent.flush();

 	}
     
//@Test(priority = 24)
    void InwardDefendentAgeingGraph2to3years() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter = 2 to 3 Years =Inward/Defendent Type = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendentAgeingGraph2to3years(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }

//@Test(priority =25)
	void TwoToThreeYearAgeingGraphFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("2 to 3 Year Ageing graph Filter Verification");
		
		
		Thread.sleep(3000);
		CFOMethod.CaseNoticeTypeFilter(driver, test,"2to3YearAgeingGraph");
		
		extent.endTest(test);
		extent.flush();
	}
  
 
//@Test(priority =27)
	void CaseNoticeTypeGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter  = Case Notice Type Graph Count Verification");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,830)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
      
       	
		 Thread.sleep(3000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseNoticeTypeInwardDefendent(driver).getText());	//Reading Notice Open count.
    	int	InwardDefendent = Integer.parseInt(performerPOM.CaseNoticeTypeApplicant(driver).getText());	//Reading Notice Open count.
    	int	Complinant = Integer.parseInt(performerPOM.CaseNoticeTypeRespondent1(driver).getText());	//Reading Notice Open count.
    	int	Respondent = Integer.parseInt(performerPOM.CaseNoticeTypeOutwardPlaintiff(driver).getText());	//Reading Notice Open count.
    	
		
    	Thread.sleep(3000);
		CFOMethod.CaseNoticeTypeGraph1(driver, test,"Inward/Defendent Type",OutwardPlaintiff);
		Thread.sleep(3000);
		CFOMethod.CaseNoticeTypeGraph1(driver, test,"Outward/Plaintiff Type",InwardDefendent);
		Thread.sleep(3000);
		CFOMethod.CaseNoticeTypeGraph1(driver, test,"Respondent Type",Complinant);
		Thread.sleep(3000);
		CFOMethod.CaseNoticeTypeGraph1(driver, test,"Petitioner Type",Respondent);
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		
		
		extent.endTest(test);
		extent.flush();
	}
   
//@Test(priority = 28)
   void CaseNoticeStageGraph1() throws InterruptedException, IOException
   { 
	   JavascriptExecutor js = (JavascriptExecutor) driver;
	   js.executeScript("window.scrollBy(0,800)");
  	
	   Thread.sleep(5000);
	   performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
	   Thread.sleep(3000);
	   performerPOM.clickDashboardCaseFilter(driver).click();
	
	   Thread.sleep(3000);
	   performerPOM.clickDashboardApplyBtn(driver).click();
	
	   js.executeScript("window.scrollBy(0,500)");
	
	   Thread.sleep(3000);
	   String StageName =performerPOM.StageName(driver).getText();
		test = extent.startTest("Select Case Filter = "+StageName+" Stage = Case Notice Stage Graph Count Verification");
 	
		Thread.sleep(3000);
		CFOMethod.CaseNoticeStageGraph1(driver, test,"cfo -");
 	
		extent.endTest(test);
		extent.flush();
   }
    
//@Test(priority = 29)
	void RiskSummaryGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter = Risk Graph Count Verification");
        
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
      
       	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,950)");
		
	    int	HighRisk = Integer.parseInt(performerPOM.RiskSummaryHigh(driver).getText());	//Reading Notice Open count.
    	int	MediumRisk = Integer.parseInt(performerPOM.RiskSummaryMedium(driver).getText());	//Reading Notice Open count.
    	int	LowRisk = Integer.parseInt(performerPOM.RiskSummaryLow(driver).getText());	//Reading Notice Open count.
    	//int	NotApplicableRisk = Integer.parseInt(performerPOM.RiskSummaryNotApplicable(driver).getText());	//Reading Notice Open count.
    	
		
    	Thread.sleep(3000);
		CFOMethod.RiskSummaryGraph1(driver, test,"High Risk",HighRisk);
		Thread.sleep(3000);
		CFOMethod.RiskSummaryGraph1(driver, test,"Medium Risk",MediumRisk);
		Thread.sleep(3000);
		CFOMethod.RiskSummaryGraph1(driver, test,"Low Risk",LowRisk);
		//Thread.sleep(3000);
		//CFOMethod.RiskSummaryGraph1(driver, test,"Not Applicable Risk",NotApplicableRisk);
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		
		extent.endTest(test);
		extent.flush();
	}
    
//@Test(priority = 30)
    void DepartmentSummaryGraph1() throws InterruptedException, IOException
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
    
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
  
   	
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();
 	
		 js.executeScript("window.scrollBy(0,950)");
	
		 String DeptName =performerPOM.DepartName(driver).getText();
		 test = extent.startTest("Select Case Filter ="+DeptName+" Department - Department Summary Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.DepartmentSummaryGraph1(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
    
//@Test(priority = 31)
    void LocationSummaryGraph1() throws InterruptedException, IOException
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
      
       	
	   Thread.sleep(5000);
	   performerPOM.clickDashboardApplyBtn(driver).click();
	   
	   js.executeScript("window.scrollBy(0,1300)");
    	
    	  String LocationName =performerPOM.LocationName(driver).getText();
  		test = extent.startTest("Select Case Filter = "+LocationName+" Location- Location Summary Graph Count Verification");
  		
      
       Thread.sleep(3000);
       CFOMethod.LocationSummaryGraph1(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
    
    
//@Test(priority = 32)
    void CategorySummaryGraph1() throws InterruptedException, IOException
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
      
       	
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();
		
	   	js.executeScript("window.scrollBy(0,1700)");
    	
    	
    	
    	Thread.sleep(2000);
  		String CategoryName =performerPOM.CategoryName(driver).getText();
    	test = extent.startTest("Select Case Filter ="+CategoryName+" Category - Category Summary Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.CategorySummaryGraph1(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
//@Test(priority =33)
    void ExpensesCaseGraph() throws InterruptedException, IOException
    {
       test = extent.startTest("Select Case Filter = Expenses Case-Wise Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.ExpensesCaseGraph(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
//@Test(priority =34)
    void ExpensesCategoryWiseCaseGraph() throws InterruptedException, IOException
    {
       test = extent.startTest("Select Case Filter = Cables Category -Expenses Category Wise Graph Count Verification");
      
       Thread.sleep(3000);
       CFOMethod.ExpensesCategoryWiseCaseGraph(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }
 //@Test(priority =35)
 void ExpensesCounselWiseCaseGraph() throws InterruptedException, IOException
 {
    test = extent.startTest("Select Case Filter -Expenses Counsel Wise Graph Count Verification");
   
    Thread.sleep(3000);
    CFOMethod.ExpensesCounselWiseCaseGraph(driver, test,"cfo -");

    extent.endTest(test);
    extent.flush();
 }
// @Test(priority =36)
 void UtilizedBudgetGraph() throws InterruptedException, IOException
 {
    test = extent.startTest("Select Case Filter -Utilized budget Graph Count Verification");
   
    Thread.sleep(3000);
    CFOMethod.UtilizedBudgetGraph(driver, test,"cfo -");

    extent.endTest(test);
    extent.flush();
 }
// @Test(priority = 37)
    void InwardDefendantAgeingGraphCase() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Case Filter =Less than a year = Inward/Defendant = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.InwardDefendantAgeingGraphCase(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority =38)
    void OutwardPlaintiffAgeingGraphCase() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Case Filter = Less than a year = Outward/Plaintiff = Ageing Graph Count Verification");
  
          Thread.sleep(3000);
          CFOMethod.OutwardPlaintiffAgeingGraphCase(driver, test,"cfo -");

          extent.endTest(test);
          extent.flush();
    }
//@Test(priority =39)
   void PetitionerAgeingGraphCase() throws InterruptedException, IOException
    {
      test = extent.startTest("Select Case Filter = Less than a year = Petitioner = Ageing Graph Count Verification");

       Thread.sleep(3000);
       CFOMethod.PetitionerAgeingGraphCase(driver, test,"cfo -");

       extent.endTest(test);
       extent.flush();
    }

        
  

 //  @Test(priority =32)
    	void NoticeOpen() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Open Count verification");
    		
    		
    		CFOMethod.NoticeOpen(driver, test, workbook, "CFO -");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
 @Test(priority =33)
 	void NoticeWithExistingData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice With Existing Data verification");
 		
 		
 		CFOMethod.NoticeWithExistingData(driver, test, workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	
 @Test(priority =34)
     void NoticeWithInvalidData() throws InterruptedException, IOException
    {
	     test = extent.startTest("Notice With Invalid Data verification");
	
	
	      CFOMethod.NoticeWithInvalidData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
   }
 @Test(priority =35)
   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice With Two Mandatory Fields verification");
	
	
	      CFOMethod.NoticeWithTwoMandatoryData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
 @Test(priority =36) 
   void NoticeWithEmptyFields() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice With Empty Fields verification");
	
	
	      CFOMethod.NoticeWithEmptyFields(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }

@Test(priority =37)
   void NoticeClearBtn() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice Summary -Clear button verification");
	
	
	      CFOMethod.NoticeClearBtn(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
//@Test(priority =38)
   void NoticeSendMailWithDoc() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice Summary-Send Mail With Document verification");
	
	
	      CFOMethod.NoticeSendMailWithDoc(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
@Test(priority =39)
   void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
  {
	     test = extent.startTest(" Notice Summary -Send Mail With Document Invalid Fields verification");
	
	
	      CFOMethod.NoticeSendMailWithDocInvalidFields(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
@Test(priority =40)
   void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice Summary -Send Mail With Document Empty Fields verification");
	
	
	      CFOMethod.NoticeSendMailWithDocEmptyFields(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
 @Test(priority =41)
   void NoticeUserAssignment() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice User Assignment  verification");
	
	
	      CFOMethod.NoticeUserAssignment(driver, test,workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
 @Test(priority =42)
   void NoticeUserAssignmentDelete() throws InterruptedException, IOException
  {
	     test = extent.startTest("Notice User Assignment Delete Icon  verification");
	
	
	      CFOMethod.NoticeUserAssignmentDelete(driver, test,workbook);
	
	     extent.endTest(test);
	     extent.flush();
 }
   
@Test(priority =43)
	void LinkNotice() throws InterruptedException, IOException
	{
		test = extent.startTest("Link Notice Verification");
		
		
		CFOMethod.LinkDocument(driver, test, workbook, "Notice");
		
		extent.endTest(test);
		extent.flush();
	}
   
 @Test(priority =44)
   void LinkNoticeViewIcon() throws InterruptedException, IOException
  {
	     test = extent.startTest("Linked notice view icon  verification");
	
	
	      CFOMethod.LinkNoticeViewIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
@Test(priority =45)
   void LinkNoticeDeleteIcon() throws InterruptedException, IOException
  {
	     test = extent.startTest("Linked notice Delete icon  verification");
	
	
	      CFOMethod.LinkNoticeDeleteIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 }
  
//@Test(priority =46)
     	void CaseOpen() throws InterruptedException, IOException
     	{
     		test = extent.startTest("Case - Open Count verification");
     		
     		
     		CFOMethod.CaseOpen(driver, test, workbook, "CFO -");
     		
     		extent.endTest(test);
     		extent.flush();
     	}
 @Test(priority =47)
 	void CaseExistingData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case with Existing Data verification");
 		
 		
 		CFOMethod.CaseExistingData(driver, test, workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
@Test(priority =48)
 	void CaseWithInvalidData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case with Invalid Data verification");
 		
 		
 		CFOMethod.CaseWithInvalidData(driver, test, workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
@Test(priority =49)
   	void CaseWithTwoFieldsData() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case with Two Manadatory fields verification");
   		
   		
   		CFOMethod.CaseWithTwoFieldsData(driver, test);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
 @Test(priority =50)
   	void CaseWithEmptyFields() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case with Empty fields verification");
   		
   		
   		CFOMethod.CaseWithEmptyFields(driver, test);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
@Test(priority =51)
   	void CaseWithClearBtn() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case Summary - Clear button verification");
   		
   		
   		CFOMethod.CaseWithClearBtn(driver, test);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
  
 @Test(priority =52)
   	void CaseUserAssignment() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Case User Assignment verification");
   		
   		
   		CFOMethod.CaseUserAssignment(driver, test,workbook);
   		
   		extent.endTest(test);
   		extent.flush();
   	}
@Test(priority =53)
    void CaseUserAssignmentDelete() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case User Assignment Delete Icon  verification");
 	
 	
 	      CFOMethod.CaseUserAssignmentDelete(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
//@Test(priority =54)
    			void TaskOpen() throws InterruptedException, IOException
    			{
    				test = extent.startTest("Task - Open Count verification");
    				
    				
    				CFOMethod.TaskOpen(driver, test, workbook, "CFO");
    				
    				extent.endTest(test);
    				extent.flush();
    			}
 @Test(priority = 55)
			void TaskwithExistingData() throws InterruptedException, IOException
			{
				test = extent.startTest("Task With existing data verification");
				
				
				CFOMethod.TaskwithExistingData(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			
			}
 @Test(priority =56)
    			void TaskwithTwoManadatoryFields() throws InterruptedException, IOException
    			{
    				test = extent.startTest("Task With Two manadatory fields verification");
    				
    				
    				CFOMethod.TaskwithTwoManadatoryFields(driver, test, workbook);
    				
    				extent.endTest(test);
    				extent.flush();
    			}
  @Test(priority = 57)
			void TaskwithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Task Without  data verification");
				
				
				CFOMethod.TaskwithoutData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
   @Test(priority =58)
			void TaskwithClearBtn() throws InterruptedException, IOException
			{
				test = extent.startTest("Task Clear button verification");
				
				
				CFOMethod.TaskwithClearBtn(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
  //@Test(priority = 59)
			void TaskDelete() throws InterruptedException, IOException
			{
				test = extent.startTest("Task Delete verification");
				
				
				CFOMethod.TaskDelete(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
   	
// @Test(priority = 60)
    	void NoticeClosed() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Closed Count verification");
    		
    		
    		CFOMethod.NoticeClosed(driver, test, workbook, "Company Admin");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
//@Test(priority = 61)
    	void CaseClose() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Closed Count verification");
    		
    		
    		CFOMethod.CaseClosed(driver, test, workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	
@Test(priority = 62)
		void LinkCase() throws InterruptedException, IOException
		{
			test = extent.startTest("Link Case Verification");
		
			
			CFOMethod.LinkDocument(driver, test, workbook, "Case");
		
		extent.endTest(test);
			extent.flush();
		}
	
 @Test(priority =63)
	   void LinkCaseViewIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case view icon  verification");
		
		
		      CFOMethod.LinkCaseViewIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
 @Test(priority =64)
	   void LinkCaseDeleteIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case delete icon  verification");
		
		
		      CFOMethod.LinkCaseDeleteIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	 	
	   
//@Test(priority = 65)
    	void CloseNotice() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Close Notice Count verification");
    		
    		CFOMethod.CloseNoticeCase(driver, test, workbook,"Notice");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
//@Test(priority = 66)
			void CloseCase() throws InterruptedException, IOException
			{
			test = extent.startTest("Close Case Count Verification");
				
				
				CFOMethod.CloseNoticeCase(driver, test, workbook,"Case");
				
			extent.endTest(test);
				extent.flush();
			}
	  
//@Test(priority = 67)
			void TaskClosed() throws InterruptedException, IOException
			{
				test = extent.startTest("Task - Closed Count verification");
				
				
				CFOMethod.TaskClosed(driver, test, workbook, "CFO");
				
				extent.endTest(test);
				extent.flush();
			}
//	@Test(priority = 68)
    	void NoticeDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Document verification");
    		
    		
    		CFOMethod.NoticeDocument(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority = 69)
	void NoticeDocumentEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document with empty fields verification");
		
		
		CFOMethod.NoticeDocumentEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 70)
	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Without Upload Document verification");
		
		
		CFOMethod.NoticeWithoutUploadDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 71)
	void NoticeDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Search Fields verification");
		
		
		CFOMethod.NoticeDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 72)
	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share with Invaid data verification");
		
		
		CFOMethod.NoticeDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 73)
	void NoticeDocumentShareWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share without data verification");
		
		
		CFOMethod.NoticeDocumentShareWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 74)
	void NoticeDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share close button verification");
		
		
		CFOMethod.NoticeDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
	
//@Test(priority = 75)
    	void NoticeTaskActivity() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice TaskActivtiy verification");
    		
    		
    		CFOMethod.TaskActivtity(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
//@Test(priority = 76)
	void TaskActivtityDeleteResponse() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Delete Response verification");
		
		
		CFOMethod.TaskActivtityDeleteResponse(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 77)
	void TaskActivtityExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy with existing data verification");
		
		
		CFOMethod.TaskActivtityExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =78)
	void TaskActivtityWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Without data verification");
		
		
		CFOMethod.TaskActivtityWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 79)
	void TaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Response Without data verification");
		
		
		CFOMethod.TaskActivtityResponseWithoutStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 
@Test(priority = 80)
	void TaskActivtityResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy  Response clear button verification");
		
		
		CFOMethod.TaskActivtityResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority =81)
    	void NoticeResponse() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Response verification");
    		
    		
    		CFOMethod.Response(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority =82)
	void ResponseExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Existing Data verification");
		
		
		CFOMethod.ResponseExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =83)
	void NoticeResponseWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Without data verification");
		
		
		CFOMethod.ResponseWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =84)
	void ResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Clear button verification");
		
		
		CFOMethod.ResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
    	
//	@Test(priority = 85)
    	void NoticePayment() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Payment verification");
    		
    		
    		CFOMethod.PaymentLog(driver,test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority = 86)
	void PaymentLogwithExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment with existing data verification");
		
		
		CFOMethod.PaymentLogwithExistingData(driver,test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 87)
	void PaymentLogwithInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment with Invalid data verification");
		
		
		CFOMethod.PaymentLogwithInvalidData(driver,test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 87)
	void NoticePaymentWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment Without data verification");
		
		
		CFOMethod.PaymentLogWithoutData(driver,test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 88)
    	void NoticeExternalLawyer() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Lawyer verification");
    		
    		CFOMethod.ExternalLawyer(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    	@Test(priority = 0)
    	void ExternalLawyerWithoutRating() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice External Lawyer without rating verification");
    		
    		CFOMethod.ExternalLawyerWithoutRating(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	@Test(priority = 89)
	void CriteriaExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Existing Data verification");
		
		CFOMethod.CriteriaExistingData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 90)
	void CriteriaInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Invalid Data verification");
		
		CFOMethod.CriteriaInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 91)
	void CriteriaWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Without Data verification");
		
		CFOMethod.CriteriaWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
  	
//	@Test(priority = 92)
    	void NoticeAuditLog() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Audit Log verification");
    	
    		
    		CFOMethod.AuditLog(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}		
//@Test(priority =93)
    	void CaseDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Document Tab");
    		
    		
    		CFOMethod.Document(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority = 94)
		void CaseWithoutUploadDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Without Upload Document verification");
			
			
			CFOMethod.CaseWithoutUploadDocument(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 95)
	void CaseDocumentEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document with empty fields verification");
		
		
		CFOMethod.CaseDocumentEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 96)
	void CaseDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Search Fields verification");
		
		
		CFOMethod.CaseDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 97)
	void CaseDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share with Invalid data verification");
		
		
		CFOMethod.CaseDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =98)
	void CaseDocumentShareWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share without data verification");
		
		
		CFOMethod.CaseDocumentShareWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 99)
	void CaseDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share close button verification");
		
		
		CFOMethod.CaseDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
 @Test(priority =100)
	    void CaseSendMailWithDoc() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case Summary-Send Mail With Document verification");
	 	
	 	
	 	      CFOMethod.CaseSendMailWithDoc(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
	    
	    @Test(priority =101)
	    void CaseSendMailWithDocInvalidFields() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case  Summary-Send Mail With Document Invalid Fields verification");
	 	
	 	
	 	      CFOMethod.CaseSendMailWithDocInvalidFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
   @Test(priority =102)
	    void CaseSendMailWithDocEmptyFields() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case Summarys -Send Mail With Document Empty Fields verification");
	 	
	 	
	 	      CFOMethod.CaseSendMailWithDocEmptyFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
	    
//	@Test(priority =103)
    	void CaseTaskActivity() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Task/Activty Tab");
    		
    		
    		CFOMethod.TaskActivity1(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
		
@Test(priority = 104)
			void CaseTaskActivityWithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activtiy Without data verification");
				
				
				CFOMethod.CaseTaskActivityWithoutData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority =105)
    	void CaseTaskActivitywithExistingData() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Task/Activty with existing data");
    		
    		
    		CFOMethod.CaseTaskActivitywithExistingData(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
		
	 @Test(priority = 106)
			void CaseTaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activtiy Response Without data verification");
				
				
				CFOMethod.CaseTaskActivtityResponseWithoutStatus(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		 
@Test(priority =107)
			void CaseTaskActivtityResponseClearBtn() throws InterruptedException, IOException
			{
				test = extent.startTest("Case Task/Activtiy  Response clear button verification");
				
				
				CFOMethod.CaseTaskActivtityResponseClearBtn(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
//	@Test(priority =108)
    	void CaseHearingcfo() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - CaseHearing Tab");
    		
    		
    		CFOMethod.CaseHearing(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority =109)
	void CaseExistingHearingDate() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Existing Hearing Date Verification");
		
		
		CFOMethod.CaseExistingHearingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
  @Test(priority= 110)
  void CaseWithoutHearingData() throws InterruptedException, IOException
  {
	test = extent.startTest("Case without hearing data Verification");
	
	
	CFOMethod.CaseHearingWithoutData(driver, test);
	
	extent.endTest(test);
	extent.flush();
  }
@Test(priority =111)
   void CaseHearingInvalidDate() throws InterruptedException, IOException
   {
 	test = extent.startTest("Case Invalid Hearing Date Verification");
 	
 	
 	CFOMethod.CaseHearingInvalidDate(driver, test,workbook);
 	
 	extent.endTest(test);
 	extent.flush();
   }
 @Test(priority =112)
   void CaseHearingClearBtn() throws InterruptedException, IOException
   {
 	test = extent.startTest("Case heraing clear button Verification");
 	
 	
 	CFOMethod.CaseHearingClearBtn(driver, test,workbook);
 	
 	extent.endTest(test);
 	extent.flush();
   }
//@Test(priority =113)
    	void CaseOrder() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Case Order Tab");
    	
    		
    		CFOMethod.CaseOrder(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority =114)
	void CaseOrderExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Order with Duplicate data");

	
		CFOMethod.CaseOrderwithExistingData(driver, test,workbook);
	
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =115)
	void CaseOrderWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Without data Order tab");

	
		CFOMethod.CaseOrderWithoutData(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =116)
	void CaseOrderwithClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Order with clear button");

	
		CFOMethod.CaseOrderwithClearBtn(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =117)
    	void CaseStatusPayment() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Status/Payment Tab");
    		
    		
    		CFOMethod.StatusPayment(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority =118)
    	void StatusPaymentExistingdata() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case Status/Payment with duplicate data ");
    		
    		
    		CFOMethod.StatusPaymentExistingdata(driver, test,workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority =119)
void StatusPaymentwithInvaliddata() throws InterruptedException, IOException
{
	test = extent.startTest("Case Status/Payment with Invalid data ");
	
	
	CFOMethod.StatusPaymentwithInvaliddata(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =119)
    	void StatusPaymentWithoutdata() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case Status/Payment without data ");
    		
    		
    		CFOMethod.StatusPaymentWithoutdata(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
		
    	@Ignore
		@Test(priority =120)
    	void CaseStatusAppealtoNextCourtTwoMandatoryfields() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case Status -Enter Two manadatory fields click on Appeal to Next Court");
    		
    		
    		CFOMethod.CaseStatusAppealtoNextCourtTwoMandatoryfields(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    	
	@Test(priority =121)
    	void CaseStatusAppealtoNextCourt() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case Status Appeal to Next Court");
    		
    		
    		CFOMethod.CaseStatusAppealtoNextCourt(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority =122)
    	void CaseStatuswithEmptyFields() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case Status With Empty Fields");
    		
    		
    		CFOMethod.CaseStatuswithEmptyFields(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
//@Test(priority =123)
    	void CaseExternalLawyerRating() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - External Lawyer Rating");
    		
    		
    		CFOMethod.CaseExternalLawyer(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    	@Test(priority =124)
    	void CaseExternalLawyerWitoutRating() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - External Lawyer Without Rating");
    		
    		
    		CFOMethod.CaseExternalLawyerWitoutRating(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority =124)
	void CaseExternalLawyerCriteria() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - External Lawyer Rating -Add New Criteria ");
		
		
		CFOMethod.CaseExternalLawyerCriteria(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
   
 @Test(priority = 125)
	void CaseExistingCriteria() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Criteria Existing Data verification");
		
		CFOMethod.CaseExistingCriteria(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 @Test(priority = 126)
	void CaseCriteriaInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Criteria Invalid Data verification");
		
		CFOMethod.CaseCriteriaInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 127)
	void CaseCriteriaWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Criteria Without Data verification");
		
		CFOMethod.CaseCriteriaWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =128)
    	void CaseAuditLog() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Audit Log Tab");
    		
    		
    		CFOMethod.Auditlog(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
    

//@Test(priority = 129)
			void MyDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Download and View Document");
			
				
				CFOMethod.MyDocument(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
		
@Test(priority = 130)
	void ShareCaseDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Share Case Document Verification");
	
		
		CFOMethod.ShareCaseDocument(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 131)
	void ShareNoticeDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Share Notice Document Verification");
	
		
		CFOMethod.ShareNoticeDocument(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =132)
	void ShareTaskDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Share Task Document Verification");
	
		
		CFOMethod.ShareTaskDocument(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
// @Test(priority = 133)
				void MyReports() throws InterruptedException, IOException
				{
					test = extent.startTest("Reports -excel count verification");
					
					CFOMethod.MyReports(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	        
//	@Test(priority = 134)
				void MoreReports() throws InterruptedException, IOException
				{
					test = extent.startTest("More Report-Reports excel  verification");
					
					
					CFOMethod.MoreReport(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				
//	@Test(priority =135)
				void MyReminder() throws InterruptedException, IOException
				{
					test = extent.startTest("My Reminder verification");
					
					CFOMethod.MyReminder(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority =136)
	void ReminderWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder Without data verification");
		
		CFOMethod.ReminderWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
				
	// @Test(priority = 137)
				void ImportUtility() throws InterruptedException, IOException
				{
					test = extent.startTest("Import Utility verification");
					
					
					CFOMethod.ImportUtility(driver,test);
					extent.endTest(test);
					extent.flush();
				}
	 @Test(priority = 138)
		void ImportUtilityWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Upload Empty File Import Utility verification");
			
			
			CFOMethod.ImportUtilityWithoutData(driver,test);
			extent.endTest(test);
			extent.flush();
		}
 @Test(priority = 139)
		void ImportUtilityInvalidData() throws InterruptedException, IOException
		{
			test = extent.startTest("Enter Invalid data in Upload File Import Utility verification");
			
			
			CFOMethod.ImportUtilityInvalidData(driver,test);
			extent.endTest(test);
			extent.flush();
		}
 @Test(priority = 140)
		void ImportUtilityTwoManadtoryFileds() throws InterruptedException, IOException
		{
			test = extent.startTest("Enter Two Manadtory fields in Upload File Import Utility verification");
			
			
			CFOMethod.ImportUtilityTwoManadtoryFileds(driver,test);
			extent.endTest(test);
			extent.flush();
		}
// @Test(priority = 0)
	void CaseUpdationImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation Import Utility verification");
		
		
		CFOMethod.CaseUpdationImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}
// @Test(priority = 0)
	void CaseUpdationUploadEmtyFile() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation -Empty File Import Utility verification");
		
		
		CFOMethod.CaseUpdationUploadEmtyFile(driver,test);
		extent.endTest(test);
		extent.flush();
	}
 //@Test(priority = 0)
	void CaseUpdationUploadInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation -Invalid Data Import Utility verification");
		
		
		CFOMethod.CaseUpdationUploadInvalidData(driver,test);
		extent.endTest(test);
		extent.flush();
	}
// @Test(priority = 0)
	void CaseUpdationUploadInvalidFile() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation -Invalid File Import Utility verification");
		
		
		CFOMethod.CaseUpdationUploadInvalidFile(driver,test);
		extent.endTest(test);
		extent.flush();
	}
 
//@Test(priority = 0)
	void NoticeUpdation() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Updation Import Utility verification");
		
		
		CFOMethod.NoticeUpdation(driver,test);
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 0)
void NoticeUpdationUploadEmtyFile() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation-Empty File Import Utility verification");
	
	
	CFOMethod.NoticeUpdationUploadEmtyFile(driver,test);
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 0)
void NoticeUpdationUploadInvalidData() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation -Invalid Data Import Utility verification");
	
	
	CFOMethod.NoticeUpdationUploadInvalidData(driver,test);
	extent.endTest(test);
	extent.flush();
}

//@Test(priority = 0)
void NoticeUpdationUploadInvalidFile() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation -Invalid File Import Utility verification");
	
	
	CFOMethod.NoticeUpdationUploadInvalidFile(driver,test);
	extent.endTest(test);
	extent.flush();
}


	@Test(priority = 141)
			void AdvancedSearch() throws InterruptedException, IOException
			{
				test = extent.startTest("My Workspace-Advanced Search verification");
				
				
				CFOMethod.AdvancedSearchWorkspace(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority = 142)
			void AdvancedSearchDoc() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document(Advanced search) -Download and View Document");
				
				
				CFOMethod.AdvancedSearchDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority = 143)
		void AdvancedSearchShareCaseDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("My Document-Advance search-Share Case Document Verification");
		
			
			CFOMethod.AdvancedSearchShareCaseDocument(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority =144)
				void AdvancedSearchShareNoticeDocument() throws InterruptedException, IOException
				{
					test = extent.startTest("My Document-Advance search-Share Notice Document Verification");
				
					
					CFOMethod.AdvancedSearchShareNoticeDocument(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority =145)
				void AdvancedSearchShareTaskDocument() throws InterruptedException, IOException
				{
					test = extent.startTest("My Document-Advance search-Share Task Document Verification");
				
					
					CFOMethod.AdvancedSearchShareTaskDocument(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
		 
		@Test(priority = 146) 		//Sever is blocking and not allowing to upload the file.
			void CriticalDocuments() throws InterruptedException, IOException
			{
				test = extent.startTest(" Critical Document Verification");
				
				CFOMethod.CriticalDocuments(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority = 147) 		//Sever is blocking and not allowing to upload the file.
			void CriticalDocuments1() throws InterruptedException, IOException
			{
				test = extent.startTest(" Critical Document Verification");
				
				CFOMethod.CriticalDocuments1(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		//@Test(priority = 148)
			void AdvancedSearchreport() throws InterruptedException, IOException
			{
				test = extent.startTest("My Report- Advanced search verification");
			
				
				CFOMethod.AdvancedSearchReport(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
				
		//@Test(priority = 149)
			void Masters() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Legal Entity  verification");
					
					CFOMethod.LegalEntity(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority = 150)
		void MastersLegalEntity() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Legal Entity Without data verification");
				
				CFOMethod.LegalEntityWithoutData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		@Test(priority =151)
		void MastersLegalEntity1() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Legal Entity Invalid data verification");
				
				CFOMethod.LegalEntityInvalidData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	@Test(priority =152)
		void MastersLegalEntity2() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Legal Entity Two Manadatory Fields verification");
				
				CFOMethod.LegalEntityTwoManadatoryFields(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
		@Test(priority =153)
				void MastersLegalEntity3() throws InterruptedException, IOException
					{
						test = extent.startTest("Masters - Legal Entity Close Button verification");
						
						CFOMethod.LegalEntityCloseButton(driver, test);
						
						extent.endTest(test);
						extent.flush();
					}
		@Test(priority =154)
		void UnitEntity() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Add Unit Entity verification");
				
				CFOMethod.AddUnitType(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
	//@Test(priority = 155)
				void Masters1() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Law Firm verification");
					
					
					CFOMethod.LawFirm(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority =156)
	void MastersLawFirm() throws InterruptedException, IOException
	{
		test = extent.startTest("Law Firm Masters - Enter Without Data verification");
		
		
		CFOMethod.LawFirmWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =157)
	void MastersLawFirm1() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Law Firm Invalid Data verification");
		
		
		CFOMethod.LawFirmInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =158)
	void MastersLawFirm2() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Law Firm Two Manadtory fields verification");
		
		
		CFOMethod.LawFirmTwoManadatoryFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =159)
	void MastersLawFirm3() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Law Firm Close button verification");
		
		
		CFOMethod.LawFirmCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 160)
	void LawyerWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Lawyer  - Enter Without Data verification");
		
		
		CFOMethod.LawyerWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =161)
	void LawyerInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Lawyer  - Enter Invalid Data verification");
		
		
		CFOMethod.LawyerInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 162)
	void LawyerTwoManadatoryFileds() throws InterruptedException, IOException
	{
		test = extent.startTest("Lawyer  - Enter Two Manadatory fields verification");
		
		
		CFOMethod.LawyerTwoManadatoryFileds(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 163)
	void LawyerCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Lawyer  - Enter close button verification");
		
		
		CFOMethod.LawyerCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 164)
				void Masters2() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - 	User  verification");
					
					
					CFOMethod.User(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
@Test(priority = 165)
	void UserWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master-  without data verification");
		
		
		CFOMethod.UserWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 166)
	void UserInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master-  Invalid data verification");
		
		
		CFOMethod.UserInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =167)
	void UserTwoManadatoryFields() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master- Two manadatory fields verification");
		
		
		CFOMethod.UserTwoManadatoryFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =168)
	void UserCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master- Close Button  verification");
		
		
		CFOMethod.UserCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 169)
				void Masters3() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Opponent  verification");
					
					
					CFOMethod.Opponent(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	

	@Test(priority =170)
				void OpponentWithoutData() throws InterruptedException, IOException
				{
					test = extent.startTest("Opponent Masters -Without Data verification");
					
					
					CFOMethod.OpponentWithoutData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority =171)
	void OpponentInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Opponent Masters -Invalid Data verification");
		
		
		CFOMethod.OpponentInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 172)
	void OpponentCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Opponent Masters -Close button verification");
		
		
		CFOMethod.OpponentCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
				
//	@Test(priority = 173)
				void Masters4() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Court  verification");
					
					
					CFOMethod.Court(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 174)
	void CourtWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Without enter Data verification");
		
		
		CFOMethod.CourtWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 175)
	void CourtInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Enter Invalid Data verification");
		
		
		CFOMethod.CourtInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 176)
	void CourtTwomanadatoryFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Enter Two Manadtory Fields verification");
		
		
		CFOMethod.CourtTwomanadatoryFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =177)
	void CourtCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Close button verification");
		
		
		CFOMethod.CourtCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 178)
				void Masters5() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Case/NoticeType  verification");
					
					
					CFOMethod.CaseNoticeType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 179)
	void CaseNoticeTypeWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case/NoticeType Master -Without Enter Data  verification");
		
		
		CFOMethod.CaseNoticeTypeWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 180)
	void CaseNoticeTypeInvaliData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case/NoticeType Master - Enter Invalid Data  verification");
		
		
		CFOMethod.CaseNoticeTypeInvaliData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 181)
	void CaseNoticeTypeCloseBuuton() throws InterruptedException, IOException
	{
		test = extent.startTest("Case/NoticeType Master - Close Button  verification");
		
		
		CFOMethod.CaseNoticeTypeCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//  @Test(priority = 182)
				void Masters6() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Payment Type  verification");
					
					
					CFOMethod.PaymentType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
 
 @Test(priority = 183)
          void PaymentTypeWithouData() throws InterruptedException, IOException
         {
	         test = extent.startTest("Payment Type Master- Without Enter Data  verification");
	 
	
	          CFOMethod.PaymentTypeWithoutData(driver, test);
	
	          extent.endTest(test);
	          extent.flush();
         }
    @Test(priority = 184)
    void PaymentTypeInvalidData() throws InterruptedException, IOException
   {
       test = extent.startTest("Payment Type Master-Enter Invalid Data  verification");


        CFOMethod.PaymentTypeInvalidData(driver, test);

        extent.endTest(test);
        extent.flush();
   }
   @Test(priority = 185)
    void PaymentTypeCloseButton() throws InterruptedException, IOException
   {
       test = extent.startTest("Payment Type Master-Close button verification");


        CFOMethod.PaymentTypeCloseButton(driver, test);

        extent.endTest(test);
        extent.flush();
   }

	//@Test(priority =186)
				void Masters7() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Custom Parameter  verification");
				
					
					CFOMethod.customParameter(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 187)
	void customParameterWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Custom Parameter Master -Without Enter Data  verification");
	
		
		CFOMethod.customParameterWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 188)
	void customParameterInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Custom Parameter Master -Enter Invalid Data verification");
	
		
		CFOMethod.customParameterInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 189)
	void customParameterCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Custom Parameter Master -Close  button  verification");
	
		
		CFOMethod.customParameterCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 190)
				void Masters8() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Case Stage  verification");
				
					
					CFOMethod.CaseStage(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
@Test(priority = 191)
	void CaseStageWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Stage Masters - Without Enter Data  verification");
	
		
		CFOMethod.CaseStageWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 192)
	void CaseStageInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Stage Masters - Enter Invalid Data  verification");
	
		
		CFOMethod.CaseStageInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 193)
	void CaseStageCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Stage Masters - Close Button verification");
	
		
		CFOMethod.CaseStageCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	//	@Test(priority = 194)
				void Masters9() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Document Type  verification");
					
					
					CFOMethod.DocumentType(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority = 195)
		void DocumentTypeWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Document Type Masters-Without data  verification");
			
			
			CFOMethod.DocumentTypeWithoutData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
		@Test(priority =196)
		void DocumentTypeInvalidData() throws InterruptedException, IOException
		{
			test = extent.startTest("Document Type Masters-Enter Invalid Data verification");
			
			
			CFOMethod.DocumentTypeInvalidData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 197)
		void DocumentTypeCloseButton() throws InterruptedException, IOException
		{
			test = extent.startTest("Document Type Masters-Close button verification");
			
			
			CFOMethod.DocumentTypeCloseButton(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	//	@Test(priority = 198)
				void Masters10() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Rating Criteria  verification");
				
					
					CFOMethod.RatingCriteria(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
	@Test(priority = 199)
		void RatingCriteriaWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Rating Criteria Masters-Without Enter Data  verification");
		
			
			CFOMethod.RatingCriteriaWithoutData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 200)
		void RatingCriteriaInvalidData() throws InterruptedException, IOException
		{
			test = extent.startTest("Rating Criteria Masters-Enter Invalid Data  verification");
		
			
			CFOMethod.RatingCriteriaInvalidData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}		
	@Test(priority =201)
		void RatingCriteriaCloseButton() throws InterruptedException, IOException
		{
			test = extent.startTest("Rating Criteria Master-Close button verification");
		
			
			CFOMethod.RatingCriteriaCloseButton(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}		
//	@Test(priority = 202)
				void Masters12() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Notice Stage  verification");
					
					
					CFOMethod.NoticeStage(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
//		@Test(priority = 203)
				void Masters11() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - UserReassignment  verification");
					
					
					CFOMethod.UserReassignment(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
//	@Test(priority = 204)
				void Masters13() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Mail Authorization  verification");
					
					CFOMethod.MailAuthorization(driver,test);
					
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
				
	//	@Test(priority = 206)
				void CaseHearing() throws InterruptedException, IOException
				{
					test = extent.startTest("Case Hearing Count Verification");
					//test.log(LogStatus.INFO, "Test Initiated");
					
					CFOMethod.CaseHearing(driver, test,"Performer","Case Hearing-");
					
					extent.endTest(test);
					extent.flush();
				}
	    	@Test(priority = 207)
		    	void NoticeDocViewandDownload() throws InterruptedException, IOException
		    	{
		    		test = extent.startTest("Notice Document verification");
		    		//test.log(LogStatus.INFO, "Test Initiated");
		    		
		    		CFOMethod.NoticeDocViewandDownload(driver, test);
		    		
		    		extent.endTest(test);
		    		extent.flush();
		    	}
				
			
			
				
				
	@Test(priority = 208)
				void WorkspaceFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("My Workspace = Notice = Multiple  Filters verification");
					
					
					CFOMethod.WorkspaceNoticeFilter(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
			
		 @Test(priority = 209)
				void WorkspaceCaseFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("My Workspace = Case = Multiple  Filters verification");
					
					
					CFOMethod.WorkspaceCaseFilter(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
	 @Test(priority = 210)
			void WorkspaceTaskFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("My Workspace = Task = Multiple  Filters verification");
				
				
				CFOMethod.WorkspaceTaskFilter(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
 @Test(priority = 211)
			void WorkspaceCaseHearingFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("My Workspace = Case Hearing = Search box  Filter verification");
				
				
				CFOMethod.WorkspaceCaseHearingFilter(driver, test,"A/Bita Pharma Company");
				
				extent.endTest(test);
				extent.flush();
			}
			
  @Test(priority = 212)
				void DocumentFilter() throws InterruptedException, IOException
				{
					test = extent.startTest(" My Document = Notice = Multiple  Filters verification");
				
					
					CFOMethod.DocumentNoticeFilter(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
  @Test(priority = 213)
	void DocumentCaseFilter() throws InterruptedException, IOException
	{
		test = extent.startTest(" My Document = Case = Multiple  Filters verification");
	
		
		CFOMethod.DocumentCaseFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 @Test(priority = 214)
  	void DocumentTaskFilter() throws InterruptedException, IOException
  	{
  		test = extent.startTest(" My Document = Task = Multiple  Filters verification");
  	
  		
  		CFOMethod.DocumentTaskFilter(driver, test);
  		
  		extent.endTest(test);
  		extent.flush();
  	}
				
	 @Test(priority =215)
					void ReportNoticeFilter() throws InterruptedException, IOException
					{
						test = extent.startTest("My Report = Notice =  Filters verification");
						
						
						CFOMethod.ReportNoticeFilter(driver, test);
						
						extent.endTest(test);
						extent.flush();
					}
		
		@Test(priority = 216)
				void ReportCaseFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("My Report = Case =  Filters verification");
					
					
					CFOMethod.ReportCaseFilter(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
	 @Test(priority =217)
				void ReportTaskFilter() throws InterruptedException, IOException
				{
					test = extent.startTest("My Report = Task =  Filters verification");
					
					
					CFOMethod.ReportTaskFilter(driver, test);
					
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
