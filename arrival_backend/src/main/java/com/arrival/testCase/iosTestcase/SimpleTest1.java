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

public class SimpleTest1 extends ArrivalIOS {

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(String serverName, Integer id) {
        System.out.println("Fast test 202 " + serverName + " " + id);
    }

    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(String serverName, Integer id) {
        System.out.println("Slow test 303 " + serverName + " " + id);
    }
}
