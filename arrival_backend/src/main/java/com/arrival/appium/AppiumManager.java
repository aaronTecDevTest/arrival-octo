package com.arrival.appium;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 * Created on 01.06.2015.
 * @since 1.0
 */

import com.arrival.appium.config.AppiumConfig;
import com.arrival.appium.config.JSONConfigReader;
import com.arrival.appium.model.NodeConfig;
import com.arrival.appium.server.AppiumAndroid;
import com.arrival.appium.server.AppiumAndroidDefault;
import com.arrival.appium.server.AppiumIOS;
import com.arrival.appium.server.AppiumIOSDefault;
import com.arrival.selenium.server.Hub;
import com.arrival.utilities.interfaces.IFAppiumServer;
import com.arrival.utilities.interfaces.IFConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class AppiumManager {
    private static final Logger log = LogManager.getLogger(AppiumManager.class);

    private IFConfig testSuiteConfigs;
    private JSONConfigReader configReader;
    private ArrayList<Path> pathList;
    private ArrayList<IFAppiumServer> appiumServersList = new ArrayList<>();
    private ArrayList<IFAppiumServer> appiumDefaultServersList = new ArrayList<>();
    private ArrayList<NodeConfig> nodeConfigList = new ArrayList<>();
    private Hub hub;


    public AppiumManager() {
    }


    public void ini() {
        iniHub();
        iniPathList();
        iniNodeConfig();
        iniAppiumServer();
    }

    private void iniHub() {
        if(testSuiteConfigs.getHubPort().isEmpty() && testSuiteConfigs.getHubServer().isEmpty())
            hub = new Hub();
        else
            hub = new Hub(testSuiteConfigs.getHubServer(), testSuiteConfigs.getHubPort());
    }

    private void iniPathList() {
      //  AppiumConfig appiumConfig = (AppiumConfig) testSuiteConfigs;

        if (testSuiteConfigs.getJsonConfigInUse()) {
            configReader = new JSONConfigReader(testSuiteConfigs.getJsonConfigPath());
        } else {
            configReader = new JSONConfigReader();
        }
        pathList = configReader.getPathList();
    }

    private void iniNodeConfig() {
        try {
            ArrayList<String> jsonConfigDataList = new ArrayList<String>();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Get Gson object and init NodeConfig Object
            for (Path path : pathList) {
                String jsonConfigDate = new String(Files.readAllBytes(path));
                NodeConfig node = gson.fromJson(jsonConfigDate, NodeConfig.class);

                jsonConfigDataList.add(jsonConfigDate);
                node.setConfigPath(path);
                nodeConfigList.add(node);
            }
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }

    private void iniAppiumServer() {
        appiumServersList = new ArrayList<>();
        String platform;
        String browserName;

        try {
            for (NodeConfig nodeConfig : nodeConfigList) {
                platform = nodeConfig.getSingelCapability().getPlatform();
                browserName = nodeConfig.getSingelCapability().getBrowserName();
                switch (platform) {
                    case "ANDROID":
                        AppiumAndroid android = new AppiumAndroid(nodeConfig);
                        appiumServersList.add(android);
                        break;
                    case "IOS":
                        if (! browserName.equalsIgnoreCase("safari")) {
                            AppiumIOS ios = new AppiumIOS(nodeConfig);
                            appiumServersList.add(ios);
                        } else {
                            AppiumIOS ios = new AppiumIOS(nodeConfig);
                            //TODO ist noch nicht fertig, bitte umsetzen
                            //ios.startIOSWebKitDebugProxy();
                            appiumServersList.add(ios);
                        }
                        break;
                    default:
                        log.error("No Server Class found for NodeConfig!");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void startDefaultANDSever() {
        AppiumAndroidDefault defaultAND = new AppiumAndroidDefault();
        appiumDefaultServersList.add(defaultAND);
        defaultAND.startServer();
        log.info(("Start a new Android default sever: " + defaultAND.toString()));
    }

    public  void stopDefaultANDServer() {
        for (IFAppiumServer tempServer : appiumDefaultServersList) {
            tempServer.stopServer();
            log.info("Stop a Android default sever: " + tempServer.toString());
        }
    }

    public  void startDefauotIOSServer() {
        AppiumIOSDefault defaultIOS = new AppiumIOSDefault();
        appiumDefaultServersList.add(defaultIOS);
        defaultIOS.startServer();
        log.info(("Start a new IOS default sever: " + defaultIOS.toString()));
    }

    public  void stopDefaultIOSServer() {
        for (IFAppiumServer tempServer : appiumDefaultServersList) {
            tempServer.stopServer();
            log.info("Stop a IOS default sever: " + tempServer.toString());
        }
    }

    //ToDO: for hub und AppiumServer do implement
    private boolean isLocalPortInUse(int port) {
        try {
            // ServerSocket try to open a LOCAL port
            new ServerSocket(port).close();
            // local port can be opened, it's available
            return false;
        } catch (IOException e) {
            // local port cannot be opened, it's in use
            return true;
        }
    }

    //ToDO: for hub und AppiumServer do implement
    private boolean isRemotePortInUse(String hostName, int portNumber) {
        try {
            // Socket try to open a REMOTE port
            new Socket(hostName, portNumber).close();
            // remote port can be opened, this is a listening port on remote machine
            // this port is in use on the remote machine !
            return true;
        } catch (Exception e) {
            // remote port is closed, nothing is running on
            return false;
        }
    }

    private void pauseSeverIni() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }

    /**
     *
     * @return the Configuration-Reader instance
     */

    public JSONConfigReader getConfigReader() {
        return configReader;
    }

    /**
     *
     * @param configReader set the Configuration Reader instance new
     */
    public void setConfigReader(JSONConfigReader configReader) {
        this.configReader = configReader;
    }

    /**
     *
     * @return a List of NodeConfig
     */
    public ArrayList<NodeConfig> getNodeConfigList() {
        return nodeConfigList;
    }

    /**
     *
     * @param nodeConfigList set the list of a Node-Configuration
     */
    public void setNodeConfigList(ArrayList<NodeConfig> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
    }

    /**
     *
     * @return the Configuration set for the Testsuite
     */
    public IFConfig getTestSuiteConfigs() {
        return testSuiteConfigs;
    }

    /**
     *
     * @param testSuiteConfigs Configuration for Testsuite
     */
    public void setTestSuiteConfigs(IFConfig testSuiteConfigs) {
        this.testSuiteConfigs = testSuiteConfigs;
    }

    /**
     * ***************************************************************************
     * ***************************************************************************
     * ***************************************************************************
     * ***************************************************************************
     * ***************************************************************************
     * ***************************************************************************
     * ***************************************************************************
     *****************************************************************************
     */
    public void startServer() {
        try {
            ini();
            hub.startHub();
            pauseSeverIni();

            for (IFAppiumServer tempServer : appiumServersList) {
                tempServer.startServer();
                log.info("Start a new Server: " + tempServer.toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    //Todo not done yet + implemnt
    public void startServerOnPort(Integer serverPort) {
        Integer port = 0;

        for (IFAppiumServer server : appiumServersList) {
            if (server instanceof AppiumAndroid) {
                //  AppiumAndroid inc = (AppiumAndroid) server.getSeverInstance();
                //port =  inc.getNodeConfig().getConfiguration().getPort();
            } else if (server instanceof AppiumIOS) {
                //  AppiumIOS inc = (AppiumIOS) server.getSeverInstance();
                //port = inc.getNodeConfig().getConfiguration().getPort();
            } else {
                log.error("Instance of port: " + serverPort + " not found.");
            }
            /*
            if(port == serverPort){
                server.stopServer();
            }else{
                log.error("Instance of port: " + serverPort + " not found.");
            }*/
        }
    }

    public void stopServer() {
        try {
            for (IFAppiumServer tempServer : appiumServersList) {
                tempServer.stopServer();
                log.info("Stop a Server: " + tempServer.toString());
            }
            pauseSeverIni();
            hub.stopHub();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void stopServerOnPort(Integer serverPort) {
        Integer port = 0;

        for (IFAppiumServer server : appiumServersList) {
            if (server instanceof AppiumAndroid) {
                AppiumAndroid inc = (AppiumAndroid) server;
                port = inc.getNodeConfig().getConfiguration().getPort();
            } else if (server instanceof AppiumIOS) {
                AppiumIOS inc = (AppiumIOS) server;
                port = inc.getNodeConfig().getConfiguration().getPort();
            } else {
                log.error("Instance of port: " + serverPort + " not found.");
            }

            if (port == serverPort) {
                server.stopServer();
            } else {
                log.error("Instance of port: " + serverPort + " not found.");
            }
        }
    }
}