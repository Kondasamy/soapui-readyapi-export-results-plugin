package com.kondasamy.soapui.plugin

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */
import com.eviware.soapui.SoapUI;
import com.eviware.soapui.model.support.TestRunListenerAdapter;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.plugins.ListenerConfiguration;
import com.eviware.soapui.support.UISupport

@ListenerConfiguration
public class SamyTestRunListener extends TestRunListenerAdapter
{
    @Override
    public void afterStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStepResult result)
    {
        SoapUI.log "***Plugin output*** \nRunning now : " + result.testStep.label

//        Get test details
        def tstName = result.testStep.name
        def tcName = result.testStep.testCase.name
        def tsName = result.testStep.testCase.testSuite.name
        def projName = result.testStep.testCase.testSuite.project.name

//        Get today date
        def today= new Date()
        String today1 = today.format("yyyyMMdd-HH:mm:ss.S")
        String today2 = today1.replaceAll(":", "");
        String today3 = today2.replaceAll("-", "");
        String fileName = tcName+"__" +tstName+ "__" + today3 +".txt"

//        Replace unsupported characters
//        String fileName1 = fileName.replaceAll("/", "-");
//        String fileName2 = fileName1.replaceAll(";","-");
//        String fileName3 = fileName2.replaceAll(":","-");
//        String fileName4 = fileName3.replaceAll(",","-");
//        String fileName5 = fileName4.replaceAll("\\?","-");
//        String fileName6 = fileName5.replaceAll("-","_")
        String fileName6 = fileName.replaceAll("[^a-zA-Z0-9.-]", "_")

//        Main Directory -User directory
        def mainDir = System.getProperty('user.home')
        def SubDir = "\\SoapUI Data\\"+projName
        def SubDir1 = new File(mainDir,SubDir)
        if(SubDir1.exists())
        {
            def file = new File(SubDir1,fileName6)
//            SoapUI.log file.absoluteFile
            def fos = new FileOutputStream(file.absoluteFile,true)
            def pw = new PrintWriter(fos)
            result.writeTo(pw)
            pw.close()
            fos.close()
        }
        else
        {
            SubDir1.mkdirs()
            def file = new File(SubDir1,fileName6)
//            SoapUI.log file.absoluteFile
            def fos = new FileOutputStream(file.absoluteFile,true)
            def pw = new PrintWriter(fos)
            result.writeTo(pw)
            pw.close()
            fos.close()
        }
        SoapUI.log.info "***Raw Request and Raw Response is exported to a file :: ==>"+mainDir+ SubDir+"\\"+fileName6
    }
}

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */