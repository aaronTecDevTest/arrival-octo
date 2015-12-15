package com.arrival.selenium;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * @Created: 09.05.2015
 * @since: 1.0
 * @Package: com.arrival.selenium
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeleniumSingleton {
    public static final String TESTNG = "TESTNG";
    public static final String ARRIVAL = "ARRIVAL";
    public static String framework;

    private static final Logger log = LogManager.getLogger(SeleniumSingleton.class);
    private static SeleniumSingleton ourInstance = new SeleniumSingleton();
    private static SeleniumManager seleniumManager = new SeleniumManager();

    private SeleniumSingleton() {
        framework = TESTNG;
        log.info(SeleniumSingleton.class + " set up!!");
    }

    public static SeleniumSingleton getInstance() {

        return ourInstance;
    }

    public static SeleniumManager getSeleniumManager() {
        return seleniumManager;
    }

    public static void setSeleniumManager(SeleniumManager seleniumManager) {
        SeleniumSingleton.seleniumManager = seleniumManager;
    }

    public static String getFramework() {
        return framework;
    }

    public static void setFramework(String framework) {
        SeleniumSingleton.framework = framework;
    }

    public boolean isArrival(){
        return    framework.equals(ARRIVAL);
    }
}
