package litigationAdditionalOwner;

import static litigationAdditionalOwner.performerPOM.clicktype1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import licensePerformer.LiPerformerPOM;
import performer.OverduePOM;

public class MethodPOM1 {
	
	
    private static List<WebElement> elementsList = null;
    public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static XSSFSheet sheet1 = null;		//Sheet variable


	public static void progress(WebDriver driver) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 180);
		try
		{
			Thread.sleep(300);
			wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		//String workingDir = System.getProperty("user.dir");
		fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Snehal\\ComplianceLatest\\ComplianceLatest\\TestData\\LitigationSheet.xlsx");
		
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(4);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	public static void AdvancedSearchReport(WebDriver driver,ExtentTest test, String type) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,180);
		
		Thread.sleep(500);
        performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
        
        
//        Thread.sleep(500);
//        performerPOM.clickExcelReport1(driver).click();
//        test.log(LogStatus.PASS, "Usage Report downloaded successfully.");
		
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		Thread.sleep(5000);
		
		performerPOM.AdvancedSearchReports(driver).click();
		
	//-------------------------------------------Notice--------------------------------------------------
		
		Thread.sleep(3000);
		performerPOM.startDate(driver).sendKeys("05/10/2022");
		
		Thread.sleep(3000);
		performerPOM.endDate(driver).sendKeys("05/12/2022");
		
		Thread.sleep(3000);
		performerPOM.clickApplyButton(driver).click();
		
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		
		Thread.sleep(3000);
		performerPOM.clickExportAdavanced(driver).click();
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		
		Thread.sleep(3000);
		performerPOM.viewNoticeDetails(driver).click();
		test.log(LogStatus.PASS, "Show details notice popup open successfully.");
		
		
		Thread.sleep(3000);
		performerPOM.Actionclosepopup(driver).click();
		
		Thread.sleep(3000);
		performerPOM.showResponseDetailIcon(driver).click();
		test.log(LogStatus.PASS, "Show response details notice popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.Actionclosepopup(driver).click();
		
	//-------------------------------------------Case--------------------------------------------------
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		Thread.sleep(3000);
		performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		Thread.sleep(4000);
		performerPOM.selectTypeCase1(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
	
		Thread.sleep(3000);
		performerPOM.viewNoticeDetails(driver).click();
		test.log(LogStatus.PASS, "Show details case popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.Actionclosepopup(driver).click();
		
		Thread.sleep(3000);
		performerPOM.showResponseDetailIcon(driver).click();
		test.log(LogStatus.PASS, "Show response details Case popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.Actionclosepopup(driver).click();
		
	//-------------------------------------------Task--------------------------------------------------
			Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		
		Thread.sleep(3000);
		performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		Thread.sleep(8000);
		performerPOM.selectTypeTask1(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		
		Thread.sleep(3000);
		performerPOM.viewTaskDetails(driver).click();	
		test.log(LogStatus.PASS, "Show details Task popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.ActioncloseTaskpopup(driver).click();
		
		Thread.sleep(1000);
		OverduePOM.clickDashboard(driver).click();
	}
	
	
	public static void DashBoardFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
	{
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
       	js.executeScript("window.scrollBy(0,800)");
       	
       	Thread.sleep(5000);
		performerPOM.clickDashboardLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardLocFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
		
//		Thread.sleep(5000);
//		performerPOM.clickDashboardCaseNoticeFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardTypeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardTypeFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDashboardDeptFilter(driver).click();
			
		Thread.sleep(6000);
		performerPOM.clickDashboardDeptFilter1(driver).click();
		
		Thread.sleep(6000);
		performerPOM.clickDashboardstatusFilter(driver).click();
		
		Thread.sleep(6000);
		performerPOM.clickDashboardstatusFilter1(driver).click();
		
        Thread.sleep(6000);
		performerPOM.clickDashboardRiskFilter(driver).click();
		
        Thread.sleep(6000);
		performerPOM.clickDashboardRiskFilter1(driver).click();
		
	    Thread.sleep(5000);
		performerPOM.clickDashboardApplyBtn(driver).click();
		
	    Thread.sleep(5000);
		performerPOM.clickDashboardClearBtn(driver).click();
		
		test.log(LogStatus.PASS,"DashBoard Filter Work Successfully");
		
		}
	public static void WorkspaceFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		Thread.sleep(5000);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickCaseNotice1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clicklocationFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clicklocationFilter2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickCalenderYear2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickCalenderYear3(driver).click();
		
		
		Thread.sleep(5000);
		performerPOM.clickDepartmentFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDepartmentFilter2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickFinancialYear2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickFinancialYear3(driver).click();
		
		
//     	Thread.sleep(5000);
//		performerPOM.clickstatus(driver).click();
//		
//		Thread.sleep(5000);
//		performerPOM.clickstatus1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickcategory(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickcategory1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickType1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clicktype1(driver).click();
		
		test.log(LogStatus.PASS, "My Workspace = Notice Filters Work Successfully");
		
		Thread.sleep(5000);
		performerPOM.clickDropdown(driver).click();
		
		Thread.sleep(5000);
		performerPOM.selectTypeCase(driver).click();
		
		test.log(LogStatus.PASS, "My Workspace = Case  Filters Work Successfully");
		

		Thread.sleep(5000);
		performerPOM.selectApplyBtn(driver).click();
		
		Thread.sleep(7000);
		performerPOM.clickDropdown(driver).click();
		
		Thread.sleep(7000);
		performerPOM.selectTypeTask(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskLocFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskPriorityFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskPriorityFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskStatusFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskStatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskPeriodFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickTaskPeriodFilter1(driver).click();
		
		
		Thread.sleep(6000);
		performerPOM.clearButton(driver).click();
		
		test.log(LogStatus.PASS, "My Workspace = Task Filters Work Successfully");
		
		Thread.sleep(5000);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickCaseHearing1(driver).click();
		
		
		Thread.sleep(5000);
		performerPOM.clickSearchFilter(driver).sendKeys("Case of financial companies");
		
		
		Thread.sleep(5000);
		performerPOM.CaseHearingView(driver).click();
		
		Thread.sleep(5000);
		performerPOM.CaseHearingPopupClose(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clearButton(driver).click();
		test.log(LogStatus.PASS, "My Workspace = Case Hearing Filters Work Successfully");
		}
		
	public static void DocumentFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
			progress(driver);
		
		//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
		performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
		performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
		
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
//		
		Thread.sleep(7000);
		performerPOM.clickDocStatusFilter(driver).click();
		
		Thread.sleep(7000);
		performerPOM.clickDocStatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTypeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTypeFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocLocFilter2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocDeptFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocDeptFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clearButton(driver).click();
		test.log(LogStatus.PASS, "My Document = Case Filters Work Successfully");
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		Thread.sleep(7000);
		performerPOM.clickDocDropdownFilter(driver).click();
		
		Thread.sleep(7000);
		performerPOM.selectTypeCase(driver).click();
		
		Thread.sleep(7000);
		performerPOM.clickDocStatusFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocStatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTypeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTypeFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocLocFilter2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocDeptFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocDeptFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clearButton(driver).click();
		
		test.log(LogStatus.PASS, "My Document = Notice Filters Work Successfully");
		
		Thread.sleep(7000);
		performerPOM.clickDocDropdownFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.selectTypeTask(driver).click();
		
		Thread.sleep(6000);
		performerPOM.clickDocStatusFilter(driver).click();
		
		Thread.sleep(6000);
		performerPOM.clickDocStatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTaskPriorityFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTaskPriorityFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocLocFilter2(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTaskFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickDocTaskFilter1(driver).click();
		
		
		Thread.sleep(5000);
		performerPOM.clickDocDropdownFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.selectTypeTask(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clearButton(driver).click();
		
		test.log(LogStatus.PASS, "My Document = Task Filters Work Successfully");
		
		}	
		
	public static void ReportFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
			
		progress(driver);
		
	
		performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		
		Thread.sleep(7000);
		performerPOM.clickReportStatusFilter(driver).click();
		
		Thread.sleep(7000);
		performerPOM.clickReportStatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportDeptFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportDeptFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportTypeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportTypeFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportCategoryFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportCategoryFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportLocFilter1(driver).click();
		
//		Thread.sleep(5000);
//		performerPOM.clickReportFYFilter(driver).click();
//		
//		Thread.sleep(5000);
//		performerPOM.clickReportFYFilter1(driver).click();
		
		Thread.sleep(7000);
		performerPOM.clickReportCYFilter(driver).click();
		
		Thread.sleep(7000);
		performerPOM.clickReportCYFilter1(driver).click();
		
		Thread.sleep(4000);
		performerPOM.clearButton(driver).click();
		test.log(LogStatus.PASS,"My Report = Notice Filter Work successfully");
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table 
		
		Thread.sleep(7000);
		performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		
		Thread.sleep(7000);
		performerPOM.selectTypeCase(driver).click();	
		
		Thread.sleep(7000);
		performerPOM.clickReportStatusFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportStatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportDeptFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportDeptFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportTypeFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportTypeFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportCategoryFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportCategoryFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportLocFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickLocationFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportLocFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportFYFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportFYFilter1(driver).click();
		
//		Thread.sleep(5000);
//		performerPOM.clickReportCYFilter(driver).click();
//		
//		Thread.sleep(5000);
//		performerPOM.clickReportCYFilter1(driver).click();
		
		Thread.sleep(4000);
		performerPOM.clearButton(driver).click();
		test.log(LogStatus.PASS,"My Report =Case Filter Work successfully");
		
		
		Thread.sleep(5000);
		performerPOM.clickTypeDropdown(driver).click();	
		
		Thread.sleep(5000);
		performerPOM.selectTypeTask(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportprioFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportprioFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportstatusFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportstatusFilter1(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportFilter(driver).click();
		
		Thread.sleep(5000);
		performerPOM.clickReportFilter1(driver).click();
		Thread.sleep(4000);
		performerPOM.clearButton(driver).click();
		
		test.log(LogStatus.PASS, "My Report = Task Filters Work Successfully");
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
		
		
		
   }
	public static void AdvancedSearchWorkspace(WebDriver driver,ExtentTest test, String type) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,60);
 		
		
		Thread.sleep(3000);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickCaseNotice1(driver).click();
	
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		Thread.sleep(5000);
		
		performerPOM.AdvancedSearchReports(driver).click();
		
	//-------------------------------------------Notice--------------------------------------------------
		
		Thread.sleep(4000);
		performerPOM.startDate(driver).sendKeys("05/10/2022");
		
		Thread.sleep(4000);
		performerPOM.endDate(driver).sendKeys("05/12/2022");
		
		Thread.sleep(4000);
		performerPOM.clickApplyButton(driver).click();
		
		
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		
		Thread.sleep(5000);
		performerPOM.clickExportAdavanced(driver).click();
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		
		Thread.sleep(4000);
		performerPOM.clickeditButton(driver).click();
		
		test.log(LogStatus.PASS,"edit notice details icon open successfully");
		
		
		Thread.sleep(5000);
		performerPOM.Actionclosepopup(driver).click();
		
		
	/*	Thread.sleep(4000);
		performerPOM.clickdeleteButton(driver).click();
		
		  Thread.sleep(5000);
		    // Switching to Alert        
	        Alert alert = driver.switchTo().alert();		
	        		
	        // Capturing alert message.    
	        String alertMessage= driver.switchTo().alert().getText();	
	        
	        Thread.sleep(3000);
	        test.log(LogStatus.PASS, alertMessage);
	        		
	        // Displaying alert message		
	        System.out.println(alertMessage);	
	        
	        		
	        // Accepting alert		
	        alert.accept();		*/
	      //-------------------------------------------Case--------------------------------------------------
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
			
			Thread.sleep(4000);
			performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
			Thread.sleep(4000);
			performerPOM.selectTypeCase1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
		
			Thread.sleep(4000);
			performerPOM.clickeditButton(driver).click();
			
			test.log(LogStatus.PASS,"edit case details icon open successfully");
			
			
			Thread.sleep(5000);
			performerPOM.Actionclosepopup(driver).click();
			
			
	/*		Thread.sleep(4000);
			performerPOM.clickdeleteButton(driver).click();
			
			  Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert1 = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		        
		        Thread.sleep(3000);
		        test.log(LogStatus.PASS, alertMessage1);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage1);	
		        
		        		
		        // Accepting alert		
		        alert1.accept();	*/
		        
          //-------------------------------------------Task--------------------------------------------------
				Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
			
			
			Thread.sleep(3000);
			performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
			Thread.sleep(3000);
			performerPOM.selectTypeTask1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(3000);
			performerPOM.viewTaskDetails1(driver).click();	
			test.log(LogStatus.PASS, "Show details Task popup open successfully.");
			
			Thread.sleep(3000);
			performerPOM.ActioncloseTaskpopup(driver).click();
			
			Thread.sleep(1000);
			OverduePOM.clickDashboard(driver).click();
		        
	}
	
	public static void CustomerMgmt(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		Thread.sleep(2000);
	    performerPOM.clickCustomerMgmt(driver).click();
	    Thread.sleep(2000);
	    performerPOM. clickCustomerMgmtCity(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtAdd(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCBU(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCBUdropdown(driver).click();
	   Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtZone(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtZonedropdown(driver).get(1).click();
	    Thread.sleep(4000);
	    performerPOM.clickCustomerMgmtRegion(driver).click();
	 
	    By locator = By.xpath("//*[@id='ddlRegion_listbox']/li");

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		Thread.sleep(4000);
		WebElement ViewButton = driver.findElement(locator);	
		Thread.sleep(3000);
		JavascriptExecutor jse=(JavascriptExecutor)driver;
	    jse.executeScript("arguments[0].click();", ViewButton);
	    performerPOM.clickCustomerMgmtRegion(driver).click();
		
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtTerritory(driver).click();
	   // Thread.sleep(3000);
	    //performerPOM.clickCustomerMgmtTerritorydropdown(driver).get(0).click();
	    
	     By locator1 = By.xpath("//*[@id='ddlTerritory_listbox']/li");
	     wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
	  	 Thread.sleep(4000);
	  	 WebElement ViewButton1 = driver.findElement(locator1);	
	  	 Thread.sleep(3000);
	  	 JavascriptExecutor jse1=(JavascriptExecutor)driver;
		 jse.executeScript("arguments[0].click();", ViewButton1);
		 Thread.sleep(3000);
		 
	  	 performerPOM.clickCustomerMgmtTerritory(driver).click();
	  	    
	      Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCityname(driver).sendKeys("Agra");
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtSave(driver).click();
	    try
	    {
	    	 
	 	    
	 	   Thread.sleep(5000);
      	   // Capturing alert message.    
              String alertMessage1= driver.switchTo().alert().getText();	
               Thread.sleep(3000);
              test.log(LogStatus.PASS, alertMessage1);
            // Accepting alert		
              driver.switchTo().alert().accept();	
              Thread.sleep(4000);
      	     performerPOM.clickCustomerMgmtClose2(driver).click();
      	     Thread.sleep(4000);
	    }
         catch(Exception e)
         {
        	 Thread.sleep(3000);
 	 	    performerPOM.clickCustomerMgmtOk(driver).click();
 	 	  test.log(LogStatus.PASS, "City Added Successfully");
         }
	    
	   

	/*    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtEdit(driver).click();
	    performerPOM.clickCustomerMgmtCityname(driver).clear();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCityname(driver).sendKeys("Aaurngabad");
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtSave(driver).click();
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtok(driver).click();
	    
	    Thread.sleep(6000);
	    By locator2 = By.xpath("/html/body/div[27]/div[3]/button");        //clickUploadfile
		    wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
		  	Thread.sleep(4000);
		  	WebElement ViewButton2 = driver.findElement(locator2);	
		  	Thread.sleep(3000);
		  	JavascriptExecutor jse2=(JavascriptExecutor)driver;
		  	Thread.sleep(3000);
		    jse2.executeScript("arguments[0].click();", ViewButton2);

	    
	    test.log(LogStatus.PASS, "City Updated Successfully");*/
	    
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtDelete(driver).click();
//	    
//	    Thread.sleep(5000);
//	   // Capturing alert message.    
//        String alertMessage1= driver.switchTo().alert().getText();	
//         Thread.sleep(3000);
//        test.log(LogStatus.PASS, alertMessage1);
//      // Accepting alert		
//        driver.switchTo().alert().accept();	
//        
//        Thread.sleep(5000);
//     // Capturing alert message.    
//        String alertMessage= driver.switchTo().alert().getText();	
//         Thread.sleep(3000);
//        test.log(LogStatus.PASS, alertMessage);
//      // Accepting alert		
//        driver.switchTo().alert().accept();	
	    
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtCustomer(driver).click();
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtCBUFilter(driver).click();
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtCBUFilter1(driver).click();
	    
	   
	}
	public static void CustomerMgmtCustomer(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		 Thread.sleep(2000);
		    performerPOM.clickCustomerMgmt(driver).click();
		  Thread.sleep(4000);
		    performerPOM.clickCustomerMgmtCustomer(driver).click();
		    Thread.sleep(3000);
		    performerPOM.clickCustomerMgmtAdd(driver).click();
		    Thread.sleep(3000);
		    performerPOM.clickCustomerName(driver).sendKeys("Amol");
		    Thread.sleep(3000);
		    performerPOM.clickCustomerID(driver).sendKeys("030");
		    Thread.sleep(3000);
		    performerPOM.clickSPOCName(driver).sendKeys("Shiv");
		    Thread.sleep(3000);
		    performerPOM.clickEmailID(driver).sendKeys("shiv@yahoo.com");
		    Thread.sleep(3000);
		    performerPOM.clickMobNo(driver).sendKeys("0000080000");
		     Thread.sleep(3000);
		    performerPOM.clickWhatsappNo(driver).sendKeys("1234078900");
		    Thread.sleep(3000);
		    performerPOM.clickCity1(driver).click();
		    Thread.sleep(3000);
		    performerPOM.selectCity1(driver).click();
		    Thread.sleep(3000);
		    performerPOM.clickCustomerMgmtSave(driver).click();
		    
		    try
		    {
		    	 
		 	    
		 	   Thread.sleep(5000);
	      	   // Capturing alert message.    
	              String alertMessage1= driver.switchTo().alert().getText();	
	               Thread.sleep(3000);
	              test.log(LogStatus.PASS, alertMessage1);
	            // Accepting alert		
	              driver.switchTo().alert().accept();	
	              Thread.sleep(5000);
	      	     performerPOM.clickCustomerMgmtClose(driver).click();
	      	     Thread.sleep(4000);
		    }
	         catch(Exception e)
	         {
	        	 Thread.sleep(3000);
	 	 	    performerPOM.clickCustomerMgmtOk(driver).click();
	 	 	  test.log(LogStatus.PASS, "Customer Added Successfully");
	         }
		    
		    Thread.sleep(3000);
		    performerPOM.clickCustomerMgmtEdit1(driver).click();
		    Thread.sleep(3000);
		    performerPOM.clickCustomerID(driver).clear();
		    Thread.sleep(3000);
		    performerPOM.clickCustomerID(driver).sendKeys("330");
		    Thread.sleep(3000);
		    performerPOM.clickCity1(driver).click();
		    Thread.sleep(3000);
		    performerPOM.selectCity1(driver).click();
		    Thread.sleep(3000);
		    performerPOM.clickCustomerMgmtSave(driver).click();
//		    Thread.sleep(5000);
//		    performerPOM.clickCustomerMgmtok(driver).click();
		    
		    Thread.sleep(6000);
		    By locator = By.xpath("(//button[@class='k-button k-primary'])[1]");        //clickUploadfile
 		    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
 		  	Thread.sleep(4000);
 		  	WebElement ViewButton = driver.findElement(locator);	
 		  	Thread.sleep(3000);
 		  	JavascriptExecutor jse=(JavascriptExecutor)driver;
 		  	Thread.sleep(3000);
 		    jse.executeScript("arguments[0].click();", ViewButton);
 		    
		    Thread.sleep(3000);
		    test.log(LogStatus.PASS, "Customer Updated Successfully");
//		    Thread.sleep(3000);
//		    performerPOM.clickCustomerMgmtDelete1(driver).click();
//
//		    Thread.sleep(5000);
//		   // Capturing alert message.    
//	        String alertMessage1= driver.switchTo().alert().getText();	
//	         Thread.sleep(3000);
//	        test.log(LogStatus.PASS, alertMessage1);
//	      // Accepting alert		
//	        driver.switchTo().alert().accept();	
//	        
//	        Thread.sleep(5000);	
//	     // Capturing alert message.    
//	        String alertMessage= driver.switchTo().alert().getText();	
//	         Thread.sleep(3000);
//	        test.log(LogStatus.PASS, alertMessage);
//	      // Accepting alert		
//	        driver.switchTo().alert().accept();	
	        
	        Thread.sleep(6000);
		    performerPOM.clickCustomerUpload(driver).click();
		    
		    Thread.sleep(6000);
		    performerPOM.clickChooseFile(driver);

		    Thread.sleep(6000);
		    By locator1 = By.xpath("//*[@id='btnFileUploadForCustomer']");        //clickUploadfile
 		    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
 		  	Thread.sleep(4000);
 		  	WebElement ViewButton1 = driver.findElement(locator1);	
 		  	Thread.sleep(3000);
 		  	JavascriptExecutor js1=(JavascriptExecutor)driver;
 		  	Thread.sleep(3000);
 		    jse.executeScript("arguments[0].click();", ViewButton1);
 		   //jse.executeScript("arguments[0].click();", ViewButton);
 		   
// 		  Thread.sleep(2000);
//		  performerPOM.clickCustomerUpload(driver).click();
		    
		    try
		    {
		 	    
		 	   Thread.sleep(5000);
	      	   // Capturing alert message.    
	              String alertMessage2= driver.switchTo().alert().getText();	
	               Thread.sleep(3000);
	              test.log(LogStatus.PASS, alertMessage2);
	            // Accepting alert		
	              driver.switchTo().alert().accept();	
	              Thread.sleep(4000);
	      	   
		    }
	         catch(Exception e)
	         {
	        	 Thread.sleep(2000);
	   		  performerPOM.clickCustomerUpload(driver).click();
	        	 Thread.sleep(3000);
	 		    performerPOM.clickCustomerErrotfile(driver).click();
	 		   test.log(LogStatus.FAIL, "Download Error File");
	 	 	 Thread.sleep(4000);
     	     performerPOM.clickCustomerMgmtClose(driver).click();
	         }
		      
		    Thread.sleep(3000);
		    performerPOM.clickCustomerUploadOutStanding(driver).click();
		    
		    Thread.sleep(3000);
		    performerPOM.clickChooseFile1(driver);
		    
		    Thread.sleep(3000);
		    performerPOM.clickUploadfile1(driver).click();
		    try
		    {
		 	    
		 	   Thread.sleep(5000);
	      	   // Capturing alert message.    
	              String alertMessage2= driver.switchTo().alert().getText();	
	               Thread.sleep(3000);
	              test.log(LogStatus.PASS, alertMessage2);
	            // Accepting alert		
	              driver.switchTo().alert().accept();	
	              Thread.sleep(4000);
	      	   
		    }
	         catch(Exception e)
	         {
	        	
	   		   Thread.sleep(3000);
		       performerPOM.clickPlanVisitErrotfile(driver).click();
	 	 	  test.log(LogStatus.FAIL, "Download Error File");
	 	 	 Thread.sleep(4000);
    	     performerPOM.clickCustomerMgmtClose1(driver).click();
	         }
		    
		   
	}
	public static void CustomerMgmtPalnVisit(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		 Thread.sleep(2000);
		    performerPOM.clickCustomerMgmt(driver).click();
	     
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitAdd(driver).click();
		    
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitcustomaerid(driver).click();
		    Thread.sleep(2000);
		    performerPOM.selectPlanVisitcustomaerid(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitcustomaerid(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitdate(driver).clear();
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitdate(driver).sendKeys("16-02-2023");
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitremark(driver).sendKeys("Ok");
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitsubmit(driver).click();
		    
		    Thread.sleep(5000);
			   // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		         Thread.sleep(3000);
		        test.log(LogStatus.PASS, alertMessage1);
		      // Accepting alert		
		        driver.switchTo().alert().accept();	
		        Thread.sleep(3000);
	 	 	    performerPOM.clickCustomerMgmtOk(driver).click();
	 	 	  test.log(LogStatus.PASS, "Plan Visit Successfully Added");
	 	 	  Thread.sleep(3000);
	 	 	   performerPOM.startDate(driver).sendKeys("01-02-2023");
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.endDate(driver).sendKeys("28-02-2023");
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickPlanVisityear(driver).clear();
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickPlanVisityear(driver).sendKeys("2023",Keys.ENTER);
	 	 	 Thread.sleep(3000);
	 	 	  performerPOM.clickPlanVisitedit(driver).click();
	 	 	performerPOM.clickPlanVisitedit(driver).click();
	 	 	 performerPOM.clickPlanVisitremark(driver).clear();
	 	 	 Thread.sleep(2000);
		    performerPOM.clickPlanVisitremark(driver).sendKeys("Ok");
			    Thread.sleep(2000);
			    performerPOM.clickPlanVisitsubmit(driver).click();
			    
			    Thread.sleep(5000);
				   // Capturing alert message.    
			        String alertMessage= driver.switchTo().alert().getText();	
			         Thread.sleep(3000);
			        test.log(LogStatus.PASS, alertMessage);
			      // Accepting alert		
			        driver.switchTo().alert().accept();	
//			        Thread.sleep(3000);
//		 	 	    performerPOM.clickCustomerMgmtok(driver).click();
		 	 	    
		 		    By locator3 = By.xpath("/html/body/div[32]/div[3]/button");
		 		    wait.until(ExpectedConditions.presenceOfElementLocated(locator3));
		 		  	Thread.sleep(4000);
		 		  	WebElement ViewButton3 = driver.findElement(locator3);	
		 		  	Thread.sleep(3000);
		 		  	JavascriptExecutor jse2=(JavascriptExecutor)driver;
		 		    jse2.executeScript("arguments[0].click();", ViewButton3);
		 		   jse2.executeScript("arguments[0].click();", ViewButton3);
		 		   
		 		  test.log(LogStatus.PASS, "Plan Visit Successfully Updated");
		 		   
		 		 /* Thread.sleep(2000);
				    performerPOM.clickPlanVisitdelete(driver).click();
				    Thread.sleep(5000);
					   // Capturing alert message.    
				        String alertMessage2= driver.switchTo().alert().getText();	
				         Thread.sleep(3000);
				        test.log(LogStatus.PASS, alertMessage2);
				      // Accepting alert		
				        driver.switchTo().alert().accept();	
				        Thread.sleep(2000);
					    performerPOM.clickPlanVisitOverdueVisit(driver).click();*/
	 	 	 
	}
	public static void UpdateCommitmentsafterremarks(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		 Thread.sleep(2000);
		    performerPOM.clickCustomerMgmt(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickUpdateCommitmentsafterremarks(driver).click();

		    Thread.sleep(2000);
		    performerPOM.clickEditPendingVisit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickAddNewRecord(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickScheduleDate(driver).clear();
		    Thread.sleep(2000);
		    performerPOM.clickScheduleDate(driver).sendKeys("05-Feb-2023");
		    
            Thread.sleep(2000);
		    performerPOM.clickAmount2(driver).sendKeys("20000",Keys.ENTER);
		    Thread.sleep(2000);
		    performerPOM.clickFollowupDate(driver).clear();
		    Thread.sleep(2000);
		    performerPOM.clickFollowupDate(driver).sendKeys("07-Feb-2023",Keys.ENTER);
		    Thread.sleep(2000);
		    performerPOM.clickUpdate(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitremark(driver).sendKeys("Ok");
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitsubmit(driver).click();
		    Thread.sleep(5000);
			   // Capturing alert message.    
		        String alertMessage2= driver.switchTo().alert().getText();	
		         Thread.sleep(3000);
		        test.log(LogStatus.PASS, alertMessage2);
		      // Accepting alert		
		        driver.switchTo().alert().accept();	
		        Thread.sleep(2000);
		        

	        	 Thread.sleep(3000);
	 	 	    performerPOM.clickCustomerMgmtOk(driver).click();
	 	 	    
	 	 	  test.log(LogStatus.PASS, "Visit Details Updated Successfully");
	 	 	  
	 	 /*	 Thread.sleep(2000);
			 performerPOM.clickDelete(driver).click();
			 // Capturing alert message.    
		        String alertMessage= driver.switchTo().alert().getText();	
		         Thread.sleep(3000);
		        test.log(LogStatus.PASS, alertMessage);
		      // Accepting alert		
		        driver.switchTo().alert().accept();	
		     // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		         Thread.sleep(3000);
		        test.log(LogStatus.PASS, alertMessage1);
		      // Accepting alert		
		        driver.switchTo().alert().accept();	
	 	 	  
	 	 	Thread.sleep(2000);
		    performerPOM.clickUpdatedVisit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickEditUpdatedVisit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickAddNewRecord(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickEdit(driver).click();
		    Thread.sleep(4000);
		    performerPOM.clickScheduleDate1(driver).click();;
		    Thread.sleep(4000);
		    performerPOM.clickScheduleDate2(driver).click();
            Thread.sleep(2000);
		    performerPOM.clickAmount2(driver).sendKeys("20000",Keys.ENTER);
		    Thread.sleep(2000);
		    performerPOM.clickFollowupDate(driver).clear();
		    Thread.sleep(2000);
		    performerPOM.clickFollowupDate(driver).sendKeys("07-Feb-2023",Keys.ENTER);
		    Thread.sleep(2000);
		    performerPOM.clickUpdate(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickPlanVisitsubmit(driver).click();
		    Thread.sleep(5000);
			   // Capturing alert message.    
		        String alertMessage= driver.switchTo().alert().getText();	
		         Thread.sleep(3000);
		        test.log(LogStatus.PASS, alertMessage);
		      // Accepting alert		
		        driver.switchTo().alert().accept();	
		        Thread.sleep(2000);
		        

	        	 Thread.sleep(3000);
	 	 	    performerPOM.clickCustomerMgmtOk(driver).click();
	 	 	  test.log(LogStatus.PASS, "Visit Details Successfully Updated");*/
		    
	}
	public static void UpdateCommitmentsStatus(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		 Thread.sleep(2000);
		    performerPOM.clickCustomerMgmt(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickUpdateCommitmentsStatus(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickUpdateCommitmentsStatusEdit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickAddNewRecords(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickCommitDate(driver).sendKeys("01-Feb-2023",Keys.ENTER);
		    
		    Thread.sleep(2000);
		    performerPOM.clickCommitAmount(driver).sendKeys("20000");
		    Thread.sleep(2000);
		    performerPOM.clickRecieptDate(driver).sendKeys("05-Feb-2023");
		    Thread.sleep(2000);
		    performerPOM.clickRecieptDateAmount(driver).sendKeys("10000");
		    Thread.sleep(2000);
		    performerPOM.clickCommitRemark(driver).sendKeys("ok");
		    Thread.sleep(2000);
		    performerPOM.clickUpdateCommit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickUpdateCommitedit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickCommitRemark(driver).clear();
		    Thread.sleep(2000);
		    performerPOM.clickCommitRemark(driver).sendKeys("ok");
		    Thread.sleep(2000);
		    performerPOM.clickUpdateCommit(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickSendReminder(driver).click();
		    
		    Thread.sleep(2000);
		 // Capturing alert message.    
	        String alertMessage= driver.switchTo().alert().getText();	
	         Thread.sleep(3000);
	        test.log(LogStatus.PASS, alertMessage);
	      // Accepting alert		
	        driver.switchTo().alert().accept();
	        
	        Thread.sleep(2000);
		    performerPOM.clickStopReminder(driver).click();
		    Thread.sleep(2000);
		    // Capturing alert message.    
	        String alertMessage1= driver.switchTo().alert().getText();	
	         Thread.sleep(3000);
	        test.log(LogStatus.PASS, alertMessage1);
	      // Accepting alert		
	        driver.switchTo().alert().accept();
	        
	        Thread.sleep(2000);
		    performerPOM.clickDelete1(driver).click();
		    
		    // Capturing alert message.    
	        String alertMessage2= driver.switchTo().alert().getText();	
	         Thread.sleep(3000);
	        test.log(LogStatus.PASS, alertMessage2);
	      // Accepting alert		
	        driver.switchTo().alert().accept();
	        Thread.sleep(2000);
		    performerPOM.clickclosePopuopCommitments(driver).click();
	}
	public static void Report(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		 Thread.sleep(2000);
		    performerPOM.clickCustomerMgmt(driver).click();
		    Thread.sleep(2000);
		    performerPOM.clickReports(driver).click();
		    
		    Thread.sleep(3000);
	 	 	   performerPOM.startDate(driver).sendKeys("01-02-2023");
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.endDate(driver).sendKeys("28-02-2023");
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickPlanVisityear(driver).clear();
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickPlanVisityear(driver).sendKeys("2023",Keys.ENTER);

	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickSchedulesReport(driver).click();
	 	 	   
	 	 	   
//	 	 	   String Data=performerPOM.clickNoRecordFound(driver).getText();
//	 	 	   
//	 	 	   if(!performerPOM.clickNoRecordFound(driver).isDisplayed())
//	 	 	   {
//	 	 		 test.log(LogStatus.PASS, "Data should be displayed");
//	 	 	   }
//	 	 	   else
//	 	 	   {
//	 	 		 test.log(LogStatus.FAIL, "Message Displayed " + Data );
//	 	 	   }
	 	 	   
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickNoRecordFound(driver).click();
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickCommitmentReport(driver).click();
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickAuditLogReport(driver).click();
	 	 	 Thread.sleep(3000);
	 	 	   performerPOM.clickExportAuditLogReport(driver).click();
	 	 	   
	 	 	 test.log(LogStatus.PASS, "File Download succssfully");
	 	 	   
	 	 	   
	
	}
	
   
 
}


