package com.arrival.unit.generic;

import com.arrival.selenium.SeleniumManager;
import com.arrival.selenium.config.SeleniumConfig;
import com.arrival.utilities.interfaces.IFTestCase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    protected static final Logger log =  LogManager.getLogger(ArrivalWeb.class);

    public static SeleniumConfigSingleton seleniumConfigSingleton = SeleniumConfigSingleton.getInstance();
    public ArrayList<Object> seleniumServerList = new ArrayList<>();

    public SeleniumConfig seleniumConfig = seleniumConfigSingleton.getTestSuiteConfiguration();
    public SeleniumManager seleniumManager;
    private WebDriver browser;


    public WebDriver openBrowser(){
        seleniumManager.setSeleniumConfig(seleniumConfig);
        browser = seleniumManager.getBrowser(seleniumConfig);
        return browser;
    }

    public void closeBrowser(WebDriver driver){
        driver.close();
        driver.quit();
    }

    /**
     *Test NG method
     */
    @DataProvider(name = "driver" ,parallel = true)
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


    @BeforeClass
    public void setUpTestClass() {
        seleniumManager = new SeleniumManager();
        if (SeleniumConfigSingleton.getTestArt().equals(SeleniumConfigSingleton.MULTI)) {
            //Should be here for Appium and Selenium Grid config (Only Json config)
            /*
            seleniumManager.setSeleniumConfig(seleniumConfig);
            browser = seleniumManager.getBrowser(seleniumConfig);
            */

            seleniumServerList.add("android Test1");
            seleniumServerList.add("android Test2");
            seleniumServerList.add("ios Test1");
            seleniumServerList.add("ios Test2");
        } else {
            seleniumManager.setSeleniumConfig(seleniumConfig);
            browser = seleniumManager.getBrowser(seleniumConfig);
            seleniumServerList.add("android Default");
        }
    }

    /**
    *Other method
    */
    public void pauseTest(long milSeconds) {
        try{
            Thread.sleep(milSeconds);
        }catch (Exception e) {
            log.error(e.getStackTrace());
            log.error("Test was not paused!!");
        }
    }

    /*
     *Web general method (Selenium)
     */

    @Override
    public void click() {
        System.out.println("clicked");
    }

    @Override
    public void doppleClick() {

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
     *Testcase Properties
     */
    public int getTcID() {
        return tcID.get();
    }

    public void setTcID(int tcID) {
        IFTestCase.tcID.set(tcID);
    }

    public SimpleIntegerProperty tcIDProperty() {
        return tcID;
    }

    public String getTcName() {
        return tcName.get();
    }

    public void setTcName(String tcName) {
        IFTestCase.tcName.set(tcName);
    }

    public SimpleStringProperty tcNameProperty() {
        return tcName;
    }

    public String getTcDescription() {
        return tcDescription.get();
    }

    public void setTcDescription(String tcDescription) {
        IFTestCase.tcDescription.set(tcDescription);
    }

    public SimpleStringProperty tcDescriptionProperty() {
        return tcDescription;
    }

    public String getTcResult() {
        return tcResult.get();
    }

    public void setTcResult(String tcResult) {
        IFTestCase.tcResult.set(tcResult);
    }

    public SimpleStringProperty tcResultProperty() {
        return tcResult;
    }

    public String getTcLastRun() {
        return tcLastRun.get();
    }

    public void setTcLastRun(String tcLastRun) {
        IFTestCase.tcLastRun.set(tcLastRun);
    }

    public SimpleStringProperty tcLastRunProperty() {
        return tcLastRun;
    }

    public String getTcLink() {
        return tcLink.get();
    }

    public void setTcLink(String tcLink) {
        IFTestCase.tcLink.set(tcLink);
    }

    public SimpleStringProperty tcLinkProperty() {
        return tcLink;
    }

    public String getTcDuration() {
        return tcDuration.get();
    }

    public void setTcDuration(String tcDuration) {
        IFTestCase.tcDuration.set(tcDuration);
    }

    public SimpleStringProperty tcDurationProperty() {
        return tcDuration;
    }

    public SimpleStringProperty tcClassPackageProperty() {
        return tcClassPackage;
    }

    public void setTcClassPackage(String tcClassPackage) {
        IFTestCase.tcClassPackage.set(tcClassPackage);
    }
}