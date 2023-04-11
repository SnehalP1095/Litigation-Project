package litigationExternalLawyer;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import licensePerformer.LiPerformerPOM;
import litigationAdditionalOwner.MethodsPOM;
import litigationAdditionalOwner.performerPOM;
import performer.OverduePOM;

public class MethodPOM 

{
	
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
			sheet = workbook.getSheetAt(9);					//Retrieving second sheet of Workbook
			return sheet;
		}
		
		static void perform(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type, String noticeCategory) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait(driver, 50);
			WebDriverWait wait1 = new WebDriverWait(driver, 300);
			progress(driver);
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
//			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
			js.executeScript("window.scrollBy(0,-700)");
			
			Thread.sleep(4000);
			clickNewNotice(driver);
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			
			clickDated(driver);
			clickFinancialYear(driver);
			clickRefNo(driver);
			selectNoticeType(driver,type);
			Thread.sleep(300);
			clickAct(driver);
			Thread.sleep(6000);
			//clickOpponentcfo(driver);
			selectOpponent(driver,type);
			Thread.sleep(300);
			selectCategory(driver, noticeCategory);
			clickNoticeTitle(driver);
			Thread.sleep(3000);
			clickNoticeDescription(driver);
			Thread.sleep(7000);
			selectLocation(driver);
			Thread.sleep(10000);
			clickDepartment(driver);
			//clickJurisdiction(driver);
			//Thread.sleep(3000);
			clickNoticeTerm(driver);
			clickOwner(driver);
			clickNoticeBudget(driver);
			clickClaimedAmount(driver);
			clickState(driver);
			clickProvisionalAmount(driver);
			clickProtestMoney(driver);
			selectRisk(driver);
			Thread.sleep(500);
			performerPOM.clickPotentialImpactRadio(driver).click();			//Clicking on 'Monetary' radio button
			Thread.sleep(400);
			performerPOM.clickMonetary(driver).sendKeys("Automation1232");
			Thread.sleep(3000);
			clickLawFirm(driver);
			 Thread.sleep(3000);
			selectNoticeRecipetDate(driver);
			 Thread.sleep(3000);
			clickInternalUser(driver);
//			 Thread.sleep(5000);
//			clickLawyer(driver);
	        Thread.sleep(3000);
			performerPOM.selectNoticeUploadDocument(driver); 
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
											
////			progress(driver);
////			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	//	
			
			Thread.sleep(1000);
			wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
			
			Thread.sleep(500);
			String msg = performerPOM.readMessage(driver).getText();		//Reading Message appeared after save button
			int flag = 0;
			if(msg.equalsIgnoreCase("Notice Created Successfully."))
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
				flag = 1;
			}
			else
			{
				test.log(LogStatus.FAIL, "Message displayed = "+msg);
			}
			
			WebElement ele1 = null;
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
			}
		
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();//Clicking on 'Close'
			
			Thread.sleep(3000);
			performerPOM.clickEditNotice(driver).click();//click edit notice
			Thread.sleep(300);
			
		

			
			NoticeDocument(driver, test);
			TaskActivtity(driver,  test, sheet,  open,gridRecords,  type); 
			Response(driver, test,  sheet, open,gridRecords,  type);
			PaymentLog(driver,test,  sheet, open,  gridRecords, type);
			//ExternalLawyerRating(driver, test);
			 AuditLog(driver);
				
					
	            
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
				test.log(LogStatus.PASS, "Total Notice Count increased in grid after adding New Notice.");
				test.log(LogStatus.INFO, "Old Notice Count from Grid = "+gridRecords+" | New Notice Count from Grid = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Total Notice Count doesn't increased in grid after adding New Notice.");
				test.log(LogStatus.INFO, "Old Notice Count from Grid = "+gridRecords+" | New Notice Count from Grid = "+count1);
			}
			
			Thread.sleep(1000);
			OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
			
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
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
				test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increamented. Old count = "+open+", New Count = "+open1);
			}
		}
			
		

			public  static void clickNewNotice(WebDriver driver) throws InterruptedException 
			  {
					Thread.sleep(3000);
//					JavascriptExecutor js = (JavascriptExecutor) driver;
//					CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
//					js.executeScript("window.scrollBy(0,-700)");
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
			
			public static void clickRefNo(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(500);
			Row row0 = sheet.getRow(5);						//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String refno = c1.getStringCellValue();
			performerPOM.clickRefNo(driver).sendKeys(refno);			//Writing 'Reference No'
			}
			
			public static void selectNoticeType(WebDriver driver, String noticeType) 
			{
				WebElement type = performerPOM.clickNoticeType(driver);
				type.click();
				
				performerPOM.chooseNoticeType(driver).click(); 
				
			}	

			public static void clickAct(WebDriver driver) throws InterruptedException
			{
			   Thread.sleep(300);
			   progress(driver);
		       XSSFRow row0 = sheet.getRow(7);						//Selected 0th index row (First row)
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
		           Row row0 = sheet.getRow(9);						//Selected 0th index row (First row)
		           Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		            String Opponent = c1.getStringCellValue();
	               performerPOM.clickOpponentcfo(driver).sendKeys(Opponent);
			   }
		
		   public static void selectOpponent(WebDriver driver) throws InterruptedException
		   {
			  Thread.sleep(300);
			   Row row1 = sheet.getRow(9);						//Selected 0th index row (First row)
			   Cell c1 = row1.getCell(1);						//Selected cell (0 row,1 column)
			   String opponent = c1.getStringCellValue();
			   selectOpponent(driver,opponent);
		    }
		   
		   public  static void selectOpponent(WebDriver driver,String opponentName)
		   {
				
				WebElement Opponent = performerPOM.clickOpponent(driver);
				Opponent.click();
				
				performerPOM.chooseOpponent(driver).click(); 

			}
		   
			public static void selectCategory(WebDriver driver,String noticeCategory) 
			{
				WebElement Category =  performerPOM.clickNoticeCategory(driver);
				Category.click();
				 performerPOM.chooseCategory(driver).click();
				 
		
			}
		   
		//	performerPOM.clickOpponent(driver).click();					//Clicking on 'Opponent'
//			performerPOM.chooseOpponent(driver).stream().filter(option -> option.getText().equals("Abcde")).toList().get(0).click();	//Writing 'Opponent' name
//			Thread.sleep(300);
//			performerPOM.clickSelectAll(driver).click();
//			performerPOM.clickOpponent(driver).click();
		
//			String Category = c1.getStringCellValue();
//			selectCategory(driver, Category);
//			Thread.sleep(300);
//			performerPOM.clickNoticeCategory(driver).click();
//			performerPOM.chooseCategory(driver);	
			
//			Thread.sleep(300);
//			row0 = sheet.getRow(5);						//Selected 0th index row (First row)
//			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//			String oppoLawyer = c1.getStringCellValue();
//			performerPOM.clickOppLawyer(driver).click();				//Clicking on 'Opponent'
//			performerPOM.clickSearch2(driver).sendKeys(oppoLawyer);		//Writing 'Opposition Lawyer' name
//			Thread.sleep(300);
//			performerPOM.clickSelectAll1(driver).click();
//			performerPOM.clickOppLawyer(driver).click();
			
			
			public static void clickNoticeTitle(WebDriver driver) throws InterruptedException
			{
			  Thread.sleep(300);
			  XSSFRow row0 = sheet.getRow(11);						//Selected 0th index row (First row)
			  XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			  String title = c1.getStringCellValue();
			  performerPOM.clickNoticeTitle(driver).sendKeys(title);		//Writing 'Notice Title'
			}
			
			public static void clickNoticeDescription(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(12);						//Selected 0th index row (First row)
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
			Thread.sleep(3000);
			//performerPOM.clickPlus(driver).click();
			performerPOM.selectLocation(driver).click();
									
			}
			
//			Thread.sleep(300);
//			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickDated(driver)));
//			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickLocation(driver)));
			
		//	performerPOM.clickNoticeDescription(driver).sendKeys(Keys.PAGE_DOWN);
			
			public static void clickJurisdiction(WebDriver driver) throws InterruptedException
			{
			 Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(13);						//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String jurisdiction = c1.getStringCellValue();
			performerPOM.clickJurisdiction(driver).click();					//Clicking on 'Jurisdiction' drop down
			Thread.sleep(600);
			performerPOM.clickSearch3(driver).sendKeys(jurisdiction, Keys.ENTER);	//Writing 'Jurisdiction' name
			
			}
			
			public static void clickDepartment(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(1000);
			Row row0 = sheet.getRow(14);						//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String dept = c1.getStringCellValue();
			performerPOM.clickDepartment(driver).click();					//Clicking on 'Department' drop down
			performerPOM.clickSearch4(driver).sendKeys(dept, Keys.ENTER);	//Writing 'Department' name
			}
			
			public static void clickContactDept(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(15);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String ContactDept = c1.getStringCellValue();
			performerPOM.clickContactDept(driver).click();					//Clicking on 'Contact Person of Department' drop down
			performerPOM.clickSearch5(driver).sendKeys(ContactDept, Keys.ENTER);	//Writing 'Contact Person' name
			
			}
			
			public static void clickNoticeTerm(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			Row row0 = sheet.getRow(16);					//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int noticeTerm = (int) c1.getNumericCellValue();
			performerPOM.clickNoticeTerm(driver).sendKeys(noticeTerm+"");		//Writing 'Notice Term'
			}
			
			public static void clickOwner(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(17);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String owner = c1.getStringCellValue();
			performerPOM.clickOwner(driver).click();					//Clicking on 'Owner' drop down
			performerPOM.clickSearch6(driver).sendKeys(owner, Keys.ENTER);	//Writing 'Owner' name
			}
			
			public static void selectRisk(WebDriver driver) throws InterruptedException
			{
//			Thread.sleep(300);
//			performerPOM.clickWinningProspect(driver).click();
			//Thread.sleep(100);
		//	performerPOM.selectRisk(driver).click();	          //Selecting 'Medium' Winning Prospect'
			Thread.sleep(500);
			performerPOM.clickRisk(driver).click();							//Clicking on 'Risk' drop down.
			Thread.sleep(500);
			performerPOM.selectRisk(driver).click();						//Selecting second option 'High' risk.
		
			
			}
			
			public static void clickNoticeBudget(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(18);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int noticeBudget = (int) c1.getNumericCellValue();
			performerPOM.clickNoticeBudget(driver).sendKeys(noticeBudget+"");	//Writing 'Notice Budget'
			
			}
			
			public static void clickClaimedAmount(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(19);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int claimedAmount = (int) c1.getNumericCellValue();
			performerPOM.clickClaimedAmount(driver).sendKeys(claimedAmount+"");	//Writing 'Claimed Amount'
			
			}
			
			public static void clickState(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(20);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String state = c1.getStringCellValue();
			performerPOM.clickState(driver).click();					//Clicking on 'Owner' drop down
			performerPOM.clickSearchState(driver).sendKeys(state, Keys.ENTER);	//Writing 'State' name
			}
			
			public static void clickProbableAmount(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(21);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int probAmount = (int) c1.getNumericCellValue();
			performerPOM.clickProbableAmount(driver).sendKeys(probAmount+"");	//Writing 'Probable Amount'
			}
			
			public static void clickProvisionalAmount(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			Row row0 = sheet.getRow(22);					//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int provAmount = (int) c1.getNumericCellValue();
			performerPOM.clickProvisionalAmount(driver).sendKeys(provAmount+"");	//Writing 'Provisional Amount'
			}
			
			public static void clickProtestMoney(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(23);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int protestAmount = (int) c1.getNumericCellValue();
			performerPOM.clickProtestMoney(driver).sendKeys(protestAmount+"");	//Writing 'Protest Amount'
			Thread.sleep(500);
			performerPOM.clickProtestMoney(driver).sendKeys(Keys.PAGE_DOWN);
			}

//			Thread.sleep(500);
//			performerPOM.clickPotentialImpactRadio(driver).click();			//Clicking on 'Monetary' radio button
//			
//			Thread.sleep(400);
//			performerPOM.clickMonetary(driver).sendKeys("Automation123");
			
			public static void clickLawFirm(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(24);					//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String lawFirm = c1.getStringCellValue();
			performerPOM.clickLawFirm(driver).click();		//Clicking on 'Law Firm' drop down.
			performerPOM.chooseLawFirm(driver).sendKeys(lawFirm, Keys.DOWN, Keys.ENTER);	//Writing & selecting 'Law Firm' name
			}
			

			
			
			public  static void selectNoticeRecipetDate(WebDriver driver)
		      {
		    	 	
		          WebElement openDate= performerPOM.selectNoticeRecipetDate(driver);
		          openDate.sendKeys("30-09-2021");
		        
		      }
			public static void clickInternalUser(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(25);						//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int internalUserNo = (int) c1.getNumericCellValue();
			performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
			elementsList = performerPOM.chooseInternalUser(driver);
			elementsList.get(internalUserNo).click();							//Selecting particular user no
			performerPOM.clickInternalUser(driver).click();	//Clicking on 'Internal User' drop down.
			}
			
			public static void clickLawyer(WebDriver driver) throws InterruptedException
			{
			Thread.sleep(300);
			XSSFRow row0 = sheet.getRow(26);						//Selected 0th index row (First row)
			XSSFCell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int lawyerNo = (int) c1.getNumericCellValue();
			performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
			elementsList = performerPOM.chooseLawyer(driver);
			elementsList.get(lawyerNo).click();								//Selecting particular lawyer no
			performerPOM.clickLawyer(driver).click();		//Clicking on 'Lawyer' drop down.
			}
			

			public static void NoticeOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
			{
				
				int sheetNo = 9;
			    if(login.equalsIgnoreCase("cfo"))
			    {
			    	sheetNo = 9;
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
				
				perform(driver, test, sheet, open, gridRecords, "Notice - Open",compliancesCount);
			}
			static int CountExcel(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
			{
				WebDriverWait wait = new WebDriverWait(driver, 50);
				progress(driver);
				
				
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
				
				
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
					open = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());	//Reading Case Open count.
					performerPOM.clickCaseOpen(driver).click();						//Clicking on 'Open' Case
				}
				else if(type.equalsIgnoreCase("Case - Closed"))
				{
					open = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());	//Reading Case Open count.
					performerPOM.clickCaseClosed(driver).click();						//Clicking on 'Open' Case
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
				
				Thread.sleep(1000);
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
				
				Thread.sleep(5000);
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
				
				Thread.sleep(100);
				File dir = new File("C://Users//Admin//Downloads");
				File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(500);
				CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
				Thread.sleep(250);
				performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
				//test.log(LogStatus.PASS, "File downloaded successfully.");
				
				Thread.sleep(5500);
				File dir1 = new File("C://Users//Admin//Downloads");
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
					
//					if(flag == 0)
//					{
//						row = sheet.getRow(no-1);
//						c1 = row.getCell(0);
//						records = c1.getStringCellValue();
//						SheetRecords = Integer.parseInt(records);
//					}
					fis.close();
					
					if(count1 == SheetRecords)
					{
						test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
						test.log(LogStatus.INFO, "Total records from Grid = "+count1+" | Total records from Report = "+SheetRecords);
					}
					else
					{
						test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
						test.log(LogStatus.INFO, "Total records from Grid = "+count1+" | Total records from Excel Sheet = "+SheetRecords);
					}
				}
				else
				{
					test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
				}
				return open;
			}
			static void NoticeDocument(WebDriver driver, ExtentTest test) throws InterruptedException
			{
				
				WebDriverWait wait=new WebDriverWait(driver,300); 
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
			int flag = 0;
			if(msg.equalsIgnoreCase("Document(s) uploaded successfully."))
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
				flag = 1;
			}
			else
			{
				test.log(LogStatus.FAIL, "Message displayed = "+msg);
			}
			
			Thread.sleep(1000);
			performerPOM.clickClosedDocument(driver).click(); 
	    }
			
			
		 public  static void TaskActivtity(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
				{
					  WebDriverWait wait = new WebDriverWait(driver, 60);
					
					   Thread.sleep(1000);
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
					  Thread.sleep(1000);
					  performerPOM.clickTaskorActivity(driver).click();
					  Thread.sleep(1000);
					  performerPOM.clickNewTask(driver).click(); 
					 
					  
					  
					Thread.sleep(3000);
					Row row0 = sheet.getRow(29);								//Selected 0th index row (First row)
					Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
					String title = c1.getStringCellValue();
					performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
					
					Thread.sleep(3000);
					row0 = sheet.getRow(30);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String desc = c1.getStringCellValue();
					performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
					
					Thread.sleep(3000);
					performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
					OverduePOM.selectNextMonth(driver).click();
					OverduePOM.selectDate(driver).click();					//Selecting particular date.
					
					Thread.sleep(500);
					Actions action = new Actions(driver);
//					action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
					
					Thread.sleep(500);
					row0 = sheet.getRow(31);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String outcome = c1.getStringCellValue();
					performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
					
					Thread.sleep(500);
					row0 = sheet.getRow(32);									//Selected 0th index row (First row)
					c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
					String internalUser = c1.getStringCellValue();
					performerPOM.clickInternalUser2(driver).click();
					//performerPOM.selectInternalUser2(driver).click();
					performerPOM.selectInternalUser2(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
					
		
					
					Thread.sleep(1000);
					row0 = sheet.getRow(33);									//Selected 0th index row (First row)
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
					row0 = sheet.getRow(34);									//Selected 0th index row (First row)
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
					String msg = performerPOM.readTaskMsg(driver).getText();
					if(msg.contains("Task Saved Successfully."))
					{
						test.log(LogStatus.PASS, "Task Saved Successfully.");
					}
					else if(msg.contains("Task with same title already exists."))
					{
						test.log(LogStatus.FAIL, "Task didn't saved successfully.");
					}
					
					
				}
			   
	  static void Response(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
				{
				   WebDriverWait wait = new WebDriverWait(driver, 60);
	//
				   
					   
					    // Thread.sleep(3000);
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
						  Row row1 = sheet.getRow(37);								//Selected 0th index row (First row)
						  Cell c2 = row1.getCell(1);								//Selected cell (0 row,1 column)
						  String DeliveryMode= c2.getStringCellValue();
						  performerPOM.clickDeliveryMode(driver).click();
						  performerPOM.selectDeliveryMode(driver).sendKeys(DeliveryMode);
						  
						  
						  Thread.sleep(500);
						  Row row0 = sheet.getRow(38);								//Selected 0th index row (First row)
						  Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
						  String CourierCompany= c1.getStringCellValue();
						  performerPOM.clickCourierCompany(driver).sendKeys(CourierCompany);
							 
						  Thread.sleep(500);
							Row row2 = sheet.getRow(39);								//Selected 0th index row (First row)
							Cell c3 = row2.getCell(1);								//Selected cell (0 row,1 column)
							String RefNo= c3.getStringCellValue();
							performerPOM.RefTrackingNo(driver).sendKeys(RefNo);
								 
							Thread.sleep(500);
							Row row3 = sheet.getRow(40);								//Selected 0th index row (First row)
							Cell c4 = row3.getCell(1);								//Selected cell (0 row,1 column)
							String Description= c4.getStringCellValue();
							 performerPOM.Description(driver).sendKeys(Description);
								
							  JavascriptExecutor jse=(JavascriptExecutor)driver;
								jse.executeScript("arguments[0].click();",  performerPOM.clickSaveResponse(driver));
							  //performerPOM.clickSaveResponse(driver).click();
								
								 Thread.sleep(1000);
								wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg(driver)));
									
								Thread.sleep(500);
								String msg3 = performerPOM.readResponseMsg(driver).getText();		//Reading Message appeared after save button
								int flag3 = 0;
								if(msg3.equalsIgnoreCase("Response Details Saved Successfully."))
								{
									test.log(LogStatus.PASS, "Message displayed = "+msg3);
									flag3 = 1;
								}
									else
									{
										test.log(LogStatus.FAIL, "Message displayed = "+msg3);
									}
								
				       }
		   static void PaymentLog(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
				{
				   performerPOM.clickStatusPayments(driver).click();			//Clicking on 'Status/Payments'
				

				
								
					Thread.sleep(300);
					performerPOM.clickInvoiceNo(driver).sendKeys("48579");
					
					
					Thread.sleep(3000);
//					Row r5 = sheet.getRow(45);
//					Cell c5 = r5.getCell(1);
//					String PaymentType = c5.getStringCellValue();
					performerPOM.clickPaymentType(driver).click();
//					performerPOM.selectPaymentType(driver).sendKeys(PaymentType,Keys.ENTER);
					List<WebElement> PaymentType1= driver.findElements(By.xpath("//*[@id='grdNoticePayment_ddlPaymentType_chosen']/div/ul/li"));
					PaymentType1.get(2).click();
						
					Thread.sleep(5000);
//					Row r6 = sheet.getRow(46);
//					Cell c6 = r6.getCell(1);
//					String Amount = c6.getStringCellValue();
		
					performerPOM.clickAmount(driver).sendKeys("7000");
				
					Thread.sleep(300);
					performerPOM.clickSavePaymentLog(driver).click();
					

					 // Thread.sleep(1000);
					  WebDriverWait wait1 = new WebDriverWait(driver, 300);
					 wait1.until(ExpectedConditions.visibilityOf(performerPOM.readPymentmsg(driver)));
						
						Thread.sleep(500);
						String msg4 = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
						int flag4= 0;
						if(msg4.equalsIgnoreCase("Payment Details Saved Successfully."))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg4);
							flag4 = 1;
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg4);
						}
					
					
				 
				}
			
			
		static void ExternalLawyerRating(WebDriver driver, ExtentTest test) throws InterruptedException
			{
				
				 WebDriverWait wait = new WebDriverWait(driver, 100);
			  Thread.sleep(3000);
			   performerPOM. clickExternalLawyerRating(driver).click();
			   Thread.sleep(3000);
			   performerPOM.selectExternalLawyerRating(driver);
			   
			
			   Thread.sleep(3000);
			   performerPOM.clickNewCriteria(driver).click();
			   Thread.sleep(3000);
			   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
			   performerPOM.clickCriteria(driver).sendKeys(" Rating New Automate Test	`	`	");
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
					int flag5= 0;
					if(msg5.equalsIgnoreCase("Rating Saved Successfully."))
					{
						test.log(LogStatus.PASS, "Message displayed = "+msg5);
						flag5 = 1;
					}
					else
					{
						test.log(LogStatus.FAIL, "Message displayed = "+msg5);
					}
			}
			   
			   
		static void AuditLog(WebDriver driver) throws InterruptedException
			{
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
			}
		static void perform1(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait(driver, 50);
			WebDriverWait wait1 = new WebDriverWait(driver, 300);
			
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
			js.executeScript("window.scrollBy(0,-700)");
			performerPOM.clickNew(driver).click();						//Clicking on 'New' button
			
			progress(driver);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			performerPOM.clickCaseDate(driver).click();					//Clicking on 'Dated' button
			OverduePOM.selectLastMonth(driver).click();					//Clicking last month arrow.
			OverduePOM.selectDate3(driver).click();						//Clicking particular date.
		
			Thread.sleep(300);
			Row row1 = sheet.getRow(0);								//Selected 0th index row (First row)
			Cell c2 = row1.getCell(1);	
			String caseType1 = c2.getStringCellValue();
			//selectCaseType(driver,caseType1);
		     performerPOM.clickCaseType1(driver).click();
		     performerPOM.chooseCaseType(driver).click();
		

			
			Thread.sleep(300);
			performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
			elementsList = performerPOM.clickFinanceSearchCheckbox(driver);
			elementsList=performerPOM.chooseDropDownOption(driver);
			elementsList.get(10).click();								//Clicking third option
			performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.

				
			
			Thread.sleep(3000);
			Row row0 = sheet.getRow(52);								//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String refno = c1.getStringCellValue();
			performerPOM.clickRefNo(driver).sendKeys(refno);			//Writing 'Court Case No'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(53);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String caseNo = c1.getStringCellValue();
			performerPOM.clickInternalCaseNo(driver).sendKeys(caseNo);	//Writing 'Court Case No'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(54);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String title = c1.getStringCellValue();
			performerPOM.clickNoticeTitle(driver).sendKeys(title);		//Writing 'Case Title'
			
			Thread.sleep(300);
			progress(driver);
			
		
			Thread.sleep(3000);
		     row0 = sheet.getRow(55);								//Selected 0th index row (First row)
		    c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
	 	    int actNo = (int) c1.getNumericCellValue();
			performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
//		//	elementsList = performerPOM.chooseAct(driver);
			elementsList = performerPOM.chooseAct1(driver);
		elementsList.get(3).click();							//Selecting particular act no
			performerPOM.clickAct(driver).click();	                  //Clicking on 'Act' drop down.
			
			Thread.sleep(3000);
			row0 = sheet.getRow(56);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String underSection = c1.getStringCellValue();
			performerPOM.clickUnderSection(driver).sendKeys(underSection);	//Writing 'Under section'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(57);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String caseType = c1.getStringCellValue();
			performerPOM.clickCaseCategory(driver).click();
			performerPOM.clickSearchCaseCategory(driver).sendKeys(caseType, Keys.ENTER);	//Writing 'Case Type'
			
			Thread.sleep(500);
			progress(driver);
			
			Thread.sleep(3000);
			row0 = sheet.getRow(58);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			int caseBudget = (int) c1.getNumericCellValue();
			performerPOM.clickCaseBudget(driver).sendKeys(caseBudget+"");
			
			

			Thread.sleep(3000);
			row0 = sheet.getRow(59);						//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String opponent = c1.getStringCellValue();
			
			selectOpponent(driver, opponent);
			
			performerPOM.clickOpponent(driver).click();	
			
////			Thread.sleep(300);
////			row0 = sheet.getRow(7);								//Selected 0th index row (First row)
////			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
////			String opponent = c1.getStringCellValue();
////			performerPOM.clickOpponent(driver).click();					//Clicking on 'Opponent'
////			performerPOM.clickSearchBox(driver).sendKeys(opponent);		//Writing 'Opponent' name
////			Thread.sleep(300);
////			selectOpponent(driver, opponent);
////			performerPOM.clickSelectAll2(driver).click();
////			performerPOM.clickOpponent(driver).click();
//			
////			Select Actdropdown = new Select(performerPOM.clickOpponent(driver));
////			Actdropdown.selectByVisibleText("abc opponent");
////			
//			
		Thread.sleep(3000);
			
			row0 = sheet.getRow(60);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String oppoLawyer = c1.getStringCellValue();
			performerPOM.clickOppLawyer(driver).click();				//Clicking on 'Opponent'
			performerPOM.clickSearchBox1(driver).sendKeys(oppoLawyer);	//Writing 'Opposition Lawyer' name
			Thread.sleep(300);
			performerPOM.clickSelectAll3(driver).click();
			performerPOM.clickOppLawyer(driver).click();
			
			Thread.sleep(3000);
			row0 = sheet.getRow(61);								//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String court = c1.getStringCellValue();
			performerPOM.clickCourt(driver).click();
			performerPOM.clickSearchCourt(driver).sendKeys(court, Keys.ENTER);
			
			Thread.sleep(3000);
			row0 = sheet.getRow(62);							//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String judge = c1.getStringCellValue();
			performerPOM.clickJudge(driver).sendKeys(judge);
			
			Thread.sleep(3000);		
			performerPOM.clickCaseBudget(driver).sendKeys(Keys.PAGE_DOWN);
			
			Thread.sleep(3000);
			row0 = sheet.getRow(63);							//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String caseDesc = c1.getStringCellValue();
			performerPOM.clickNoticeDescription(driver).sendKeys(caseDesc);
			
			Thread.sleep(4000);
			performerPOM.clickLocation(driver).click();					//Clicking on Location drop down
			//performerPOM.clickPlus(driver).click();
			Thread.sleep(4000);
			 performerPOM.selectLocation(driver).click();
			//elementsList.get(3).click();								//Selecting third visible location
			
			Thread.sleep(700);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseDate(driver)));
			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCaseDate(driver)));
			
			Thread.sleep(700);
			js.executeScript("window.scrollBy(0,600)");
			
////			Thread.sleep(300);
////			row0 = sheet.getRow(12);							//Selected 0th index row (First row)
////			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
////			String jurisdiction = c1.getStringCellValue();
////			performerPOM.clickJurisdiction(driver).click();					//Clicking on 'Jurisdiction' drop down
////			Thread.sleep(600);
////			performerPOM.clickSearch3(driver).sendKeys(jurisdiction, Keys.ENTER);	//Writing 'Jurisdiction' name
////			
//			
			
			Thread.sleep(8000);
			row0 = sheet.getRow(65);							//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String dept = c1.getStringCellValue();
			performerPOM.clickDepartment(driver).click();					//Clicking on 'Department' drop down
			performerPOM.clickSearch4(driver).sendKeys(dept, Keys.ENTER);	//Writing 'Department' name
			
	///		Thread.sleep(300);
	///		performerPOM.clickLocation(driver).click();					//Clicking on Location drop down
	///		//performerPOM.clickPlus(driver).click();
	///		Thread.sleep(300);
//		/	elementsList = performerPOM.selectLocation(driver);
	///		elementsList.get(2).click();								//Selecting third visible location
//		/	
			
			Thread.sleep(3000);
			row0 = sheet.getRow(66);							//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String ContactDept = c1.getStringCellValue();
			performerPOM.clickContactDept(driver).click();					//Clicking on 'Contact Person of Department' drop down
			performerPOM.clickSearch5(driver).sendKeys(ContactDept, Keys.ENTER);	//Writing 'Contact Person' name
			
			Thread.sleep(3000);
			row0 = sheet.getRow(67);							//Selected 0th index row (First row)
			c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String owner = c1.getStringCellValue();
			performerPOM.clickOwner(driver).click();					//Clicking on 'Owner' drop down
		  performerPOM.clickSearch6(driver).sendKeys(owner, Keys.ENTER);	//Writing 'Owner' name
			
			Thread.sleep(3000);
			performerPOM.clickWinningProspect1(driver).click();
			Thread.sleep(100);
		performerPOM.selectRisk1(driver).click();			//Selecting 'Medium' Winning Prospect'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(68);					//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int claimedAmount = (int) c1.getNumericCellValue();
			performerPOM.clickClaimedAmount(driver).sendKeys(claimedAmount+"");	//Writing 'Claimed Amount'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(69);					//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int probAmount = (int) c1.getNumericCellValue();
			performerPOM.clickProbableAmount(driver).sendKeys(probAmount+"");	//Writing 'Probable Amount'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(70);					//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int provAmount = (int) c1.getNumericCellValue();
			performerPOM.clickProvisionalAmount(driver).sendKeys(provAmount+"");	//Writing 'Provisional Amount'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(71);					//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int protestAmount = (int) c1.getNumericCellValue();
			performerPOM.clickProtestMoney(driver).sendKeys(protestAmount+"");	//Writing 'Protest Amount'
			
			Thread.sleep(3000);
			performerPOM.clickProtestMoney(driver).sendKeys(Keys.PAGE_DOWN);
			
			Thread.sleep(3000);
			performerPOM.clickPotentialImpactRadio(driver).click();			//Clicking on 'Monetary' radio button
			
			Thread.sleep(3000);
			performerPOM.clickMonetary(driver).sendKeys("Automation123");
			
			
			Thread.sleep(3000);
			row0 = sheet.getRow(72);					//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String state = c1.getStringCellValue();
			performerPOM.clickState(driver).click();					//Clicking on 'Owner' drop down
			performerPOM.clickSearchState(driver).sendKeys(state, Keys.ENTER);	//Writing 'State' name
		
			
			Thread.sleep(3000);
			row0 = sheet.getRow(73);					//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String lawFirm = c1.getStringCellValue();
			performerPOM.clickLawFirm(driver).click();		//Clicking on 'Law Firm' drop down.
			performerPOM.chooseLawFirm(driver).sendKeys(lawFirm, Keys.DOWN, Keys.ENTER);	//Writing & selecting 'Law Firm' name
			
			Thread.sleep(300);
			progress(driver);
//		/	
//		/	Thread.sleep(500);
	///		performerPOM.clickRisk(driver).click();							//Clicking on 'Risk' drop down.
	///		Thread.sleep(300);
	///		performerPOM.selectRisk2(driver).click();						//Selecting second option 'High' risk.
		
			
			Thread.sleep(3000);
		    row0 = sheet.getRow(74);						//Selected 0th index row (First row)
			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			int internalUserNo = (int) c1.getNumericCellValue();
			performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
			elementsList = performerPOM.chooseInternalUser1(driver);
			elementsList.get(internalUserNo).click();							//Selecting particular user no
			performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
			
//			Thread.sleep(3000);
//			row0 = sheet.getRow(23);						//Selected 0th index row (First row)
//			c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//			int lawyerNo = (int) c1.getNumericCellValue();
//			performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
//			elementsList = performerPOM.chooseLawyer(driver);
//			elementsList.get(lawyerNo).click();								//Selecting particular lawyer no
//			performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
			
			Thread.sleep(3000);
			OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
			
			Thread.sleep(1000);
			wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage1(driver)));
			
			Thread.sleep(500);
			String msg = performerPOM.readMessage1(driver).getText();		//Reading Message appeared after save button
			int flag = 0;
			if(msg.equalsIgnoreCase("Case Created Successfully."))
			{
				test.log(LogStatus.PASS, "Message displayed = "+msg);
				flag = 1;
			}
		else
			{
				test.log(LogStatus.FAIL, "Message displayed = "+msg);
			}
		
			WebElement ele1 = null;
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
			}
		
			driver.switchTo().parentFrame();
			performerPOM.clickClose(driver).click();			//Clicking on 'Close'
			
			Thread.sleep(500);
			performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			js.executeScript("window.scrollBy(0,700)");
			
		
			 
			
			Thread.sleep(1000);
			performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
			js.executeScript("window.scrollBy(0,700)");

	      Thread.sleep(5000);
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
	       test.log(LogStatus.PASS, "Total Case Count increased in grid after adding New Case.");
	       test.log(LogStatus.INFO, "Old Case Count from Grid = "+gridRecords+" | New Case Count from Grid = "+count1);
	     }
	     else
	     {
	        test.log(LogStatus.FAIL, "Total Case Count doesn't increased in grid after adding New Case.");
	        test.log(LogStatus.INFO, "Old Case Count from Grid = "+gridRecords+" | New Case Count from Grid = "+count1);
	     }

	       Thread.sleep(1000);
	       OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'

	       Thread.sleep(500);
	       wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
	       int open1 = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());	//Reading Notice Open count.

	       if(open1 > open)
	       {
	          test.log(LogStatus.PASS, type+" Dashboard Count increamented. Old count = "+open+", New Count = "+open1);
	       }
	       else
	      {
	          test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increamented. Old count = "+open+", New Count = "+open1);
	       }
	     }

			

			static void Document(WebDriver driver,ExtentTest test) throws InterruptedException
			{
	           			
			
	          WebDriverWait wait = new WebDriverWait(driver, 50);
	          Thread.sleep(4000);
	          performerPOM.clickCaseOpen(driver).click();
	          Thread.sleep(3000);
	          performerPOM.clickEditNotice(driver).click();
			  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			  Thread.sleep(2000);
			  performerPOM.clickNoticeDocument(driver).click();     //click notice document
			  Thread.sleep(500);
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
			  int flag = 0;
			  if(msg.equalsIgnoreCase("Document(s) uploaded successfully"))
			 {
				 test.log(LogStatus.PASS, "Message displayed = "+msg);
				 flag = 1;
			 }
			 else
			 {
				 test.log(LogStatus.FAIL, "Message displayed = "+msg);
			 }
			
			  Thread.sleep(1000);
			  performerPOM.clickClosedDocument(driver).click(); 
			  
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
		        
		        Thread.sleep(3000);
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

		     driver.switchTo().parentFrame();
			  
			  
		 }
			
			static void TaskActivity1(WebDriver driver, ExtentTest test, XSSFWorkbook workbook,String login) throws InterruptedException, IOException
			{
				
				sheet = workbook.getSheetAt(9);	
				
			    WebDriverWait wait=new WebDriverWait(driver,20);
//			    Thread.sleep(2000);
//			    performerPOM.clickCaseOpen(driver).click();
//		          Thread.sleep(3000);
//		          performerPOM.clickEditNotice(driver).click();
			    Thread.sleep(3000);
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    Thread.sleep(3000);
			    performerPOM.clickCaseTask(driver).click();
			    Thread.sleep(300);
			    performerPOM.clickCaseNewTask(driver).click(); 
			    Thread.sleep(5000);
			    performerPOM.clickHearingDate(driver).sendKeys("14-2-2023");
//			    Thread.sleep(4000);
//			    performerPOM.clickSaveHearingDate(driver).click();
			    
			    By locator = By.xpath("//*[@id='lnkSaveRefNo']/img");

				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				Thread.sleep(4000);
				WebElement ViewButton = driver.findElement(locator);	
				Thread.sleep(3000);
			    JavascriptExecutor jse=(JavascriptExecutor)driver;
			    jse.executeScript("arguments[0].click();", ViewButton);
			
			  
			  
				Thread.sleep(5000);
				Row row0 = sheet.getRow(29);								//Selected 0th index row (First row)
				Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
				String title = c1.getStringCellValue();
				performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
				
				Thread.sleep(5000);
				row0 = sheet.getRow(30);									//Selected 0th index row (First row)
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
				 row0 = sheet.getRow(31);									//Selected 0th index row (First row)
				 c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String outcome = c1.getStringCellValue();
				performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
				
				
				
				Thread.sleep(1000);
				row0 = sheet.getRow(32);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String internalUser = c1.getStringCellValue();
				performerPOM.clickInternalUser3(driver).click();
				//performerPOM.selectInternalUser2(driver).click();
				performerPOM.selectInternalUser3(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
				
				Thread.sleep(1000);
				row0 = sheet.getRow(33);									//Selected 0th index row (First row)
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
				row0 = sheet.getRow(34);									//Selected 0th index row (First row)
				c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
				String remark = c1.getStringCellValue();
				performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
				
			    
				
				Thread.sleep(300);
				OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
				
				Thread.sleep(2000);
				performerPOM.clickMinimize(driver).click();	
				
				
//				Thread.sleep(300);
//				wait.until(ExpectedConditions.visibilityOf(performerPOM.readTaskMsg(driver)));
				
				Thread.sleep(3000);
				String msg = performerPOM.readTaskMsg(driver).getText();
				if(msg.contains("Task Saved Successfully."))
				{
					test.log(LogStatus.PASS, "Task Saved Successfully.");
				}
				else
				{
					test.log(LogStatus.FAIL, "Select Hearing or if you do not want to map task with hearing, then please select 'Not Applicable'.");
				}
				
//				Thread.sleep(2000);
//				performerPOM.clickMinimize(driver).click();	
				
			/*	Thread.sleep(3000);
				performerPOM.clickNoticeEditTaskcfo(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickTaskTitle(driver).clear();
				
				Thread.sleep(3000);
				performerPOM.clickTaskTitle(driver).sendKeys("New Task 5 jan");	//Writing 'Task Title'
				
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
				performerPOM.clickNoticeTaskEditResponsecfo1(driver).click();
				
				Thread.sleep(1000);
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
			        alert.accept(); */
				
				
			}
		

		
			static void CaseHearing(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException
			{
				
				sheet = workbook.getSheetAt(9);	
				 WebDriverWait wait=new WebDriverWait(driver,20);
			    
				 driver.switchTo().parentFrame();
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			    
			       Thread.sleep(3000);
				   performerPOM.clickCaseHearing(driver).click();
					Thread.sleep(3000);
					performerPOM.clickNewCaseHearing(driver).click();
					
					
					
//					Thread.sleep(300);
//					Row row0 = sheet.getRow(35);					//Selected 0th index row (First row)
//					Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//					int HearingDate = (int) c1.getNumericCellValue();
//					performerPOM.clickCaseHearingDate(driver).sendKeys(HearingDate+"");	//Writing 'HearingDate'
//					
					performerPOM.clickCaseHearingDate(driver).sendKeys("10-3-2023");	//Writing 'HearingDate'
					
				
				    Thread.sleep(3000);
				    performerPOM.clickSaveCaseHearingDate(driver).click();
				
					
					Thread.sleep(2000);
					Row row1 = sheet.getRow(78);									//Selected 0th index row (First row)
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
				    performerPOM.clickminimize(driver).click();
					  Thread.sleep(3000);
					    performerPOM.clickEditCaseHearingcfo(driver).click();
					    
					    Thread.sleep(3000);
					    performerPOM.clickCaseHearingDecsri(driver).clear();
					    Thread.sleep(3000);
					    performerPOM.clickCaseHearingDecsri(driver).sendKeys("Case Hearing 5 jan 2023");		//Writing 'HearingDescription'
					    
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
				 
			static void CaseOrder(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException
			{
				
				sheet = workbook.getSheetAt(9);	
				 WebDriverWait wait=new WebDriverWait(driver,20);
				 driver.switchTo().parentFrame();
				  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				 Thread.sleep(3000);
				 performerPOM.clickCaseOrder(driver).click();
				 Thread.sleep(6000);
				 performerPOM.clickNewCaseOrder(driver).click();
				 Thread.sleep(3000);
				 performerPOM. clickCaseOrderDate(driver).sendKeys("16-1-2023");
				 Thread.sleep(3000);
				 performerPOM.clickOrderPanel(driver).click();
//				 Thread.sleep(3000);
//				 performerPOM. clickCaseOrderType(driver).click();
//				 Thread.sleep(3000);
//				 performerPOM.selectCaseOrderType(driver).click();
				
				 
				 
					
//					Thread.sleep(300);
//					Row row0 = sheet.getRow(82);					//Selected 0th index row (First row)
//					Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
//					int OrderTitle = (int) c1.getNumericCellValue();
//					performerPOM.clickCaseOrderTitle(driver).sendKeys(OrderTitle+"");	//Writing 'HearingDate'
					
			     Thread.sleep(2000);
				 Row row1 = sheet.getRow(82);									//Selected 0th index row (First row)
				 Cell c1 = row1.getCell(1);									//Selected cell (0 row,1 column)
				 String OrderTitle = c1.getStringCellValue();
				 performerPOM.clickCaseOrderTitle(driver).sendKeys(OrderTitle);   //click order title
				 
				 Thread.sleep(2000);
				 Row row2 = sheet.getRow(83);									//Selected 0th index row (First row)
				 Cell c2 = row2.getCell(1);									//Selected cell (0 row,1 column)
				 String OrderDecri = c2.getStringCellValue();
				 performerPOM.clickCaseOrderDecri(driver).sendKeys(OrderDecri);     //click oder description
				

				 Thread.sleep(4000);
				 performerPOM.clickSaveCaseOrder(driver).click();
				 
				 
				 Thread.sleep(3000);
					String msg = performerPOM.clickReadOrderMsg(driver).getText();
					if(msg.contains("Order Details Saved Successfully."))
					{
						test.log(LogStatus.PASS, "Order Details Saved Successfully.");
					}
					else
					{
						test.log(LogStatus.FAIL, "Provide Order Date");
					
					}
					
				/*	 Thread.sleep(3000);
					 performerPOM.clickEditCaseOrdercfo(driver).click();
					 
					 performerPOM.clickCaseOrderTitle(driver).clear();
					 
					 performerPOM.clickCaseOrderTitle(driver).sendKeys("Order no 56");
					 
					 performerPOM.clickCaseOrderDecri(driver).clear();
					 
					 performerPOM.clickCaseOrderDecri(driver).sendKeys("order as on 5 jan 23");     //click oder description
					 
					 performerPOM.ChooseOrderFile(driver).click();
					 
					 Thread.sleep(3000);
					 performerPOM.clickSaveCaseOrder(driver).click();
					 
					 
					 wait.until(ExpectedConditions.visibilityOf(performerPOM.readResponseMsg(driver)));
						
						Thread.sleep(500);
						String msg3 = performerPOM.readResponseMsg(driver).getText();		//Reading Message appeared after save button
						int flag3 = 0;
						if(msg3.equalsIgnoreCase("Order Details Saved Successfully."))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg3);
							flag3 = 1;
						}
							else
							{
								test.log(LogStatus.FAIL, "Message displayed = "+msg3);
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
				        alert.accept();*/
			}	 
				 
			static void AdvocateBill(WebDriver driver,ExtentTest test) throws InterruptedException
			{
				 WebDriverWait wait=new WebDriverWait(driver,20);
				 
//				 Thread.sleep(4000);
//		          performerPOM.clickCaseOpen(driver).click();
//		          Thread.sleep(3000);
//		          performerPOM.clickEditNotice(driver).click();
		          
		     	 driver.switchTo().parentFrame();
		          Thread.sleep(3000);
				    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
				    
				   
				   
			      Thread.sleep(3000);
				 performerPOM.clickAdvocateBill(driver).click();
				 
				 Thread.sleep(3000);
				 performerPOM.clickExportAdvocateBill(driver).click();
				 Thread.sleep(3000);
				 performerPOM. clickNewAdvocateBill(driver).click();
				
				 Thread.sleep(5000);
			     performerPOM. clickInvoiceNum(driver).sendKeys("60857");
				 Thread.sleep(4000);
				 performerPOM. clickInvoiceDate(driver).sendKeys("16-11-2022");
				 Thread.sleep(4000);
				 performerPOM.clickAdvocateBillPanel(driver).click();
				 Thread.sleep(4000);
				 performerPOM. clickInvoiceAmount(driver).sendKeys("30000");
				 Thread.sleep(4000);
				 performerPOM.clickLawFirm1(driver).click();
				 performerPOM.selectLawFirm2(driver).get(2).click();
				 Thread.sleep(4000);
				 performerPOM.clickApprover1(driver).click();
			      Thread.sleep(4000);
			      performerPOM.selectApprover1(driver).get(5).click();
				 Thread.sleep(4000);
				 performerPOM.clickApprover2(driver).click();
			     Thread.sleep(4000);
				 performerPOM.selectApprover2(driver).get(5).click();
				 
				 Thread.sleep(4000);
				 performerPOM.clickUploadDoc(driver).click();
				
				 Thread.sleep(4000);
				 performerPOM.clickSaveAdvocateBill(driver).click();
				 
				 Thread.sleep(500);
					String msg4 = performerPOM.clickReadAdvocateMsg(driver).getText();		//Reading Message appeared after save button
					String msg6 = performerPOM.clickReadAdvocateMsg1(driver).getText();		//Reading Message appeared after save button
					if(msg4.equalsIgnoreCase("Advocate Bill Added Successfully."))
					{
						test.log(LogStatus.PASS, "Message displayed = "+msg4);
					
					}
					
					else
					{
						test.log(LogStatus.FAIL, "Message displayed = "+msg6);
					}
					performerPOM.clickeditAdvocatebill(driver).click();
					
					 Thread.sleep(5000);
				     performerPOM. clickInvoiceNum(driver).clear();
					 Thread.sleep(5000);
				     performerPOM. clickInvoiceNum(driver).sendKeys("60957");
				     
				     Thread.sleep(4000);
					 performerPOM.clickSaveAdvocateBill(driver).click();
				     
					 Thread.sleep(500);
						String msg5 = performerPOM.clickReadAdvocateMsg(driver).getText();		//Reading Message appeared after save button
					
						if(msg5.equalsIgnoreCase("Advocate Bill Updated Successfully."))
						{
							test.log(LogStatus.PASS, "Message displayed = "+msg5);
						
						}
						else
						{
							test.log(LogStatus.FAIL, "Message displayed = "+msg5);
						}
						
						Thread.sleep(2000);
						performerPOM.clickDownloadDocAdvocatebill(driver).click();
						
						 test.log(LogStatus.PASS, "Advocate Bill Document Download Successfully");
						 
						 Thread.sleep(2000);
						performerPOM.clickViewDocAdvocatebill(driver).click();
				         
						 test.log(LogStatus.PASS, "Advocate Bill Document View Successfully");
						 
						 Thread.sleep(2000);
						performerPOM.clickViewDocAdvocatebillClose(driver).click();
				 
						 Thread.sleep(2000);
						performerPOM.clickViewDocAdvocatebillPdf(driver).click();
							
					    Thread.sleep(2000);
						performerPOM.clickViewDocAdvocatebillPdfClose(driver).click();
						
						 test.log(LogStatus.PASS, "Advocate Bill Document Pdf Successfully");
						
						Thread.sleep(2000);
						performerPOM.clickAdvocateBillDelete(driver).click();
						
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
				 
						 test.log(LogStatus.PASS, "Advocate Bill Document Deleted Successfully");
				 
	      }

	      static void StatusPayment(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException
	      {	

				sheet = workbook.getSheetAt(9);	
	    	  
	    	       WebDriverWait wait=new WebDriverWait(driver,50);
	    	       driver.switchTo().parentFrame();
	 	          Thread.sleep(3000);
	 			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	      
	               performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
					
					wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));

			     	Thread.sleep(3000);
					Row row0 = sheet.getRow(95);					//Selected 0th index row (First row)
					Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
					int InvoiceNo = (int) c1.getNumericCellValue();
					performerPOM.clickCaseInvoiceNo1(driver).sendKeys(InvoiceNo+"");	//Writing 'Invoice No'
					
				    
					Thread.sleep(5000);
					performerPOM.clickPaymentTyp1(driver);
					List<WebElement> PaymentType1= driver.findElements(By.xpath("//*[@id='grdCasePayment_ddlPaymentType_chosen']/div/ul/li"));
					PaymentType1.get(1).click();
					
					
					Thread.sleep(10000);
//					Row row1 = sheet.getRow(54);					//Selected 0th index row (First row)
//					Cell c2 = row1.getCell(1);						//Selected cell (0 row,1 column)
//					int Amount = (int) c2.getNumericCellValue();
//					performerPOM.clickAmount1(driver).sendKeys(Amount+"");	//Writing 'Amount'
					performerPOM.clickAmount1(driver).sendKeys("5000");	//Writing 'Amount'
				
		
					Thread.sleep(3000);
					performerPOM.clickSavePaymentLog1(driver).click();
					
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
					performerPOM.clickViewPaymentDoccfo(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickNoticeclosePaymentDocpopupcfo(driver).click();
					
					test.log(LogStatus.PASS, "Payment Document popup open successfully");
					
					
				
					Thread.sleep(3000);
					performerPOM.clickEditPaymentDoccfo(driver).click();
					
					Thread.sleep(3000);
					performerPOM.clickCaseInvoiceNo1(driver).clear();
					 Thread.sleep(3000);
				    performerPOM.clickCaseInvoiceNo1(driver).sendKeys("Invoice No 578");
				    
				    Thread.sleep(6000);
					performerPOM.clickCaseStatusPaymentUploadtcfo(driver);
				    

					Thread.sleep(3000);
					performerPOM.clickSavePaymentLog1(driver).click();
					
					  Thread.sleep(500);
						String msg = performerPOM.readPymentmsg(driver).getText();		//Reading Message appeared after save button
					
						if(msg.equalsIgnoreCase("Payment Details Saved Successfully."))
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
	      

	      static void ExternalLawyer(WebDriver driver,ExtentTest test,int opp) throws InterruptedException
	      {
	    	  
	    	           WebDriverWait wait=new WebDriverWait(driver,50);
	    	           driver.switchTo().parentFrame();
	    		          Thread.sleep(3000);
	    				    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
					  Thread.sleep(3000);
					   performerPOM. clickExternalLawyerRating1(driver).click();
					   
//					   Thread.sleep(4000);
//					   performerPOM.selectCaseExternalLawyer(driver);
					   
					   WebElement ExternalLawyer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ddlLayerType_chosen']")));
					   if(ExternalLawyer.isEnabled())
					   {
						   
					     Select ExternalLawyer1=new Select(ExternalLawyer);
					     ExternalLawyer1.selectByIndex(1);
					     List<WebElement> ExternalLawyer2= driver.findElements(By.xpath("//*[@id='ddlLayerType_chosen']/div/ul/li"));
					     int op = ExternalLawyer2.size();
//					      int size = op.size();
					     if(op>=1) 
					     {
					    	 ExternalLawyer2.get(opp).click();
					        
			
					    Thread.sleep(3000);
					    performerPOM.selectExternalLawyerRating(driver);
					   Thread.sleep(3000);
					   performerPOM.clickNewCriteria(driver).click();
					   Thread.sleep(3000);
					   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeLayerRatingCriteria"));
					   performerPOM.clickCriteria(driver).sendKeys("Test Test New");
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
					   }
					   else
					   {
						   test.log(LogStatus.PASS, "Case Closed");
					   }
					      
			  }	   
		   
	      static void Auditlog(WebDriver driver,ExtentTest test) throws InterruptedException
	      {
	    	  WebDriverWait wait=new WebDriverWait(driver,50);
	    	  driver.switchTo().parentFrame();
	          Thread.sleep(3000);
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
					   Thread.sleep(3000);
					   performerPOM. clickAuditLog(driver).click();
					   Thread.sleep(3000);
					   performerPOM.clickExport(driver).click();		   
					   Thread.sleep(3000);
					   driver.switchTo().parentFrame();
					   
					   test.log(LogStatus.PASS,"Export report download sucssesfully ");
					   performerPOM.clickclosebutton(driver).click();
	      }	 
	      public static void CaseOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	  	{
	  		int sheetNo = 9;
	  	    if(login.equalsIgnoreCase("Performer"))
	  	    {
	  	    	sheetNo = 9;
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
	  			caseOpen = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());
	  			
	  			performerPOM.clickNoticeOpen(driver).click();									//Clicking on 'Open' notice
	  		}
	  		else if(type.equals("Case"))
	  		{
	  			open = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());			//Reading Case Open count.
	  			closed = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());		//Reading Case Closed count.
	  			
	  			performerPOM.clickCaseOpen(driver).click();										//Clicking on 'Open' case
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
	  		//js.executeScript("arguments[0].scrollIntoView();", elementsList.get(0));
	  		
	  		Thread.sleep(500);
	  		elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
	  		elementsList.get(0).click();								//Clicking on first action button.
	  		
	  		Thread.sleep(500);
	  		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Waiting and switching to IFrame
	  		
	  		Thread.sleep(300);
	  		if(type.equals("Notice"))
	  		{
	  			sheet = workbook.getSheetAt(1);
	  			
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
	  			Row r1 = sheet.getRow(40);
	  			Cell c1 = r1.getCell(1);
	  			String remark = c1.getStringCellValue();
	  			performerPOM.clickRemark1(driver).sendKeys(remark);
	  			
	  			Thread.sleep(300);
	  			r1 = sheet.getRow(41);
	  			c1 = r1.getCell(1);
	  			String CaseNo = c1.getStringCellValue();
	  			performerPOM.clickCourtCaseNo(driver).sendKeys(CaseNo);
	  			
	  			Thread.sleep(300);
	  			performerPOM.clickSaveConvertCase(driver).click();	
	  			
//	  			Thread.sleep(300);
//	  		Row r1 = sheet.getRow(25);
//	  			Cell c1 = r1.getCell(1);
//	  			String remark = c1.getStringCellValue();
//	  			performerPOM.clickRemark1(driver).sendKeys(remark);
//	  			
//	  			Thread.sleep(300);
//	  			r1 = sheet.getRow(26);
//	  			c1 = r1.getCell(1);
//	  			String CaseNo = c1.getStringCellValue();
//	  			performerPOM.clickCourtCaseNo(driver).sendKeys(CaseNo);
//	  			
//	  			Thread.sleep(300);
//	  			performerPOM.clickSaveConvertCase(driver).click();
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
	  		
	  		Thread.sleep(3000);
	  		wait.until(ExpectedConditions.visibilityOf(performerPOM.readMessage2(driver)));
	  		String msg = performerPOM.readMessage2(driver).getText();
	  		
	  		if(msg.contains("Successfully"))
	  		{
	  			test.log(LogStatus.PASS, "Message displayed - "+msg);
	  		}
	  		else if(msg.contains("already exist"))
	  		{
	  			test.log(LogStatus.SKIP, "Message displayed - "+msg);
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
	  			caseOpen1 = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());
	  			
	  			if(open > open1 && closed1 > closed && caseOpen1 > caseOpen)
	  			{
	  				test.log(LogStatus.PASS, "Notice-Closed count increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
	  				test.log(LogStatus.PASS, "Notice-Open count decreased.");
	  				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
	  				test.log(LogStatus.PASS, "Case-Open count increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+caseOpen+" | New Count = "+caseOpen1);
	  			}
	  			else
	  			{
	  				test.log(LogStatus.FAIL, "Notice-Closed count doesn't increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
	  				test.log(LogStatus.FAIL, "Notice-Open count doesn't decreased.");
	  				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
	  				test.log(LogStatus.FAIL, "Case-Open count doesn't increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+caseOpen+" | New Count = "+caseOpen1);
	  			}
	  		}
	  		else if(type.equals("Case"))
	  		{
	  			open1 = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());			//Reading Case Open count.
	  			closed1 = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());		//Reading Case Closed count.
	  			
	  			if(open > open1 && closed1 > closed)
	  			{
	  				test.log(LogStatus.PASS, "Case-Closed count increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
	  				test.log(LogStatus.PASS, "Case-Open count decreased.");
	  				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
	  			}
	  			else
	  			{
	  				test.log(LogStatus.FAIL, "Case-Closed count doesn't increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
	  				test.log(LogStatus.FAIL, "Case-Open count doesn't decreased.");
	  				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
	  			}
	  		}
	  		else if(type.equals("Task"))
	  		{
	  			open1 = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());			//Reading Task Open count.
	  			closed1 = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());		//Reading Task Closed count.
	  			
	  			if(open > open1 && closed1 > closed)
	  			{
	  				test.log(LogStatus.PASS, "Task-Closed count increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
	  				test.log(LogStatus.PASS, "Task-Open count decreased.");
	  				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
	  			}
	  			else
	  			{
	  				test.log(LogStatus.PASS, "Task-Closed count doesn't increased.");
	  				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
	  				test.log(LogStatus.PASS, "Task-Open count doesn't decreased.");
	  				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
	  			}
	  		}
	  	}
	      public static void LinkDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
	  	{
	  		WebDriverWait wait = new WebDriverWait(driver, 180);
	  		progress(driver);
	  		
	  		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
	  		if(type.equals("Notice"))
	  		{
	  			performerPOM.clickNoticeOpen(driver).click();							//Clicking on 'Open' notice
	  		}
	  		else if(type.equals("Case"))
	  		{
	  			performerPOM.clickCaseOpen(driver).click();								//Clicking on 'Open' case
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
	  		//js.executeScript("arguments[0].scrollIntoView();", elementsList.get(0));
	  		
	  		Thread.sleep(3000);
	  		elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
	  		elementsList.get(0).click();								//Clicking on first action button.
	  		
	  		String refNo = null;
	  		Thread.sleep(3000);
	  		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Waiting and switching to IFrame		
	  		if(type.equals("Notice"))
	  		{
	  			Thread.sleep(3000);
	  			performerPOM.clickLinkNotice(driver).click();			//Clicking on Link Notice icon
	  			
	  			Thread.sleep(300);
	  			progress(driver);
	  			
	  			Thread.sleep(300);
	  			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCheckBox(driver)));	//Waiting for Checkbox to get visible.
	  			refNo = performerPOM.readRef(driver).getText();			//Reading ref no.
	  			
	  			Thread.sleep(3000);
	  			performerPOM.clickCheckBox(driver).click();			//CLicking on first checkbox
	  		}
	  		else if(type.equals("Case"))
	  		{
	  			Thread.sleep(300);
	  			performerPOM.clickLinkCase(driver).click();			//Clicking on Link Notice icon
	  			
	  			Thread.sleep(300);
	  			progress(driver);
	  			
	  			Thread.sleep(300);
	  			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCaseCheckBox(driver)));	//Waiting for Checkbox to get visible.
	  			refNo = performerPOM.readCaseRef(driver).getText();			//Reading ref no.
	  			
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
	  		
	  		int flag = 0;
	  		int n = 0;
	  		if(type.equals("Notice"))
	  		{
	  			performerPOM.clickClosePopup(driver).click();
	  			
	  			Thread.sleep(300);
	  			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
	  			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
	  			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
	  			
	  			Thread.sleep(300);
	  			elementsList = performerPOM.readRef1(driver);
	  			n = elementsList.size();
	  			
	  			if(n > 0)
	  			{
	  				for(int i = 0; i < n; i++)
	  				{
	  					String ref = elementsList.get(i).getText();
	  					if(refNo.equalsIgnoreCase(ref))
	  					{
	  						flag = 1;
	  						break;
	  					}
	  				}
	  			}
	  		}
	  		else if(type.equals("Case"))
	  		{
	  			performerPOM.clickClosePopupCase(driver).click();
	  			
	  			Thread.sleep(300);
	  			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
	  			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
	  			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
	  			
	  			Thread.sleep(300);
	  			elementsList = performerPOM.readCaseRef1(driver);
	  			n = elementsList.size();
	  			
	  			if(n > 0)
	  			{
	  				for(int i = 0; i < n; i++)
	  				{
	  					String ref = elementsList.get(i).getText();
	  					if(refNo.equalsIgnoreCase(ref))
	  					{
	  						flag = 1;
	  						break;
	  					}
	  				}
	  			}
	  		}
	  		
	  		if(flag == 1)
	  		{
	  			test.log(LogStatus.PASS, "Linked "+type+" displayed in "+type+" Summary. Reference No = "+refNo);
	  		}
	  		else
	  		{
	  			test.log(LogStatus.FAIL, "Linked "+type+" doesn't displayed in "+type+" Summary. Reference No = "+refNo);
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
	      public static void NoticeClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	  	{
	  		WebDriverWait wait = new WebDriverWait(driver, 50);
	  		
	  		CountExcel(driver, test, "Notice - Closed");
	  		
	  		Thread.sleep(500);
	  		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNew(driver)));
	  		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
	  	}
	      public static void CaseClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	  	{
	  		WebDriverWait wait = new WebDriverWait(driver, 50);
	  		
	  		CountExcel(driver, test, "Case - Closed");
	  		
	  		Thread.sleep(1000);
	  		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNew(driver)));
	  		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
	  	}
	  	public static void TaskOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
		{
			int sheetNo = 9;
		    if(login.equalsIgnoreCase("Performer"))
		    {
		    	sheetNo = 9;
		    }
		    
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
//			Thread.sleep(300);
//			performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
//			OverduePOM.selectNextMonth(driver).click();
//			OverduePOM.selectDate(driver).click();					//Selecting particular date.
//			
			Thread.sleep(3000);
			Row row0 = sheet.getRow(29);								//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
			String title = c1.getStringCellValue();
			performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(30);									//Selected 0th index row (First row)
			c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
			String desc = c1.getStringCellValue();
			performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
			
			Thread.sleep(3000);
			performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
			OverduePOM.selectNextMonth(driver).click();
			OverduePOM.selectDate(driver).click();					//Selecting particular date.
			
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
			
			Thread.sleep(3000);
			row0 = sheet.getRow(31);									//Selected 0th index row (First row)
			c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
			String outcome = c1.getStringCellValue();
			performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
			
			Thread.sleep(3000);
			row0 = sheet.getRow(32);									//Selected 0th index row (First row)
			c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
			String internalUser = c1.getStringCellValue();
			performerPOM.clickInternalUser1(driver).click();
			performerPOM.clickSearchInternalUser1(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
			
			Thread.sleep(1000);
			row0 = sheet.getRow(33);									//Selected 0th index row (First row)
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
			row0 = sheet.getRow(34);									//Selected 0th index row (First row)
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
			if(msg.contains("Task Saved Successfully."))
			{
				test.log(LogStatus.PASS, "Task Saved Successfully.");
			}
			else
			{
				test.log(LogStatus.FAIL, "Task didn't saved successfully.");
			}
			
			driver.switchTo().parentFrame();
			performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
			
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));
			
			Thread.sleep(300);
			performerPOM.clickStatusDropDown(driver).click();		//Clicking on 'Status drop down.
			Thread.sleep(500);
			//performerPOM.selectStatusDropDown(driver).click();		//Selecting 'Pending/Open' status
			
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
				test.log(LogStatus.PASS, "Total Task Count increased in grid after adding New Task.");
				test.log(LogStatus.INFO, "Old Task Count from Grid = "+gridRecords+" | New Task Count from Grid = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Total Task Count doesn't increased in grid after adding New Task.");
				test.log(LogStatus.INFO, "Old Task Count from Grid = "+gridRecords+" | New Task Count from Grid = "+count1);
			}
			
			Thread.sleep(500);
			OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
			
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickTaskOpen(driver)));
			int open1 = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());	//Reading Notice Open count.
			
			if(open1 > open)
			{
				test.log(LogStatus.PASS, type+" Dashboard Count Increased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
			}
			else
			{
				test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
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
		 public static void NoticeDocViewandDownload(WebDriver driver, ExtentTest test) throws InterruptedException, IOException
         {    
	  
	          WebDriverWait wait=new WebDriverWait(driver,20);
	          Thread.sleep(3000);
	          performerPOM.clickNoticeOpen(driver).click();
	   
	          Thread.sleep(1000);
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
	  
	         
             driver.switchTo().frame("IframeNoticeDocument");
	  
	   
//	          Thread.sleep(5000);
//	          performerPOM.clickViewNoticeDocpopup(driver).click();
//	  
//	 
//	           //driver.switchTo().parentFrame();
//	  
//	         Thread.sleep(3000);
//	          performerPOM.clickViewNoticeDocpopupclose1(driver).click();
	  
	  
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
		 public static void MyDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
			{
				WebDriverWait wait = new WebDriverWait(driver, 60);
				progress(driver);
				
				//wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
				performerPOM.clickMyDocument(driver).click();					//Clicking on 'My Document'
				performerPOM.clickmyDocument(driver).click();	                    //Clicking on 'My Document'
				
				

				Thread.sleep(3000);
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
				}
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
				
				//--------------------------------Case----------------------------------
				       Thread.sleep(4000);
				       performerPOM.clickDownloadDocument(driver).click();	
				       Thread.sleep(4000);
				       performerPOM.clickViewDocument(driver).click();	
				       Thread.sleep(3000);
				       performerPOM.clickcloseViewDocument(driver).click();
					
				       Thread.sleep(3000);
				       test.log(LogStatus.PASS, "Document  View Successfully.");
				       test.log(LogStatus.PASS, "Document  Downloaded Successfully.");
						
						//driver.navigate().refresh();
			
				//--------------------------------Notice----------------------------------
		 
				       Thread.sleep(4000);
					    JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(500,0)");
						Thread.sleep(4000);
						performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(4000);
						performerPOM.selectTypeCase(driver).click();					//Selecting 'Case' option.
						 Thread.sleep(4000);
					       performerPOM.clickDownloadDocument(driver).click();	
					       Thread.sleep(4000);
					       performerPOM.clickViewDocument(driver).click();	
					       Thread.sleep(4000);
					       performerPOM.clickcloseViewDocument(driver).click();
					       
					       Thread.sleep(3000);
					       test.log(LogStatus.PASS, "Document view Successfully.");
					       test.log(LogStatus.PASS, "Document Downloaded Successfully.");
							//driver.navigate().refresh();
										
		          ////--------------------------------Task----------------------------------
						
					    
						Thread.sleep(5000);
						performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(5000);
						performerPOM.selectTypeTask(driver).click();					//Selecting 'Task' option.
						
//						 Thread.sleep(4000);
//					     performerPOM.clickDownloadDocument(driver).click();	
					     Thread.sleep(5000);
					     performerPOM.clickViewDocument(driver).click();	
					     Thread.sleep(3000);
					     performerPOM.clickcloseViewDocument(driver).click();

					     Thread.sleep(1000);
					     test.log(LogStatus.PASS, "Document view Successfully.");
					   //  test.log(LogStatus.PASS, "Document  Downloaded Successfully.");
					     
					     driver.navigate().refresh();
					       
					       Thread.sleep(1000);
						   OverduePOM.clickDashboard(driver).click();				//Clicking on 'My Dashboard'
					     
					    
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
				performerPOM.startDate(driver).sendKeys("05/01/2022");
				
				Thread.sleep(4000);
				performerPOM.endDate(driver).sendKeys("05/07/2022");
				
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
					
					
					Thread.sleep(4000);
					performerPOM.clickTypeDropdown1(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
					Thread.sleep(4000);
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
		  public static void AdvancedSearchDocument(WebDriver driver, ExtentTest test,String login) throws InterruptedException, IOException
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
				       test.log(LogStatus.PASS, "Advanced Search-Document  View Successfully.");
				       test.log(LogStatus.PASS, "Advanced Search-Document  Downloaded Successfully.");
						
					
			
						//--------------------------------Notice----------------------------------
		 
						
						Thread.sleep(3000);
						performerPOM.clickTypeDropdown3(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(3000);
						performerPOM.selectTypeCase2(driver).click();					//Selecting 'Case' option.
						 Thread.sleep(3000);
					       performerPOM.clickDownloadDocument1(driver).click();	
					       Thread.sleep(3000);
					       performerPOM.clickViewDocument1(driver).click();	
					       Thread.sleep(3000);
					       performerPOM.clickcloseViewDocument1(driver).click();
					       
					       Thread.sleep(3000);
					       test.log(LogStatus.PASS, "Advanced Search-Document view Successfully.");
					       test.log(LogStatus.PASS, "Advanced Search-Document Downloaded Successfully.");
							
										
		               ////--------------------------------Task----------------------------------
						
					   
						Thread.sleep(3000);
						performerPOM.clickTypeDropdown3(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
						Thread.sleep(3000);
						performerPOM.selectTypeTask2(driver).click();					//Selecting 'Task' option.
						
						
						 Thread.sleep(3000);
					     performerPOM.clickViewDocument1(driver).click();	
					     Thread.sleep(3000);
					     performerPOM.clickcloseViewDocument1(driver).click();
					     
					     test.log(LogStatus.PASS, "Advanced Search-Document view Successfully.");
						
						 Thread.sleep(3000);
					     performerPOM.clickDownloadDocument1(driver).click();	
					     
					      try 
					      {      Thread.sleep(2000);
								String msg = driver.switchTo().alert().getText();
								Thread.sleep(2000);
								driver.switchTo().alert().accept();							//Clicking on OK of alert.
								test.log(LogStatus.PASS, "Message displayed -:- " + msg);
										
							}
						catch(Exception e)
								{
									test.log(LogStatus.PASS, "Advanced Search-Document  Downloaded Successfully.");	
								}
					       
				         driver.navigate().refresh();
				       
				       Thread.sleep(1000);
					   OverduePOM.clickDashboard(driver).click();				//Clicking on 'My Dashboard'
		}

		  public static void MyReports(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
			{
				WebDriverWait wait = new WebDriverWait(driver, 60);
				progress(driver);
				
				wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
				performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
				
				
				Thread.sleep(3000);
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
				}
				
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
				
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

				Thread.sleep(10000);
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
			static void Report(WebDriver driver, ExtentTest test, int count1, String type) throws InterruptedException, IOException
			{
				Thread.sleep(700);
				File dir = new File("C://Users//Admin//Downloads");
				File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
				
				//Thread.sleep(500);
				//CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
				Thread.sleep(300);
				performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(6000);
				File dir1 = new File("C://Users//Admin//Downloads");
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
						test.log(LogStatus.PASS, type+" - No of records displayed matches to no of records in Excel Sheet.");
						test.log(LogStatus.INFO, "Total records displayed = "+count1+". Total records in Excel sheet = "+SheetRecords);
					}
					else
					{
						test.log(LogStatus.FAIL, type+" - No of records displayed doesn't matches to no of records in Excel Sheet.");
						test.log(LogStatus.INFO, "Total records displayed = "+count1+". Total records in Excel sheet = "+SheetRecords);
					}
				}
				else
				{
					test.log(LogStatus.FAIL, type+" - File doesn't downloaded successfully.");
				}
			}
			public static void MoreReport(WebDriver driver, ExtentTest test, String type) throws InterruptedException
			{
				
				WebDriverWait wait = new WebDriverWait(driver, 180);
				
				Thread.sleep(3000);
				performerPOM.clickMyReports(driver).click();
				
				Thread.sleep(5000);
				performerPOM.clickMoreReports(driver).click();
				//--------------------------------Case Report------------------------------------------
//				Thread.sleep(3000);
//				performerPOM.clicklocationFilterReports(driver).click();
//				
//				Thread.sleep(3000);
//				performerPOM.selectlocationFilterReports(driver).click();
				
				Thread.sleep(3000);
				performerPOM.FromDateReports(driver).sendKeys("01-12-2022");
				
//				Thread.sleep(3000);
//				performerPOM.selectFromDate(driver).click();
				
				Thread.sleep(3000);
				performerPOM.ToDateReports(driver).sendKeys("21-12-2022");
				
//				Thread.sleep(3000);
//				performerPOM.selectToDate(driver).click();
				
				
				//--------------------------MIS Report------------------------------
				
				File dir = new File("C://Users//Admin//Downloads");
				File[] dirContents = dir.listFiles(); // Counting number of files in directory before download

				Thread.sleep(1000);
				performerPOM.MISReports(driver).click();      // Exporting (Downloading) file

				Thread.sleep(3000);
				File dir1 = new File("C://Users//Admin//Downloads");
				File[] allFilesNew = dir1.listFiles(); // Counting number of files in directory after download
				Thread.sleep(3000);
				if (dirContents.length < allFilesNew.length) {
					test.log(LogStatus.PASS,  "MIS Report downloaded successfully.");
				} else {
					test.log(LogStatus.INFO, " MIS Report doesn't downloaded successfully.");
				}
				
				
			    //--------------------------closed Cases Reports------------------------------
			
				File dir2 = new File("C://Users//Admin//Downloads");
				File[] dirContents1 = dir.listFiles(); // Counting number of files in directory before download

				Thread.sleep(1000);
				performerPOM.closedCasesReports(driver).click();      // Exporting (Downloading) file

				File dir3 = new File("C://Users//Admin//Downloads");
				File[] dirContents2 = dir.listFiles(); // Counting number of files in directory before download
				Thread.sleep(3000);
				if (dirContents.length < allFilesNew.length) {
					test.log(LogStatus.PASS,  "closed Cases Reports downloaded successfully.");
				} else {
					test.log(LogStatus.INFO, "closed Cases Reports downloaded successfully.");
				}
				
				
		
				
				//--------------------------Budget Reports-----------------------------------
				
				
				Thread.sleep(100);
				File dir6 = new File("C://Users//Admin//Downloads");
				File[] dirContents5 = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(250);
				performerPOM.BudgetReports(driver).click();					//Clicking on 'Excel Report' image.
				
				
				File dir7 = new File("C://Users//Admin//Downloads");
				File[] dirContents6 = dir.listFiles(); // Counting number of files in directory before download
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Budget Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Budget Reports Doesn't downloaded successfully.");
			     }
				
				
				
				//--------------------------Lawyer Details Reports------------------------------
				
				
				
				Thread.sleep(100);
				File dir8 = new File("C://Users//Admin//Downloads");
				File[] dirContents7 = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(250);
				performerPOM.LawyerDetailsReports(driver).click();					//Clicking on 'Excel Report' image.
				
				File dir9 = new File("C://Users//Admin//Downloads");
				File[] dirContents8 = dir.listFiles(); // Counting number of files in directory before download
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Lawyer Details Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Lawyer Details Reports Doesn't downloaded successfully.");
			     }
				
				//--------------------------Case Payment Reports------------------------------
				
				
				Thread.sleep(100);
				File dir10 = new File("C://Users//Admin//Downloads");
				File[] dirContents9 = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(250);
				performerPOM.CasePaymentReports(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(100);
				File dir11 = new File("C://Users//Admin//Downloads");
				File[] dirContents10 = dir.listFiles();							//Counting number of files in directory before download 
				
				 
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Case Payment Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Case Payment Reports Doesn't downloaded successfully.");
			     }

				
			//--------------------------Case Hearing Reports------------------------------
				
				
				Thread.sleep(100);
				File dir12 = new File("C://Users//Admin//Downloads");
				File[] dirContents11 = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(250);
				performerPOM.CaseHearingReports(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(100);
				File dir13 = new File("C://Users//Admin//Downloads");
				File[] dirContents12 = dir.listFiles();							//Counting number of files in directory before download 
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Case Hearing Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Case Hearing Reports Doesn't downloaded successfully.");
			     }

				
				//--------------------------CourtCaseReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir14 = new File("C://Users//Admin//Downloads");
		         File[] dirContents13 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.CourtCaseReports(driver).click();					//Clicking on 'Excel Report' image.
				

				 Thread.sleep(100);
				 File dir15 = new File("C://Users//Admin//Downloads");
		         File[] dirContents14 = dir.listFiles();							//Counting number of files in directory before download 
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Court Case Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Court Case Reports Doesn't downloaded successfully.");
			     }

				
				//--------------------------CourtOrderReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir16 = new File("C://Users//Admin//Downloads");
				File[] dirContents15 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.CourtOrderReports(driver).click();					//Clicking on 'Excel Report' image.
				

				 Thread.sleep(100);
				File dir17 = new File("C://Users//Admin//Downloads");
				File[] dirContents16 = dir.listFiles();							//Counting number of files in directory before download 
			
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Court Order Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Court Order Reports Doesn't downloaded successfully.");
			     }
				
				
				
				
				//-------------------------CourtDoumentReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir18 = new File("C://Users//Admin//Downloads");
				File[] dirContents17 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.CourtDoumentReports(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(100);
				File dir19 = new File("C://Users//Admin//Downloads");
				File[] dirContents18 = dir.listFiles();							//Counting number of files in directory before download 
					
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Court Doument Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Court Doument Reports Doesn't downloaded successfully.");
			     }
				
				
				
				
				//-------------------------noticeCovertedToCaseReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir20 = new File("C://Users//Admin//Downloads");
			     File[] dirContents19 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.noticeCovertedToCaseReports(driver).click();					//Clicking on 'Excel Report' image.
				
				 Thread.sleep(100);
				 File dir21 = new File("C://Users//Admin//Downloads");
			     File[] dirContents20 = dir.listFiles();							//Counting number of files in directory before download 
					
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "notice Coverted To Case Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "notice Coverted To Case Reports Doesn't downloaded successfully.");
			     }
			
				
				//-------------------------AllReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir22 = new File("C://Users//Admin//Downloads");
			     File[] dirContents21 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.AllReports(driver).click();					//Clicking on 'Excel Report' image.
				
				
				 Thread.sleep(100);
				 File dir23 = new File("C://Users//Admin//Downloads");
			     File[] dirContents22 = dir.listFiles();							//Counting number of files in directory before download 
					
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "All Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "All Reports Doesn't downloaded successfully.");
			     }
			
				
			
				//----------------------------------------Notice Report------------------------------------------------
				
				Thread.sleep(3000);
				performerPOM.clickNoticeReport(driver).click();
				
//				
//				Thread.sleep(3000);
//				performerPOM.clicklocationFilterReports(driver).click();
//				
//				Thread.sleep(3000);
//				performerPOM.selectlocationFilterReports(driver).click();
				
				Thread.sleep(3000);
				performerPOM.FromDateReports(driver).sendKeys("01-12-2022");
				
//				Thread.sleep(3000);
//				performerPOM.selectFromDate(driver).click();
				
				Thread.sleep(3000);
				performerPOM.ToDateReports(driver).sendKeys("21-12-2022");
				
				//------------------------MISReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir24 = new File("C://Users//Admin//Downloads");
			     File[] dirContents23 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.MISReports(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(100);
				 File dir25 = new File("C://Users//Admin//Downloads");
			     File[] dirContents24 = dir.listFiles();							//Counting number of files in directory before download 
				
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "MIS Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "MIS Reports Doesn't downloaded successfully.");
			     }
				
				
				//------------------------closedCasesReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir26 = new File("C://Users//Admin//Downloads");
			     File[] dirContents25 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.closedCasesReports(driver).click();					//Clicking on 'Excel Report' image.
				
				 Thread.sleep(100);
				 File dir27 = new File("C://Users//Admin//Downloads");
			     File[] dirContents26 = dir.listFiles();							//Counting number of files in directory before download 
				
				
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "closed Cases Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "closed Cases Reports Doesn't downloaded successfully.");
			     }
				
			
				//------------------------MISReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir28 = new File("C://Users//Admin//Downloads");
			     File[] dirContents27 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.MISReports(driver).click();					//Clicking on 'Excel Report' image.
				
				 Thread.sleep(100);
				 File dir29 = new File("C://Users//Admin//Downloads");
			     File[] dirContents28 = dir.listFiles();							//Counting number of files in directory before download 
						
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "MIS All Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "MIS All Reports Doesn't downloaded successfully.");
			     }
				
				
				
				
				
				//------------------------BudgetReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir32 = new File("C://Users//Admin//Downloads");
			     File[] dirContents31 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.BudgetReports(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(100);
				 File dir33 = new File("C://Users//Admin//Downloads");
			     File[] dirContents32 = dir.listFiles();							//Counting number of files in directory before download 
				
			
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Budget  Reports downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Budget  Reports Doesn't downloaded successfully.");
			     }
				
				
				
				
				//------------------------clickNoticePaymentReport------------------------------
				
				
				 Thread.sleep(100);
				 File dir34 = new File("C://Users//Admin//Downloads");
			     File[] dirContents33 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.LawyerDetailsReports(driver).click();					//Clicking on 'Excel Report' image.
				
				 Thread.sleep(100);
				 File dir35 = new File("C://Users//Admin//Downloads");
			     File[] dirContents34 = dir.listFiles();							//Counting number of files in directory before download
				
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Lawyer Details downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Lawyer Details Doesn't downloaded successfully.");
			     }
				
				
				//------------------------clickNoticePaymentReport------------------------------
				
				
				 Thread.sleep(100);
				 File dir36 = new File("C://Users//Admin//Downloads");
			     File[] dirContents35 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.clickNoticePaymentReport(driver).click();					//Clicking on 'Excel Report' image.
				
				Thread.sleep(100);
				 File dir37 = new File("C://Users//Admin//Downloads");
			     File[] dirContents36 = dir.listFiles();							//Counting number of files in directory before download 
			     
				test.log(LogStatus.PASS, "Notice Payment Report downloaded successfully.");
				

				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Notice Payment downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Notice Payment Doesn't downloaded successfully.");
			     }
				
				
				
				//------------------------clickNoticeResponseReport------------------------------
				
				
				 Thread.sleep(100);
				 File dir38 = new File("C://Users//Admin//Downloads");
			     File[] dirContents37 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.clickNoticeResponseReport(driver).click();					//Clicking on 'Excel Report' image.
				

				 Thread.sleep(100);
				 File dir39 = new File("C://Users//Admin//Downloads");
			     File[] dirContents38 = dir.listFiles();							//Counting number of files in directory before download 
				
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "Notice Response Report downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "Notice Response Report Doesn't downloaded successfully.");
			     }
				
				
				//-------------------------AllReports------------------------------
				
				
				 Thread.sleep(100);
				 File dir40 = new File("C://Users//Admin//Downloads");
			     File[] dirContents39 = dir.listFiles();							//Counting number of files in directory before download 
						
				Thread.sleep(250);
				performerPOM.AllReports(driver).click();					//Clicking on 'Excel Report' image.
				
				 Thread.sleep(100);
				 File dir41 = new File("C://Users//Admin//Downloads");
			     File[] dirContents40 = dir.listFiles();							//Counting number of files in directory before download 
				
				
				
				
				if (dirContents.length < allFilesNew.length) 
				{
				       test.log(LogStatus.PASS, "All Report downloaded successfully.");
				}
			   else 
			   {
				       test.log(LogStatus.INFO, "All Report Doesn't downloaded successfully.");
			     }
				
				
			}
			public static void MyReminder(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
			{
				WebDriverWait wait = new WebDriverWait(driver, 180);
				progress(driver);
				
				wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
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
				performerPOM.clickReminderText(driver).sendKeys("Automation Reminder Message new.");
				
				Thread.sleep(3000);
				performerPOM.clickDescription(driver).sendKeys("Automation Reminder Message new.");
				
				Thread.sleep(3000);
				performerPOM.clickRemark2(driver).sendKeys("Automation reminder remark new.");
				
				Thread.sleep(3000);
				performerPOM.clickDate(driver).click();
				Thread.sleep(3000);
				OverduePOM.selectNextMonth(driver).click();
				OverduePOM.selectDate(driver).click();
				
				Thread.sleep(3000);
				performerPOM.clickSave(driver).click();				//Clicking on Save button.
				
				Thread.sleep(500);
				try
				{
					wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg1(driver)));
				}
				catch(Exception e)
				{
					wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg1(driver)));
				}
				Thread.sleep(3000);
				String msg = performerPOM.readMsg1(driver).getText();

				
				if(msg.equalsIgnoreCase("Reminder Saved Successfully."))
				{
					test.log(LogStatus.PASS, "Message displayed = "+msg);
				
				}
				else
				{
					test.log(LogStatus.FAIL, "Message displayed = "+msg);
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
				else if(type.equalsIgnoreCase("Task"))
				{
					action1.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
				}
				
				Thread.sleep(2000);
				action1.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
				
				Thread.sleep(3000);
				performerPOM.clickReminderText(driver).clear();
				
				Thread.sleep(3000);
				performerPOM.clickReminderText(driver).sendKeys("Automation Reminder Message new");
				
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
				
				Thread.sleep(300);
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
			public static void AdvancedSearchReport(WebDriver driver,ExtentTest test, String type) throws InterruptedException
			{
				WebDriverWait wait=new WebDriverWait(driver,180);
				
				Thread.sleep(2000);
		        performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
		        
		        
//		        Thread.sleep(500);
//		        performerPOM.clickExcelReport1(driver).click();
//		        test.log(LogStatus.PASS, "Usage Report downloaded successfully.");
				
				Thread.sleep(5000);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
				
				Thread.sleep(5000);
				
				performerPOM.AdvancedSearchReports(driver).click();
				
			//-------------------------------------------Notice--------------------------------------------------
				
				Thread.sleep(3000);
				performerPOM.startDate(driver).sendKeys("05/01/2022");
				
				Thread.sleep(3000);
				performerPOM.endDate(driver).sendKeys("05/05/2022");
				
				Thread.sleep(3000);
				performerPOM.clickApplyButton(driver).click();
				
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
				
				Thread.sleep(3000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("document.querySelector(\"div[id='grid1'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=2000");
				
				
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
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("document.querySelector(\"div[id='grid1'] div[class='k-grid-content k-auto-scrollable']\").scrollLeft=2000");
			
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
		 	public static void AdvocateBillTab(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
	    	{
	      		WebDriverWait wait=new WebDriverWait(driver,20);
	    	     Thread.sleep(3000);
	      		performerPOM.clickAdvocateBillTab(driver).click();
	      		 Thread.sleep(3000);
	      		performerPOM.clickAdvocateBillTabViewIcon(driver).click();
	      		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
	      		Thread.sleep(2000);
	      		performerPOM.clickAdvocateBillTabAuditLog(driver).click();
	      		driver.switchTo().parentFrame();
	      		Thread.sleep(2000);
	      		performerPOM.clickAdvocateBillTabclose(driver).click();
	      		Thread.sleep(2000);
	      		performerPOM.clickAdvocateBillTabTriangle1(driver).click();
	      		Thread.sleep(2000);
	      		performerPOM.clearButton(driver).click();

	      		
				Thread.sleep(10000);
				CFOcountPOM.readTotalItems1(driver).click();
				String item1 = CFOcountPOM.readTotalItems1(driver).getText();
				String[] bits1 = item1.split(" ");								//Splitting the String
				String compliancesCount1 = bits1[bits1.length - 2];				//Getting the second last word (total number of users)
				int count2 = Integer.parseInt(compliancesCount1);
				
			    try
				{
					performerPOM.clickExportAdavanced(driver).sendKeys(Keys.PAGE_DOWN);
				}
				catch(Exception e)
				{
					
				}
			
			
				Thread.sleep(100);
				File dir = new File("C://Users//Admin//Downloads");
				File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
				
				Thread.sleep(500);
				CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
				Thread.sleep(250);
				performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
				test.log(LogStatus.PASS, "File downloaded successfully.");
				
				Thread.sleep(5500);
				File dir1 = new File("C://Users//Admin//Downloads");
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
						test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
						test.log(LogStatus.INFO, "Total records from Grid = "+count2+" | Total records from Report = "+records);
					}
					else
					{
						test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
						test.log(LogStatus.INFO, "Total records from Grid = "+count2+" | Total records from Excel Sheet = "+records);
					}
				}
				
				
	    		
	    	}
		 	
		 	public static void ApproverAssignmentLog(WebDriver driver,ExtentTest test) throws InterruptedException, IOException
	    	{
			
				WebDriverWait wait=new WebDriverWait(driver,20);
	    	     Thread.sleep(3000);
	      		performerPOM.clickAdvocateBillTab(driver).click();
			Thread.sleep(3000);
      		performerPOM.clickApproverAssignmentLog(driver).click();
      		

		
		
			Thread.sleep(100);
			File dir2 = new File("C://Users//Admin//Downloads");
			File[] dirContents1 = dir2.listFiles();							//Counting number of files in directory before download 

			Thread.sleep(250);
			performerPOM.clickExportAdavanced(driver).click();					//Clicking on 'Excel Report' image.
			
			
			Thread.sleep(5500);
			File dir3 = new File("C://Users//Admin//Downloads");
			File[] allFilesNew1 = dir3.listFiles();							//Counting number of files in directory after download
			
			if(dirContents1.length < allFilesNew1.length)
			{
				test.log(LogStatus.PASS, "Approver Assignment Log - File downloaded successfully.");
				
				File lastModifiedFile = allFilesNew1[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
			    for (int i = 1; i < allFilesNew1.length; i++) 	//For loop till the number of files in directory.
			    {
			       if (lastModifiedFile.lastModified() < allFilesNew1[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
			       {
			           lastModifiedFile = allFilesNew1[i];
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
				
				Thread.sleep(3000);
	    		performerPOM.clickExportAdavanced(driver).sendKeys(Keys.PAGE_DOWN);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("window.scrollBy(0,700)");
	      		
	      		
	      		
				
				Thread.sleep(3000);
				CFOcountPOM.readTotalItems2(driver).click();
				String item = CFOcountPOM.readTotalItems2(driver).getText();
				String[] bits = item.split(" ");								//Splitting the String
				String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
				int count = Integer.parseInt(compliancesCount);
				
				if(count == records)
				{
					test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
					test.log(LogStatus.INFO, "Total records from Grid = "+count+" | Total records from Report = "+records);
				}
				else
				{
					test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
					test.log(LogStatus.INFO, "Total records from Grid = "+count+" | Total records from Excel Sheet = "+records);
				}
			}
      		
      		
      		Thread.sleep(500);
      		OverduePOM.clickDashboard(driver).click();
      		
	    	}		
		 	
		 	
		 	  public static void WorkspaceFilter(WebDriver driver,ExtentTest test, String type) throws InterruptedException
		      	{
		      		WebDriverWait wait=new WebDriverWait(driver,20);
		      		Thread.sleep(5000);
		      		performerPOM.clickMyWorkspace(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickCaseNotice1(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clicklocationFilter(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickLocationFilter1(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clicklocationFilter3(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickCalenderYear2(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickCalenderYear3(driver).click();
		      		
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickDepartmentFilter1(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickDepartmentFilter3(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickFinancialYear2(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickFinancialYear3(driver).click();
		      		
		      		
		           	Thread.sleep(3000);
		      		performerPOM.clickstatus(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickstatus1(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickcategory(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickcategory1(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickType1(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clicktype2(driver).click();
		      		
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
		      		performerPOM.clickTaskPriorityFilter2(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickTaskStatusFilter(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.clickTaskStatusFilter2(driver).click();
		      		
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
		      		performerPOM.clickSearchFilter(driver).sendKeys("Case for Tax");
		      		
		      		
		      		Thread.sleep(3000);
		      		performerPOM.CaseHearingView(driver).click();
		      		
		      		Thread.sleep(3000);
		      		performerPOM.CaseHearingPopupClose(driver).click();
		      		
		      		Thread.sleep(3000);
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
		    		
//		    		Thread.sleep(3000);
//		    		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
//		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocStatusFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocStatusFilter1(driver).click();
		    		
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
		    		
//		    		Thread.sleep(3000);
//		    		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		    		
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
		    		
		    		test.log(LogStatus.PASS, "My Document = Notice Filters Work Successfully");
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocDropdownFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.selectTypeTask(driver).click();
		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickDocStatusFilter(driver).click();
//		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickReportStatusFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocTaskPriorityFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocTaskPriorityFilter3(driver).click();
		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickDocLocFilter(driver).click();
//		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickLocationFilter1(driver).click();
//		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickDocLocFilter2(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocTaskFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickDocTaskFilter1(driver).click();
		    		
		    		
		    		
		    		
		    		Thread.sleep(3000);
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
		    		
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportStatusFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportStatusFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportDeptFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportDeptFilter2(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportTypeFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportTypeFilter2(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportCategoryFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportCategoryFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportLocFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickLocationFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportLocFilter2(driver).click();
		    		
//		    		Thread.sleep(5000);
//		    		performerPOM.clickReportFYFilter(driver).click();
//		    		
//		    		Thread.sleep(5000);
//		    		performerPOM.clickReportFYFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportCYFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportCYFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clearButton(driver).click();
		    		test.log(LogStatus.PASS,"My Report = Notice Filter Work successfully");
		    		
		    		Thread.sleep(3000);
		    		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table 
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		    		
		    		Thread.sleep(3000);
		    		performerPOM.selectTypeCase(driver).click();	
		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickReportStatusFilter(driver).click();
//		    		
//		    		Thread.sleep(3000);
//		    		performerPOM.clickReportStatusFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportDeptFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportDeptFilter3(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportTypeFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportTypeFilter2(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportCategoryFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportCategoryFilter2(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportLocFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickLocationFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickLocationFilter4(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportFYFilter(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickReportFYFilter1(driver).click();
		    		
//		    		Thread.sleep(5000);
//		    		performerPOM.clickReportCYFilter(driver).click();
//		    		
//		    		Thread.sleep(5000);
//		    		performerPOM.clickReportCYFilter1(driver).click();
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clearButton(driver).click();
		    		test.log(LogStatus.PASS,"My Report =Case Filter Work successfully");
		    		
		    		
		    		Thread.sleep(3000);
		    		performerPOM.clickTypeDropdown(driver).click();	
		    		
		    		Thread.sleep(3000);
		    		performerPOM.selectTypeTask(driver).click();
		    		
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
	  	
			

}
