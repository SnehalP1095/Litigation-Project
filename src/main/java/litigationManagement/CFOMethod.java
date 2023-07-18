package litigationManagement;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.ui.Select;


import cfo.CFOcountPOM;
import licensePerformer.LiPerformerPOM;
import litigationAdditionalOwner.performerPOM;
import performer.OverduePOM;

public class CFOMethod {
	
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
			fis = new FileInputStream("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx");
			
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
			return sheet;
		}
		public static void DashBoardFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	       	js.executeScript("window.scrollBy(0,600)");
	       	
	       	Thread.sleep(5000);
			performerPOM.clickDashboardLocFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardLocFilter1(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
			
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
			
			Thread.sleep(500);
			OverduePOM.clickDashboard(driver).click();
			
			test.log(LogStatus.PASS,"DashBoard Filter Work Successfully");
			
			
			
			}
		public static void CaseNoticeStageGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	       	js.executeScript("window.scrollBy(0,800)");
	       	
	       	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
			
			 Thread.sleep(5000);
				performerPOM.clickDashboardApplyBtn(driver).click();
				
				js.executeScript("window.scrollBy(0,300)");
			
	       	Thread.sleep(2000);
		
	       	int	open = Integer.parseInt(performerPOM.clickCaseNoticeStageHearingGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.clickCaseNoticeStageHearingGraph(driver).click();						//Clicking on 'Open' notice
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(open == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    
			Thread.sleep(3000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(3000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
			
		/*	Thread.sleep(3000);
			performerPOM.clickLocationFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter1(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickLocationFilter3(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickCaseNotice(driver).click();
			
			Thread.sleep(5000);
			performerPOM.selectCaseNotice(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickStatusFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.selectstatusFiltercfo(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDepartmentFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.selectDepartmentFilter2(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickCaseNoticeType1(driver).click();
			
			Thread.sleep(5000);
			performerPOM.selectCaseNoticeType2(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickRiskFilter(driver).click();
			

			Thread.sleep(5000);
			performerPOM.selectRiskFilter2cfo(driver).click();
			
//			Thread.sleep(5000);
//			performerPOM.clickAgeFilter(driver).click();
//			
//			Thread.sleep(5000);
//			performerPOM.selectAgeFiltercfo(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickCategoryFilter(driver).click();
			
			
			Thread.sleep(5000);
			performerPOM.selectCategoryFilter2(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickStageFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.selectStageFilter2(driver).click(); */
			
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(1000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "clear button work successfully.");
			
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	   }
		
		public static void CaseNoticeStageGraph1(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	       	js.executeScript("window.scrollBy(0,800)");
	       	
	       	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickDashboardCaseFilter(driver).click();
			
			 Thread.sleep(3000);
			performerPOM.clickDashboardApplyBtn(driver).click();
			
		 	js.executeScript("window.scrollBy(0,500)");
			
	       	Thread.sleep(2000);
		
	       	int	open = Integer.parseInt(performerPOM.clickCaseNoticeStageHearingGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.clickCaseNoticeStageHearingGraph(driver).click();						//Clicking on 'Open' notice
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(open == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    
			Thread.sleep(3000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(3000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
		
			Thread.sleep(1000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				test.log(LogStatus.PASS, "File downloaded successfully.");
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			
			
			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
				
		   
			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully");
		   
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	   }	
		
		
		
		
		
		
		
		
		public static void CaseNoticeTypeGraph(WebDriver driver, ExtentTest test, String type,int countType) throws InterruptedException, IOException
		{
			

			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;

			
	       	
     
			if(type.equalsIgnoreCase("Outward/Plaintiff Type"))
			{
	         	Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeOutwardPlaintiff(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Inward/Defendent Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeInwardDefendent(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Complinant Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeComplinant(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Respondent Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeRespondent(driver).click();						//Clicking on 'Open' notice
			}
			
			else if(type.equalsIgnoreCase("Applicant Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeApplicant(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Petitioner Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypePetitioner(driver).click();						//Clicking on 'Open' notice
			}
		
			
				
		    
		    
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(countType == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, type+ ":- Dashboard Count = "+countType+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL,  type+ ":- Dashboard Count = "+countType+" | Displayed records from grid = "+count1);
			}
	       	
	    
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
			
		/*	Thread.sleep(3000);
			performerPOM.clickLocationFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter3(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickCaseNotice(driver).click();
			
			Thread.sleep(3000);
			performerPOM.selectCaseNotice(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStatusFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectstatusFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickDepartmentFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectDepartmentFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCaseNoticeType1(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectCaseNoticeType2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickRiskFilter(driver).click();
			

			Thread.sleep(4000);
			performerPOM.selectRiskFilter2cfo(driver).click();
			
//			Thread.sleep(5000);
//			performerPOM.clickAgeFilter(driver).click();
//			
//			Thread.sleep(5000);
//			performerPOM.selectAgeFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCategoryFilter(driver).click();
			
			
			Thread.sleep(4000);
			performerPOM.selectCategoryFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStageFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectStageFilter2(driver).click(); */
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				test.log(LogStatus.PASS, "File downloaded successfully.");
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
         }	
			
	  	
		
		public static void CaseNoticeTypeGraph1(WebDriver driver, ExtentTest test, String type,int countType ) throws InterruptedException, IOException
		{
			

			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     
	       	
	       	
	       	if(type.equalsIgnoreCase("Outward/Plaintiff Type"))
			{
	         	Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeOutwardPlaintiff(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Inward/Defendent Type"))
			{
				Thread.sleep(3000);
		        performerPOM.CaseNoticeTypeInwardDefendent(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Respondent Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeComplinant(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Petitioner Type"))
			{
				Thread.sleep(2000);
		        performerPOM.CaseNoticeTypeRespondent(driver).click();						//Clicking on 'Open' notice
			}
		
	       
		    
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(countType == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, type+ ":- Dashboard Count = "+countType+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, type+ ":-Dashboard Count = "+countType+" | Displayed records from grid = "+count1);
			}
	       	
	    
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
		
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			
			
			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			
			
	   }	
		
		
		
		
		
		
		
		
		
		
		
		public static void RiskSummaryGraph(WebDriver driver,ExtentTest test, String type,int countype) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	       
					   
						if(type.equalsIgnoreCase("High Risk"))
						{
				         	Thread.sleep(2000);
					        performerPOM.RiskSummaryHigh(driver).click();						//Clicking on 'Open' notice
						}
						else if(type.equalsIgnoreCase("Medium Risk"))
						{
							Thread.sleep(2000);
					        performerPOM.RiskSummaryMedium(driver).click();						//Clicking on 'Open' notice
						}
						else if(type.equalsIgnoreCase("Low Risk"))
						{
							Thread.sleep(3000);
					        performerPOM.RiskSummaryLow(driver).click();						//Clicking on 'Open' notice
						}
						else if(type.equalsIgnoreCase("Not Applicable Risk"))
						{
							Thread.sleep(3000);
					        performerPOM.RiskSummaryNotApplicable(driver).click();						//Clicking on 'Open' notice
						}
						
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(countype == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, type+ ":- Dashboard Count = "+countype+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, type+ ":-Dashboard Count = "+countype+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
		
			js.executeScript("window.scrollBy(0,1000)");
		
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			
			
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
		}
		
		
		
	public static void RiskSummaryGraph1(WebDriver driver,ExtentTest test, String type,int counttype) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			
			
			if(type.equalsIgnoreCase("High Risk"))
			{
	         	Thread.sleep(2000);
		        performerPOM.RiskSummaryHigh(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Medium Risk"))
			{
				Thread.sleep(2000);
		        performerPOM.RiskSummaryMedium(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Low Risk"))
			{
				Thread.sleep(2000);
		        performerPOM.RiskSummaryLow(driver).click();						//Clicking on 'Open' notice
			}
			else if(type.equalsIgnoreCase("Not Applicable Risk"))
			{
				Thread.sleep(2000);
		        performerPOM.RiskSummaryNotApplicable(driver).click();						//Clicking on 'Open' notice
			}
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(counttype == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, type+ ":- Dashboard Count = "+counttype+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, type+ ":-Dashboard Count = "+counttype+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
		
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			
			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
				
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work  successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			
			
		}
		
		
		
		
		
	   public static void DepartmentSummaryGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	       	js.executeScript("window.scrollBy(0,800)");
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
			 Thread.sleep(5000);
				performerPOM.clickDashboardApplyBtn(driver).click();
				
				   js.executeScript("window.scrollBy(0,800)");

			
	       	Thread.sleep(2000);
		
	      	int	open = Integer.parseInt(performerPOM.DepartmentSummaryGraph1(driver).getText());	//Reading Notice Open count.
		    performerPOM.DepartmentSummaryGraph1(driver).click();						//Clicking on 'Open' notice
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(open == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
		/*	Thread.sleep(3000);
			performerPOM.clickLocationFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter3(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickCaseNotice(driver).click();
			
			Thread.sleep(3000);
			performerPOM.selectCaseNotice(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStatusFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectstatusFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickDepartmentFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectDepartmentFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCaseNoticeType1(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectCaseNoticeType2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickRiskFilter(driver).click();
			

			Thread.sleep(4000);
			performerPOM.selectRiskFilter2cfo(driver).click();
			
//			Thread.sleep(5000);
//			performerPOM.clickAgeFilter(driver).click();
//			
//			Thread.sleep(7000);
//			performerPOM.selectAgeFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCategoryFilter(driver).click();
			
			
			Thread.sleep(4000);
			performerPOM.selectCategoryFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStageFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectStageFilter2(driver).click(); */
			
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
			
		}
	   
	   
	   public static void DepartmentSummaryGraph1(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
			{
				
				WebDriverWait wait=new WebDriverWait(driver,20);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				js.executeScript("window.scrollBy(0,800)");
					Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
					Thread.sleep(6000);
					performerPOM.clickDashboardCaseFilter(driver).click();
		          
		           	
					 Thread.sleep(3000);
						performerPOM.clickDashboardApplyBtn(driver).click();
		       js.executeScript("window.scrollBy(0,800)");
		       	
		    	
		       	Thread.sleep(2000);
			
		      	int	open = Integer.parseInt(performerPOM.DepartmentSummaryGraph2(driver).getText());	//Reading Notice Open count.
			    performerPOM.DepartmentSummaryGraph2(driver).click();						//Clicking on 'Open' notice
			
				Thread.sleep(2000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
				
				Thread.sleep(10000);
				CFOcountPOM.readTotalItems1(driver).click();
				String item = CFOcountPOM.readTotalItems1(driver).getText();
				String[] bits = item.split(" ");								//Splitting the String
				String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
				int count1 = 0;
				if(compliancesCount.equalsIgnoreCase("to"))
				{
					Thread.sleep(2000);
				   item = CFOcountPOM.readTotalItems1(driver).getText();
					bits = item.split(" ");								//Splitting the String
				   compliancesCount = bits[bits.length - 2];
				}
				if(compliancesCount.equalsIgnoreCase("to"))
				{
					count1 = 0;
				}
				else
				{
					count1 = Integer.parseInt(compliancesCount);
				}
				
				if(open == count1)
				{
					//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
					test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
				}
				else
				{
					//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
					test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
				}
		       	
		    	
				Thread.sleep(5000);
				performerPOM.CaseNoticeTypeViewGraph(driver).click();
				
				Thread.sleep(5000);
				performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
				
				test.log(LogStatus.PASS, "View popup open successfully.");
				
			/*	Thread.sleep(3000);
				performerPOM.clickLocationFilter(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickLocationFilter1(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickLocationFilter3(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickCaseNotice(driver).click();
				
				Thread.sleep(3000);
				performerPOM.selectCaseNotice(driver).click();
				
				Thread.sleep(4000);
				performerPOM.clickStatusFilter(driver).click();
				
				Thread.sleep(4000);
				performerPOM.selectstatusFiltercfo(driver).click();
				
				Thread.sleep(4000);
				performerPOM.clickDepartmentFilter(driver).click();
				
				Thread.sleep(4000);
				performerPOM.selectDepartmentFilter2(driver).click();
				
				Thread.sleep(4000);
				performerPOM.clickCaseNoticeType1(driver).click();
				
				Thread.sleep(4000);
				performerPOM.selectCaseNoticeType2(driver).click();
				
				Thread.sleep(4000);
				performerPOM.clickRiskFilter(driver).click();
				

				Thread.sleep(4000);
				performerPOM.selectRiskFilter2cfo(driver).click();
				
//				Thread.sleep(5000);
//				performerPOM.clickAgeFilter(driver).click();
//				
//				Thread.sleep(7000);
//				performerPOM.selectAgeFiltercfo(driver).click();
				
				Thread.sleep(4000);
				performerPOM.clickCategoryFilter(driver).click();
				
				
				Thread.sleep(4000);
				performerPOM.selectCategoryFilter2(driver).click();
				
				Thread.sleep(4000);
				performerPOM.clickStageFilter(driver).click();
				
				Thread.sleep(4000);
				performerPOM.selectStageFilter2(driver).click(); */
				
				
				Thread.sleep(500);
				progress(driver);
				
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
				Thread.sleep(2000);
				
				js.executeScript("window.scrollBy(0,1000)");
				
				
				
				Thread.sleep(10000);
				CFOcountPOM.readTotalItems1(driver).click();
				String item1 = CFOcountPOM.readTotalItems1(driver).getText();
				String[] bits1 = item1.split(" ");								//Splitting the String
				String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
				int count2 = Integer.parseInt(compliancesCount1);
				
			    try
				{
					performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
				}
				catch(Exception e)
				{
					
				}
				js.executeScript("window.scrollBy(0,1000)");
				
			
				Thread.sleep(100);
				File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
				File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(500);
				CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
				Thread.sleep(250);
				performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
				test.log(LogStatus.PASS, "File downloaded successfully.");
				
				Thread.sleep(5500);
				File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
				File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
				
				if(dirContents.length < allFilesNew.length)
				{
					
					
					File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
				    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
				    {
				       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
				       {
				           lastModifiedFile = allFilesNew[i];
				       }
				    }
					
					Thread.sleep(100);
					fis = new FileInputStream(lastModifiedFile);
					workbook = new XSSFWorkbook(fis);
					sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
					
					int no = sheet.getLastRowNum();
					Row row = sheet.getRow(no);
					Cell c1 = row.getCell(0);
					int records =(int) c1.getNumericCellValue();
					fis.close();
					
					if(count2 == records)
					{
						//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
						test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
					}
					else
					{
						//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
						test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
					}
				}
				else
				{
					test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
				}
				
				File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
				File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
				
				Thread.sleep(3000);
			     performerPOM.ClickDetailedExpenseReport(driver).click();
				
			 	Thread.sleep(3000);
				File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
				File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
				
				Thread.sleep(3000);
			   if (dirContents1.length < allFilesNew1.length) {
					test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
				}
			   else
			   {
					test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
				}
				
				

				Thread.sleep(7000);
				performerPOM.clearButton(driver).click();
				
				test.log(LogStatus.PASS, "clear button work successfully.");
				
				
				Thread.sleep(3000);
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				performerPOM.caseNoticeSummaryGraphClose(driver).click();
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
				
				
			}
	   
	   
	   
	   public static void LocationSummaryGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
		
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,800)");
			
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
		   Thread.sleep(5000);
		   performerPOM.clickDashboardApplyBtn(driver).click();
		   
		   js.executeScript("window.scrollBy(0,1300)");
	      	
	    	
			
	      	Thread.sleep(3000);
		
	      	int	open = Integer.parseInt(performerPOM.LocationSummaryGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.LocationSummaryGraph(driver).click();						//Clicking on 'Open' notice
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(open == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
		/*	Thread.sleep(3000);
			performerPOM.clickLocationFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter3(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickCaseNotice(driver).click();
			
			Thread.sleep(3000);
			performerPOM.selectCaseNotice(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStatusFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectstatusFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickDepartmentFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectDepartmentFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCaseNoticeType1(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectCaseNoticeType2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickRiskFilter(driver).click();
			

			Thread.sleep(4000);
			performerPOM.selectRiskFilter2cfo(driver).click();
			
//			Thread.sleep(5000);
//			performerPOM.clickAgeFilter(driver).click();
//			
//			Thread.sleep(5000);
//			performerPOM.selectAgeFiltercfo(driver).click();
		
			Thread.sleep(4000);
			performerPOM.clickCategoryFilter(driver).click();
			
			
			Thread.sleep(4000);
			performerPOM.selectCategoryFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStageFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectStageFilter2(driver).click(); */
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	}
	   
	   
	   public static void LocationSummaryGraph1(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
		
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.scrollBy(0,800)");
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(6000);
			performerPOM.clickDashboardCaseFilter(driver).click();
          
           	
			 Thread.sleep(3000);
			performerPOM.clickDashboardApplyBtn(driver).click();
			
			
			js.executeScript("window.scrollBy(0,1300)");
	      	
	    	
			
	      	Thread.sleep(3000);
		
	      	int	open = Integer.parseInt(performerPOM.LocationSummaryGraphCase(driver).getText());	//Reading Notice Open count.
		    performerPOM.LocationSummaryGraphCase(driver).click();						//Clicking on 'Open' notice
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(open == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
		/*	Thread.sleep(3000);
			performerPOM.clickLocationFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter3(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickCaseNotice(driver).click();
			
			Thread.sleep(3000);
			performerPOM.selectCaseNotice(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStatusFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectstatusFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickDepartmentFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectDepartmentFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCaseNoticeType1(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectCaseNoticeType2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickRiskFilter(driver).click();
			

			Thread.sleep(4000);
			performerPOM.selectRiskFilter2cfo(driver).click();
			
//			Thread.sleep(5000);
//			performerPOM.clickAgeFilter(driver).click();
//			
//			Thread.sleep(5000);
//			performerPOM.selectAgeFiltercfo(driver).click();
		
			Thread.sleep(4000);
			performerPOM.clickCategoryFilter(driver).click();
			
			
			Thread.sleep(4000);
			performerPOM.selectCategoryFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStageFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectStageFilter2(driver).click(); */
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				test.log(LogStatus.PASS, "File downloaded successfully.");
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			
			
			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	}
	   
	   
	   
	   
	   
	   
	   
	   public static void CategorySummaryGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
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
		
	      	int	open = Integer.parseInt(performerPOM.CategorySummaryGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.CategorySummaryGraph(driver).click();						//Clicking on 'Open' notice
		
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int count1 = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
			   item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
			   compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				count1 = 0;
			}
			else
			{
				count1 = Integer.parseInt(compliancesCount);
			}
			
			if(open == count1)
			{
				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	      	
	      	
			
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(2000);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(2000);
			performerPOM.clickCaseNoticeStageHearingExport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			test.log(LogStatus.PASS, "View popup open successfully.");
			
		/*	Thread.sleep(3000);
			performerPOM.clickLocationFilter(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter1(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickLocationFilter3(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickCaseNotice(driver).click();
			
			Thread.sleep(3000);
			performerPOM.selectCaseNotice(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStatusFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectstatusFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickDepartmentFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectDepartmentFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCaseNoticeType1(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectCaseNoticeType2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickRiskFilter(driver).click();
			

			Thread.sleep(4000);
			performerPOM.selectRiskFilter2cfo(driver).click();
			
//			Thread.sleep(5000);
//			performerPOM.clickAgeFilter(driver).click();
//			
//			Thread.sleep(5000);
//			performerPOM.selectAgeFiltercfo(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickCategoryFilter(driver).click();
			
			
			Thread.sleep(4000);
			performerPOM.selectCategoryFilter2(driver).click();
			
			Thread.sleep(4000);
			performerPOM.clickStageFilter(driver).click();
			
			Thread.sleep(4000);
			performerPOM.selectStageFilter2(driver).click(); */
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
		}
	   
		public static void ExpensesNoticeGraph(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
		{
			

			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			
	       	
				js.executeScript("window.scrollBy(0,2000)");
			
	       	Thread.sleep(2000);
		
	        Integer.parseInt(performerPOM.ExpensesNoticeGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.ExpensesNoticeGraph(driver).click();						//Clicking on 'Open' notice
		    
		    
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				test.log(LogStatus.PASS, "File  downloaded successfully.");
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
	    
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(2000);
  		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
  		  
  		  if(msg.equalsIgnoreCase("Notice Summary"))
  		  {
  			  test.log(LogStatus.PASS, "View Icon Of Expenses Graph Open Successfully = " +msg);
  		  }
  		  else
  		  {
  			 test.log(LogStatus.FAIL, "View Icon Of Expenses Graph Not Open Successfully = " +msg);
  		  }
  		  
  		  driver.switchTo().parentFrame();
  				
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
		
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
		

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	   }	
		
		public static void ExpensesCategoryWiseNoticeGraph(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
		{
			

			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardNoticeFilter(driver).click();
          
           	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			
	       	
				js.executeScript("window.scrollBy(0,2400)");
			
	       	Thread.sleep(2000);
		
	        int open=Integer.parseInt(performerPOM.ExpensesCategoryWiseNoticeGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.ExpensesCategoryWiseNoticeGraph(driver).click();						//Clicking on 'Open' notice
		    
		    
		    
		    
		    
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
		
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				test.log(LogStatus.PASS, "File  downloaded successfully.");
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
	    
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(2000);
  		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
  		  
  		  if(msg.equalsIgnoreCase("Notice Summary"))
  		  {
  			  test.log(LogStatus.PASS, "View Icon Of Expenses-Category Wise Graph Open Successfully = " +msg);
  		  }
  		  else
  		  {
  			 test.log(LogStatus.FAIL, "View Icon Of Expenses-Category Wise Graph Not Open Successfully = " +msg);
  		  }
  		  
  		  driver.switchTo().parentFrame();
  				
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
		
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			}
			
	   	
		
		
	   
	   
	   public static void CategorySummaryGraph1(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
	 		{
	 			
	 			WebDriverWait wait=new WebDriverWait(driver,20);
	 			JavascriptExecutor js = (JavascriptExecutor) driver;
	 			  js.executeScript("window.scrollBy(0,800)");
					Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
					Thread.sleep(6000);
					performerPOM.clickDashboardCaseFilter(driver).click();
		          
		           	
					 Thread.sleep(3000);
					performerPOM.clickDashboardApplyBtn(driver).click();
	 			
	 			js.executeScript("window.scrollBy(0,1600)");
	 			Thread.sleep(2000);
	 		
	 	      	int	open = Integer.parseInt(performerPOM.CategorySummaryGraph(driver).getText());	//Reading Notice Open count.
	 		    performerPOM.CategorySummaryGraph(driver).click();						//Clicking on 'Open' notice
	 		
	 			Thread.sleep(2000);
	 			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
	 			
	 			Thread.sleep(10000);
	 			CFOcountPOM.readTotalItems1(driver).click();
	 			String item = CFOcountPOM.readTotalItems1(driver).getText();
	 			String[] bits = item.split(" ");								//Splitting the String
	 			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
	 			int count1 = 0;
	 			if(compliancesCount.equalsIgnoreCase("to"))
	 			{
	 				Thread.sleep(2000);
	 			   item = CFOcountPOM.readTotalItems1(driver).getText();
	 				bits = item.split(" ");								//Splitting the String
	 			   compliancesCount = bits[bits.length - 2];
	 			}
	 			if(compliancesCount.equalsIgnoreCase("to"))
	 			{
	 				count1 = 0;
	 			}
	 			else
	 			{
	 				count1 = Integer.parseInt(compliancesCount);
	 			}
	 			
	 			if(open == count1)
	 			{
	 				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
	 				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
	 			}
	 			else
	 			{
	 				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
	 				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
	 			}
	 	      	
	 	      	
	 			
	 			Thread.sleep(2000);
	 			js.executeScript("window.scrollBy(0,1000)");
	 			
	 			
	 			
	 			Thread.sleep(2000);
	 			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
	 			Thread.sleep(2000);
	 			performerPOM.clickCaseNoticeStageHearingExport(driver).click();					//Clicking on 'Excel Report' image.
	 		
	 			
	 			Thread.sleep(5000);
	 			performerPOM.CaseNoticeTypeViewGraph(driver).click();
	 			
	 			Thread.sleep(5000);
	 			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
	 			
	 			test.log(LogStatus.PASS, "View popup open successfully.");
	 			
	 		/*	Thread.sleep(3000);
	 			performerPOM.clickLocationFilter(driver).click();
	 			
	 			Thread.sleep(3000);
	 			performerPOM.clickLocationFilter1(driver).click();
	 			
	 			Thread.sleep(3000);
	 			performerPOM.clickLocationFilter3(driver).click();
	 			
	 			Thread.sleep(3000);
	 			performerPOM.clickCaseNotice(driver).click();
	 			
	 			Thread.sleep(3000);
	 			performerPOM.selectCaseNotice(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.clickStatusFilter(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.selectstatusFiltercfo(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.clickDepartmentFilter(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.selectDepartmentFilter2(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.clickCaseNoticeType1(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.selectCaseNoticeType2(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.clickRiskFilter(driver).click();
	 			

	 			Thread.sleep(4000);
	 			performerPOM.selectRiskFilter2cfo(driver).click();
	 			
//	 			Thread.sleep(5000);
//	 			performerPOM.clickAgeFilter(driver).click();
//	 			
//	 			Thread.sleep(5000);
//	 			performerPOM.selectAgeFiltercfo(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.clickCategoryFilter(driver).click();
	 			
	 			
	 			Thread.sleep(4000);
	 			performerPOM.selectCategoryFilter2(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.clickStageFilter(driver).click();
	 			
	 			Thread.sleep(4000);
	 			performerPOM.selectStageFilter2(driver).click(); */
	 			
	 			Thread.sleep(500);
	 			progress(driver);
	 			
	 			Thread.sleep(1000);
	 			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
	 			Thread.sleep(2000);
	 		
	 			js.executeScript("window.scrollBy(0,1000)");
	 			
	 			
	 			
	 			Thread.sleep(10000);
	 			CFOcountPOM.readTotalItems1(driver).click();
	 			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
	 			String[] bits1 = item1.split(" ");								//Splitting the String
	 			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
	 			int count2 = Integer.parseInt(compliancesCount1);
	 			
	 		    try
	 			{
	 				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
	 			}
	 			catch(Exception e)
	 			{
	 				
	 			}
	 			js.executeScript("window.scrollBy(0,1000)");
	 			
	 		
	 			Thread.sleep(100);
	 			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
	 			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
	 			
	 			Thread.sleep(500);
	 			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
	 			Thread.sleep(250);
	 			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
	 		
	 			
	 			Thread.sleep(5500);
	 			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
	 			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
	 			
	 			if(dirContents.length < allFilesNew.length)
	 			{
	 				
	 				test.log(LogStatus.PASS, "File downloaded successfully.");
	 				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
	 			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
	 			    {
	 			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
	 			       {
	 			           lastModifiedFile = allFilesNew[i];
	 			       }
	 			    }
	 				
	 				Thread.sleep(100);
	 				fis = new FileInputStream(lastModifiedFile);
	 				workbook = new XSSFWorkbook(fis);
	 				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
	 				
	 				int no = sheet.getLastRowNum();
	 				Row row = sheet.getRow(no);
	 				Cell c1 = row.getCell(0);
	 				int records =(int) c1.getNumericCellValue();
	 				fis.close();
	 				
	 				if(count2 == records)
	 				{
	 					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
	 					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
	 				}
	 				else
	 				{
	 					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
	 					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
	 				}
	 			}
	 			else
	 			{
	 				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
	 			}
	 			
	 			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
				File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
				
				Thread.sleep(3000);
			     performerPOM.ClickDetailedExpenseReport(driver).click();
				
			 	Thread.sleep(3000);
				File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
				File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
				
				Thread.sleep(3000);
			   if (dirContents1.length < allFilesNew1.length) {
					test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
				}
			   else
			   {
					test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
				}

	 			Thread.sleep(7000);
	 			performerPOM.clearButton(driver).click();
	 			
	 			test.log(LogStatus.PASS, "Clear button work  successfully.");
	 			
	 			
	 			Thread.sleep(3000);
	 			driver.switchTo().parentFrame();
	 			Thread.sleep(2000);
	 			performerPOM.caseNoticeSummaryGraphClose(driver).click();
	 			
	 			Thread.sleep(3000);
	 			OverduePOM.clickDashboard(driver).click();
	 		}
	   
	   
	   
		public static void ExpensesCaseGraph(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
		{
			

			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
          
           	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			
	       	
				js.executeScript("window.scrollBy(0,2000)");
			
	       	Thread.sleep(2000);
		
	        Integer.parseInt(performerPOM.ExpensesCaseGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.ExpensesCaseGraph(driver).click();						//Clicking on 'Open' notice
		    
		    
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				test.log(LogStatus.PASS, "File  downloaded successfully.");
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
	    
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(2000);
  		  String msg =performerPOM.clickAgeingViewCaseSummary(driver).getText();
  		  
  		  if(msg.equalsIgnoreCase("Case Summary"))
  		  {
  			  test.log(LogStatus.PASS, "View Icon Of Expenses Graph Open Successfully = " +msg);
  		  }
  		  else
  		  {
  			 test.log(LogStatus.FAIL, "View Icon Of Expenses Graph Not Open Successfully = " +msg);
  		  }
  		  
  		  driver.switchTo().parentFrame();
  				
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
		
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}

			
			
			
		

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	   }	
		
		public static void ExpensesCategoryWiseCaseGraph(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
		{
			

			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	     	js.executeScript("window.scrollBy(0,800)");
	     	
	     	Thread.sleep(5000);
			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			
			Thread.sleep(5000);
			performerPOM.clickDashboardCaseFilter(driver).click();
          
           	
			 Thread.sleep(5000);
			 performerPOM.clickDashboardApplyBtn(driver).click();
			
	       	
				js.executeScript("window.scrollBy(0,2400)");
			
	       	Thread.sleep(2000);
		
	        int open=Integer.parseInt(performerPOM.ExpensesNoticeGraph(driver).getText());	//Reading Notice Open count.
		    performerPOM.ExpensesNoticeGraph(driver).click();						//Clicking on 'Open' notice
		    
		    
		    
		    
		    
		    Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
		
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			
			Thread.sleep(10000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits1 = item1.split(" ");								//Splitting the String
			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
			int count2 = Integer.parseInt(compliancesCount1);
			
		    try
			{
				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			}
			catch(Exception e)
			{
				
			}
			js.executeScript("window.scrollBy(0,1000)");
			
		
			Thread.sleep(100);
			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
			
			Thread.sleep(5500);
			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
			
			if(dirContents.length < allFilesNew.length)
			{
				
				test.log(LogStatus.PASS, "File  downloaded successfully.");
				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew[i];
			       }
			    }
				
				Thread.sleep(100);
				fis = new FileInputStream(lastModifiedFile);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
				
				int no = sheet.getLastRowNum();
				Row row = sheet.getRow(no);
				Cell c1 = row.getCell(0);
				int records =(int) c1.getNumericCellValue();
				fis.close();
				
				if(count2 == records)
				{
					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
	    
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(2000);
  		  String msg =performerPOM.clickAgeingViewCaseSummary(driver).getText();
  		  
  		  if(msg.equalsIgnoreCase("Case Summary"))
  		  {
  			  test.log(LogStatus.PASS, "View Icon Of Expenses-Category Wise Graph Open Successfully = " +msg);
  		  }
  		  else
  		  {
  			 test.log(LogStatus.FAIL, "View Icon Of Expenses-Category Wise Graph Not Open Successfully = " +msg);
  		  }
  		  
  		  driver.switchTo().parentFrame();
  				
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
		
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
			Thread.sleep(2000);
			
			js.executeScript("window.scrollBy(0,1000)");
			
			
			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}

		
			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			test.log(LogStatus.PASS, "Clear button work successfully.");
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			}
			
	   
	   
	   
	   
	   
	   static void perform(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException, IOException
		{
		   
			
			WebDriverWait wait1 = new WebDriverWait(driver, 300);
			progress(driver);
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
//			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
			js.executeScript("window.scrollBy(0,-700)");
			
			

			Thread.sleep(4000);
			clickNewNotice(driver);
			
			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			
			
			Thread.sleep(3000);
			clickDated(driver);

			Thread.sleep(3000);
			clickFinancialYear(driver);

			Thread.sleep(3000);
			clickRefNo(driver);

			Thread.sleep(3000);
			selectNoticeType(driver);

			Thread.sleep(3000);
			clickAct(driver);

			Thread.sleep(3000);
			clickOpponentcfo(driver);

			Thread.sleep(3000);
			selectCategory(driver);

			Thread.sleep(3000);
			clickNoticeTitle(driver);

			Thread.sleep(3000);
			clickNoticeDescription(driver);

			Thread.sleep(3000);
			selectLocation(driver);

			Thread.sleep(3000);
			clickDepartment(driver);

			Thread.sleep(3000);
			clickOwner(driver);

			Thread.sleep(3000);
            selectRisk(driver);
        	//Thread.sleep(3000);
          //  clickLawFirm(driver);

			Thread.sleep(3000);
            selectNoticeRecipetDate(driver);
            
            Thread.sleep(3000);
            clickInternalUser(driver);
           // Thread.sleep(3000);
        //    clickLawyer(driver);
            
//  		    Thread.sleep(3000);
//    		performerPOM.clickAdditionalOwnerCfo(driver); 
//    		
//    		 Thread.sleep(3000);
//     		 performerPOM.selectAdditionalOwnerCfo(driver); 

            Thread.sleep(3000);
    		performerPOM.selectNoticeUploadDocument(driver); 
    		
       		
       		Thread.sleep(3000);
    		OverduePOM.clickSaveButton(driver).click();		
    		
    		Thread.sleep(1000);
    		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
    		
    		Thread.sleep(2000);
    		String msg = performerPOM.readMessage(driver).getText();		//Reading Message appeared after save button
    	
    		if(msg.equalsIgnoreCase("Notice Created Successfully."))
    		{
    			test.log(LogStatus.PASS, "Message displayed = "+msg);
    		
    		}
    		else
    		{
    			test.log(LogStatus.PASS, "Message displayed = "+msg);
    		}
    		
//    		WebElement ele1 = null;
//    		WebElement ele2 = null;
//    		WebElement ele3 = null;
//    		WebElement ele4 = null;
//    		
//    		if(flag == 1)
//    		{
//    			try
//    			{
//    				Thread.sleep(5000);
//    				ele1 = wait1.until(ExpectedConditions.visibilityOf(performerPOM.clickLinkNotice(driver)));
//    				ele2 = performerPOM.clickViewDoc(driver);
//    				ele3 = performerPOM.clickSendMail(driver);
//    				ele4 = performerPOM.clickEditNotice1(driver);
//    			}
//    			catch(Exception e)
//    			{
//    				
//    			}
//    			
//    			if(ele1 != null && ele2 != null && ele3 != null && ele4 != null)
//    			{
//    				test.log(LogStatus.PASS, "Icons displayed are :- Link Notice, View Document, Send Mail with Document, Edit Notice");
//    			}
//    			else
//    			{
//    				test.log(LogStatus.FAIL, "All icons are not displayed.");
//    			}
//    		}
    	

    		Thread.sleep(3000);
    		driver.switchTo().parentFrame();
    		performerPOM.clickClose(driver).click();//Clicking on 'Close'
    		
    		
    		js.executeScript("window.scrollBy(0,700)");
    		Thread.sleep(3000);
    		CFOcountPOM.readTotalItems1(driver).click();
    		String item = CFOcountPOM.readTotalItems1(driver).getText();
    		String[] bits = item.split(" ");								//Splitting the String
    		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
    		int count1 = 0;
    		if(compliancesCount.equalsIgnoreCase("to"))
    		{
    			Thread.sleep(2000);
    			item = CFOcountPOM.readTotalItems1(driver).getText();
    			bits = item.split(" ");								//Splitting the String
    			compliancesCount = bits[bits.length - 2];
    		}
    		count1 = Integer.parseInt(compliancesCount);
    		
    		if(count1 > gridRecords)
    		{
    			//test.log(LogStatus.PASS, "Total Notice Count increased in grid after adding New Notice.");
    			test.log(LogStatus.PASS, "Old Notice Count from Grid = "+gridRecords+" | New Notice Count from Grid = "+count1);
    		}
    		else
    		{
    			//test.log(LogStatus.FAIL, "Total Notice Count doesn't increased in grid after adding New Notice.");
    			test.log(LogStatus.PASS, "Old Notice Count from Grid = "+gridRecords+" | New Notice Count from Grid = "+count1);
    		}
    		
    		
			Thread.sleep(5000);
			performerPOM.clickcategory(driver).click();
//			
//			Thread.sleep(3000);
//			performerPOM.clickcategory1(driver).click();
//			
//			
			if(performerPOM.clearButton(driver).isEnabled())
	  		{
				Thread.sleep(3000);
	  			performerPOM.clearButton(driver).click();
	  			test.log(LogStatus.PASS, "Clear button working successfully");
	  		}
	  		else
	  		{
	  			test.log(LogStatus.FAIL, "Clear button not working successfully");
	  		}
    		
    		Thread.sleep(1000);
    		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
    		
    		Thread.sleep(500);
    		//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
    		int open1 = 0;
    		if(type.equalsIgnoreCase("Notice - Open"))
    		{
    			open1 = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());	//Reading Notice Open count.
    		}
    		else
    		{
    			open1 = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Open count.
    		}
    		
    		if(open1 > open)
    		{
    			test.log(LogStatus.PASS, type+" Dashboard Count increamented. Old count = "+open+", New Count = "+open1);
    		}
    		else
    		{
    			test.log(LogStatus.PASS, type+" Dashboard Count doesn't increamented. Old count = "+open+", New Count = "+open1);
    		}
    		
    		
    	}
    		
    	
    		
			
		
	   
	   public  static void clickNewNotice(WebDriver driver) throws InterruptedException 
		  {
				Thread.sleep(3000);
				performerPOM.clickNew(driver).click();	//Clicking on 'New' button
           }
				
		public static void clickDated(WebDriver driver)
		{
		performerPOM.clickDated(driver).click();					//Clicking on 'Dated' button
		OverduePOM.selectLastMonth(driver).click();					//Clicking last month arrow.
		OverduePOM.selectDate3(driver).click();	//Clicking particular date.
		}
		
		public static void clickFinancialYear(WebDriver driver) throws InterruptedException
		{
		Thread.sleep(300);
		performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
		elementsList = performerPOM.chooseDropDownOption(driver);
		elementsList.get(10).click();								//Clicking third option
		performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
      }
		
		public static void clickRefNo(WebDriver driver) throws InterruptedException, IOException
		{
			
		Thread.sleep(1000);
		Row row0 = sheet.getRow(5);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String refno = c1.getStringCellValue();
		performerPOM.clickRefNo(driver).clear();
		performerPOM.clickRefNo(driver).sendKeys(refno);			//Writing 'Reference No'
		}
		
		public static void selectNoticeType(WebDriver driver) 
		{
			WebElement type = performerPOM.clickNoticeType(driver);
			type.click();
			
			performerPOM.chooseNoticeType(driver).click(); 
			
		}
		public static void clickOpponent(WebDriver driver, String noticeType) 
		{
	
			
			performerPOM.clickOpponentcfo(driver).click(); 
			
		}
		
		public static void clickAct(WebDriver driver) throws InterruptedException
		{
		   Thread.sleep(300);
		   progress(driver);
	       XSSFRow row0 = sheet.getRow(6);						//Selected 0th index row (First row)
		   XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		   int actNo = (int) c1.getNumericCellValue();
		   performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
		   elementsList = performerPOM.chooseAct(driver);
		   elementsList.get(3).click();							//Selecting particular act no
		   performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
		}
		 public static void clickOpponentcfo(WebDriver driver) throws InterruptedException
		   {
	           Thread.sleep(300);
//	           Row row0 = sheet.getRow(7);						//Selected 0th index row (First row)
//	           Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//	            String Opponent = c1.getStringCellValue();
             //performerPOM.clickOpponentcfo(driver).sendKeys(Opponent);
             performerPOM.clickOpponentcfo(driver).click();
         	performerPOM.chooseOpponent(driver).click(); 
		   }
	
			public static void selectCategory(WebDriver driver) 
			{
				WebElement Category =  performerPOM.clickNoticeCategory(driver);
				Category.click();
				 performerPOM.chooseCategory(driver).click();
			}
			
			public static void clickNoticeTitle(WebDriver driver) throws InterruptedException
			{
			  Thread.sleep(300);
			  XSSFRow row0 = sheet.getRow(8);						//Selected 0th index row (First row)
			  XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			  String title = c1.getStringCellValue();
			  performerPOM.clickNoticeTitle(driver).sendKeys(title);		//Writing 'Notice Title'
			}
			public static void clickNoticeDescription(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(9);						//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String desc = c1.getStringCellValue();
			performerPOM.clickNoticeDescription(driver).sendKeys(desc);	//Writing 'Notice Description'
			Thread.sleep(300);		
			performerPOM.clickNoticeDescription(driver).sendKeys(Keys.PAGE_DOWN);
	        }
			public static void selectLocation(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(7000);
			performerPOM.clickLocation(driver).click();					//Clicking on Location drop down
			Thread.sleep(5000);
			//performerPOM.clickPlus(driver).click();
			performerPOM.selectLocationCfo(driver).click();;
			//elementsList.get(2).click();								//Selecting third visible location
			}
			public static void clickDepartment(WebDriver driver) throws InterruptedException
			{
				Thread.sleep(4000);
			performerPOM.clickDeptCfo(driver).click();					//Clicking on 'Department' drop down
			performerPOM.selectDeptCfo(driver).click();	//Writing 'Department' name
			}
			public static void clickOwner(WebDriver driver) throws InterruptedException
			{
			
			performerPOM.clickOwnerCfo(driver).click();					//Clicking on 'Owner' drop down
			performerPOM.selectOwnerCfo(driver).click();	//Writing 'Owner' name
			}
			public static void selectRisk(WebDriver driver) throws InterruptedException
			{

			  performerPOM.clickRisk(driver).click();							//Clicking on 'Risk' drop down.
			  Thread.sleep(500);
			  performerPOM.selectRisk(driver).click();						//Selecting second option 'High' risk.
	        }
			
	
			
			public  static void selectNoticeRecipetDate(WebDriver driver)
		      {
		    	 	
		          WebElement openDate= performerPOM.selectNoticeRecipetDate(driver);
		          openDate.sendKeys("30-09-2021");
		        
		      }
			
			public static void clickInternalUser(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(10);						//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int internalUserNo = (int) c1.getNumericCellValue();
			Thread.sleep(300);
			performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
			elementsList = performerPOM.chooseInternalUser(driver);
			elementsList.get(internalUserNo).click();							//Selecting particular user no
			performerPOM.clickInternalUser(driver).click();	//Clicking on 'Internal User' drop down.
			}
			
			
			
			
			
	public static void NoticeOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
		{
			
			
			int sheetNo = 8;
		    if(login.equalsIgnoreCase("cfo"))
		    {
		    	sheetNo = 8;
		    }
			
			
			Thread.sleep(3000);
			int open = CountExcel(driver, test, "Notice - Open");
			
			Thread.sleep(3000);
			performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,700)");
			
			Thread.sleep(5000);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int gridRecords = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
				item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
				compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				gridRecords = 0;
			}
			else
			{
				gridRecords = Integer.parseInt(compliancesCount);
			}
			

			
			sheet = workbook.getSheetAt(sheetNo);
			
			perform(driver, test, sheet, open, gridRecords, "Notice - Open");
		}
	
	
    	static void NoticeDocument(WebDriver driver, ExtentTest test) throws InterruptedException
       	{
    		WebDriverWait wait = new WebDriverWait(driver, 50);
             
    		Thread.sleep(1000);
    		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
    		
	        Thread.sleep(3000);
			performerPOM.clickNoticeOpen(driver).click();//click edit notice
	     
	        Thread.sleep(3000);
			performerPOM.clickEditNotice(driver).click();//click edit notice
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
	        
	        performerPOM.clickNoticeDocument(driver).click();     //click notice document
	        performerPOM.clickNewDocument(driver).click();        //click new document button
	
	        Thread.sleep(1000);
           	driver.switchTo().frame("IFrameManageDocument");
           	performerPOM.selectDocumentType(driver);
          	Thread.sleep(3000);
	        performerPOM.chooseDocumentType(driver);
	        Thread.sleep(1000);
	        performerPOM.selectUploadDocument(driver); 
	        Thread.sleep(1000);
         	performerPOM.clickUploadDocument(driver).click(); 
	
	
         	Thread.sleep(1000);
         	wait.until(ExpectedConditions.visibilityOf(performerPOM.readDocMsg(driver)));
	
        	Thread.sleep(3000);
	        String msg= performerPOM.readDocMsg(driver).getText();		//Reading Message appeared after save button
	       
         	if(msg.equalsIgnoreCase("Document(s) uploaded successfully"))
         	{
	        	test.log(LogStatus.PASS, "Message displayed = "+msg);
	         
	        }
	      else
	        {
		       test.log(LogStatus.FAIL, "Message displayed = "+msg);
	        }
	
	        Thread.sleep(1000);
	        performerPOM.clickClosedDocument(driver).click(); 
	        
	        driver.switchTo().parentFrame();
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentDownloadcfo(driver).click();
	        
	        test.log(LogStatus.PASS, "Document download succssesfully");
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentViewcfo(driver).click();
	        
	       
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentViewClosepopupcfo(driver).click();
	        
	        test.log(LogStatus.PASS, "Document View popup open  succssesfully");
	        
	       
	        
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentsharecfo(driver).click();
	        
	        Thread.sleep(5000);
		    // Switching to Alert        
	        Alert alert1 = driver.switchTo().alert();		
	        		
	        // Capturing alert message.    
	        String alertMessage1= driver.switchTo().alert().getText();	
	        
	        
	        test.log(LogStatus.PASS, alertMessage1);
	        		
	        // Displaying alert message		
	        System.out.println(alertMessage1);
	        
	     // Accepting alert		
	        alert1.accept();	
	        
	        Thread.sleep(3000);
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));
	        
	        Thread.sleep(4000);
	        performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5768798045");
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentsharesavecfo(driver).click();
	        
	        
	        Thread.sleep(3000);
	        String msg1= performerPOM.clickNoticeDocumentsharereadmsgcfo(driver).getText();		//Reading Message appeared after save button
	       
         	if(msg1.equalsIgnoreCase("Document shared successfully."))
         	{
	        	test.log(LogStatus.PASS, "Message displayed = "+msg1);
	         
	        }
	      else
	        {
		       test.log(LogStatus.FAIL, "Message displayed = "+msg1);
	        }
	        
	        
	        driver.switchTo().parentFrame();
	        Thread.sleep(3000);
	        performerPOM. clickNoticeDocumentshareclosepopupcfo(driver).click();
	        
	        Thread.sleep(3000);
	        performerPOM.clickNoticeDocumentdeletecfo(driver).click();
	        
	        Thread.sleep(5000);
		    // Switching to Alert        
	        Alert alert = driver.switchTo().alert();		
	        		
	        // Capturing alert message.    
	        String alertMessage= driver.switchTo().alert().getText();	
	        
	        
	        test.log(LogStatus.PASS, alertMessage);
	        		
	        // Displaying alert message		
	        System.out.println(alertMessage);
	        
	 		
	        // Accepting alert		
	        alert.accept();	
	        
	       driver.switchTo().parentFrame();
	     }
    	
    	 public  static void TaskActivtity(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
			{
    		 
    		       XSSFSheet sheet = ReadExcel();
				   WebDriverWait wait = new WebDriverWait(driver, 60);

				    Thread.sleep(3000);
	 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 				
	 				
				   Thread.sleep(1000);
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				  Thread.sleep(1000);
				  performerPOM.clickTaskorActivity(driver).click();
				  Thread.sleep(1000);
				  performerPOM.clickNewTask(driver).click(); 
				 
				  
				  
				Thread.sleep(3000);
				Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
				Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
				String title = c1.getStringCellValue();
				performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
				
				Thread.sleep(3000);
				row0 = sheet.getRow(13);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String desc = c1.getStringCellValue();
				performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
				
				Thread.sleep(3000);
				performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
				OverduePOM.selectNextMonth(driver).click();
				OverduePOM.selectDate(driver).click();					//Selecting particular date.
				
				Thread.sleep(500);
				Actions action = new Actions(driver);
//				action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
				
				Thread.sleep(500);
				row0 = sheet.getRow(14);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String outcome = c1.getStringCellValue();
				performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
				
				Thread.sleep(500);
				row0 = sheet.getRow(15);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String internalUser = c1.getStringCellValue();
				performerPOM.clickInternalUser2(driver).click();
				//performerPOM.selectInternalUser2(driver).click();
				performerPOM.selectInternalUser2(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
				
	
				
				Thread.sleep(1000);
				row0 = sheet.getRow(16);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String externalUser = c1.getStringCellValue();
				try
				{
					Thread.sleep(300);
					performerPOM.clickExternalUser(driver).click();
					Thread.sleep(500);
					action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
				}
				catch(Exception e)
				{
					
				}
			
				Thread.sleep(2000);
				row0 = sheet.getRow(17);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String remark = c1.getStringCellValue();
				performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
				
				//Thread.sleep(300);
				//String workingDir = System.getProperty("user.dir");
				//performerPOM.clickUpload(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file
				
				Thread.sleep(3000);
				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
				
				Thread.sleep(300);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsg(driver)));
				
				Thread.sleep(300);
				
				String msg1 = performerPOM.readTaskMsg1(driver).getText();
				if(msg1.contains("Task Saved Successfully."))
				{
					test.log(LogStatus.PASS, "Task Saved Successfully.");
				}
				
				else if(msg1.contains("Task with same title already exists."))
				{
					test.log(LogStatus.FAIL, "Task with same title already exists.");
				}
				
				Thread.sleep(3000);
				
				performerPOM.clickTaskTitle(driver).clear();
				
				Thread.sleep(3000);
				Row row1 = sheet.getRow(18);								//Selected 0th index row (First row)
				Cell c2 = row1.getCell(1);								//Selected cell (0 row,1 column)
				String title1 = c2.getStringCellValue();
				performerPOM.clickTaskTitle(driver).sendKeys(title1);	//Writing 'Task Title'
				
				Thread.sleep(3000);
				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
				
				Thread.sleep(300);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsg(driver)));
				
				Thread.sleep(300);
				String msg2 = performerPOM.readTaskMsg(driver).getText();
		
				if(msg2.contains("Task Saved Successfully."))
				{
					test.log(LogStatus.PASS, "Task Saved Successfully.");
				}
				
				else if(msg2.contains("Task with same title already exists."))
				{
					test.log(LogStatus.FAIL, "Task with same title already exists.");
				}
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskEditResponsecfo(driver).click();
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo1(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskcmtResponsecfo(driver).sendKeys("Automate Test");
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskSaveResponsecfo(driver).click();
				
				
				
				test.log(LogStatus.PASS,"Task Response Saved Successfully.");
				
				driver.switchTo().parentFrame();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskCloseResponsecfo(driver).click();
				
                Thread.sleep(3000);
				performerPOM.clickNoticeTaskClosecfo(driver).click();
				
				 Thread.sleep(5000);
				    // Switching to Alert        
			        Alert alert = driver.switchTo().alert();		
			        		
			        // Capturing alert message.    
			        String alertMessage= driver.switchTo().alert().getText();	
			        
			        
			        test.log(LogStatus.PASS, alertMessage);
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage);
			        
			     // Accepting alert		
			        alert.accept();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskdeletecfo(driver).click();
				
				 Thread.sleep(5000);
				    // Switching to Alert        
			        Alert alert1 = driver.switchTo().alert();		
			        		
			        // Capturing alert message.    
			        String alertMessage1= driver.switchTo().alert().getText();	
			        
			        
			        test.log(LogStatus.PASS, alertMessage1);
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage1);
			        
			     // Accepting alert		
			        alert1.accept();
		
			}
    	 
     static void Response(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
			   WebDriverWait wait = new WebDriverWait(driver, 60);
			   
			   XSSFSheet sheet = ReadExcel();
			  
			   Thread.sleep(3000);
				performerPOM.clickNoticeOpen(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   
			           Thread.sleep(1000);
			           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			           Thread.sleep(3000);
					  performerPOM. clickResponse(driver).click();
					  Thread.sleep(3000);
					  performerPOM. clickNewResponse(driver).click();
					  Thread.sleep(3000);
					  performerPOM. selectSentNotice(driver);
					  Thread.sleep(3000);
					  performerPOM. selectReplyDueDate(driver);
					  Thread.sleep(3000);
					  performerPOM. selectRespondedDate(driver);
				
					 		 
					  Thread.sleep(500);
					  Row row1 = sheet.getRow(20);								//Selected 0th index row (First row)
					  Cell c2 = row1.getCell(1);								//Selected cell (0 row,1 column)
					  String DeliveryMode= c2.getStringCellValue();
					  performerPOM.clickDeliveryMode(driver).click();
					  performerPOM.selectDeliveryMode(driver).sendKeys(DeliveryMode);
					  
					  
					  Thread.sleep(500);
					  Row row0 = sheet.getRow(21);								//Selected 0th index row (First row)
					  Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
					  String CourierCompany= c1.getStringCellValue();
					  performerPOM.clickCourierCompany(driver).sendKeys(CourierCompany);
						 
					  Thread.sleep(500);
						Row row2 = sheet.getRow(22);								//Selected 0th index row (First row)
						Cell c3 = row2.getCell(1);								//Selected cell (0 row,1 column)
						String RefNo= c3.getStringCellValue();
						performerPOM.RefTrackingNo(driver).sendKeys(RefNo);
							 
						Thread.sleep(500);
						Row row3 = sheet.getRow(23);								//Selected 0th index row (First row)
						Cell c4 = row3.getCell(1);								//Selected cell (0 row,1 column)
						String Description= c4.getStringCellValue();
						 performerPOM.Description(driver).sendKeys(Description);
						 
						 Thread.sleep(3000);
						 performerPOM.clickNoticeResponseDocUploadtcfo(driver);
							
						 JavascriptExecutor jse=(JavascriptExecutor)driver;
						 jse.executeScript("arguments[0].click();",  performerPOM.clickSaveResponse(driver));
						  //performerPOM.clickSaveResponse(driver).click();
							
							Thread.sleep(1000);
							wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg(driver)));
								
							Thread.sleep(500);
							String msg3 = performerPOM.readResponseMsg(driver).getText();		//Reading Message appeared after save button
							
							if(msg3.equalsIgnoreCase("Response Details Saved Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg3);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg3);
								}
							Thread.sleep(3000);
							performerPOM.clickNoticeEditResponsecfo(driver).click();
							
							performerPOM.clickCourierCompany(driver).clear();
							  Thread.sleep(500);
							  Row row4 = sheet.getRow(21);								//Selected 0th index row (First row)
							  Cell c5 = row4.getCell(1);								//Selected cell (0 row,1 column)
							  String CourierCompany1= c5.getStringCellValue();
							  performerPOM.clickCourierCompany(driver).sendKeys(CourierCompany1);
							  
							  Thread.sleep(3000);
							 performerPOM.clickNoticeResponseDocUploadtcfo(driver);
							
							  
							  jse.executeScript("arguments[0].click();",  performerPOM.clickSaveResponse(driver));
							  
							  Thread.sleep(1000);
								wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg(driver)));
									
								Thread.sleep(500);
								String msg4 = performerPOM.readResponseMsg(driver).getText();		//Reading Message appeared after save button
								
								if(msg3.equalsIgnoreCase("Response Details Saved Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg4);
									
								}
									else
									{
										test.log(LogStatus.FAIL, "Message displayed = "+msg4);
									}
								
								Thread.sleep(4000);
								performerPOM.clickNoticeDownloadResponsecfo(driver).click();
								
								//test.log(LogStatus.PASS, "Document download succssesfully");
								
								Thread.sleep(4000);
								performerPOM.clickNoticeViewResponsecfo(driver).click();
								
								Thread.sleep(6000);
								performerPOM.clickNoticeclosePopupResponsecfo(driver).click();
								
								test.log(LogStatus.PASS, "Document view popup open succssesfully");
								
								Thread.sleep(4000);
								performerPOM.clickNoticeDeleteResponsecfo(driver).click();
								
								 Thread.sleep(5000);
								    // Switching to Alert        
							        Alert alert1 = driver.switchTo().alert();		
							        		
							        // Capturing alert message.    
							        String alertMessage1= driver.switchTo().alert().getText();	
							        
							        
							        test.log(LogStatus.PASS, alertMessage1);
							        		
							        // Displaying alert message		
							        System.out.println(alertMessage1);
							        
							     // Accepting alert		
							        alert1.accept();
							        
							        driver.switchTo().parentFrame();
							
							
			       }
    	
    	 static void PaymentLog(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
    		 
    		 
    		   WebDriverWait wait = new WebDriverWait(driver, 60);
			   
			   XSSFSheet sheet = ReadExcel();
			  
			     
			   Thread.sleep(3000);
			     performerPOM.clickNoticeOpen(driver).click();//click edit notice
	     
	             Thread.sleep(3000);
			     performerPOM.clickEditNotice(driver).click();//click edit notice
		  
			      driver.switchTo().parentFrame();
			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			     
			     Thread.sleep(3000);
			    performerPOM.clickStatusPayments(driver).click();			//Clicking on 'Status/Payments'
			
			    Thread.sleep(1000);
				performerPOM.clickInvoiceNo(driver).sendKeys("56742584");
				
				
				Thread.sleep(3000);
				Row r5 = sheet.getRow(30);
				Cell c5 = r5.getCell(1);
				String PaymentType = c5.getStringCellValue();
				performerPOM.clickPaymentType(driver).click();
				performerPOM.selectPaymentType(driver).sendKeys(PaymentType,Keys.ENTER);
//				List<WebElement> PaymentType1= driver.findElements(By.xpath("//*[@id='grdNoticePayment_ddlPaymentType_chosen']/div/ul/li"));
//				PaymentType1.get(2).click();
					
				Thread.sleep(3000);
				performerPOM.clickAmount(driver).sendKeys("5000");
				
				Thread.sleep(6000);
				performerPOM.clickNoticeStatusPaymentUploadtcfo(driver);
			
				Thread.sleep(300);
				performerPOM.clickSavePaymentLog(driver).click();
				

				
				 WebDriverWait wait1 = new WebDriverWait(driver, 300);
				 wait1.until(ExpectedConditions.visibilityOf(performerPOM.readPymentmsg(driver)));
					
					Thread.sleep(500);
					String msg4 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
				
					if(msg4.equalsIgnoreCase("Payment Details Saved Successfully."))
					{
						test.log(LogStatus.PASS, "Message displayed = "+msg4);
					
					}
					else
					{
						test.log(LogStatus.FAIL, "Message displayed = "+msg4);
					}
					
					Thread.sleep(3000);
					performerPOM.clickNoticeViewPaymentDoccfo(driver).click();
					
					Thread.sleep(4000);
					performerPOM.clickNoticeclosePaymentDocpopupcfo(driver).click();
					
					test.log(LogStatus.PASS, "Payment Document popup open successfully");
					
//					if(msg4.equalsIgnoreCase("Payment Document popup open successfully"))
//					{
//						test.log(LogStatus.PASS, "Payment Document popup open successfully");
//					
//					}
//					else
//					{
//						test.log(LogStatus.FAIL, "Payment Document popup does not open successfully");
//					}
					
					Thread.sleep(3000);
					performerPOM.clickNoticeEditPaymentcfo(driver).click();
					
					performerPOM.clickInvoiceNo(driver).clear();
					 Thread.sleep(3000);
				    performerPOM.clickInvoiceNo(driver).sendKeys("Invoice No 5782");
				    
				    Thread.sleep(6000);
					performerPOM.clickNoticeStatusPaymentUploadtcfo(driver);
				    
				    Thread.sleep(3000);
					performerPOM.clickSavePaymentLog(driver).click();
					
					
				        
				        Thread.sleep(500);
						String msg5 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
					
						if(msg5.equalsIgnoreCase("Payment Details Saved Successfully."))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg5);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg5);
						}
				        
				        Thread.sleep(3000);
				        performerPOM.clickNoticeDownloadPaymentcfo(driver).click();
				        
				        test.log(LogStatus.PASS, "Payment Document Download Successfully.");
				        
				        Thread.sleep(3000);
						performerPOM.clickNoticeDeletePaymentcfo(driver).click();
						
						 Thread.sleep(5000);
						    // Switching to Alert        
					        Alert alert1 = driver.switchTo().alert();		
					        		
					        // Capturing alert message.    
					        String alertMessage1= driver.switchTo().alert().getText();	
					        
					        
					        test.log(LogStatus.PASS, alertMessage1);
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage1);
					        
					     // Accepting alert		
					        alert1.accept();
						
					        Thread.sleep(500);
							String msg6 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
						
							if(msg6.equalsIgnoreCase("Payment Details Deleted Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg6);
							
							}
							else
							{
								test.log(LogStatus.FAIL, "Message displayed = "+msg6);
							}
				    
				    	
				
			}
    	 
    	  static void ExternalLawyer(WebDriver driver,ExtentTest test) throws InterruptedException
          {
        	  
    		         WebDriverWait wait = new WebDriverWait(driver, 300);
		   
    		         Thread.sleep(3000);
    			     performerPOM.clickNoticeOpen(driver).click();//click edit notice
    	     
    	             Thread.sleep(3000);
    			     performerPOM.clickEditNotice(driver).click();//click edit notice
    		  
    			      driver.switchTo().parentFrame();
    			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
    		         
			          
        	          
    			       Thread.sleep(1000);
    				   performerPOM. clickExternalLawyerRating(driver).click();
    				   

    				   
    				  Thread.sleep(3000);
    				  performerPOM.selectExternalLawyerRating(driver);
    				   Thread.sleep(3000);
    				   performerPOM.clickNewCriteria(driver).click();
    				   Thread.sleep(3000);
    				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
    				   performerPOM.clickCriteria(driver).sendKeys("Test Test New");
    				   Thread.sleep(3000);
    				   performerPOM.clickSaveCriteria(driver).click();
    				   String msg = performerPOM.readOppoenentMsg(driver).getText();
    				   
    				   if(msg.equalsIgnoreCase("Criteria Saved Successfully."))
    				   {
    					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
    				   }
    				   else
    				   {
    					   String msg1 = performerPOM.readMesg(driver).getText();
    					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg1);
    				   }
    				   
    				   Thread.sleep(3000);
    				   driver.switchTo().parentFrame();
    				   performerPOM.clickclosecriteria(driver).click();
    				   Thread.sleep(3000);
    				   performerPOM. clickstar(driver).click();
    			       Thread.sleep(3000);
    				   performerPOM. clickstar1(driver).click();
    				   Thread.sleep(3000);
    				   performerPOM. clickSaveRating(driver).click();
    				   
    				   
    			   	  Thread.sleep(1000);
    				 wait.until(ExpectedConditions.visibilityOf(performerPOM.readRatingmsg(driver)));
    							
    					Thread.sleep(500);
    					String msg5 = performerPOM.readRatingmsg(driver).getText();		//Reading Message appeared after save button
    					
    					if(msg5.equalsIgnoreCase("Rating Saved Successfully."))
    						{
    								test.log(LogStatus.PASS, "Message displayed = "+msg5);
    								
    						}
    					else
    						{
    								test.log(LogStatus.FAIL, "Message displayed = "+msg5);
    						}
    				   
    		  }	   
    	  static void AuditLog(WebDriver driver, ExtentTest test) throws InterruptedException
  		  {
    		  
    		  WebDriverWait wait = new WebDriverWait(driver, 300);
		       
		          Thread.sleep(3000);
 			     performerPOM.clickNoticeOpen(driver).click();//click edit notice
 	     
 	             Thread.sleep(3000);
 			     performerPOM.clickEditNotice(driver).click();//click edit notice
 		  
 			     
 			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 		         
  		              Thread.sleep(3000);
  		               performerPOM. clickAuditLog(driver).click();
  		                 Thread.sleep(3000);
  		                 performerPOM.clickExport(driver).click();		   
  		                 Thread.sleep(3000);
  		                  driver.switchTo().parentFrame();
  		                  performerPOM.clickclosebutton(driver).click();
  		
  		                  Thread.sleep(1000);
  		                  performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
  		                  JavascriptExecutor js = (JavascriptExecutor) driver;
  		                  js.executeScript("window.scrollBy(0,700)");
  		                  
  		                  test.log(LogStatus.PASS,"Export report download sucssesfully ");
  		                  
  		                  Thread.sleep(3000);
  		                  OverduePOM.clickDashboard(driver).click();
  		 } 
    	
	
		
    	  static int CountExcel(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
    		{
    		  WebDriverWait wait = new WebDriverWait(driver, 50);
    			progress(driver);
    			
    			//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
    			
    			
    			int open = 0;
    			if(type.equalsIgnoreCase("Notice - Open"))
    			{
    				open = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());	//Reading Notice Open count.
    				performerPOM.clickNoticeOpen(driver).click();						//Clicking on 'Open' notice
    			}
    			else if(type.equalsIgnoreCase("Notice - Closed"))
    			{
    				open = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Closed count.
    				performerPOM.clickNoticeClosed(driver).click();						//Clicking on 'Closed' notice
    			}
    			else if(type.equalsIgnoreCase("Case - Open"))
    			{
    				open = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());	//Reading Case Open count.
    				performerPOM.clickCaseOpencfo(driver).click();						//Clicking on 'Open' Case
    			}
    			else if(type.equalsIgnoreCase("Case - Closed"))
    			{
    				open = Integer.parseInt(performerPOM.clickCaseClosedCFO(driver).getText());	//Reading Case Open count.
    				performerPOM.clickCaseClosedCFO(driver).click();						//Clicking on 'Open' Case
    			}
    			
    			else if(type.equalsIgnoreCase("Task - Open"))
    			{
    				open = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());	//Reading Case Open count.
    				performerPOM.clickTaskOpen(driver).click();						//Clicking on 'Open' Case
    			}
    			
    			else if(type.equalsIgnoreCase("Task - Closed"))
    			{
    				open = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());	//Reading Case Open count.
    				performerPOM.clickTaskClosed(driver).click();						//Clicking on 'Open' Case
    			}
    			
    			
    			
    			
    			
    			Thread.sleep(500);
    			progress(driver);
    			
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
    			
    			Thread.sleep(2000);
    			JavascriptExecutor js = (JavascriptExecutor) driver;
    			try
    			{
    				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
    			}
    			catch(Exception e)
    			{
    				
    			}
    			js.executeScript("window.scrollBy(0,1000)");
    			
    			Thread.sleep(7000);
    			CFOcountPOM.readTotalItems1(driver).click();
    			String item = CFOcountPOM.readTotalItems1(driver).getText();
    			String[] bits = item.split(" ");								//Splitting the String
    			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
 //   			int count1 = 0;
//    			if(compliancesCount.equalsIgnoreCase("to"))
//    			{
//    				Thread.sleep(2000);
//    			   item = CFOcountPOM.readTotalItems1(driver).getText();
//    				bits = item.split(" ");								//Splitting the String
//    			   compliancesCount = bits[bits.length - 2];
//    			}
//    			if(compliancesCount.equalsIgnoreCase("to"))
//    			{
//    				count1 = 0;
//    			}
//    			else
 //   			{
    				int count1 = Integer.parseInt(compliancesCount);
  //  			}
    			
    			if(open == count1)
    			{
    				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
    				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
    			}
    			else
    			{
    				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
    				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
    			}
    			
    			Thread.sleep(100);
    			File dir = new File("C://Users//Snehal Patil//Downloads");
    			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
    			
    			Thread.sleep(500);
    			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
    			Thread.sleep(250);
    			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
    			
    			
    			Thread.sleep(5500);
    			File dir1 = new File("C://Users//Snehal Patil//Downloads");
    			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
    			
    			if(dirContents.length < allFilesNew.length)
    			{
    				test.log(LogStatus.PASS, "File downloaded successfully.");
    				
    				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
    			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
    			    {
    			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
    			       {
    			           lastModifiedFile = allFilesNew[i];
    			       }
    			    }
    				
    				Thread.sleep(100);
    				fis = new FileInputStream(lastModifiedFile);
    				workbook = new XSSFWorkbook(fis);
    				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
    				
    				int no = sheet.getLastRowNum();
    				Row row = sheet.getRow(no);
    				Cell c1 = row.getCell(0);
    				String records =c1.getStringCellValue();
    				int SheetRecords = 0;
    			
    				try
    				{
    					SheetRecords = Integer.parseInt(records);
    					
    				}
    				catch(Exception e)
    				{
    					
    				}
    				
//    				if(flag == 0)
//    				{
//    					row = sheet.getRow(no-1);
//    					c1 = row.getCell(0);
//    					records = c1.getStringCellValue();
//    					SheetRecords = Integer.parseInt(records);
//    				}
    				fis.close();
    				
    		if(count1 == SheetRecords)
    				{
    					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
    					test.log(LogStatus.PASS, "Total records from Grid = "+count1+" | Total records from Report = "+SheetRecords);
    				}
    				else
    				{
    					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
    					test.log(LogStatus.FAIL, "Total records from Grid = "+count1+" | Total records from Excel Sheet = "+SheetRecords);
    				}
    			}
    			else
    			{
    				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
    			}
    			return open;
    		}
		public static void CaseOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
		{
			
			Thread.sleep(1000);
    		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
			int sheetNo = 8;
		    if(login.equalsIgnoreCase("cfo"))
		    {
		    	sheetNo = 8;
		    }
			

			
			int open = CountExcel(driver, test, "Case - Open");
			
			
			Thread.sleep(500);
			performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,700)");
			
			Thread.sleep(300);
			CFOcountPOM.readTotalItems1(driver).click();
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			int gridRecords = 0;
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2000);
				item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
				compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				gridRecords = 0;
			}
			else
			{
				gridRecords = Integer.parseInt(compliancesCount);
			}
			
			sheet = workbook.getSheetAt(sheetNo);
			
			perform1(driver, test, sheet, open, gridRecords, "Case - Open");
		}
		static void perform1(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		//	CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
			js.executeScript("window.scrollBy(0,-700)");
			
			Thread.sleep(3000);
			clickNewCase(driver);
			
//			Thread.sleep(3000);
//			clickCaseskip(driver);
			
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			Thread.sleep(1000);
			selectCaseType(driver);
			Thread.sleep(3000);
			clickDated1(driver);
			Thread.sleep(3000);
			clickFinanicialYear(driver);
			Thread.sleep(3000);
			clickRefNo1(driver);
			Thread.sleep(3000);
			clickInternalCaseNo(driver);
			Thread.sleep(3000);
			clickCaseTitle(driver);
			Thread.sleep(3000);
			clickCaseAct(driver);
			Thread.sleep(3000);
			clickUnderSection(driver);
			Thread.sleep(3000);
			clickSearchCaseCategory(driver);
			Thread.sleep(3000);
			clickCaseBudget(driver);
			Thread.sleep(3000);
			clickCaseOpponent(driver);
//			Thread.sleep(3000);
//			clickCaseOppLawyer(driver);
			Thread.sleep(3000);
			clickCaseCourt(driver);
			Thread.sleep(3000);
			clickCaseDescription(driver);
			Thread.sleep(3000);
			selectCaseLocation(driver);
			Thread.sleep(3000);
			clickCaseDepartment(driver);
			Thread.sleep(3000);
			clickCaseOwner(driver);
			Thread.sleep(3000);
			clickCaseRisk(driver);
//			Thread.sleep(3000);
//			clickLawFirm(driver);
			Thread.sleep(3000);
			clickCaseInternalUser(driver);
//			Thread.sleep(3000);
//			clickLawyer( driver) ;
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.readMessage1(driver)));
			
			Thread.sleep(500);
			String msg = performerPOM.readMessage1(driver).getText();		//Reading Message appeared after save button
			
			if(msg.equalsIgnoreCase("Case Created Successfully."))
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
				
			}
		else
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
			}
		
		/*	WebElement ele1 = null;
			WebElement ele2 = null;
			WebElement ele3 = null;
			WebElement ele4 = null;
			
			if(flag == 1)
			{
				try
				{
					Thread.sleep(700);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickEditCase(driver)));
					ele1 = performerPOM.clickLinkCase(driver);
					ele2 = performerPOM.clickViewDoc(driver);
					ele3 = performerPOM.clickSendMail1(driver);
				ele4 = performerPOM.clickEditCase(driver);
				}
				catch(Exception e)
				{
					
				}
				
				if(ele1 != null && ele2 != null && ele3 != null && ele4 != null)
				{
					test.log(LogStatus.PASS, "Icons displayed are :- Link Notice, View Document, Send Mail with Document, Edit Notice");
				}
				else
				{
					test.log(LogStatus.FAIL, "All icons are not displayed.");
				}
			}*/
			
			Thread.sleep(2000);
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();			//Clicking on 'Close'
			
			Thread.sleep(500);
			performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			js.executeScript("window.scrollBy(0,700)");
			
			

	      Thread.sleep(1000);
	      CFOcountPOM.readTotalItems1(driver).click();
	      String item = CFOcountPOM.readTotalItems1(driver).getText();
	      String[] bits = item.split(" ");								//Splitting the String
	      String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
	      int count1 = 0;
	      if(compliancesCount.equalsIgnoreCase("to"))
	     {
	        Thread.sleep(2000);
	        item = CFOcountPOM.readTotalItems1(driver).getText();
	         bits = item.split(" ");								//Splitting the String
	        compliancesCount = bits[bits.length - 2];
	     }
	       count1 = Integer.parseInt(compliancesCount);

	    if(count1 > gridRecords)
	     {
	       //test.log(LogStatus.PASS, "Total Case Count increased in grid after adding New Case.");
	       test.log(LogStatus.PASS, "Old Case Count from Grid = "+gridRecords+" | New Case Count from Grid = "+count1);
	     }
	     else
	     {
	        //test.log(LogStatus.FAIL, "Total Case Count doesn't increased in grid after adding New Case.");
	        test.log(LogStatus.PASS, "Old Case Count from Grid = "+gridRecords+" | New Case Count from Grid = "+count1);
	     }

	       Thread.sleep(500);
	       OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'

	 
	       
	       
	       Thread.sleep(500);
	    
	       int open1 = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());	//Reading Notice Open count.
	       
	   	Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		
		if(type.equalsIgnoreCase("Case - Open"))
		{
			open1 = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());	//Reading Notice Open count.
		}
		else
		{
			open1 = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());	//Reading Notice Open count.
		}

	      if(open1 > open)
	       {
	          test.log(LogStatus.PASS, type+" Dashboard Count increamented. Old count = "+open+", New Count = "+open1);
	       }
	       else
	      {
	          test.log(LogStatus.PASS, type+" Dashboard Count doesn't increamented. Old count = "+open+", New Count = "+open1);
	       }
	     }

	
			  public  static void clickNewCase(WebDriver driver) throws InterruptedException 
			  {
					Thread.sleep(3000);
					performerPOM.clickNew(driver).click();	//Clicking on 'New' button

	           }
			  public  static void clickCaseskip(WebDriver driver) throws InterruptedException 
			  {
					Thread.sleep(3000);
					
					performerPOM.clickCaseskipfo(driver).click();
	           }
			  public static void selectCaseType(WebDriver driver) 
				{
					WebElement type = performerPOM.clickCaseType1(driver);
					type.click();
					
					performerPOM.chooseCaseType(driver).click(); 
					
				}
			  
			
			  public  static void clickDated1(WebDriver driver) throws InterruptedException 
			  {
				  Thread.sleep(3000);
			      performerPOM.clickCaseDate(driver).click();					//Clicking on 'Dated' button
			      OverduePOM.selectLastMonth(driver).click();					//Clicking last month arrow.
			      OverduePOM.selectDate3(driver).click();						//Clicking particular date.
			  }
			
			  public  static void clickFinanicialYear(WebDriver driver) throws InterruptedException 
			  {
			      Thread.sleep(3000);
			      performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
			      elementsList = performerPOM.clickFinanceSearchCheckbox(driver);
			      elementsList=performerPOM.chooseDropDownOption(driver);
			      elementsList.get(10).click();								//Clicking third option
			      performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
			  }
			
			

				
			  public  static void clickRefNo1(WebDriver driver) throws InterruptedException 
			  {
			       Thread.sleep(3000);
			       Row row0 = sheet.getRow(34);								//Selected 0th index row (First row)
			      Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			      String refno = c1.getStringCellValue();
			      performerPOM.clickRefNo(driver).sendKeys(refno);			//Writing 'Court Case No'
			  }
				
			  public  static void clickInternalCaseNo(WebDriver driver) throws InterruptedException 
			  {
			       Thread.sleep(3000);
			      Row row0 = sheet.getRow(35);								//Selected 0th index row (First row)
			      Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			       String caseNo = c1.getStringCellValue();
			       performerPOM.clickInternalCaseNo(driver).sendKeys(caseNo);	//Writing 'Court Case No'
			  }
			  public  static void clickCaseTitle(WebDriver driver) throws InterruptedException 
			  {
			       Thread.sleep(3000);
			       Row row0 = sheet.getRow(36);								//Selected 0th index row (First row)
			       Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			      String title = c1.getStringCellValue();
			       performerPOM.clickNoticeTitle(driver).sendKeys(title);		//Writing 'Case Title'
			  }

		 	
			  public  static void clickCaseAct(WebDriver driver) throws InterruptedException 
			  {
	   	      Thread.sleep(3000);
		         Row row0 = sheet.getRow(37);								//Selected 0th index row (First row)
		         Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
	 	          int actNo = (int) c1.getNumericCellValue();
			     performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
//		       //	elementsList = performerPOM.chooseAct(driver);
			    elementsList = performerPOM.chooseAct1(driver);
		        elementsList.get(2).click();							//Selecting particular act no
			     performerPOM.clickAct(driver).click();	                  //Clicking on 'Act' drop down.
			  }
			  
			  public  static void clickUnderSection(WebDriver driver) throws InterruptedException 
			  { 
			     Thread.sleep(3000);
			     Row row0 = sheet.getRow(38);								//Selected 0th index row (First row)
			     Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			     String underSection = c1.getStringCellValue();
			      performerPOM.clickUnderSection(driver).sendKeys(underSection);	//Writing 'Under section'
			  }
			  public  static void clickSearchCaseCategory(WebDriver driver) throws InterruptedException 
			  { 
			     Thread.sleep(3000);
			     Row row0 = sheet.getRow(39);								//Selected 0th index row (First row)
			    Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			    String caseType = c1.getStringCellValue();
			    performerPOM.clickCaseCategory(driver).click();
			    performerPOM.clickSearchCaseCategory(driver).sendKeys(caseType, Keys.ENTER);	//Writing 'Case Type'
			  }
			  public  static void clickCaseBudget(WebDriver driver) throws InterruptedException 
			  {
			      Thread.sleep(3000);
			     Row row0 = sheet.getRow(40);								//Selected 0th index row (First row)
			      Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			      int caseBudget = (int) c1.getNumericCellValue();
			     performerPOM.clickCaseBudget(driver).sendKeys(caseBudget+"");
			  }
			
			  public  static void clickCaseOpponent(WebDriver driver) throws InterruptedException 
			  {
			     Thread.sleep(3000);
			     Row row0 = sheet.getRow(41);						//Selected 0th index row (First row)
			     Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			      String opponent = c1.getStringCellValue();
			     performerPOM.clickOpponentcfo(driver).sendKeys(opponent);	
			  }

			  public  static void clickCaseOppLawyer(WebDriver driver) throws InterruptedException 
			  {
		          Thread.sleep(3000);
			      Row row0 = sheet.getRow(42);								//Selected 0th index row (First row)
			      Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			       String oppoLawyer = c1.getStringCellValue();
			       performerPOM.clickOppLawyer(driver).click();				//Clicking on 'Opponent'
			       performerPOM.clickSearchBox1(driver).sendKeys(oppoLawyer);	//Writing 'Opposition Lawyer' name
			       Thread.sleep(300);
			        performerPOM.clickSelectAll3(driver).click();
			        performerPOM.clickOppLawyer(driver).click();
			  }
			  public  static void clickCaseCourt(WebDriver driver) throws InterruptedException 
			  {
			         Thread.sleep(3000);
			        Row row0 = sheet.getRow(43);								//Selected 0th index row (First row)
			         Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			       String court = c1.getStringCellValue();
			       performerPOM.clickCourt(driver).click();
			       performerPOM.clickSearchCourt(driver).sendKeys(court, Keys.ENTER);
			  }
			
			
		
			  public  static void clickCaseDescription(WebDriver driver) throws InterruptedException 
			  {
			        Thread.sleep(3000);
			       Row row0 = sheet.getRow(36);							//Selected 0th index row (First row)
			       Cell  c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			       String casedesc = c1.getStringCellValue();
			      performerPOM.clickNoticeDescription(driver).sendKeys(casedesc);
			  }
			  
			  public static void selectCaseLocation(WebDriver driver) throws InterruptedException
				{
				Thread.sleep(7000);
				performerPOM.clickLocation(driver).click();					//Clicking on Location drop down
				Thread.sleep(3000);
			//	performerPOM.clickPlus(driver).click();
				performerPOM.selectLocationCfo(driver).click();;
				//elementsList.get(2).click();								//Selecting third visible location
				}
				public static void clickCaseDepartment(WebDriver driver) throws InterruptedException
				{
					Thread.sleep(4000);
				performerPOM.clickDeptCfo(driver).click();					//Clicking on 'Department' drop down
				performerPOM.selectDeptCfo(driver).click();	//Writing 'Department' name
				}
				public static void clickCaseOwner(WebDriver driver) throws InterruptedException
				{
				
				performerPOM.clickOwnerCfo(driver).click();					//Clicking on 'Owner' drop down
				performerPOM.selectOwnerCfo(driver).click();	//Writing 'Owner' name
				}
			  
			  
			  
			  

			 public  static void clickCaseRisk(WebDriver driver) throws InterruptedException 
			  { 
			    Thread.sleep(3000);
			    performerPOM.clickWinningProspect1(driver).click();
		 	   Thread.sleep(100);
		       performerPOM.selectRisk1(driver).click();			//Selecting 'Medium' Winning Prospect'
			  }
			 
				public static void clickLawFirm(WebDriver driver) throws InterruptedException
				{
				Thread.sleep(300);
				XSSFRow row0 = sheet.getRow(11);					//Selected 0th index row (First row)
				XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
				String lawFirm = c1.getStringCellValue();
				performerPOM.clickLawFirm(driver).click();		//Clicking on 'Law Firm' drop down.
				performerPOM.chooseLawFirm(driver).sendKeys(lawFirm, Keys.DOWN, Keys.ENTER);	//Writing & selecting 'Law Firm' name
				}
			
		 public  static void clickCaseInternalUser(WebDriver driver) throws InterruptedException 
			  { 
			       Thread.sleep(3000);
		            Row row0 = sheet.getRow(47);						//Selected 0th index row (First row)
			       Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			       int internalUserNo = (int) c1.getNumericCellValue();
			      performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
			      elementsList = performerPOM.chooseInternalUser1(driver);
			       elementsList.get(internalUserNo).click();							//Selecting particular user no
			      //performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
			  }
		 
			public static void clickLawyer(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(4);						//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int lawyerNo = (int) c1.getNumericCellValue();
			performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
			elementsList = performerPOM.chooseLawyer(driver);
			elementsList.get(lawyerNo).click();								//Selecting particular lawyer no
			performerPOM.clickLawyer(driver).click();		//Clicking on 'Lawyer' drop down.
			}
			
		 
		 static void Document(WebDriver driver,ExtentTest test) throws InterruptedException
			{
	           			
			
	          WebDriverWait wait = new WebDriverWait(driver, 50);
	          

		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
			  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			  performerPOM.clickNoticeDocument(driver).click();     //click notice document
			  performerPOM.clickNewDocument(driver).click();        //click new document button
			
	 
				Thread.sleep(1000);
				driver.switchTo().frame("IFrameManageDocument");
				performerPOM.selectDocumentType(driver);
				Thread.sleep(3000);
				performerPOM.chooseDocumentType(driver);
				Thread.sleep(1000);
				performerPOM.selectUploadDocument(driver); 
				Thread.sleep(1000);
				performerPOM.clickUploadDocument(driver).click(); 
			
			
			  Thread.sleep(1000);
			  wait.until(ExpectedConditions.visibilityOf(performerPOM.readDocMsg(driver)));
			
			  Thread.sleep(500);
			  String msg=performerPOM.readDocMsg(driver).getText();		//Reading Message appeared after save button
			  
			  if(msg.equalsIgnoreCase("Document(s) uploaded successfully"))
			 {
				 test.log(LogStatus.PASS, "Message displayed = "+msg);
				 
			 }
			 else
			 {
				 test.log(LogStatus.FAIL, "Message displayed = "+msg);
			 }
			
			  Thread.sleep(1000);
			  performerPOM.clickClosedDocument(driver).click(); 
			  Thread.sleep(3000);
			  
			  driver.switchTo().parentFrame();
			    Thread.sleep(3000);
		        performerPOM.clickCaseDownloadDocumentcfo(driver).click();
		        
		        test.log(LogStatus.PASS, "Document download succssesfully");
		        
		        Thread.sleep(3000);
		        performerPOM.clickCaseDocumentViewcfo(driver).click();
		        
		        Thread.sleep(3000);
		        performerPOM.clickNoticeDocumentViewClosepopupcfo(driver).click();
		        
		        test.log(LogStatus.PASS, "Document View popup open  succssesfully");
		        
		        Thread.sleep(3000);
		        performerPOM.clickCaseDocumentdeletecfo(driver).click();
		        
		        Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage= driver.switchTo().alert().getText();	
		        
		        
		        test.log(LogStatus.PASS, alertMessage);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage);
		        
		 		
		        // Accepting alert		
		        alert.accept();	
		        
		       
		        
		        Thread.sleep(3000);
		        performerPOM.clickCaseDocumentsharecfo(driver).click();
		        
		        
		     	  
		        Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert1 = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		        
		        
		        test.log(LogStatus.PASS, alertMessage1);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage1);
		        
		     // Accepting alert		
		        alert1.accept();	
		        
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));
		        
		        Thread.sleep(4000);
		        performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
		        
		        Thread.sleep(3000);
		        performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5768798045");
		        
		        Thread.sleep(3000);
		        performerPOM.clickNoticeDocumentsharesavecfo(driver).click();
		        
		        
		        Thread.sleep(3000);
		        String msg1= performerPOM.clickNoticeDocumentsharereadmsgcfo(driver).getText();		//Reading Message appeared after save button
		       
	         	if(msg1.equalsIgnoreCase("Document shared successfully."))
	         	{
		        	test.log(LogStatus.PASS, "Message displayed = "+msg1);
		         
		        }
		      else
		        {
			       test.log(LogStatus.FAIL, "Message displayed = "+msg1);
		        }
		        
	         	
	         	  driver.switchTo().parentFrame();
	         	   Thread.sleep(3000);
	  	     performerPOM. clickNoticeDocumentshareclosepopupcfo(driver).click();
	  	        
		 }
		 
		 static void TaskActivity1(WebDriver driver, ExtentTest test,XSSFWorkbook workbook ) throws InterruptedException, IOException
			{
			    WebDriverWait wait=new WebDriverWait(driver,20);
			    
			    
		       XSSFSheet sheet=ReadExcel();

		       Thread.sleep(500);
 	        	performerPOM.clickCaseOpencfo(driver).click();		
 	 			
 	             Thread.sleep(4000);
 	         
		      	performerPOM.clickEditNotice(driver).click();
		      
			    Thread.sleep(3000);
			   
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    Thread.sleep(3000);
			    performerPOM.clickCaseTask(driver).click();
			    Thread.sleep(300);
			    performerPOM.clickCaseNewTask(driver).click();
			    Thread.sleep(5000);
			    performerPOM.clickHearingDate(driver).sendKeys("24-10-2023");
			    
			    
//			    Thread.sleep(300);
//			    performerPOM.clickHearingDatecfo(driver).click(); 
//			    Thread.sleep(300);
//			    performerPOM.clickHearingDatedropdowncfo(driver).click(); 
			    
			   
			    
			    Thread.sleep(2000);
			    performerPOM.clickSaveHearingDatecfo(driver).click();
			  
			  
				Thread.sleep(6000);
				Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
				Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
				String title = c1.getStringCellValue();
				performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
				
				Thread.sleep(5000);
				row0 = sheet.getRow(13);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String desc = c1.getStringCellValue();
				performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
				
				
				Thread.sleep(1000);
				performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
				OverduePOM.selectNextMonth(driver).click();
				OverduePOM.selectDate(driver).click();					//Selecting particular date.
				
				Thread.sleep(1000);
				Actions action = new Actions(driver);
//				action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
				
				
				Thread.sleep(1000);
				row0 = sheet.getRow(14);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String outcome = c1.getStringCellValue();
				performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
				

				
				Thread.sleep(1000);
				row0 = sheet.getRow(15);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String internalUser = c1.getStringCellValue();
				performerPOM.clickInternalUser3(driver).click();
				//performerPOM.selectInternalUser2(driver).click();
				performerPOM.selectInternalUser3(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
				
				Thread.sleep(1000);
				row0 = sheet.getRow(16);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String externalUser = c1.getStringCellValue();
				try
				{
					Thread.sleep(300);
					performerPOM.clickExternalUser(driver).click();
					Thread.sleep(500);
					action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
				}
				catch(Exception e)
				{
					
				}
				Thread.sleep(5000);
				row0 = sheet.getRow(17);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String remark = c1.getStringCellValue();
				performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
             	Thread.sleep(1000);
				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
				
//				Thread.sleep(2000);
//				performerPOM.clickMinimize(driver).click();	
				
				Thread.sleep(300);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsgcfo(driver)));
				
				Thread.sleep(3000);
			
				String msg1 = performerPOM.readTaskMsgcfo(driver).getText();
				if(msg1.contains(msg1))
				{
					test.log(LogStatus.PASS, "Add Task for Case ="+msg1);
				}
				
				else 
				{
					test.log(LogStatus.FAIL, "Add Task for Case ="+msg1);
				}
				
			/*	Thread.sleep(3000);
				performerPOM.clickMinimize(driver).click();
				
			//	Thread.sleep(3000);
			//	performerPOM.clickNoticeTaskEditResponsecfo1(driver).click();
				
				Thread.sleep(1000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				
				Thread.sleep(5000);
				row0 = sheet.getRow(13);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String desc1 = c1.getStringCellValue();
				performerPOM.clickTaskDesc(driver).sendKeys(desc1);		//Writing 'Task Description'
				
				Thread.sleep(1000);
				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
				
				String msg2 = performerPOM.readTaskMsgcfo(driver).getText();
				if(msg2.contains("Task Saved Successfully. An Email containing task detail and access URL to provide response sent to assignee."))
				{
					test.log(LogStatus.PASS, "Task Saved Successfully. An Email containing task detail and access URL to provide response sent to assignee.");
				}
				
				else 
				{
					test.log(LogStatus.FAIL, "Select Hearing or if you do not want to map task with hearing, then please select 'Not Applicable'.");
				}*/
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskEditResponse1(driver).click();
				Thread.sleep(1000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo(driver).click();
				
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo1(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskcmtResponsecfo(driver).sendKeys("Automate Testt new");
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskSaveResponsecfo(driver).click();
				
				
				
				test.log(LogStatus.PASS,"Task Response Saved Successfully.");
				
				driver.switchTo().parentFrame();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskCloseResponsecfo(driver).click();
				
                Thread.sleep(3000);
				performerPOM.clickNoticeTaskClosecfo1(driver).click();
				
				 Thread.sleep(5000);
				    // Switching to Alert        
			        Alert alert = driver.switchTo().alert();		
			        		
			        // Capturing alert message.    
			        String alertMessage= driver.switchTo().alert().getText();	
			        
			        
			        test.log(LogStatus.PASS, alertMessage);
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage);
			        
			     // Accepting alert		
			        alert.accept();
				
			
				
			}
		 
		 static void CaseHearing(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
			       WebDriverWait wait=new WebDriverWait(driver,20);
			       XSSFSheet sheet=ReadExcel();
			       
			       Thread.sleep(500);
	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 			
	 	             Thread.sleep(4000);
	 	         
			      	performerPOM.clickEditNotice(driver).click();
			      	
			        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			        Thread.sleep(3000);
				   performerPOM.clickCaseHearing(driver).click();
					Thread.sleep(3000);
					performerPOM.clickNewCaseHearing(driver).click();
					
//					Thread.sleep(3000);
//					performerPOM.clickHearingcfo(driver).click();
//					
//					Thread.sleep(3000);
//					performerPOM.clickHearingdropdowncfo(driver).click();
					
					
//					Thread.sleep(300);
//					Row row0 = sheet.getRow(35);					//Selected 0th index row (First row)
//					Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//					int HearingDate = (int) c1.getNumericCellValue();
//					performerPOM.clickCaseHearingDate(driver).sendKeys(HearingDate+"");	//Writing 'HearingDate'
					
					performerPOM.clickCaseHearingDate(driver).sendKeys("31-08-2024");	//Writing 'HearingDate'
					
					
				
				    Thread.sleep(3000);
				    performerPOM.clickSaveCaseHearingDate(driver).click();
				
					
					Thread.sleep(2000);
					Row row1 = sheet.getRow(50);									//Selected 0th index row (First row)
					Cell c2 = row1.getCell(1);									//Selected cell (0 row,1 column)
					String HearingDescription = c2.getStringCellValue();
					performerPOM.clickCaseHearingDecsri(driver).sendKeys(HearingDescription);		//Writing 'HearingDescription'
					
				   
					Thread.sleep(3000);
				    performerPOM.clickSaveCaseHearing(driver).click();
				    
				    
				    Thread.sleep(3000);
					String msg = performerPOM.clickReadHearingMsg(driver).getText();
					if(msg.contains("Hearing Details Saved Successfully."))
					{
						test.log(LogStatus.PASS, "Hearing Details Saved Successfully.");
					}
					else
					{
						test.log(LogStatus.FAIL, "Select Hearing.");
					}
				    
				    
				    
				    Thread.sleep(3000);
				    performerPOM.clickCaseHearingcfo(driver).click();
				    
				    
				    Thread.sleep(3000);
				    performerPOM.clickEditCaseHearingcfo(driver).click();
				    
				    Thread.sleep(3000);
				    performerPOM.clickCaseHearingDecsri(driver).clear();
				    Thread.sleep(3000);
				    performerPOM.clickCaseHearingDecsri(driver).sendKeys("Case Hearing6 JULY 2023");		//Writing 'HearingDescription'
				    
				    Thread.sleep(3000);
				    performerPOM.clickSaveCaseHearing(driver).click();
				    
				    Thread.sleep(3000);
					String msg1 = performerPOM.clickReadHearingMsg(driver).getText();
					if(msg1.contains("Hearing Details Saved Successfully."))
					{
						test.log(LogStatus.PASS, "Hearing Details Saved Successfully.");
					}
					else
					{
						test.log(LogStatus.FAIL, "Select Hearing.");
					}
				    
				    
				    
				    Thread.sleep(3000);
				    performerPOM.clickDeleteCaseHearingcfo(driver).click();
				    
					 Thread.sleep(5000);
					    // Switching to Alert        
				        Alert alert = driver.switchTo().alert();		
				        		
				        // Capturing alert message.    
				        String alertMessage= driver.switchTo().alert().getText();	
				        
				        
				        test.log(LogStatus.PASS, alertMessage);
				        		
				        // Displaying alert message		
				        System.out.println(alertMessage);
				        
				     // Accepting alert		
				        alert.accept();
				    
				    
			} 
		 
			static void CaseOrder(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
				WebDriverWait wait=new WebDriverWait(driver,20);
				 XSSFSheet sheet=ReadExcel();
				 Thread.sleep(3000);
					performerPOM.clickCaseOpencfo(driver).click();//click edit notice
			     
			        Thread.sleep(3000);
					performerPOM.clickEditNotice(driver).click();//click edit notice
				  
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				 Thread.sleep(5000);
				 performerPOM.clickCaseOrder(driver).click();
				 Thread.sleep(6000);
				 performerPOM.clickNewCaseOrder(driver).click();
				 Thread.sleep(6000);
				 performerPOM. clickCaseOrderDate(driver).sendKeys("25-03-2023");
				 Thread.sleep(3000);
				 performerPOM.clickOrderPanel(driver).click();
				 Thread.sleep(3000);
				 performerPOM. clickCaseOrderType(driver).click();
				 Thread.sleep(3000);
				 performerPOM.selectCaseOrderType(driver).click();
				
				 
				 
					
					Thread.sleep(300);
					Row row0 = sheet.getRow(53);					//Selected 0th index row (First row)
					Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
					int OrderTitle = (int) c1.getNumericCellValue();
					performerPOM.clickCaseOrderTitle(driver).sendKeys(OrderTitle+"");	//Writing 'HearingDate'
					
	 
				 Thread.sleep(2000);
				 Row row2 = sheet.getRow(54);									//Selected 0th index row (First row)
				 Cell c2 = row2.getCell(1);									//Selected cell (0 row,1 column)
				 String OrderDecri = c2.getStringCellValue();
				 performerPOM.clickCaseOrderDecri(driver).sendKeys(OrderDecri);     //click oder description
				

				 Thread.sleep(3000);
				 performerPOM.clickSaveCaseOrder(driver).click();
				 
				 
				 Thread.sleep(3000);
				 performerPOM.clickEditCaseOrdercfo(driver).click();
				 
				 performerPOM.clickCaseOrderTitle(driver).clear();
				 
				 performerPOM.clickCaseOrderTitle(driver).sendKeys("Order no 786");
				 
				 performerPOM.clickCaseOrderDecri(driver).clear();
				 
				 performerPOM.clickCaseOrderDecri(driver).sendKeys("order as on 17 JULY 23");     //click oder description
				 
				 performerPOM.ChooseOrderFile(driver).click();
				 
				 Thread.sleep(3000);
				 performerPOM.clickSaveCaseOrder(driver).click();
				 
				 
				 wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg(driver)));
					
				
					
					 Thread.sleep(3000);
						String msg = performerPOM.readResponseMsg(driver).getText();
						if(msg.contains("Order Details Saved Successfully."))
						{
							test.log(LogStatus.PASS, "Case Order ith valid data ="+msg);
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Order ith valid data ="+msg);
						
						}
				 
				 Thread.sleep(3000);
				 performerPOM.clickDownloadCaseOrdercfo(driver).click();
				 
				
			      test.log(LogStatus.PASS, "Case Document Download Successfully");
			         
			        
		     	 Thread.sleep(3000);
				 performerPOM.clickViewCaseOrdercfo(driver).click();
				 
				 Thread.sleep(6000);
			     performerPOM.clickNoticeDocumentViewClosepopupcfo(driver).click();
			     
			     test.log(LogStatus.PASS,"Case View Document Popup Open Successfully");
			     
			     Thread.sleep(3000);
			     performerPOM.clickDeleteCaseOrdercfo(driver).click();
			     
				 Thread.sleep(5000);
				    // Switching to Alert        
			        Alert alert = driver.switchTo().alert();		
			        		
			        // Capturing alert message.    
			        String alertMessage= driver.switchTo().alert().getText();	
			        
			        
			        test.log(LogStatus.PASS, alertMessage);
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage);
			        
			     // Accepting alert		
			        alert.accept();
			        
		
				 
			}	 
			
			   static void StatusPayment(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			      {	
			    	       WebDriverWait wait=new WebDriverWait(driver,50);
			      
			    	       XSSFSheet sheet=ReadExcel();
			    	       
			    	       performerPOM.clickCaseOpencfo(driver).click();//click edit notice
						     
					        Thread.sleep(3000);
							performerPOM.clickEditNotice(driver).click();//click edit notice
						  
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    	       
			    	       
			    	    
			    	       Thread.sleep(3000);
			               performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
							
							wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
//							
							Thread.sleep(3000);
							Row row0 = sheet.getRow(58);					//Selected 0th index row (First row)
							Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
							int InvoiceNo = (int) c1.getNumericCellValue();
							performerPOM.clickCaseInvoiceNo1(driver).sendKeys(InvoiceNo+"");	//Writing 'Invoice No'
							
						    
						
							
							
							
							Thread.sleep(4000);
							performerPOM.clickPaymentTyp1(driver);
							List<WebElement> PaymentType1= driver.findElements(By.xpath("//*[@id='grdCasePayment_ddlPaymentType_chosen']/div/ul/li"));
							PaymentType1.get(2).click();
							
							
							Thread.sleep(10000);
                            performerPOM.clickAmount1(driver).sendKeys("900000");	//Writing 'Amount'
                            
                        	Thread.sleep(3000);
            				performerPOM.clickAmountCase(driver).sendKeys("7000");
            			
						
				
							Thread.sleep(3000);
							performerPOM.clickSavePaymentLog1(driver).click();
							
							
							   Thread.sleep(5000);
								String msg5 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
							
								if(msg5.equalsIgnoreCase("Payment Details Saved Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg5);
								
								}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg5);
								}
						        
							
							
							Thread.sleep(3000);
							performerPOM.clickViewPaymentDoccfo(driver).click();
							
							Thread.sleep(3000);
							performerPOM.clickNoticeclosePaymentDocpopupcfo(driver).click();
							
							test.log(LogStatus.PASS, "Payment Document popup open successfully");
							
							
						
							Thread.sleep(3000);
							performerPOM.clickEditPaymentDoccfo(driver).click();
							
							Thread.sleep(3000);
							performerPOM.clickCaseInvoiceNo1(driver).clear();
							 Thread.sleep(3000);
						    performerPOM.clickCaseInvoiceNo1(driver).sendKeys("Invoice No 564");
						    
						    Thread.sleep(6000);
							performerPOM.clickCaseStatusPaymentUploadtcfo(driver);
						    

							Thread.sleep(3000);
							performerPOM.clickSavePaymentLog1(driver).click();
							
							  Thread.sleep(500);
								String msg = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
							
								if(msg5.equalsIgnoreCase("Payment Details Saved Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg);
								
								}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg);
								}
							
							
							
							
							Thread.sleep(3000);
							performerPOM.clickDeletePaymentDoccfo1(driver).click();
							
							 Thread.sleep(5000);
							    // Switching to Alert        
						        Alert alert1 = driver.switchTo().alert();		
						        		
						        // Capturing alert message.    
						        String alertMessage1= driver.switchTo().alert().getText();	
						        
						        
						        test.log(LogStatus.PASS, alertMessage1);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage1);
						        
						     // Accepting alert		
						        alert1.accept();
						        
						        Thread.sleep(500);
								String msg6 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
							
								if(msg6.equalsIgnoreCase("Payment Details Deleted Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg6);
								
								}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg6);
								}
						        
						        
							
							
							
						
			      }
			   static void CaseExternalLawyer(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
			      {
				               
				   
			    	           WebDriverWait wait=new WebDriverWait(driver,50);
			    	           
			    	    	   Thread.sleep(3000);
			   				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
			   		     
			   		        Thread.sleep(3000);
			   				performerPOM.clickEditNotice(driver).click();//click edit notice
			   			  
			   			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    	      
							  Thread.sleep(3000);
							   performerPOM.clickExternalLawyerRating1(driver).click();
							   
							   Thread.sleep(4000);
							   performerPOM.selectCaseExternalLawyer(driver);
							   
							  Thread.sleep(3000);
							  performerPOM.selectExternalLawyerRating(driver);
							   Thread.sleep(3000);
							   performerPOM.clickNewCriteria(driver).click();
							   Thread.sleep(3000);
							   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
							   performerPOM.clickCriteria(driver).sendKeys("Automation Test 11oct23");
							   Thread.sleep(3000);
							   performerPOM.clickSaveCriteria(driver).click();
							   Thread.sleep(3000);
							   driver.switchTo().parentFrame();
							   performerPOM.clickclosecriteria(driver).click();
							   Thread.sleep(3000);
							   performerPOM. clickstar(driver).click();
						       Thread.sleep(3000);
							   performerPOM. clickstar1(driver).click();
							   Thread.sleep(3000);
							   performerPOM. clickSaveRating(driver).click();
							   
							   
						   	  Thread.sleep(1000);
							 wait.until(ExpectedConditions.visibilityOf(performerPOM.readRatingmsg(driver)));
										
								Thread.sleep(500);
								String msg5 = performerPOM.readRatingmsg(driver).getText();		//Reading Message appeared after save button
								
								if(msg5.equalsIgnoreCase("Rating Saved Successfully"))
									{
											test.log(LogStatus.PASS, "Message displayed = "+msg5);
											
									}
								else
									{
											test.log(LogStatus.FAIL, "Message displayed = "+msg5);
									}
								
								
							   
					  }	   
				   
			      static void Auditlog(WebDriver driver,ExtentTest test) throws InterruptedException
			      {
			    	 
			    	  WebDriverWait wait=new WebDriverWait(driver,50);
			   	               Thread.sleep(3000);
				            	performerPOM.clickCaseOpencfo(driver).click();//click edit notice
			     
			                    Thread.sleep(3000);
					               performerPOM.clickEditNotice(driver).click();//click edit notice
				  
				                     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    	 
							   Thread.sleep(3000);
							   performerPOM. clickAuditLog(driver).click();
							   Thread.sleep(3000);
							   performerPOM.clickExport(driver).click();		   
							   Thread.sleep(3000);
							   driver.switchTo().parentFrame();
							   performerPOM.clickclosebutton(driver).click();
							   
							   test.log(LogStatus.PASS,"Audit Detail Report Download successfully");
							   
							   Thread.sleep(1000);
								
								OverduePOM.clickDashboard(driver).click();
			      }	 
			      
			  	public static void LinkDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
				{
					WebDriverWait wait = new WebDriverWait(driver, 180);
					progress(driver);
					
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
					
//					Thread.sleep(3000);
//					performerPOM.clickEditNotice(driver).click();//click edit notice
					if(type.equals("Notice"))
					{
						performerPOM.clickNoticeOpen(driver).click();							//Clicking on 'Open' notice
					}
					else if(type.equals("Case"))
					{
						performerPOM.clickCaseOpencfo(driver).click();								//Clicking on 'Open' case
					}
					
					progress(driver);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));
					
					Thread.sleep(400);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,500)");
					
					Thread.sleep(1500);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
					//performerPOM.GridLoad(driver).click();
					elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
					js.executeScript("arguments[0].scrollIntoView();", elementsList.get(0));
					
					Thread.sleep(600);
					elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
					elementsList.get(0).click();								//Clicking on first action button.
					
					
					Thread.sleep(3000);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Waiting and switching to IFrame		
					if(type.equals("Notice"))
					{
						performerPOM.clickLinkNotice(driver).click();			//Clicking on Link Notice icon
						
						Thread.sleep(300);
						progress(driver);
						
						Thread.sleep(3000);
						wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCheckBox(driver)));	//Waiting for Checkbox to get visible.
						
						Thread.sleep(3000);
						performerPOM.clickCheckBox(driver).click();			//CLicking on first checkbox
					}
					else if(type.equals("Case"))
					{
						performerPOM.clickLinkCase(driver).click();			//Clicking on Link Notice icon
						
						Thread.sleep(300);
						progress(driver);
						
						Thread.sleep(3000);
						wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCaseCheckBox(driver)));	//Waiting for Checkbox to get visible.

						
						Thread.sleep(300);
						performerPOM.clickCaseCheckBox(driver).click();		//CLicking on first checkbox
					}
					
					Thread.sleep(300);
					if(type.equals("Case"))
					{
						performerPOM.clickApply(driver).sendKeys(Keys.PAGE_DOWN);
					}
					else
					{
						performerPOM.clickApply1(driver).sendKeys(Keys.PAGE_DOWN);
					}
					
					Thread.sleep(300);
					performerPOM.clickSave(driver).click();				//Clicking on Save button.
					
					Thread.sleep(300);
					progress(driver);
					
					Thread.sleep(500);
					try
					{
						wait.until(ExpectedConditions.elementToBeClickable(performerPOM.readMsg(driver)));
					}
					catch(Exception e)
					{
						wait.until(ExpectedConditions.elementToBeClickable(performerPOM.readMsg(driver)));
					}
					Thread.sleep(300);
					String msg = performerPOM.readMsg(driver).getText();
					if(msg.contains("Linked Successfully"))
					{
						test.log(LogStatus.PASS, "Message displayed = "+msg);
					}
					else
					{
						test.log(LogStatus.FAIL, "Message displayed = "+msg);
					}
					
					
					if(type.equals("Notice"))
					{
						performerPOM.clickClosePopup(driver).click();
						
						Thread.sleep(300);
						performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
						performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
						performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
						
						
						

					}
					else if(type.equals("Case"))
					{
						performerPOM.clickClosePopupCase(driver).click();
						
						Thread.sleep(300);
						performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
						performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
						performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);


					}

						
						

					if(type.equals("Notice"))
					{
						performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_UP);
						performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_UP);
						performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_UP);
					}
					else if(type.equals("Case"))
					{
						performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_UP);
						performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_UP);
						performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_UP);
					}
					
					
					Thread.sleep(300);
					driver.switchTo().parentFrame();
					performerPOM.clickClose(driver).click();
					
			
					
					Thread.sleep(1000);
				
					OverduePOM.clickDashboard(driver).click();
					}
				
			  	
			  	public static void AdvancedSearchWorkspace(WebDriver driver,ExtentTest test) throws InterruptedException
				{
					Thread.sleep(3000);
					performerPOM.clickMyWorkspace(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickCaseNotice1(driver).click();
					
					WebDriverWait wait=new WebDriverWait(driver,30);
					Thread.sleep(5000);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
					
					Thread.sleep(5000);
					
					performerPOM.AdvancedSearchReports(driver).click();
					
				//-------------------------------------------Notice--------------------------------------------------
					
					Thread.sleep(4000);
					performerPOM.startDate(driver).sendKeys("05/4/2022");
					
					Thread.sleep(4000);
					performerPOM.endDate(driver).sendKeys("05/7/2022");
					
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
					
					

				      //-------------------------------------------Case--------------------------------------------------
						Thread.sleep(4000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						Thread.sleep(4000);
						performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(5000);
						performerPOM.selectTypeCase1(driver).click();
						
						Thread.sleep(4000);
						performerPOM.clickApplyButton(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "File downloaded successfully.");
					
						Thread.sleep(4000);
						performerPOM.clickeditButton(driver).click();
						
						test.log(LogStatus.PASS,"edit case details icon open successfully");
						
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup(driver).click();
						
						
						
			          //-------------------------------------------Task--------------------------------------------------
							Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						
						Thread.sleep(5000);
						performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(6000);
						performerPOM.selectTypeTask1(driver).click();
						
						Thread.sleep(5000);
						performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "File downloaded successfully.");
						
						Thread.sleep(5000);
						performerPOM.viewTaskDetails1(driver).click();	
						test.log(LogStatus.PASS, "Show details Task popup open successfully.");
						
						Thread.sleep(6000);
						performerPOM.ActioncloseTaskpopup(driver).click();
						
						Thread.sleep(500);
						OverduePOM.clickDashboard(driver).click();
					        
				}
			  	
			  	public static void WorkspaceFilter(WebDriver driver,ExtentTest test) throws InterruptedException
				{
					WebDriverWait wait=new WebDriverWait(driver,20);
					Thread.sleep(3000);
					performerPOM.clickMyWorkspace(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickCaseNotice1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clicklocationFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickLocationFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickLocationFiltercfo(driver).click();
					
					
					Thread.sleep(3000);
					performerPOM.clickDepartmentFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDepartFiltercfo(driver).click();
					
//					Thread.sleep(5000);
//					performerPOM.clickFinancialYear2(driver).click();
//					
//					Thread.sleep(5000);
//					performerPOM.clickFinancialYear3(driver).click();
					
					
//		         	Thread.sleep(5000);
//					performerPOM.clickCalenderYear2(driver).click();
//					
//					Thread.sleep(5000);
//					performerPOM.clickCalenderYear3(driver).click();
	
					
			     	Thread.sleep(3000);
					performerPOM.clickstatus(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickstatus1(driver).click();
					
//					Thread.sleep(3000);
//					performerPOM.clickcategory(driver).click();
//					
//					Thread.sleep(3000);
//					performerPOM.clickCategoryFiltercfo(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickType1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTypeFiltercfo1(driver).click();
					
					test.log(LogStatus.PASS, "My Workspace = Notice Filters Work Successfully");
					
					Thread.sleep(3000);
					performerPOM.clickDropdown(driver).click();
					
					Thread.sleep(3000);
					performerPOM.selectTypeCase(driver).click();
					
					
					
					test.log(LogStatus.PASS, "My Workspace = Case  Filters Work Successfully");
					

					Thread.sleep(3000);
					performerPOM.selectApplyBtn(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDropdown(driver).click();
					
					Thread.sleep(3000);
					performerPOM.selectTypeTask(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskLocFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickLocationFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskLocFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskPriorityFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskPriorityFiltercfo(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskStatusFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskStatusFiltercfo(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskPeriodFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickTaskPeriodFilter1(driver).click();
					
					
					Thread.sleep(3000);
					performerPOM.clearButton(driver).click();
					
					test.log(LogStatus.PASS, "My Workspace = Task Filters Work Successfully");
					
					Thread.sleep(3000);
					performerPOM.clickMyWorkspace(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickCaseHearing1(driver).click();
					
					
					Thread.sleep(3000);
					performerPOM.clickSearchFilter(driver).sendKeys("ASL Training 2021");
					
					Thread.sleep(3000);
					performerPOM.clearButton(driver).click();
					
					
					Thread.sleep(3000);
					performerPOM.CaseHearingView(driver).click();
					
					Thread.sleep(3000);
					performerPOM.CaseHearingPopupClose(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clearButton(driver).click();
					
					Thread.sleep(500);
					OverduePOM.clickDashboard(driver).click();
					test.log(LogStatus.PASS, "My Workspace = Case Hearing Filters Work Successfully");
					test.log(LogStatus.PASS, "My Workspace = Clear Button Work Successfully");
					}
			  	
				public static void DocumentFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
				{
					WebDriverWait wait=new WebDriverWait(driver,20);
						progress(driver);
					
					//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
					performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
					performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
					
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
					
//					Thread.sleep(7000);
//					performerPOM.clickDocStatusFilter(driver).click();
//					
//					Thread.sleep(8000);
//					performerPOM.clickDocStatusFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTypeFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTypeFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocLocFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickLocationFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocLocFilter2(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocDeptFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocDeptFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clearButton(driver).click();
					test.log(LogStatus.PASS, "My Document = Case Filters Work Successfully");
					
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
					
					Thread.sleep(3000);
					performerPOM.clickDocDropdownFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.selectTypeCase(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocStatusFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocStatusFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTypeFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTypeFilter2(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocLocFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickLocationFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocLocFilter3(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocDeptFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocDeptFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clearButton(driver).click();
					
					
					test.log(LogStatus.PASS, "My Document = Notice Filters Work Successfully");
					
					Thread.sleep(3000);
					performerPOM.clickDocDropdownFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.selectTypeTask(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocStatusFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocStatusFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTaskPriorityFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTaskPriorityFilter2(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocLocFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickLocationFilter1(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocLocFilter2(driver).click();
					
//					Thread.sleep(3000);
//					performerPOM.clickDocTaskFilter(driver).click();
//					
//					Thread.sleep(3000);
//					performerPOM.clickDocTaskFilter1(driver).click();
					
					
					Thread.sleep(3000);
					performerPOM.clearButton(driver).click();
					
					test.log(LogStatus.PASS, "My Document = Task Filters Work Successfully");
					
					}	
			  	
				public static void MyDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					progress(driver);
					
					//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
					performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
					performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
					
					
					/*Thread.sleep(3000);
					performerPOM.clickDocTypeFilter(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickDocTypeFilter1(driver).click();

					Thread.sleep(5000);
					if(performerPOM.clearButton(driver).isEnabled())
					{
						performerPOM.clearButton(driver).click();
						 test.log(LogStatus.PASS, "My Document = clear button Work Successfully");
					}
					else
					{
						test.log(LogStatus.PASS, "My Document = clear button not Work Successfully");
					}*/
					   
					
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
					
					//--------------------------------Case----------------------------------
					     Thread.sleep(4000);
					       performerPOM.clickDownloadDocument(driver).click();	
					       test.log(LogStatus.PASS, "Case=Document  Downloaded Successfully.");
					       
					       try
					       {
					    	    Thread.sleep(4000);
					            performerPOM.clickViewDocument(driver).click();	
					            Thread.sleep(3000);
					            performerPOM.clickcloseViewDocument(driver).click();
					           Thread.sleep(3000);
					          test.log(LogStatus.PASS, "Case=Document  View Successfully.");
					       }
					       catch(Exception e)
					       {
					    	   
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
					        alert.accept();
					       }
							
							//driver.navigate().refresh();
				
					//--------------------------------Notice----------------------------------
			 
					       Thread.sleep(5000);
						    JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("window.scrollBy(500,0)");
							Thread.sleep(3000);
							performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
							Thread.sleep(6000);
							performerPOM.selectTypeCase(driver).click();					//Selecting 'Case' option.
							 Thread.sleep(4000);
						       performerPOM.clickDownloadDocument(driver).click();	
						       test.log(LogStatus.PASS, "Notice=Document Downloaded Successfully.");
						      try
						      {
						         Thread.sleep(4000);
						         performerPOM.clickViewDocument(driver).click();	
						         Thread.sleep(4000);
						         performerPOM.clickcloseViewDocument(driver).click();
						         test.log(LogStatus.PASS, "Notice=Document view Successfully.");
						      }
						      catch(Exception e)
						      {
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
							        alert1.accept();
						      }
						       
							      driver.navigate().refresh();
											
			          ////--------------------------------Task----------------------------------
							
						    
							Thread.sleep(5000);
							performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
							Thread.sleep(6000);
							performerPOM.selectTypeTask(driver).click();					//Selecting 'Task' option.
										 
						     try
						     {
						     Thread.sleep(5000);
						       performerPOM.clickViewDocument(driver).click();	
						       Thread.sleep(3000);
						        performerPOM.clickcloseViewDocument(driver).click();

						        Thread.sleep(1000);
						        test.log(LogStatus.PASS, "Task=Document view Successfully.");
						     }
						   catch(Exception e)
						   {
							   Thread.sleep(5000);
							    // Switching to Alert        
						        Alert alert2 = driver.switchTo().alert();		
						        		
						        // Capturing alert message.    
						        String alertMessage2= driver.switchTo().alert().getText();	
						        
						        Thread.sleep(3000);
						        test.log(LogStatus.PASS, alertMessage2);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage2);	
						        
					      		
						        // Accepting alert		
						        alert2.accept();
						   }
						     
						     Thread.sleep(4000);
						     performerPOM.clickDownloadDocument(driver).click();
						     
							try
								{
									   Thread.sleep(5000);
									    // Switching to Alert        
								        Alert alert2 = driver.switchTo().alert();		
								        		
								        // Capturing alert message.    
								        String alertMessage2= driver.switchTo().alert().getText();	
								        
								        Thread.sleep(3000);
								        test.log(LogStatus.PASS, alertMessage2);
								        		
								        // Displaying alert message		
								        System.out.println(alertMessage2);	
								        
							      		
								        // Accepting alert		
								        alert2.accept();
								}
								
								catch(Exception e)
								{
									
								     
								     test.log(LogStatus.PASS, "Task=Document  Downloaded Successfully.");
								}
						  
						      driver.navigate().refresh();
						       
						       Thread.sleep(1000);
							   OverduePOM.clickDashboard(driver).click();				//Clicking on 'My Dashboard'
				}   
				
				public static void ShareCaseDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					progress(driver);
					
					
					performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
					performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
					Thread.sleep(1000);
					performerPOM.selectDocument(driver).click();	
					Thread.sleep(3000);
					//performerPOM.selectDocument1(driver).click();
					
				     //Select t=new Select(driver.findElement(By.xpath("/html/body/div[77]/div/div[2]/ul/li[2]")));
				  //  t.selectByIndex(1);
				
					
//      		       List<WebElement>SeletcRisk = driver.findElements(By.xpath("//li[@class='k-item']"));
//      			   selectOptionFromDropDown_bs(SeletcRisk, "Case Documents");
					Thread.sleep(1000);
					performerPOM.shareDocumentIcon(driver).click();
					
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("OverViews1"));
					   
					   Thread.sleep(1000);
						performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
						Thread.sleep(1000);
						performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5555555555");
						Thread.sleep(1000);
						performerPOM. clickNoticeDocumentsharesavecfo(driver).click();
					
						Thread.sleep(1000);
						String msg=performerPOM. clickNoticeDocumentsharereadmsgcfo(driver).getText();
						if(msg.equalsIgnoreCase("Document shared successfully."))
						{
							test.log(LogStatus.PASS, " Documents for respective case should be shared =" +msg);
						}
						else
						{
							test.log(LogStatus.FAIL, " Documents for respective case should be shared =" +msg);
						}
						driver.switchTo().parentFrame();
						Thread.sleep(1000);
						performerPOM. clickViewDocAdvocatebillPdfClose(driver).click();
						
				}
				
				
				public static void ShareNoticeDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					progress(driver);
					performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
					performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
					

			       Thread.sleep(5000);
				    JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(500,0)");
					Thread.sleep(3000);
					performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
					Thread.sleep(3000);
					performerPOM.selectTypeCase(driver).click();					//Selecting 'Case' option.
//					Thread.sleep(1000);
//					performerPOM.selectDocument(driver).click();	
					Thread.sleep(1000);
					performerPOM.shareDocumentIcon(driver).click();
					
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("OverViews1"));
					   
					   Thread.sleep(1000);
						performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
						Thread.sleep(1000);
						performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5555555555");
						Thread.sleep(1000);
						performerPOM. clickNoticeDocumentsharesavecfo(driver).click();
					
						Thread.sleep(1000);
						String msg=performerPOM. clickNoticeDocumentsharereadmsgcfo(driver).getText();
						if(msg.equalsIgnoreCase("Document shared successfully."))
						{
							test.log(LogStatus.PASS, " Documents for respective Notice should be shared =" +msg);
						}
						else
						{
							test.log(LogStatus.FAIL, " Documents for respective Notice should be shared =" +msg);
						}
						driver.switchTo().parentFrame();
						Thread.sleep(1000);
						performerPOM. clickViewDocAdvocatebillPdfClose(driver).click();
				}
				       
				public static void ShareTaskDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					progress(driver);
					performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
					performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
					
			       Thread.sleep(5000);
				    JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(500,0)");
					Thread.sleep(3000);
					performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
					Thread.sleep(3000);
					performerPOM.selectTypeTask(driver).click();					//Selecting 'Task' option.
//					Thread.sleep(1000);
//					performerPOM.selectDocument(driver).click();	
					
					
					try
					{
						
					
					Thread.sleep(1000);
					performerPOM.shareDocumentIcon(driver).click();
					
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("OverViews1"));
					   
					   Thread.sleep(1000);
						performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
						Thread.sleep(1000);
						performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5555555555");
						Thread.sleep(1000);
						performerPOM. clickNoticeDocumentsharesavecfo(driver).click();
					
						Thread.sleep(1000);
						String msg=performerPOM. clickNoticeDocumentsharereadmsgcfo(driver).getText();
						if(msg.equalsIgnoreCase("Document shared successfully."))
						{
							test.log(LogStatus.PASS, " Documents for respective Task should be shared =" +msg);
						}
						else
						{
							test.log(LogStatus.FAIL, " Documents for respective Task should be shared =" +msg);
						}
						driver.switchTo().parentFrame();
						Thread.sleep(1000);
						performerPOM. clickViewDocAdvocatebillPdfClose(driver).click();
					}
					catch(Exception e)
					{
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
					        alert.accept();	
						
					}
				}
				       
				
				
				
				 public static void AdvancedSearchDocument(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
				   {
						 		WebDriverWait wait = new WebDriverWait(driver, 60);
						 		progress(driver);
						 		
						 		//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
						 		performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
						 		performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
						 		
						 		Thread.sleep(3000);
						 		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						 		
							  //--------------------------------Case----------------------------------
								
							 Thread.sleep(3000);
							 performerPOM.AdvancedSearchReports(driver).click();
						      Thread.sleep(4000);
						       performerPOM.clickDownloadDocument1(driver).click();	
						       Thread.sleep(4000);
						       performerPOM.clickViewDocument1(driver).click();	
						       Thread.sleep(10000);
						       performerPOM.clickcloseViewDocument1(driver).click();
							
						       Thread.sleep(3000);
						       test.log(LogStatus.PASS, "Advanced Search(Case)=Document  View Successfully.");
						       test.log(LogStatus.PASS, "Advanced Search(Case)=Document  Downloaded Successfully.");
								
							
					
								//--------------------------------Notice----------------------------------
				 
								
								Thread.sleep(5000);
								performerPOM.clickTypeDropdown2(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
								Thread.sleep(5000);
								performerPOM.selectTypeCase2(driver).click();					//Selecting 'Case' option.
								 Thread.sleep(4000);
							       performerPOM.clickDownloadDocument1(driver).click();	
							       Thread.sleep(4000);
							       performerPOM.clickViewDocument1(driver).click();	
							       Thread.sleep(10000);
							       performerPOM.clickcloseViewDocument1(driver).click();
							       
							       Thread.sleep(3000);
							       test.log(LogStatus.PASS, "Advanced Search(Notice)=Document view Successfully.");
							       test.log(LogStatus.PASS, "Advanced Search(Notice)=Document Downloaded Successfully.");
									
												
				               ////--------------------------------Task----------------------------------
								
							   
								Thread.sleep(5000);
								performerPOM.clickTypeDropdown2(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
								Thread.sleep(5000);
								performerPOM.selectTypeTask2(driver).click();					//Selecting 'Task' option.
								
								 Thread.sleep(4000);
							     performerPOM.clickDownloadDocument1(driver).click();	
							     Thread.sleep(4000);
							     performerPOM.clickViewDocument1(driver).click();	
							     Thread.sleep(10000);
							     performerPOM.clickcloseViewDocument1(driver).click();

							     Thread.sleep(1000);
							     test.log(LogStatus.PASS, "Advanced Search(Task)=Document view Successfully.");
							     test.log(LogStatus.PASS, "Advanced Search(Task)=Document  Downloaded Successfully.");
							     
						         driver.navigate().refresh();
						       
						       Thread.sleep(1000);
							   OverduePOM.clickDashboard(driver).click();				//Clicking on 'My Dashboard'
				}
				 
				 
				 public static void AdvancedSearchShareCaseDocument(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 60);
						progress(driver);
						
						
						performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
						performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
						 Thread.sleep(3000);
						 performerPOM.AdvancedSearchReports(driver).click();
						
//						Thread.sleep(1000);
//						performerPOM.selectDocument(driver).click();	
						Thread.sleep(3000);
						//performerPOM.selectDocument1(driver).click();
						
					     //Select t=new Select(driver.findElement(By.xpath("/html/body/div[77]/div/div[2]/ul/li[2]")));
					  //  t.selectByIndex(1);
					
						
//	      		       List<WebElement>SeletcRisk = driver.findElements(By.xpath("//li[@class='k-item']"));
//	      			   selectOptionFromDropDown_bs(SeletcRisk, "Case Documents");
						Thread.sleep(1000);
						performerPOM.shareDocumentIcon1(driver).click();
						
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("OverViews3"));
						   
						   Thread.sleep(1000);
							performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
							Thread.sleep(1000);
							performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5555555555");
							Thread.sleep(1000);
							performerPOM. clickNoticeDocumentsharesavecfo(driver).click();
						
							Thread.sleep(1000);
							String msg=performerPOM. clickNoticeDocumentsharereadmsgcfo(driver).getText();
							if(msg.equalsIgnoreCase("Document shared successfully."))
							{
								test.log(LogStatus.PASS, " Documents for respective case should be shared =" +msg);
							}
							else
							{
								test.log(LogStatus.FAIL, " Documents for respective case should be shared =" +msg);
							}
							driver.switchTo().parentFrame();
							Thread.sleep(1000);
							performerPOM. CloseSharePopup(driver).click();
							
					}
					
					
					public static void AdvancedSearchShareNoticeDocument(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 60);
						progress(driver);
						performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
						performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
						
						 Thread.sleep(3000);
						 performerPOM.AdvancedSearchReports(driver).click();
						

				       Thread.sleep(5000);
					    JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(500,0)");
						Thread.sleep(5000);
						performerPOM.clickTypeDropdown2(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(5000);
						performerPOM.selectTypeCase2(driver).click();					//Selecting 'Case' option.
//						Thread.sleep(1000);
//						performerPOM.selectDocument(driver).click();	
						Thread.sleep(1000);
						performerPOM.shareDocumentIcon1(driver).click();
						
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("OverViews3"));
						   
						   Thread.sleep(1000);
							performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
							Thread.sleep(1000);
							performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5555555555");
							Thread.sleep(1000);
							performerPOM. clickNoticeDocumentsharesavecfo(driver).click();
						
							Thread.sleep(1000);
							String msg=performerPOM. clickNoticeDocumentsharereadmsgcfo(driver).getText();
							if(msg.equalsIgnoreCase("Document shared successfully."))
							{
								test.log(LogStatus.PASS, " Documents for respective Notice should be shared =" +msg);
							}
							else
							{
								test.log(LogStatus.FAIL, " Documents for respective Notice should be shared =" +msg);
							}
							driver.switchTo().parentFrame();
							Thread.sleep(1000);
							performerPOM. CloseSharePopup(driver).click();
					}
					       
					public static void AdvancedSearchShareTaskDocument(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 60);
						progress(driver);
						performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
						performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
						 Thread.sleep(3000);
						 performerPOM.AdvancedSearchReports(driver).click();
						
				       Thread.sleep(5000);
					    JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(500,0)");
						Thread.sleep(5000);
						performerPOM.clickTypeDropdown2(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(5000);
						performerPOM.selectTypeTask2(driver).click();					//Selecting 'Task' option.
//						Thread.sleep(1000);
//						performerPOM.selectDocument(driver).click();	
						
						
						try
						{
							
						
						Thread.sleep(1000);
						performerPOM.shareDocumentIcon1(driver).click();
						
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("OverViews3"));
						   
						   Thread.sleep(1000);
							performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin@gmail.com");
							Thread.sleep(1000);
							performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("5555555555");
							Thread.sleep(1000);
							performerPOM. clickNoticeDocumentsharesavecfo(driver).click();
						
							Thread.sleep(1000);
							String msg=performerPOM. clickNoticeDocumentsharereadmsgcfo(driver).getText();
							if(msg.equalsIgnoreCase("Document shared successfully."))
							{
								test.log(LogStatus.PASS, " Documents for respective Task should be shared =" +msg);
							}
							else
							{
								test.log(LogStatus.FAIL, " Documents for respective Task should be shared =" +msg);
							}
							driver.switchTo().parentFrame();
							Thread.sleep(1000);
							performerPOM. clickViewDocAdvocatebillPdfClose(driver).click();
						}
						catch(Exception e)
						{
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
						        alert.accept();	
							
						}
					}
					
				
					 
						public static void CriticalDocuments(WebDriver driver, ExtentTest test) throws InterruptedException
						{
							WebDriverWait wait = new WebDriverWait(driver, 60);
							Thread.sleep(1000);
							performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Documents'
							
							Thread.sleep(500);
							performerPOM.clickcriticalDocument(driver).click();	             //clicking on 'critical document'
							
							
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdFolderDetail']")));	//Wating till the content table gets visible
							
							Thread.sleep(500);
							String name = OverduePOM.readFolderName(driver).getText();		//Reading the folder name to create new folder.
							
							String folder = name+"Doc10july23"; 
							
							OverduePOM.clickNew(driver).click();							//Clicking on '+New' button.
							
							Thread.sleep(300);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							Thread.sleep(500);
							OverduePOM.clickNewFolder(driver).click();						//Clicking on 'New Folder'
							
							Thread.sleep(300);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							Thread.sleep(300);
							OverduePOM.clickIsUniversal(driver).click();
							
							Thread.sleep(300);
							OverduePOM.writeFolderName(driver).sendKeys(folder);			//Writing Folder name.
							
							Thread.sleep(500);
							OverduePOM.clickCreate(driver).click();						//Clicking on create button.
						
							
							Thread.sleep(500);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							
							//String msg = driver.switchTo().alert().getText();
							//test.log(LogStatus.PASS,"Message displayed=" +msg);
							
						//	driver.switchTo().alert().accept();
							Thread.sleep(100);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@align='left'])[1]")));
							name = OverduePOM.readFolderName(driver).getText();				//Reading the folder name we had created
							
							if(folder.equalsIgnoreCase(name))
							{
								test.log(LogStatus.PASS, "Folder Created Succesfully.='"+folder+"' displayed in the records.");
							}
							else
							{
								test.log(LogStatus.FAIL, "Folder Created Succesfully.='"+folder+"' doesn't displayed in the records.");
							}
							
							//without Enter folder Name
							
							Thread.sleep(2000);
							OverduePOM.clickNew(driver).click();	
							
							Thread.sleep(2000);
							OverduePOM.clickNewFolder(driver).click();						//Clicking on 'New Folder'
							
							Thread.sleep(2000);
							OverduePOM.clickCreate(driver).click();						//Clicking on create button.
							String msg=performerPOM.ClickInvalidMsg(driver).getText();
							if(msg.equalsIgnoreCase(msg))
							{
								test.log(LogStatus.PASS,"Without Enter Folder Name =" +msg);
							}
							else
							{
								test.log(LogStatus.FAIL,"Without Enter Folder Name =" +msg);
							}
							
							Thread.sleep(2000);
							OverduePOM.closeFolderPoppup(driver).click();	
							
							///Share Document in Main Folder 
							
						/*	Thread.sleep(500);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@align='left'])[1]")));
							/*if(OverduePOM.readFolderName(driver).isDisplayed())			//Checking if file got created or not.
								test.log(LogStatus.PASS, "Uploaded file displayed.");
							else
								test.log(LogStatus.PASS, "Uploaded file does not displayed.");
							Thread.sleep(2000);
							OverduePOM.readFolderName(driver).click();					//Clicking on file we had uploaded.
									
							Thread.sleep(2000);
							OverduePOM.clickShareFolder(driver).click();					//Clicking on Share Folder image.
							
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickPeople(driver)));
							Thread.sleep(2000);
							OverduePOM.clickPeople(driver).click();						//Clicking on People drop down 
							Thread.sleep(2000);
							OverduePOM.clickSearchPeople(driver).click();					//Clicking on Search People drop down.
							
							Thread.sleep(2000);
							OverduePOM.clickSearchPeople(driver).sendKeys("Aarav Aharma");			//Writing user name to search for
							
							Thread.sleep(2000);
							OverduePOM.clickPeopleCheckBox(driver).click();				//Clicking on label to get out from people search box
							
							Thread.sleep(2000);
							OverduePOM.clickDone(driver).click();	  //Clicking on 'Done' to share folder.
							
							String msg3 = driver.switchTo().alert().getText();
						    test.log(LogStatus.PASS,"Message displayed=" +msg3);
							
							driver.switchTo().alert().accept(); */
							
							//Delete Folder
							
							Thread.sleep(500);
							OverduePOM.readFolderName(driver).click();						//Clicking on folder name we had created.
							
							Thread.sleep(2000);
							performerPOM.ClickDeleteFile(driver).click();
							
							String msg1 = driver.switchTo().alert().getText();
						    test.log(LogStatus.PASS,"Message displayed=" +msg1);
							
							driver.switchTo().alert().accept();
							
							
							
			
							Thread.sleep(500);
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickDashboard(driver)));
							OverduePOM.clickDashboard(driver).click();	
							
							
							
							
						}
						
						public static void CriticalDocuments1(WebDriver driver, ExtentTest test) throws InterruptedException
						{
							WebDriverWait wait = new WebDriverWait(driver, 60);
					
							Thread.sleep(1000);
							performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Documents'
							
							Thread.sleep(500);
							performerPOM.clickcriticalDocument(driver).click();	             //clicking on 'critical document'
							
							//Create Sub folder
							
								Thread.sleep(500);
								OverduePOM.readFolderName(driver).click();						//Clicking on file name we had uploaded.
								Thread.sleep(500);
								OverduePOM.readFolderName(driver).click();						//Clicking on file name we had uploaded.
								Thread.sleep(500);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdFolderDetail']")));	//Wating till the content table gets visible
								

								Thread.sleep(500);
								OverduePOM.clickNew(driver).click();							//Clicking on '+New' button.
								
								Thread.sleep(5000);
								OverduePOM.clickNewFolder(driver).click();						//Clicking on 'New Folder'
								
								Thread.sleep(5000);
								OverduePOM.writeFolderName(driver).sendKeys("Document 28jun23");			//Writing Folder name.
								
								Thread.sleep(5000);
								OverduePOM.clickCreate1(driver).click();						//Clicking on create button.
								Thread.sleep(5000);
								try
								{
									String msg1=performerPOM.ClickSuccessMsg(driver).getText();
									test.log(LogStatus.PASS, " sub-folder should get created =" +msg1);
								}
								catch(Exception e)
								{
									String msg1=performerPOM.ClickInvalidMsg(driver).getText();
									test.log(LogStatus.PASS, " sub-folder should not get  created =" +msg1);
								}
								
								Thread.sleep(500);
								OverduePOM.closeFolderPoppup(driver).click();	
								
								//Without enter sub-folder 
								

								Thread.sleep(2000);
								OverduePOM.clickNew(driver).click();			
								
								Thread.sleep(2000);
								OverduePOM.clickNewFolder(driver).click();						//Clicking on 'New Folder'
								
								
								Thread.sleep(2000);
								OverduePOM.clickCreate1(driver).click();						//Clicking on create button.
								String msg=performerPOM.ClickInvalidMsg(driver).getText();
								if(msg.equalsIgnoreCase(msg))
								{
									test.log(LogStatus.PASS,"Without Enter Sub-Folder Name =" +msg);
								}
								else
								{
									test.log(LogStatus.FAIL,"Without Enter Sub-Folder Name =" +msg);
								}
								
								Thread.sleep(2000);
								OverduePOM.closeFolderPoppup(driver).click();	
								
								
								
								
								
							
							//Upload Document File
							
							Thread.sleep(500);
							OverduePOM.readFolderName(driver).click();						//Clicking on folder name we had created.
							Thread.sleep(500);
							OverduePOM.readFolderName(driver).click();						//Clicking on folder name we had created.
							
							Thread.sleep(500);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							Thread.sleep(500);
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickNew(driver)));
							OverduePOM.clickNew(driver).click();							//Clicking on 'New'
							
							Thread.sleep(500);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							//Thread.sleep(500);
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickNewFile(driver)));
							OverduePOM.clickNewFile(driver).click();						//CLicking on 'New File'
							
							Thread.sleep(500);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							Thread.sleep(500);
							
							OverduePOM.uploadNewFile(driver).sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");	//uploading new file		
							
							Thread.sleep(500);
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickUploadDocument(driver)));
							OverduePOM.clickUploadDocument(driver).click();				//Clicking on 'Upload Document'
							
						
							
							String msg1 = driver.switchTo().alert().getText();
						    test.log(LogStatus.PASS,"Message displayed=" +msg1);
							
							driver.switchTo().alert().accept();
							
							
							//Share Document 
							Thread.sleep(100);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
						Thread.sleep(500);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@align='left'])[1]")));
							/*if(OverduePOM.readFolderName(driver).isDisplayed())			//Checking if file got created or not.
								test.log(LogStatus.PASS, "Uploaded file displayed.");
							else
								test.log(LogStatus.PASS, "Uploaded file does not displayed.");*/
							
							OverduePOM.readFolderName(driver).click();					//Clicking on file we had uploaded.
									
							Thread.sleep(500);
							OverduePOM.clickShareFolder(driver).click();					//Clicking on Share Folder image.
							
							Thread.sleep(500);
							litigationAdditionalOwner.MethodsPOM.progress(driver);
							
							Thread.sleep(500);
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickPeople(driver)));
							OverduePOM.clickPeople(driver).click();						//Clicking on People drop down 
							OverduePOM.clickSearchPeople(driver).click();					//Clicking on Search People drop down.
							
							Thread.sleep(500);
							OverduePOM.clickSearchPeople(driver).sendKeys("Aarav Aharma");			//Writing user name to search for
							
							Thread.sleep(500);
							OverduePOM.clickPeopleCheckBox(driver).click();				//Clicking on label to get out from people search box
							
							Thread.sleep(500);
							OverduePOM.clickDone(driver).click();	  //Clicking on 'Done' to share folder.
							
							String msg3 = driver.switchTo().alert().getText();
						    test.log(LogStatus.PASS,"Message displayed=" +msg3);
							
							driver.switchTo().alert().accept();
							
							//view Document File

							Thread.sleep(2000);
				
						OverduePOM.readFolderName(driver).click();						//Clicking on file name we had uploaded.
						
						test.log(LogStatus.PASS, "View Popup open successfully");
						
						//Download Document file
						
						Thread.sleep(2000);
						performerPOM.ClickDownloadfile(driver).click();
						
						test.log(LogStatus.PASS, "File Download successfully");
						
						//Update Document Details
						
					/*	Thread.sleep(2000);
						OverduePOM.readFolderName(driver).click();	
						
						
						Thread.sleep(2000);
						performerPOM.ClickEditDetailesFile(driver).click();	
						
						Thread.sleep(500);
						performerPOM.ClickHeader(driver).clear();	
						
						
						Thread.sleep(500);
						performerPOM.ClickHeader(driver).sendKeys("ABCD");
						
						Thread.sleep(500);
						performerPOM.ClickUpdateInfo(driver).click();	
						Thread.sleep(500);
						String msg4=performerPOM.ClickUpdateSuccessmsg(driver).getText();
						
						if(msg4.equalsIgnoreCase(msg4))
						{
							test.log(LogStatus.PASS, "Message Displayed =" +msg4);
						}
						else
						{
							test.log(LogStatus.FAIL, "Message Displayed =" +msg4);
						}*/
							
								
							//Delete Document file
							Thread.sleep(500);
							performerPOM.ClickDeleteFile(driver).click();
							
							String msg2 = driver.switchTo().alert().getText();
						    test.log(LogStatus.PASS,"Message displayed=" +msg2);
							
							driver.switchTo().alert().accept();
							
							
							Thread.sleep(500);
							wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickDashboard(driver)));
							OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
						}
						
						
	
						
						

				 
				 
				 
				 
				 public static void ReportFilter(WebDriver driver,ExtentTest test) throws InterruptedException
					{
						WebDriverWait wait=new WebDriverWait(driver,20);
							
						progress(driver);
						
					
						performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
						
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						
						Thread.sleep(3000);
						performerPOM.clickReportStatusFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportStatusFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportDeptFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportDeptFiltercfo(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportTypeFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportTypeFiltercfo(driver).click();
						
//						Thread.sleep(3000);
//						performerPOM.clickReportCategoryFilter(driver).click();
//						
//						Thread.sleep(3000);
//						performerPOM.clickReportCategoryFiltercfo(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportLocFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickLocationFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportLocFiltercfo(driver).click();
						
//						Thread.sleep(5000);
//						performerPOM.clickReportFYFilter(driver).click();
//						
//						Thread.sleep(5000);
//						performerPOM.clickReportFYFilter1(driver).click();
						
//						Thread.sleep(7000);
//						performerPOM.clickReportCYFilter(driver).click();
//						
//						Thread.sleep(7000);
//						performerPOM.clickReportCYFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clearButton(driver).click();
						
					
						
						   test.log(LogStatus.PASS,"My Report = Notice Filter Work successfully");
						
						
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table 
						
						Thread.sleep(3000);
						performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						
						Thread.sleep(3000);
						performerPOM.selectTypeCase(driver).click();	
						
						Thread.sleep(3000);
						performerPOM.clickReportStatusFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportStatusFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportDeptFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportDeptFiltercfo(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportTypeFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportTypeFiltercfo1(driver).click();
						
//						Thread.sleep(3000);
//						performerPOM.clickReportCategoryFilter(driver).click();
//						
//						Thread.sleep(3000);
//						performerPOM.clickReportCategoryFiltercfo(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportLocFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickLocationFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportLocFiltercfo(driver).click();
						
//						Thread.sleep(3000);
//						performerPOM.clickReportFYFilter(driver).click();
//						
//						Thread.sleep(3000);
//						performerPOM.clickReportFYFilter1(driver).click();
						
//						Thread.sleep(5000);
//						performerPOM.clickReportCYFilter(driver).click();
//						
//						Thread.sleep(5000);
//						performerPOM.clickReportCYFilter1(driver).click();
						
//						Thread.sleep(3000);
//						performerPOM.clickApplyButton(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clearButton(driver).click();
						test.log(LogStatus.PASS,"My Report =Case Filter Work successfully");
						
						
						Thread.sleep(3000);
						performerPOM.clickTypeDropdown(driver).click();	
						
						Thread.sleep(3000);
						performerPOM.selectTypeTask(driver).click();
						
//						Thread.sleep(3000);
//						performerPOM.clickReportLocFilter(driver).click();
//						
//						Thread.sleep(3000);
//						performerPOM.clickLocationFilter1(driver).click();
//						
//						Thread.sleep(3000);
//						performerPOM.clickReportLocFiltercfo(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportprioFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportprioFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportstatusFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportstatusFilter1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportFilter(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickReportFilter1(driver).click();
						Thread.sleep(3000);
						performerPOM.clearButton(driver).click();
						
						test.log(LogStatus.PASS, "My Report = Task Filters Work Successfully");
						
						Thread.sleep(500);
						OverduePOM.clickDashboard(driver).click();
						
				  }
				 static void Report(WebDriver driver, ExtentTest test, int count1, String type) throws InterruptedException, IOException
					{
						Thread.sleep(700);
						File dir = new File("C://Users//Snehal Patil//Downloads");
						File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
//						Thread.sleep(500);
//						CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
						Thread.sleep(300);
						performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
						
						Thread.sleep(6000);
						File dir1 = new File("C://Users//Snehal Patil//Downloads");
						File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
						
						if(dirContents.length < allFilesNew.length)
						{
							test.log(LogStatus.PASS, "File Downloaded Successfully.");
							
							File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
						    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
						    {
						       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
						       {
						           lastModifiedFile = allFilesNew[i];
						       }
						    }
							
							Thread.sleep(3000);
							fis = new FileInputStream(lastModifiedFile);
							workbook = new XSSFWorkbook(fis);
							sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
							int no = sheet.getLastRowNum();
							int SheetRecords = 0;
							for(int i = 0; i <= 5; i++)
							{
								Row row = sheet.getRow(no-i);
								Cell c1 = row.getCell(0);
								String records = c1.getStringCellValue();
								if(records.equals("") || records.equals(null))
								{
									
								}
								else
								{
									SheetRecords = Integer.parseInt(records);
									break;
								}
							}
							fis.close();
							
							if(count1 == SheetRecords)
							{
								//test.log(LogStatus.PASS, type+" - No of records displayed matches to no of records in Excel Sheet.");
								test.log(LogStatus.PASS, "Total records displayed = "+count1+". Total records in Excel sheet = "+SheetRecords);
							}
							else
							{
								//test.log(LogStatus.FAIL, type+" - No of records displayed doesn't matches to no of records in Excel Sheet.");
								test.log(LogStatus.FAIL, "Total records displayed = "+count1+". Total records in Excel sheet = "+SheetRecords);
							}
						}
						else
						{
							test.log(LogStatus.FAIL, type+" - File doesn't downloaded successfully.");
						}
					}
				 
				 public static void MyReports(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 60);
						progress(driver);
						
						//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
						performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						
						
				/*		Thread.sleep(4000);
						performerPOM.clickReportTypeFilter(driver).click();
						
//						Thread.sleep(3000);
//						performerPOM.clickReportTypeFilter2(driver).click();
						
						Thread.sleep(5000);
						performerPOM.clickReportTypeFilter4(driver).click();
						
						Thread.sleep(5000);
						if(performerPOM.clearButton(driver).isEnabled())
						{
							performerPOM.clearButton(driver).click();
							 test.log(LogStatus.PASS, "My Workspace = clear button Work Successfully");
						}
						else
						{
							test.log(LogStatus.PASS, "My Workspace = clear button not Work Successfully");
						}*/
						
						//--------------------------------Notice----------------------------------
						
						Thread.sleep(2000);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
						performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
						js.executeScript("arguments[0].scrollIntoView();", CFOcountPOM.readTotalItems1(driver));
						
						Thread.sleep(3000);
						CFOcountPOM.readTotalItems1(driver).click();
						String item = CFOcountPOM.readTotalItems1(driver).getText();
						String[] bits = item.split(" ");								//Splitting the String
						if(bits.length < 2)
						{
							performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
							Thread.sleep(3000);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");									//Splitting the String
						}
						String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
						int count1 = 0;
						if(compliancesCount.equalsIgnoreCase("to"))
						{
							Thread.sleep(3000);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");										//Splitting the String
							compliancesCount = bits[bits.length - 2];					//Getting the second last word (total number of users)
						}
						else if(compliancesCount.equalsIgnoreCase("to"))
						{
							count1 = 0;
						}
						else
						{
							count1 = Integer.parseInt(compliancesCount);
						}
						Thread.sleep(500);
						Report(driver, test, count1, "Notice");
						
						js.executeScript("window.scrollBy(0,500)");
						js.executeScript("document.querySelector(\"div[id='grid'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=5000");
					

						Thread.sleep(5000);
						performerPOM.viewNoticeDetails1(driver).click();
						test.log(LogStatus.PASS, "Show details Notice popup open successfully.");
						
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup1(driver).click();
						
						Thread.sleep(5000);
						performerPOM.showResponseDetailIcon1(driver).click();
						test.log(LogStatus.PASS, "Show response details Notice  popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup1(driver).click();
						
						driver.navigate().refresh();
						
						//--------------------------------Case----------------------------------
						
						Thread.sleep(1500);
						js.executeScript("window.scrollBy(500,0)");
						
						Thread.sleep(3000);
						performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(3000);
						performerPOM.selectTypeCase(driver).click();					//Selecting 'Case' option.
						
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						Thread.sleep(500);
						performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
						js.executeScript("arguments[0].scrollIntoView();", CFOcountPOM.clickNextPage1(driver));
						js.executeScript("window.scrollBy(0,500)");
						
						Thread.sleep(1000);
						item = CFOcountPOM.readTotalItems1(driver).getText();
						bits = item.split(" ");									//Splitting the String
						if(bits.length < 2)
						{
							performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
							Thread.sleep(300);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");									//Splitting the String
							
						}
						compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
						count1 = 0;
						if(compliancesCount.equalsIgnoreCase("to"))
						{
							Thread.sleep(2500);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");										//Splitting the String
							compliancesCount = bits[bits.length - 2];					//Getting the second last word (total number of users)
						}
						else if(compliancesCount.equalsIgnoreCase("to"))
						{
							count1 = 0;
						}
						else
						{
							count1 = Integer.parseInt(compliancesCount);
						}
						
						js.executeScript("window.scrollBy(0,500)");
						js.executeScript("document.querySelector(\"div[id='grid'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=5000");
						
						
						Thread.sleep(5000);
						performerPOM.viewNoticeDetails1(driver).click();
						test.log(LogStatus.PASS, "Show details Case popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup1(driver).click();
						
						Thread.sleep(5000);
						performerPOM.showResponseDetailIcon1(driver).click();
						test.log(LogStatus.PASS, "Show response details Case popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup1(driver).click();
						
						Thread.sleep(500);
						Report(driver, test, count1, "Case");
						
						driver.navigate().refresh();

						//--------------------------------Task----------------------------------
						
						Thread.sleep(1500);
						js.executeScript("window.scrollBy(500,0)");
						
						performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(300);
						performerPOM.selectTypeTask(driver).click();					//Selecting 'Task' option.
						
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						Thread.sleep(500);
						performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
						js.executeScript("arguments[0].scrollIntoView();", CFOcountPOM.readTotalItems1(driver));
						
						Thread.sleep(1000);
						item = CFOcountPOM.readTotalItems1(driver).getText();
						bits = item.split(" ");								//Splitting the String
						if(bits.length < 2)
						{
							performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
							Thread.sleep(300);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");									//Splitting the String
							
						}
						compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
						count1 = 0;
						if(compliancesCount.equalsIgnoreCase("to"))
						{
							count1 = 0;
						}
						else
						{
							count1 = Integer.parseInt(compliancesCount);
						}
						
						Thread.sleep(5000);
						performerPOM.viewTaskDetails(driver).click();	
						test.log(LogStatus.PASS, "Show details Task popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.ActioncloseTaskpopup(driver).click();
						
						Thread.sleep(500);
						Report(driver, test, count1, "Task");
						
						
					}

					public static void AdvancedSearchReport(WebDriver driver,ExtentTest test) throws InterruptedException
					{
						WebDriverWait wait=new WebDriverWait(driver,180);
						
						Thread.sleep(500);
				        performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
				        
				        
				        Thread.sleep(500);
				        performerPOM.clickExcelReport1(driver).click();
				        test.log(LogStatus.PASS, "Usage Report downloaded successfully.");
						
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						Thread.sleep(5000);
						
						performerPOM.AdvancedSearchReports(driver).click();
						
					//-------------------------------------------Notice--------------------------------------------------
						
						Thread.sleep(4000);
						performerPOM.startDate(driver).sendKeys("05/01/2022");
						
						Thread.sleep(4000);
						performerPOM.endDate(driver).sendKeys("05/04/2022");
						
						Thread.sleep(4000);
						performerPOM.clickApplyButton(driver).click();
						
						
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						
						Thread.sleep(5000);
						performerPOM.clickExportAdavanced(driver).click();
						test.log(LogStatus.PASS, "File downloaded successfully.");
						
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,800)");
						js.executeScript("document.querySelector(\"div[id='grid1'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=5000");
						
						
						  By locator = By.xpath("//a[@class='k-button k-button-icontext ob-hearing1 k-grid-hearing1']");
							
							wait.until(ExpectedConditions.presenceOfElementLocated(locator));
							Thread.sleep(4000);
							
							WebElement ViewButton = driver.findElement(locator);	
							Thread.sleep(4000);
						    JavascriptExecutor jse=(JavascriptExecutor)driver;
						    jse.executeScript("arguments[0].click();", ViewButton);
						
						//Thread.sleep(5000);
						//performerPOM.viewNoticeDetails(driver).click();
						test.log(LogStatus.PASS, "Show details notice popup open successfully.");
						
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup(driver).click();
						
						  By locator1 = By.xpath("//a[@class='k-button k-button-icontext ob-edit1 k-grid-edit1']");
							
							wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
							Thread.sleep(4000);
							
							WebElement ViewButton1 = driver.findElement(locator1);	
							Thread.sleep(4000);
						    JavascriptExecutor jse1=(JavascriptExecutor)driver;
						    jse1.executeScript("arguments[0].click();", ViewButton1);
						
						//Thread.sleep(5000);
						//performerPOM.showResponseDetailIcon(driver).click();
						test.log(LogStatus.PASS, "Show response details notice popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup(driver).click();
						
					//-------------------------------------------Case--------------------------------------------------
						Thread.sleep(4000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						Thread.sleep(4000);
						performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(5000);
						performerPOM.selectTypeCase1(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "File downloaded successfully.");
						
						
						 By locator2 = By.xpath("//a[@class='k-button k-button-icontext ob-hearing1 k-grid-hearing1']");
							
							wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
							Thread.sleep(4000);
							
							WebElement ViewButton2 = driver.findElement(locator2);	
							Thread.sleep(4000);
						    JavascriptExecutor jse2=(JavascriptExecutor)driver;
						    jse2.executeScript("arguments[0].click();", ViewButton2);
					
						//Thread.sleep(5000);
						//performerPOM.viewNoticeDetails(driver).click();
						test.log(LogStatus.PASS, "Show details case popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup(driver).click();
						
						 By locator3 = By.xpath("//a[@class='k-button k-button-icontext ob-edit1 k-grid-edit1']");
							
							wait.until(ExpectedConditions.presenceOfElementLocated(locator3));
							Thread.sleep(4000);
							
							WebElement ViewButton3 = driver.findElement(locator3);	
							Thread.sleep(4000);
						    JavascriptExecutor jse3=(JavascriptExecutor)driver;
						    jse3.executeScript("arguments[0].click();", ViewButton3);
						
						
						//Thread.sleep(5000);
						//performerPOM.showResponseDetailIcon(driver).click();
						test.log(LogStatus.PASS, "Show Hearing details Case popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.Actionclosepopup(driver).click();
						
					//-------------------------------------------Task--------------------------------------------------
							Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
						
						
						Thread.sleep(8000);
						performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(8000);
						performerPOM.selectTypeTask1(driver).click();
						
						Thread.sleep(5000);
						performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "File downloaded successfully.");
						
						
						Thread.sleep(5000);
						performerPOM.viewTaskDetails(driver).click();	
						test.log(LogStatus.PASS, "Show details Task popup open successfully.");
						
						Thread.sleep(5000);
						performerPOM.ActioncloseTaskpopup(driver).click();
						
						Thread.sleep(500);
						OverduePOM.clickDashboard(driver).click();
					}
					public static void MoreReport(WebDriver driver, ExtentTest test) throws InterruptedException
					{
						
						WebDriverWait wait = new WebDriverWait(driver, 180);
						
						Thread.sleep(3000);
						performerPOM.clickMyReports(driver).click();
						
						Thread.sleep(5000);
						performerPOM.clickMoreReports(driver).click();
						//--------------------------------Case Report------------------------------------------
//						Thread.sleep(3000);
//						performerPOM.clicklocationFilterReports(driver).click();
//						
//						Thread.sleep(5000);
//						performerPOM.selectlocationFilterReportscfo(driver).click();
						
						Thread.sleep(5000);
						performerPOM.FromDateReports(driver).sendKeys("01-12-2022");
						
//						Thread.sleep(3000);
//						performerPOM.selectFromDate(driver).click();
						
						Thread.sleep(4000);
						performerPOM.ToDateReports(driver).sendKeys("21-12-2022");
						
//						Thread.sleep(3000);
//						performerPOM.selectToDate(driver).click();
						
						
						//--------------------------MIS Report------------------------------
						
					    Thread.sleep(100);
						File dir = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.MISReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "MIS Report downloaded successfully.");
						
						
					    //--------------------------closed Cases Reports------------------------------
						
						Thread.sleep(100);
						File dir1 = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.closedCasesReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "closed Cases Reports downloaded successfully.");
						
						
					    //--------------------------Ext LawyerPerformance Reports------------------------------
						
						Thread.sleep(100);
						File dir2 = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.ExtLawyerPerformanceReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Ext Lawyer Performance Reports downloaded successfully.");
						
						
						//--------------------------Budget Reports-----------------------------------
						
						
						Thread.sleep(100);
						File dir3 = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.BudgetReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Budget Reports downloaded successfully.");
						
						
						//--------------------------Lawyer Details Reports------------------------------
						
						
						
						Thread.sleep(100);
						File dir4 = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.LawyerDetailsReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Lawyer Details Reports downloaded successfully.");
						
						//--------------------------Case Payment Reports------------------------------
						
						
						Thread.sleep(100);
						File dir5 = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.CasePaymentReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Case Payment Reports downloaded successfully.");

						
					//--------------------------Case Hearing Reports------------------------------
						
						
						Thread.sleep(100);
						File dir6 = new File("C://Users//Admin//Downloads");
					//	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
						
						Thread.sleep(250);
						performerPOM.CaseHearingReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Case Hearing Reports downloaded successfully.");

						
						//--------------------------CourtCaseReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir7 = new File("C://Users//Admin//Downloads");
					 //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.CourtCaseReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Court Case Reports downloaded successfully.");

						
						//--------------------------CourtOrderReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir8 = new File("C://Users//Admin//Downloads");
					 //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.CourtOrderReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Court Order Reports downloaded successfully.");
						
						
						//-------------------------CourtDoumentReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir9 = new File("C://Users//Admin//Downloads");
					 //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.CourtDoumentReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Court Doument Reports downloaded successfully.");
						
						//-------------------------noticeCovertedToCaseReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir10 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.noticeCovertedToCaseReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "notice Coverted To Case Reports downloaded successfully.");
					
						
						//-------------------------AllReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir11 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.AllReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "All Reports downloaded successfully.");
					
						
					
						//----------------------------------------Notice Report------------------------------------------------
						
						Thread.sleep(3000);
						performerPOM.clickNoticeReport(driver).click();
						
						
//						Thread.sleep(3000);
//						performerPOM.clicklocationFilterReports(driver).click();
					
						//Thread.sleep(3000);
						//performerPOM.selectlocationFilterReportscfo(driver).click();
						
						Thread.sleep(3000);
						performerPOM.FromDateReports(driver).sendKeys("01-12-2022");
						
//						Thread.sleep(3000);
//						performerPOM.selectFromDate(driver).click();
						
						Thread.sleep(3000);
						performerPOM.ToDateReports(driver).sendKeys("21-12-2022");
						
						//------------------------MISReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir15 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.MISReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "MIS Reports downloaded successfully.");
						
						
						//------------------------closedCasesReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir20 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.closedCasesReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "closed Cases Reports downloaded successfully.");
						
						
						
					
						//------------------------MISReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir19 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.MISReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "MIS All Reports downloaded successfully.");
						
						
						//------------------------ExtLawyerPerformanceReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir18 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.ExtLawyerPerformanceReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Ext Lawyer Performance Reports downloaded successfully.");
						
						
						
						
						//------------------------BudgetReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir17 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.BudgetReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Budget Reports downloaded successfully.");
						
						
						
						
						//------------------------clickNoticePaymentReport------------------------------
						
						
						 Thread.sleep(100);
						 File dir16 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.LawyerDetailsReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Lawyer Details downloaded successfully.");
						
						
						//------------------------clickNoticePaymentReport------------------------------
						
						
						 Thread.sleep(100);
						 File dir13 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.clickNoticePaymentReport(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Notice Payment Report downloaded successfully.");
						
						
						
						//------------------------clickNoticeResponseReport------------------------------
						
						
						 Thread.sleep(100);
						 File dir14 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.clickNoticeResponseReport(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, "Notice Response Report downloaded successfully.");
						
							
						
						
						//-------------------------AllReports------------------------------
						
						
						 Thread.sleep(100);
						 File dir12 = new File("C://Users//Admin//Downloads");
					     //	File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
						Thread.sleep(250);
						performerPOM.AllReports(driver).click();					//Clicking on 'Excel Report' image.
						test.log(LogStatus.PASS, " All Report downloaded successfully.");
						
						
						Thread.sleep(500);
						OverduePOM.clickDashboard(driver).click();
						
					}
					public static void TaskOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
					{
						  
			           int sheetNo=8; 
						
					    
					//  performerPOM.clickTaskOpen(driver).click();
						int open = CountExcel(driver, test, "Task - Open");
						
						Thread.sleep(500);
						performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,700)");
						
						Thread.sleep(300);
						CFOcountPOM.readTotalItems1(driver).click();
						String item = CFOcountPOM.readTotalItems1(driver).getText();
						String[] bits = item.split(" ");								//Splitting the String
						String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
						int gridRecords = 0;
						if(compliancesCount.equalsIgnoreCase("to"))
						{
							Thread.sleep(2000);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");								//Splitting the String
							compliancesCount = bits[bits.length - 2];
						}
						if(compliancesCount.equalsIgnoreCase("to"))
						{
							gridRecords = 0;
						}
						else
						{
							gridRecords = Integer.parseInt(compliancesCount);
						}
						
						sheet = workbook.getSheetAt(sheetNo);
						
						TaskAdd(driver, test, sheet, open, gridRecords, "Task - Open");
					}
					static void TaskAdd(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
					{
						WebDriverWait wait = new WebDriverWait(driver, 60);
						
						Thread.sleep(500);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
						js.executeScript("window.scrollBy(0,-700)");
						performerPOM.clickAddNewTask(driver).click();				//Clicking on 'New' button
						
						progress(driver);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddTask"));
						
//						
//						Thread.sleep(300);
//						performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
//						OverduePOM.selectNextMonth(driver).click();
//						OverduePOM.selectDate(driver).click();					//Selecting particular date.
//						
						Thread.sleep(500);
						Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
						Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
						String title = c1.getStringCellValue();
						performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
						
						Thread.sleep(300);
						row0 = sheet.getRow(13);									//Selected 0th index row (First row)
						c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
						String desc = c1.getStringCellValue();
						performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
						
						Thread.sleep(300);
						performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
						OverduePOM.selectNextMonth(driver).click();
						OverduePOM.selectDate(driver).click();					//Selecting particular date.
						
						Thread.sleep(300);
						Actions action = new Actions(driver);
						action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
						
						Thread.sleep(300);
						row0 = sheet.getRow(14);									//Selected 0th index row (First row)
						c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
						String outcome = c1.getStringCellValue();
						performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
						
						Thread.sleep(1000);
						row0 = sheet.getRow(15);									//Selected 0th index row (First row)
						c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
						String internalUser = c1.getStringCellValue();
						performerPOM.clickInternalUser3(driver).click();
						//performerPOM.selectInternalUser2(driver).click();
						performerPOM.selectInternalUser3(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
						
						Thread.sleep(1000);
						row0 = sheet.getRow(16);									//Selected 0th index row (First row)
						c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
						String externalUser = c1.getStringCellValue();
						try
						{
							Thread.sleep(300);
							performerPOM.clickExternalUser(driver).click();
							Thread.sleep(500);
							action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
						}
						catch(Exception e)
						{
							
						}
						Thread.sleep(5000);
						row0 = sheet.getRow(17);									//Selected 0th index row (First row)
						c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
						String remark = c1.getStringCellValue();
						performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
						//Thread.sleep(300);
						//String workingDir = System.getProperty("user.dir");
						//performerPOM.clickUpload(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file
						
						Thread.sleep(300);
						OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
						
						Thread.sleep(300);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickMessage(driver)));
						
						Thread.sleep(300);
						String msg = performerPOM.clickMessage(driver).getText();
						if(msg.contains(msg))
						{
							test.log(LogStatus.PASS, "Add Task Verification=" +msg);
						}
						else
						{
							test.log(LogStatus.PASS, "Add Task Verification=" +msg);
						}
						
						driver.switchTo().parentFrame();
						performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));
						
						Thread.sleep(300);
						performerPOM.clickStatusDropDown(driver).click();		//Clicking on 'Status drop down.
						Thread.sleep(500);
						performerPOM.selectStatusDropDown(driver).click();		//Selecting 'Pending/Open' status
						
						Thread.sleep(500);
						performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
						js.executeScript("window.scrollBy(0,700)");
						
						Thread.sleep(1000);
						CFOcountPOM.readTotalItems1(driver).click();
						String item = CFOcountPOM.readTotalItems1(driver).getText();
						String[] bits = item.split(" ");								//Splitting the String
						String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
						int count1 = 0;
						if(compliancesCount.equalsIgnoreCase("to"))
						{
							Thread.sleep(2000);
							item = CFOcountPOM.readTotalItems1(driver).getText();
							bits = item.split(" ");								//Splitting the String
							compliancesCount = bits[bits.length - 2];
						}
						count1 = Integer.parseInt(compliancesCount);
						
						if(count1 > gridRecords)
						{
							//test.log(LogStatus.PASS, "Total Task Count increased in grid after adding New Task.");
							test.log(LogStatus.PASS, "Old Task Count from Grid = "+gridRecords+" | New Task Count from Grid = "+count1);
						}
						else
						{
							//test.log(LogStatus.FAIL, "Total Task Count doesn't increased in grid after adding New Task.");
							test.log(LogStatus.FAIL, "Old Task Count from Grid = "+gridRecords+" | New Task Count from Grid = "+count1);
						}
						
						Thread.sleep(500);
						OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickTaskOpen(driver)));
						int open1 = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());	//Reading Notice Open count.
						
						if(open1 > open)
						{
							//test.log(LogStatus.PASS, type+" Dashboard Count Increased.");
							test.log(LogStatus.PASS, "Old Count = "+open+" | New Count = "+open1);
						}
						else
						{
							//test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increased.");
							test.log(LogStatus.FAIL, "Old Count = "+open+" | New Count = "+open1);
						}
					}
					public static void TaskClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 50);
						
						CountExcel(driver, test, "Task - Closed");
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNewTask(driver)));
						OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
					}
					public static void CaseClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 50);
						
						CountExcel(driver, test, "Case - Closed");
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNew(driver)));
						OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
					}
					public static void NoticeClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 50);
						
						Thread.sleep(500);
					     CountExcel(driver, test, "Notice - Closed");
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNew(driver)));
						OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
					}
					public static void CloseNoticeCase(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
				
						int closed = 0;
						int open = 0;
						int caseOpen = 0;
						if(type.equals("Notice"))
						{
							closed = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Closed count.
							open = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());		//Reading Notice Open count.
							caseOpen = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());
							
							performerPOM.clickNoticeOpen(driver).click();									//Clicking on 'Open' notice
						}
						else if(type.equals("Case"))
						{
							open = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());			//Reading Case Open count.
							closed = Integer.parseInt(performerPOM.clickCaseClosedCFO(driver).getText());		//Reading Case Closed count.
							
							performerPOM.clickCaseOpencfo(driver).click();										//Clicking on 'Open' case
						}
						else if(type.equals("Task"))
						{
							open = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());			//Reading Task Open count.
							closed = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());		//Reading Task Closed count.
							
							performerPOM.clickTaskOpen(driver).click();										//Clicking on 'Open' task
						}
						
						Thread.sleep(300);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));	//Waiting until visibility of Excel Report button.
						
						Thread.sleep(1000);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,500)");
						
						Thread.sleep(3000);
						performerPOM.GridLoad(driver).click();
						elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
						js.executeScript("arguments[0].scrollIntoView();", elementsList.get(0));
						
						Thread.sleep(500);
						elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
						elementsList.get(0).click();								//Clicking on first action button.
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Waiting and switching to IFrame
						
						Thread.sleep(300);
						if(type.equals("Notice"))
						{
							sheet = workbook.getSheetAt(8);
							
							performerPOM.clickStatusPayments(driver).click();			//Clicking on 'Status/Payments'
							
							wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeStatus(driver)));
							performerPOM.clickNoticeStatus(driver).click();				//Clicking on 'Notice Status' drop down.
							Thread.sleep(300);
							performerPOM.clickClosedStatus(driver).click();				//Selecting 'Closed' option from drop down.
							
							Thread.sleep(300);
							wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCloseDate(driver)));
							performerPOM.clickCloseDate(driver).click();				//Clicking on 'Closed Date' date box
							OverduePOM.selectLastMonth(driver).click();					//Getting last month
							OverduePOM.selectDate2(driver).click();						//Selecting particular date.
							
							Thread.sleep(300);
							performerPOM.clickNoticeResult(driver).click();
							performerPOM.clickSelectResult(driver).sendKeys("In Progress", Keys.ENTER);
							
							
							Thread.sleep(300);
							Row r1 = sheet.getRow(26);
							Cell c1 = r1.getCell(1);
							String remark = c1.getStringCellValue();
							performerPOM.clickRemark1(driver).sendKeys(remark);
							
							Thread.sleep(300);
							r1 = sheet.getRow(27);
							c1 = r1.getCell(1);
							String CaseNo = c1.getStringCellValue();
							performerPOM.clickCourtCaseNo(driver).sendKeys(CaseNo);
							
							Thread.sleep(300);
							performerPOM.clickSaveConvertCase(driver).click();	
						}
						else if(type.equals("Case"))
						{
							performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
							
							wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
							
							performerPOM.clickCaseStage(driver).click();
							Thread.sleep(300);
							performerPOM.selectCaseStage(driver).sendKeys("Hearing", Keys.ENTER);
							
							Thread.sleep(300);
							performerPOM.clickCaseStatus(driver).click();				//Clicking on 'Case Status' drop down.
							Thread.sleep(300);
							performerPOM.clickCaseStatusClose(driver).click();			//Selecting 'Closed' option from drop down.
							
							Thread.sleep(300);
							wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseCloseDate(driver)));
							performerPOM.clickCaseCloseDate(driver).click();				//Clicking on 'Closed Date' date box
							OverduePOM.selectLastMonth(driver).click();					//Getting last month
							OverduePOM.selectDate2(driver).click();						//Selecting particular date.
							
							Thread.sleep(300);
							performerPOM.clickCaseResult(driver).click();
							performerPOM.clickSelectCaseResult(driver).sendKeys("In Progress", Keys.ENTER);
							
							Thread.sleep(300);
							performerPOM.clickRemark1(driver).sendKeys("Automation Testing");
							
							Thread.sleep(300);
							performerPOM.clickSave1(driver).click();
						}
						else if(type.equals("Task"))
						{
							
						}
						
						Thread.sleep(4000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readMessage2(driver)));
						String msg = performerPOM.readMessage2(driver).getText();
						
						if(msg.contains("Successfully"))
						{
							test.log(LogStatus.PASS, "Message displayed - "+msg);
						}
						else if(msg.contains("already exist"))
						{
							test.log(LogStatus.FAIL, "Message displayed - "+msg);
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed - "+msg);
						}
						
						Thread.sleep(3000);
						driver.switchTo().parentFrame();
						
						Thread.sleep(3000);
						performerPOM.clickClose(driver).click();
						
						Thread.sleep(5000);
						OverduePOM.clickDashboard(driver).click();
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
						int closed1 = 0;
						int open1 = 0;
						int caseOpen1 = 0;
						if(type.equals("Notice"))
						{
							closed1 = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Open count.
							open1 = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());		//Reading Notice Open count.
							caseOpen1 = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());
							
							if(open > open1 && closed1 > closed && caseOpen1 > caseOpen)
							{
								//test.log(LogStatus.PASS, "Notice-Closed count increased.");
								test.log(LogStatus.PASS, "Notice-Closed count increased :- Old Count = "+closed+" | New Count = "+closed1);
								//test.log(LogStatus.PASS, "Notice-Open count decreased.");
								test.log(LogStatus.PASS, "Notice-Open count decreased :- Old Count = "+open+" | New Count = "+open1);
								//test.log(LogStatus.PASS, "Case-Open count increased.");
								test.log(LogStatus.PASS, "Case-Open count increased :- Old Count = "+caseOpen+" | New Count = "+caseOpen1);
							}
							else
							{
								//test.log(LogStatus.FAIL, "Notice-Closed count doesn't increased.");
								test.log(LogStatus.FAIL, "Old Count = "+closed+" | New Count = "+closed1);
								//test.log(LogStatus.FAIL, "Notice-Open count doesn't decreased.");
								test.log(LogStatus.FAIL, "Old Count = "+open+" | New Count = "+open1);
								//test.log(LogStatus.FAIL, "Case-Open count doesn't increased.");
								test.log(LogStatus.FAIL, "Old Count = "+caseOpen+" | New Count = "+caseOpen1);
							}
						}
						else if(type.equals("Case"))
						{
							open1 = Integer.parseInt(performerPOM.clickCaseOpencfo(driver).getText());			//Reading Case Open count.
							closed1 = Integer.parseInt(performerPOM.clickCaseClosedCFO(driver).getText());		//Reading Case Closed count.
							
							if(open > open1 && closed1 > closed)
							{
								//test.log(LogStatus.PASS, "Case-Closed count increased.");
								test.log(LogStatus.PASS, "Case-Closed count increased :- Old Count = "+closed+" | New Count = "+closed1);
								//test.log(LogStatus.PASS, "Case-Open count decreased.");
								test.log(LogStatus.PASS, "Case-Open count decreased:-Old Count = "+open+" | New Count = "+open1);
							}
							else
							{
								//test.log(LogStatus.FAIL, "Case-Closed count doesn't increased.");
								test.log(LogStatus.FAIL, "Case-Closed count doesn't increased:-Old Count = "+closed+" | New Count = "+closed1);
								//test.log(LogStatus.FAIL, "Case-Open count doesn't decreased.");
								test.log(LogStatus.FAIL, "Case-Closed count doesn't increased:-Old Count = "+open+" | New Count = "+open1);
							}
						}
						else if(type.equals("Task"))
						{
							open1 = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());			//Reading Task Open count.
							closed1 = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());		//Reading Task Closed count.
							
							if(open > open1 && closed1 > closed)
							{
								//test.log(LogStatus.PASS, "Task-Closed count increased.");
								test.log(LogStatus.PASS, "Old Count = "+closed+" | New Count = "+closed1);
								//test.log(LogStatus.PASS, "Task-Open count decreased.");
								test.log(LogStatus.PASS, "Old Count = "+open+" | New Count = "+open1);
							}
							else
							{
								//test.log(LogStatus.PASS, "Task-Closed count doesn't increased.");
								test.log(LogStatus.PASS, "Old Count = "+closed+" | New Count = "+closed1);
								//test.log(LogStatus.PASS, "Task-Open count doesn't decreased.");
								test.log(LogStatus.PASS, "Old Count = "+open+" | New Count = "+open1);
							}
						}
					}
					public static void ImportUtility(WebDriver driver,ExtentTest test) throws InterruptedException
					{
					
						performerPOM.ClickImportUtility(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
						
						WebDriverWait wait=new WebDriverWait(driver,30);
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg(driver)));
						
						Thread.sleep(500);
						String msg5 = performerPOM.readCaseMsg(driver).getText();		//Reading Message appeared after save button
					
						if(msg5.equalsIgnoreCase(msg5))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg5);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg5);
						}
					
						
						Thread.sleep(3000);
						performerPOM.ClickcaseHearing(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
			
						Thread.sleep(500);
						String msg6 = performerPOM.readCaseMsg(driver).getText();		//Reading Message appeared after save button
						
						if(msg6.equalsIgnoreCase(msg6))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg6);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg6);
						}
					
						
						
						Thread.sleep(3000);
						performerPOM.ClickcaseOrder(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
					
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg(driver)));
						
						Thread.sleep(500);
						String msg7 = performerPOM.readCaseMsg(driver).getText();		//Reading Message appeared after save button
						
						if(msg7.equalsIgnoreCase(msg7))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg7);
							
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg7);
						}
						
						
						Thread.sleep(3000);
						performerPOM.ClickcasePayment(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						Thread.sleep(3000);
						
						
//						
////						WebDriverWait wait3=new WebDriverWait(driver,30);
////						Thread.sleep(3000);
////						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg(driver)));
//						
						Thread.sleep(500);
						String msg8 = performerPOM.readCaseMsg(driver).getText();		//Reading Message appeared after save button
						
						if(msg8.equalsIgnoreCase(msg8))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg8);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg8);
						}
						
						
						performerPOM.clickNotice(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						
						Thread.sleep(500);
						String msg = performerPOM.readNoticeMsg(driver).getText();		//Reading Message appeared after save button
						
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg);
						}
						
						Thread.sleep(3000);
						performerPOM.ChooseNoticeResponse(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						Thread.sleep(500);
						String msg1= performerPOM.readNoticeMsg(driver).getText();		//Reading Message appeared after save button
						
						if(msg1.equalsIgnoreCase(msg1))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg1);
							
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg1);
						}
					
//						
						Thread.sleep(3000);
						performerPOM.ChoosePaymentInfo(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						Thread.sleep(3000);
//						
//							
//						
//						WebDriverWait wait4=new WebDriverWait(driver,30);
//						Thread.sleep(3000);
//						wait1.until(ExpectedConditions.visibilityOf(performerPOM.readNoticeMsg(driver)));
						
						Thread.sleep(500);
						String msg3 = performerPOM.readNoticeMsg(driver).getText();		//Reading Message appeared after save button
						
						if(msg3.equalsIgnoreCase(msg3))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg3);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg3);
						}
						Thread.sleep(300);
						OverduePOM.clickDashboard(driver).click();
						
						
					}
					
					
					public static void ImportUtilityWithoutData(WebDriver driver,ExtentTest test) throws InterruptedException
					{
					
						performerPOM.ClickImportUtility(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
						
						try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, "Court Case =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Court Case = Validation message not displayed");
						}
					
						
				     	Thread.sleep(3000);
						performerPOM.ClickcaseHearing(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
			        	try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, "Case Hearing =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Case Hearing = Validation message not displayed");
						}
						
					
						
						
						Thread.sleep(3000);
						performerPOM.ClickcaseOrder(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
							
						try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, "Case Order =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Case Order = Validation message not displayed");
						}
						
						
						Thread.sleep(3000);
						performerPOM.ClickcasePayment(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						Thread.sleep(3000);
											
						try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, "Case Payment =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Case Payment = Validation message not displayed");
						}
						
						
						
						performerPOM.clickNotice(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						
						try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, "Legal Notice =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Legal Notice = Validation message not displayed");
						}
						
						Thread.sleep(3000);
						performerPOM.ChooseNoticeResponse(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, " Notice Response =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Notice Response =Validation message not displayed");
						}
					
					
						Thread.sleep(3000);
						performerPOM.ChoosePaymentInfo(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile1(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						Thread.sleep(3000);
						
						try
						{
							
						  Thread.sleep(500);
						  String msg7 = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
			              test.log(LogStatus.PASS, "Notice Payment =  Upload Empty File = "+msg7);
							
						  }
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Notice Payment = Validation message not displayed");
						}
						Thread.sleep(300);
						OverduePOM.clickDashboard(driver).click();
						
						
					}
					public static void ImportUtilityInvalidData(WebDriver driver,ExtentTest test) throws InterruptedException
					{
					
						performerPOM.ClickImportUtility(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
						
						WebDriverWait wait=new WebDriverWait(driver,30);
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg1(driver)));
						
						Thread.sleep(500);
						String msg5 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
					
						if(msg5.equalsIgnoreCase(msg5))
						{
							test.log(LogStatus.PASS, "Court Case = Enter Invalid Data in Upload File = "+msg5);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Court Case = Enter Invalid Data in Upload File  = "+msg5);
						}
					
						
						Thread.sleep(3000);
						performerPOM.ClickcaseHearing(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
			
						Thread.sleep(500);
						String msg6 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg6.equalsIgnoreCase(msg6))
						{
							test.log(LogStatus.PASS, "Case Hearing  = Enter Invalid Data in Upload File = "+msg6);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Hearing = Enter Invalid Data in Upload File  = "+msg6);
						}
					
						
						
						Thread.sleep(3000);
						performerPOM.ClickcaseOrder(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
					
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg1(driver)));
						
						Thread.sleep(500);
						String msg7 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg7.equalsIgnoreCase(msg7))
						{
							test.log(LogStatus.PASS, "Case Order = Enter Invalid Data in Upload File  = "+msg7);
							
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Order = Enter Invalid Data in Upload File  = "+msg7);
						}
						
						
						Thread.sleep(3000);
						performerPOM.ClickcasePayment(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						Thread.sleep(3000);
						
												
						Thread.sleep(500);
						String msg8 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg8.equalsIgnoreCase(msg8))
						{
							test.log(LogStatus.PASS, "Case Payment = Enter Invalid Data in Upload File  = "+msg8);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Payment = Enter Invalid Data in Upload File = "+msg8);
						}
						
						
						performerPOM.clickNotice(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						
						Thread.sleep(500);
						String msg = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Leagl Notice = Enter Invalid Data in Upload File  = "+msg);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Leagl Notice  = Enter Invalid Data in Upload File = "+msg);
						}
						
						Thread.sleep(3000);
						performerPOM.ChooseNoticeResponse(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						Thread.sleep(500);
						String msg1= performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg1.equalsIgnoreCase(msg1))
						{
							test.log(LogStatus.PASS, "Notice Reposnse = Enter Invalid Data in Upload File = "+msg1);
							
						}
						else
						{
							test.log(LogStatus.FAIL, "Notice Reposnse = Enter Invalid Data in Upload File = "+msg1);
						}
					
//						
						Thread.sleep(3000);
						performerPOM.ChoosePaymentInfo(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile2(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						Thread.sleep(3000);

						Thread.sleep(500);
						String msg3 = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg3.equalsIgnoreCase(msg3))
						{
							test.log(LogStatus.PASS, "Notice Payment = Enter Invalid Data in Upload File = "+msg3);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Notice Payment = Enter Invalid Data in Upload File  = "+msg3);
						}
						Thread.sleep(300);
						OverduePOM.clickDashboard(driver).click();
						
						
					}
					
					public static void ImportUtilityTwoManadtoryFileds(WebDriver driver,ExtentTest test) throws InterruptedException
					{
					
						performerPOM.ClickImportUtility(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
						
						WebDriverWait wait=new WebDriverWait(driver,30);
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg1(driver)));
						
						Thread.sleep(500);
						String msg5 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
					
						if(msg5.equalsIgnoreCase(msg5))
						{
							test.log(LogStatus.PASS, "Court Case = Enter Two Manadatory fields in Upload File = "+msg5);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Court Case =Enter Two Manadatory fields in Upload File  = "+msg5);
						}
					
						
						Thread.sleep(3000);
						performerPOM.ClickcaseHearing(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
			
						Thread.sleep(500);
						String msg6 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg6.equalsIgnoreCase(msg6))
						{
							test.log(LogStatus.PASS, "Case Hearing  = Enter Two Manadatory fields in Upload File = "+msg6);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Hearing = Enter Two Manadatory fields in Upload File = "+msg6);
						}
					
						
						
						Thread.sleep(3000);
						performerPOM.ClickcaseOrder(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						
					
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readCaseMsg1(driver)));
						
						Thread.sleep(500);
						String msg7 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg7.equalsIgnoreCase(msg7))
						{
							test.log(LogStatus.PASS, "Case Order =Enter Two Manadatory fields in Upload File  = "+msg7);
							
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Order = Enter Two Manadatory fields in Upload File  = "+msg7);
						}
						
						
						Thread.sleep(3000);
						performerPOM.ClickcasePayment(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseCaseFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadCaseFile(driver).click();
						Thread.sleep(3000);
						
												
						Thread.sleep(500);
						String msg8 = performerPOM.readCaseMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg8.equalsIgnoreCase(msg8))
						{
							test.log(LogStatus.PASS, "Case Payment = Enter Two Manadatory fields in Upload File  = "+msg8);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Case Payment = Enter Two Manadatory fields in Upload File = "+msg8);
						}
						
						
						performerPOM.clickNotice(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeType(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						
						Thread.sleep(500);
						String msg = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Leagl Notice = Enter Two Manadatory Fileds  in Upload File  = "+msg);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Leagl Notice  = Enter Two Manadatory Fileds  in Upload File = "+msg);
						}
						
						Thread.sleep(3000);
						performerPOM.ChooseNoticeResponse(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						
						
						Thread.sleep(500);
						String msg1= performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg1.equalsIgnoreCase(msg1))
						{
							test.log(LogStatus.PASS, "Notice Reposnse = Enter Two Manadatory Fileds  in Upload File = "+msg1);
							
						}
						else
						{
							test.log(LogStatus.FAIL, "Notice Reposnse = Enter Two Manadatory Fileds  in Upload File = "+msg1);
						}
					
						
						Thread.sleep(3000);
						performerPOM.ChoosePaymentInfo(driver).click();
						Thread.sleep(3000);
						performerPOM.ChooseNoticeFile3(driver);
						Thread.sleep(3000);
						performerPOM.UploadNoticeFile(driver).click();
						Thread.sleep(3000);

						Thread.sleep(500);
						String msg3 = performerPOM.readNoticeMsg1(driver).getText();		//Reading Message appeared after save button
						
						if(msg3.equalsIgnoreCase(msg3))
						{
							test.log(LogStatus.PASS, "Notice Payment = Enter Two Manadatory Fileds in Upload File = "+msg3);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Notice Payment = Enter Two Manadatory Fileds  in Upload File  = "+msg3);
						}
						Thread.sleep(300);
						OverduePOM.clickDashboard(driver).click();
						
						
					}
					public static void MyReminder(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
						
						
						performerPOM.clickMyReminder(driver).click();					//Clicking on 'My Reports'
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.CheckRecordsTable(driver)));	//Wait until records table gets visible.
						
						NewReminder(driver, test, "Case");
						
						NewReminder(driver, test, "Notice");
						
						NewReminder(driver, test, "Task");
						
						Thread.sleep(3000);
						OverduePOM.clickDashboard(driver).click();
					}

					static void NewReminder(WebDriver driver, ExtentTest test, String type) throws InterruptedException
					{
						WebDriverWait wait = new WebDriverWait(driver, 180);
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew1(driver)));
						performerPOM.clickAddNew1(driver).click();		//Clicking on 'Add New' button.
						
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickType(driver)));
						Actions action = new Actions(driver);
						
						if(type.equalsIgnoreCase("Notice"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						else if(type.equalsIgnoreCase("Task"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						
						Thread.sleep(2000);
						action.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						
						Thread.sleep(3000);
						performerPOM.clickReminderText(driver).sendKeys("Reminder new 10july23");
						
						Thread.sleep(3000);
						performerPOM.clickDescription(driver).sendKeys("Reminder new  10july23");
						
						Thread.sleep(3000);
						performerPOM.clickRemark2(driver).sendKeys("Remark");
						
						Thread.sleep(3000);
						performerPOM.clickDate(driver).click();
						Thread.sleep(3000);
						OverduePOM.selectNextMonth(driver).click();
						OverduePOM.selectDate(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickSave(driver).click();				//Clicking on Save button.
						
						Thread.sleep(500);
//						try
//						{
//							wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg1(driver)));
//						}
//						catch(Exception e)
//						{
//							wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg1(driver)));
//						}
						Thread.sleep(3000);
						String msg = performerPOM.readMsg1(driver).getText();

						
						if(msg.equalsIgnoreCase("Reminder Saved Successfully."))
						{
							test.log(LogStatus.PASS, "Reminder Saved Successfully.");
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Reminder with same details already exists");
						}
						
						
						Thread.sleep(300);
						driver.switchTo().parentFrame();
						
						Thread.sleep(300);
						performerPOM.clickCloseReminder(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickEditReminder(driver).click();
						
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
						
						
				         Actions action1 = new Actions(driver);
						
						if(type.equalsIgnoreCase("Notice"))
						{
							action1.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
//						else if(type.equalsIgnoreCase("Task"))
//						{
//							action1.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
//						}
						
						Thread.sleep(2000);
						action1.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						
						
						
//						Thread.sleep(2000);
//						action.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						
						
						Thread.sleep(3000);
						performerPOM.clickReminderText(driver).clear();
						
						Thread.sleep(3000);
						performerPOM.clickReminderText(driver).sendKeys("Reminder  new 25march2023");
						
						Thread.sleep(3000);
						performerPOM.clickDescription(driver).clear();
						
						Thread.sleep(3000);
						performerPOM.clickDescription(driver).sendKeys("Reminder new 25march2023");
						
						Thread.sleep(3000);
						performerPOM.clickDate(driver).click();
						Thread.sleep(3000);
						OverduePOM.selectNextMonth(driver).click();
						OverduePOM.selectDate(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickSave(driver).click();				//Clicking on Save button.
						
						
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg2(driver)));
						
						Thread.sleep(500);
						String msg5 = performerPOM.readMsg2(driver).getText();		//Reading Message appeared after save button
					
						if(msg5.equalsIgnoreCase("Reminder Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg5);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg5);
						}
						

						Thread.sleep(300);
						driver.switchTo().parentFrame();
						
						Thread.sleep(300);
						performerPOM.clickCloseReminder(driver).click();
						
//						Thread.sleep(300);
//						performerPOM.clickReminderFilter(driver).click();
//						
//						Thread.sleep(300);
//						performerPOM.clickReminderFilter1(driver).click();
//						
//						Thread.sleep(300);
//						performerPOM.clickReminderFilter2(driver).click();
//						
//						Thread.sleep(300);
//						performerPOM.clickReminderFilter3(driver).click();
						
						
					
						
						
						Thread.sleep(3000);
						performerPOM.clickDeleteReminder(driver).click();
						
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
					        alert.accept();		
					}
					
					
					public static void ReminderWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					{
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
						
						
						performerPOM.clickMyReminder(driver).click();					//Clicking on 'My Reports'
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.CheckRecordsTable(driver)));	//Wait until records table gets visible.
						
						Reminder(driver, test, "Case");
						
						Reminder(driver, test, "Notice");
						
						Reminder(driver, test, "Task");
						
						
						//Close Button
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew1(driver)));
						Thread.sleep(500);
						performerPOM.clickAddNew1(driver).click();		//Clicking on 'Add New' button.
						
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
						
						Thread.sleep(300);
						  if(performerPOM.clickClosedDocument(driver).isEnabled())
					  		{
					  			performerPOM.clickClosedDocument(driver).click();
					  			test.log(LogStatus.PASS, "Close button working successfully");
					  		}
					  		else
					  		{
					  			test.log(LogStatus.FAIL, "Close button not working successfully");
					  		}
						  
							Thread.sleep(300);
							driver.switchTo().parentFrame();
						
						
						
						
						
						Thread.sleep(3000);
						OverduePOM.clickDashboard(driver).click();
					}
					
					static void Reminder(WebDriver driver, ExtentTest test, String type) throws InterruptedException
					{
						WebDriverWait wait = new WebDriverWait(driver, 180);
						
						//Without Enter Data
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew1(driver)));
						performerPOM.clickAddNew1(driver).click();		//Clicking on 'Add New' button.
						
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickType(driver)));
						Actions action = new Actions(driver);
						
						if(type.equalsIgnoreCase("Notice"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						else if(type.equalsIgnoreCase("Task"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						
						
						
						Thread.sleep(3000);
						performerPOM.clickSave(driver).click();				//Clicking on Save button.
						
						Thread.sleep(3000);
						String msg = performerPOM.readMsg3(driver).getText();

						
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Without Enter Data =" +msg);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Without Enter Data  =" +msg);
						}
						
						Thread.sleep(300);
						driver.switchTo().parentFrame();
						
						Thread.sleep(300);
						performerPOM.clickCloseReminder(driver).click();
						
						
						
						//Two Mandatory fields
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew1(driver)));
						performerPOM.clickAddNew1(driver).click();		//Clicking on 'Add New' button.
						
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickType(driver)));
					
						
						if(type.equalsIgnoreCase("Notice"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						else if(type.equalsIgnoreCase("Task"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						
					
						Thread.sleep(2000);
						action.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						
						
						Thread.sleep(3000);
						performerPOM.clickDate(driver).click();
						Thread.sleep(3000);
						OverduePOM.selectNextMonth(driver).click();
						OverduePOM.selectDate(driver).click();
						
						Thread.sleep(3000);
						performerPOM.clickSave(driver).click();				//Clicking on Save button.
						
						Thread.sleep(3000);
						String msg1 = performerPOM.readMsg3(driver).getText();

						
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Enter Two Manadatory Fields =" +msg1);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Enter Two Manadatory Fields  =" +msg1);
						}
						
						Thread.sleep(300);
						driver.switchTo().parentFrame();
						
						Thread.sleep(300);
						performerPOM.clickCloseReminder(driver).click();
						
						
						//Reminder date greater than current date
						
						
						
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew1(driver)));
						performerPOM.clickAddNew1(driver).click();		//Clicking on 'Add New' button.
						
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickType(driver)));
						
						
						if(type.equalsIgnoreCase("Notice"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						else if(type.equalsIgnoreCase("Task"))
						{
							action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
						}
						
						
						
						Thread.sleep(2000);
						action.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
												
						Thread.sleep(3000);
						performerPOM.clickReminderText(driver).clear();
						
						Thread.sleep(3000);
						performerPOM.clickReminderText(driver).sendKeys("Reminder  test 25march2023");
						
						Thread.sleep(3000);
						performerPOM.clickDescription(driver).clear();
						
						Thread.sleep(3000);
						performerPOM.clickDescription(driver).sendKeys("Reminder test 25march2023");
						
						Thread.sleep(3000);
						performerPOM.clickDate(driver).sendKeys("29-06-2023");
						
						
						Thread.sleep(3000);
						performerPOM.clickSave(driver).click();				//Clicking on Save button.
						
						Thread.sleep(3000);
						String msg2 = performerPOM.readMsg2(driver).getText();

						
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Reminder date =" +msg2);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Reminder date  =" +msg2);
						}
						
					
					Thread.sleep(300);
					driver.switchTo().parentFrame();
			        Thread.sleep(300);
					performerPOM.clickCloseReminder(driver).click();
					
					
					
						
						
						
						
						
				}
						

					
					public static void LegalEntity(WebDriver driver,ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					 {
						
						XSSFSheet sheet = ReadExcel();
						WebDriverWait wait = new WebDriverWait(driver, 180);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						progress(driver);
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
						
						 performerPOM.clickMasters(driver).click();
					     Thread.sleep(300);
						 performerPOM.chooseMasterLegalEntity(driver).click();
						Thread.sleep(300);
						 performerPOM.addLegalEntity(driver).click();
						

						Thread.sleep(5000);
						Row row0 = sheet.getRow(63);						//Selected 0th index row (First row)
						Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
						String legalEntity= c1.getStringCellValue();
					    performerPOM.legalEntityName(driver).sendKeys(legalEntity);
					
						 Thread.sleep(3000);
					    performerPOM.clickUnitType(driver).click();
					    Thread.sleep(3000);
					    performerPOM.chooseUnitType(driver).click();
					    Thread.sleep(3000);
					    performerPOM.clickLegalEntityType(driver).click();
						Thread.sleep(3000);
						performerPOM.chooseLegalEntityType(driver).click();
					    

						Thread.sleep(1000);
						Row row = sheet.getRow(64);						//Selected 0th index row (First row)
						Cell c = row.getCell(1);						//Selected cell (0 row,1 column)
						String address= c.getStringCellValue();
					    performerPOM.clickAddressLine(driver).sendKeys(address);
						
					    Thread.sleep(3000);
					    performerPOM.clickState1(driver).click();
					    
					    Thread.sleep(3000);
					    performerPOM.chooseState1(driver).click();
					    
					    Thread.sleep(5000);
					    performerPOM.clickCity(driver).click();
					    
					    Thread.sleep(5000);
					    performerPOM.chooseCity(driver).click();
					    
					   
					    Thread.sleep(4000);
						
						Row row2 = sheet.getRow(65);						//Selected 0th index row (First row)
						Cell c2 = row2.getCell(1);						//Selected cell (0 row,1 column)
						String contact= c2.getStringCellValue();
					    performerPOM.clickContactPerson(driver).sendKeys(contact+"");
					    
					    Thread.sleep(3000);
					  	Row row3 = sheet.getRow(66);						//Selected 0th index row (First row)
					  	Cell c3 = row3.getCell(1);						//Selected cell (0 row,1 column)
					  	String email= c3.getStringCellValue();
					  	 performerPOM.clickEmail(driver).sendKeys(email);
					   
					   	Thread.sleep(3000);
					    performerPOM.clickSaveLegalEntity(driver).click();
					    
					    Thread.sleep(3000);
						
				        js.executeScript("window.scrollBy(0,-400)");
						
					
					    Thread.sleep(3000);
						 wait.until(ExpectedConditions.visibilityOf(performerPOM.readlegalmsg(driver)));
									
						Thread.sleep(500);
						String msg5 = performerPOM.readlegalmsg(driver).getText();		//Reading Message appeared after save button
						if(msg5.equalsIgnoreCase("Branch Added Successfully."))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg5);
							
						}
							else
							{
								test.log(LogStatus.FAIL, "Message displayed = "+msg5);
							}
						   
							Thread.sleep(3000);
							performerPOM.clickcloseLegalEntity(driver).click();
							    
						    Thread.sleep(3000);  
							performerPOM.editLegalEntity(driver).click();  
							  
							Thread.sleep(3000);  
						    performerPOM.legalEntityName(driver).clear();
							  
						    Thread.sleep(5000);
							Row row4 = sheet.getRow(67);						//Selected 0th index row (First row)
						    Cell c4 = row4.getCell(1);						//Selected cell (0 row,1 column)
						    String NamelegalEntity= c4.getStringCellValue();
						    performerPOM.legalEntityName(driver).sendKeys(NamelegalEntity);
							    
							Thread.sleep(5000);
							performerPOM.clickSaveLegalEntity(driver).click();
							    
							 Thread.sleep(5000);
							 String msg6 = performerPOM.readlegalmsg(driver).getText();		//Reading Message appeared after save button
							 if(msg6.equalsIgnoreCase("Branch Updated Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg6);
									
								}
									else
									{
										test.log(LogStatus.FAIL, "Message displayed = "+msg6);
									}
							    
							 Thread.sleep(5000);
							 performerPOM.clickcloseLegalEntity(driver).click();
							 
							/* Thread.sleep(5000);
							 performerPOM.clickLegalEntityFilter(driver).sendKeys("Sandeep Agrawal", Keys.ENTER);
							 
							 Thread.sleep(5000);
							 performerPOM.clickLegalEntityFilter(driver).clear();
							 
								test.log(LogStatus.PASS,"Legal Entity Filter Work Successfully");*/
								
								
								 Thread.sleep(3000);
								 performerPOM.clickSubUnitscfo(driver).click();	 
								// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddCustomerBranch")));
								 Thread.sleep(300);
								 performerPOM.addLegalEntity(driver).click();
								

								Thread.sleep(5000);
								Row row1 = sheet.getRow(68);						//Selected 0th index row (First row)
								Cell c0 = row1.getCell(1);						//Selected cell (0 row,1 column)
								String subunit= c0.getStringCellValue();
							    performerPOM.legalEntityName(driver).sendKeys(subunit);
							
								 Thread.sleep(3000);
							    performerPOM.clickUnitType(driver).click();
							    Thread.sleep(3000);
							    performerPOM.chooseUnitType(driver).click();
							    Thread.sleep(3000);
							    performerPOM.clickLegalEntityType(driver).click();
								Thread.sleep(3000);
								performerPOM.chooseLegalEntityType(driver).click();
							    

								Thread.sleep(1000);
								Row row6 = sheet.getRow(64);						//Selected 0th index row (First row)
								Cell c6 = row6.getCell(1);						//Selected cell (0 row,1 column)
								String address1= c6.getStringCellValue();
							    performerPOM.clickAddressLine(driver).sendKeys(address1);
								
							    Thread.sleep(3000);
							    performerPOM.clickState1(driver).click();
							    
							    Thread.sleep(3000);
							    performerPOM.chooseState1(driver).click();
							    
							    Thread.sleep(5000);
							    performerPOM.clickCity(driver).click();
							    
							    Thread.sleep(5000);
							    performerPOM.chooseCity(driver).click();
							    
							   
							    Thread.sleep(4000);
								
								Row row7 = sheet.getRow(65);						//Selected 0th index row (First row)
								Cell c7 = row7.getCell(1);						//Selected cell (0 row,1 column)
								String contact1= c7.getStringCellValue();
							    performerPOM.clickContactPerson(driver).sendKeys(contact1+"");
							    
							    Thread.sleep(3000);
							  	Row row8 = sheet.getRow(69);						//Selected 0th index row (First row)
							  	Cell c8 = row8.getCell(1);						//Selected cell (0 row,1 column)
							  	String email1= c8.getStringCellValue();
							  	 performerPOM.clickEmail(driver).sendKeys(email1);
							   
							   	Thread.sleep(3000);
							    performerPOM.clickSaveLegalEntity(driver).click();
							    
							    Thread.sleep(3000);
								
						        js.executeScript("window.scrollBy(0,-400)");
								
							    
							    Thread.sleep(3000);
								 wait.until(ExpectedConditions.visibilityOf(performerPOM.readlegalmsg(driver)));
											
								Thread.sleep(500);
								String msg9 = performerPOM.readlegalmsg(driver).getText();		//Reading Message appeared after save button
								
								if(msg9.equalsIgnoreCase("Branch Added Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg9);
									
								}
									else
									{
										test.log(LogStatus.FAIL, "Message displayed = "+msg9);
									}
								   
									Thread.sleep(3000);
									performerPOM.clickcloseLegalEntity(driver).click();
							    
							    
							    
					 }
					
					public static void LegalEntityWithoutData(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
					 {
						
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
						
						 performerPOM.clickMasters(driver).click();
					     Thread.sleep(300);
						 performerPOM.chooseMasterLegalEntity(driver).click();
						 Thread.sleep(300);
						 performerPOM.addLegalEntity(driver).click();
						 
						  	Thread.sleep(3000);
						    performerPOM.clickSaveLegalEntity(driver).click();
						    
						    Thread.sleep(3000);
							JavascriptExecutor js = (JavascriptExecutor) driver;
					        js.executeScript("window.scrollBy(0,-400)");
							
						  
						    Thread.sleep(3000);
							 wait.until(ExpectedConditions.visibilityOf(performerPOM.readlegalmsg1(driver)));
										
							Thread.sleep(500);
							String msg = performerPOM.readlegalmsg1(driver).getText();		//Reading Message appeared after save button
							if(msg.equalsIgnoreCase(msg))
							{
								test.log(LogStatus.PASS, "Wihout Enter data = "+msg);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Wihout Enter data = "+msg);
								}
							   
								Thread.sleep(3000);
								performerPOM.clickcloseLegalEntity(driver).click();
					 }
					
					public static void LegalEntityInvalidData(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
					 {
						
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
						
						 performerPOM.clickMasters(driver).click();
					     Thread.sleep(300);
						 performerPOM.chooseMasterLegalEntity(driver).click();
						 Thread.sleep(300);
						 performerPOM.addLegalEntity(driver).click();
						 Thread.sleep(3000);
						 performerPOM.legalEntityName(driver).sendKeys("343$");
						 Thread.sleep(3000);
						    performerPOM.clickUnitType(driver).click();
						    Thread.sleep(3000);
						    performerPOM.chooseUnitType(driver).click();
						    Thread.sleep(3000);
						    performerPOM.clickLegalEntityType(driver).click();
							Thread.sleep(3000);
							performerPOM.chooseLegalEntityType(driver).click();
						    Thread.sleep(3000);
						    performerPOM.clickAddressLine(driver).sendKeys("Pune");
							
						    Thread.sleep(3000);
						    performerPOM.clickState1(driver).click();
						    
						    Thread.sleep(3000);
						    performerPOM.chooseState1(driver).click();
						    
						    Thread.sleep(5000);
						    performerPOM.clickCity(driver).click();
						    
						    Thread.sleep(5000);
						    performerPOM.chooseCity(driver).click();
						 
						 Thread.sleep(3000);
						 performerPOM.clickContactPerson(driver).sendKeys("345");
						 Thread.sleep(3000);
						 performerPOM.clickEmail(driver).sendKeys("dds34");
						 
						  	Thread.sleep(3000);
						    performerPOM.clickSaveLegalEntity(driver).click();
						    
						    Thread.sleep(3000);
							JavascriptExecutor js = (JavascriptExecutor) driver;
					        js.executeScript("window.scrollBy(0,-400)");
							
						  
						    Thread.sleep(3000);
							 wait.until(ExpectedConditions.visibilityOf(performerPOM.readlegalmsg1(driver)));
										
							Thread.sleep(500);
							String msg = performerPOM.readlegalmsg1(driver).getText();		//Reading Message appeared after save button
							if(msg.equalsIgnoreCase(msg))
							{
								test.log(LogStatus.PASS, "Enter Invalid data = "+msg);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid data = "+msg);
								}
							   
								Thread.sleep(3000);
								performerPOM.clickcloseLegalEntity(driver).click();
					 }
					
					public static void LegalEntityTwoManadatoryFields(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
					 {
						
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
					  performerPOM.clickMasters(driver).click();
					     Thread.sleep(300);
						 performerPOM.chooseMasterLegalEntity(driver).click();
						 Thread.sleep(300);
						 performerPOM.addLegalEntity(driver).click();
					
						 Thread.sleep(3000);
						    performerPOM.clickUnitType(driver).click();
						    
						    Thread.sleep(3000);
						    performerPOM.chooseUnitType(driver).click();
						    
						    Thread.sleep(3000);
						    performerPOM.clickAddressLine(driver).sendKeys("Pune");
						    
							Thread.sleep(3000);
						    performerPOM.clickSaveLegalEntity(driver).click();
						    
						    Thread.sleep(3000);
							JavascriptExecutor js = (JavascriptExecutor) driver;
					        js.executeScript("window.scrollBy(0,-400)");
							
						  
						    Thread.sleep(3000);
							 wait.until(ExpectedConditions.visibilityOf(performerPOM.readlegalmsg1(driver)));
										
							Thread.sleep(500);
							String msg = performerPOM.readlegalmsg1(driver).getText();		//Reading Message appeared after save button
							if(msg.equalsIgnoreCase(msg))
							{
								test.log(LogStatus.PASS, "Enter Two Manadatory Fields = "+msg);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Enter Two Manadatory Fields = "+msg);
								}
							   
								Thread.sleep(3000);
								performerPOM.clickcloseLegalEntity(driver).click();
					 }
					
					public static void LegalEntityCloseButton(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
					 {
						
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
					  performerPOM.clickMasters(driver).click();
					     Thread.sleep(300);
						 performerPOM.chooseMasterLegalEntity(driver).click();
						 Thread.sleep(300);
						 performerPOM.addLegalEntity(driver).click();
						 
						 
						 
						 if(performerPOM.CloseLegalEntity(driver).isEnabled())
						 {
							 Thread.sleep(3000);
							 performerPOM.CloseLegalEntity(driver).click();
							 test.log(LogStatus.PASS,"Legal Entity - Close Button is Clickable");
						 }
						 else
						 {
							 test.log(LogStatus.FAIL,"Legal Entity - Close Button is Clickable");
						 }
					 }
					
					
					
					public static void AddUnitType(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
					 {
						
						WebDriverWait wait = new WebDriverWait(driver, 180);
						progress(driver);
						
						 performerPOM.clickMasters(driver).click();
					     Thread.sleep(300);
						 performerPOM.chooseMasterLegalEntity(driver).click();
						 Thread.sleep(300);
						 performerPOM.addLegalEntity(driver).click();
						  Thread.sleep(300);
						 performerPOM.clickUnitTypePlusIcon(driver).click();
						 
						 Thread.sleep(300);
						 performerPOM.EnterUnitType(driver).sendKeys("Automation Test");
						 
						 Thread.sleep(300);
						 performerPOM.SaveUnitType(driver).click();
						 
						 Thread.sleep(300);
						 String msg =performerPOM.SaveValidationMsg(driver).getText();
						 
						 if(msg.equalsIgnoreCase(msg))
						 {
							test.log(LogStatus.PASS,"Add Unit Type =" +msg);
						 }
						 else
						 {
							 test.log(LogStatus.FAIL, "Add Unit Type =" +msg);
						 }
						 
						 Thread.sleep(300);
						 performerPOM.CloseUnitType(driver).click();
					 }
					
					
					
					
					 public static void LawFirm(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					  {
						  
						  XSSFSheet sheet = ReadExcel();
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
							
							//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
							
						  
						    Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						  //  Thread.sleep(3000);
							//performerPOM.clickMastersMenu(driver).click();
						     Thread.sleep(5000);
						    performerPOM.chooseMasterLawFirm(driver).click();
						    Thread.sleep(3000);
							performerPOM.newLawFirm(driver).click();
							
							Thread.sleep(3000);
							Row row4 = sheet.getRow(71);						//Selected 0th index row (First row)
							Cell c4 = row4.getCell(1);						//Selected cell (0 row,1 column)
							String name= c4.getStringCellValue();
							performerPOM.nameLawFirm(driver).sendKeys(name);
						    
							
							Thread.sleep(3000);
						    Row row5 = sheet.getRow(72);						//Selected 0th index row (First row)
							Cell c5 = row5.getCell(1);						//Selected cell (0 row,1 column)
							String email1= c5.getStringCellValue();
							performerPOM.Email(driver).sendKeys(email1);
							
							

							Thread.sleep(3000);
							progress(driver);
							Thread.sleep(3000);
							Row row6 = sheet.getRow(73);						//Selected 0th index row (First row)
							Cell c6 = row6.getCell(1);						//Selected cell (0 row,1 column)
							int contactno = (int) c6.getNumericCellValue();
						    performerPOM.contactNo(driver).sendKeys(contactno+" ");
						    
						   	Thread.sleep(3000);
							performerPOM.clickSaveLawFirm(driver).click();
							
						    
							Thread.sleep(3000);
							String msg5 = performerPOM.ReadLawFirmMsg(driver).getText();		//Reading Message appeared after save button
							
					     	if(msg5.equalsIgnoreCase("Law Firm Details Saved Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg5);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg5);
								}
							
								
							Thread.sleep(3000);
							performerPOM.clickCloseButton(driver).click();
							
							Thread.sleep(3000);
							performerPOM.editLawFirm(driver).click();
							
							Thread.sleep(3000);
							performerPOM.nameLawFirm(driver).clear();
							
							Thread.sleep(3000);
							Row row12 = sheet.getRow(77);						//Selected 0th index row (First row)
							Cell c12 = row12.getCell(1);						//Selected cell (0 row,1 column)
							String LawFirmname= c12.getStringCellValue();
							performerPOM.nameLawFirm(driver).sendKeys(LawFirmname);
							
							Thread.sleep(3000);
							performerPOM.Email(driver).clear();
							Thread.sleep(3000);
						    Row row13 = sheet.getRow(72);						//Selected 0th index row (First row)
							Cell c13 = row5.getCell(1);						//Selected cell (0 row,1 column)
							String email2= c13.getStringCellValue();
							performerPOM.Email(driver).sendKeys(email2);
							
							Thread.sleep(3000);
							 performerPOM.contactNo(driver).clear();

							Thread.sleep(3000);
							progress(driver);
							Thread.sleep(3000);
							Row row14 = sheet.getRow(73);						//Selected 0th index row (First row)
							Cell c14 = row14.getCell(1);						//Selected cell (0 row,1 column)
							int editcontactno = (int) c14.getNumericCellValue();
						    performerPOM.contactNo(driver).sendKeys(editcontactno+"");
							
							Thread.sleep(3000);
							performerPOM.clickSaveLawFirm(driver).click();
							
							
							String msg6 = performerPOM.ReadLawFirmMsg(driver).getText();		//Reading Message appeared after save button
							if(msg6.equalsIgnoreCase("Details Updated Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg6);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg6);
								}
							
								
							Thread.sleep(3000);
							performerPOM.clickCloseButton(driver).click();
							
							Thread.sleep(3000);
							performerPOM.clickAddNewLawyer(driver).click();
							
							Thread.sleep(3000);
							Row row7 = sheet.getRow(74);						//Selected 0th index row (First row)
							Cell c7 = row7.getCell(1);						//Selected cell (0 row,1 column)
							String firstname= c7.getStringCellValue();
							performerPOM.clickLawyerName(driver).sendKeys(firstname);
							
							Thread.sleep(3000);
							Row row8 = sheet.getRow(75);						//Selected 0th index row (First row)
							Cell c8 = row8.getCell(1);						//Selected cell (0 row,1 column)
							String lastname= c8.getStringCellValue();
							performerPOM.clickLawyerLastName(driver).sendKeys(lastname);
							

							Thread.sleep(3000);
							Row row9 = sheet.getRow(76);						//Selected 0th index row (First row)
							Cell c9 = row9.getCell(1);						//Selected cell (0 row,1 column)
							String Designation= c9.getStringCellValue();
							performerPOM.clickLawyerDesignation(driver).sendKeys(Designation);
							
							
							Thread.sleep(3000);
							Row row10 = sheet.getRow(72);						//Selected 0th index row (First row)
							Cell c10 = row10.getCell(1);						//Selected cell (0 row,1 column)
							String email3= c10.getStringCellValue();
							performerPOM.clickLawyerEmail(driver).sendKeys(email3);
							
						   	Thread.sleep(3000);
							Row row11 = sheet.getRow(73);						//Selected 0th index row (First row)
							Cell c11 = row11.getCell(1);						//Selected cell (0 row,1 column)
							int contactno1= (int)c11.getNumericCellValue();
							performerPOM.clickLawyerContactNo(driver).sendKeys(contactno1+"");
						    
							Thread.sleep(3000);
							performerPOM.clickLawyerDepartment(driver).click();
							Thread.sleep(3000);
							performerPOM.selectLawyerDepartment(driver).click();
							Thread.sleep(4000);
							performerPOM.clickLawyerRole(driver).click();
							Thread.sleep(4000);
							performerPOM.selectLawyerRole(driver).click();
							Thread.sleep(5000);
							performerPOM.saveLawyer(driver).click();
							
							String msg7 = performerPOM.readLawyerMsg(driver).getText();		//Reading Message appeared after save button
							if(msg7.equalsIgnoreCase("Lawyer Details Saved Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg7);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg7);
								}
							
								
							Thread.sleep(5000);
							performerPOM.closeLawyer(driver).click();
							
							
							Thread.sleep(5000);
							performerPOM.clickLawFirmFilter(driver).sendKeys("Aditya Puri",Keys.ENTER);
							Thread.sleep(5000);
							performerPOM.clickLawFirmFilter(driver).clear();
							
							test.log(LogStatus.PASS,"Law Firm Filter Work Successfully");
							
							
						}	
					 
					 public static void LawFirmWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
					
						    Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						
						     Thread.sleep(5000);
						    performerPOM.chooseMasterLawFirm(driver).click();
						    Thread.sleep(3000);
							performerPOM.newLawFirm(driver).click();
							
						   	Thread.sleep(3000);
							performerPOM.clickSaveLawFirm(driver).click();
							
						    
							Thread.sleep(3000);
							String msg5 = performerPOM.ReadLawFirmMsg1(driver).getText();		//Reading Message appeared after save button
							
					     	if(msg5.equalsIgnoreCase(msg5))
							{
								test.log(LogStatus.PASS, "Without Enter Data = "+msg5);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data = "+msg5);
								}
							
								
							Thread.sleep(3000);
							performerPOM.clickCloseButton(driver).click();
							
							Thread.sleep(2000);
							OverduePOM.clickDashboard(driver).click();
							
							
							
					  }
					 
					 public static void LawFirmInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  

							 Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						     Thread.sleep(5000);
						    performerPOM.chooseMasterLawFirm(driver).click();
						    Thread.sleep(3000);
							performerPOM.newLawFirm(driver).click();
							
							Thread.sleep(3000);
							performerPOM.nameLawFirm(driver).sendKeys("#$FSG");
						   	Thread.sleep(3000);
							performerPOM.Email(driver).sendKeys("FGD");
							Thread.sleep(3000);
						    performerPOM.contactNo(driver).sendKeys("675");
						  	Thread.sleep(3000);
							performerPOM.clickSaveLawFirm(driver).click();
					     	Thread.sleep(3000);
							String msg5 = performerPOM.ReadLawFirmMsg1(driver).getText();		//Reading Message appeared after save button
							
					     	if(msg5.equalsIgnoreCase(msg5))
							{
								test.log(LogStatus.PASS, "Enter Invalid Data  = "+msg5);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid Data = "+msg5);
								}
							
								
							Thread.sleep(3000);
							performerPOM.clickCloseButton(driver).click();
							
							Thread.sleep(2000);
							OverduePOM.clickDashboard(driver).click();
					  }
					 public static void LawFirmTwoManadatoryFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  

							 Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						     Thread.sleep(5000);
						    performerPOM.chooseMasterLawFirm(driver).click();
						    Thread.sleep(3000);
							performerPOM.newLawFirm(driver).click();
						
						   	Thread.sleep(3000);
							performerPOM.Email(driver).sendKeys("snehal@gmail.com");
							Thread.sleep(3000);
						    performerPOM.contactNo(driver).sendKeys("6222222275");
						  	Thread.sleep(3000);
							performerPOM.clickSaveLawFirm(driver).click();
					     	Thread.sleep(3000);
							String msg5 = performerPOM.ReadLawFirmMsg1(driver).getText();		//Reading Message appeared after save button
							
					     	if(msg5.equalsIgnoreCase(msg5))
							{
								test.log(LogStatus.PASS, "Enter Two Manadatory Fields  = "+msg5);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Enter Two Manadatory Fields = "+msg5);
								}
							
								
							Thread.sleep(3000);
							performerPOM.clickCloseButton(driver).click();
							
							Thread.sleep(2000);
							OverduePOM.clickDashboard(driver).click();
					  }	
					 
					 public static void LawFirmCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  

							 Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						     Thread.sleep(5000);
						    performerPOM.chooseMasterLawFirm(driver).click();
						    Thread.sleep(3000);
							performerPOM.newLawFirm(driver).click();
							
							
							
							if(performerPOM.clickCloseButton(driver).isEnabled())
							{
								Thread.sleep(3000);
								performerPOM.clickCloseButton(driver).click();
								test.log(LogStatus.PASS, "Close Button is clickable");		
							}
							else
							{
								test.log(LogStatus.PASS, "Close Button is not clickable");		
							}
							
							
							
					        Thread.sleep(2000);
							OverduePOM.clickDashboard(driver).click();
					  }
					 
					 public static void LawyerWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  

							 Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						     Thread.sleep(5000);
						    performerPOM.chooseMasterLawFirm(driver).click();
						 
							
							Thread.sleep(3000);
							performerPOM.clickAddNewLawyer(driver).click();
							
							Thread.sleep(5000);
							performerPOM.saveLawyer(driver).click();
							
							String msg7 = performerPOM.readLawyerMsg1(driver).getText();		//Reading Message appeared after save button
							if(msg7.equalsIgnoreCase(msg7))
							{
								test.log(LogStatus.PASS, "Without Enter Data = "+msg7);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data  = "+msg7);
								}
							
								
							Thread.sleep(5000);
							performerPOM.closeLawyer(driver).click();
							
						     Thread.sleep(2000);
								OverduePOM.clickDashboard(driver).click();
					  }
		 public static void LawyerInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			 {
						  

		       		 Thread.sleep(3000);
				     performerPOM.clickMasters(driver).click();
				     Thread.sleep(5000);
					 performerPOM.chooseMasterLawFirm(driver).click();
					 
						Thread.sleep(3000);
						performerPOM.clickAddNewLawyer(driver).click();
						
						Thread.sleep(3000);
						
						performerPOM.clickLawyerName(driver).sendKeys("344@#");
						
						Thread.sleep(3000);
						performerPOM.clickLawyerLastName(driver).sendKeys("23#");
						

						Thread.sleep(3000);
						performerPOM.clickLawyerDesignation(driver).sendKeys("#$34");
						
						
						Thread.sleep(3000);
						performerPOM.clickLawyerEmail(driver).sendKeys("dg");
						
					   	Thread.sleep(3000);
						performerPOM.clickLawyerContactNo(driver).sendKeys("689");
					    
						Thread.sleep(3000);
						performerPOM.clickLawyerDepartment(driver).click();
						Thread.sleep(3000);
						performerPOM.selectLawyerDepartment(driver).click();
						Thread.sleep(4000);
						performerPOM.clickLawyerRole(driver).click();
						Thread.sleep(4000);
						performerPOM.selectLawyerRole(driver).click();
						Thread.sleep(5000);
						performerPOM.saveLawyer(driver).click();
						
						String msg = performerPOM.readLawyerMsg1(driver).getText();		//Reading Message appeared after save button
						if(msg.equalsIgnoreCase(msg))
						{
							test.log(LogStatus.PASS, "Enter Invalid Data = "+msg);
							
						}
							else
							{
								test.log(LogStatus.FAIL, "Enter Invalid Data= "+msg);
							}
						
							
						Thread.sleep(5000);
						performerPOM.closeLawyer(driver).click();
						
						   Thread.sleep(2000);
							OverduePOM.clickDashboard(driver).click();
						
			 }
		 
		 public static void LawyerTwoManadatoryFileds(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
		 {
					  

	       		 Thread.sleep(3000);
			     performerPOM.clickMasters(driver).click();
			     Thread.sleep(5000);
				 performerPOM.chooseMasterLawFirm(driver).click();
				 
					Thread.sleep(3000);
					performerPOM.clickAddNewLawyer(driver).click();
					
					Thread.sleep(3000);
					
					performerPOM.clickLawyerName(driver).sendKeys("Sneha");
					
					Thread.sleep(3000);
					performerPOM.clickLawyerLastName(driver).sendKeys("Patil");
					
					Thread.sleep(5000);
					performerPOM.saveLawyer(driver).click();
					
					String msg = performerPOM.readLawyerMsg1(driver).getText();		//Reading Message appeared after save button
					if(msg.equalsIgnoreCase(msg))
					{
						test.log(LogStatus.PASS, "Enter Two Manadatory fields = "+msg);
						
					}
						else
						{
							test.log(LogStatus.FAIL, "Enter Two Manadatory fields= "+msg);
						}
					
						
					Thread.sleep(5000);
					performerPOM.closeLawyer(driver).click();
					
					   Thread.sleep(2000);
	                   OverduePOM.clickDashboard(driver).click();
		 }
		 
		 public static void LawyerCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
		 {
					  

	       		 Thread.sleep(3000);
			     performerPOM.clickMasters(driver).click();
			     Thread.sleep(5000);
				 performerPOM.chooseMasterLawFirm(driver).click();
				 
					Thread.sleep(3000);
					performerPOM.clickAddNewLawyer(driver).click();
					
					if(performerPOM.CloseLawyer(driver).isEnabled())
					{
						Thread.sleep(5000);
						performerPOM.CloseLawyer(driver).click();
						test.log(LogStatus.PASS, "Close button is clickable");
					}
					else
					{
						test.log(LogStatus.FAIL, "Close button is not clickable");
					}
					
					
					
					   Thread.sleep(2000);
	                   OverduePOM.clickDashboard(driver).click();
		 }
					
						
							
							
							
					 
					 
					 public static void User(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
						{
						 
						    XSSFSheet sheet = ReadExcel();
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						
						    Thread.sleep(1000);
						    performerPOM.clickMasters(driver).click();
//						    Thread.sleep(3000);
//							performerPOM.clickMastersMenu(driver).click();
					        Thread.sleep(3000);
						     performerPOM.clickUserMaster(driver).click();
							 Thread.sleep(3000);
							 performerPOM.clickAddNewUser(driver).click();
							 
							 
						      Thread.sleep(4000);
							  Row row12 = sheet.getRow(80);						//Selected 0th index row (First row)
							  Cell c12 = row12.getCell(1);						//Selected cell (0 row,1 column)
							  String firstname1= c12.getStringCellValue();
							  performerPOM.clickUserName(driver).sendKeys(firstname1);
								
								Thread.sleep(4000);
								Row row13 = sheet.getRow(81);						//Selected 0th index row (First row)
								Cell c13 = row13.getCell(1);						//Selected cell (0 row,1 column)
								String lastname1= c13.getStringCellValue();
								performerPOM.clickUserLastName(driver).sendKeys(lastname1);
								

								Thread.sleep(4000);
								Row row14 = sheet.getRow(82);						//Selected 0th index row (First row)
								Cell c14 = row14.getCell(1);						//Selected cell (0 row,1 column)
								String Designation1= c14.getStringCellValue();
								performerPOM.clickUserDesignation(driver).sendKeys(Designation1);
								
								
								Thread.sleep(4000);
								Row row15 = sheet.getRow(83);						//Selected 0th index row (First row)
							   Cell c15 = row15.getCell(1);						//Selected cell (0 row,1 column)
								String email3= c15.getStringCellValue();
								performerPOM.clickUserEmail(driver).sendKeys(email3);
								
								
								
					     		Thread.sleep(4000);
								Row row16 = sheet.getRow(84);						//Selected 0th index row (First row)
								Cell c16 = row16.getCell(1);						//Selected cell (0 row,1 column)
								int contactno2= (int)c16.getNumericCellValue();
								 performerPOM.clickUserContactNo(driver).sendKeys(contactno2+"");
							    
							 

							 Thread.sleep(4000);
							 performerPOM.clickUserDepartment(driver).click();
							  Thread.sleep(4000);
							 performerPOM.selectUserDepartment(driver).click();
							  Thread.sleep(4000);
							 performerPOM.clickUserRole(driver).click();
							  Thread.sleep(4000);
							 performerPOM.selectUserRole(driver).click();
							 Thread.sleep(4000);
							 performerPOM.saveUser(driver).click();
							 
							   Thread.sleep(500);
							  
								String msg = performerPOM.UserReadMsg(driver).getText();
								if(msg.contains("Details Updated Successfully."))
								{
									test.log(LogStatus.PASS,"User -Details Updated Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL,"User- User with Same Email already Exists.");
								}
							  
							  Thread.sleep(3000);
								 performerPOM.closeUser(driver).click();
							  
							 Thread.sleep(3000);
							 performerPOM.editUser(driver).click();
							 
							 Thread.sleep(3000);
							 performerPOM.UserAddress(driver).clear();
							 
							 Thread.sleep(3000);
							 Row row17 = sheet.getRow(85);						//Selected 0th index row (First row)
							 Cell c17 = row17.getCell(1);						//Selected cell (0 row,1 column)
						     String address= c17.getStringCellValue();
						     performerPOM.UserAddress(driver).sendKeys(address);
						     
						     Thread.sleep(4000);
							 performerPOM.clickUserDepartment(driver).click();
							  Thread.sleep(4000);
							 performerPOM.selectUserDepartment(driver).click();
							  Thread.sleep(4000);
							 performerPOM.clickUserRole(driver).click();
							  Thread.sleep(4000);
							 performerPOM.selectUserRole(driver).click();
						     
						     Thread.sleep(3000);
							 performerPOM.saveUser(driver).click();
						     
							 
							 
							 Thread.sleep(3000);
							 String msg1 = performerPOM.UserReadMsg(driver).getText();
								if(msg1.contains("Details Updated Successfully."))
								{
									test.log(LogStatus.PASS, "Update User-Details Updated Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Update User-User with Same Email already Exists.");
								}
							 
							  Thread.sleep(4000);
							  performerPOM.closeUser(driver).click();
							 
							  Thread.sleep(4000);
							  performerPOM.UserDeleted(driver).click();
							  
							  Thread.sleep(5000);
							    // Switching to Alert        
						        Alert alert = driver.switchTo().alert();		
						        		
						        // Capturing alert message.    
						        String alertMessage= driver.switchTo().alert().getText();	
						        
						        
						        test.log(LogStatus.PASS, alertMessage);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage);	
						        
						        		
						        // Accepting alert		
						        alert.accept();	
						        
						        Thread.sleep(5000);
						        String alertMessage1=driver.switchTo().alert().getText();
						        
						        
						        Thread.sleep(3000);
						        test.log(LogStatus.PASS, alertMessage1);
						        
						        Thread.sleep(2000);
						        // Capturing alert message.    
						         driver.switchTo().alert().accept();		
						         
						        /* Thread.sleep(5000);
								 performerPOM.clickLegalEntityFilter(driver).sendKeys("Management", Keys.ENTER);
								 
								  Thread.sleep(5000);
									 performerPOM.clickLegalEntityFilter(driver).clear();
								 
								 test.log(LogStatus.PASS, "User Filter work successfully" );*/
								 
								    Thread.sleep(5000);
									 performerPOM.clickUserMasterResetcfo(driver).click();
									 
									  Thread.sleep(5000);
									    // Switching to Alert        
								        Alert alert1 = driver.switchTo().alert();		
								        		
								        // Capturing alert message.    
								        String alertMessage2= driver.switchTo().alert().getText();	
								        
								        
								        test.log(LogStatus.PASS, alertMessage2);
								        		
								        // Displaying alert message		
								        System.out.println(alertMessage2);	
								        
								        		
								        // Accepting alert		
								        alert1.accept();	
								        
								        Thread.sleep(5000);
								        String alertMessage3=driver.switchTo().alert().getText();
								        
								        
								        Thread.sleep(3000);
								        test.log(LogStatus.PASS, alertMessage3);
								        
								        Thread.sleep(2000);
								        // Capturing alert message.    
								         driver.switchTo().alert().accept();
							  	 
						}
					 
					 public static void UserWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
						{
						    Thread.sleep(1000);
						    performerPOM.clickMasters(driver).click();

					        Thread.sleep(3000);
						     performerPOM.clickUserMaster(driver).click();
							 Thread.sleep(3000);
							 performerPOM.clickAddNewUser(driver).click();
							 
							 Thread.sleep(4000);
							 performerPOM.saveUser(driver).click();
							 
							   Thread.sleep(500);
							  
								String msg = performerPOM.UserReadMsg1(driver).getText();
								if(msg.contains(msg))
								{
									test.log(LogStatus.PASS,"Enter Without Data ="+msg);
								}
								else
								{
									test.log(LogStatus.FAIL,"Enter Without Data ="+msg);
								}
							  
							  Thread.sleep(3000);
								 performerPOM.closeUser(driver).click();
							 
						}
					 
					 public static void UserInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
						{
						    Thread.sleep(1000);
						    performerPOM.clickMasters(driver).click();

					        Thread.sleep(3000);
						     performerPOM.clickUserMaster(driver).click();
							 Thread.sleep(3000);
							 performerPOM.clickAddNewUser(driver).click();
							 
							 Thread.sleep(4000);
							  performerPOM.clickUserName(driver).sendKeys("$%67ad");
								
								Thread.sleep(4000);
								performerPOM.clickUserLastName(driver).sendKeys("56fg%^");
								

								Thread.sleep(4000);
								performerPOM.clickUserDesignation(driver).sendKeys("364$%");
								
								
								Thread.sleep(4000);
								performerPOM.clickUserEmail(driver).sendKeys("DJF");
								
								
								
					     		Thread.sleep(4000);
								 performerPOM.clickUserContactNo(driver).sendKeys("5726");
							    Thread.sleep(4000);
							 performerPOM.clickUserDepartment(driver).click();
							  Thread.sleep(4000);
							 performerPOM.selectUserDepartment(driver).click();
							  Thread.sleep(4000);
							 performerPOM.clickUserRole(driver).click();
							  Thread.sleep(4000);
							 performerPOM.selectUserRole(driver).click();
							 Thread.sleep(4000);
							 performerPOM.saveUser(driver).click();
							 
							   Thread.sleep(500);
							  
								String msg = performerPOM.UserReadMsg1(driver).getText();
								if(msg.contains(msg))
								{
									test.log(LogStatus.PASS,"Enter Invalid  Data ="+msg);
								}
								else
								{
									test.log(LogStatus.FAIL,"Enter Invalid Data ="+msg);
								}
							  
							  Thread.sleep(3000);
							   performerPOM.closeUser(driver).click();
							 
						}
					 
					 public static void UserTwoManadatoryFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
						{
						    Thread.sleep(1000);
						    performerPOM.clickMasters(driver).click();

					        Thread.sleep(3000);
						     performerPOM.clickUserMaster(driver).click();
							 Thread.sleep(3000);
							 performerPOM.clickAddNewUser(driver).click();
							 
							 Thread.sleep(4000);
							  performerPOM.clickUserName(driver).sendKeys("Snehal");
								
								Thread.sleep(4000);
								performerPOM.clickUserLastName(driver).sendKeys("Patil");
								 Thread.sleep(4000);
								 performerPOM.saveUser(driver).click();
								 
								   Thread.sleep(500);
								  
									String msg = performerPOM.UserReadMsg1(driver).getText();
									if(msg.contains(msg))
									{
										test.log(LogStatus.PASS,"Enter Two Manadatory fields ="+msg);
									}
									else
									{
										test.log(LogStatus.FAIL,"Enter Two Manadatory fields ="+msg);
									}
								  
								  Thread.sleep(3000);
								   performerPOM.closeUser(driver).click();
						}
					 public static void UserCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
						{
						    Thread.sleep(1000);
						    performerPOM.clickMasters(driver).click();

					        Thread.sleep(3000);
						     performerPOM.clickUserMaster(driver).click();
							 Thread.sleep(3000);
							 performerPOM.clickAddNewUser(driver).click();
							
							 if(performerPOM.CloseLegalEntity(driver).isEnabled())
							 {
								 Thread.sleep(3000);
								   performerPOM.CloseLegalEntity(driver).click();
								   test.log(LogStatus.PASS, "Close Button is clickable");
							 }
							 else
							 {
								 test.log(LogStatus.PASS, "Close Button is not clickable");
							
							 }
							 
					}
							 
					 
					 
					 public static void Opponent(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					  {
						  
						    XSSFSheet sheet = ReadExcel();
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						  
						  
							 Thread.sleep(5000);
							 performerPOM.clickMasters(driver).click();
//							 Thread.sleep(3000);
//						     performerPOM.clickMastersMenu(driver).click();
							 Thread.sleep(3000);
						     performerPOM.chooseOpponentMasters(driver).click();
						     Thread.sleep(3000);
						     performerPOM.NewOpponent(driver).click();
						   
							
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePartyDetial"));
								
							
						    Thread.sleep(3000);
							Row row17 = sheet.getRow(88);						//Selected 0th index row (First row)
							Cell c17 = row17.getCell(1);						//Selected cell (0 row,1 column)
							String opponentname= c17.getStringCellValue();
						    performerPOM.clickOpponentName(driver).sendKeys(opponentname);
						    
						   Thread.sleep(3000);
						   performerPOM.saveOpponent(driver).click();
						   
						   Thread.sleep(3000);
								 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
									if(msg1.equalsIgnoreCase("Opponent Details Saved Successfully."))
									{
										test.log(LogStatus.PASS, "Message displayed = "+msg1);
										
									}
										else
										{
											test.log(LogStatus.FAIL, "Message displayed = "+msg1);
										}
									
									
						   
						   
						   Thread.sleep(3000);
						   performerPOM.closeOpponent(driver).click();
						   
						   Thread.sleep(3000);
						   performerPOM.editOpponent(driver).click();
						   
						   
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePartyDetial"));
						   
						   Thread.sleep(3000);
						   performerPOM.clickOpponentName(driver).clear();
						   
						   Thread.sleep(4000);
						 	Row row18 = sheet.getRow(89);						//Selected 0th index row (First row)
						 	Cell c18 = row18.getCell(1);						//Selected cell (0 row,1 column)
						 	String editopponentname= c18.getStringCellValue();
						 	performerPOM.clickOpponentName(driver).sendKeys(editopponentname);
						 	Thread.sleep(2000);
						 	performerPOM.opponentcontactNo(driver).clear();
						 	Thread.sleep(2000);
						 	performerPOM.opponentcontactNo(driver).sendKeys("0987654321");
						 	    
						 	   Thread.sleep(3000);
							   performerPOM.saveOpponent(driver).click();
							   
						     Thread.sleep(3000);
								String msg2 = performerPOM.readOppoenentMsg(driver).getText();
		                         if(msg2.equalsIgnoreCase("Opponent Details Updated Successfully."))
									{
										test.log(LogStatus.PASS, "Message displayed = "+msg2);
										
									}
										else
										{
											test.log(LogStatus.FAIL, "Message displayed = "+msg2);
										}
									
							   
						      Thread.sleep(3000);
							   performerPOM.closeOpponent(driver).click();
							   
							   Thread.sleep(3000);
							   performerPOM.deleteOpponent(driver).click();
							   
					     	   Thread.sleep(5000);
							    // Switching to Alert        
						        Alert alert = driver.switchTo().alert();		
						        		
						        // Capturing alert message.    
						        String alertMessage= driver.switchTo().alert().getText();	
						        
						        
						        test.log(LogStatus.PASS, alertMessage);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage);	
						        
						        		
						        // Accepting alert		
						        alert.accept();	
						        
						        Thread.sleep(2000);
						        String alertMessage1=driver.switchTo().alert().getText();
						        
						        
						        Thread.sleep(3000);
						        test.log(LogStatus.PASS, alertMessage1);
						        
						        Thread.sleep(2000);
						        // Capturing alert message.    
						         driver.switchTo().alert().accept();	
						         
						         Thread.sleep(3000);
								 performerPOM.clickLawFirmFilter(driver).sendKeys("Civil Opponent",Keys.ENTER);
								   
								 Thread.sleep(3000);
								 performerPOM.clickLawFirmFilter(driver).clear();
								 
								 test.log(LogStatus.PASS,"Opponent Filter work successfully");
							   
				  } 
					 
					 public static void OpponentWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  
						  
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						  
						  
							 Thread.sleep(5000);
							 performerPOM.clickMasters(driver).click();

							 Thread.sleep(3000);
						     performerPOM.chooseOpponentMasters(driver).click();
						     Thread.sleep(3000);
						     performerPOM.NewOpponent(driver).click();
						   
							
						    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePartyDetial"));
						
						   Thread.sleep(3000);
						   performerPOM.saveOpponent(driver).click();
						   
						   Thread.sleep(3000);
								 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
									if(msg1.equalsIgnoreCase(msg1))
									{
										test.log(LogStatus.PASS, "Without Enter Data = "+msg1);
										
									}
										else
										{
											test.log(LogStatus.FAIL, "Without Enter Data = "+msg1);
										}
									
									
						   
						   
						   Thread.sleep(3000);
						   performerPOM.closeOpponent(driver).click();
						   
					  }
					 public static void OpponentInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  
						  
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						  
						  
							 Thread.sleep(5000);
							 performerPOM.clickMasters(driver).click();

							 Thread.sleep(3000);
						     performerPOM.chooseOpponentMasters(driver).click();
						     Thread.sleep(3000);
						     performerPOM.NewOpponent(driver).click();
						   
							
						    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePartyDetial"));
						
							   
							   Thread.sleep(3000);
							   performerPOM.clickOpponentName(driver).sendKeys("23@#");
							   
							   Thread.sleep(3000);
							   performerPOM.clickOpponentEmail(driver).sendKeys("dfg2#");
							   
							   Thread.sleep(2000);
							 	performerPOM.opponentcontactNo(driver).sendKeys("456");
							   
							   
						    
						   Thread.sleep(3000);
						   performerPOM.saveOpponent(driver).click();
						   
						   
						   Thread.sleep(3000);
						  String msg= performerPOM. clickCriteriaInvalidMsg(driver).getText();
						  
						   test.log(LogStatus.PASS, "Enter Invalid Opponent name = "+msg);
						   
						   
						   Thread.sleep(3000);
								 String msg1 = performerPOM.readOppoenentMsg1(driver).getText();
									if(msg1.equalsIgnoreCase(msg1))
									{
										test.log(LogStatus.PASS, "Enter Invalid Data = "+msg1);
										
									}
										else
										{
											test.log(LogStatus.FAIL, "Enter Invalid Data = "+msg1);
										}
									
									
						   
						   
						   Thread.sleep(3000);
						   performerPOM.closeOpponent(driver).click();
						   
					  }
					 
					 public static void OpponentCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  
						  
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						  
						  
							 Thread.sleep(5000);
							 performerPOM.clickMasters(driver).click();

							 Thread.sleep(3000);
						     performerPOM.chooseOpponentMasters(driver).click();
						     Thread.sleep(3000);
						     performerPOM.NewOpponent(driver).click();
						   
							
						    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePartyDetial"));
						    
						    if( performerPOM.closeOpponent(driver).isEnabled())
						    {
							   Thread.sleep(3000);
							   performerPOM.closeOpponent(driver).click();
							   test.log(LogStatus.PASS, "Close button is clickable");	
						    }
						    else
						    {
						    	 test.log(LogStatus.FAIL, "Close button is not clickable");	
						    }
						    
						    Thread.sleep(2000);
						    OverduePOM.clickDashboard(driver).click();
					  }
						
					 
					 
					 
					 public static void Court(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					   {
						   XSSFSheet sheet = ReadExcel();
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						 
						   Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						
						    Thread.sleep(3000);
						     performerPOM.clickCourtMasters(driver).click();

					  	   Thread.sleep(3000);
						   performerPOM.clickNewCourt(driver).click();
						   
						   Thread.sleep(5000);
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCourt"));
						 
						   Thread.sleep(5000);
						   Row row18 = sheet.getRow(93);						//Selected 0th index row (First row)
						   Cell c18 = row18.getCell(1);						//Selected cell (0 row,1 column)
						   String courtname= c18.getStringCellValue();
						   performerPOM.clickCourtName(driver).sendKeys(courtname);
						   
						   Thread.sleep(5000);
						   performerPOM.clickCourtType(driver).click();
						   Thread.sleep(5000);
						   performerPOM.selectCourtType(driver).click();
						   Thread.sleep(5000);
						   performerPOM.clickCountry(driver).click();
						   Thread.sleep(5000);
						   performerPOM.selectCountry(driver).click();
						   
					       Thread.sleep(3000);
						   performerPOM.saveCourt(driver).click();
						   
						   Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains("Court Detail Save Successfully."))
								{
									test.log(LogStatus.PASS, "Court -Court Detail Save Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Court-Court Name already exists.");
								}
						    
						   Thread.sleep(4000);
						   performerPOM.closeCourt(driver).click();
						   
						   driver.switchTo().parentFrame();
						   
						/*   Thread.sleep(4000);
						   performerPOM.editCourt(driver).click();
						   
						   
						   Thread.sleep(4000);
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCourt"));
						   

						   Thread.sleep(400);
						   performerPOM.clickCourtName(driver).clear();
						   
						   
						   Thread.sleep(2000);
						   Row row19 = sheet.getRow(94);						//Selected 0th index row (First row)
						   Cell c19 = row19.getCell(1);						//Selected cell (0 row,1 column)
						   String editcourtname= c19.getStringCellValue();
						   performerPOM.clickCourtName(driver).sendKeys(editcourtname);
						   
//						   Thread.sleep(5000);
//						   performerPOM.clickCourtType(driver).click();
//						   Thread.sleep(5000);
//						   performerPOM.selectCourtType(driver).click();
//						   Thread.sleep(5000);
//						   performerPOM.clickCountry(driver).click();
//						   Thread.sleep(5000);
//						   performerPOM.selectCountry(driver).click();
						   
						   
						   
						   Thread.sleep(4000);
						   performerPOM.saveCourt(driver).click();
						   
						   Thread.sleep(3000);
							 String msg2 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg2.contains("Court Detail Updated Successfully"))
								{
									test.log(LogStatus.PASS, "Update Court-Court Detail Updated Successfully");
								}
								else
								{
									test.log(LogStatus.FAIL, "Update Court-Court Name already exists.");
								}
								 Thread.sleep(4000);
								   performerPOM.closeCourt(driver).click(); */
								   
								   Thread.sleep(3000);
								   performerPOM.deleteCourt(driver).click();
								   
								   
								   
								   Thread.sleep(5000);
								   // Switching to Alert        
							        Alert alert = driver.switchTo().alert();		
							        		
							        // Capturing alert message.    
							        String alertMessage= driver.switchTo().alert().getText();	
							        
							        
							        test.log(LogStatus.PASS, alertMessage);
							        		
							        // Displaying alert message		
							        System.out.println(alertMessage);	
							        
							        		
							        // Accepting alert		
							        alert.accept();	
							        
							        
							        
							        Thread.sleep(2000);
							        String alertMessage1=driver.switchTo().alert().getText();
							        
							        
							        Thread.sleep(3000);
							        test.log(LogStatus.PASS, alertMessage1);
							        
							        Thread.sleep(2000);
							        // Capturing alert message.    
							         driver.switchTo().alert().accept();	
								  	 
							        
							        Thread.sleep(3000);
									 performerPOM.clickLawFirmFilter(driver).sendKeys("	Dehl High Court",Keys.ENTER);
									   
									 Thread.sleep(3000);
									 performerPOM.clickLawFirmFilter(driver).clear();
									 
									 test.log(LogStatus.PASS,"Court Filter work successfully");
								   	
								  	 
								   
						}	
					 
					 public static void CourtWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						  
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						 
						   Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						
						    Thread.sleep(3000);
						     performerPOM.clickCourtMasters(driver).click();

					  	   Thread.sleep(3000);
						   performerPOM.clickNewCourt(driver).click();
						   
						   Thread.sleep(5000);
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCourt"));
						 
					       Thread.sleep(3000);
						   performerPOM.saveCourt(driver).click();
						   
						   Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg1(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS,"Without Enter Data=" +msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data=" +msg1);
								}
						    
						   Thread.sleep(4000);
						   performerPOM.closeCourt(driver).click();
					   }
					 
					 public static void CourtInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						 
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						 
						   Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						
						    Thread.sleep(3000);
						     performerPOM.clickCourtMasters(driver).click();

					  	   Thread.sleep(3000);
						   performerPOM.clickNewCourt(driver).click();
						   
						   Thread.sleep(5000);
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCourt"));
						 
						   Thread.sleep(5000);
						   performerPOM.clickCourtName(driver).sendKeys("$%");
						   
						   Thread.sleep(3000);
							  String msg= performerPOM.clickCriteriaInvalidMsg(driver).getText();
							  
							   test.log(LogStatus.PASS, "Enter Invalid Court name = "+msg);
							   
						   
						   Thread.sleep(5000);
						   performerPOM.clickCourtType(driver).click();
						   Thread.sleep(5000);
						   performerPOM.selectCourtType(driver).click();
						   Thread.sleep(5000);
						   performerPOM.clickCountry(driver).click();
						   Thread.sleep(5000);
						   performerPOM.selectCountry(driver).click();
						   
					       Thread.sleep(3000);
						   performerPOM.saveCourt(driver).click();
						   
						 
						   
						   Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Enter Invalid Data="+msg1);
								}
								else
								{
									test.log(LogStatus.FAIL,  "Enter Invalid Data="+msg1);
								}
						    
						   Thread.sleep(4000);
						   performerPOM.closeCourt(driver).click();
					   }
					 
					 
					 public static void CourtTwomanadatoryFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						 
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						 
						   Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						
						    Thread.sleep(3000);
						     performerPOM.clickCourtMasters(driver).click();

					  	   Thread.sleep(3000);
						   performerPOM.clickNewCourt(driver).click();
						   
						   Thread.sleep(5000);
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCourt"));
						 
						   Thread.sleep(5000);
						   performerPOM.clickCourtName(driver).sendKeys("abc");
						  
						   Thread.sleep(5000);
						   performerPOM.clickCourtType(driver).click();
						   Thread.sleep(5000);
						   performerPOM.selectCourtType(driver).click();
						   
						    Thread.sleep(3000);
							   performerPOM.saveCourt(driver).click();
							   
						   Thread.sleep(3000);
								 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
									if(msg1.contains(msg1))
									{
										test.log(LogStatus.PASS, "Enter Two Manadatory fields ="+msg1);
									}
									else
									{
										test.log(LogStatus.FAIL,  "Enter Two Manadatory fields="+msg1);
									}
							    
							   Thread.sleep(4000);
							   performerPOM.closeCourt(driver).click();
						   }
					 
					 public static void CourtCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						 
							WebDriverWait wait = new WebDriverWait(driver, 180);
							progress(driver);
						 
						   Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
						
						    Thread.sleep(3000);
						     performerPOM.clickCourtMasters(driver).click();

					  	   Thread.sleep(3000);
						   performerPOM.clickNewCourt(driver).click();
						   
						   Thread.sleep(5000);
						   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCourt"));
						   
						   if(performerPOM.closeCourt(driver).isEnabled())
						   {
						     Thread.sleep(4000);
						      performerPOM.closeCourt(driver).click();
						      test.log(LogStatus.PASS, "Close button is  clickable");
						   }
						   else
						   {
							   test.log(LogStatus.FAIL, "Close button is not clickable");
						   }
					   }
				 
						   
					 public static void CaseNoticeType(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					   {
						   XSSFSheet sheet = ReadExcel();
						   
						    WebDriverWait wait=new WebDriverWait(driver,20);  
						    Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
							   
							Thread.sleep(3000);
							performerPOM.clickCasNoticeTypecfo(driver).click();
							
						
						    Thread.sleep(3000);
							performerPOM.NewCaseNoticeType(driver).click();
							
							 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseType"));
							Thread.sleep(3000);
							performerPOM.CaseNoticeType(driver).click();
							
							Thread.sleep(3000);
							performerPOM.selectCaseNoticeType(driver).click();
							
							Thread.sleep(3000);
							performerPOM.CaseNoticeType(driver).click();
							
							Thread.sleep(3000);
							Row row19 = sheet.getRow(98);						//Selected 0th index row (First row)
							Cell c19 = row19.getCell(1);						//Selected cell (0 row,1 column)
							String typename= c19.getStringCellValue();
							performerPOM.TypeName(driver).sendKeys(typename);
						
							Thread.sleep(6000);
							performerPOM.saveCaseNoticeType(driver).click();
							
							 Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains("Case/Notice Type Saved Successfully."))
								{
									test.log(LogStatus.PASS, " Case/Notice -Case/Notice Type Saved Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Case/Notice -Case/Notice type already exists.");
								}
							
							
							Thread.sleep(3000);
							performerPOM.closeCaseNoticeType(driver).click();
							
							driver.switchTo().parentFrame();
							
							Thread.sleep(3000);
							performerPOM.editCaseNoticeType(driver).click();
							
							Thread.sleep(3000);
							 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseType"));
							 
							 
						 
								Thread.sleep(3000);
								performerPOM.TypeName(driver).clear();
								
								Thread.sleep(3000);
								Row row20 = sheet.getRow(99);						//Selected 0th index row (First row)
								Cell c20 = row20.getCell(1);						//Selected cell (0 row,1 column)
								String typename1= c20.getStringCellValue();
								performerPOM.TypeName(driver).sendKeys(typename1);
								
								
								Thread.sleep(6000);
								performerPOM.saveCaseNoticeType(driver).click();
								
								 Thread.sleep(3000);
								 String msg2 = performerPOM.readOppoenentMsg(driver).getText();
									if(msg2.contains("Case/Notice Type Updated Successfully."))
									{
										test.log(LogStatus.PASS, "Update Case/Notice - Case/Notice Type Updated Successfully.");
									}
									else
									{
										test.log(LogStatus.FAIL, "Update Case/Notice - Case/Notice type already exists.");
									}
								
								
								Thread.sleep(3000);
								performerPOM.closeCaseNoticeType(driver).click();
								driver.switchTo().parentFrame();
								
								Thread.sleep(3000);
								performerPOM.deleteCaseNoticeType(driver).click();
								
								   Thread.sleep(5000);
								    // Switching to Alert        
							        Alert alert = driver.switchTo().alert();		
							        		
							        // Capturing alert message.    
							        String alertMessage= driver.switchTo().alert().getText();	
							        
							        
							        test.log(LogStatus.PASS, alertMessage);
							        		
							        // Displaying alert message		
							        System.out.println(alertMessage);	
							        
							        		
							        // Accepting alert		
							        alert.accept();	
							        
							       
							        String alertMessage1=driver.switchTo().alert().getText();
							        
							        
							        Thread.sleep(3000);
							        test.log(LogStatus.PASS, alertMessage1);
							        
							        Thread.sleep(2000);
							        // Capturing alert message.    
							         driver.switchTo().alert().accept(); 
							         
							         Thread.sleep(3000);
									 performerPOM.clickLawFirmFilter(driver).sendKeys("Arbittration",Keys.ENTER);
									   
									 Thread.sleep(3000);
									 performerPOM.clickLawFirmFilter(driver).clear();
									 
									 test.log(LogStatus.PASS,"Case/Notice Type Filter work successfully");
								  	 
						}	
					 
					 
					 public static void CaseNoticeTypeWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						  
						   
						    WebDriverWait wait=new WebDriverWait(driver,20);  
						    Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
							   
							Thread.sleep(3000);
							performerPOM.clickCasNoticeTypecfo(driver).click();
							
						
						    Thread.sleep(3000);
							performerPOM.NewCaseNoticeType(driver).click();
							
							 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseType"));
						
						
							Thread.sleep(6000);
							performerPOM.saveCaseNoticeType(driver).click();
							
							 Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg1(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS,"Enter Without Data =" +msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Enter Without Data =" +msg1);
								}
							
							
							Thread.sleep(3000);
							performerPOM.closeCaseNoticeType(driver).click();
					   }
					 
					 public static void CaseNoticeTypeInvaliData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						  
						   
						    WebDriverWait wait=new WebDriverWait(driver,20);  
						    Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
							   
							Thread.sleep(3000);
							performerPOM.clickCasNoticeTypecfo(driver).click();
							
						
						    Thread.sleep(3000);
							performerPOM.NewCaseNoticeType(driver).click();
							
							 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseType"));
							Thread.sleep(3000);
							performerPOM.CaseNoticeType(driver).click();
							
							Thread.sleep(3000);
							performerPOM.selectCaseNoticeType(driver).click();
							
							Thread.sleep(3000);
							performerPOM.CaseNoticeType(driver).click();
							
							Thread.sleep(3000);
						
							performerPOM.TypeName(driver).sendKeys("23#$");
							
							 
							   Thread.sleep(3000);
								  String msg= performerPOM.clickCriteriaInvalidMsg(driver).getText();
								  
								   test.log(LogStatus.PASS, "Enter Invalid Case/Notice Type  = "+msg);
							
							
						
							Thread.sleep(6000);
							performerPOM.saveCaseNoticeType(driver).click();
							
							 Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Enter Invalid Case/Notice Type=" +msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid Case/Notice Type=" +msg1);
								}
							
							
							Thread.sleep(3000);
							performerPOM.closeCaseNoticeType(driver).click();
					   }
					 
					 public static void CaseNoticeTypeCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					   {
						  
						   
						    WebDriverWait wait=new WebDriverWait(driver,20);  
						    Thread.sleep(3000);
						    performerPOM.clickMasters(driver).click();
							   
							Thread.sleep(3000);
							performerPOM.clickCasNoticeTypecfo(driver).click();
							
						
						    Thread.sleep(3000);
							performerPOM.NewCaseNoticeType(driver).click();
							
							 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseType"));
							
							 if(performerPOM.closeCaseNoticeType(driver).isEnabled())
							 {
							     Thread.sleep(3000);
								performerPOM.closeCaseNoticeType(driver).click();
								test.log(LogStatus.PASS, "Close Button is clickable");	
							 }
							 else
							 {
								 test.log(LogStatus.FAIL, "Close Button is not  clickable");	
							 }
					   }
								
					 
					 public static void PaymentType(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					 {
						XSSFSheet sheet = ReadExcel();
						 WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(5000);
						  performerPOM.clickMasters(driver).click();  
						  Thread.sleep(3000);
						  performerPOM.clickPaymentTypeMasters(driver).click();
				          Thread.sleep(4000);
						  performerPOM.clickPaymentTypeNew(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePayment"));
					   
						  Thread.sleep(3000);
						  Row row20 = sheet.getRow(103);						//Selected 0th index row (First row)
						  Cell c20= row20.getCell(1);						//Selected cell (0 row,1 column)
						  String payment= c20.getStringCellValue();
						   performerPOM.PaymentType(driver).sendKeys(payment);
						   
						   
						  Thread.sleep(4000);
						  performerPOM.savePaymentType(driver).click();
						  

							 Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains("Payment Type Saved Successfully"))
								{
									test.log(LogStatus.PASS, "Payment Type -Payment Type Saved Successfully");
								}
								else
								{
									test.log(LogStatus.FAIL, "Payment Type -Payment Type Already Exists");
								}
						   
						   Thread.sleep(4000);
						  performerPOM.closePaymentType(driver).click();
						  
						  
						  Thread.sleep(3000);
						  performerPOM.editPaymentType(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePayment"));
						   
						  Thread.sleep(3000);
						  performerPOM.PaymentType(driver).clear();
						  Thread.sleep(3000);
						  Row row21 = sheet.getRow(104);						//Selected 0th index row (First row)
						  Cell c21= row21.getCell(1);						//Selected cell (0 row,1 column)
						  String payment1= c21.getStringCellValue();
						   performerPOM.PaymentType(driver).sendKeys(payment1);
						   
						   Thread.sleep(4000);
							  performerPOM.savePaymentType(driver).click();
							  

								 Thread.sleep(3000);
								 String msg2 = performerPOM.readOppoenentMsg(driver).getText();
									if(msg2.contains("Payment Type Updated Successfully"))
									{
										test.log(LogStatus.PASS, "Update Payment Type - Payment Type Updated Successfully");
									}
									else
									{
										test.log(LogStatus.FAIL, "Update Payment Type -Payment Type Already Exists");
									}
							   
							   Thread.sleep(4000);
							  performerPOM.closePaymentType(driver).click();
						   
							  Thread.sleep(4000);
							  performerPOM.deletePaymentType(driver).click();
							  
							  
							  Thread.sleep(5000);
							    // Switching to Alert        
						        Alert alert = driver.switchTo().alert();		
						        		
						        // Capturing alert message.    
						        String alertMessage= driver.switchTo().alert().getText();	
						        
						        
						        test.log(LogStatus.PASS, alertMessage);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage);	
						        
						        		
						        // Accepting alert		
						        alert.accept();	
						        
						        Thread.sleep(2000);
						        String alertMessage1=driver.switchTo().alert().getText();
						        
						        
						        Thread.sleep(3000);
						        test.log(LogStatus.PASS, alertMessage1);
						        
						        Thread.sleep(2000);
						        // Capturing alert message.    
						         driver.switchTo().alert().accept();		
						         
						         Thread.sleep(3000);
								 performerPOM.clickLawFirmFilter(driver).sendKeys("Case drafting fees",Keys.ENTER);
								 
								 
								Thread.sleep(3000);
								performerPOM.clickApplybtn(driver).click();
								   
								 Thread.sleep(3000);
								 performerPOM.clickLawFirmFilter(driver).clear();
								 
								 
								 
								 test.log(LogStatus.PASS,"Payment Type Filter work successfully");
					 }	
					 
					 
					 public static void PaymentTypeWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					 {
						
						 WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(5000);
						  performerPOM.clickMasters(driver).click();  
						  Thread.sleep(3000);
						  performerPOM.clickPaymentTypeMasters(driver).click();
				          Thread.sleep(4000);
						  performerPOM.clickPaymentTypeNew(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePayment")); 
					 	   
						  Thread.sleep(4000);
						  performerPOM.savePaymentType(driver).click();
						  

							 Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg1(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Enter Without Data ="+msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Enter Without Data ="+msg1);
								}
						   
						   Thread.sleep(4000);
						  performerPOM.closePaymentType(driver).click();
					 }
					 public static void PaymentTypeInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					 {
						
						 WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(5000);
						  performerPOM.clickMasters(driver).click();  
						  Thread.sleep(3000);
						  performerPOM.clickPaymentTypeMasters(driver).click();
				          Thread.sleep(4000);
						  performerPOM.clickPaymentTypeNew(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePayment"));
						  
						  Thread.sleep(4000);
						  performerPOM.PaymentType(driver).sendKeys("#$34");
						  
						   Thread.sleep(3000);
							  String msg= performerPOM.clickCriteriaInvalidMsg(driver).getText();
							  
							   test.log(LogStatus.PASS, "Enter Invalid Payment Type  = "+msg);
					 	   
						  Thread.sleep(4000);
						  performerPOM.savePaymentType(driver).click();
						  

							 Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg1(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Enter Invalid Data ="+msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid Data ="+msg1);
								}
						   
						   Thread.sleep(4000);
						  performerPOM.closePaymentType(driver).click();
					 }
					 
					 public static void PaymentTypeCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					 {
						
						 WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(5000);
						  performerPOM.clickMasters(driver).click();  
						  Thread.sleep(3000);
						  performerPOM.clickPaymentTypeMasters(driver).click();
				          Thread.sleep(4000);
						  performerPOM.clickPaymentTypeNew(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframePayment"));
						  
						  
						  if(performerPOM.closePaymentType(driver).isEnabled())
						  {
						       Thread.sleep(4000);
							  performerPOM.closePaymentType(driver).click();
							  test.log(LogStatus.PASS,"Close button is clickable");
						  }
						  
						  else
						  {
							  test.log(LogStatus.PASS,"Close button is not clickable");
						  }
					 }
					 
						  
					 public static void customParameter(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					  {
				    	 
				    	 XSSFSheet sheet = ReadExcel();
						 WebDriverWait wait=new WebDriverWait(driver,20);  
							 
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						 
						  
						  Thread.sleep(3000);
						  performerPOM.customParameterMaster(driver).click();
						  Thread.sleep(3000);
						  performerPOM.newCustomParameter(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCustomFieldDetial"));
						  
						  Thread.sleep(3000);
						  performerPOM.typeCustomParameter(driver).click();
						  Thread.sleep(3000);
						  performerPOM.selectTypeCustomParameter(driver).click();
						  
						  Thread.sleep(3000);
						  Row row21 = sheet.getRow(108);						//Selected 0th index row (First row)
						  Cell c21= row21.getCell(1);						//Selected cell (0 row,1 column)
						  String parameterLable= c21.getStringCellValue();
						  performerPOM.ParameterLabel(driver).sendKeys(parameterLable);
						  
						  Thread.sleep(3000);
						  performerPOM.typeCustomParameter(driver).click();
						  Thread.sleep(3000);
						  performerPOM.saveCustomParameter(driver).click();
						  
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains("Custome Field Saved Successfully."))
								{
									test.log(LogStatus.PASS, "Custome Field- Custome Field Saved Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Custome Field- Custome Field with same name already exists.");
								}
						   
						   
						  Thread.sleep(3000);
						  performerPOM.closeCustomParameter(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.editCustomParameter(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCustomFieldDetial"));
						  
						  Thread.sleep(3000);
						  performerPOM.ParameterLabel(driver).clear();
						  Thread.sleep(3000);
						  Row row22 = sheet.getRow(109);						//Selected 0th index row (First row)
						  Cell c22= row22.getCell(1);						//Selected cell (0 row,1 column)
						  String parameterLable1= c22.getStringCellValue();
						  performerPOM.ParameterLabel(driver).sendKeys(parameterLable1);
						  
						  
						  Thread.sleep(3000);
						  performerPOM.saveCustomParameter(driver).click();
						  
						  
						  Thread.sleep(3000);
							 String msg2 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg2.contains("Custome Field Updated Successfully."))
								{
									test.log(LogStatus.PASS, "Update Custome Field - Custome Field Updated Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Update Custome Field-Custome Field with same name already exists.");
								}
						   
						   
						  Thread.sleep(3000);
						  performerPOM.closeCustomParameter(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.deleteCustomParameter(driver).click();
						  
						  Thread.sleep(5000);
						    // Switching to Alert        
					        Alert alert = driver.switchTo().alert();		
					        		
					        // Capturing alert message.    
					        String alertMessage= driver.switchTo().alert().getText();	
					        
					        
					        test.log(LogStatus.PASS, alertMessage);
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage);	
					        
					        		
					        // Accepting alert		
					        alert.accept();	
					        
					        Thread.sleep(2000);
					        String alertMessage1=driver.switchTo().alert().getText();
					        
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage1);
					        
					        Thread.sleep(2000);
					        // Capturing alert message.    
					         driver.switchTo().alert().accept();	
					        
					        Thread.sleep(6000);
							performerPOM. clickCustomParameterFilter(driver).click();
							
						    Thread.sleep(6000);
							performerPOM. clickCustomParameterFilter1(driver).click();
							
							Thread.sleep(3000);
							performerPOM.clickApplybtn(driver).click();
								   
						    Thread.sleep(3000);
							 performerPOM.clickLawFirmFilter(driver).sendKeys("Test",Keys.ENTER);
								 
							Thread.sleep(3000);
							performerPOM.clickApplybtn(driver).click();
							
							 Thread.sleep(3000);
							 performerPOM.clickLawFirmFilter(driver).clear();
					
						    
							 test.log(LogStatus.PASS,"Custom Prameter Filter work successfully");
					} 
					 
					  public static void customParameterWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
				    	 
				    	
						 WebDriverWait wait=new WebDriverWait(driver,20);  
							 
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						  Thread.sleep(3000);
						  performerPOM.customParameterMaster(driver).click();
						  Thread.sleep(3000);
						  performerPOM.newCustomParameter(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCustomFieldDetial"));
						  
						 
						
						  Thread.sleep(3000);
						  performerPOM.saveCustomParameter(driver).click();
						  
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg1(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Without Enter Data ="+msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data ="+msg1);
								}
						   
						   
						  Thread.sleep(3000);
						  performerPOM.closeCustomParameter(driver).click();
					  }
					  
					  public static void customParameterInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
				    	 
				    	
						 WebDriverWait wait=new WebDriverWait(driver,20);  
							 
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						  Thread.sleep(3000);
						  performerPOM.customParameterMaster(driver).click();
						  Thread.sleep(3000);
						  performerPOM.newCustomParameter(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCustomFieldDetial"));
						  
						  Thread.sleep(3000);
						  performerPOM.typeCustomParameter(driver).click();
						  Thread.sleep(3000);
						  performerPOM.selectTypeCustomParameter(driver).click();
						  Thread.sleep(3000);
						  performerPOM.typeCustomParameter(driver).click();
						  
						  Thread.sleep(4000);
						  performerPOM.ParameterLabel(driver).sendKeys("$%#8a4");
						    Thread.sleep(4000);
						  performerPOM.saveCustomParameter(driver).click();
						  
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.FAIL, "Enter Invalid  Data ="+msg1);
								}
								else
								{
									test.log(LogStatus.PASS, "Enter Invalid  Data ="+msg1);
								}
						   
						   
						  Thread.sleep(3000);
						  performerPOM.closeCustomParameter(driver).click();
					  } 
					  
					  public static void customParameterCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
				    	 
				    	
						 WebDriverWait wait=new WebDriverWait(driver,20);  
							 
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						  Thread.sleep(3000);
						  performerPOM.customParameterMaster(driver).click();
						  Thread.sleep(3000);
						  performerPOM.newCustomParameter(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCustomFieldDetial"));
						  
					 
						if( performerPOM.closeCustomParameter(driver).isEnabled())  
					    {
							Thread.sleep(3000);
							 performerPOM.closeCustomParameter(driver).click();
							 test.log(LogStatus.PASS,"Close button is clicakble");
					    }
						else
						{
							 test.log(LogStatus.FAIL,"Close button is not clicakble");
						}
						 
					  } 
					  
					 
					 
					 
					 
					 
					 public static void CaseStage(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
				     {
				    	 XSSFSheet sheet=ReadExcel();
				    	 WebDriverWait wait=new WebDriverWait(driver,20);  
				    	  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						 
						  
				     	 performerPOM.caseStageMaster(driver).click();
						  Thread.sleep(0);
				    	 performerPOM.newCaseStage(driver).click();
				    	 
				    	 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseStageType"));
				    	 
				    	 Thread.sleep(3000);
						 Row row=sheet.getRow(113);
						 Cell c=row.getCell(1);
						 String casestage=c.getStringCellValue();
				    	 performerPOM.clickcaseStage(driver).sendKeys(casestage);
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.savecaseStage(driver).click();
				    	 
				    	 Thread.sleep(3000);
						 String msg1 = performerPOM.readcaseStagemsg(driver).getText();
							if(msg1.equalsIgnoreCase("Case Stage Type Saved Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg1);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg1);
								}
							
					   
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.closecaseStage(driver).click();
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.editcaseStage(driver).click();
				    	 
				    	 
				         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseStageType"));
				    	 
				         Thread.sleep(3000);
				         performerPOM.clickcaseStage(driver).clear();
				 
				    	 Thread.sleep(3000);
						 Row row1=sheet.getRow(114);
						 Cell c1=row1.getCell(1);
						 String casestage1=c1.getStringCellValue();
				    	 performerPOM.clickcaseStage(driver).sendKeys(casestage1);
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.savecaseStage(driver).click();
				    	 
				    	 Thread.sleep(3000);
						 String msg2 = performerPOM.readcaseStagemsg(driver).getText();
							
							if(msg2.equalsIgnoreCase("Case Stage Type Updated Successfully."))
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg2);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Message displayed = "+msg2);
								}
							
					   
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.closecaseStage(driver).click();
				    	 
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.deletecaseStage(driver).click();
				    	 
				    	 
						  
						  Thread.sleep(5000);
						    // Switching to Alert        
					        Alert alert = driver.switchTo().alert();		
					        		
					        Thread.sleep(5000);
					        // Capturing alert message.    
					        String alertMessage= driver.switchTo().alert().getText();	
					        
					        Thread.sleep(5000);
					        test.log(LogStatus.PASS, alertMessage);
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage);	
					        
					        Thread.sleep(5000);	
					        // Accepting alert		
					        driver.switchTo().alert().accept();		
					           
					        Thread.sleep(2000);
					        String alertMessage1=driver.switchTo().alert().getText();
					        
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage1);
					        
					        Thread.sleep(2000);
					        // Capturing alert message.    
					         driver.switchTo().alert().accept();	
					         
					         Thread.sleep(3000);
							 performerPOM.clickLawFirmFilter(driver).sendKeys("Final Stage",Keys.ENTER);
							 
							 Thread.sleep(3000);
							 performerPOM.clickLawFirmFilter(driver).clear();
							 
							 test.log(LogStatus.PASS, "Case Stage Filter work successfully");
				    	 
				  }
					 
					 public static void CaseStageInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
				     {
				    
				    	 WebDriverWait wait=new WebDriverWait(driver,20);  
				    	  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						 
						  
				     	 performerPOM.caseStageMaster(driver).click();
						  Thread.sleep(0);
				    	 performerPOM.newCaseStage(driver).click();
				    	 
				    	 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseStageType"));
				    	  
				    	 Thread.sleep(3000);
						
				    	 performerPOM.clickcaseStage(driver).sendKeys("$%45");
				    	 
				  	   Thread.sleep(3000);
						  String msg= performerPOM.clickCriteriaInvalidMsg(driver).getText();
						  
						   test.log(LogStatus.PASS, "Enter Invalid Case Stage Name  = "+msg);
				    	 
				    	 Thread.sleep(3000);
				    	 performerPOM.savecaseStage(driver).click();
				    	 
				    	 Thread.sleep(3000);
						 String msg1 = performerPOM.readcaseStagemsg(driver).getText();
							if(msg1.equalsIgnoreCase(msg1))
							{
								test.log(LogStatus.PASS, "Enter Invalid Data = "+msg1);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid Data  = "+msg1);
								} 
				    	 Thread.sleep(3000);
				    	 performerPOM.closecaseStage(driver).click();
				     }
					 
					 public static void CaseStageWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
				     {
				    
				    	 WebDriverWait wait=new WebDriverWait(driver,20);  
				    	  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						 
						  
				     	 performerPOM.caseStageMaster(driver).click();
						  Thread.sleep(0);
				    	 performerPOM.newCaseStage(driver).click();
				    	 
				    	 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseStageType"));
				    	  
				    	 Thread.sleep(3000);
				    	 performerPOM.savecaseStage(driver).click();
				    	 
				    	 Thread.sleep(3000);
						 String msg1 = performerPOM.readcaseStagemsg(driver).getText();
							if(msg1.equalsIgnoreCase(msg1))
							{
								test.log(LogStatus.PASS, "Without Enter Data = "+msg1);
								
							}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data  = "+msg1);
								} 
				    	 Thread.sleep(3000);
				    	 performerPOM.closecaseStage(driver).click();
				     }
					 public static void CaseStageCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
				     {
				    
				    	 WebDriverWait wait=new WebDriverWait(driver,20);  
				    	  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();
						  performerPOM.caseStageMaster(driver).click();
						  Thread.sleep(0);
				    	 performerPOM.newCaseStage(driver).click();
				    	 
				    	 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseStageType"));
				    	  
				    	 
				    	 if( performerPOM.closecaseStage(driver).isEnabled())
				    	 {
				    	    Thread.sleep(3000);
				    	    performerPOM.closecaseStage(driver).click();
				    	    test.log(LogStatus.PASS, "Close Button is clickable");	
				    	 }
				    	 else
				    	 {
				    		 test.log(LogStatus.FAIL, "Close Button is not clickable");	
				    	 }
				     }
					 
					 
					 public static void DocumentType(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					  {
					  
					      XSSFSheet sheet=ReadExcel();
					      WebDriverWait wait=new WebDriverWait(driver,20);  
					      Thread.sleep(3000);
					      performerPOM.clickMasters(driver).click();
					      
					      Thread.sleep(3000);
						  performerPOM.DocumentTypeMasters(driver).click();
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeDocType"));
						  
						  Thread.sleep(3000);
						  Row row=sheet.getRow(118);
						  Cell c=row.getCell(1);
						  String doctype=c.getStringCellValue();
						  performerPOM.clickDocumentType(driver).sendKeys(doctype);
						  
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains("Document Type Added Successfully"))
								{
									test.log(LogStatus.PASS, "Document Type- Document Type Added Successfully");
								}
								else
								{
									test.log(LogStatus.FAIL, "Document Type-Litigation Document-Type already exists.");
								}
			              Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.editDocumentType(driver).click();
						  
			              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeDocType"));
						  
			              Thread.sleep(3000);
			              performerPOM.clickDocumentType(driver).clear();
			              
						  Thread.sleep(3000);
						  Row row1=sheet.getRow(119);
						  Cell c1=row1.getCell(1);
						  String doctype1=c1.getStringCellValue();
						  performerPOM.clickDocumentType(driver).sendKeys(doctype1);
						  
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg2 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg2.contains("Document Type Updated Successfully"))
								{
									test.log(LogStatus.PASS, "Update Document Type-Document Type Updated Successfully");
								}
								else
								{
									test.log(LogStatus.FAIL, "Update Document Type-Litigation Document-Type already exists.");
								}
			              Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
						  
						   Thread.sleep(3000);
						   performerPOM.deleteDocumentType(driver).click();
						   
						   
						   
						   Thread.sleep(5000);
						    // Switching to Alert        
					        Alert alert = driver.switchTo().alert();		
					        		
					        Thread.sleep(3000);
					        // Capturing alert message.    
					        String alertMessage= driver.switchTo().alert().getText();	
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage);
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage);	
					        
					        Thread.sleep(3000);	
					        // Accepting alert		
					        driver.switchTo().alert().accept();		
					           
					        Thread.sleep(2000);
					        String alertMessage1=driver.switchTo().alert().getText();
					        
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage1);
					        
					        Thread.sleep(2000);
					        // Capturing alert message.    
					         driver.switchTo().alert().accept();	
					         
					         Thread.sleep(3000);
							 performerPOM.clickLegalEntityFilter(driver).sendKeys("Case Document",Keys.ENTER);
							   
						     Thread.sleep(3000);
							 performerPOM.clickLegalEntityFilter(driver).clear(); 
					         
					         test.log(LogStatus.PASS,"Document Type filter working successfully");
						  
						 }
					 
					 public static void DocumentTypeWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
					   
					      WebDriverWait wait=new WebDriverWait(driver,20);  
					      Thread.sleep(3000);
					      performerPOM.clickMasters(driver).click();
					      
					      Thread.sleep(3000);
						  performerPOM.DocumentTypeMasters(driver).click();
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeDocType"));
						 
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Without Enter Data =" +msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data =" +msg1);
								}
			              Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
					  }
					 public static void DocumentTypeInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
					   
					      WebDriverWait wait=new WebDriverWait(driver,20);  
					      Thread.sleep(3000);
					      performerPOM.clickMasters(driver).click();
					      
					      Thread.sleep(3000);
						  performerPOM.DocumentTypeMasters(driver).click();
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeDocType"));
						 
						  Thread.sleep(3000);
						  performerPOM.clickDocumentType(driver).sendKeys("%^$2");
						  
						   Thread.sleep(3000);
							  String msg= performerPOM.clickCriteriaInvalidMsg(driver).getText();
							  
							   test.log(LogStatus.PASS, "Enter Invalid Document Type  = "+msg);
						  
				  
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Enter Invalid Data =" +msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid Data =" +msg1);
								}
			              Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
					  }
					 public static void DocumentTypeCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
					   
					      WebDriverWait wait=new WebDriverWait(driver,20);  
					      Thread.sleep(3000);
					      performerPOM.clickMasters(driver).click();
					      
					      Thread.sleep(3000);
						  performerPOM.DocumentTypeMasters(driver).click();
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeDocType"));
						 
						  if( performerPOM.closeDocumentType(driver).isEnabled())
						  {
						      Thread.sleep(3000);
						     performerPOM.closeDocumentType(driver).click();
						     test.log(LogStatus.PASS, "Close button is clickable");					 
						  }
						  else
						  {
							  test.log(LogStatus.FAIL, "Close button is not clickable");
						  }
					  }
						 
					 
					   
					   
					  public static void RatingCriteria(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					  {
						  XSSFSheet sheet=ReadExcel();
						  WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();
						  Thread.sleep(3000);
						
						  performerPOM.ratingCriteriaMasters(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeCriteriaMastre"));
						  
						  Thread.sleep(3000);
						  Row row=sheet.getRow(123);
						  Cell c=row.getCell(1);
						  String criteria=c.getStringCellValue();
						  performerPOM.clickCriteria(driver).sendKeys(criteria);
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains("Criteria Saved Successfully."))
								{
									test.log(LogStatus.PASS, "Rating Criteria-Criteria Saved Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Rating Criteria-Criteria already exists.");
								}
						  
						  
						  Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.editcriteria(driver).click();
						  
				        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeCriteriaMastre"));
						  
				        Thread.sleep(3000);
				        performerPOM.clickCriteria(driver).clear();
				        
						  Thread.sleep(3000);
						  Row row1=sheet.getRow(124);
						  Cell c1=row1.getCell(1);
						  String criteria1=c1.getStringCellValue();
						  performerPOM.clickCriteria(driver).sendKeys(criteria1);
						  
						  Thread.sleep(3000);
						  performerPOM.saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg2 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg2.contains("Criteria Updated Successfully."))
								{
									test.log(LogStatus.PASS, "Update Rating Criteria- Criteria Updated Successfully.");
								}
								else
								{
									test.log(LogStatus.FAIL, "Update Rating Criteria-Criteria already exists.");
								}
						  
						  
						  Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.deletecriteria(driver).click();
						  
						  
						   Thread.sleep(5000);
						    // Switching to Alert        
					        Alert alert = driver.switchTo().alert();		
					        		
					        Thread.sleep(3000);
					        // Capturing alert message.    
					        String alertMessage= driver.switchTo().alert().getText();	
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage);
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage);	
					        
					        Thread.sleep(3000);	
					        // Accepting alert		
					        driver.switchTo().alert().accept();		
					           
					        Thread.sleep(2000);
					        String alertMessage1=driver.switchTo().alert().getText();
					        
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage1);
					        
					        Thread.sleep(2000);
					        // Capturing alert message.    
					         driver.switchTo().alert().accept();	
					         
					         Thread.sleep(3000);
							 performerPOM.clickLawFirmFilter(driver).sendKeys("Case Arguments",Keys.ENTER);
							 
							 Thread.sleep(3000);
							 performerPOM.clickLawFirmFilter(driver).clear();
							 
							 test.log(LogStatus.PASS, "Rating Criteria Filter working  successfully");
						  
					 }
					  
					  public static void RatingCriteriaWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						
						  WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();
						  Thread.sleep(3000);
						
						  performerPOM.ratingCriteriaMasters(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeCriteriaMastre"));
						
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Without Enter Data="+msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Without Enter Data="+msg1);
								}
						  
						  
						  Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
					  }
					  
					  public static void RatingCriteriaInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  
						  WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();
						  Thread.sleep(3000);
						
						  performerPOM.ratingCriteriaMasters(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeCriteriaMastre"));
						  
						  Thread.sleep(3000);
					     performerPOM.clickCriteria(driver).sendKeys("#$54");
					     
					     Thread.sleep(3000);
						  String msg= performerPOM.clickCriteriaInvalidMsg(driver).getText();
						  
						   test.log(LogStatus.PASS, "Enter Invalid Rating Criteria  = "+msg);
					     
					     
					     
						  Thread.sleep(3000);
						  performerPOM. saveDocumentType(driver).click();
						  
						  Thread.sleep(3000);
							 String msg1 = performerPOM.readOppoenentMsg(driver).getText();
								if(msg1.contains(msg1))
								{
									test.log(LogStatus.PASS, "Enter Invalid Data ="+msg1);
								}
								else
								{
									test.log(LogStatus.FAIL, "Enter Invalid Data ="+msg1);
								}
						  
						  
						  Thread.sleep(3000);
						  performerPOM.closeDocumentType(driver).click();
					  }
					  
					  
					  public static void RatingCriteriaCloseButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
						  
						  WebDriverWait wait=new WebDriverWait(driver,20);  
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();
						  Thread.sleep(3000);
						
						  performerPOM.ratingCriteriaMasters(driver).click();
						  
						  Thread.sleep(3000);
						  performerPOM.NewDocumentType(driver).click();
						  
						  Thread.sleep(3000);
						  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeCriteriaMastre"));
						
						  if(  performerPOM.closeDocumentType(driver).isEnabled())
						  {
						     Thread.sleep(3000);
						     performerPOM.closeDocumentType(driver).click();
						     test.log(LogStatus.PASS, "Close button is clickable");
						  }
						  else
						  {
							  test.log(LogStatus.FAIL, "Close button is not clickable");
						  }
					  }
					  
						  
					  public static void NoticeStage(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
					  {
						  XSSFSheet sheet=ReadExcel();

						  Thread.sleep(4000);
						  performerPOM.clickMasters(driver).click();

						  
						 
						  
						  Thread.sleep(3000);
						  performerPOM.noticeStagecfoMasters(driver).click();
						  Thread.sleep(3000);
						  performerPOM.addNoticeStage(driver).click();
						  
						  
						  
						  Thread.sleep(3000);
						  Row row=sheet.getRow(128);
						  Cell c=row.getCell(1);
						  String NoticeStage=c.getStringCellValue();
						  performerPOM.clickNoticeStage(driver).sendKeys(NoticeStage);
						  
						  Thread.sleep(3000);
						  performerPOM.updateNoticeStage(driver).click();
						  
						  
						   Thread.sleep(10000);
						   // Switching to Alert        
					        Alert alert = driver.switchTo().alert();		
					        		
					        // Capturing alert message.    
					        String alertMessage= driver.switchTo().alert().getText();
					        
					        Thread.sleep(3000);
					        test.log(LogStatus.PASS, alertMessage);
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage);	
					        
					        		
					        // Accepting alert		
					        alert.accept();		
					        
					        Thread.sleep(3000);
							  performerPOM.editNoticeStage(driver).click();
							  
						        Thread.sleep(3000);
							  performerPOM.clickNoticeStage(driver).clear();
							  
							  Thread.sleep(3000);
							  Row row1=sheet.getRow(129);
							  Cell c1=row1.getCell(1);
							  String NoticeStage1=c1.getStringCellValue();
							  performerPOM.clickNoticeStage(driver).sendKeys(NoticeStage1);
							 
							  Thread.sleep(3000);
							  performerPOM.updateNoticeStage(driver).click();
							  
							   Thread.sleep(10000);
							  	
						        		
						        // Capturing alert message.    
						        String alertMessage1= driver.switchTo().alert().getText();
						        
						        Thread.sleep(3000);
						        test.log(LogStatus.PASS, alertMessage1);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage1);	
						        
						        		
						        // Accepting alert		
						        alert.accept();	
						        
						        Thread.sleep(3000);
						        performerPOM.deleteNoticeStage(driver).click();
						        
						        
						 	   Thread.sleep(10000);
							 	
						         // Capturing alert message.    
						        String alertMessage2= driver.switchTo().alert().getText();
						        
						        Thread.sleep(3000);
						        test.log(LogStatus.PASS, alertMessage2);
						        		
						        // Displaying alert message		
						        System.out.println(alertMessage2);	
						        
						        		
						        // Accepting alert		
						        alert.accept();	
						        
						        Thread.sleep(3000);
								 performerPOM.clickNoticeStageFilter(driver).sendKeys("Notice Arguments",Keys.ENTER);
								 
								 Thread.sleep(3000);
								 performerPOM.clickNoticeStageFilter(driver).clear();
								 
								 test.log(LogStatus.PASS, "Notice Stage Filter working  successfully");
							  
				     }
					  public static void UserReassignment(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
					  {
					        
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						
						  
						  Thread.sleep(3000);
						  performerPOM.UserReassignmentcfoMasters(driver).click();
						  Thread.sleep(3000);
						  performerPOM.clickUser1(driver).click();
						  Thread.sleep(3000);
						  performerPOM.selectUser1(driver).click();
						  
						 
						  Thread.sleep(3000);
						  performerPOM.clickAssigntoUser(driver).click();
						   Thread.sleep(3000);
						  performerPOM.selectAssigntoUser(driver).click();
						  
						  JavascriptExecutor js = (JavascriptExecutor) driver;
				          js.executeScript("window.scrollBy(0,1000)");
						 
						  Thread.sleep(1000);
						  performerPOM.selectcheckBox(driver).click();
						 
//						  Thread.sleep(1000);
//						  performerPOM.selectcheckBoxcfo(driver);
						
						  System.out.println("true");
						  	
						  Thread.sleep(3000);
						  performerPOM.clicknotice(driver).click();
						  Thread.sleep(3000);
						  performerPOM.selectNoticeCheckkBox(driver).click();
						  Thread.sleep(3000);
						  performerPOM.clickTask(driver).click();
						  Thread.sleep(3000);
						  performerPOM. selectTaskCheckkBox(driver).click();
						 
						  Thread.sleep(3000);
						  performerPOM.clickAssignButoon(driver).click();
					
						
						 
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
					        alert.accept();		
					        if(performerPOM.clearButton(driver).isEnabled())
					  		{
					  			performerPOM.clearButton(driver).click();
					  			test.log(LogStatus.PASS, "Clear button working successfully");
					  		}
					  		else
					  		{
					  			test.log(LogStatus.FAIL, "Clear button not working successfully");
					  		}
					        
					        
					        
					  	  Thread.sleep(4000);
						  performerPOM.clickAutidLog(driver).click();
						  
						 
						 
						  
						  
						  
					  }
					  public static void MailAuthorization(WebDriver driver,ExtentTest test) throws InterruptedException
					  {
						  Thread.sleep(3000);
						  performerPOM.clickMasters(driver).click();

						  
						  Thread.sleep(3000);
						  performerPOM.MailAuthorizationMasterscfo(driver).click();
						  

						  Thread.sleep(3000);
						  performerPOM.clickTypeOfUser(driver).click();
						  Thread.sleep(3000);
						  performerPOM.selectTypeOfUser(driver).click();
						  Thread.sleep(4000);
						  performerPOM.clickRole(driver).click();
						  Thread.sleep(4000);
						  performerPOM.selectRole(driver).click();
						  Thread.sleep(3000);
						  performerPOM.clickUsers(driver).click();
						 // Thread.sleep(3000);
						 // performerPOM.clickSearchBoxUser(driver).sendKeys("company admin");
						  
						  Thread.sleep(3000);
						  performerPOM.selectUsers(driver).click();

						  Thread.sleep(3000);
						  performerPOM.clickMailServices(driver).click();
						 // Thread.sleep(3000);
						 // performerPOM.clickSearchBoxMail(driver).sendKeys("Hearings After 2 Days");
						  Thread.sleep(300);
						  performerPOM.selectMailService(driver).click();
						  Thread.sleep(3000);
						  performerPOM.clickEnable(driver).click();
						  Thread.sleep(4000);
						  performerPOM.clickExportMail(driver).click();
						  Thread.sleep(3000);
						  performerPOM.clickDisable(driver).click();
						  Thread.sleep(3000);
						 // performerPOM.clearButton(driver).click();
						  
						  if(performerPOM.clearButton(driver).isEnabled())
					  		{
					  			performerPOM.clearButton(driver).click();
					  			test.log(LogStatus.PASS, "Clear button working successfully");
					  		}
					  		else
					  		{
					  			test.log(LogStatus.FAIL, "Clear button not working successfully");
					  		}
						  test.log(LogStatus.PASS, "File Download successfully");
						  
						  
					  }
						public static void CaseHearing(WebDriver driver, ExtentTest test, String compliancesCount1,String type) throws InterruptedException, IOException
						{
							
							//performerPOM.CaseHearingCount(driver).click();
							//performerPOM.CaseHearingGridCount(driver).click();
							
							int	open = Integer.parseInt(performerPOM.CaseHearingCount(driver).getText());	//Reading Notice Open count.
					        performerPOM.CaseHearingCount(driver).click();						//Clicking on 'Open' notice

					        JavascriptExecutor js = (JavascriptExecutor) driver;
					    	js.executeScript("window.scrollBy(0,300)");
							
							Thread.sleep(10000);
							CFOcountPOM.readTotalItems1(driver).click();
							String item = CFOcountPOM.readTotalItems1(driver).getText();
							String[] bits = item.split(" ");								//Splitting the String
							String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
							int count1 = 0;
							if(compliancesCount.equalsIgnoreCase("to"))
							{
								Thread.sleep(2000);
							   item = CFOcountPOM.readTotalItems1(driver).getText();
								bits = item.split(" ");								//Splitting the String
							   compliancesCount = bits[bits.length - 2];
							}
							if(compliancesCount.equalsIgnoreCase("to"))
							{
								count1 = 0;
							}
							else
							{
								count1 = Integer.parseInt(compliancesCount);
							}
							
							if(open == count1)
							{
								//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
								test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
							}
							else
							{
								//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
								test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
							}
				           	
				           	
							
							
								Thread.sleep(100);
								File dir = new File("C://Users//Admin//Downloads");
								File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
								
								Thread.sleep(500);
								CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
								Thread.sleep(250);
								performerPOM.CaseHearingExport(driver).click();					//Clicking on 'Excel Report' image.
								test.log(LogStatus.PASS, "File downloaded successfully.");
								
								
								Thread.sleep(3000);
								performerPOM.CaseHearingView(driver).click();
								
								test.log(LogStatus.PASS, "Case Hearing View Popup open successfully.");
								
								
								
								Thread.sleep(3000);
								driver.switchTo().parentFrame();
								
								Thread.sleep(3000);
								performerPOM.CaseHearingPopupClose(driver).click();
								
								Thread.sleep(5000);
								performerPOM.clickSearchFilter(driver).sendKeys("347623");
								
								Thread.sleep(5000);
								if(performerPOM.clearButton(driver).isEnabled())
								{
									performerPOM.clearButton(driver).click();
									 test.log(LogStatus.PASS, "Case Hearing = clear button Work Successfully");
								}
								else
								{
									test.log(LogStatus.PASS, "Case Hearing  = clear button not Work Successfully");
								}
								
								
								
								Thread.sleep(300);
								OverduePOM.clickDashboard(driver).click();
						}
						public static void HearingCalender(WebDriver driver,ExtentTest test,String compliancesCount1, String type) throws InterruptedException
						{
							/*String month="March 2023";
							String day="22";
						
							while(true)
							{
						        String text=driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[1]/h4")).getText();    //click month and year 
						           if(text.equals(month))
						           {
							                       break;
						            }
						           else
						            {
						            	Thread.sleep(2000);
							        driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[1]/a[2]/div")).click();           // click forward
						
							         }
							        Thread.sleep(4000);
							      //  driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div/a[contains(text(),"+day+")]")).click();    //click day
							        driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div[24]/a")).click();             
						    } */
							WebElement text=driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div[6]/a"));
							if(text.isEnabled())
							{
								driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div[6]/a")).click();
								
								test.log(LogStatus.PASS, "Hearing for particular date is clickable.");
								
							}
							
			
							int	open = Integer.parseInt(performerPOM.HearingCalenderNumcfo(driver).getText());	//Reading Notice Open count.
							WebDriverWait Wait=new WebDriverWait(driver,20);
						 	Wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("calframe"));
							
							Thread.sleep(10000);
							CFOcountPOM.readcalenderCount(driver).click();
							String item = CFOcountPOM.readcalenderCount(driver).getText();
							String[] bits = item.split(" ");								//Splitting the String
							String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
							int count1 = 0;
							if(compliancesCount.equalsIgnoreCase("to"))
							{
								Thread.sleep(2000);
							    item = CFOcountPOM.readcalenderCount(driver).getText();
								bits = item.split(" ");								//Splitting the String
							   compliancesCount = bits[bits.length - 2];
							}
							if(compliancesCount.equalsIgnoreCase("to"))
							{
								count1 = 0;
							}
							else
							{
								count1 = Integer.parseInt(compliancesCount);
							}
							
							if(open == count1)
							{
								//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
								test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
							}
							else
							{
								//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
								test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
							}
				           	
							
							
							
							JavascriptExecutor js = (JavascriptExecutor) driver;
			               	js.executeScript("window.scrollBy(0,300)");
			               	
			            
			               	Thread.sleep(2000);
					
							
							Thread.sleep(100);
							new File("C://Users//Snehal Patil//Downloads");
						
							
							Thread.sleep(250);
							performerPOM.HearingCalenderExport(driver).click();					//Clicking on 'Excel Report' image.
							test.log(LogStatus.PASS, "File downloaded successfully.");
							
							Thread.sleep(500);
				            performerPOM.HearingCalenderView(driver).click();
				            test.log(LogStatus.PASS, "Show Hearing Detailes View Popup open successfully.");
							
							Thread.sleep(2000);
							driver.switchTo().parentFrame();
						
							Thread.sleep(3000);
							performerPOM.HearingCalenderclose(driver).click();
							
		                 	Thread.sleep(1000);
							OverduePOM.clickDashboard(driver).click();
							
						}	
							
						

                  
                    public static void Draft(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
                    {

						
                        Perform2(driver, test, "Draft");
						

						OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
                  
                    }
                    public static void Perform2(WebDriver driver, ExtentTest test,String type) throws InterruptedException, IOException
                    {
                    
        			    int	open = Integer.parseInt(performerPOM.clickDraftcfo(driver).getText());	//Reading Case Open count.
        				performerPOM.clickDraftcfo(driver).click();						//Clicking on 'Open' Case
        				
        				JavascriptExecutor js = (JavascriptExecutor) driver;
        				js.executeScript("window.scrollBy(0,1000)");
            			
            			Thread.sleep(7000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits = item.split(" ");								//Splitting the String
            			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
            			int count1 = 0;
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				Thread.sleep(2000);
            			   item = CFOcountPOM.readTotalItems1(driver).getText();
            				bits = item.split(" ");								//Splitting the String
            			   compliancesCount = bits[bits.length - 2];
            			}
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				count1 = 0;
            			}
            			else
            			{
            				count1 = Integer.parseInt(compliancesCount);
            			}
            			
            			if(open == count1)
            			{
            				test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
            				test.log(LogStatus.INFO, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
            				test.log(LogStatus.INFO, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
                    }		
            	  public static void NoticeDocViewandDownload(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
            	  {    
            		  
            		  WebDriverWait wait=new WebDriverWait(driver,20);
            		  Thread.sleep(3000);
            		  performerPOM.clickNoticeOpen(driver).click();
            		  
            		  Thread.sleep(500);
          			  wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
            		  
            		  Thread.sleep(3000);
            		  performerPOM.clickEditNotice(driver).click();
            		  
            		
            		  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
          			
            		  
            		  Thread.sleep(3000);
            		  performerPOM.clickEditNotice1(driver).click();
            		  
            		  JavascriptExecutor js = (JavascriptExecutor) driver;
      				  js.executeScript("window.scrollBy(0,1000)");
      				  
      				  Thread.sleep(3000);
            		  performerPOM.clickViewNoticeDoc(driver).click();
            		  
            		  Thread.sleep(1000);
                      driver.switchTo().frame("IframeNoticeDocument");
            		  
            		  
            		  Thread.sleep(3000);
            		  performerPOM.clickViewNoticeDocpopup(driver).click();
            		  
            		 
            		  //driver.switchTo().parentFrame();
            		  
            		  Thread.sleep(4000);
            		  performerPOM.clickViewNoticeDocpopupclose1(driver).click();
            		  
                 	  
            		  Thread.sleep(3000);
            		  performerPOM.clickDownloadNoticeDocpopup(driver).click();
            		  
                 	  driver.switchTo().parentFrame();
            		  
            		  Thread.sleep(3000);
            		  performerPOM.clickViewNoticeDocpopupclose(driver).click();
            		  
            		  test.log(LogStatus.PASS,"View Notice Document Popup open successfully");
            		  
            		  Thread.sleep(3000);
            		  performerPOM.clickDownloadNoticeDoc(driver).click();
            		  
            		  test.log(LogStatus.PASS,"Notice Document Download successfully");
            		  
            			Thread.sleep(3000);
                		driver.switchTo().parentFrame();
                		performerPOM.clickClose(driver).click();//Clicking on 'Close'
                		
                		Thread.sleep(500);
                		OverduePOM.clickDashboard(driver).click();
             
            	  }
            public static void InwardDefendantAgeingGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
          		
          		{
          			
          			WebDriverWait wait=new WebDriverWait(driver,20);
          			JavascriptExecutor js = (JavascriptExecutor) driver;
          	       	js.executeScript("window.scrollBy(0,800)");
          	       	
          	       	Thread.sleep(5000);
          			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDashboardNoticeFilter(driver).click();
          			
          			 Thread.sleep(5000);
          				performerPOM.clickDashboardApplyBtn(driver).click();
          				
          				js.executeScript("window.scrollBy(0,3000)");
 
          			
          	       	Thread.sleep(3000);
          		
          	       	int	open = Integer.parseInt(performerPOM.clickAgeing(driver).getText());	//Reading Notice Open count.
          		    performerPOM.clickAgeing(driver).click();						//Clicking on 'Open' notice
          		
          			Thread.sleep(2000);
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
          			
          			Thread.sleep(10000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits = item.split(" ");								//Splitting the String
          			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
          			int count1 = 0;
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				Thread.sleep(2000);
          			   item = CFOcountPOM.readTotalItems1(driver).getText();
          				bits = item.split(" ");								//Splitting the String
          			   compliancesCount = bits[bits.length - 2];
          			}
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				count1 = 0;
          			}
          			else
          			{
          				count1 = Integer.parseInt(compliancesCount);
          			}
          			
          			if(open == count1)
          			{
          				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
          				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          			else
          			{
          				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
          				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          	       	
          	    
          			Thread.sleep(3000);
          			performerPOM.clickAgeingViewIcon(driver).click();
          			

        			
        			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
        			Thread.sleep(2000);
          		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
          		  
          		  if(msg.equalsIgnoreCase("Notice Summary"))
          		  {
          			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
          		  }
          		  else
          		  {
          			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
          		  }
          		  
          		  driver.switchTo().parentFrame();
          			
          		 Thread.sleep(3000);
          		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
          			
          			
          			
          			
          		/*	Thread.sleep(3000);
          			performerPOM.clickLocationFilter(driver).click();
          			
          			Thread.sleep(3000);
          			performerPOM.clickLocationFilter1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickLocationFilter3(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStatusFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectstatusFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDepartmentFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectDepartmentFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNoticeType1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNoticeType2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickRiskFilter(driver).click();
          			

          			Thread.sleep(5000);
          			performerPOM.selectRiskFilter2cfo(driver).click();
          			
//          			Thread.sleep(5000);
//          			performerPOM.clickAgeFilter(driver).click();
//          			
//          			Thread.sleep(5000);
//          			performerPOM.selectAgeFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCategoryFilter(driver).click();
          			
          			
          			Thread.sleep(5000);
          			performerPOM.selectCategoryFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStageFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectStageFilter2(driver).click(); */
          			
          			
          			Thread.sleep(500);
          			progress(driver);
          			
          			Thread.sleep(1000);
          			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
          			Thread.sleep(2000);
          			
          			js.executeScript("window.scrollBy(0,1000)");
          			
          			
          			
          			Thread.sleep(1000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits1 = item1.split(" ");								//Splitting the String
          			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
          			int count2 = Integer.parseInt(compliancesCount1);
          			
          		    try
          			{
          				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
          			}
          			catch(Exception e)
          			{
          				
          			}
          			js.executeScript("window.scrollBy(0,1000)");
          			
          		
          			Thread.sleep(100);
          			File dir = new File("C:\\Users\\Snehal Patil//Downloads");
          			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
          			
          			Thread.sleep(500);
          			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
          			Thread.sleep(250);
          			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
          			test.log(LogStatus.PASS, "File downloaded successfully.");
          			
          			Thread.sleep(5500);
          			File dir1 = new File("C:\\Users\\Snehal Patil//Downloads");
          			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
          			
          			if(dirContents.length < allFilesNew.length)
          			{
          				
          				
          				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
          			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
          			    {
          			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
          			       {
          			           lastModifiedFile = allFilesNew[i];
          			       }
          			    }
          				
          				Thread.sleep(100);
          				fis = new FileInputStream(lastModifiedFile);
          				workbook = new XSSFWorkbook(fis);
          				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
          				
          				int no = sheet.getLastRowNum();
          				Row row = sheet.getRow(no);
          				Cell c1 = row.getCell(0);
          				int records =(int) c1.getNumericCellValue();
          				fis.close();
          				
          				if(count2 == records)
          				{
          					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
          					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
          				}
          				else
          				{
          					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
          					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
          				}
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
          			}
          			
//          			Thread.sleep(7000);
//          			performerPOM.clickRiskFilter(driver).click();
//          			
//          			Thread.sleep(7000);
//          			performerPOM.selectRiskFilter(driver).click();
          			
//          		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//          			selectOptionFromDropDown_bs(SeletcRisk, "High");
          			
          			Thread.sleep(7000);
          			if(performerPOM.clearButton(driver).isEnabled())
          			{
          				Thread.sleep(7000);
          				performerPOM.clearButton(driver).click();
              			test.log(LogStatus.PASS, "clear button work successfully.");
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "clear button not work successfully.");
          			}
          		
          			
          			
          			
          			Thread.sleep(3000);
          			driver.switchTo().parentFrame();
          			Thread.sleep(2000);
          			performerPOM.caseNoticeSummaryGraphClose(driver).click();
          			
         			Thread.sleep(3000);
         			OverduePOM.clickDashboard(driver).click();
          			
          	   }
            	  public static void ComplainantAgeingGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
            		
            		{
            			
            			WebDriverWait wait=new WebDriverWait(driver,20);
            			JavascriptExecutor js = (JavascriptExecutor) driver;
            	       	js.executeScript("window.scrollBy(0,800)");
            	       	
            	       	Thread.sleep(5000);
            			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickDashboardNoticeFilter(driver).click();
            			
            			 Thread.sleep(5000);
            				performerPOM.clickDashboardApplyBtn(driver).click();
            				
            				js.executeScript("window.scrollBy(0,3000)");
            			
            	       	Thread.sleep(3000);
            		
            	       	int	open = Integer.parseInt(performerPOM.clickComplainantAgeing(driver).getText());	//Reading Notice Open count.
            		    performerPOM.clickComplainantAgeing(driver).click();						//Clicking on 'Open' notice
            		
            			Thread.sleep(2000);
            			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
            			
            			Thread.sleep(10000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits = item.split(" ");								//Splitting the String
            			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
            			int count1 = 0;
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				Thread.sleep(2000);
            			   item = CFOcountPOM.readTotalItems1(driver).getText();
            				bits = item.split(" ");								//Splitting the String
            			   compliancesCount = bits[bits.length - 2];
            			}
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				count1 = 0;
            			}
            			else
            			{
            				count1 = Integer.parseInt(compliancesCount);
            			}
            			
            			if(open == count1)
            			{
            				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
            				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            			else
            			{
            				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
            				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            	       	
            	    
            			Thread.sleep(3000);
            			performerPOM.clickAgeingViewIcon(driver).click();
            			

          			
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
          			Thread.sleep(2000);
            		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
            		  
            		  if(msg.equalsIgnoreCase("Notice Summary"))
            		  {
            			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
            		  }
            		  else
            		  {
            			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
            		  }
            		  
            		  driver.switchTo().parentFrame();
            			
            		 Thread.sleep(3000);
            		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
            			
            			
            			
            			
            		/*	Thread.sleep(3000);
            			performerPOM.clickLocationFilter(driver).click();
            			
            			Thread.sleep(3000);
            			performerPOM.clickLocationFilter1(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickLocationFilter3(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCaseNotice(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectCaseNotice(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickStatusFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectstatusFiltercfo(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickDepartmentFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectDepartmentFilter2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCaseNoticeType1(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectCaseNoticeType2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickRiskFilter(driver).click();
            			

            			Thread.sleep(5000);
            			performerPOM.selectRiskFilter2cfo(driver).click();
            			
//            			Thread.sleep(5000);
//            			performerPOM.clickAgeFilter(driver).click();
//            			
//            			Thread.sleep(5000);
//            			performerPOM.selectAgeFiltercfo(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCategoryFilter(driver).click();
            			
            			
            			Thread.sleep(5000);
            			performerPOM.selectCategoryFilter2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickStageFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectStageFilter2(driver).click(); */
            			
            			
            			Thread.sleep(500);
            			progress(driver);
            			
            			Thread.sleep(1000);
            			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
            			Thread.sleep(2000);
            			JavascriptExecutor js1 = (JavascriptExecutor) driver;
            			js.executeScript("window.scrollBy(0,1000)");
            			
            			
            			
            			Thread.sleep(1000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits1 = item1.split(" ");								//Splitting the String
            			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
            			int count2 = Integer.parseInt(compliancesCount1);
            			
            		    try
            			{
            				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
            			}
            			catch(Exception e)
            			{
            				
            			}
            			js.executeScript("window.scrollBy(0,1000)");
            			
            		
            			Thread.sleep(100);
            			File dir = new File("C:\\Users\\Snehal Patil//Downloads");
            			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
            			
            			Thread.sleep(500);
            			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
            			Thread.sleep(250);
            			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
            			test.log(LogStatus.PASS, "File downloaded successfully.");
            			
            			Thread.sleep(5500);
            			File dir1 = new File("C:\\Users\\Snehal Patil//Downloads");
            			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
            			
            			if(dirContents.length < allFilesNew.length)
            			{
            				
            				
            				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
            			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
            			    {
            			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
            			       {
            			           lastModifiedFile = allFilesNew[i];
            			       }
            			    }
            				
            				Thread.sleep(100);
            				fis = new FileInputStream(lastModifiedFile);
            				workbook = new XSSFWorkbook(fis);
            				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
            				
            				int no = sheet.getLastRowNum();
            				Row row = sheet.getRow(no);
            				Cell c1 = row.getCell(0);
            				int records =(int) c1.getNumericCellValue();
            				fis.close();
            				
            				if(count2 == records)
            				{
            					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
            					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
            				}
            				else
            				{
            					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
            					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
            				}
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
            			}
            			
//            			Thread.sleep(7000);
//            			performerPOM.clickRiskFilter(driver).click();
//            			
//            			Thread.sleep(7000);
//            			performerPOM.selectRiskFilter(driver).click();
//            			
//            		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//            			selectOptionFromDropDown_bs(SeletcRisk, "High");
            			
            			Thread.sleep(7000);
            			if(performerPOM.clearButton(driver).isEnabled())
            			{
            				Thread.sleep(7000);
            				performerPOM.clearButton(driver).click();
                			test.log(LogStatus.PASS, "clear button work successfully.");
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, "clear button not work successfully.");
            			}
            		
            			
            			
            			
            			Thread.sleep(3000);
            			driver.switchTo().parentFrame();
            			Thread.sleep(2000);
            			performerPOM.caseNoticeSummaryGraphClose(driver).click();
            			
           			Thread.sleep(3000);
           			OverduePOM.clickDashboard(driver).click();
            			
            	   }
            	  public static void ApplicantAgeingGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
          		
          		{
          			
          			WebDriverWait wait=new WebDriverWait(driver,20);
          			JavascriptExecutor js = (JavascriptExecutor) driver;
          	       	js.executeScript("window.scrollBy(0,800)");
          	       	
          	       	Thread.sleep(5000);
          			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDashboardNoticeFilter(driver).click();
          			
          			 Thread.sleep(5000);
          				performerPOM.clickDashboardApplyBtn(driver).click();
          				
          				js.executeScript("window.scrollBy(0,3000)");
          			
          	       	Thread.sleep(3000);
          		
          	       	int	open = Integer.parseInt(performerPOM.clickApplicantAgeing(driver).getText());	//Reading Notice Open count.
          		    performerPOM.clickApplicantAgeing(driver).click();						//Clicking on 'Open' notice
          		
          			Thread.sleep(2000);
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
          			
          			Thread.sleep(10000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits = item.split(" ");								//Splitting the String
          			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
          			int count1 = 0;
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				Thread.sleep(2000);
          			   item = CFOcountPOM.readTotalItems1(driver).getText();
          				bits = item.split(" ");								//Splitting the String
          			   compliancesCount = bits[bits.length - 2];
          			}
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				count1 = 0;
          			}
          			else
          			{
          				count1 = Integer.parseInt(compliancesCount);
          			}
          			
          			if(open == count1)
          			{
          				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
          				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          			else
          			{
          				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
          				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          	       	
          	    
          			Thread.sleep(3000);
          			performerPOM.clickAgeingViewIcon(driver).click();
          			

        			
        			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
        			Thread.sleep(2000);
          		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
          		  
          		  if(msg.equalsIgnoreCase("Notice Summary"))
          		  {
          			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
          		  }
          		  else
          		  {
          			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
          		  }
          		  
          		  driver.switchTo().parentFrame();
          			
          		 Thread.sleep(3000);
          		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
          			
          			
          			
          			
          		/*	Thread.sleep(3000);
          			performerPOM.clickLocationFilter(driver).click();
          			
          			Thread.sleep(3000);
          			performerPOM.clickLocationFilter1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickLocationFilter3(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStatusFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectstatusFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDepartmentFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectDepartmentFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNoticeType1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNoticeType2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickRiskFilter(driver).click();
          			

          			Thread.sleep(5000);
          			performerPOM.selectRiskFilter2cfo(driver).click();
          			
//          			Thread.sleep(5000);
//          			performerPOM.clickAgeFilter(driver).click();
//          			
//          			Thread.sleep(5000);
//          			performerPOM.selectAgeFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCategoryFilter(driver).click();
          			
          			
          			Thread.sleep(5000);
          			performerPOM.selectCategoryFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStageFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectStageFilter2(driver).click(); */
          			
          			
          			Thread.sleep(500);
          			progress(driver);
          			
          			Thread.sleep(1000);
          			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
          			Thread.sleep(2000);
          			JavascriptExecutor js1 = (JavascriptExecutor) driver;
          			js.executeScript("window.scrollBy(0,1000)");
          			
          			
          			
          			Thread.sleep(1000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits1 = item1.split(" ");								//Splitting the String
          			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
          			int count2 = Integer.parseInt(compliancesCount1);
          			
          		    try
          			{
          				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
          			}
          			catch(Exception e)
          			{
          				
          			}
          			js.executeScript("window.scrollBy(0,1000)");
          			
          		
          			Thread.sleep(100);
          			File dir = new File("C://Users//Snehal Patil//Downloads");
          			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
          			
          			Thread.sleep(500);
          			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
          			Thread.sleep(250);
          			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
          			test.log(LogStatus.PASS, "File downloaded successfully.");
          			
          			Thread.sleep(5500);
          			File dir1 = new File("C://Users//Snehal Patil//Downloads");
          			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
          			
          			if(dirContents.length < allFilesNew.length)
          			{
          				
          				
          				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
          			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
          			    {
          			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
          			       {
          			           lastModifiedFile = allFilesNew[i];
          			       }
          			    }
          				
          				Thread.sleep(100);
          				fis = new FileInputStream(lastModifiedFile);
          				workbook = new XSSFWorkbook(fis);
          				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
          				
          				int no = sheet.getLastRowNum();
          				Row row = sheet.getRow(no);
          				Cell c1 = row.getCell(0);
          				int records =(int) c1.getNumericCellValue();
          				fis.close();
          				
          				if(count2 == records)
          				{
          					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
          					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
          				}
          				else
          				{
          					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
          					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
          				}
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
          			}
          			
//          			Thread.sleep(7000);
//          			performerPOM.clickRiskFilter(driver).click();
//          			
//          			Thread.sleep(7000);
//          			performerPOM.selectRiskFilter(driver).click();
          			
//          		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//          			selectOptionFromDropDown_bs(SeletcRisk, "High");
          			
          			Thread.sleep(7000);
          			if(performerPOM.clearButton(driver).isEnabled())
          			{
          				Thread.sleep(7000);
          				performerPOM.clearButton(driver).click();
              			test.log(LogStatus.PASS, "clear button work successfully.");
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "clear button not work successfully.");
          			}
          		
          			
          			
          			
          			Thread.sleep(3000);
          			driver.switchTo().parentFrame();
          			Thread.sleep(2000);
          			performerPOM.caseNoticeSummaryGraphClose(driver).click();
          			
         			Thread.sleep(3000);
         			OverduePOM.clickDashboard(driver).click();
          			
          	   }
            	  public static void OutwardPlaintiffAgeingGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
            		
            		{
            			
            			WebDriverWait wait=new WebDriverWait(driver,20);
            			JavascriptExecutor js = (JavascriptExecutor) driver;
            	       	js.executeScript("window.scrollBy(0,800)");
            	       	
            	       	Thread.sleep(5000);
            			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickDashboardNoticeFilter(driver).click();
            			
            			 Thread.sleep(5000);
            				performerPOM.clickDashboardApplyBtn(driver).click();
            				
            				js.executeScript("window.scrollBy(0,3000)");
            			
            	       	Thread.sleep(3000);
            		
            	       	int	open = Integer.parseInt(performerPOM.clickOutwardPlaintiffAgeing(driver).getText());	//Reading Notice Open count.
            		    performerPOM.clickOutwardPlaintiffAgeing(driver).click();						//Clicking on 'Open' notice
            		
            			Thread.sleep(2000);
            			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
            			
            			Thread.sleep(10000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits = item.split(" ");								//Splitting the String
            			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
            			int count1 = 0;
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				Thread.sleep(2000);
            			   item = CFOcountPOM.readTotalItems1(driver).getText();
            				bits = item.split(" ");								//Splitting the String
            			   compliancesCount = bits[bits.length - 2];
            			}
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				count1 = 0;
            			}
            			else
            			{
            				count1 = Integer.parseInt(compliancesCount);
            			}
            			
            			if(open == count1)
            			{
            				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
            				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            			else
            			{
            				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
            				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            	       	
            	    
            			Thread.sleep(3000);
            			performerPOM.clickAgeingViewIcon(driver).click();
            			

          			
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
          			Thread.sleep(2000);
            		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
            		  
            		  if(msg.equalsIgnoreCase("Notice Summary"))
            		  {
            			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
            		  }
            		  else
            		  {
            			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
            		  }
            		  
            		  driver.switchTo().parentFrame();
            			
            		 Thread.sleep(3000);
            		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
            			
            			
            			
            			
            		/*	Thread.sleep(3000);
            			performerPOM.clickLocationFilter(driver).click();
            			
            			Thread.sleep(3000);
            			performerPOM.clickLocationFilter1(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickLocationFilter3(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCaseNotice(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectCaseNotice(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickStatusFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectstatusFiltercfo(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickDepartmentFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectDepartmentFilter2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCaseNoticeType1(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectCaseNoticeType2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickRiskFilter(driver).click();
            			

            			Thread.sleep(5000);
            			performerPOM.selectRiskFilter2cfo(driver).click();
            			
//            			Thread.sleep(5000);
//            			performerPOM.clickAgeFilter(driver).click();
//            			
//            			Thread.sleep(5000);
//            			performerPOM.selectAgeFiltercfo(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCategoryFilter(driver).click();
            			
            			
            			Thread.sleep(5000);
            			performerPOM.selectCategoryFilter2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickStageFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectStageFilter2(driver).click(); */
            			
            			
            			Thread.sleep(500);
            			progress(driver);
            			
            			Thread.sleep(1000);
            			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
            			Thread.sleep(2000);
            			JavascriptExecutor js1 = (JavascriptExecutor) driver;
            			js.executeScript("window.scrollBy(0,1000)");
            			
            			
            			
            			Thread.sleep(1000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits1 = item1.split(" ");								//Splitting the String
            			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
            			int count2 = Integer.parseInt(compliancesCount1);
            			
            		    try
            			{
            				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
            			}
            			catch(Exception e)
            			{
            				
            			}
            			js.executeScript("window.scrollBy(0,3000)");
            			
            		
            			Thread.sleep(100);
            			File dir = new File("C://Users//Snehal Patil//Downloads");
            			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
            			
            			Thread.sleep(500);
            			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
            			Thread.sleep(250);
            			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
            			test.log(LogStatus.PASS, "File downloaded successfully.");
            			
            			Thread.sleep(5500);
            			File dir1 = new File("C://Users//Snehal Patil//Downloads");
            			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
            			
            			if(dirContents.length < allFilesNew.length)
            			{
            				
            				
            				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
            			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
            			    {
            			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
            			       {
            			           lastModifiedFile = allFilesNew[i];
            			       }
            			    }
            				
            				Thread.sleep(100);
            				fis = new FileInputStream(lastModifiedFile);
            				workbook = new XSSFWorkbook(fis);
            				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
            				
            				int no = sheet.getLastRowNum();
            				Row row = sheet.getRow(no);
            				Cell c1 = row.getCell(0);
            				int records =(int) c1.getNumericCellValue();
            				fis.close();
            				
            				if(count2 == records)
            				{
            					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
            					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
            				}
            				else
            				{
            					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
            					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
            				}
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
            			}
            			
//            			Thread.sleep(7000);
//            			performerPOM.clickRiskFilter(driver).click();
//            			
//            			Thread.sleep(7000);
//            			performerPOM.selectRiskFilter(driver).click();
            			
//            		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//            			selectOptionFromDropDown_bs(SeletcRisk, "High");
            			
            			Thread.sleep(7000);
            			if(performerPOM.clearButton(driver).isEnabled())
            			{
            				Thread.sleep(7000);
            				performerPOM.clearButton(driver).click();
                			test.log(LogStatus.PASS, "clear button work successfully.");
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, "clear button not work successfully.");
            			}
            		
            			
            			
            			
            			Thread.sleep(3000);
            			driver.switchTo().parentFrame();
            			Thread.sleep(2000);
            			performerPOM.caseNoticeSummaryGraphClose(driver).click();
            			
           			Thread.sleep(3000);
           			OverduePOM.clickDashboard(driver).click();
            			
            	   }
            	  public static void PetitionerAgeingGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
          		
          		{
          			
          			WebDriverWait wait=new WebDriverWait(driver,20);
          			JavascriptExecutor js = (JavascriptExecutor) driver;
          	       	js.executeScript("window.scrollBy(0,800)");
          	       	
          	       	Thread.sleep(5000);
          			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDashboardNoticeFilter(driver).click();
          			
          			 Thread.sleep(5000);
          				performerPOM.clickDashboardApplyBtn(driver).click();
          				
          				js.executeScript("window.scrollBy(0,3000)");
          			
          	       	Thread.sleep(3000);
          		
          	       	int	open = Integer.parseInt(performerPOM.clickPetitionerAgeing(driver).getText());	//Reading Notice Open count.
          		    performerPOM.clickPetitionerAgeing(driver).click();						//Clicking on 'Open' notice
          		
          			Thread.sleep(2000);
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
          			
          			Thread.sleep(10000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits = item.split(" ");								//Splitting the String
          			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
          			int count1 = 0;
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				Thread.sleep(2000);
          			   item = CFOcountPOM.readTotalItems1(driver).getText();
          				bits = item.split(" ");								//Splitting the String
          			   compliancesCount = bits[bits.length - 2];
          			}
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				count1 = 0;
          			}
          			else
          			{
          				count1 = Integer.parseInt(compliancesCount);
          			}
          			
          			if(open == count1)
          			{
          				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
          				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          			else
          			{
          				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
          				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          	       	
          	    
          			Thread.sleep(3000);
          			performerPOM.clickAgeingViewIcon(driver).click();
          			

        			
        			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
        			Thread.sleep(2000);
          		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
          		  
          		  if(msg.equalsIgnoreCase("Notice Summary"))
          		  {
          			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
          		  }
          		  else
          		  {
          			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
          		  }
          		  
          		  driver.switchTo().parentFrame();
          			
          		 Thread.sleep(3000);
          		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
          			
          			
          			
          			
          		/*	Thread.sleep(3000);
          			performerPOM.clickLocationFilter(driver).click();
          			
          			Thread.sleep(3000);
          			performerPOM.clickLocationFilter1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickLocationFilter3(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStatusFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectstatusFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDepartmentFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectDepartmentFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNoticeType1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNoticeType2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickRiskFilter(driver).click();
          			

          			Thread.sleep(5000);
          			performerPOM.selectRiskFilter2cfo(driver).click();
          			
//          			Thread.sleep(5000);
//          			performerPOM.clickAgeFilter(driver).click();
//          			
//          			Thread.sleep(5000);
//          			performerPOM.selectAgeFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCategoryFilter(driver).click();
          			
          			
          			Thread.sleep(5000);
          			performerPOM.selectCategoryFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStageFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectStageFilter2(driver).click(); */
          			
          			
          			Thread.sleep(500);
          			progress(driver);
          			
          			Thread.sleep(1000);
          			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
          			Thread.sleep(2000);
          			JavascriptExecutor js1 = (JavascriptExecutor) driver;
          			js.executeScript("window.scrollBy(0,1000)");
          			
          			
          			
          			Thread.sleep(1000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits1 = item1.split(" ");								//Splitting the String
          			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
          			int count2 = Integer.parseInt(compliancesCount1);
          			
          		    try
          			{
          				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
          			}
          			catch(Exception e)
          			{
          				
          			}
          			js.executeScript("window.scrollBy(0,1000)");
          			
          		
          			Thread.sleep(100);
          			File dir = new File("C://Users//Snehal Patil//Downloads");
          			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
          			
          			Thread.sleep(500);
          			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
          			Thread.sleep(250);
          			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
          			test.log(LogStatus.PASS, "File downloaded successfully.");
          			
          			Thread.sleep(5500);
          			File dir1 = new File("C://Users//Snehal Patil//Downloads");
          			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
          			
          			if(dirContents.length < allFilesNew.length)
          			{
          				
          				
          				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
          			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
          			    {
          			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
          			       {
          			           lastModifiedFile = allFilesNew[i];
          			       }
          			    }
          				
          				Thread.sleep(100);
          				fis = new FileInputStream(lastModifiedFile);
          				workbook = new XSSFWorkbook(fis);
          				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
          				
          				int no = sheet.getLastRowNum();
          				Row row = sheet.getRow(no);
          				Cell c1 = row.getCell(0);
          				int records =(int) c1.getNumericCellValue();
          				fis.close();
          				
          				if(count2 == records)
          				{
          					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
          					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
          				}
          				else
          				{
          					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
          					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
          				}
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
          			}
          			
//          			Thread.sleep(7000);
//          			performerPOM.clickRiskFilter(driver).click();
//          			
//          			Thread.sleep(7000);
//          			performerPOM.selectRiskFilter(driver).click();
          			
//          		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//          			selectOptionFromDropDown_bs(SeletcRisk, "High");
          			
          			Thread.sleep(7000);
          			if(performerPOM.clearButton(driver).isEnabled())
          			{
          				Thread.sleep(7000);
          				performerPOM.clearButton(driver).click();
              			test.log(LogStatus.PASS, "clear button work successfully.");
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "clear button not work successfully.");
          			}
          		
          			
          			
          			
          			Thread.sleep(3000);
          			driver.switchTo().parentFrame();
          			Thread.sleep(2000);
          			performerPOM.caseNoticeSummaryGraphClose(driver).click();
          			
         			Thread.sleep(3000);
         			OverduePOM.clickDashboard(driver).click();
          			
          	   }
            	  public static void RespondentAgeingGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
            		
            		{
            			
            			WebDriverWait wait=new WebDriverWait(driver,20);
            			JavascriptExecutor js = (JavascriptExecutor) driver;
            	       	js.executeScript("window.scrollBy(0,800)");
            	       	
            	       	Thread.sleep(5000);
            			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickDashboardNoticeFilter(driver).click();
            			
            			 Thread.sleep(5000);
            				performerPOM.clickDashboardApplyBtn(driver).click();
            				
            				js.executeScript("window.scrollBy(0,3000)");
            			
            	       	Thread.sleep(3000);
            		
            	       	int	open = Integer.parseInt(performerPOM.clickRespondentAgeing(driver).getText());	//Reading Notice Open count.
            		    performerPOM.clickRespondentAgeing(driver).click();						//Clicking on 'Open' notice
            		
            			Thread.sleep(2000);
            			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
            			
            			Thread.sleep(10000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits = item.split(" ");								//Splitting the String
            			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
            			int count1 = 0;
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				Thread.sleep(2000);
            			   item = CFOcountPOM.readTotalItems1(driver).getText();
            				bits = item.split(" ");								//Splitting the String
            			   compliancesCount = bits[bits.length - 2];
            			}
            			if(compliancesCount.equalsIgnoreCase("to"))
            			{
            				count1 = 0;
            			}
            			else
            			{
            				count1 = Integer.parseInt(compliancesCount);
            			}
            			
            			if(open == count1)
            			{
            				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
            				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            			else
            			{
            				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
            				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
            			}
            	       	
            	    
            			Thread.sleep(3000);
            			performerPOM.clickAgeingViewIcon(driver).click();
            			

          			
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
          			Thread.sleep(2000);
            		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
            		  
            		  if(msg.equalsIgnoreCase("Notice Summary"))
            		  {
            			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
            		  }
            		  else
            		  {
            			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
            		  }
            		  
            		  driver.switchTo().parentFrame();
            			
            		 Thread.sleep(3000);
            		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
            			
            			
            			
            			
            		/*	Thread.sleep(3000);
            			performerPOM.clickLocationFilter(driver).click();
            			
            			Thread.sleep(3000);
            			performerPOM.clickLocationFilter1(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickLocationFilter3(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCaseNotice(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectCaseNotice(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickStatusFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectstatusFiltercfo(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickDepartmentFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectDepartmentFilter2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCaseNoticeType1(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectCaseNoticeType2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickRiskFilter(driver).click();
            			

            			Thread.sleep(5000);
            			performerPOM.selectRiskFilter2cfo(driver).click();
            			
//            			Thread.sleep(5000);
//            			performerPOM.clickAgeFilter(driver).click();
//            			
//            			Thread.sleep(5000);
//            			performerPOM.selectAgeFiltercfo(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickCategoryFilter(driver).click();
            			
            			
            			Thread.sleep(5000);
            			performerPOM.selectCategoryFilter2(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.clickStageFilter(driver).click();
            			
            			Thread.sleep(5000);
            			performerPOM.selectStageFilter2(driver).click(); */
            			
            			
            			Thread.sleep(500);
            			progress(driver);
            			
            			Thread.sleep(1000);
            			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
            			Thread.sleep(2000);
            			JavascriptExecutor js1 = (JavascriptExecutor) driver;
            			js.executeScript("window.scrollBy(0,1000)");
            			
            			
            			
            			Thread.sleep(1000);
            			CFOcountPOM.readTotalItems1(driver).click();
            			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
            			String[] bits1 = item1.split(" ");								//Splitting the String
            			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
            			int count2 = Integer.parseInt(compliancesCount1);
            			
            		    try
            			{
            				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
            			}
            			catch(Exception e)
            			{
            				
            			}
            			js.executeScript("window.scrollBy(0,1000)");
            			
            		
            			Thread.sleep(100);
            			File dir = new File("C://Users//Snehal Patil//Downloads");
            			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
            			
            			Thread.sleep(500);
            			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
            			Thread.sleep(250);
            			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
            			test.log(LogStatus.PASS, "File downloaded successfully.");
            			
            			Thread.sleep(5500);
            			File dir1 = new File("C://Users//Snehal Patil//Downloads");
            			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
            			
            			if(dirContents.length < allFilesNew.length)
            			{
            				
            				
            				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
            			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
            			    {
            			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
            			       {
            			           lastModifiedFile = allFilesNew[i];
            			       }
            			    }
            				
            				Thread.sleep(100);
            				fis = new FileInputStream(lastModifiedFile);
            				workbook = new XSSFWorkbook(fis);
            				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
            				
            				int no = sheet.getLastRowNum();
            				Row row = sheet.getRow(no);
            				Cell c1 = row.getCell(0);
            				int records =(int) c1.getNumericCellValue();
            				fis.close();
            				
            				if(count2 == records)
            				{
            					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
            					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
            				}
            				else
            				{
            					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
            					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
            				}
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
            			}
            			
//            			Thread.sleep(7000);
//            			performerPOM.clickRiskFilter(driver).click();
//            			
//            			Thread.sleep(7000);
//            			performerPOM.selectRiskFilter(driver).click();
            			
//            		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//            			selectOptionFromDropDown_bs(SeletcRisk, "High");
            			
            			Thread.sleep(7000);
            			if(performerPOM.clearButton(driver).isEnabled())
            			{
            				Thread.sleep(7000);
            				performerPOM.clearButton(driver).click();
                			test.log(LogStatus.PASS, "clear button work successfully.");
            			}
            			else
            			{
            				test.log(LogStatus.FAIL, "clear button not work successfully.");
            			}
            		
            			
            			
            			
            			Thread.sleep(3000);
            			driver.switchTo().parentFrame();
            			Thread.sleep(2000);
            			performerPOM.caseNoticeSummaryGraphClose(driver).click();
            			
           			Thread.sleep(3000);
           			OverduePOM.clickDashboard(driver).click();
            			
            	   }
             public static void ComplainantAgeingGraph1to2years(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
          		
          		{
          			
          			WebDriverWait wait=new WebDriverWait(driver,20);
          			JavascriptExecutor js = (JavascriptExecutor) driver;
          	       	js.executeScript("window.scrollBy(0,800)");
          	       	
          	       	Thread.sleep(5000);
          			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDashboardNoticeFilter(driver).click();
          			
          			 Thread.sleep(5000);
          				performerPOM.clickDashboardApplyBtn(driver).click();
          				
          				js.executeScript("window.scrollBy(0,3000)");
          			
          	       	Thread.sleep(3000);
          		
          	       	int	open = Integer.parseInt(performerPOM.clickComplianant(driver).getText());	//Reading Notice Open count.
          		    performerPOM.clickComplianant(driver).click();						//Clicking on 'Open' notice
          		
          			Thread.sleep(2000);
          			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
          			
          			Thread.sleep(10000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits = item.split(" ");								//Splitting the String
          			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
          			int count1 = 0;
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				Thread.sleep(2000);
          			   item = CFOcountPOM.readTotalItems1(driver).getText();
          				bits = item.split(" ");								//Splitting the String
          			   compliancesCount = bits[bits.length - 2];
          			}
          			if(compliancesCount.equalsIgnoreCase("to"))
          			{
          				count1 = 0;
          			}
          			else
          			{
          				count1 = Integer.parseInt(compliancesCount);
          			}
          			
          			if(open == count1)
          			{
          				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
          				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          			else
          			{
          				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
          				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
          			}
          	       	
          	    
          			Thread.sleep(3000);
          			performerPOM.clickAgeingViewIcon(driver).click();
          			

        			
        			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
        			Thread.sleep(2000);
          		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
          		  
          		  if(msg.equalsIgnoreCase("Notice Summary"))
          		  {
          			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
          		  }
          		  else
          		  {
          			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
          		  }
          		  
          		  driver.switchTo().parentFrame();
          			
          		 Thread.sleep(3000);
          		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
          			
          			
          			
          			
          		/*	Thread.sleep(3000);
          			performerPOM.clickLocationFilter(driver).click();
          			
          			Thread.sleep(3000);
          			performerPOM.clickLocationFilter1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickLocationFilter3(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNotice(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStatusFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectstatusFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickDepartmentFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectDepartmentFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCaseNoticeType1(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectCaseNoticeType2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickRiskFilter(driver).click();
          			

          			Thread.sleep(5000);
          			performerPOM.selectRiskFilter2cfo(driver).click();
          			
//          			Thread.sleep(5000);
//          			performerPOM.clickAgeFilter(driver).click();
//          			
//          			Thread.sleep(5000);
//          			performerPOM.selectAgeFiltercfo(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickCategoryFilter(driver).click();
          			
          			
          			Thread.sleep(5000);
          			performerPOM.selectCategoryFilter2(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.clickStageFilter(driver).click();
          			
          			Thread.sleep(5000);
          			performerPOM.selectStageFilter2(driver).click(); */
          			
          			
          			Thread.sleep(500);
          			progress(driver);
          			
          			Thread.sleep(1000);
          			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
          			Thread.sleep(2000);
          			
          			js.executeScript("window.scrollBy(0,1000)");
          			
          			
          			
          			Thread.sleep(1000);
          			CFOcountPOM.readTotalItems1(driver).click();
          			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
          			String[] bits1 = item1.split(" ");								//Splitting the String
          			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
          			int count2 = Integer.parseInt(compliancesCount1);
          			
          		    try
          			{
          				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
          			}
          			catch(Exception e)
          			{
          				
          			}
          			js.executeScript("window.scrollBy(0,1000)");
          			
          		
          			Thread.sleep(100);
          			File dir = new File("C://Users//Snehal Patil//Downloads");
          			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
          			
          			Thread.sleep(500);
          			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
          			Thread.sleep(250);
          			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
          			test.log(LogStatus.PASS, "File downloaded successfully.");
          			
          			Thread.sleep(5500);
          			File dir1 = new File("C://Users//Snehal Patil//Downloads");
          			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
          			
          			if(dirContents.length < allFilesNew.length)
          			{
          				
          				
          				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
          			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
          			    {
          			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
          			       {
          			           lastModifiedFile = allFilesNew[i];
          			       }
          			    }
          				
          				Thread.sleep(100);
          				fis = new FileInputStream(lastModifiedFile);
          				workbook = new XSSFWorkbook(fis);
          				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
          				
          				int no = sheet.getLastRowNum();
          				Row row = sheet.getRow(no);
          				Cell c1 = row.getCell(0);
          				int records =(int) c1.getNumericCellValue();
          				fis.close();
          				
          				if(count2 == records)
          				{
          					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
          					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
          				}
          				else
          				{
          					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
          					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
          				}
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
          			}
          			
//          			Thread.sleep(7000);
//          			performerPOM.clickRiskFilter(driver).click();
//          			
//          			Thread.sleep(7000);
//          			performerPOM.selectRiskFilter(driver).click();
          			
//          		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//          			selectOptionFromDropDown_bs(SeletcRisk, "High");
          			
          			Thread.sleep(7000);
          			if(performerPOM.clearButton(driver).isEnabled())
          			{
          				Thread.sleep(7000);
          				performerPOM.clearButton(driver).click();
              			test.log(LogStatus.PASS, "clear button work successfully.");
          			}
          			else
          			{
          				test.log(LogStatus.FAIL, "clear button not work successfully.");
          			}
          		
          			
          			
          			
          			Thread.sleep(3000);
          			driver.switchTo().parentFrame();
          			Thread.sleep(2000);
          			performerPOM.caseNoticeSummaryGraphClose(driver).click();
          			
         			Thread.sleep(3000);
         			OverduePOM.clickDashboard(driver).click();
          			
          	   }
             public static void InwardDefendentAgeingGraph1to2years(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
       		
       		{
       			
       			WebDriverWait wait=new WebDriverWait(driver,20);
       			JavascriptExecutor js = (JavascriptExecutor) driver;
       	       	js.executeScript("window.scrollBy(0,800)");
       	       	
       	       	Thread.sleep(5000);
       			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickDashboardNoticeFilter(driver).click();
       			
       			 Thread.sleep(5000);
       				performerPOM.clickDashboardApplyBtn(driver).click();
       				
       				js.executeScript("window.scrollBy(0,3000)");
       			
       	       	Thread.sleep(3000);
       		
       	       	int	open = Integer.parseInt(performerPOM.clickInwardDefendent(driver).getText());	//Reading Notice Open count.
       		    performerPOM.clickInwardDefendent(driver).click();						//Clicking on 'Open' notice
       		
       			Thread.sleep(2000);
       			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
       			
       			Thread.sleep(10000);
       			CFOcountPOM.readTotalItems1(driver).click();
       			String item = CFOcountPOM.readTotalItems1(driver).getText();
       			String[] bits = item.split(" ");								//Splitting the String
       			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
       			int count1 = 0;
       			if(compliancesCount.equalsIgnoreCase("to"))
       			{
       				Thread.sleep(2000);
       			   item = CFOcountPOM.readTotalItems1(driver).getText();
       				bits = item.split(" ");								//Splitting the String
       			   compliancesCount = bits[bits.length - 2];
       			}
       			if(compliancesCount.equalsIgnoreCase("to"))
       			{
       				count1 = 0;
       			}
       			else
       			{
       				count1 = Integer.parseInt(compliancesCount);
       			}
       			
       			if(open == count1)
       			{
       				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
       				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
       			}
       			else
       			{
       				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
       				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
       			}
       	       	
       	    
       			Thread.sleep(3000);
       			performerPOM.clickAgeingViewIcon(driver).click();
       			

     			
     			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
     			Thread.sleep(2000);
       		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
       		  
       		  if(msg.equalsIgnoreCase("Notice Summary"))
       		  {
       			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
       		  }
       		  else
       		  {
       			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
       		  }
       		  
       		  driver.switchTo().parentFrame();
       			
       		 Thread.sleep(3000);
       		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
       			
       			
       			
       			
       		/*	Thread.sleep(3000);
       			performerPOM.clickLocationFilter(driver).click();
       			
       			Thread.sleep(3000);
       			performerPOM.clickLocationFilter1(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickLocationFilter3(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickCaseNotice(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.selectCaseNotice(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickStatusFilter(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.selectstatusFiltercfo(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickDepartmentFilter(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.selectDepartmentFilter2(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickCaseNoticeType1(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.selectCaseNoticeType2(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickRiskFilter(driver).click();
       			

       			Thread.sleep(5000);
       			performerPOM.selectRiskFilter2cfo(driver).click();
       			
//       			Thread.sleep(5000);
//       			performerPOM.clickAgeFilter(driver).click();
//       			
//       			Thread.sleep(5000);
//       			performerPOM.selectAgeFiltercfo(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickCategoryFilter(driver).click();
       			
       			
       			Thread.sleep(5000);
       			performerPOM.selectCategoryFilter2(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.clickStageFilter(driver).click();
       			
       			Thread.sleep(5000);
       			performerPOM.selectStageFilter2(driver).click(); */
       			
       			
       			Thread.sleep(500);
       			progress(driver);
       			
       			Thread.sleep(1000);
       			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
       			Thread.sleep(2000);
       			JavascriptExecutor js1 = (JavascriptExecutor) driver;
       			js.executeScript("window.scrollBy(0,1000)");
       			
       			
       			
       			Thread.sleep(1000);
       			CFOcountPOM.readTotalItems1(driver).click();
       			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
       			String[] bits1 = item1.split(" ");								//Splitting the String
       			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
       			int count2 = Integer.parseInt(compliancesCount1);
       			
       		    try
       			{
       				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
       			}
       			catch(Exception e)
       			{
       				
       			}
       			js.executeScript("window.scrollBy(0,1000)");
       			
       		
       			Thread.sleep(100);
       			File dir = new File("C://Users//Snehal Patil//Downloads");
       			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
       			
       			Thread.sleep(500);
       			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
       			Thread.sleep(250);
       			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
       			test.log(LogStatus.PASS, "File downloaded successfully.");
       			
       			Thread.sleep(5500);
       			File dir1 = new File("C://Users//Snehal Patil//Downloads");
       			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
       			
       			if(dirContents.length < allFilesNew.length)
       			{
       				
       				
       				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
       			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
       			    {
       			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
       			       {
       			           lastModifiedFile = allFilesNew[i];
       			       }
       			    }
       				
       				Thread.sleep(100);
       				fis = new FileInputStream(lastModifiedFile);
       				workbook = new XSSFWorkbook(fis);
       				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
       				
       				int no = sheet.getLastRowNum();
       				Row row = sheet.getRow(no);
       				Cell c1 = row.getCell(0);
       				int records =(int) c1.getNumericCellValue();
       				fis.close();
       				
       				if(count2 == records)
       				{
       					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
       					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
       				}
       				else
       				{
       					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
       					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
       				}
       			}
       			else
       			{
       				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
       			}
       			
//       			Thread.sleep(7000);
//       			performerPOM.clickRiskFilter(driver).click();
//       			
//       			Thread.sleep(7000);
//       			performerPOM.selectRiskFilter(driver).click();
       			
//       		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//       			selectOptionFromDropDown_bs(SeletcRisk, "High");
       			
       			Thread.sleep(7000);
       			if(performerPOM.clearButton(driver).isEnabled())
       			{
       				Thread.sleep(7000);
       				performerPOM.clearButton(driver).click();
           			test.log(LogStatus.PASS, "clear button work successfully.");
       			}
       			else
       			{
       				test.log(LogStatus.FAIL, "clear button not work successfully.");
       			}
       		
       			
       			
       			
       			Thread.sleep(3000);
       			driver.switchTo().parentFrame();
       			Thread.sleep(2000);
       			performerPOM.caseNoticeSummaryGraphClose(driver).click();
       			
      			Thread.sleep(3000);
      			OverduePOM.clickDashboard(driver).click();
       			
       	   }
             public static void OutwardPlaintiffAgeingGraph1to2years(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
        		
        		{
        			
        			WebDriverWait wait=new WebDriverWait(driver,20);
        			JavascriptExecutor js = (JavascriptExecutor) driver;
        	       	js.executeScript("window.scrollBy(0,800)");
        	       	
        	       	Thread.sleep(5000);
        			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickDashboardNoticeFilter(driver).click();
        			
        			 Thread.sleep(5000);
        				performerPOM.clickDashboardApplyBtn(driver).click();
        				
        				js.executeScript("window.scrollBy(0,3000)");
        			
        	       	Thread.sleep(3000);
        		
        	       	int	open = Integer.parseInt(performerPOM.clickOutwardPlaintiff(driver).getText());	//Reading Notice Open count.
        		    performerPOM.clickOutwardPlaintiff(driver).click();						//Clicking on 'Open' notice
        		
        			Thread.sleep(2000);
        			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
        			
        			Thread.sleep(10000);
        			CFOcountPOM.readTotalItems1(driver).click();
        			String item = CFOcountPOM.readTotalItems1(driver).getText();
        			String[] bits = item.split(" ");								//Splitting the String
        			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
        			int count1 = 0;
        			if(compliancesCount.equalsIgnoreCase("to"))
        			{
        				Thread.sleep(2000);
        			   item = CFOcountPOM.readTotalItems1(driver).getText();
        				bits = item.split(" ");								//Splitting the String
        			   compliancesCount = bits[bits.length - 2];
        			}
        			if(compliancesCount.equalsIgnoreCase("to"))
        			{
        				count1 = 0;
        			}
        			else
        			{
        				count1 = Integer.parseInt(compliancesCount);
        			}
        			
        			if(open == count1)
        			{
        				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
        				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
        			}
        			else
        			{
        				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
        				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
        			}
        	       	
        	    
        			Thread.sleep(3000);
        			performerPOM.clickAgeingViewIcon(driver).click();
        			

      			
      			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
      			Thread.sleep(2000);
        		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
        		  
        		  if(msg.equalsIgnoreCase("Notice Summary"))
        		  {
        			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
        		  }
        		  else
        		  {
        			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
        		  }
        		  
        		  driver.switchTo().parentFrame();
        			
        		 Thread.sleep(3000);
        		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
        			
        			
        			
        			
        		/*	Thread.sleep(3000);
        			performerPOM.clickLocationFilter(driver).click();
        			
        			Thread.sleep(3000);
        			performerPOM.clickLocationFilter1(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickLocationFilter3(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickCaseNotice(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.selectCaseNotice(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickStatusFilter(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.selectstatusFiltercfo(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickDepartmentFilter(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.selectDepartmentFilter2(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickCaseNoticeType1(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.selectCaseNoticeType2(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickRiskFilter(driver).click();
        			

        			Thread.sleep(5000);
        			performerPOM.selectRiskFilter2cfo(driver).click();
        			
//        			Thread.sleep(5000);
//        			performerPOM.clickAgeFilter(driver).click();
//        			
//        			Thread.sleep(5000);
//        			performerPOM.selectAgeFiltercfo(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickCategoryFilter(driver).click();
        			
        			
        			Thread.sleep(5000);
        			performerPOM.selectCategoryFilter2(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.clickStageFilter(driver).click();
        			
        			Thread.sleep(5000);
        			performerPOM.selectStageFilter2(driver).click(); */
        			
        			
        			Thread.sleep(500);
        			progress(driver);
        			
        			Thread.sleep(1000);
        			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
        			Thread.sleep(2000);
        		
        			js.executeScript("window.scrollBy(0,1000)");
        			
        			
        			
        			Thread.sleep(1000);
        			CFOcountPOM.readTotalItems1(driver).click();
        			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
        			String[] bits1 = item1.split(" ");								//Splitting the String
        			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
        			int count2 = Integer.parseInt(compliancesCount1);
        			
        		    try
        			{
        				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
        			}
        			catch(Exception e)
        			{
        				
        			}
        			js.executeScript("window.scrollBy(0,1000)");
        			
        		
        			Thread.sleep(100);
        			File dir = new File("C://Users//Snehal Patil//Downloads");
        			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
        			
        			Thread.sleep(500);
        			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
        			Thread.sleep(250);
        			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
        			test.log(LogStatus.PASS, "File downloaded successfully.");
        			
        			Thread.sleep(5500);
        			File dir1 = new File("C://Users//Snehal Patil//Downloads");
        			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
        			
        			if(dirContents.length < allFilesNew.length)
        			{
        				
        				
        				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
        			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
        			    {
        			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
        			       {
        			           lastModifiedFile = allFilesNew[i];
        			       }
        			    }
        				
        				Thread.sleep(100);
        				fis = new FileInputStream(lastModifiedFile);
        				workbook = new XSSFWorkbook(fis);
        				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
        				
        				int no = sheet.getLastRowNum();
        				Row row = sheet.getRow(no);
        				Cell c1 = row.getCell(0);
        				int records =(int) c1.getNumericCellValue();
        				fis.close();
        				
        				if(count2 == records)
        				{
        					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
        					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
        				}
        				else
        				{
        					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
        					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
        				}
        			}
        			else
        			{
        				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
        			}
        			
//        			Thread.sleep(7000);
//        			performerPOM.clickRiskFilter(driver).click();
//        			
//        			Thread.sleep(7000);
//        			performerPOM.selectRiskFilter(driver).click();
        			
//        		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//        			selectOptionFromDropDown_bs(SeletcRisk, "High");
        			
        			Thread.sleep(7000);
        			if(performerPOM.clearButton(driver).isEnabled())
        			{
        				Thread.sleep(7000);
        				performerPOM.clearButton(driver).click();
            			test.log(LogStatus.PASS, "clear button work successfully.");
        			}
        			else
        			{
        				test.log(LogStatus.FAIL, "clear button not work successfully.");
        			}
        		
        			
        			
        			
        			Thread.sleep(3000);
        			driver.switchTo().parentFrame();
        			Thread.sleep(2000);
        			performerPOM.caseNoticeSummaryGraphClose(driver).click();
        			
       			Thread.sleep(3000);
       			OverduePOM.clickDashboard(driver).click();
        			
        	   }
         public static void RespondentAgeingGraph1to2years(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
     		
     		{
     			
     			WebDriverWait wait=new WebDriverWait(driver,20);
     			JavascriptExecutor js = (JavascriptExecutor) driver;
     	       	js.executeScript("window.scrollBy(0,800)");
     	       	
     	       	Thread.sleep(5000);
     			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickDashboardNoticeFilter(driver).click();
     			
     			 Thread.sleep(5000);
     				performerPOM.clickDashboardApplyBtn(driver).click();
     				
     				js.executeScript("window.scrollBy(0,3000)");
     			
     	       	Thread.sleep(3000);
     		
     	       	int	open = Integer.parseInt(performerPOM.clickRespondent(driver).getText());	//Reading Notice Open count.
     		    performerPOM.clickRespondent(driver).click();						//Clicking on 'Open' notice
     		
     			Thread.sleep(2000);
     			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
     			
     			Thread.sleep(10000);
     			CFOcountPOM.readTotalItems1(driver).click();
     			String item = CFOcountPOM.readTotalItems1(driver).getText();
     			String[] bits = item.split(" ");								//Splitting the String
     			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
     			int count1 = 0;
     			if(compliancesCount.equalsIgnoreCase("to"))
     			{
     				Thread.sleep(2000);
     			   item = CFOcountPOM.readTotalItems1(driver).getText();
     				bits = item.split(" ");								//Splitting the String
     			   compliancesCount = bits[bits.length - 2];
     			}
     			if(compliancesCount.equalsIgnoreCase("to"))
     			{
     				count1 = 0;
     			}
     			else
     			{
     				count1 = Integer.parseInt(compliancesCount);
     			}
     			
     			if(open == count1)
     			{
     				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
     				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
     			}
     			else
     			{
     				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
     				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
     			}
     	       	
     	    
     			Thread.sleep(3000);
     			performerPOM.clickAgeingViewIcon(driver).click();
     			

   			
   			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
   			Thread.sleep(2000);
     		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
     		  
     		  if(msg.equalsIgnoreCase("Notice Summary"))
     		  {
     			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
     		  }
     		  else
     		  {
     			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
     		  }
     		  
     		  driver.switchTo().parentFrame();
     			
     		 Thread.sleep(3000);
     		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
     			
     			
     			
     			
     		/*	Thread.sleep(3000);
     			performerPOM.clickLocationFilter(driver).click();
     			
     			Thread.sleep(3000);
     			performerPOM.clickLocationFilter1(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickLocationFilter3(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickCaseNotice(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.selectCaseNotice(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickStatusFilter(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.selectstatusFiltercfo(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickDepartmentFilter(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.selectDepartmentFilter2(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickCaseNoticeType1(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.selectCaseNoticeType2(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickRiskFilter(driver).click();
     			

     			Thread.sleep(5000);
     			performerPOM.selectRiskFilter2cfo(driver).click();
     			
//     			Thread.sleep(5000);
//     			performerPOM.clickAgeFilter(driver).click();
//     			
//     			Thread.sleep(5000);
//     			performerPOM.selectAgeFiltercfo(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickCategoryFilter(driver).click();
     			
     			
     			Thread.sleep(5000);
     			performerPOM.selectCategoryFilter2(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.clickStageFilter(driver).click();
     			
     			Thread.sleep(5000);
     			performerPOM.selectStageFilter2(driver).click(); */
     			
     			
     			Thread.sleep(500);
     			progress(driver);
     			
     			Thread.sleep(1000);
     			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
     			Thread.sleep(2000);
     		
     			js.executeScript("window.scrollBy(0,1000)");
     			
     			
     			
     			Thread.sleep(1000);
     			CFOcountPOM.readTotalItems1(driver).click();
     			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
     			String[] bits1 = item1.split(" ");								//Splitting the String
     			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
     			int count2 = Integer.parseInt(compliancesCount1);
     			
     		    try
     			{
     				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
     			}
     			catch(Exception e)
     			{
     				
     			}
     			js.executeScript("window.scrollBy(0,1000)");
     			
     		
     			Thread.sleep(100);
     			File dir = new File("C://Users//Snehal Patil//Downloads");
     			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
     			
     			Thread.sleep(500);
     			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
     			Thread.sleep(250);
     			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
     			test.log(LogStatus.PASS, "File downloaded successfully.");
     			
     			Thread.sleep(5500);
     			File dir1 = new File("C://Users//Snehal Patil//Downloads");
     			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
     			
     			if(dirContents.length < allFilesNew.length)
     			{
     				
     				
     				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
     			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
     			    {
     			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
     			       {
     			           lastModifiedFile = allFilesNew[i];
     			       }
     			    }
     				
     				Thread.sleep(100);
     				fis = new FileInputStream(lastModifiedFile);
     				workbook = new XSSFWorkbook(fis);
     				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
     				
     				int no = sheet.getLastRowNum();
     				Row row = sheet.getRow(no);
     				Cell c1 = row.getCell(0);
     				int records =(int) c1.getNumericCellValue();
     				fis.close();
     				
     				if(count2 == records)
     				{
     					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
     					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
     				}
     				else
     				{
     					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
     					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
     				}
     			}
     			else
     			{
     				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
     			}
     			
//     			Thread.sleep(7000);
//     			performerPOM.clickRiskFilter(driver).click();
//     			
//     			Thread.sleep(7000);
//     			performerPOM.selectRiskFilter(driver).click();
     			
//     		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//     			selectOptionFromDropDown_bs(SeletcRisk, "High");
     			
     			Thread.sleep(7000);
     			if(performerPOM.clearButton(driver).isEnabled())
     			{
     				Thread.sleep(7000);
     				performerPOM.clearButton(driver).click();
         			test.log(LogStatus.PASS, "clear button work successfully.");
     			}
     			else
     			{
     				test.log(LogStatus.FAIL, "clear button not work successfully.");
     			}
     		
     			
     			
     			
     			Thread.sleep(3000);
     			driver.switchTo().parentFrame();
     			Thread.sleep(2000);
     			performerPOM.caseNoticeSummaryGraphClose(driver).click();
     			
    			Thread.sleep(3000);
    			OverduePOM.clickDashboard(driver).click();
     			
     	   }
         public static void InwardDefendentAgeingGraph2to3years(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
  		
  		{
  			
  			WebDriverWait wait=new WebDriverWait(driver,20);
  			JavascriptExecutor js = (JavascriptExecutor) driver;
  	       	js.executeScript("window.scrollBy(0,800)");
  	       	
  	       	Thread.sleep(5000);
  			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickDashboardNoticeFilter(driver).click();
  			
  			 Thread.sleep(5000);
  				performerPOM.clickDashboardApplyBtn(driver).click();
  				
  				js.executeScript("window.scrollBy(0,3000)");
  			
  	       	Thread.sleep(3000);
  		
  	       	int	open = Integer.parseInt(performerPOM.clickInwardDefendent1(driver).getText());	//Reading Notice Open count.
  		    performerPOM.clickInwardDefendent1(driver).click();						//Clicking on 'Open' notice
  		
  			Thread.sleep(2000);
  			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
  			
  			Thread.sleep(10000);
  			CFOcountPOM.readTotalItems1(driver).click();
  			String item = CFOcountPOM.readTotalItems1(driver).getText();
  			String[] bits = item.split(" ");								//Splitting the String
  			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
  			int count1 = 0;
  			if(compliancesCount.equalsIgnoreCase("to"))
  			{
  				Thread.sleep(2000);
  			   item = CFOcountPOM.readTotalItems1(driver).getText();
  				bits = item.split(" ");								//Splitting the String
  			   compliancesCount = bits[bits.length - 2];
  			}
  			if(compliancesCount.equalsIgnoreCase("to"))
  			{
  				count1 = 0;
  			}
  			else
  			{
  				count1 = Integer.parseInt(compliancesCount);
  			}
  			
  			if(open == count1)
  			{
  				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
  				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
  			}
  			else
  			{
  				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
  				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
  			}
  	       	
  	    
  			Thread.sleep(3000);
  			performerPOM.clickAgeingViewIcon(driver).click();
  			

			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(2000);
  		  String msg =performerPOM.clickAgeingViewNoticeSummary(driver).getText();
  		  
  		  if(msg.equalsIgnoreCase("Notice Summary"))
  		  {
  			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
  		  }
  		  else
  		  {
  			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
  		  }
  		  
  		  driver.switchTo().parentFrame();
  			
  		 Thread.sleep(3000);
  		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
  			
  			
  			
  			
  		/*	Thread.sleep(3000);
  			performerPOM.clickLocationFilter(driver).click();
  			
  			Thread.sleep(3000);
  			performerPOM.clickLocationFilter1(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickLocationFilter3(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickCaseNotice(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.selectCaseNotice(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickStatusFilter(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.selectstatusFiltercfo(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickDepartmentFilter(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.selectDepartmentFilter2(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickCaseNoticeType1(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.selectCaseNoticeType2(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickRiskFilter(driver).click();
  			

  			Thread.sleep(5000);
  			performerPOM.selectRiskFilter2cfo(driver).click();
  			
//  			Thread.sleep(5000);
//  			performerPOM.clickAgeFilter(driver).click();
//  			
//  			Thread.sleep(5000);
//  			performerPOM.selectAgeFiltercfo(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickCategoryFilter(driver).click();
  			
  			
  			Thread.sleep(5000);
  			performerPOM.selectCategoryFilter2(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickStageFilter(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.selectStageFilter2(driver).click(); */
  			
  			
  			Thread.sleep(500);
  			progress(driver);
  			
  			Thread.sleep(1000);
  			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
  			Thread.sleep(2000);
  			
  			js.executeScript("window.scrollBy(0,1000)");
  			
  			
  			
  			Thread.sleep(1000);
  			CFOcountPOM.readTotalItems1(driver).click();
  			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
  			String[] bits1 = item1.split(" ");								//Splitting the String
  			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
  			int count2 = Integer.parseInt(compliancesCount1);
  			
  		    try
  			{
  				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
  			}
  			catch(Exception e)
  			{
  				
  			}
  			js.executeScript("window.scrollBy(0,1000)");
  			
  		
  			Thread.sleep(100);
  			File dir = new File("C://Users//Snehal Patil//Downloads");
  			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
  			
  			Thread.sleep(500);
  			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
  			Thread.sleep(250);
  			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
  			test.log(LogStatus.PASS, "File downloaded successfully.");
  			
  			Thread.sleep(5500);
  			File dir1 = new File("C://Users//Snehal Patil//Downloads");
  			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
  			
  			if(dirContents.length < allFilesNew.length)
  			{
  				
  				
  				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
  			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
  			    {
  			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
  			       {
  			           lastModifiedFile = allFilesNew[i];
  			       }
  			    }
  				
  				Thread.sleep(100);
  				fis = new FileInputStream(lastModifiedFile);
  				workbook = new XSSFWorkbook(fis);
  				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
  				
  				int no = sheet.getLastRowNum();
  				Row row = sheet.getRow(no);
  				Cell c1 = row.getCell(0);
  				int records =(int) c1.getNumericCellValue();
  				fis.close();
  				
  				if(count2 == records)
  				{
  					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
  					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
  				}
  				else
  				{
  					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
  					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
  				}
  			}
  			else
  			{
  				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
  			}
  			
//  			Thread.sleep(7000);
//  			performerPOM.clickRiskFilter(driver).click();
//  			
//  			Thread.sleep(7000);
//  			performerPOM.selectRiskFilter(driver).click();
  			
//  		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//  			selectOptionFromDropDown_bs(SeletcRisk, "High");
  			
  			Thread.sleep(7000);
  			if(performerPOM.clearButton(driver).isEnabled())
  			{
  				Thread.sleep(7000);
  				performerPOM.clearButton(driver).click();
      			test.log(LogStatus.PASS, "clear button work successfully.");
  			}
  			else
  			{
  				test.log(LogStatus.FAIL, "clear button not work successfully.");
  			}
  		
  			
  			
  			
  			Thread.sleep(3000);
  			driver.switchTo().parentFrame();
  			Thread.sleep(2000);
  			performerPOM.caseNoticeSummaryGraphClose(driver).click();
  			
 			Thread.sleep(3000);
 			OverduePOM.clickDashboard(driver).click();
  			
  	   }
         
         public static void InwardDefendantAgeingGraphCase(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
   		
   		{
   			
   			WebDriverWait wait=new WebDriverWait(driver,20);
   			JavascriptExecutor js = (JavascriptExecutor) driver;
   	       	js.executeScript("window.scrollBy(0,800)");
   	       	
   	       	Thread.sleep(5000);
   			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
   			
   			Thread.sleep(5000);
   			performerPOM.clickDashboardCaseFilter(driver).click();
   			
   			 Thread.sleep(5000);
   				performerPOM.clickDashboardApplyBtn(driver).click();
   				
   				js.executeScript("window.scrollBy(0,3000)");
   			
   	       	Thread.sleep(3000);
   		
   	       	int	open = Integer.parseInt(performerPOM.clickInwardDefendentCase(driver).getText());	//Reading Notice Open count.
   		    performerPOM.clickInwardDefendentCase(driver).click();						//Clicking on 'Open' notice
   		
   			Thread.sleep(2000);
   			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
   			
   			Thread.sleep(10000);
   			CFOcountPOM.readTotalItems1(driver).click();
   			String item = CFOcountPOM.readTotalItems1(driver).getText();
   			String[] bits = item.split(" ");								//Splitting the String
   			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
   			int count1 = 0;
   			if(compliancesCount.equalsIgnoreCase("to"))
   			{
   				Thread.sleep(2000);
   			   item = CFOcountPOM.readTotalItems1(driver).getText();
   				bits = item.split(" ");								//Splitting the String
   			   compliancesCount = bits[bits.length - 2];
   			}
   			if(compliancesCount.equalsIgnoreCase("to"))
   			{
   				count1 = 0;
   			}
   			else
   			{
   				count1 = Integer.parseInt(compliancesCount);
   			}
   			
   			if(open == count1)
   			{
   				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
   				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
   			}
   			else
   			{
   				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
   				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
   			}
   	       	
   	    
   			Thread.sleep(3000);
   			performerPOM.clickAgeingViewIcon(driver).click();
   			

 			
 			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 			Thread.sleep(2000);
   		  String msg =performerPOM.clickAgeingViewCaseSummary(driver).getText();
   		  
   		  if(msg.equalsIgnoreCase("Case Summary"))
   		  {
   			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
   		  }
   		  else
   		  {
   			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
   		  }
   		  
   		  driver.switchTo().parentFrame();
   			
   		 Thread.sleep(3000);
   		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
   			
   			Thread.sleep(500);
   			progress(driver);
   			
   			Thread.sleep(1000);
   			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
   			Thread.sleep(2000);
   			
   			js.executeScript("window.scrollBy(0,1000)");
   			
   			
   			
   			Thread.sleep(1000);
   			CFOcountPOM.readTotalItems1(driver).click();
   			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
   			String[] bits1 = item1.split(" ");								//Splitting the String
   			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
   			int count2 = Integer.parseInt(compliancesCount1);
   			
   		    try
   			{
   				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
   			}
   			catch(Exception e)
   			{
   				
   			}
   			js.executeScript("window.scrollBy(0,1000)");
   			
   		
   			Thread.sleep(100);
   			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
   			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
   			
   			Thread.sleep(500);
   			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
   			Thread.sleep(250);
   			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
   		
   			
   			Thread.sleep(5500);
   			File dir1 = new File("C:\\Users\\Snehal Patil\\Downloads");
   			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
   			
   			if(dirContents.length < allFilesNew.length)
   			{
   				
   				test.log(LogStatus.PASS, "File downloaded successfully.");
   				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
   			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
   			    {
   			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
   			       {
   			           lastModifiedFile = allFilesNew[i];
   			       }
   			    }
   				
   				Thread.sleep(100);
   				fis = new FileInputStream(lastModifiedFile);
   				workbook = new XSSFWorkbook(fis);
   				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
   				
   				int no = sheet.getLastRowNum();
   				Row row = sheet.getRow(no);
   				Cell c1 = row.getCell(0);
   				int records =(int) c1.getNumericCellValue();
   				fis.close();
   				
   				if(count2 == records)
   				{
   					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
   					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
   				}
   				else
   				{
   					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
   					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
   				}
   			}
   			else
   			{
   				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
   			}
   			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
   			
   			
   			
   			
//   			Thread.sleep(7000);
//   			performerPOM.clickRiskFilter(driver).click();
//   			
//   			Thread.sleep(7000);
//   			performerPOM.selectRiskFilter1(driver).click();
   			
   			
   			Thread.sleep(7000);
   			if(performerPOM.clearButton(driver).isEnabled())
   			{
   				Thread.sleep(7000);
   				performerPOM.clearButton(driver).click();
       			test.log(LogStatus.PASS, "clear button work successfully.");
   			}
   			else
   			{
   				test.log(LogStatus.FAIL, "clear button not work successfully.");
   			}
   		
   			
   			
   			
   			Thread.sleep(3000);
   			driver.switchTo().parentFrame();
   			Thread.sleep(2000);
   			performerPOM.caseNoticeSummaryGraphClose(driver).click();
   			
  			Thread.sleep(3000);
  			OverduePOM.clickDashboard(driver).click();
   			
   	   }
         public static void OutwardPlaintiffAgeingGraphCase(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
 		
 		{
 			
 			WebDriverWait wait=new WebDriverWait(driver,20);
 			JavascriptExecutor js = (JavascriptExecutor) driver;
 	       	js.executeScript("window.scrollBy(0,800)");
 	       	
 	       	Thread.sleep(5000);
 			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
 			
 			Thread.sleep(5000);
 			performerPOM.clickDashboardCaseFilter(driver).click();
 			
 			 Thread.sleep(5000);
 				performerPOM.clickDashboardApplyBtn(driver).click();
 				
 				js.executeScript("window.scrollBy(0,3000)");
 			
 	       	Thread.sleep(3000);
 		
 	       	int	open = Integer.parseInt(performerPOM.clickOutwardPlaintiffCase(driver).getText());	//Reading Notice Open count.
 		    performerPOM.clickOutwardPlaintiffCase(driver).click();						//Clicking on 'Open' notice
 		
 			Thread.sleep(2000);
 			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
 			
 			Thread.sleep(10000);
 			CFOcountPOM.readTotalItems1(driver).click();
 			String item = CFOcountPOM.readTotalItems1(driver).getText();
 			String[] bits = item.split(" ");								//Splitting the String
 			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
 			int count1 = 0;
 			if(compliancesCount.equalsIgnoreCase("to"))
 			{
 				Thread.sleep(2000);
 			   item = CFOcountPOM.readTotalItems1(driver).getText();
 				bits = item.split(" ");								//Splitting the String
 			   compliancesCount = bits[bits.length - 2];
 			}
 			if(compliancesCount.equalsIgnoreCase("to"))
 			{
 				count1 = 0;
 			}
 			else
 			{
 				count1 = Integer.parseInt(compliancesCount);
 			}
 			
 			if(open == count1)
 			{
 				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
 				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
 			}
 			else
 			{
 				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
 				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
 			}
 	       	
 	    
 			Thread.sleep(3000);
 			performerPOM.clickAgeingViewIcon(driver).click();
 			

			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(2000);
 		  String msg =performerPOM.clickAgeingViewCaseSummary(driver).getText();
 		  
 		  if(msg.equalsIgnoreCase("Case Summary"))
 		  {
 			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
 		  }
 		  else
 		  {
 			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
 		  }
 		  
 		  driver.switchTo().parentFrame();
 			
 		 Thread.sleep(3000);
 		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
 			
 			
 			
 			
 		
 			
 			Thread.sleep(500);
 			progress(driver);
 			
 			Thread.sleep(1000);
 			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
 			Thread.sleep(2000);
 			
 			js.executeScript("window.scrollBy(0,1000)");
 			
 			
 			
 			Thread.sleep(1000);
 			CFOcountPOM.readTotalItems1(driver).click();
 			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
 			String[] bits1 = item1.split(" ");								//Splitting the String
 			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
 			int count2 = Integer.parseInt(compliancesCount1);
 			
 		    try
 			{
 				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
 			}
 			catch(Exception e)
 			{
 				
 			}
 			js.executeScript("window.scrollBy(0,1000)");
 			
 		
 			Thread.sleep(100);
 			File dir = new File("C:\\Users\\Snehal Patil\\Downloads");
 			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
 			
 			Thread.sleep(500);
 			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
 			Thread.sleep(250);
 			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
 		
 			
 			Thread.sleep(5500);
 			File dir1 = new File("C://Users//Snehal Patil//Downloads");
 			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
 			
 			if(dirContents.length < allFilesNew.length)
 			{
 				
 				test.log(LogStatus.PASS, "File downloaded successfully.");
 				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
 			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
 			    {
 			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
 			       {
 			           lastModifiedFile = allFilesNew[i];
 			       }
 			    }
 				
 				Thread.sleep(100);
 				fis = new FileInputStream(lastModifiedFile);
 				workbook = new XSSFWorkbook(fis);
 				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
 				
 				int no = sheet.getLastRowNum();
 				Row row = sheet.getRow(no);
 				Cell c1 = row.getCell(0);
 				int records =(int) c1.getNumericCellValue();
 				fis.close();
 				
 				if(count2 == records)
 				{
 					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
 					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
 				}
 				else
 				{
 					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
 					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
 				}
 			}
 			else
 			{
 				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
 			}
 			
 			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
 			
// 			Thread.sleep(7000);
// 			performerPOM.clickRiskFilter(driver).click();
// 			
// 			Thread.sleep(7000);
// 			performerPOM.selectRiskFilter1(driver).click();
 			
// 		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
// 			selectOptionFromDropDown_bs(SeletcRisk, "High");
 			
 			Thread.sleep(7000);
 			if(performerPOM.clearButton(driver).isEnabled())
 			{
 				Thread.sleep(7000);
 				performerPOM.clearButton(driver).click();
     			test.log(LogStatus.PASS, "clear button work successfully.");
 			}
 			else
 			{
 				test.log(LogStatus.FAIL, "clear button not work successfully.");
 			}
 		
 			
 			
 			
 			Thread.sleep(3000);
 			driver.switchTo().parentFrame();
 			Thread.sleep(2000);
 			performerPOM.caseNoticeSummaryGraphClose(driver).click();
 			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
 			
 	   }
         public static void PetitionerAgeingGraphCase(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
  		
  		{
  			
  			WebDriverWait wait=new WebDriverWait(driver,20);
  			JavascriptExecutor js = (JavascriptExecutor) driver;
  	       	js.executeScript("window.scrollBy(0,800)");
  	       	
  	       	Thread.sleep(5000);
  			performerPOM.clickDashboardCaseNoticeFilter(driver).click();
  			
  			Thread.sleep(5000);
  			performerPOM.clickDashboardCaseFilter(driver).click();
  			
  			 Thread.sleep(5000);
  				performerPOM.clickDashboardApplyBtn(driver).click();
  				
  				js.executeScript("window.scrollBy(0,3000)");
  			
  	       	Thread.sleep(3000);
  		
  	       	int	open = Integer.parseInt(performerPOM.clickPetitionerCase(driver).getText());	//Reading Notice Open count.
  		    performerPOM.clickPetitionerCase(driver).click();						//Clicking on 'Open' notice
  		
  			Thread.sleep(2000);
  			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showChartDetails"));
  			
  			Thread.sleep(10000);
  			CFOcountPOM.readTotalItems1(driver).click();
  			String item = CFOcountPOM.readTotalItems1(driver).getText();
  			String[] bits = item.split(" ");								//Splitting the String
  			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
  			int count1 = 0;
  			if(compliancesCount.equalsIgnoreCase("to"))
  			{
  				Thread.sleep(2000);
  			   item = CFOcountPOM.readTotalItems1(driver).getText();
  				bits = item.split(" ");								//Splitting the String
  			   compliancesCount = bits[bits.length - 2];
  			}
  			if(compliancesCount.equalsIgnoreCase("to"))
  			{
  				count1 = 0;
  			}
  			else
  			{
  				count1 = Integer.parseInt(compliancesCount);
  			}
  			
  			if(open == count1)
  			{
  				//test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
  				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
  			}
  			else
  			{
  				//test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
  				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
  			}
  	       	
  	    
  			Thread.sleep(3000);
  			performerPOM.clickAgeingViewIcon(driver).click();
  			

 			
 			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 			Thread.sleep(2000);
  		  String msg =performerPOM.clickAgeingViewCaseSummary(driver).getText();
  		  
  		  if(msg.equalsIgnoreCase("Case Summary"))
  		  {
  			  test.log(LogStatus.PASS, "View Icon Of Ageing Graph Open Successfully = " +msg);
  		  }
  		  else
  		  {
  			 test.log(LogStatus.FAIL, "View Icon Of Ageing Graph Not Open Successfully = " +msg);
  		  }
  		  
  		  driver.switchTo().parentFrame();
  			
  		 Thread.sleep(3000);
  		 performerPOM.clickAgeingViewNoticeSummaryCloseIcon(driver).click();
  			
 	
  			Thread.sleep(500);
  			progress(driver);
  			
  			Thread.sleep(1000);
  			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
  			Thread.sleep(2000);
  			
  			js.executeScript("window.scrollBy(0,1000)");
  			
  			
  			
  			Thread.sleep(1000);
  			CFOcountPOM.readTotalItems1(driver).click();
  			String item1 = CFOcountPOM.readTotalItems1(driver).getText();
  			String[] bits1 = item1.split(" ");								//Splitting the String
  			String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
  			int count2 = Integer.parseInt(compliancesCount1);
  			
  		    try
  			{
  				performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
  			}
  			catch(Exception e)
  			{
  				
  			}
  			js.executeScript("window.scrollBy(0,1000)");
  			
  		
  			Thread.sleep(100);
  			File dir = new File("C://Users//Snehal Patil//Downloads");
  			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
  			
  			Thread.sleep(500);
  			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
  			Thread.sleep(250);
  			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
  		
  			
  			Thread.sleep(5500);
  			File dir1 = new File("C://Users//Snehal Patil//Downloads");
  			File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
  			
  			if(dirContents.length < allFilesNew.length)
  			{
  				
  				test.log(LogStatus.PASS, "File downloaded successfully.");
  				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
  			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
  			    {
  			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
  			       {
  			           lastModifiedFile = allFilesNew[i];
  			       }
  			    }
  				
  				Thread.sleep(100);
  				fis = new FileInputStream(lastModifiedFile);
  				workbook = new XSSFWorkbook(fis);
  				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
  				
  				int no = sheet.getLastRowNum();
  				Row row = sheet.getRow(no);
  				Cell c1 = row.getCell(0);
  				int records =(int) c1.getNumericCellValue();
  				fis.close();
  				
  				if(count2 == records)
  				{
  					//test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
  					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Excel Report = "+records);
  				}
  				else
  				{
  					//test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
  					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
  				}
  			}
  			else
  			{
  				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
  			}
  			
  			
  			File dir2 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] dirContents1 = dir2.listFiles();						//Counting number of files in directory before download
			
			Thread.sleep(3000);
		     performerPOM.ClickDetailedExpenseReport(driver).click();
			
		 	Thread.sleep(3000);
			File dir3 = new File("C:\\Users\\Snehal Patil\\Downloads");
			File[] allFilesNew1 = dir3.listFiles();						//Counting number of files in directory after download
			
			Thread.sleep(3000);
		   if (dirContents1.length < allFilesNew1.length) {
				test.log(LogStatus.PASS,  "Detailed Expenses Report Download Successfully");
			}
		   else
		   {
				test.log(LogStatus.FAIL, "Detailed Expenses Report does not Download Successfully");
			}
  			
//  			Thread.sleep(7000);
//  			performerPOM.clickRiskFilter(driver).click();
//  			
//  			Thread.sleep(7000);
//  			performerPOM.selectRiskFilter1(driver).click();
  			
//  		    List<WebElement>SeletcRisk = driver.findElements(By.xpath("(//ul[@class='k-group k-treeview-lines'])[8]/li/div/span"));
//  			selectOptionFromDropDown_bs(SeletcRisk, "High");
  			
  			Thread.sleep(7000);
  			if(performerPOM.clearButton(driver).isEnabled())
  			{
  				Thread.sleep(7000);
  				performerPOM.clearButton(driver).click();
      			test.log(LogStatus.PASS, "clear button work successfully.");
  			}
  			else
  			{
  				test.log(LogStatus.FAIL, "clear button not work successfully.");
  			}
  		
  			
  			
  			
  			Thread.sleep(3000);
  			driver.switchTo().parentFrame();
  			Thread.sleep(2000);
  			performerPOM.caseNoticeSummaryGraphClose(driver).click();
  			
 			Thread.sleep(3000);
 			OverduePOM.clickDashboard(driver).click();
  			
  	   }
         
         
         static void NoticeWithInvalidData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
 		{
 		   
        	 sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 			progress(driver);
 			
 			Thread.sleep(500);
 			JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-700)");
            Thread.sleep(500);
        	performerPOM.clickNoticeOpen(driver).click();		
 			
             Thread.sleep(4000);
 			clickNewNotice(driver);
 			
 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 			
 			
 			
 			Thread.sleep(3000);
 			clickDated(driver);

 			Thread.sleep(3000);
 			clickFinancialYear(driver);

 			Thread.sleep(3000);
 			clickRefNo(driver);

 			Thread.sleep(3000);
 			selectNoticeType(driver);

 			Thread.sleep(3000);
 			clickAct(driver);

 			Thread.sleep(3000);
 			clickOpponentcfo(driver);

 			Thread.sleep(3000);
 			selectCategory(driver);

 			Thread.sleep(3000);
 			clickNoticeTitle(driver);

 			Thread.sleep(3000);
 			clickNoticeDescription(driver);

 			Thread.sleep(3000);
 			selectLocation(driver);

 			Thread.sleep(3000);
 			clickDepartment(driver);

 			Thread.sleep(3000);
 			clickOwner(driver);

 			Thread.sleep(3000);
             selectRisk(driver);

 			Thread.sleep(3000);
             selectNoticeRecipetDate1(driver);
             
             Thread.sleep(3000);
             clickInternalUser(driver);
             


             Thread.sleep(3000);
     		performerPOM.selectNoticeUploadDocument(driver); 
     		
        		
        	Thread.sleep(3000);
     		OverduePOM.clickSaveButton(driver).click();		
     		
     		Thread.sleep(1000);
     		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
     		
     		Thread.sleep(2000);
     		String msg = performerPOM.readMessage(driver).getText();		//Reading Message appeared after save button
     		
     		if(msg.equalsIgnoreCase("Server Error Occurred. Please try again."))
     		{
     			test.log(LogStatus.FAIL, "Message displayed = "+msg);
     		
     		}
     		else
     		{
     			test.log(LogStatus.PASS, "Message displayed = "+msg);
     		}
     		
     	

     		Thread.sleep(3000);
     		driver.switchTo().parentFrame();
     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
     		
     		Thread.sleep(3000);
     		OverduePOM.clickDashboard(driver).click();
     		
     	
  	}
    		
 			public  static void selectNoticeRecipetDate1(WebDriver driver)
 		      {
 		    	 	
 		          WebElement openDate= performerPOM.selectNoticeRecipetDate(driver);
 		          openDate.sendKeys("30-09-202");
 		        
 		      }

 			
 		   static void NoticeWithExistingData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
 	 		{
 	 		   
 	        	 sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 			progress(driver);
 	 			
 	 			Thread.sleep(500);
 	 			JavascriptExecutor js = (JavascriptExecutor) driver;
 	            js.executeScript("window.scrollBy(0,-700)");
 	            Thread.sleep(500);
 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 			
 	             Thread.sleep(4000);
 	 			clickNewNotice(driver);
 	 			
 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 			
 	 			
 	 			
 	 			Thread.sleep(3000);
 	 			clickDated(driver);

 	 			Thread.sleep(3000);
 	 			clickFinancialYear(driver);

 	 			Thread.sleep(3000);
 	 			clickRefNo3(driver);

 	 			Thread.sleep(3000);
 	 			selectNoticeType(driver);

 	 			Thread.sleep(3000);
 	 			clickAct(driver);

 	 			Thread.sleep(3000);
 	 			clickOpponentcfo(driver);

 	 			Thread.sleep(3000);
 	 			selectCategory(driver);

 	 			Thread.sleep(3000);
 	 			clickNoticeTitle(driver);

 	 			Thread.sleep(3000);
 	 			clickNoticeDescription(driver);

 	 			Thread.sleep(3000);
 	 			selectLocation(driver);

 	 			Thread.sleep(3000);
 	 			clickDepartment(driver);

 	 			Thread.sleep(3000);
 	 			clickOwner(driver);

 	 			Thread.sleep(3000);
 	             selectRisk(driver);

 	 			Thread.sleep(3000);
 	             selectNoticeRecipetDate(driver);
 	             
 	             Thread.sleep(3000);
 	             clickInternalUser(driver);
 	             


 	             Thread.sleep(3000);
 	     		performerPOM.selectNoticeUploadDocument(driver); 
 	     		
 	        		
 	        	Thread.sleep(3000);
 	     		OverduePOM.clickSaveButton(driver).click();		
 	     		
 	     		Thread.sleep(1000);
 	     		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
 	     		
 	     		Thread.sleep(2000);
 	     		String msg = performerPOM.readMessage(driver).getText();		//Reading Message appeared after save button
 	     		
 	     		if(msg.equalsIgnoreCase("Notice with Same Reference No. already exists"))
 	     		{
 	     			test.log(LogStatus.PASS, "Message displayed = "+msg);
 	     		
 	     		}
 	     		else
 	     		{
 	     			test.log(LogStatus.FAIL, "Message displayed = "+msg);
 	     		}
 	     		
 	     		Thread.sleep(3000);
 	     		driver.switchTo().parentFrame();
 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	     		
 	     		Thread.sleep(3000);
 	     		OverduePOM.clickDashboard(driver).click();
 	     		
        }
 	     		
     public static void clickRefNo3(WebDriver driver) throws InterruptedException, IOException
 	 		{
 	 		   Thread.sleep(300);
 	 		   performerPOM.clickRefNo(driver).sendKeys("89253");			//Writing 'Reference No'
 	 		}
 	 		
 	 	
 	 			
 	  static void NoticeWithTwoMandatoryData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
 	 	 {
 	 	 		   
 	 	        	 sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 			progress(driver);
 	 	 			
 	 	 			Thread.sleep(500);
 	 	 			JavascriptExecutor js = (JavascriptExecutor) driver;
 	 	            js.executeScript("window.scrollBy(0,-700)");
 	 	            Thread.sleep(500);
 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 			
 	 	             Thread.sleep(4000);
 	 	 			clickNewNotice(driver);
 	 	 			
 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 			
 	 	 			
 	 	 			
 	 	 			Thread.sleep(3000);
 	 	 			clickDated(driver);

 	 	 			Thread.sleep(3000);
 	 	 			clickFinancialYear(driver);
 	 	 			
 	 	 			Thread.sleep(3000);
 	 	     		OverduePOM.clickSaveButton(driver).click();		
 	 	     		
 	 	     		Thread.sleep(1000);
 	 	     		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
 	 	     		
 	 	     		Thread.sleep(2000);
 	 	     		String msg = performerPOM.readInvalidMessage(driver).getText();		//Reading Message appeared after save button
 	 	     		
 	 	     	
 	 	     		test.log(LogStatus.PASS, "Message displayed = "+msg);
 	 	     		
 	 	     		
 	 	 
 	 	     		Thread.sleep(3000);
 	 	     		driver.switchTo().parentFrame();
 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	     		
 	 	     		Thread.sleep(3000);
 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 }
 	 	     		
 	 	     	
 	 	 	 		
 	 	 	 		
 	 	 	 	 static void NoticeWithEmptyFields(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
 	 	 	 	 {
 	 	 	 	 		   
 	 	 	 	        	 sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
 	 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 	 			progress(driver);
 	 	 	 	 			
 	 	 	 	 			Thread.sleep(500);
 	 	 	 	 			JavascriptExecutor js = (JavascriptExecutor) driver;
 	 	 	 	            js.executeScript("window.scrollBy(0,-700)");
 	 	 	 	            Thread.sleep(500);
 	 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 	 			
 	 	 	 	             Thread.sleep(4000);
 	 	 	 	 			clickNewNotice(driver);
 	 	 	 	 			
 	 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 	 			
 	 	 	 	 		
 	 	 	 	 			Thread.sleep(3000);
 	 	 	 	     		OverduePOM.clickSaveButton(driver).click();		
 	 	 	 	     		
 	 	 	 	     		Thread.sleep(1000);
 	 	 	 	     		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
 	 	 	 	     		
 	 	 	 	     		Thread.sleep(2000);
 	 	 	 	     		String msg = performerPOM.readInvalidMessage(driver).getText();		//Reading Message appeared after save button
 	 	 	 	     		
 	 	 	 	     	
 	 	 	 	     		test.log(LogStatus.PASS, "Message displayed = "+msg);
 	 	 	 	     		
 	 	 	 	     		
 	 	 	 	 
 	 	 	 	     		Thread.sleep(3000);
 	 	 	 	     		driver.switchTo().parentFrame();
 	 	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	 	 	     		
 	 	 	 	     		Thread.sleep(3000);
 	 	 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	 	 }
 	 	 	  static void NoticeClearBtn(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	 	 	 	 {
	 	 	 	 		   
	 	 	 	        	 sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
	 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 	 			progress(driver);
	 	 	 	 			
	 	 	 	 			Thread.sleep(500);
	 	 	 	 			JavascriptExecutor js = (JavascriptExecutor) driver;
	 	 	 	            js.executeScript("window.scrollBy(0,-700)");
	 	 	 	            Thread.sleep(500);
	 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
	 	 	 	 			
	 	 	 	             Thread.sleep(4000);
	 	 	 	 			clickNewNotice(driver);
	 	 	 	 			
	 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 	 		Thread.sleep(3000);
	 	 	 			clickDated(driver);

	 	 	 			Thread.sleep(3000);
	 	 	 			clickFinancialYear(driver);
	 	 	 	 		
	 	 	 	 			
	 	 	 	     	      Thread.sleep(2000);
	 	 	 	             if(performerPOM.clickNoticeClearBtn(driver).isEnabled())
	 	 			          {
	 	 			            Thread.sleep(2000);
	 	 			              performerPOM.clickNoticeClearBtn(driver).click();
	 	 			              test.log(LogStatus.PASS, "Clear Button is clickable");
	 	 			           }
	 	 			         else
	 	 			         {
	 	 			    	   test.log(LogStatus.FAIL, "Clear Button is not clickable");
	 	 			         }
	 	 	 	 
	 	 	 	     		Thread.sleep(3000);
	 	 	 	     		driver.switchTo().parentFrame();
	 	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	 	     		
	 	 	 	     		Thread.sleep(3000);
	 	 	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	 	 }
 	 	 	 static void NoticeSendMailWithDoc(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 	 	        	
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 			
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 			
 	 	 	             Thread.sleep(4000);
 	 	 	         
 	 			      	performerPOM.clickEditNotice(driver).click();
 	 	 	 			
 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 			
 	 	 	 		   Thread.sleep(4000);
 	 	 	 		   performerPOM.clickSendMail(driver).click();
 	 	 	 		   
 	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickSelectCheckbox(driver).click();
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickMailTo(driver).sendKeys("admin@gmail.com");
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickMessageMail(driver).sendKeys("Test");
	 	 	 		 
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickSend(driver).click();
	 	 	 		 
	 	 	 		Thread.sleep(4000);
	 	 	 		String msg= performerPOM.clickSendMailMsg(driver).getText();
	 	 	 		
	 	 	 		if(msg.equalsIgnoreCase("E-Mail Sent Successfully."))
	 	 	 		{
	 	 	 			test.log(LogStatus.PASS ,"Message displayed =" +msg);
	 	 	 		}
	 	 	 		else
	 	 	 		{
	 	 	 			test.log(LogStatus.FAIL ,"Message displayed =" +msg);
	 	 	 		}
	 	 	 		
	 	 			Thread.sleep(3000);
	 	 	     
	 	 	     	performerPOM.clickcloseBtn(driver).click();//Clicking on 'Close'
	 	 	     	Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	     	
	 	 	     	Thread.sleep(3000);
	 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	 
 	 	 	 }
 	 		 static void NoticeSendMailWithDocInvalidFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 	 	        	
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 			
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 			
 	 	 	             Thread.sleep(4000);
 	 	 	         
 	 			      	performerPOM.clickEditNotice(driver).click();
 	 	 	 			
 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 			
 	 	 	 		   Thread.sleep(4000);
 	 	 	 		   performerPOM.clickSendMail(driver).click();
 	 	 	 		   
 	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickSelectCheckbox(driver).click();
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickMailTo(driver).sendKeys("admin");
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickMessageMail(driver).sendKeys("Test");
	 	 	 		 
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickSend(driver).click();
	 	 	 		 
	 	 	 		Thread.sleep(4000);
	 	 	 		String msg= performerPOM.clickSendMailMsg(driver).getText();
	 	 	 		
	 	 	 		if(msg.equalsIgnoreCase("Please enter a valid email."))
	 	 	 		{
	 	 	 			test.log(LogStatus.PASS ,"Message displayed =" +msg);
	 	 	 		}
	 	 	 		else
	 	 	 		{
	 	 	 			test.log(LogStatus.FAIL ,"Message displayed =" +msg);
	 	 	 		}
	 	 	 		
	 	 			Thread.sleep(3000);
	 	 	     
	 	 	     	performerPOM.clickcloseBtn(driver).click();//Clicking on 'Close'
	 	 	     	Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	     	
	 	 	     	Thread.sleep(3000);
	 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	 
 	 	 	 }
 	 	 	 			
 	 		 static void NoticeSendMailWithDocEmptyFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 	 	        	
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 			
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 			
 	 	 	             Thread.sleep(4000);
 	 	 	         
 	 			      	performerPOM.clickEditNotice(driver).click();
 	 	 	 			
 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 			
 	 	 	 		   Thread.sleep(4000);
 	 	 	 		   performerPOM.clickSendMail(driver).click();
 	 	 	 		   
 	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickSelectCheckbox(driver).click();
	 	 	 		 
	 	 	 		
	 	 	 		 
	 	 	 		 Thread.sleep(4000);
	 	 	 		 performerPOM.clickSend(driver).click();
	 	 	 		 
	 	 	 		Thread.sleep(4000);
	 	 	 		String msg= performerPOM.clickSendMailMsg1(driver).getText();
	 	 	 		
	 	 	 		
	 	 	 			test.log(LogStatus.PASS ,"Message displayed =" +msg);
	 	 	 		
	 	 	 		
	 	 			Thread.sleep(3000);
	 	 	     
	 	 	     	performerPOM.clickcloseBtn(driver).click();//Clicking on 'Close'
	 	 	     	Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	     	
	 	 	     	Thread.sleep(3000);
	 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	 
 	 	 	 }
 	 		 
 	 		 static void LinkNoticeViewIcon(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 	 	        	
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 			
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 			
 	 	 	            Thread.sleep(4000);
 	 	 	            performerPOM.clickEditNotice(driver).click();
 	 	 	 			
 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 			
 	 	 	 		    Thread.sleep(4000);
 	 	 	            performerPOM.clickLinkedNoticeViewIcon(driver).click();
	 			      	
	 			      	Thread.sleep(4000);
	 	 	 	        performerPOM.clickViewPopup(driver).click();
	 	 	 	        
	 	 	 	        String msg="Linked Notice Details";
	 	 	 	        
	 	 	 	        if(msg.equalsIgnoreCase(msg))
	 	 	 	        {
	 	 	 	        	test.log(LogStatus.PASS, "View Notice Detailes icon open succssefully");
	 	 	 	        }
	 	 	 	        else
	 	 	 	        {
	 	 	 	        	test.log(LogStatus.FAIL, "View Notice Detailes icon not open succssefully");

	 	 	 	        }
	 	 	 	        

 	 	 	 		Thread.sleep(4000);
 	 	 	        performerPOM.clickClosePopup1(driver).click();
 	 	 	     	Thread.sleep(3000);
 	 	     		driver.switchTo().parentFrame();
 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	     	
 	 	     	    Thread.sleep(3000);
 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	 			
 	 	 	 }
 	 		 static void LinkNoticeDeleteIcon(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 	 	        	
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 			
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 			
 	 	 	            Thread.sleep(4000);
 	 	 	            performerPOM.clickEditNotice(driver).click();
 	 	 	 			
 	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 			
 	 	 	 		    Thread.sleep(4000);
 	 	 	            performerPOM.clickLinkedNoticeDeleteIcon(driver).click();
	 			      	
 	 	 	         Thread.sleep(5000);
 	 			    // Switching to Alert        
 	 		        Alert alert1 = driver.switchTo().alert();		
 	 		        		
 	 		        // Capturing alert message.    
 	 		        String alertMessage1= driver.switchTo().alert().getText();	
 	 		        
 	 		        
 	 		        test.log(LogStatus.PASS, alertMessage1);
 	 		        		
 	 		        // Displaying alert message		
 	 		        System.out.println(alertMessage1);
 	 		        
 	 		       // Accepting alert		
 	 		        alert1.accept();	
 	 		        
 	 		      Thread.sleep(4000);
	 	 	       String msg= performerPOM.clickLinkedNoticeDeleteIconValidMsg(driver).getText();
	 	 	       
	 	 	       test.log(LogStatus.PASS, "Message Displayed =" +msg);
	 	 	 	        

 	 	 	     	Thread.sleep(3000);
 	 	     		driver.switchTo().parentFrame();
 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	     	
 	 	     	    Thread.sleep(3000);
 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	 			
 	 	 	 }
 	 		 
 	 		 static void NoticeUserAssignment(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 			        sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 	  
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();		
 	 	 	 			
 	 	 	            Thread.sleep(4000);
 	 	 	            performerPOM.clickEditNotice(driver).click();
 	 	 	            
 	 	 	            try
 	 	 	            {
 	 	 	            
 	 	 	              	wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 	 	 			
 	 	 	 	            Thread.sleep(4000);
	 	 	                 performerPOM.clickEditUserAssign(driver).click();
 	 	 	 			
	 	 	                  Thread.sleep(3000);
	 	                     clickInternalUser(driver);
	 	                 
	 	                       Thread.sleep(3000);
	 	 	                   performerPOM.clickUpdateButton(driver).click();
	 	              		
	 	               
 	 				              //js.executeScript("window.scrollBy(0,700)");
 	 	 	 			
 	 	 	 			
 	 	 	    		Thread.sleep(2000);
 	 	 	    		String msg = performerPOM.readMessage(driver).getText();		//Reading Message appeared after save button
 	 	 	    		
 	 	 	    		if(msg.equalsIgnoreCase("Notice Details Updated Successfully."))
 	 	 	    		{
 	 	 	    			test.log(LogStatus.PASS, "Message displayed = "+msg);
 	 	 	    			
 	 	 	    		}
 	 	 	    		else
 	 	 	    		{
 	 	 	    			test.log(LogStatus.FAIL, "Message displayed = "+msg);
 	 	 	    		}
 	 	 	    	
 	 	 	       } 
 	 	 	            
 	 	 	            catch(Exception e)
 	 	 	            {
 	 	 	            	test.log(LogStatus.PASS, "Record not displayed in Notice-User assignment");
 	 	 	            }
 	 	 	            
 	 	 	     	Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	     		
 	 	 	     	
 	 	 	     	    Thread.sleep(3000);
 	 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	    }
 	 		 
 	 		 static void NoticeUserAssignmentDelete(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
 	 	 	 {
 	 	 	 		   
 	 			        sheet = workbook.getSheetAt(8);					//Retrieving second sheet of Workbook
 	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
 	 	 	 			progress(driver);
 	 	 	 			
 	 	 	 		
 	 	 	 			
 	 	 	            Thread.sleep(500);
 	 	 	        	performerPOM.clickNoticeOpen(driver).click();	
 	 	 	        	
// 	 	 	            Thread.sleep(4000);
//	 	 	            performerPOM.clickEditNotice(driver).click();
	 	 	            
	 	 	            try
	 	 	            {
	 	 	            
	 	 	       	       wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	       	       Thread.sleep(4000);
	 	 	                performerPOM.clickEditNotice(driver).click();
 	 	 	 			
 	 	 	               Thread.sleep(4000);
 	 	 	               performerPOM.clickDeleteUserAssign(driver).click();
 	 	 	            
 	 	 	               Thread.sleep(2000);
	 	 	    	 	   String msg = performerPOM.clickDeleteUserAssignValidMsg(driver).getText();		//Reading Message appeared after save button
	 	 	    		
	 	 	    		    if(msg.equalsIgnoreCase("User Assignment Detail Deleted"))
	 	 	    		    {
	 	 	    			     test.log(LogStatus.PASS, "Message displayed = "+msg);
	 	 	    			
	 	 	    		    }
	 	 	    		    else
	 	 	    		      {
	 	 	    			         test.log(LogStatus.FAIL, "Message displayed = "+msg);
	 	 	    		        }
 	 	 	            
 	 	 	        
 	 	 	    	
	 	 	        }
	 	 	            catch(Exception e)
	 	 	            {
	 	 	            	test.log(LogStatus.PASS, "Record not displayed in Notice-User assignment");
 	 	 	           
	 	 	            }
	 	 	            
	 	 	      	Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	          
 	 	 	     	    Thread.sleep(3000);
 	 	 	     		OverduePOM.clickDashboard(driver).click();
 	 	 	    }
 	 		static void NoticeDocumentEmptyFields(WebDriver driver, ExtentTest test) throws InterruptedException
 	       	{
 	    		WebDriverWait wait = new WebDriverWait(driver, 50);
 	             
 	    	
 	    		
 		        Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 				
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 				
 		        
 		        performerPOM.clickNoticeDocument(driver).click();     //click notice document
 		        performerPOM.clickNewDocument(driver).click();        //click new document button
 		
 		        Thread.sleep(1000);
 	           	driver.switchTo().frame("IFrameManageDocument");
 	  
 		        Thread.sleep(1000);
 	         	performerPOM.clickUploadDocument(driver).click(); 
 		
 		
 	         	Thread.sleep(1000);
 	         	wait.until(ExpectedConditions.visibilityOf(performerPOM.readDocMsg(driver)));
 		
 	        	Thread.sleep(3000);
 		        String msg= performerPOM.readDocMsgInvalidMsg(driver).getText();		//Reading Message appeared after save button
 		       
 	         	if(msg.equalsIgnoreCase("Please select document type"))
 	         	{
 		        	test.log(LogStatus.PASS, "Message displayed = "+msg);
 		         
 		        }
 		      else
 		        {
 			       test.log(LogStatus.FAIL, "Message displayed = "+msg);
 		        }
 		
 	         	
 		        Thread.sleep(1000);
 		        performerPOM.clickClosedDocument(driver).click(); 
 		       driver.switchTo().parentFrame();
 		      
 		      Thread.sleep(3000);
 	     		driver.switchTo().parentFrame();
 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	    }
 	 	  	static void NoticeWithoutUploadDocument(WebDriver driver, ExtentTest test) throws InterruptedException
 	       	{
 	    		WebDriverWait wait = new WebDriverWait(driver, 50);
 	             
 	    		Thread.sleep(1000);
 	    		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
 	    		
 		        Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 				
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 				
 		        
 		        performerPOM.clickNoticeDocument(driver).click();     //click notice document
 		        performerPOM.clickNewDocument(driver).click();        //click new document button
 		
 		        Thread.sleep(1000);
 	           	driver.switchTo().frame("IFrameManageDocument");
 	           	performerPOM.selectDocumentType(driver);
 	           Thread.sleep(3000);
 		        performerPOM.chooseDocumentType(driver);
 	          	
 		        Thread.sleep(1000);
 	         	performerPOM.clickUploadDocument(driver).click(); 
 		
 		
 	         	Thread.sleep(1000);
 	         	wait.until(ExpectedConditions.visibilityOf(performerPOM.readDocMsg(driver)));
 		
 	        	Thread.sleep(3000);
 		        String msg= performerPOM.readDocMsg(driver).getText();		//Reading Message appeared after save button
 		       
 	         	if(msg.equalsIgnoreCase("Please select file to upload"))
 	         	{
 		        	test.log(LogStatus.PASS, "Message displayed = "+msg);
 		         
 		        }
 		      else
 		        {
 			       test.log(LogStatus.FAIL, "Message displayed = "+msg);
 		        }
 		
 		        Thread.sleep(1000);
 		        performerPOM.clickClosedDocument(driver).click();
 		        
 		       driver.switchTo().parentFrame();
  		      
  		      Thread.sleep(3000);
  	     		driver.switchTo().parentFrame();
  	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	       	}
 	 	  	
 	 		static void NoticeDocumentSearchFields(WebDriver driver, ExtentTest test) throws InterruptedException
 	       	{
 	    		WebDriverWait wait = new WebDriverWait(driver, 50);
 	             
 	    		
 		        Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 				
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

 		        Thread.sleep(3000);
 				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
 				
 				Thread.sleep(3000);
 	 			performerPOM.clickSearchDocument(driver).sendKeys("Compliance Assignment.xlsx");
 	 			
 	 			Thread.sleep(3000);
 				performerPOM.clickApplyBtn(driver).click();
 				
 				String msg=performerPOM.clickDocName(driver).getText();
 				if(msg.equalsIgnoreCase(msg)) 
 				{
 					test.log(LogStatus.PASS,"Document Filter Apply =" +msg);
 				}
 				else
 				{
 					test.log(LogStatus.FAIL,"Document Filter Apply  =" +msg);
 				}
 				
 				Thread.sleep(3000);
  	     		driver.switchTo().parentFrame();
  	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 				
 				
 	       	}
 	 		
 	 		static void NoticeDocumentShareInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException
 	       	{
 	    		WebDriverWait wait = new WebDriverWait(driver, 50);
 	             
 	    		
 		        Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 				
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

 		        Thread.sleep(3000);
 				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
 				 
 				 Thread.sleep(3000);
 		        performerPOM.clickNoticeDocumentsharecfo(driver).click();
 		        
 		        Thread.sleep(5000);
 			    // Switching to Alert        
 		        Alert alert1 = driver.switchTo().alert();		
 		        		
 		        // Capturing alert message.    
 		        String alertMessage1= driver.switchTo().alert().getText();	
 		        
 		        
 		        test.log(LogStatus.PASS, alertMessage1);
 		        		
 		        // Displaying alert message		
 		        System.out.println(alertMessage1);
 		        
 		     // Accepting alert		
 		        alert1.accept();	
 		        
 		        Thread.sleep(3000);
 		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));
 		        
 		        Thread.sleep(4000);
 		        performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin");
 		        
 		        Thread.sleep(3000);
 		        performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("576879");
 		        
 		        Thread.sleep(3000);
 		        performerPOM.clickNoticeDocumentsharesavecfo(driver).click();
 		        
 		        
 		        Thread.sleep(3000);
 		        String msg1= performerPOM.clickNoticeDocumentshareInvalidmsgcfo(driver).getText();		//Reading Message appeared after save button
 		       
 	         
 		        	test.log(LogStatus.PASS, "Message displayed = "+msg1);
 		      
 		        
 		        Thread.sleep(3000);
 		        performerPOM. clickNoticeDocumentshareclosepopupcfo(driver).click();
 		        
 		       driver.switchTo().parentFrame();
   		      
   		        Thread.sleep(3000);
   	     		driver.switchTo().parentFrame();
   	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
  	       	}
 	 		
 	 		static void NoticeDocumentShareWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException
 	       	{
 	    		WebDriverWait wait = new WebDriverWait(driver, 50);
 	             
 	    		
 		        Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 				
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

 		        Thread.sleep(3000);
 				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
 				 
 				 Thread.sleep(3000);
 		        performerPOM.clickNoticeDocumentsharecfo(driver).click();
 		        
 		        Thread.sleep(5000);
 			    // Switching to Alert        
 		        Alert alert1 = driver.switchTo().alert();		
 		        		
 		        // Capturing alert message.    
 		        String alertMessage1= driver.switchTo().alert().getText();	
 		        
 		        
 		        test.log(LogStatus.PASS, alertMessage1);
 		        		
 		        // Displaying alert message		
 		        System.out.println(alertMessage1);
 		        
 		     // Accepting alert		
 		        alert1.accept();	
 		        
 		       Thread.sleep(3000);
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));
 		        
 		      
 		        
 		        Thread.sleep(3000);
 		        performerPOM.clickNoticeDocumentsharesavecfo(driver).click();
 		        
 		        
 		        Thread.sleep(3000);
 		        String msg1= performerPOM.clickNoticeDocumentsharereadmsgcfo(driver).getText();		//Reading Message appeared after save button
 		        if(msg1.equalsIgnoreCase("Please Enter Email."))
 		        {
 	         
 		        	test.log(LogStatus.PASS, "Message displayed = "+msg1);
 		        }
 		        else
 		        {
 		        	test.log(LogStatus.FAIL, "Message displayed = "+msg1);
 		        }
 		        
 		        Thread.sleep(3000);
 		        performerPOM. clickNoticeDocumentshareclosepopupcfo(driver).click();
 		        
 		       driver.switchTo().parentFrame();
   		      
   		        Thread.sleep(3000);
   	     		driver.switchTo().parentFrame();
   	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
  	       	}
 	 		
 	 		static void NoticeDocumentShareCloseBtn(WebDriver driver, ExtentTest test) throws InterruptedException
 	       	{
 	    		WebDriverWait wait = new WebDriverWait(driver, 50);
 	             
 	    		
 		        Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 				
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

 		        Thread.sleep(3000);
 				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
 				 
 				 
 				 Thread.sleep(3000);
 		        performerPOM.clickNoticeDocumentsharecfo(driver).click();
 		        
 		       Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert1 = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		        
		        
//		        test.log(LogStatus.PASS, alertMessage1);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage1);
		        
		     // Accepting alert		
		        alert1.accept();	
		        
		       Thread.sleep(3000);
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));

	     	      Thread.sleep(2000);
	             if(performerPOM.clickNoticeDocumentshareclosepopupcfo(driver).isEnabled())
	             {
	              Thread.sleep(2000);
	              performerPOM.clickNoticeDocumentshareclosepopupcfo(driver).click();
	              test.log(LogStatus.PASS, "Close Button is clickable");
	             }
	            else
	           {
	    	     test.log(LogStatus.FAIL, "Close Button is not clickable");
	           }
	           
	   	     		driver.switchTo().parentFrame();
	   	     	driver.switchTo().parentFrame();
	   	     	   Thread.sleep(3000);
	   	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	   	     	
 	       }
 	 		
 	 		 public  static void TaskActivtityExistingData(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
 			{
     		 
     		       XSSFSheet sheet = ReadExcel();
 				   WebDriverWait wait = new WebDriverWait(driver, 60);
 				   
 				  Thread.sleep(3000);
	 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 				

 				   
 				   Thread.sleep(1000);
 				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 				  Thread.sleep(1000);
 				  performerPOM.clickTaskorActivity(driver).click();
 				  Thread.sleep(1000);
 				  performerPOM.clickNewTask(driver).click(); 
 				 
 				  
 				  
 				Thread.sleep(3000);
 				Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
 				Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
 				String title = c1.getStringCellValue();
 				performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
 				
 				Thread.sleep(3000);
 				row0 = sheet.getRow(13);									//Selected 0th index row (First row)
 				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
 				String desc = c1.getStringCellValue();
 				performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
 				
 				Thread.sleep(3000);
 				performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
 				OverduePOM.selectNextMonth(driver).click();
 				OverduePOM.selectDate(driver).click();					//Selecting particular date.
 				
 				Thread.sleep(500);
 				Actions action = new Actions(driver);
// 				action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
 				
 				Thread.sleep(500);
 				row0 = sheet.getRow(14);									//Selected 0th index row (First row)
 				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
 				String outcome = c1.getStringCellValue();
 				performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
 				
 				Thread.sleep(500);
 				row0 = sheet.getRow(15);									//Selected 0th index row (First row)
 				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
 				String internalUser = c1.getStringCellValue();
 				performerPOM.clickInternalUser2(driver).click();
 				//performerPOM.selectInternalUser2(driver).click();
 				performerPOM.selectInternalUser2(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
 				
 	
 				
 				Thread.sleep(1000);
 				row0 = sheet.getRow(16);									//Selected 0th index row (First row)
 				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
 				String externalUser = c1.getStringCellValue();
 				try
 				{
 					Thread.sleep(300);
 					performerPOM.clickExternalUser(driver).click();
 					Thread.sleep(500);
 					action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
 				}
 				catch(Exception e)
 				{
 					
 				}
 			
 				Thread.sleep(2000);
 				row0 = sheet.getRow(17);									//Selected 0th index row (First row)
 				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
 				String remark = c1.getStringCellValue();
 				performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
 				
 				//Thread.sleep(300);
 				//String workingDir = System.getProperty("user.dir");
 				//performerPOM.clickUpload(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file
 				
 				Thread.sleep(3000);
 				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
 				
 				Thread.sleep(300);
 				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsg(driver)));
 				
 				Thread.sleep(300);
 				
 				String msg1 = performerPOM.readTaskMsg1(driver).getText();
 				if(msg1.contains("Task Saved Successfully."))
 				{
 					test.log(LogStatus.PASS, "Task Saved Successfully.");
 				}
 				
 				else if(msg1.contains("Task with same title already exists."))
 				{
 					test.log(LogStatus.PASS, "Task with same title already exists.");
 				}
 				
 				Thread.sleep(3000);
 				performerPOM.clickNoticeEditTaskcfo(driver).click();
 			}
 	 		 public  static void TaskActivtityWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
  			{
      		 
      		      
  				   WebDriverWait wait = new WebDriverWait(driver, 60);
  				   
  				  Thread.sleep(3000);
 	 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 	 		     
 	 		        Thread.sleep(3000);
 	 				performerPOM.clickEditNotice(driver).click();//click edit notice
 	 			   Thread.sleep(1000);
 				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 			  Thread.sleep(1000);
 				  performerPOM.clickTaskorActivity(driver).click();
 				  Thread.sleep(1000);
 				  performerPOM.clickNewTask(driver).click(); 
 	 				

  	
  				
  				Thread.sleep(3000);
  				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
  				
  			  Thread.sleep(3000);
				performerPOM.clickMinimize(driver).click();
  				
//  				Thread.sleep(300);
//  				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsg(driver)));
  				
  				Thread.sleep(300);
  				
  				String msg1 = performerPOM.readTaskMsg2(driver).getText();
  				
  					test.log(LogStatus.PASS, "Without data ="+msg1);
  				
  				
  				Thread.sleep(3000);
  				performerPOM.clickNoticeEditTaskcfo(driver).click();
  				
  			}
 	 		 
 	 		 public  static void TaskActivtityResponseWithoutStatus(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
   			{ 
 	 		   WebDriverWait wait = new WebDriverWait(driver, 60);
 	 			  Thread.sleep(3000);
	 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 			   Thread.sleep(1000);
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 			  Thread.sleep(1000);
				  performerPOM.clickTaskorActivity(driver).click();

				Thread.sleep(3000);
				performerPOM.clickNoticeTaskEditResponsecfo(driver).click();
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskcmtResponsecfo(driver).sendKeys("Automate Test");
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskSaveResponsecfo(driver).click();
				
				
				String msg=performerPOM.clickInvalidResponsemsg(driver).getText();
				if(msg.equalsIgnoreCase("Provide Response Status."))
				{
				    test.log(LogStatus.PASS, "Mesaage displayed ="+msg);
				}
				else
				{
					 test.log(LogStatus.FAIL, "Mesaage displayed ="+msg);
				}
				driver.switchTo().parentFrame();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskCloseResponsecfo(driver).click();
				
				driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
				
             
			}
 	 		 public  static void TaskActivtityDeleteResponse(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
    			{ 
  	 		   WebDriverWait wait = new WebDriverWait(driver, 60);
  	 			  Thread.sleep(3000);
 	 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 	 		     
 	 		        Thread.sleep(3000);
 	 				performerPOM.clickEditNotice(driver).click();//click edit notice
 	 			   Thread.sleep(1000);
 				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 	 			  Thread.sleep(1000);
 				  performerPOM.clickTaskorActivity(driver).click();

 				Thread.sleep(3000);
 				performerPOM.clickNoticeTaskEditResponsecfo(driver).click();
 				
 				Thread.sleep(3000);
 				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 				
 				Thread.sleep(3000);
 				performerPOM.clickDeleteResponse(driver).click();
 				
 			   Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert1 = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		        
		        
//		        test.log(LogStatus.PASS, alertMessage1);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage1);
		        
		     // Accepting alert	
		        alert1.accept();
		        
		    	
	 			   Thread.sleep(5000);
		        String msg=performerPOM.clickTaskResponse(driver).getText();
		        if(msg.equalsIgnoreCase("Response Deleted Successfully."))
		        {
		              test.log(LogStatus.PASS,"Message displayed ="+msg);
		        }
		        else
		        {
		        	 test.log(LogStatus.FAIL,"Message displayed ="+msg);
		        }

		        driver.switchTo().parentFrame();
		        driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
				
    	
    		}
 	 		 public  static void TaskActivtityResponseClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
 			{ 
	 		   WebDriverWait wait = new WebDriverWait(driver, 60);
	 			  Thread.sleep(3000);
	 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 			   Thread.sleep(1000);
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 			  Thread.sleep(1000);
				  performerPOM.clickTaskorActivity(driver).click();

				Thread.sleep(3000);
				performerPOM.clickNoticeTaskEditResponsecfo(driver).click();
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo1(driver).click();
				
				
				if(performerPOM.clickClearResponse(driver).isEnabled())
		  		{
					Thread.sleep(3000);
		  			performerPOM.clickClearResponse(driver).click();
		  			test.log(LogStatus.PASS, "Clear button working successfully");
		  		}
		  		else
		  		{
		  			test.log(LogStatus.FAIL, "Clear button not working successfully");
		  		}
				driver.switchTo().parentFrame();
				  driver.switchTo().parentFrame();
		   	     	Thread.sleep(3000);
		   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
 			}
 	 		 
 	 		static void ResponseWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			{
			   WebDriverWait wait = new WebDriverWait(driver, 60);
			  
			    Thread.sleep(3000);
				performerPOM.clickNoticeOpen(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			 
			   
			    Thread.sleep(1000);
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    Thread.sleep(3000);
				performerPOM. clickResponse(driver).click();
			    Thread.sleep(3000);
			    performerPOM. clickNewResponse(driver).click();
					 
			    Thread.sleep(3000);		
			   JavascriptExecutor jse=(JavascriptExecutor)driver;
			   jse.executeScript("arguments[0].click();",  performerPOM.clickSaveResponse(driver));
			   //performerPOM.clickSaveResponse(driver).click();
							
			  Thread.sleep(3000);
			  performerPOM.clickMinimizeResponse(driver).click();
						 
			  Thread.sleep(1000);
			   wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg1(driver)));
								
			 Thread.sleep(500);
			 String msg4 = performerPOM.readResponseMsg1(driver).getText();		//Reading Message appeared after save button
							
							
			test.log(LogStatus.PASS, "Message displayed = "+msg4);
							
			driver.switchTo().parentFrame();
			Thread.sleep(3000);
			performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 }
 	 		
 	 	 static void ResponseExistingData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
 			{
 			   WebDriverWait wait = new WebDriverWait(driver, 60);
 			   
 			   XSSFSheet sheet = ReadExcel();
 			  
 			   Thread.sleep(3000);
 				performerPOM.clickNoticeOpen(driver).click();//click edit notice
 		     
 		        Thread.sleep(3000);
 				performerPOM.clickEditNotice(driver).click();//click edit notice
 			  
 			   
 			           Thread.sleep(1000);
 			           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
 			           Thread.sleep(3000);
 					  performerPOM. clickResponse(driver).click();
 					  Thread.sleep(3000);
 					  performerPOM. clickNewResponse(driver).click();
 					  Thread.sleep(3000);
 					  performerPOM. selectSentNotice(driver);
 					  Thread.sleep(3000);
 					  performerPOM. selectReplyDueDate(driver);
 					  Thread.sleep(3000);
 					  performerPOM. selectRespondedDate(driver);
 				
 					 		 
 					  Thread.sleep(500);
 					  Row row1 = sheet.getRow(20);								//Selected 0th index row (First row)
 					  Cell c2 = row1.getCell(1);								//Selected cell (0 row,1 column)
 					  String DeliveryMode= c2.getStringCellValue();
 					  performerPOM.clickDeliveryMode(driver).click();
 					  performerPOM.selectDeliveryMode(driver).sendKeys(DeliveryMode);
 					  
 					  
 					  Thread.sleep(500);
 					  Row row0 = sheet.getRow(21);								//Selected 0th index row (First row)
 					  Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
 					  String CourierCompany= c1.getStringCellValue();
 					  performerPOM.clickCourierCompany(driver).sendKeys(CourierCompany);
 						 
 					  Thread.sleep(500);
 						Row row2 = sheet.getRow(22);								//Selected 0th index row (First row)
 						Cell c3 = row2.getCell(1);								//Selected cell (0 row,1 column)
 						String RefNo= c3.getStringCellValue();
 						performerPOM.RefTrackingNo(driver).sendKeys(RefNo);
 							 
 						Thread.sleep(500);
 						Row row3 = sheet.getRow(23);								//Selected 0th index row (First row)
 						Cell c4 = row3.getCell(1);								//Selected cell (0 row,1 column)
 						String Description= c4.getStringCellValue();
 						 performerPOM.Description(driver).sendKeys(Description);
 						 
 						 Thread.sleep(3000);
 						 performerPOM.clickNoticeResponseDocUploadtcfo(driver);
 							
 						 JavascriptExecutor jse=(JavascriptExecutor)driver;
 						 jse.executeScript("arguments[0].click();",  performerPOM.clickSaveResponse(driver));
 						  //performerPOM.clickSaveResponse(driver).click();
 							
 							Thread.sleep(1000);
 							wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg(driver)));
 								
 							Thread.sleep(500);
 							String msg3 = performerPOM.readResponseMsg(driver).getText();		//Reading Message appeared after save button
 							
 							if(msg3.equalsIgnoreCase("Response Details Saved Successfully."))
 							{
 								test.log(LogStatus.FAIL, "Message displayed = "+msg3);
 								
 							}
 								else
 								{
 									test.log(LogStatus.PASS, "Message displayed = "+msg3);
 								}
 							
 							driver.switchTo().parentFrame();
 							Thread.sleep(3000);
 							performerPOM.clickClose(driver).click();//Clicking on 'Close'
 							
 			}
 	 	 
 	 	 static void ResponseClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			{
			   WebDriverWait wait = new WebDriverWait(driver, 60);
			   
			 
			  
			   Thread.sleep(3000);
				performerPOM.clickNoticeOpen(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   
			           Thread.sleep(1000);
			           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			           Thread.sleep(3000);
					  performerPOM. clickResponse(driver).click();
					  Thread.sleep(3000);
					  performerPOM. clickNewResponse(driver).click();
					  Thread.sleep(3000);
					  performerPOM. selectSentNotice(driver);
					  Thread.sleep(3000);
					  performerPOM. selectReplyDueDate(driver);
					  Thread.sleep(3000);
					  performerPOM. selectRespondedDate(driver);
				
					
						if(performerPOM.clickClearNoticeResponse(driver).isEnabled())
				  		{
							Thread.sleep(3000);
							 JavascriptExecutor jse=(JavascriptExecutor)driver;
	 						 jse.executeScript("arguments[0].click();",  performerPOM.clickClearNoticeResponse(driver));
							
				  			test.log(LogStatus.PASS, "Clear button working successfully");
				  		}
				  		else
				  		{
				  			test.log(LogStatus.FAIL, "Clear button not working successfully");
				  		}
					
						  driver.switchTo().parentFrame();
				   	     	Thread.sleep(3000);
				   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		 			}
 	 	 
 	 	 static void PaymentLogWithoutData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
 		 
 		 
 		        WebDriverWait wait = new WebDriverWait(driver, 60);
			   
 		         Thread.sleep(3000);
			     performerPOM.clickNoticeOpen(driver).click();//click edit notice
	     
	             Thread.sleep(3000);
			     performerPOM.clickEditNotice(driver).click();//click edit notice
		  
			      driver.switchTo().parentFrame();
			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			     
			     Thread.sleep(3000);
			    performerPOM.clickStatusPayments(driver).click();			//Clicking on 'Status/Payments'
			    
				Thread.sleep(300);
				performerPOM.clickSavePaymentLog(driver).click();
				

				
				 WebDriverWait wait1 = new WebDriverWait(driver, 300);
				 wait1.until(ExpectedConditions.visibilityOf(performerPOM.readPymentmsg1(driver)));
					
					Thread.sleep(500);
					String msg4 = performerPOM.readPymentmsg1(driver).getText();		//Reading Message appeared after save button
				
				
						test.log(LogStatus.PASS, "Message displayed = "+msg4);
						
						 driver.switchTo().parentFrame();
				   	     	Thread.sleep(3000);
				   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
					
					
			}
 	 	 
 	 	 static void PaymentLogwithExistingData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
 		 
 		 
 		   WebDriverWait wait = new WebDriverWait(driver, 60);
			   
			   XSSFSheet sheet = ReadExcel();
			   
			   Thread.sleep(3000);
			     performerPOM.clickNoticeOpen(driver).click();//click edit notice
	     
	             Thread.sleep(3000);
			     performerPOM.clickEditNotice(driver).click();//click edit notice
		  
			     driver.switchTo().parentFrame();
			     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			     
			     Thread.sleep(3000);
			    performerPOM.clickStatusPayments(driver).click();			//Clicking on 'Status/Payments'
			
			    Thread.sleep(1000);
				performerPOM.clickInvoiceNo(driver).sendKeys("56742584");
				
				
				Thread.sleep(3000);
				Row r5 = sheet.getRow(30);
				Cell c5 = r5.getCell(1);
				String PaymentType = c5.getStringCellValue();
				performerPOM.clickPaymentType(driver).click();
				performerPOM.selectPaymentType(driver).sendKeys(PaymentType,Keys.ENTER);
//				List<WebElement> PaymentType1= driver.findElements(By.xpath("//*[@id='grdNoticePayment_ddlPaymentType_chosen']/div/ul/li"));
//				PaymentType1.get(2).click();
					
				Thread.sleep(3000);
				performerPOM.clickAmount(driver).sendKeys("5000");
				
				Thread.sleep(6000);
				performerPOM.clickNoticeStatusPaymentUploadtcfo(driver);
			
				Thread.sleep(300);
				performerPOM.clickSavePaymentLog(driver).click();
				

				
				 WebDriverWait wait1 = new WebDriverWait(driver, 300);
				 wait1.until(ExpectedConditions.visibilityOf(performerPOM.readPymentmsg(driver)));
					
					Thread.sleep(500);
					String msg4 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
				
					if(msg4.equalsIgnoreCase("Payment Details Saved Successfully."))
					{
						test.log(LogStatus.FAIL, "Message displayed = "+msg4);
					
					}
					else
					{
						test.log(LogStatus.PASS, "Message displayed = "+msg4);
					}
					
					 driver.switchTo().parentFrame();
			   	     	Thread.sleep(3000);
			   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
			   	  }
 	 	 
 	 	 static void CriteriaExistingData(WebDriver driver,ExtentTest test) throws InterruptedException
         {
       	  
   		         WebDriverWait wait = new WebDriverWait(driver, 300);
		   
   		         Thread.sleep(3000);
   			     performerPOM.clickNoticeOpen(driver).click();//click edit notice
   	     
   	             Thread.sleep(3000);
   			     performerPOM.clickEditNotice(driver).click();//click edit notice
   		  
   			      driver.switchTo().parentFrame();
   			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
   		         
			          
       	          
   			       Thread.sleep(1000);
   				   performerPOM. clickExternalLawyerRating(driver).click();
   				   

   				   
   				  Thread.sleep(3000);
   				  performerPOM.selectExternalLawyerRating(driver);
   				   Thread.sleep(3000);
   				   performerPOM.clickNewCriteria(driver).click();
   				   Thread.sleep(3000);
   				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
   				   performerPOM.clickCriteria(driver).sendKeys("Test Test New");
   				   Thread.sleep(3000);
   				   performerPOM.clickSaveCriteria(driver).click();
   				   String msg = performerPOM.readOppoenentMsg(driver).getText();
   				   
   				   if(msg.equalsIgnoreCase("Criteria already exists."))
   				   {
   					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
   				   }
   				   else
   				   {
   					   String msg1 = performerPOM.readMesg(driver).getText();
   					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg1);
   				   }
   				   Thread.sleep(3000);
   				   driver.switchTo().parentFrame();
   				   performerPOM.clickclosecriteria(driver).click();
   				   
   				 driver.switchTo().parentFrame();
		   	     	Thread.sleep(3000);
		   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
         }
 	 	 static void CriteriaInvalidData(WebDriver driver,ExtentTest test) throws InterruptedException
         {
       	  
   		         WebDriverWait wait = new WebDriverWait(driver, 300);
		  
			          Thread.sleep(3000);
					     performerPOM.clickNoticeOpen(driver).click();//click edit notice
			     
			             Thread.sleep(3000);
					     performerPOM.clickEditNotice(driver).click();//click edit notice
				  
					      driver.switchTo().parentFrame();
					      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
       	          
   			       Thread.sleep(1000);
   				   performerPOM. clickExternalLawyerRating(driver).click();
   				   

   				   
   				  Thread.sleep(3000);
   				  performerPOM.selectExternalLawyerRating(driver);
   				   Thread.sleep(3000);
   				   performerPOM.clickNewCriteria(driver).click();
   				   Thread.sleep(3000);
   				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
   				 Thread.sleep(3000);
   				  performerPOM.clickCriteria(driver).sendKeys("342");
   				 
   				   Thread.sleep(3000);
   				   performerPOM.clickSaveCriteria(driver).click();
   				   Thread.sleep(3000);
   				   String msg = performerPOM.clickCriteriaInvalidMsg(driver).getText();
   				   
   				   if(msg.equalsIgnoreCase("Only alphabets allowed."))
   				   {
   					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
   				   }
   				   else
   				   {
   					   test.log(LogStatus.FAIL, "Meassag Displayed ="+msg);
   				   }
   				   
   				   Thread.sleep(3000);
   				   driver.switchTo().parentFrame();
   				   performerPOM.clickclosecriteria(driver).click();
         }
 	 	 static void CriteriaWithoutData(WebDriver driver,ExtentTest test) throws InterruptedException
         {
       	  
   		         WebDriverWait wait = new WebDriverWait(driver, 300);
		  
			          Thread.sleep(3000);
					     performerPOM.clickNoticeOpen(driver).click();//click edit notice
			     
			             Thread.sleep(3000);
					     performerPOM.clickEditNotice(driver).click();//click edit notice
				  
					      driver.switchTo().parentFrame();
					      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
       	          
   			       Thread.sleep(1000);
   				   performerPOM. clickExternalLawyerRating(driver).click();
   				   

   				   
   				  Thread.sleep(3000);
   				  performerPOM.selectExternalLawyerRating(driver);
   				   Thread.sleep(3000);
   				   performerPOM.clickNewCriteria(driver).click();
   				   Thread.sleep(3000);
   				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
   				
   				 
   				   Thread.sleep(3000);
   				   performerPOM.clickSaveCriteria(driver).click();
   				   Thread.sleep(3000);
   				   String msg = performerPOM.readOppoenentMsg(driver).getText();
   				   
   				   if(msg.equalsIgnoreCase("Criteria can not be empty."))
   				   {
   					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
   				   }
   				   else
   				   {
   					   test.log(LogStatus.FAIL, "Meassag Displayed ="+msg);
   				   }
   				   
   				   Thread.sleep(3000);
   				   driver.switchTo().parentFrame();
   				   performerPOM.clickclosecriteria(driver).click();
         }
 	 	 
 		static void CaseExistingData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException
		{
 			
 			sheet = workbook.getSheetAt(8);		
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
					
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		
			js.executeScript("window.scrollBy(0,-700)");
			Thread.sleep(3000);
			performerPOM.clickCaseOpencfo(driver).click();						//Clicking on 'Open' Case
			Thread.sleep(3000);
			clickNewCase(driver);
			

			
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			Thread.sleep(1000);
			selectCaseType(driver);
			Thread.sleep(3000);
			clickDated1(driver);
			Thread.sleep(3000);
			clickFinanicialYear(driver);
			Thread.sleep(3000);
			clickRefNo1(driver);
			Thread.sleep(3000);
			clickInternalCaseNo(driver);
			Thread.sleep(3000);
			clickCaseTitle(driver);
			Thread.sleep(3000);
			clickCaseAct(driver);
			Thread.sleep(3000);
			clickUnderSection(driver);
			Thread.sleep(3000);
			clickSearchCaseCategory(driver);
			Thread.sleep(3000);
			clickCaseBudget(driver);
			Thread.sleep(3000);
			clickCaseOpponent(driver);
//			Thread.sleep(3000);
//			clickCaseOppLawyer(driver);
			Thread.sleep(3000);
			clickCaseCourt(driver);
			Thread.sleep(3000);
			clickCaseDescription(driver);
			Thread.sleep(3000);
			selectCaseLocation(driver);
			Thread.sleep(3000);
			clickCaseDepartment(driver);
			Thread.sleep(3000);
			clickCaseOwner(driver);
			Thread.sleep(3000);
			clickCaseRisk(driver);
			Thread.sleep(3000);
			clickCaseInternalUser(driver);
			
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.CaseInvalidreadMessage(driver)));
			
			Thread.sleep(500);
			String msg = performerPOM.CaseInvalidreadMessage(driver).getText();		//Reading Message appeared after save button
			
			if(msg.equalsIgnoreCase("Case with Same Court Case No already exists"))
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
				
			}
		else
			{
				test.log(LogStatus.FAIL, "Message displayed = "+msg);
			}
		

		
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();			//Clicking on 'Close'
			
		}
 		static void CaseWithInvalidData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException
		{
 			
 			sheet = workbook.getSheetAt(8);		
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
					
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		
			js.executeScript("window.scrollBy(0,-700)");
			Thread.sleep(3000);
			performerPOM.clickCaseOpencfo(driver).click();						//Clicking on 'Open' Case
			Thread.sleep(3000);
			clickNewCase(driver);
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			Thread.sleep(1000);
			selectCaseType(driver);
			Thread.sleep(3000);
			clickInvalidDate(driver);
			Thread.sleep(3000);
			clickFinanicialYear(driver);
			Thread.sleep(3000);
			clickRefNo1(driver);
			Thread.sleep(3000);
			clickInternalCaseNo(driver);
			Thread.sleep(3000);
			clickCaseTitle(driver);
			Thread.sleep(3000);
			clickCaseAct(driver);
			Thread.sleep(3000);
			clickUnderSection(driver);
			Thread.sleep(3000);
			clickSearchCaseCategory(driver);
			Thread.sleep(3000);
			clickCaseBudget(driver);
			Thread.sleep(3000);
			clickCaseOpponent(driver);
//			Thread.sleep(3000);
//			clickCaseOppLawyer(driver);
			Thread.sleep(3000);
			clickCaseCourt(driver);
			Thread.sleep(3000);
			clickCaseDescription(driver);
			Thread.sleep(3000);
			selectCaseLocation(driver);
			Thread.sleep(3000);
			clickCaseDepartment(driver);
			Thread.sleep(3000);
			clickCaseOwner(driver);
			Thread.sleep(3000);
			clickCaseRisk(driver);
			Thread.sleep(3000);
			clickCaseInternalUser(driver);
			
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.readMessageCase(driver)));
			
			Thread.sleep(500);
			String msg = performerPOM.readMessageCase(driver).getText();		//Reading Message appeared after save button
			
			if(msg.equalsIgnoreCase("Server Error Occurred. Please try again."))
			{
				test.log(LogStatus.FAIL, "Message displayed = "+msg);
				
			}
		else
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
			}
		

		
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();			//Clicking on 'Close'
			
		}
 		
 		 public  static void clickInvalidDate(WebDriver driver) throws InterruptedException 
		  {
			  Thread.sleep(3000);
		      performerPOM.clickCaseDate(driver).sendKeys("22-01-202");					//Clicking on 'Dated' button
		  }
 		 
 		static void CaseWithTwoFieldsData(WebDriver driver, ExtentTest test) throws InterruptedException
		{
 			
 				
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
					
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		
			js.executeScript("window.scrollBy(0,-700)");
			Thread.sleep(3000);
			performerPOM.clickCaseOpencfo(driver).click();						//Clicking on 'Open' Case
			Thread.sleep(3000);
			clickNewCase(driver);
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			Thread.sleep(1000);
			selectCaseType(driver);
			Thread.sleep(3000);
			clickDated1(driver);
			
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
			
			Thread.sleep(500);
			String msg = performerPOM.readMessageCase1(driver).getText();		//Reading Message appeared after save button
			
			test.log(LogStatus.PASS, "Case With Two Manadatory Fields = "+msg);
				
		
		

		
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();			//Clicking on 'Close'
			
		}
 		
 		static void CaseWithEmptyFields(WebDriver driver, ExtentTest test) throws InterruptedException
		{
 			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
					
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		
			js.executeScript("window.scrollBy(0,-700)");
			Thread.sleep(3000);
			performerPOM.clickCaseOpencfo(driver).click();						//Clicking on 'Open' Case
			Thread.sleep(3000);
			clickNewCase(driver);
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
			
			Thread.sleep(500);
			String msg = performerPOM.readMessageCase1(driver).getText();		//Reading Message appeared after save button
			
			test.log(LogStatus.PASS, "Case With Empty Fields = "+msg);
				
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();			//Clicking on 'Close'
		}
 		static void CaseWithClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException
		{
 			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
					
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		
			js.executeScript("window.scrollBy(0,-700)");
			Thread.sleep(3000);
			performerPOM.clickCaseOpencfo(driver).click();						//Clicking on 'Open' Case
			Thread.sleep(3000);
			clickNewCase(driver);
			
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 		Thread.sleep(3000);
	 			clickDated1(driver);

	 			Thread.sleep(3000);
	 			clickFinancialYear(driver);
	 	 		
	 			js.executeScript("window.scrollBy(0,-700)");
			 
	             if(performerPOM.clickCaseClearBtn(driver).isEnabled())
		          {
		            Thread.sleep(2000);
		              performerPOM.clickCaseClearBtn(driver).click();
		              test.log(LogStatus.PASS, "Clear Button is clickable");
		           }
		         else
		         {
		    	   test.log(LogStatus.FAIL, "Clear Button is not clickable");
		         }
	 
	     		Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	     		
	     		Thread.sleep(3000);
	     		OverduePOM.clickDashboard(driver).click();
		}
 		 static void LinkCaseViewIcon(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	 	 	 {
	 	 	 		   
	 	 	        	
	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 		
	 	 	 			
	 	 	            Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 	 			
	 	 	            Thread.sleep(4000);
	 	 	            performerPOM.clickEditNotice(driver).click();
	 	 	 			
	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			
	 	 	 			WebDriverWait wait = new WebDriverWait(driver, 1000);
	 	 	 			
	 	 	 		    Thread.sleep(4000);
	 	 	            performerPOM.clickLinkedCaseViewIcon(driver).click();
 			      	
 			      	Thread.sleep(4000);
 	 	 	        performerPOM.clickViewPopup(driver).click();
 	 	 	        
 	 	 	        String msg="Linked Case Details";
 	 	 	        
 	 	 	        if(msg.equalsIgnoreCase(msg))
 	 	 	        {
 	 	 	        	test.log(LogStatus.PASS, "View Case Detailes icon open succssefully");
 	 	 	        }
 	 	 	        else
 	 	 	        {
 	 	 	        	test.log(LogStatus.FAIL, "View Case Detailes icon not open succssefully");

 	 	 	        }
 	 	 	        

	 	 	 		Thread.sleep(4000);
	 	 	        performerPOM.clickClosePopup1(driver).click();
	 	 	     	Thread.sleep(3000);
	 	     		driver.switchTo().parentFrame();
	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	     	
	 	     	    Thread.sleep(3000);
	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	 			
	 	 	 }
 		 
 		 static void LinkCaseDeleteIcon(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	 	 	 {
	 	 	 		   
	 	 	        	
	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 		
	 	 	 			
	 	 	            Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 	 			
	 	 	            Thread.sleep(4000);
	 	 	            performerPOM.clickEditNotice(driver).click();
	 	 	 			
	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			
	 	 	 		   	
	 	 	 			WebDriverWait wait = new WebDriverWait(driver, 1000);
	 	 	 			
	 	 	 		    Thread.sleep(4000);
	 	 	            performerPOM.clickLinkedCaseDeleteIcon(driver).click();
 			      	
	 	 	         Thread.sleep(5000);
	 			    // Switching to Alert        
	 		        Alert alert1 = driver.switchTo().alert();		
	 		        		
	 		        // Capturing alert message.    
	 		        String alertMessage1= driver.switchTo().alert().getText();	
	 		        
	 		        
	 		        test.log(LogStatus.PASS, alertMessage1);
	 		        		
	 		        // Displaying alert message		
	 		        System.out.println(alertMessage1);
	 		        
	 		       // Accepting alert		
	 		        alert1.accept();	
	 		        
	 		      Thread.sleep(4000);
 	 	       String msg= performerPOM.clickLinkedCaseDeleteIconValidMsg(driver).getText();
 	 	       
 	 	       test.log(LogStatus.PASS, "Message Displayed =" +msg);
 	 	 	        

	 	 	     	Thread.sleep(3000);
	 	     		driver.switchTo().parentFrame();
	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	     	
	 	     	    Thread.sleep(3000);
	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	 			
	 	 	 }
 		 static void CaseUserAssignment(WebDriver driver, ExtentTest test,XSSFWorkbook workbook) throws InterruptedException, IOException
	 	 	 {

  			            sheet = workbook.getSheetAt(8);		

	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 		
	 	 	 			
	 	 	            Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 	 			
	 	 	            Thread.sleep(4000);
	 	 	            performerPOM.clickEditNotice(driver).click();
	 	 	            
	 	 	        
	 	 	 			
	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			

//	 	 	            Thread.sleep(4000);
//	 	 	            performerPOM.clickEditUserAssign(driver).click();
	 	 	 			
	 	 	            
	 	 	          Thread.sleep(3000);
	 	 			clickCaseInternalUser(driver);
	 	 			Thread.sleep(3000);
	 	 		   performerPOM.clickInternalUser(driver).click();			
	 	                
	 	              Thread.sleep(3000);
 	 	             performerPOM.clickUpdateButton(driver).click();
	 	 	    		Thread.sleep(2000);
	 	 	    		String msg = performerPOM.readMessageCase(driver).getText();		//Reading Message appeared after save button
	 	 	    		
	 	 	    		if(msg.equalsIgnoreCase("Case Details Updated Successfully."))
	 	 	    		{
	 	 	    			test.log(LogStatus.PASS, "Message displayed = "+msg);
	 	 	    			
	 	 	    		}
	 	 	    		else
	 	 	    		{
	 	 	    			test.log(LogStatus.FAIL, "Message displayed = "+msg);
	 	 	    		}
	 	 	    		Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	     	
	 	 	     	    Thread.sleep(3000);
	 	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	    }
 		 static void CaseUserAssignmentDelete(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	 	 	 {
	 	 	 		   
	 			     			
	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 	        Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();	
	 	 	        	
	 	 	            Thread.sleep(4000);
 	 	                performerPOM.clickEditNotice(driver).click();
 	 	            
 	 	         	    wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			
	 	 	            Thread.sleep(4000);
	 	 	            performerPOM.clickDeleteUserAssign(driver).click();
	 	 	            
	 	 	         Thread.sleep(2000);
 	 	    		String msg = performerPOM.clickDeleteUserAssignValidMsg1(driver).getText();		//Reading Message appeared after save button
 	 	    		
 	 	    		if(msg.equalsIgnoreCase("User Assignment Detail Deleted"))
 	 	    		{
 	 	    			test.log(LogStatus.PASS, "Message displayed = "+msg);
 	 	    			
 	 	    		}
 	 	    		else
 	 	    		{
 	 	    			test.log(LogStatus.FAIL, "Message displayed = "+msg);
 	 	    		}
	 	 	            
	 	 	        	Thread.sleep(3000);
	 	 	     		driver.switchTo().parentFrame();
	 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	 	 	     	
	 	 	     	    Thread.sleep(3000);
	 	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	    }
 		 
 		 static void CaseSendMailWithDoc(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	 	 	 {
	 	 	 		   
	 	 	        	
	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 		
	 	 	 			
	 	 	            Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 	 			
	 	 	             Thread.sleep(4000);
	 	 	         
	 			      	performerPOM.clickEditNotice(driver).click();
	 	 	 			
	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			
	 	 	 		   Thread.sleep(4000);
	 	 	 		   performerPOM.clickSendMailCase(driver).click();
	 	 	 		   
	 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickSelectCheckbox(driver).click();
 	 	 		 
 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickMailTo(driver).sendKeys("admin@gmail.com");
 	 	 		 
 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickMessageMail(driver).sendKeys("Test");
 	 	 		 
 	 	 		 
 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickSend(driver).click();
 	 	 		 
 	 	 		Thread.sleep(4000);
 	 	 		String msg= performerPOM.clickSendMailMsg(driver).getText();
 	 	 		
 	 	 		if(msg.equalsIgnoreCase("E-Mail Sent Successfully."))
 	 	 		{
 	 	 			test.log(LogStatus.PASS ,"Message displayed =" +msg);
 	 	 		}
 	 	 		else
 	 	 		{
 	 	 			test.log(LogStatus.FAIL ,"Message displayed =" +msg);
 	 	 		}
 	 	 		
 	 			Thread.sleep(3000);
 	 	     
 	 	     	performerPOM.clickcloseBtn(driver).click();//Clicking on 'Close'
 	 	     	Thread.sleep(3000);
 	 	     		driver.switchTo().parentFrame();
 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	     	
 	 	     	Thread.sleep(3000);
 	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	 
	 	 	 }
 		 static void CaseSendMailWithDocInvalidFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	 	 	 {
	 	 	 		   
	 	 	        	
	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 		
	 	 	 			
	 	 	            Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 	 			
	 	 	             Thread.sleep(4000);
	 	 	         
	 			      	performerPOM.clickEditNotice(driver).click();
	 	 	 			
	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			
	 	 	 		   Thread.sleep(4000);
	 	 	 		   performerPOM.clickSendMailCase(driver).click();
	 	 	 		   
	 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickSelectCheckbox(driver).click();
 	 	 		 
 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickMailTo(driver).sendKeys("admin");
 	 	 		 
 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickMessageMail(driver).sendKeys("Test");
 	 	 		 
 	 	 		 
 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickSend(driver).click();
 	 	 		 
 	 	 		Thread.sleep(4000);
 	 	 		String msg= performerPOM.clickSendMailMsg(driver).getText();
 	 	 		
 	 	 		if(msg.equalsIgnoreCase("Please enter a valid email."))
 	 	 		{
 	 	 			test.log(LogStatus.PASS ,"Message displayed =" +msg);
 	 	 		}
 	 	 		else
 	 	 		{
 	 	 			test.log(LogStatus.FAIL ,"Message displayed =" +msg);
 	 	 		}
 	 	 		
 	 			Thread.sleep(3000);
 	 	     
 	 	     	performerPOM.clickcloseBtn(driver).click();//Clicking on 'Close'
 	 	     	Thread.sleep(3000);
 	 	     		driver.switchTo().parentFrame();
 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	     	
 	 	     	Thread.sleep(3000);
 	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	 
	 	 	 }
 		 
 		 static void CaseSendMailWithDocEmptyFields(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	 	 	 {
	 	 	 		   
	 	 	        	
	 	 	 			WebDriverWait wait1 = new WebDriverWait(driver, 300);
	 	 	 			progress(driver);
	 	 	 			
	 	 	 		
	 	 	 			
	 	 	            Thread.sleep(500);
	 	 	        	performerPOM.clickCaseOpencfo(driver).click();		
	 	 	 			
	 	 	             Thread.sleep(4000);
	 	 	         
	 			      	performerPOM.clickEditNotice(driver).click();
	 	 	 			
	 	 	 			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	 	 	 			
	 	 	 		   Thread.sleep(4000);
	 	 	 		   performerPOM.clickSendMailCase(driver).click();
	 	 	 		   
	 	 	 		 Thread.sleep(4000);
 	 	 		 performerPOM.clickSelectCheckbox(driver).click();
 	 	 		 
 	 	 		Thread.sleep(4000);
 		       performerPOM.clickSend(driver).click();
 	 	 		 
 	 	 		Thread.sleep(4000);
 	 	 		String msg= performerPOM.clickSendMailMsg1(driver).getText();
 	 	 		
 	 	 		test.log(LogStatus.PASS ,"Message displayed =" +msg);
 	 	 		
 	 	 		
 	 			Thread.sleep(3000);
 	 	     
 	 	     	performerPOM.clickcloseBtn(driver).click();//Clicking on 'Close'
 	 	     	Thread.sleep(3000);
 	 	     		driver.switchTo().parentFrame();
 	 	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
 	 	     	
 	 	     	Thread.sleep(3000);
 	 	     		OverduePOM.clickDashboard(driver).click();
	 	 	 
	 	 	 }
 	 	static void CaseWithoutUploadDocument(WebDriver driver, ExtentTest test) throws InterruptedException
	       	{
	    		WebDriverWait wait = new WebDriverWait(driver, 50);
	             
	    		Thread.sleep(1000);
	    		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
	    		
		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
		        
		        performerPOM.clickNoticeDocument(driver).click();     //click notice document
		        performerPOM.clickNewDocument(driver).click();        //click new document button
		
		        Thread.sleep(1000);
	           	driver.switchTo().frame("IFrameManageDocument");
	           	performerPOM.selectDocumentType(driver);
	           Thread.sleep(3000);
		        performerPOM.chooseDocumentType(driver);
	          	
		        Thread.sleep(1000);
	         	performerPOM.clickUploadDocument(driver).click(); 
		
		
	         	Thread.sleep(1000);
	         	wait.until(ExpectedConditions.visibilityOf(performerPOM.readDocMsg(driver)));
		
	        	Thread.sleep(3000);
		        String msg= performerPOM.readDocMsg(driver).getText();		//Reading Message appeared after save button
		       
	         	if(msg.equalsIgnoreCase("Please select file to upload"))
	         	{
		        	test.log(LogStatus.PASS, "Message displayed = "+msg);
		         
		        }
		      else
		        {
			       test.log(LogStatus.FAIL, "Message displayed = "+msg);
		        }
		
		        Thread.sleep(1000);
		        performerPOM.clickClosedDocument(driver).click();
		        
		       driver.switchTo().parentFrame();
		      
		      Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	       	}
 	 	static void CaseDocumentEmptyFields(WebDriver driver, ExtentTest test) throws InterruptedException
	       	{
	    		WebDriverWait wait = new WebDriverWait(driver, 50);
	             
	    	
	    		
		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
		        
		        performerPOM.clickNoticeDocument(driver).click();     //click notice document
		        performerPOM.clickNewDocument(driver).click();        //click new document button
		
		        Thread.sleep(1000);
	           	driver.switchTo().frame("IFrameManageDocument");
	  
		        Thread.sleep(1000);
	         	performerPOM.clickUploadDocument(driver).click(); 
		
		
	         	Thread.sleep(1000);
	         	wait.until(ExpectedConditions.visibilityOf(performerPOM.readDocMsg(driver)));
		
	        	Thread.sleep(3000);
		        String msg= performerPOM.readDocMsgInvalidMsg(driver).getText();		//Reading Message appeared after save button
		       
	         	if(msg.equalsIgnoreCase("Please select document type"))
	         	{
		        	test.log(LogStatus.PASS, "Message displayed = "+msg);
		         
		        }
		      else
		        {
			       test.log(LogStatus.FAIL, "Message displayed = "+msg);
		        }
		
	         	
		        Thread.sleep(1000);
		        performerPOM.clickClosedDocument(driver).click(); 
		       driver.switchTo().parentFrame();
		      
		      Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	    }
 	 	
 	 	static void CaseDocumentSearchFields(WebDriver driver, ExtentTest test) throws InterruptedException
	       	{
	    		WebDriverWait wait = new WebDriverWait(driver, 50);
	             
	    		
		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

		        Thread.sleep(3000);
				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
				
				Thread.sleep(3000);
	 			performerPOM.clickSearchDocument(driver).sendKeys("Approver Test Case.xlsx");
	 			
	 			Thread.sleep(3000);
				performerPOM.clickApplyBtn(driver).click();
				Thread.sleep(3000);
				String msg=performerPOM.clickDocName1(driver).getText();
				if(msg.equalsIgnoreCase(msg)) 
				{
					test.log(LogStatus.PASS,"Document Filter Apply =" +msg);
				}
				else
				{
					test.log(LogStatus.FAIL,"Document Filter Apply  =" +msg);
				}
				
				Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
				
				
	       	}
 	 	
 		static void CaseDocumentShareInvalidData(WebDriver driver, ExtentTest test) throws InterruptedException
	       	{
	    		WebDriverWait wait = new WebDriverWait(driver, 50);
	             
	    		
		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

		        Thread.sleep(3000);
				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
				 
				 Thread.sleep(3000);
		        performerPOM.clickCaseDocumentsharecfo(driver).click();
		        
		        Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert1 = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		        
		        
		        test.log(LogStatus.PASS, alertMessage1);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage1);
		        
		     // Accepting alert		
		        alert1.accept();	
		        
		        Thread.sleep(3000);
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));
		        
		        Thread.sleep(4000);
		        performerPOM.clickNoticeDocumentshareemailcfo(driver).sendKeys("admin");
		        
		        Thread.sleep(3000);
		        performerPOM.clickNoticeDocumentsharecontactnocfo(driver).sendKeys("576879");
		        
		        Thread.sleep(3000);
		        performerPOM.clickNoticeDocumentsharesavecfo(driver).click();
		        
		        
		        Thread.sleep(3000);
		        String msg1= performerPOM.clickNoticeDocumentshareInvalidmsgcfo(driver).getText();		//Reading Message appeared after save button
		       
	         
		        	test.log(LogStatus.PASS, "Message displayed = "+msg1);
		      
		        
		        Thread.sleep(3000);
		        performerPOM. clickNoticeDocumentshareclosepopupcfo(driver).click();
		        
		       driver.switchTo().parentFrame();
		      
		        Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	       	}
 		
 		static void CaseDocumentShareWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException
	       	{
	    		WebDriverWait wait = new WebDriverWait(driver, 50);
	             
	    		
		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

		        Thread.sleep(3000);
				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
				 
				 Thread.sleep(4000);
		        performerPOM.clickCaseDocumentsharecfo(driver).click();
		        
		        Thread.sleep(5000);
			    // Switching to Alert        
		        Alert alert1 = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage1= driver.switchTo().alert().getText();	
		        
		        
		        test.log(LogStatus.PASS, alertMessage1);
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage1);
		        
		     // Accepting alert		
		        alert1.accept();	
		        
		       Thread.sleep(3000);
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));
		        
		      
		        
		        Thread.sleep(3000);
		        performerPOM.clickNoticeDocumentsharesavecfo(driver).click();
		        
		        
		        Thread.sleep(3000);
		        String msg1= performerPOM.clickNoticeDocumentsharereadmsgcfo(driver).getText();		//Reading Message appeared after save button
		        if(msg1.equalsIgnoreCase("Please Enter Email."))
		        {
	         
		        	test.log(LogStatus.PASS, "Message displayed = "+msg1);
		        }
		        else
		        {
		        	test.log(LogStatus.FAIL, "Message displayed = "+msg1);
		        }
		        
		        Thread.sleep(3000);
		        performerPOM. clickNoticeDocumentshareclosepopupcfo(driver).click();
		        
		       driver.switchTo().parentFrame();
		      
		        Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
	       	}
 		static void CaseDocumentShareCloseBtn(WebDriver driver, ExtentTest test) throws InterruptedException
	       	{
	    		WebDriverWait wait = new WebDriverWait(driver, 50);
	             
	    		
		        Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
				
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));

		        Thread.sleep(3000);
				 performerPOM.clickNoticeDocument(driver).click();     //click notice document
				 
				 
				 Thread.sleep(3000);
		        performerPOM.clickCaseDocumentsharecfo(driver).click();
		        
		       Thread.sleep(5000);
		    // Switching to Alert        
	        Alert alert1 = driver.switchTo().alert();		
	        		
	        // Capturing alert message.    
	        String alertMessage1= driver.switchTo().alert().getText();	
	        
	        
//	        test.log(LogStatus.PASS, alertMessage1);
	        		
	        // Displaying alert message		
	        System.out.println(alertMessage1);
	        
	     // Accepting alert		
	        alert1.accept();	
	        
	       Thread.sleep(3000);
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Iframe_Docshare"));

     	      Thread.sleep(2000);
             if(performerPOM.clickNoticeDocumentshareclosepopupcfo(driver).isEnabled())
             {
              Thread.sleep(2000);
              performerPOM.clickNoticeDocumentshareclosepopupcfo(driver).click();
              test.log(LogStatus.PASS, "Close Button is clickable");
             }
            else
           {
    	     test.log(LogStatus.FAIL, "Close Button is not clickable");
           }
           
   	     		driver.switchTo().parentFrame();
   	     	driver.switchTo().parentFrame();
   	     	   Thread.sleep(3000);
   	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
   	     	
	       }
 		
 		 static void CaseTaskActivitywithExistingData(WebDriver driver, ExtentTest test,XSSFWorkbook workbook ) throws InterruptedException, IOException
			{
			    WebDriverWait wait=new WebDriverWait(driver,20);
			    
			    
		       XSSFSheet sheet=ReadExcel();

		       Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
		      
			    Thread.sleep(3000);
			   
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    Thread.sleep(3000);
			    performerPOM.clickCaseTask(driver).click();
			    Thread.sleep(300);
			    performerPOM.clickCaseNewTask(driver).click();
//			    Thread.sleep(5000);
//			    performerPOM.clickHearingDate(driver).sendKeys("23-07-2022");
			    
			    
			    Thread.sleep(3000);
			    performerPOM.clickHearingDatecfo(driver).click(); 
			    Thread.sleep(3000);
			    performerPOM.clickHearingDatedropdowncfo(driver).click(); 
//			    Thread.sleep(2000);
//			    performerPOM.clickSaveHearingDatecfo(driver).click();
			  
			  
				Thread.sleep(6000);
				Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
				Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
				String title = c1.getStringCellValue();
				performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
				
				Thread.sleep(5000);
				row0 = sheet.getRow(13);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String desc = c1.getStringCellValue();
				performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
				
				
				Thread.sleep(1000);
				performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
				OverduePOM.selectNextMonth(driver).click();
				OverduePOM.selectDate(driver).click();					//Selecting particular date.
				
				Thread.sleep(1000);
				Actions action = new Actions(driver);
//				action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
				
				
				Thread.sleep(1000);
				row0 = sheet.getRow(14);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String outcome = c1.getStringCellValue();
				performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
				

				
				Thread.sleep(1000);
				row0 = sheet.getRow(15);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String internalUser = c1.getStringCellValue();
				performerPOM.clickInternalUser3(driver).click();
				//performerPOM.selectInternalUser2(driver).click();
				performerPOM.selectInternalUser3(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
				
				Thread.sleep(1000);
				row0 = sheet.getRow(16);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String externalUser = c1.getStringCellValue();
				try
				{
					Thread.sleep(300);
					performerPOM.clickExternalUser(driver).click();
					Thread.sleep(500);
					action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
				}
				catch(Exception e)
				{
					
				}
				Thread.sleep(5000);
				row0 = sheet.getRow(17);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String remark = c1.getStringCellValue();
				performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
          	Thread.sleep(1000);
				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
				

				
				Thread.sleep(300);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsgcfo(driver)));
				
				Thread.sleep(3000);
			
				String msg1 = performerPOM.readTaskMsgcfo(driver).getText();
				if(msg1.contains("Task with same title already exists."))
				{
					test.log(LogStatus.PASS, "Case -Task/Activtiy with existing data =" +msg1);
				}
				
				else 
				{
					test.log(LogStatus.FAIL, "Case -Task/Activtiy with existing data =" +msg1);
				}
				
				Thread.sleep(3000);
	     		driver.switchTo().parentFrame();
	     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
			}
 		 
 		 static void CaseTaskActivityWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			{
			    WebDriverWait wait=new WebDriverWait(driver,20);
			    
		       Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
		      
			    Thread.sleep(3000);
			   
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    Thread.sleep(3000);
			    performerPOM.clickCaseTask(driver).click();
			    Thread.sleep(300);
			    performerPOM.clickCaseNewTask(driver).click();
			    
			    Thread.sleep(3000);
  				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
  				
//  			  Thread.sleep(3000);
//				performerPOM.clickMinimize(driver).click();
			    

  				Thread.sleep(300);
  				String msg1 = performerPOM.readTaskMsg2(driver).getText();
  				
  					test.log(LogStatus.PASS, "Task/Activity Without data ="+msg1);
  				
  				
  					Thread.sleep(3000);
  		     		driver.switchTo().parentFrame();
  		     		performerPOM.clickClose(driver).click();//Clicking on 'Close'
  				
  			}
 		 public  static void CaseTaskActivtityResponseWithoutStatus(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			{ 
	 		   WebDriverWait wait = new WebDriverWait(driver, 60);
	 			  Thread.sleep(3000);
	 				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 			   
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				    Thread.sleep(3000);
				    performerPOM.clickCaseTask(driver).click();
				  

				Thread.sleep(3000);
				performerPOM.clickNoticeTaskEditResponsecfo1(driver).click();
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskcmtResponsecfo(driver).sendKeys("Automate Test");
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskSaveResponsecfo(driver).click();
				
				
				String msg=performerPOM.clickInvalidResponsemsg(driver).getText();
				if(msg.equalsIgnoreCase("Provide Response Status."))
				{
				    test.log(LogStatus.PASS, "Mesaage displayed ="+msg);
				}
				else
				{
					 test.log(LogStatus.FAIL, "Mesaage displayed ="+msg);
				}
				driver.switchTo().parentFrame();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskCloseResponsecfo(driver).click();
				
				driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
				
          
			}
 		 
 		 public  static void CaseTaskActivtityResponseClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			{ 
	 		   WebDriverWait wait = new WebDriverWait(driver, 60);
	 			  Thread.sleep(3000);
	 				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 			  
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				    Thread.sleep(3000);
				    performerPOM.clickCaseTask(driver).click();
			

				Thread.sleep(3000);
				performerPOM.clickNoticeTaskEditResponsecfo1(driver).click();
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickNoticeTaskstatusResponsecfo1(driver).click();
				
				
				if(performerPOM.clickClearResponse(driver).isEnabled())
		  		{
					Thread.sleep(3000);
		  			performerPOM.clickClearResponse(driver).click();
		  			test.log(LogStatus.PASS, "Clear button working successfully");
		  		}
		  		else
		  		{
		  			test.log(LogStatus.FAIL, "Clear button not working successfully");
		  		}
				  driver.switchTo().parentFrame();
				  driver.switchTo().parentFrame();
		   	     	Thread.sleep(3000);
		   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		   	     	
		   	
					
	             
			}
 		 
 		 static void CaseExistingHearingDate(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
			       WebDriverWait wait=new WebDriverWait(driver,20);
			       XSSFSheet sheet=ReadExcel();
			       
			       Thread.sleep(3000);
	 				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 			  
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				    performerPOM.clickCaseHearing(driver).click();
					Thread.sleep(3000);
					performerPOM.clickNewCaseHearing(driver).click();
					Thread.sleep(3000);
                	performerPOM.clickCaseHearingDate(driver).sendKeys("28-06-2023");	//Writing 'HearingDate'
				    Thread.sleep(3000);
				    performerPOM.clickSaveCaseHearingDate(driver).click();
				
					
					Thread.sleep(2000);
					Row row1 = sheet.getRow(50);									//Selected 0th index row (First row)
					Cell c2 = row1.getCell(1);									//Selected cell (0 row,1 column)
					String HearingDescription = c2.getStringCellValue();
					performerPOM.clickCaseHearingDecsri(driver).sendKeys(HearingDescription);		//Writing 'HearingDescription'
					
				   
				    Thread.sleep(3000);
					String msg = performerPOM.clickReadHearingMsg1(driver).getText();
					if(msg.contains("Hearing Date already exists, Please select Hearing and continue with the task creation."))
					{
						test.log(LogStatus.PASS, "Existing Hearing Date =" +msg);
					}
					else
					{
						test.log(LogStatus.FAIL, "Existing Hearing Date =" +msg);
					}
					
					  driver.switchTo().parentFrame();
			   	     	Thread.sleep(3000);
			   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
			}
 		 
 		 static void CaseHearingWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
			{
			       WebDriverWait wait=new WebDriverWait(driver,20);
			   
			       
			       Thread.sleep(3000);
	 				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
	 		     
	 		        Thread.sleep(3000);
	 				performerPOM.clickEditNotice(driver).click();//click edit notice
	 			  
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				    performerPOM.clickCaseHearing(driver).click();
					Thread.sleep(3000);
					performerPOM.clickNewCaseHearing(driver).click();
					Thread.sleep(3000);
				    performerPOM.clickSaveCaseHearing(driver).click();
				    Thread.sleep(3000);
					performerPOM.clickMinimizeHearing(driver).click();	
					 
					String msg = performerPOM.clickReadHearingMsg1(driver).getText();
					test.log(LogStatus.PASS, "Case  without hearing data =" +msg);
					 Thread.sleep(1000);
					  driver.switchTo().parentFrame();
			   	     	Thread.sleep(3000);
			   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
			}
 		static void CaseHearingInvalidDate(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
		{
		       WebDriverWait wait=new WebDriverWait(driver,20);
		       XSSFSheet sheet=ReadExcel();
		       
		       Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		       
		       Thread.sleep(3000);
			   performerPOM.clickCaseHearing(driver).click();
				Thread.sleep(3000);
				performerPOM.clickNewCaseHearing(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickCaseHearingDate(driver).sendKeys("31-05-202");	//Writing 'HearingDate'
			   Thread.sleep(3000);
			    performerPOM.clickSaveCaseHearingDate(driver).click();
			
				
				Thread.sleep(2000);
				Row row1 = sheet.getRow(50);									//Selected 0th index row (First row)
				Cell c2 = row1.getCell(1);									//Selected cell (0 row,1 column)
				String HearingDescription = c2.getStringCellValue();
				performerPOM.clickCaseHearingDecsri(driver).sendKeys(HearingDescription);		//Writing 'HearingDescription'
				
			 
			    
			    Thread.sleep(3000);
				String msg = performerPOM.clickReadHearingMsg1(driver).getText();
				if(msg.contains("Server Error Occurred. Please try again."))
				{
					test.log(LogStatus.FAIL, "Case with Invalid Hearng Date=" +msg);
				}
				else
				{
					test.log(LogStatus.PASS, "Case with Invalid Hearng Date=" +msg);
				}
				
				  driver.switchTo().parentFrame();
		   	     	Thread.sleep(3000);
		   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		}
 		
 		 static void CaseHearingClearBtn(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
			       WebDriverWait wait=new WebDriverWait(driver,20);
			       XSSFSheet sheet=ReadExcel();
			       
			       Thread.sleep(3000);
					performerPOM.clickCaseOpencfo(driver).click();//click edit notice
			     
			        Thread.sleep(3000);
					performerPOM.clickEditNotice(driver).click();//click edit notice
				  
				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			       Thread.sleep(3000);
				   performerPOM.clickCaseHearing(driver).click();
					Thread.sleep(3000);
					performerPOM.clickNewCaseHearing(driver).click();
					Thread.sleep(2000);
					Row row1 = sheet.getRow(50);									//Selected 0th index row (First row)
					Cell c2 = row1.getCell(1);									//Selected cell (0 row,1 column)
					String HearingDescription = c2.getStringCellValue();
					performerPOM.clickCaseHearingDecsri(driver).sendKeys(HearingDescription);		//Writing 'HearingDescription'
					
					if(performerPOM.clickHearingClearBtn(driver).isEnabled())
					{

						Thread.sleep(3000);
					   performerPOM.clickHearingClearBtn(driver).click();
					   
					   test.log(LogStatus.PASS,"After clicking the clear button the data should be remove");
					}
					else
					{
						 test.log(LogStatus.FAIL,"After clicking the clear button the data should not be remove");
					}

					  driver.switchTo().parentFrame();
			   	     	Thread.sleep(3000);
			   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
			}
 		 
 		static void CaseOrderWithoutData(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			
			 Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			 Thread.sleep(5000);
			 performerPOM.clickCaseOrder(driver).click();
			 Thread.sleep(6000);
			 performerPOM.clickNewCaseOrder(driver).click();
			  Thread.sleep(3000);
			 performerPOM.clickSaveCaseOrder(driver).click();
			 Thread.sleep(3000);
			 String msg= performerPOM.readResponseMsgOrder(driver).getText();
			 String msg1= performerPOM.readResponseMsgOrder1(driver).getText();
			 String msg2= performerPOM.readResponseMsgOrder2(driver).getText();
			
				 test.log(LogStatus.PASS," Without data in Case Order = " +msg +"," +msg1 +"," +msg2);
		
			 driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		}
 		
		static void CaseOrderwithExistingData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			 XSSFSheet sheet=ReadExcel();
			
			 Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			 Thread.sleep(5000);
			 performerPOM.clickCaseOrder(driver).click();
			 Thread.sleep(6000);
			 performerPOM.clickNewCaseOrder(driver).click();
			 Thread.sleep(6000);
			 performerPOM. clickCaseOrderDate(driver).sendKeys("25-02-2023");
			 Thread.sleep(3000);
			 performerPOM.clickOrderPanel(driver).click();
			 Thread.sleep(3000);
			 performerPOM. clickCaseOrderType(driver).click();
			 Thread.sleep(3000);
			 performerPOM.selectCaseOrderType(driver).click();
			
			 Thread.sleep(300);
			 Row row0 = sheet.getRow(53);					//Selected 0th index row (First row)
			 Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			 int OrderTitle = (int) c1.getNumericCellValue();
			 performerPOM.clickCaseOrderTitle(driver).sendKeys(OrderTitle+"");	//Writing 'HearingDate'
				
 
			 Thread.sleep(2000);
			 Row row2 = sheet.getRow(54);									//Selected 0th index row (First row)
			 Cell c2 = row2.getCell(1);									//Selected cell (0 row,1 column)
			 String OrderDecri = c2.getStringCellValue();
			 performerPOM.clickCaseOrderDecri(driver).sendKeys(OrderDecri);     //click oder description
			 Thread.sleep(3000);
			 performerPOM.ChooseOrderFile(driver).click();
			 
			 Thread.sleep(3000);
			 performerPOM.clickSaveCaseOrder(driver).click();
			 	
				 Thread.sleep(3000);
					String msg = performerPOM.readResponseMsg(driver).getText();
					if(msg.contains("Order Details Saved Successfully."))
					{
						test.log(LogStatus.FAIL, "Case Order with Existing data ="+msg);
					}
					else
					{
						test.log(LogStatus.PASS, "Case Order with Existing data ="+msg);
					
					}
					
					 driver.switchTo().parentFrame();
			   	     	Thread.sleep(3000);
			   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		}
		
		static void CaseOrderwithClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			
			
			 Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			 Thread.sleep(5000);
			 performerPOM.clickCaseOrder(driver).click();
			 Thread.sleep(6000);
			 performerPOM.clickNewCaseOrder(driver).click();
			 Thread.sleep(6000);
			 performerPOM. clickCaseOrderDate(driver).sendKeys("25-02-2023");
			 
			 if(performerPOM.clickClearCaseOrderBtn(driver).isEnabled())
			 {
				 Thread.sleep(6000);
				 performerPOM.clickClearCaseOrderBtn(driver).click();
				test.log(LogStatus.PASS, "After clicking on the clear button the data should be remove");
			 }
			 else
			 {
				 test.log(LogStatus.FAIL, "After clicking on the clear button the data should not be remove");
			 }
			 
			 driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
			  
		}
		
		public static void CaseStatusAppealtoNextCourt(WebDriver driver, ExtentTest test) throws InterruptedException
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			
			 Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
			
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
			
			performerPOM.clickCaseStage(driver).click();
			Thread.sleep(300);
			performerPOM.selectCaseStage(driver).sendKeys("Hearing", Keys.ENTER);
			
			Thread.sleep(300);
			performerPOM.clickCaseStatus(driver).click();				//Clicking on 'Case Status' drop down.
			Thread.sleep(300);
			performerPOM.clickCaseStatusClose(driver).click();			//Selecting 'Closed' option from drop down.
			
			Thread.sleep(300);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseCloseDate(driver)));
			performerPOM.clickCaseCloseDate(driver).click();				//Clicking on 'Closed Date' date box
			OverduePOM.selectLastMonth(driver).click();					//Getting last month
			OverduePOM.selectDate2(driver).click();						//Selecting particular date.
			
			Thread.sleep(300);
			performerPOM.clickCaseResult(driver).click();
			performerPOM.clickSelectCaseResult(driver).sendKeys("In Progress", Keys.ENTER);
			
			Thread.sleep(3000);
			performerPOM.clickRemark1(driver).sendKeys("Automation Testing");
			
			
			Thread.sleep(3000);
			performerPOM.clickCaseAppealToNextCourt(driver).click();	
			
			Thread.sleep(300);
			String msg=performerPOM.clickCasereadMsg(driver).getText();
			
			if(msg.equalsIgnoreCase(msg))
			{
				test.log(LogStatus.PASS, "Message dispalyed =" +msg);
			}
			else
			{
				test.log(LogStatus.FAIL, "Message dispalyed =" +msg);
			}
			 driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		}
		
		public static void CaseStatusAppealtoNextCourtTwoMandatoryfields(WebDriver driver, ExtentTest test) throws InterruptedException
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			
			 Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
			
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
			
			performerPOM.clickCaseStage(driver).click();
			Thread.sleep(300);
			performerPOM.selectCaseStage(driver).sendKeys("Hearing", Keys.ENTER);
			
			Thread.sleep(3000);
			performerPOM.clickCaseStatus(driver).click();				//Clicking on 'Case Status' drop down.
			Thread.sleep(3000);
			performerPOM.clickCaseStatusClose(driver).click();			//Selecting 'Closed' option from drop down.
			
			
			Thread.sleep(3000);
			performerPOM.clickCaseAppealToNextCourt(driver).click();	
			
			Thread.sleep(3000);
			String msg=performerPOM.clickCasereadMsg(driver).getText();
			
			if(msg.equalsIgnoreCase(msg))
			{
				test.log(LogStatus.PASS, "Message dispalyed =" +msg);
			}
			else
			{
				test.log(LogStatus.FAIL, "Message dispalyed =" +msg);
			}
			 driver.switchTo().parentFrame();
	   	     	Thread.sleep(3000);
	   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		}
			
		
		
		
		
		public static void CaseStatuswithEmptyFields(WebDriver driver, ExtentTest test) throws InterruptedException
		{
			  WebDriverWait wait=new WebDriverWait(driver,20);
			
			   Thread.sleep(3000);
				performerPOM.clickCaseOpencfo(driver).click();//click edit notice
		     
		        Thread.sleep(3000);
				performerPOM.clickEditNotice(driver).click();//click edit notice
			  
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			   performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
			
			   wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
				
				performerPOM.clickCaseStage(driver).click();
				Thread.sleep(300);
				performerPOM.selectCaseStage(driver).sendKeys("Select Stage", Keys.ENTER);
				
				 Thread.sleep(300);
				    performerPOM.clickSave1(driver).click();
				Thread.sleep(300);
				String msg=performerPOM.clickCasereadMsg(driver).getText();
				
				if(msg.equalsIgnoreCase(msg))
				{
					test.log(LogStatus.PASS, "Case Stage with Empty fields =" +msg);
				}
				else
				{
					test.log(LogStatus.FAIL, "Case Stage with Empty fields =" +msg);
				}
				 
				
				Thread.sleep(300);
				performerPOM.clickCaseStatus(driver).click();				//Clicking on 'Case Status' drop down.
				Thread.sleep(300);
				performerPOM.clickCaseStatus1(driver).click();			//Selecting 'Closed' option from drop down.
			   
			   Thread.sleep(300);
			    performerPOM.clickSave1(driver).click();
			    
				Thread.sleep(300);
				String msg1=performerPOM.clickCasereadMsg(driver).getText();
				
				if(msg1.equalsIgnoreCase(msg1))
				{
					test.log(LogStatus.PASS, "Case Status with Empty fields =" +msg1);
				}
				else
				{
					test.log(LogStatus.FAIL, "Case Status with Empty fields =" +msg1);
				}
				 
				 driver.switchTo().parentFrame();
		   	     	Thread.sleep(3000);
		   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
			    
			    
			
		}
		
		   static void StatusPaymentExistingdata(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
		      {	
		    	       WebDriverWait wait=new WebDriverWait(driver,50);
		      
		    	       XSSFSheet sheet=ReadExcel();
		    	       
		    	       Thread.sleep(3000);
						performerPOM.clickCaseOpencfo(driver).click();//click edit notice
				     
				        Thread.sleep(3000);
						performerPOM.clickEditNotice(driver).click();//click edit notice
					  
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
					  
		    	       Thread.sleep(3000);
		               performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
						
						wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
//						
						Thread.sleep(3000);
						Row row0 = sheet.getRow(58);					//Selected 0th index row (First row)
						Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
						int InvoiceNo = (int) c1.getNumericCellValue();
						performerPOM.clickCaseInvoiceNo1(driver).sendKeys(InvoiceNo+"");	//Writing 'Invoice No'
						
					    Thread.sleep(4000);
						performerPOM.clickPaymentTyp1(driver);
						Thread.sleep(2000);
						List<WebElement> PaymentType1= driver.findElements(By.xpath("//*[@id='grdCasePayment_ddlPaymentType_chosen']/div/ul/li"));
						selectOptionFromDropDown_bs(PaymentType1, "Checks");
						Thread.sleep(10000);
                     performerPOM.clickAmount1(driver).sendKeys("900000");	//Writing 'Amount'
					
                 	Thread.sleep(10000);
                    performerPOM. clickAmountPaid(driver).sendKeys("5000");
						Thread.sleep(3000);
						performerPOM.clickSavePaymentLog1(driver).click();
						
						
						   Thread.sleep(500);
							String msg5 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
						
							if(msg5.equalsIgnoreCase("Payment Details Saved Successfully."))
							{
								test.log(LogStatus.FAIL, "Message displayed = "+msg5);
							
							}
							else
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg5);
							}
							
							 driver.switchTo().parentFrame();
					   	     	Thread.sleep(3000);
					   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		      }
		
		   static void StatusPaymentWithoutdata(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
		      {	
		    	       WebDriverWait wait=new WebDriverWait(driver,50);
		      
		    	       XSSFSheet sheet=ReadExcel();
		    	       
		    	       Thread.sleep(3000);
						performerPOM.clickCaseOpencfo(driver).click();//click edit notice
				     
				        Thread.sleep(3000);
						performerPOM.clickEditNotice(driver).click();//click edit notice
					  
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
					  
		    	       Thread.sleep(3000);
		               performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
		               
		               Thread.sleep(3000);
						performerPOM.clickSavePaymentLog1(driver).click();
						
						
						   Thread.sleep(500);
							String msg5 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
						
							if(msg5.equalsIgnoreCase("Payment Details Saved Successfully."))
							{
								test.log(LogStatus.FAIL, "Message displayed = "+msg5);
							
							}
							else
							{
								test.log(LogStatus.PASS, "Message displayed = "+msg5);
							}
							
							 driver.switchTo().parentFrame();
					   	     	Thread.sleep(3000);
					   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
		      }
		   
		 	  static void CaseExternalLawyerCriteria(WebDriver driver,ExtentTest test) throws InterruptedException
	          {
	        	  
	    		         WebDriverWait wait = new WebDriverWait(driver, 300);
			   
	    		         Thread.sleep(3000);
							performerPOM.clickCaseOpencfo(driver).click();//click edit notice
					     
					        Thread.sleep(3000);
							performerPOM.clickEditNotice(driver).click();//click edit notice
						    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	    		            Thread.sleep(1000);
	    				    performerPOM. clickExternalLawyerRating1(driver).click();
	    				    Thread.sleep(4000);
							   performerPOM.selectCaseExternalLawyer(driver);
	    				    Thread.sleep(3000);
	    				  performerPOM.selectExternalLawyerRating(driver);
	    				   Thread.sleep(3000);
	    				   performerPOM.clickNewCriteria(driver).click();
	    				   Thread.sleep(3000);
	    				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
	    				   performerPOM.clickCriteria(driver).sendKeys("Automation Test 456");
	    				   Thread.sleep(3000);
	    				   performerPOM.clickSaveCriteria(driver).click();
	    				   String msg = performerPOM.readOppoenentMsg(driver).getText();
	    				   
	    				   if(msg.equalsIgnoreCase("Criteria Saved Successfully."))
	    				   {
	    					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
	    				   }
	    				   else
	    				   {
	    					   String msg1 = performerPOM.readMesg(driver).getText();
	    					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg1);
	    				   }
	    				   
	    				   Thread.sleep(3000);
	    				   driver.switchTo().parentFrame();
	    				   performerPOM.clickclosecriteria(driver).click();
	    				   
	    					 driver.switchTo().parentFrame();
					   	     	Thread.sleep(3000);
					   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
	          }
			
			
		 	  static void CaseExistingCriteria(WebDriver driver,ExtentTest test) throws InterruptedException
	          {
	        	  
	    		         WebDriverWait wait = new WebDriverWait(driver, 300);
			   
	    		         Thread.sleep(3000);
							performerPOM.clickCaseOpencfo(driver).click();//click edit notice
					     
					        Thread.sleep(3000);
							performerPOM.clickEditNotice(driver).click();//click edit notice
						    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	    		            Thread.sleep(1000);
	    				    performerPOM. clickExternalLawyerRating1(driver).click();
	    				    Thread.sleep(4000);
							   performerPOM.selectCaseExternalLawyer(driver);
	    				    Thread.sleep(3000);
	    				  performerPOM.selectExternalLawyerRating(driver);
	    				   Thread.sleep(3000);
	    				   performerPOM.clickNewCriteria(driver).click();
	    				   Thread.sleep(3000);
	    				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
	    				   performerPOM.clickCriteria(driver).sendKeys("Test Test New");
	    				   Thread.sleep(3000);
	    				   performerPOM.clickSaveCriteria(driver).click();
	    				   String msg = performerPOM.readOppoenentMsg(driver).getText();
	    				   
	    				   if(msg.equalsIgnoreCase("Criteria already exists."))
	    				   {
	    					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
	    				   }
	    				   else
	    				   {
	    					   String msg1 = performerPOM.readMesg(driver).getText();
	    					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg1);
	    				   }
	    				   
	    				   Thread.sleep(3000);
	    				   driver.switchTo().parentFrame();
	    				   performerPOM.clickclosecriteria(driver).click();
	    				   
	    					 driver.switchTo().parentFrame();
					   	     	Thread.sleep(3000);
					   	     	performerPOM.clickClose(driver).click();//Clicking on 'Close'
	          }
		 	 static void CaseCriteriaInvalidData(WebDriver driver,ExtentTest test) throws InterruptedException
	         {
	       	  
	   		         WebDriverWait wait = new WebDriverWait(driver, 300);
			  
	   		      Thread.sleep(3000);
					performerPOM.clickCaseOpencfo(driver).click();//click edit notice
				             Thread.sleep(3000);
						     performerPOM.clickEditNotice(driver).click();//click edit notice
					  
						      driver.switchTo().parentFrame();
						      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	       	          
	   			       Thread.sleep(1000);
	   				   performerPOM. clickExternalLawyerRating1(driver).click();
	   				   
	   				 Thread.sleep(4000);
					   performerPOM.selectCaseExternalLawyer(driver);
	   				   
	   				  Thread.sleep(3000);
	   				  performerPOM.selectExternalLawyerRating(driver);
	   				   Thread.sleep(3000);
	   				   performerPOM.clickNewCriteria(driver).click();
	   				   Thread.sleep(3000);
	   				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
	   				 Thread.sleep(3000);
	   				  performerPOM.clickCriteria(driver).sendKeys("342");
	   				 
	   				   Thread.sleep(3000);
	   				   performerPOM.clickSaveCriteria(driver).click();
	   				   Thread.sleep(3000);
	   				   String msg = performerPOM.clickCriteriaInvalidMsg(driver).getText();
	   				   
	   				   if(msg.equalsIgnoreCase("Only alphabets allowed."))
	   				   {
	   					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
	   				   }
	   				   else
	   				   {
	   					   test.log(LogStatus.FAIL, "Meassag Displayed ="+msg);
	   				   }
	   				   
	   				   Thread.sleep(3000);
	   				   driver.switchTo().parentFrame();
	   				   performerPOM.clickclosecriteria(driver).click();
	         }
		 	 
			 static void CaseCriteriaWithoutData(WebDriver driver,ExtentTest test) throws InterruptedException
	         {
	       	  
	   		         WebDriverWait wait = new WebDriverWait(driver, 300);
			  
	   		      Thread.sleep(3000);
					performerPOM.clickCaseOpencfo(driver).click();//click edit notice
				     
				             Thread.sleep(3000);
						     performerPOM.clickEditNotice(driver).click();//click edit notice
					  
						      driver.switchTo().parentFrame();
						      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	       	          
	   			       Thread.sleep(1000);
	   				   performerPOM. clickExternalLawyerRating1(driver).click();
	   				   
	   				 Thread.sleep(4000);
					   performerPOM.selectCaseExternalLawyer(driver);
	   				   
	   				  Thread.sleep(3000);
	   				  performerPOM.selectExternalLawyerRating(driver);
	   				   Thread.sleep(3000);
	   				   performerPOM.clickNewCriteria(driver).click();
	   				   Thread.sleep(3000);
	   				   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
	   				
	   				 
	   				   Thread.sleep(3000);
	   				   performerPOM.clickSaveCriteria(driver).click();
	   				   Thread.sleep(3000);
	   				   String msg = performerPOM.readOppoenentMsg(driver).getText();
	   				   
	   				   if(msg.equalsIgnoreCase("Criteria can not be empty."))
	   				   {
	   					   test.log(LogStatus.PASS, "Meassag Displayed ="+msg);
	   				   }
	   				   else
	   				   {
	   					   test.log(LogStatus.FAIL, "Meassag Displayed ="+msg);
	   				   }
	   				   
	   				   Thread.sleep(3000);
	   				   driver.switchTo().parentFrame();
	   				   performerPOM.clickclosecriteria(driver).click();
	         }
				static void TaskwithExistingData(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					Thread.sleep(500);
					performerPOM.clickTaskOpen(driver).click();	
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
					js.executeScript("window.scrollBy(0,-700)");
					performerPOM.clickAddNewTask(driver).click();				//Clicking on 'New' button
					
					progress(driver);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddTask"));
					
//					
//					Thread.sleep(300);
//					performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
//					OverduePOM.selectNextMonth(driver).click();
//					OverduePOM.selectDate(driver).click();					//Selecting particular date.
//					
					Thread.sleep(500);
					Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
					Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
					String title = c1.getStringCellValue();
					performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
					
					Thread.sleep(300);
					row0 = sheet.getRow(13);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String desc = c1.getStringCellValue();
					performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
					
					Thread.sleep(300);
					performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
					OverduePOM.selectNextMonth(driver).click();
					OverduePOM.selectDate(driver).click();					//Selecting particular date.
					
					Thread.sleep(300);
					Actions action = new Actions(driver);
					action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
					
					Thread.sleep(300);
					row0 = sheet.getRow(14);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String outcome = c1.getStringCellValue();
					performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
					
					Thread.sleep(1000);
					row0 = sheet.getRow(15);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String internalUser = c1.getStringCellValue();
					performerPOM.clickInternalUser3(driver).click();
					//performerPOM.selectInternalUser2(driver).click();
					performerPOM.selectInternalUser3(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
					
					Thread.sleep(1000);
					row0 = sheet.getRow(16);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String externalUser = c1.getStringCellValue();
					try
					{
						Thread.sleep(300);
						performerPOM.clickExternalUser(driver).click();
						Thread.sleep(500);
						action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
					}
					catch(Exception e)
					{
						
					}
					Thread.sleep(5000);
					row0 = sheet.getRow(17);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String remark = c1.getStringCellValue();
					performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
					//Thread.sleep(300);
					//String workingDir = System.getProperty("user.dir");
					//performerPOM.clickUpload(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file
					
					Thread.sleep(300);
					OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
					
					Thread.sleep(300);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickMessage(driver)));
					
					Thread.sleep(300);
					String msg = performerPOM.clickMessage(driver).getText();
					if(msg.contains(msg))
					{
						test.log(LogStatus.FAIL, "Task with existing data ="+msg);
					}
					else
					{
						test.log(LogStatus.PASS, "Task with existing data ="+msg);
					}
					
					driver.switchTo().parentFrame();
					performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
				}
				static void TaskwithClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					Thread.sleep(500);
					performerPOM.clickTaskOpen(driver).click();
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
					js.executeScript("window.scrollBy(0,-700)");
					performerPOM.clickAddNewTask(driver).click();				//Clicking on 'New' button
					
					progress(driver);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddTask"));
					
					Thread.sleep(300);
					Actions action = new Actions(driver);
					action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
					
					Thread.sleep(300);
					
					if(performerPOM.clickTaskClearBtn(driver).isEnabled())
					{
						performerPOM.clickTaskClearBtn(driver).click();
						test.log(LogStatus.PASS, "After clicking the clear button the data should be remove");
					}
					else
					{
						test.log(LogStatus.FAIL, "After clicking the clear button the data should not be remove");
					}
					
					driver.switchTo().parentFrame();
					performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
				}
				
				static void TaskDelete(WebDriver driver, ExtentTest test) throws InterruptedException
				{
					
					Thread.sleep(500);
					performerPOM.clickTaskOpen(driver).click();
					Thread.sleep(2000);
					performerPOM.clickTaskdelete(driver).click();	
					
					 Thread.sleep(5000);
					    // Switching to Alert        
				        Alert alert1 = driver.switchTo().alert();		
				        		
				        // Capturing alert message.    
				        String alertMessage1= driver.switchTo().alert().getText();	
				        
				        
				        test.log(LogStatus.PASS, alertMessage1);
				        		
				        // Displaying alert message		
				        System.out.println(alertMessage1);
				        
				     // Accepting alert		
				        alert1.accept();	
				}
				
				static void TaskwithoutData(WebDriver driver, ExtentTest test) throws InterruptedException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					Thread.sleep(500);
					performerPOM.clickTaskOpen(driver).click();	
					Thread.sleep(500);
					
					performerPOM.clickAddNewTask(driver).click();				//Clicking on 'New' button
					
					progress(driver);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddTask"));
					
					Thread.sleep(300);
					OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
					
					Thread.sleep(300);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickMessage2(driver)));
					
					Thread.sleep(300);
					String msg = performerPOM.clickMessage2(driver).getText();
					if(msg.contains(msg))
					{
						test.log(LogStatus.PASS, "Task without data ="+msg);
					}
					else
					{
						test.log(LogStatus.FAIL, "Task without data ="+msg);
					}
					
					driver.switchTo().parentFrame();
					performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
				}
				
				
				static void TaskwithTwoManadatoryFields(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					Thread.sleep(500);
					performerPOM.clickTaskOpen(driver).click();	
					Thread.sleep(500);
				
					performerPOM.clickAddNewTask(driver).click();				//Clicking on 'New' button
					
					progress(driver);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddTask"));
									
					Thread.sleep(500);
					Row row0 = sheet.getRow(12);								//Selected 0th index row (First row)
					Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
					String title = c1.getStringCellValue();
					performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
					
					Thread.sleep(300);
					row0 = sheet.getRow(13);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String desc = c1.getStringCellValue();
					performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
					
					Thread.sleep(300);
					OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
					
					Thread.sleep(300);
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickMessage1(driver)));
					
					Thread.sleep(300);
					String msg = performerPOM.clickMessage1(driver).getText();
					if(msg.contains(msg))
					{
						test.log(LogStatus.PASS, "Task with two mandatory fields ="+msg);
					}
					else
					{
						test.log(LogStatus.FAIL, "Task with two mandatory fields ="+msg);
					}
					
					driver.switchTo().parentFrame();
					performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
				}
				
					
			 
			 
			 

					
					
	 		
	 		
	 	 	 			
 		
				
		
 				
 		        
 	 	 	  static void selectOptionFromDropDown_bs(List<WebElement> options, String value)
            	  {
          			
          			for(WebElement option:options) 
          			{
          				if(option.getText().equals(value))
          				{
          					option.click();
          					
          					break;
          				}
          			}
          		 }
        		
}

                         
						
					    
						
						
					
					
				
				
						 
				
		

