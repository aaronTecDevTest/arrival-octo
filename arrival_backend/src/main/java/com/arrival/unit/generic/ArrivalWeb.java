package com.arrival.unit.generic;

import com.arrival.selenium.SeleniumManager;
import com.arrival.selenium.SeleniumSingleton;
import com.arrival.selenium.WebDriverManager;
import com.arrival.utilities.ArrivalResult;
import com.arrival.utilities.SystemPreferences;
import com.arrival.utilities.interfaces.IFConfig;
import com.arrival.utilities.interfaces.IFTestCase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.generic
 */

public abstract class ArrivalWeb implements IFTestCase, IFGenericWeb {
    protected static final Logger log = LogManager.getLogger(ArrivalWeb.class);

    private SeleniumSingleton seleniumConfigSingleton = SeleniumSingleton.getInstance();
    private SeleniumManager seleniumManager = SeleniumSingleton.getSeleniumManager();
    private ArrayList<Object> seleniumServerList = new ArrayList<>();
    protected WebDriver browser;

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
    public ArrivalWeb() {
        tcID = new SimpleIntegerProperty();
        tcName = new SimpleStringProperty();
        tcDescription = new SimpleStringProperty();
        tcDuration = new SimpleStringProperty();
        tcLastRun = new SimpleStringProperty();
        tcLink = new SimpleStringProperty();
        tcResult = new SimpleStringProperty();
        tcClassPackage = new SimpleStringProperty();
    }

    /**
     *
     * @param driver auto run from TestNG, fist Test and ini the browser for future Tests
     **/
    @Test(dataProvider = "driver", groups = {"fast"}, priority = 1)
    public void setUpDriver(WebDriver driver, Integer id){
        browser = driver;
    }

    /*
    public void setWebDriver(WebDriver driver) {
        browser = driver;
    }*/

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

    @BeforeClass
    public void setUpSeleniumServerList() {
        WebDriverManager webDriverManager = new WebDriverManager();
        IFConfig seleniumConfig =  seleniumManager.getTestSuiteConfigs();
        WebDriver webDriver;

        //If Arrival GUI is running
        if (SeleniumSingleton.getFramework().equals(SeleniumSingleton.ARRIVAL)) {
            //If Test should be start parallel
            if (seleniumConfig.getParallelTesting()) {
                ResourceBundle bundle = SystemPreferences.getResourceBundle("arrivalOptions");
                String[] optionWebBrowser =  (bundle.getString("tab.web.browser").split(","));

                //Setup the first three driver from arrivalOptions (Properties)
                for (int i = 0; i<3; i++){
                    seleniumConfig.setBrowserName(optionWebBrowser[i]);
                    webDriver = webDriverManager.setUpDriver(seleniumConfig);
                    seleniumServerList.add(webDriver);
                }

                if (!seleniumConfig.getServerName().equals("Non")) {
                    for (Object tempWebDriver : seleniumServerList) {
                        ((WebDriver) tempWebDriver).get(seleniumConfig.getServerName());
                    }
                }
            } else {
                webDriver = webDriverManager.setUpDriver(seleniumConfig);
                seleniumServerList.add(webDriver);
            }
        } else {
            webDriver = webDriverManager.setUpDriver(seleniumConfig);
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
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @BeforeSuite
    public void setUpSeleniumConfig() {
       if(seleniumConfigSingleton.isArrival()){
        //seleniumManager.setUpSeleniumServerList();
       }
    }

    /**
     * Code will be run only if the SeleniumTestSuite was instanced
     */
    @AfterSuite
    public void cleanSeleniumConfig() {
       if(seleniumConfigSingleton.isArrival()) {
           //seleniumManager.setDownSeleniumServerList();
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

    public void waitPageLoading(long seconds){
        try {
            browser.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            log.error("Test was not waiting!!");
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

    public void setTcResult(ArrivalResult tcResult) {
        this.tcResult.set(tcResult.toString());
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