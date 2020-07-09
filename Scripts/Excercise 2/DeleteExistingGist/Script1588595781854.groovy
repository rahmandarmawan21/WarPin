
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium

import internal.GlobalVariable as GlobalVariable


try{
	'using if direct test'
	//	WebUI.openBrowser(GlobalVariable.caseUrl)
	//WebUI.openBrowser("https://gist.github.com/echogeer/cc2bed64394b52652a1346b4f8ce82f4")
	'initiate Selenium Driver'
	WebDriver driver = DriverFactory.getWebDriver()
	String urlBase = GlobalVariable.caseUrl
	
	'initiate katalon webdriver'
	katalon = new WebDriverBackedSelenium(driver, urlBase)
	
	'Click Button Delete'
	driver.findElement(By.cssSelector(".btn-danger")).click()
    driver.switchTo().alert().accept()
	'Validate Delete Success Notif display'
	//def DelStatus = katalon.isElementPresent("xpath=//*[@id='js-flash-container]/div/div")
	def DelStatus = katalon.isElementPresent("id=js-flash-container")
	
	println DelStatus
	if(DelStatus == true){
	'Delete berhasil'
	KeywordUtil.markPassed("Delete successfully")}
	else{
		'Delete Gagal'
		KeywordUtil.markError("Delete Failed")
//		KeywordUtil.markErrorAndStop("Create Failed")
		
		}
	
}catch (Exception ex) {
	println ex
	def error= ex.message.toString()
	def testName = ""
	if (error.length()> 40){
		testName = RunConfiguration.getExecutionSourceName().toString()
	}else{
		testName = RunConfiguration.getExecutionSourceName().toString() +'['+error+']'
	
	}
	//println testName
	'custome error handling'
	CustomKeywords.'errorHandling.fail.failure'(testName, FailureHandling.STOP_ON_FAILURE)
	
}