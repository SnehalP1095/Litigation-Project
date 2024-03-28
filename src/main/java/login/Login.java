package login;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import performer.OverduePOM;

public class Login 
{
	public static WebDriver driver = null;				//WebDriver instance created
	public static WebElement upload = null;				//WebElement to get upload button
	
	public static void BrowserSetup(String URL)
	{

		
			//WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();					//Created new Chrome driver instance. 
		
			//WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver","E:\\eclips-projects\\Selenium\\chromedriver-win32\\chromedriver.exe");
		  	 
			WebDriverManager.chromedriver().setup();		
		   driver = new ChromeDriver();		
				
			
			//WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
		
			//	WebDriverManager.operadriver().setup();
			//	driver = new OperaDriver();
		
		  /*  System.setProperty("webdriver.opera.driver", "E:\\eclips-projects\\Selenium\\operadriver_win64 -121 version\\operadriver.exe");
		    OperaOptions operaOptions =new OperaOptions();
		    operaOptions.setBinary("C:\\Program Files\\OperaBroswer\\opera.exe");
			driver = new OperaDriver(operaOptions);*/
		
			driver.manage().window().maximize();			//Set window size to maximum.
			driver.get(URL);								//Set the URL of WebApplication.
	}
	
	
	public static void BrowserSetup1(String URL,String browser) throws Exception
	{
		
		
		if(browser.equalsIgnoreCase("firefox"))
		{
			 //create firefox instance
				System.setProperty("webdriver.gecko.driver", "E:\\eclips-projects\\Selenium\\geckodriver-v0.33.0-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("chrome"))
			{
				//set path to chromedriver.exe
				System.setProperty("webdriver.chrome.driver","E:\\eclips-projects\\Selenium\\chromedriver-win32\\chromedriver.exe");
				//create chrome instance
				driver = new ChromeDriver();
			
			}
			else if(browser.equalsIgnoreCase("Opera"))
			{
				//set path to chromedriver.exe
				System.setProperty("webdriver.opera.driver","E:\\eclips-projects\\Selenium\\operadriver_win32\\operadriver.exe");
				//create chrome instance
				driver = new OperaDriver();
			
			}
			//Check if parameter passed as 'Edge'
			else if(browser.equalsIgnoreCase("Edge")) 
			{
			     //set path to Edge.exeMicrosoftWebDriver
			     WebDriverManager.edgedriver().setup();
			     //System.setProperty("webdriver.edge.driver","C:\\Users\\Admin\\Desktop\\eclips-projects\\Selenium\\EdgeDriver\\msedgedriver.exe");
			     //create Edge instance
				 driver = new EdgeDriver();
			}
			else
			{
						//If no browser passed throw exception
						throw new Exception("Browser is not correct");
			}
							 
							 
		               driver.manage().window().maximize();			//Set window size to maximum.
		                driver.get(URL);
		
	}
	
	public static WebDriver UserLogin(String username, String password, String method) throws InterruptedException
	{		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		
		LoginPOM.setUname(driver).sendKeys(username);		//Sent username to input box 
		Thread.sleep(500);
		LoginPOM.setPassword(driver).sendKeys(password);	//Sent password to input box
		LoginPOM.clickSubmit(driver).click();				//Clicked on Sign-in button
		Thread.sleep(500);
		if(!username.equalsIgnoreCase("performer@avantis.info"))
		{
			try
			{
				Thread.sleep(500);
				wait1.until(ExpectedConditions.visibilityOf(LoginPOM.clickQALink(driver)));
				wait1.until(ExpectedConditions.elementToBeClickable(LoginPOM.clickQALink(driver)));
				LoginPOM.clickQALink(driver).click();				//Clicking on QA Link instead of OTP.
				
				//----------------------------------------------------------------------------//
				
				wait1.until(ExpectedConditions.invisibilityOf(LoginPOM.clickQALink(driver)));
		
			
		 Thread.sleep(1000);
			wait1.until(ExpectedConditions.visibilityOf(LoginPOM.Question1(driver)));
			wait1.until(ExpectedConditions.elementToBeClickable(LoginPOM.Question1(driver)));
			String que1 = LoginPOM.Question1(driver).getText();	//Storing the question in que variable.
			String ans1 = null;
			
			if(method.equalsIgnoreCase("cfo"))
			{
				ans1 = getAnswerCFO(que1);						//Storing the answer in ans variable.
			}
			else if(method.equalsIgnoreCase("fe"))
			{
				ans1 = getAnswerFE(que1);						//Storing the answer in ans variable.
			}
			else if(method.equalsIgnoreCase("cfo-diy"))
			{
				ans1 = "123";						//Storing the answer in ans variable.
			}
			
			else if(method.equalsIgnoreCase("company"))
			{
				ans1 = "123";						//Storing the answer in ans variable.
			}
			else if(method.equalsIgnoreCase("Implementation"))
			{
				ans1 = "123";						//Storing the answer in ans variable.
			}
			
			else
			{
				ans1 = getAnswer(que1);							//Storing the answer in ans variable.
			}
			
			if(ans1.equalsIgnoreCase("birthplace"))
				LoginPOM.Answer1(driver).sendKeys("place");		//Sending answer to the input box.
			else
				LoginPOM.Answer1(driver).sendKeys(ans1);		//Sending answer to the input box.
			Thread.sleep(1000);
			
			//----------------------------------------------------------//
			
			String que2 = LoginPOM.Question2(driver).getText();	//Storing the question in que variable.
			String ans2 = null;
			if(method.equalsIgnoreCase("cfo"))
			{
				ans2 = getAnswerCFO(que2);						//Storing the answer in ans variable.
			}
			else if(method.equalsIgnoreCase("fe"))
			{
				ans2 = getAnswerFE(que2);						//Storing the answer in ans variable.
			}
			else if(method.equalsIgnoreCase("cfo-diy"))
			{
				ans2 = "123";						//Storing the answer in ans variable.
			}
			
			else if(method.equalsIgnoreCase("company"))
			{
				ans2 = "123";						//Storing the answer in ans variable.
			}
			else if(method.equalsIgnoreCase("Implementation"))
			{
				ans2 = "123";						//Storing the answer in ans variable.
			}
			else
			{
				ans2 = getAnswer(que2);							//Storing the answer in ans variable.
			}
			
			
			if(ans2.equalsIgnoreCase("birthplace"))
				LoginPOM.Answer2(driver).sendKeys("place");		//Sending answer to the input box.
			else
				LoginPOM.Answer2(driver).sendKeys(ans2);		//Sending answer to the input box.
			Thread.sleep(100);
			
			LoginPOM.SubmitAnswer(driver).click();				//Clicking on Submit button.
		
			}
			catch(Exception e)
			{
				
			}
			
		}
		
		
		
		if(!method.equalsIgnoreCase("Implementation"))
		{
			//wait1.until(ExpectedConditions.elementToBeClickable(LoginPOM.clickComplicane(driver)));
			
			if(method.equalsIgnoreCase("License"))
			{
				LoginPOM.clickLicense(driver).click();				//Clicking on Litigation Image.
			}
			else if(method.equalsIgnoreCase("Litigation"))
			{
				//Thread.sleep(8000);
				LoginPOM.ClickLitigation(driver).click();			//Clicking on Litigation Image.
			}
			else if(method.equalsIgnoreCase("Contract"))
			{
				LoginPOM.ClickContract(driver).click();			//Clicking on Litigation Image.
			}
			else
			{
				LoginPOM.ClickLitigation(driver).click();	
				//LoginPOM.clickComplicane(driver).click();			//Clicking on Compliance Image.
			}
			
			try
			{
				Thread.sleep(500);
				if(OverduePOM.closeMessage(driver).isDisplayed())	//If Compliance Updation message popped up,
				{
					wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.closeMessage(driver)));
					OverduePOM.closeMessage(driver).click();		//then close the message.
				}
			}
			catch(Exception e)
			{
				
			}
		}		
		return driver;
	}
	
	public static String getAnswer(String que)				//Method created to extract last word from question
	{														//as it is the answer of the question.
		String last = que.substring(que.lastIndexOf(" "));		//We are selecting word after last " ".
		int len = last.length();							
		String ans = last.substring(1, len-1);				//We are neglecting letters from string of position first " " and last "?"
		return ans.toLowerCase();							//Returning answer and converting to LowerCase too.  
	}
	
	public static String getAnswerCFO(String que)			//Method created to extract last word from question
	{														//as it is the answer of the question.
		String last = que.substring(que.lastIndexOf(" "));	//We are selecting word after last " ".
		int len = last.length();							
		String ans = last.substring(1, len-1);				//We are neglecting letters from string of position first " " and last "?"
		if(ans.equalsIgnoreCase("pet"))
			ans = "dog";
		if(ans.equalsIgnoreCase("car"))
			ans = "red";
		return ans.toLowerCase();							//Returning answer and converting to LowerCase too.  
	}
	public static String getAnswerFE(String que)			//Method created to extract last word from question
	{														//as it is the answer of the question.
		String last = que.substring(que.lastIndexOf(" "));	//We are selecting word after last " ".
		int len = last.length();							
		String ans = last.substring(1, len-1);				//We are neglecting letters from string of position first " " and last "?"
		if(ans.equalsIgnoreCase("pet"))
			ans = "dog";
		if(ans.equalsIgnoreCase("car"))
			ans = "red";
		return ans.toLowerCase();							//Returning answer and converting to LowerCase too.  
	}
	public static WebDriver UserLogin1(String username, String password, String method) throws InterruptedException
	{		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		
		LoginPOM.setUname(driver).clear();
		LoginPOM.setUname(driver).sendKeys(username);		//Sent username to input box 
		Thread.sleep(500);
		LoginPOM.setPassword(driver).sendKeys(password);	//Sent password to input box
		LoginPOM.clickSubmit(driver).click();				//Clicked on Sign-in button
		
		return driver;
    }
	
	  public static WebDriver forgotPassword() throws InterruptedException, IOException
		
		{
	
		  
		LoginPOM.ClickForgotPass(driver).click();
		
		//LoginPOM.ClickEmailid(driver).sendKeys("");
		
		Thread.sleep(2000);
		LoginPOM.ClickSubmit(driver).click();
		Thread.sleep(2000);
		LoginPOM.ClickBackButton(driver).click();
		return driver;
	}
	  
	  public static void AccountLocked(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		
	
		Thread.sleep(2000);
		LoginPOM.ClickAccountLocked(driver).click();
		
		//LoginPOM.ClickEmailid(driver).sendKeys("");
		Thread.sleep(2000);
		LoginPOM.ClickSubmit(driver).click();
		Thread.sleep(2000);
		LoginPOM.ClickBackButton1(driver).click();
	}
	  public static void Google(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		
		
		LoginPOM.ClickGoogle(driver).click();
		
		//LoginPOM.ClickEmailid(driver).sendKeys("");
		//LoginPOM.ClickSubmit(driver).click();
		//LoginPOM.ClickBackButton(driver).click();
	}
	  public static void LoginHelp(WebDriver driver,ExtentTest test, String type) throws InterruptedException, IOException
		
		{
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		
		LoginPOM.ClickLoginHelp(driver).click();
		
		
	}
}
