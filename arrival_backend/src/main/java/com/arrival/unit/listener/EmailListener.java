package com.arrival.unit.listener;

import org.testng.IExecutionListener;

/**
 * Created by tecdesdev on 07/07/15.
 */
public class EmailListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.out.println("Notify by mail that TestNG is going to start");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Notify by mail, TestNG is finished");
    }
}
