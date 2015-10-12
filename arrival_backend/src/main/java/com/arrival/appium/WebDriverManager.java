package com.arrival.appium;


/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.10.2015.
 * @since: 1.0
 * Package: com.arrival.appium
 */

import com.arrival.utilities.interfaces.IFTestCase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WebDriverManager {
    private static final Logger log = LogManager.getLogger(WebDriverManager.class);

    public WebDriverManager(){

    }

    public Object setUpDriver(IFTestCase runningConfiguration /*noch was hier*/){

        Object driver = null;

        String testTyp = runningConfiguration.getTcLastRun();

        switch (testTyp){
            case "ios":
               // AndroidDriver androidDriver = new AndroidDriver();
              //  IOSDriver iosDriver;
                break;

            case "and":

                break;

            default:

                break;
        }
        return driver;
    }
}
