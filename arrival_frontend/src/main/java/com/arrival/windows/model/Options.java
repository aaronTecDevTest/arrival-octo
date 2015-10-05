package com.arrival.windows.model;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 14.08.2015.
 * @since: 1.0
 * Package: com.arrival.windows.model
 */

import com.arrival.utilities.interfaces.IFConfig;

public class Options implements IFConfig {
    private String platform = null;
    private Boolean parallelTesting = false;
    private Integer parallelTestingCount = 0;

    private String browserName = null;
    private String serverName = null;
    private String mobileTestingArt = null;
    private Boolean jsonConfigInUse = false;
    private String jsonConfigPath = null;
    private String saveResultPath = null;

    @Override
    public String toString() {
        return platform + "->" + parallelTesting.toString() + "->" + parallelTestingCount + "->" + browserName + "->" + serverName
                + "->" + mobileTestingArt + "->" +jsonConfigInUse.toString() + "->" +jsonConfigPath + "->" + saveResultPath;
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
}
