package com.arrival.unit.suites;

import com.arrival.unit.listener.EmailListener;
import com.arrival.unit.listener.PreConfigListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class ArrivalTestSuite {
    private static final Logger log =  LogManager.getLogger(ArrivalTestSuite.class);
    /**
        @param appiumManager: Mange the configuration and server properties to run testcase other
                            IOS and Android devices or simulation.
     */
    private Object appiumManager;

    /**
       @param seleniumManager: Manage the configuration and server properties to run testcase other
                             Firefox, Chrome and IE driver.
     */
    private Object seleniumManger;

    /**
        TestNG properties
     */
    private TestNG tng = new TestNG();
    private List<XmlClass> classes = new ArrayList<>();

    private XmlSuite suite = new XmlSuite();
    private List<XmlSuite> suites = new ArrayList<>();

    private XmlTest xmlTest = new XmlTest(suite);

    private EmailListener eml;
    private PreConfigListener pcl;

    private String path;

    /**
        @param suiteID:  Counter to create different TestNG-Suite-Name
     */
    private static int suiteID = 0;

    public ArrivalTestSuite() {

        eml = new EmailListener();
        pcl = new PreConfigListener();

        tng.setOutputDirectory(getNewPathDirectory());
        suiteID++;
        tng.setDefaultSuiteName("RegressionsTest - " + suiteID);
        tng.addListener(eml);
        tng.addListener(pcl);

        xmlTest.setName("RegressionsTest - " + suiteID);
        suite.setName("RegressonsTest - " + suiteID);
        suites.add(suite);
    }

    public static void main(String[] args) {
        ArrivalTestSuite runTest = new ArrivalTestSuite();
        runTest.runVirtualSuit();
    }

    public void runVirtualSuit() {
        createVirtualSuite();
        //suites.add(suite);
        tng.setXmlSuites(suites);
        tng.run();
    }

    private void createVirtualSuite() {
        //suite.setName("TmpSuite");
        xmlTest.setName("TmpTest");
        /*classes.add(new XmlClass("com.arrival.unit.generic.SeleniumConfigSingleton"));
        classes.add(new XmlClass("com.arrival.testCase.andTestcase.SimpleTest1"));
        classes.add(new XmlClass("com.arrival.testCase.andTestcase.SimpleTest1"));*/
    }

    /**
     * @return Path as a String
     */
    private String getNewPathDirectory() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Calendar cal = Calendar.getInstance();
        //ToDo: Mit Path class implement
        String tempPath = "../arrival-Octo/arrival_backen/src/main/resources/report/testng/selenium/";
        String outPutDirectory = tempPath + dateFormat.format(cal.getTime());

        createNewDirectory(outPutDirectory);
        return outPutDirectory;
    }

    private void createNewDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        boolean success = dir.mkdir();

        if (!success)
            log.info("Directory creation failed1");
        else
            log.info("Directory creation success");
    }

    public List<XmlClass> getClasses() {
        return classes;
    }

    public void setClasses(List<XmlClass> classes) {

        xmlTest.setXmlClasses(classes);

        this.classes = classes;
    }

    public void setSuiteName(String name) {
        suite.setName(name);
    }

    public void setXmlTest(String name) {
        xmlTest.setName(name);
    }

    public void setTngName(String name){
        tng.setDefaultSuiteName(name);
    }

    public void setPath(String path){
        this.path = path;
    }

    public  String getPath() {
        String path;
        path = this.getClass().getPackage().getName();
        System.out.println(path);
        return path;
    }
}