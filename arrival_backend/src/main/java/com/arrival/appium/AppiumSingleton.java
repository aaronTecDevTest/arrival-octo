package com.arrival.appium;

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

public class AppiumSingleton {
    private static final Logger log = LogManager.getLogger(AppiumSingleton.class);
    private static AppiumSingleton ourInstance = new AppiumSingleton();
    public static final  String TESTNG = "TESTNG";
    public static final  String ARRIVAL = "ARRIVAL";
    public static String framework;
    public AppiumManager appiumManager;


    private AppiumSingleton(){
        framework = TESTNG;
        appiumManager = new AppiumManager();
    }

    public static AppiumSingleton getInstance(){
        log.info(AppiumSingleton.class + "set up!!");
        return  ourInstance;
    }

    public AppiumManager getAppiumManager() {
        return appiumManager;
    }

    public static String getTesting() {
        return TESTNG;
    }

    public static String getArrival() {
        return ARRIVAL;
    }

    public static String getFramework() {
        return framework;
    }

    public static void setFramework(String framework) {
        AppiumSingleton.framework = framework;
    }

    public void setAppiumManager(AppiumManager appiumManager) {
        this.appiumManager = appiumManager;
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        setFramework(AppiumSingleton.ARRIVAL);
        appiumManager.startServer();
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     */
    @AfterSuite
    public void cleanUpAppiumConfig() {
        appiumManager.stopServer();
    }
}
