package com.arrival.testCase.webTestcase.Projekt2;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SimpleTestWeb2 extends ArrivalWeb {

    public SimpleTestWeb2() {
        super();
        setTcID(222);
        setTcName("SimpleTestWeb2");
        setTcDescription("Test2");
        setTcDuration("20");
        setTcLink("wwww.wetter.info");
        setTcLastRun("100");
        setTcResult(ArrivalResult.DEFAULT);
    }

    @Test(dataProvider = "driver", priority = 2,groups = {"slow"})
    public void aFastTest(WebDriver driver, Integer id) {
        System.out.println("Fast test 222 " + driver + " " + id);
        //setWebDriver(driver);
        driver.get("http://www.apple.com/");
        WebElement ele = driver.findElement(By.xpath("//*[@id=\"hero-gallery-item-hero-iphone-6s-change\"]/div/a"));
        ele.click();
        pauseTest(6000);
        click();
    }

    @Test(dataProvider = "driver", priority = 2,groups = {"slow"})
    public void aSlowTest(WebDriver driver, Integer id) {
        System.out.println("Slow test 303 " + driver + " " + id);
        //setWebDriver(driver);
        driver.get("http://www.telekom.com/");
        pauseTest(1000);
        click();
    }
}
