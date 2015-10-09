package com.arrival.appium.server;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 26/05/15.
 * @since 1.0
 */

import com.arrival.utilities.interfaces.IFAppiumServer;
import com.arrival.appium.model.NodeConfig;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppiumAndroid implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumAndroid.class);

    private static final String APPIUM_PATH_MAC = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static final String NODE_PATH_MAC =   "/Applications/Appium.app/Contents/Resources/node/bin/node";

    private static final String APPIUM_PATH_WIN = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
    private static final String NODE_PATH_WIN =   "C:/Program Files (x86)/Appium/node.exe";

    private NodeConfig nodeConfig = null;
    private Process process = null;

    /**
     * Standard Constructor
     */
    public AppiumAndroid() {
        this.nodeConfig = null;
    }

    public AppiumAndroid(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {
        try{
            ProcessBuilder pb = new ProcessBuilder(
                    NODE_PATH_MAC, APPIUM_PATH_MAC,
                    "--address",  nodeConfig.getConfiguration().getHost(),
                    "--port",     nodeConfig.getConfiguration().getPort().toString(),
                    "--nodeconfig", nodeConfig.getConfigPath().toString()
            );
            process = pb.start();
        }catch (Exception e){
            log.error(e.getStackTrace());
            log.error("Count'n start server on: " + nodeConfig.toString());
        }
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void stopServer() {
        try {
            process.destroy();
        }
        catch(Throwable e) {
            log.error(e.getStackTrace());
            log.error("Count'n stop the server: " + process.toString());
        }
    }

    /**
     * This functions Restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {

    }

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
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