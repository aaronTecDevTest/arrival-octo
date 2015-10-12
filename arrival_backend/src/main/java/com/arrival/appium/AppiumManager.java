package com.arrival.appium;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
 * @since 1.0
 */

import com.arrival.appium.config.AppiumConfig;
import com.arrival.utilities.interfaces.IFConfig;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AppiumManager {
    private static final Logger log = LogManager.getLogger(AppiumManager.class);

    AndroidDriver androidDriver;
    private IFConfig testSuiteConfigs;
    private ArrayList<Object> appiumServerList;

    public AppiumManager() {
        androidDriver = null;
        testSuiteConfigs = new AppiumConfig();
        appiumServerList = new ArrayList<>();
    }

    public ArrayList<Object> getAppiumServerList() {
        return appiumServerList;
    }

    public void setAppiumServerList(ArrayList<Object> appiumServerList) {
        this.appiumServerList = appiumServerList;
    }

    public IFConfig getTestSuiteConfigs() {
        return testSuiteConfigs;
    }

    public void setTestSuiteConfigs(IFConfig testSuiteConfigs) {
        this.testSuiteConfigs = testSuiteConfigs;
    }
}
