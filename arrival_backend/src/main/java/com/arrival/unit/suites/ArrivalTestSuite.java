package com.arrival.unit.suites;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.unit.suites
 */

import com.arrival.unit.listener.EmailListener;
import com.arrival.unit.listener.PreConfigListenerAppium;
import com.arrival.unit.listener.TestListener;

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


public class ArrivalTestSuite /*implements Worker{*/ extends Thread{
    private static final Logger log = LogManager.getLogger(ArrivalTestSuite.class);
    /**
     * @param suiteID:  Counter to create different TestNG-Suite-Name
     */
    private static int suiteID = 0;
    /**
     * @param appiumManager: Mange the configuration and server properties to run testcase other
     * IOS and Android devices or simulation.
     */
    private Object appiumManager;
    /**
     * @param seleniumManager: Manage the configuration and server properties to run testcase other
     * Firefox, Chrome and IE driver.
     */
    private Object seleniumManger;

    /**
     * TestNG properties
     */
    private TestNG tng = new TestNG();

    private List<XmlClass> classes = new ArrayList<>();
    private XmlSuite suite = new XmlSuite();
    private List<XmlSuite> suites = new ArrayList<>();
    private XmlTest xmlTest = new XmlTest(suite);
    //private EmailListener eml;
    //private PreConfigListenerAppium pcl;
    //private TestListener tla;
    private String saveResultDir;

    public ArrivalTestSuite() {
        //eml = new EmailListener();
        // pcl = new PreConfigListenerAppium();
        //tla = new TestListener();

        suiteID++;
        tng.setDefaultSuiteName("RegressionsTest - " + suiteID);
        //tng.addListener(eml);
        //tng.addListener(pcl);
        //tng.addListener(tla);

        xmlTest.setName("RegressionsTest - " + suiteID);
        suite.setName("RegressionsTest - " + suiteID);
        suites.add(suite);
    }

 //   @Override
    public void run() {
        createVirtualSuite();
        //suites.add(suite);
        tng.setXmlSuites(suites);
        tng.run();
    }

    public void stopThread(){
       // this.interrupt();
    }

    public void pauseThread(){
        synchronized(tng) {
            try {
                tng.wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void resumeThread() {
        synchronized(tng) {
            tng.notify();
        }
    }

    public void skippTestCase() {
        tng.getTestListeners().iterator().next();
    }


    private void createVirtualSuite() {
        //suite.setName("TmpSuite");
        xmlTest.setName("TmpTest");
        /*classes.add(new XmlClass("com.arrival.unit.generic.SeleniumSingleton"));
        classes.add(new XmlClass("com.arrival.testCase.andTestcase.SimpleAnd1"));
        classes.add(new XmlClass("com.arrival.testCase.andTestcase.SimpleAnd1"));*/
    }

    /**
     * @return Path as a String to save ArrivalResult from TestNG
     */
    public String getNewPathDirectory() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Calendar cal = Calendar.getInstance();
        saveResultDir = "../arrival-Octo/arrival_backend/src/main/resources/report/testng/";
        saveResultDir = saveResultDir + dateFormat.format(cal.getTime());
        createNewDirectory(saveResultDir);
        return saveResultDir;
    }


    public List<XmlClass> getClasses() {
        return classes;
    }

    public XmlSuite getSuite() {
        return suite;
    }

    public TestNG getTng(){return tng;}

    public String getPath() {
        String path;
        path = this.getClass().getPackage().getName();
        System.out.println(path);
        return path;
    }

    private void createNewDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        boolean success = dir.mkdir();

        if (! success)
            log.info("Directory creation failed!");
        else
            log.info("Directory creation success!");
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

    public void setTngName(String name) {
        tng.setDefaultSuiteName(name);
    }

    public void setParallelTesting(boolean parallelTesting){
        suite.setParallel(String.valueOf(parallelTesting));
    }

    public void setPath(String path) {
        this.saveResultDir = path;
    }
}