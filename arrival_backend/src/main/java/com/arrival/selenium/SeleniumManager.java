package com.arrival.selenium;

import com.arrival.selenium.config.SeleniumConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
 * @since 1.0
 */
public class SeleniumManager {
    private static final Logger log = LogManager.getLogger(SeleniumManager.class);
    private HashMap<String, SeleniumConfig> seleniumConfigs;

    public  SeleniumManager(SeleniumConfig seleniumConfig){
        seleniumConfigs.put(seleniumConfig.getClass().toString(),seleniumConfig);
    }

    public SeleniumManager() {
        seleniumConfigs = new HashMap<>();
    }

    public void setUpConfgi(String testSuiteName) {

        seleniumConfigs.get(testSuiteName).runConfig();
    }

    public WebDriver getBrowser(SeleniumConfig seleniumConfig) {
        WebDriverManager webDriverManager = new WebDriverManager();
        //SeleniumConfig tempConfig = seleniumConfigs.get(seleniumConfig.getClass().toString());
        //WebDriver tempWebDriver = webDriverManager.setUpDriver(tempConfig);
        WebDriver tempWebDriver = webDriverManager.setUpDriver(null);
        return tempWebDriver;
    }
}
