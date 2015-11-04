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

public class Configuration {
    private static final Logger log = LogManager.getLogger(Configuration.class);
    private Integer cleanUpCycle;
    private Integer timeout;
    private String proxy;
    private String url;
    private String host;
    private Integer port;
    private Integer maxSession;
    private boolean register;
    private Integer registerCycle;
    private Integer hubPort;
    private String hubHost;

    public Integer getCleanUpCycle() {
        return cleanUpCycle;
    }


    public void setCleanUpCycle(Integer cleanUpCycle) {
        this.cleanUpCycle = cleanUpCycle;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(Integer maxSession) {
        this.maxSession = maxSession;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public Integer getRegisterCycle() {
        return registerCycle;
    }

    public void setRegisterCycle(Integer registerCycle) {
        this.registerCycle = registerCycle;
    }

    public Integer getHubPort() {
        return hubPort;
    }

    public void setHubPort(Integer hubPort) {
        this.hubPort = hubPort;
    }

    public String getHubHost() {
        return hubHost;
    }

    public void setHubHost(String hubHost) {
        this.hubHost = hubHost;
    }

    @Override
    public String toString() {
        return "Configuration: {" +
                       ", cleanUpCycle=" + cleanUpCycle +
                       ", timeout=" + timeout +
                       ", proxy='" + proxy + '\'' +
                       ", url='" + url + '\'' +
                       ", host='" + host + '\'' +
                       ", port=" + port +
                       ", maxSession=" + maxSession +
                       ", register=" + register +
                       ", registerCycle=" + registerCycle +
                       ", hubPort=" + hubPort +
                       ", hubHost='" + hubHost + '\'' +
                       '}';
    }
}
