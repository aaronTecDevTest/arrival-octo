package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.arrival.utilities.FileNameLoader;
import com.arrival.utilities.SystemPreferences;
import com.arrival.utilities.WindowsDialogs;
import com.arrival.utilities.interfaces.IFTestCase;
import com.arrival.windows.model.TestCase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalMainController implements Initializable {

    /**
     * Logger
     */
    private static final Logger log = LogManager.getLogger(FXMLArrivalMainController.class);

    public ObservableList <TestCase> dataIOSTestcase;
    public ObservableList <TestCase> dataANDTestcase;
    public ObservableList <TestCase> dataWebPortalTestcase;
    public ObservableList <TestCase> dataFilterAndSearch;
    public ObservableList <TestCase> dataTestsuite;

    /**
     * For Internationalization
     */
    private ResourceBundle bundle;

    @FXML
    private Menu mnuFile;
    @FXML
    private Menu mnuEdit;
    @FXML
    private Menu mnuHelp;
    @FXML
    private Menu mnuOpenRecent;
    @FXML
    private MenuItem mnuAbout;
    @FXML
    private MenuItem mnuClose;
    @FXML
    private MenuItem mnuCopy;
    @FXML
    private MenuItem mnuCut;
    @FXML
    private MenuItem mnuDelete;
    @FXML
    private MenuItem mnuOpen;
    @FXML
    private MenuItem mnuPaste;
    @FXML
    private MenuItem mnuPreferences;
    @FXML
    private MenuItem mnuQuit;
    @FXML
    private MenuItem mnuRedo;
    @FXML
    private MenuItem mnuRevert;
    @FXML
    private MenuItem mnuSave;
    @FXML
    private MenuItem mnuSaveAs;
    @FXML
    private MenuItem mnuSelectAll;
    @FXML
    private MenuItem mnuNew;
    @FXML
    private MenuItem mnuUndo;
    @FXML
    private MenuItem mnuUnSelectAll;


    @FXML
    private Label lblSearchField;
    @FXML
    private Label lblStatusLeft;
    @FXML
    private Label lblStatusRight;


    @FXML
    private Button btnAddTestcase;
    @FXML
    private Button btnNewTestsuite;
    @FXML
    private Button btnRemoveTestcase;
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
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnStepUp;
    @FXML
    private Button btnStepDown;


    @FXML
    private VBox vBoxTestcase;
    @FXML
    private TabPane tabMainTabPane;
    @FXML
    private Accordion accTestCaseMain;
    private Accordion  accTestCaseMainSearch;


    @FXML
    private TableView<TestCase> tbvIOS;
    @FXML
    private TableView<TestCase> tbvAND;
    @FXML
    private TableView<TestCase> tbvWEB;
    @FXML
    private TableView<TestCase> tbvSearch;

    @FXML
    private TitledPane tpnIOS;
    @FXML
    private TitledPane tpnAND;
    @FXML
    private TitledPane tpnWEB;
    @FXML
    private TitledPane tpnSearch;


    @FXML
    private TableColumn<TestCase, String> tbcIOS;
    @FXML
    private TableColumn<TestCase, String> tbcAndroid;
    @FXML
    private TableColumn<TestCase, String> tbcWebPortal;
    @FXML
    private TableColumn<TestCase, String> tbcSearch;

    @FXML
    private TextField txtSearchField;





    private FXMLArrivalTableViewController tbvTestsuiteController;
    private TableView<TestCase> currentTableView;

    private FXMLArrivalOptionsController optionsController;
    private Stage optionsViewStage;

    private FileNameLoader fileNameLoaderIOS;
    private FileNameLoader fileNameLoaderAND;
    private FileNameLoader fileNameLoaderWeb;

    private FilteredList<TestCase> filteredDate;


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
        iniAccTestCaseMain();

        //Setup Table-Data Objects
        dataTestsuite = FXCollections.observableArrayList();
        dataANDTestcase = FXCollections.observableArrayList();
        dataIOSTestcase = FXCollections.observableArrayList();
        dataFilterAndSearch = FXCollections.observableArrayList();
        dataWebPortalTestcase = FXCollections.observableArrayList();

        //Setup Table-Column Properties
        tbcIOS.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcAndroid.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcWebPortal.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));
        tbcSearch.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcName"));

        //tbvIOS.getSelectionModel().setCellSelectionEnabled(true);
        tbvIOS.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbvIOS.getStyleClass().add("table-right");

        //tbvAND.getSelectionModel().setCellSelectionEnabled(true);
        tbvAND.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbvAND.getStyleClass().add("table-right");

        //tbvWEB.getSelectionModel().setCellSelectionEnabled(true);
        tbvWEB.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbvWEB.getStyleClass().add("table-right");

        //tbvSearch.getSelectionModel().setCellSelectionEnabled(true);
        //tbvSearch.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //tbvSearch.getStyleClass().add("table-right");

        //SetUp Testcase to Table
        setUpIOSTestcase();
        setUpANDTestcase();
        setUpWebPortalTestcase();
        setUpSearchTestcase();

        tbvIOS.setItems(dataIOSTestcase);
        tbvAND.setItems(dataANDTestcase);
        tbvWEB.setItems(dataWebPortalTestcase);
//      tbvSearch.setItems(dataFilterAndSearch);

        //Set first TitlePane open
        accTestCaseMain.setExpandedPane(tpnIOS);

        //SetUp Testsuite
        setUpFirstTableView();

        //Listener
        addDataListener();
        addTableViewListener();
        addSearchFieldListener();

        //currentTableView.getStyleClass().add("/css/arrivalMain.css");
    }

    @FXML
    public void openTestsuite(ActionEvent actionEvent) throws IOException {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void saveTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void createNewTestsuite(ActionEvent actionEvent) throws IOException {
        log.info(actionEvent.getSource());

        URL url = this.getClass().getResource("/fxml/FXMLArrivalTableView.fxml");
        FXMLLoader loader = new FXMLLoader(null, bundle);
        TableView testSuiteTable = loader.load(url.openStream());
        Tab tab = new Tab(null, testSuiteTable);
        Label tabLabel = new Label();
        tab.setGraphic(tabLabel);

        tabLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        String tabName = "Regression Test";
                        if (mouseEvent.getSource() == tabLabel)
                            tabName = WindowsDialogs.setTestsuiteNameDialog();

                        if (! tabName.isEmpty()) {
                            tabLabel.setText(tabName);
                        }
                    }
                }
            }
        });

        FXMLArrivalTableViewController controller = loader.getController();
        testSuiteTable.setUserData(controller);
        tab.setContent(testSuiteTable);

        //Dialog für Testsuitename
        String tabName;
        if (actionEvent.getSource() == btnNewTestsuite)
            tabName = WindowsDialogs.setTestsuiteNameDialog();
        else
            tabName = "Regression Test";

        if (tabName.isEmpty()) {
            tabLabel.setText("Testsuite -" + " " + tabMainTabPane.getTabs().size());
        } else {
            tabLabel.setText(tabName);
        }

        tabMainTabPane.getTabs().addAll(tab);
        tabMainTabPane.getSelectionModel().select(tab);
    }

    @FXML
    public void deletedTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        if(tabMainTabPane.getTabs().size() > 1){
        //Tab selecetedTab = tabMainTabPane.getSelectionModel().getSelectedItem();
        tabMainTabPane.getTabs().remove(tabMainTabPane.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void addTestcaseInTestsuite(ActionEvent actionEvent) {
        try {

            if (accTestCaseMain.getExpandedPane().getText().equals("iOS - Testcase")) {
                log.info(actionEvent.getSource() + "IOS");
                if (/*dataTestsuite.isEmpty() &&*/ tbvTestsuiteController.getPlatform().equals("platform")) {
                    tbvTestsuiteController.setPlatform("IOS");
                }

                if (tbvTestsuiteController.isIOSPlatform()) {
                    dataTestsuite = currentTableView.getItems();
                    if (!(dataTestsuite.containsAll(tbvIOS.getSelectionModel().getSelectedItems()))) {
                        dataTestsuite.addAll(tbvIOS.getSelectionModel().getSelectedItems());
                    } else {
                        log.warn("Testcase all ready added!");
                        WindowsDialogs.testCaseInTestsuite();
                    }
                } else {
                    log.warn("Is not a IOS Testcase");
                    WindowsDialogs.wrongPlatform(tbvTestsuiteController.getPlatform());
                }
            }

            if (accTestCaseMain.getExpandedPane().getText().equals("Android - Testcase")) {
                log.info(actionEvent.getSource() + "AND");
                if (/*dataTestsuite.isEmpty() &&*/ tbvTestsuiteController.getPlatform().equals("platform")) {
                    tbvTestsuiteController.setPlatform("Android");
                }

                if (tbvTestsuiteController.isANDPlatform()) {
                    dataTestsuite = currentTableView.getItems();
                    if (!(dataTestsuite.containsAll(tbvAND.getSelectionModel().getSelectedItems()))) {
                        dataTestsuite.addAll(tbvAND.getSelectionModel().getSelectedItems());
                    } else {
                        log.warn("Testcase all ready added!");
                        WindowsDialogs.testCaseInTestsuite();
                    }
                } else {
                    log.warn("Is not a Android Testcase");
                    WindowsDialogs.wrongPlatform(tbvTestsuiteController.getPlatform());
                }
            }

            if (accTestCaseMain.getExpandedPane().getText().equals("Web-Portal - Testcase")) {
                log.info(actionEvent.getSource() + "Web");
                if (/*dataTestsuite.isEmpty() && */tbvTestsuiteController.getPlatform().equals("platform")) {
                    tbvTestsuiteController.setPlatform("Web");
                }

                if (tbvTestsuiteController.isWebPlatform()) {
                    dataTestsuite = currentTableView.getItems();
                    if (!(dataTestsuite.containsAll(tbvWEB.getSelectionModel().getSelectedItems()))) {
                        dataTestsuite.addAll(tbvWEB.getSelectionModel().getSelectedItems());
                    } else {
                        log.warn("Testcase all ready added!");
                        WindowsDialogs.testCaseInTestsuite();
                    }
                } else {
                    log.warn("Is not a Web Testcase");
                    WindowsDialogs.wrongPlatform(tbvTestsuiteController.getPlatform());
                }
            }

            if (accTestCaseMain.getExpandedPane().getText().equals("Search - Testcase")) {
                log.info(actionEvent.getSource() + "Search");
                dataTestsuite = currentTableView.getItems();
                if (!(dataTestsuite.containsAll(tbvSearch.getSelectionModel().getSelectedItems()))) {
                    dataTestsuite.addAll(tbvSearch.getSelectionModel().getSelectedItems());
                } else {
                    log.warn("Testcase all ready added!");
                    WindowsDialogs.testCaseInTestsuite();
                }
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
    }

    @FXML
    public void deleteTestcaseFromTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        try {
            ObservableList<TestCase> testCases = currentTableView.getSelectionModel().getSelectedItems();
            System.out.println(currentTableView.toString());
            dataTestsuite.removeAll(testCases);

            if (currentTableView.getItems().isEmpty()) {
                tbvTestsuiteController.setPlatform("platform");
                dataTestsuite = FXCollections.emptyObservableList();
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
    }

    @FXML
    public void runTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.run();
    }

    @FXML
    public void skipTestcase(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.skipped();
    }

    @FXML
    public void pauseTestcase(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.paused();
    }

    @FXML
    public void stopTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.stopped();
    }

    @FXML
    public void showTestsuiteResult(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void showOptions(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        optionsViewStage = setUpOptionsView();

        if (! tbvTestsuiteController.isOptionsEmpty()) {
            optionsController.setOptions(tbvTestsuiteController.getOptions());
            optionsController.updateOptionsView();
        }

        optionsViewStage.hide();
        optionsViewStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        optionsViewStage.show();
    }

    @FXML
    public void showHelp(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void searchTestcase(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
    }

    @FXML
    public void up(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.up();
    }

    @FXML
    public void down(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.down();
    }

    @FXML
    public void stepUp(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.stepUp();
    }

    @FXML
    public void stepDown(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        tbvTestsuiteController.stepDown();
    }


    /**
     * Bundle Resources ini
     */
    private void iniBundleResources() {
        lblSearchField.setText(bundle.getString("label.search"));
        lblStatusLeft.setText(bundle.getString("label.text.left.status"));
        lblStatusRight.setText(bundle.getString("label.text.right.status"));

        btnAddTestcase.getTooltip().setText(bundle.getString("tooltip.add.testcase"));
        btnRemoveTestcase.getTooltip().setText(bundle.getString("tooltip.deleted.testcase"));

        btnNewTestsuite.getTooltip().setText(bundle.getString("tooltip.new.testsuite"));
        btnDeletedTestsuite.getTooltip().setText(bundle.getString("tooltip.deleted.testsuite"));

        btnHelp.getTooltip().setText(bundle.getString("tooltip.help"));
        btnOpenTestsuite.getTooltip().setText(bundle.getString("tooltip.open"));
        btnOptions.getTooltip().setText(bundle.getString("tooltip.options"));
        btnPause.getTooltip().setText(bundle.getString("tooltip.pause"));
        btnResult.getTooltip().setText(bundle.getString("tooltip.result"));
        btnSaveTestsuite.getTooltip().setText(bundle.getString("tooltip.save"));
        btnSkip.getTooltip().setText(bundle.getString("tooltip.skip"));
        btnStop.getTooltip().setText(bundle.getString("tooltip.stop"));
        btnRun.getTooltip().setText(bundle.getString("tooltip.run"));
        btnUp.getTooltip().setText(bundle.getString("tooltip.up"));
        btnDown.getTooltip().setText(bundle.getString("tooltip.down"));
        btnStepUp.getTooltip().setText(bundle.getString("tooltip.stepUp"));
        btnStepDown.getTooltip().setText(bundle.getString("tooltip.stepDown"));

        mnuFile.setText(bundle.getString("menu.title.file"));
        mnuEdit.setText(bundle.getString("menu.title.edit"));
        mnuHelp.setText(bundle.getString("menu.title.help"));
        mnuOpenRecent.setText(bundle.getString("menu.title.open.recent"));
        mnuNew.setText(bundle.getString("menu.title.new"));
        mnuAbout.setText(bundle.getString("menu.title.about"));
        mnuClose.setText(bundle.getString("menu.title.close"));
        mnuCopy.setText(bundle.getString("menu.title.copy"));
        mnuPaste.setText(bundle.getString("menu.title.paste"));
        mnuDelete.setText(bundle.getString("menu.title.delete"));
        mnuCut.setText(bundle.getString("menu.title.cut"));
        mnuOpen.setText(bundle.getString("menu.title.open"));
        mnuPreferences.setText(bundle.getString("menu.title.preferences"));
        mnuQuit.setText(bundle.getString("menu.title.quit"));
        mnuRedo.setText(bundle.getString("menu.title.redo"));
        mnuSave.setText(bundle.getString("menu.title.save"));
        mnuSaveAs.setText(bundle.getString("menu.title.save.as"));
        mnuSelectAll.setText(bundle.getString("menu.title.select.all"));
        mnuUndo.setText(bundle.getString("menu.title.undo"));
        mnuUnSelectAll.setText(bundle.getString("menu.title.unselect.all"));
        mnuRevert.setText(bundle.getString("menu.title.revert"));
    }

    private void iniAccTestCaseMain(){
        accTestCaseMainSearch = new Accordion();

        accTestCaseMainSearch.getPanes().add(accTestCaseMain.getPanes().remove(3));
    }

    /**
     * No FML Functions
     */
    private void setUpIOSTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        fileNameLoaderIOS = new FileNameLoader("/com/arrival/testCase/iosTestcase", ".class");
        //ArrayList<String> fileNames = fileNameLoaderIOS.getClassName();
        ArrayList<String> classPackage = fileNameLoaderIOS.getClassPackage();
        try {
            for (int i = 0; i < fileNameLoaderIOS.getSize(); i++) {
                String fullName = classPackage.get(i);

                Class tempTestCaseClass = Class.forName(fullName);
                Object tempTestCaseObject = tempTestCaseClass.newInstance();
                IFTestCase tempTestCaseIF = (IFTestCase) tempTestCaseObject;
                tempList.add(new TestCase(
                        tempTestCaseIF.getTcName(),
                        tempTestCaseIF.getTcDescription(),
                        tempTestCaseIF.getTcResult(),
                        tempTestCaseIF.getTcDuration(),
                        tempTestCaseIF.getTcLastRun(),
                        tempTestCaseIF.getTcLink(),
                        classPackage.get(i),
                        getResultImageViewer(tempTestCaseIF.getTcResult())));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        }

        dataIOSTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpANDTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        fileNameLoaderAND = new FileNameLoader("/com/arrival/testCase/andTestcase", ".class");
        //ArrayList<String> fileNames = fileNameLoaderAND.getClassName();
        ArrayList<String> classPackage = fileNameLoaderAND.getClassPackage();
        try {
            for (int i = 0; i < fileNameLoaderAND.getSize(); i++) {
                String fullName = classPackage.get(i);

                Class tempTestCaseClass = Class.forName(fullName);
                Object tempTestCaseObject = tempTestCaseClass.newInstance();
                IFTestCase tempTestCaseIF = (IFTestCase) tempTestCaseObject;
                tempList.add(new TestCase(
                        tempTestCaseIF.getTcName(),
                        tempTestCaseIF.getTcDescription(),
                        tempTestCaseIF.getTcResult(),
                        tempTestCaseIF.getTcDuration(),
                        tempTestCaseIF.getTcLastRun(),
                        tempTestCaseIF.getTcLink(),
                        classPackage.get(i),
                        getResultImageViewer(tempTestCaseIF.getTcResult())));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        }
        dataANDTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpWebPortalTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        fileNameLoaderWeb = new FileNameLoader("/com/arrival/testCase/webTestcase", ".class");
        //  ArrayList<String> fileNames = fileNameLoaderWeb.getClassName();
        ArrayList<String> classPackage = fileNameLoaderWeb.getClassPackage();

        try {
            for (int i = 0; i < fileNameLoaderWeb.getSize(); i++) {
                String fullName = classPackage.get(i);

                Class tempTestCaseClass = Class.forName(fullName);
                Object tempTestCaseObject = tempTestCaseClass.newInstance();
                IFTestCase tempTestCaseIF = (IFTestCase) tempTestCaseObject;
                tempList.add(new TestCase(
                        tempTestCaseIF.getTcName(),
                        tempTestCaseIF.getTcDescription(),
                        tempTestCaseIF.getTcResult(),
                        tempTestCaseIF.getTcDuration(),
                        tempTestCaseIF.getTcLastRun(),
                        tempTestCaseIF.getTcLink(),
                        classPackage.get(i),
                        getResultImageViewer(tempTestCaseIF.getTcResult())));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(e.getStackTrace() + ":  " + e.toString());
        }
        dataWebPortalTestcase = FXCollections.observableArrayList(tempList);
    }

    private void setUpSearchTestcase(){
        dataFilterAndSearch.clear();
        dataFilterAndSearch.addAll(dataANDTestcase);
        dataFilterAndSearch.addAll(dataIOSTestcase);
        dataFilterAndSearch.addAll(dataWebPortalTestcase);
        filteredDate = new FilteredList<TestCase>(dataFilterAndSearch);
    }

    private void updateSearchTestcase(String valueToFilter){
        log.info("Test: " + valueToFilter);
         Predicate<TestCase> test = new Predicate<TestCase>() {
             @Override
             public boolean test(TestCase testCase) {
                 if (valueToFilter == null || valueToFilter.isEmpty()) {
                     return true;
                 }
                 // Compare first name and last name of every person with filter text
                 String lowerCaseFilter = valueToFilter.toLowerCase();

                 return testCase.getTcName().toLowerCase().indexOf(lowerCaseFilter) != -1;
             }
         };
        filteredDate.setPredicate(test);
        tbvSearch.setItems(filteredDate);
    }

    /**
     * Update teh dataFilterAndSearch.
     */
    private void addDataListener(){
        dataANDTestcase.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                setUpSearchTestcase();
            }
        });
        dataIOSTestcase.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                setUpSearchTestcase();
            }
        });
        dataWebPortalTestcase.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                setUpSearchTestcase();
            }
        });
    }

    private void addSearchFieldListener(){
        // Listen for TextField text changes
        txtSearchField.textProperty().addListener(new ChangeListener<String>() {
            URL url = getClass().getResource("/fxml/FXMLArrivalSearch.fxml");
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                log.info("TextField Text Changed (newValue: " + newValue + ")");
                if(newValue.equals("") && newValue.length()<=1) {
                    tpnIOS.setVisible(true);
                    tpnAND.setVisible(true);
                    tpnWEB.setVisible(true);
                    tpnSearch.setVisible(false);
                    accTestCaseMain = (Accordion) vBoxTestcase.getChildren().remove(1);
                    vBoxTestcase.getChildren().addAll(accTestCaseMainSearch);
                    setUpSearchTestcase();
                }else {
                    tpnIOS.setVisible(false);
                    tpnAND.setVisible(false);
                    tpnWEB.setVisible(false);
                    tpnSearch.setVisible(true);
                    accTestCaseMainSearch = (Accordion) vBoxTestcase.getChildren().remove(1);
                    vBoxTestcase.getChildren().addAll(accTestCaseMain);
                    setUpSearchTestcase();
                }
                updateSearchTestcase(newValue);
            }
        });
    }

    private void addTableViewListener() {
        tabMainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                TableView tempTableView = (TableView) tabMainTabPane
                                                              .getSelectionModel()
                                                              .getSelectedItem()
                                                              .getContent();
                currentTableView = tempTableView;
                dataTestsuite = currentTableView.getItems();
                tbvTestsuiteController = (FXMLArrivalTableViewController) tempTableView.getUserData();
            }
        });
    }

    private void setUpFirstTableView() {
        try {
            createNewTestsuite(new ActionEvent());
            TableView tempTableView = (TableView) tabMainTabPane
                                                          .getSelectionModel()
                                                          .getSelectedItem()
                                                          .getContent();
            currentTableView = tempTableView;
            dataTestsuite = currentTableView.getItems();
            tbvTestsuiteController = (FXMLArrivalTableViewController) tempTableView.getUserData();
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }

    private Stage setUpOptionsView() {
        try {
            URL url = getClass().getResource("/fxml/FXMLArrivalOptions.fxml");
            URL applicationIcon = getClass().getResource("/icons/appIcons.png");
            FXMLLoader loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalOptions"));
            Parent root = loader.load();
            Scene optionsScene = new Scene(root, 500, 400);
            Stage optionsStage = new Stage();
            optionsScene.getStylesheets().add("/css/arrivalMain.css");
            optionsStage.setScene(optionsScene);
            optionsStage.getIcons().add(new Image(applicationIcon.toString()));
            optionsStage.setTitle("Options - ArrivalOcto");
            optionsStage.setResizable(false);
            optionsStage.initModality(Modality.APPLICATION_MODAL);
            optionsController = loader.getController();
            optionsScene.setUserData(tbvTestsuiteController);
            return optionsStage;
        } catch (IOException e) {
            log.error(e.getStackTrace());
            return null;
        }
    }

    private ImageView getResultImageViewer(String tcResult){
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