package compliancemanagement;


import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;

import litigationAdditionalOwner.performerPOM;

public class MgmtMethod
{
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static XSSFSheet sheet1 = null;		//Sheet variable

	
	public static void UserLogReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 60);
    	Thread.sleep(5000);
    	MgmtPOM.clickMyReport(driver).click();
    	
    	Thread.sleep(2000);
   	     js.executeScript("arguments[0].scrollIntoView();", MgmtPOM.clickUserLogReport(driver));

       	
   		Thread.sleep(7000);
       	if(MgmtPOM.clickUserLogReport(driver).isDisplayed())
		{
       		
			Thread.sleep(7000);
			MgmtPOM.clickUserLogReport(driver).click();
			test.log(LogStatus.PASS, "The tab of User Log Report should be seen under the tab of Report dropdown.");
		}
		else
		{
			test.log(LogStatus.FAIL, "The tab of User Log Report  should not be seen under the tab of Report dropdown.");
		}
     }
	
	public static void SelectMultipleUsers(WebDriver driver, ExtentTest test) throws InterruptedException, IOException, AWTException
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Thread.sleep(8000);
    	MgmtPOM.clickMyReport(driver).click();
	
    	Thread.sleep(2000);
    	 js.executeScript("arguments[0].scrollIntoView();", MgmtPOM.clickUserLogReport(driver));

    	
   	
    	Thread.sleep(7000);
		MgmtPOM.clickUserLogReport(driver).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
		
		Thread.sleep(7000);
		MgmtPOM.clickUserFilter(driver).click();
		
		Thread.sleep(7000);
		MgmtPOM.selectUser(driver).click();
		
		Thread.sleep(7000);
		MgmtPOM.selectUser1(driver).click();
		
		
		Thread.sleep(4000);
	  	if(MgmtPOM.clickapplybtn(driver).isEnabled())
			{
				Thread.sleep(4000);
				MgmtPOM.clickapplybtn(driver).click();
				test.log(LogStatus.PASS, "Multiple users selected  from the user dropdown.");
			}
			else
			{
				test.log(LogStatus.FAIL, "Multiple users not selected  from the user dropdown.");
			}
	  
	  }
	public static void UserFilter(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
		
			Thread.sleep(8000);
    		MgmtPOM.clickMyReport(driver).click();
    		
    		Thread.sleep(2000);
    		js.executeScript("arguments[0].scrollIntoView();", MgmtPOM.clickUserLogReport(driver));
       	
    		Thread.sleep(7000);
    		MgmtPOM.clickUserLogReport(driver).click();
    		
    		Thread.sleep(7000);
    		MgmtPOM.clickUserFilter(driver).click();
    		
		   	Thread.sleep(3000);
	       	String user=MgmtPOM.selectUser(driver).getText();
	       	Thread.sleep(3000);
	       	MgmtPOM.selectUser(driver).click();
	       	Thread.sleep(3000);
			MgmtPOM.clickapplybtn(driver).click();
			  
			  List<String> li=new ArrayList<String>();
		        li.add(user);
		        
		        List<String> filter=new ArrayList<String>();	
				filter.add("user");
				
				Actions a = new Actions(driver);
				//scroll down a page
				a.sendKeys(Keys.PAGE_DOWN).build().perform();
				
				CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
				String s = CFOcountPOM.readTotalItems1(driver).getText();
				Thread.sleep(2000);

				if(!s.equalsIgnoreCase("No items to display")) {
				Thread.sleep(5000);
			
				List<WebElement> usercol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[1]"));
				
				for(int i=0; i<li.size(); i++){
					
					List<String> text= new ArrayList<String>();
					HashSet<String> pass=new LinkedHashSet<>();
					HashSet<String> fail=new LinkedHashSet<>();
					List<WebElement> raw=new ArrayList<WebElement>();

						if(i==0)
						{
							raw.addAll(usercol);
						}
						
						for(int k=0;k<raw.size();k++)
						{
							text.add(raw.get(k).getText());
						}

						for(int l=0;l<text.size();l++)
						{
							
				
							
							if(text.get(l).equals(li.get(i)))
							{
							
							
								pass.add(text.get(l));	
								System.out.println("pass : "+text.get(l)+" : "+li.get(i));

							}
							else
							{
								fail.add(text.get(l));		
								System.out.println("fail : "+text.get(l)+" : "+li.get(i));
								System.out.println(i);

							}
						 }
						
					for(String Fal : fail)
					{
							test.log(LogStatus.FAIL, filter.get(i)+" column shows incorrect value : "+Fal);
					}	
					for(String Pas : pass)
					{
						test.log(LogStatus.PASS,  filter.get(i)+" dropdown working properly.");
						test.log(LogStatus.PASS, filter.get(i)+" displayed : "+Pas);	
						System.out.println(filter.get(i)+" : "+Pas);
					}
						text.clear();
						pass.clear();
						fail.clear();
						raw.clear();
				
				
					}
					}
					else 
					{
						test.log(LogStatus.PASS,"No records found");	
					}
				
	}
	
	public static void ExportButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(8000);
		MgmtPOM.clickMyReport(driver).click();
		
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", MgmtPOM.clickUserLogReport(driver));
   	
		Thread.sleep(7000);
		MgmtPOM.clickUserLogReport(driver).click();
		
		
		Thread.sleep(7000);
		MgmtPOM.clickUserFilter(driver).click();
		Thread.sleep(7000);
       	MgmtPOM.selectUser(driver).click();
       	Thread.sleep(7000);
		MgmtPOM.clickapplybtn(driver).click();
		  
	
		Actions a = new Actions(driver);
		//scroll down a page
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();
		String item1 = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits1 = item1.split(" ");								//Splitting the String
		String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
		int count2 = Integer.parseInt(compliancesCount1);
		
	    try
		{
	    	MgmtPOM.clickExport(driver).sendKeys(Keys.PAGE_DOWN);
		}
		catch(Exception e)
		{
			
		}
		//js.executeScript("window.scrollBy(0,1000)");
		
	
		Thread.sleep(100);
		File dir = new File("C:\\Users\\snehalp\\Downloads");
		File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
		
		Thread.sleep(500);
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
		Thread.sleep(250);
		MgmtPOM.clickExport(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		Thread.sleep(5500);
		File dir1 = new File("C:\\Users\\snehalp\\Downloads");
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
		
		
	}
	
	public static void LeaveReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
    	Thread.sleep(5000);
    	MgmtPOM.clickMyReport(driver).click();
       	
       
       	if(MgmtPOM.clickLeaveReport(driver).isDisplayed())
		{
       		
			Thread.sleep(7000);
			MgmtPOM.clickLeaveReport(driver).click();
			test.log(LogStatus.PASS, "The tab of Leave  Report should be seen under the tab of Report dropdown.");
		}
		else
		{
			test.log(LogStatus.FAIL, "The tab of Leave Report  should not be seen under the tab of Report dropdown.");
		}
     }
	public static void SelectMultipleUsersLeaveReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException, AWTException
	{
	
		Thread.sleep(8000);
    	MgmtPOM.clickMyReport(driver).click();
		
    	
    	Thread.sleep(7000);
		MgmtPOM.clickLeaveReport(driver).click();
		
		
		Thread.sleep(7000);
		MgmtPOM.clickUserFilter1(driver).click();
		
		Thread.sleep(7000);
		MgmtPOM.selectUser(driver).click();
		
		Thread.sleep(7000);
		MgmtPOM.clickUserFilter1(driver).click();
		
		Thread.sleep(7000);
		MgmtPOM.selectUser1(driver).click();
		
		
		Thread.sleep(4000);
	  	if(MgmtPOM.clickLeaveapplybtn(driver).isEnabled())
			{
				Thread.sleep(4000);
				MgmtPOM.clickLeaveapplybtn(driver).click();
				test.log(LogStatus.PASS, "Select multiple users from the user dropdown.");
			}
			else
			{
				test.log(LogStatus.FAIL, "Select not multiple users from the user dropdown.");
			}
	  
	  }
	
	public static void UserFilterLeaveReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
			Thread.sleep(8000);
    		MgmtPOM.clickMyReport(driver).click();
		
    	
    		Thread.sleep(7000);
			MgmtPOM.clickLeaveReport(driver).click();
		
		
			Thread.sleep(7000);
			MgmtPOM.clickUserFilter1(driver).click();
	
		   	Thread.sleep(500);
	       	String user=MgmtPOM.selectUser(driver).getText();
	       	Thread.sleep(500);
	       	MgmtPOM.selectUser(driver).click();
	       	//Thread.sleep(7000);
			//MgmtPOM.clickUserFilter1(driver).click();
	      
			Thread.sleep(7000);
			MgmtPOM.clickStartDateCalender(driver).click();
			Thread.sleep(500);
	       	MgmtPOM.clickDate(driver).click();
			Thread.sleep(500);
	       	String startdate=MgmtPOM.clickStartDatepiker(driver).getText();
	       
	       	
	       	Thread.sleep(7000);
			MgmtPOM.clickEndDateCalender(driver).click();
			Thread.sleep(500);
	       	MgmtPOM.clickDate1(driver).click();
			Thread.sleep(500);
	       	String enddate=MgmtPOM.clickEndDatepiker(driver).getText();
	       
	       	
	       	Thread.sleep(500);
			MgmtPOM.clickLeaveapplybtn(driver).click();
			
			
			  
			  List<String> li=new ArrayList<String>();
		      li.add(user);
		      li.add(startdate);
		      li.add(enddate);
		        
		        
		        List<String> filter=new ArrayList<String>();	
				filter.add("user");
				filter.add("startdate");
				filter.add("enddate");
				
				
				Actions a = new Actions(driver);
				//scroll down a page
				a.sendKeys(Keys.PAGE_DOWN).build().perform();
				
				CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
				String s = CFOcountPOM.readTotalItems1(driver).getText();
				Thread.sleep(2000);

				if(!s.equalsIgnoreCase("No items to display")) {
				Thread.sleep(5000);
			
				List<WebElement> usercol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[1]"));
				List<WebElement> StartDatecol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[2]"));
				List<WebElement> EndDatecol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[3]"));
				
				
				for(int i=0; i<li.size(); i++){
					
					List<String> text= new ArrayList<String>();
					HashSet<String> pass=new LinkedHashSet<>();
					HashSet<String> fail=new LinkedHashSet<>();
					List<WebElement> raw=new ArrayList<WebElement>();

						if(i==0)
						{
							raw.addAll(usercol);
						}
						else if(i==1)
						{
							raw.addAll(StartDatecol);
						}
						else if(i==2)
						{
							raw.addAll(EndDatecol);
						}
						
						for(int k=0;k<raw.size();k++)
						{
							text.add(raw.get(k).getText());
						}

						for(int l=0;l<text.size();l++)
						{
							
				
							
							if(text.get(l).equals(li.get(i)))
							{
							
							
								pass.add(text.get(l));	
								System.out.println("pass : "+text.get(l)+" : "+li.get(i));

							}
							else
							{
								fail.add(text.get(l));		
								System.out.println("fail : "+text.get(l)+" : "+li.get(i));
								System.out.println(i);

							}
						 }
						
					for(String Fal : fail)
					{
							test.log(LogStatus.FAIL, filter.get(i)+" column shows incorrect value : "+Fal);
					}	
					for(String Pas : pass)
					{
						test.log(LogStatus.PASS,  filter.get(i)+" dropdown working properly.");
						test.log(LogStatus.PASS, filter.get(i)+" displayed : "+Pas);	
						System.out.println(filter.get(i)+" : "+Pas);
					}
						text.clear();
						pass.clear();
						fail.clear();
						raw.clear();
				
				
					}
					}
					else 
					{
						test.log(LogStatus.PASS,"No records found");	
					}
				
	}
	
	public static void ClearBtn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
			Thread.sleep(8000);
    		MgmtPOM.clickMyReport(driver).click();
		
    	
    		Thread.sleep(7000);
			MgmtPOM.clickLeaveReport(driver).click();
		
		
			Thread.sleep(7000);
			MgmtPOM.clickUserFilter1(driver).click();
	
		  
	       	Thread.sleep(7000);
	       	MgmtPOM.selectUser(driver).click();
	       
			Thread.sleep(7000);
			MgmtPOM.clickStartDateCalender(driver).click();
			Thread.sleep(7000);
	       	MgmtPOM.clickDate(driver).click();
			
	       
	       	
	       	Thread.sleep(7000);
			MgmtPOM.clickEndDateCalender(driver).click();
			Thread.sleep(500);
	       	MgmtPOM.clickDate1(driver).click();
	       	
	    	Thread.sleep(500);
			MgmtPOM.clickLeaveapplybtn(driver).click();
			
			
			if(MgmtPOM.clickClearBtn(driver).isEnabled())
			{
				Thread.sleep(500);
				MgmtPOM.clickClearBtn(driver).click();
				test.log(LogStatus.PASS, "All the applied filters should get cleared after clicking on clear button");
			}
			else
			{
				test.log(LogStatus.FAIL, "All the applied filters should get cleared after clicking on clear button");
			}
	}
	
	public static void ExportButtonLeaveReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
		

		Thread.sleep(8000);
		MgmtPOM.clickMyReport(driver).click();
	
	
		Thread.sleep(7000);
		MgmtPOM.clickLeaveReport(driver).click();
		
		
		Thread.sleep(7000);
		MgmtPOM.clickUserFilter1(driver).click();

	  
       	Thread.sleep(7000);
       	MgmtPOM.selectUser(driver).click();
    	Thread.sleep(500);
		MgmtPOM.clickLeaveapplybtn(driver).click();
		  
	
		Actions a = new Actions(driver);
		//scroll down a page
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();
		String item1 = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits1 = item1.split(" ");								//Splitting the String
		String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
		int count2 = Integer.parseInt(compliancesCount1);
		
	    try
		{
	    	MgmtPOM.clickExport(driver).sendKeys(Keys.PAGE_DOWN);
		}
		catch(Exception e)
		{
			
		}
		//js.executeScript("window.scrollBy(0,1000)");
		
	
		Thread.sleep(100);
		File dir = new File("C:\\Users\\snehalp\\Downloads");
		File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
		
		Thread.sleep(500);
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
		Thread.sleep(250);
		MgmtPOM.clickExport(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
		Thread.sleep(5500);
		File dir1 = new File("C:\\Users\\snehalp\\Downloads");
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
		
		
	}
	

}
