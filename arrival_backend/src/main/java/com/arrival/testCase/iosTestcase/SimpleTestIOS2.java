package com.arrival.testCase.iosTestcase;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival
 */


import com.arrival.unit.generic.ArrivalIOS;
import org.testng.annotations.Test;

public class SimpleTestIOS2 extends ArrivalIOS {

    public SimpleTestIOS2(){
        super();
        setTcID(11123);
        setTcName("SimpleTestIOS2");
        setTcDescription("Test1122");
        setTcDuration("2");
        setTcLink("wwww.wetter.info");
        setTcLastRun("10");
        setTcResult("Link1");
    }

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(String serverName, Integer id) {
        System.out.println("Fast test 20 " + serverName + " " + id);
    }

    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(String serverName, Integer id) {
        System.out.println("Slow test 30 " + serverName + " " + id);
    }
}