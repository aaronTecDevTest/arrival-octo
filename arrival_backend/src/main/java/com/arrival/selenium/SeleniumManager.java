package com.arrival.selenium;


/**
 * @author Aaron Kutekidila
 * @version 1.0
 * @Created on 01.06.2015
 * @since 1.0
 * @Package: com.arrival.selenium
 *
 */

import com.arrival.selenium.config.SeleniumConfig;
import com.arrival.utilities.interfaces.IFConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SeleniumManager {
    private static final Logger log = LogManager.getLogger(SeleniumManager.class);
    WebDriver webDriver;
    private IFConfig testSuiteConfigs;
    private ArrayList<Object> seleniumServerList;

    public SeleniumManager() {
        webDriver = null;
        testSuiteConfigs = new SeleniumConfig();
        seleniumServerList = new ArrayList<>();
    }

    public ArrayList<Object> getSeleniumServerList() {
        return seleniumServerList;
    }

    public void setSeleniumServerList(ArrayList<Object> seleniumServerList) {
        this.seleniumServerList = seleniumServerList;
    }

    public IFConfig getTestSuiteConfigs() {
        return testSuiteConfigs;
    }

    public void setTestSuiteConfigs(IFConfig testSuiteConfigs) {
        this.testSuiteConfigs = testSuiteConfigs;
    }

    public void setUpSeleniumServerList() {
    }

    public void setDownSeleniumServerList() {
    }
}
