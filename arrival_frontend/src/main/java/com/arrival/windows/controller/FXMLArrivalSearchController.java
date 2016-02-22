package com.arrival.windows.controller;

import com.arrival.windows.model.TestCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.02.2016.
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */
public class FXMLArrivalSearchController implements Initializable{
    private static final Logger log = LogManager.getLogger(FXMLArrivalSearchController.class);


    public ObservableList<TestCase> dataFilterAndSearch;

    private ResourceBundle bundle;

    @FXML
    private Accordion accTestCaseSearch;
    @FXML
    private TableColumn tbcSearch;
    @FXML
    private TableView<TestCase> tbvSearch;
    @FXML
    private TitledPane tpnSearch;



    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        iniBundleResources();

        dataFilterAndSearch = FXCollections.observableArrayList();

        tbcSearch.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));

        //tbvSearch.getSelectionModel().setCellSelectionEnabled(true);
        tbvSearch.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbvSearch.getStyleClass().add("table-right");

       // tbvSearch.setItems(dataFilterAndSearch);
    }
    public Accordion getAccTestCaseSearch() {
        return accTestCaseSearch;
    }

    public void setAccTestCaseSearch(Accordion accTestCaseSearch) {
        this.accTestCaseSearch = accTestCaseSearch;
    }

    public TableColumn getTbcSearch() {
        return tbcSearch;
    }

    public void setTbcSearch(TableColumn tbcSearch) {
        this.tbcSearch = tbcSearch;
    }

    public TableView<TestCase> getTbvSearch() {
        return tbvSearch;
    }

    public void setTbvSearch(TableView<TestCase> tbvSearch) {
        this.tbvSearch = tbvSearch;
    }

    public TitledPane getTpnSearch() {
        return tpnSearch;
    }

    public void setTpnSearch(TitledPane tpnSearch) {
        this.tpnSearch = tpnSearch;
    }

    /**
     * Bundle Resources ini
     */
    private void iniBundleResources() {
    }
}
