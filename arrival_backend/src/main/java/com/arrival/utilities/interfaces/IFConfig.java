package com.arrival.utilities.interfaces;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 14.08.2015.
 * @since: 1.0
 * Package: com.arrival.utilities.interfaces
 */

public interface IFConfig {
    String getPlatform();

    void setPlatform(String platform);

    String getJsonConfigPath();

    void setJsonConfigPath(String jsonConfigPath);

    String getMobileTestingArt();

    void setMobileTestingArt(String mobileTestingArt);

    String getServerName();

    void setServerName(String serverName);

    String getBrowserName();

    void setBrowserName(String browserName);

    Boolean getParallelTesting();

    void setParallelTesting(Boolean parallelTesting);

    Integer getParallelThreadCounter();

    void setParallelThreadCounter(Integer parallelTestingCount);

    Boolean getJsonConfigInUse();

    void setJsonConfigInUse(Boolean jsonConfigInUse);

    String getSaveResultPath();

    void setSaveResultPath(String saveResultPath);

    String getMobilePlatform();

    void setMobilePlatform(String mobilePlatform);

    String getAppFilePath();

    void setAppFilePath(String appFilePath);

    String getPackageBundleID();

    void setPackageBundleID(String packageBundleID);

    String getHubServer();

    void setHubServer(String hubServer);

    String getHubPort();

    void setHubPort(String hubPort);
}
