package com.arrival.unit.generic;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;

/**
 * Created by tecdesdev on 06/07/15.
 */
public abstract class ArrivalMobil  implements IFGenericAND, IFGenericIOS{

    public static AppiumConfigSingleton appiumConfi = AppiumConfigSingleton.getInstance();
    public ArrayList<Object> appiumServerList = new ArrayList<>();

    /*
     *Test NG method
     */
    @DataProvider(name = "driver" /*,parallel = true*/)
    public Object[][] createServer() {

        Object [][] server;
        int y = appiumServerList.size();
        int x = 2;

        server = new Object[y][x];

        for(int i = 0; i< y; i++){
            for(int j = 0; j < x; j++) {
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
    public void setUpTestClass(){
        if(AppiumConfigSingleton.getTestArt().equals("multi")){
            appiumServerList.add("android Test1");
            appiumServerList.add("android Test2");
            appiumServerList.add("ios Test1");
            appiumServerList.add("ios Test2");
        }
        else {
            appiumServerList.add("android Default");
        }
    }

    /*
    *Other method
    */
    public void pauseTest(long milSec){

    }

    /*
     *IOS general method
     */
    public void clickIOS(){}
    public void doppleClickIOS(){}
    public void pressIOS(){}
    public void longPressIOS(){}
    public void swipeRightIOS(){}
    public void swipeLeftIOS(){}
    public void scrollUpIOS(){}
    public void scrollDownIOS(){}
    public void zoomInIOS(){}
    public void zoomOutIOS(){}
    public void takeScreenShotIOS(){}


    /*
    *Android general method
    */
    public void clickAND(){}
    public void doppleClickAND(){}
    public void pressAND(){}
    public void longPressAND(){}
    public void swipeRightAND(){}
    public void swipeLeftAND(){}
    public void scrollUpAND(){}
    public void scrollDownAND(){}
    public void zoomInAND(){}
    public void zoomOutAND(){}
    public void takeScreenShotAND(){}
}