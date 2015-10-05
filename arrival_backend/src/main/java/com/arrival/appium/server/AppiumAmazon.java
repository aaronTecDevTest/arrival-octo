package com.arrival.appium.server;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 26/05/15.
 * @since 1.0
 */

import com.arrival.utilities.interfaces.IFAppiumServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public class AppiumAmazon implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumAmazon.class);

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {

    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void stopServer() {

    }

    /**
     * This functions Restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {

    }

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file existe.
     */
    @Override
    public void runServerWithJSON(Path JSONFilePath) {

    }

    /**
     * @return a Instance of AppiumServer e.g. ApppiumServer for IOS or Android
     */
    @Override
    public Object getInstance() {
        return null;
    }
}
