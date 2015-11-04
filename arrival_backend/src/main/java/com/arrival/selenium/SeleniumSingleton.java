package com.arrival.selenium;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SeleniumSingleton {
    private static final  Logger log = LogManager.getLogger(SeleniumSingleton.class);
    private static SeleniumSingleton ourInstance = new SeleniumSingleton();
    private static SeleniumManager seleniumManager;

    public static final String TESTNG = "TESTNG";
    public static final String ARRIVAL = "ARRIVAL";
    public static String framework;


    private SeleniumSingleton() {
        framework = TESTNG;
        seleniumManager = new SeleniumManager();
    }

    public static SeleniumSingleton getInstance() {
        log.info(SeleniumSingleton.class + "set up!!");
        return ourInstance;
    }

    public static String getFramework() {
        return framework;
    }

    public static void setFramework(String framework) {
        SeleniumSingleton.framework = framework;
    }

    public SeleniumManager getSeleniumManager() {
        return seleniumManager;
    }

    public void setSeleniumManager(SeleniumManager seleniumManager) {
        SeleniumSingleton.seleniumManager = seleniumManager;

    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @BeforeSuite
    public void setUpSeleniumConfig() {
        setFramework(SeleniumSingleton.ARRIVAL);
       // seleniumManager.setUpSeleniumServerList();
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @AfterSuite
    public void cleanSeleniumConfig() {
        //setFramework(SeleniumSingleton.TESTNG);
     //   seleniumManager.setDownSeleniumServerList();
    }
}
