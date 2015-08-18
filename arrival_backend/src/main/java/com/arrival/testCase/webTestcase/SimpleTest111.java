package com.arrival.testCase.webTestcase;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival
 */


import com.arrival.unit.generic.ArrivalWeb;
import org.testng.annotations.Test;

public class SimpleTest111 extends ArrivalWeb {

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(String serverName, Integer id) {
        System.out.println("Fast test 202 " + serverName + " " + id);

        openBrowser().get("http://www.google.com");

        pauseTest(3000);

        closeBrowser();
    }
/*
    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(String serverName, Integer id) {
        System.out.println("Slow test 303 " + serverName + " " + id);

        openBrowser().get("http://www.t-online.de");

        pauseTest(3000);

        closeBrowser();
    }*/
}
