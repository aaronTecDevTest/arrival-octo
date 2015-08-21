package com.arrival.unit.generic;

import com.arrival.selenium.config.SeleniumConfig;
import com.arrival.utilities.interfaces.IFConfig;
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
    private static final Logger log = LogManager.getLogger(SeleniumConfigSingleton.class);
    public static String testArt;
    private static SeleniumConfigSingleton ourInstance = new SeleniumConfigSingleton();
    private SeleniumConfig testSuiteConfiguration;

    private SeleniumConfigSingleton() {
        testArt = SINGLE;
        testSuiteConfiguration = null;
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

    public SeleniumConfig getTestSuiteConfiguration() {
        return testSuiteConfiguration;
    }

    public void setTestSuiteConfiguration(IFConfig testSuiteConfiguration) {
      //  this.testSuiteConfiguration = (SeleniumConfig) testSuiteConfiguration;
    }


    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        setTestArt(SeleniumConfigSingleton.MULTI);
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @AfterSuite
    public void cleanUpAppiumConfig() {
        setTestArt("");
    }
}
