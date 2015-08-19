package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.arrival.selenium.config.SeleniumConfig;
import com.arrival.unit.generic.SeleniumConfigSingleton;
import com.arrival.unit.suites.ArrivalTestSuite;
import com.arrival.utilities.interfaces.IFConfig;
import com.arrival.windows.model.Options;
import com.arrival.windows.model.TestCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.xml.XmlClass;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalTableViewController implements Initializable {

    /**
     * Logger
     */
    private static final Logger log =  LogManager.getLogger(FXMLArrivalTableViewController.class);

    /**
     * For Internationalization
     */
    private ResourceBundle bundle;

    public ObservableList dateTestsuite;

    @FXML
    private TableView<TestCase> tbvTestsuite;
    @FXML
    private TableColumn<TestCase, String> tbcName;
    @FXML
    private TableColumn<TestCase, String> tbcDescription;
    @FXML
    private TableColumn<TestCase, String> tbcDuration;
    @FXML
    private TableColumn<TestCase, String> tbcLastRun;
    @FXML
    private TableColumn<TestCase, String> tbcLink;
    @FXML
    private TableColumn<TestCase, String> tbcResult;

    private ArrivalTestSuite runableTestSuite;
    private ObservableList dateTestSuite;
    private Options optionsTestSuite;
    /**
     *Is a path where are the index of testng result.
     */
    private String resultTestSuite;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        iniBundleResources();

        //Setup Tablecolmn Propertys
        tbcName.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDescription"));
        tbcDuration.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDuration"));
        tbcLastRun.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLastRun"));
        tbcLink.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLink"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));

        //tbvTestsuite.getSelectionModel().setCellSelectionEnabled(true);
        tbvTestsuite.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //SetUp Testcase to Table
        setUpTestsuite();
        tbvTestsuite.setItems(dateTestsuite);

        runableTestSuite = new ArrivalTestSuite();

        //SetUp new OptionsView
    }



    public void runTestSuite(){
        List<XmlClass> tempClasses = new ArrayList<>();
        SeleniumConfigSingleton.getInstance().setTestSuiteConfiguration(optionsTestSuite);
        dateTestSuite = tbvTestsuite.getItems();

        tempClasses.add(new XmlClass("com.arrival.unit.generic.SeleniumConfigSingleton"));

        for(int i=0; i<dateTestsuite.size();i++){
            tempClasses.add(new XmlClass(((TestCase) dateTestSuite.get(i)).getTcClassPackage()));
        }
        runableTestSuite.setClasses(tempClasses);
        runableTestSuite.runVirtualSuit();
    }

    private void iniBundleResources() {
        tbcDescription.setText(bundle.getString("tableColumn.description"));
        tbcName.setText(bundle.getString("tableColumn.name"));
        tbcDuration.setText(bundle.getString("tableColumn.duration"));
        tbcLastRun.setText(bundle.getString("tableColumn.lastRun"));
        tbcLink.setText(bundle.getString("tableColumn.link"));
        tbcResult.setText(bundle.getString("tableColumn.result"));
    }

    /**
     * No FML Functions
     */
    private void setUpTestsuite() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        dateTestsuite = FXCollections.observableArrayList(tempList);
    }

    public TableView<TestCase> getTbvTestsuite(){
        return tbvTestsuite;
    }
}
