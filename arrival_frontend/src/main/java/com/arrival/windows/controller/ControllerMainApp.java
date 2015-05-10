package com.arrival.windows.controller;

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
 * Created by Aaron on 09.05.2015.
 */

public class ControllerMainApp implements Initializable {

    @FXML
    private Button btRun;
    @FXML
    private Button btStop;
    @FXML
    private Button btUpdate;

    @FXML
    private TableView<TestCase> tbvTable;

    @FXML
    private TableColumn<TestCase, Integer> tbcID;
    @FXML
    private TableColumn<TestCase, String> tbcName;
    @FXML
    private TableColumn<TestCase, String> tbcDescription;
    @FXML
    private TableColumn<TestCase, String> tbcResult;

    private  int count =0;
    public ObservableList date = FXCollections.observableArrayList(
        new TestCase(count++,"Testname1","Beschrieung3","Pass"),
        new TestCase(count++,"Testname4","Beschrieung4","Pass"),
        new TestCase(count++,"Testname5","Beschrieung2","Pass"),
        new TestCase(count++,"Testname8","Beschrieung1","Pass")
    );


    public void initialize(URL location, ResourceBundle resources) {
        tbcID.setCellValueFactory(new PropertyValueFactory<TestCase, Integer>("tcID"));
        tbcName.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDescription"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));

        tbvTable.setItems(date);

    }

    public void clickButtonRun(ActionEvent actionEvent) {
        //System.out.println(tbvTable.getItems().get(1).getTcName());
        //if ( actionEvent.getTarget().toString() == btRun.toString()){
        //}
    }

    public void clickButtonStop(ActionEvent actionEvent) {
        if ( (actionEvent.getTarget().getClass()) == btStop.getClass()){
            System.out.println("test13");
        }
    }

    public void clickButtonUpdate(ActionEvent actionEvent){
        if ( (actionEvent.getTarget().getClass()) == btUpdate.getClass()){
            tbvTable.getItems().add(new TestCase(3, "Pichou ", "test", "pass"));
        }
    }
}
