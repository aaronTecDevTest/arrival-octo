package com.arrival.appium;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.appium
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppiumSingleton {
    public static final String TESTNG = "TESTNG";
    public static final String ARRIVAL = "ARRIVAL";
    public static String framework;

    private static final Logger log = LogManager.getLogger(AppiumSingleton.class);
    private static AppiumSingleton ourInstance = new AppiumSingleton();
    private static AppiumManager appiumManager;

    private AppiumSingleton() {
        framework = TESTNG;
        appiumManager = new AppiumManager();
        log.info(AppiumSingleton.class + " set up!!");
    }

    public static AppiumSingleton getInstance() {
        return ourInstance;
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


    public boolean isArrival(){
        return    framework.contains(ARRIVAL);
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     *
    @BeforeSuite
    public void setUpAppiumConfig() {
        setFramework(AppiumSingleton.ARRIVAL);
        //appiumManager.startServer();
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     *
    @AfterSuite
    public void cleanUpAppiumConfig() {
        //appiumManager.stopServer();
    }*/
}
