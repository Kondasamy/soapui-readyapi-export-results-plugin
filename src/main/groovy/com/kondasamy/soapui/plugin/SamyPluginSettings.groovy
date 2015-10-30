package com.kondasamy.soapui.plugin

import com.eviware.soapui.SoapUI
import com.eviware.soapui.actions.Prefs
import com.eviware.soapui.model.settings.Settings

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */
import com.eviware.soapui.plugins.auto.PluginPrefs
import com.eviware.soapui.support.components.SimpleForm
import com.eviware.soapui.support.types.StringToStringMap

@PluginPrefs
public class SamyPluginSettings implements Prefs
{

    @Override
    SimpleForm getForm() {
        return null
    }

    @Override
    void setFormValues(Settings settings) {

    }

    @Override
    void getFormValues(Settings settings) {

    }

    @Override
    void storeValues(StringToStringMap stringToStringMap, Settings settings) {

    }

    @Override
    StringToStringMap getValues(Settings settings) {
        return null
    }

    @Override
    String getTitle() {
        return "Samy Prefs"
    }
}

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */