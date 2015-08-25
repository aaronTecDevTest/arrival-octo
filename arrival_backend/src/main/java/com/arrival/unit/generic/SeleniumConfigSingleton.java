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
    public final static String SINGLE = "SINGLE";
    public final static String MULTI = "MULTI";
    private final static  Logger log = LogManager.getLogger(SeleniumConfigSingleton.class);
    public static String testArt;
    private static SeleniumConfigSingleton ourInstance = new SeleniumConfigSingleton();
    private static SeleniumManager seleniumManager;

    private SeleniumConfigSingleton() {
        testArt = SINGLE;
    }

    public static SeleniumConfigSingleton getInstance() {
        log.info(SeleniumConfigSingleton.class + "set up!!");
        return ourInstance;
    }

    public static String getTestArt() {
        return testArt;
    }

    public static void setTestArt(String testArt) {
        SeleniumConfigSingleton.testArt = testArt;
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
        setTestArt(SeleniumConfigSingleton.MULTI);
        seleniumManager.setUpSeleniumServerList();
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @AfterSuite
    public void cleanSeleniumConfig() {
        setTestArt(SeleniumConfigSingleton.SINGLE);
        seleniumManager.setDownSeleniumServerList();
    }
}
