package com.kondasamy.soapui.plugin

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */

import com.eviware.soapui.SoapUI
import com.eviware.soapui.impl.support.http.HttpRequestTestStep
import com.eviware.soapui.plugins.ActionConfiguration
import com.eviware.soapui.support.UISupport
import com.eviware.soapui.support.action.support.AbstractSoapUIAction

@ActionConfiguration(actionGroup = ActionGroups.TEST_STEP_ACTIONS)
class TestStepSaveResponseAction extends AbstractSoapUIAction<HttpRequestTestStep>
{
    public TestStepSaveResponseAction()
    {
        super( "Plugin:Export Request and Response", "Saves recent response to a file" );
    }


    @Override
    void perform(HttpRequestTestStep httpTestRequestStep, Object o)
    {
        //Get the response of current TestStep
        def response = httpTestRequestStep.httpRequest.response
        /*Check if response is null/ means teststep not ran*/
        if( response == null )
        {
            UISupport.showInfoMessage( "Missing Response for TestStep ");
            return
        }
        /*Check if null response returned post successful run of the teststep*/
        def data = response.rawResponseData
//        def data1 = httpTestRequestStep.httpRequest.getResponse().getContentAsXml()

        if( data == null || data.length == 0 )
        {
            UISupport.showInfoMessage( "Empty Response data for TestStep" );
            return
        }
        /*If the data is available, save to a file in User's current directory*/
        else
        {
            def rawRequest = new String(httpTestRequestStep.httpRequest.response.getRawRequestData(),"UTF-8")
            def rawResponse = new String(httpTestRequestStep.httpRequest.response.getRawResponseData(),"UTF-8")
            def today= new Date() //represents the date and time when it is created
            //SoapUI.log today.format("yyyyMMdd-HH:mm:ss")
            String today1 = today.format("yyyyMMdd-HH:mm:ss.S")
            //SoapUI.log today1
            String result = today1.replaceAll(":", "");
            //SoapUI.log result
            String todayTime = result.replaceAll("-", "");
//            SoapUI.log.info "Refined Date is : " + todayTime
            def tstName = httpTestRequestStep.testStep.getName()
            def tcName = httpTestRequestStep.testCase.getName()
            def tsName = httpTestRequestStep.testCase.testSuite.getName()
            def projName = httpTestRequestStep.testCase.testSuite.project.name
            String fileName = tcName+"__" +tstName+ "__" + todayTime +".txt"
//            SoapUI.log.info "File Name is :: " +fileName

//            String fileName1 = fileName.replaceAll("/", "-");
//            String fileName2 = fileName1.replaceAll(";","-");
//            String fileName3 = fileName2.replaceAll(":","-");
//            String fileName4 = fileName3.replaceAll(",","-");
//            String fileName5 = fileName4.replaceAll("\\?","-");
//            String fileName6 = fileName5.replaceAll("-","_")
            String fileName6 = fileName.replaceAll("[^a-zA-Z0-9.-]", "_")

            def mainDir = System.getProperty('user.home')
            String date = today.format("yyyyMMdd")
//            SoapUI.log.info "User's current Directory is : " + mainDir
            def SubDir = "\\SoapUI Data\\" + projName + "\\" + date
//            SoapUI.log.info SubDir
            def SubDir1 = new File(mainDir,SubDir)
//            SubDir1.mkdirs()
            if(SubDir1.exists())
            {
                def file = new File(SubDir1,fileName6)
                if(!file.exists())
                    file.append "Raw Request:" + System.getProperty("line.separator") + rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
                else
                    file << "Raw Request:" + System.getProperty("line.separator") +  rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
            }
            else
            {
                SubDir1.mkdirs()
                def file = new File(SubDir1,fileName6)
                file << "Raw Request:" + System.getProperty("line.separator") + rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
            }
            SoapUI.log.info "***Raw Request and Raw Response is exported to a file :: ==> "+mainDir+ SubDir+"\\"+fileName6
            UISupport.showInfoMessage("File Successfully saved!! Please see the SoapUI log for more information!","File Export Success!!!")


        }
    }
}
/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */
