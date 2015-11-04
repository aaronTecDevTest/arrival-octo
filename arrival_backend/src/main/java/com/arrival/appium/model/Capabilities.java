package com.arrival.appium.model;
/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 28.09.2015.
 * @since: 1.0
 * Package: com.arrival.appium.model
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Capabilities {
    private static final Logger log = LogManager.getLogger(Capabilities.class);
    private String browserName;
    private String version;
    private Integer maxInstances;
    private String platform;
    private String deviceName;
    private String udid;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getMaxInstances() {
        return maxInstances;
    }

    public void setMaxInstances(Integer maxInstances) {
        this.maxInstances = maxInstances;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Override
    public String toString() {
        return "Capabilities: {" +
                       ", browserName='" + browserName + '\'' +
                       ", version='" + version + '\'' +
                       ", maxInstances=" + maxInstances +
                       ", platform='" + platform + '\'' +
                       ", deviceName='" + deviceName + '\'' +
                       ", udid='" + udid + '\'' +
                       '}';
    }
}
