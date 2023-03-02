package litigationFE;

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

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import licensePerformer.LiPerformerPOM;
import litigationAdditionalOwner.performerPOM;
import performer.OverduePOM;

public class FeMethod {
	
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
				test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    
			Thread.sleep(3000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(3000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
			
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
			File dir = new File("C://Users//Admin//Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C://Users//Admin//Downloads");
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
					test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			performerPOM.caseNoticeSummaryGraphClose(driver).click();
			
			Thread.sleep(3000);
			OverduePOM.clickDashboard(driver).click();
			
	   }	
	public static void CaseNoticeTypeGraph(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
	{
		
//		Thread.sleep(3000);
//		performerPOM.CaseNoticeTypeSummaryGraph1(driver).click();
		
		
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
       	js.executeScript("window.scrollBy(0,1000)");
		
       	Thread.sleep(2000);
	
       	int	open = Integer.parseInt(performerPOM.CaseNoticeTypeSummaryGraph1(driver).getText());	//Reading Notice Open count.
	    performerPOM.CaseNoticeTypeSummaryGraph1(driver).click();						//Clicking on 'Open' notice
	    
	    
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
			test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
			test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
			test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
		}
       	
    
		Thread.sleep(5000);
		performerPOM.CaseNoticeTypeViewGraph(driver).click();
		
		Thread.sleep(5000);
		performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
		
		
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
		
//		Thread.sleep(5000);
//		performerPOM.clickAgeFilter(driver).click();
//		
//		Thread.sleep(5000);
//		performerPOM.selectAgeFiltercfo(driver).click();
		
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
		File dir = new File("C://Users//Admin//Downloads");
		File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
		
		Thread.sleep(500);
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
		Thread.sleep(250);
		performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		Thread.sleep(5500);
		File dir1 = new File("C://Users//Admin//Downloads");
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
				test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
				test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
			}
			else
			{
				test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
				test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
		}
		

		Thread.sleep(7000);
		performerPOM.clearButton(driver).click();
		
		
		Thread.sleep(3000);
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		performerPOM.caseNoticeSummaryGraphClose(driver).click();
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		
   }	
	public static void RiskSummaryGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
	
	{
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
       	js.executeScript("window.scrollBy(0,1500)");
		
       	Thread.sleep(2000);
	
      	int	open = Integer.parseInt(performerPOM.RiskSummaryGraph1(driver).getText());	//Reading Notice Open count.
	    performerPOM.RiskSummaryGraph1(driver).click();						//Clicking on 'Open' notice
	
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
			test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
			test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
			test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
		}
       	
    	
		Thread.sleep(5000);
		performerPOM.CaseNoticeTypeViewGraph(driver).click();
		
		Thread.sleep(5000);
		performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
		
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
		
//		Thread.sleep(5000);
//		performerPOM.clickAgeFilter(driver).click();
//		
//		Thread.sleep(5000);
//		performerPOM.selectAgeFiltercfo(driver).click();
		
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
		File dir = new File("C://Users//Admin//Downloads");
		File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
		
		Thread.sleep(500);
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
		Thread.sleep(250);
		performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		Thread.sleep(5500);
		File dir1 = new File("C://Users//Admin//Downloads");
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
				test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
				test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
			}
			else
			{
				test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
				test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
		}
		

		Thread.sleep(7000);
		performerPOM.clearButton(driver).click();
		
		
		Thread.sleep(3000);
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		performerPOM.caseNoticeSummaryGraphClose(driver).click();
		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		
	}
	 public static void DepartmentSummaryGraph(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	       	js.executeScript("window.scrollBy(0,1500)");
			
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
				test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
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
			File dir = new File("C://Users//Admin//Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C://Users//Admin//Downloads");
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
					test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			
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
	      	js.executeScript("window.scrollBy(0,1800)");
			
	      	Thread.sleep(2000);
		
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
				test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
				test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
				test.log(LogStatus.FAIL, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
			}
	       	
	    	
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeViewGraph(driver).click();
			
			Thread.sleep(5000);
			performerPOM.CaseNoticeTypeclosePopupGraph(driver).click();
			
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
			File dir = new File("C://Users//Admin//Downloads");
			File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
			
			Thread.sleep(500);
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
			Thread.sleep(250);
			performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			Thread.sleep(5500);
			File dir1 = new File("C://Users//Admin//Downloads");
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
					test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
				}
				else
				{
					test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
			}
			

			Thread.sleep(7000);
			performerPOM.clearButton(driver).click();
			
			
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
		      	js.executeScript("window.scrollBy(0,2000)");
				
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
					test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
					test.log(LogStatus.PASS, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
				}
				else
				{
					test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
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
//				Thread.sleep(5000);
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
				File dir = new File("C://Users//Admin//Downloads");
				File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(500);
				CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
				Thread.sleep(250);
				performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
				test.log(LogStatus.PASS, "File downloaded successfully.");
				
				Thread.sleep(5500);
				File dir1 = new File("C://Users//Admin//Downloads");
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
						test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
						test.log(LogStatus.PASS, "Total records from Grid = "+count2+" | Total records from Report = "+records);
					}
					else
					{
						test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
						test.log(LogStatus.FAIL, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
					}
				}
				else
				{
					test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
				}
				

				Thread.sleep(7000);
				performerPOM.clearButton(driver).click();
				
				
				Thread.sleep(3000);
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				performerPOM.caseNoticeSummaryGraphClose(driver).click();
				
				Thread.sleep(3000);
				OverduePOM.clickDashboard(driver).click();
			}

}
