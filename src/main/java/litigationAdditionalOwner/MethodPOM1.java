package litigationAdditionalOwner;



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
		performerPOM.endDate(driver).sendKeys("05/12/2023");
		
		Thread.sleep(3000);
		performerPOM.clickApplyButton(driver).click();
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"div[id='grid1'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=2000");
		
		
		
        By locator = By.xpath("(//a[@class='k-button k-button-icontext ob-hearing1 k-grid-hearing1'])[1]");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		Thread.sleep(4000);
		
		WebElement ViewButton = driver.findElement(locator);	
		Thread.sleep(4000);
	    JavascriptExecutor jse=(JavascriptExecutor)driver;
	    jse.executeScript("arguments[0].click();", ViewButton);
	
		
		
//		Thread.sleep(2000);
//		performerPOM.viewNoticeDetails1(driver).click();
		test.log(LogStatus.PASS, "Show details notice popup open successfully.");
		
		
		Thread.sleep(2000);
		performerPOM.Actionclosepopup(driver).click();
		
		Thread.sleep(2000);
		By locator1 = By.xpath("//a[@class='k-button k-button-icontext ob-edit1 k-grid-edit1']");
			
	    wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
		Thread.sleep(4000);
			
		WebElement ViewButton1 = driver.findElement(locator1);	
		Thread.sleep(4000);
	    JavascriptExecutor jse1=(JavascriptExecutor)driver;
		jse1.executeScript("arguments[0].click();", ViewButton1);
		
		
//		Thread.sleep(2000);
//		performerPOM.showResponseDetailIcon1(driver).click();
		test.log(LogStatus.PASS, "Show response details notice popup open successfully.");
		
		Thread.sleep(2000);
		performerPOM.Actionclosepopup(driver).click(); 
		
		Thread.sleep(3000);
		performerPOM.clickExportAdavanced(driver).click();
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
	//-------------------------------------------Case--------------------------------------------------
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
	    Thread.sleep(3000);
		performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		Thread.sleep(4000);
		performerPOM.selectTypeCase1(driver).click();
		
	
		js.executeScript("window.scrollBy(0,800)");
		js.executeScript("document.querySelector(\"div[id='grid1'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=5000");
		
		Thread.sleep(2000);
		By locator2 = By.xpath("//a[@class='k-button k-button-icontext ob-hearing1 k-grid-hearing1']");
			
	    wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
		Thread.sleep(4000);
			
		WebElement ViewButton2 = driver.findElement(locator2);	
		Thread.sleep(4000);
	    JavascriptExecutor jse2=(JavascriptExecutor)driver;
	    jse2.executeScript("arguments[0].click();", ViewButton2);
		
//		Thread.sleep(3000);
//		performerPOM.viewNoticeDetails(driver).click();
	    
	    
		test.log(LogStatus.PASS, "Show details case popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.Actionclosepopup(driver).click();
		
		Thread.sleep(2000);
		By locator3 = By.xpath("//a[@class='k-button k-button-icontext ob-edit1 k-grid-edit1']");
		 wait.until(ExpectedConditions.presenceOfElementLocated(locator3));
		Thread.sleep(1000);
		WebElement ViewButton3 = driver.findElement(locator3);	
		Thread.sleep(1000);
	    JavascriptExecutor jse3=(JavascriptExecutor)driver;
	    jse3.executeScript("arguments[0].click();", ViewButton3);
		
//		Thread.sleep(3000);
//		performerPOM.showResponseDetailIcon(driver).click();
	    
		test.log(LogStatus.PASS, "Show Hearing details Case popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.Actionclosepopup(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
	//-------------------------------------------Task--------------------------------------------------
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		
		Thread.sleep(3000);
		performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		Thread.sleep(8000);
		performerPOM.selectTypeTask1(driver).click();
		
		
		Thread.sleep(2000);
		By locator4 = By.xpath("//a[@class='k-button k-button-icontext ob-edit k-grid-edit']");
			
	    wait.until(ExpectedConditions.presenceOfElementLocated(locator4));
		Thread.sleep(4000);
			
		WebElement ViewButton4 = driver.findElement(locator4);	
		Thread.sleep(4000);
	    JavascriptExecutor jse4=(JavascriptExecutor)driver;
	    jse4.executeScript("arguments[0].click();", ViewButton4);
		
		//Thread.sleep(3000);
		//performerPOM.viewTaskDetails(driver).click();	
		test.log(LogStatus.PASS, "Show details Task popup open successfully.");
		
		Thread.sleep(3000);
		performerPOM.ActioncloseTaskpopup(driver).click();
		
		
		Thread.sleep(3000);
		performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		test.log(LogStatus.PASS, "File downloaded successfully.");
		
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
	public static void WorkspaceFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickCaseNotice1(driver).click();
		
		  Thread.sleep(3000);
			performerPOM.clicklocationFilter(driver).click();
			Thread.sleep(3000);
			performerPOM.clickExpand(driver).click();
			Thread.sleep(3000);
	       String locationtext =performerPOM.SelectLocationDoceNonAdmin(driver).getText();
	       Thread.sleep(3000);
	       performerPOM. SelectLocationDoceNonAdmin(driver).click();
	       
	       
          Thread.sleep(500);
	       performerPOM.clickDepartmentFilterWorkspace(driver).click();
	       Thread.sleep(500);
	       String DeptText = performerPOM.selectDepartmentFilterDocNonAdmin(driver).getText();
	       Thread.sleep(500);
	       performerPOM. selectDepartmentFilterDocNonAdmin(driver).click();
	       				        
	       Thread.sleep(500);
	       performerPOM.clickTypeFilter(driver).click();
	       Thread.sleep(500);
	       String Typetext = performerPOM.SelectTypeFilterCA(driver).getText();
	       Thread.sleep(500);
	       performerPOM.SelectTypeFilterCA(driver).click();
	           
	       
	        List<String> li=new ArrayList<String>();
	        li.add(locationtext);
	         li.add(DeptText);
	         li.add(Typetext);
	        
	        Thread.sleep(3000);
	        
			List<String> filter=new ArrayList<String>();	
			filter.add("Location");
			filter.add("Dept");
			filter.add("Type");
			
			
			 Thread.sleep(500);
			 performerPOM.clickTrignle(driver).click();
		     Thread.sleep(500);
		     performerPOM.clickCol(driver).click();

		     
		     By dept = By.xpath("(//span[@class='k-link k-menu-link'][normalize-space()='Department'])[1]");
            wait.until(ExpectedConditions.presenceOfElementLocated(dept));
		     Thread.sleep(4000);
            WebElement ViewButton = driver.findElement(dept);	
			 Thread.sleep(4000);
			 JavascriptExecutor jse=(JavascriptExecutor)driver;
			 jse.executeScript("arguments[0].click();", ViewButton);
		     Thread.sleep(500);
			 performerPOM.clickTrignle(driver).click(); 
			
			
			js.executeScript("window.scrollBy(0,150)");	
			Thread.sleep(3000);

			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display")) {
			Thread.sleep(5000);
		
			List<WebElement> entitycol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]"));
			List<WebElement> Dept=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[14]"));
			List<WebElement> Type=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
			
			Thread.sleep(2000);

			for(int i=0; i<li.size(); i++){
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();

					if(i==0)
					{
						raw.addAll(entitycol);
					}
				
				   else if(i==1)
				   {
					 raw.addAll(Dept);
				   }
				   else if(i==2)
				   {
					   raw.addAll(Type);
				   }
				  
							
				for(int k=0;k<raw.size();k++)
					{
						text.add(raw.get(k).getText());
					}

					for(int l=0;l<text.size();l++)
					{
						
						if(i==2)
						{
							if(text.get(l).equalsIgnoreCase("Inward")||text.get(l).equalsIgnoreCase("Defendant"))
							{
							  pass.add(text.get(l));
							}
							else
							{
								 fail.add(text.get(l));
							}
						}
							
						
						else
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
			}else {
				test.log(LogStatus.PASS,"No records found");	
			}
			
		}
	public static void CaseWorkspaceFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickCaseNotice1(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickTypeDropdown(driver).click();
		
		Thread.sleep(3000);
		performerPOM.selectTypeNotice(driver).click();
		
		  Thread.sleep(3000);
			performerPOM.clicklocationFilter(driver).click();
			Thread.sleep(3000);
			performerPOM.clickExpand(driver).click();
			Thread.sleep(3000);
	       String locationtext =performerPOM.SelectLocationCase(driver).getText();
	       Thread.sleep(3000);
	       performerPOM. SelectLocationCase(driver).click();
	       
	       
          Thread.sleep(500);
	       performerPOM.clickDepartmentFilterWorkspace(driver).click();
	       Thread.sleep(500);
	       String DeptText = performerPOM.selectReportCaseDeptFilter(driver).getText();
	       Thread.sleep(500);
	       performerPOM. selectReportCaseDeptFilter(driver).click();
	       				        
	       Thread.sleep(500);
	       performerPOM.clickTypeFilter(driver).click();
	       Thread.sleep(500);
	       String Typetext = performerPOM.SelectTypeFilterCA(driver).getText();
	       Thread.sleep(500);
	       performerPOM.SelectTypeFilterCA(driver).click();
	           
	       
	        List<String> li=new ArrayList<String>();
	        li.add(locationtext);
	         li.add(DeptText);
	         li.add(Typetext);
	        
	        Thread.sleep(3000);
	        
			List<String> filter=new ArrayList<String>();	
			filter.add("Location");
			filter.add("Dept");
			filter.add("Type");
			
			
			 Thread.sleep(500);
			 performerPOM.clickTrignle(driver).click();
		     Thread.sleep(500);
		     performerPOM.clickCol(driver).click();

		     
		     By dept = By.xpath("(//span[@class='k-link k-menu-link'][normalize-space()='Department'])[1]");
            wait.until(ExpectedConditions.presenceOfElementLocated(dept));
		     Thread.sleep(4000);
            WebElement ViewButton = driver.findElement(dept);	
			 Thread.sleep(4000);
			 JavascriptExecutor jse=(JavascriptExecutor)driver;
			 jse.executeScript("arguments[0].click();", ViewButton);
		     Thread.sleep(500);
			 performerPOM.clickTrignle(driver).click(); 
			
			
			js.executeScript("window.scrollBy(0,300)");	
			Thread.sleep(3000);

			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display")) {
			Thread.sleep(5000);
		
			List<WebElement> entitycol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]"));
			List<WebElement> Dept=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[14]"));
			List<WebElement> Type=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
			
			Thread.sleep(2000);

			for(int i=0; i<li.size(); i++){
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();

					if(i==0)
					{
						raw.addAll(entitycol);
					}
				
				   else if(i==1)
				   {
					 raw.addAll(Dept);
				   }
				   else if(i==2)
				   {
					   raw.addAll(Type);
				   }
				  
							
				for(int k=0;k<raw.size();k++)
					{
						text.add(raw.get(k).getText());
					}

					for(int l=0;l<text.size();l++)
					{
						
						if(i==2)
						{
							if(text.get(l).equalsIgnoreCase("Inward")||text.get(l).equalsIgnoreCase("Defendant"))
							{
							  pass.add(text.get(l));
							}
							else
							{
								 fail.add(text.get(l));
							}
						}
							
						
						else
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
			}else {
				test.log(LogStatus.PASS,"No records found");	
			}
			
		}
	public static void WorkspaceTaskFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickCaseNotice1(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickTypeDropdown(driver).click();
		
		Thread.sleep(3000);
		performerPOM.selectTypeTask(driver).click();
		

		  Thread.sleep(3000);
			performerPOM.clickTaskLocFilter(driver).click();
			Thread.sleep(3000);
			performerPOM.clickExpand(driver).click();
			Thread.sleep(3000);
	       String locationtext =performerPOM.SelectLocationDoceNonAdmin(driver).getText();
	       Thread.sleep(3000);
	       performerPOM. SelectLocationDoceNonAdmin(driver).click();
	       
	       
           Thread.sleep(500);
	       performerPOM.clickTaskPriorityFilter(driver).click();
	       Thread.sleep(500);
	       String priorityText = performerPOM.SelectTaskPriorityFilter(driver).getText();
	       Thread.sleep(500);
	       performerPOM. SelectTaskPriorityFilter(driver).click();
	       				        
	       Thread.sleep(500);
	       performerPOM.clickTaskStatusFilter(driver).click();
	       Thread.sleep(500);
	       String Statustext = performerPOM.SelectTaskStatusFilter(driver).getText();
	       Thread.sleep(500);
	       performerPOM.SelectTaskStatusFilter(driver).click();
	           
	       
	        List<String> li=new ArrayList<String>();
	     
	         li.add(priorityText);
	         li.add(Statustext);
	        
	        Thread.sleep(3000);
	        
			List<String> filter=new ArrayList<String>();	
			
			filter.add("priority");
			filter.add("Status");
			
			
			

		     
			js.executeScript("window.scrollBy(0,150)");	
			Thread.sleep(3000);

			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display")) {
			Thread.sleep(5000);
		
			List<WebElement> Prioritycol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]"));
			List<WebElement> Status=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[6]"));
			//List<WebElement> Type=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
			
			Thread.sleep(2000);

			for(int i=0; i<li.size(); i++){
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();

					if(i==0)
					{
						raw.addAll(Prioritycol);
					}
				
				   else if(i==1)
				   {
					 raw.addAll(Status);
				   }
				 
							
				for(int k=0;k<raw.size();k++)
					{
						text.add(raw.get(k).getText());
					}

					for(int l=0;l<text.size();l++)
					{
						
						if(i==1)
						{
							if(text.get(l).equalsIgnoreCase("Open")||text.get(l).equalsIgnoreCase("Pending"))
							{
							  pass.add(text.get(l));
							}
							else
							{
								 fail.add(text.get(l));
							}
						}
						else
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
			}else {
				test.log(LogStatus.PASS,"No records found");	
			}
		
}
	
	public static void WorkspaceCaseHearingFilter(WebDriver driver,ExtentTest test,String type) throws InterruptedException
	{
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		performerPOM.clickMyWorkspace(driver).click();
		Thread.sleep(3000);
		performerPOM.clickCaseHearing1(driver).click();
		
		Thread.sleep(3000);
		performerPOM.clickSearchFilter(driver).sendKeys(type,Keys.ENTER);
		
	      List<String> li=new ArrayList<String>();
	        
	       
	        li.add(type);
	       
	        
			List<String> filter=new ArrayList<String>();	
			
			filter.add("Location");	
			
			
			js.executeScript("window.scrollBy(0,150)");	
			Thread.sleep(3000);

			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display")) {
			Thread.sleep(5000);
	
			List<WebElement> typecol=driver.findElements(By.xpath("//*[@id='gridPendingUpdation']/div[2]/table/tbody/tr/td[1]"));
			Thread.sleep(2000);

			for(int i=0; i<li.size(); i++){
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();


			 if(i==0)
					{
						raw.addAll(typecol);
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
				 	test.log(LogStatus.PASS,  " Search box working properly.");
					test.log(LogStatus.PASS, filter.get(i)+" displayed : "+Pas);	
					System.out.println(filter.get(i)+" : "+Pas);
			 }
			 text.clear();
			pass.clear();
			fail.clear();
			raw.clear();
			
			
			}
			}else {
				test.log(LogStatus.PASS,"No records found");	
			}
		}
		
	public static void DocumentNoticeFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		progress(driver);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	
		 performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
		 performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
		 
		 Thread.sleep(3000);
			performerPOM.clickTypeDropdown(driver).click();
			Thread.sleep(3000);
			performerPOM.selectTypeNotice(driver).click();
	
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
	
	
		
	
		Thread.sleep(3000);
		performerPOM.clickDocStatusFilter(driver).click();
		Thread.sleep(3000);
		String Statustext =performerPOM.selectDocStatusFilter(driver).getText();
		Thread.sleep(3000);
		performerPOM. selectDocStatusFilter(driver).click();
   

		Thread.sleep(3000);
		performerPOM.clickDocTypeFilter(driver).click();
		Thread.sleep(3000);
		String typetext =performerPOM.SelectTypeFilterCA(driver).getText();
		Thread.sleep(3000);
		performerPOM. SelectTypeFilterCA(driver).click();
     
     
     
  
		Thread.sleep(3000);
		performerPOM.clickDocLocFilter(driver).click();
		Thread.sleep(3000);
		performerPOM.clickExpand(driver).click();
		Thread.sleep(3000);
		String locationtext =performerPOM.SelectLocationCase(driver).getText();
		Thread.sleep(3000);
		performerPOM. SelectLocationCase(driver).click();
		Thread.sleep(3000);
		performerPOM.clickDocLocFilter(driver).click();
       
	    List<String> li=new ArrayList<String>();
         li.add(Statustext);
         li.add(typetext);
         li.add(locationtext);
        
		List<String> filter=new ArrayList<String>();	
		filter.add("Status");
		filter.add("Type");
		filter.add("Loaction");
	
		 
		  By Tringle = By.xpath("//span[@class='k-icon k-i-more-vertical']");
          wait.until(ExpectedConditions.presenceOfElementLocated(Tringle));
		  Thread.sleep(4000);
          WebElement ViewButton = driver.findElement(Tringle);	
		  Thread.sleep(4000);
		  JavascriptExecutor jse=(JavascriptExecutor)driver;
		  jse.executeScript("arguments[0].click();", ViewButton);
	     
		 Thread.sleep(3000);
	     performerPOM.clickCol(driver).click();

	     
	     By dept = By.xpath("//input[@data-field='Status']");
         wait.until(ExpectedConditions.presenceOfElementLocated(dept));
	     Thread.sleep(4000);
         WebElement ViewButton1 = driver.findElement(dept);	
		 Thread.sleep(4000);
		 JavascriptExecutor jse1=(JavascriptExecutor)driver;
		 jse1.executeScript("arguments[0].click();", ViewButton1);
	    
		
		js.executeScript("window.scrollBy(0,300)");	
		Thread.sleep(3000);

		CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
		String s = CFOcountPOM.readTotalItems1(driver).getText();
		Thread.sleep(2000);

		if(!s.equalsIgnoreCase("No items to display")) {
		Thread.sleep(5000);
	
		List<WebElement> statuscol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[13]"));
		List<WebElement> Type=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[4]"));
		List<WebElement> Location=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
		
		Thread.sleep(2000);

		for(int i=0; i<li.size(); i++){
			
			List<String> text= new ArrayList<String>();
			HashSet<String> pass=new LinkedHashSet<>();
			HashSet<String> fail=new LinkedHashSet<>();
			List<WebElement> raw=new ArrayList<WebElement>();

				if(i==0)
				{
					raw.addAll(statuscol);
				}
			
			   else if(i==1)
			   {
				 raw.addAll(Type);
			   }
			   else if(i==2)
			   {
				   raw.addAll(Location);
			   }
			  
						
			for(int k=0;k<raw.size();k++)
				{
					text.add(raw.get(k).getText());
				}

				for(int l=0;l<text.size();l++)
				{
					
				
					
				  if(i==1)
					 {
						if(text.get(l).equalsIgnoreCase("Inward")||text.get(l).equalsIgnoreCase("Defendant"))
						{
						  pass.add(text.get(l));
						}
						else
						{
							 fail.add(text.get(l));
						}
					 }
					else
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

	
	
	public static void DocumentCaseFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		progress(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
		performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
		
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		

		Thread.sleep(3000);
		performerPOM.clickDocStatusFilter(driver).click();
		Thread.sleep(3000);
        String Statustext =performerPOM.selectDocStatusFilter1(driver).getText();
        Thread.sleep(3000);
         performerPOM. selectDocStatusFilter1(driver).click();
       
	
         Thread.sleep(3000);
		performerPOM.clickDocTypeFilter(driver).click();
		Thread.sleep(3000);
	    String typetext =performerPOM.selectDocTypeFilter(driver).getText();
	    Thread.sleep(3000);
	     performerPOM. selectDocTypeFilter(driver).click();
         
         
         
      
		  Thread.sleep(3000);
			performerPOM.clickDocLocFilter(driver).click();
			Thread.sleep(3000);
			performerPOM.clickExpand(driver).click();
			Thread.sleep(3000);
	       String locationtext =performerPOM.SelectLocationCase(driver).getText();
	       Thread.sleep(3000);
	       performerPOM. SelectLocationCase(driver).click();
	       Thread.sleep(3000);
			performerPOM.clickDocLocFilter(driver).click();
	       
	        List<String> li=new ArrayList<String>();
	         li.add(Statustext);
	         li.add(typetext);
	         li.add(locationtext);
	        
			List<String> filter=new ArrayList<String>();	
			filter.add("Status");
			filter.add("Type");
			filter.add("Loaction");
			
			
			// Thread.sleep(3000);
			 //performerPOM.clickTrignle(driver).click();
			 
			  By Tringle = By.xpath("//span[@class='k-icon k-i-more-vertical']");
                 wait.until(ExpectedConditions.presenceOfElementLocated(Tringle));
			     Thread.sleep(4000);
                 WebElement ViewButton = driver.findElement(Tringle);	
				 Thread.sleep(4000);
				 JavascriptExecutor jse=(JavascriptExecutor)driver;
				 jse.executeScript("arguments[0].click();", ViewButton);
		     
				 Thread.sleep(3000);
		     performerPOM.clickCol(driver).click();

		     
		     By dept = By.xpath("//span[@class='k-link k-menu-link'][normalize-space()='Status']");
             wait.until(ExpectedConditions.presenceOfElementLocated(dept));
		     Thread.sleep(4000);
             WebElement ViewButton1 = driver.findElement(dept);	
			 Thread.sleep(4000);
			 JavascriptExecutor jse1=(JavascriptExecutor)driver;
			 jse1.executeScript("arguments[0].click();", ViewButton1);
		    
			
			js.executeScript("window.scrollBy(0,150)");	
			Thread.sleep(3000);

			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display")) {
			Thread.sleep(5000);
		
			List<WebElement> statuscol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[13]"));
			List<WebElement> Type=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[4]"));
			List<WebElement> Location=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
			
			Thread.sleep(2000);

			for(int i=0; i<li.size(); i++){
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();

				if(i==0)
				{
					raw.addAll(statuscol);
				}
			
			   else if(i==1)
			   {
				 raw.addAll(Type);
			   }
			   else if(i==2)
			   {
				   raw.addAll(Location);
			   }
			 
						
			for(int k=0;k<raw.size();k++)
				{
					text.add(raw.get(k).getText());
				}

				for(int l=0;l<text.size();l++)
				{

				 if(i==1)
					{
						if(text.get(l).equalsIgnoreCase("Inward")||text.get(l).equalsIgnoreCase("Defendant"))
						{
						  pass.add(text.get(l));
						}
						else
						{
							 fail.add(text.get(l));
						}
					}
					else
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
	
	public static void DocumentTaskFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		progress(driver);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	
		 performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
		performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
	
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
	
	
		  Thread.sleep(3000);
		  performerPOM.clickTypeDropdown(driver).click();
		  Thread.sleep(3000);
		  performerPOM.selectTypeTask(driver).click();
	
           Thread.sleep(500);
	       performerPOM.clickDocTaskPriorityFilter(driver).click();
	       Thread.sleep(500);
	       String priorityText = performerPOM.SelectTaskPriorityFilter(driver).getText();
	       Thread.sleep(500);
	       performerPOM. SelectTaskPriorityFilter(driver).click();
	       				        
	       Thread.sleep(500);
	       performerPOM.clickDocStatusFilter(driver).click();
	       Thread.sleep(500);
	       String Statustext = performerPOM.selectDocStatusFilter(driver).getText();
	       Thread.sleep(500);
	       performerPOM.selectDocStatusFilter(driver).click();
	           
	       
	        List<String> li=new ArrayList<String>();
	       
	         li.add(priorityText);
	         li.add(Statustext);
	        
	        Thread.sleep(3000);
	        
			List<String> filter=new ArrayList<String>();	
		
			filter.add("priority");
			filter.add("Status");
			 
			js.executeScript("window.scrollBy(0,200)");	
			Thread.sleep(3000);
			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display")) {
			Thread.sleep(5000);
		
			List<WebElement> Prioritycol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[9]"));
			List<WebElement> Status=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[13]"));
			
			
			Thread.sleep(2000);

			for(int i=0; i<li.size(); i++){
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();

					if(i==0)
					{
						raw.addAll(Prioritycol);
					}
				
				   else if(i==1)
				   {
					 raw.addAll(Status);
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
			}else {
				test.log(LogStatus.PASS,"No records found");	
			}
			
	}
	
	public static void ReportFilter(WebDriver driver,ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-150)");	
		
	
         Thread.sleep(3000);
		performerPOM.clickReportDeptFilter(driver).click();
		Thread.sleep(3000);
	    String depttext =performerPOM.selectDepartmentFilterDocNonAdmin(driver).getText();
	    Thread.sleep(3000);
	     performerPOM. selectDepartmentFilterDocNonAdmin(driver).click();
         
	     Thread.sleep(3000);
			performerPOM.clickReportTypeFilter(driver).click();
			Thread.sleep(3000);
		    String typetext =performerPOM.SelectTypeFilterCA(driver).getText();
		    Thread.sleep(3000);
		     performerPOM. SelectTypeFilterCA(driver).click();
	         
         
         
      
		    Thread.sleep(3000);
			performerPOM.clickReportLocFilter(driver).click();
			Thread.sleep(3000);
			performerPOM.clickExpand(driver).click();
			Thread.sleep(3000);
	       String locationtext =performerPOM.SelectLocationCase(driver).getText();
	       
	       Thread.sleep(3000);
	       performerPOM. SelectLocationCase(driver).click();
	      
			Thread.sleep(3000);
			performerPOM.clickReportFYFilter(driver).click();
			Thread.sleep(3000);
		    String FYtext =performerPOM.selectReportFYFilterCA(driver).getText();
		    Thread.sleep(3000);
		     performerPOM. selectReportFYFilterCA(driver).click();
	       
	        List<String> li=new ArrayList<String>();
	     
	         li.add(depttext);
	         li.add(typetext);
	         li.add(locationtext);
	         li.add(FYtext);
	        
			List<String> filter=new ArrayList<String>();	
			
			filter.add("Dept");
			filter.add("Type");
			filter.add("Loaction");
			filter.add("FY");
		
			js.executeScript("window.scrollBy(0,200)");	
			CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
			String s = CFOcountPOM.readTotalItems1(driver).getText();
			Thread.sleep(2000);

			if(!s.equalsIgnoreCase("No items to display"))
			{
			Thread.sleep(3000);
		
			
			List<WebElement> deptcol=driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[7]"));
			List<WebElement> typecol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
			List<WebElement> loctioncol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]"));
			List<WebElement> FYcol=driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[14]"));
			
			js.executeScript("document.querySelector(\"div[id='grid'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=400");
			
			Thread.sleep(2000);
            for(int i=0; i<li.size(); i++)
			{
				
				List<String> text= new ArrayList<String>();
				HashSet<String> pass=new LinkedHashSet<>();
				HashSet<String> fail=new LinkedHashSet<>();
				List<WebElement> raw=new ArrayList<WebElement>();


					 if(i==0)
					{
						raw.addAll(deptcol);
					}
				   else if(i==1)
				   {
					 raw.addAll(typecol);
				   }
				   else if(i==2)
				   {
					   raw.addAll(loctioncol);
				   }
				   else if(i==3)
				   {
					   Thread.sleep(5000);
					   js.executeScript("document.querySelector(\"div[id='grid'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=5000");
					   raw.addAll(FYcol);
				   }
							
					 for(int k=0;k<raw.size();k++)
						{
							text.add(raw.get(k).getText());
						}

						for(int l=0;l<text.size();l++)
						{
							
							
							
							 if(i==1)
							{
								if(text.get(l).equalsIgnoreCase("Inward")||text.get(l).equalsIgnoreCase("Defendant"))
								{
								  pass.add(text.get(l));
								}
								else
								{
									 fail.add(text.get(l));
								}
							}
							else
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
	
	 
	 public static void ReportCaseFilter(WebDriver driver,ExtentTest test) throws InterruptedException
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
			
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
			
			
			 JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,-150)");	
				
				Thread.sleep(3000);
				performerPOM.clickTypeDropdown(driver).click();
				Thread.sleep(3000);
				performerPOM.selectTypeNotice(driver).click();
			
			Thread.sleep(3000);
			performerPOM.clickReportStatusFilter(driver).click();
			Thread.sleep(3000);
	        String Statustext =performerPOM.selectReportStatusFilter(driver).getText();
	        Thread.sleep(3000);
	         performerPOM. selectReportStatusFilter(driver).click();
	       
		
	         Thread.sleep(3000);
			performerPOM.clickReportDeptFilter(driver).click();
			Thread.sleep(3000);
		    String depttext =performerPOM.selectReportCaseDeptFilter(driver).getText();
		    Thread.sleep(5000);
		     performerPOM.selectReportCaseDeptFilter(driver).click();
	         
		     Thread.sleep(3000);
				performerPOM.clickReportTypeFilter(driver).click();
				Thread.sleep(3000);
			    String typetext =performerPOM.SelectTypeFilterCA(driver).getText();
			    Thread.sleep(3000);
			     performerPOM. SelectTypeFilterCA(driver).click();
		         
	         
	         
	      
			    Thread.sleep(3000);
				performerPOM.clickReportLocFilter(driver).click();
				Thread.sleep(3000);
				performerPOM.clickExpand(driver).click();
				Thread.sleep(3000);
		       String locationtext =performerPOM.SelectLocationCase(driver).getText();
		       Thread.sleep(3000);
		       performerPOM. SelectLocationCase(driver).click();
		      
				Thread.sleep(3000);
				performerPOM.clickReportFYFilter(driver).click();
				Thread.sleep(3000);
			    String FYtext =performerPOM.selectReportFYFilter(driver).getText();
			    Thread.sleep(3000);
			     performerPOM. selectReportFYFilter(driver).click();
		       
		        List<String> li=new ArrayList<String>();
		         li.add(Statustext);
		         li.add(depttext);
		         li.add(typetext);
		         li.add(locationtext);
		         li.add(FYtext);
		        
				List<String> filter=new ArrayList<String>();	
				filter.add("Status");
				filter.add("Dept");
				filter.add("Type");
				filter.add("Loaction");
				filter.add("FY");
			
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(2000);
				CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
				String s = CFOcountPOM.readTotalItems1(driver).getText();
				Thread.sleep(2000);

				if(!s.equalsIgnoreCase("No items to display")) {
				Thread.sleep(5000);
			
				List<WebElement> statuscol=driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[4]"));
				List<WebElement> deptcol=driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[7]"));
				List<WebElement> typecol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[3]"));
				List<WebElement> loctioncol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]"));
				List<WebElement> FYcol=driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[14]"));
				Thread.sleep(2000);
				js.executeScript("document.querySelector(\"div[id='grid'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=500");
				for(int i=0; i<li.size(); i++){
					
					List<String> text= new ArrayList<String>();
					HashSet<String> pass=new LinkedHashSet<>();
					HashSet<String> fail=new LinkedHashSet<>();
					List<WebElement> raw=new ArrayList<WebElement>();

						if(i==0)
						{
							raw.addAll(statuscol);
						}
						else if(i==1)
						{
							raw.addAll(deptcol);
						}
					   else if(i==2)
					   {
						 raw.addAll(typecol);
					   }
					   else if(i==3)
					   {
						   raw.addAll(loctioncol);
					   }
					   else if(i==4)
					   {
						   Thread.sleep(5000);
						   js.executeScript("document.querySelector(\"div[id='grid'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=5000");
						   raw.addAll(FYcol);
					   }
								
						for(int k=0;k<raw.size();k++)
						{
							text.add(raw.get(k).getText());
						}

						for(int l=0;l<text.size();l++)
						{
							
							if(i==0)
							{
								if(text.get(l).equalsIgnoreCase("Open")||text.get(l).equalsIgnoreCase("Pending"))
								{
								  pass.add(text.get(l));
								}
								else
								{
									 fail.add(text.get(l));
								}
							}
							
							else if(i==2)
							{
								if(text.get(l).equalsIgnoreCase("Inward")||text.get(l).equalsIgnoreCase("Defendant"))
								{
								  pass.add(text.get(l));
								}
								else
								{
									 fail.add(text.get(l));
								}
							}
							else
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
	 
		public static void ReportTaskFilter(WebDriver driver,ExtentTest test) throws InterruptedException
		{
			
				WebDriverWait wait=new WebDriverWait(driver,20);
				performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
			
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
			
			
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,-150)");	
		
				Thread.sleep(3000);
				performerPOM.clickTypeDropdown(driver).click();
				Thread.sleep(3000);
				performerPOM.selectTypeTask(driver).click();
			

		       
				Thread.sleep(500);
				performerPOM.clickTaskPriorityFilter(driver).click();
				Thread.sleep(500);
				String priorityText = performerPOM.SelectTaskPriorityFilter(driver).getText();
				Thread.sleep(500);
				performerPOM. SelectTaskPriorityFilter(driver).click();
		       				        
				Thread.sleep(500);
				performerPOM.clickTaskStatusFilter(driver).click();
				Thread.sleep(500);
				String Statustext = performerPOM.SelectTaskStatusFilter(driver).getText();
				Thread.sleep(500);
				performerPOM.SelectTaskStatusFilter(driver).click();
		           
		       
			    List<String> li=new ArrayList<String>();
		       //li.add(locationtext);
		         li.add(priorityText);
		         li.add(Statustext);
		        
		        Thread.sleep(3000);
		        
				List<String> filter=new ArrayList<String>();	
				//filter.add("Location");
				filter.add("priority");
				filter.add("Status");
				 
				js.executeScript("window.scrollBy(0,200)");	
				Thread.sleep(3000);
				CFOcountPOM.readTotalItems1(driver).click();					//Clicking on Text of total items just to scroll down.
				String s = CFOcountPOM.readTotalItems1(driver).getText();
				Thread.sleep(2000);

				if(!s.equalsIgnoreCase("No items to display"))
				{
				Thread.sleep(5000);
			
				List<WebElement> Prioritycol=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]"));
				List<WebElement> Status=driver.findElements(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[6]"));
				
				
				Thread.sleep(2000);

				for(int i=0; i<li.size(); i++){
					
					List<String> text= new ArrayList<String>();
					HashSet<String> pass=new LinkedHashSet<>();
					HashSet<String> fail=new LinkedHashSet<>();
					List<WebElement> raw=new ArrayList<WebElement>();

						if(i==0)
						{
							raw.addAll(Prioritycol);
						}
					
					   else if(i==1)
					   {
						 raw.addAll(Status);
					   }
					 
								
					for(int k=0;k<raw.size();k++)
						{
							text.add(raw.get(k).getText());
						}

						for(int l=0;l<text.size();l++)
						{
							
							if(i==1)
							{
								if(text.get(l).equalsIgnoreCase("Open")||text.get(l).equalsIgnoreCase("Pending"))
								{
								  pass.add(text.get(l));
								}
								else
								{
									 fail.add(text.get(l));
								}
							}
							else
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
				}else {
					test.log(LogStatus.PASS,"No records found");	
				}
			
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
		
//		Thread.sleep(5000);
//		performerPOM.clickcategory(driver).click();
//		
//		Thread.sleep(5000);
//		performerPOM.clickcategory2(driver).click();
		
		
		if(performerPOM.clearButton(driver).isEnabled())
  		{
			Thread.sleep(5000);
  			performerPOM.clearButton(driver).click();
  			test.log(LogStatus.PASS, "Clear button working successfully");
  		}
  		else
  		{
  			test.log(LogStatus.FAIL, "Clear button not working successfully");
  		}
		
		Thread.sleep(5000);
		
		performerPOM.AdvancedSearchReports(driver).click();
		
	//-------------------------------------------Notice--------------------------------------------------
		
		Thread.sleep(4000);
		performerPOM.startDate(driver).sendKeys("05/10/2022");
		
		Thread.sleep(4000);
		performerPOM.endDate(driver).sendKeys("05/12/2023");
		
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
			
			Thread.sleep(3000);
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


