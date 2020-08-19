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

def slurper = new JsonSlurper()

File jsontxt = new File('/Users/perry.gami/Downloads/config_008.json')

def result = slurper.parse(jsontxt)

def user = result.tenants[0].tenant_config.users_to_applications[0].devices.count
for (i=0;i<5;i++)
{
def userlist = result.tenants[0].users[i].user_name
print(userlist)
WebUI.openBrowser(userlist)
}
//WebUI.callTestCase(findTestCase('Login case'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Print(GlobalVariable.Count)
////WebUI.verifyMatch(user, GlobalVariable.Count)
//WebUI.verifyMatch(user, GlobalVariable.Count, false, FailureHandling.STOP_ON_FAILURE)

