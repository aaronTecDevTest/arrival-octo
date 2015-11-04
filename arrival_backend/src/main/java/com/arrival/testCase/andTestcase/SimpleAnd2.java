package com.arrival.testCase.andTestcase;
/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival
 */


import com.arrival.unit.generic.ArrivalAND;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SimpleAnd2 extends ArrivalAND {

    public SimpleAnd2() {
        super();
        setTcID(2);
        setTcName("SimpleAnd2");
        setTcDescription("Test2");
        setTcDuration("2");
        setTcLink("wwww.wetter.info.de");
        setTcLastRun("10");
        setTcResult("Link1");
    }

    @Test(dataProvider = "driver", groups = {"fast"})
    public void getGoogle(AndroidDriver driver, Integer id) {
        setWebDriver(driver);

        // And now use this to visit Google
        androidDriver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
        pauseTest(1000);
        // Find the text input element by its name
        WebElement element = androidDriver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + androidDriver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        /*(new WebDriverWait(androidDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("cheese!");
            }
        });*/

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + androidDriver.getTitle());
    }

    @Test(dataProvider = "driver", groups = {"fast"})
    public void getTOnlineTv(AndroidDriver driver, Integer id) {
        setWebDriver(driver);

        // And now use this to visit Google
        androidDriver.get("http://www.t-online.de/tv");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
        pauseTest(1000);
        //Find the text input element by its name
        //WebElement element = wd.findElement(By.xpath("//*[@id=\"T-74389010\"]/div[2]/a"));
        //WebElement element = wd.findElement(By.xpath("//*[@id=\"T-74389010\"]/div/div/a/div"));

        // Check the title of the page
        System.out.println("Page title is: " + androidDriver.getTitle());

        WebElement element = androidDriver.findElement(By.xpath(".//*[@id=\"T-74389010\"]/div/L/a"));
        element.click();
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        /*(new WebDriverWait(androidDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("video");
            }
        });*/
        pauseTest(2000);
        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + androidDriver.getTitle());
    }
}
