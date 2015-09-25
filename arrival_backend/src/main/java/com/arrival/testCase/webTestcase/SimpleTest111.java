package com.arrival.testCase.webTestcase;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival
 */


import com.arrival.unit.generic.ArrivalWeb;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SimpleTest111 extends ArrivalWeb {

    public SimpleTest111(){
        setTcID(111);
        setTcDescription("Test1");
        setTcDuration("2");
        setTcLink("wwww.wetter.info");
        setTcLastRun("10");
        setTcResult("Link1");
    }

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(WebDriver driver, Integer id) {
        System.out.println("Fast test 111 " + driver + " " + id);
        setBrowser(driver);
        driver.get("http://www.google.com");
        pauseTest(3000);
        click();
    }

    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(WebDriver driver, Integer id) {
        System.out.println("Slow test 303 " + driver + " " + id);
        setBrowser(driver);
        driver.get("http://www.t-online.de");
        pauseTest(1000);
        click();
    }

}
