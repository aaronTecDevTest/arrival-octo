package com.arrival.utilities.interfaces;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 14.08.2015.
 * @since: 1.0
 * Package: com.arrival.utilities.interfaces
 */
public interface IFConfig {
    String platform = null;
    Boolean parallelTesting = false;
    Integer parallelTestingCount = 0;

    String browserName = null;
    String serverName = null;
    String mobileTestingArt = null;
    Boolean jsonConfigInUse = false;
    String jsonConfigPath = null;

    String getPlatform();

    void setPlatform(String plattform);

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

    Integer getParallelTestingCount();

    void setParallelTestingCount(Integer parallelTestingCount);

     Boolean getJsonConfigInUse();

     void setJsonConfigInUse(Boolean jsonConfigInUse);
}
