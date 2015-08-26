package com.arrival.selenium;

import com.arrival.unit.generic.SeleniumConfigSingleton;
import com.arrival.utilities.interfaces.IFConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

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
    WebDriver webDriver;
    private IFConfig testSuiteConfigs;
    private ArrayList<Object> seleniumServerList = new ArrayList<>();

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

    public void setUpSeleniumServerList() {
        WebDriverManager webDriverManager = new WebDriverManager();
        //ToDo not good impl.
        if (SeleniumConfigSingleton.getTestArt().equals(SeleniumConfigSingleton.MULTI)) {
            //Should be here for Appium and Selenium Grid config (Only Json config)
            if(testSuiteConfigs.getParallelTesting()){
                for(int i = 0; i <testSuiteConfigs.getParallelTestingCount(); i++) {
                    seleniumServerList.add(webDriver = webDriverManager.setUpDriver(testSuiteConfigs));
                }
                if(!testSuiteConfigs.getServerName().contains("Non")){
                    for(Object tempWebDriver:seleniumServerList){
                        ((WebDriver) tempWebDriver).get(testSuiteConfigs.getServerName());
                    }
                }
            }
            else{
                seleniumServerList.add(webDriver = webDriverManager.setUpDriver(testSuiteConfigs));
                ((WebDriver) seleniumServerList.get(0)).get(testSuiteConfigs.getServerName());
            }

        } else {
            seleniumServerList.add(webDriver = webDriverManager.setUpDriver(testSuiteConfigs));
        }
    }

    public void setDownSeleniumServerList() {
        for (Object temp : seleniumServerList) {
            ((WebDriver) temp).close();
            ((WebDriver) temp).quit();
        }
    }

}
