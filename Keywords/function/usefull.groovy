package function

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import java.text.SimpleDateFormat as SimpleDateFormat

public class usefull {
	/**
	 Function cek row item location
	 */
	@Keyword

	def checkRowItem(id){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = driver.findElement(By.xpath(('//*[@id=\'' + id) + '\']/fieldset'))
		List<WebElement> rows_table = Table.findElements(By.tagName('div'))
		return rows_table.size()
	}
	/**
	 Function cek row item size
	 */
	@Keyword
	def checkRowItemSize(id,sizeType){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = driver.findElement(By.xpath(('//*[@id=\'' + id) + '\']/fieldset/div['+sizeType+']'))
		List<WebElement> rows_table = Table.findElements(By.tagName('div'))
		return rows_table.size()
	}
	/**
	 Function cek row item category
	 */
	@Keyword
	def checkRowListCategory(id){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = driver.findElement(By.xpath(('//*[@id=\'' + id) + '\']'))
		List<WebElement> rows_table = Table.findElements(By.tagName('option'))
		return rows_table.size()
	}
}

public class fail {
	/**
	 * Failure Handling
	 */
	@Keyword
	def failure(String message, FailureHandling flowControl) {
		switch (flowControl) {
			case FailureHandling.OPTIONAL:
			//KeywordUtil.logInfo(message)
				break
			case FailureHandling.CONTINUE_ON_FAILURE:
			//KeywordUtil.markFailed(message)
				break
			case FailureHandling.STOP_ON_FAILURE:
				'get dateTime Now'
				SimpleDateFormat sDate = new SimpleDateFormat('dd-MM-yy HHmmss')
				String date = sDate.format(new Date())

				'error picture path'
				String path = "D:\\Test\\TestQA\\Data Files\\ScreenError\\"+message+date+".png"

				'take error picture'
				WebUI.takeScreenshot(path)

				'error mark view on log'
				KeywordUtil.markErrorAndStop(message)

				'Close browser'
			//WebUI.closeBrowser()
				break
		}
	}
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}
