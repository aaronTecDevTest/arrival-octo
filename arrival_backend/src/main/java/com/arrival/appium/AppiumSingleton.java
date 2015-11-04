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
    public static final String TESTNG = "TESTNG";
    public static final String ARRIVAL = "ARRIVAL";
    private static final Logger log = LogManager.getLogger(AppiumSingleton.class);
    public static String framework;
    private static AppiumSingleton ourInstance = new AppiumSingleton();
    private static AppiumManager appiumManager;

    private AppiumSingleton() {
        framework = TESTNG;
        appiumManager = new AppiumManager();
    }

    public static AppiumSingleton getInstance() {
        log.info(AppiumSingleton.class + "set up!!");
        return ourInstance;
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

    public AppiumManager getAppiumManager() {
        return appiumManager;
    }

    public void setAppiumManager(AppiumManager appiumManager) {
        AppiumSingleton.appiumManager = appiumManager;
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        setFramework(AppiumSingleton.ARRIVAL);
        appiumManager.ini();
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
