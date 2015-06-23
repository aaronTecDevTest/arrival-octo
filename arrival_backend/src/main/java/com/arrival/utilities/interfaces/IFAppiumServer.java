package com.arrival.utilities.interfaces;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 * Created on 26/05/15.
 * @since 1.0
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
}
