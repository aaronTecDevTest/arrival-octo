package com.arrival.appium;
/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
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

    private IFConfig testSuiteConfigs = null;

    private JSONConfigReader configReader;
    private ArrayList<Path> pathList = null;
    private  ArrayList<IFAppiumServer> appiumServersList = new ArrayList<>();
    private static ArrayList<IFAppiumServer> appiumDefaultServersList = new ArrayList<>();
    private ArrayList<NodeConfig> nodeConfigList = new ArrayList<>();

    private Hub hub;
    private final static String  HUBHOST = "localhost";
    private final static Integer  HUBPORT = 4444;



    AppiumManager() {
        iniHub(HUBHOST, HUBPORT);
        iniPathLis();
        iniNodeConfig();
        iniAppiumServer();
    }


    /**
     * @param hubHost
     * @param hubPort
     */
    AppiumManager(String hubHost, Integer hubPort) {
        iniHub(hubHost, hubPort);
        iniPathLis();
        iniNodeConfig();
        iniAppiumServer();
    }

    private void iniHub(String hubHost, Integer hubPort){
        hub = new Hub(hubHost, hubPort);
    }

    private void iniPathLis(){
        AppiumConfig appiumConfig = (AppiumConfig)testSuiteConfigs;

        if(appiumConfig.getJsonConfigInUse()) {
            configReader = new JSONConfigReader(appiumConfig.getJsonConfigPath());
        }
        else {
            configReader = new JSONConfigReader();
        }
        pathList = configReader.getPathList();
    }

    private void iniNodeConfig(){
        try {
            ArrayList<String> jsonConfigDataList = new ArrayList<>();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Get Gson object and init NodeConfig Object
            for (Path path: pathList) {
                String jsonConfigDate = new String(Files.readAllBytes(path));
                NodeConfig node = gson.fromJson(jsonConfigDate, NodeConfig.class);

                jsonConfigDataList.add(jsonConfigDate);
                node.setConfigPath(path);
                nodeConfigList.add(node);
            }
        } catch (IOException e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
        }
    }

    private void iniAppiumServer(){
        appiumServersList = new ArrayList<>();
        String platform;
        String browserName;

        try {
            for(NodeConfig nodeConfig : nodeConfigList) {
                platform = nodeConfig.getSingelCapability().getPlatform();
                browserName = nodeConfig.getSingelCapability().getBrowserName();
                switch(platform) {
                    case "ANDROID":
                        AppiumAndroid android = new AppiumAndroid(nodeConfig);
                        appiumServersList.add(android);
                        break;
                    case "IOS":
                        if (!browserName.equalsIgnoreCase("safari")) {
                            AppiumIOS ios = new AppiumIOS(nodeConfig);
                            appiumServersList.add(ios);
                        }else {
                            AppiumIOS ios = new AppiumIOS(nodeConfig);
                            //TODO ist noch nicht fertig, bitte umsetzen
                            //ios.startIOSWebKitDebugProxy();
                            appiumServersList.add(ios);
                        }
                        break;
                    default:
                        System.out.println("No Server Class found for NodeConfig!");
                        break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ToDO: for hub und AppiumServer do implement
    private boolean isLocalPortInUse(int port) {
        try {
            // ServerSocket try to open a LOCAL port
            new ServerSocket(port).close();
            // local port can be opened, it's available
            return false;
        } catch(IOException e) {
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
        } catch(Exception e) {
            // remote port is closed, nothing is running on
            return false;
        }
    }

    private void pauseSeverIni(){
        try {
            Thread.sleep(10000);
        }catch (Exception e) {

        }
    }

    public void stopAppiumServerOnPort(Integer serverPort){
        for (IFAppiumServer server: appiumServersList) {
            Integer port = 0;
            if (server.getSeverIntance() instanceof AppiumAndroid) {
                AppiumAndroid inc = (AppiumAndroid) server.getSeverIntance();
                port =  inc.getNodeConfig().getConfiguration().getPort();
            }
          /* else (server.getSeverIntance() instanceof AppiumIOS){
                AppiumIOS inc = (AppiumIOS) server.getSeverIntance();
                inc.toString();
             //   port = inc.getNodeConfig().getConfiguration().getPort();
            }*/

            if(port == serverPort){
                server.stopServer();
            }else{
                log.error("Instance of port: " + serverPort + " not found.");
            }
        }
    }


    public JSONConfigReader getConfigReader() {
        return configReader;
    }

    public void setConfigReader(JSONConfigReader configReader) {
        this.configReader = configReader;
    }

    public ArrayList<NodeConfig> getNodeConfigList() {
        return nodeConfigList;
    }

    public void setNodeConfigList(ArrayList<NodeConfig> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
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
        try{
            hub.startHub();
            pauseSeverIni();
            for(IFAppiumServer tempServer: appiumServersList){
                tempServer.startServer();
                log.info("XXXXXXX");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void stopServer(){
        try{
            for(IFAppiumServer tempServer: appiumServersList){
                tempServer.stopServer();
                log.info("XXXXXXX");
            }
            pauseSeverIni();
            hub.stopHub();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void startDefaultANDSever(){
        AppiumAndroidDefault defaultAND = new AppiumAndroidDefault();
        appiumDefaultServersList.add(defaultAND);
        defaultAND.startServer();
        log.info("XXXXXXX");
    }

    public static void stopDefaultANDServer(){
        for(IFAppiumServer tempServer:  appiumDefaultServersList){
            tempServer.stopServer();
            log.info("XXXXXXX");
        }
    }

    public static void startDefauotIOSServer(){
        AppiumIOSDefault defaultIOS = new AppiumIOSDefault();
        appiumDefaultServersList.add(defaultIOS);
        defaultIOS.startServer();
        log.info("XXXXXXX");
    }

    public static void stopDefaultIOSServer() {
        for(IFAppiumServer tempServer:  appiumDefaultServersList){
            tempServer.stopServer();
            log.info("XXXXXXX");
        }
    }
}
