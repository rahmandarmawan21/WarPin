
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium

import internal.GlobalVariable as GlobalVariable

try{
	'Open Browser'
	WebUI.openBrowser(GlobalVariable.caseUrl)
	
	'initiate Selenium Driver'
	WebDriver driver = DriverFactory.getWebDriver()
	
	String urlBase = GlobalVariable.caseUrl
	
	'initiate katalon webdriver'
	katalon = new WebDriverBackedSelenium(driver, urlBase)
	
	'Click Button Signin'
	katalon.click("link=Sign in")
	'Input UserName'
	katalon.type("id=login_field","echogeer@gmail.com")
	'Input Password'
	katalon.type("id=password", "683127ndz")
	'Click commit sign in'
	katalon.click("name=commit")
	'wait load page'
	//katalon.waitForPageToLoad("5")
	//WebUI.waitForPageLoad(10)
	
	'Verify Login Success'
	def loginSuccess = katalon.isElementPresent("class=avatar avatar-user")
	//println loginSuccess
	
	'Validate Test Login'
	if(loginSuccess == true){
	'login berhasil'
	KeywordUtil.markPassed("Login successfully")}
	else{
		'Login Gagal'
		KeywordUtil.markError("Login Failed")
	//KeywordUtil.markErrorAndStop("Login Failed")
		
	}
	
}catch (Exception ex) {
	//println ex
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