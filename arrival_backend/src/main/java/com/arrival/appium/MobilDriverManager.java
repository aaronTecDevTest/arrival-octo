package com.arrival.appium;


/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.10.2015.
 * @since: 1.0
 * Package: com.arrival.appium
 */

import com.arrival.appium.config.AppiumConfig;
import com.arrival.appium.model.Capabilities;
import com.arrival.appium.model.NodeConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class MobilDriverManager {
    private static final Logger log = LogManager.getLogger(MobilDriverManager.class);

    AppiumConfig appiumConfig;
    NodeConfig nodeConfig ;

    public MobilDriverManager() {
        appiumConfig = null;
        nodeConfig = null;
    }

    public AppiumDriver setUpDriver(AppiumConfig runningConfiguration, NodeConfig nodeConfig) {
        log.debug("Setting up AppiumDriver");

        AppiumDriver driver = null;
        this.appiumConfig = runningConfiguration;
        this.nodeConfig = nodeConfig;

        String platform = appiumConfig.getPlatform();

        switch (platform) {
            case "IOS":
                if(appiumConfig.getJsonConfigInUse())
                    driver = setUpIOS();
                else
                    driver = setIOSDefault();
                break;

            case "Android":
                if(appiumConfig.getJsonConfigInUse())
                    driver = setUpAndroid();
                else
                    driver = setAndroidDefault();
                break;
        }
        return driver;
    }


    private AndroidDriver setUpAndroid( ) {
        Capabilities tempCap = nodeConfig.getSingelCapability();

        try {
            //File classpathRoot = new File(System.getProperty("user.dir"));
            //File appDir = new File(classpathRoot, "/ContactManager");
            //File app = new File(appDir, ".apk");
            File app = new File(appiumConfig.getAppFilePath());

            URL url = new URL("http://"
                                      + nodeConfig.getConfiguration().getHubHost() + ":"
                                      + nodeConfig.getConfiguration().getHubPort() + "/wd/hub"
            );

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, tempCap.getBrowserName());
            capabilities.setCapability(CapabilityType.PLATFORM, tempCap.getPlatform());
            capabilities.setCapability(CapabilityType.VERSION, tempCap.getVersion());
            capabilities.setCapability("udid", tempCap.getUdid());
            capabilities.setCapability("deviceName", tempCap.getDeviceName());
            capabilities.setCapability("app", app.getAbsolutePath());
            if(appiumConfig.getMobileTestingArt().contains("Mobile Web")){
                capabilities.setCapability("autoWebview", true);
            } else{
                capabilities.setCapability("appPackage", appiumConfig.getPackageBundleID());
                //capabilities.setCapability("appActivity", ".ContactManage");
            }
            return new AndroidDriver(url, capabilities);
        } catch (MalformedURLException e) {
            log.error(e.toString());
        }
        return null;
    }


    private IOSDriver setUpIOS( ) {
        Capabilities tempCap = nodeConfig.getSingelCapability();

        try {
            //File classpathRoot = new File(System.getProperty("user.dir"));
            //File appDir = new File(classpathRoot, "/ContactManager");
            //File app = new File(appDir, "ContactManager.apk");
            File app = new File(appiumConfig.getAppFilePath());

            URL url = new URL("http://"
                                      + nodeConfig.getConfiguration().getHubHost() + ":"
                                      + nodeConfig.getConfiguration().getHubPort() + "/wd/hub"
            );

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, tempCap.getBrowserName());
            capabilities.setCapability(CapabilityType.PLATFORM, tempCap.getPlatform());
            capabilities.setCapability(CapabilityType.VERSION, tempCap.getVersion());
            capabilities.setCapability("udid", tempCap.getUdid());
            capabilities.setCapability("deviceName", tempCap.getDeviceName());
            capabilities.setCapability("app", app.getAbsolutePath());
            if(appiumConfig.getMobileTestingArt().contains("Mobile Web")){
                capabilities.setCapability("autoWebview", true);
            } else{
                capabilities.setCapability("bundleId", appiumConfig.getPackageBundleID());
                //capabilities.setCapability("appActivity", ".ContactManage");
            }
            return new IOSDriver(url, capabilities);
        } catch (MalformedURLException e) {
            log.error(e.toString());
        }
        return null;
    }


    private AndroidDriver setAndroidDefault(){
        return null;
    }


    private IOSDriver setIOSDefault(){
        return null;
    }
}
