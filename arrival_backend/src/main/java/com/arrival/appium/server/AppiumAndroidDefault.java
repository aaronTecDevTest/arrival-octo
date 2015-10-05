package com.arrival.appium.server;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.10.2015.
 * @since: 1.0
 * Package: com.arrival.appium.server
 */
import com.arrival.appium.model.NodeConfig;
import com.arrival.utilities.interfaces.IFAppiumServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;


/**
 * Created by tecdesdev on 26/05/15.
 */
public class AppiumAndroidDefault implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumAndroidDefault.class);
    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";
    private static String appiumArg = "'/Applications/Appium.app/Contents/Resources/node/bin/node' " +
            "lib/server/main.js " +
            "--log-no-colors " +
            "--debug-log-spacing " +
            "--automation-name \"Appium\" " +
            "--platform-name \"Android\" " +
            "--platform-version \"4.4\"";
    private Process process = null;
    private NodeConfig nodeConfig = null;


    /**
     * Standard Constructor
     */
    public AppiumAndroidDefault() {
    }

    public AppiumAndroidDefault(String appiumArg) {
        AppiumAndroidDefault.appiumArg = appiumArg;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {
        try {
            ProcessBuilder pb = new ProcessBuilder(appiumArg);
            process = pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This functions stop a current Server over commando line.
     **/
    @Override
    public void stopServer() {
        try {
            process.destroy();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * This functions restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {
        this.stopServer();
        this.startServer();
    }


    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
    @Override
    public void runServerWithJSON(Path JSONFilePath) {
        nodeConfig.setConfigPath(JSONFilePath);
    }

    /**
     * @return a Instance of AppiumServer e.g. AppiumSever for IOS oder Android
     */
    @Override
    public AppiumAndroidDefault getInstance() {
        return this;
    }

    /**
     * Getter and Setter functions for appiumPath, nodePath and nodeConfig
     */
    public static String getAppiumPath() {
        return appiumPath;
    }

    public static void setAppiumPath(String appiumPath) {
        AppiumAndroidDefault.appiumPath = appiumPath;
    }

    public static String getNodePath() {
        return nodePath;
    }

    public static void setNodePath(String nodePath) {
        AppiumAndroidDefault.nodePath = nodePath;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public static String getAppiumArg() {
        return appiumArg;
    }

    public static void setAppiumArg(String appiumArg) {
        AppiumAndroidDefault.appiumArg = appiumArg;
    }
}
