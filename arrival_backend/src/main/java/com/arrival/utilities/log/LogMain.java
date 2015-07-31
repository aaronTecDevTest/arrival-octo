package com.arrival.utilities.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogMain {
    public static void main(String[] args) {
       /* ClassLoader cl = ClassLoader.getSystemClassLoader();
        ClassLoader cl = getClass().getClassLoader();
        private static final Logger logger = LogManager.getLogger(LogMain.class.getName());
        String p = "log4j2.xml";
        File f = new File(String.valueOf(cl.getResource(p)));
        System.out.println(f.toString());
        System.out.println(f.getName());*/
        //Log4jConfiguration.configureRollingFile();
        final Logger logger = LogManager.getLogger(LogMain.class.getName());

        logger.debug("Hello world - debug log");
        logger.info("Hello world - info log");
        logger.warn("Hello world - warn log");
        logger.error("Hello world - error log");
    }
}
