package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.arrival.utilities.FileNameLoader;
import com.arrival.windows.model.TestCase;
import com.arrival.windows.view.ViewArrivalTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class ControllerArrivalMainApp implements Initializable {

    /**
     * Logger
     */
    //TODO: logger implment

    /**
     * For Internationalization
     */
    public ObservableList dateIOSTestcase;
    public ObservableList dateANDTestcase;
    public ObservableList dateWebPortalTestcase;
    public ObservableList dateTestsuite;

    @FXML
    private Label lblSearchfield;
    @FXML
    private Label lblStatusLeft;
    @FXML
    private Label lblStatusRight;


    @FXML
    private Button btnAddTestcase;
    @FXML
    private Button btnCreateTestsuite;
    @FXML
    private Button btnDeletedTestcase;
    @FXML
    private Button btnDeletedTestsuite;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnOpenTestsuite;
    @FXML
    private Button btnOptions;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnResult;
    @FXML
    private Button btnRun;
    @FXML
    private Button btnSaveTestsuite;
    @FXML
    private Button btnSkip;
    @FXML
    private Button btnStop;

    @FXML
    private TabPane tabMainTabPane;
    @FXML
    private Accordion accTestCase;


    @FXML
    private TableView<TestCase> tbvIOS;
    @FXML
    private TableView<TestCase> tbvAND;
    @FXML
    private TableView<TestCase> tbvWebportal;
    @FXML
    private TableView<TestCase> tbvTestsuite;


    @FXML
    private TableColumn<TestCase, String> tbcIOS;
    @FXML
    private TableColumn<TestCase, String> tbcAndroid;
    @FXML
    private TableColumn<TestCase, String> tbcWebportal;

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

    private HashMap<String, ViewArrivalTab> testSuitesTab;
    private ResourceBundle bundle;
    private FileNameLoader fileNameLoaderIOS;
    private FileNameLoader fileNameLoaderAND;
    private FileNameLoader fileNameLoaderWeb;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

        //Ini BundleResources
        bundle = resources;
      //  iniBundleResources();

        //Set up Tablecolmn Propertys
        tbcName.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDescription"));
        tbcDuration.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcDuration"));
        tbcLastRun.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLastRun"));
        tbcLink.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcLink"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));

        tbcIOS.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcAndroid.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcWebportal.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));

        //tbvIOS.getSelectionModel().setCellSelectionEnabled(true);
        tbvIOS.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //tbvAND.getSelectionModel().setCellSelectionEnabled(true);
        tbvAND.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //tbvWebportal.getSelectionModel().setCellSelectionEnabled(true);
        tbvWebportal.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //tbvTestsuite.getSelectionModel().setCellSelectionEnabled(true);
        tbvTestsuite.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Set Up Testcase to Table
        setUpIOSTestcase();
        setUpANDTestcase();
        setUpWebPortalTestcase();
        setUpTestsuite();

        tbvIOS.setItems(dateIOSTestcase);
        tbvAND.setItems(dateANDTestcase);
        tbvWebportal.setItems(dateWebPortalTestcase);
        tbvTestsuite.setItems(dateTestsuite);

        //Set first TitlePane open
        TitledPane ios = accTestCase.getPanes().get(0);
        accTestCase.setExpandedPane(ios);

        //Set Up Testsuite
        testSuitesTab = new HashMap<>();



        /*


        Test
         */

    }


    @FXML
    public void openTestsuite(ActionEvent actionEvent) throws IOException{
        System.out.println(actionEvent.getSource());
        //URL url = getClass().getResource("/fxml/arrivalTab.fxml");
        //final Tab tab = new Tab("Tab" + (tabMainTabPane.getTabs().size()+1));

        System.out.println(tabMainTabPane.getChildrenUnmodifiable().get(0).toString());

//        tabMainTabPane.getTabs().add(tab);
  //      tabMainTabPane.getSelectionModel().select(tab);
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
        try {
            if (accTestCase.getExpandedPane().getText().equals("iOS - Testcase")) {
                System.out.println(actionEvent.getSource() + "ios");
                dateTestsuite.addAll(tbvIOS.getSelectionModel().getSelectedItems());
            }


            if (accTestCase.getExpandedPane().getText().equals("Android - Testcase")) {
                System.out.println(actionEvent.getSource() + "and");
                dateTestsuite.addAll(tbvAND.getSelectionModel().getSelectedItems());

            }

            if (accTestCase.getExpandedPane().getText().equals("Webportal - Testcase")) {
                System.out.println(actionEvent.getSource() + "web");
                dateTestsuite.addAll(tbvWebportal.getSelectionModel().getSelectedItems());
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @FXML
    public void deleteTestcaseFromTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        try {
            ObservableList<Integer> indeces = tbvTestsuite.getSelectionModel().getSelectedIndices();
            for (Integer index : indeces) {
                dateTestsuite.remove((int) index);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @FXML
    public void runTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());

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
    public void showHelp(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    /**
     * Bundle Resources ini
     */
    private void iniBundleResources() {
        lblSearchfield.setText(bundle.getString("label.search"));
        lblStatusLeft.setText(bundle.getString("label.search"));
        lblStatusRight.setText(bundle.getString("label.search"));

        btnAddTestcase.getTooltip().setText(bundle.getString("label.search"));
        btnCreateTestsuite.getTooltip().setText(bundle.getString("label.search"));
        btnDeletedTestcase.getTooltip().setText(bundle.getString("label.search"));
        btnDeletedTestsuite.getTooltip().setText(bundle.getString("label.search"));
        btnHelp.getTooltip().setText(bundle.getString("label.search"));
        btnOpenTestsuite.getTooltip().setText(bundle.getString("label.search"));
        btnOptions.getTooltip().setText(bundle.getString("label.search"));
        btnPause.getTooltip().setText(bundle.getString("label.search"));
        btnResult.getTooltip().setText(bundle.getString("label.search"));
        btnSaveTestsuite.getTooltip().setText(bundle.getString("label.search"));
        btnSkip.getTooltip().setText(bundle.getString("label.search"));
        btnStop.getTooltip().setText(bundle.getString("label.search"));
        btnRun.getTooltip().setText(bundle.getString("label.search"));
    }

    /**
     * No FML Functions
     */

    private void setUpIOSTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        fileNameLoaderIOS = new FileNameLoader("/com/arrival/testCase/iosTestcase", ".class");
        ArrayList<String> fileNames = fileNameLoaderIOS.getClassName();
        ArrayList<String> classPackage = fileNameLoaderIOS.getClassPackage();

        for (int i = 0; i < fileNameLoaderIOS.getSize(); i++) {
            tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
        }
        dateIOSTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpANDTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();

        fileNameLoaderAND = new FileNameLoader("/com/arrival/testCase/andTestcase", ".class");
        ArrayList<String> fileNames = fileNameLoaderAND.getClassName();
        ArrayList<String> classPackage = fileNameLoaderIOS.getClassPackage();

        for (int i = 0; i < fileNameLoaderIOS.getSize(); i++) {
            tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
        }
        dateANDTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpWebPortalTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();

        fileNameLoaderWeb = new FileNameLoader("/com/arrival/testCase/portalTestcase", ".class");
        ArrayList<String> fileNames = fileNameLoaderWeb.getClassName();
        ArrayList<String> classPackage = fileNameLoaderIOS.getClassPackage();

        for (int i = 0; i < fileNameLoaderIOS.getSize(); i++) {
            tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
        }

        dateWebPortalTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpTestsuite() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        dateTestsuite = FXCollections.observableArrayList(tempList);
    }
}
