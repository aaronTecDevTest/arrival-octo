package com.arrival.appium.server;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 26/05/15.
 * @since 1.0
 */

import com.arrival.utilities.interfaces.IFAppiumServer;
import com.arrival.appium.model.NodeConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppiumAndroid implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumAndroid.class);

    private static final String APPIUM_PATH_MAC = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static final String NODE_PATH_MAC =   "/Applications/Appium.app/Contents/Resources/node/bin/node";

    private static final String APPIUM_PATH_WIN = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
    private static final String NODE_PATH_WIN =   "C:/Program Files (x86)/Appium/node.exe";

    private NodeConfig nodeConfig ;

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private GeneralServerFlag generalServerFlag;
    /**
     * Standard Constructor
     *
    public AppiumAndroid() {
        this.nodeConfig = null;
        this.service = null;
        this.builder = null;
        this.generalServerFlag = null;
    }*/

    public AppiumAndroid(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
        this.nodeConfig = null;
        this.service = null;
        this.builder = null;
        this.generalServerFlag = null;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }


    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {
        try{
          /*  ProcessBuilder pb = new ProcessBuilder(
                    NODE_PATH_MAC, APPIUM_PATH_MAC,
                    "--address",  nodeConfig.getConfiguration().getHost(),
                    "--port",     nodeConfig.getConfiguration().getPort().toString(),
                    "--nodeconfig", nodeConfig.getConfigPath().toString()
            );
            process = pb.start();*/
            service.start();
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
           service.stop();
        }
        catch(Throwable e) {
            log.error(e.getStackTrace());
        }
    }

    /**
     * This functions Restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {

        try {
            service.stop();
            service.start();
        }
     catch (Exception e)
        {
            log.error(e.getStackTrace());
     }
    }

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
    @Override
    public void runServerWithJSON(String JSONFilePath) {

    }

    /**
     * @return a Instance of AppiumServer e.g. ApppiumServer for IOS or Android
     */
    @Override
    public Object getSeverIntance() {
        return service;
    }

    /*public static void main(String[] args) throws IOException {


        AppiumManager manager = new AppiumManager();
        manager.startHubWithNode();
        try{
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        manager.stopHubWithNode();
        System.out.printf(manager.toString());
    AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
            .usingDriverExecutable(new File("C:/Program Files (x86)/nodejs/node.exe"))
            .withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js"))
            .withLogFile(new File("C:/Users/a.kutekidila/Dev/GitHub/arrival-octo/arrival_backend/src/main/resources/report/log/appiumLogs.txt"))
            .withArgument(GeneralServerFlag.CONFIGURATION_FILE, "C:\\Users\\a.kutekidila\\Dev\\GitHub\\arrival-octo\\arrival_backend\\src\\main\\resources\\appiumNodeConfig\\AppiumNodeGFlex.json"));
    service.start();

    try {
        Thread.sleep(60000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    service.stop();
}*/
}
