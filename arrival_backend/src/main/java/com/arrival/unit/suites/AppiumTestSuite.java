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
 * Created by a.kutekidila on 13.05.2015.
 **/

public class AppiumTestSuite {

    TestNG tng = new TestNG();
    List<XmlClass> classes = new ArrayList<>();

    XmlSuite suite = new XmlSuite();
    List<XmlSuite> suites = new ArrayList<>();

    XmlTest testXML = new XmlTest(suite);

    EmailListener eml;
    PreConfigListener pcl;

    public AppiumTestSuite() {

        eml = new EmailListener();
        pcl = new PreConfigListener();

        tng.setOutputDirectory(getNewPathDirectory());
        tng.setDefaultSuiteName("RegressionsTest");
        tng.addListener(eml);
        tng.addListener(pcl);

        testXML.setName("RegressionsTest");
    }

    public static void main(String[] args) {
        AppiumTestSuite runTest = new AppiumTestSuite();
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
        classes.add(new XmlClass("com.arrival.unit.generic.AppiumConfigSingleton"));
        classes.add(new XmlClass("com.arrival.testCase.iosTestcase.SimpleTest1"));
        classes.add(new XmlClass("com.arrival.testCase.iosTestcase.SimpleTest2"));
        testXML.setXmlClasses(classes);
    }

    /**
     * @return Path as a String
     */
    private String getNewPathDirectory() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Calendar cal = Calendar.getInstance();
        String tempPath = "../arrival-octo/arrival_backend/src/main/resources/report/testng/iosTestcase/";
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