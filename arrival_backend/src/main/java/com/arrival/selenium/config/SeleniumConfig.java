package com.arrival.selenium.config;

import com.arrival.utilities.interfaces.IFConfig;
import org.apache.avalon.excalibur.logger.LoggerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 13.08.2015.
 * @since: 1.0
 * Package: com.arrival.selenium.config
 */
public class SeleniumConfig implements IFConfig {
    private static final Logger log = LogManager.getLogger(SeleniumConfig.class.getName());

    String Testpart;
    boolean parallelTesting;
    Integer parallelTestingCounter;
    String browser;
    //Todo


    public void SeleniumConfig(){

    }

    public void runConfig() {
    }

    public String getBrowser() {
        return browser;
    }
}
