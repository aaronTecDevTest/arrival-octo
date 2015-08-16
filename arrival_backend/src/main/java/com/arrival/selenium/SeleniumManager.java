package com.arrival.selenium;

import com.arrival.selenium.config.SeleniumConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
 * @since 1.0
 */
public class SeleniumManager {
private static final Logger log =  LogManager.getLogger(SeleniumManager.class);

private HashMap<String, SeleniumConfig> seleniumConfigs;

public SeleniumManager(){
               seleniumConfigs = new HashMap<>();
                                                      }

public void setUpConfgi(String testSuiteName) {

               seleniumConfigs.get(testSuiteName).runConfig();
               }
               }
