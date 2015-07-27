package com.arrival.windows.model;

/**
 * Created by Aaron Kutekidila on 09.05.2015.
 **/

import com.arrival.utilities.interfaces.IFTestCase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class TestCase implements IFTestCase {

    private SimpleIntegerProperty tcID;
    private SimpleStringProperty tcName;
    private SimpleStringProperty tcDescription;
    private SimpleStringProperty tcDuration;
    private SimpleStringProperty tcLastRun;
    private SimpleStringProperty tcLink;
    private SimpleStringProperty tcResult;

    public TestCase() {
        this(0, "", "", "","","","");
    }

    public TestCase(Integer id, String tcName, String tcDescription, String tcResult, String tcDuration, String tcLastRun, String tcLink) {
        this.tcID = new SimpleIntegerProperty(id);
        this.tcName = new SimpleStringProperty(tcName);
        this.tcDescription = new SimpleStringProperty(tcDescription);
        this.tcDuration = new SimpleStringProperty(tcDuration);
        this.tcLastRun = new SimpleStringProperty(tcLastRun);
        this.tcLink = new SimpleStringProperty(tcLink);
        this.tcResult = new SimpleStringProperty(tcResult);
    }

    public TestCase(String tcName, String tcDescription, String tcResult, String tcDuration, String tcLastRun, String tcLink) {
        this.tcName = new SimpleStringProperty(tcName);
        this.tcDescription = new SimpleStringProperty(tcDescription);
        this.tcDuration = new SimpleStringProperty(tcDuration);
        this.tcLastRun = new SimpleStringProperty(tcLastRun);
        this.tcLink = new SimpleStringProperty(tcLink);
        this.tcResult = new SimpleStringProperty(tcResult);
    }

    public int getTcID() {
        return tcID.get();
    }

    public SimpleIntegerProperty tcIDProperty() {
        return tcID;
    }

    public void setTcID(int tcID) {
        this.tcID.set(tcID);
    }

    public String getTcName() {
        return tcName.get();
    }

    public SimpleStringProperty tcNameProperty() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName.set(tcName);
    }

    public String getTcDescription() {
        return tcDescription.get();
    }

    public SimpleStringProperty tcDescriptionProperty() {
        return tcDescription;
    }

    public void setTcDescription(String tcDescription) {
        this.tcDescription.set(tcDescription);
    }

    public String getTcResult() {
        return tcResult.get();
    }

    public SimpleStringProperty tcResultProperty() {
        return tcResult;
    }

    public void setTcResult(String tcResult) {
        this.tcResult.set(tcResult);
    }

    public String getTcLastRun() {
        return tcLastRun.get();
    }

    public SimpleStringProperty tcLastRunProperty() {
        return tcLastRun;
    }

    public void setTcLastRun(String tcLastRun) {
        this.tcLastRun.set(tcLastRun);
    }

    public String getTcLink() {
        return tcLink.get();
    }

    public SimpleStringProperty tcLinkProperty() {
        return tcLink;
    }

    public void setTcLink(String tcLink) {
        this.tcLink.set(tcLink);
    }

    public String getTcDuration() {
        return tcDuration.get();
    }

    public SimpleStringProperty tcDurationProperty() {
        return tcDuration;
    }

    public void setTcDuration(String tcDuration) {
        this.tcDuration.set(tcDuration);
    }
}

