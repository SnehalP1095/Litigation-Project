package CriticalTestCases;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import litigationAdditionalOwner.MethodsPOM;
import litigationAdditionalOwner.performerPOM;
import litigationManagement.CFOMethod;
import performer.OverduePOM;

public class CompanyAdminLogin

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
			//String workingDir = System.getProperty("user.dir");
			fis = new FileInputStream("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\LitigationSheet.xlsx");
		
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(4);					//Retrieving second sheet of Workbook
			return sheet;
		}
		
		@BeforeTest
		void setBrowser() throws InterruptedException, IOException
		{
			String workingDir = System.getProperty("user.dir");
			extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LitigationCompanyAdmin.html",true);
			test = extent.startTest("Litigation Logging In - Company Admin");
			test.log(LogStatus.PASS, "Test Passed = Verify Chrome browser.");
			extent.endTest(test);
			extent.flush();
		}
		
		@BeforeMethod
		
		void Login() throws InterruptedException, IOException
		{
			
			XSSFSheet sheet = ReadExcel();
			Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
			
			login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
			
			
			Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
			Cell c2 = row1.getCell(1);						//Selected cell (1 row,1 column)
			String uname = c2.getStringCellValue();			//Got the URL stored at position 1,1
			
			Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
			Cell c3 = row2.getCell(1);						//Selected cell (2 row,1 column)
			String password = c3.getStringCellValue();		//Got the URL stored at position 2,1
			
			driver = login.Login.UserLogin(uname,password,"company");		//Method of Login class to login user.
			
		}

		
//	@Test(priority = 0)
		void HearingCalender() throws InterruptedException, IOException, AWTException
		{
			test = extent.startTest("Case Hearing Calender Verification");
			
			
			MethodsPOM.HearingCalender(driver, test,"Performer","Company admin");
			
			extent.endTest(test);
			extent.flush();
		}
		
@Test(priority = 1)
				void CaseNoticeTypeGraph() throws InterruptedException, IOException
				{
					test = extent.startTest("Select Notice Filter  = Case Notice Type Graph Count Verification");
					
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
			     	js.executeScript("window.scrollBy(0,850)");
			     	
			     	Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
					Thread.sleep(5000);
					performerPOM.clickDashboardNoticeFilter(driver).click();
		          
		           	
					 Thread.sleep(5000);
					 performerPOM.clickDashboardApplyBtn(driver).click();
					 Thread.sleep(5000);
			    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseNoticeTypeOutwardPlaintiff(driver).getText());	//Reading Notice Open count.
			    	int	InwardDefendent = Integer.parseInt(performerPOM.CaseNoticeTypeInwardDefendent(driver).getText());	//Reading Notice Open count.
			    	int	Petitioner = Integer.parseInt(performerPOM.CaseNoticeTypeComplinant(driver).getText());	//Reading Notice Open count.
			    	int	Respondent = Integer.parseInt(performerPOM.CaseNoticeTypeRespondent(driver).getText());	//Reading Notice Open count.
			    	
					
			    	Thread.sleep(3000);
			    	MethodsPOM.CaseNoticeTypeGraph(driver, test,"Outward/Plaintiff Type",OutwardPlaintiff);
					Thread.sleep(3000);
					MethodsPOM.CaseNoticeTypeGraph(driver, test,"Inward/Defendent Type",InwardDefendent);
					Thread.sleep(3000);
					MethodsPOM.CaseNoticeTypeGraph(driver, test,"Petitioner Type",Petitioner);
					Thread.sleep(3000);
					MethodsPOM.CaseNoticeTypeGraph(driver, test,"Respondent Type",Respondent);
					
					
					Thread.sleep(3000);
					OverduePOM.clickDashboard(driver).click();
					
					extent.endTest(test);
					extent.flush();
				}
				
		@Test(priority = 2)
				void CaseNoticeStageGraphNotice() throws InterruptedException, IOException
				{
				
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,800)");
			 
					Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
					Thread.sleep(5000);
					performerPOM.clickDashboardNoticeFilter(driver).click();
			  
			   	
					Thread.sleep(5000);
					performerPOM.clickDashboardApplyBtn(driver).click();

					js.executeScript("window.scrollBy(0,850)");
					
					String StageName =performerPOM.StageName(driver).getText();
					test = extent.startTest("Select Notice Filter = "+StageName+" Stage = Case Notice Stage Graph Count Verification");

					MethodsPOM.CaseNoticeStageGraph(driver, test,"Notice");
					
					extent.endTest(test);
					extent.flush();
				}
		@Test(priority =3)
				
				void RiskSummaryGraph() throws InterruptedException, IOException
				{
					test = extent.startTest("Select Notice Filter  =Risk Summary Graph Count Verification");
				    
				
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,800)");
					
				        	Thread.sleep(5000);
							performerPOM.clickDashboardCaseNoticeFilter(driver).click();
							
							Thread.sleep(5000);
							performerPOM.clickDashboardNoticeFilter(driver).click();
				          
				           	
							 Thread.sleep(5000);
							 performerPOM.clickDashboardApplyBtn(driver).click();
							 js.executeScript("window.scrollBy(0,900)");
							
							
						    int	HighRisk = Integer.parseInt(performerPOM.RiskSummaryHigh(driver).getText());	//Reading Notice Open count.
					    	int	MediumRisk = Integer.parseInt(performerPOM.RiskSummaryMedium(driver).getText());	//Reading Notice Open count.
					    	int	LowRisk = Integer.parseInt(performerPOM.RiskSummaryLowCA(driver).getText());	//Reading Notice Open count.
					    	int	NotApplicableRisk = Integer.parseInt(performerPOM.RiskSummaryNotApplicableCA(driver).getText());	//Reading Notice Open count.
					    	
							
					    	Thread.sleep(3000);
					    	MethodsPOM.RiskSummaryGraph(driver, test,"High Risk",HighRisk);
							Thread.sleep(3000);
							MethodsPOM.RiskSummaryGraph(driver, test,"Medium Risk",MediumRisk);
							Thread.sleep(3000);
							MethodsPOM.RiskSummaryGraph(driver, test,"Low Risk",LowRisk);
							Thread.sleep(3000);
							MethodsPOM.RiskSummaryGraph(driver, test,"Not Applicable Risk",NotApplicableRisk);
							
							
							Thread.sleep(3000);
							OverduePOM.clickDashboard(driver).click();
					         extent.endTest(test);
					        extent.flush();
				}
	//		@Test(priority =4)
				
				void DepartmentSummaryGraph() throws InterruptedException, IOException
				{
				
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,800)");
			    
					Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
					Thread.sleep(5000);
					performerPOM.clickDashboardNoticeFilter(driver).click();
			  
			   	
					Thread.sleep(5000);
					performerPOM.clickDashboardApplyBtn(driver).click();
			 	
					 js.executeScript("window.scrollBy(0,900)");
				
					 String DeptName =performerPOM.DepartName(driver).getText();
					 test = extent.startTest("Select Notice Filter ="+DeptName+" Department - Department Summary Graph Count Verification");
					
					 MethodsPOM.DepartmentSummaryGraph(driver, test,"Notice");
					 
					 extent.endTest(test);
					 extent.flush();
				}
				
			@Test(priority = 5)
				
				void LocationSummaryGraph() throws InterruptedException, IOException
				{
				
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,800)");
			 
					Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
				
					Thread.sleep(5000);
					performerPOM.clickDashboardNoticeFilter(driver).click();
			  
			   	
					Thread.sleep(5000);
					performerPOM.clickDashboardApplyBtn(driver).click();
				
			        js.executeScript("window.scrollBy(0,1500)");
			   
				    String LocationName =performerPOM.LocationName(driver).getText();
					test = extent.startTest("Select Notice Filter = "+LocationName+" Location- Location Summary Graph Count Verification");
					
					MethodsPOM.LocationSummaryGraph(driver, test,"Notice");
					
					extent.endTest(test);
					extent.flush();
				}
		//	@Test(priority =6)
				
			  	void CategorySummaryGraph() throws InterruptedException, IOException
			  	{
				
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,800)");
				     
			    	Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
					Thread.sleep(5000);
					performerPOM.clickDashboardNoticeFilter(driver).click();
					Thread.sleep(2000);
			        performerPOM.clickDashboardApplyBtn(driver).click();
				   
			       	js.executeScript("window.scrollBy(0,2000)");
			        Thread.sleep(2000);
					String CategoryName =performerPOM.CategoryName(driver).getText();
			  		test = extent.startTest("Select Multiple Filter ="+CategoryName+" Category - Category Summary Graph count Verification");
			  		
			  		
			  		MethodsPOM.CategorySummaryGraph(driver, test,"Notice");
			  		
			  		extent.endTest(test);
			  		extent.flush();
			  	}
			@Test(priority = 7)
			    void InwardDefendantAgeingGraph() throws InterruptedException, IOException
			    {
			         test = extent.startTest("Select Notice Filter =Less than a year  = Ageing Graph Count Verification");
			         
			         JavascriptExecutor js = (JavascriptExecutor) driver;
				     	js.executeScript("window.scrollBy(0,800)");
				     	
				     	Thread.sleep(5000);
						performerPOM.clickDashboardCaseNoticeFilter(driver).click();
						
						Thread.sleep(5000);
						performerPOM.clickDashboardNoticeFilter(driver).click();
			       
			        	
						 Thread.sleep(5000);
						 performerPOM.clickDashboardApplyBtn(driver).click();
						 Thread.sleep(3000);
						js.executeScript("window.scrollBy(0,3700)");
						Thread.sleep(3000);
					    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA(driver).getText());	//Reading Notice Open count.
				    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickComplainantAgeing(driver).getText());	//Reading Notice Open count.
				    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCA(driver).getText());	//Reading Notice Open count.
				    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA(driver).getText());	//Reading Notice Open count.
				    	
						
				    	Thread.sleep(3000);
				    	MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Inward/Defendent",InwardDefendent);
						Thread.sleep(3000);
						MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
						Thread.sleep(3000);
						MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Petitioner",Petitioner);
						Thread.sleep(3000);
						MethodsPOM.InwardDefendantAgeingGraph(driver, test,"Respondent",Respondent);
						
						
						Thread.sleep(3000);
						OverduePOM.clickDashboard(driver).click();
			        
			          extent.endTest(test);
			          extent.flush();
			    }
			 @Test(priority = 8)
			    void AgeingGraph1to2years() throws InterruptedException, IOException
			    {
			         	test = extent.startTest("Select Notice Filter =1 to 2 years = Ageing Graph Count Verification");
			         
			         	JavascriptExecutor js = (JavascriptExecutor) driver;
			         
			    		js.executeScript("window.scrollBy(0,3700)");
			    		Thread.sleep(5000);
			    	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1to2(driver).getText());	//Reading Notice Open count.
			        	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickPetitionerCA1To2Years(driver).getText());	//Reading Notice Open count.
			        	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCase(driver).getText());	//Reading Notice Open count.
			        	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA1To2Years(driver).getText());	//Reading Notice Open count.
			        	
			    		
			        	Thread.sleep(3000);
			        	MethodsPOM.AgeingGraph1to2years(driver, test,"Inward/Defendent",InwardDefendent);
			    		Thread.sleep(3000);
			    		MethodsPOM.AgeingGraph1to2years(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
			    		Thread.sleep(3000);
			    		MethodsPOM.AgeingGraph1to2years(driver, test,"Petitioner",Petitioner);
			    		Thread.sleep(3000);
			    		MethodsPOM.AgeingGraph1to2years(driver, test,"Respondent",Respondent);
			    		
			    		Thread.sleep(3000);
			    		OverduePOM.clickDashboard(driver).click();
			        
			          extent.endTest(test);
			          extent.flush();
			     }
			    
		  @Test(priority = 9)
			    void AgeingGraph2to3years() throws InterruptedException, IOException
			    {
			      test = extent.startTest("Select Notice Filter =2 to 3 years = Ageing Graph Count Verification");
			     
			       JavascriptExecutor js = (JavascriptExecutor) driver;
			     	js.executeScript("window.scrollBy(0,800)");
			     	
			     	Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
					Thread.sleep(5000);
					performerPOM.clickDashboardNoticeFilter(driver).click();
			   
			    	
					 Thread.sleep(5000);
					 performerPOM.clickDashboardApplyBtn(driver).click();
					 Thread.sleep(3000);
					js.executeScript("window.scrollBy(0,3700)");
					 Thread.sleep(3000);
				    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA2to3(driver).getText());	//Reading Notice Open count.
			    	
			    	Thread.sleep(3000);
			    	MethodsPOM.AgeingGraph2to3years(driver, test,"Inward/Defendent",InwardDefendent);
				
					Thread.sleep(3000);
					OverduePOM.clickDashboard(driver).click();
			    
			      extent.endTest(test);
			      extent.flush();
			  }	
	 @Test(priority = 10)
			    void AgeingGraphMorethan3years() throws InterruptedException, IOException
			     {
			         test = extent.startTest("Select Notice Filter =More than 3 years = Ageing Graph Count Verification");
			      
			         JavascriptExecutor js = (JavascriptExecutor) driver;
			      	js.executeScript("window.scrollBy(0,800)");
			      	
			      	Thread.sleep(5000);
			 		performerPOM.clickDashboardCaseNoticeFilter(driver).click();
			 		
			 		Thread.sleep(5000);
			 		performerPOM.clickDashboardNoticeFilter(driver).click();
			    
			     	
			 		 Thread.sleep(5000);
			 		 performerPOM.clickDashboardApplyBtn(driver).click();
			 		 Thread.sleep(3000);
			 		js.executeScript("window.scrollBy(0,3800)");
			 		 Thread.sleep(3000);
			 	    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendent1(driver).getText());	//Reading Notice Open count.
			     	
			     	Thread.sleep(3000);
			     	MethodsPOM.AgeingGraphMorethan3years(driver, test,"Inward/Defendent",InwardDefendent);
			 	
			 		Thread.sleep(3000);
			 		OverduePOM.clickDashboard(driver).click();
			     
			       extent.endTest(test);
			       extent.flush();
			    }	
			  @Test(priority =11)
				void CaseNoticeTypeGraph1() throws InterruptedException, IOException
				{
					test = extent.startTest("Select Case Filter  = Case Notice Type Graph Count Verification");
					
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
			  	    js.executeScript("window.scrollBy(0,850)");
			  	
			  	    Thread.sleep(5000);
					performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
					Thread.sleep(5000);
					performerPOM.clickDashboardCaseFilter(driver).click();
			   
			    	
					 Thread.sleep(5000);
					 performerPOM.clickDashboardApplyBtn(driver).click();
					 Thread.sleep(5000);
			       int	OutwardPlaintiff = Integer.parseInt(performerPOM.CaseNoticeTypeSummaryGraph(driver).getText());	//Reading Notice Open count.
			 	   int	InwardDefendent = Integer.parseInt(performerPOM.CaseNoticeTypeInwardDefendent(driver).getText());	//Reading Notice Open count.
			 	   int	Petitioner = Integer.parseInt(performerPOM.CaseNoticeTypeComplinant(driver).getText());	//Reading Notice Open count.
			 	   int	Respondent = Integer.parseInt(performerPOM.CaseNoticeTypeRespondent(driver).getText());	//Reading Notice Open count.
			 	
					
			 	    Thread.sleep(3000);
			 	    MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Inward/Defendent Type",OutwardPlaintiff);
					Thread.sleep(3000);
					MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Outward/Plaintiff Type",InwardDefendent);
					Thread.sleep(3000);
					MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Respondent Type",Petitioner);
					Thread.sleep(3000);
					MethodsPOM.CaseNoticeTypeGraph1(driver, test,"Petitioner Type",Respondent);
					
					
					Thread.sleep(3000);
					OverduePOM.clickDashboard(driver).click();
					
					extent.endTest(test);
					extent.flush();
				}
			@Test(priority =12)
				void CaseNoticeStageGraphNotice1() throws InterruptedException, IOException
				{
					test = extent.startTest("Select Case Filter = Hearing Stage = Case Notice Stage Graph Count Verification");
					
					
					MethodsPOM.CaseNoticeStageGraph1(driver, test,"Case");
					
					extent.endTest(test);
					extent.flush();
				}
				
			 @Test(priority = 13)
					void RiskSummaryGraph1() throws InterruptedException, IOException
					{
						test = extent.startTest("Select Case Filter = Risk Graph Count Verification");
				        
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,800)");
				     	
				     	Thread.sleep(5000);
						performerPOM.clickDashboardCaseNoticeFilter(driver).click();
						
						Thread.sleep(5000);
						performerPOM.clickDashboardCaseFilter(driver).click();
				      
				       	
						 Thread.sleep(5000);
						 performerPOM.clickDashboardApplyBtn(driver).click();
						 Thread.sleep(3000);
						js.executeScript("window.scrollBy(0,950)");
						
					    int	HighRisk = Integer.parseInt(performerPOM.RiskSummaryHigh(driver).getText());	//Reading Notice Open count.
				    	int	MediumRisk = Integer.parseInt(performerPOM.RiskSummaryMedium(driver).getText());	//Reading Notice Open count.
				    	int	LowRisk = Integer.parseInt(performerPOM.RiskSummaryLow(driver).getText());	//Reading Notice Open count.
				    	int	NotApplicableRisk = Integer.parseInt(performerPOM.RiskSummaryNotApplicable(driver).getText());	//Reading Notice Open count.
				    	
						
				    	Thread.sleep(3000);
						MethodsPOM.RiskSummaryGraph1(driver, test,"High Risk",HighRisk);
						Thread.sleep(3000);
						MethodsPOM.RiskSummaryGraph1(driver, test,"Medium Risk",MediumRisk);
						Thread.sleep(3000);
						MethodsPOM.RiskSummaryGraph1(driver, test,"Low Risk",LowRisk);
						Thread.sleep(3000);
						MethodsPOM.RiskSummaryGraph1(driver, test,"Not Applicable Risk",NotApplicableRisk);
					
						Thread.sleep(3000);
						OverduePOM.clickDashboard(driver).click();
						
						extent.endTest(test);
						extent.flush();
					}
				@Test(priority = 14)

					void DepartmentSummaryGraph1() throws InterruptedException, IOException
					{
						
					JavascriptExecutor js = (JavascriptExecutor) driver;
			           js.executeScript("window.scrollBy(0,800)");
						Thread.sleep(5000);
						performerPOM.clickDashboardCaseNoticeFilter(driver).click();
						
						Thread.sleep(6000);
						performerPOM.clickDashboardCaseFilter(driver).click();
			          
			           	
						 Thread.sleep(3000);
							performerPOM.clickDashboardApplyBtn(driver).click();

				        js.executeScript("window.scrollBy(0,900)");
					
						 String DeptName =performerPOM.DepartName(driver).getText();
						 test = extent.startTest("Select Notice Filter ="+DeptName+" Department - Department Summary Graph Count Verification");
						
						 MethodsPOM.DepartmentSummaryGraph1(driver, test,"Case");
						 
						 extent.endTest(test);
						 extent.flush();
						
						
					}
				@Test(priority =15)

				void LocationSummaryGraph1() throws InterruptedException, IOException
				{

					
					JavascriptExecutor js = (JavascriptExecutor) driver;
			           js.executeScript("window.scrollBy(0,800)");
						Thread.sleep(5000);
						performerPOM.clickDashboardCaseNoticeFilter(driver).click();
						
						Thread.sleep(6000);
						performerPOM.clickDashboardCaseFilter(driver).click();
			          
			           	
						 Thread.sleep(3000);
							performerPOM.clickDashboardApplyBtn(driver).click();
				
			        js.executeScript("window.scrollBy(0,1500)");
			   
				    String LocationName =performerPOM.LocationName(driver).getText();
					test = extent.startTest("Select Notice Filter = "+LocationName+" Location- Location Summary Graph Count Verification");
					
					MethodsPOM.LocationSummaryGraph1(driver, test,"Case");
					
					extent.endTest(test);
					extent.flush();
					
					
				}
			@Test(priority = 16)

					void CategorySummaryGraph1() throws InterruptedException, IOException
					{
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,800)");
						Thread.sleep(5000);
						performerPOM.clickDashboardCaseNoticeFilter(driver).click();
					
						Thread.sleep(6000);
						performerPOM.clickDashboardCaseFilter(driver).click();
		          
						Thread.sleep(3000);
						performerPOM.clickDashboardApplyBtn(driver).click();
					
					
			       	
						js.executeScript("window.scrollBy(0,2000)");
			       
						Thread.sleep(2000);
						String CategoryName =performerPOM.CategoryName(driver).getText();
						test = extent.startTest("Select Multiple Filter ="+CategoryName+" Category - Category Summary Graph count Verification");
		  
						MethodsPOM.CategorySummaryGraph1(driver, test,"Case");
						
						extent.endTest(test);
						extent.flush();
					}
				@Test(priority =17)
				    void ExpensesCaseGraph() throws InterruptedException, IOException
				    {
				       test = extent.startTest("Select Case Filter = Expenses Case-Wise Graph Count Verification");
				      
				       Thread.sleep(3000);
				       MethodsPOM.ExpensesCaseGraph(driver, test,"Company admin-");

				       extent.endTest(test);
				       extent.flush();
				    }

			@Test(priority =18)
				void ExpensesCategoryWiseCaseGraph() throws InterruptedException, IOException
				{
				   test = extent.startTest("Select Case Filter  -Expenses Category Wise Graph Count Verification");
				  
				   Thread.sleep(3000);
				   MethodsPOM.ExpensesCategoryWiseCaseGraph(driver, test,"cfo -");

				   extent.endTest(test);
				   extent.flush();
				}
			@Test(priority =19)
				void ExpensesCounselWiseCaseGraph() throws InterruptedException, IOException
				{
				  test = extent.startTest("Select Case Filter -Expenses Counsel Wise Graph Count Verification");
				 
				  Thread.sleep(3000);
				  MethodsPOM.ExpensesCounselWiseCaseGraph(driver, test,"cfo -");

				  extent.endTest(test);
				  extent.flush();
				}
			@Test(priority =20)
				void UtilizedBudgetGraph() throws InterruptedException, IOException
				{
				  test = extent.startTest("Select Case Filter -Utilized budget Graph Count Verification");
				 
				  Thread.sleep(3000);
				  MethodsPOM.UtilizedBudgetGraph(driver, test,"cfo -");

				  extent.endTest(test);
				  extent.flush();
				}
				@Test(priority = 21)
				    void AgeingGraph() throws InterruptedException, IOException
				    {
				         test = extent.startTest("Select Case Filter =Less than a year  = Ageing Graph Count Verification");
				         
				         JavascriptExecutor js = (JavascriptExecutor) driver;
					     	js.executeScript("window.scrollBy(0,800)");
					     	
					     	Thread.sleep(5000);
							performerPOM.clickDashboardCaseNoticeFilter(driver).click();
							
							Thread.sleep(5000);
							performerPOM.clickDashboardCaseFilter(driver).click();
				       
				        	
							 Thread.sleep(5000);
							 performerPOM.clickDashboardApplyBtn(driver).click();
							 Thread.sleep(3000);
							js.executeScript("window.scrollBy(0,3700)");
							 Thread.sleep(3000);
						    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1(driver).getText());	//Reading Notice Open count.
					    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffAgeing1(driver).getText());	//Reading Notice Open count.
					    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCA1(driver).getText());	//Reading Notice Open count.
					    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA1(driver).getText());	//Reading Notice Open count.
					    	
							
					    	Thread.sleep(3000);
					    	MethodsPOM.AgeingGraphLessThanYear(driver, test,"Inward/Defendent",InwardDefendent);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraphLessThanYear(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraphLessThanYear(driver, test,"Petitioner",Petitioner);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraphLessThanYear(driver, test,"Respondent",Respondent);
							
							
							Thread.sleep(3000);
							OverduePOM.clickDashboard(driver).click();
				        
				          extent.endTest(test);
				          extent.flush();
				    }
				 @Test(priority =22)
					void AgeingGraph1to2yearsCase() throws InterruptedException, IOException
					{
					     test = extent.startTest("Select Case Filter =1 to 2 years = Ageing Graph Count Verification");
					     
					     JavascriptExecutor js = (JavascriptExecutor) driver;
					     	js.executeScript("window.scrollBy(0,800)");
					     	
					     	Thread.sleep(5000);
							performerPOM.clickDashboardCaseNoticeFilter(driver).click();
							
							Thread.sleep(5000);
							performerPOM.clickDashboardCaseFilter(driver).click();
					   
					    	
							 Thread.sleep(5000);
							 performerPOM.clickDashboardApplyBtn(driver).click();
							
							js.executeScript("window.scrollBy(0,3700)");
							 Thread.sleep(3000);
						    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA1to21(driver).getText());	//Reading Notice Open count.
					    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffCaseCA(driver).getText());	//Reading Notice Open count.
					    	int	Petitioner = Integer.parseInt(performerPOM.clickPetitionerCAA(driver).getText());	//Reading Notice Open count.
					    	int	Respondent = Integer.parseInt(performerPOM.clickRespondentCA2(driver).getText());	//Reading Notice Open count.
							
					    	Thread.sleep(3000);
					    	MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Inward/Defendent",InwardDefendent);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Petitioner",Petitioner);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraph1to2yearsCase(driver, test,"Respondent",Respondent);
							
							
							Thread.sleep(3000);
							OverduePOM.clickDashboard(driver).click();
					    
					      extent.endTest(test);
					      extent.flush();
					 }
				@Test(priority =23)
					void AgeingGraph2to3yearsCase() throws InterruptedException, IOException
					{
					     test = extent.startTest("Select Case Filter =2 to 3 years = Ageing Graph Count Verification");
					     
					     JavascriptExecutor js = (JavascriptExecutor) driver;
					     	js.executeScript("window.scrollBy(0,800)");
					     	
					     	Thread.sleep(5000);
							performerPOM.clickDashboardCaseNoticeFilter(driver).click();
							
							Thread.sleep(5000);
							performerPOM.clickDashboardCaseFilter(driver).click();
					   
					    	
							 Thread.sleep(5000);
							 performerPOM.clickDashboardApplyBtn(driver).click();
							
							js.executeScript("window.scrollBy(0,3850)");
							 Thread.sleep(3000);
						    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCA2to3Case(driver).getText());	//Reading Notice Open count.
					    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffCaseCA2to3(driver).getText());	//Reading Notice Open count.
					    
							
					    	Thread.sleep(3000);
					    	MethodsPOM.AgeingGraph2to3yearsCase(driver, test,"Inward/Defendent",InwardDefendent);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraph2to3yearsCase(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
							
							
							
							Thread.sleep(3000);
							OverduePOM.clickDashboard(driver).click();
					    
					      extent.endTest(test);
					      extent.flush();
					 }
				@Test(priority =24)
					void AgeingGraphMoreThan3yearsCase() throws InterruptedException, IOException
					{
					     test = extent.startTest("Select Case Filter =More than 3 years = Ageing Graph Count Verification");
					     
					     JavascriptExecutor js = (JavascriptExecutor) driver;
					     	js.executeScript("window.scrollBy(0,800)");
					     	
					     	Thread.sleep(5000);
							performerPOM.clickDashboardCaseNoticeFilter(driver).click();
							
							Thread.sleep(5000);
							performerPOM.clickDashboardCaseFilter(driver).click();
					   
					    	
							 Thread.sleep(5000);
							 performerPOM.clickDashboardApplyBtn(driver).click();
							 Thread.sleep(3000);
							js.executeScript("window.scrollBy(0,3800)");
							
						    int	InwardDefendent = Integer.parseInt(performerPOM.clickInwardDefendentCAMoreThan3yearsCase(driver).getText());	//Reading Notice Open count.
					    	int	OutwardPlaintiff = Integer.parseInt(performerPOM.clickOutwardPlaintiffCaseCAMoreThan3years(driver).getText());	//Reading Notice Open count.
					    
							
					    	Thread.sleep(3000);
					    	MethodsPOM.AgeingGraphMoreThan3yearsCase(driver, test,"Inward/Defendent",InwardDefendent);
							Thread.sleep(3000);
							MethodsPOM.AgeingGraphMoreThan3yearsCase(driver, test,"Outward/Plaintiff",OutwardPlaintiff);
							
							Thread.sleep(3000);
							OverduePOM.clickDashboard(driver).click();
					    
					      extent.endTest(test);
					      extent.flush();
					 }
					
			 @Test(priority =25)
				 	void NoticeOpen() throws InterruptedException, IOException
				 	{
				 		test = extent.startTest("Notice - Open Count Verification");
				 		
				 		
				 		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
				 		
				 		test.log(LogStatus.PASS, "Test Passed.");
				 		extent.endTest(test);
				 		extent.flush();
				 	}
				@Test(priority =26)
						void NoticeWithExistingData() throws InterruptedException, IOException
						{
							test = extent.startTest("Notice With Existing Data verification");
						   MethodsPOM.NoticeWithExistingData(driver, test);
						  extent.endTest(test);
							extent.flush();
						}
					
				@Test(priority =27)
				     void NoticeWithInvalidData() throws InterruptedException, IOException
				    {
					     test = extent.startTest("Notice With Invalid Data verification");
					
					
					     MethodsPOM.NoticeWithInvalidData(driver, test, workbook);
					
					     extent.endTest(test);
					     extent.flush();
				   }
				@Test(priority =28)
					   void NoticeWithTwoMandatoryData() throws InterruptedException, IOException
					  {
						     test = extent.startTest("Notice With Two Mandatory Fields verification");
						
						
						     MethodsPOM.NoticeWithTwoMandatoryData(driver, test, workbook);
						
						     extent.endTest(test);
						     extent.flush();
					 }
					 
				@Test(priority =29) 
					   void NoticeWithEmptyFields() throws InterruptedException, IOException
					  {
						     test = extent.startTest("Notice With Empty Fields verification");
						
						
						     MethodsPOM.NoticeWithEmptyFields(driver, test);
						
						     extent.endTest(test);
						     extent.flush();
					 }
			  @Test(priority =30)
					   void NoticeSendMailWithDoc() throws InterruptedException, IOException
					  {
					 	     test = extent.startTest("Notice Summary-Send Mail With Document verification");
					 	
					 	
					 	      MethodsPOM.NoticeSendMailWithDoc(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
			 @Test(priority =31)
					   void NoticeSendMailWithDocInvalidFields() throws InterruptedException, IOException
					  {
					 	     test = extent.startTest(" Notice Summary -Send Mail With Document Invalid Fields verification");
					 	
					 	
					 	      MethodsPOM.NoticeSendMailWithDocInvalidFields(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
			 @Test(priority =32)
					   void NoticeSendMailWithDocEmptyFields() throws InterruptedException, IOException
					  {
					 	     test = extent.startTest("Notice Summary -Send Mail With Document Empty Fields verification");
					 	
					 	
					 	      MethodsPOM.NoticeSendMailWithDocEmptyFields(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
					 @Test(priority =33)
					   void NoticeUserAssignment() throws InterruptedException, IOException
					  {
					 	     test = extent.startTest("Notice User Assignment  verification");
					 	
					 	
					 	      MethodsPOM.NoticeUserAssignment(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
				
					   
					 @Test(priority =34)
					 	void LinkNotice() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Link Notice Verification");
					 		
					 		
					 		MethodsPOM.LinkDocument(driver, test, workbook, "Notice");
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					   
					  @Test(priority =35)
					   void LinkNoticeViewIcon() throws InterruptedException, IOException
					  {
					 	     test = extent.startTest("Linked notice view icon  verification");
					 	
					 	
					 	      MethodsPOM.LinkNoticeViewIcon(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
					 @Test(priority =36)
					   void LinkNoticeDeleteIcon() throws InterruptedException, IOException
					  {
					 	     test = extent.startTest("Linked notice Delete icon  verification");
					 	
					 	
					 	      MethodsPOM.LinkNoticeDeleteIcon(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
					  
					 @Test(priority =37)
					     	void CaseOpen() throws InterruptedException, IOException
					     	{
					     		test = extent.startTest("Case - Open Count verification");
					     		
					     		
					     		MethodsPOM.CaseOpen(driver, test, workbook, "CFO -");
					     		
					     		extent.endTest(test);
					     		extent.flush();
					     	}
					 @Test(priority =38)
					 	void CaseExistingData() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case with Existing Data verification");
					 		
					 		
					 		MethodsPOM.CaseExistingData(driver, test, workbook);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority =39)
					 	void CaseWithInvalidData() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case with Invalid Data verification");
					 		
					 		
					 		MethodsPOM.CaseWithInvalidData(driver, test, workbook);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority =40)
					   	void CaseWithTwoFieldsData() throws InterruptedException, IOException
					   	{
					   		test = extent.startTest("Case with Two Manadatory fields verification");
					   		
					   		
					   		MethodsPOM.CaseWithTwoFieldsData(driver, test);
					   		
					   		extent.endTest(test);
					   		extent.flush();
					   	}
					 @Test(priority =41)
					   	void CaseWithEmptyFields() throws InterruptedException, IOException
					   	{
					   		test = extent.startTest("Case with Empty fields verification");
					   		
					   		
					   		MethodsPOM.CaseWithEmptyFields(driver, test);
					   		
					   		extent.endTest(test);
					   		extent.flush();
					   	}
					   	
					 @Test(priority =42)
					     			void TaskOpen() throws InterruptedException, IOException
					     			{
					     				test = extent.startTest("Task - Open Count verification");
					     				
					     				
					     				MethodsPOM.TaskOpen(driver, test, workbook, "CFO");
					     				
					     				extent.endTest(test);
					     				extent.flush();
					     			}
				@Test(priority = 43)
						void TaskwithExistingData() throws InterruptedException, IOException
						{
							test = extent.startTest("Task With existing data verification");
							
							
							MethodsPOM.TaskWithExistingData(driver, test, workbook);
							
							extent.endTest(test);
							extent.flush();
						}
					 @Test(priority =44)
					     			void TaskwithTwoManadatoryFields() throws InterruptedException, IOException
					     			{
					     				test = extent.startTest("Task With Two manadatory fields verification");
					     				
					     				
					     				MethodsPOM.TaskWithTwoMandatoryFields(driver, test, workbook);
					     				
					     				extent.endTest(test);
					     				extent.flush();
					     			}
					    @Test(priority = 45)
					 			void TaskwithoutData() throws InterruptedException, IOException
					 			{
					 				test = extent.startTest("Task Without  data verification");
					 				
					 				
					 				MethodsPOM.TaskwithoutData(driver, test);
					 				
					 				extent.endTest(test);
					 				extent.flush();
					 			}
					 			
					 		 @Test(priority = 46)
					 			void TaskDelete() throws InterruptedException, IOException
					 			{
					 				test = extent.startTest("Task Delete verification");
					 				
					 				
					 				MethodsPOM.TaskDelete(driver, test);
					 				
					 				extent.endTest(test);
					 				extent.flush();
					 			}
					    	
					  @Test(priority = 47)
					     	void NoticeClosed() throws InterruptedException, IOException
					     	{
					     		test = extent.startTest("Notice - Closed Count verification");
					     		
					     		
					     		MethodsPOM.NoticeClosed(driver, test, workbook, "Company Admin");
					     		
					     		extent.endTest(test);
					     		extent.flush();
					     	}
					@Test(priority =48)
						void CaseClose() throws InterruptedException, IOException
						{
							test = extent.startTest("Case - Closed Count Verification");
							
							MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
							
							extent.endTest(test);
							extent.flush();
						}
					 	
					 @Test(priority = 49)
					 		void LinkCase() throws InterruptedException, IOException
					 		{
					 			test = extent.startTest("Link Case Verification");
					 		
					 			
					 			MethodsPOM.LinkDocument(driver, test, workbook, "Case");
					 		
					 		extent.endTest(test);
					 			extent.flush();
					 		}
					 	
					  @Test(priority =50)
					 	   void LinkCaseViewIcon() throws InterruptedException, IOException
					 	  {
					 		     test = extent.startTest("Linked case view icon  verification");
					 		
					 		
					 		      MethodsPOM.LinkCaseViewIcon(driver, test);
					 		
					 		     extent.endTest(test);
					 		     extent.flush();
					 	 }
					  @Test(priority =51)
					 	   void LinkCaseDeleteIcon() throws InterruptedException, IOException
					 	  {
					 		     test = extent.startTest("Linked case delete icon  verification");
					 		
					 		
					 		      MethodsPOM.LinkCaseDeleteIcon(driver, test);
					 		
					 		     extent.endTest(test);
					 		     extent.flush();
					 	 }
					 	 	
					 	   
					 @Test(priority = 52)
					     	void CloseNotice() throws InterruptedException, IOException
					     	{
					     		test = extent.startTest("Close Notice Count verification");
					     		
					     		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
					     		
					     		extent.endTest(test);
					     		extent.flush();
					     	}
					 @Test(priority = 53)
					 			void CloseCase() throws InterruptedException, IOException
					 			{
					 			test = extent.startTest("Close Case Count Verification");
					 				
					 				
					 				MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
					 				
					 			extent.endTest(test);
					 				extent.flush();
					 			}
					 	  
					 @Test(priority = 54)
					 			void TaskClosed() throws InterruptedException, IOException
					 			{
					 				test = extent.startTest("Task - Closed Count verification");
					 				
					 				
					 				MethodsPOM.TaskClosed(driver, test, workbook, "CFO");
					 				
					 				extent.endTest(test);
					 				extent.flush();
					 			}
					 	@Test(priority = 55)
					     	void NoticeDocument() throws InterruptedException, IOException
					     	{
					     		test = extent.startTest("Notice Document verification");
					     		
					     		
					     		MethodsPOM.NoticeDocument(driver, test);
					     		
					     		extent.endTest(test);
					     		extent.flush();

					     	}
					  
					 	@Test(priority = 56)
					 	void NoticeWithoutUploadDocument() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Notice Without Upload Document verification");
					 		
					 		
					 		MethodsPOM.NoticeWithoutUploadDocument(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 	@Test(priority = 57)
					 	void NoticeDocumentShareInvalidData() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Notice Document Share with Invaid data verification");
					 		
					 		
					 		MethodsPOM.NoticeDocumentShareInvalidData(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority = 58)
					 	void NoticeDocumentShareWithoutData() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Notice Document Share without data verification");
					 		
					 		
					 		MethodsPOM.NoticeDocumentShareWithoutData(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 	@Test(priority = 59)
					 	void NoticeTaskActivity() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Notice TaskActivtiy verification");
					 		
					 		
					 		MethodsPOM.TaskActivtity(driver, test,workbook);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority = 60)
					 void TaskActivtityDeleteResponse() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Task/Activtiy Delete Response verification");
					 	
					 	
					 	MethodsPOM.TaskActivtityDeleteResponse(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 61)
					 void TaskActivtityExistingData() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Task/Activtiy with existing data verification");
					 	
					 	
					 	MethodsPOM.TaskActivtityExistingData(driver, test,workbook);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority =62)
					 void TaskActivtityWithoutData() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Task/Activtiy Without data verification");
					 	
					 	
					 	MethodsPOM.TaskActivtityWithoutData(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 63)
					 void TaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Task/Activtiy Response Without data verification");
					 	
					 	
					 	MethodsPOM.TaskActivtityResponseWithoutStatus(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority =64)
					 void NoticeResponse() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Response verification");
					 	
					 	
					 	MethodsPOM.Response(driver, test,workbook);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority =65)
					 void ResponseExistingData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Response Existing Data verification");


					 MethodsPOM.ResponseExistingData(driver, test,workbook);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority =66)
					 void NoticeResponseWithoutData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Response Without data verification");


					 MethodsPOM.ResponseWithoutData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 67)
					 void NoticePayment() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Payment verification");
					 	
					 	
					 	MethodsPOM.PaymentLog(driver,test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 68)
					 void PaymentLogwithExistingData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Payment with existing data verification");


					 MethodsPOM.PaymentLogExistingData(driver,test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 69)
					 void NoticePaymentWithoutData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Payment Without data verification");


					 MethodsPOM.PaymentLogWithoutData(driver,test,workbook);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 70)
					 void NoticeExternalLawyer() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Lawyer verification");
					 	
					 	MethodsPOM.ExternalLawyerRating(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 71)
					 void CriteriaExistingData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Criteria Existing Data verification");

					 MethodsPOM.CriteriaExistingData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 72)
					 void CriteriaInvalidData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Criteria Invalid Data verification");

					 MethodsPOM.CriteriaInvalidData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 73)
					 void CriteriaWithoutData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Notice Criteria Without Data verification");

					 MethodsPOM.CriteriaWithoutData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 74)
					 void NoticeAuditLog() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Notice Audit Log verification");

					 	
					 	MethodsPOM.AuditLog(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }		
					 @Test(priority =75)
					 void CaseDocument() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Case - Document Tab");
					 	
					 	
					 	MethodsPOM.Document(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 76)
					 void CaseWithoutUploadDocument() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Case Without Upload Document verification");
					 	
					 	
					 	MethodsPOM.CaseWithoutUploadDocument(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 77)
					 void CaseDocumentEmptyFields() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Case Document with empty fields verification");


					 MethodsPOM.CaseDocumentEmptyFields(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }

					 @Test(priority = 78)
					 void CaseDocumentSearchFields() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Case Document Search Fields verification");


					 MethodsPOM.CaseDocumentSearchFields(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority = 79)
					 void CaseDocumentShareInvalidData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Case Document Share with Invaid data verification");


					 MethodsPOM.CaseDocumentShareInvalidData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority =80)
					 void CaseDocumentShareWithoutData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Case Document Share without data verification");


					 MethodsPOM.CaseDocumentShareWithoutData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority =81)
					 void CaseSendMailWithDoc() throws InterruptedException, IOException
					 {
					 	     test = extent.startTest("Case Summary-Send Mail With Document verification");
					 	
					 	
					 	      MethodsPOM.CaseSendMailWithDoc(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }

					 @Test(priority =82)
					 void CaseSendMailWithDocInvalidFields() throws InterruptedException, IOException
					 {
					 	     test = extent.startTest("Case  Summary-Send Mail With Document Invalid Fields verification");
					 	
					 	
					 	      MethodsPOM.CaseSendMailWithDocInvalidFields(driver, test);
					 	
					 	     extent.endTest(test);
					 	     extent.flush();
					 }
				

					@Test(priority = 83)
					 	void CaseTaskActivityTab() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case Task/Activity verification");
					 		
					 		
					 		MethodsPOM.TaskActivity1(driver, test,workbook,"Performer");
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}

					 @Test(priority = 84)
					 	void CaseTaskActivityWithoutData() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case Task/Activtiy Without data verification");
					 		
					 		
					 		MethodsPOM.CaseTaskActivityWithoutData(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority =85)
					 void CaseTaskActivitywithExistingData() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Case - Task/Activty with existing data");
					 	
					 	
					 	MethodsPOM.CaseTaskActivitywithExistingData(driver, test,workbook);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }

					 @Test(priority = 86)
					 	void CaseTaskActivtityResponseWithoutStatus() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case Task/Activtiy Response Without data verification");
					 		
					 		
					 		MethodsPOM.CaseTaskActivtityResponseWithoutStatus(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 	@Test(priority =87)
					 	void CaseHearingcfo() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case - CaseHearing Tab");
					 		
					 		
					 		MethodsPOM.CaseHearing(driver, test,workbook);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					
					 @Test(priority= 88)
					 void CaseWithoutHearingData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Case without hearing data Verification");


					 MethodsPOM.CaseHearingWithoutData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority =89)
					 void CaseHearingInvalidDate() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Case Invalid Hearing Date Verification");
					 	
					 	
					 	MethodsPOM.CaseHearingInvalidDate(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					@Test(priority = 90)
					 	void CaseOrderTab() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case Order verification");
					 		
					 		
					 		MethodsPOM.CaseOrder(driver, test,workbook,"Performer");
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority =91)
					 void CaseOrderExistingData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Case Order with Duplicate data");


					 MethodsPOM.CaseOrderWithExistingData(driver, test,workbook);

					 extent.endTest(test);
					 extent.flush();
					 }

					 @Test(priority =92)
					 void CaseOrderWithoutData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("Without data Order tab");


					 MethodsPOM.CaseOrderWithoutData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 @Test(priority =93)
					 void CaseStatusPayment() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Case - Status/Payment Tab");
					 	
					 	
					 	MethodsPOM.StatusPayment(driver, test,workbook);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }
					
					 @Test(priority =94)
					 void StatusPaymentWithoutdata() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Case Status/Payment without data ");
					 	
					 	
					 	MethodsPOM.StatusPaymentWithoutdata(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 }

					 	@Test(priority =95)
					 	void CaseStatuswithEmptyFields() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case Status With Empty Fields");
					 		
					 		
					 		MethodsPOM.CaseStatuswithEmptyFields(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
						@Test(priority =96)
					 	void ExternalLawyer() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case External Lawyer verification");
					 		
					 		
					 		MethodsPOM.ExternalLawyer(driver, test,1);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 	@Test(priority =97)
					 	void CaseExternalLawyerCriteria() throws InterruptedException, IOException
					 	{
					 	test = extent.startTest("Case - External Lawyer Rating -Add New Criteria ");
					 	
					 	
					 	MethodsPOM.CaseExternalLawyerCriteria(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 	}

					 @Test(priority = 98)
					 	void CaseExistingCriteria() throws InterruptedException, IOException
					 	{
					 	test = extent.startTest("Case Criteria Existing Data verification");
					 	
					 	MethodsPOM.CaseExistingCriteria(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 	}
					 @Test(priority = 99)
					 	void CaseCriteriaInvalidData() throws InterruptedException, IOException
					 	{
					 	test = extent.startTest("Case Criteria Invalid Data verification");
					 	
					 	MethodsPOM.CaseCriteriaInvalidData(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 	}
					 @Test(priority = 100)
					 	void CaseCriteriaWithoutData() throws InterruptedException, IOException
					 	{
					 	test = extent.startTest("Case Criteria Without Data verification");
					 	
					 	MethodsPOM.CaseCriteriaWithoutData(driver, test);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 	}
					 @Test(priority =101)
					 	void CaseAuditLog() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Case - Audit Log Tab");
					 		
					 		
					 		MethodsPOM.Auditlog(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority = 102)
					 	void MyDocument() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("My Document-Download and View Document");
					 	
					 		
					 		MethodsPOM.MyDocument(driver, test, workbook, "Performer");
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 	
					 @Test(priority = 103)
					 	void ShareCaseDocument() throws InterruptedException, IOException
					 	{
					 	test = extent.startTest("My Document-Share Case Document Verification");
					 	
					 	
					 	MethodsPOM.ShareCaseDocument(driver, test, workbook);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 	}
					 @Test(priority = 104)
					 	void ShareNoticeDocument() throws InterruptedException, IOException
					 	{
					 	test = extent.startTest("My Document-Share Notice Document Verification");
					 	
					 	
					 	MethodsPOM.ShareNoticeDocument(driver, test, workbook);
					 	
					 	extent.endTest(test);
					 	extent.flush();
					 	}
					 @Test(priority =105)
					 		void ShareTaskDocument() throws InterruptedException, IOException
					 		{
					 			test = extent.startTest("My Document-Share Task Document Verification");


					 			MethodsPOM.ShareTaskDocument(driver, test, workbook);

					 			extent.endTest(test);
					 			extent.flush();
					 		}
						@Test(priority = 106)
						void MyReports() throws InterruptedException, IOException
						{
							test = extent.startTest("Reports -excel count verification");
							
							
							MethodsPOM.MyReports(driver, test, workbook, "Company Admin");
							
							extent.endTest(test);
							extent.flush();
						}
					  
					@Test(priority = 107)
						void MoreReports() throws InterruptedException, IOException
						{
							test = extent.startTest("More Report-Reports excel  verification");
							
							
							MethodsPOM.MoreReport(driver, test, "Company Admin");
							
							extent.endTest(test);
							extent.flush();
						}
					 @Test(priority =108)
					 	void MyReminder() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("My Reminder verification");
					 		
					 		MethodsPOM.MyReminder(driver, test, workbook);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority =109)
					 void ReminderWithoutData() throws InterruptedException, IOException
					 {
					 test = extent.startTest("My Reminder Without data verification");

					 MethodsPOM.ReminderWithoutData(driver, test);

					 extent.endTest(test);
					 extent.flush();
					 }
					 	
					 @Test(priority = 110)
					 	void ImportUtility() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest("Import Utility verification");
					 		
					 		
					 		MethodsPOM.ImportUtility(driver,test);
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					  @Test(priority = 111)
					 void ImportUtilityWithoutData() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Upload Empty File Import Utility verification");
					 	MethodsPOM.ImportUtilityWithoutData(driver,test);
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 112)
					 void ImportUtilityInvalidData() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Enter Invalid data in Upload File Import Utility verification");


					 	MethodsPOM.ImportUtilityInvalidData(driver,test);
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 113)
					 void ImportUtilityTwoManadtoryFileds() throws InterruptedException, IOException
					 {
					 	test = extent.startTest("Enter Two Manadtory fields in Upload File Import Utility verification");


					 	MethodsPOM.ImportUtilityTwoManadtoryFileds(driver,test);
					 	extent.endTest(test);
					 	extent.flush();
					 }
					 @Test(priority = 114) 		//Sever is blocking and not allowing to upload the file.
					 	void CriticalDocuments() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest(" Critical Document Verification");
					 		
					 		MethodsPOM.CriticalDocuments(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 @Test(priority = 115) 		//Sever is blocking and not allowing to upload the file.
					 	void CriticalDocuments1() throws InterruptedException, IOException
					 	{
					 		test = extent.startTest(" Critical Document Verification");
					 		
					 		MethodsPOM.CriticalDocuments1(driver, test);
					 		
					 		extent.endTest(test);
					 		extent.flush();
					 	}
					 
					 @Test(priority = 116)
						void Masters() throws InterruptedException, IOException
						{
							test = extent.startTest("Masters - Legal Entity  verification");
							
							
							MethodsPOM.LegalEntity(driver, test, workbook);
							
							extent.endTest(test);
							extent.flush();
						}
					@Test(priority = 117)
					void MastersLegalEntity() throws InterruptedException, IOException
						{
							test = extent.startTest("Masters - Legal Entity Without data verification");
							
							MethodsPOM.LegalEntityWithoutData(driver, test);
							
							extent.endTest(test);
							extent.flush();
						}
					@Test(priority =118)
					void MastersLegalEntity1() throws InterruptedException, IOException
						{
							test = extent.startTest("Masters - Legal Entity Invalid data verification");
							
							MethodsPOM.LegalEntityInvalidData(driver, test);
							
							extent.endTest(test);
							extent.flush();
						}

					@Test(priority =119)
					void MastersLegalEntity2() throws InterruptedException, IOException
						{
							test = extent.startTest("Masters - Legal Entity Two Manadatory Fields verification");
							
							MethodsPOM.LegalEntityTwoManadatoryFields(driver, test);
							
							extent.endTest(test);
							extent.flush();
						}
					@Test(priority = 120)
					void Masters1() throws InterruptedException, IOException
					{
						test = extent.startTest("Masters - Law Firm verification");
						
						
						MethodsPOM.LawFirm(driver, test, workbook);
						
						extent.endTest(test);
						extent.flush();
					}

				@Test(priority =121)
				void MastersLawFirm() throws InterruptedException, IOException
				{
					test = extent.startTest("Law Firm Masters - Enter Without Data verification");
					
					
					MethodsPOM.LawFirmWithoutData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority =122)
				void MastersLawFirm1() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Law Firm Invalid Data verification");
					
					
					MethodsPOM.LawFirmInvalidData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority =123)
				void MastersLawFirm2() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - Law Firm Two Manadtory fields verification");
					
					
					MethodsPOM.LawFirmTwoManadatoryFields(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority =124)
				void LawyerWithoutData() throws InterruptedException, IOException
				{
					test = extent.startTest("Lawyer  - Enter Without Data verification");
					
					
					MethodsPOM.LawyerWithoutData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority =125)
				void LawyerInvalidData() throws InterruptedException, IOException
				{
					test = extent.startTest("Lawyer  - Enter Invalid Data verification");
					
					
					MethodsPOM.LawyerInvalidData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority = 126)
				void LawyerTwoManadatoryFileds() throws InterruptedException, IOException
				{
					test = extent.startTest("Lawyer  - Enter Two Manadatory fields verification");
					
					
					MethodsPOM.LawyerTwoManadatoryFileds(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority = 127)
				void Masters2() throws InterruptedException, IOException
				{
					test = extent.startTest("Masters - 	User  verification");
					
					MethodsPOM.User(driver, test, workbook);
					
					extent.endTest(test);
					extent.flush();
				}
				
				@Test(priority = 128)
				void UserWithoutData() throws InterruptedException, IOException
				{
					test = extent.startTest("User Master-  without data verification");
					
					
					MethodsPOM.UserWithoutData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
				@Test(priority = 129)
				void UserInvalidData() throws InterruptedException, IOException
				{
					test = extent.startTest("User Master-  Invalid data verification");
					
					
					MethodsPOM.UserInvalidData(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
			@Test(priority =130)
				void UserTwoManadatoryFields() throws InterruptedException, IOException
				{
					test = extent.startTest("User Master- Two manadatory fields verification");
					
					
					MethodsPOM.UserTwoManadatoryFields(driver, test);
					
					extent.endTest(test);
					extent.flush();
				}
			@Test(priority = 131)
			void Masters3() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Opponent  verification");
				
				
				MethodsPOM.Opponent(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}

			@Test(priority =132)
			void OpponentWithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Opponent Masters -Without Data verification");
			
				MethodsPOM.OpponentWithoutData(driver, test);
			   extent.endTest(test);
			  extent.flush();
		   }
		  @Test(priority =133)
		  void OpponentInvalidData() throws InterruptedException, IOException
		  {
			  test = extent.startTest("Opponent Masters -Invalid Data verification");


			  MethodsPOM.OpponentInvalidData(driver, test);

			  extent.endTest(test);
			  extent.flush();
		  }
		  @Test(priority = 134)
			void Masters4() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Court  verification");
				
				MethodsPOM.Court(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
		 
		 
		 @Test(priority =135)
			void CourtWithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Court Master- Without enter Data verification");
				
				
				MethodsPOM.CourtWithoutData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
			@Test(priority = 136)
			void CourtInvalidData() throws InterruptedException, IOException
			{
				test = extent.startTest("Court Master- Enter Invalid Data verification");
				
				
				MethodsPOM.CourtInvalidData(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
			@Test(priority = 137)
			void CourtTwomanadatoryFields() throws InterruptedException, IOException
			{
				test = extent.startTest("Court Master- Enter Two Manadtory Fields verification");
				
				
				MethodsPOM.CourtTwomanadatoryFields(driver, test);
				
				extent.endTest(test);
				extent.flush();
			}
			@Test(priority = 138)
			void Masters5() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Case/NoticeType  verification");
				
				
				MethodsPOM.CaseNoticeType(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
			
			@Test(priority = 139)
			void CaseNoticeTypeWithoutData() throws InterruptedException, IOException
			{
				test = extent.startTest("Case/NoticeType Master -Without Enter Data  verification");

				MethodsPOM.CaseNoticeTypeWithoutData(driver, test);
				extent.endTest(test);
				extent.flush();
			}
			@Test(priority = 140)
			void CaseNoticeTypeInvaliData() throws InterruptedException, IOException
			{
				test = extent.startTest("Case/NoticeType Master - Enter Invalid Data  verification");
				MethodsPOM.CaseNoticeTypeInvaliData(driver, test);
		        extent.endTest(test);
		        extent.flush();
		    }
			@Test(priority = 141)
			void Masters6() throws InterruptedException, IOException
			{
				test = extent.startTest("Masters - Payment Type  verification");
				
				
				MethodsPOM.PaymentType(driver, test, workbook);
				
				extent.endTest(test);
				extent.flush();
			}
			
			@Test(priority = 142)
		    void PaymentTypeWithouData() throws InterruptedException, IOException
		   {
		       test = extent.startTest("Payment Type Master- Without Enter Data  verification");


		       MethodsPOM.PaymentTypeWithoutData(driver, test);

		        extent.endTest(test);
		        extent.flush();
		   }
		@Test(priority = 143)
		void PaymentTypeInvalidData() throws InterruptedException, IOException
		{
		 test = extent.startTest("Payment Type Master-Enter Invalid Data  verification");


		 MethodsPOM.PaymentTypeInvalidData(driver, test);

		  extent.endTest(test);
		  extent.flush();
		}
		@Test(priority = 145)
		void Masters7() throws InterruptedException, IOException
		{
			test = extent.startTest("Masters - Custom Parameter  verification");
			
			
			MethodsPOM.customParameter(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}

	@Test(priority = 146)
		void customParameterWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Custom Parameter Master -Without Enter Data  verification");

		
			CFOMethod.customParameterWithoutData(driver, test);
		
			extent.endTest(test);
			extent.flush();
		}
	     @Ignore
		@Test(priority = 147)
		void customParameterInvalidData() throws InterruptedException, IOException
		{
			test = extent.startTest("Custom Parameter Master -Enter Invalid Data verification");

		
			CFOMethod.customParameterInvalidData(driver, test);
		
			extent.endTest(test);
			extent.flush();
		}
	 	@Test(priority = 148)
		void Masters8() throws InterruptedException, IOException
		{
			test = extent.startTest("Masters - Case Stage  verification");
			
			
			MethodsPOM.CaseStage(driver, test, workbook);
			
			extent.endTest(test);
			extent.flush();
		}
		
		@Test(priority =149)
		void CaseStageWithoutData() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Stage Masters - Without Enter Data  verification");
		
			
			MethodsPOM.CaseStageWithoutData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 150)
		void CaseStageInvalidData() throws InterruptedException, IOException
		{
			test = extent.startTest("Case Stage Masters - Enter Invalid Data  verification");
		
			
			MethodsPOM.CaseStageInvalidData(driver, test);
			
			extent.endTest(test);
			extent.flush();
		}
	@Test(priority = 151)
	void Masters9() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Document Type  verification");
		
		
		MethodsPOM.DocumentType(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 152)
	void DocumentTypeWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Document Type Masters-Without data  verification");
		
		
		MethodsPOM.DocumentTypeWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority =153)
	void DocumentTypeInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Document Type Masters-Enter Invalid Data verification");
		
		
		MethodsPOM.DocumentTypeInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 154)
	void Masters10() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Rating Criteria  verification");
		
		
		MethodsPOM.RatingCriteria(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 155)
	void RatingCriteriaWithoutData() throws InterruptedException, IOException
	{
		test = extent.startTest("Rating Criteria Masters-Without Enter Data  verification");
	
		
		MethodsPOM.RatingCriteriaWithoutData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 156)
	void RatingCriteriaInvalidData() throws InterruptedException, IOException
	{
		test = extent.startTest("Rating Criteria Masters-Enter Invalid Data  verification");
	
		
		MethodsPOM.RatingCriteriaInvalidData(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}		
	@Test(priority = 157)
	void Masters11() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - PageAuthorization   verification");
		
		
		MethodsPOM.PageAuthorization(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority = 158)
	void AnnualBudget() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Annual Budget verification");
		
		
		MethodsPOM.AnnualBudget(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
@Test(priority =159)
void ExistingAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Existing Annual Budget verification");
	
	
	MethodsPOM.AnnualBudget(driver, test, workbook);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 160)
void UpdateAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Update Annual Budget verification");
	
	
	MethodsPOM.UpdateAnnualBudget(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 161)
void DeleteAnnualBudget() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Delete Annual Budget verification");
	
	
	MethodsPOM.DeleteAnnualBudget(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 162)
void WithoutEnterFY() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Without Enter FY verification");
	
	
	MethodsPOM.WithoutEnterFY(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority =163)
void Masters12() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Advocate Bill Approver  verification");
	
	
	MethodsPOM.AdvocateBillApprover(driver, test);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 164)
void Masters13() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - UserReassignment  verification");

	MethodsPOM.UserReassignment(driver, test);
	
	extent.endTest(test);
	extent.flush();
}


@Test(priority =165)
void Masters14() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Notice Stage  verification");
	
	
	MethodsPOM.NoticeStage(driver, test, workbook);
	
	extent.endTest(test);
	extent.flush();
}
@Test(priority = 166)
void Masters15() throws InterruptedException, IOException
{
	test = extent.startTest("Masters - Mail Authorization  verification");
	
	
	MethodsPOM.MailAuthorization(driver,test);
	
	extent.endTest(test);
	extent.flush();
}
@AfterMethod

void Close()
{
	 driver.close(); 
}				 	
	
	

}
