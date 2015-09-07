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
    public void aFastTest(WebDriver browser, Integer id) {
        System.out.println("Fast test 222 " + browser + " " + id);
        setBrowser(browser);
        browser.get("http://www.google.com");
        pauseTest(3000);
        click();
    }

    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(WebDriver browser, Integer id) {
        System.out.println("Slow test 303 " + browser + " " + id);
        setBrowser(browser);
        browser.get("http://www.telekom.de/");
        pauseTest(1000);
        click();
    }
}
