package com.arrival.unit.generic;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by tecdesdev on 14/07/15.
 */
public class AppiumConfigSingleton {
    private static AppiumConfigSingleton ourInstance = new AppiumConfigSingleton();
    private final static String single = "single" ;
    private final static String multi = "multi";
    public static String testArt;

    public static AppiumConfigSingleton getInstance() {
        return ourInstance;
    }

    private AppiumConfigSingleton() {
        testArt = single;
    }

    public static String getTestArt() {
        return testArt;
    }

    public static void setTestArt(String testArt) {
        AppiumConfigSingleton.testArt = testArt;
    }

    @BeforeSuite
    public void setUpAppiumConfig() {
        setTestArt(AppiumConfigSingleton.multi);
    }

    @AfterSuite
    public void cleanUpAppiumConfig(){
        setTestArt("");
    }
}
