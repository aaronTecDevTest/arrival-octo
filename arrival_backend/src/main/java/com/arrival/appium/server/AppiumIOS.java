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

public class AppiumIOS implements IFAppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumIOS.class);
    private ResourceBundle bundle = SystemPreferences.getResourceBundle("bundleGlobal");

    private String APPIUM_PATH_MAC = bundle.getString("APPIUM_PATH_MAC");
    private String NODE_PATH_MAC = bundle.getString("NODE_PATH_MAC");
    private String WEB_KIT_PATH_MAC = bundle.getString("WEB_KIT_PATH_MAC ");

    private String LOG_FILE = bundle.getString("LOG_FILE");
    private Integer WEB_KIT_PROXY_PORT = Integer.getInteger(bundle.getString("WEB_KIT_PROXY_PORT ")); //27751; //27752-27852

    private NodeConfig nodeConfig;
    private AppiumDriverLocalService service;
    private Integer webKitProxyPort;

    /**
     * Standard Constructor
     */
    public AppiumIOS() {
        this.nodeConfig = null;
        this.service = null;
    }

    public AppiumIOS(NodeConfig nodeConfig) {
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

            if (nodeConfig.getSingelCapability().getBrowserName().equalsIgnoreCase("safari")) {
                startIOSWebKitDebugProxy();
            }
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
            if (nodeConfig.getSingelCapability().getBrowserName().equalsIgnoreCase("safari")) {
                stopIOSWebKitDebugProxy();
            }
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
                                                                    .withLogFile(new File(LOG_FILE))
                                                                    .withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfig.getConfigPath().toString()));

        }

      /*  if (SystemPreferences.getInstance().isWindows()){
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(NODE_PATH_WIN))
                    .withAppiumJS(new File(APPIUM_PATH_WIN))
                    .withLogFile(new File(LOG_FILE))
                    .withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfig.getConfigPath().toString()));
        }*/
    }

    public void startIOSWebKitDebugProxy() {
        WEB_KIT_PROXY_PORT++;
    }

    public void stopIOSWebKitDebugProxy() {
        WEB_KIT_PROXY_PORT--;
    }
}


