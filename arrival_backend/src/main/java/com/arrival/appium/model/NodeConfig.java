package com.arrival.appium.model;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 28.09.2015.
 * @since: 1.0
 * Package: com.arrival.appium.model
 */

import com.google.gson.annotations.SerializedName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Arrays;

public class NodeConfig {
    private static final Logger log = LogManager.getLogger(NodeConfig.class);
    private Path configPath;

    @SerializedName("capabilities")
    private Capabilities[] capabilities;
    private Configuration configuration;

    public Capabilities[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities[] capabilities) {
        this.capabilities = capabilities;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Capabilities getSingelCapability(){
        return capabilities[0];
    }

    public Path getConfigPath() {
        return configPath;
    }

    public void setConfigPath(Path configPath) {
        this.configPath = configPath;
    }

    @Override
    public String toString() {
        return "NodeConfig{" +
                "capabilities=" + Arrays.toString(capabilities) +
                ", configuration=" + configuration +
                '}';
    }
}
