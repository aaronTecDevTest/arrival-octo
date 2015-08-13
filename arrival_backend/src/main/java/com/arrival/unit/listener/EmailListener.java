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

public class EmailListener implements IExecutionListener {
    private static final Logger log =  LogManager.getLogger(EmailListener.class);
    @Override
    public void onExecutionStart() {
        System.out.println("Notify by mail that TestNG is going to start");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Notify by mail, TestNG is finished");
    }
}
