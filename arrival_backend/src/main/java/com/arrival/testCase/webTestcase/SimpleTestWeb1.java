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
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SimpleTestWeb1 extends ArrivalWeb {

    public SimpleTestWeb1() {
        super();
        setTcID(111);
        setTcName("SimpleTestWeb1");
        setTcDescription("Test1");
        setTcDuration("2");
        setTcLink("wwww.wetter.info");
        setTcLastRun("10");
        setTcResult(ArrivalResult.PASSED);
    }

    @Test(dataProvider = "driver",priority = 2, groups = {"fast"})
    public void aFastTest(WebDriver driver, Integer id) {
        System.out.println("Fast test 111 " + driver + " " + id);
       // setWebDriver(driver);
        driver.get("http://www.google.com");
        pauseTest(3000);
        click();
    }

    @Test(dataProvider = "driver", priority = 2,groups = {"slow"})
    public void aSlowTest(WebDriver driver, Integer id) {
        System.out.println("Slow test 303 " + driver + " " + id);
       // setWebDriver(driver);
        driver.get("http://www.t-online.de");
        pauseTest(1000);
        click();
    }
}
