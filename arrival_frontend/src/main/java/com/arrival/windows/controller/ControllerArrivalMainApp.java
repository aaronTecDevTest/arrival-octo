package com.arrival.windows.controller;

/**
 * Created by Aaron Kutekidila on 09.05.2015.
 **/

import com.arrival.windows.model.TestCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class ControllerArrivalMainApp implements Initializable {

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

    @FXML private TableView<TestCase> tbvIOS;
    @FXML private TableView<TestCase> tbvAndroid;
    @FXML private TableView<TestCase> tbvWebportal;

    @FXML private TableColumn<String, String> tbcIOS;
    @FXML private TableColumn<String, String> tbcAndroid;
    @FXML private TableColumn<String, String> tbcWebportal;

    @FXML private TabPane tbMainTabPane;

    @FXML private TableView<TestCase> tbvTestsuite;
    @FXML private TableColumn<TestCase, String> tbcName;
    @FXML private TableColumn<TestCase, String> tbcDescription;
    @FXML private TableColumn<TestCase, String> tbcDuration;
    @FXML private TableColumn<TestCase, String> tbcLastRun;
    @FXML private TableColumn<TestCase, String> tbcLink;
    @FXML private TableColumn<TestCase, String> tbcResult;

    public ObservableList date = FXCollections.observableArrayList(
            new TestCase("Testname1", "Beschrieung3", "1:22", "27.07.2015","google.com","Pass"),
            new TestCase("Testname4", "Beschrieung4", "1:22", "27.07.2015","google.com","Pass"),
            new TestCase("Testname5", "Beschrieung2", "1:22", "27.07.2015","google.com","Pass"),
            new TestCase("Testname8", "Beschrieung1", "1:22", "27.07.2015","google.com","Pass")
    );

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        tbcName.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDescription"));
        tbcDuration.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDuration"));
        tbcLastRun.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLastRun"));
        tbcLink.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLink"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));

        tbvTestsuite.setItems(date);
    }

    public void clickButtonRun(ActionEvent actionEvent) {
        if (actionEvent.getTarget().toString() == btnRun.toString()) {
        }
    }

    public void clickButtonStop(ActionEvent actionEvent) {
        if ((actionEvent.getTarget().getClass()) == btnStop.getClass()) {
            System.out.println("test13");
        }
    }

    public void clickButtonSkip(ActionEvent actionEvent) {
        if ((actionEvent.getTarget().getClass()) == btnSkip.getClass()) {
            tbvTestsuite.getItems().add(new TestCase("aaron ", "test", "pass","test","test","test"));
        }
    }
}
