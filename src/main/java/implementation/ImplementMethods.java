package implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import companyadmin.CompanyPOM;



public class ImplementMethods 
{
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static XSSFSheet sheet1 = null;		//Sheet variable

	
	
	public static void UserLogReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{

		
    	Thread.sleep(5000);
    	ImplementPOM.clickReport(driver).click();
       	
    	
       	if(ImplementPOM.clickUserLogReport(driver).isDisplayed())
		{
			Thread.sleep(7000);
			ImplementPOM.clickUserLogReport(driver).click();
			test.log(LogStatus.PASS, "The tab of User Log Report should be seen under the tab of Report dropdown.");
		}
		else
		{
			test.log(LogStatus.FAIL, "The tab of User Log Report should not be seen under the tab of Report dropdown.");
		}
       	SwitchtoChild(driver,test);
       	SwitchtoParent(driver,test);	
	}
	
	public static void SelectMultipleUsers(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
		Thread.sleep(5000);
    	ImplementPOM.clickReport(driver).click();
	      
    	Thread.sleep(7000);
		ImplementPOM.clickUserLogReport(driver).click();
		
		
       	SwitchtoChild(driver,test);
     	
       	Thread.sleep(7000);
		ImplementPOM.clickCustomer(driver).click();
       	
		Thread.sleep(7000);
		ImplementPOM.selectCustomer(driver).click();
		
		Thread.sleep(7000);
		ImplementPOM.clickUser(driver).click();
		
		Thread.sleep(7000);
		ImplementPOM.selectUser(driver).click();
		
		Thread.sleep(7000);
		ImplementPOM.selectUser1(driver).click();
		
		
		Thread.sleep(4000);
	  	if(ImplementPOM.clickApplyBtn(driver).isEnabled())
			{
				Thread.sleep(4000);
				ImplementPOM.clickApplyBtn(driver).click();
				test.log(LogStatus.PASS, "Multiple users selected  from the user dropdown.");
			}
			else
			{
				test.log(LogStatus.FAIL, "Multiple users not selected  from the user dropdown.");
			}
	  	Thread.sleep(4000);
	       	SwitchtoParent(driver,test);
	  }
	
	public static void UserFilter(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
			Thread.sleep(5000);
			ImplementPOM.clickReport(driver).click();
	      
    		Thread.sleep(7000);
			ImplementPOM.clickUserLogReport(driver).click();
		
		
			SwitchtoChild(driver,test);
     	
       		Thread.sleep(7000);
       		ImplementPOM.clickCustomer(driver).click();
       	
			Thread.sleep(7000);
			ImplementPOM.selectCustomer(driver).click();
			
			Thread.sleep(7000);
			ImplementPOM.clickUser(driver).click();	  
		   	Thread.sleep(500);
	       	String user=ImplementPOM.selectUser(driver).getText();
	       	Thread.sleep(500);
	       	ImplementPOM.selectUser(driver).click();
	       	Thread.sleep(500);
	       	ImplementPOM.clickApplyBtn(driver).click();
			  
			  
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
				Thread.sleep(4000);
		       	SwitchtoParent(driver,test);
	}
	public static void ExportButton(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
	{
		
		Thread.sleep(5000);
		ImplementPOM.clickReport(driver).click();
      
		Thread.sleep(7000);
		ImplementPOM.clickUserLogReport(driver).click();
	
	
		SwitchtoChild(driver,test);
 	
   		Thread.sleep(7000);
   		ImplementPOM.clickCustomer(driver).click();
   	
		Thread.sleep(7000);
		ImplementPOM.selectCustomer(driver).click();
		
		Thread.sleep(7000);
		ImplementPOM.clickUser(driver).click();	  
		
		Thread.sleep(500);
       	ImplementPOM.selectUser(driver).click();
       	Thread.sleep(500);
       	ImplementPOM.clickApplyBtn(driver).click();
	
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
	    	ImplementPOM.clickExport(driver).sendKeys(Keys.PAGE_DOWN);
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
		ImplementPOM.clickExport(driver).click();					//Clicking on 'Excel Report' image.
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
		
		SwitchtoParent(driver,test);
	}
	
	public static void SwitchtoChild( WebDriver driver,ExtentTest test) throws InterruptedException, IOException
	{		
		
		Set w = driver.getWindowHandles();    // window handles
		Thread.sleep(3000);
	      Iterator t = w.iterator();  // window handles iterate
	      String pw = (String) t.next();
	      String ch = (String) t.next();
	      
	      driver.switchTo().window(ch);         // switching child window
	  }
	


public static void SwitchtoParent( WebDriver driver,ExtentTest test) throws InterruptedException, IOException
	{		
		Thread.sleep(3000);
		Set w = driver.getWindowHandles();    // window handles
		Thread.sleep(3000);
	      Iterator t = w.iterator();  // window handles iterate
	      String pw = (String) t.next();
	      String ch = (String) t.next();
	      driver.close();
	      Thread.sleep(3000);
	      driver.switchTo().window(pw);         // switching child window
	       
	}

}
