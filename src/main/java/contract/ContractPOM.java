package contract;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContractPOM 
{
	private static WebElement contract = null;		
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting Status-Asc/Desc (Status shows multiple elements back side)
	
	public static WebElement clickDraft(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divDraftCount']"));
		return contract;
	}
	
	public static WebElement clickAddNew(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddContract']"));
		return contract;
	}
	
	public static WebElement readTotalRecords(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lblTotalRecord']"));
		return contract;
	}
	
	public static WebElement clickExportReport(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//a[@id='ContentPlaceHolder1_btnExportExcel']"));
		return contract;
	}
	
	public static WebElement clickDashboard(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='leftdashboardmenu']"));
		return contract;
	}
	
	public static WebElement clickContractNumber(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtContractNo']"));
		return contract;
	}
	
	public static WebElement clickContractTitle(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtTitle']"));
		return contract;
	}
	
	public static WebElement clickDescription(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxDescription']"));
		return contract;
	}
	
	public static WebElement clickEntityLocation(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxBranch']"));
		return contract;
	}
	
	public static WebElement selectLocation(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tvBranchest0']"));
		return contract;
	}
	
	public static WebElement clickVendor(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[1]"));
		return contract;
	}
	
	public static WebElement clickDepartment(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']"));
		return contract;
	}
	
	public static WebElement clickContactPerson(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlCPDepartment_chosen']"));
		return contract;
	}
	
	public static WebElement clickProposalDate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtProposalDate']"));
		return contract;
	}
	
	public static WebElement selectLastMonth(WebDriver driver)				//Method to click on arrow which shows last month
	{
		contract = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span"));
		return contract;
	}
	
	public static WebElement selectNextMonth(WebDriver driver)			//Method to click on arrow which shows last month
	{
		contract = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span"));
		return contract;
	}
	
	public static WebElement selectDate(WebDriver driver)					//Method to click on date at second row and fourth column
	{
		contract = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a"));
		return contract;
	}
	
	public static WebElement clickAgreementDate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtAgreementDate']"));
		return contract;
	}
	
	public static WebElement clickStartDate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtEffectiveDate']"));
		return contract;
	}
	
	public static WebElement clickReviewDate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtReviewDate']"));
		return contract;
	}
	
	public static WebElement clickEndDate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtExpirationDate']"));
		return contract;
	}
	
	public static WebElement clickNoticeTerm(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='txtNoticeTerm']"));
		return contract;
	}
	
	public static WebElement clickNoticeTermPeriod(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlNoticeTerm']"));
		return contract;
	}
	
	public static WebElement clickContractType(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlContractType_chosen']"));
		return contract;
	}
	
	public static WebElement clickContractSubType(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlContractSubType_chosen']"));
		return contract;
	}
	
	public static WebElement clickContractOwner(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[2]"));
		return contract;
	}
	
	public static WebElement clickContractAmount(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxContractAmt']"));
		return contract;
	}
	
	public static WebElement clickPaymentType(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlPaymentType_chosen']"));
		return contract;
	}
	
	public static WebElement clickSave(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@value='Save']"));
		return contract;
	}
	
	public static WebElement clickExpired(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divExpiredCount']"));
		return contract;
	}
	
	public static WebElement clickStatusDropDown(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlContractStatus_chosen']"));
		return contract;
	}
	
	public static List<WebElement> selectContractStatus(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//div[@id='ContentPlaceHolder1_ddlContractStatus_chosen']/div/ul/li"));
		return elementsList;
	}
	
	public static WebElement clickAllContractDropDown(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlContract_chosen']"));
		return contract;
	}
	
	public static WebElement selectAssignedToReview(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlContract_chosen']/div/ul/li[2]"));
		return contract;
	}
	
	public static WebElement selectAllContract(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlContract_chosen']/div/ul/li[1]"));
		return contract;
	}
	
	public static WebElement clickMyReports(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//a[@href='/ContractProduct/Reports/MyReportContract.aspx']"));
		return contract;
	}
	
	public static WebElement selectRenewed(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlContractStatus_chosen']/div/ul/li[10]"));
		return contract;
	}
	
	public static WebElement selectExpired(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlContractStatus_chosen']/div/ul/li[11]"));
		return contract;
	}
	
	public static WebElement readMessage(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='VSContractPopup']"));
		return contract;
	}
	
	public static WebElement clickClose(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@onclick='closeModal();']"));
		return contract;
	}
	
	public static WebElement clickPendingForReview(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPendingReviewCount']"));
		return contract;
	}
	
	public static WebElement clickEditDraft(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdContractList_lnkEditContract_0']"));
		return contract;
	}
	
	public static WebElement clickDraftEdit(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='btnEditContractDetail']"));
		return contract;
	}
	
	public static WebElement clickDraftStatus(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlContractStatus_chosen']"));
		return contract;
	}
	
	public static WebElement clickDraftStatusTextBox(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlContractStatus_chosen']/div/div/input"));
		return contract;
	}
	
	public static WebElement clickUpdate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@value='Update']"));
		return contract;
	}
	
	public static WebElement clickReviewed(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divReviewedCount']"));
		return contract;
	}
	
	public static WebElement clickPendingApproval(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPendingApprovalCount']"));
		return contract;
	}
	
	public static WebElement clickTasks(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='lnkBtnTask']"));
		return contract;
	}
	
	public static WebElement clickAddNewTask(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='lnkAddNewTask']"));
		return contract;
	}
	
	public static WebElement readContractName(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdContractList']/tbody/tr[2]/td[3]/div/span"));
		return contract;
	}
	
	public static WebElement clickReviewRadioButton(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//input[@id='rbTaskType_0']"));
		return contract;
	}
	
	public static WebElement clickTaskTitle(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxTaskTitle']"));
		return contract;
	}
	
	public static WebElement clickTaskDescription(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxTaskDesc']"));
		return contract;
	}
	
	public static WebElement clickTaskDueDate(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxTaskDueDate']"));
		return contract;
	}
	
	public static WebElement clickAssignTo(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@title='None selected']"));
		return contract;
	}
	
	public static WebElement clickSearchBox(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//input[@placeholder='Type to Search for User..']"));
		return contract;
	}
	
	public static WebElement checkProgress(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='imgUpdateProgress']"));
		return contract;
	}
	
	public static WebElement checkDownload(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='grdTaskContractDocuments_lnkBtnDownloadTaskDoc_0']"));
		return contract;
	}
	
	public static WebElement clickAddDocument(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='imgAddNewTaskDoc']"));
		return contract;
	}
	
	public static WebElement clickDocumentType(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlDocType_chosen']"));
		return contract;
	}
	
	public static WebElement clickChooseFile(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContractFileUpload']"));
		return contract;
	}
	
	public static WebElement clickUpload(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='lnkDocumentUpload']"));
		return contract;
	}
	
	public static WebElement readMessage1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='vsContractDocument']"));
		return contract;
	}
	
	public static WebElement clickClose1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@onclick='CloseMe();']"));
		return contract;
	}
	
	public static WebElement clickSelectAll(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='grdTaskContractDocuments_chkHeaderTaskDocument']"));
		return contract;
	}
	
	public static WebElement clickCreatAndAssign(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='btnTaskSave']"));
		return contract;
	}
	
	public static WebElement readMessage2(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='vsTaskTab']/ul/li[1]"));
		return contract;
	}
	
	public static WebElement clickApproveRadio(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='rbTaskType_1']"));
		return contract;
	}
	
	public static WebElement clickTaskMenu(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='Tasklistmenu']"));
		return contract;
	}
	
	public static WebElement clickTask(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkTask']"));
		return contract;
	}
	
	public static WebElement clickStatus(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlTaskStatus_chosen']"));
		return contract;
	}
	
	public static WebElement clickAllTask(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_ddlTaskAssigned_chosen']"));
		return contract;
	}
	
	public static WebElement clickApply(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkBtnApplyFilter']"));
		return contract;
	}
	
	public static List<WebElement> readAssignedTo(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='ContentPlaceHolder1_grdTaskActivity']/tbody/tr/td[6]/div/span"));
		return elementsList;
	}
	
	public static List<WebElement> clickAction(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'ContentPlaceHolder1_grdTaskActivity_lnkBtnTaskResponse')]"));
		return elementsList;
	}
	
	public static WebElement clickStatus1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ddlStatus_chosen']"));
		return contract;
	}
	
	public static WebElement clickComment(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='tbxTaskResComment']"));
		return contract;
	}
	
	public static WebElement clickChooseFile1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='fuTaskResponseDocUpload']"));
		return contract;
	}
	
	public static WebElement readMsg(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='vsTaskResponse']/ul/li"));
		return contract;
	}
	
	public static WebElement clickClose2(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@onclick='javascript:reloadTaskList();']"));
		return contract;
	}
	
	public static WebElement clickApproved(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divApprovedcount']"));
		return contract;
	}
	
	public static WebElement clickPendingApprove(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPendingApprovalCount']"));
		return contract;
	}
	
	public static WebElement clickMyDocuments(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='leftdocumentsmenu']"));
		return contract;
	}
	
	public static WebElement clickMyDocuments1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("(//*[@id='DocumentShareListNew'])[1]"));
		return contract;
	}
	
	public static WebElement clickCriticalDocuments(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("(//*[@id='DocumentShareListNew'])[2]"));
		return contract;
	}
	
	public static WebElement clickNew(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='menu1']"));
		return contract;
	}
	
	public static WebElement clickNewFolder(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkAddNewFolder']"));
		return contract;
	}
	
	public static WebElement clickFolderTextbox(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtFolderName']"));
		return contract;
	}
	
	public static WebElement clickCreateButton(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCreateFolder']"));
		return contract;
	}
	
	public static WebElement readMsg1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_successmsgaCTemSec']"));
		return contract;
	}
	
	public static WebElement clickAction1(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//a[@id='ContentPlaceHolder1_grdContractList_lnkEditContract_0']"));
		return contract;
	}
	
	public static WebElement clickEditContract(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//a[@id='btnEditContractDetail']"));
		return contract;
	}
	
	public static WebElement clickContractStatus(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ddlContractStatus_chosen']"));
		return contract;
	}
	
	public static WebElement selectRenewedStatus(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//div[@id='ddlContractStatus_chosen']/div/ul/li[9]"));
		return contract;
	}
	
	public static WebElement clickYes(WebDriver driver)
	{
		contract = driver.findElement(By.xpath("//input[@id='btnRenewContract']"));
		return contract;
	}
	
	public static WebElement clickDraftew(WebDriver driver)
	{
		contract = driver.findElement(By.xpath(""));
		return contract;
	}
	
	public static WebElement clickDrafteq(WebDriver driver)
	{
		contract = driver.findElement(By.xpath(""));
		return contract;
	}
	
	public static WebElement clickDraftwwq(WebDriver driver)
	{
		contract = driver.findElement(By.xpath(""));
		return contract;
	}
	
	public static WebElement clickDraftewq(WebDriver driver)
	{
		contract = driver.findElement(By.xpath(""));
		return contract;
	}
}
