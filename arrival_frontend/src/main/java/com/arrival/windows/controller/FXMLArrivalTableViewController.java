package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.arrival.appium.AppiumManager;
import com.arrival.appium.AppiumSingleton;
import com.arrival.selenium.SeleniumManager;
import com.arrival.selenium.SeleniumSingleton;
import com.arrival.unit.suites.ArrivalTestSuite;
import com.arrival.utilities.WindowsDialogs;
import com.arrival.windows.model.Options;
import com.arrival.windows.model.TestCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
public class FXMLArrivalTableViewController /*extends Thread*/ implements Initializable  {

    /**
     * Logger
     */
    private static final Logger log = LogManager.getLogger(FXMLArrivalTableViewController.class);
    public ObservableList dateTestsuite;
    /**
     * For Internationalization
     */
    private ResourceBundle bundle;
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
    private TableColumn<TestCase, ImageView> tbcResult;

    private ArrivalTestSuite runTestSuite;
    private ObservableList dateTestSuite;
    private SeleniumManager tempSeleniumManager;
    private AppiumManager tempAppiumManager;
    private Options options;
    private String platform;

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
        //tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, String>("tcResult"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<TestCase, ImageView>("tcResultIcons"));
        tbcResult.getStyleClass().add("tbcResults");

        //tbvTestsuite.getSelectionModel().setCellSelectionEnabled(true);
        tbvTestsuite.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //SetUp Testcase to Table
        setUpTestsuite();
        tbvTestsuite.setItems(dateTestsuite);

        runTestSuite = new ArrivalTestSuite();
        platform = "platform";
    }

    public void run() {
        try {

            log.info(options.toString());

            tempSeleniumManager = new SeleniumManager();
            tempAppiumManager = new AppiumManager();
            List<XmlClass> tempClasses = new ArrayList<>();

            if (options.getPlatform().equals("Web")) {
                tempSeleniumManager.setTestSuiteConfigs(options);
                SeleniumSingleton.getInstance().setSeleniumManager(tempSeleniumManager);
                SeleniumSingleton.setFramework(SeleniumSingleton.ARRIVAL);
                //tempClasses.add(new XmlClass("com.arrival.selenium.SeleniumSingleton"));
            } else if (options.getPlatform().equals("Mobile")) {
                tempAppiumManager.setTestSuiteConfigs(options);
                AppiumSingleton.getInstance().setAppiumManager(tempAppiumManager);
                AppiumSingleton.setFramework(AppiumSingleton.ARRIVAL);
                //tempClasses.add(new XmlClass("com.arrival.appium.AppiumSingleton"));
            } else {
                log.warn("No Platform is set up!");
                WindowsDialogs.noTestConfigSet();
            }

            dateTestSuite = tbvTestsuite.getItems();
            for (int i = 0; i < dateTestsuite.size(); i++) {
                tempClasses.add(new XmlClass(((TestCase) dateTestSuite.get(i)).getTcClassPackage()));
            }
            if(options.getParallelTesting()){
                //runTestSuite.getSuite().setParallel(options.getParallelTesting().toString());
                //runTestSuite.getSuite().setThreadCount(options.getParallelThreadCounter());
                //runTestSuite.getSuite().setDataProviderThreadCount(options.getParallelThreadCounter());
            }

            if(!options.getSaveResultPath().isEmpty()){
                runTestSuite.getTng().setOutputDirectory(options.getSaveResultPath().replaceAll("/","\\"));
            }else {
                runTestSuite.getTng().setOutputDirectory(runTestSuite.getNewPathDirectory());
            }

            runTestSuite.setClasses(tempClasses);
            runTestSuite.run();

        } catch (Exception e) {
            log.warn("Options object is null" + e.getStackTrace());
            WindowsDialogs.optionsIsNull();
        }
    }

    public void stopped() {
        runTestSuite.stopThread();
    }

    public void paused(){
        runTestSuite.pauseThread();
    }

    public void resumed() {
      runTestSuite.resumeThread();
    }

    public void skipped() {
        runTestSuite.skippTestCase();
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

    public TableView<TestCase> getTbvTestsuite() {
        return tbvTestsuite;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public boolean isOptionsEmpty() {
        return options == null;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isWebPlatform() {
        return platform.equals("Web");
    }

    public boolean isIOSPlatform() {
        return platform.equals("IOS");
    }

    public boolean isANDPlatform() {
        return platform.equals("Android");
    }
}