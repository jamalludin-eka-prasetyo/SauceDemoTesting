import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.saucedemo.com/')

WebUI.verifyElementText(findTestObject('Login_Page/Login Confirmation_Swag Labs'), 'Swag Labs')

WebUI.setText(findTestObject('Login_Page/input_user-name'), 'standard_user123')

WebUI.setText(findTestObject('Login_Page/input_password'), 'secret_sauce123')

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Login_Page/input_login-button'))


//TestObject verifyAlertNotShow = findTestObject('Object Repository/Login_Page/Page_Swag Labs/h3_Epic sadface Username and password do not match any user in this service')

TestObject verifyAlertError = findTestObject('Object Repository/Login_Page/h3_Alert_Username and passworddo not match')

if (WebUI.verifyElementPresent(verifyAlertError, 0, FailureHandling.STOP_ON_FAILURE)) {
//	WebUI.verifyElementPresent(verifyAlertError, 0, FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementText(verifyAlertError, 'Epic sadface: Username and password do not match any user in this service', FailureHandling.STOP_ON_FAILURE)
	WebUI.comment("Login Gagal")
	} else if (WebUI.verifyElementNotPresent(verifyAlertError, 0)) {
		
		WebUI.comment("Login Berhasil")
		WebUI.verifyElementText(findTestObject('Login_Page/Validate_Sudah_login'), 'Products')
		}


WebUI.closeBrowser()

