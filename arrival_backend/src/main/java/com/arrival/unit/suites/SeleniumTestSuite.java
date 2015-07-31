package com.arrival.unit.suites;

import com.arrival.unit.listener.EmailListener;
import com.arrival.unit.listener.PreConfigListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.suites
 */

public class SeleniumTestSuite {

    TestNG tng = new TestNG();
    List<XmlClass> classes = new ArrayList<>();

    XmlSuite suite = new XmlSuite();
    List<XmlSuite> suites = new ArrayList<>();

    XmlTest testXML = new XmlTest(suite);

    EmailListener eml;
    PreConfigListener pcl;

    public SeleniumTestSuite() {

        eml = new EmailListener();
        pcl = new PreConfigListener();

        tng.setOutputDirectory(getNewPathDirectory());
        tng.setDefaultSuiteName("RegressionsTest");
        tng.addListener(eml);
        tng.addListener(pcl);

        testXML.setName("RegressionsTest");
    }

    public static void main(String[] args) {
        SeleniumTestSuite runTest = new SeleniumTestSuite();
        runTest.runVirtualSuit();
    }

    public void runVirtualSuit() {
        createVirtualSuite();
        suites.add(suite);
        tng.setXmlSuites(suites);
        tng.run();
    }

    private void createVirtualSuite() {
        suite.setName("TmpSuite");
        testXML.setName("TmpTest");
        classes.add(new XmlClass("com.arrival.testNG.generic.AppiumConfigSingleton"));
        classes.add(new XmlClass("com.arrival.testNG.test.SimpleTest1"));
        classes.add(new XmlClass("com.arrival.testNG.test.SimpleTest2"));
        testXML.setXmlClasses(classes);
    }

    /**
     * @return Path as a String
     */
    private String getNewPathDirectory() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Calendar cal = Calendar.getInstance();
        String tempPath = "../arrival-septem/testNG/src/main/resources/testng/result/";
        String outPutDirectory = tempPath + dateFormat.format(cal.getTime());

        createNewDirectory(outPutDirectory);
        return outPutDirectory;
    }

    private void createNewDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        boolean success = dir.mkdir();

        if (!success)
            System.out.println("Directory creation failed1");
        else
            System.out.println("Directory creation success");
    }

    private String getPath() {
        String path;

        path = this.getClass().getPackage().getName();

        System.out.println(path);
        return path;
    }
}