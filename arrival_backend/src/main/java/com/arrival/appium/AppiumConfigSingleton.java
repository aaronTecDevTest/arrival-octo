package com.arrival.appium;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

import com.arrival.appium.config.JSONConfigReader;
import com.arrival.selenium.server.Hub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Path;
import java.util.ArrayList;

public class AppiumConfigSingleton {
    private static final Logger log = LogManager.getLogger(AppiumConfigSingleton.class);
    public static final  String TESTNG = "TESTNG";
    public static final  String ARRIVAL = "ARRIVAL";
    public static String framework;
    private static AppiumConfigSingleton ourInstance = new AppiumConfigSingleton();
    private static AppiumManager appiumManager;

    //public
    public ArrayList<Object> appiumSeverList = new ArrayList<>();
    public WebDriver browser;

    private Hub hub;

    private AppiumConfigSingleton(){
        framework = TESTNG;
        appiumManager = new AppiumManager();
    }

    public static AppiumConfigSingleton getInstance(){
        log.info(AppiumConfigSingleton.class + "set up!!");
        return  ourInstance;
    }

    public static AppiumManager getAppiumManager() {
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
        AppiumConfigSingleton.framework = framework;
    }

    public static void setAppiumManager(AppiumManager appiumManager) {
        AppiumConfigSingleton.appiumManager = appiumManager;
    }

    /**
     * Code will be run only if the ArrivalTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        //Todo: Start the Appium server + SeleniumGrid (SeleniumHub) here
        setFramework(AppiumConfigSingleton.ARRIVAL);
        hub = new Hub();
        hub.startHub();

        JSONConfigReader jsonConfigReader = new JSONConfigReader(appiumManager.getTestSuiteConfigs().getJsonConfigPath());
        ArrayList<Path> jsonPathList = jsonConfigReader.getPathList();

        for(Path path : jsonPathList){

            appiumSeverList.add(null);
        }
    }

    /**
     * Code will be run only if the ArrivalTestSuite was instanced
     */
    @AfterSuite
    public void cleanUpAppiumConfig() {
        //Todo: Stop the Appium server + SeleniumGrid (SeleniumHub) here
        setFramework(null);
        hub.stopHub();
    }
}
