package com.arrival.windows.model;

/**
 * Created by Aaron on 09.05.2015.
 */

import javafx.beans.property.*;


public class TestSuite {
    private SimpleIntegerProperty tsId;
    private SimpleStringProperty tsName;
    private SimpleStringProperty tsResult;

    public TestSuite(Integer id, String tsName, String tsResult) {
        this.tsId = new SimpleIntegerProperty(id);
        this.tsName = new SimpleStringProperty(tsName);
        this.tsResult = new SimpleStringProperty(tsResult);
    }

    public String gettsResult() {
        return tsResult.get();
    }

    public SimpleStringProperty tsResultProperty() {
        return tsResult;
    }

    public void settsResult(String tsResult) {
        this.tsResult.set(tsResult);
    }

    public String gettsName() {
        return tsName.get();
    }

    public SimpleStringProperty tsNameProperty() {
        return tsName;
    }

    public void settsName(String tsName) {
        this.tsName.set(tsName);
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
}
