package com.arrival.unit.generic;

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

public class AppiumConfigSingleton {
    private static final Logger log = LogManager.getLogger(AppiumConfigSingleton.class);
    private final static String single = "single";
    private final static String multi = "multi";
    public static String testArt;
    private static AppiumConfigSingleton ourInstance = new AppiumConfigSingleton();

    private AppiumConfigSingleton() {
        testArt = single;
    }

    public static AppiumConfigSingleton getInstance() {
        return ourInstance;
    }

    public static String getTestArt() {
        return testArt;
    }

    public static void setTestArt(String testArt) {
        AppiumConfigSingleton.testArt = testArt;
    }

    /**
     * Code will be run only if the ArrivalTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        //Todo: Start the Appium server + SeleniumGrid (SeleniumHub) here
        setTestArt(AppiumConfigSingleton.multi);
    }

    /**
     * Code will be run only if the ArrivalTestSuite was instanced
     */
    @AfterSuite
    public void cleanUpAppiumConfig() {
        //Todo: Stop the Appium server + SeleniumGrid (SeleniumHub) here
        setTestArt("");
    }
}
