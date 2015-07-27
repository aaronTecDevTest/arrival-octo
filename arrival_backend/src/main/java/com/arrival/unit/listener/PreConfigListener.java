package com.arrival.unit.listener;

import org.testng.IExecutionListener;
import org.testng.xml.XmlClass;

import java.util.List;

/**
 * Created by tecdesdev on 07/07/15.
 */
public class PreConfigListener implements IExecutionListener {
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
        System.out.println("TestNG is going to start");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
