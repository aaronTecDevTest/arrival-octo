package com.arrival.selenium.config;

import com.arrival.utilities.interfaces.IFConfig;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.avalon.excalibur.logger.LoggerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 13.08.2015.
 * @since: 1.0
 * Package: com.arrival.selenium.config
 */
public class SeleniumConfig implements IFConfig {
    private static final Logger log = LogManager.getLogger(SeleniumConfig.class.getName());

    private String platform = null;
    private Boolean parallelTesting = false;
    private Integer parallelTestingCount = 0;

    private String browserName = null;
    private String serverName = null;
    private String mobileTestingArt = null;

    private String jsonConfigPath = null;


    public void SeleniumConfig(){

    }

    public void runConfig() {
    }

    @Override
    public String toString() {
        return super.toString();
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

    public Integer getParallelTestingCount() {
        return parallelTestingCount;
    }

    public void setParallelTestingCount(Integer parallelTestingCount) {
        this.parallelTestingCount = parallelTestingCount;
    }
}
