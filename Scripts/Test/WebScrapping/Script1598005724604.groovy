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


@Grab(group='commons-httpclient', module='commons-httpclient', version='3.1')
import org.apache.commons.httpclient.*
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.cookie.CookiePolicy

@Grab(group='org.jsoup', module='jsoup', version='1.6.2')
import org.jsoup.Jsoup

import groovy.json.*


def url = "https://analytics.elm.pzt.dev.perfsec.com/admin/#/"

def httpClient = new HttpClient()
def getUrl = new GetMethod(url)
getUrl.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES)

httpClient.executeMethod(getUrl)
def html = getUrl.getResponseBodyAsString()
//print(html)
//println "Response from ${url} : \${html}"

/* Set-Cookie header from request is somehow malformed (bad domain of origin value) so we have to handle it 
   by ourselves - in this case we need from cookie only session id which is SID cookie parameter. */
def cookieHeader = getUrl.getResponseHeader("Set-Cookie")

/* Session id is needed because when we call ajax api it checks SID against generated hash keys
   eg. http://bandzone.cz/track/play/327662?hash=2af3b047c67791610341fad72fc694a5be854b82 is link
   to api which response with json where is url of real mp3 file - without matching hash parameter
   with session id, bandzone.cz response with "Need to login" response */
//def sessionId = cookieHeader.getElements().find{element->element.name=="SID"}.value


/* finding data in html - in our case we are looking for urls which leads to reveal real mp3 urls of band songs */
println "Finding links in responed html..." 
def document = Jsoup.parse(html)

//Element ele = d.select("div#dashboard-summary__container")
//for(Element element : elm){
//	String btn = element.select("dev.Gateways")
//	print(btn)
//	
//}

def elements = document.select("div#zero-app")
def mp3Urls = elements.collect { element ->
    element.attr("data-source")
}
print(mp3Urls)
//println "Real mp3 address hiding urls: \n $mp3Urls"

println "Obtaining real mp3 urls from ajax api..."

def realMp3Urls = mp3Urls.collect { mp3Url ->
    def m = new PostMethod(mp3Url)
    /* ignoring cookies as we need to handle them by hand - in normal case when cookies are ok
       (according to specification) HttpClient can handle all the cookie managment by us and we would't do this */
    m.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES) 
    m.setRequestHeader("X-Requested-With", "XMLHttpRequest") // mark it as ajax request (as it do jQuery.ajax call)
    m.setRequestHeader("Cookie", "SID=$sessionId") // without this - bandzone.cz reject with "Need to login" stuff
    httpClient.executeMethod(m)
    //println "-" * 20
    //println "Request header: "
    //println m.getRequetHeaders()
    //println "Response header:"
    //println m.getResponseHeaders()
    def jsonText = m.getResponseBodyAsString() /* it returns json response */
    
    def json = new JsonSlurper().parseText(jsonText)    
	
	print(json)
    
    json.url
}