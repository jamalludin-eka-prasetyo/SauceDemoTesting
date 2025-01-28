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
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

TestData testDataDashboard = TestDataFactory.findTestData('Data Files/Data Dashboard/Data Dashboard from CSV')

for (int i = 1; i <= testDataDashboard.getRowNumbers(); i++) {
    String item_name = testDataDashboard.getValue(1, i)
    String item_description = testDataDashboard.getValue(2, i)
    String item_price = testDataDashboard.getValue(3, i).trim()
	String username = testDataDashboard.getValue(4, i)
	String password = testDataDashboard.getValue(5, i)
	String name_cart = testDataDashboard.getValue(6, i)
	String firstname_cart = testDataDashboard.getValue(7, i)
	String lastname_cart = testDataDashboard.getValue(8, i)
	String zipPostal_cart = testDataDashboard.getValue(9, i)
	
    WebUI.openBrowser('')
    WebUI.navigateToUrl('https://www.saucedemo.com/')
    WebUI.setText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/input_Swag Labs_user-name'), username)
    WebUI.setText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/input_Swag Labs_password'), password)
    WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/input_Swag Labs_login-button'))
    WebUI.click(findTestObject('Dashboard/Page_Swag Labs/div_Sauce Labs Backpack', [('item_name') : item_name]))
    WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/div_carry.allTheThings() with the sleek, st_6ae692'), 
        item_description)
    WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/div_29.99'), item_price)
    WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/Page_Swag Labs/button_add_to_card_verify'), 0)
    WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Btn_addCart'))
	WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/Page_Swag Labs/span_cart_badge'), 0)
	WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/span_cart_badge'))
	WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/span_yourCart'), 'Your Cart')
	WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/verify_elementTextYourCart'), name_cart)
	WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/btn_checkoutYourCart'))
	WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/span_yourCart'), 'Checkout: Your Information')
	WebUI.setText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/firstname_cart'), firstname_cart)
	WebUI.setText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/lastname_cart'), lastname_cart)
	WebUI.setText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/zipPostal_cart'), zipPostal_cart)
	WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/btn_continueCart'))
	WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/verif_coOverview'), name_cart)
	
	def itemTotal = "Item total: "
	def priceTotal_Cart = itemTotal+item_price
	println("Price total = " +priceTotal_Cart)
	WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/price_total'), priceTotal_Cart)
	WebUI.takeFullPageScreenshot()
	WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/btn_finish'))
	WebUI.takeFullPageScreenshot()
	WebUI.verifyElementText(findTestObject('Object Repository/Dashboard/Page_Swag Labs/Input Cart/h2_completeOrder'), 'Thank you for your order!')
	WebUI.takeFullPageScreenshot()
//	WebUI.click(findTestObject('Object Repository/Dashboard/Page_Swag Labs/button_Back to products'))
	
	WebUI.delay(5)
}

WebUI.closeBrowser()

