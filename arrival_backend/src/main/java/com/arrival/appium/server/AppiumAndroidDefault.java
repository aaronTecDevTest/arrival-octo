package com.arrival.appium.server;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.10.2015.
 * @since: 1.0
 * Package: com.arrival.appium.server
 */

import com.arrival.appium.model.NodeConfig;
import com.arrival.utilities.SystemPreferences;
import com.arrival.utilities.interfaces.IFAppiumServer;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ResourceBundle;

public class AppiumAndroidDefault implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumAndroidDefault.class);

    private SystemPreferences systemPreferences = SystemPreferences.getInstance();
    private ResourceBundle bundle = SystemPreferences.getResourceBundle("bundleGlobal");

    private String APPIUM_PATH_MAC = bundle.getString("APPIUM_PATH_MAC");
    private String NODE_PATH_MAC = bundle.getString("NODE_PATH_MAC");

    private String APPIUM_PATH_WIN = bundle.getString("APPIUM_PATH_WIN");
    private String NODE_PATH_WIN = bundle.getString("NODE_PATH_WIN");

    private String LOG_FILE = bundle.getString("LOG_FILE");


    private NodeConfig nodeConfig;
    private AppiumDriverLocalService service;

    /**
     * Standard Constructor
     */
    public AppiumAndroidDefault() {
        setUpServer();
    }

    /**
     * Main
     *

    public static void main(String[] args) throws InterruptedException {
        SystemPreferences.getInstance();
        AppiumAndroidDefault appiumAndroidDefault = new AppiumAndroidDefault();
        appiumAndroidDefault.startServer();
        Thread.sleep(10000);
        appiumAndroidDefault.stopServer();
    }*/

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
        try {
            log.info("Android Default server start....");
            service.start();
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
    }

    /**
     * This functions stop a current Server over commando line.
     **/
    @Override
    public void stopServer() {
        try {
            log.info("Android Default server stop....");
            service.stop();
        } catch (Throwable e) {
            log.error(e.getStackTrace());
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
    public void runServerWithJSON(String JSONFilePath) {

    }

    /**
     * @return a Instance of AppiumServer e.g. AppiumSever for IOS oder Android
     */
    @Override
    public AppiumDriverLocalService getSeverInstance() {
        return null;
    }


    private void setUpServer(){
        if (SystemPreferences.getInstance().isMacOS()) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(NODE_PATH_MAC))
                    .withAppiumJS(new File(APPIUM_PATH_MAC))
                    .withLogFile(new File(LOG_FILE))
                    .withArgument(GeneralServerFlag.LOG_NO_COLORS, "false")
                    .withArgument(GeneralServerFlag.AUTOMATION_NAME, "Appium")
                    .withArgument(GeneralServerFlag.PLATFORM_NAME,"Android")
                    .withArgument(GeneralServerFlag.PLATFORM_VERSION,"5.0"));
        }

        if (SystemPreferences.getInstance().isWindows()) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(NODE_PATH_WIN))
                    .withAppiumJS(new File(APPIUM_PATH_WIN))
                    .withLogFile(new File(LOG_FILE))
                    .withArgument(GeneralServerFlag.LOG_NO_COLORS, "false")
                    .withArgument(GeneralServerFlag.AUTOMATION_NAME, "Appium")
                    .withArgument(GeneralServerFlag.PLATFORM_NAME,"Android")
                    .withArgument(GeneralServerFlag.PLATFORM_VERSION,"5.0"));
        }
    }
}
