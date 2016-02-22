package com.arrival.testCase.webTestcase;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival
 */

import com.arrival.unit.generic.ArrivalWeb;
import com.arrival.utilities.ArrivalResult;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


/**
 * Example Page : https://www.w3.org/2010/05/video/mediaevents.html
 */

public class SimpleTestWeb4 extends ArrivalWeb {

    public SimpleTestWeb4() {
        super();
        setTcID(222);
        setTcName("SimpleTestWeb4");
        setTcDescription("Test2");
        setTcDuration("20");
        setTcLink("wwww.wetter.info");
        setTcLastRun("100");
        setTcResult(ArrivalResult.PASSED);
    }

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(WebDriver driver, Integer id) {
        System.out.println("Fast test 222 " + driver + " " + id);
        //setWebDriver(driver);
        driver.get("http://www.w3.org/2010/05/video/mediaevents.html");

        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("document.getElementById(\"video\").play()");
        pauseTest(10000);
        exe.executeScript("document.getElementById(\"video\").pause()");
        pauseTest(10000);
        exe.executeScript("document.getElementById(\"video\").load()");
    }
}