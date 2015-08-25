package com.arrival.selenium;

import com.arrival.selenium.config.SeleniumConfig;
import com.arrival.unit.generic.SeleniumConfigSingleton;
import com.arrival.utilities.interfaces.IFConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
 * @since 1.0
 */
public class SeleniumManager {
    private static final Logger log = LogManager.getLogger(SeleniumManager.class);
    //Later for Singel-Test Config
    //private HashMap<String, IFConfig> singelConfigs;
    //Later for Singel-Test Config

    private IFConfig testSuiteConfigs;
    private ArrayList<Object> seleniumServerList = new ArrayList<>();
    WebDriver webDriver;

    public SeleniumManager() {
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

    public void setUpSeleniumServerList(){
        WebDriverManager webDriverManager = new WebDriverManager();
        if (SeleniumConfigSingleton.getTestArt().equals(SeleniumConfigSingleton.MULTI)) {
            //Should be here for Appium and Selenium Grid config (Only Json config)
            seleniumServerList.add(       webDriver = webDriverManager.setUpDriver(this.testSuiteConfigs)  );
            seleniumServerList.add(       webDriver = webDriverManager.setUpDriver(this.testSuiteConfigs)  );
            seleniumServerList.add(       webDriver = webDriverManager.setUpDriver(this.testSuiteConfigs)  );
            seleniumServerList.add(       webDriver = webDriverManager.setUpDriver(this.testSuiteConfigs)  );

        } else {
            seleniumServerList.add(       webDriver = webDriverManager.setUpDriver(this.testSuiteConfigs)  );
        }
    }

    public void setDownSeleniumServerList(){
        for (Object temp : seleniumServerList){
            ((WebDriver) temp).close();
            ((WebDriver) temp).quit();
        }
    }

}
