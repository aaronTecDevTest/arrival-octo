package com.arrival.unit.listener;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.listener
 */

import com.arrival.selenium.SeleniumManager;
import com.arrival.selenium.SeleniumSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;

public class PreConfigListenerSelenium implements IExecutionListener {
    private static final Logger log = LogManager.getLogger(PreConfigListenerSelenium.class);
    private long startTime;
    private static SeleniumManager seleniumManager = new SeleniumManager();

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public SeleniumManager getSeleniumManager() {
        return seleniumManager;
    }

    public void setSeleniumManager(SeleniumManager seleniumManager) {
        PreConfigListenerSelenium.seleniumManager = seleniumManager;
    }

    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
        log.info("TestNG is going to start the Pre-Config for Selenium");
        seleniumManager.setUpSeleniumServerList();
    }

    @Override
    public void onExecutionFinish() {
        seleniumManager.setDownSeleniumServerList();
        log.info("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
    }
}