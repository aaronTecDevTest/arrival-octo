package com.arrival.unit.generic;

import com.arrival.utilities.interfaces.IFTestCase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

public abstract class ArrivalIOS implements IFTestCase, IFGenericMobil {

    public static AppiumConfigSingleton appiumConfi = AppiumConfigSingleton.getInstance();
    public ArrayList<Object> appiumServerList = new ArrayList<>();

    /*
     *Test NG method
     */
    @DataProvider(name = "driver" /*,parallel = true*/)
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

        /*
        if(appiumConfi.getTestArt().equals("multi")){
            server = new Object[][]{
                                           {"Server1", 11},
                                           {"Server2", 32},
                                           {"Server3", 23},
            };
        }
        else {
            server = new Object[][]{
                                           {"Default1", 1},
            };
        }*/
        return server;
    }


    @BeforeClass
    public void setUpTestClass() {
        if (AppiumConfigSingleton.getTestArt().equals("multi")) {
            appiumServerList.add("android Test1");
            appiumServerList.add("android Test2");
            appiumServerList.add("ios Test1");
            appiumServerList.add("ios Test2");
        } else {
            appiumServerList.add("android Default");
        }
    }

    /*
    *Other method
    */
    public void pauseTest(long milSec) {

    }

    /*
    *Web general method (Selenium)
    */

    @Override
    public void click() {

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


    /*
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
}