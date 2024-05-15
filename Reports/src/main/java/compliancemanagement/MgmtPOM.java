package compliancemanagement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MgmtPOM 
{
		private static WebElement mgmt = null;				//WebElement variable created for 'Categories' click
		private static List<WebElement> mgmtList = null;
		
		
		public static WebElement clickMyReport(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='leftdocumentsmenu1']/a/span[1]"));
			return mgmt;
		}
		public static WebElement clickUserLogReport(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='Userlogreport']/a"));
			return mgmt;
		}
		public static WebElement clickUserFilter(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='example']/div[1]/div/div"));
			return mgmt;
		}
		public static WebElement selectUser(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//span[normalize-space()='Finance Executive']"));
			return mgmt;
		}
		public static WebElement selectUser1(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//span[normalize-space()='Finance Manager']"));
			return mgmt;
		}
		public static WebElement clickapplybtn(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//button[@id='Applybtn']"));
			return mgmt;
		}
		public static WebElement clickExport(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='exportAdvanced']"));
			return mgmt;
		}
		public static WebElement clickLeaveReport(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='Myreport3']/a"));
			return mgmt;
		}
		public static WebElement clickUserFilter1(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='example']/div[1]/div[1]/div/div"));
			return mgmt;
		}
		public static WebElement clickLeaveapplybtn(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//button[@id='btnApply']"));
			return mgmt;
		}
		public static WebElement clickStartDateCalender(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//span[@class='k-icon k-i-calendar']"));
			return mgmt;
		}
		public static WebElement clickDate(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.linkText("9"));
			return mgmt;
		}
		public static WebElement clickStartDatepiker(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='startDatePicker']"));
			return mgmt;
		}
		public static WebElement clickDate1(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.linkText("11"));
			return mgmt;
		}
		public static WebElement clickEndDatepiker(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='endDatePicker']"));
			return mgmt;
		}
		public static WebElement clickEndDateCalender(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("(//span[@class='k-icon k-i-calendar'])[2]"));
			return mgmt;
		}
		public static WebElement clickClearBtn(WebDriver driver)			//Searching 'Open' Notice link
		{
			
			mgmt = driver.findElement(By.xpath("//*[@id='ClearfilterMain']"));
			return mgmt;
		}
		
}
