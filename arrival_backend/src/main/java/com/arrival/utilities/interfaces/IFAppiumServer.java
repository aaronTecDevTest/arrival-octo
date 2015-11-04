package com.arrival.utilities.interfaces;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.utilities.interfaces
 */

import com.arrival.appium.model.NodeConfig;
import io.appium.java_client.service.local.AppiumDriverLocalService;


/**
 *
 */
public interface IFAppiumServer {

    /**
     * This functions start a current Server over commando line.
     **/
    void startServer();


    /**
     * This functions start a current Server over commando line.
     **/
    void stopServer();


    /**
     * This functions Restart a current Server over commando line.
     **/
    void restartSever();


    /**
     * Set the configurations for the Sever.
     */
    void setNodeConfig(NodeConfig nodeConfig);

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
    void runServerWithJSON(String JSONFilePath);

    /**
     * @return a Instance of AppiumServer e.g. ApppiumServer for IOS or Android
     */
    AppiumDriverLocalService getSeverInstance();

}
