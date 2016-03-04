package com.arrival.windows.model;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.model
 */

import com.arrival.utilities.ArrivalResult;
import com.arrival.utilities.interfaces.IFTestCase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestCase implements IFTestCase {

    private SimpleIntegerProperty tcID;
    private SimpleStringProperty tcName;
    private SimpleStringProperty tcDescription;
    private SimpleStringProperty tcDuration;
    private SimpleStringProperty tcLastRun;
    private SimpleStringProperty tcLink;
    private SimpleStringProperty tcClassPackage;
    private SimpleStringProperty tcResult;
    private SimpleObjectProperty<ImageView> tcResultIcons;


    public SimpleIntegerProperty nameProperty() {
        if (tcID == null) {
            tcID = new SimpleIntegerProperty(this, "id");
        }
        return tcID;
    }
    public SimpleStringProperty emailProperty() {
        if (tcName == null) {
            tcName = new SimpleStringProperty(this, "name");
        }
        return tcName;
    }



    public TestCase() {
        this(0, "", "", "", "", "", "", "", null);
    }

    public TestCase(Integer id, String tcName, String tcDescription, String tcResult, String tcDuration, String tcLastRun, String tcLink, String tcClassPackage, ImageView tcResultIcons) {
        this.tcID = new SimpleIntegerProperty(id);
        this.tcName = new SimpleStringProperty(tcName);
        this.tcDescription = new SimpleStringProperty(tcDescription);
        this.tcDuration = new SimpleStringProperty(tcDuration);
        this.tcLastRun = new SimpleStringProperty(tcLastRun);
        this.tcLink = new SimpleStringProperty(tcLink);
        this.tcResult = new SimpleStringProperty(tcResult);
        this.tcClassPackage = new SimpleStringProperty(tcClassPackage);
        this.tcResultIcons =  new SimpleObjectProperty<ImageView>(tcResultIcons) ;
    }

    public TestCase(String tcName, String tcDescription, String tcResult, String tcDuration, String tcLastRun, String tcLink, String tcClassPackage, ImageView tcResultIcons) {
        this.tcName = new SimpleStringProperty(tcName);
        this.tcDescription = new SimpleStringProperty(tcDescription);
        this.tcDuration = new SimpleStringProperty(tcDuration);
        this.tcLastRun = new SimpleStringProperty(tcLastRun);
        this.tcLink = new SimpleStringProperty(tcLink);
        this.tcResult = new SimpleStringProperty(tcResult);
        this.tcClassPackage = new SimpleStringProperty(tcClassPackage);
        this.tcResultIcons =  new SimpleObjectProperty<ImageView>(tcResultIcons) ;
    }

    public TestCase(TestCase testCase) {
        this.tcName = new SimpleStringProperty(testCase.getTcName());
        this.tcDescription = new SimpleStringProperty(testCase.getTcDescription());
        this.tcDuration = new SimpleStringProperty(testCase.getTcDuration());
        this.tcLastRun = new SimpleStringProperty(testCase.getTcLastRun());
        this.tcLink = new SimpleStringProperty(testCase.getTcLink());
        this.tcResult = new SimpleStringProperty(testCase.getTcResult());
        this.tcClassPackage = new SimpleStringProperty(testCase.getTcClassPackage());
        this.setTcResultIcons(getResultImageViewer(getTcResult()));
       // this.tcResultIcons =  new SimpleObjectProperty<ImageView>(testCase.getTcResultIcons()) ;
    }

    public static ObservableList<TestCase> copyTestCases(ObservableList<TestCase> testCasesList){
        ObservableList<TestCase> tempTestCases = FXCollections.observableArrayList();

       for (TestCase testCase: testCasesList){
           tempTestCases.add(new TestCase(testCase));
       }
        return tempTestCases;
    }

    public static TestCase copyTestCase(TestCase testCase){
        return new TestCase(testCase);
    }

    @Override
    public String getTcName() {
        return tcName.get();
    }

    @Override
    public void setTcName(String tcName) {
        this.tcName.set(tcName);
    }

    @Override
    public SimpleStringProperty tcNameProperty() {
        return tcName;
    }

    @Override
    public int getTcID() {
        return tcID.get();
    }

    @Override
    public void setTcID(int tcID) {
        this.tcID.set(tcID);
    }

    @Override
    public SimpleIntegerProperty tcIDProperty() {
        return tcID;
    }

    @Override
    public String getTcDescription() {
        return tcDescription.get();
    }

    @Override
    public void setTcDescription(String tcDescription) {
        this.tcDescription.set(tcDescription);
    }

    @Override
    public SimpleStringProperty tcDescriptionProperty() {
        return tcDescription;
    }

    @Override
    public String getTcDuration() {
        return tcDuration.get();
    }

    public void setTcDuration(String tcDuration) {
        this.tcDuration.set(tcDuration);
    }

    @Override
    public SimpleStringProperty tcDurationProperty() {
        return tcDuration;
    }

    @Override
    public String getTcLastRun() {
        return tcLastRun.get();
    }

    @Override
    public void setTcLastRun(String tcLastRun) {
        this.tcLastRun.set(tcLastRun);
    }

    @Override
    public SimpleStringProperty tcLastRunProperty() {
        return tcLastRun;
    }

    @Override
    public String getTcResult() {
        return tcResult.get();
    }

    @Override
    public void setTcResult(ArrivalResult tcResult) {

        this.tcResult.set(tcResult.toString());
        this.setTcResultIcons(getResultImageViewer(tcResult.toString()));
    }

    @Override
    public SimpleStringProperty tcResultProperty() {
        return tcResult;
    }

    @Override
    public String getTcLink() {
        return tcLink.get();
    }

    @Override
    public void setTcLink(String tcLink) {
        this.tcLink.set(tcLink);
    }

    @Override
    public SimpleStringProperty tcLinkProperty() {
        return tcLink;
    }

    public String getTcClassPackage() {
        return tcClassPackage.get();
    }

    @Override
    public void setTcClassPackage(String tcClassPackage) {
        this.tcClassPackage.set(tcClassPackage);
    }

    public SimpleStringProperty tcClassPackageProperty() {
        return tcClassPackage;
    }

    @Override
    public ImageView getTcResultIcons() {
        return tcResultIcons.get();
    }

    @Override
    public void setTcResultIcons(ImageView tcResultIcons) {
        this.tcResultIcons = new SimpleObjectProperty<ImageView>(tcResultIcons);
    }


    private  ImageView getResultImageViewer(String tcResult){
        ImageView imageView = new ImageView();

        switch (tcResult){
            case "PASSED":
                imageView.setImage(new Image(getClass().getResource("/icons/passed.png").toString()));
                return  imageView;
            case "FAILED":
                imageView.setImage(new Image(getClass().getResource("/icons/failed.png").toString()));
                return  imageView;
            case "SKIPPED":
                imageView.setImage(new Image(getClass().getResource("/icons/skipped.png").toString()));
                return  imageView;
            default:
                imageView.setImage(new Image(getClass().getResource("/icons/default.png").toString()));
                return  imageView;
        }
    }
}