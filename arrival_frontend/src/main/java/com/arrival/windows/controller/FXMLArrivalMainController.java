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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalMainController implements Initializable {

    /**
     * Logger
     */
    //TODO: logger implment

    public ObservableList dateIOSTestcase;
    public ObservableList dateANDTestcase;
    public ObservableList dateWebPortalTestcase;

    /**
     * For Internationalization
     */
    private ResourceBundle bundle;

    @FXML
    private Label lblSearchField;
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
    private TableView<TestCase> tbvWebPortal;

    @FXML
    private TableColumn<TestCase, String> tbcIOS;
    @FXML
    private TableColumn<TestCase, String> tbcAndroid;
    @FXML
    private TableColumn<TestCase, String> tbcWebPortal;

    @FXML
   // private FXMLArrivalTableViewController tbvTestsuite;

    private TableView<TestCase> view;

    private HashMap<String, ViewArrivalTab> testSuitesTab;

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
        iniBundleResources();

        //Setup Tablecolmn Propertys
        tbcIOS.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcAndroid.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcWebPortal.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));

        //tbvIOS.getSelectionModel().setCellSelectionEnabled(true);
        tbvIOS.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //tbvAND.getSelectionModel().setCellSelectionEnabled(true);
        tbvAND.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //tbvWebPortal.getSelectionModel().setCellSelectionEnabled(true);
        tbvWebPortal.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //SetUp Testcase to Table
        setUpIOSTestcase();
        setUpANDTestcase();
        setUpWebPortalTestcase();
        //setUpTestsuite();

        tbvIOS.setItems(dateIOSTestcase);
        tbvAND.setItems(dateANDTestcase);
        tbvWebPortal.setItems(dateWebPortalTestcase);
       // tbvTestsuite.setItems(dateTestsuite);

        //Set first TitlePane open
        TitledPane ios = accTestCase.getPanes().get(0);
        accTestCase.setExpandedPane(ios);

        //SetUp Testsuite
        testSuitesTab = new HashMap<>();

//        view = tbvTestsuite.getTbvTestsuite();
    }


    @FXML
    public void openTestsuite(ActionEvent actionEvent) throws IOException{
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void saveTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
    }

    @FXML
    public void createNewTestsuite(ActionEvent actionEvent) throws IOException{
        System.out.println(actionEvent.getSource());

        URL url = this.getClass().getResource("/fxml/FXMLArrivalTableView.fxml");
        TableView testSuiteTable =  FXMLLoader.load(url);
        //ViewArrivalTab tab = new ViewArrivalTab("", testSuiteTable);
        Tab tab = new Tab("", testSuiteTable);
        //tab.setTableView(testSuiteTable);
        tab.setContent(testSuiteTable);
        //Dialog für Testsuitename
        String tabName = setTestsuiteNameDialog();

        if (tabName.isEmpty()) {
            tab.setText("Testsuite -" + " " + tabMainTabPane.getTabs().size());
        //    testSuitesTab.put(tab.getText(), tab);
        }
        else {
            tab.setText(tabName);
          //  testSuitesTab.put(tabName,tab);
        }

        tabMainTabPane.getTabs().addAll(tab);
        tabMainTabPane.getSelectionModel().select(tab);
    }

    @FXML
    public void deletedTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());

    }

    @FXML
    public void addTestcaseInTestsuite(ActionEvent actionEvent) {
        try {
            /*
            if (accTestCase.getExpandedPane().getText().equals("iOS - Testcase")) {
                System.out.println(actionEvent.getSource() + "ios");
                tbvTestsuite = (TableView<TestCase>)tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
                ObservableList dateTestsuite = tbvTestsuite.getItems();
                dateTestsuite.addAll(tbvIOS.getSelectionModel().getSelectedItems());
            }

            if (accTestCase.getExpandedPane().getText().equals("Android - Testcase")) {
                System.out.println(actionEvent.getSource() + "and");
                tbvTestsuite = (TableView<TestCase>)tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
                ObservableList dateTestsuite = tbvTestsuite.getItems();
                dateTestsuite.addAll(tbvAND.getSelectionModel().getSelectedItems());
            }

            if (accTestCase.getExpandedPane().getText().equals("Web-Portal - Testcase")) {
                System.out.println(actionEvent.getSource() + "web");
                tbvTestsuite = (TableView<TestCase>)tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
                ObservableList dateTestsuite = tbvTestsuite.getItems();
                dateTestsuite.addAll(tbvWebPortal.getSelectionModel().getSelectedItems());

            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteTestcaseFromTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        try {
            /*
            ObservableList<Integer> indeces = tbvTestsuite.getSelectionModel().getSelectedIndices();
            ObservableList<TestCase> testCases = tbvTestsuite.getSelectionModel().getSelectedItems();

            tbvTestsuite = (TableView<TestCase>)tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
            ObservableList dateTestsuite = tbvTestsuite.getItems();
            dateTestsuite.addAll(tbvWebPortal.getSelectionModel().getSelectedItems());

            if (indeces.size() == dateTestsuite.size()){
                dateTestsuite.removeAll(testCases);
            }
            else {
                for (TestCase testCase : testCases) {
                    dateTestsuite.remove(testCase);
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void runTestsuite(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        /*ViewArrivalTab currentTab = (ViewArrivalTab)tabMainTabPane.getSelectionModel().getSelectedItem();
        currentTab.runTestSuite();*/
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
    public void showTestsuiteResult(ActionEvent actionEvent) {
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
        lblSearchField.setText(bundle.getString("label.search"));
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
        ArrayList<String> classPackage = fileNameLoaderAND.getClassPackage();

        for (int i = 0; i < fileNameLoaderAND.getSize(); i++) {
            tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
        }
        dateANDTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpWebPortalTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();

        fileNameLoaderWeb = new FileNameLoader("/com/arrival/testCase/portalTestcase", ".class");
        ArrayList<String> fileNames = fileNameLoaderWeb.getClassName();
        ArrayList<String> classPackage = fileNameLoaderWeb.getClassPackage();

        for (int i = 0; i < fileNameLoaderWeb.getSize(); i++) {
            tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
        }

        dateWebPortalTestcase = FXCollections.observableArrayList(tempList);
    }

    private String setTestsuiteNameDialog(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Testsuite name");
        dialog.setHeaderText("Bitte den Name des Testsuite eintragen:");

        Optional<String> result = dialog.showAndWait();
        String entered;

        if (result.isPresent()) {
            entered = result.get();
        }
        else{
            entered = "";
        }
        return entered;
    }
/*
    private void setupFirstTestsuite()  {
        try{

            URL url = this.getClass().getResource("/fxml/FXMLArrivalTableView.fxml");
            TableView testSuiteTable = FXMLLoader.load(url);

            ViewArrivalTab tab = new ViewArrivalTab("Testsuite - Regressionstest", testSuiteTable);
            tab.setTableView(testSuiteTable);
            tabMainTabPane.getTabs().add(tab);
            tabMainTabPane.getSelectionModel().select(tab);

            testSuitesTab.put("Testsuite - Regressionstest", tab);

            tabMainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
                @Override
                public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                    TableView<TestCase> selectedTableview = (TableView<TestCase>) tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
                    tbvTestsuite = selectedTableview;
                    dateTestsuite = selectedTableview.getItems();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tabSelected() {
        TableView <TestCase> selectedTableView = (TableView <TestCase>)tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
        tbvTestsuite = selectedTableView;
        dateTestsuite = selectedTableView.getItems();
    }*/
}
