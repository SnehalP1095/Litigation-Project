package CriticalTestCases;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.performerPOM;
import litigationManagement.CFOMethod;
import performer.OverduePOM;

public class CFOlogin 
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
	

 @Test(priority =1)
   	void NoticeOpen() throws InterruptedException, IOException
   	{
   		test = extent.startTest("Notice - Open Count verification");
   		
   		
   		CFOMethod.NoticeOpen(driver, test, workbook, "CFO -");
   		
   		extent.endTest(test);
   		extent.flush();

   	}

@Test(priority =2)
    	void CaseOpen() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Open Count verification");
    		
    		
    		CFOMethod.CaseOpen(driver, test, workbook, "CFO -");
    		
    		extent.endTest(test);
    		extent.flush();
    	}

@Test(priority =3)
    			void TaskOpen() throws InterruptedException, IOException
    			{
    				test = extent.startTest("Task - Open Count verification");
    				
    				
    				CFOMethod.TaskOpen(driver, test, workbook, "CFO");
    				
    				extent.endTest(test);
    				extent.flush();
    			}
  
			
		 @Test(priority = 4)
			void TaskDelete() throws InterruptedException, IOException
			{
				test = extent.startTest("Task Delete verification");
				
				
				CFOMethod.TaskDelete(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
   	
 @Test(priority = 5)
    	void NoticeClosed() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice - Closed Count verification");
    		
    		
    		CFOMethod.NoticeClosed(driver, test, workbook, "Company Admin");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority = 6)
    	void CaseClose() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Case - Closed Count verification");
    		
    		
    		CFOMethod.CaseClosed(driver, test, workbook);
    		
    		extent.endTest(test);
    		extent.flush();
    	}
	

	 	
	   
@Test(priority = 7)
    	void CloseNotice() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Close Notice Count verification");
    		
    		CFOMethod.CloseNoticeCase(driver, test, workbook,"Notice");
    		
    		extent.endTest(test);
    		extent.flush();
    	}
@Test(priority = 8)
			void CloseCase() throws InterruptedException, IOException
			{
			test = extent.startTest("Close Case Count Verification");
				
				
				CFOMethod.CloseNoticeCase(driver, test, workbook,"Case");
				
			extent.endTest(test);
				extent.flush();
			}
	  
@Test(priority = 9)
			void TaskClosed() throws InterruptedException, IOException
			{
				test = extent.startTest("Task - Closed Count verification");
				
				
				CFOMethod.TaskClosed(driver, test, workbook, "CFO");
				
				extent.endTest(test);
				extent.flush();
			}
@Test(priority = 10)
void ClosedTask() throws InterruptedException, IOException
{
	test = extent.startTest(" Closed Task Count verification");
	
	
	CFOMethod.CloseNoticeCase(driver, test, workbook, "Task");
	
	extent.endTest(test);
	extent.flush();
}

	@Test(priority = 11)
    	void NoticeDocument() throws InterruptedException, IOException
    	{
    		test = extent.startTest("Notice Document verification");
    		
    		
    		CFOMethod.NoticeDocument(driver, test);
    		
    		extent.endTest(test);
    		extent.flush();

    	}
 
	@Test(priority =12)
	void NoticeTaskActivity() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice TaskActivtiy verification");
		
		
		CFOMethod.TaskActivtity(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =13)
void NoticeResponse() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Response verification");
	
	
	CFOMethod.Response(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =14)
void NoticePayment() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Payment verification");
	
	
	CFOMethod.PaymentLog(driver,test,workbook);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 15)
void NoticeExternalLawyer() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Lawyer verification");
	
	CFOMethod.ExternalLawyer(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority = 16)
void NoticeAuditLog() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Audit Log verification");

	
	CFOMethod.AuditLog(driver, test);
	
	extent.endTest(test);
	extent.flush();
}		
@Test(priority =17)
void CaseDocument() throws InterruptedException, IOException
{
	test = extent.startTest("Case - Document Tab");
	
	
	CFOMethod.Document(driver, test);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =18)
void CaseTaskActivity() throws InterruptedException, IOException
{
	test = extent.startTest("Case - Task/Activty Tab");
	
	
	CFOMethod.TaskActivity1(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}


@Test(priority =19)
	void CaseHearingcfo() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - CaseHearing Tab");
		
		
		CFOMethod.CaseHearing(driver, test,workbook);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority =20)
void CaseOrder() throws InterruptedException, IOException
{
	test = extent.startTest("Case - Case Order Tab");

	
	CFOMethod.CaseOrder(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}

@Test(priority =21)
void CaseStatusPayment() throws InterruptedException, IOException
{
	test = extent.startTest("Case - Status/Payment Tab");
	
	
	CFOMethod.StatusPayment(driver, test,workbook);
	
	extent.endTest(test);
	extent.flush();
}

	@Test(priority =22)
	void CaseExternalLawyerRating() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - External Lawyer Rating");
		
		
		CFOMethod.CaseExternalLawyer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =23)
	void CaseAuditLog() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Audit Log Tab");
		
		
		CFOMethod.Auditlog(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 23)
void CaseHearing() throws InterruptedException, IOException
{
	test = extent.startTest("Case Hearing Count Verification");


	CFOMethod.CaseHearing(driver, test,"Performer","Case Hearing-");

	extent.endTest(test);
	extent.flush();
}
@Test(priority = 23)
void HearingCalender() throws InterruptedException, IOException, AWTException
{
	test = extent.startTest("Hearing Calender verification");

	
	CFOMethod.HearingCalender(driver, test,"Performer","Cfo");
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 24)
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


@Test(priority = 25)
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
		test = extent.startTest("Select Notice Filter = "+StageName+"= Case Notice Stage Graph Count Verification");
		
		CFOMethod.CaseNoticeStageGraph(driver, test,"cfo -");
		
		extent.endTest(test);
		extent.flush();
	}
	


@Test(priority =26)
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

@Test(priority = 27)
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

@Test(priority = 28)
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


@Test(priority = 29)
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





@Test(priority = 30)
void InwardDefendantAgeingGraph() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter =Less than a year = Inward/Defendant Type = Ageing Graph Count Verification");
 

  Thread.sleep(3000);
  CFOMethod.InwardDefendantAgeingGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}



@Test(priority = 31)
void ComplainantAgeingGraph() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = Less than a year = Complainant Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.ComplainantAgeingGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority = 32)
void ApplicantAgeingGraph() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = Less than a year = Applicant Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.ApplicantAgeingGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}

@Test(priority = 33)
void OutwardPlaintiffAgeingGraph() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = Less than a year = Outward/Plaintiff Type= Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.OutwardPlaintiffAgeingGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority = 34)
void PetitionerAgeingGraph() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = Less than a year  =Petitioner Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.PetitionerAgeingGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority = 35)
void RespondentAgeingGraph() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = Less than a year  =Respondent Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.RespondentAgeingGraph(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority =36)
void ComplainantAgeingGraph1to2years() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = 1 to 2 Years = Complainant Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.ComplainantAgeingGraph1to2years(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority = 37)
void InwardDefendentAgeingGraph1to2years() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = 1 to 2 Years = Inward/Defendent Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.InwardDefendentAgeingGraph1to2years(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority = 38)
void OutwardPlaintiffAgeingGraph1to2years() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = 1 to 2 Years =Outward/Plaintiff Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.OutwardPlaintiffAgeingGraph1to2years(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}
@Test(priority =39)
void RespondentAgeingGraph1to2yearsAgeingGraph1to2years() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = 1 to 2 Years =Respondent Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.RespondentAgeingGraph1to2years(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}


@Test(priority = 40)
void InwardDefendentAgeingGraph2to3years() throws InterruptedException, IOException
{
 test = extent.startTest("Select Notice Filter = 2 to 3 Years =Inward/Defendent Type = Ageing Graph Count Verification");

  Thread.sleep(3000);
  CFOMethod.InwardDefendentAgeingGraph2to3years(driver, test,"cfo -");

  extent.endTest(test);
  extent.flush();
}




	@Test(priority =41)
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
	
	@Test(priority = 42)
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
	   String StageName =performerPOM.CaseStageName(driver).getText();
		test = extent.startTest("Select Case Filter = "+StageName+" Stage = Case Notice Stage Graph Count Verification");
		
		Thread.sleep(3000);
		CFOMethod.CaseNoticeStageGraph1(driver, test,"cfo -");
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 43)
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
	
	@Test(priority = 44)
	void DepartmentSummaryGraph1() throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
	
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
	
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
	
		
		Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();
		
		 js.executeScript("window.scrollBy(0,950)");
		 Thread.sleep(5000);
		 String DeptName =performerPOM.DepartName(driver).getText();
		 test = extent.startTest("Select Case Filter ="+DeptName+" Department - Department Summary Graph Count Verification");
	  
	   Thread.sleep(3000);
	   CFOMethod.DepartmentSummaryGraph1(driver, test,"cfo -");
	
	   extent.endTest(test);
	   extent.flush();
	}
	
	@Test(priority = 45)
	void LocationSummaryGraph1() throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
	  
	   	
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
	
	
	@Test(priority = 46)
	void CategorySummaryGraph1() throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
	  
	   	
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
	
	@Test(priority = 47)
	void InwardDefendantAgeingGraphCase() throws InterruptedException, IOException
	{
	     test = extent.startTest("Select Case Filter =Less than a year = Inward/Defendant = Ageing Graph Count Verification");
	
	      Thread.sleep(3000);
	      CFOMethod.InwardDefendantAgeingGraphCase(driver, test,"cfo -");
	
	      extent.endTest(test);
	      extent.flush();
	}
	@Test(priority =48)
	void OutwardPlaintiffAgeingGraphCase() throws InterruptedException, IOException
	{
	     test = extent.startTest("Select Case Filter = Less than a year = Outward/Plaintiff = Ageing Graph Count Verification");
	
	      Thread.sleep(3000);
	      CFOMethod.OutwardPlaintiffAgeingGraphCase(driver, test,"cfo -");
	
	      extent.endTest(test);
	      extent.flush();
	}
	@Test(priority =49)
	void PetitionerAgeingGraphCase() throws InterruptedException, IOException
	{
	  test = extent.startTest("Select Case Filter = Less than a year = Petitioner = Ageing Graph Count Verification");
	
	   Thread.sleep(3000);
	   CFOMethod.PetitionerAgeingGraphCase(driver, test,"cfo -");
	
	   extent.endTest(test);
	   extent.flush();
	}
	@Test(priority =50)
	void CaseOneToTwoYearGraph() throws InterruptedException, IOException
	{
		test = extent.startTest("Select Case Filter  = 1 to 2 year Graph Count Verification");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	 	js.executeScript("window.scrollBy(0,830)");
	 	
	 	Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseFilter(driver).click();
	  
	   	
		 Thread.sleep(3000);
		 performerPOM.clickDashboardApplyBtn(driver).click();
		 
			js.executeScript("window.scrollBy(0,4000)");
			Thread.sleep(3000);
		int	InwardDefendent = Integer.parseInt(performerPOM.CaseInwardDefendent1to2year(driver).getText());	//Reading Notice Open count.
		int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseOutwardPlaintiff1to2year(driver).getText());	//Reading Notice Open count.
		//int	Respondent = Integer.parseInt(performerPOM.CaseRespondnent1to2year(driver).getText());	//Reading Notice Open count.
		int	Complainant = Integer.parseInt(performerPOM.CaseComplainant1to2year(driver).getText());	//Reading Notice Open count.
		
		
		Thread.sleep(3000);
		CFOMethod.CaseOneToTwoYearGraph(driver, test,"Inward/Defendent Type",OutwardPlaintiff);
		Thread.sleep(3000);
		CFOMethod.CaseOneToTwoYearGraph(driver, test,"Outward/Plaintiff Type",InwardDefendent);
		//Thread.sleep(3000);
		//CFOMethod.CaseOneToTwoYearGraph(driver, test,"Respondent Type",Respondent);
		Thread.sleep(3000);
		CFOMethod.CaseOneToTwoYearGraph(driver, test,"Complainant Type",Complainant);
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =51)
	void Case2to3yeargraph() throws InterruptedException, IOException
	 {
	   test = extent.startTest("Select Case Filter = 2 to 3 Ageing Graph Count Verification");

	    Thread.sleep(3000);
	    CFOMethod.Case2to3yeargraph(driver, test,"cfo -");

	    extent.endTest(test);
	    extent.flush();
	 }


@Test(priority = 50)
	void MyDocument() throws InterruptedException, IOException
	{
		test = extent.startTest("My Document-Download and View Document");
	
		
		CFOMethod.MyDocument(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	

@Test(priority = 51)
	void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("Reports -excel count verification");
		
		CFOMethod.MyReports(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 52)
	void MoreReports() throws InterruptedException, IOException
	{
		test = extent.startTest("More Report-Reports excel  verification");
		
		
		CFOMethod.MoreReport(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
@Test(priority =53)
	void MyReminder() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder verification");
		
		CFOMethod.MyReminder(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}

	
@Test(priority = 54)
	void ImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Import Utility verification");
		
		
		CFOMethod.ImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 55)
	void CaseUpdationImportUtility() throws InterruptedException, IOException
	{
		test = extent.startTest("Case Updation Import Utility verification");
		
		
		CFOMethod.CaseUpdationImportUtility(driver,test);
		extent.endTest(test);
		extent.flush();
	}

@Test(priority = 56)
void NoticeUpdation() throws InterruptedException, IOException
{
	test = extent.startTest("Notice Updation Import Utility verification");
	
	
	CFOMethod.NoticeUpdation(driver,test);
	extent.endTest(test);
	extent.flush();
}
 
	
	
	@Test(priority = 57)
		void Masters() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Legal Entity  verification");
				
				CFOMethod.LegalEntity(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
	
	
@Test(priority = 58)
		void Masters1() throws InterruptedException, IOException
		{
			test = extent.startTest("Masters - Law Firm verification");
			
			
			CFOMethod.LawFirm(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}

	@Test(priority = 59)
	void Masters2() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - 	User  verification");
	
	
		CFOMethod.User(driver, test, workbook);
	
		extent.endTest(test);
			extent.flush();
	}
	
	@Test(priority = 60)
	void Masters3() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Opponent  verification");
		
		
		CFOMethod.Opponent(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}



	@Test(priority = 61)
	void Masters4() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Court  verification");
	
	
		CFOMethod.Court(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority = 62)
	void Masters5() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case/NoticeType  verification");
	
	
		CFOMethod.CaseNoticeType(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority = 63)
	void Masters6() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Payment Type  verification");
	
	
		CFOMethod.PaymentType(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}


	@Test(priority =64)
	void Masters7() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Custom Parameter  verification");

	
		CFOMethod.customParameter(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 65)
	void Masters8() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case Stage  verification");

	
		CFOMethod.CaseStage(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 66)
	void Masters9() throws InterruptedException, IOException
	{
	test = extent.startTest("Masters - Document Type  verification");
	
	
	CFOMethod.DocumentType(driver, test, workbook);
	
	extent.endTest(test);
	extent.flush();
	}

	@Test(priority = 67)
	void Masters10() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Rating Criteria  verification");

	
		CFOMethod.RatingCriteria(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 68)
	void Masters12() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Notice Stage  verification");
	
	
		CFOMethod.NoticeStage(driver, test, workbook);
	
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 69)
	void Masters11() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - UserReassignment  verification");
	
	
		CFOMethod.UserReassignment(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 70)
	void Masters13() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Mail Authorization  verification");
	
		CFOMethod.MailAuthorization(driver,test);
	
		extent.endTest(test);
		extent.flush();
	}

	
	
	
	 @AfterMethod
	 
	 void Close()
	 {
		 driver.close(); 
	 }


	


}
