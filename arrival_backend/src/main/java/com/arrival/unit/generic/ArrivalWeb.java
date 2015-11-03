package com.arrival.unit.generic;

import com.arrival.selenium.SeleniumSingleton;
import com.arrival.selenium.SeleniumManager;
import com.arrival.selenium.WebDriverManager;
import com.arrival.utilities.interfaces.IFConfig;
import com.arrival.utilities.interfaces.IFTestCase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

public abstract class ArrivalWeb implements IFTestCase, IFGenericWeb {
    protected static final Logger log = LogManager.getLogger(ArrivalWeb.class);

    public SeleniumSingleton seleniumConfigSingleton = SeleniumSingleton.getInstance();
    public SeleniumManager seleniumManager = seleniumConfigSingleton.getSeleniumManager();
    //public String server = seleniumManager.getTestSuiteConfigs().getServerName();
    public ArrayList<Object> seleniumServerList = new ArrayList<>();
    public WebDriver browser;

    /**
     * Testcase properties WebView
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
    public ArrivalWeb(){
        tcID = new SimpleIntegerProperty();
        tcName = new SimpleStringProperty();
        tcDescription = new SimpleStringProperty();
        tcDuration = new SimpleStringProperty();
        tcLastRun = new SimpleStringProperty();
        tcLink = new SimpleStringProperty();
        tcResult = new SimpleStringProperty();
        tcClassPackage = new SimpleStringProperty();
    }

    public void setWebDriver(WebDriver driver) {
        browser = driver;
    }

    /**
     * Test NG method
     */
    @DataProvider(name = "driver", parallel = true)
    public Object[][] createServer() {

        Object[][] server;
        int y = seleniumServerList.size();
        int x = 2;

        server = new Object[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (j == 0) {
                    server[i][j] = seleniumServerList.get(i);
                }
                if (j == 1) {
                    server[i][j] = i;
                }
            }
        }
        return server;
    }
/*
    @BeforeClass
    public void setUpTestClass() {
        System.out.println("hallo asdfasdfasdfas");
        seleniumManager.setUpSeleniumServerList();
        seleniumServerList = seleniumManager.getAppiumServerList();
    }

    @AfterClass
    public void closeBrowser() {
        seleniumManager.setDownSeleniumServerList();
       /*for (Object temp : seleniumServerList){
           ((WebDriver) temp).close();
           ((WebDriver) temp).quit();
        }
    }*/

    @BeforeClass
    public void setUpSeleniumServerList() {
        WebDriverManager webDriverManager = new WebDriverManager();
        IFConfig testSuiteConfigs = seleniumManager.getTestSuiteConfigs();
        WebDriver webDriver;

        if (SeleniumSingleton.getFramework().equals(SeleniumSingleton.ARRIVAL)) {
            //If Test should be start parallel
            if (testSuiteConfigs.getParallelTesting()) {
                for (int i = 0; i < testSuiteConfigs.getParallelTestingCount(); i++) {
                    webDriver = webDriverManager.setUpDriver(testSuiteConfigs);
                    seleniumServerList.add(webDriver);
                }

                if (!testSuiteConfigs.getServerName().contains("Non")) {
                    for (Object tempWebDriver : seleniumServerList) {
                        ((WebDriver) tempWebDriver).get(testSuiteConfigs.getServerName());
                    }
                }
            } else {
                webDriver = webDriverManager.setUpDriver(testSuiteConfigs);
                seleniumServerList.add(webDriver);
            }
        } else {
            webDriver = webDriverManager.setUpDriver(testSuiteConfigs);
            seleniumServerList.add(webDriver);
        }
    }

    @AfterClass
    public void setDownSeleniumServerList() {
        for (Object temp : seleniumServerList) {
            ((WebDriver) temp).close();
            ((WebDriver) temp).quit();
        }
    }

    /**
     * Other method
     */
    public void pauseTest(long milSeconds) {
        try {
            Thread.sleep(milSeconds);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            log.error("Test was not paused!!");
        }
    }

    /**
     * Web general method (Selenium)
     */

    @Override
    public void click() {
        System.out.println("clicked");
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

    /**
     * Testcase Properties
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