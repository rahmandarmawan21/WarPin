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
	
	'Click Button avatar'
	driver.findElement(By.cssSelector(".Header-link:nth-child(1) > .avatar")).click()
	'Click Button SignOut'
	driver.findElement(By.cssSelector(".dropdown-signout")).click()
	
	/*
	 * Check Trigger when Display
	 * */
	'Click Sign Out Button'
	driver.findElement(By.xpath("//*[@id='js-pjax-container']/div/form/input[2]")).click()
	
		'Wait Page load'
	WebUI.waitForPageLoad(5)
	'Varify LogOut Status'
	def logOutVal = katalon.isElementPresent("link=Sign in")
	
	'Validate Logout'
	if(logOutVal == true){
		'LogOut berhasil'
		KeywordUtil.markPassed("LogOut successfully")
		WebUI.closeBrowser()
	}
	else{
		'LogOut Gagal'
		KeywordUtil.markErrorAndStop("LogOut Failed")
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