package com.arrival.utilities.interfaces;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.utilities.interfaces
 */

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
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
    void runServerWithJSON(String JSONFilePath);

    /**
     * @return  a Instance of AppiumServer e.g. ApppiumServer for IOS or Android
     */
    Object geInstance();
}
