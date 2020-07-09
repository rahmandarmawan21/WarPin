import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable


WebUI.openBrowser('') //start to open browser
WebUI.navigateToUrl(GlobalVariable.Url) //navigate to url
WebUI.waitForPageLoad(100) //wait page load successfull
try{
	WebUI.click(findTestObject('Ebay/HomePage/button_Shop by category'))
	WebUI.click(findTestObject('Ebay/HomePage/a_Cell phones  accessories'))
	WebUI.waitForPageLoad(1000)
	WebUI.click(findTestObject('Ebay/HomePage/a_Cell Phones  Smartphones'))
	WebUI.waitForPageLoad(1000)
	WebUI.scrollToElement(findTestObject('Ebay/CellphonePage/lebel_Condition'), 1)
	WebUI.waitForPageLoad(1000)
	WebUI.click(findTestObject('Ebay/CellphonePage/button_see all'))
	WebUI.delay(1)
	def rows_countItemSize = CustomKeywords.'function.usefull.checkRowItemSize'('c3-subPanel', GlobalVariable.sizeType) //function to get list item
	if(GlobalVariable.sizeType){
	for(i = 1; i <= rows_countItemSize; i++){
		def pickSize = findTestObject('Object Repository/Ebay/CellphonePage/item_size_type',[('index') : i])
		def cekSize = findTestObject('Object Repository/Ebay/CellphonePage/cekbox_size',[('index') : i])
		def getValue = WebUI.getText(pickSize)
		println getValue
		if(getValue == GlobalVariable.inch){
			WebUI.click(cekSize)
			WebUI.waitForPageLoad(100)
			}
		}
	}
	WebUI.click(findTestObject('Ebay/CellphonePage/label_price'))
	WebUI.setText(findTestObject('Ebay/CellphonePage/price'),GlobalVariable.price)
	WebUI.click(findTestObject('Object Repository/Ebay/CellphonePage/span_Item Location'))
	WebUI.delay(1)
	def rows_countItemLoc = CustomKeywords.'function.usefull.checkRowItem'('c3-subPanel') //function to get list item
	for(i = 1; i <= rows_countItemLoc; i++){
		def pickLoc = findTestObject('Ebay/CellphonePage/item_locations_name',[('index') : i])
		def cekLoc = findTestObject('Object Repository/Ebay/CellphonePage/cekbox_location',[('index') : i])
		def getValue = WebUI.getText(pickLoc)
		println getValue
		if(getValue == GlobalVariable.locName){
			WebUI.click(cekLoc)
			WebUI.waitForPageLoad(100)
		}
	}
	def tagFilter = findTestObject('Object Repository/Ebay/CellphonePage/tag_filter')
	def getValueText = WebUI.getText(tagFilter)
	WebUI.verifyElementText(tagFilter, getValueText) //verify elemetn filter is match
	WebUI.click(findTestObject('Object Repository/Ebay/CellphonePage/button_Apply'))
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