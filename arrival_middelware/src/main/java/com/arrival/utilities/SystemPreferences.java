package com.arrival.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 19.08.2015.
 * @since: 1.0
 * Package: com.arrival.utilities
 */
public class SystemPreferences {
    private static final Logger log = LogManager.getLogger(SystemPreferences.class);
    private static SystemPreferences ourInstance = new SystemPreferences();

    private static String osName;
    private static String osContry;
    private static String osLanguege;


    public static SystemPreferences getInstance() {
        log.info(SystemPreferences.class + " is loaded!!");
        osName = System.getenv("os.Name");
        osContry = Locale.getDefault().getCountry();
        osLanguege = Locale.getDefault().getLanguage();

        return ourInstance;
    }

    private SystemPreferences() {
    }

    public boolean isMacOS(){
        return osName.contains("Mac OS X");
    }

    public boolean isWindows(){
        return osName.contains("Windows");
    }

    public boolean isLinux(){
        return osName.contains("Linux");
    }

    public boolean isUnix(){
        return osName.contains("Unix");
    }

    @Override
    public String toString(){
        return osName + " " +osContry+ " "+ osLanguege;
    }

    public static void main(String[] args) {
        System.out.println(SystemPreferences.getInstance().toString());
    }
}
