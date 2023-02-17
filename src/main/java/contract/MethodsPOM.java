package contract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationPerformer.performerPOM;

public class MethodsPOM 
{
	private static List<WebElement> elementsList = null;
	private static List<WebElement> elementsList1 = null;
	
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook wb = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static XSSFSheet sheet1 = null;		//Sheet variable
	
	static void progress(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try
		{
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOf(ContractPOM.checkProgress(driver)));
			Thread.sleep(500);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void DraftCount(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	{		
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		
		Thread.sleep(300);
		int draft = Integer.parseInt(ContractPOM.clickDraft(driver).getText());
		ContractPOM.clickDraft(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAddNew(driver)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int total = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		js.executeScript("window.scrollBy(0,-1000)");
		
		if(draft == total)
		{
			test.log(LogStatus.PASS, "Dashboard count matches to total number of records displayed. Total records = "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard count doesn't matches to total number of records displayed. Dashboard count ="+ draft+", Total records = "+total);
		}
		
		Thread.sleep(300);
		ContractPOM.clickAddNew(driver).click();
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		
		String workingDir = System.getProperty("user.dir");
		fis = new FileInputStream(workingDir+"//TestData//Contract.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(1);					//Retrieving second sheet of Workbook
		
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String ContractNumber = c1.getStringCellValue();	//Getting Contract Number from sheet.
		ContractPOM.clickContractNumber(driver).sendKeys(ContractNumber);
		
		row0 = sheet.getRow(1);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String ContractTitle = c1.getStringCellValue();	//Getting Contract Title from sheet.
		ContractPOM.clickContractTitle(driver).sendKeys(ContractTitle);
		
		row0 = sheet.getRow(2);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String Description = c1.getStringCellValue();	//Getting Contract Description from sheet.
		ContractPOM.clickDescription(driver).sendKeys(Description);
		
		ContractPOM.clickEntityLocation(driver).click();	//Clicking on Entity/Branch/Location text box
		Thread.sleep(300);
		ContractPOM.selectLocation(driver).click();		//Selecting first location (ABC Mall Thane)
		
		Thread.sleep(300);
		ContractPOM.clickVendor(driver).click();		//Clicking on Vendor
		Thread.sleep(300);
		ContractPOM.clickVendor(driver).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		ContractPOM.clickVendor(driver).click();		//Clicking on Vendor
		
		Thread.sleep(300);
		ContractPOM.clickDepartment(driver).click();	//Clicking on Department drop down
		Thread.sleep(300);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.CONTROL).perform();
		
		Thread.sleep(300);
		ContractPOM.clickContactPerson(driver).click();	//Clicking on Contact Person of Department drop down
		Thread.sleep(300);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.CONTROL).perform();
		
		Thread.sleep(300);
		ContractPOM.clickProposalDate(driver).click();	//Clicking on Proposal Date
		ContractPOM.selectLastMonth(driver).click();	//Clicking on Last Month arrow
		Thread.sleep(300);
		ContractPOM.selectDate(driver).click();			//Selecting particular date
		
		Thread.sleep(300);
		ContractPOM.clickAgreementDate(driver).click();	//Clicking on Agreement Date
		ContractPOM.selectLastMonth(driver).click();	//Clicking on Last Month arrow
		Thread.sleep(300);
		ContractPOM.selectDate(driver).click();			//Selecting particular date
		
		Thread.sleep(300);
		ContractPOM.clickStartDate(driver).click();		//Clicking on Start Date
		ContractPOM.selectLastMonth(driver).click();	//Clicking on Last Month arrow
		Thread.sleep(300);
		ContractPOM.selectDate(driver).click();			//Selecting particular date
		
		Thread.sleep(300);
		ContractPOM.clickReviewDate(driver).click();	//Clicking on Review Date
		ContractPOM.selectLastMonth(driver).click();	//Clicking on Last Month arrow
		Thread.sleep(300);
		ContractPOM.selectDate(driver).click();			//Selecting particular date
		
		Thread.sleep(300);
		ContractPOM.clickEndDate(driver).click();		//Clicking on End Date
		ContractPOM.selectNextMonth(driver).click();	//Clicking on Last Month arrow
		Thread.sleep(300);
		ContractPOM.selectDate(driver).click();			//Selecting particular date
		
		Thread.sleep(300);
		row0 = sheet.getRow(3);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String NoticeTerm = Integer.toString((int) c1.getNumericCellValue());	//Getting Notice Term value from sheet.
		ContractPOM.clickNoticeTerm(driver).sendKeys(NoticeTerm);
		
		Thread.sleep(300);
		ContractPOM.clickNoticeTermPeriod(driver).click();	//Clicking on Notice Term drop down.
		ContractPOM.clickNoticeTermPeriod(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);	//Selecting 'Week' from drop down.
		
		Thread.sleep(300);
		js.executeScript("window.scrollBy(0,500)");
		
		ContractPOM.clickContractType(driver).click();		//Clicking on Contract Type drop down.
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.CONTROL).perform();	//Selecting second option Conditional Contract
		
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(ContractPOM.clickContractSubType(driver)));
		action.moveToElement(ContractPOM.clickContractSubType(driver)).click().perform();
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).perform();	//Selecting first - already selecting option.
		
		Thread.sleep(500);
		ContractPOM.clickContractOwner(driver).click();		//Clicking on Contract Owner
		Thread.sleep(300);
		ContractPOM.clickContractOwner(driver).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		ContractPOM.clickContractOwner(driver).click();		//Clicking on Contract Owner
		
		Thread.sleep(300);
		row0 = sheet.getRow(3);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String ContractAmount = Integer.toString((int) c1.getNumericCellValue());	//Getting Contract Amount/Value from sheet.
		ContractPOM.clickContractAmount(driver).sendKeys(ContractAmount);
		
		Thread.sleep(300);
		ContractPOM.clickPaymentType(driver).click();		//Clicking on Payment Type drop down
		Thread.sleep(300);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.CONTROL).perform();
		
		Thread.sleep(300);
		js.executeScript("window.scrollBy(0,500)");
		workbook.close();
		
		Thread.sleep(300);
		ContractPOM.clickSave(driver).click();		//Clicking on Save button.
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.readMessage(driver)));
		String msg = ContractPOM.readMessage(driver).getText();
		
		if(msg.contains("Successfully"))
		{
			test.log(LogStatus.PASS, "Message displayed = "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, "Message displayed = "+msg);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		ContractPOM.clickClose(driver).click();
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int totalNew = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		
		if(totalNew > total)
		{
			test.log(LogStatus.PASS, "Records count in grid have been increased. Old count = "+total+", New count = "+totalNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "Records count in grid have not been increased. Old count = "+total+", New count = "+totalNew);
		}
		
		Thread.sleep(300);
		ContractPOM.clickDashboard(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		
		Thread.sleep(300);
		int draftNew = Integer.parseInt(ContractPOM.clickDraft(driver).getText());
		
		if(draftNew > draft)
		{
			test.log(LogStatus.PASS, "Dashboard count of 'Draft' have been increased. Old count = "+draft+", New count = "+draftNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard count of 'Draft' have not been increased. Old count = "+draft+", New count = "+draftNew);
		}
	}
	
	public static void PendingReviewApprovalCount(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickPendingForReview(driver)));
		
		Thread.sleep(300);
		int pending = 0;
		if(type.equalsIgnoreCase("Pending Approval"))
		{
			pending = Integer.parseInt(ContractPOM.clickPendingApproval(driver).getText());
			ContractPOM.clickPendingApproval(driver).click();				//Clicked on "Pending Approval" count
		}
		else
		{
			pending = Integer.parseInt(ContractPOM.clickPendingForReview(driver).getText());
			ContractPOM.clickPendingForReview(driver).click();				//Clicked on "Pending Review" count
		}
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAddNew(driver)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int total = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());	//Reading total "Pending Review" records
		js.executeScript("window.scrollBy(0,-1000)");
		
		if(pending == total)
		{
			test.log(LogStatus.PASS, "Dashboard '"+type+"' count matches to total number of records displayed. Total '"+type+"' records = "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard '"+type+"' count doesn't matches to total number of records displayed.");
			test.log(LogStatus.INFO, "Dashboard count ="+ pending+", Total records = "+total);
		}
		
		Thread.sleep(300);
		ContractPOM.clickDashboard(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		ContractPOM.clickDraft(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAddNew(driver)));
		String title = ContractPOM.readContractName(driver).getText()+" Test1";
		String Description = ContractPOM.readContractName(driver).getText()+" Description";
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int totalDraft = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());	//Reading total "Pending Review" records
		
		Thread.sleep(300);
		js.executeScript("window.scrollBy(0,-1000)");
		ContractPOM.clickEditDraft(driver).click();			//Clicking on first draft's edit button.
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		ContractPOM.clickTasks(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAddNewTask(driver)));
		ContractPOM.clickAddNewTask(driver).click();
		
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickTaskTitle(driver)));
		if(type.equalsIgnoreCase("Pending Approval"))
		{
			ContractPOM.clickApproveRadio(driver).click();				//Clicked on "Approve" radio button
		}
		else
		{
			ContractPOM.clickReviewRadioButton(driver).click();			//Clicked on "Review" radio button
		}
		
		Thread.sleep(300);
		ContractPOM.clickTaskTitle(driver).sendKeys(title);
		
		Thread.sleep(300);
		ContractPOM.clickTaskDescription(driver).sendKeys(Description);
		
		Thread.sleep(300);
		ContractPOM.clickTaskDueDate(driver).click();
		Thread.sleep(300);
		ContractPOM.selectNextMonth(driver).click();
		Thread.sleep(300);
		ContractPOM.selectDate(driver).click();
		
		Thread.sleep(300);
		ContractPOM.clickAssignTo(driver).click();
		Thread.sleep(500);
		ContractPOM.clickSearchBox(driver).sendKeys("Company Admin");
		Thread.sleep(300);
		ContractPOM.clickAssignTo(driver).click();
		Thread.sleep(300);
		ContractPOM.clickAssignTo(driver).click();
		Thread.sleep(300);
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.CONTROL).perform();
		
		Thread.sleep(500);
		int flag = 1;
		try
		{
			WebElement ele = ContractPOM.checkDownload(driver);
			System.out.println("Element - "+ele);
			if(ele.equals(null))
			{
				flag = 0;
			}
		}
		catch(Exception e)
		{
			
		}
		
		if(flag == 0) //(flag == 0) Temporarily disabled.
		{
			Thread.sleep(300);
			ContractPOM.clickAddDocument(driver).click();
			
			progress(driver);
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddDocuments"));
			
			Thread.sleep(300);
			ContractPOM.clickDocumentType(driver).click();
			action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.CONTROL).perform();
			
			Thread.sleep(300);
			String workingDir = System.getProperty("user.dir");
			ContractPOM.clickChooseFile(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");
			
			Thread.sleep(300);
			ContractPOM.clickUpload(driver).click();
			
			Thread.sleep(300);
			wait.until(ExpectedConditions.visibilityOf(ContractPOM.readMessage1(driver)));
			
			Thread.sleep(300);
			ContractPOM.clickClose1(driver).click();
			driver.switchTo().parentFrame();
			
			Thread.sleep(300);
			progress(driver);
			
			Thread.sleep(300);
			ContractPOM.clickSelectAll(driver).click();			
		}
		
		ContractPOM.clickCreatAndAssign(driver).click();
		
		Thread.sleep(300);
		progress(driver);
		
		String msg = ContractPOM.readMessage2(driver).getText();
		if(msg.contains("Task Saved Successfully."))
		{
			test.log(LogStatus.PASS, "Task Saved Successfully.");
		}
		else
		{
			test.log(LogStatus.FAIL, msg);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		ContractPOM.clickClose(driver).click();
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int totalDraftNew = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		
		if(totalDraftNew < totalDraft)
		{
			test.log(LogStatus.PASS, "Records count in Draft grid have been decreased.");
			test.log(LogStatus.INFO, "Old count = "+totalDraft+" | New count = "+totalDraftNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "Records count in Draft grid doesn't decreased.");
			test.log(LogStatus.INFO, "Old count = "+totalDraft+" | New count = "+totalDraftNew);
		}
		
		Thread.sleep(500);
		ContractPOM.clickDashboard(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		
		Thread.sleep(300);
		int pendingNew = 0;
		if(type.equalsIgnoreCase("Pending Approval"))
		{
			pendingNew = Integer.parseInt(ContractPOM.clickPendingApproval(driver).getText());
		}
		else
		{
			pendingNew = Integer.parseInt(ContractPOM.clickPendingForReview(driver).getText());
		}
		
		if(pendingNew > pending)
		{
			test.log(LogStatus.PASS, "Dashboard count of '"+type+"' have been increased.");
			test.log(LogStatus.INFO, "Old Count = "+pending+" | New Count = "+pendingNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard count of '"+type+"' doesn't increased.");
			test.log(LogStatus.INFO, "Old Count = "+pending+" | New Count = "+pendingNew);
		}
	}
	
	public static void ReviewedApprovedCount(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickReviewed(driver)));
		
		Thread.sleep(300);
		int reviewed = Integer.parseInt(ContractPOM.clickReviewed(driver).getText());
		int pendingReview = Integer.parseInt(ContractPOM.clickPendingForReview(driver).getText());
		int approved = Integer.parseInt(ContractPOM.clickApproved(driver).getText());
		int pendingApprove = Integer.parseInt(ContractPOM.clickPendingApprove(driver).getText());
		
		Thread.sleep(500);
		performerPOM.clickMyWorkspace(driver).click();
		
		Thread.sleep(300);
		ContractPOM.clickTaskMenu(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickStatus(driver)));
		
		ContractPOM.clickStatus(driver).click();
		Thread.sleep(300);
		Actions action = new Actions(driver);
		if(type.equals("Reviewed"))
		{
			action.keyDown(Keys.SHIFT).sendKeys("Submitted for Review", Keys.ENTER).keyUp(Keys.SHIFT).perform();
		}
		else
		{
			action.keyDown(Keys.SHIFT).sendKeys("Submitted for Approval", Keys.ENTER).keyUp(Keys.SHIFT).perform();
		}
		
		Thread.sleep(300);
		ContractPOM.clickAllTask(driver).click();				//Clicking on 'All Tasks' drop down.
		Thread.sleep(300);
		action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.SHIFT).perform();	//Selecting 'My Tasks'
		
		Thread.sleep(300);
		ContractPOM.clickApply(driver).click();
		
		int flag = 0;
		try
		{
			Thread.sleep(500);
			elementsList = ContractPOM.readAssignedTo(driver);
			for(int i = 0; i < elementsList.size(); i++)
			{
				String name = elementsList.get(i).getText();
				if(name.contains("company admin"))
				{
					elementsList1 = ContractPOM.clickAction(driver);
					elementsList1.get(i).click();
					flag = 1;
					break;
				}
			}
		}
		catch(Exception e)
		{
			
		}
		
		if(flag == 1)
		{
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			Thread.sleep(300);
			js.executeScript("window.scrollBy(0,1000)");		
			
			ContractPOM.clickStatus1(driver).click();
			Thread.sleep(300);
			action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).keyUp(Keys.SHIFT).perform();
			
			Thread.sleep(300);
			ContractPOM.clickComment(driver).sendKeys("Automation Testing");
			
			Thread.sleep(300);
			String workingDir = System.getProperty("user.dir");
			ContractPOM.clickChooseFile1(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");
			
			Thread.sleep(300);
			ContractPOM.clickSave(driver).click();
			
			Thread.sleep(700);
			js.executeScript("window.scrollBy(0,-1000)");
			wait.until(ExpectedConditions.visibilityOf(ContractPOM.readMsg(driver)));
			
			String msg = ContractPOM.readMsg(driver).getText();
			if(msg.contains("Successfully"))
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
			}
			else
			{
				test.log(LogStatus.FAIL, "Message displayed = "+msg);
			}
			
			Thread.sleep(300);
			driver.switchTo().parentFrame();
			
			ContractPOM.clickClose2(driver).click();
			
			Thread.sleep(500);
			ContractPOM.clickDashboard(driver).click();
			
			wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
			
			Thread.sleep(300);			
			if(type.equals("Reviewed"))
			{
				int reviewedNew = Integer.parseInt(ContractPOM.clickReviewed(driver).getText());
				int pendingReviewNew = Integer.parseInt(ContractPOM.clickPendingForReview(driver).getText());
				if(reviewedNew > reviewed && pendingReview > pendingReviewNew)
				{
					test.log(LogStatus.PASS, "Dashboard count of 'Reviewed' increased.");
					test.log(LogStatus.INFO, "Old Count = "+reviewed+" | New Count = "+reviewedNew);
					test.log(LogStatus.PASS, "Dashboard count of 'Pending For Review' decreased.");
					test.log(LogStatus.INFO, "Old Count = "+pendingReview+" | New Count = "+pendingReviewNew);
				}
				else
				{
					test.log(LogStatus.FAIL, "Dashboard count of 'Reviewed' doesn't increased.");
					test.log(LogStatus.INFO, "Old Count = "+reviewed+" | New Count = "+reviewedNew);
					test.log(LogStatus.FAIL, "Dashboard count of 'Pending For Review' doesn't decreased.");
					test.log(LogStatus.INFO, "Old Count = "+pendingReview+" | New Count = "+pendingReviewNew);
				}
			}
			else
			{
				int approvedNew = Integer.parseInt(ContractPOM.clickApproved(driver).getText());
				int pendingApproveNew = Integer.parseInt(ContractPOM.clickPendingApprove(driver).getText());
				if(approvedNew > approved && pendingApprove > pendingApproveNew)
				{
					test.log(LogStatus.PASS, "Dashboard count of 'Approved' increased.");
					test.log(LogStatus.INFO, "Old Count = "+approved+" | New Count = "+approvedNew);
					test.log(LogStatus.PASS, "Dashboard count of 'Pending Approval' decreased.");
					test.log(LogStatus.INFO, "Old Count = "+pendingApprove+" | New Count = "+pendingApproveNew);
				}
				else
				{
					test.log(LogStatus.FAIL, "Dashboard count of 'Approved' doesn't increased.");
					test.log(LogStatus.INFO, "Old Count = "+approved+" | New Count = "+approvedNew);
					test.log(LogStatus.FAIL, "Dashboard count of 'Pending Approval' doesn't decreased.");
					test.log(LogStatus.INFO, "Old Count = "+pendingApprove+" | New Count = "+pendingApproveNew);
				}
			}
		}
		else
		{
			test.log(LogStatus.SKIP, "No 'Contract - Task' found which is assigned to Company Admin.");
			ContractPOM.clickDashboard(driver).click();
		}
	}
	
	public static void ExpiredCount(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		
		Thread.sleep(300);
		int expired = Integer.parseInt(ContractPOM.clickExpired(driver).getText());
		ContractPOM.clickExpired(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAddNew(driver)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int total = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		js.executeScript("window.scrollBy(0,-1000)");
		
		if(expired == total)
		{
			test.log(LogStatus.PASS, "Dashboard 'Expired' count matches to the total number of records displayed in grid.");
			test.log(LogStatus.INFO, "Dashboard 'Expired' count = "+expired+" | Total records in grid = "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard 'Expired' count doesn't matches to the total number of records displayed in grid.");
			test.log(LogStatus.INFO, "Dashboard 'Expired' count = "+expired+" | Total records in grid = "+total);
		}
		
		Thread.sleep(500);
		ContractPOM.clickStatusDropDown(driver).click();		//Clicking on 'Select Status' drop down.
		Thread.sleep(300);
		ContractPOM.selectRenewed(driver).click();				//Clicking on 'Renewed' option.
		Thread.sleep(500);
		ContractPOM.clickApply(driver).click();					//Clicking on 'Apply' button.
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAction1(driver)));	//Waiting till the 'Action' button gets visible.
		
		Thread.sleep(700);
		js.executeScript("window.scrollBy(0,1000)");
		int totalRenewed = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());	//Read the total 'Renewed' contracts.
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(500);
		ContractPOM.clickStatusDropDown(driver).click();		//Clicking on 'Select Status' drop down.
		Thread.sleep(300);
		ContractPOM.selectExpired(driver).click();				//Clicking on 'Expired' option.
		
		Thread.sleep(500);
		ContractPOM.clickAllContractDropDown(driver).click();	//Clicking on 'All Contract' drop down.
		Thread.sleep(400);
		ContractPOM.selectAssignedToReview(driver).click();		//Selecting 'Assigned To Review'.
		Thread.sleep(400);
		
		ContractPOM.clickApply(driver).click();					//Clicking on 'Apply' button.
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAction1(driver)));	//Waiting till the 'Action' button gets visible.
		
		Thread.sleep(500);
		ContractPOM.clickAction1(driver).click();				//Clicking on first action button.
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickEditContract(driver)));
		
		Thread.sleep(500);
		ContractPOM.clickEditContract(driver).click();
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(ContractPOM.clickContractStatus(driver)));
		
		ContractPOM.clickContractStatus(driver).click();		//Clicking on 'Contract Status' drop down
		Thread.sleep(300);
		ContractPOM.selectRenewedStatus(driver).click();		//Selecting 'Expired' status
		
		Thread.sleep(500);
		ContractPOM.clickYes(driver).click();					//Clicking on 'Yes' button 
		
		Thread.sleep(500);
		String title = ContractPOM.clickContractTitle(driver).getAttribute("value");
		ContractPOM.clickContractTitle(driver).clear();
		ContractPOM.clickContractTitle(driver).sendKeys(title+" - Renewed");
		
		Thread.sleep(500);
		ContractPOM.clickDescription(driver).sendKeys(" - Renewed");
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,500)");
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");		//Setting format of date which we want the date into.
		DateFormat date1 = new SimpleDateFormat("dd");
		DateFormat month1 = new SimpleDateFormat("MM");
		DateFormat year1 = new SimpleDateFormat("yyyy");
		Date date = new Date();							//Getting current date.
		
		String StartDate= dateFormat.format(date); 		//Getting whole date as "23-07-2021" from current date
		String Date = date1.format(date);				//Getting day only  as "23" from current date
		String Month = month1.format(date);				//Getting month only  as "07" from current date
		String Year = year1.format(date);				//Getting year only  as "2021" from current date
		
		int year = Integer.parseInt(Year);
		int newYear = year + 2;							//Increasing year by 2.
		String EndDate = Date+"-"+Month+"-"+newYear;
		
		Thread.sleep(500);
		ContractPOM.clickStartDate(driver).clear();					//Clearing Start Date field.
		ContractPOM.clickStartDate(driver).sendKeys(StartDate);		//Providing Start Date
		Thread.sleep(400);
		ContractPOM.clickStartDate(driver).sendKeys(Keys.ENTER);
		
		Thread.sleep(500);
		ContractPOM.clickEndDate(driver).clear();					//Clearing End Date field.
		ContractPOM.clickEndDate(driver).sendKeys(EndDate);			//Providing End Date
		Thread.sleep(400);
		ContractPOM.clickEndDate(driver).sendKeys(Keys.TAB);
		
		Thread.sleep(300);
		js.executeScript("arguments[0].scrollIntoView(true);", ContractPOM.clickUpdate(driver));
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(300);
		ContractPOM.clickUpdate(driver).click();						//Clicking on Update button.
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,-1000)");
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.readMessage(driver)));
		String msg = ContractPOM.readMessage(driver).getText();
		
		if(msg.contains("Successfully"))
		{
			test.log(LogStatus.PASS, "Message displayed = "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, "Message displayed = "+msg);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		ContractPOM.clickClose(driver).click();
		
		Thread.sleep(800);
		ContractPOM.clickAllContractDropDown(driver).click();	//Clicking on 'All Contract' drop down.
		Thread.sleep(400);
		ContractPOM.selectAllContract(driver).click();		//Selecting 'Assigned To Review'.
		Thread.sleep(400);
		ContractPOM.clickApply(driver).click();					//Clicking on 'Apply' button.
		Thread.sleep(800);
		
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(300);
		int totalNew = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		if(totalNew < total)
		{
			test.log(LogStatus.PASS, "The total number of 'Expired' Contracts in grid is decreased.");
			test.log(LogStatus.INFO, "Old 'Expired' Contracts count = "+total+" | New 'Expired' Contracts count = "+totalNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "The total number of 'Expired' Contracts in grid doesn't decreased.");
			test.log(LogStatus.INFO, "Old 'Expired' Contracts count = "+total+" | New 'Expired' Contracts count = "+totalNew);
		}
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(500);
		ContractPOM.clickStatusDropDown(driver).click();		//Clicking on 'Select Status' drop down.
		Thread.sleep(300);
		ContractPOM.selectRenewed(driver).click();				//Clicking on 'Renewed' option.
		Thread.sleep(500);
		ContractPOM.clickApply(driver).click();					//Clicking on 'Apply' button.
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickAction1(driver)));	//Waiting till the 'Action' button gets visible.
		
		Thread.sleep(700);
		js.executeScript("window.scrollBy(0,1000)");
		int totalRenewedNew = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		
		if(totalRenewedNew > totalRenewed)
		{
			test.log(LogStatus.PASS, "The total number of 'Renewed' Contracts from grid is increased.");
			test.log(LogStatus.INFO, "Old 'Renewed' Contracts count from grid = "+totalRenewed+" | New 'Renewed' Contracts count from grid = "+totalRenewedNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "The total number of 'Renewed' Contracts from grid doesn't increased.");
			test.log(LogStatus.INFO, "Old 'Renewed' Contracts count from grid = "+totalRenewed+" | New 'Renewed' Contracts count from grid = "+totalRenewedNew);
		}
		
		Thread.sleep(500);
		ContractPOM.clickDashboard(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		int expiredNew = Integer.parseInt(ContractPOM.clickExpired(driver).getText());
		if(expiredNew < expired)
		{
			test.log(LogStatus.PASS, "Dashboard 'Expired' count is decreased");
			test.log(LogStatus.INFO, "Old count = "+expired+" | New count = "+expiredNew);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard 'Expired' count doesn't decreased");
			test.log(LogStatus.INFO, "Old count = "+expired+" | New count = "+expiredNew);
		}
	}
	
	public static void MyReports(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickDraft(driver)));
		
		Thread.sleep(500);
		ContractPOM.clickMyReports(driver).click();
		
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickStatusDropDown(driver)));
		
		MyReportsCounts(driver, test, 1, "Draft");
		
		MyReportsCounts(driver, test, 2, "Pending For Review");
		
		MyReportsCounts(driver, test, 3, "Review Completed");
		
		MyReportsCounts(driver, test, 4, "Pending Approval");
		
		MyReportsCounts(driver, test, 5, "Approval Completed");
		
		MyReportsCounts(driver, test, 7, "Active");
		
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();
	}
	
	public static void MyReportsCounts(WebDriver driver, ExtentTest test, int no, String type) throws InterruptedException, IOException
	{
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		
		
		Thread.sleep(500);
		ContractPOM.clickStatusDropDown(driver).click();
		Thread.sleep(400);
		elementsList = ContractPOM.selectContractStatus(driver);	//Getting all Contract Status.
		elementsList.get(no).click();
		
		Thread.sleep(400);
		ContractPOM.clickApply(driver).click();						//CLicking on 'Apply' button.
		
		Thread.sleep(1000);

		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(500);
		ContractPOM.readTotalRecords(driver).click();
		int total = Integer.parseInt(ContractPOM.readTotalRecords(driver).getText());
		
		File dir = new File("C://Users//jiya//Downloads");
		File[] allFiles = dir.listFiles();					//Counting number of files in directory before download
		
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(300);
		ContractPOM.clickExportReport(driver).click();
		
		Thread.sleep(4000);
		File dir1 = new File("C://Users//jiya//Downloads");
		File[] allFilesNew = dir1.listFiles();				//Counting number of files in directory after download
		
		if(allFiles.length < allFilesNew.length)
		{
			test.log(LogStatus.PASS, type+" :- File downloaded successfully.");
			
			File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
		    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
		    {
		       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
		       {
		           lastModifiedFile = allFilesNew[i];
		       }
		    }
			
		    Thread.sleep(500);		
			fis = new FileInputStream(lastModifiedFile);	//Provided last modified / latest downloaded file.
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);						//Retrieving first sheet of Workbook
			//Row row4 = sheet.getRow(3);						//Selected 3rd index row (Fourth row)
			//Cell c1 = row4.createCell(0);					//Selected cell (4th row, 1st column)
			//c1.setCellValue("Test");						//Entered temp data at empty row, so that it could make easy to get Last Row Number
			FileOutputStream fos = new FileOutputStream(lastModifiedFile);
			wb.write(fos);
			fos.close();
			
			int excelTotal = sheet.getLastRowNum();
			int SheetRecords = excelTotal - 4;				//Sheet have extra 5 lines of information at top (But row count started from 0, so 4)
			
			if(total == SheetRecords)
			{
				test.log(LogStatus.PASS, type + " :- No of Records in sheet matches to the number of contracts in grid.");
				test.log(LogStatus.INFO, "Number of records in downloaded sheet = " +SheetRecords+ " | No of contracts in grid = "+total);
			}
			else
			{
				test.log(LogStatus.FAIL, type + " :- No of Records in sheet doesn't matches to the number of contracts in grid.");
				test.log(LogStatus.INFO, "Number of records in downloaded sheet = " +SheetRecords+ " | No of contracts in grid = "+total);
			}
		}
		else
		{
			test.log(LogStatus.FAIL, type+" :- File does not downloaded.");
		}
	}
	
	public static void CriticalDocuments(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickReviewed(driver)));
		
		ContractPOM.clickMyDocuments(driver).click();
		Thread.sleep(300);
		ContractPOM.clickCriticalDocuments(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickNew(driver)));
		Thread.sleep(300);
		ContractPOM.clickNew(driver).click();
		Thread.sleep(300);
		ContractPOM.clickNewFolder(driver).click();
		
		String name = "Test 123";
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(ContractPOM.clickFolderTextbox(driver)));
		Thread.sleep(300);
		ContractPOM.clickFolderTextbox(driver).sendKeys(name);
		
		Thread.sleep(300);
		ContractPOM.clickCreateButton(driver).click();
		
		Thread.sleep(500);
		String msg = ContractPOM.readMsg1(driver).getText();
		
		if(msg.contains("Succesfully"))
		{
			test.log(LogStatus.PASS, msg);
		}
		else
		{
			test.log(LogStatus.FAIL, msg);
		}
	}
}