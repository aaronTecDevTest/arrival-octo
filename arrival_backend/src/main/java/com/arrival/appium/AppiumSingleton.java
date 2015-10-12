package com.arrival.appium;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

import com.arrival.appium.config.JSONConfigReader;
import com.arrival.appium.server.AppiumAndroid;
import com.arrival.appium.server.AppiumServer;
import com.arrival.selenium.server.Hub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Path;
import java.util.ArrayList;

public class AppiumSingleton {
    private static final Logger log = LogManager.getLogger(AppiumSingleton.class);
    private static AppiumSingleton ourInstance = new AppiumSingleton();
    public static final  String TESTNG = "TESTNG";
    public static final  String ARRIVAL = "ARRIVAL";
    public static String framework;
    public AppiumManager appiumManager;
    public AppiumServer appiumServer;

    //public
    public ArrayList<Object> appiumSeverList = new ArrayList<>();
    public WebDriver browser;

    private Hub hub;

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
     * Code will be run only if the ArrivalTestSuite was instanced
     */
    @BeforeSuite
    public void setUpAppiumConfig() {
        //Todo: Start the Appium server + SeleniumGrid (SeleniumHub) here
        setFramework(AppiumSingleton.ARRIVAL);
      /*  hub = new Hub();
        hub.startHub();

        JSONConfigReader jsonConfigReader = new JSONConfigReader(appiumManager.getTestSuiteConfigs().getJsonConfigPath());
        ArrayList<Path> jsonPathList = jsonConfigReader.getPathList();

        for(Path path : jsonPathList){

            appiumSeverList.add(null);
        }*/
    }

    /**
     * Code will be run only if the ArrivalTestSuite was instanced
     */
    @AfterSuite
    public void cleanUpAppiumConfig() {
        //Todo: Stop the Appium server + SeleniumGrid (SeleniumHub) here
      /*  setFramework(null);
        hub.stopHub();*/
    }
}
