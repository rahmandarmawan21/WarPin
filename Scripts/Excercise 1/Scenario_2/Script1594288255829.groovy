import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.ebay.com/')
WebUI.waitForPageLoad(1000)
try{
	WebUI.setText(findTestObject('Ebay/HomePage/search_bar'), 'MacBook')
	WebUI.selectOptionByValue(findTestObject('Ebay/HomePage/list_category'), '58058',  true)
	WebUI.click(findTestObject('Ebay/HomePage/btn_search'))
	WebUI.waitForPageLoad(1000)
	def text = WebUI.getText(findTestObject('Ebay/HomePage/list_first_item'))
	if(text.contains(GlobalVariable.SearchItem)){
		KeywordUtil.markPassed('This item have string '+ GlobalVariable.SearchItem)
	}else{
		KeywordUtil.markFailedAndStop('Not match' +FailureHandling.STOP_ON_FAILURE)
	}
	WebUI.closeBrowser()
}catch(Exception ex){
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
	CustomKeywords.'function.fail.failure'(testName, FailureHandling.STOP_ON_FAILURE)
}
