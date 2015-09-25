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
import com.arrival.windows.model.TestCase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalMainController implements Initializable {

    /**
     * Logger
     */
    private static final Logger log = LogManager.getLogger(FXMLArrivalMainController.class);

    public ObservableList dateIOSTestcase;
    public ObservableList dateANDTestcase;
    public ObservableList dateWebPortalTestcase;
    public ObservableList dateTestsuite;

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
    private MenuItem mnuUnselectAll;


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

    private FXMLArrivalTableViewController tbvTestsuiteController;

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

        //Setup Table-Data Objects
        dateANDTestcase = FXCollections.observableArrayList();
        dateTestsuite = FXCollections.observableArrayList();
        dateIOSTestcase = FXCollections.observableArrayList();
        dateWebPortalTestcase = FXCollections.observableArrayList();

        //Setup Table-Colmn Properties
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

        tbvIOS.setItems(dateIOSTestcase);
        tbvAND.setItems(dateANDTestcase);
        tbvWebPortal.setItems(dateWebPortalTestcase);

        //Set first TitlePane open
        TitledPane ios = accTestCase.getPanes().get(0);
        accTestCase.setExpandedPane(ios);

        //SetUp Testsuite
        setUpFirstTableView();
        addTableViewListener();

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
                        if(mouseEvent.getSource()== tabLabel)
                            tabName = WindowsDialogs.setTestsuiteNameDialog();

                        if (!tabName.isEmpty()) {
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
        if(actionEvent.getSource()== btnNewTestsuite)
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
    }

    @FXML
    public void addTestcaseInTestsuite(ActionEvent actionEvent) {
        try {
            if (accTestCase.getExpandedPane().getText().equals("iOS - Testcase")) {
                log.info(actionEvent.getSource() + "IOS");
                if(dateTestsuite.isEmpty()){
                    tbvTestsuiteController.setPlatform("IOS");
                }

                if(tbvTestsuiteController.isIOSPlatform()){
                    dateTestsuite = currentTableView.getItems();
                    dateTestsuite.addAll(tbvIOS.getSelectionModel().getSelectedItems());
                }else{
                    log.warn("Is not a IOS Testcase");
                    WindowsDialogs.rongPlatform(tbvTestsuiteController.getPlatform());
                }
            }

            if (accTestCase.getExpandedPane().getText().equals("Android - Testcase")) {
                log.info(actionEvent.getSource() + "AND");
                if(dateTestsuite.isEmpty()){
                    tbvTestsuiteController.setPlatform("Android");
                }

                if(tbvTestsuiteController.isANDPlatform()){
                    dateTestsuite = currentTableView.getItems();
                    dateTestsuite.addAll(tbvAND.getSelectionModel().getSelectedItems());
                }else{
                    log.warn("Is not a IOS Testcase");
                    WindowsDialogs.rongPlatform(tbvTestsuiteController.getPlatform());
                }
            }

            if (accTestCase.getExpandedPane().getText().equals("Web-Portal - Testcase")) {
                log.info(actionEvent.getSource() + "Web");
                if(dateTestsuite.isEmpty()){
                    tbvTestsuiteController.setPlatform("Web");
                }
                if(tbvTestsuiteController.isWebPlatform()){
                    dateTestsuite = currentTableView.getItems();
                    dateTestsuite.addAll(tbvWebPortal.getSelectionModel().getSelectedItems());
                }else{
                    log.warn("Is not a IOS Testcase");
                    WindowsDialogs.rongPlatform(tbvTestsuiteController.getPlatform());
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
            dateTestsuite.removeAll(testCases);

            if(currentTableView.getItems().isEmpty()) {
                tbvTestsuiteController.setPlatform(null);
                dateTestsuite = FXCollections.emptyObservableList();
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
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

        if(!tbvTestsuiteController.isOptionsEmpty()){
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
        mnuUnselectAll.setText(bundle.getString("menu.title.unselect.all"));
        mnuRevert.setText(bundle.getString("menu.title.revert"));
    }

    /**
     * No FML Functions
     */
    //ToDo classe
    private void setUpIOSTestcase() {
        ArrayList<TestCase> tempList = new ArrayList<>();
        fileNameLoaderIOS = new FileNameLoader("/com/arrival/testCase/iosTestcase", ".class");
        ArrayList<String> fileNames = fileNameLoaderIOS.getClassName();
        ArrayList<String> classPackage = fileNameLoaderIOS.getClassPackage();
        try {
            for (int i = 0; i < fileNameLoaderIOS.getSize(); i++) {
                Class tempTestCaseClass = Class.forName(classPackage.get(i));
                tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
            }
        }catch (Exception e){
            log.error(e.getStackTrace());
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

        try {
            for (int i = 0; i < fileNameLoaderWeb.getSize(); i++) {

                String fullName = classPackage.get(i);

                Class tempTestCaseClass = Class.forName(fullName);
              //  Object object = tempTestCaseClass.newInstance();
                Method m = tempTestCaseClass.getDeclaredMethod("getTcLink",null);
                System.out.println(m.invoke(null, null));
               // System.out.println(object.toString());
                tempList.add(new TestCase(fileNames.get(i), "test2", "datum", "timer", "", "true", classPackage.get(i)));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error(e.getStackTrace() + ":  " + e.toString());
        }

        dateWebPortalTestcase = FXCollections.observableArrayList(tempList);
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
            Scene optionsScene = new Scene(root, 480, 280);
            Stage optionsStage = new Stage();
            optionsScene.getStylesheets().add("/css/arrivalMain.css");
            optionsStage.setScene(optionsScene);
            optionsStage.getIcons().add(new Image(applicationIcon.toString()));
            optionsStage.setTitle("Options - ArrivalOcto");
            optionsStage.setResizable(false);
            optionsStage.initModality(Modality.APPLICATION_MODAL);
            optionsController = loader.getController();
            optionsScene.setUserData(tbvTestsuiteController);
            //log.warn(optionsStage);
            return optionsStage;
        } catch (IOException e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
}
