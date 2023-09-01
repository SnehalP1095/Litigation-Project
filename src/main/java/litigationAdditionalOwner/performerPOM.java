package litigationAdditionalOwner;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class performerPOM 
{
	private static WebElement litigation = null;		
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting Status-Asc/Desc (Status shows multiple elements back side)

	
	
	public static WebElement clickNoticeOpen(WebDriver driver)			//Searching 'Open' Notice link
	{
		
		litigation = driver.findElement(By.id("ContentPlaceHolder1_divOpenNoticeCount"));
		return litigation;
	}
	
	public static WebElement clickNew(WebDriver driver)					//Searching 'New'
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_AddNewCaseNotice']"));
		return litigation;
	}
	
	public static WebElement clickDated(WebDriver driver)				//Searching 'Dated' input box
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtNoticeDate']"));
		
		return litigation;
	}
	
	public static WebElement clickFinancialYear(WebDriver driver)
	{//Searching 'Financial Year' drop down
	
		WebDriverWait wait = new WebDriverWait(driver,(30));
	    litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[1]")));
		//litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[1]"));
		return litigation;
	}
	
	public static List<WebElement> chooseDropDownOption(WebDriver driver)	//Searching drop down in 'Financial Year'
	{
		//elementsList = driver.findElements(By.xpath("//*[@id='pnlNotice']/div[1]/div[2]/div[2]/div[2]/span[1]/div/ul/li/a/label"));
		elementsList = driver.findElement(By.xpath("(//*[@class='multiselect-container dropdown-menu'])[1]")).findElements(By.tagName("li"));
		return elementsList;
	}
	
	public static WebElement clickAct(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[2]"));
		return litigation;
	}
	
	public static List<WebElement> chooseAct(WebDriver driver)
	{
    //	div.findElement(By.className("multiselect-container dropdown-menu")).findElement(By.className("chosen-results")).findElements(By.tagName("li"));
		//elementsList = driver.findElements(By.xpath("//*[@id='pnlNotice']/div[5]/div[1]/div[1]/span[1]/div/ul/li/a/label"));
		elementsList=driver.findElement(By.xpath("//*[@id=\"pnlNotice\"]/div[4]/div[1]/div[1]/span[1]/div/ul")).findElements(By.tagName("li"));
		
		return elementsList;
	}
	
	public static List<WebElement> chooseAct1(WebDriver driver)
	{
		//elementsList = driver.findElements(By.xpath("//*[@id='pnlCase']/div[4]/div[1]/div[1]/span[1]/div/ul/li/a/label"));
		//elementsList=driver.findElement(By.xpath("//*[@id=\"pnlCase\"]/div[4]/div[1]/div[1]/span[1]/div/ul")).findElements(By.tagName("li"));
		elementsList=driver.findElements(By.xpath("//*[@id='pnlCase']/div[5]/div[1]/div[1]/span[1]/div/ul/li/a/label"));
		return elementsList;
	}
	
	public static WebElement clickRefNo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxRefNo']"));
		return litigation;
	}
	
	public static WebElement clickNoticeType(WebDriver driver)
	{
		//litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeCategory_chosen']"));
		litigation = driver.findElement(By.id("rbNoticeInOutType_chosen"));
		
		return litigation;
	}
	
	public static WebElement chooseNoticeType(WebDriver driver)
	{
		  // elementsList =  driver.findElement(By.className("chosen-drop")).findElement(By.className("chosen-results")).findElements(By.tagName("li"));
		   litigation= driver.findElement(By.xpath("//*[@id='rbNoticeInOutType_chosen']/div/ul/li[2]"));
		   		
		   return litigation;
		}
	
	
	public static WebElement clickUnderSection(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxSection']"));
		return litigation;
	}
	
	public static WebElement clickNoticeCategory(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeCategory_chosen']"));
		return litigation;
		
		
	}
	
	public static WebElement chooseCategory(WebDriver driver)
	{
		//elementsList = clickNoticeCategory(driver).findElement(By.className("chosen-drop")).findElement(By.className("chosen-results")).findElements(By.tagName("li"));
		litigation=driver.findElement(By.xpath("//*[@id='ddlNoticeCategory_chosen']/div/ul/li[1]"));
		return litigation;
	}
	
	
	
	
	public static WebElement clickSearch(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeCategory_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickOpponentcfo(WebDriver driver)
	{
		//litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[3]"));
		
		 litigation=driver.findElement(By.xpath("//*[@id='divOpponentAndOpposition']/div[1]/div[1]/span[1]/div/button"));
		  return litigation;
	}
	public static WebElement clickOpponent(WebDriver driver)
	{
		//litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[3]"));
		
		 litigation=driver.findElement(By.xpath(("(//*[@id='divOpponentAndOpposition']/div[1]/div[1]/span[1]/div/button)")));
		  return litigation;
	}
	public static WebElement chooseOpponent(WebDriver driver)
	{
		//elementsList = driver.findElement(By.xpath("(//*[@class='multiselect-container dropdown-menu'])[3]")).findElements(By.tagName("li"));
		litigation=driver.findElement(By.xpath("//*[@id='divOpponentAndOpposition']/div[1]/div[1]/span[1]/div/ul/li/a/label/input"));
		return litigation;
	}
	
	public static WebElement deleteOpponent(WebDriver driver)
	{
		
		litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdLCParty_LinkButton2_0']/img"));
		return litigation;
	}
	
	
	public static WebElement clickOppLawyer(WebDriver driver)
	{    
		
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[4]"));
		return litigation;
	}
	
	public static WebElement clickSearch1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[1]/div[1]/span[1]/div/ul/li[1]/div/input"));
		return litigation;
	}
	
	public static WebElement clickSearch2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[2]/span/div/ul/li[1]/div/input"));
		return litigation;
	}
	
	public static WebElement clickSelectAll(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[1]/div[1]/span[1]/div/ul/li[2]/a/label"));
		return litigation;
	}
	
	public static WebElement clickSelectAll1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[2]/span/div/ul/li[2]/a/label"));
		return litigation;
	}
	
	public static WebElement clickNoticeTitle(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTitle']"));
		return litigation;
	}
	
	public static WebElement clickNoticeDescription(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxDescription']"));
		return litigation;
	}
	
	public static WebElement clickLocation(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxBranch']"));
		return litigation;
	}
	
	public static WebElement clickPlus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tvBranchesn1']"));
		return litigation;
	}
	
	public static WebElement selectLocation(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[contains(@id,'tvBranchest1')]"));
		
		return litigation;
	}
	
	public static WebElement clickJurisdiction(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlJurisdiction_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch3(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlJurisdiction_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickDepartment(WebDriver driver)
	{
		 WebDriverWait wait=new WebDriverWait(driver,40);
		 litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlDepartment_chosen']")));
		//litigation = driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch4(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickContactDept(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCPDepartment_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch5(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCPDepartment_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickNoticeTerm(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxNoticeTerm']"));
		return litigation;
	}
	
	public static WebElement clickOwner(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlOwner_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch6(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlOwner_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickWinningProspect(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']/a"));
		return litigation;
	}
	
	public static WebElement clickWinningProspect1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseRisk_chosen']/a"));
		return litigation;
	}
	
	//*[@id="ddlNoticeRisk_chosen"]
	public static WebElement clickRisk(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']"));
		return litigation;
	}
	public static WebElement clickSapCode(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxSapcode']"));
		return litigation;
	}
	
	public static WebElement selectRisk(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement selectRisk1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseRisk_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement clickNoticeBudget(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxNoticeBudget']"));
		return litigation;
	}
	
	public static WebElement clickClaimedAmount(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxClaimedAmt']"));
		return litigation;
	}
	
	public static WebElement clickState(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlState_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchState(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlState_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickProbableAmount(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxProbableAmt']"));
		return litigation;
	}
	
	public static WebElement clickProvisionalAmount(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtprovisionalamt']"));
		return litigation;
	}
	
	public static WebElement clickProtestMoney(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtprotestmoney']"));
		return litigation;
	}
	
	public static WebElement clickRisk1(WebDriver driver)
	{
		//litigation = driver.findElement(By.xpath("//div[@id='ddlRisk_chosen']"));
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseRisk_chosen']"));
		return litigation;
	}
	
	public static WebElement selectRisk2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@id='ddlRisk_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement clickPotentialImpactRadio(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='rblPotentialImpact_0']"));
		return litigation;
	}
	
	public static WebElement clickMonetary(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxMonetory']"));
		return litigation;
	}
	
	public static WebElement clickLawFirm(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlLawFirm_chosen']"));
		return litigation;
	}
	
	public static WebElement chooseLawFirm(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlLawFirm_chosen']/div/div/input"));
		return litigation;
	}
	
	
	
	
	
	
	 public  static WebElement selectNoticeUploadDocument(WebDriver driver) throws InterruptedException 
	    {
	  	  
	  	  WebDriverWait wait = new WebDriverWait(driver, 100);
	        WebElement NoticeUploadDocument = wait.until(ExpectedConditions.elementToBeClickable(By.id("FileUpLoad1")));
	  	  NoticeUploadDocument.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
	  	  Thread.sleep(3000);
	  	    return litigation;
	    }
	 
	  public  static WebElement selectNoticeRecipetDate(WebDriver driver)
      {
 	
         WebElement openDate=driver.findElement(By.id("txtNoticeReceiptDate"));
         return openDate;
        
      }
	 
	public static WebElement clickInternalUser(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[5]"));
		return litigation;
	}
	
	public static List<WebElement> chooseInternalUser(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlNoticeAssignment']/div[1]/div/span[1]/div/ul/li/a/label/input"));
		return elementsList;
	}
	
	public static List<WebElement> chooseInternalUser1(WebDriver driver)
	{
		//elementsList = driver.findElements(By.xpath("//*[@id='pnlCaseAssignment']/div[1]/div/span[1]/div/ul/li/a/label"));
		
		//elementsList = driver.findElements(By.xpath("//*[@id='pnlCaseAssignment']/div[1]/div/span[1]/div/button"));
		
		elementsList = driver.findElements(By.xpath("//*[@id='pnlCaseAssignment']/div[1]/div/span[1]/div/ul/li/a/label/input"));
		//elementsList=driver.findElement(By.xpath("(//*[@class='multiselect-container dropdown-menu'])[5]")).findElements(By.tagName("li"));
		return elementsList;
	}
	
	public static WebElement clickLawyer(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[6]"));
		return litigation;
	}
	
	public static List<WebElement> chooseLawyer(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='UpdatePanel6']/div/span/div/ul/li/a/label"));
		return 		elementsList;
	}
	
	public static WebElement readTotal(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']/div[3]/span"));
		return litigation;
	}
	
	public static WebElement readMessage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSNoticePopup']"));
		return litigation;
	}
	public static WebElement readMessageCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSCasePopup']"));
		return litigation;
	}
	public static WebElement readMessageCase1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSCasePopup']/ul"));
		return litigation;
	}
	public static WebElement readInvalidMessage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSNoticePopup']/ul"));
		return litigation;
	}
	
	public static WebElement readMessage1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSCasePopup']"));
		return litigation;
	}
	public static WebElement CaseInvalidreadMessage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSCasePopup']/ul/li"));
		return litigation;
	}
	
	public static WebElement clickClose(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
		return litigation;
	}
	
	public static WebElement clickLinkNotice(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkLinkNotice']"));
		return litigation;
	}
	
	public static WebElement clickLinkCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkLinkCase']"));
		return litigation;
	}
	
	public static WebElement clickViewDoc(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkActDetails']"));
		return litigation;
	}
	
	public static WebElement clickSendMail(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnSendMailPopup']"));
		return litigation;
	}
	public static WebElement clickSendMailCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkSendMailWithDoc']"));
		return litigation;
	}
	public static WebElement clickSendMail1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkSendMailWithDoc']"));
		return litigation;
	}
	
	public static WebElement clickEditNotice(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[18]/a[1]"));
		return litigation;
	}
	public static WebElement clickEditnotice(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//a[@class='k-button k-button-icontext ob-edit k-grid-edit'])[2]"));
		return litigation;
	}
	public static WebElement clickEditNotice1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnEditNoticeDetail']"));
		return litigation;
	}
	public static WebElement clickViewNoticeDoc(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ViewNoticeDoc']"));
		return litigation;
	}
	public static WebElement clickViewNoticeDocpopup(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdComplianceDocument_lblViewFile_0']"));
		return litigation;
	}
	
	public static WebElement clickViewNoticeDocpopupclose(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='divNoticeDocumentShowDialog']/div/div/div[1]/button"));
		return litigation;
	}
	public static WebElement clickViewNoticeDocpopupclose1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='divViewDocument']/div/div/div[1]/button"));
		return litigation;
	}
	
	
	public static WebElement clickDownloadNoticeDoc(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lblDownLoadfile']"));
		return litigation;
	}
	public static WebElement clickDownloadNoticeDocpopup(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdComplianceDocument_lblDownLoadfile_0']"));
		return litigation;
	}
	
	public static WebElement clickEditCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnEditCaseDetail']"));
		return litigation;
	}
	
	public static WebElement clickNoticeClosed(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedNoticeCount']"));
		return litigation;
	}
	
	public static WebElement clickExcelReport(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='exportReport']"));
		return litigation;
	}
	public static WebElement clickExcelReport1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='exportReport2']"));
		return litigation;
	}
	
	public static WebElement clickCaseOpen(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOpenCaseCount']"));
		return litigation;
	}
	public static WebElement clickCaseOpencfo(WebDriver driver)
	{
		//litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOpenCaseCountRPA']"));
		litigation = driver.findElement(By.id("ContentPlaceHolder1_divOpenCaseCount"));
		//litigation = driver.findElement(By.xpath("(//div[@class='count'])[3]"));
		return litigation;
	}
	public static WebElement clickCaseskipfo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnSkip']"));
		return litigation;
	}
	
	public static WebElement clickCaseDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtCaseDate']"));
		return litigation;
	}
	
	public static List<WebElement> clickFinanceSearchCheckbox(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlCase']/div[1]/div[2]/div[2]/div[2]/span[1]/div/ul/li/a/label"));
		
		return elementsList;
	}
	
	public static WebElement clickFinancialYear1(WebDriver driver)
	{//Searching 'Financial Year' drop down
	
		WebDriverWait wait = new WebDriverWait(driver,(30));
	    litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[1]")));
		//litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[1]"));
		return litigation;
	}
	
	public static List<WebElement> chooseDropDownOption1(WebDriver driver)	//Searching drop down in 'Financial Year'
	{
		//elementsList = driver.findElements(By.xpath("//*[@id='pnlNotice']/div[1]/div[2]/div[2]/div[2]/span[1]/div/ul/li/a/label"));
		elementsList = driver.findElement(By.xpath("(//*[@class='multiselect-container dropdown-menu'])[1]")).findElements(By.tagName("li"));
		return elementsList;
	}
	
	
	public static WebElement clickInternalCaseNo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxInternalCaseNo']"));
		return litigation;
	}
	
	public static WebElement clickCaseCategory(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseCategory_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchCaseCategory(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseCategory_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickCaseType1(WebDriver driver) 
	{
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//WebElement CaseType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rbCaseInOutType_chosen")));
		litigation = driver.findElement(By.id("rbCaseInOutType_chosen"));
		return litigation;
	}
	
	public static WebElement chooseCaseType(WebDriver driver)	//Searching drop down in 'case type'
	{
		litigation=driver.findElement(By.xpath("//*[@id='rbCaseInOutType_chosen']/div/ul/li[2]"));

		return litigation;
	}
	
	
	public static WebElement clickCaseBudget(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCaseBudget']"));
		return litigation;
	}
	
	public static WebElement clickSearchBox(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@placeholder='Search'])[3]"));
		return litigation;
	}
	
	public static WebElement clickSearchBox1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@placeholder='Search'])[4]"));
		return litigation;
	}
	
	public static WebElement clickSelectAll2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlCase']/div[6]/div[1]/div[1]/span[1]/div/ul/li[2]/a/label"));
		return litigation;
	}
	
	public static WebElement clickSelectAll3(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@value='multiselect-all'])[4]"));
		return litigation;
	}
	
	public static WebElement clickCourt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCourt_chosen']/a"));
		return litigation;
	}
	
	public static WebElement clickSearchCourt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCourt_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickJudge(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxJudge']"));
		return litigation;
	}
	
	public static WebElement clickCaseClosed(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedCaseCount']"));
		return litigation;
	}
	public static WebElement clickCaseClosedCFO(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedCaseCount']"));
		return litigation;
	}
	public static WebElement clickTaskOpen(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOpenTaskCount']"));
		return litigation;
	}
	
	public static WebElement clickAddNewTask(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkAdd']"));
		return litigation;
	}
	
	public static WebElement clickTaskTitle(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskTitle']"));
		return litigation;
	}
	
	public static WebElement clickTaskDesc(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskDesc']"));
		return litigation;
	}
	
	public static WebElement clickDueDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskDueDate']"));
		return litigation;
	}
	
	public static WebElement clickPriority(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskPriorityADD_chosen']"));
		return litigation;
	}
	
	public static WebElement clickExpOutcome(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxExpOutcome']"));
		return litigation;
	}
	
	public static WebElement clickInternalUser1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskLawyerListInternal_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchInternalUser1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskLawyerListInternal_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickExternalUser(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskUserExternal_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchExternalUser(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskUserExternal_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickRemark(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskRemark']"));
		return litigation;
	}
	
	public static WebElement clickUpload(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='fuTaskDocUpload']"));
		return litigation;
	}
	
	public static WebElement clickMessage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VsAddTasValidateSuccess']"));
		return litigation;
	}
	public static WebElement clickMessagewithoutdata(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSCasePopup']/ul"));
		return litigation;
	}
	public static WebElement clickMessage1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VsAddTasValidate']/ul/li"));
		return litigation;
	}
	public static WebElement clickMessage2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VsAddTasValidate']/ul"));
		return litigation;
	}
	
	public static WebElement clickTaskClearBtn(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnTaskClear']"));
		return litigation;
	}
	public static WebElement clickTaskdelete(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[9]/a[2]"));
		return litigation;
	}
	
	public static WebElement clickClose1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@onclick='javascript:reloadTaskList();']"));
		return litigation;
	}
	
	public static WebElement clickTaskClosed(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedTaskCount']"));
		return litigation;
	}
	
	public static WebElement clickStatusDropDown(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='k-multiselect-wrap k-floatwrap'])[3]"));
		return litigation;
	}
	
	public static WebElement selectStatusDropDown(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[36]"));
		return litigation;
	}
	
	public static WebElement GridLoad(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']"));
		return litigation;
	}
	
	public static WebElement clickGridElement(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[2]/td[1]"));
		return litigation;
	}
	
	public static List<WebElement> clickAction(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
		return elementsList;
	}
	
	public static WebElement clickCheckBox(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdNoticeList_LinkNotice_chkRowLinkCases_0']"));
		return litigation;
	}
	
	public static WebElement clickApply(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//a[@id='lnkLinkCaseFilter']"));
		return litigation;
	}
	
	public static WebElement clickApply1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//a[@id='lnkLinkNoticeFilter']"));
		return litigation;
	}
	
	public static WebElement clickSave(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@value='Save']"));
		return litigation;
	}
	
	public static WebElement readMsg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsLinkCase']"));
		return litigation;
	}
	
	public static WebElement clickClosePopup(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='divLinkNoticePopup']/div/div/div[1]/button"));
		return litigation;
	}
	
	public static WebElement readRef(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdNoticeList_LinkNotice']/tbody/tr[2]/td[3]/div/span"));
		return litigation;
	}
	
	public static List<WebElement> readRef1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'grdLinkedNotices_lblCaseNo')]"));
		return elementsList;
	}
	
	public static WebElement clickMyWorkspace(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftworkspacemenu']"));
		return litigation;
	}
	
	public static WebElement clickStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div/span"));
		return litigation;
	}
	
	public static WebElement clickStatusPayments(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkNoticeStatusPayment']"));
		return litigation;
	}
	
	public static WebElement clickNoticeStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeStatus_chosen']"));
		return litigation;
	}
	
	public static WebElement clickClosedStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeStatus_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement clickCloseDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxNoticeCloseDate']"));
		return litigation;
	}
	
	public static WebElement clickNoticeResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeResult_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSelectResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeResult_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickRemark1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCloseRemark']"));
		return litigation;
	}
	
	public static WebElement clickSave1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//input[@id='btnSaveStatus']"));
		return litigation;
	}
	
	public static WebElement readMessage2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary3']"));
		return litigation;
	}
	
	public static WebElement clickCourtCaseNo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxAppealCaseNo']"));
		return litigation;
	}
	
	public static WebElement clickSaveConvertCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnSaveConvertCase']"));
		return litigation;
	}
	
	public static WebElement clickCaseStatusPayments(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkCaseStatus']"));
		return litigation;
	}
	
	public static WebElement clickCaseStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStatus_chosen']"));
		return litigation;
	}
	
	public static WebElement clickCaseStatusClose(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStatus_chosen']/div/ul/li[3]"));
		return litigation;
	}
	public static WebElement clickCaseStatus1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStatus_chosen']/div/ul/li[1]"));
		return litigation;
	}
	public static WebElement clickCaseAppealToNextCourt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//input[@id='btnCaseTransfer']"));
		return litigation;
	}
	public static WebElement clickCasereadMsg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary3']/ul/li"));
		return litigation;
	}
	public static WebElement clickCaseCloseDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCaseCloseDate']"));
		return litigation;
	}
	
	public static WebElement clickCaseResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseResult_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSelectCaseResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseResult_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickCaseStage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStage_chosen']"));
		return litigation;
	}
	
	public static WebElement selectCaseStage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStage_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickCaseCheckBox(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdCaseList_LinkCase_chkRowLinkCases_0']"));
		return litigation;
	}
	
	public static WebElement readCaseRef(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdCaseList_LinkCase']/tbody/tr[2]/td[3]/div/span"));
		return litigation;
	}
	
	public static List<WebElement> readCaseRef1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'grdLinkedCases_lblCaseNo')]"));
		return elementsList;
	}
	
	public static WebElement clickClosePopupCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='divLinkCasePopup']/div/div/div[1]/button"));
		return litigation;
	}
	
	public static WebElement clickMyReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftreportsmenu']"));
		return litigation;
	}
	public static WebElement clickMoreReports(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		
		litigation = driver.findElement(By.xpath("//*[@id='MoreReport']"));
		return litigation;
	}
	public static WebElement clicklocationFilterReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxFilterLocation']"));
		return litigation;
	}
	public static WebElement selectlocationFilterReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tvFilterLocationt8']"));
		return litigation;
	}
	public static WebElement selectlocationFilterReportscfo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tvFilterLocationt6']"));
		return litigation;
	}
	public static WebElement FromDateReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtFromDate']"));
		return litigation;
	}
	public static WebElement MISReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnMis']"));
		return litigation;
	}
	
	public static WebElement closedCasesReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnMisCloseReport']"));
		return litigation;
	}
	public static WebElement ExtLawyerPerformanceReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnLawyerPerformance']"));
		return litigation;
	}
	public static WebElement BudgetReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnBudgetVsExpenseTracking']"));
		return litigation;
	}
	public static WebElement LawyerDetailsReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnLawyerDetails']"));
		return litigation;
	}
	public static WebElement CasePaymentReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCasepayments']"));
		return litigation;
	}
	public static WebElement CaseHearingReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCaseHearning']"));
		return litigation;
	}
	public static WebElement CourtCaseReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCourtCases']"));
		return litigation;
	}
	public static WebElement CourtOrderReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCourtOrders']"));
		return litigation;
	}
	public static WebElement CourtDoumentReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCaseDocument']"));
		return litigation;
	}
	public static WebElement noticeCovertedToCaseReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnNoticeToCase']"));
		return litigation;
	}
	public static WebElement AllReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAll']"));
		return litigation;
	}
	
	public static WebElement clickNoticeReport(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkNotice']"));
		return litigation;
	}
	public static WebElement clickNoticePaymentReport(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnNoticepayments']"));
		return litigation;
	}
	public static WebElement clickNoticeResponseReport(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnNoticeResponse']"));
		return litigation;
	}
	
	
	
	
	
	public static WebElement selectFromDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[5]/a"));
		return litigation;
	}
	public static WebElement selectToDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[4]/a"));
		return litigation;
	}
	
	
	public static WebElement ToDateReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtToDate']"));
		return litigation;
	}
	public static WebElement AdvancedSearchReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='AdavanceSearch']"));
		return litigation;
	}
	public static WebElement startDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='Startdatepicker']"));
		return litigation;
	}
	
	public static WebElement endDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='Lastdatepicker']"));
		return litigation;
	}
	public static WebElement clickApplyButton(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ApplyBtnAdvanced']"));
		return litigation;
	}
	public static WebElement clickExportAdavanced(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='exportAdvanced']"));
		return litigation;
	}
	public static WebElement clickShowResponseDetails(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[22]/a[1]"));
		return litigation;
	}
	public static WebElement clickclosepopup(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
		return litigation;
	}
	
	public static WebElement clickviewNoticeDtails(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[22]/a[2]"));
		return litigation;
	}
	
	public static WebElement CheckRecordsTable(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']"));
		return litigation;
	}
	
	public static WebElement clickTypeDropdown(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@aria-owns='dropdownType_listbox']"));
		return litigation;
	}
	public static WebElement clickTypeDropdown1(WebDriver driver)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 100);
		litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[6]")));
		return litigation;
	}
	public static WebElement clickTypeDropdown2(WebDriver driver)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, 100);
		litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[15]")));
		//litigation = driver.findElement(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[15]"));
		return litigation;
	}
	public static WebElement clickTypeDropdown3(WebDriver driver)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, 100);
		litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divAdvanceSearchModel']/div[1]/div[1]/span/span/span[2]/span")));

		return litigation;
	}
	
	
	public static WebElement selectTypeCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownType_listbox']/li[2]"));
		return litigation;
	}
	
	public static WebElement selectTypeCase1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownType1_listbox']/li[2]"));
		return litigation;
	}
	public static WebElement selectTypeCase2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownlistCase_listbox']/li[2]"));
		return litigation;
	}

	
	public static WebElement selectTypeTask(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownType_listbox']/li[3]"));
		return litigation;
	}
	
	public static WebElement selectTypeTask1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownType1_listbox']/li[3]"));
		return litigation;
	}
	public static WebElement selectTypeTask2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownlistCase_listbox']/li[3]"));
		return litigation;
	}
	public static WebElement clickMyReminder(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftremindersmenu'][@class='leftdummy']"));
		return litigation;
	}
	
	public static WebElement clickAddNew1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='Addnew']"));
		return litigation;
	}
	
	public static WebElement clickType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTypePopup_chosen']"));
		return litigation;
	}
	
	public static WebElement clickTitle(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTitlePopup_chosen']"));
		return litigation;
	}
	
	public static WebElement clickReminderText(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtReminderTitle']"));
		return litigation;
	}
	
	public static WebElement clickDescription(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtReminderDesc']"));
		return litigation;
	}
	
	public static WebElement clickRemark2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtRemark']"));
		return litigation;
	}
	
	public static WebElement clickDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtRemindOn']"));
		return litigation;
	}
	
	public static WebElement readMsg1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsReminder']"));
		return litigation;
	}
	
	public static WebElement readMsg2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsReminder']/ul/li"));
		return litigation;
	}
	public static WebElement readMsg3(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsReminder']/ul"));
		return litigation;
	}
	
	public static WebElement clickCloseReminder(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@onclick='CloseMyReminderPopup();']"));
		return litigation;
	}
	
	public static WebElement clickMasters(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftmastermenu']"));
		return litigation;
	}
	
	public static WebElement clickMastersMenu(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftmastermenu']/ul"));
		return litigation;
	}
	
	public static WebElement clickAddNew2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']"));
		return litigation;
	}
	
	public static WebElement clickCaseNoticeType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCaseType']"));
		return litigation;
	}
	
	public static WebElement readMesg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary1']"));
		return litigation;
	}
	
	public static WebElement clickClose2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@onclick='RefreshParent()']"));
		return litigation;
	}
	
	public static WebElement clickDtei(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath(""));
		return litigation;
	}
	
	public static WebElement clickDtevrt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath(""));
		return litigation;
	}
	
	public static WebElement clickDteir(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath(""));
		return litigation;
	}

	
	public static WebElement readDocMsg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsContractDocument']"));
		return litigation;
	}
	public static WebElement readDocMsgInvalidMsg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsContractDocument']/ul/li"));
		return litigation;
	}
	
	 public static WebElement clickNoticeDocument(WebDriver driver)
	 {
		litigation= driver.findElement(By.xpath("//*[@id='lnkDocument']"));
		return litigation;
		 
	 }
	 public static WebElement clickNewDocument(WebDriver driver)
	    {
		 litigation = driver.findElement(By.xpath("//*[@id='lnkAddNewDoctype']"));
	  	  return litigation;
	 } 
	 
	    public static void selectDocumentType(WebDriver driver)
	    {
	  	
	        WebElement DocumentType = driver.findElement(By.xpath ("//*[@id='ddlDocType_chosen']"));
            System.out.println("pdf doc");
	  	    DocumentType.click();
	    } 

		    public static void chooseDocumentType(WebDriver driver)
		    {
		    	 WebElement DocumentType = driver.findElement(By.xpath ("//*[@id='ddlDocType_chosen']/div/ul/li[2]"));
		            System.out.println("pdf doc");
			  	    DocumentType.click();
		    	
		    }

	    public static void selectUploadDocument(WebDriver driver) 
	     {
	   	  
	   	     WebDriverWait wait = new WebDriverWait(driver, 20);
	         WebElement UploadDocument = wait.until(ExpectedConditions.elementToBeClickable(By.id("LitigationFileUpload")));
	   	     UploadDocument.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
	   	
	     } 
		 
		 public static WebElement clickUploadDocument(WebDriver driver) 
	     {
	   	  
			  WebDriverWait wait = new WebDriverWait(driver, 20);
	         litigation = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkDocumentUpload")));
	    	  return litigation;
	   	
	     } 
		 
		 public static WebElement clickClosedDocument(WebDriver driver)
		 {
			 WebDriverWait wait=new WebDriverWait(driver,20);
			 litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
			 return litigation;
			
			 
		 }
		 
		 public static WebElement readTaskMsg(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary8']"));
				return litigation;
			}
		 public static WebElement readTaskMsg1(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary8']/ul/li"));
				return litigation;
			}
		 public static WebElement readTaskMsg2(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary5']/ul"));
				return litigation;
			}
		 public static WebElement readTaskMsgcfo(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary8']/ul/li"));
				return litigation;
			}
		 
		 public static WebElement clickTaskorActivity(WebDriver driver)
		 {
			
			 litigation=driver.findElement(By.id("lnkNoticeTask"));
			 return litigation;
		 }
		 
		 public static WebElement clickNewTask(WebDriver driver)
		 {
			 litigation=driver.findElement(By.id("LinkButton2")); 
			return litigation;
		 }
		 

		public static WebElement ClickTaskTitle(WebDriver driver)
		{
			   WebDriverWait wait = new WebDriverWait(driver, 30);
			  litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbxTaskTitle")));
			   return litigation;
		 }
		
		public static WebElement ClickTaskDescription(WebDriver driver)
		{

			    WebDriverWait wait = new WebDriverWait(driver, 30);
			    litigation= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbxTaskDesc']")));
			    return litigation;
			 
		 }
		
		public static WebElement selectTaskDueDate(WebDriver driver)
	    {
		
	       litigation = driver.findElement(By.id("tbxTaskDueDate"));
	       return litigation;
	    }
	       public static WebElement UpdatePanel1(WebDriver driver)
		    {
	    	     litigation=driver.findElement(By.id("UpdatePanel1"));
	    	     return litigation;
	     }
		
		public static WebElement clickInternalUser2(WebDriver driver)
	    {      
			WebElement TaskPanel=driver.findElement(By.id("UpdatePanel1"));
	        TaskPanel.click();
			
		    WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlTaskUserInternal_chosen']")));
          TaskPanel.click();
            return litigation;
		  }
		
		 public static WebElement selectInternalUser2(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlTaskUserInternal_chosen']/div/div/input")));
	    	  return litigation;
	    	  
	      } 
		 public static WebElement selectInternalUser3(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlTaskLawyerListInternal_chosen']/div/div/input")));
	    	  return litigation;
	    	  
	      } 
		
		  public static WebElement clickSaveButton(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnTaskSave")));
	    	// litigation = driver.findElement(By.id("btnTaskSave"));
	    	  return litigation;
	    	  
	      } 

		  public static WebElement readResponseMsg(WebDriver driver)
		  {
			  litigation= driver.findElement(By.xpath("//*[@id='ValidationSummary10']"));
			 return litigation;
			  
		  }
		  public static WebElement readResponseMsgOrder(WebDriver driver)
		  {
			  litigation= driver.findElement(By.xpath("//*[@id='ValidationSummary2']/ul/li[1]"));
			 return litigation;
			  
		  }
		  public static WebElement readResponseMsgOrder1(WebDriver driver)
		  {
			  litigation= driver.findElement(By.xpath("//*[@id='ValidationSummary2']/ul/li[2]"));
			 return litigation;
			  
		  }
		  public static WebElement readResponseMsgOrder2(WebDriver driver)
		  {
			  litigation= driver.findElement(By.xpath("//*[@id='ValidationSummary2']/ul/li[3]"));
			 return litigation;
			  
		  }
		  public static WebElement readResponseMsg1(WebDriver driver)
		  {
			  litigation= driver.findElement(By.xpath("//*[@id='ValidationSummary1']"));
			 return litigation;
			  
		  }
		  public static WebElement readOrderMsg(WebDriver driver)
		  {
			 return litigation;
			  
		  }
		  
		  public static WebElement clickResponse(WebDriver driver)
		  {
			  
			  litigation= driver.findElement(By.xpath("//*[@id='lnkNoticeResponse']"));
			 return litigation;
			  
		  }
		  
		  public static WebElement clickNewResponse(WebDriver driver)
		  {
			  litigation =driver.findElement(By.id("LinkButton1"));
			  return litigation;
		  }
		  
		  public static void selectSentNotice(WebDriver driver)
		  {
			
			  System.out.println("Received");
			  Select sentnotice = new Select(driver.findElement(By.id("ddlNoticeResponseDate"))); 
			 // sentnotice .selectByVisibleText("Received");
			  sentnotice.selectByValue("1");
			
		  }
		  
		  public static void selectReplyDueDate(WebDriver driver)
		  {
			

			  WebDriverWait wait = new WebDriverWait(driver, 30);
			  WebElement ReplyDueDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='tbxNoticeDueDate']")));
			 ReplyDueDate.sendKeys("01-10-2022");
			
		  }
		  
		  public static void selectRespondedDate(WebDriver driver)
		  {
			  

			  WebDriverWait wait = new WebDriverWait(driver, 30);
			  WebElement RespondedDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='tbxResponseDate']")));
			 // WebElement RespondedDate =driver.findElement(By.xpath("//*[@id='tbxResponseDate']"));
			  RespondedDate.sendKeys("01-09-2022");
			  
			  WebElement ResponsePanel=driver.findElement(By.id("DivResponceCollapsTwo"));
			  ResponsePanel.click();
			  
		  }
		  
		  public static WebElement clickDeliveryMode(WebDriver driver)
		  {
		
//			  Select selectDeliveryMode = new Select(driver.findElement(By.xpath("//*[@id='ddlRespBy_chosen']"))); 
//			
//			  selectDeliveryMode.selectByValue("1");
			  litigation =driver.findElement(By.xpath("//*[@id='ddlRespBy_chosen']"));
			  return litigation;
			  
			  
		  }
		  
		  public static WebElement selectDeliveryMode(WebDriver driver)
		  {
		
			  litigation =driver.findElement(By.xpath("//*[@id='ddlRespBy_chosen']/div/div/input"));
			  return litigation;
			  
		  }
		  
		  public static WebElement clickCourierCompany(WebDriver driver)
		  {
			 litigation =driver.findElement(By.id("tbxRespThrough"));
			  return litigation;
		  }
		
		  
		  public static WebElement RefTrackingNo(WebDriver driver)
		  {

			 litigation=driver.findElement(By.xpath("//*[@id='tbxRespRefNo']"));
			  return litigation;
			  
		  }
		
		
		  public static WebElement Description(WebDriver driver)
		  {
			  litigation =driver.findElement(By.xpath("//*[@id='tbxResponseDesc']"));
			 return litigation;
			  
		  }
		
		  
		  public static WebElement clickSaveResponse(WebDriver driver)
		  {
			  WebDriverWait wait = new WebDriverWait(driver,30);
			 // litigation =driver.findElement((By.xpath("//*[@id='btnSaveResponse']")));
			 
			litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSaveResponse']")));
			  return litigation;
			 
			  
		  }
		  
		  public static WebElement clickExternalLawyerRating(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver,300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lnkLawyerRating']")));
	    	// litigation = driver.findElement(By.xpath("//*[@id='lnkLawyerRating']"));
	    	  return litigation;
	      }  
		  
		  public static void selectExternalLawyerRating(WebDriver driver) 
	      {  
			 WebDriverWait wait = new WebDriverWait(driver,300);
		     WebElement ExternalLawyer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ddlLayerType_chosen']")));
		     ExternalLawyer.click();
	    	 List<WebElement> ExternalLawyer1= driver.findElements(By.xpath("//*[@id='ddlLayerType_chosen']/div/ul/li"));
	    	 ExternalLawyer1.get(1).click();
	    		 
	      } 
		  public static WebElement clickExternalLawyerRating1(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver,300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lnkCaseRating']")));
	    	// litigation = driver.findElement(By.xpath("//*[@id='lnkLawyerRating']"));
	    	  return litigation;
	    	  
	      } 
		
		  
		  public static void selectCaseExternalLawyer(WebDriver driver) 
	      {

			  WebDriverWait wait = new WebDriverWait(driver,30);
			  WebElement ExternalLawyer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ddlLayerType_chosen']")));
	    
	    	 ExternalLawyer.click();
	    	  List<WebElement> options =ExternalLawyer.findElement(By.className("chosen-drop")).findElement(By.className("chosen-results")).findElements(By.tagName("li"));

				for (WebElement option : options)
				{
				    if (option.getText().equals("Amol Patil"))
				    {
				    	System.out.println(option.getText());
				        option.click(); // click the desired option
				        break;
				    }
				} 
				 
		 }
		  
		  public static WebElement clickNewCriteria(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver,30);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnAddPromotor']")));
	    	// litigation = driver.findElement(By.xpath("//*[@id='lnkLawyerRating']"));
	    	  return litigation;
	    	  
	      } 
		  public static WebElement clickCriteria (WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver,30);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='tbxCriteria']")));
	         // litigation = driver.findElement(By.xpath("//*[@id='tbxCriteria']"));
	    	  return litigation;

//			  WebDriverWait wait = new WebDriverWait(driver, 30);
//			  WebElement criteria = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='tbxCriteria']")));
//			 criteria.sendKeys("Test");
	    	  
	      } 
		  public static WebElement clickSaveCriteria(WebDriver driver) 
	      {
//			  WebDriverWait wait = new WebDriverWait(driver,30);
//			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnSave']")));
	    	 litigation = driver.findElement(By.xpath("//*[@id='btnSave']"));
	    	  return litigation;
	    	  
	      } 
		  
		  public static WebElement clickclosecriteria(WebDriver driver) 
	      {
//			  WebDriverWait wait = new WebDriverWait(driver,30);
//			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnSave']")));
	    	 litigation = driver.findElement(By.xpath("//*[@id='AddLayerRatingCriteriaShowDialog']/div/div/div[1]/button"));
	    	  return litigation;
	    	  
	      } 
		  
		  public static WebElement clickstar(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdLawyerRating_LawyerRating_9_Star_1']")));
	    	
	    	  return litigation;
	      } 
		  
		  public static WebElement clickstar1(WebDriver driver) 
	      {
			  
               WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdLawyerRating_LawyerRating_9_Star_2']")));
	    	//litigation = driver.findElement(By.xpath("//*[@id='grdLawyerRating_LawyerRating_9_Star_2']"));
	    	  return litigation;
	      } 
		  public static WebElement clickSaveRating(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnSaveLawRating']")));
	    	//litigation = driver.findElement(By.xpath("	//*[@id='btnSaveLawRating']"));
	    	  return litigation;
	      } 
		  
		  public static WebElement clickAuditLog(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='liAuditLog']")));
	    	 //litigation = driver.findElement(By.xpath("//*[@id='liAuditLog']"));
	    	  return litigation;
	    	  
	      } 
		
		  
		  public static WebElement clickExport(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnExport']")));
	    	// litigation = driver.findElement(By.xpath("//*[@id='btnExport']"));
	    	  return litigation;
	    	  
	      } 
		  
	
		  public static WebElement clickclosebutton(WebDriver driver) 
	      {
			  WebDriverWait wait = new WebDriverWait(driver, 500);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnAddEditcase']")));
	    	 //litigation = driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
	    	  return litigation;
	    	  
	      } 
		  public static WebElement clickInvoiceNo(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='grdNoticePayment_tbxInvoiceNo']"));
				return litigation;
			}
		  public static WebElement clickPaymentType(WebDriver driver)
			{
			litigation = driver.findElement(By.xpath("//*[@id='grdNoticePayment_ddlPaymentType_chosen']"));
			return litigation;
			
			}
		  
		  public static WebElement selectPaymentType(WebDriver driver)
			{
			litigation = driver.findElement(By.xpath("//*[@id='grdNoticePayment_ddlPaymentType_chosen']/div/div/input"));
			return litigation;
			
			}
		  
		
		  
				
		  
		  public static WebElement clickAmount(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='grdNoticePayment_tbxAmount']"));
				return litigation;
			}
		  public static WebElement clickAmountCase(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='grdCasePayment_tbxAmountPaid']"));
				return litigation;
			}
		  public static WebElement clickSavePaymentLog(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='grdNoticePayment_btnPaymentSave']"));
				return litigation;
			}
		  
		  public static WebElement readPymentmsg(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary4']"));
				return litigation;
			}
		  public static WebElement readPymentmsg1(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary4']/ul"));
				return litigation;
			}
		  
		  public static WebElement readRatingmsg(WebDriver driver)
			{
				litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary6']"));
				return litigation;
			}
		  public static WebElement clickCaseTask(WebDriver driver)
			{
				litigation = driver.findElement(By.id("lnkCaseTask"));
				return litigation;
			}
		  public static WebElement clickCaseNewTask(WebDriver driver)
		    {
		  	  litigation = driver.findElement(By.xpath("//*[@id='LinkButton1']"));
		  	  return litigation ;
		    }
		 public static WebElement clickHearingDate(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver,300);
			  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='tbxTaskHearingDate']")));
		  	 //litigation = driver.findElement(By.id("tbxTaskHearingDate"));
		  	  return litigation;
		  	  
		    }
		 public static WebElement clickSaveHearingDate(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lnkSaveRefNo']/img")));
			
		  	 return litigation; 
		  	  
		    }
		 
		
		 public static WebElement clickSaveHearingDatecfo(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkSaveRefNo")));
		  
		   
		  	 return litigation; 
		  	  
		    }
		 public static WebElement clickHearingDatecfo(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlHearingRefNo_chosen']/a")));
		     return litigation; 
		  	  
		    }
		 public static WebElement clickHearingDatedropdowncfo(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlHearingRefNo_chosen']/div/ul/li[2]")));
		     return litigation; 
		  	  
		    }
		 public static WebElement clickHearingcfo(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlTabHearingRef_chosen']")));
		     return litigation; 
		  	  
		    }
		 public static WebElement clickHearingdropdowncfo(WebDriver driver)
		    {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlTabHearingRef_chosen']/div/ul/li[3]")));
		     return litigation; 
		  	  
		    }
		 
		 
		 public static WebElement clickInternalUser3(WebDriver driver)
		    {      
//				WebElement TaskPanel=driver.findElement(By.id("UpdatePanel1"));
//		        TaskPanel.click();
				
				  WebDriverWait wait = new WebDriverWait(driver, 10);
				  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ddlTaskLawyerListInternal_chosen']")));
				
				 
				return litigation;

			  }
		 public static WebElement clickCaseHearing(WebDriver driver)
		 {
			 WebDriverWait wait = new WebDriverWait(driver,30);
			litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lnkCaseHearing']")));
			// litigation=driver.findElement(By.xpath("//*[@id='lnkCaseHearing']"));
			 return litigation;
		 }
		 public static WebElement clickNewCaseHearing(WebDriver driver)
		 {
			 WebDriverWait wait = new WebDriverWait(driver,30);
				litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lnkAddhearing']")));
			 //litigation=driver.findElement(By.xpath("//*[@id='lnkAddhearing']"));
			 return litigation;
		 }
		 
		 public static WebElement clickCaseHearingDate(WebDriver driver)
		 {
			 WebDriverWait wait = new WebDriverWait(driver,30);
				litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxTabHearingDate']")));
			   return litigation;
		 }
		 
		 public static WebElement clickSaveCaseHearingDate(WebDriver driver)
		 {
			 WebDriverWait wait = new WebDriverWait(driver,30);
				//litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lnkTabSaveRefNo']")));
				litigation=driver.findElement(By.xpath("//*[@id='lnkTabSaveRefNo']"));
				
		       return litigation;
		 }
		 public static WebElement clickCaseHearingDecsri(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxResponseDesc']"));
			 return litigation;
		 }
		 public static WebElement clickSaveCaseHearing(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='btnSaveHearing']"));
			 return litigation;
		 }
		
		 public static WebElement clickCaseOrder(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='lnkCaseOrder']"));
			 return litigation;
		 }
		 public static WebElement clickNewCaseOrder(WebDriver driver)
		 {
			 WebDriverWait wait = new WebDriverWait(driver,30);
				litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='LinkButton3']")));
			// litigation=driver.findElement(By.xpath("//*[@id='AddNewOrderDiv']"));
			 return litigation;
		 }
		 public static WebElement clickCaseOrderDate(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxOrderDate']"));
			 return litigation;
		 }
		 public static WebElement clickCaseOrderType(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='ddlOrderType_chosen']"));
			 return litigation;
		 }
		 public static WebElement selectCaseOrderType(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='ddlOrderType_chosen']/div/ul/li[2]"));
			 return litigation;
		 }
		 
		 public static WebElement clickCaseOrderTitle(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxOrderTitle']"));
			 return litigation;
		 }
		 public static WebElement clickCaseOrderDecri(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxOrderDesc']"));
			 return litigation;
		 }
		
		 public static WebElement clickSaveCaseOrder(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='btnOrderSave']"));
			 return litigation;
		 }
		 public static WebElement clickClearCaseOrderBtn(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='btnOrderClear']"));
			 return litigation;
		 }
		 
		 public static WebElement clickOrderPanel(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='collapseDivOrderLogs']"));
			 return litigation;
		 }
		 public static WebElement clickAdvocateBillGridload(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='id='imgUpdateProgress']"));
			 return litigation;
		 }
		 
		 public static WebElement clickAdvocateBill(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='lnkCaseAdvocateBill']"));
			 return litigation;
		 }
		 public static WebElement clickNewAdvocateBill(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='LnkAddAdvocateBill']"));
			 return litigation;
		 }
		 public static WebElement clickInvoiceNum(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxAdvInvoiceno']"));
			 return litigation;
		 }
		 public static WebElement clickInvoiceDate(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxinvoicedate']"));
			 return litigation;
		 }
		 public static WebElement clickInvoiceAmount(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='tbxAdvInvoiceAmount']"));
			 return litigation;
		 }
		 public static WebElement clickLawFirm1(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='ddlLawyerAdvocate_chosen']"));
			 return litigation;
		 }
		 public static WebElement selectLawFirm1(WebDriver driver)
		 {
	
			 litigation=driver.findElement(By.xpath("//*[@id='dlLawyerAdvocate_chosen']/div/ul/li[3]"));
			 return litigation;
	    }
		 public static List<WebElement> selectLawFirm2(WebDriver driver)
		 {
	
			  elementsList=driver.findElements(By.xpath("//*[@id='ddlLawyerAdvocate_chosen']/div/ul/li"));
		      return elementsList; 
			
	    }
	

		public static WebElement clickApprover1(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='ddlApprover1_chosen']"));
			 return litigation;
		 }
		 public static List<WebElement> selectApprover1(WebDriver driver) throws InterruptedException
		 {
			 WebDriverWait wait=new WebDriverWait(driver,20);
			 elementsList=driver.findElements(By.xpath("//*[@id='ddlApprover1_chosen']/div/ul/li"));
		      return elementsList; 
		
		 }
		 public static WebElement clickApprover2(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='ddlApprover2_chosen']"));
			 return litigation;
		 }
		 public static List<WebElement> selectApprover2(WebDriver driver)
		 {
			 elementsList=driver.findElements(By.xpath("//*[@id='ddlApprover2_chosen']/div/ul/li"));
		      return elementsList; 
		 }
		 public static WebElement clickSaveAdvocateBill(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='btnAdvocateBillSave']"));
			 return litigation;
		 }
		 public static WebElement clickAdvocateBillPanel(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='collapseDivAdvocateBillLogs']"));
			 return litigation;
		 }
		
		 public static WebElement clickCaseInvoiceNo1(WebDriver driver)
		 {
			 litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_tbxInvoiceNo']"));
			 return litigation;
		 }
		  public static void clickPaymentTyp1(WebDriver driver)
				{
				WebElement PaymentType  = driver.findElement(By.xpath("//*[@id='grdCasePayment_ddlPaymentType_chosen']"));
				PaymentType.click();
					
				}
			  
		  public static WebElement clickAmount1(WebDriver driver)
			 {
			  WebDriverWait wait = new WebDriverWait(driver,30);
			  litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grdCasePayment_tbxAmount']")));
			  return litigation;
			 }
		  public static WebElement clickSavePaymentLog1(WebDriver driver)
			 {
				 litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_btnPaymentSave']"));
				 return litigation;
			 }
		  public static WebElement clickAmountPaid(WebDriver driver)
			 {
				 litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_tbxAmountPaid']"));
				 return litigation;
			 }
		  
		  
		  public static WebElement clickMyDocument(WebDriver driver)
			 {
	           WebDriverWait wait = new WebDriverWait(driver,30);
			    litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='leftdocumentsmenu']/a/span[1]")));
	            return litigation;
			 }
		  public static WebElement clickmyDocument(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='DocumentShareListNew']")));
			 
			   return litigation;
			   
			}
		  public static WebElement clickcriticalDocument(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='DocumentShareListNew']/a)[2]")));
			 
			   return litigation;
			   
			}
		  public static WebElement clickDownloadDocument(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[16]/a[1]")));
			 
			   return litigation;
			   
			}
		  public static WebElement clickDownloadDocument1(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[14]/a[1]")));
			 
			   return litigation;
			   
			}
		    public static WebElement clickViewDocument(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[16]/a[2]")));
			 
			   return litigation;
			 }

		    public static WebElement clickViewDocument1(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[14]/a[2]")));
			 
			   return litigation;
			 }
		    
		    
		    public static WebElement clickcloseViewDocument(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divViewDocument2']/div/div/div[1]/button")));
			 
			   return litigation;
			   
			}
		  
		    public static WebElement clickcloseViewDocument1(WebDriver driver)
			{
		
		       WebDriverWait wait = new WebDriverWait(driver,30);
				
		      litigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divViewDocument1']/div/div/div[1]/button")));
			 
			   return litigation;
			   
			}
		  public static WebElement ClickImportUtility(WebDriver driver)
		  {
			  WebDriverWait wait=new WebDriverWait(driver,30);
			  litigation=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='leftuploadmenu']/a/span[1]")));
			  return litigation;
		  }
		  public static WebElement ChooseCaseType(WebDriver driver)
		  {
			  WebDriverWait wait=new WebDriverWait(driver,30);
			  litigation=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_rdoCCUpload']")));
			  return litigation;
		  }
		  public static WebElement ChooseCaseFile(WebDriver driver) throws InterruptedException
		  {
			  
			     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
			    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Case_Upload_Format.xlsx");
			     return litigation;
	      }
		  public static WebElement ChooseCaseFile1(WebDriver driver) throws InterruptedException
		  {
			  
			     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
			    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Case_Upload_Format (1).xlsx");
			     return litigation;
	      }
		  public static WebElement ChooseCaseFile2(WebDriver driver) throws InterruptedException
		  {
			  
			     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
			    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Case_Upload_Format (2).xlsx");
			     return litigation;
	      }
		  public static WebElement ChooseCaseFile3(WebDriver driver) throws InterruptedException
		  {
			  
			     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
			    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Case_Upload_Format (3).xlsx");
			     return litigation;
	      }
		  public static WebElement UploadCaseFile(WebDriver driver) throws InterruptedException
		  {
			  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnUploadFile']"));
			  return litigation;
		  }
		  
		  
		  
		  public static WebElement readCaseMsg(WebDriver driver) throws InterruptedException
		  {
			  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']"));
			  return litigation;
		  }
		  public static WebElement readCaseMsg1(WebDriver driver) throws InterruptedException
		  {
			  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']/ul/li/ol"));
			  return litigation;
		  }
		  public static WebElement ClickcaseHearing(WebDriver driver) throws InterruptedException
		  {
			  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_rdoCHUpload']"));
			  return litigation;
		  }
		  
		  public static WebElement ClickcaseOrder(WebDriver driver) throws InterruptedException
		  {
			  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_rdoCOUpload']"));
			  return litigation;
		  }
		  public static WebElement ClickcasePayment(WebDriver driver) throws InterruptedException
		  {
			  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_rdoCPUpload']"));
			  return litigation;
		  }
		  
		  
		  

        public static WebElement clickNotice(WebDriver driver) throws InterruptedException
          {
              litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkLN']"));
               return litigation;
           }
        
  	  public static WebElement ChooseNoticeType(WebDriver driver)
	  {
		  WebDriverWait wait=new WebDriverWait(driver,30);
		  litigation=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_rdoLNUpload']")));
		  return litigation;
	  }
  	  
  	  public static WebElement ChooseNoticeResponse(WebDriver driver)
	  {
		  WebDriverWait wait=new WebDriverWait(driver,30);
		  litigation=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_rdoNRUpload']")));
		  return litigation;
	  }
  	  
	  public static WebElement ChoosePaymentInfo(WebDriver driver)
	  {
		  WebDriverWait wait=new WebDriverWait(driver,30);
		  litigation=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_rdoPIUpload']")));
		  return litigation;
	  }
  	    
	  public static WebElement ChooseNoticeFile(WebDriver driver) throws InterruptedException
	  {
		  
		     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
		    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Notice_Upload_Format.xlsx");
		     return litigation;
      }
	  public static WebElement ChooseNoticeFile1(WebDriver driver) throws InterruptedException
	  {
		  
		     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
		    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Notice_Upload_Format (1).xlsx");
		     return litigation;
      }
	  public static WebElement ChooseNoticeFile2(WebDriver driver) throws InterruptedException
	  {
		  
		     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
		    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Notice_Upload_Format (2).xlsx");
		     return litigation;
      }
	  public static WebElement ChooseNoticeFile3(WebDriver driver) throws InterruptedException
	  {
		  
		     WebElement CaseFile=driver.findElement(By.cssSelector("input[type='file']"));
		    CaseFile.sendKeys("E:\\Snehal\\ComplianceLatest\\Litigation-Project-main (1)\\Litigation-Project-main\\TestData\\Litigation_Notice_Upload_Format (3).xlsx");
		     return litigation;
      }
	  public static WebElement ChooseOrderFile(WebDriver driver) throws InterruptedException
	  {
		  
		     WebElement CaseFile=driver.findElement(By.xpath("//*[@id='fuCaseOrderDocUpload']"));
		    CaseFile.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
		     return litigation;
      }
	  public static WebElement UploadNoticeFile(WebDriver driver) throws InterruptedException
	  {
		  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnUploadFileLN']"));
		  return litigation;
	  }
	  
	  public static WebElement readNoticeMsg(WebDriver driver) throws InterruptedException
	  {
		  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']"));
		  return litigation;
	  }
	  public static WebElement readNoticeMsg1(WebDriver driver) throws InterruptedException
	  {
		  litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']/ul/li/ol"));
		  return litigation;
	  }
	  
	  public static WebElement caseNoticeSummaryGraph(WebDriver driver) throws InterruptedException
	  {
		  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 ']"));
		  return litigation;
	  }
	  
	  
	  public static WebElement readTotalItemsD(WebDriver driver) throws InterruptedException
	  {
		  litigation=driver.findElement(By.xpath("//*[@id='grid']/div[3]/span[2]"));
		  return litigation;
	  }
	  
	  public static WebElement caseNoticeSummaryGraphExport(WebDriver driver) throws InterruptedException
	  {
		  
		  WebDriverWait wait = new WebDriverWait(driver,10);
		  litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='exportReport']")));
		 // litigation=driver.findElement(By.cssSelector("button[id='exportReport']"));
		  return litigation;
	  }
	  
	  public static WebElement CaseNoticeTypeSummaryGraph(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 ']"));
		  return litigation;
	  }
	  public static WebElement RiskSummaryGraph(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[3]"));
		  return litigation;
	  }
	  public static WebElement RiskSummaryGraphCase(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])"));
		  return litigation;
	  }
	  public static WebElement DepartmentSummaryGraph(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[4]"));
		  return litigation;
	  }
	  public static WebElement DepartmentSummaryGraph3(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[4]"));
		  return litigation;
	  }
	  public static WebElement LocationSummaryGraph(WebDriver driver) throws InterruptedException
	  {
		 
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[6]"));
	
		  return litigation;
	  }
	  public static WebElement LocationSummaryGraph1(WebDriver driver) throws InterruptedException
	  {
		 
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[4]"));
	
		  return litigation;
	  }
	  public static WebElement LocationSummaryGraphCase(WebDriver driver) throws InterruptedException
	  {
		 
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[6]"));
	
		  return litigation;
	  }
	  
	  public static WebElement CategorySummaryGraph(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[8]"));
		  return litigation;
	  }
	  public static WebElement CategorySummaryGraph1(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[8]"));
		  return litigation;
	  }
	  public static WebElement CategorySummaryGraphCase1(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[10]"));
		  return litigation;
	  }
	  public static WebElement CategorySummaryGraphCase(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[9]"));
		  return litigation;
	  }
	  
	  public static WebElement caseNoticeSummaryGraphClose(WebDriver driver) throws InterruptedException
	  {
		  
		  litigation=driver.findElement(By.xpath("//*[@id='divGraphDetails']/div/div/div[1]/button"));
		 // litigation=driver.findElement(By.cssSelector("button[id='exportReport']"));
		  return litigation;
	  }
	  
	  

	  public static WebElement TableLoad(WebDriver driver) throws InterruptedException
	  {
		  litigation=driver.findElement(By.xpath("//*[@id='grid']"));
		  return litigation;
	  }
	  
	  
	  
		public static WebElement chooseMasterLegalEntity(WebDriver driver)
		{
			 WebDriverWait wait = new WebDriverWait(driver, 20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[1]/a")));
			//WebElement LawFirm = driver.findElement(By.xpath("//*[@id='ddlLawFirm_chosen']/div/div/input"));
			return litigation;
			
		}
		
		public static WebElement addLegalEntity(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddCustomerBranch']")));
			return litigation;
		}
	  
		public static WebElement legalEntityName(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxName']")));
			return litigation;
		}
		
		
	
	  
		public static WebElement clickUnitType(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlType']")));
			return litigation;
		}
		public static WebElement chooseUnitType(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlType']/option[2]")));
			return litigation;
		}
		public static WebElement clickLegalEntityType(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlCompanyType']")));
			return litigation;
		}
		public static WebElement chooseLegalEntityType(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlCompanyType']/option[2]")));
			return litigation;
		}
		public static WebElement editLegalEntity(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCustomerBranch_LinkButton1_0']/img")));
			return litigation;
		}
		
		
		public static WebElement clickAddressLine(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxAddressLine1']")));
			return litigation;
		}
  	  
		public static WebElement clickState1(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlState']")));
			return litigation;
		}
        
		public static WebElement chooseState1(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlState']/option[75]")));
			return litigation;
		}
        
        
		public static WebElement clickCity(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlCity']")));
			return litigation;
		}
        
		public static WebElement chooseCity(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlCity']/option[3]")));
			return litigation;
		}
        
		public static WebElement clickContactPerson(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxContactPerson']")));
			return litigation;
		}
		public static WebElement clickEmail(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxEmail']")));
			return litigation;
		}
		
		public static WebElement clickSaveLegalEntity(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnSave']")));
			return litigation;
		}
		public static WebElement clickcloseLegalEntity(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")));
			return litigation;
		}
		
		public static WebElement readlegalmsg(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_valcustomerbranch']")));
			return litigation;
		}
		public static WebElement readlegalmsg1(WebDriver driver)
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_valcustomerbranch']/ul")));
			return litigation;
		}
		
		public static WebElement chooseMasterLawFirm(WebDriver driver)
		{
			litigation=driver.findElement(By.xpath("//*[@id='Mastersubmenu']/li[2]/a"));
			//WebElement LawFirm = driver.findElement(By.xpath("//*[@id='ddlLawFirm_chosen']/div/div/input"));
			return litigation;
			
		}
		  public static WebElement newLawFirm(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddLaywer']"));
				return litigation;
				
			}
			
			public static WebElement  nameLawFirm(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,20);
				litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxFirstName']")));
				return litigation;
			}
			
			public static WebElement Email(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxEmail']")));
				return litigation;
			}
		 
			public static WebElement contactNo(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxContactNo']")));
				return litigation;
			}
			public static WebElement opponentcontactNo(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxContactNo']")));
				return litigation;
			}
			
			public static WebElement ReadLawFirmMsg(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']/ul/li")));
				return litigation;
			}
			public static WebElement ReadLawFirmMsg1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']/ul")));
				return litigation;
			}
			
			public static WebElement clickSaveLawFirm(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnSave']")));
				return litigation;
			}
			

			public static WebElement clickCloseButton(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")));
				return litigation;
			}
			
			public static WebElement editLawFirm(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdLawyer_lbtnEdit_0']/img")));
				return litigation;
			}
			
			public static WebElement clickAddNewLawyer(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdLawyer_lbtAddLawyer_0']/img")));
				return litigation;
			}
			
			public static WebElement clickLawyerName(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxFirstNameUser']")));
				return litigation;
			}
			
			public static WebElement clickLawyerLastName(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxLastNameUser']")));
						
				return litigation;
			}
			
			public static WebElement clickLawyerDesignation(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxDesignation']")));
						
				return litigation;
			}
			public static WebElement clickLawyerEmail(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxEmailUser']")));
						
				return litigation;
			}
			public static WebElement clickLawyerContactNo(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxContactNoUser']")));
						
				return litigation;
			}
			
			public static WebElement clickLawyerDepartment(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlDepartment']")));
						
				return litigation;
			}
			public static WebElement selectLawyerDepartment(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlDepartment']/option[3]")));
						
				return litigation;
			}
			public static WebElement clickLawyerRole(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlLitigationRole']")));
						
				return litigation;
			}
			public static WebElement selectLawyerRole(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlLitigationRole']/option[2]")));
						
				return litigation;
			}
			public static WebElement readLawyerMsg(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_vsUserPopup']/ul/li")));
						
				return litigation;
			}
			
			public static WebElement readLawyerMsg1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_vsUserPopup']/ul")));
						
				return litigation;
			}
			public static WebElement saveLawyer(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_SaveLawyer']")));
						
				return litigation;
			}
			public static WebElement closeLawyer(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divUserDialog']/div/div/div[1]/button")));
						
				return litigation;
			}
			public static WebElement CloseLawyer(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ContentPlaceHolder1_CloseLaywerPopUp']")));
						
				return litigation;
			}
			
			public static WebElement clickUserMaster(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[3]/a")));
				return litigation;
			}
			
			public static WebElement clickAddNewUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddUser']")));
				return litigation;
			}
			
			public static WebElement clickUserName(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxFirstName']")));
				return litigation;
			}
			
			public static WebElement clickUserLastName(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxLastName']")));
						
				return litigation;
			}
			
			public static WebElement clickUserDesignation(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxDesignation']")));
						
				return litigation;
			}
			public static WebElement clickUserEmail(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxEmail']")));
						
				return litigation;
			}
			public static WebElement clickUserContactNo(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxContactNo']")));
						
				return litigation;
			}
			
			public static WebElement clickUserDepartment(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlDepartment']")));
						
				return litigation;
			}
			public static WebElement selectUserDepartment(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlDepartment']/option[3]")));
						
				return litigation;
			}
			public static WebElement clickUserRole(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlLitigationRole']")));
						
				return litigation;
			}
			public static WebElement selectUserRole(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlLitigationRole']/option[2]")));
						
				return litigation;
			}
			
			public static WebElement saveUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnSave']")));
						
				return litigation;
			}
			public static WebElement closeUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")));
						
				return litigation;
			}
			public static WebElement editUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdUser_lbtnEdit_0']/img")));
						
				return litigation;
			}
			
			public static WebElement UserAddress(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_tbxAddress']")));
						
				return litigation;
			}
			public static WebElement UserReadMsg(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_vsUserPopup']/ul/li")));
						
				return litigation;
			}
			public static WebElement UserReadMsg1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_vsUserPopup']/ul")));
						
				return litigation;
			}
			public static WebElement UserDeleted(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdUser_lbtnDelete_0']/img")));
						
				return litigation;
			}
			
			
			
			public static WebElement chooseOpponentMasters(WebDriver driver)
			{
				
				litigation =driver.findElement(By.xpath("//*[@id='Mastersubmenu']/li[4]/a"));
						
				return litigation;
			}
			public static WebElement NewOpponent(WebDriver driver)
			{
				
				litigation =driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']"));
						
				return litigation;
			}
			public static WebElement clickOpponentName(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='tbxName']"));
			    return litigation;
			}
			public static WebElement clickOpponentEmail(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//input[@id='tbxEmail']"));
			    return litigation;
			}
			
			public static WebElement  opponentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rbPartyType_1']")));
				return litigation;
			}
			public static WebElement  readOppoenentMsg(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ValidationSummary1']/ul/li")));
				return litigation;
			}
			public static WebElement  readOppoenentMsg1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ValidationSummary1']/ul")));
				return litigation;
			}
			public static WebElement  saveOpponent(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
			public static WebElement  closeOpponent(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
				return litigation;
			}
			
			public static WebElement  editOpponent(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdLCParty_LinkButton1_0']/img")));
				return litigation;
			}
			
			public static WebElement  clickCourtMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[5]/a")));
				return litigation;
			}
			public static WebElement  clickNewCourt(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']")));
				return litigation;
			}
			public static WebElement clickCourtName(WebDriver driver)
			{
				//WebDriverWait wait= new WebDriverWait(driver,300);
				//litigation =wait.until(ExpectedConditions.elementToBeClickable(By.id("tbxCourtName")));
			//	litigation =driver.findElement(By.id("tbxCourtName"));
				
				//WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =driver.findElement(By.xpath("//*[@id='tbxCourtName']"));
				
				
				
				return litigation;
			}
			public static WebElement  clickCourtType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlCourtType_chosen']")));
				return litigation;
			}
			
			public static WebElement  selectCourtType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlCourtType_chosen']/div/ul/li[3]")));
				return litigation;
			}
			
			public static WebElement  clickCountry(WebDriver driver)
			{
//				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =driver.findElement(By.xpath("//*[@id='ddlCountry_chosen']"));
				return litigation;
			}
			
			public static WebElement  selectCountry(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlCountry_chosen']/div/ul/li[1]")));
				return litigation;
			}
			
			
			
			public static WebElement  saveCourt(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
	
			
			
			public static WebElement  closeCourt(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
				return litigation;
			}
			public static WebElement  editCourt(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCourtMaster_lbtcourMedit_0']/img")));
				return litigation;
			}
			public static WebElement deleteCourt(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCourtMaster_lbtcourMdelete_0']/img")));
				return litigation;
			}
			
			
			public static WebElement  clickCasNoticeTypecfo(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Case/Notice Type")));
				return litigation;
			}
			
			
			public static WebElement  clickCasNoticeType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[6]")));
				return litigation;
			}
			
			public static WebElement  NewCaseNoticeType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']")));
				return litigation;
			}
			public static WebElement  CaseNoticeType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='upPromotor']/div/div[2]/div/span[1]/div/button")));
				return litigation;
			}
			public static WebElement  selectCaseNoticeType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='upPromotor']/div/div[2]/div/span[1]/div/ul/li[2]/a/label/input")));
				return litigation;
			}
			
			public static WebElement  TypeName(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxCaseType']")));
				return litigation;
			}
			
			public static WebElement  saveCaseNoticeType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
			public static WebElement  closeCaseNoticeType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
				return litigation;
			}
			
			public static WebElement  editCaseNoticeType(WebDriver driver)
			{
				//WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =driver.findElement(By.id("ContentPlaceHolder1_grdCaseType_LinkButton1_0"));
				return litigation;
			}
			
			public static WebElement  deleteCaseNoticeType(WebDriver driver)
			{
				//WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdCaseType_LinkButton2_0']/img"));
				return litigation;
			}
			
			
			public static WebElement  clickPaymentTypeMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[7]/a")));
				return litigation;
			}
			
			public static WebElement  clickPaymentTypeNew(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPayment']")));
				return litigation;
			}
			public static WebElement PaymentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='txtFName']")));
				return litigation;
			}
			
			
			public static WebElement savePaymentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
			public static WebElement closePaymentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancelDeptPopUp']")));
				return litigation;
			}
			public static WebElement editPaymentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdPayment_LinkButton1_0']/img")));
				return litigation;
			}
			public static WebElement deletePaymentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdPayment_LinkButton2_0']/img")));
				return litigation;
			}
			
			
			public static WebElement customParameterMaster(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[8]/a")));
				return litigation;
			}
			public static WebElement newCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAdd']")));
				return litigation;
			}
			public static WebElement typeCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='AddCustFieldDiv']/div/div[2]/span[1]/div/button")));
				return litigation;
			}
			public static WebElement selectTypeCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='AddCustFieldDiv']/div/div[2]/span[1]/div/ul/li[2]")));
				return litigation;
			}
			
			public static WebElement ParameterLabel(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxLableName']")));
				return litigation;
			}
			public static WebElement saveCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
			public static WebElement closeCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
				return litigation;
			}
			
			public static WebElement editCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCustomFieldList_lblEdit_0']/img")));
				return litigation;
			}
			public static WebElement deleteCustomParameter(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCustomFieldList_lblDelete_0']/img")));
				return litigation;
			}
			
			public static WebElement caseStageMaster(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[9]/a")));
				return litigation;
			}
			public static WebElement newCaseStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']")));
				return litigation;
			}
			public static WebElement clickcaseStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxcasestageType']")));
				return litigation;
			}
			public static WebElement readcaseStagemsg(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='vsAddEditCaseStageType']/ul/li")));
				return litigation;
			}
			
			
			public static WebElement savecaseStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
			public static WebElement closecaseStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
				return litigation;
			}
			
			public static WebElement editcaseStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdcaseStageType_LinkButton1_0']/img")));
				return litigation;
			}
			
			
			public static WebElement deletecaseStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdcaseStageType_LinkButton2_0']/img")));
				return litigation;
			}
			
			
			
			public static WebElement DocumentTypeMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[10]/a")));
				return litigation;
			}
			public static WebElement NewDocumentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']")));
				return litigation;
			}
			public static WebElement clickDocumentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxDocumentType']")));
				return litigation;
			}
			public static WebElement saveDocumentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSave']")));
				return litigation;
			}
			public static WebElement closeDocumentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnCancel']")));
				return litigation;
			}
			public static WebElement editDocumentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdDocType_lnkEditDocType_0']/img")));
				return litigation;
			}
			
			public static WebElement deleteDocumentType(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdDocType_lnkDeleteDocType_0']/img")));
				return litigation;
			}
			
			
			public static WebElement ratingCriteriaMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[11]/a")));
				return litigation;
			}
			public static WebElement clickcriteria(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbxCriteria']")));
				return litigation;
			}
			
			public static WebElement editcriteria(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCriteriaMaster_LinkButton1_0']/img")));
				return litigation;
			}
			public static WebElement deletecriteria(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdCriteriaMaster_LinkButton2_0']/img")));
				return litigation;
			}
			
			
			
			
			public static WebElement pageAuthorizationaMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[12]/a")));
				return litigation;
			}
			public static WebElement clickUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlUserType_chosen']/a/span")));
				return litigation;
			}
			public static WebElement selectUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ddlUserType_chosen']/div/ul/li[2]")));
				return litigation;
			}
			public static WebElement clickAddButton(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdPageAuthorization_chkADD_0']")));
				return litigation;
			}
			
			public static WebElement clickUpdateButton(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdPageAuthorization_chkupdate_0']")));
				return litigation;
			}
			public static WebElement clickDeleteButton(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdPageAuthorization_chkDelete_0']")));
				return litigation;
			}
			public static WebElement clickViewButton(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_grdPageAuthorization_chkView_0']")));
				return litigation;
			}
			public static WebElement clicksaveButton(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_btnSavePageAutorization']")));
				return litigation;
			}
			
			public static WebElement readPageAuthoMsg(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary2']/ul/li")));
				return litigation;
			}
			
			
			public static WebElement noticeStageMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[14]/a")));
				return litigation;
			}
			public static WebElement noticeStagecfoMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[12]")));
				return litigation;
			}
			public static WebElement addNoticeStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[1]/div/a")));
				return litigation;
			}
			public static WebElement clickNoticeStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[1]/input")));
				return litigation;
			}
			public static WebElement updateNoticeStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[2]/a[1]")));
				return litigation;
			}
			public static WebElement editNoticeStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[2]/a[1]")));
				return litigation;
			}
			public static WebElement deleteNoticeStage(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[2]/a[2]")));
				return litigation;
			}
			
			
			public static WebElement UserReassignmentMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[15]/a")));
				return litigation;
			}
			public static WebElement UserReassignmentcfoMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[13]")));
				return litigation;
			}
			
			public static WebElement clickUser1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='childrow']/div/div[1]/div[1]/div[1]/span/span/span[1]")));
				return litigation;
			}
			public static WebElement selectUser1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlUsers_listbox']/li[1]")));
				return litigation;
			}
			public static WebElement clickAssigntoUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='childrow']/div/div[1]/div[1]/div[2]/span/span/span[1]")));
				return litigation;
			}
			public static WebElement selectAssigntoUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ddlAssignUsers_listbox']/li[2]")));
				return litigation;
			}
			public static WebElement selectcheckBox(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				
				litigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gridCases']/div[2]/table/tbody/tr[1]/td[1]")));
				
						//*[@id='gridCases']/div[2]/table/tbody/tr[1]/td[1]
			     return litigation;
			     
			}
			 public static void selectcheckBoxcfo(WebDriver driver)
			 {	
					WebElement checkBoxSelected = driver.findElement(By.cssSelector("label[class='k-checkbox-label k-no-text']"));
					boolean isSelected = checkBoxSelected.isSelected();

					// performing click operation if element is not selected 
					if(isSelected == false) 
					{
						checkBoxSelected.click();
					}
			 }  
       
			

			public static WebElement clickAssignButoon(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnAssignTo']")));
				return litigation;
			}
			public static WebElement clicknotice(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tabstripAssignment']/ul/li[2]/span[2]")));
				return litigation;
			}
			
			
			public static WebElement selectNoticeCheckkBox(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gridNotices']/div[2]/table/tbody/tr[1]/td[1]")));
				return litigation;
			}
			public static WebElement clickTask(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tabstripAssignment']/ul/li[3]/span[2]")));
				return litigation;
			}
			public static WebElement selectTaskCheckkBox(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gridTask']/div[2]/table/tbody/tr[1]/td[1]")));
				return litigation;
			}
			public static WebElement clickAutidLog(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tabstripAssignment']/ul/li[4]/span[2]")));
				return litigation;
			}
			
			public static WebElement MailAuthorizationMasters(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[16]/a")));
				return litigation;
			}
			public static WebElement MailAuthorizationMasterscfo(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Mastersubmenu']/li[14]/a")));
				return litigation;
			}
			
			public static WebElement clickTypeOfUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='childrow']/div/div[1]/div[1]/span/span/span[1]")));
				return litigation;
			}
			public static WebElement selectTypeOfUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dropdownUserType_listbox']/li[1]")));
				return litigation;
			}
			public static WebElement clickRole(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='childrow']/div/div[1]/div[2]/span/span/span[1]")));
				return litigation;
			}
			public static WebElement selectRole(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dropdownRole_listbox']/li[2]")));
				return litigation;
			}
			public static WebElement clickUsers(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divusers']/div/div")));
				return litigation;
			}
			
			public static WebElement clickSearchBoxUser(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[11]/div/span/input")));
				return litigation;
			}
			
		
			public static WebElement selectUsers(WebDriver driver)
			{
		    
			  //  litigation=driver.findElement(By.xpath("//*[@id='d1930d81-019a-4612-ab86-bc6942b76687_tv_active']/div/span[2]"));
			    
			    litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-wrapper'])[9]"));
				
				return litigation;
			}
			
	
			public static WebElement clickMailServices(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='childrow']/div/div[1]/div[4]/div/div/span[1]")));
				return litigation;
			}
			
			public static WebElement clickSearchBoxMail(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/span/input")));
				return litigation;
			}
			
			
			
			public static WebElement selectMailService(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='k-checkbox-wrapper'])[2]")));
				return litigation;
			}
			public static WebElement clickEnable(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnEnabledMail']")));
				return litigation;
			}
			public static WebElement clickExportMail(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[1]/a")));
				return litigation;
			}
			public static WebElement clickDisable(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnDisabledMail']")));
				return litigation;
			}
			
			public static WebElement clickCaseNoticeStageHearingGraph(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[2]"));
				return litigation;
			}
			public static WebElement clickCaseNoticeStageHearingGraph1(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-7 '])[1]"));
				return litigation;
			}
			public static WebElement clickCaseNoticeStageHearingExport(WebDriver driver)
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='exportReport']")));
				return litigation;
			}
			
			public static WebElement clickGridCount(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid']/div[3]/span[2]"));
				return litigation;
			}
			public static WebElement clickLocationFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/div[1]/div/span[1]"));
				return litigation;
			}
			public static WebElement clickLocationFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-icon k-i-expand'])[1]"));
				return litigation;
			}
			
			public static WebElement clickLocationFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[97]"));
				return litigation;
			}
			public static WebElement clickLocationFilter4(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[268]"));
				return litigation;
			}
			public static WebElement clickCaseNotice(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/div[2]/div/span[1]"));
				return litigation;
			}
			
			public static WebElement selectCaseNotice(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//div[@class='k-check-all'])[7]"));
				return litigation;
			}
			
			public static WebElement clickStatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/div[3]/div"));
				return litigation;
			}
			public static WebElement selectStatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[181]"));
				return litigation;
			}
			
			
			public static WebElement clickDepartmentFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/div[4]/div"));
				return litigation;
			}
			
			public static WebElement selectDepartmentFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[50]"));
				return litigation;
			}
			public static WebElement selectDepartmentFilter1(WebDriver driver)
			{

				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[56]"));
				return litigation;
			}
			public static WebElement clickRiskFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[6]"));
				return litigation;
			}
			public static WebElement selectRiskFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[152]"));
				return litigation;
			}
			public static WebElement selectRiskFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[137]"));
				return litigation;
			}
			public static WebElement clickAgeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/span/span/span[1]"));
				return litigation;
			}
			public static WebElement selectAgeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='drpAgeing_listbox']/li[1]"));
				return litigation;
			}
			public static WebElement clickCategoryFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[2]/div[1]/div"));
				return litigation;
			}
			public static WebElement selectCategoryFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[3]"));
				return litigation;
			}
			public static WebElement selectCategoryFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[5]"));
				return litigation;
			}
			
			
			public static WebElement clickStageFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[2]/div[2]/div"));
				return litigation;
			}
			public static WebElement selectStageFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[39]"));
				return litigation;
			}
			
			public static WebElement selectNotice1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[183]"));
				return litigation;
			}
			public static WebElement clickCaseNoticeType1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/div[5]/div/span[1]"));
				return litigation;
			}
			public static WebElement selectCaseNoticeType1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[29]"));
				return litigation;
			}
			
			
        	public static WebElement clearButton(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ClearfilterMain']"));
				return litigation;
			}
			
			
			public static WebElement CaseHearingCount(WebDriver driver) 
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ContentPlaceHolder1_divPendingHearing']")));
				return litigation;
			}
			public static WebElement CaseHearingGridCount(WebDriver driver) 
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gridPendingUpdation']/div[3]/span[2]")));
				return litigation;
			}
			public static WebElement CaseHearingExport(WebDriver driver) 
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnexport']")));
				return litigation;
			}
			public static WebElement CaseHearingView(WebDriver driver) 
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gridPendingUpdation']/div[2]/table/tbody/tr[1]/td[7]/a")));
				return litigation;
			}
			public static WebElement CaseHearingPopupClose(WebDriver driver) 
			{
				WebDriverWait wait= new WebDriverWait(driver,30);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divShowDialog']/div/div/div[1]/button")));
				return litigation;
			}
			
			
			public static WebElement HearingCalender(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div[13]/a")));
				return litigation;
			}
			public static WebElement HearingCalenderNum(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=driver.findElement(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div[32]/span"));
				return litigation;
			}
			public static WebElement HearingCalenderNumcfo(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='collapseUpcomingHearing']/div/div[2]/div[1]/div/div[3]/div[6]/span")));
				return litigation;
			}
			
			public static WebElement HearingCalenderCount(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[3]/span[2]")));
				return litigation;
			}
			
			
			public static WebElement HearingCalenderView(WebDriver driver)
			{
				WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[6]/a")));
				return litigation;
			}
			
			
			public static WebElement HearingCalenderExport(WebDriver driver)
			{
				//WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=driver.findElement(By.xpath("//*[@id='exportReport']/span"));
				return litigation;
			}
			public static WebElement HearingCalenderclose(WebDriver driver)
			{
				//WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=driver.findElement(By.xpath("//*[@id='divShowDialog']/div/div/div[1]/button"));
				return litigation;
			}
			
			public static WebElement CaseNoticeTypeViewGraph(WebDriver driver)
			{
				//WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=driver.findElement(By.xpath("//a[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
				return litigation;
			}
			public static WebElement CaseNoticeTypeclosePopupGraph(WebDriver driver)
			{
				//WebDriverWait wait=new WebDriverWait(driver,30);
				litigation=driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
				return litigation;
			}
			
			public static WebElement viewNoticeDetails1(WebDriver driver)
			{
				
				litigation=driver.findElement(By.xpath("//a[@class='k-button k-button-icontext ob-hearing k-grid-hearing']"));
	            return litigation;
			}
			public static WebElement viewNoticeDetails(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[24]/a[2]"));
				return litigation;
			}
			public static WebElement viewTaskDetails(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[8]/a"));
				return litigation;
			}
			public static WebElement Actionclosepopup(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='btnAddEditcase1']"));
				return litigation;
			}
			public static WebElement Actionclosepopup1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
				return litigation;
			}
			
			public static WebElement ActioncloseTaskpopup(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='divShowDialog']/div/div/div[1]/button"));
				return litigation;
			}
			public static WebElement showResponseDetailIcon(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[24]/a[1]"));
				return litigation;
			}
		
			public static WebElement showResponseDetailIcon1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//a[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
				return litigation;
			}
			public static WebElement clickEditReminder(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[8]/a[1]"));
				return litigation;
			}
			public static WebElement clickDeleteReminder(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//a[@class='k-button k-button-icontext ob-deleteuser k-grid-edit1']"));
				return litigation;
			}
			
			public static WebElement clickCaseNotice1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='linoticeCase']/a"));
				return litigation;
			}
			
			public static WebElement clicklocationFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[2]/div[1]/div/span[1]"));
				return litigation;
			}
			public static WebElement clicklocationFilter2(WebDriver driver)
			{
				 WebDriverWait wait = new WebDriverWait(driver, 300);
				  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[242]")));
				return litigation;
			}
			public static WebElement clicklocationFilter3(WebDriver driver)
			{
				 WebDriverWait wait = new WebDriverWait(driver, 300);
				  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[276]")));
				return litigation;
			}
			public static WebElement clicklocationFilter4(WebDriver driver)
			{
				 WebDriverWait wait = new WebDriverWait(driver, 300);
				  litigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='k-in k-state-selected')[2]")));
				return litigation;
			}
			public static WebElement clickDepartmentFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[2]/div[2]/div"));
				return litigation;
			}
			public static WebElement clickDepartmentFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[387]"));
				return litigation;
			}
			public static WebElement clickDepartmentFilter3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[402]"));
				return litigation;
			}
			
			public static WebElement clickFinancialYear2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[2]/span[2]/span/span[1]"));
				return litigation;
			}
			
			public static WebElement clickFinancialYear3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownFY_listbox']/li[2]"));
				return litigation;
			}
			
			public static WebElement clickCalenderYear2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[2]/span[3]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickCalenderYear3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownCalYear_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement clickstatus(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[3]/span/span/span[1]"));
				return litigation;
			}
			
			public static WebElement clickstatus1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownStatus_listbox']/li[3]"));
				return litigation;
			}
			public static WebElement clickcategory(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[3]"));
				return litigation;
			}
			public static WebElement clickcategory1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[286]"));
				return litigation;
			}
			public static WebElement clickcategory2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[153]"));
				return litigation;
			}
			public static WebElement clickType1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[3]/div[2]/div"));
				return litigation;
			}
			public static WebElement clicktype1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[464]"));
				return litigation;
			}
			public static WebElement clickType2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[726]"));
				return litigation;
			}
			public static WebElement clicktype2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[476]"));
				return litigation;
			}
			public static WebElement clicktype4(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[488]"));
				return litigation;
			}
			public static WebElement clicktype3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-in k-state-selected'])[3]"));
				return litigation;
			}
			public static WebElement clickeditButton(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid1']/div[2]/table/tbody/tr[1]/td[18]/a[1]"));
				return litigation;
			}
			public static WebElement clickdeleteButton(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid1']/div[2]/table/tbody/tr[1]/td[18]/a[2]"));
				return litigation;
			}
			public static WebElement viewTaskDetails1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[9]/a"));
				return litigation;
			}
			public static WebElement clickDropdown(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[2]/span[1]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickCaseHearing1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='liHearing']/a"));
				return litigation;
			}
			public static WebElement clickSearchFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='txtSearch']"));
				return litigation;
			}
			
			public static WebElement clickTaskLocFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div[1]/div"));
				return litigation;
			}
			public static WebElement clickTaskLocFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[5]"));
				return litigation;
			}
			
			public static WebElement clickTaskPriorityFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div[2]/div"));
				return litigation;
			}
			
			public static WebElement clickTaskPriorityFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[32]"));
				return litigation;
			}
			public static WebElement clickTaskPriorityFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[23]"));
				return litigation;
			}
			public static WebElement clickTaskStatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div[3]/div"));
				return litigation;
			}
			public static WebElement clickTaskStatusFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[35]"));
				return litigation;
			}
			public static WebElement clickTaskStatusFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[26]"));
				return litigation;
			}
			public static WebElement clickTaskPeriodFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/span/span/span[1]"));
				return litigation;
			}
			public static WebElement clickTaskPeriodFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownPastData_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement clickDocStatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/span[2]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickDocStatusFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownlist2_listbox']/li[3]"));
				return litigation;
			}
			public static WebElement clickDocStatusFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownType_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement clickDocTypeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/div[1]/div"));
				return litigation;
			}
			public static WebElement clickDocTypeFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[459]"));
				return litigation;
			}
			public static WebElement clickDocTypeFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[1]"));
				return litigation;
			}
			public static WebElement clickDocLocFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/div[3]/div/span[1]"));
				return litigation;
			}
			public static WebElement clickDocLocFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[16]"));
				return litigation;
			}
			public static WebElement clickDocLocFilter3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[21]"));
				return litigation;
			}
			public static WebElement clickDocDeptFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/div[4]/div/span[1]"));
				return litigation;
			}
			public static WebElement clickDocDeptFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[109]"));
				return litigation;
			}
			
			public static WebElement clickDocDropdownFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/span[1]/span/span[1]"));
				return litigation;
			}
			
			public static WebElement clickDocTaskFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/span[3]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickDocTaskFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownTaskType_listbox']/li[4]"));
				return litigation;
			}
			public static WebElement clickDocTaskPriorityFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/div[2]/div"));
				return litigation;
			}
			public static WebElement clickDocTaskPriorityFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[158]"));
				return litigation;
			}
			public static WebElement clickDocTaskPriorityFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[210]"));
				return litigation;
			}
			public static WebElement clickDocTaskPriorityFilter3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[181]"));
				return litigation;
			}
			public static WebElement clickReportStatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[3]/span[2]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickReportStatusFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//ul[@id='dropdownStatus_listbox']/li[3]"));
				return litigation;
			}
			public static WebElement clickReportStatusFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//ul[@id='dropdownlist2_listbox]/li[4]"));
				return litigation;
			}
			
			public static WebElement clickReportDeptFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[3]/div[1]/div/span[1]"));
				return litigation;
			}
			public static WebElement clickReportDeptFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[387]"));
				return litigation;
			}
			public static WebElement clickReportDeptFilter3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[397]"));
				return litigation;
			}
			public static WebElement clickReportDeptFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[402]"));
				return litigation;
			}
			public static WebElement clickReportDeptFiltercfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[484]"));
				return litigation;
			}
			public static WebElement clickReportTypeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[3]/div[2]/div"));
				return litigation;
			}
			public static WebElement clickReportTypeFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[461]"));
				return litigation;
			}
			public static WebElement clickReportTypeFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[480]"));
				return litigation;
			}
			public static WebElement clickReportTypeFilter5(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-wrapper'])[597]"));
				return litigation;
			}
			public static WebElement clickReportTypeFilter4(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[738]"));
				return litigation;
			}
			public static WebElement clickReportTypeFilter3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[471]"));
				return litigation;
			}
			public static WebElement clickReportTypeFiltercfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[585]"));
				return litigation;
			}
			public static WebElement clickReportTypeFiltercfo1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[586]"));
				return litigation;
			}
			public static WebElement clickReportCategoryFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[3]/div[3]/div/span[1]"));
				return litigation;
			}
			public static WebElement clickReportCategoryFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[96]"));
				return litigation;
			}
			public static WebElement clickReportCategoryFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[94]"));
				return litigation;
			}
			public static WebElement clickReportCategoryFiltercfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[255]"));
				return litigation;
			}
			public static WebElement clickReportLocFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[4]/div/div"));
				return litigation;
			}
			public static WebElement clickReportLocFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[242]"));
				return litigation;
			}
			public static WebElement clickReportLocFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[379]"));
				return litigation;
			}
			public static WebElement clickReportLocFiltercfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[398]"));
				return litigation;
			}
			public static WebElement clickReportFYFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[4]/span[1]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickReportFYFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownFY_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement clickReportCYFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div/div[4]/span[2]/span/span[1]"));
				return litigation;
			}
			public static WebElement clickReportCYFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownCalYear_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement clickReportprioFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div[2]/div"));
				return litigation;
			}
			public static WebElement clickReportprioFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[4]"));
				return litigation;
			}
			public static WebElement clickReportstatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div[3]/div/span[1]"));
				return litigation;
			}
			public static WebElement clickReportstatusFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//span[@class='k-checkbox-label checkbox-span']"));
				return litigation;
			}
			public static WebElement clickReportFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/span/span/span[1]"));
				return litigation;
			}
			public static WebElement clickReportFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='dropdownPastData_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement clickDashboardLocFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxFilterLocation']"));
				return litigation;
			}
			public static WebElement clickDashboardLocFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tvFilterLocationt10']"));
				return litigation;
			}
			public static WebElement clickDashboardCaseNoticeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlTypePage_chosen']/a/span"));
				return litigation;
			}
			public static WebElement clickDashboardNoticeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlTypePage_chosen']/div/ul/li[2]"));
				return litigation;
			}
			public static WebElement clickDashboardCaseFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlTypePage_chosen']/div/ul/li[3]"));
				return litigation;
			}
			public static WebElement clickDashboardTypeFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlNoticeTypePage_chosen']/a/span"));
				return litigation;
			}
			public static WebElement clickDashboardTypeFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlNoticeTypePage_chosen']/div/ul/li[2]"));
				return litigation;
			}
			public static WebElement clickDashboardDeptFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlDeptPage_chosen']/a/span"));
				return litigation;
			}
			public static WebElement clickDashboardDeptFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlDeptPage_chosen']/div/ul/li[4]"));
				return litigation;
			}
			public static WebElement clickDashboardstatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlStatus_chosen']/a/span"));
				return litigation;
			}
			public static WebElement clickDashboardstatusFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlStatus_chosen']/div/ul/li[2]"));
				return litigation;
			}
			public static WebElement clickDashboardRiskFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlWinningImpact_chosen']/a/span"));
				return litigation;
			}
			public static WebElement clickDashboardRiskFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlWinningImpact_chosen']/div/ul/li[2]"));
				return litigation;
			}
			public static WebElement clickDashboardApplyBtn(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnFilter']"));
				return litigation;
			}
			
			public static WebElement clickDashboardClearBtn(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnClearFilter']"));
				return litigation;
			}
			
			public static WebElement clickLegalEntityFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxFilter']"));
				return litigation;
			}
			public static WebElement clickSubUnits(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdCustomerBranch']/tbody/tr[3]/td[8]/a"));
				return litigation;
			}
			public static WebElement clickSubUnitscfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdCustomerBranch']/tbody/tr[2]/td[8]/a"));
				return litigation;
			}
			public static WebElement clickUnitTypePlusIcon(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_lnkShowAddNewVendorModal']"));
				return litigation;
			}
			public static WebElement EnterUnitType(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_tbxSubunitType']"));
				return litigation;
			}
			public static WebElement SaveUnitType(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_btnSaveSubUnitType']"));
				return litigation;
			}
			public static WebElement SaveValidationMsg(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_SubUnitValidationSummary']"));
				return litigation;
			}
			public static WebElement CloseUnitType(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_btnCancelCityPopUp']"));
				return litigation;
			}
			public static WebElement CloseLegalEntity(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_btnCancel']"));
				return litigation;
			}
			public static WebElement clickLawFirmFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxtypeTofilter']"));
				return litigation;
			}
			
			public static WebElement clickApplybtn(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkBtnApplyFilter']"));
				return litigation;
			}
			public static WebElement clickCustomParameterFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlType_chosen']/a/span"));
				return litigation;
			}
			
			public static WebElement clickCustomParameterFilter1(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlType_chosen']/div/ul/li[2]"));
				return litigation;
			}
			public static WebElement clickNoticeStageFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='txtSearch']"));
				return litigation;
			}
			public static WebElement clickLocationFilter3(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[117]"));
				return litigation;
			}
			public static WebElement selectstatusFilter(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[196]"));
				return litigation;
			}
			public static WebElement selectstatusFiltercfo(WebDriver driver)
			{
				litigation=driver.findElement(By.linkText("Closed"));
				return litigation;
			}
			public static WebElement selectDepartmentFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[57]"));
				return litigation;
			}
			public static WebElement selectCaseNoticeType2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[28]"));
				return litigation;
			}
			public static WebElement selectRiskFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[202]"));
				return litigation;
			}
			public static WebElement selectRiskFilter2cfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[204]"));
				return litigation;
			}
			public static WebElement selectAgeFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='drpAgeing_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement selectAgeFiltercfo(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("//*[@id='drpAgeing_listbox']/li[2]"));
				return litigation;
			}
			public static WebElement selectCategoryFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[3]"));
				return litigation;
			}
			public static WebElement selectStageFilter2(WebDriver driver)
			{
				litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[38]"));
				return litigation;
			}
			
			 public static WebElement RiskSummaryHigh(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[3]"));
				  return litigation;
			  }
			 public static WebElement RiskSummaryMedium(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[3]"));
				  return litigation;
			  }
			 public static WebElement RiskSummaryLow(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[3]"));
				  return litigation;
			  }
			 public static WebElement RiskSummaryLowCA(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[2]"));
				  return litigation;
			  }
			 public static WebElement RiskSummaryNotApplicable(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[3]"));
				  return litigation;
			  }
			 public static WebElement RiskSummaryNotApplicableCA(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[2]"));
				  return litigation;
			  }
			 public static WebElement RiskSummaryGraph2(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[4]"));
				  return litigation;
			  }
			 public static WebElement DepartmentSummaryGraph1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[4]"));
				  return litigation;
			  }
			 public static WebElement DepartmentSummaryGraph2(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[4]"));
				  return litigation;
			  }
			 public static WebElement CaseNoticeTypeOutwardPlaintiff(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])"));
				  return litigation;
			  }
			 public static WebElement CaseNoticeTypeInwardDefendent(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 ']"));
				  return litigation;
			  }
			 public static WebElement CaseNoticeTypeComplinant(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 ']"));
				  return litigation;
			  }
			 public static WebElement CaseNoticeTypeRespondent(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 ']"));
				  return litigation;
			  }
			 public static WebElement CaseNoticeTypeApplicant(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-4 ']"));
				  return litigation;
			  }
			 public static WebElement CaseNoticeTypePetitioner(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-5 ']"));
				  return litigation;
			  }
			 public static WebElement ExpensesNoticeGraph(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[10]"));
				  return litigation;
			  }
			 public static WebElement ExpensesCategoryWiseNoticeGraph(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[12]"));
				  return litigation;
			  }
			 public static WebElement ExpensesCaseGraph(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[9]"));
				  return litigation;
			  }
			 public static WebElement ClickDetailedExpenseReport(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//a[@class='k-button k-button-icontext ob-export k-grid-edit2']"));
				  return litigation;
			  }
			 public static WebElement clickDeptCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']/a/span"));
				  return litigation;
			  }
			 public static WebElement selectDeptCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']/div/ul/li[3]"));
				  return litigation;
			  }
			 public static WebElement clickOwnerCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlOwner_chosen']/a/span"));
				  return litigation;
			  }
			 public static WebElement selectOwnerCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlOwner_chosen']/div/ul/li[2]"));
				  return litigation;
			  }
			 public static WebElement clickRiskCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']/a/span"));
				  return litigation;
			  }
			 public static WebElement selectRiskCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']/div/ul/li[2]"));
				  return litigation;
			  }
			 public static WebElement selectLocationCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='tvBranchest3']"));
				  return litigation;
			  }
			 public static WebElement clickAdditionalOwnerCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='pnlNoticeAssignment']/div[1]/div/span[1]/div/button"));
				  return litigation;
			  }
			 public static WebElement selectAdditionalOwnerCfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='pnlNoticeAssignment']/div[1]/div/span[1]/div/ul/li[4]/a/label/input"));
				  return litigation;
			  }
			 public static WebElement selectApplyBtn(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ApplyBtnMain']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentDownloadcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticeDocuments_lnkBtnDownLoadNoticeDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentViewcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticeDocuments_lblNoticeDocView_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentViewClosepopupcfo(WebDriver driver) throws InterruptedException
			  {
				 WebDriverWait wait= new WebDriverWait(driver,20);
				litigation =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DocumentReviewPopUp1']/div/div/div[1]/button")));
				return litigation;
			  }
			 public static WebElement clickNoticeDocumentdeletecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticeDocuments_lnkBtnDeleteNoticeDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentsharecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticeDocuments_lnkBtnShareNoticeDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentshareemailcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='txtEmail']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentsharecontactnocfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='txtcontactNum']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentsharesavecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='btnUpdateDocInfo']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentshareclosepopupcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='btnCancel']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentsharereadmsgcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='vsDocInfo']/ul/li"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDocumentshareInvalidmsgcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='vsDocInfo']/ul"));
				  return litigation;
			  }
			 public static WebElement clickNoticeEditTaskcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnEditTaskDoc_1']/img"));
				  return litigation;
			  }
			
			 public static WebElement clickNoticeTaskEditResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnEditTask_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskEditResponsecfo1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnEditResponseTask_1']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskEditResponse1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnEditResponseTask_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskstatusResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlStatus_chosen']/a/span"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskstatusResponsecfo1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='ddlStatus_chosen']/div/ul/li[1]"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskcmtResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='tbxTaskResComment']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskSaveResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='btnSaveTaskResponse']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskCloseResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='divShowDialog']/div/div/div[1]/button"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskClosecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnCloseTask_0']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskClosecfo1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnResCloseTask_0']"));
				  return litigation;
			  }
			 public static WebElement clickNoticeTaskdeletecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdTaskActivity_lnkBtnDeleteTask_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeEditResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdResponseLog_lnkBtnEditResponseDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDownloadResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdResponseLog_lnkBtnDownLoadResponseDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeViewResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdResponseLog_lblNoticeResponseDocView_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeclosePopupResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='DocumentReviewPopUp1']/div/div/div[1]/button"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDeleteResponsecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdResponseLog_lnkBtnDeleteResponse_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeViewPaymentDoccfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticePayment_lnkBtnViewPayment_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeclosePaymentDocpopupcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='PaymentDocumentReviewPopUp1']/div/div/div[1]/button"));
				  return litigation;
			  }
			 public static WebElement clickNoticeEditPaymentcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticePayment_lnkBtnEditPayment_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDeletePaymentcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticePayment_lnkBtnDeletePayment_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickNoticeDownloadPaymentcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdNoticePayment_lnkBtnDownLoadCaseDoc_0']/img"));
				  return litigation;
			  }
			 public static void clickNoticeResponseDocUploadtcfo(WebDriver driver) throws InterruptedException
			  {
				  
				 WebElement ResponseDoc =driver.findElement(By.xpath("//*[@id='fuResponseDocUpload']"));
				 ResponseDoc.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
				  
			  }
			 public static void clickNoticeStatusPaymentUploadtcfo(WebDriver driver) throws InterruptedException
			  {
				  
				 WebElement ResponseDoc =driver.findElement(By.xpath("//*[@id='grdNoticePayment_fuSampleFileNew']"));
				 ResponseDoc.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
				  
			  }
			 public static void clickCaseStatusPaymentUploadtcfo(WebDriver driver) throws InterruptedException
			  {
				  
				 WebElement ResponseDoc =driver.findElement(By.xpath("//*[@id='grdCasePayment_fuSampleFile']"));
				 ResponseDoc.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
				  
			  }
			 public static void clickCaseorderFile(WebDriver driver) throws InterruptedException
			  {
				  
				 WebElement ResponseDoc =driver.findElement(By.xpath("//*[@id='fuCaseOrderDocUpload']"));
				 ResponseDoc.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
				  
			  }
			 
			 public static WebElement clickCaseDownloadDocumentcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseDocuments_lnkBtnDownLoadCaseDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickCaseDocumentViewcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseDocuments_lnkBtnViewDocCase_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickCaseDocumentdeletecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseDocuments_lnkBtnDeleteCaseDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickCaseDocumentsharecfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseDocuments_LinkButton2_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickEditCaseHearingcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdResponseLog_lnkBtnEditResponseDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickDeleteCaseHearingcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdResponseLog_lnkBtnDeleteResponse_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickCaseHearingcfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='secondTabAccordion']/div/div/div[1]/div/div/a/i"));
				  return litigation;
			  }
			 public static WebElement clickEditCaseOrdercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseOrder_lnkBtnEditOrderDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickDownloadCaseOrdercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseOrder_lnkBtnDownloadOrderDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickViewCaseOrdercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseOrder_lnkBtnViewDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickDeleteCaseOrdercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCaseOrder_lnkBtnDeleteOrder_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickViewPaymentDoccfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_lnkBtnViewPayment_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickEditPaymentDoccfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_lnkBtnEditPayment_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickDeletePaymentDoccfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_lnkBtnDownLoadCaseDoc_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickDeletePaymentDoccfo1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='grdCasePayment_lnkBtnDeletePayment_0']/img"));
				  return litigation;
			  }
			 public static WebElement clickLocationFiltercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[398]"));
				  return litigation;
			  }
			 public static WebElement clickDepartFiltercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[484]"));
				  return litigation;
			  }
			 public static WebElement clickCategoryFiltercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[251]"));
				  return litigation;
			  }
			 public static WebElement clickCategoryFiltercfo1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[252]"));
				  return litigation;
			  }
			 
			 public static WebElement clickTypeFiltercfo(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[575]"));
				  return litigation;
			  }
			 public static WebElement clickTypeFiltercfo1(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[585]"));
				  return litigation;
			  }
			 public static WebElement clickTaskLocFiltercfo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[6]"));
					return litigation;
				}
			 public static WebElement clickTaskPriorityFiltercfo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[30]"));
					return litigation;
				}
			 public static WebElement clickTaskStatusFiltercfo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[33]"));
					return litigation;
				}
			 public static WebElement clickUserMasterResetcfo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdUser_lbtnReset_0']"));
					return litigation;
				}
			 public static WebElement clickDraftcfo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divRPADraft']"));
					return litigation;
				}
			 public static WebElement clickReminderFilter(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div/div[1]/span/span"));
					return litigation;
				}
			 public static WebElement clickReminderFilter1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='dropdownlist1_listbox']/li[2]"));
					return litigation;
				}
			 public static WebElement clickReminderFilter2(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='dd2']/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement clickReminderFilter3(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='dropdownlist2_listbox']/li[2]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmt(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='leftCMmastermenu']/a/span[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtAdd(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnAddNew']"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtCBU(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='window']/div[1]/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtCBUdropdown(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ddlCBU_listbox']/li[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtZone(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='window']/div[2]/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtZoneSearch(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ddlZone-list']/span/input"));
					return litigation;
				}

			 public static List<WebElement> clickCustomerMgmtZonedropdown(WebDriver driver) throws InterruptedException
			 {
				 WebDriverWait wait=new WebDriverWait(driver,20);
				 elementsList=driver.findElements(By.xpath("//*[@id='ddlZone_listbox']/li"));
			      return elementsList; 
			
			 }
			 public static WebElement clickCustomerMgmtRegion(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='window']/div[3]/span/span/span[1]"));
					return litigation;
				}
			 public static List<WebElement> clickCustomerMgmtRegiondropdown(WebDriver driver)
				{
					 WebDriverWait wait=new WebDriverWait(driver,20);
					 elementsList=driver.findElements(By.xpath("//*[@id='ddlRegion_listbox']/li"));
				      return elementsList; 
				}
			 public static WebElement clickCustomerMgmtTerritory(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='window']/div[4]/span/span/span[1]"));
					return litigation;
				}
			
			 public static List<WebElement> clickCustomerMgmtTerritorydropdown(WebDriver driver) throws InterruptedException
			 {
				 WebDriverWait wait=new WebDriverWait(driver,20);
				 elementsList=driver.findElements(By.xpath("//*[@id='ddlTerritory_listbox']/li"));
			      return elementsList; 
			
			 }
			 public static WebElement clickCustomerMgmtCityname(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtCity']"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtSave(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnSubmit']"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtClose2(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[16]/div[1]/div/a"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtClose(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[14]/div[1]/div/a"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtClose1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[18]/div[1]/div/a"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtCity(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='MasterCMsubmenu']/li[1]/a"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtOk(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//button[@class='k-button k-primary']"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtOk1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//div[@role='toolbar']"));
					return litigation;
				}
			 
			 public static WebElement clickCustomerMgmtEdit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[6]/a[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtCustomer(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='MasterCMsubmenu']/li[2]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtDelete(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[6]/a[2]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtCBUFilter(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='childrow']/div/div[1]/div[1]/div[1]/div"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtCBUFilter1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerName(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtCustomerName']"));
					return litigation;
				}
			 public static WebElement clickCustomerID(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtSalesCustomerID']"));
					return litigation;
				}
			 public static WebElement clickSPOCName(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtUserName']"));
					return litigation;
				}
			 public static WebElement clickEmailID(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtEmailID']"));
					return litigation;
				}
			 public static WebElement clickMobNo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtMobileNo']"));
					return litigation;
				}
			 public static WebElement clickCity1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='window']/div/div[7]/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement selectCity1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ddlCity_listbox']/li[1]"));
					return litigation;
				}
			 public static WebElement clickWhatsappNo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtWhatsappNo']"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtEdit1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[15]/a[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtok(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//button[@class='k-button k-primary'])[1]"));
					return litigation;
				}
			 public static WebElement clickCustomerMgmtDelete1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[15]/a[2]"));
					return litigation;
				}
			 
			  public static WebElement clickCustomerUpload(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='btnUploadCustomer']"));
				     
				     return litigation;
		      }
			  public static WebElement clickChooseFile(WebDriver driver)
				{
				  WebElement CustomerUpload=driver.findElement(By.xpath("//*[@id='fileinputForCustomer']"));
				   CustomerUpload.sendKeys("C:\\Users\\Admin\\Desktop\\Customer (3).xlsx");
					return litigation;
				}
			  public static WebElement clickUploadfile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnFileUploadForCustomer']"));
					return litigation;
				}
			  public static WebElement clickCustomerUploadOutStanding(WebDriver driver) throws InterruptedException
			  {
				  
				  litigation=driver.findElement(By.xpath("//*[@id='btnUpload']"));
			        return litigation;
		      }
			  public static WebElement clickChooseFile1(WebDriver driver)
				{

				     WebElement CustomerUploadOutStanding=driver.findElement(By.xpath("//*[@id='fileinput']"));
				     CustomerUploadOutStanding.sendKeys("C:\\Users\\Admin\\Desktop\\CustomerOutsatnding (1).xlsx");
					return litigation;
				}
			  public static WebElement clickUploadfile1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnFileUpload']"));
					return litigation;
				}
			  public static WebElement clickPlanVisit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='MasterCMsubmenu']/li[3]/a"));
					return litigation;
				}
			  public static WebElement clickPlanVisitAdd(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnAddVisits']"));
					return litigation;
				}
			  public static WebElement selectPlanVisitcustomaerid(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-checkbox-label checkbox-span'])[1]"));
					return litigation;
				}
			  public static WebElement clickPlanVisitcustomaerid(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='dvSalesCustomer']/div/div/span[1]"));
					return litigation;
				}
			  public static WebElement clickPlanVisitdate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='dateVisit']"));
					return litigation;
				}
			  public static WebElement clickPlanVisitremark(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtRemarks']"));
					return litigation;
				}
			  public static WebElement clickPlanVisitsubmit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnAddEditVisits']"));
					return litigation;
				}
			  public static WebElement clickUpdatedVisit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tabstripVisits']/ul/li[2]/span[2]"));
					return litigation;
				}
			  public static WebElement clickEditUpdatedVisit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridUpdatedVisits']/div[2]/table/tbody/tr[1]/td[13]/a"));
					return litigation;
				}
			  public static WebElement clickPlanVisityear(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='dateCaseYear']"));
					return litigation;
				}
			  public static WebElement clickPlanVisitedit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPlannedVisits']/div[2]/table/tbody/tr[1]/td[13]/a[1]"));
					return litigation;
				}
			  public static WebElement clickPlanVisitdelete(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPlannedVisits']/div[2]/table/tbody/tr[1]/td[13]/a[2]"));
					return litigation;
				}
			  public static WebElement clickPlanVisitOverdueVisit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tabstripVisits']/ul/li[2]/span[2]"));
					return litigation;
				}
			  public static WebElement clickPlanVisitErrotfile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='errorLink']"));
					return litigation;
				}
			  public static WebElement clickCustomerErrotfile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='errorLinkForCustomer']"));
					return litigation;
				}
			  public static WebElement clickUpdateCommitmentsafterremarks(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='MasterCMsubmenu']/li[4]/a"));
					return litigation;
				}
			  public static WebElement clickEditPendingVisit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPendingVisits']/div[2]/table/tbody/tr[1]/td[13]/a"));
					return litigation;
				}
			  public static WebElement clickAddNewRecord(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[1]/a"));
					return litigation;
				}
			  public static WebElement clickAmount2(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[3]/table/tbody/tr/td[3]/span[1]/span/input[1]"));
					return litigation;
				}
			  public static WebElement clickFollowupDate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='Schedule_FollowUp_Date']"));
					return litigation;
				}
			  public static WebElement clickFollowupDate1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@name='Schedule_FollowUp_Date']"));
					return litigation;
				}
			  public static WebElement clickUpdate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[3]/table/tbody/tr/td[5]/a[1]"));
					return litigation;
				}
			  public static WebElement clickScheduleDate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ScheduleDate']"));
					return litigation;
				}
			  public static WebElement clickScheduleDate1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[3]/table/tbody/tr[1]/td[2]/span/span/span[2]"));
					return litigation;
				}
			  public static WebElement clickScheduleDate2(WebDriver driver)
				{
					litigation=driver.findElement(By.linkText("6"));
					return litigation;
				}
			  public static WebElement clickBlankSapce(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[3]"));
					return litigation;
				}
			  public static WebElement clickEdit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[3]/table/tbody/tr/td[5]/a[1]"));
					return litigation;
				}
			  public static WebElement clickDelete(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPaymentSchedule']/div[3]/table/tbody/tr/td[5]/a[2]"));
					return litigation;
				}
			  public static WebElement clickUpdateCommitmentsStatus(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='MasterCMsubmenu']/li[5]/a"));
					return litigation;
				}
			  public static WebElement clickUpdateCommitmentsStatusEdit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridSchedule']/div[2]/table/tbody/tr[1]/td[14]/a"));
					return litigation;
				}
			  public static WebElement clickAddNewRecords(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[1]/a"));
					return litigation;
				}
			  public static WebElement clickCommitDate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='CommitDate']"));
					return litigation;
				}
			  public static WebElement clickCommitAmount(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[3]/span[1]/span/input[1]"));
					return litigation;
				}
			  public static WebElement clickRecieptDate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ReceiveDate']"));
					return litigation;
				}
			  public static WebElement clickRecieptDateAmount(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[5]/span[1]/span/input[1]"));
					return litigation;
				}
			  public static WebElement clickCommitRemark(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@name='commitRemark']"));
					return litigation;
				}
			  public static WebElement clickUpdateCommit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[7]/a[1]"));
					return litigation;
				}
			  public static WebElement clickUpdateCommitedit(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[7]/a[1]"));
					return litigation;
				}
			  public static WebElement clickSendReminder(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[7]/a[3]"));
					return litigation;
				}
			  public static WebElement clickStopReminder(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[7]/a[4]"));
					return litigation;
				}
			  public static WebElement clickDelete1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridCommitments']/div[3]/table/tbody/tr[1]/td[7]/a[2]"));
					return litigation;
				}
			  public static WebElement clickclosePopuopCommitments(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[17]/div[1]/div/a"));
					return litigation;
				}
			  public static WebElement clickReports(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='MasterCMsubmenu']/li[6]/a"));
					return litigation;
				}
			  public static WebElement clickSchedulesReport(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tabstripVisits']/ul/li[2]/span[2]"));
					return litigation;
				}
			  public static WebElement clickCommitmentReport(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tabstripVisits']/ul/li[3]/span[2]"));
					return litigation;
				}
			  public static WebElement clickAuditLogReport(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tabstripVisits']/ul/li[4]/span[2]"));
					return litigation;
				}
			  public static WebElement clickExportAuditLogReport(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='exportReportLog']"));
					return litigation;
				}
			  public static WebElement clickNoRecordFound(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='gridPlannedVisits']/div[2]/div[1]/div"));
					return litigation;
				}
			  
			 public static WebElement clickAdvocateBillTab(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='lefAdvocatemenu']/a/span[1]"));
					return litigation;
				}
			 public static WebElement clickAdvocateBillTabViewIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[21]/a[2]"));
					return litigation;
				}
			 public static WebElement clickAdvocateBillTabAuditLog(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='lnkAuditLog']"));
					return litigation;
				}
			 public static WebElement clickAdvocateBillTabclose(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnAddEditcase1']"));
					return litigation;
				}
			 public static WebElement clickAdvocateBillTabTriangle(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[3]/td[1]/a"));
					return litigation;
				}
			 public static WebElement clickAdvocateBillTabTriangle1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[1]/a"));
					return litigation;
				}
			 public static WebElement clickApproverAssignmentLog(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tabstripAssignment']/ul/li[2]/span[2]"));
					return litigation;
				}
			 public static WebElement clickMinimize(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='DivTaskCollapsTwo']/div/div/div[1]/div/div/a"));
					return litigation;
				}
			 public static WebElement clickMinimizeHearing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//a[@class='btn-minimize'])[2]"));
					return litigation;
				}
			 public static WebElement clickMinimizeResponse(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='DivResponceCollapsTwo']/div/div/div[1]/div/div/a"));
					return litigation;
				}
			 public static WebElement clickReadHearingMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary9']"));
					return litigation;
				}
			 public static WebElement clickReadHearingMsg1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary1']/ul/li"));
					return litigation;
				}
			 public static WebElement clickHearingClearBtn(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@name='btnHearingClear']"));
					return litigation;
				}
			 public static WebElement clickReadOrderMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary10']"));
					return litigation;
				}
			 public static WebElement clickminimize(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='secondTabAccordion']/div/div/div[1]/div/div/a"));
					return litigation;
				}
			 public static WebElement clickUploadDoc(WebDriver driver)
				{
					WebElement UploadDocument =driver.findElement(By.xpath("//*[@id='fuAdvocateBillDocUpload']"));
					 UploadDocument.sendKeys("C:\\Users\\Snehal Patil\\Desktop\\Test Cases\\Approver Test Case.xlsx");
					return litigation;
				}
			 public static WebElement clickReadAdvocateMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary12']"));
					return litigation;
				}
			 public static WebElement clickReadAdvocateMsg1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary13']"));
					return litigation;
				}
			 public static WebElement clickeditAdvocatebill(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdAdvocateBill_lnkBtnEditCaseAdvocateBill_0']"));
					return litigation;
				}
			 public static WebElement clickDownloadDocAdvocatebill(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdAdvocateBill_lnkBtnDownloadAdvocateBillDoc_0']"));
					return litigation;
				}
			 public static WebElement clickViewDocAdvocatebill(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdAdvocateBill_lnkBtnViewAdvocateBillDoc_0']"));
					return litigation;
				}
			 public static WebElement clickViewDocAdvocatebillClose(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='DocumentAdvBillReviewPopUp1']/div/div/div[1]/button"));
					return litigation;
				}
			 public static WebElement clickViewDocAdvocatebillPdf(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdAdvocateBill_LnkBtnconvetpdf_0']"));
					return litigation;
				}
			 public static WebElement clickViewDocAdvocatebillPdfClose(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='divViewDocument']/div/div/div[1]/button"));
					return litigation;
				}
			 public static WebElement clickExportAdvocateBill(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnAdvbillExport']"));
					return litigation;
				}
			 public static WebElement clickAdvocateBillDelete(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdAdvocateBill_lnkBtnDeleteAdvocateBill_0']"));
					return litigation;
				}
			 public static WebElement clickTaskActionIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[9]/a"));
					return litigation;
				}
			 public static WebElement clickAssignedBy(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='lblAssignBy']"));
					return litigation;
				}
			 public static WebElement clickStatus1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ddlStatus_chosen']/a/span"));
					return litigation;
				}
			 public static WebElement clickStatusdropdown(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ddlStatus_chosen']/div/ul/li[2]"));
					return litigation;
				}
			 public static WebElement clickTaskResponse(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='vsTaskResponse']/ul/li"));
					return litigation;
				}
			 public static WebElement clickTaskResponseclose(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='divShowDialog']/div/div/div[1]/button"));
					return litigation;
				}
			 public static WebElement clickMasterAdvocateBill(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='Mastersubmenu']/li[13]/a"));
					return litigation;
				}
			 public static WebElement clickAddApprover(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[1]/div/a"));
					return litigation;
				}
			 public static WebElement clickSelectapprover1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[1]/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement clickSelectapprover1Dropdown(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ApproverLevel_listbox']/li[1]"));
					return litigation;
				}
			 public static WebElement clickSelectapprover2(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[2]/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement clickSelectapprover1Dropdown1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[9]/div/div[3]/ul/li[2]"));
					return litigation;
				}
			 public static WebElement clickupdate(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[3]/a[1]"));
					return litigation;
				}
			 public static WebElement clickAgeing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[12]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCA(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[11]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCA1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[60]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCA1to2(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[12]"));
					return litigation;
				}
			 
			 public static WebElement clickInwardDefendentCA1to21(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[61]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCA2to3Case(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[62]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCAMoreThan3yearsCase(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[63]"));
					return litigation;
				}
			 public static WebElement clickAgeingViewIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//a[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
					return litigation;
				}
			 public static WebElement clickAgeingViewNoticeSummary(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='lnkNoticeDetail']"));
					return litigation;
				}

              public static WebElement clickAgeingViewCaseSummary(WebDriver driver)
	          {
		          litigation=driver.findElement(By.xpath("//*[@id='lnkCaseDetail']"));
		           return litigation;
	          }
			 
			 public static WebElement clickAgeingViewNoticeSummaryCloseIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
					return litigation;
				}
			 public static WebElement clickComplainantAgeing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[11]"));
					return litigation;
				}
			 public static WebElement clickApplicantAgeing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[15]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiffAgeing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[11]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiffAgeing1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[59]"));
					return litigation;
				}
			 public static WebElement clickPetitionerAgeing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-4 '])[9]"));
					return litigation;
				}
			 public static WebElement clickRespondentAgeing(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-5 '])[9]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendent(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[15]"));
					return litigation;
				}
			 public static WebElement clickComplianant(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[15]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiff(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[13]"));
					return litigation;
				}
			 public static WebElement clickRespondent(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[11]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendent1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[14]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCA2to3(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[13]"));
					return litigation;
				}
			 public static WebElement clickInwardDefendentCase(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0 '])[14]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiffCase(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[13]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiffCaseCA(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[60]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiffCaseCA2to3(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[61]"));
					return litigation;
				}
			 public static WebElement clickOutwardPlaintiffCaseCAMoreThan3years(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[62]"));
					return litigation;
				}
			 public static WebElement clickPetitionerCase(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[9]"));
					return litigation;
				}
			 public static WebElement clickPetitionerCA(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[8]"));
					return litigation;
				}
			 public static WebElement clickPetitionerCA1To2Years(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-1 '])[12]"));
					return litigation;
				}
			 public static WebElement clickPetitionerCA1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[12]"));
					return litigation;
				}
			 public static WebElement clickPetitionerCAA(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-2 '])[13]"));
					return litigation;
				}
			 public static WebElement clickRespondentCA(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[8]"));
					return litigation;
				}
			 public static WebElement clickRespondentCA1To2Years(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[8]"));
					return litigation;
				}
			 public static WebElement clickRespondentCA1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[12]"));
					return litigation;
				}
			 public static WebElement clickRespondentCA2(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//*[@class='highcharts-label highcharts-data-label highcharts-data-label-color-3 '])[13]"));
					return litigation;
				}
			 public static WebElement clickNoticeClearBtn(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnClearNoticeDetail']"));
					return litigation;
				}
			 public static WebElement clickSelectCheckbox(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdShowDocumentList_chkDocument_0']"));
					return litigation;
				}
			 public static WebElement clickMailTo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tbxMailTo']"));
					return litigation;
				}
			 public static WebElement clickMessageMail(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='tbxMailMsg']"));
					return litigation;
				}
			 public static WebElement clickSend(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnSendDocumentMail']"));
					return litigation;
				}
			 public static WebElement clickSendMailMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary7']/ul/li"));
					return litigation;
				}
			 public static WebElement clickSendMailMsg1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ValidationSummary7']/ul"));
					return litigation;
				}
			 public static WebElement clickcloseBtn(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='divOpenSendMailPopup']/div/div/div[1]/button"));
					return litigation;
				}
			 public static WebElement clickLinkedNoticeViewIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdLinkedNotices_lnkViewLinkedNotice_0']/img"));
					return litigation;
				}
			 public static WebElement clickLinkedCaseViewIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdLinkedCases_lnkViewLinkedCase_0']/img"));
					return litigation;
				}
			 public static WebElement clickViewPopup(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='historyPopUpHeader']"));
					return litigation;
				}
			 public static WebElement clickClosePopup1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='divNoticeCaseHistoryPopup']/div/div/div[1]/button"));
					return litigation;
				}
			 public static WebElement clickLinkedNoticeDeleteIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdLinkedNotices_lnkBtnDeleteNoticeLinking_0']/img"));
					return litigation;
				}
			 public static WebElement clickLinkedCaseDeleteIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdLinkedCases_lnkBtnDeleteCaseLinking_0']/img"));
					return litigation;
				}
			 public static WebElement clickLinkedNoticeDeleteIconValidMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='vsLinkedNotices']/ul/li"));
					return litigation;
				}
			 public static WebElement clickLinkedCaseDeleteIconValidMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='vsLinkedCases']/ul/li"));
					return litigation;
				}
			 public static WebElement clickUserAssign(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdUserAssignment_lblUserName_2']"));
					return litigation;
				}
			 public static WebElement clickEditUserAssign(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='lnkBtnEditUserAssignment']"));
					return litigation;
				}
			 public static WebElement clickDeleteUserAssign(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdUserAssignment_lnkDeleteUserAssignment_0']/img"));
					return litigation;
				}
			 public static WebElement clickDeleteUserAssignValidMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='vsNoticeUserAssign']/ul/li"));
					return litigation;
				}
			 public static WebElement clickDeleteUserAssignValidMsg1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='vsCaseUserAssign']/ul/li"));
					return litigation;
				}
			 public static WebElement clickSearchDocument(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='txtdocsearch']"));
					return litigation;
				}
			 public static WebElement clickApplyBtn(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnsearchDoc']"));
					return litigation;
				}
			 public static WebElement clickDocName(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdNoticeDocuments_lblFileName_0']"));
					return litigation;
				}
			 public static WebElement clickDocName1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdCaseDocuments_lblFileName_0']"));
					return litigation;
				}
			 public static WebElement clickInvalidResponsemsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='vsTaskResponse']/ul/li"));
					return litigation;
				}
			 public static WebElement clickDeleteResponse(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdTaskResponseLognew_lnkBtnDeleteTaskResponse_0']/img"));
					return litigation;
				}
			 public static WebElement clickClearResponse(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='Button2']"));
					return litigation;
				}
			 public static WebElement clickClearNoticeResponse(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnResponseClear']"));
					return litigation;
				}
			 public static WebElement clickCriteriaInvalidMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='lblError']"));
					return litigation;
				}
			 public static WebElement clickCaseClearBtn(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='btnClearCaseDetail']"));
					return litigation;
				}
			 public static WebElement clickNoticAmountPaid(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grdNoticePayment_tbxAmountPaid']"));
					return litigation;
				}
			 public static WebElement clickNoticeStatus1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/form/div[3]/section/section/section/div/div/div/div[3]/span/span/span[1]"));
					return litigation;
				}
			 public static WebElement selectNoticeStatus(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/ul/li[2]"));
					return litigation;
				}
			 public static WebElement selectDocument(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[4]"));
					return litigation;
				}
			 public static WebElement selectDocument1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("/html/body/div[77]/div/div[2]/ul/li[2]"));
					
					
					return litigation;
					
					
				}
			 public static WebElement shareDocumentIcon(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]/td[16]/a[3]"));
					return litigation;
				}
			 public static WebElement shareDocumentIcon1(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[14]/a[3]"));
					return litigation;
				}
			 public static WebElement CloseSharePopup(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='divViewDocument3']/div/div/div[1]/button"));
					return litigation;
				}
			 public static WebElement ClickNewBtn(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//button[@id='menu1']"));
					return litigation;
				}
			 public static WebElement ClickNewFolderName(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//a[@id='ContentPlaceHolder1_lnkAddNewFolder']"));
					return litigation;
				}
			 public static WebElement ClickUsers(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']"));
					return litigation;
				}
			 public static WebElement ClickFoldername(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_txtFolderName']"));
					return litigation;
				}
			 public static WebElement ClickViewFile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_grdFolderDetail_lblName_0']"));
					return litigation;
				}
			 public static WebElement ClickDeleteFile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//img[@class='deletedrive']"));
					return litigation;
				}
			 public static WebElement ClickSuccessMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divsuccessmsgaCTemSec']"));
					return litigation;
				}
			 public static WebElement ClickInvalidMsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_FolderValidation']/ul/li"));
					return litigation;
				}
			 public static WebElement ClickEditFolder(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//a[@id='ContentPlaceHolder1_grdFolderDetail_lnkEditFolder_0']"));
					return litigation;
				}
			 public static WebElement ClickDownloadfile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//img[@class='viewdrive']"));
					return litigation;
				}
			 public static WebElement ClickEditDetailesFile(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//img[@class='editdrive']"));
					return litigation;
				}
			 public static WebElement ClickHeader(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_TxtDocHeader']"));
					return litigation;
				}
			 public static WebElement ClickUpdateInfo(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_btnUpdateInfo']"));
					return litigation;
				}
			 public static WebElement ClickUpdateSuccessmsg(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//span[@id='ContentPlaceHolder1_successmsg']"));
					return litigation;
				}
			 public static WebElement SelectStatusFilter(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[3]"));
					return litigation;
				}
			 public static WebElement StatusDropDown(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("(//span[@class='k-in'])[130]"));
					return litigation;
				}
			 public static WebElement NoRecordFound(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@class='k-pager-info k-label']"));
					return litigation;
				}
			 public static WebElement StageName(WebDriver driver)
				{
					litigation=driver.findElement(By.xpath("//*[@class='highcharts-axis-labels highcharts-xaxis-labels ']"));
					return litigation;
				}
			 public static WebElement DepartName(WebDriver driver)
				{
					litigation=driver.findElement(By.cssSelector("#highcharts-2 > svg > g.highcharts-axis-labels.highcharts-xaxis-labels > text:nth-child(1) > tspan"));
					return litigation;
				}
			 public static WebElement LocationName(WebDriver driver)
				{
					litigation=driver.findElement(By.cssSelector("#highcharts-8 > svg > g.highcharts-axis-labels.highcharts-xaxis-labels > text:nth-child(5) > tspan"));
					return litigation;
				}
			 public static WebElement CategoryName(WebDriver driver)
				{
					litigation=driver.findElement(By.cssSelector("#highcharts-6 > svg > g.highcharts-axis-labels.highcharts-xaxis-labels > text:nth-child(1) > tspan"));
					return litigation;
				}
}


			




		  
