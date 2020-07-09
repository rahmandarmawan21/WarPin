import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper

try{
	/**
	 * POST API
	 * */
	ResponseObject mainPage = WS.sendRequest(findTestObject('Object Repository/API/create company status'))
	'Validate response 200'
	WS.verifyResponseStatusCode(mainPage, 200)
	assertThat(mainPage.getStatusCode()).isEqualTo(200)
	'Mark pass'
	KeywordUtil.markPassed("Status Created")
	//validate response message
	def getLoginResponse = mainPage.getResponseText()
	if(getLoginResponse.contains("Resource has been created")){
		'Mark success Login'
		KeywordUtil.markPassed("POST created")
	}else{
		'error Login Not found'
		KeywordUtil.markFailed("POST not created")
	}
}catch(Exception ex){
	println ex
}