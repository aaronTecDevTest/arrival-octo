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
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

/**
 * Created by tecdesdev on 26/05/15.
 */
public class AppiumIOSDefault implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumIOSDefault.class);
    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";
    private static Integer webKitProxyPort= 27751; //27752-27852
    private NodeConfig nodeConfig = null;

    /**
     * Standard Constructor
     */
    public AppiumIOSDefault() {
        nodeConfig = null;
    }

    public AppiumIOSDefault(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {
        try{
            CommandLine command = new CommandLine(nodePath);
            command.addArgument(appiumPath);
            command.addArgument("--address");
            command.addArgument(nodeConfig.getConfiguration().getHost());
            command.addArgument("--port");
            command.addArgument(nodeConfig.getConfiguration().getPort().toString());
            command.addArgument("--nodeconfig");
            command.addArgument(nodeConfig.getConfigPath().toString());
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This functions stop a current Server over commando line.
     **/
    @Override
    public void stopServer() {

    }

    /**
     * This functions restart a current Server over commando line.
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
        nodeConfig.setConfigPath(JSONFilePath);
    }

    /**
     * @return a Instance of AppiumServer e.g. AppiumSever for IOS oder Android
     */
    @Override
    public AppiumIOSDefault getInstance() {
        return this;
    }

    /**
     * Getter and Setter functions for appiumPath, nodePath and nodeConfig
     */
    public static String getAppiumPath() {
        return appiumPath;
    }

    public static void setAppiumPath(String appiumPath) {
        AppiumIOSDefault.appiumPath = appiumPath;
    }

    public static String getNodePath() {
        return nodePath;
    }

    public static void setNodePath(String nodePath) {
        AppiumIOSDefault.nodePath = nodePath;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    /**
     *
     */
    public void startIOSWebKitDebugProxy(){
        AppiumIOSDefault.webKitProxyPort++;
    }

    public void stopIOSWebKitDebugProxy(){
        AppiumIOSDefault.webKitProxyPort--;
    }

}