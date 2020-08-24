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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('PZT/Login case'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.openBrowser(GlobalVariable.PZT_URL)
//
////Maximize window.
//WebUI.maximizeWindow()

//KeywordUtil object
KeywordUtil log = new KeywordUtil()

////Click INSIGHTS top navigation control
//WebUI.click(findTestObject('PZT Analytics Application/Main Navigation/insightsNavigationButton'))
//
////Click Gateways button in chart navigation
//WebUI.click(findTestObject('PZT Analytics Application/Insights Page/Chart Navigation/gatewaysNavigation'))

//Locate the chart container
WebElement chartBusy11 = CustomKeywords.'pzt_package.SeleniumExtensions.findElement'(findTestObject('Object Repository/L2/TopEngagedUser/ElementMouseHover/1stRow1stColumn'))

//Scroll the chart into view
CustomKeywords.'pzt_package.SeleniumExtensions.scrollIntoView'(chartBusy11)

//Wait for chart to load...
//Mouseover the middle of the chart
CustomKeywords.'pzt_package.SeleniumExtensions.mouseoverElement'(chartBusy11)

//Get tooltip values
Integer cpuVal = CustomKeywords.'pzt_package.AnalyticsInsights.getTooltipValue'('cpu')

Integer throughputVal = CustomKeywords.'pzt_package.AnalyticsInsights.getTooltipValue'('throughput')

//Verify the tooltip values are positive
assert throughputVal > 0 : 'Throughput data was not more than 0kB/s'

assert cpuVal > 0 : 'CPU Performance was not more than 0'

WebUI.closeBrowser()

