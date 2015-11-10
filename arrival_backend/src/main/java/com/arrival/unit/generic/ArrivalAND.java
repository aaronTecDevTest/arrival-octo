package com.arrival.unit.generic;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

import com.arrival.appium.AppiumManager;
import com.arrival.appium.AppiumSingleton;
import com.arrival.appium.MobilDriverManager;
import com.arrival.appium.model.NodeConfig;
import com.arrival.appium.server.AppiumAndroidDefault;
import com.arrival.utilities.SystemPreferences;
import com.arrival.utilities.interfaces.IFConfig;
import com.arrival.utilities.interfaces.IFTestCase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class ArrivalAND implements IFTestCase, IFGenericMobil {
    private static final Logger log = LogManager.getLogger(ArrivalAND.class);

    public AppiumSingleton appiumConfigSingleton = AppiumSingleton.getInstance();
    public AppiumManager appiumManager = appiumConfigSingleton.getAppiumManager();
    public ArrayList<NodeConfig> nodeConfigsList = appiumManager.getNodeConfigList();
    public ArrayList<Object> appiumServerList = new ArrayList<>();
    public AndroidDriver androidDriver;

    /**
     * Testcase properties Android
     **/
    private SimpleIntegerProperty tcID = null;
    private SimpleStringProperty tcName = null;
    private SimpleStringProperty tcDescription = null;
    private SimpleStringProperty tcDuration = null;
    private SimpleStringProperty tcLastRun = null;
    private SimpleStringProperty tcLink = null;
    private SimpleStringProperty tcResult = null;
    private SimpleStringProperty tcClassPackage = null;

    /**
     * Default Constructor
     */
    public ArrivalAND() {
        tcID = new SimpleIntegerProperty();
        tcName = new SimpleStringProperty();
        tcDescription = new SimpleStringProperty();
        tcDuration = new SimpleStringProperty();
        tcLastRun = new SimpleStringProperty();
        tcLink = new SimpleStringProperty();
        tcResult = new SimpleStringProperty();
        tcClassPackage = new SimpleStringProperty();
    }

    public void setWebDriver(AndroidDriver driver) {
        androidDriver = driver;
    }

    /*
     *Test NG method
     */
    @DataProvider(name = "driver", parallel = true)
    public Object[][] createServer() {

        Object[][] server;
        int y = appiumServerList.size();
        int x = 2;

        server = new Object[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (j == 0) {
                    server[i][j] = appiumServerList.get(i);
                }
                if (j == 1) {
                    server[i][j] = i;
                }
            }
        }
        return server;
    }

    @BeforeClass
    public void setUpAppiumServerList() {
        MobilDriverManager mobilDriverManager = new MobilDriverManager();
        IFConfig appiumConfig = appiumManager.getTestSuiteConfigs();
        AppiumDriver androidDriver;

        if (AppiumSingleton.getFramework().equals(AppiumSingleton.ARRIVAL)) {
            //If Test should be start parallel
            if (appiumConfig.getParallelTesting()) {
                //If Json is in use
                if( appiumConfig.getJsonConfigInUse()) {
                    for (NodeConfig tempNodeConfig : nodeConfigsList){
                        androidDriver = mobilDriverManager.setUpDriver(appiumConfig, tempNodeConfig);
                        appiumServerList.add(androidDriver);
                    }
                }else {
                    //Todo:
                    //androidDriver = mobilDriverManager.setUpDriver(appiumConfig, nodeConfigsList.get(0));
                    //appiumServerList.add(androidDriver);
                }
            } else {
                //Todo: Find out how to shoot down the @DataProvider (parallel = false)

                //If Json is in use
                if(appiumConfig.getJsonConfigInUse()) {
                    for (NodeConfig tempNodeConfig : nodeConfigsList){
                        androidDriver = mobilDriverManager.setUpDriver(appiumConfig, tempNodeConfig);
                        appiumServerList.add(androidDriver);
                    }
                }else {
                    //Todo:
                    //androidDriver = mobilDriverManager.setUpDriver(appiumConfig, nodeConfigsList.get(0));
                    //appiumServerList.add(androidDriver);
                }
            }
        }else{
            androidDriver = mobilDriverManager.setUpDriver(appiumConfig, nodeConfigsList.get(0));
            appiumServerList.add(androidDriver);
        }
}

    @AfterClass
    public void setDownAppiumServerList() {
        if (AppiumSingleton.getFramework().equals(AppiumSingleton.ARRIVAL)) {
            for (Object temp : appiumServerList) {
                ((AppiumDriver) temp).close();
                ((AppiumDriver) temp).quit();
            }
        }
    }

    /*
    *Other method
    */
    public void pauseTest(long milSec) {

    }

    /*
     *Mobile general method
     */

    @Override
    public void click() {

    }

    @Override
    public void doubleClick() {

    }

    @Override
    public void press() {

    }

    @Override
    public void longPress() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void scrollUp() {

    }

    @Override
    public void scrollDown() {

    }

    @Override
    public void zoomIn() {

    }

    @Override
    public void zoomOut() {

    }

    @Override
    public void takeScreenShot() {

    }

    /*
   *Testcase Properties
   */
    public int getTcID() {
        return tcID.get();
    }

    public void setTcID(int tcID) {
        this.tcID.set(tcID);
    }

    public SimpleIntegerProperty tcIDProperty() {
        return tcID;
    }

    public String getTcName() {
        return tcName.get();
    }

    public void setTcName(String tcName) {
        this.tcName.set(tcName);
    }

    public SimpleStringProperty tcNameProperty() {
        return tcName;
    }

    public String getTcDescription() {
        return tcDescription.get();
    }

    public void setTcDescription(String tcDescription) {
        this.tcDescription.set(tcDescription);
    }

    public SimpleStringProperty tcDescriptionProperty() {
        return tcDescription;
    }

    public String getTcResult() {
        return tcResult.get();
    }

    public void setTcResult(String tcResult) {
        this.tcResult.set(tcResult);
    }

    public SimpleStringProperty tcResultProperty() {
        return tcResult;
    }

    public String getTcLastRun() {
        return tcLastRun.get();
    }

    public void setTcLastRun(String tcLastRun) {
        this.tcLastRun.set(tcLastRun);
    }

    public SimpleStringProperty tcLastRunProperty() {
        return tcLastRun;
    }

    public String getTcLink() {
        return tcLink.get();
    }

    public void setTcLink(String tcLink) {
        this.tcLink.set(tcLink);
    }

    public SimpleStringProperty tcLinkProperty() {
        return tcLink;
    }

    public String getTcDuration() {
        return tcDuration.get();
    }

    public void setTcDuration(String tcDuration) {
        this.tcDuration.set(tcDuration);
    }

    public SimpleStringProperty tcDurationProperty() {
        return tcDuration;
    }

    public SimpleStringProperty tcClassPackageProperty() {
        return tcClassPackage;
    }

    public void setTcClassPackage(String tcClassPackage) {
        this.tcClassPackage.set(tcClassPackage);
    }
}