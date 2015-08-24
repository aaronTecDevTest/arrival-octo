package com.arrival.selenium.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.grid.internal.utils.GridHubConfiguration;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 31.05.2015.
 * @since 1.0
 * <p>
 * <P>Hub</P>
 * This Class return a instance of SeleniumHub.
 * Also can start, stop and restart the Hub.
 */
public class Hub {
    private static final Logger log = LogManager.getLogger(Hub.class.getName());
    /**
     * @param osName: Operation-System Name like "Mac OS", "Windows xxx" or "Linux xx"
     */
    private String osName;
    /**
     * @param gridHubConfig: @see GridHubConfiguration
     */
    private GridHubConfiguration gridHubConfig;
    /**
     * @param hub: @see org.openqa.grid.web.Hub
     */
    private org.openqa.grid.web.Hub hub;
    /**
     * @param hubHost: Hostname oder IP-Address where to run the SeleniumHub
     */
    private String hubHost;
    /**
     * @param hubHost: Port where to run the SeleniumHub
     */
    private Integer hubPort;


    /**
     * Standard construct to ini gridHubConfig, hubHost, hubPort and
     * osName.
     */
    public Hub() {
        log.info("Creating SeleniumHb");
        gridHubConfig = new GridHubConfiguration();
        hubHost = "localhost";
        hubPort = 4444;
        osName = System.getProperty("os.name");
        setUpHub();
        log.info("SeleniumHb created");
    }

    /**
     * Construct with ini parameter
     *
     * @param host @see hubHost
     * @param port @see hubPort
     */
    public Hub(String host, Integer port) {
        gridHubConfig = new GridHubConfiguration();
        hubHost = host;
        hubPort = port;
        osName = System.getProperty("os.name");
        setUpHub();
    }

    public static void main(String[] args) {
        Hub hubNode = new Hub();

        hubNode.startHub();
        // hubNode.shutDownNodeAndHub();
    }

    /**
     * Setup the Hub with GridHubConfig
     */
    private void setUpHub() {
        try {
            gridHubConfig.setHost(hubHost);
            gridHubConfig.setPort(hubPort);
            hub = new org.openqa.grid.web.Hub(gridHubConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start the hub with configured Host and Post
     */
    public void startHub() {
        try {
            //todo: Find out how to stop the sever if its running.
            /*if (hub != null)
                this.stopHub();*/

            hub.start();
            log.info("Start the hub on: " + hubHost + " on port: " + hubPort);
        } catch (Exception e) {
            log.error("Fail to start the hub on: " + hubHost + " on port: " + hubPort);
            log.warn("Host: " + hubHost + " on port: " + hubPort + " all ready in use!");
            //e.printStackTrace();
        }
    }

    /**
     * Stop the hub on configured Host and Post
     */
    public void stopHub() {
        try {
            hub.stop();
            log.info("Stop the hub on: " + hubHost + " on port: " + hubPort);
        } catch (Exception e) {
            log.error("Fail to stop the hub on: " + hubHost + " on port: " + hubPort);
            e.printStackTrace();
        }
    }

    /**
     * Restart the hub with configured/reset Host and Post
     */
    public void restartHub() {
        try {
            hub.stop();
            hub.start();
            log.info("Restart the hub on: " + hubHost + " on port: " + hubPort);
        } catch (Exception e) {
            log.error("Fail to restart the hub on: " + hubHost + " on port: " + hubPort);
            e.printStackTrace();
        }
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public GridHubConfiguration getGridHubConfig() {
        return gridHubConfig;
    }

    public void setGridHubConfig(GridHubConfiguration gridHubConfig) {
        this.gridHubConfig = gridHubConfig;
    }

    public org.openqa.grid.web.Hub getHub() {
        return hub;
    }

    public void setHub(org.openqa.grid.web.Hub hub) {
        this.hub = hub;
    }

    public String getHubHost() {
        return hubHost;
    }

    public void setHubHost(String hubHost) {
        this.hubHost = hubHost;
    }

    public Integer getHubPort() {
        return hubPort;
    }

    public void setHubPort(Integer hubPort) {
        this.hubPort = hubPort;
    }
}