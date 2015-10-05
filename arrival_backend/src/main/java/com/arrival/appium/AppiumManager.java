package com.arrival.appium;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
 * @since 1.0
 */

import com.arrival.selenium.config.SeleniumConfig;
import com.arrival.utilities.interfaces.IFConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class AppiumManager {
    private static final Logger log = LogManager.getLogger(AppiumManager.class);

    WebDriver webDriver;
    private IFConfig testSuiteConfigs;
    private ArrayList<Object> appiumServerList;

    public AppiumManager() {
        webDriver = null;
        testSuiteConfigs = new SeleniumConfig();
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
