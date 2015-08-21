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

public class SimpleTest222 extends ArrivalWeb {

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(String serverName, Integer id) {
        try {
            System.out.println("Fast test 222 " + serverName + " " + id);

            WebDriver driver = openBrowser();

            driver.get("http://www.google.com");

            pauseTest(1000);

            click();
            closeBrowser(driver);
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
    }
/*
    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(String serverName, Integer id) {
        System.out.println("Slow test 303 " + serverName + " " + id);

        openBrowser().get("http//www.t-online.de");

        pauseTest(3000);

        closeBrowser();
    }
    */
}
