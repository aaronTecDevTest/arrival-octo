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
    private static AppiumManager appiumManager = new AppiumManager();

    private AppiumSingleton() {
        framework = TESTNG;
        log.info(AppiumSingleton.class + " set up!!");
    }

    public static AppiumManager getAppiumManager() {
        return appiumManager;
    }

    public static void setAppiumManager(AppiumManager appiumManager) {
        AppiumSingleton.appiumManager = appiumManager;
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

    public boolean isArrival(){
        return    framework.equals(ARRIVAL);
    }
}
