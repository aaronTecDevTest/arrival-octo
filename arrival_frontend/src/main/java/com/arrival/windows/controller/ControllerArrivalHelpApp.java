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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class ControllerArrivalHelpApp implements Initializable {

    @FXML private Button btnRun;
    @FXML private Button btnStop;
    @FXML private Button btnSkip;

    @FXML private TableView<TestCase> tbvTable;

    @FXML private TableColumn<TestCase, Integer> tbcID;
    @FXML private TableColumn<TestCase, String> tbcName;
    @FXML private TableColumn<TestCase, String> tbcDescription;
    @FXML private TableColumn<TestCase, String> tbcResult;

    private int count = 0;
    public ObservableList date = FXCollections.observableArrayList(
            new TestCase(count++, "Testname1", "Beschrieung3", "Pass"),
            new TestCase(count++, "Testname4", "Beschrieung4", "Pass"),
            new TestCase(count++, "Testname5", "Beschrieung2", "Pass"),
            new TestCase(count++, "Testname8", "Beschrieung1", "Pass")
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
        tbcID.setCellValueFactory(new PropertyValueFactory<TestCase, Integer>("tcID"));
        tbcName.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDescription"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));

        tbvTable.setItems(date);
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
            tbvTable.getItems().add(new TestCase(3, "Pichou ", "test", "pass"));
        }
    }
}
