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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import groovy.json.JsonSlurper as JsonSlurper

com.kms.katalon.core.util.internal.JsonUtil

WebUI.callTestCase(findTestCase('PZT/Login case'), [:], FailureHandling.STOP_ON_FAILURE)

//WebDriver driver = DriverFactory.getWebDriver()
def slurper = new JsonSlurper()

File jsontxt = new File('/Users/maitri.brahmakshatriya/git/Pulse_Insight/BusyUsers.json')

def result = slurper.parse(jsontxt)

print(result)

//	JsonObject obj = (result) as JSONObject
ArrayList<String> arr = new ArrayList<String>()

//String arr[] = new String[]
for (int i = 0; i < result.size(); ++i) {
    String css = '#topBusyHoursChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + 
    i + ') > g > g > path'

    //String css = '//div[@id="' + dynamicId + '"]'
    myTestOject = new TestObject('customObject')

    myTestOject.addProperty('css', ConditionType.EQUALS, css)

    System.out.println(css)

    WebUI.mouseOver(myTestOject)

    Tooltip = new TestObject('customObject')

    Tooltip.addProperty('css', ConditionType.EQUALS, '#topBusyHoursChart > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

    WebUI.getText(Tooltip)

    Println(Tooltip)

    (arr[i]) = (result.blocks[i])
}

print(arr[0])