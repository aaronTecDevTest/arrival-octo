package com.arrival.unit.listener;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 27.11.2015.
 * @since: 1.0
 * Package: com.arrival.unit.listener
 */

public class TestListener extends TestListenerAdapter {
/*    @Override
    public void onTestStart(ITestResult arg0) {
        super.onTestStart(arg0);

        if (skipTests.contains(arg0.getMethod().getDescription())){
            throw new SkipException("Skipping Test: " +
                    arg0.getMethod().getDescription());
        }
    }

    @Override
    public void onTestSkiped(ITestResult arg0) {
        super.onTestStart(arg0);

        if (skipTests.contains(arg0.getMethod().getDescription())){
            throw new SkipException("Skipping Test: " +
                    arg0.getMethod().getDescription());
        }
    }*/
}