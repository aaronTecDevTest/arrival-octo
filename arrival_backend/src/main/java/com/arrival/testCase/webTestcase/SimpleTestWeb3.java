package com.arrival.testCase.webTestcase;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival
 */

import com.arrival.unit.generic.ArrivalWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SimpleTestWeb3 extends ArrivalWeb {

    public SimpleTestWeb3() {
        super();
        setTcID(222);
        setTcName("SimpleTestWeb3");
        setTcDescription("Test2");
        setTcDuration("20");
        setTcLink("wwww.wetter.info");
        setTcLastRun("100");
        setTcResult("Link2");
    }

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(WebDriver driver, Integer id) {
        System.out.println("Fast test 222 " + driver + " " + id);
        setWebDriver(driver);
        driver.get("http://www.chakib.de/");
        WebElement wb = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/ul/li[2]/a"));
        wb.click();

        pauseTest(5000);

        click();
    }

}
