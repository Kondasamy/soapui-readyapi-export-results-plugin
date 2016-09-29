package com.kondasamy.soapui.plugin

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */
import com.eviware.soapui.SoapUI
import com.eviware.soapui.model.testsuite.TestCase
import com.eviware.soapui.plugins.ActionConfiguration
import com.eviware.soapui.support.UISupport
import com.eviware.soapui.support.action.support.AbstractSoapUIAction

@ActionConfiguration(actionGroup = ActionGroups.TEST_CASE_ACTIONS)
class TestCaseSaveResponseAction extends AbstractSoapUIAction<TestCase>
{
    public TestCaseSaveResponseAction()
    {
        super("Plugin:Export Request and Response", "Saves recent response of underlying TestSteps's to a file")
    }
    @Override
    void perform(TestCase testCase, Object o)
    {
        testCase.getTestStepsOfType(com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep.class).each
                {
                    tests->
                        def response = tests.httpRequest.response
                        if( response == null )
                        {
                            SoapUI.log.warn "Missing Response for TestStep : " + tests.testStep.testCase.testSuite.name + "=>"+tests.testStep.testCase.name+ "->"+tests.name
                            return
                        }
                        def data = response.getRawResponseData()
                        if( data == null || data.length == 0 )
                        {
                            SoapUI.log.warn "Empty Response data for TestStep : "+ tests.testStep.testCase.testSuite.name + "=>"+tests.testStep.testCase.name+ "->"+tests.name
                            return
                        }
                        else
                        {
                            def rawRequest = new String(response.getRawRequestData(),"UTF-8")
                            def rawResponse = new String(response.getRawResponseData(),"UTF-8")
                            def tstName = tests.getName()
                            def tcName = tests.testCase.getName()
                            def tsName = tests.testCase.testSuite.getName()
                            def projName = tests.testCase.testSuite.project.name

                            def today= new Date()
                            String today1 = today.format("yyyyMMdd-HH:mm:ss.S")
                            String result = today1.replaceAll(":", "");
                            String todayTime = result.replaceAll("-", "");
                            String fileName = tcName+"__" +tstName+ "__" + todayTime +".txt"

//                            String fileName1 = fileName.replaceAll("/", "-");
//                            String fileName2 = fileName1.replaceAll(";","-");
//                            String fileName3 = fileName2.replaceAll(":","-");
//                            String fileName4 = fileName3.replaceAll(",","-");
//                            String fileName5 = fileName4.replaceAll("\\?","-");
//                            String fileName6 = fileName5.replaceAll("-","_")
                            String fileName6 = fileName.replaceAll("[^a-zA-Z0-9.-]", "_")

                            def mainDir = System.getProperty('user.home')
                            String date = today.format("yyyyMMdd")
                            //            SoapUI.log.info "User's current Directory is : " + mainDir
                            def SubDir = "\\SoapUI Data\\" + projName + "\\" + date
                            def SubDir1 = new File(mainDir,SubDir)
                            //SubDir1.mkdirs()
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
                                file << "Raw Request:" + System.getProperty("line.separator") +  rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
                            }
                            SoapUI.log.info "***Raw Request and Raw Response is exported to a file :: ==> "+mainDir+ SubDir+"\\"+fileName6

                        }

                }
        testCase.getTestStepsOfType(com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep.class).each
                {
                    tests->
                        def response = tests.httpRequest.response
                        if( response == null )
                        {
                            SoapUI.log.warn "Missing Response for TestStep : " +tests.testStep.testCase.testSuite.name + "=>"+tests.testStep.testCase.name+ "->"+tests.name
                            return
                        }
                        def data = response.getRawResponseData()
                        if( data == null || data.length == 0 )
                        {
                            SoapUI.log.warn "Empty Response data for TestStep : "+ tests.testStep.testCase.testSuite.name + "=>"+tests.testStep.testCase.name+ "->"+tests.name
                            return
                        }
                        else
                        {
                            def rawRequest = new String(response.getRawRequestData(),"UTF-8")
                            def rawResponse = new String(response.getRawResponseData(),"UTF-8")
                            def tstName = tests.getName()
                            def tcName = tests.testCase.getName()
                            def tsName = tests.testCase.testSuite.getName()
                            def projName = tests.testCase.testSuite.project.name

                            def today= new Date()
                            String today1 = today.format("yyyyMMdd-HH:mm:ss.S")
                            String result = today1.replaceAll(":", "");
                            String todayTime = result.replaceAll("-", "");
                            String fileName = tcName+"__" +tstName+ "__" + todayTime +".txt"

//                            String fileName1 = fileName.replaceAll("/", "-");
//                            String fileName2 = fileName1.replaceAll(";","-");
//                            String fileName3 = fileName2.replaceAll(":","-");
//                            String fileName4 = fileName3.replaceAll(",","-");
//                            String fileName5 = fileName4.replaceAll("\\?","-");
//                            String fileName6 = fileName5.replaceAll("-","_")
                            String fileName6 = fileName.replaceAll("[^a-zA-Z0-9.-]", "_")

                            def mainDir = System.getProperty('user.home')
                            String date = today.format("yyyyMMdd")
//                          SoapUI.log.info "User's current Directory is : " + mainDir
                            def SubDir = "\\SoapUI Data\\" + projName + "\\" + date
                            def SubDir1 = new File(mainDir,SubDir)
//                            SubDir1.mkdirs()
                            if(SubDir1.exists())
                            {
                                def file = new File(SubDir1,fileName6)
                                if(!file.exists())
                                    file.append "Raw Request:" + System.getProperty("line.separator") + rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
                                else
                                    file << "Raw Request:" + System.getProperty("line.separator") + rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
                            }
                            else
                            {
                                SubDir1.mkdirs()
                                def file = new File(SubDir1,fileName6)
                                file << "Raw Request:" + System.getProperty("line.separator") + rawRequest+System.getProperty("line.separator")+System.getProperty("line.separator") + "Raw Response :"+ System.getProperty("line.separator")+ rawResponse
                            }
                            SoapUI.log.info "***Raw Request and Raw Response is exported to a file :: ==>"+mainDir+ SubDir+"\\"+fileName6

                        }

                }

        UISupport.showInfoMessage("Artifacts Successfully exported!! Please see the SoapUI log for more information!","File Export Success!!!")
    }

}
/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */