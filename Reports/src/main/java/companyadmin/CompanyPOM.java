package companyadmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompanyPOM 
{
	
	private static WebElement admin = null;				//WebElement variable created for 'Categories' click
	
	private static List<WebElement> adminList = null;
	
	
	public static WebElement clickMyAdmin(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//*[@id='leftApprovermenu']/a/span[1]"));
		return admin;
	}
	public static WebElement clickReport(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//*[@id='CMPMenuBar']/ul/li[2]/a"));
		return admin;
	}
	public static WebElement clickUserLogReport(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//*[@id='CMPMenuBar:submenu:3']/li[10]/a"));
		return admin;
	}
	public static WebElement clickAlertpopup(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//*[@id='divbnl']/div/div/div[1]/button"));
		return admin;
	}
	public static WebElement clickNotificatonpopup(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//*[@id='divNotification']/div/div/div[1]/button"));
		return admin;
	}
	
	public static WebElement clickUsers(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']"));
		return admin;
	}
	public static WebElement clickExport(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//*[@id='exportAdvanced']"));
		return admin;
	}
	public static WebElement clickUserName(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//span[normalize-space()='abc External']"));
		return admin;
	}
	public static WebElement clickUserName1(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("(//span[normalize-space()='abc lawyer'])[1]"));
		return admin;
	}
	public static WebElement clickAppyButton(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		admin = driver.findElement(By.xpath("//button[@id='Applybtn']"));
		return admin;
	}
	

}
