package com.kondasamy.soapui.plugin

import com.eviware.soapui.SoapUI

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */
import com.eviware.soapui.model.project.Project
import com.eviware.soapui.plugins.ActionConfiguration
import com.eviware.soapui.support.UISupport
import com.eviware.soapui.support.action.support.AbstractSoapUIAction

@ActionConfiguration(actionGroup = ActionGroups.OPEN_PROJECT_ACTIONS)
class ProjectSaveResponseActionDummy extends AbstractSoapUIAction <Project>
{
    public ProjectSaveResponseActionDummy()
    {
        super("Plugin:Export Request and Response", "Saves recent responses of underlying TestSteps's to a file")
    }

    @Override
    void perform(Project project, Object o)
    {
        SoapUI.log.info project.name
        project.getTestSuiteList().each
                {
                    testSuite->
                        SoapUI.Log.info testSuite.name
                        testSuite.
                        testSuite.getTestCaseList().each
                                {
                                    testCase->

                                }

                }
        UISupport.showInfoMessage("Artifacts Successfully exported!! Please see the SoapUI log for more information!","File Export Success!!!")
    }
}
/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */
