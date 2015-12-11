package com.arrival.appium.server;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 * Created on 26/05/15.
 * @since 1.0
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

public class AppiumAndroid implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumAndroid.class);
    private ResourceBundle bundle = SystemPreferences.getResourceBundle("arrivalGlobal");

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
    public AppiumAndroid() {
        this.nodeConfig = null;
        this.service = null;
    }

    public AppiumAndroid(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
        this.service = null;

        setUpSerer();
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
        try {
            service.start();
        } catch (Exception e) {
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
        } catch (Throwable e) {
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
        } catch (Exception e) {
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
    public AppiumDriverLocalService getSeverInstance() {
        return service;
    }

    private void setUpSerer() {
        if (SystemPreferences.getInstance().isMacOS()) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(NODE_PATH_MAC))
                    .withAppiumJS(new File(APPIUM_PATH_MAC))
                    //.withLogFile(new File(LOG_FILE))
                    .withArgument(GeneralServerFlag.UIID, nodeConfig.getSingelCapability().getUdid())
                    .withArgument(GeneralServerFlag.AUTOMATION_NAME,"Appium")

                    .withArgument( GeneralServerFlag.CONFIGURATION_FILE, nodeConfig.getConfigPath().toString()));
        }

        if (SystemPreferences.getInstance().isWindows()) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(NODE_PATH_WIN))
                    .withAppiumJS(new File(APPIUM_PATH_WIN))
                    //.withLogFile(new File(LOG_FILE))
                    .withArgument(GeneralServerFlag.UIID, nodeConfig.getSingelCapability().getUdid())
                    .withArgument(GeneralServerFlag.AUTOMATION_NAME,"Appium")
                    .withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfig.getConfigPath().toString()));
        }
    }
}
