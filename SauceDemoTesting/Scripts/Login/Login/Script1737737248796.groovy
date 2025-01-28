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
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

TestData testdataLogin = TestDataFactory.findTestData("Data Files/Data Login/Login From Intenal Data")

for (int i=1;i<=testdataLogin.getRowNumbers();i++) {
	String user = testdataLogin.getValue(1, i)
	String password = testdataLogin.getValue(2, i)
	String expectedResult = testdataLogin.getValue(3, i)
	
	WebUI.openBrowser('')
	
	WebUI.navigateToUrl('https://www.saucedemo.com/')
	
	WebUI.verifyElementText(findTestObject('Login_Page/Login Confirmation_Swag Labs'), 'Swag Labs')
	
	WebUI.setText(findTestObject('Login_Page/input_user-name'), user)
	
	WebUI.setText(findTestObject('Login_Page/input_password'), password)

	WebUI.takeFullPageScreenshot()
	
	WebUI.click(findTestObject('Login_Page/input_login-button'))
	
	if ( expectedResult == "Failures")
		{
			WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/h3_Alert_Username and passworddo not match'), 10, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementText(findTestObject('Object Repository/Login_Page/h3_Alert_Username and passworddo not match'), 'Epic sadface: Username and password do not match any user in this service', FailureHandling.STOP_ON_FAILURE)
			WebUI.comment("Login Gagal")
			String message_failures = "Login gagal menggunakan username : " +user+ " dam Password : " +password
			print(message_failures)
		}
		else if (expectedResult == "Success")
		{
			WebUI.verifyElementNotPresent(findTestObject('Object Repository/Login_Page/h3_Alert_Username and passworddo not match'), 10, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementText(findTestObject('Object Repository/Login_Page/Validate_Sudah_login'), 'Products')
			WebUI.comment("Login Berhasil")
			String message_success = "Login berhasil menggunakan username : " +user+ " dan password : " +password
			print(message_success)
			WebUI.click(findTestObject('Object Repository/Login_Page/Burger_menu'))
			WebUI.click(findTestObject('Object Repository/Login_Page/a_Logout'))
		} 
		else if (expectedResult == "uname require")
		{
			WebUI.delay(3)
			WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/alert_h3_Username is required'), 10 , FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementText(findTestObject('Object Repository/Login_Page/alert_h3_Username is required'), 'Epic sadface: Username is required')
		} 
		else if (expectedResult == "pass require")
		{
			WebUI.delay(3)
			WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/alert_h3_Username is required'), 10 , FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementText(findTestObject('Object Repository/Login_Page/alert_h3_Username is required'), 'Epic sadface: Password is required')
		}
}
WebUI.closeBrowser()


