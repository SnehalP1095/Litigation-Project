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
		
		Thread.sleep(5000);
		performerPOM.clickDashboardCaseNoticeFilter1(driver).click();
		
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
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtZone(driver).click();
//	    
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtZonedropdown(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtRegion(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtRegiondropdown(driver).click();
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtTerritory(driver).click();
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtTerritorydropdown(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCityname(driver).sendKeys("Pune");
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtSave(driver).click();
//	    Thread.sleep(3000);
//	    performerPOM.clickCustomerMgmtClose(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtOk(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtEdit(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCityname(driver).sendKeys("Mumbai");
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtSave(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtClose(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtOk(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtDelete(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCustomer(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCBUFilter(driver).click();
	    Thread.sleep(3000);
	    performerPOM.clickCustomerMgmtCBUFilter1(driver).click();
	    
	    
		
		
	}
	
	
	
	
	
   
 
}


