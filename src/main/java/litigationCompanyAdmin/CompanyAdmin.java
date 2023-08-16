package litigationCompanyAdmin;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.MethodPOM1;
import litigationAdditionalOwner.MethodsPOM;
import litigationAdditionalOwner.performerPOM;
import litigationManagement.CFOMethod;
import performer.OverduePOM;

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
		fis = new FileInputStream("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx");
	
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(4);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationCompanyAdmin.html",true);
		//test = extent.startTest("Verify OpenBrowser");
		test = extent.startTest("Litigation Logging In - Company Admin");
		//test.log(LogStatus.INFO, "Browser test is initiated");
		
//		XSSFSheet sheet = ReadExcel();
//		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
//		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
//		
//		login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	@BeforeMethod
	
	void Login() throws InterruptedException, IOException
	{
		//test = extent.startTest("Litigation Logging In - Company Admin");
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
		
		driver = login.Login.UserLogin(uname,password,"company");		//Method of Login class to login user.
		
//		test.log(LogStatus.PASS, "Test Passed.");
//		extent.endTest(test);
//		extent.flush();
	}

	
//	@Test(priority = 2)
	void HearingCalender() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Hearing Calender Verification");
		
		
		MethodsPOM.HearingCalender(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 3)
			void CaseNoticeTypeGraph() throws InterruptedException, IOException
			{
				test = extent.startTest("Select Notice Filter  = Case Notice Type Graph Count Verification");
				
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
		     	js.executeScript("window.scrollBy(0,850)");
		     	
		     	Thread.sleep(5000);
				performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
				Thread.sleep(5000);
				performerPOM.clickDashboardNoticeFilter(driver).click();
	          
	           	
				 Thread.sleep(5000);
				 performerPOM.clickDashboardApplyBtn(driver).click();
				
		    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseNoticeTypeOutwardPlaintiff(driver).getText());	//Reading Notice Open count.
		    	int	InwardDefendent = Integer.parseInt(performerPOM.CaseNoticeTypeInwardDefendent(driver).getText());	//Reading Notice Open count.
		    	int	Petitioner = Integer.parseInt(performerPOM.CaseNoticeTypeComplinant(driver).getText());	//Reading Notice Open count.
		    	int	Respondent = Integer.parseInt(performerPOM.CaseNoticeTypeRespondent(driver).getText());	//Reading Notice Open count.
		    	
				
		    	Thread.sleep(3000);
		    	MethodsPOM.CaseNoticeTypeGraph(driver, test,"Outward/Plaintiff Type",OutwardPlaintiff);
				Thread.sleep(3000);
				MethodsPOM.CaseNoticeTypeGraph(driver, test,"Inward/Defendent Type",InwardDefendent);
				Thread.sleep(3000);
				MethodsPOM.CaseNoticeTypeGraph(driver, test,"Petitioner Type",Petitioner);
				Thread.sleep(3000);
				MethodsPOM.CaseNoticeTypeGraph(driver, test,"Respondent Type",Respondent);
				
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
				
				extent.endTest(test);
				extent.flush();
			}
		//@Test(priority =1)
			void CaseNoticeTypeGraphFilter() throws InterruptedException, IOException
			{
				test = extent.startTest("Select Notice Filter =  Case Notice type Graph -Select Status Filter Verification");
				
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("window.scrollBy(0,900)");	 
				Thread.sleep(5000);
				performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
				Thread.sleep(5000);
				performerPOM.clickDashboardNoticeFilter(driver).click();
	        
	         	
				 Thread.sleep(5000);
				 performerPOM.clickDashboardApplyBtn(driver).click();
			
				Thread.sleep(3000);
		    	MethodsPOM.CaseNoticeTypeGraphFilter(driver, test,"Outward/Plaintiff Type");
				Thread.sleep(3000);
				MethodsPOM.CaseNoticeTypeGraphFilter(driver, test,"Inward/Defendent Type");
				Thread.sleep(3000);
				MethodsPOM.CaseNoticeTypeGraphFilter(driver, test,"Petitioner Type");
				Thread.sleep(3000);
				MethodsPOM.CaseNoticeTypeGraphFilter(driver, test,"Respondent Type");
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
				
			
				extent.endTest(test);
				extent.flush();
			}	
			

//@Test(priority = 4)
	void CaseNoticeStageGraphNotice() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter = Open Stage = Case Notice Stage Graph Count Verification");
		
		
		MethodsPOM.CaseNoticeStageGraph(driver, test,"Notice");
		
		extent.endTest(test);
		extent.flush();
	}
	//@Test(priority = 1)
	void CaseNoticeStageGraphFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter = Case Notice stage graph  = Select status filter Verification");
		
		
		MethodsPOM.CaseNoticeStageGraphFilter(driver, test,"Open Stage");
		
		extent.endTest(test);
		extent.flush();
	}
 
	

@Test(priority =5)
	
	void RiskSummaryGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter  =Risk Summary Graph Count Verification");
	    
	
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
	        	Thread.sleep(5000);
				performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
				Thread.sleep(5000);
				performerPOM.clickDashboardNoticeFilter(driver).click();
	          
	           	
				 Thread.sleep(5000);
				 performerPOM.clickDashboardApplyBtn(driver).click();
				 js.executeScript("window.scrollBy(0,900)");
				
				
			    int	HighRisk = Integer.parseInt(performerPOM.RiskSummaryHigh(driver).getText());	//Reading Notice Open count.
		    	int	MediumRisk = Integer.parseInt(performerPOM.RiskSummaryMedium(driver).getText());	//Reading Notice Open count.
		    	int	LowRisk = Integer.parseInt(performerPOM.RiskSummaryLowCA(driver).getText());	//Reading Notice Open count.
		    	int	NotApplicableRisk = Integer.parseInt(performerPOM.RiskSummaryNotApplicableCA(driver).getText());	//Reading Notice Open count.
		    	
				
		    	Thread.sleep(3000);
		    	MethodsPOM.RiskSummaryGraph(driver, test,"High Risk",HighRisk);
				Thread.sleep(3000);
				MethodsPOM.RiskSummaryGraph(driver, test,"Medium Risk",MediumRisk);
				Thread.sleep(3000);
				MethodsPOM.RiskSummaryGraph(driver, test,"Low Risk",LowRisk);
				Thread.sleep(3000);
				MethodsPOM.RiskSummaryGraph(driver, test,"Not Applicable Risk",NotApplicableRisk);
				
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
		         extent.endTest(test);
		        extent.flush();
	}

  //@Test(priority =2)
     void RiskSummaryGraphFilter() throws InterruptedException, IOException
       {
	       test = extent.startTest("Select Notice Filter =  Risk Summary  Graph -Select Status Filter Verification");
	
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,800)");
	      Thread.sleep(5000);
	      performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
	      Thread.sleep(5000);
	      performerPOM.clickDashboardNoticeFilter(driver).click();

 	
	      Thread.sleep(5000);
	      performerPOM.clickDashboardApplyBtn(driver).click();
	      
	      js.executeScript("window.scrollBy(0,1000)");

	  	
	    	Thread.sleep(3000);
	    	MethodsPOM.RiskSummaryGraphFilter(driver, test,"High Risk");
			Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraphFilter(driver, test,"Medium Risk");
			Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraphFilter(driver, test,"Low Risk");
			Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraphFilter(driver, test,"Not Applicable Risk");
	
	        Thread.sleep(3000);
	        OverduePOM.clickDashboard(driver).click();
            extent.endTest(test);
	        extent.flush();
    }	



//@Test(priority =6)
	
	void DepartmentSummaryGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter =IT Department- Department Summary Graph Count Verification");
		
		MethodsPOM.DepartmentSummaryGraph(driver, test,"Notice");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =1)
	
	void DepartmentSummaryGraphFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter - Department summary graph - Select status filter Verification");
		
		MethodsPOM.DepartmentSummaryGraphFilter(driver, test,"IT Department");
		
		extent.endTest(test);
		extent.flush();
	}
 // @Test(priority = 7)
	
	void LocationSummaryGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter =ABC Mall,Thane Location- Location Summary Graph Count Verification");
		
		
		MethodsPOM.LocationSummaryGraph(driver, test,"Notice");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 1)
	
	void LocationSummaryGraphFilter() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Notice Filter - Location Summary Graph -Select status filter Verification");
		
		
		MethodsPOM.LocationSummaryGraphFilter(driver, test,"Management Location");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 8)
	
  	void CategorySummaryGraph() throws InterruptedException, IOException
  	{
  		test = extent.startTest("Select Notice Filter =LMP Category- Category Summary Graph Count Verification");
  		
  		
  		MethodsPOM.CategorySummaryGraph(driver, test,"Notice");
  		
  		extent.endTest(test);
  		extent.flush();
  	}
//@Test(priority =1)
	
  	void CategorySummaryGraphFilter() throws InterruptedException, IOException
  	{
  		test = extent.startTest("Select Notice Filter - Category Summary Graph -Select status filter Verification");
  		
  		
  		MethodsPOM.CategorySummaryGraphFilter(driver, test,"LMP Category");
  		
  		extent.endTest(test);
  		extent.flush();
  	}

//@Test(priority = 1)
    void InwardDefendantAgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter =Less than a year  = Ageing Graph Count Verification");
         
         JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
       
        	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3000)");
			
		    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA(driver).getText());	//Reading Notice Open count.
	    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickComplainantAgeing(driver).getText());	//Reading Notice Open count.
	    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCA(driver).getText());	//Reading Notice Open count.
	    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA(driver).getText());	//Reading Notice Open count.
	    	
			
	    	Thread.sleep(3000);
	    	MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Inward/Defendent",InwardDefendent);
			Thread.sleep(3000);
			MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
			Thread.sleep(3000);
			MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Petitioner",Petitioner);
			Thread.sleep(3000);
			MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Respondent",Respondent);
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
        
          extent.endTest(test);
          extent.flush();
    }
    
 // @Test(priority = 1)
    void LessThanYearGraphFilter() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Notice Filter =Less than a year  = Select status filter Verification");
         
         JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
       
        	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3700)");
			
			
		 	Thread.sleep(3000);
	    	MethodsPOM.LessThanYearGraphFilter(driver, test,"Inward/Defendent");
			Thread.sleep(3000);
			MethodsPOM.LessThanYearGraphFilter(driver, test,"Outward/Plaintiff");
			Thread.sleep(3000);
			MethodsPOM.LessThanYearGraphFilter(driver, test,"Petitioner");
			Thread.sleep(3000);
			MethodsPOM.LessThanYearGraphFilter(driver, test,"Respondent");
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
        
          extent.endTest(test);
          extent.flush();
    }
  	
//@Test(priority = 2)
void AgeingGraph1to2years() throws InterruptedException, IOException
{
     test = extent.startTest("Select Notice Filter =1 to 2 years = Ageing Graph Count Verification");
     
     JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,800)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
   
    	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3000)");
		
	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1to2(driver).getText());	//Reading Notice Open count.
    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickPetitionerCA(driver).getText());	//Reading Notice Open count.
    	int	Respondent = Integer.parseInt(performerPOM.clickPetitionerCase(driver).getText());	//Reading Notice Open count.
    	
		
    	Thread.sleep(3000);
    	MethodsPOM.AgeingGraph1to2years(driver, test,"Inward/Defendent",InwardDefendent);
		Thread.sleep(3000);
		MethodsPOM.AgeingGraph1to2years(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
		Thread.sleep(3000);
		MethodsPOM.AgeingGraph1to2years(driver, test,"Respondent",Respondent);
		
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
 }
  //@Test(priority = 2)
   void GraphFilter1to2years() throws InterruptedException, IOException
   {
      test = extent.startTest("Select Notice Filter =1 to 2 years = Select status filter Verification");
   
       JavascriptExecutor js = (JavascriptExecutor) driver;
      	js.executeScript("window.scrollBy(0,800)");
   	
   	    Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
 
  	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3700)");
		
		Thread.sleep(3000);
    	MethodsPOM.GraphFilter1to2years(driver, test,"Inward/Defendent");
		Thread.sleep(3000);
		MethodsPOM.GraphFilter1to2years(driver, test,"Outward/Plaintiff");
		Thread.sleep(3000);
		MethodsPOM.GraphFilter1to2years(driver, test,"Petitioner");
		
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
         extent.endTest(test);
         extent.flush();
    }



 //  @Test(priority = 1)
    void AgeingGraph2to3years() throws InterruptedException, IOException
    {
      test = extent.startTest("Select Notice Filter =2 to 3 years = Ageing Graph Count Verification");
     
       JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,800)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
   
    	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3700)");
		
	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA2to3(driver).getText());	//Reading Notice Open count.
    	
    	Thread.sleep(3000);
    	MethodsPOM.AgeingGraph2to3years(driver, test,"Inward/Defendent",InwardDefendent);
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
  }	
    
      //@Test(priority = 1)
    void GraphFilter2to3years() throws InterruptedException, IOException
    {
      test = extent.startTest("Select Notice Filter =2 to 3 years = Select status filter Verification");
     
       JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,800)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
   
    	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3800)");
		
	   
    	
    	Thread.sleep(3000);
    	MethodsPOM.GraphFilter2to3years(driver, test,"Inward/Defendent");
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
  }	

// @Test(priority = 1)
   void AgeingGraphMorethan3years() throws InterruptedException, IOException
    {
        test = extent.startTest("Select Notice Filter =More than 3 years = Ageing Graph Count Verification");
     
        JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,800)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
   
    	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3000)");
		
	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendent1(driver).getText());	//Reading Notice Open count.
    	
    	Thread.sleep(3000);
    	MethodsPOM.AgeingGraphMorethan3years(driver, test,"Inward/Defendent",InwardDefendent);
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
   }	
 //@Test(priority = 1)
   void Morethan3yearsGraphFilter() throws InterruptedException, IOException
    {
        test = extent.startTest("Select Notice Filter =More than 3 years = Select Status filter  Verification");
     
        JavascriptExecutor js = (JavascriptExecutor) driver;
     	js.executeScript("window.scrollBy(0,800)");
     	
     	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
   
    	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,3800)");
		
    	Thread.sleep(3000);
    	MethodsPOM.Morethan3yearsGraphFilter(driver, test,"Inward/Defendent");
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
   }
  	
  	

//@Test(priority =1)
	void CaseNoticeTypeGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter  = Case Notice Type Graph Count Verification");
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
  	    js.executeScript("window.scrollBy(0,850)");
  	
  	    Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
   
    	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		
       int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseNoticeTypeSummaryGraph(driver).getText());	//Reading Notice Open count.
 	   int	InwardDefendent = Integer.parseInt(performerPOM.CaseNoticeTypeInwardDefendent(driver).getText());	//Reading Notice Open count.
 	   int	Petitioner = Integer.parseInt(performerPOM.CaseNoticeTypeComplinant(driver).getText());	//Reading Notice Open count.
 	   int	Respondent = Integer.parseInt(performerPOM.CaseNoticeTypeRespondent(driver).getText());	//Reading Notice Open count.
 	
		
 	    Thread.sleep(3000);
 	    MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Inward/Defendent Type",OutwardPlaintiff);
		Thread.sleep(3000);
		MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Outward/Plaintiff Type",InwardDefendent);
		Thread.sleep(3000);
		MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Respondent Type",Petitioner);
		Thread.sleep(3000);
		MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Petitioner Type",Respondent);
		
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		
		extent.endTest(test);
		extent.flush();
	}
	
	
	
 // @Test(priority =1)
	void CaseNoticeTypeGraph1Filter() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter =  Case Notice type Graph -Select Status Filter Verification");
		
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,900)");	 
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
    
     	
		 Thread.sleep(5000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
	
		  Thread.sleep(3000);
	 	    MethodsPOM.CaseNoticeTypeGraph1Filter(driver, test,"Inward/Defendent Type");
			Thread.sleep(3000);
			MethodsPOM.CaseNoticeTypeGraph1Filter(driver, test,"Outward/Plaintiff Type");
			Thread.sleep(3000);
			MethodsPOM.CaseNoticeTypeGraph1Filter(driver, test,"Respondent Type");
			Thread.sleep(3000);
			MethodsPOM.CaseNoticeTypeGraph1Filter(driver, test,"Petitioner Type");
		
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
		
	
			extent.endTest(test);
			extent.flush();
		}	
	
 
//  @Test(priority =10)
	void CaseNoticeStageGraphNotice1() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter = Admission hearing Stage = Case Notice Stage Graph Count Verification");
		
		
		MethodsPOM.CaseNoticeStageGraph1(driver, test,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
	
 
	   
	// @Test(priority = 1)
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
	    	int	NotApplicableRisk = Integer.parseInt(performerPOM.RiskSummaryNotApplicable(driver).getText());	//Reading Notice Open count.
	    	
			
	    	Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraph1(driver, test,"High Risk",HighRisk);
			Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraph1(driver, test,"Medium Risk",MediumRisk);
			Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraph1(driver, test,"Low Risk",LowRisk);
			Thread.sleep(3000);
			MethodsPOM.RiskSummaryGraph1(driver, test,"Not Applicable Risk",NotApplicableRisk);
		
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
			extent.endTest(test);
			extent.flush();
		}
	//	@Test(priority = 1)
				void RiskSummaryGraph1Filter() throws InterruptedException, IOException
				{
					test = extent.startTest("Select Case Filter = Risk Summary  Graph -Select Status filter Verification");
			        
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
					
				 
			    	Thread.sleep(3000);
					MethodsPOM.RiskSummaryGraph1Filter(driver, test,"High Risk");
					Thread.sleep(3000);
					MethodsPOM.RiskSummaryGraph1Filter(driver, test,"Medium Risk");
					Thread.sleep(3000);
					MethodsPOM.RiskSummaryGraph1Filter(driver, test,"Low Risk");
					Thread.sleep(3000);
					MethodsPOM.RiskSummaryGraph1Filter(driver, test,"Not Applicable Risk");
				
					Thread.sleep(3000);
					OverduePOM.clickDashboard(driver).click();
					
					extent.endTest(test);
					extent.flush();
				}
//@Test(priority = 12)

	void DepartmentSummaryGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest(" Select Case Filter= Finance Department =Department Summary Graph Count Verification");
		
		MethodsPOM.DepartmentSummaryGraph1(driver, test,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 13)

void LocationSummaryGraph1() throws InterruptedException, IOException
{
	test = extent.startTest(" Select Case Filter = ABC Mall Thane Location =Location Summary Graph Count Verification");
	
	
	MethodsPOM.LocationSummaryGraph1(driver, test,"Case");
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 14)

	void CategorySummaryGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest(" Select Case Filter = Tax Category = Category Summary Graph Count Verification");
		
		
		MethodsPOM.CategorySummaryGraph1(driver, test,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 1)
    void AgeingGraph() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Case Filter =Less than a year  = Ageing Graph Count Verification");
         
         JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
       
        	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3000)");
			
		    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1(driver).getText());	//Reading Notice Open count.
	    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffAgeing1(driver).getText());	//Reading Notice Open count.
	    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCA1(driver).getText());	//Reading Notice Open count.
	    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA1(driver).getText());	//Reading Notice Open count.
	    	
			
	    	Thread.sleep(3000);
	    	MethodsPOM.AgeingGraphLessThanYear(driver, test,"Inward/Defendent",InwardDefendent);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphLessThanYear(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphLessThanYear(driver, test,"Petitioner",Petitioner);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphLessThanYear(driver, test,"Respondent",Respondent);
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
        
          extent.endTest(test);
          extent.flush();
    }
    
 //@Test(priority = 1)
    void AgeingGraphFilter() throws InterruptedException, IOException
    {
         test = extent.startTest("Select Case Filter =Less than a year  = Select status filter Verification");
         
         JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
       
        	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			
			 js.executeScript("window.scrollBy(0,3700)");
	
	    		
	    	Thread.sleep(3000);
	    	MethodsPOM.AgeingGraphLessThanYearFilter(driver, test,"Inward/Defendent");
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphLessThanYearFilter(driver, test,"Outward/Plaintiff");
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphLessThanYearFilter(driver, test,"Petitioner");
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphLessThanYearFilter(driver, test,"Respondent");
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
        
          extent.endTest(test);
          extent.flush();
    }
	
	
	//@Test(priority =1)
	void AgeingGraph1to2yearsCase() throws InterruptedException, IOException
	{
	     test = extent.startTest("Select Case Filter =1 to 2 years = Ageing Graph Count Verification");
	     
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
	   
	    	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3000)");
			
		    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1to21(driver).getText());	//Reading Notice Open count.
	    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffCaseCA(driver).getText());	//Reading Notice Open count.
	    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCAA(driver).getText());	//Reading Notice Open count.
	    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA2(driver).getText());	//Reading Notice Open count.
			
	    	Thread.sleep(3000);
	    	MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Inward/Defendent",InwardDefendent);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Petitioner",Petitioner);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Respondent",Respondent);
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
	    
	      extent.endTest(test);
	      extent.flush();
	 }
	
 // @Test(priority =1)
		void AgeingGraph1to2yearsCaseFilter() throws InterruptedException, IOException
		{
		     test = extent.startTest("Select Case Filter =1 to 2 years = Select status filter Verification");
		     
		     JavascriptExecutor js = (JavascriptExecutor) driver;
		     	js.executeScript("window.scrollBy(0,800)");
		     	
		     	Thread.sleep(5000);
				performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
				Thread.sleep(5000);
				performerPOM.clickDashboardCaseFilter(driver).click();
		   
		    	
				 Thread.sleep(5000);
				 performerPOM.clickDashboardApplyBtn(driver).click();
				 Thread.sleep(3000);
				js.executeScript("window.scrollBy(0,3700)");
			
				
		    	Thread.sleep(3000);
		    	MethodsPOM.AgeingGraph1to2yearsCaseFilter(driver, test,"Inward/Defendent");
				Thread.sleep(3000);
				MethodsPOM.AgeingGraph1to2yearsCaseFilter(driver, test,"Outward/Plaintiff");
				Thread.sleep(3000);
				MethodsPOM.AgeingGraph1to2yearsCaseFilter(driver, test,"Petitioner");
				Thread.sleep(3000);
				MethodsPOM.AgeingGraph1to2yearsCaseFilter(driver, test,"Respondent");
				
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
		    
		      extent.endTest(test);
		      extent.flush();
		 }
	//@Test(priority =1)
	void AgeingGraph2to3yearsCase() throws InterruptedException, IOException
	{
	     test = extent.startTest("Select Case Filter =2 to 3 years = Ageing Graph Count Verification");
	     
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
	   
	    	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3000)");
			
		    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA2to3Case(driver).getText());	//Reading Notice Open count.
	    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffCaseCA2to3(driver).getText());	//Reading Notice Open count.
	    
			
	    	Thread.sleep(3000);
	    	MethodsPOM.AgeingGraph2to3yearsCase(driver, test,"Inward/Defendent",InwardDefendent);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraph2to3yearsCase(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
			
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
	    
	      extent.endTest(test);
	      extent.flush();
	 }
	
	
//	@Test(priority =1)
	void AgeingGraph2to3yearsCaseFilter() throws InterruptedException, IOException
	{
	     test = extent.startTest("Select Case Filter =2 to 3 years = Select status filter Verification");
	     
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
	   
	    	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3850)");
		
	    	Thread.sleep(3000);
	    	MethodsPOM.AgeingGraph2to3yearsCaseFilter(driver, test,"Inward/Defendent");
			Thread.sleep(3000);
			MethodsPOM.AgeingGraph2to3yearsCaseFilter(driver, test,"Outward/Plaintiff");
			
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
	    
	      extent.endTest(test);
	      extent.flush();
	 }
	
	
	

	//@Test(priority =1)
	void AgeingGraphMoreThan3yearsCase() throws InterruptedException, IOException
	{
	     test = extent.startTest("Select Case Filter =More than 3 years = Ageing Graph Count Verification");
	     
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
	   
	    	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			 Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,3000)");
			
		    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCAMoreThan3yearsCase(driver).getText());	//Reading Notice Open count.
	    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffCaseCAMoreThan3years(driver).getText());	//Reading Notice Open count.
	    
			
	    	Thread.sleep(3000);
	    	MethodsPOM.AgeingGraphMoreThan3yearsCase(driver, test,"Inward/Defendent",InwardDefendent);
			Thread.sleep(3000);
			MethodsPOM.AgeingGraphMoreThan3yearsCase(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
			
			
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
	    
	      extent.endTest(test);
	      extent.flush();
	 }

//	@Test(priority =1)
		void AgeingGraphMoreThan3yearsCaseFilter() throws InterruptedException, IOException
		{
		     test = extent.startTest("Select Case Filter =More than 3 years = Select status  filter Verification");
		     
		     JavascriptExecutor js = (JavascriptExecutor) driver;
		     	js.executeScript("window.scrollBy(0,800)");
		     	
		     	Thread.sleep(5000);
				performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
				Thread.sleep(5000);
				performerPOM.clickDashboardCaseFilter(driver).click();
		   
		    	
				 Thread.sleep(5000);
				 performerPOM.clickDashboardApplyBtn(driver).click();
				 Thread.sleep(3000);
				js.executeScript("window.scrollBy(0,3850)");
				
			  
		    	Thread.sleep(3000);
		    	MethodsPOM.AgeingGraphMoreThan3yearsCaseFilter(driver, test,"Inward/Defendent");
				Thread.sleep(3000);
				MethodsPOM.AgeingGraphMoreThan3yearsCaseFilter(driver, test,"Outward/Plaintiff");
				
				
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
		    
		      extent.endTest(test);
		      extent.flush();
		 }

	 
 
    
@Test(priority =15)
 	void NoticeOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice - Open Count Verification");
 		
 		
 		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
 		
 		test.log(LogStatus.PASS, "Test Passed.");
 		extent.endTest(test);
 		extent.flush();
 	}
	//@Test(priority =1)
		void NoticeWithExistingData() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice With Existing Data verification");
		   MethodsPOM.NoticeWithExistingData(driver, test, workbook);
		  extent.endTest(test);
			extent.flush();
		}
	
	// @Test(priority =2)
     void NoticeWithInvalidData() throws InterruptedException, IOException
    {
	     test = extent.startTest("Notice With Invalid Data verification");
	
	
	     MethodsPOM.NoticeWithInvalidData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
   }
	 
	// @Test(priority =3)
	   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Two Mandatory Fields verification");
		
		
		     MethodsPOM.NoticeWithTwoMandatoryData(driver, test, workbook);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	 
// @Test(priority =4) 
	   void NoticeWithEmptyFields() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Empty Fields verification");
		
		
		     MethodsPOM.NoticeWithEmptyFields(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
 
// @Test(priority =1)
 	void NoticeClearBtn() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice Summary -Clear button verification");
	
	
 		MethodsPOM.NoticeClearBtn(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 	}
//@Test(priority =1)
 void NoticeSendMailWithDoc() throws InterruptedException, IOException
{
	     test = extent.startTest("Notice Summary-Send Mail With Document verification");
	
	
	     MethodsPOM.NoticeSendMailWithDoc(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
}
 
//@Test(priority =3)
 void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
 {
     test = extent.startTest(" Notice Summary -Send Mail With Document Invalid Fields verification");


     MethodsPOM.NoticeSendMailWithDocInvalidFields(driver, test);

     extent.endTest(test);
     extent.flush();
 }

 // @Test(priority =1)
	void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
	{
	     test = extent.startTest("Notice Summary -Send Mail With Document Empty Fields verification");
	
	
	     MethodsPOM.NoticeSendMailWithDocEmptyFields(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
	}
  
//@Test(priority =1)
  	void NoticeUserAssignment() throws InterruptedException, IOException
 	{
	     test = extent.startTest("Notice User Assignment  verification");
	
         MethodsPOM.NoticeUserAssignment(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 	}

 
@Test(priority =16)
void NoticeClosed() throws InterruptedException, IOException
{
	test = extent.startTest("Notice - Closed Count Verification");
	
	
	MethodsPOM.NoticeClosed(driver, test, workbook, "Performer");
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 17)
	void CaseOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Open Count Verification");
		
		
		MethodsPOM.CaseOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =18)
	void CaseClose() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Closed Count Verification");
		
		MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 19)
	void TaskOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Open Count Verification");
		
		
		MethodsPOM.TaskOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 20)
	void TaskClosed() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Closed Count Verification");
		
		
		MethodsPOM.TaskClosed(driver, test, workbook, "Performer");
		
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
	@Test(priority =23)
  void LinkNoticeDeleteIcon() throws InterruptedException, IOException
	{
	     test = extent.startTest("Linked notice Delete icon  verification");
	
	

	 	MethodsPOM.LinkNoticeDeleteIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
	}
	@Test(priority = 22)
	void LinkCase() throws InterruptedException, IOException
	{
	test = extent.startTest("Link Case Verification");
    MethodsPOM.LinkDocument(driver, test, workbook, "Case");
    extent.endTest(test);
	extent.flush();
   }
	
	@Test(priority = 23)
	void CloseNotice() throws InterruptedException, IOException
	{
	test = extent.startTest("Close Notice Count Verification");
	MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
	extent.endTest(test);
	extent.flush();
	}

@Test(priority = 24)
	void CloseCase() throws InterruptedException, IOException
	{
	test = extent.startTest("Close Case Count Verification");
		
		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
		
	extent.endTest(test);
		extent.flush();
	}
	


	@Test(priority = 25)
	 	void NoticeDocumentTab() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice Document verification");
	 		
	 		
	 		MethodsPOM.NoticeDocument(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	
	@Test(priority = 1)
	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Without Upload Document verification");
		
		
		MethodsPOM.NoticeWithoutUploadDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 2)
	void NoticeDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Search Fields verification");
		
		
		MethodsPOM.NoticeDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 3)
	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share with Invaid data verification");
		
		
		MethodsPOM.NoticeDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 26)
	void NoticeTaskActivityTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activity verification");
		
		
		MethodsPOM.TaskActivtity(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 27)
	void NoticeResponseTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response tab verification");
		
		
		MethodsPOM.Response(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 28)
	void NoticePaymentLogTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice PaymentLog tab verification");
		
		
		MethodsPOM.PaymentLog(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 3)
	void NoticeExternalLawyerTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice ExternalLawyerRating tab verification");
		
		
		MethodsPOM.ExternalLawyerRating(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 30)
	void NoticeAuditLogTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice AuditLog tab verification");
		
		
		MethodsPOM.AuditLog(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 31)
void NoticeDocViewandDownload() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Document verification");
	
	
	MethodsPOM.NoticeDocViewandDownload(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

	@Test(priority = 32)
 	void CaseDocumentTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Document verification");
 		
 		
 		MethodsPOM.Document(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	@Test(priority = 33)
 	void CaseTaskActivityTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Task/Activity verification");
 		
 		
 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	@Test(priority = 34)
 	void CaseHearingTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Hearing verification");
 		
 		
 		MethodsPOM.CaseHearing(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	@Test(priority = 35)
 	void CaseOrderTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Order verification");
 		
 		
 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	@Test(priority = 36)
 	void CaseAdvocateBillTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case advocate bill verification");
 	
 		
 		MethodsPOM.AdvocateBill(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	@Test(priority = 37)
 	void StatusPayment() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Status/Payment verification");
 	
 		
 		MethodsPOM.StatusPayment(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	@Test(priority = 37)
 	void ExternalLawyer() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case External Lawyer verification");
 		
 		
 		MethodsPOM.ExternalLawyer(driver, test,1);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 @Test(priority = 39)
 	void Auditlog() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Audit Log verification");
 		
 		
 		MethodsPOM.Auditlog(driver,test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 
 	
	

 	
 
	@Test(priority = 40)
	void CaseHearing() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Hearing  Verification");
		
		
		MethodsPOM.CaseHearing(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 41)
	void AdvancedSearchworkspace() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Workspace- excel  verification");
		
		MethodPOM1.AdvancedSearchWorkspace(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}


	

	@Test(priority = 42)
	void MyDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Download and View Document");
		
		
		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 43)
	void AdvancedSearchDoc() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Document-Download and View Document");
		
		
		MethodsPOM.AdvancedSearchDocument(driver, test, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}


	@Test(priority = 5)
	void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("Reports -excel count verification");
		
		
		MethodsPOM.MyReports(driver, test, workbook, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 45)
	void AdvancedSearch() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Reports excel  verification");
		
		
		MethodPOM1.AdvancedSearchReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
  
 @Test(priority = 6)
	void MoreReports() throws InterruptedException, IOException
	{
		test = extent.startTest("More Report-Reports excel  verification");
		
		
		MethodsPOM.MoreReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
    
	
@Test(priority = 47)
	void MyReminder() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder verification");
		
		
		MethodsPOM.MyReminder(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 7)
	void ImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Import Utility verification");
	
		
		MethodsPOM.ImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 49)
		void CaseAdvocateBill() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			
			
			MethodsPOM.AdvocateBillTab(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
		@Test(priority = 50)
		void CaseAdvocateBill1() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			
			
			MethodsPOM.ApproverAssignmentLog(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	

  	
	
@Test(priority = 51)
	void Masters() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Legal Entity  verification");
		
		
		MethodsPOM.LegalEntity(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 52)
	void Masters1() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Law Firm verification");
		
		
		MethodsPOM.LawFirm(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 53)
	void Masters2() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - 	User  verification");
		
		MethodsPOM.User(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 54)
	void Masters3() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Opponent  verification");
		
		
		MethodsPOM.Opponent(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 55)
	void Masters4() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Court  verification");
		
		MethodsPOM.Court(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 56)
	void Masters5() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case/NoticeType  verification");
		
		
		MethodsPOM.CaseNoticeType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 57)
	void Masters6() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Payment Type  verification");
		
		
		MethodsPOM.PaymentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 58)
	void Masters7() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Custom Parameter  verification");
		
		
		MethodsPOM.customParameter(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 59)
	void Masters8() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case Stage  verification");
		
		
		MethodsPOM.CaseStage(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 60)
	void Masters9() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Document Type  verification");
		
		
		MethodsPOM.DocumentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 61)
	void Masters10() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Rating Criteria  verification");
		
		
		MethodsPOM.RatingCriteria(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 62)
	void Masters11() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - PageAuthorization   verification");
		
		
		MethodsPOM.PageAuthorization(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =63)
	void Masters12() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Advocate Bill Approver  verification");
		
		
		MethodsPOM.AdvocateBillApprover(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 64)
	void Masters13() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - UserReassignment  verification");
	
		MethodsPOM.UserReassignment(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
@Test(priority =65)
	void Masters14() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Notice Stage  verification");
		
		
		MethodsPOM.NoticeStage(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 66)
	void Masters15() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Mail Authorization  verification");
		
		
		MethodsPOM.MailAuthorization(driver,test);
		
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
	
	 @AfterMethod
	 
	 void Close()
	 {
		 driver.close(); 
	 }
	 
	
	

	

	

	

	

	 

	



    
    
	

	

}
