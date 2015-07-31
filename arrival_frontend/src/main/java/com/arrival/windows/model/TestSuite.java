package com.arrival.windows.model;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.model
 */

import com.arrival.utilities.interfaces.IFTestSuite;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class TestSuite implements IFTestSuite {
    private SimpleIntegerProperty tsId;
    private SimpleStringProperty tsName;
    private SimpleStringProperty tsResult;
    private ArrayList<TestCase> testCases;



    public TestSuite(Integer id, String tsName, String tsResult) {
        this.tsId = new SimpleIntegerProperty(id);
        this.tsName = new SimpleStringProperty(tsName);
        this.tsResult = new SimpleStringProperty(tsResult);
    }

    public int getTsId() {
        return tsId.get();
    }

    public SimpleIntegerProperty tsIdProperty() {
        return tsId;
    }

    public void setTsId(int tsId) {
        this.tsId.set(tsId);
    }

    public String getTsName() {
        return tsName.get();
    }

    public SimpleStringProperty tsNameProperty() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName.set(tsName);
    }

    public String getTsResult() {
        return tsResult.get();
    }

    public SimpleStringProperty tsResultProperty() {
        return tsResult;
    }

    public void setTsResult(String tsResult) {
        this.tsResult.set(tsResult);
    }

    public ArrayList<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(ArrayList<TestCase> testCases) {
        this.testCases = testCases;
    }
}
