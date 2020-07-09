
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
	
	'Click Button add Gist'
	driver.findElement(By.cssSelector(".octicon-plus:nth-child(1)")).click()
	'Click and set text on description'
	driver.findElement(By.name("gist[description]")).click()
	driver.findElement(By.name("gist[description]")).sendKeys("Test2")
	'Click and set text on Name'
	driver.findElement(By.name("gist[contents][][name]")).click()
	driver.findElement(By.name("gist[contents][][name]")).sendKeys("Test2.xml")
	'Click and set text on Content'
	katalon.click("//div[@id='gists']/div[2]/div/div[2]/div/div[5]")
	driver.findElement(By.xpath("//*[@id='gists']/div[2]/div/div[2]/div/div[5]/div[1]/div/div/div/div[5]/div/pre/span")).sendKeys("Test2.xml")
	'Click Create Public Gist'
	driver.findElement(By.name("gist[public]")).click()
	
	'Validate Create Success'
	//def CreateStatus= katalon.isElementPresent("xpath=//*[@id='gist-pjax-container'']/div[1]/div/div[1]/h1/strong/a")
	def CreateStatus= katalon.isElementPresent("xpath=//*[@id='gist-pjax-container']/div[1]/div/div[1]/h1/span[1]/a")
	
	'Validate Create Status'
	if(CreateStatus == true){
		'Create berhasil'
		KeywordUtil.markPassed("Create successfully")}
	else{
		'Create Gagal'
		KeywordUtil.markError("Create Failed")
//		KeywordUtil.markErrorAndStop("Create Failed")
		
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