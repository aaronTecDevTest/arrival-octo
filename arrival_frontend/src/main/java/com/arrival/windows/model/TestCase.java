package com.arrival.windows.model;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.model
 */

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
    private SimpleStringProperty tcClassPackage;



    public TestCase() {
        this(0, "", "", "", "", "", "","");
    }

    public TestCase(Integer id, String tcName, String tcDescription, String tcResult, String tcDuration, String tcLastRun, String tcLink, String tcClassPackage) {
        this.tcID = new SimpleIntegerProperty(id);
        this.tcName = new SimpleStringProperty(tcName);
        this.tcDescription = new SimpleStringProperty(tcDescription);
        this.tcDuration = new SimpleStringProperty(tcDuration);
        this.tcLastRun = new SimpleStringProperty(tcLastRun);
        this.tcLink = new SimpleStringProperty(tcLink);
        this.tcResult = new SimpleStringProperty(tcResult);
        this.tcClassPackage = new SimpleStringProperty(tcClassPackage);
    }

    public TestCase(String tcName, String tcDescription, String tcResult, String tcDuration, String tcLastRun, String tcLink, String tcClassPackage) {
        this.tcName = new SimpleStringProperty(tcName);
        this.tcDescription = new SimpleStringProperty(tcDescription);
        this.tcDuration = new SimpleStringProperty(tcDuration);
        this.tcLastRun = new SimpleStringProperty(tcLastRun);
        this.tcLink = new SimpleStringProperty(tcLink);
        this.tcResult = new SimpleStringProperty(tcResult);
        this.tcClassPackage = new SimpleStringProperty(tcClassPackage);
    }

    @Override
    public String getTcName() {
        return tcName.get();
    }

    @Override
    public SimpleStringProperty tcNameProperty() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName.set(tcName);
    }

    @Override
    public int getTcID() {
        return tcID.get();
    }

    @Override
    public SimpleIntegerProperty tcIDProperty() {
        return tcID;
    }

    public void setTcID(int tcID) {
        this.tcID.set(tcID);
    }

    @Override
    public String getTcDescription() {
        return tcDescription.get();
    }

    @Override
    public SimpleStringProperty tcDescriptionProperty() {
        return tcDescription;
    }

    public void setTcDescription(String tcDescription) {
        this.tcDescription.set(tcDescription);
    }

    @Override
    public String getTcDuration() {
        return tcDuration.get();
    }

    @Override
    public SimpleStringProperty tcDurationProperty() {
        return tcDuration;
    }

    public void setTcDuration(String tcDuration) {
        this.tcDuration.set(tcDuration);
    }

    @Override
    public String getTcLastRun() {
        return tcLastRun.get();
    }

    @Override
    public SimpleStringProperty tcLastRunProperty() {
        return tcLastRun;
    }

    public void setTcLastRun(String tcLastRun) {
        this.tcLastRun.set(tcLastRun);
    }

    @Override
    public String getTcResult() {
        return tcResult.get();
    }

    @Override
    public SimpleStringProperty tcResultProperty() {
        return tcResult;
    }

    public void setTcResult(String tcResult) {
        this.tcResult.set(tcResult);
    }

    @Override
    public String getTcLink() {
        return tcLink.get();
    }

    @Override
    public SimpleStringProperty tcLinkProperty() {
        return tcLink;
    }

    public void setTcLink(String tcLink) {
        this.tcLink.set(tcLink);
    }

    public String getTcClassPackage() {
        return tcClassPackage.get();
    }

    public SimpleStringProperty tcClassPackageProperty() {
        return tcClassPackage;
    }

    public void setTcClassPackage(String tcClassPackage) {
        this.tcClassPackage.set(tcClassPackage);
    }
}

