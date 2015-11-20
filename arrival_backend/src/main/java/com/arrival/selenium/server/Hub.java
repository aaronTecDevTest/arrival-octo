package com.arrival.selenium.server;


/**
 * @author Aaron Kutekidila
 * @version 1.0
 * Created on 31.05.2015.
 * @since 1.0
 * <p/>
 * <P>Hub</P>
 * This Class return a instance of SeleniumHub.
 * Also can start, stop and restart the Hub.
 */

import com.arrival.utilities.SystemPreferences;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.grid.internal.utils.GridHubConfiguration;

public class Hub {
    private static final Logger log = LogManager.getLogger(Hub.class.getName());

    private static final String HUBHOST = "127.0.0.1";
    private static final Integer HUBPORT = 4444;

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
    private int hubPort;


    /**
     * Standard construct to ini gridHubConfig, hubHost, hubPort and
     * osName.
     */
    public Hub() {
        gridHubConfig = new GridHubConfiguration();
        hubHost = HUBHOST;
        hubPort = HUBPORT;
        osName = SystemPreferences.getOsName();
        setUpHub();
        log.info("SeleniumHub created");
    }

    /**
     * Construct with ini parameter
     *
     * @param host @see hubHost
     * @param port @see hubPort
     */
    public Hub(String host, String port) {
        gridHubConfig = new GridHubConfiguration();
        hubHost = host;
        hubPort = Integer.valueOf(port);
        osName = SystemPreferences.getOsName();
        setUpHub();
        log.info("SeleniumHub created");
    }

   /*public static void main(String[] args) {
        Hub hubNode = new Hub("127.0.0.1","4444");

        hubNode.startHub();
        // hubNode.shutDownNodeAndHub();
        //hubNode.stopHub();
    }*/

    /**
     * Setup the Hub with GridHubConfig
     */
    private void setUpHub() {
        try {
            gridHubConfig.setHost(java.lang.String.valueOf(hubHost));
            gridHubConfig.setPort(hubPort);
            gridHubConfig.setTimeout(600000);
            hub = new org.openqa.grid.web.Hub(gridHubConfig);
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
    }

    /**
     * Start the hub with configured Host and Post
     */
    public void startHub() {
        try {
         /* if (hub != null) {
                hub.stop();
            }*/
            setUpHub();
            hub.start();
            log.info("Start the hub on: " + hubHost + " on port: " + hubPort + " successful!");
        } catch (Exception e) {
            log.error("Fail to start the hub on: " + hubHost + " on port: " + hubPort + ". " + e.getMessage());
        }
    }

    /**
     * Stop the hub on configured Host and Post
     */
    public void stopHub() {
        try {
            hub.stop();
            log.info("Stop the hub on: " + hubHost + " on port: " + hubPort + " successful!");
        } catch (Exception e) {
            log.error("Fail to stop the hub on: " + hubHost + " on port: " + hubPort);
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
