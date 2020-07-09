
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
	//	WebUI.openBrowser("https://gist.github.com/echogeer/8c20150dfb9d5a8b20d2e2725b076c89")
	'initiate Selenium Driver'
	WebDriver driver = DriverFactory.getWebDriver()
	String urlBase = GlobalVariable.caseUrl
	
	'initiate katalon webdriver'
	katalon = new WebDriverBackedSelenium(driver, urlBase)
	driver.findElement(By.linkText("Edit")).click()
	driver.findElement(By.name("gist[description]")).click()
	driver.findElement(By.name("gist[description]")).sendKeys("Test20edit")
	driver.findElement(By.cssSelector(".filename")).click()
	def textNameedit= "TestEdit20.xml"
	driver.findElement(By.cssSelector(".filename")).sendKeys("TestEdit20.xml")
	driver.findElement(By.cssSelector(".btn-primary")).click()
	
	'Get Status Edit element'
	def EditStatus = katalon.isElementPresent("class=user-select-contain gist-blob-name css-truncate-target")
	
	'Get Text Edit Name'
	//def getTextEditVerify = driver.findElement(By.className("user-select-contain gist-blob-name css-truncate-target")).getText()
	
	//println  getTextEditVerify
	
	'Verify  exist element and textnameEdit'
	if(EditStatus == true ){
		'Edit berhasil'
		KeywordUtil.markPassed("Edit successfully")}
	else{
		'Edit Gagal'
		KeywordUtil.markError("Edit Failed")
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