package com.arrival.unit.suites;

import com.arrival.unit.listener.EmailListener;
import com.arrival.unit.listener.PreConfigListener;
import com.arrival.utilities.interfaces.IFTestCase;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.nio.file.Path;
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

    private TestNG tng = new TestNG();
    private List<XmlClass> classes = new ArrayList<>();

    private XmlSuite suite = new XmlSuite();
    private List<XmlSuite> suites = new ArrayList<>();

    private XmlTest testXML = new XmlTest(suite);

    private EmailListener eml;
    private PreConfigListener pcl;

    private Path testResultPath;
    private ArrayList<IFTestCase> testCases;

    public ArrivalTestSuite() {

        eml = new EmailListener();
        pcl = new PreConfigListener();

        tng.setOutputDirectory(getNewPathDirectory());
        tng.setDefaultSuiteName("RegressionsTest");
        tng.addListener(eml);
        tng.addListener(pcl);

        testXML.setName("RegressionsTest");
    }

    public static void main(String[] args) {
        ArrivalTestSuite runTest = new ArrivalTestSuite();
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
        /*classes.add(new XmlClass("com.arrival.unit.generic.SeleniumConfigSingleton"));
        classes.add(new XmlClass("com.arrival.testCase.andTestcase.SimpleTest1"));
        classes.add(new XmlClass("com.arrival.testCase.andTestcase.SimpleTest1"));*/
        testXML.setXmlClasses(classes);
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
            System.out.println("Directory creation failed1");
        else
            System.out.println("Directory creation success");
    }

    public List<XmlClass> getClasses() {
        return classes;
    }

    public void setClasses(List<XmlClass> classes) {
        this.classes = classes;
    }

    public ArrayList<IFTestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(ArrayList<IFTestCase> testCases) {
        this.testCases = testCases;
    }

    private String getPath() {
        String path;

        path = this.getClass().getPackage().getName();

        System.out.println(path);
        return path;
    }
}