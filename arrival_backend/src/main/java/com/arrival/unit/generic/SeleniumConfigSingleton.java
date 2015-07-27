package com.arrival.unit.generic;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by tecdesdev on 14/07/15.
 */
public class SeleniumConfigSingleton {
    private static SeleniumConfigSingleton ourInstance = new SeleniumConfigSingleton();
    private final static String single = "single" ;
    private final static String multi = "multi";
    public static String testArt;

    public static SeleniumConfigSingleton getInstance() {
        return ourInstance;
    }

    private SeleniumConfigSingleton() {
        testArt = single;
    }

    public static String getTestArt() {
        return testArt;
    }

    public static void setTestArt(String testArt) {
        SeleniumConfigSingleton.testArt = testArt;
    }

    @BeforeSuite
    public void setUpAppiumConfig() {
        setTestArt(SeleniumConfigSingleton.multi);
    }

    @AfterSuite
    public void cleanUpAppiumConfig(){
        setTestArt("");
    }
}
