package com.arrival.utilities;

import com.arrival.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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
    private static String osCountry;
    private static String osLanguage;
    private static String osData;

    private static ResourceBundle bundleHelp;
    private static ResourceBundle bundleLogIn;
    private static ResourceBundle bundleMain;
    private static ResourceBundle bundleOptions;
    private static ResourceBundle bundleDialogs;
    private static ResourceBundle bundleGlobal;

    private SystemPreferences() {
        log.info(SystemPreferences.class + " is loaded!!");
        osName = System.getProperty("os.name");

        osCountry = Locale.getDefault().getCountry();
        osLanguage = Locale.getDefault().getLanguage();

        setUpResourceBundle();
    }

    public static SystemPreferences getInstance() {
        return ourInstance;
    }

    public static Logger getLog() {
        return log;
    }


    public static SystemPreferences getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(SystemPreferences ourInstance) {
        SystemPreferences.ourInstance = ourInstance;
    }


    public static String getOsName() {
        return osName;
    }

    public static void setOsName(String osName) {
        SystemPreferences.osName = osName;
    }


    public static String getOsCountry() {
        return osCountry;
    }

    public static void setOsCountry(String osCountry) {
        SystemPreferences.osCountry = osCountry;
    }


    public static String getOsLanguage() {
        return osLanguage;
    }

    public  void setOsLanguage(String osLanguage) {
        SystemPreferences.osLanguage = osLanguage;
        setUpResourceBundle();
    }


    public static String getOsData() {
        return osData;
    }

    public static void setOsData(String osData) {
        SystemPreferences.osData = osData;
    }


    private void setUpResourceBundle() {
        try {
            bundleHelp = loadBundle("bundles/arrivalHelp_de.properties");
            bundleLogIn = loadBundle("bundles/arrivalLogIn_de.properties");
            bundleMain = loadBundle("bundles/arrivalMain_de.properties");
            bundleOptions = loadBundle("bundles/arrivalOptions_de.properties");
            bundleDialogs = loadBundle("bundles/arrivalDialogs_de.properties");
            bundleGlobal = loadBundle("bundles/arrivalGlobal_de.properties");
        } catch (Exception e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
        }
    }

    private static ResourceBundle loadBundle(String url) throws Exception {
       // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = SystemPreferences.class.getClassLoader();

        URL urlResource = classLoader.getResource(url);
        InputStream inputStream = urlResource.openStream();

        ResourceBundle tempBundle = new PropertyResourceBundle(inputStream);
        return tempBundle;
    }

    public static ResourceBundle getResourceBundle(String bundleName) {
        switch (bundleName) {
            case "arrivalHelp":
                return bundleHelp;
            case "arrivalLogIn":
                return bundleLogIn;
            case "arrivalMain":
                return bundleMain;
            case "arrivalOptions":
                return bundleOptions;
            case "arrivalDialogs":
                return bundleDialogs;
            case "arrivalGlobal":
                return bundleGlobal;
            default:
                return null;
        }
    }

    public boolean isMacOS() {
        return osName.contains("Mac OS X");
    }

    public boolean isWindows() {
        return osName.contains("Windows");
    }

    public boolean isLinux() {
        return osName.contains("Linux");
    }

    public boolean isUnix() {
        return osName.contains("Unix");
    }

    @Override
    public String toString() {
        return osName + " " + osCountry + " " + osLanguage;
    }
/*
    public static void main(String[] args) {
        SystemPreferences.getInstance();

    }*/
}
