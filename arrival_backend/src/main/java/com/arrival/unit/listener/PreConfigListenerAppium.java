package com.arrival.unit.listener;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.listener
 */

import com.arrival.appium.AppiumManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;

public class PreConfigListenerAppium implements IExecutionListener {
    private static final Logger log = LogManager.getLogger(PreConfigListenerAppium.class);
    private long startTime;
    private static AppiumManager appiumManager = new AppiumManager();

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public AppiumManager getAppiumManager() {
        return appiumManager;
    }

    public void setAppiumManager(AppiumManager appiumManager) {
        PreConfigListenerAppium.appiumManager = appiumManager;
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     **/
    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
        log.info("TestNG is going to start the Pre-Config for Appium");
        appiumManager.startServer();
    }

    /**
     * Function will be run only if the ArrivalTestSuite was instanced
     **/
    @Override
    public void onExecutionFinish() {
        appiumManager.stopServer();
        log.info("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
    }
}