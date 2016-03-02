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
import com.arrival.utilities.ArrivalResult;
import com.arrival.utilities.interfaces.IFAppiumServer;
import com.arrival.utilities.interfaces.IFConfig;
import com.arrival.utilities.interfaces.IFTestCase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public abstract class ArrivalIOS implements IFTestCase, IFGenericMobil {
    private static final Logger log = LogManager.getLogger(ArrivalIOS.class);

    private AppiumSingleton appiumSingleton = AppiumSingleton.getInstance();
    private AppiumManager appiumManager = AppiumSingleton.getAppiumManager();
    private ArrayList<NodeConfig> nodeConfigsList = appiumManager.getNodeConfigList();
    private ArrayList<Object> appiumDriverList = new ArrayList<>();
    protected IOSDriver iosDriver;

    /**
     * Testcase properties IOS
     **/
    private SimpleIntegerProperty tcID = null;
    private SimpleStringProperty tcName = null;
    private SimpleStringProperty tcDescription = null;
    private SimpleStringProperty tcDuration = null;
    private SimpleStringProperty tcLastRun = null;
    private SimpleStringProperty tcLink = null;
    private SimpleStringProperty tcResult = null;
    private SimpleStringProperty tcClassPackage = null;
    private SimpleObjectProperty<ImageView> tcResultIcons = null;

    /**
     * Default Constructor
     */
    public ArrivalIOS() {
        tcID = new SimpleIntegerProperty();
        tcName = new SimpleStringProperty();
        tcDescription = new SimpleStringProperty();
        tcDuration = new SimpleStringProperty();
        tcLastRun = new SimpleStringProperty();
        tcLink = new SimpleStringProperty();
        tcResult = new SimpleStringProperty();
        tcClassPackage = new SimpleStringProperty();
        tcResultIcons = new SimpleObjectProperty<ImageView>();
    }


    /**
     *
     * @param driver auto run from TestNG, fist Test and ini the iosDriver for future Tests
     **/
    @Test(dataProvider = "driver", priority = 1)
    public void setUpDriver(IOSDriver driver, Integer id){
        iosDriver = driver;
    }

    /*public void setWebDriver(IOSDriver driver) {
        iosDriver = driver;
    }*/

    /**
     *Test NG method
     */
    @DataProvider(name = "driver", parallel = true)
    public Object[][] createServer() {

        Object[][] server;
        int y = appiumDriverList.size();
        int x = 2;

        server = new Object[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (j == 0) {
                    server[i][j] = appiumDriverList.get(i);
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
        ArrayList<IFAppiumServer> appiumServersList = appiumManager.getAppiumServersList();

        AppiumDriver iosDriver;

        if (AppiumSingleton.getFramework().equals(AppiumSingleton.ARRIVAL)) {
            //If Test should be start parallel
            if (appiumConfig.getParallelTesting()) {
                //If Json is in use
                if( appiumConfig.getJsonConfigInUse()) {


                      /* for (NodeConfig tempNodeConfig : nodeConfigsList){
                        androidDriver = mobilDriverManager.setUpDriver(appiumConfig, tempNodeConfig);
                        appiumDriverList.add(androidDriver);
                    }*/

                    for (IFAppiumServer tempServer : appiumServersList){
                        iosDriver = mobilDriverManager.setUpDriver(tempServer, appiumConfig);
                        appiumDriverList.add(iosDriver);
                    }
                }else {
                    //Todo:
                    //androidDriver = mobilDriverManager.setUpDriver(appiumConfig, nodeConfigsList.get(0));
                    //appiumDriverList.add(androidDriver);
                }
            } else {
                //Todo: Find out how to shoot down the @DataProvider (parallel = false)
                //If Json is in use
                if(appiumConfig.getJsonConfigInUse()) {
                    /*for (NodeConfig tempNodeConfig : nodeConfigsList){
                        androidDriver = mobilDriverManager.setUpDriver(appiumManager,appiumConfig, tempNodeConfig);
                        appiumDriverList.add(androidDriver);
                    }*/

                    for (IFAppiumServer tempServer : appiumServersList){
                        iosDriver = mobilDriverManager.setUpDriver(tempServer, appiumConfig);
                        appiumDriverList.add(iosDriver);
                    }
                }else {
                    //Todo:
                    //androidDriver = mobilDriverManager.setUpDriver(appiumConfig, nodeConfigsList.get(0));
                    //appiumDriverList.add(androidDriver);
                }
            }
        }else{
            //Start DefaultServer
            appiumManager = new AppiumManager();
            appiumManager.startDefaultANDSever();

            //Get ServerDriver
            iosDriver = MobilDriverManager.setAndroidDefault();
            appiumDriverList.add(iosDriver);
        }
    }


    @AfterClass
    public void setDownAppiumServerList() {
        for (Object temp : appiumDriverList) {
            ((AppiumDriver) temp).close();
            ((AppiumDriver) temp).quit();
        }

        //Stop DefaultServer
        if (!AppiumSingleton.getFramework().equals(AppiumSingleton.ARRIVAL)) {
            appiumManager.stopDefaultANDServer();
        }
    }

    /**
     *
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log.info("Test: FAILURE");
            this.setTcResult(ArrivalResult.FAILED);

        }

        if (result.getStatus() == ITestResult.SUCCESS) {
            log.info("Test: SUCCESS");
            this.setTcResult(ArrivalResult.PASSED);

        }

        if (result.getStatus() == ITestResult.SKIP) {
            log.info("Test: SKIP");
            this.setTcResult(ArrivalResult.SKIPPED);

        }
    }

    /**
    *Other method
    */
    public void pauseTest(long milSeconds) {
        try {
            Thread.sleep(milSeconds);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            log.error("Test was not paused!!");
        }
    }

    public void waitPageLoading(long seconds){
        try {
            iosDriver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            log.error("Test was not waiting!!");
        }
    }

    /*
    *Web general method (Selenium)
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

    public void setTcResult(ArrivalResult tcResult) {
        this.tcResult.set(tcResult.toString());
        this.setTcResultIcons(getResultImageViewer(tcResult.toString()));
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

    public ImageView getTcResultIcons() {
        return tcResultIcons.get();
    }

    public void setTcResultIcons(ImageView tcResultIcons) {
        this.tcResultIcons = new SimpleObjectProperty<ImageView>(tcResultIcons);
    }

    public ImageView getResultImageViewer(String tcResult){
        ImageView imageView = new ImageView();

        switch (tcResult){
            case "PASSED":
                imageView.setImage(new Image(getClass().getResource("/icons/passed.png").toString()));
                return  imageView;
            case "FAILED":
                imageView.setImage(new Image(getClass().getResource("/icons/failed.png").toString()));
                return  imageView;
            case "SKIPPED":
                imageView.setImage(new Image(getClass().getResource("/icons/skipped.png").toString()));
                return  imageView;
            default:
                imageView.setImage(new Image(getClass().getResource("/icons/default.png").toString()));
                return  imageView;
        }
    }
}