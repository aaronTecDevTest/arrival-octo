package com.arrival.unit.generic;

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
    private final static String single = "single";
    private final static String multi = "multi";
    public static String testArt;
    private static SeleniumConfigSingleton ourInstance = new SeleniumConfigSingleton();

    private SeleniumConfigSingleton() {
        testArt = single;
    }

    public static SeleniumConfigSingleton getInstance() {
        return ourInstance;
    }

    public static String getTestArt() {
        return testArt;
    }

    public static void setTestArt(String testArt) {
        SeleniumConfigSingleton.testArt = testArt;
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        setTestArt(SeleniumConfigSingleton.multi);
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @AfterSuite
    public void cleanUpAppiumConfig() {
        setTestArt("");
    }
}
