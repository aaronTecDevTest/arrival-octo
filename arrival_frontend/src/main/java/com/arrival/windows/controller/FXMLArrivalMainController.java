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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.util.*;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalMainController implements Initializable {

    /**
     * Logger
     */
    private static final Logger log =  LogManager.getLogger(FXMLArrivalMainController.class);

    public ObservableList dateIOSTestcase;
    public ObservableList dateANDTestcase;
    public ObservableList dateWebPortalTestcase;
    public ObservableList dateTestsuite;

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
    private FXMLArrivalTableViewController tbvTestsuiteController;
    private FXMLArrivalTableViewController firstTestsuiteController;

    private TableView<TestCase> currentTableView;

    private FXMLArrivalOptionsController optionsController;
    private Stage optionsViewStage;


  //  private HashMap<String, TableView> testSuitesTab;

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

        //Set first TitlePane open
        TitledPane ios = accTestCase.getPanes().get(0);
        accTestCase.setExpandedPane(ios);

        //SetUp Testsuite
        currentTableView = tbvTestsuiteController.getTbvTestsuite();
        firstTestsuiteController = tbvTestsuiteController;
        addTableViewListener();

        //SetUp OptionsView
       // setUpOptionsView();
    }

    @FXML
    public void openTestsuite(ActionEvent actionEvent) throws IOException{
        log.info(actionEvent.getSource());
    }

    @FXML
    public void saveTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void createNewTestsuite(ActionEvent actionEvent) throws IOException{
        log.info(actionEvent.getSource());

        URL url = this.getClass().getResource("/fxml/FXMLArrivalTableView.fxml");
        FXMLLoader loader = new FXMLLoader(null,bundle);
        TableView testSuiteTable =  loader.load(url.openStream());
        Tab tab = new Tab("", testSuiteTable);
        FXMLArrivalTableViewController controller = loader.getController();
        testSuiteTable.setUserData(controller);
        tab.setContent(testSuiteTable);

        //Dialog für Testsuitename
        String tabName = setTestsuiteNameDialog();

        if (tabName.isEmpty()) {
            tab.setText("Testsuite -" + " " + tabMainTabPane.getTabs().size());
        }
        else {
            tab.setText(tabName);
        }

        tabMainTabPane.getTabs().addAll(tab);
        tabMainTabPane.getSelectionModel().select(tab);
    }

    @FXML
    public void deletedTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void addTestcaseInTestsuite(ActionEvent actionEvent) {
        try {

            if (accTestCase.getExpandedPane().getText().equals("iOS - Testcase")) {
                log.info(actionEvent.getSource() + "ios");
                dateTestsuite = currentTableView.getItems();
                dateTestsuite.addAll(tbvIOS.getSelectionModel().getSelectedItems());
            }

            if (accTestCase.getExpandedPane().getText().equals("Android - Testcase")) {
                log.info(actionEvent.getSource() + "and");
                dateTestsuite = currentTableView.getItems();
                dateTestsuite.addAll(tbvAND.getSelectionModel().getSelectedItems());
            }

            if (accTestCase.getExpandedPane().getText().equals("Web-Portal - Testcase")) {
                log.info(actionEvent.getSource() + "web");
                dateTestsuite = currentTableView.getItems();
                dateTestsuite.addAll(tbvWebPortal.getSelectionModel().getSelectedItems());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteTestcaseFromTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        try {
            
            ObservableList<Integer> indeces = currentTableView.getSelectionModel().getSelectedIndices();
            ObservableList<TestCase> testCases = currentTableView.getSelectionModel().getSelectedItems();

            currentTableView = (TableView<TestCase>)tabMainTabPane.getSelectionModel().getSelectedItem().getContent();
            ObservableList dateTestsuite = currentTableView.getItems();
            dateTestsuite.addAll(tbvWebPortal.getSelectionModel().getSelectedItems());

            if (indeces.size() == dateTestsuite.size()){
                dateTestsuite.removeAll(testCases);
            }
            else {
                for (TestCase testCase : testCases) {
                    dateTestsuite.remove(testCase);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void runTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.runTestSuite();
    }

    @FXML
    public void skipTestcase(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void pauseTestcase(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void stopTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void showTestsuiteResult(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void showOptions(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        optionsViewStage = setUpOptionsView();
        optionsViewStage.hide();
        optionsViewStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        log.warn(((Node)actionEvent.getSource()).getScene().getWindow() );
        optionsViewStage.show();
    }

    @FXML
    public void showHelp(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
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

        fileNameLoaderWeb = new FileNameLoader("/com/arrival/testCase/webTestcase", ".class");
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

    private void addTableViewListener(){
        tabMainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                TableView tempTableView  = (TableView) tabMainTabPane
                        .getSelectionModel()
                        .getSelectedItem()
                        .getContent();

                currentTableView = tempTableView;

                if(tempTableView.getUserData() == null){
                    currentTableView =  tempTableView;
                    tbvTestsuiteController = firstTestsuiteController;
                }
                else {
                    currentTableView =  tempTableView;
                    tbvTestsuiteController = (FXMLArrivalTableViewController)tempTableView.getUserData();
              }
            }
        });
    }

    private Stage setUpOptionsView() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResource("bundles/arrivalOptions_de.properties").openStream();
            ResourceBundle bundle = new PropertyResourceBundle(inputStream);

            URL url = getClass().getResource("/fxml/FXMLArrivalOptions.fxml");
            FXMLLoader loader = new FXMLLoader(url, bundle);

            Parent root = loader.load();
            Scene optionsScene = new Scene(root, 460, 265);
            Stage optionsStage = new Stage();

            optionsScene.getStylesheets().add("/css/arrivalMain.css");
            optionsStage.setScene(optionsScene);
            optionsStage.setTitle("Options...");
            optionsStage.setResizable(false);
            optionsStage.initModality(Modality.APPLICATION_MODAL);
            optionsController = loader.getController();
            log.warn(optionsStage);
            return  optionsStage;

        }catch (IOException e){
            log.error(e.getStackTrace());
            return null;
        }
    }
}
