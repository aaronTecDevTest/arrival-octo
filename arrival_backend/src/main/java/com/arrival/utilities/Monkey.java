package com.arrival.utilities;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 17.02.2016.
 * @since: 1.0
 * Package: com.arrival.utilities
 */

import java.util.Map;
import java.util.TreeMap;
//import com.android.chimpchat.ChimpChat;
//import com.android.chimpchat.core.IChimpDevice;

public class Monkey {

    private static final String ADB = "C:\\Users\\a.kutekidila\\android-sdk\\platform-tools\\adb.exe";
    private static final long TIMEOUT = 5000;

    /**
     * @param args
     *
    public static void main(String[] args) {

        Map<String, String> options = new TreeMap<String, String>();
        options.put("backend", "adb");
        //this is so you don't need to add adb or platform-tools to your system path
        options.put("adbLocation", ADB);
        ChimpChat chimpchat = ChimpChat.getInstance(options);
        //Using this method is advised as to avoid hangs,as this would wait indefinitely
        //Actually waitForConnection() doesn't wait indefinitely but for Integer.MAX_VALUE milliseconds, which still makes up for 596 hours
        IChimpDevice device = chimpchat.waitForConnection(TIMEOUT, ".*");
        chimpchat.shutdown();
    }*/
}