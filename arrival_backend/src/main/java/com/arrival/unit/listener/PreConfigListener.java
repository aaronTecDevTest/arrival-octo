package com.arrival.unit.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.listener
 */

public class PreConfigListener implements IExecutionListener {
    private static final Logger log =  LogManager.getLogger(PreConfigListener.class);
    private long startTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
        log.info("TestNG is going to start");
    }

    @Override
    public void onExecutionFinish() {
        log.info("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
    }
}