package com.arrival.windows.controller;

/**
 * Created by Aaron Kutekidila on 09.05.2015.
 **/

import com.arrival.utilities.FileNameLoader;
import com.arrival.windows.model.TestCase;
import com.arrival.windows.model.TestSuite;
import com.sun.beans.finder.ClassFinder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import  javafx.scene.control.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class ControllerArrivalMainApp implements Initializable {

    /**
     * For Internationalization
     */

    @FXML private Label lblSearchfield;
    @FXML private Label lblStatusLeft;
    @FXML private Label  lblStatusRight;


    @FXML private Button btnAddTestcase;
    @FXML private Button btnCreateTestsuite;
    @FXML private Button btnDeletedTestcase;
    @FXML private Button btnDeletedTestsuite;
    @FXML private Button btnHelp;
    @FXML private Button btnOpenTestsuite;
    @FXML private Button btnOptions;
    @FXML private Button btnPause;
    @FXML private Button btnResult;
    @FXML private Button btnRun;
    @FXML private Button btnSaveTestsuite;
    @FXML private Button btnSkip;
    @FXML private Button btnStop;

    @FXML private TabPane tbMainTabPane;
    @FXML private Accordion accTestCase;


    @FXML private TableView<TestCase> tbvIOS;
    @FXML private TableView<TestCase> tbvAND;
    @FXML private TableView<TestCase> tbvWebportal;
    @FXML private TableView<TestCase> tbvTestsuite;


    @FXML private TableColumn<TestCase, String> tbcIOS;
    @FXML private TableColumn<TestCase, String> tbcAndroid;
    @FXML private TableColumn<TestCase, String> tbcWebportal;

    @FXML private TableColumn<TestCase, String> tbcName;
    @FXML private TableColumn<TestCase, String> tbcDescription;
    @FXML private TableColumn<TestCase, String> tbcDuration;
    @FXML private TableColumn<TestCase, String> tbcLastRun;
    @FXML private TableColumn<TestCase, String> tbcLink;
    @FXML private TableColumn<TestCase, String> tbcResult;

    private ArrayList<TestSuite> testSuites;
    private ResourceBundle bundle;

    public ObservableList dateIOSTestcase;
    public ObservableList dateANDTestcase;
    public ObservableList dateWebPortalTestcase;
    public ObservableList dateTestsuite;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

        //ini BundleResources
        bundle = resources;
        lblSearchfield.setText(bundle.getString("label.search"));

        tbcName.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDescription"));
        tbcDuration.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDuration"));
        tbcLastRun.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLastRun"));
        tbcLink.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLink"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));

        tbcIOS.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcAndroid.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcWebportal.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));

        setUpIOSTestcase();
        setUpANDTestcase();
        setUpWebPortalTestcase();
        setUpTestsuite();

        tbvIOS.setItems(dateIOSTestcase);
        tbvAND.setItems(dateANDTestcase);
        tbvWebportal.setItems(dateWebPortalTestcase);
        tbvTestsuite.setItems(dateTestsuite);

        TitledPane ios = accTestCase.getPanes().get(0);
        accTestCase.setExpandedPane(ios);
    }


    @FXML
    public void openTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void saveTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }
    @FXML
    public void createNewTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void deletedTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());

    }
    @FXML
    public void addTestcaseInTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        //tbvTestsuite.getItems().add(new TestCase("aaron ", "test", "pass","test","test","test"));
        dateTestsuite.add(new TestCase("aaron ", "test", "pass","test","test","test"));
    }

    @FXML
    public void deleteTestcaseFromTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void runTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        if ((actionEvent.getTarget().getClass()) == btnSkip.getClass()) {

        }
    }

    @FXML
     public void skipTestcase(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void pauseTestcase(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void stopTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void showTestsuitResult(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }
    @FXML
    public void showOptions(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void showhelp(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }


    /**
     * No FML Functions
     */

    private void setUpIOSTestcase(){
        ArrayList<TestCase> tempList = new ArrayList<>();
        FileNameLoader fileNameLoader = new FileNameLoader("/com/arrival/testCase/iosTestcase", ".class");
        ArrayList<String> fileNames = fileNameLoader.getFileName();

        for(String fileName: fileNames) {
            tempList.add(new TestCase(fileName,"",",","","",""));
        }
/*
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
*/
        dateIOSTestcase = FXCollections.observableArrayList(tempList);

    }

    private void setUpANDTestcase(){
        ArrayList<TestCase> tempList = new ArrayList<>();

        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));

        dateANDTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpWebPortalTestcase(){
        ArrayList<TestCase> tempList = new ArrayList<>();

        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));

        dateWebPortalTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpTestsuite() {
        ArrayList<TestCase> tempList = new ArrayList<>();

        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));
        tempList.add( new TestCase("Testname8555", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass"));

        dateTestsuite = FXCollections.observableArrayList(tempList);
    }
}
