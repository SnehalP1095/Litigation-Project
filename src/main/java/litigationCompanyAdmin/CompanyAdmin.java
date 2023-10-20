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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
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
		test = extent.startTest("Litigation Logging In - Company Admin");
		test.log(LogStatus.PASS, "Test Passed.");
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
		Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"company");		//Method of Login class to login user.
		
	}

	
//@Test(priority = 0)
	void HearingCalender() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Hearing Calender Verification");
		
		
		MethodsPOM.HearingCalender(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority = 1)
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
				 Thread.sleep(5000);
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
	//@Test(priority =2)
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
			

//@Test(priority = 3)
	void CaseNoticeStageGraphNotice() throws InterruptedException, IOException
	{
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
 
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
  
   	
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();

		js.executeScript("window.scrollBy(0,850)");
		
		String StageName =performerPOM.StageName(driver).getText();
		test = extent.startTest("Select Notice Filter = "+StageName+" Stage = Case Notice Stage Graph Count Verification");

		MethodsPOM.CaseNoticeStageGraph(driver, test,"Notice");
		
		extent.endTest(test);
		extent.flush();
	}
// @Test(priority = 	1)
	void CaseNoticeStageGraphFilter() throws InterruptedException, IOException
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
 
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
  
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();

		js.executeScript("window.scrollBy(0,850)");
		
		String StageName =performerPOM.StageName(driver).getText();
		test = extent.startTest("Select Notice Filter = "+StageName+" = Select status filter Verification");
		
		
		MethodsPOM.CaseNoticeStageGraphFilter(driver, test,"type");
		
		extent.endTest(test);
		extent.flush();
	}
 
	
//@Test(priority =5)
	
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
 //@Test(priority =6)
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

//@Test(priority =7)
	
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
 	
		 js.executeScript("window.scrollBy(0,900)");
	
		 String DeptName =performerPOM.DepartName(driver).getText();
		 test = extent.startTest("Select Notice Filter ="+DeptName+" Department - Department Summary Graph Count Verification");
		
		 MethodsPOM.DepartmentSummaryGraph(driver, test,"Notice");
		 
		 extent.endTest(test);
		 extent.flush();
	}
//@Test(priority =8)
	
	void DepartmentSummaryGraphFilter() throws InterruptedException, IOException
	{
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");

		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();

		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();

	
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();
	
		js.executeScript("window.scrollBy(0,900)");

		String DeptName =performerPOM.DepartName(driver).getText();
		test = extent.startTest("Select Notice Filter - "+DeptName+" Department - Select status filter Verification");
		
		MethodsPOM.DepartmentSummaryGraphFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 9)
	
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
	
        js.executeScript("window.scrollBy(0,1500)");
   
	    String LocationName =performerPOM.LocationName(driver).getText();
		test = extent.startTest("Select Notice Filter = "+LocationName+" Location- Location Summary Graph Count Verification");
		
		MethodsPOM.LocationSummaryGraph(driver, test,"Notice");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 10)
	
	void LocationSummaryGraphFilter() throws InterruptedException, IOException
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");

		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();

		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();

	
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();

		js.executeScript("window.scrollBy(0,1500)");

		String LocationName =performerPOM.LocationName(driver).getText();
		test = extent.startTest("Select Notice Filter - "+LocationName+" Location -Select status filter Verification");
		
		MethodsPOM.LocationSummaryGraphFilter(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =11)
	
  	void CategorySummaryGraph() throws InterruptedException, IOException
  	{
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
	     
    	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
		Thread.sleep(2000);
        performerPOM.clickDashboardApplyBtn(driver).click();
	   
       	js.executeScript("window.scrollBy(0,2000)");
        Thread.sleep(2000);
		String CategoryName =performerPOM.CategoryName(driver).getText();
  		test = extent.startTest("Select Notice Filter ="+CategoryName+" Category - Category Summary Graph Count Verification");
  		
  		
  		MethodsPOM.CategorySummaryGraph(driver, test,"Notice");
  		
  		extent.endTest(test);
  		extent.flush();
  	}
//@Test(priority =12)
	
  	void CategorySummaryGraphFilter() throws InterruptedException, IOException
  	{
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
     
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
		Thread.sleep(5000);
		performerPOM.clickDashboardNoticeFilter(driver).click();
  
		performerPOM.clickDashboardApplyBtn(driver).click();
	
		js.executeScript("window.scrollBy(0,2000)");
		Thread.sleep(2000);
		String CategoryName =performerPOM.CategoryName(driver).getText();
  		test = extent.startTest("Select Notice Filter - "+CategoryName+" Category = Category Summary Graph -Select status filter Verification");
  		
  		
  		MethodsPOM.CategorySummaryGraphFilter(driver, test);
  		
  		extent.endTest(test);
  		extent.flush();
  	}

//@Test(priority = 13)
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
			js.executeScript("window.scrollBy(0,3700)");
			Thread.sleep(3000);
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
    
 //@Test(priority = 14)
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
  	
//@Test(priority = 15)
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
		
		js.executeScript("window.scrollBy(0,3700)");
		Thread.sleep(5000);
	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1to2(driver).getText());	//Reading Notice Open count.
    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickPetitionerCA1To2Years(driver).getText());	//Reading Notice Open count.
    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCase(driver).getText());	//Reading Notice Open count.
    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA1To2Years(driver).getText());	//Reading Notice Open count.
    	
		
    	Thread.sleep(3000);
    	MethodsPOM.AgeingGraph1to2years(driver, test,"Inward/Defendent",InwardDefendent);
		Thread.sleep(3000);
		MethodsPOM.AgeingGraph1to2years(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
		Thread.sleep(3000);
		MethodsPOM.AgeingGraph1to2years(driver, test,"Petitioner",Petitioner);
		Thread.sleep(3000);
		MethodsPOM.AgeingGraph1to2years(driver, test,"Respondent",Respondent);
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
 }
// @Test(priority = 16)
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



//@Test(priority = 17)
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
		 Thread.sleep(3000);
	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA2to3(driver).getText());	//Reading Notice Open count.
    	
    	Thread.sleep(3000);
    	MethodsPOM.AgeingGraph2to3years(driver, test,"Inward/Defendent",InwardDefendent);
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
  }	
    
//  @Test(priority = 18)
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

// @Test(priority = 19)
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
		js.executeScript("window.scrollBy(0,3800)");
		 Thread.sleep(3000);
	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendent1(driver).getText());	//Reading Notice Open count.
    	
    	Thread.sleep(3000);
    	MethodsPOM.AgeingGraphMorethan3years(driver, test,"Inward/Defendent",InwardDefendent);
	
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
    
      extent.endTest(test);
      extent.flush();
   }	
// @Test(priority = 20)
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
  	
  	

//@Test(priority =21)
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
		 Thread.sleep(5000);
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
	
	
	
//@Test(priority =22)
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
	
 
// @Test(priority =23)
	void CaseNoticeStageGraphNotice1() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter = Hearing Stage = Case Notice Stage Graph Count Verification");
		
		
		MethodsPOM.CaseNoticeStageGraph1(driver, test,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
	
 
	   
 //@Test(priority = 24)
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
//	@Test(priority = 25)
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
//@Test(priority = 26)

	void DepartmentSummaryGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest(" Select Case Filter= HR Department =Department Summary Graph Count Verification");
		
		MethodsPOM.DepartmentSummaryGraph1(driver, test,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =27)

void LocationSummaryGraph1() throws InterruptedException, IOException
{
	test = extent.startTest(" Select Case Filter = F Pvt Ltd Location =Location Summary Graph Count Verification");
	
	
	MethodsPOM.LocationSummaryGraph1(driver, test,"Case");
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 28)

	void CategorySummaryGraph1() throws InterruptedException, IOException
	{
		test = extent.startTest(" Select Case Filter = Tax Laws Category = Category Summary Graph Count Verification");
		
		
		MethodsPOM.CategorySummaryGraph1(driver, test,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =1)
    void ExpensesCaseGraph() throws InterruptedException, IOException
    {
       test = extent.startTest("Select Case Filter = Expenses Case-Wise Graph Count Verification");
      
       Thread.sleep(3000);
       MethodsPOM.ExpensesCaseGraph(driver, test,"Company admin-");

       extent.endTest(test);
       extent.flush();
    }

//@Test(priority =2)
void ExpensesCategoryWiseCaseGraph() throws InterruptedException, IOException
{
   test = extent.startTest("Select Case Filter = Cables Category -Expenses Category Wise Graph Count Verification");
  
   Thread.sleep(3000);
   MethodsPOM.ExpensesCategoryWiseCaseGraph(driver, test,"cfo -");

   extent.endTest(test);
   extent.flush();
}
//@Test(priority =1)
void ExpensesCounselWiseCaseGraph() throws InterruptedException, IOException
{
  test = extent.startTest("Select Case Filter -Expenses Counsel Wise Graph Count Verification");
 
  Thread.sleep(3000);
  MethodsPOM.ExpensesCounselWiseCaseGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
//@Test(priority =2)
void UtilizedBudgetGraph() throws InterruptedException, IOException
{
  test = extent.startTest("Select Case Filter -Utilized budget Graph Count Verification");
 
  Thread.sleep(3000);
  MethodsPOM.UtilizedBudgetGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
//@Test(priority = 29)
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
			js.executeScript("window.scrollBy(0,3700)");
			 Thread.sleep(3000);
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
    
 //@Test(priority =30)
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
	
	
//@Test(priority =31)
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
			
			js.executeScript("window.scrollBy(0,3700)");
			 Thread.sleep(3000);
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
	
 //@Test(priority =32)
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
//@Test(priority =33)
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
			
			js.executeScript("window.scrollBy(0,3850)");
			 Thread.sleep(3000);
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
	
	
//	@Test(priority =34)
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
	
//@Test(priority =35)
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
			js.executeScript("window.scrollBy(0,3800)");
			
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

//	@Test(priority =36)
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

	 
 
// @Test(priority =37)
 	void NoticeOpen() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice - Open Count Verification");
 		
 		
 		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
 		
 		test.log(LogStatus.PASS, "Test Passed.");
 		extent.endTest(test);
 		extent.flush();
 	}
//@Test(priority =38)
		void NoticeWithExistingData() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice With Existing Data verification");
		   MethodsPOM.NoticeWithExistingData(driver, test);
		  extent.endTest(test);
			extent.flush();
		}
	
//@Test(priority =39)
     void NoticeWithInvalidData() throws InterruptedException, IOException
    {
	     test = extent.startTest("Notice With Invalid Data verification");
	
	
	     MethodsPOM.NoticeWithInvalidData(driver, test, workbook);
	
	     extent.endTest(test);
	     extent.flush();
   }
//@Test(priority =40)
	   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Two Mandatory Fields verification");
		
		
		     MethodsPOM.NoticeWithTwoMandatoryData(driver, test, workbook);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	 
//@Test(priority =41) 
	   void NoticeWithEmptyFields() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Notice With Empty Fields verification");
		
		
		     MethodsPOM.NoticeWithEmptyFields(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
 
//@Test(priority =42)
 	void NoticeClearBtn() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Notice Summary -Clear button verification");
	
	
 		MethodsPOM.NoticeClearBtn(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 	}
//@Test(priority =43)
 void NoticeSendMailWithDoc() throws InterruptedException, IOException
{
	     test = extent.startTest("Notice Summary-Send Mail With Document verification");
	
	
	     MethodsPOM.NoticeSendMailWithDoc(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
}
 
//@Test(priority =44)
 void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
 {
     test = extent.startTest(" Notice Summary -Send Mail With Document Invalid Fields verification");


     MethodsPOM.NoticeSendMailWithDocInvalidFields(driver, test);

     extent.endTest(test);
     extent.flush();
 }
//@Test(priority =45)
	void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
	{
	     test = extent.startTest("Notice Summary -Send Mail With Document Empty Fields verification");
	
	
	     MethodsPOM.NoticeSendMailWithDocEmptyFields(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
	}
  
//@Test(priority =46)
  	void NoticeUserAssignment() throws InterruptedException, IOException
 	{
	     test = extent.startTest("Notice User Assignment  verification");
	
         MethodsPOM.NoticeUserAssignment(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
 	}

 
//@Test(priority =47)
void NoticeClosed() throws InterruptedException, IOException
{
	test = extent.startTest("Notice - Closed Count Verification");
	
	
	MethodsPOM.NoticeClosed(driver, test, workbook, "Performer");
	
	extent.endTest(test);
	extent.flush();
}

//@Test(priority = 48)
	void CaseOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Open Count Verification");
		
		
		MethodsPOM.CaseOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}

// @Test(priority =49)
	void CaseExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Existing Data verification");
		
		
		MethodsPOM.CaseExistingData(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
 
//@Test(priority =50)
	void CaseWithInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Invalid Data verification");
		
		
		MethodsPOM.CaseWithInvalidData(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =51)
	void CaseWithTwoFieldsData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Two Manadatory fields verification");
		
		
		MethodsPOM.CaseWithTwoFieldsData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =52)
	void CaseWithEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Empty fields verification");
		
		
		MethodsPOM.CaseWithEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =53)
	void CaseWithClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Summary - Clear button verification");
		
		
		MethodsPOM.CaseWithClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =54)
	void CaseClose() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Closed Count Verification");
		
		MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority = 55)
	void TaskOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Open Count Verification");
		
		
		MethodsPOM.TaskOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 56)
		void TaskwithExistingData() throws InterruptedException, IOException
		{
			test = extent.startTest("Task With existing data verification");
			
			
			MethodsPOM.TaskWithExistingData(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
// @Test(priority =57)
		void TaskwithTwoManadatoryFields() throws InterruptedException, IOException
		{
			test = extent.startTest("Task With Two manadatory fields verification");
			
			
			MethodsPOM.TaskWithTwoMandatoryFields(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
	 
//  @Test(priority =58)
				void TaskwithoutData() throws InterruptedException, IOException
				{
					test = extent.startTest("Task Without  data verification");
					
					
					MethodsPOM.TaskwithoutData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
 // @Test(priority =59)
				void TaskwithClearBtn() throws InterruptedException, IOException
				{
					test = extent.startTest("Task Clear button verification");
					
					
					MethodsPOM.TaskwithClearBtn(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
	   
 //  @Test(priority = 60)
		void TaskDelete() throws InterruptedException, IOException
		{
			test = extent.startTest("Task Delete verification");
			
			
			MethodsPOM.TaskDelete(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
//@Test(priority = 61)
	void TaskClosed() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Closed Count Verification");
		
		
		MethodsPOM.TaskClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 62)
void LinkNotice() throws InterruptedException, IOException
{
	test = extent.startTest("Link Notice Verification");
	
	
	MethodsPOM.LinkDocument(driver, test, workbook, "Notice");
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority =63)
	void LinkNoticeViewIcon() throws InterruptedException, IOException
	{
	     test = extent.startTest("Linked notice view icon  verification");
	
	

	 	MethodsPOM.LinkNoticeViewIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
	}
//@Test(priority =64)
  void LinkNoticeDeleteIcon() throws InterruptedException, IOException
	{
	     test = extent.startTest("Linked notice Delete icon  verification");
	
	

	 	MethodsPOM.LinkNoticeDeleteIcon(driver, test);
	
	     extent.endTest(test);
	     extent.flush();
	}
//@Test(priority = 65)
	void LinkCase() throws InterruptedException, IOException
	{
	test = extent.startTest("Link Case Verification");
    MethodsPOM.LinkDocument(driver, test, workbook, "Case");
    extent.endTest(test);
	extent.flush();
   }
//@Test(priority =66)
	   void LinkCaseViewIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case view icon  verification");
		
		
		     MethodsPOM.LinkCaseViewIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
//@Test(priority =67)
	   void LinkCaseDeleteIcon() throws InterruptedException, IOException
	  {
		     test = extent.startTest("Linked case delete icon  verification");
		
		
		     MethodsPOM.LinkCaseDeleteIcon(driver, test);
		
		     extent.endTest(test);
		     extent.flush();
	 }
	
//@Test(priority =68)
	void CloseNotice() throws InterruptedException, IOException
	{
	test = extent.startTest("Close Notice Count Verification");
	MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
	extent.endTest(test);
	extent.flush();
	}
//@Test(priority = 69)
	void CloseCase() throws InterruptedException, IOException
	{
	test = extent.startTest("Close Case Count Verification");
		
		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
		
	extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority = 70)
	 	void NoticeDocumentTab() throws InterruptedException, IOException
	 	{
	 		test = extent.startTest("Notice Document verification");
	 		
	 		
	 		MethodsPOM.NoticeDocument(driver, test);
	 		
	 		extent.endTest(test);
	 		extent.flush();
	 	}
	
//@Test(priority = 71)
	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Without Upload Document verification");
		
		
		MethodsPOM.NoticeWithoutUploadDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 72)
	void NoticeDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Search Fields verification");
		
		
		MethodsPOM.NoticeDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 73)
	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share with Invaid data verification");
		
		
		MethodsPOM.NoticeDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =74)
		void NoticeDocumentShareWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Notice Document Share without data verification");
			
			
			MethodsPOM.NoticeDocumentShareWithoutData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	
//	@Test(priority = 75)
	void NoticeDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Document Share close button verification");
		
		
		MethodsPOM.NoticeDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =76)
	void NoticeTaskActivityTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activity verification");
		
		
		MethodsPOM.TaskActivtity(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =77)
	void TaskActivtityDeleteResponse() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Delete Response verification");
		
		
		MethodsPOM.TaskActivtityDeleteResponse(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =78)
	void TaskActivtityExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy with existing data verification");
		
		
		MethodsPOM.TaskActivtityExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority = 79)
	void TaskActivtityWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Without data verification");
		
		
		MethodsPOM.TaskActivtityWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority = 80)
	void TaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy Response Without data verification");
		
		
		MethodsPOM.TaskActivtityResponseWithoutStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority = 81)
	void TaskActivtityResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Task/Activtiy  Response clear button verification");
		
		
		MethodsPOM.TaskActivtityResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}


//@Test(priority = 82)
	void NoticeResponseTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response tab verification");
		
		
		MethodsPOM.Response(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =83)
	void ResponseExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Existing Data verification");
	
	
		MethodsPOM.ResponseExistingData(driver, test,workbook);
	
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =84)
	void NoticeResponseWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Without data verification");

		MethodsPOM.ResponseWithoutData(driver, test);
	    extent.endTest(test);
		extent.flush();
}	

//@Test(priority =85)
	void ResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Response Clear button verification");
		MethodsPOM.ResponseClearBtn(driver, test);
	     extent.endTest(test);
	     extent.flush();
  }
//@Test(priority = 86)
	void NoticePaymentLogTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice PaymentLog tab verification");
		
		
		MethodsPOM.PaymentLog(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority = 87)
	void PaymentLogwithExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment with existing data verification");
		MethodsPOM.PaymentLogExistingData(driver,test);
	   extent.endTest(test);
	    extent.flush();
	}


//@Test(priority = 88)
	void NoticePaymentWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Payment Without data verification");
	
	
		MethodsPOM.PaymentLogWithoutData(driver,test,workbook);
	
		extent.endTest(test);
		extent.flush();
}
//@Test(priority = 89)
	void NoticeExternalLawyerTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice ExternalLawyerRating tab verification");
		
		
		MethodsPOM.ExternalLawyerRating(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =90)
	void CriteriaInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Invalid Data verification");
		
		MethodsPOM.CriteriaInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority = 91)
	void CriteriaExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Existing Data verification");
		
		MethodsPOM.CriteriaExistingData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =92)
	void CriteriaWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice Criteria Without Data verification");
		
		MethodsPOM.CriteriaWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 93)
	void NoticeAuditLogTab() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice AuditLog tab verification");
		
		
		MethodsPOM.AuditLog(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 94)
void NoticeDocViewandDownload() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Document verification");
	
	
	MethodsPOM.NoticeDocViewandDownload(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

//@Test(priority = 95)
 	void CaseDocumentTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Document verification");
 		
 		
 		MethodsPOM.Document(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
//@Test(priority = 96)
 	void CaseWithoutUploadDocument() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Document -Without Upload File verification");
 		
 		
 		MethodsPOM.CaseWithoutUploadDocument(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
	
//@Test(priority =97)
	void CaseDocumentEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document with empty fields verification");
		
		
		MethodsPOM.CaseDocumentEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =98)
	void CaseDocumentSearchFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Search Fields verification");
		
		
		MethodsPOM.CaseDocumentSearchFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =99)
	void CaseDocumentShareInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share with Invaid data verification");
		
		
		MethodsPOM.CaseDocumentShareInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =100)
	void CaseDocumentShareWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share without data verification");
		
		
		MethodsPOM.CaseDocumentShareWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 101)
	void CaseDocumentShareCloseBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Document Share close button verification");
		
		
		MethodsPOM.CaseDocumentShareCloseBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =102)
    void CaseSendMailWithDoc() throws InterruptedException, IOException
   {
 	     test = extent.startTest("Case Summary-Send Mail With Document verification");
 	
 	    MethodsPOM.CaseSendMailWithDoc(driver, test);
 	
 	     extent.endTest(test);
 	     extent.flush();
  }
// @Test(priority =103)
	    void CaseSendMailWithDocInvalidFields() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case  Summary-Send Mail With Document Invalid Fields verification");
	 	
	 	
	 	    MethodsPOM.CaseSendMailWithDocInvalidFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
//@Test(priority =104)
	    void CaseSendMailWithEmptyFields() throws InterruptedException, IOException
	   {
	 	     test = extent.startTest("Case  Summary-Send Mail With Empty Fields verification");
	 	
	 	
	 	    MethodsPOM.CaseSendMailWithEmptyFields(driver, test);
	 	
	 	     extent.endTest(test);
	 	     extent.flush();
	  }
//@Test(priority = 105)
 	void CaseTaskActivityTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Task/Activity verification");
 		
 		
 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
//@Test(priority = 106)
	void CaseTaskActivityWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Task/Activtiy Without data verification");
		
		
		MethodsPOM.CaseTaskActivityWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 
//@Test(priority =107)
	void CaseTaskActivitywithExistingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Task/Activty with existing data");
		
		
		MethodsPOM.CaseTaskActivitywithExistingData(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}
 
//@Test(priority = 107)
	void CaseTaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Task/Activtiy Response Without data verification");
		
		
		MethodsPOM.CaseTaskActivtityResponseWithoutStatus(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =108)
	void CaseTaskActivtityResponseClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Task/Activtiy  Response clear button verification");
		
		
		MethodsPOM.CaseTaskActivtityResponseClearBtn(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 109)
 	void CaseHearingTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Hearing verification");
 		
 		
 		MethodsPOM.CaseHearing(driver, test,workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
//@Test(priority =200)
	void CaseExistingHearingData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case with Existing Hearing Date Verification");
		
		
		MethodsPOM.CaseExistingHearingData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 	
// @Test(priority= 201)
 	  void CaseWithoutHearingData() throws InterruptedException, IOException
 	  {
 		test = extent.startTest("Case without hearing data Verification");
 		
 		
 		MethodsPOM.CaseHearingWithoutData(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	  }
 	 
//@Test(priority =203)
 	   void CaseHearingInvalidDate() throws InterruptedException, IOException
 	   {
 	 	test = extent.startTest("Case Invalid Hearing Date Verification");
 	 	
 	 	
 	 	MethodsPOM.CaseHearingInvalidDate(driver, test);
 	 	
 	 	extent.endTest(test);
 	 	extent.flush();
 	   }
//@Test(priority =204)
 	   void CaseHearingClearBtn() throws InterruptedException, IOException
 	   {
 	 	test = extent.startTest("Case heraing clear button Verification");
 	 	
 	 	
 	 	MethodsPOM.CaseHearingClearBtn(driver, test);
 	 	
 	 	extent.endTest(test);
 	 	extent.flush();
 	   }
//@Test(priority = 205)
 	void CaseOrderTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Order verification");
 		
 		
 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
 		
 		extent.endTest(test);
 		extent.flush();
 	}
//@Test(priority =205)
 		void CaseOrderExistingData() throws InterruptedException, IOException
 		{
 			test = extent.startTest("To check validation message displayed  for case order with existing data");

 		
 			MethodsPOM.CaseOrderWithExistingData(driver, test,workbook);
 		
 			extent.endTest(test);
 			extent.flush();
 		}
//@Test(priority =206)
		void CaseOrderWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("To check validation message displayed  for case order without data");

		
			MethodsPOM.CaseOrderWithoutData(driver, test);
		
			extent.endTest(test);
			extent.flush();
		}
//	@Test(priority =207)
	void CaseOrderwithClearBtn() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Order with clear button");

	
		MethodsPOM.CaseOrderwithClearBtn(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority = 208)
 	void CaseAdvocateBillTab() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case advocate bill verification");
 	
 		
 		MethodsPOM.AdvocateBill(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
//@Test(priority = 209)
 	void StatusPayment() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Status/Payment verification");
 	
 		
 		MethodsPOM.StatusPayment(driver, test,workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
//@Test(priority = 210)
 	void StatusPaymentWithExistingData() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Status/Payment with existing data verification");
 	
 		
 		MethodsPOM.StatusPaymentWithExistingData(driver, test,workbook);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
//@Test(priority =211)
	void StatusPaymentWithoutdata() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status/Payment without data ");
		
		
		MethodsPOM.StatusPaymentWithoutdata(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
 	
 //@Test(priority =7)
	void CaseStatusAppealtoNextCourt() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status Appeal to Next Court");
		
		
		MethodsPOM.CaseStatusAppealtoNextCourt(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority =213)
	void CaseStatuswithEmptyFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Status With Empty Fields");
		
		
		CFOMethod.CaseStatuswithEmptyFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 214)
 	void ExternalLawyer() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case External Lawyer verification");
 		
 		
 		MethodsPOM.ExternalLawyer(driver, test,1);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
//@Test(priority =215)
 		void CaseExternalLawyerCriteria() throws InterruptedException, IOException
 		{
 			test = extent.startTest("Case - External Lawyer Rating -Add New Criteria ");
 			
 			
 			MethodsPOM.CaseExternalLawyerCriteria(driver, test);
 			
 			extent.endTest(test);
 			extent.flush();
 		}
 	
 //@Test(priority = 216)
 	void CaseExistingCriteria() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Criteria Existing Data verification");
 		
 		MethodsPOM.CaseExistingCriteria(driver, test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 	
//	@Test(priority =217)
 		void CaseCriteriaInvalidData() throws InterruptedException, IOException
 		{
 			test = extent.startTest("Case Criteria Invalid Data verification");
 			
 			MethodsPOM.CaseCriteriaInvalidData(driver, test);
 			
 			extent.endTest(test);
 			extent.flush();
 		}
 	
//	@Test(priority =218)
 		void CaseCriteriaWithoutData() throws InterruptedException, IOException
 		{
 			test = extent.startTest("Case Criteria Without Data verification");
 			
 			MethodsPOM.CaseCriteriaWithoutData(driver, test);
 			
 			extent.endTest(test);
 			extent.flush();
 		}
// @Test(priority = 219)
 	void Auditlog() throws InterruptedException, IOException
 	{
 		test = extent.startTest("Case Audit Log verification");
 		
 		
 		MethodsPOM.Auditlog(driver,test);
 		
 		extent.endTest(test);
 		extent.flush();
 	}
 
 	
	

 	
 
//	@Test(priority = 220)
	void CaseHearing() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Hearing  Verification");
		
		
		MethodsPOM.CaseHearing(driver, test,"Performer","Company admin");
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 221)
	void AdvancedSearchworkspace() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Workspace- excel  verification");
		
		MethodPOM1.AdvancedSearchWorkspace(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 222)
	void MyDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Download and View Document");
		
		
		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 223)
	void ShareCaseDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Share Case Document Verification");
	
		
		MethodsPOM.ShareCaseDocument(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
//@Test(priority = 224)
		void ShareNoticeDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("My Document-Share Notice Document Verification");
		
			MethodsPOM.ShareNoticeDocument(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
//@Test(priority =225)
		void ShareTaskDocument() throws InterruptedException, IOException
		{
			test = extent.startTest("My Document-Share Task Document Verification");
		
			
			MethodsPOM.ShareTaskDocument(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
	
//@Test(priority = 226)
	void AdvancedSearchDoc() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Document-Download and View Document");
		
		
		MethodsPOM.AdvancedSearchDocument(driver, test, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
//@Test(priority = 227)
	void AdvancedSearchShareCaseDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Advance search-Share Case Document Verification");
	
		
		MethodsPOM.AdvancedSearchShareCaseDocument(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority =228)
			void AdvancedSearchShareNoticeDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Advance search-Share Notice Document Verification");
			
				
				MethodsPOM.AdvancedSearchShareNoticeDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
//@Test(priority =229)
			void AdvancedSearchShareTaskDocument() throws InterruptedException, IOException
			{
				test = extent.startTest("My Document-Advance search-Share Task Document Verification");
			
				
				MethodsPOM.AdvancedSearchShareTaskDocument(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
//@Test(priority = 230) 		//Sever is blocking and not allowing to upload the file.
			void CriticalDocuments() throws InterruptedException, IOException
			{
				test = extent.startTest(" Critical Document Verification");
				
				MethodsPOM.CriticalDocuments(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
			
//	@Test(priority = 231) 		//Sever is blocking and not allowing to upload the file.
			void CriticalDocuments1() throws InterruptedException, IOException
			{
				test = extent.startTest(" Critical Document Verification");
				
				MethodsPOM.CriticalDocuments1(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}

//	@Test(priority = 232)
	void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("Reports -excel count verification");
		
		
		MethodsPOM.MyReports(driver, test, workbook, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
//	@Test(priority = 233)
	void AdvancedSearch() throws InterruptedException, IOException
	{
		test = extent.startTest("Advanced Search Reports excel  verification");
		
		
		MethodPOM1.AdvancedSearchReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
  
//@Test(priority = 234)
	void MoreReports() throws InterruptedException, IOException
	{
		test = extent.startTest("More Report-Reports excel  verification");
		
		
		MethodsPOM.MoreReport(driver, test, "Company Admin");
		
		extent.endTest(test);
		extent.flush();
	}
    
	
//@Test(priority = 235)
	void MyReminder() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder verification");
		
		
		MethodsPOM.MyReminder(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority =236)
void ReminderWithoutData() throws InterruptedException, IOException
{
	test = extent.startTest("My Reminder Without data verification");
	
	MethodsPOM.ReminderWithoutData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 237)
	void ImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Import Utility verification");
	
		
		MethodsPOM.ImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}

//@Test(priority = 237)
void ImportUtilityWithoutData() throws InterruptedException, IOException
{
	test = extent.startTest("Upload Empty File Import Utility verification");
	
	
	MethodsPOM.ImportUtilityWithoutData(driver,test);
	extent.endTest(test);
	extent.flush();
}

//@Test(priority = 238)
void ImportUtilityInvalidData() throws InterruptedException, IOException
{
	test = extent.startTest("Enter Invalid data in Upload File Import Utility verification");
	
	
	MethodsPOM.ImportUtilityInvalidData(driver,test);
	extent.endTest(test);
	extent.flush();
}

//@Test(priority = 239)
void ImportUtilityTwoManadtoryFileds() throws InterruptedException, IOException
{
	test = extent.startTest("Enter Two Manadtory fields in Upload File Import Utility verification");
	
	
	MethodsPOM.ImportUtilityTwoManadtoryFileds(driver,test);
	extent.endTest(test);
	extent.flush();
}
	
//@Test(priority = 240)
		void CaseAdvocateBill() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			
			
			MethodsPOM.AdvocateBillTab(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	//	@Test(priority = 241)
		void CaseAdvocateBill1() throws InterruptedException, IOException
		{
			test = extent.startTest("Advocate bill verification");
			
			
			MethodsPOM.ApproverAssignmentLog(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	

@Test(priority = 242)
	void Masters() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Legal Entity  verification");
		
		
		MethodsPOM.LegalEntity(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 243)
void MastersLegalEntity() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Legal Entity Without data verification");
		
		MethodsPOM.LegalEntityWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =244)
void MastersLegalEntity1() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Legal Entity Invalid data verification");
		
		MethodsPOM.LegalEntityInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =245)
void MastersLegalEntity2() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Legal Entity Two Manadatory Fields verification");
		
		MethodsPOM.LegalEntityTwoManadatoryFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =246)
		void MastersLegalEntity3() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Legal Entity Close Button verification");
				
				MethodsPOM.LegalEntityCloseButton(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}

@Test(priority =247)
void UnitEntity() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Add Unit Entity verification");
		
		MethodsPOM.AddUnitType(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority = 248)
	void Masters1() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Law Firm verification");
		
		
		MethodsPOM.LawFirm(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =249)
void MastersLawFirm() throws InterruptedException, IOException
{
	test = extent.startTest("Law Firm Masters - Enter Without Data verification");
	
	
	MethodsPOM.LawFirmWithoutData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =250)
void MastersLawFirm1() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Law Firm Invalid Data verification");
	
	
	MethodsPOM.LawFirmInvalidData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =251)
void MastersLawFirm2() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Law Firm Two Manadtory fields verification");
	
	
	MethodsPOM.LawFirmTwoManadatoryFields(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =252)
void MastersLawFirm3() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Law Firm Close button verification");
	
	
	MethodsPOM.LawFirmCloseButton(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =253)
void LawyerWithoutData() throws InterruptedException, IOException
{
	test = extent.startTest("Lawyer  - Enter Without Data verification");
	
	
	MethodsPOM.LawyerWithoutData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =254)
void LawyerInvalidData() throws InterruptedException, IOException
{
	test = extent.startTest("Lawyer  - Enter Invalid Data verification");
	
	
	MethodsPOM.LawyerInvalidData(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 255)
void LawyerTwoManadatoryFileds() throws InterruptedException, IOException
{
	test = extent.startTest("Lawyer  - Enter Two Manadatory fields verification");
	
	
	MethodsPOM.LawyerTwoManadatoryFileds(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 256)
void LawyerCloseButton() throws InterruptedException, IOException
{
	test = extent.startTest("Lawyer  - Enter close button verification");
	
	
	MethodsPOM.LawyerCloseButton(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
	@Test(priority = 257)
	void Masters2() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - 	User  verification");
		
		MethodsPOM.User(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 258)
	void UserWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master-  without data verification");
		
		
		MethodsPOM.UserWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 259)
	void UserInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master-  Invalid data verification");
		
		
		MethodsPOM.UserInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =260)
	void UserTwoManadatoryFields() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master- Two manadatory fields verification");
		
		
		MethodsPOM.UserTwoManadatoryFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =261)
	void UserCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("User Master- Close Button  verification");
		
		
		MethodsPOM.UserCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 262)
	void Masters3() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Opponent  verification");
		
		
		MethodsPOM.Opponent(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority =263)
	void OpponentWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Opponent Masters -Without Data verification");
	
		MethodsPOM.OpponentWithoutData(driver, test);
	   extent.endTest(test);
	  extent.flush();
   }
  @Test(priority =264)
  void OpponentInvalidData() throws InterruptedException, IOException
  {
	  test = extent.startTest("Opponent Masters -Invalid Data verification");


	  MethodsPOM.OpponentInvalidData(driver, test);

	  extent.endTest(test);
	  extent.flush();
  }
   @Test(priority = 265)
   void OpponentCloseButton() throws InterruptedException, IOException
   {
	   test = extent.startTest("Opponent Masters -Close button verification");

	   MethodsPOM.OpponentCloseButton(driver, test);

	   extent.endTest(test);
	   extent.flush();
   }
 @Test(priority = 266)
	void Masters4() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Court  verification");
		
		MethodsPOM.Court(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
 
 
 @Test(priority =267)
	void CourtWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Without enter Data verification");
		
		
		MethodsPOM.CourtWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 268)
	void CourtInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Enter Invalid Data verification");
		
		
		MethodsPOM.CourtInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 269)
	void CourtTwomanadatoryFields() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Enter Two Manadtory Fields verification");
		
		
		MethodsPOM.CourtTwomanadatoryFields(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =270)
	void CourtCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Court Master- Close button verification");
		
		
		MethodsPOM.CourtCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	

	
	@Test(priority = 271)
	void Masters5() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case/NoticeType  verification");
		
		
		MethodsPOM.CaseNoticeType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 272)
	void CaseNoticeTypeWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case/NoticeType Master -Without Enter Data  verification");

		MethodsPOM.CaseNoticeTypeWithoutData(driver, test);
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 273)
	void CaseNoticeTypeInvaliData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case/NoticeType Master - Enter Invalid Data  verification");
		MethodsPOM.CaseNoticeTypeInvaliData(driver, test);
        extent.endTest(test);
        extent.flush();
    }
	@Test(priority = 274)
	void CaseNoticeTypeCloseBuuton() throws InterruptedException, IOException
	{
		test = extent.startTest("Case/NoticeType Master - Close Button  verification");
		MethodsPOM.CaseNoticeTypeCloseButton(driver, test);
        extent.endTest(test);
        extent.flush();
	}
	@Test(priority = 275)
	void Masters6() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Payment Type  verification");
		
		
		MethodsPOM.PaymentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 276)
    void PaymentTypeWithouData() throws InterruptedException, IOException
   {
       test = extent.startTest("Payment Type Master- Without Enter Data  verification");


       MethodsPOM.PaymentTypeWithoutData(driver, test);

        extent.endTest(test);
        extent.flush();
   }
@Test(priority = 277)
void PaymentTypeInvalidData() throws InterruptedException, IOException
{
 test = extent.startTest("Payment Type Master-Enter Invalid Data  verification");


 MethodsPOM.PaymentTypeInvalidData(driver, test);

  extent.endTest(test);
  extent.flush();
}
@Test(priority = 278)
void PaymentTypeCloseButton() throws InterruptedException, IOException
{
 test = extent.startTest("Payment Type Master-Close button verification");


 MethodsPOM.PaymentTypeCloseButton(driver, test);

  extent.endTest(test);
  extent.flush();
}
	
@Test(priority = 279)
	void Masters7() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Custom Parameter  verification");
		
		
		MethodsPOM.customParameter(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 280)
	void customParameterWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Custom Parameter Master -Without Enter Data  verification");

	
		CFOMethod.customParameterWithoutData(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}
     @Ignore
	@Test(priority = 281)
	void customParameterInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Custom Parameter Master -Enter Invalid Data verification");

	
		CFOMethod.customParameterInvalidData(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 282)
	void customParameterCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Custom Parameter Master -Close  button  verification");

	
		CFOMethod.customParameterCloseButton(driver, test);
	
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 283)
	void Masters8() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case Stage  verification");
		
		
		MethodsPOM.CaseStage(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority =284)
	void CaseStageWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Stage Masters - Without Enter Data  verification");
	
		
		MethodsPOM.CaseStageWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 285)
	void CaseStageInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Stage Masters - Enter Invalid Data  verification");
	
		
		MethodsPOM.CaseStageInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 286)
	void CaseStageCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Stage Masters - Close Button verification");
	
		
		MethodsPOM.CaseStageCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 287)
	void Masters9() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Document Type  verification");
		
		
		MethodsPOM.DocumentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 288)
	void DocumentTypeWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Document Type Masters-Without data  verification");
		
		
		MethodsPOM.DocumentTypeWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =289)
	void DocumentTypeInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Document Type Masters-Enter Invalid Data verification");
		
		
		MethodsPOM.DocumentTypeInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 290)
	void DocumentTypeCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Document Type Masters-Close button verification");
		
		MethodsPOM.DocumentTypeCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 291)
	void Masters10() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Rating Criteria  verification");
		
		
		MethodsPOM.RatingCriteria(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 292)
	void RatingCriteriaWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Rating Criteria Masters-Without Enter Data  verification");
	
		
		MethodsPOM.RatingCriteriaWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 293)
	void RatingCriteriaInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Rating Criteria Masters-Enter Invalid Data  verification");
	
		
		MethodsPOM.RatingCriteriaInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}		
	@Test(priority =294)
	void RatingCriteriaCloseButton() throws InterruptedException, IOException
	{
		test = extent.startTest("Rating Criteria Master-Close button verification");
	
		
		MethodsPOM.RatingCriteriaCloseButton(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}		
	@Test(priority = 295)
	void Masters11() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - PageAuthorization   verification");
		
		
		MethodsPOM.PageAuthorization(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 1)
	void AnnualBudget() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Annual Budget verification");
		
		
		MethodsPOM.AnnualBudget(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
//@Test(priority = 2)
void ExistingAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Existing Annual Budget verification");
	
	
	MethodsPOM.AnnualBudget(driver, test, workbook);
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 2)
void UpdateAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Update Annual Budget verification");
	
	
	MethodsPOM.UpdateAnnualBudget(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 2)
void DeleteAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Delete Annual Budget verification");
	
	
	MethodsPOM.DeleteAnnualBudget(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 1)
void WithoutEnterFY() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Without Enter FY verification");
	
	
	MethodsPOM.WithoutEnterFY(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
//@Test(priority = 3)
void SearchFilterAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Search Filter - Annual Budget verification");
	
	
	MethodsPOM.SearchFilterAnnualBudget(driver, test,"2022-2023");
	
	extent.endTest(test);
	extent.flush();
}
	@Test(priority =296)
	void Masters12() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Advocate Bill Approver  verification");
		
		
		MethodsPOM.AdvocateBillApprover(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 297)
	void Masters13() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - UserReassignment  verification");
	
		MethodsPOM.UserReassignment(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
@Test(priority =298)
	void Masters14() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Notice Stage  verification");
		
		
		MethodsPOM.NoticeStage(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 299)
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
@Test(priority = 1)
		void WorkspaceFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("My Workspace - Multiple Filters verification");
			
			
			MethodPOM1.WorkspaceFilter(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
 @Test(priority = 301)
		void DocumentFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("My Document Tab - Multiple Filters verification");
			
			
			MethodPOM1.DocumentFilter(driver, test, "Company Admin");
			
			extent.endTest(test);
			extent.flush();
		}
 @Test(priority = 302)
		void ReportFilter() throws InterruptedException, IOException
		{
			test = extent.startTest("My Report - Multiple Filters verification");
			
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
