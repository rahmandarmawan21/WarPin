
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

	'initiate Selenium Driver'
	WebDriver driver = DriverFactory.getWebDriver()
	String urlBase = GlobalVariable.caseUrl
	
	'initiate katalon webdriver'
	katalon = new WebDriverBackedSelenium(driver, urlBase)
	'Click Profile'
	driver.findElement(By.cssSelector(".Header-link > .avatar")).click()
	'Click Your Gist Menu' 
    driver.findElement(By.linkText("Your gists")).click();
	
	'Validate List Success  display'
	def listStatus = katalon.isElementPresent("class=js-selected-navigation-item selected reponav-item")
	if(listStatus == true){
	'listStatus berhasil'
	KeywordUtil.markPassed("List successfully displayed")}
	else{
		'listStatus Gagal'
		KeywordUtil.markError("List Failed displayed")
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