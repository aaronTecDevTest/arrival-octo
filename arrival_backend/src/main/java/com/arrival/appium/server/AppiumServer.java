package com.arrival.appium.server;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.10.2015.
 * @since: 1.0
 * Package: com.arrival.appium.server
 */
import com.arrival.appium.config.JSONConfigReader;
import com.arrival.appium.model.NodeConfig;
import com.arrival.selenium.server.Hub;
import com.arrival.utilities.interfaces.IFAppiumServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class AppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumServer.class);

    private JSONConfigReader configReader;
    private ArrayList<Path> pathList;
    private ArrayList<NodeConfig> nodeConfigList;
    private Hub hub;
    private ArrayList<IFAppiumServer> appiumServersList;


    public static void main(String[] args) throws IOException {

        /*AppiumManager manager = new AppiumManager();
        manager.startHubWithNode();
        try{
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        manager.stopHubWithNode();
        System.out.printf(manager.toString());*/
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
    }

    AppiumServer() {
        configReader = new JSONConfigReader();
        hub = new Hub();
        nodeConfigList = new ArrayList<>();

        pathList = configReader.getPathList();
        iniNodeConfig();
        iniAppiumServer();
    }


    /**
     * @param filePath
     * @param hubHost
     * @param hubPort
     */
    AppiumServer(String filePath, String hubHost, Integer hubPort) {
        configReader = new JSONConfigReader(filePath);
        hub = new Hub(hubHost, hubPort);
        nodeConfigList = new ArrayList<>();

        pathList = configReader.getPathList();
        iniNodeConfig();
        iniAppiumServer();
    }


    /**
     * @param platformName can be "IOS", "ANDROID"
     */
    AppiumServer(String platformName){
     /*   if(platformName.equalsIgnoreCase("android")) {
            AppiumAndroidDefault androidDefault = new AppiumAndroidDefault();
            androidDefault.startServer();
            appiumServersList.add(androidDefault);
        }
        else if(platformName.equalsIgnoreCase("ios")){
            AppiumIOSDefault iosDefault = new AppiumIOSDefault();
            iosDefault.startServer();
            appiumServersList.add(iosDefault);
        }
        else{
            System.out.println("PlatformName " + platformName + " not found! Only 'Android' or 'IOS' its alloy.");
        }*/
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
                            //AppiumIOS ios = new AppiumIOS(nodeConfig);
                            //appiumServersList.add(ios);
                        }else {
                            //AppiumIOS ios = new AppiumIOS(nodeConfig);
                            //ios.startIOSWebKitDebugProxy();
                            //appiumServersList.add(ios);
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

    //TODO: Start the Appium Server in deferrent tread.
    private void startHubWithNode(){
        try{
            hub.startHub();
            for(IFAppiumServer appiumServer:appiumServersList){
                appiumServer.startServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopHubWithNode(){
        try {
            for(IFAppiumServer appiumServer:appiumServersList){
                appiumServer.stopServer();
            }
            hub.stopHub();
        } catch (Exception e) {
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

    private void iniDefaultAndoridServer(){
        //AppiumAndroidDefault defaultAndroid = new AppiumAndroidDefault();
        //appiumServersList.add(defaultAndroid);
    }

    private void iniDefaultIOSServer(){
        //AppiumIOSDefault defaultIOS = new AppiumIOSDefault();
        //appiumServersList.add(defaultIOS);
    }

    public void stopAppiumServerOnPort(Integer serverPort){
        for (IFAppiumServer server: appiumServersList) {
            Integer port = 0;
            if (server.getInstance() instanceof AppiumAndroid) {
                AppiumAndroid inc = (AppiumAndroid) server.getInstance();
                port =  inc.getNodeConfig().getConfiguration().getPort();
            }
          /* else (server.getInstance() instanceof AppiumIOS){
                AppiumIOS inc = (AppiumIOS) server.getInstance();
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

    public void startDefaulAppiumServer(String platformName){
        if(platformName.equalsIgnoreCase("android")) {
         /*   AppiumAndroidDefault androidDefault = new AppiumAndroidDefault();
            androidDefault.startServer();
            appiumServersList.add(androidDefault);*/
        }
        else if(platformName.equalsIgnoreCase("ios")){
          /* AppiumIOSDefault iosDefault = new AppiumIOSDefault();
            iosDefault.startServer();
            appiumServersList.add(iosDefault);*/
        }
        else{
            log.error("PlatformName " + platformName + " not found! Only 'Android' or 'IOS' its alloy.");
        }
    }

    public void stopDefaulAppiumServer(){
        try {
            for(IFAppiumServer appiumServer: appiumServersList) {
                appiumServer.stopServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}