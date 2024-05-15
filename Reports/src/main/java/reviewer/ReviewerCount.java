package reviewer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import performer.OverduePOM;

public class ReviewerCount
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
		String workingDir = System.getProperty("user.dir");
		fis = new FileInputStream(workingDir+"//TestData//ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(1);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//ReviewerResults.html",true);
		test = extent.startTest("Verify OpenBrowser");
		test.log(LogStatus.INFO, "Browser test is initiated");
		
		XSSFSheet sheet = ReadExcel();
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 1)
	void Login() throws InterruptedException, IOException
	{
		test = extent.startTest("Logging In - Reviewer");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"PendingReview");		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 2)
	void ReviewCountStatutoryApprove() throws InterruptedException, IOException
	{
		test = extent.startTest("Statutory Review Count when Approved");
		test.log(LogStatus.INFO, "Test initiated");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		
		int oldValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading old value of Statutory Pending For Review
		ReviewerPOM.clickStatutoryReview(driver).click();		//Clicking on Statutory Review value.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		elementsList = ReviewerPOM.clickStatus(driver);			//CLicking on Status to sort it in ascending order
		elementsList.get(0).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickAction1(driver)));
		ReviewerPOM.clickAction1(driver).click();				//Clicking on third action button.
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");
		
		try
		{
			Thread.sleep(500);
			ReviewerPOM.clickDownload1(driver).click();					//Clicking on 'Click Here' label.
		}
		catch(Exception e)
		{
			
		}
		try
		{
			Thread.sleep(500);
			ReviewerPOM.clickDownload2(driver).click();					//Clicking on 'Click Here' label.
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,300)"," ");					//Scrolling down window by 2000 px.
		
		WebElement el = null;
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimely(driver)));
			el = ReviewerPOM.clickClosedTimely(driver);			
		}
		catch(Exception e)
		{
			
		}
		if(el != null)
		{
			Thread.sleep(500);
			ReviewerPOM.clickClosedTimely(driver).click();				//Clicking on first radio button.
		}
		
		WebElement checkbox = null;
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickCheckBox(driver)));
			checkbox = ReviewerPOM.clickCheckBox(driver);				//Clicking on Check box
		}
		catch(Exception e)
		{
			
		}
		if(checkbox != null)
		{
			if(checkbox.isDisplayed() && !checkbox.isSelected())
				ReviewerPOM.clickCheckBox(driver).click();
		}
		
		Thread.sleep(500);
		XSSFSheet sheet = ReadExcel();
		WebElement ele1 = null;
		WebElement ele2 = null;
		WebElement ele3 = null;
		try
		{
			Thread.sleep(600);
			//wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertLiability1(driver)));
			ele1 = ReviewerPOM.insertLiability1(driver);			//Loaded element in ele1. So that, if element not found it will do nothing.
			ele2 = ReviewerPOM.insertLiability2(driver);			//Loaded element in ele2. So that, if element not found it will do nothing.
			ele3 = ReviewerPOM.insertLiability3(driver);			//Loaded element in ele3. So that, if element not found it will do nothing.
		}
		catch(Exception e)
		{
			
		}
		if(ele1 != null)
		{
			Row row3 = sheet.getRow(3);									//Selected 3rd index row (Fourth row)
			Cell c1 = row3.getCell(1);									//Selected cell (3 row,1 column)
			int liability1 = (int) c1.getNumericCellValue();			//Got the amount stored at position 3,1
			String l1 = Integer.toString(liability1); 					//Converting int to String
			ReviewerPOM.insertLiability1(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability1(driver).sendKeys(l1);			//Inserting amount in text box
			Thread.sleep(400);
		}
		
		if(ele2 != null)
		{
			Row row4 = sheet.getRow(4);											//Selected 4th index row (Fifth row)
			Cell c2 = row4.getCell(1);											//Selected cell (4 row,1 column)
			int liability2 = (int) c2.getNumericCellValue();					//Got the amount stored at position 4,1
			String l2 = Integer.toString(liability2); 							//Converting int to String
			ReviewerPOM.insertLiability2(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability2(driver).sendKeys(l2);		//Inserting amount in text box
			Thread.sleep(400);
		}
		
		if(ele3 != null)
		{
			Row row5 = sheet.getRow(5);											//Selected 5th index row (Sixth row)
			Cell c3 = row5.getCell(1);											//Selected cell (5 row,1 column)
			int liability3 = (int) c3.getNumericCellValue();					//Got the amount stored at position 5,1
			String l3 = Integer.toString(liability3); 							//Converting int to String
			ReviewerPOM.insertLiability3(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability3(driver).sendKeys(l3);		//Inserting amount in text box
			Thread.sleep(500);
		}
		js.executeScript("window.scrollBy(0,400)"," ");
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertTextArea(driver)));
		Row row6 = sheet.getRow(6);											//Selected 6th index row (Seventh row)
		Cell c4 = row6.getCell(1);											//Selected cell (6 row,1 column)
		String remark = c4.getStringCellValue();							//Got the URL stored at position 6,1
		ReviewerPOM.insertTextArea(driver).sendKeys(remark);		//Inserting remark in Text area
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickApprove(driver)));
		ReviewerPOM.clickApprove(driver).click();					//Clicking on Approve button.

		Thread.sleep(1000);
		driver.switchTo().alert().accept();									//Accepting msg of Successful Submission.
		driver.switchTo().parentFrame();									//Switching back to parent frame from iFrame
		
		Thread.sleep(1000);
		performer.OverduePOM.clickDashboard(driver).click();
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickStatutoryReview(driver)));
		int newValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading new value of Statutory Pending For Review
		
		if(newValue < oldValue)
		{
			test.log(LogStatus.PASS, "Statutory count of Pending For Review decremented.");
			test.log(LogStatus.INFO, "Old Count = "+oldValue + " | New Count = "+ newValue);
		}
		else
		{
			test.log(LogStatus.FAIL, "Statutory count of Pending For Review doesn't decremented.");
			test.log(LogStatus.INFO, "Old Count = "+oldValue + " | New Count = "+ newValue);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 3)
	void ReviewCountStatutoryReject() throws InterruptedException, IOException
	{
		test = extent.startTest("Statutory 'Pending For Review' and 'Rejected' Count when Rejected");
		test.log(LogStatus.INFO, "Test initiated");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		int oldStatutoryReviewValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading old value of Statutory Pending For Review
		int oldStatutoryRejectValue = Integer.parseInt(ReviewerPOM.readStatutoryReject(driver).getText());	//Reading old value of Statutory Rejected
		ReviewerPOM.clickStatutoryReview(driver).click();			//Clicking on Statutory Review value.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		elementsList = ReviewerPOM.clickStatus(driver);				//CLicking on Status to sort it in ascending order
		elementsList.get(0).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickAction1(driver)));
		ReviewerPOM.clickAction1(driver).click();					//Clicking on third action button.
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");					//Scrolling down window by 2000 px.
		try
		{
			Thread.sleep(500);
			ReviewerPOM.clickDownload1(driver).click();					//Clicking on 'Click Here' label.
		}
		catch(Exception e)
		{
			
		}
		try
		{
			Thread.sleep(500);
			ReviewerPOM.clickDownload2(driver).click();
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,300)"," ");					//Scrolling down window by 2000 px.
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimely(driver)));
			ReviewerPOM.clickClosedTimely(driver).click();				//Clicking on Closed-Delay radio button.
		}
		catch(Exception e)
		{
			
		}
		
		XSSFSheet sheet = ReadExcel();
		WebElement ele1 = null;
		WebElement ele2 = null;
		WebElement ele3 = null;
		try
		{
			Thread.sleep(600);
			//wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertLiability1(driver)));
			ele1 = ReviewerPOM.insertLiability1(driver);			//Loaded element in ele1. So that, if element not found it will do nothing.
			ele2 = ReviewerPOM.insertLiability2(driver);			//Loaded element in ele2. So that, if element not found it will do nothing.
			ele3 = ReviewerPOM.insertLiability3(driver);			//Loaded element in ele3. So that, if element not found it will do nothing.
		}
		catch(Exception e)
		{
			
		}
		if(ele1 != null)
		{
			js.executeScript("window.scrollBy(0,300)"," ");
			Row row3 = sheet.getRow(3);											//Selected 3rd index row (Fourth row)
			Cell c1 = row3.getCell(1);											//Selected cell (3 row,1 column)
			int liability1 = (int) c1.getNumericCellValue();					//Got the amount stored at position 3,1
			String l1 = Integer.toString(liability1); 							//Converting int to String
			ReviewerPOM.insertLiability1(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability1(driver).sendKeys(l1);		//Inserting amount in text box
			Thread.sleep(500);
		}
		
		if(ele2 != null)
		{
			Row row4 = sheet.getRow(4);										//Selected 4th index row (Fifth row)
			Cell c2 = row4.getCell(1);										//Selected cell (4 row,1 column)
			int liability2 = (int) c2.getNumericCellValue();				//Got the amount stored at position 4,1
			String l2 = Integer.toString(liability2); 						//Converting int to String
			ReviewerPOM.insertLiability2(driver).clear();					//Clearing the text box.
			ReviewerPOM.insertLiability2(driver).sendKeys(l2);				//Inserting amount in text box
			Thread.sleep(500);
		}
		
		if(ele3 != null)
		{
			Row row5 = sheet.getRow(5);										//Selected 5th index row (Sixth row)
			Cell c3 = row5.getCell(1);										//Selected cell (5 row,1 column)
			int liability3 = (int) c3.getNumericCellValue();				//Got the amount stored at position 5,1
			String l3 = Integer.toString(liability3); 						//Converting int to String
			ReviewerPOM.insertLiability3(driver).clear();					//Clearing the text box.
			ReviewerPOM.insertLiability3(driver).sendKeys(l3);				//Inserting amount in text box
			Thread.sleep(500);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertTextArea(driver)));
		Row row6 = sheet.getRow(6);											//Selected 6th index row (Seventh row)
		Cell c4 = row6.getCell(1);											//Selected cell (6 row,1 column)
		String remark = c4.getStringCellValue();							//Got the URL stored at position 6,1
		ReviewerPOM.insertTextArea(driver).sendKeys(remark);				//Inserting remark in Text area
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickReject(driver)));
		Actions action = new Actions(driver);
		action.moveToElement(ReviewerPOM.clickReject(driver)).click().perform();
		//ReviewerPOM.clickReject(driver).click();							//Clicking on Reject button.
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@value='Reject']")));
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();									//Switching back to parent frame from iFrame
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(performer.OverduePOM.clickDashboard(driver)));
		WebElement element = performer.OverduePOM.clickDashboard(driver);
		action.moveToElement(element).click().perform();					//Clicking on dashboard,
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		int newStatutoryReviewValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading new value of Statutory Pending For Review
		int newStatutoryRejectValue = Integer.parseInt(ReviewerPOM.readStatutoryReject(driver).getText());	//Reading new value of Statutory Rejected
		
		if(newStatutoryReviewValue < oldStatutoryReviewValue && newStatutoryRejectValue > oldStatutoryRejectValue)
		{
			test.log(LogStatus.PASS, "Statutory value for 'Pending For Review' decreamented and Statutory value for 'Rejected' incremented.");
			test.log(LogStatus.INFO, "Old Statutory Pending Review Value = "+oldStatutoryReviewValue+ " | New Statutory Pending Review Value = "+ newStatutoryReviewValue+".");
			test.log(LogStatus.INFO, "Old Statutory Reject Value = "+oldStatutoryRejectValue+ " | New Statutory Reject Value = "+ newStatutoryRejectValue+".");
		}
		else
		{
			test.log(LogStatus.FAIL, "Updated statutory values not reverted on Dashboard.");
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 4)
	void ReviewCountInternalApprove() throws InterruptedException, IOException
	{
		test = extent.startTest("Internal 'Pending For Review' - Approved Verification");
		test.log(LogStatus.INFO, "Test initiated");
		
		ReMethodsPOM.PendingReviewInternal(driver, test, sheet, "Approve");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 5)
	void ReviewCountInternalReject() throws InterruptedException, IOException
	{
		test = extent.startTest("Internal 'Pending For Review' - Rejected Verification");
		test.log(LogStatus.INFO, "Test initiated");
		
		ReMethodsPOM.PendingReviewInternal(driver, test, sheet, "Reject");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 6)
	void MyReminderStatutory() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder - Statutory Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		OverduePOM.MyReminder(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 7)
	void MyReminderInternal() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder - Internal Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		OverduePOM.MyReminder(driver, test, "Internal");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 8)
	void InterimReview() throws InterruptedException, IOException
	{
		test = extent.startTest("Interim Review Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		ReMethodsPOM.SubmittedInterimReview(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 9)
	void MyEscalation() throws InterruptedException, IOException
	{
		test = extent.startTest("My Escalation verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		ReMethodsPOM.MyEscalationReviewer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 9)
	void ReassignUser() throws InterruptedException, IOException
	{
		test = extent.startTest("Reassign User verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		ReMethodsPOM.ReassignPerformer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@AfterTest
	void Closing() throws InterruptedException
	{
		//Thread.sleep(2000);
		//driver.close();
	}
}
