package com.arrival.unit.generic;

import com.arrival.selenium.SeleniumManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

public class SeleniumConfigSingleton {
    private final static  Logger log = LogManager.getLogger(SeleniumConfigSingleton.class);
    public final static String TESTNG = "TESTNG";
    public final static String ARRIVAL = "ARRIVAL";
    public static String framework;
    private static SeleniumConfigSingleton ourInstance = new SeleniumConfigSingleton();
    private static SeleniumManager seleniumManager;

    private SeleniumConfigSingleton() {
        framework = TESTNG;
        seleniumManager = new SeleniumManager();
    }

    public static SeleniumConfigSingleton getInstance() {
        log.info(SeleniumConfigSingleton.class + "set up!!");
        return ourInstance;
    }

    public static String getFramework() {
        return framework;
    }

    public static void setFramework(String framework) {
        SeleniumConfigSingleton.framework = framework;
    }

    public SeleniumManager getSeleniumManager() {
        return seleniumManager;
    }

    public void setSeleniumManager(SeleniumManager seleniumManager) {
        SeleniumConfigSingleton.seleniumManager = seleniumManager;

    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @BeforeSuite
    public void setUpSeleniumConfig() {
        setFramework(SeleniumConfigSingleton.ARRIVAL);
       // seleniumManager.setUpSeleniumServerList();
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @AfterSuite
    public void cleanSeleniumConfig() {
        //setFramework(SeleniumConfigSingleton.TESTNG);
     //   seleniumManager.setDownSeleniumServerList();
    }
}
