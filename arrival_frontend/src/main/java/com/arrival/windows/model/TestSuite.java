package com.arrival.windows.model;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.model
 */

import com.arrival.unit.suites.ArrivalTestSuite;
import com.arrival.utilities.interfaces.IFTestSuite;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * Class not in Use jet!!
 */
public class TestSuite implements IFTestSuite {
    private SimpleIntegerProperty tsId;
    private SimpleStringProperty tsName;
    private SimpleStringProperty tsResult;
    private ArrayList<ArrivalTestSuite> testCases;
    private SimpleStringProperty suiteIdentifyIdentify;


    public TestSuite(Integer id, String tsName, String tsResult) {
        this.tsId = new SimpleIntegerProperty(id);
        this.tsName = new SimpleStringProperty(tsName);
        this.tsResult = new SimpleStringProperty(tsResult);
    }

    public TestSuite(TestSuite testSuite){
        this.tsId = new SimpleIntegerProperty(testSuite.getTsId());
        this.tsName = new SimpleStringProperty(testSuite.getTsName());
        this.tsResult = new SimpleStringProperty(testSuite.getTsResult());
    }

    @Override
    public int getTsId() {
        return tsId.get();
    }

    public void setTsId(int tsId) {
        this.tsId.set(tsId);
    }

    @Override
    public SimpleIntegerProperty tsIdProperty() {
        return tsId;
    }

    @Override
    public String getTsName() {
        return tsName.get();
    }

    public void setTsName(String tsName) {
        this.tsName.set(tsName);
    }

    @Override
    public SimpleStringProperty tsNameProperty() {
        return tsName;
    }

    @Override
    public String getTsResult() {
        return tsResult.get();
    }

    public void setTsResult(String tsResult) {
        this.tsResult.set(tsResult);
    }

    @Override
    public SimpleStringProperty tsResultProperty() {
        return tsResult;
    }

    public ArrayList<ArrivalTestSuite> getTestCases() {
        return testCases;
    }

    public void setTestCases(ArrayList<ArrivalTestSuite> testCases) {
        this.testCases = testCases;
    }

    public String getSuiteIdentifyIdentify() {
        return suiteIdentifyIdentify.get();
    }

    public void setSuiteIdentifyIdentify(String suiteIdentifyIdentify) {
        this.suiteIdentifyIdentify.set(suiteIdentifyIdentify);
    }

    public SimpleStringProperty suiteIdentifyIdentifyProperty() {
        return suiteIdentifyIdentify;
    }
}
