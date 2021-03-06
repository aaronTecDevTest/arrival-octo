package com.arrival.selenium.config;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 13.08.2015.
 * @since: 1.0
 * Package: com.arrival.selenium.config
 */

import com.arrival.utilities.interfaces.IFConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeleniumConfig implements IFConfig {
    private static final Logger log = LogManager.getLogger(SeleniumConfig.class.getName());

    private String platform = null;
    private Boolean parallelTesting = false;
    private Integer parallelTestingCount = 0;
    private String saveResultPath = null;


    private String browserName = null;
    private String serverName = null;

    private String mobilePlatform = null;
    private String mobileTestingArt = null;
    private String hubServer = null;
    private String hubPort = null;
    private String appFilePath = null;
    private String packageBundleID = null;


    private Boolean jsonConfigInUse = false;
    private String jsonConfigPath = null;

    public SeleniumConfig() {
        browserName = "CH";
        serverName = "";
    }

    @Override
    public String toString() {
        return platform + "->" + parallelTesting.toString() + "->" + parallelTestingCount + "->" + browserName + "->" + serverName
                       + "->" + mobileTestingArt + "->" + jsonConfigInUse.toString() + "->" + jsonConfigPath;
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getJsonConfigPath() {
        return jsonConfigPath;
    }

    public void setJsonConfigPath(String jsonConfigPath) {
        this.jsonConfigPath = jsonConfigPath;
    }

    public String getMobileTestingArt() {
        return mobileTestingArt;
    }

    public void setMobileTestingArt(String mobileTestingArt) {
        this.mobileTestingArt = mobileTestingArt;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public Boolean getParallelTesting() {
        return parallelTesting;
    }

    public void setParallelTesting(Boolean parallelTesting) {
        this.parallelTesting = parallelTesting;
    }

    public Integer getParallelThreadCounter() {
        return parallelTestingCount;
    }

    public void setParallelThreadCounter(Integer parallelTestingCount) {
        this.parallelTestingCount = parallelTestingCount;
    }

    public Boolean getJsonConfigInUse() {
        return jsonConfigInUse;
    }

    public void setJsonConfigInUse(Boolean jsonConfigInUse) {
        this.jsonConfigInUse = jsonConfigInUse;
    }

    public String getSaveResultPath() {
        return saveResultPath;
    }

    public void setSaveResultPath(String saveResultPath) {
        this.saveResultPath = saveResultPath;
    }

    public String getMobilePlatform() {
        return mobilePlatform;
    }

    public void setMobilePlatform(String mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }

    public String getAppFilePath() {
        return appFilePath;
    }

    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath;
    }

    public String getPackageBundleID() {
        return packageBundleID;
    }

    public void setPackageBundleID(String packageBundleID) {
        this.packageBundleID = packageBundleID;
    }


    public String getHubPort() {
        return hubPort;
    }


    public void setHubPort(String hubPort) {
        this.hubPort = hubPort;
    }


    public String getHubServer() {
        return hubServer;
    }


    public void setHubServer(String hubServer) {
        this.hubServer = hubServer;
    }
}
