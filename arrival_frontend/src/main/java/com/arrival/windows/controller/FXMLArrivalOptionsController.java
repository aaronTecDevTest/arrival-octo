package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.arrival.windows.model.Options;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalOptionsController implements Initializable {
    private static final Logger log = LogManager.getLogger(FXMLArrivalOptionsController.class);
    ObservableList<String> platform;
    ObservableList<String> mobilePlatform;
    ObservableList<String> mobileTestingArt;
    ObservableList<String> maxParallel;
    ObservableList<String> webBrowser;
    ObservableList<String> webServer;


//  @FXML
 // private Tab tabGeneral;
    @FXML
    private Tab tabWebConfig;
    @FXML
    private Tab tabMobileConfig;


    @FXML
    private ToggleButton togJsonWeb;
    @FXML
    private ToggleButton togJsonMobile;

    @FXML
    private Button btnJsonConfigWeb;
    @FXML
    private Button btnJsonConfigMobile;
    @FXML
    private Button btnAppPath;


    @FXML
    private TextField txtJsonConfigWeb;
    @FXML
    private TextField txtJsonConfigMobile;
    @FXML
    private TextField txtAppPath;
    @FXML
    private TextField txtPackageBundleID;
    @FXML
    private TextField txtHubServer;
    @FXML
    private TextField txtHubPort;


    @FXML
    private ComboBox<String> cmbPlatform;
    @FXML
    private ToggleButton togOnOffParallel;
    @FXML
    private ComboBox<String> cmbParallelThread;
    @FXML
    private ComboBox<String> cmbWebBrowser;
    @FXML
    private ComboBox<String> cmbWebServer;


    @FXML
    private ComboBox<String> cmbMobilePlatform;
    @FXML
    private ComboBox<String> cmbMobileTestingArt;


    @FXML
    private TextField txtSaveResultPath;
    @FXML
    private Button btnSaveResultPath;

    /*
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnRestConfig;*/

    private Options options;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        options = new Options();

        platform = FXCollections.observableArrayList(resources.getString("tab.general.platform").split(","));
        mobilePlatform = FXCollections.observableArrayList(resources.getString("tab.mobile.platform").split(","));
        mobileTestingArt = FXCollections.observableArrayList(resources.getString("tab.mobile.testingArt").split(","));

        maxParallel = FXCollections.observableArrayList(resources.getString("tab.general.parallelThread").split(","));
        webBrowser = FXCollections.observableArrayList(resources.getString("tab.web.browser").split(","));
        webServer = FXCollections.observableArrayList(resources.getString("tab.web.server").split(","));

        cmbPlatform.getItems().addAll(platform);
        cmbMobilePlatform.getItems().addAll(mobilePlatform);
        cmbMobileTestingArt.getItems().addAll(mobileTestingArt);
        cmbParallelThread.getItems().addAll(maxParallel);
        cmbWebServer.getItems().addAll(webServer);
        cmbWebBrowser.getItems().addAll(webBrowser);

        cmbPlatform.getSelectionModel().select("Non");
        setDefaultConfig();
    }

    @FXML
    public void openDirectoryChooser(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(((Node) actionEvent.getSource()).getScene().getWindow());

        if (selectedDirectory != null) {
            if (actionEvent.getSource().equals(btnJsonConfigWeb))
                txtJsonConfigWeb.setText(selectedDirectory.getAbsolutePath());
            else if (actionEvent.getSource().equals(btnJsonConfigMobile))
                txtJsonConfigMobile.setText(selectedDirectory.getAbsolutePath());
            else if (actionEvent.getSource().equals(btnSaveResultPath))
                txtSaveResultPath.setText((selectedDirectory.getAbsolutePath()));
        } else {
            log.warn("Count open or load directory!");
        }
    }

    @FXML
    public void openFileChooser(ActionEvent actionEvent){
        log.info(actionEvent);
        final FileChooser FileChooser = new FileChooser();
        final File selectedFile= FileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            if (actionEvent.getSource().equals(btnAppPath))
                txtAppPath.setText(selectedFile.getAbsolutePath());
        } else {
            log.warn("Count open or load file path!");
        }
    }

    @FXML
    public void saveConfigToTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        updateOptionsObject();

        FXMLArrivalTableViewController temp = (FXMLArrivalTableViewController) (((Node) actionEvent.getSource()).getScene().getUserData());
        temp.setOptions(options);
        Scene tempScene = ((Node) actionEvent.getSource()).getScene();
        ((Stage) tempScene.getWindow()).close();

        log.info(options.toString());
    }


    @FXML
    public void cancelOptionSetting(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        Scene tempScene = ((Node) actionEvent.getSource()).getScene();
        ((Stage) tempScene.getWindow()).close();
    }


    @FXML
    public void resetConfig(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        setDefaultConfig();
    }


    @FXML
    public void generalController(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cmbPlatform) {
            log.info(actionEvent.getSource());
            String tempPlatform = cmbPlatform.getSelectionModel().getSelectedItem();
            setDefaultConfig();
            switch (tempPlatform) {
                case "Web":
                    tabWebConfig.setDisable(false);
                    tabMobileConfig.setDisable(true);

                    break;
                case "Mobile":
                    tabWebConfig.setDisable(true);
                    tabMobileConfig.setDisable(false);

                    break;
                default:
                    tabWebConfig.setDisable(true);
                    tabMobileConfig.setDisable(true);
                    break;
            }
        }

        if (actionEvent.getSource() == togOnOffParallel) {
            log.info(actionEvent.getSource());
            if (togOnOffParallel.isSelected()) {
                cmbParallelThread.setDisable(false);
                togOnOffParallel.setSelected(true);
                togOnOffParallel.setText("On");
            } else {
                cmbParallelThread.setDisable(true);
                togOnOffParallel.setSelected(false);
                togOnOffParallel.setText("Off");
            }
        }

    }

    @FXML
    public void webConfigController(ActionEvent actionEvent) {
        if (actionEvent.getSource() == togJsonWeb) {
            log.info(actionEvent.getSource());
            if (! togJsonWeb.isSelected()) {
                txtJsonConfigWeb.setDisable(true);
                txtJsonConfigWeb.setText("");
                btnJsonConfigWeb.setDisable(true);
                togJsonWeb.setSelected(false);
                togJsonWeb.setText("Off");
                cmbWebBrowser.setDisable(false);
                cmbWebServer.setDisable(false);

            } else {
                txtJsonConfigWeb.setDisable(false);
                btnJsonConfigWeb.setDisable(false);
                togJsonWeb.setSelected(true);
                togJsonWeb.setText("On");
                cmbWebBrowser.setDisable(true);
                cmbWebServer.setDisable(true);
            }
        }
    }

    @FXML
    public void mobileConfigController(ActionEvent actionEvent) {
        if (actionEvent.getSource() == togJsonMobile) {
            log.info(actionEvent.getSource());
            if (togJsonMobile.isSelected()) {
                txtHubPort.setDisable(true);
                txtHubServer.setDisable(true);

                txtJsonConfigMobile.setDisable(false);
                btnJsonConfigMobile.setDisable(false);
                togJsonMobile.setSelected(true);
                togJsonMobile.setText("On");
            } else {
                txtHubPort.setDisable(false);
                txtHubServer.setDisable(false);

                txtJsonConfigMobile.setDisable(true);
                txtJsonConfigMobile.setText("");
                btnJsonConfigMobile.setDisable(true);
                togJsonMobile.setSelected(false);
                togJsonMobile.setText("Off");
            }
        }

        if (actionEvent.getSource() == cmbMobileTestingArt) {
            String platform = cmbMobileTestingArt.getSelectionModel().getSelectedItem();
            switch (platform) {
                case "Native":
                    btnAppPath.setDisable(false);
                    txtAppPath.setDisable(false);
                    txtPackageBundleID.setDisable(false);
                    txtAppPath.setText("");
                    txtPackageBundleID.setText("");
                    break;

                case "Mobile Web":
                    btnAppPath.setDisable(true);
                    txtAppPath.setDisable(true);
                    txtPackageBundleID.setDisable(true);
                    txtAppPath.setText("");
                    txtPackageBundleID.setText("");
                    break;

                case "Hybrid":
                    btnAppPath.setDisable(false);
                    txtAppPath.setDisable(false);
                    txtPackageBundleID.setDisable(false);
                    txtAppPath.setText("");
                    txtPackageBundleID.setText("");
                    break;
                default:
                    log.error("No Mobile Testing-Art is set!!");
                    break;
            }
        }
    }


    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void updateOptionsObject() {
        options.setPlatform(cmbPlatform.getSelectionModel().getSelectedItem());
        options.setMobilePlatform(cmbMobilePlatform.getSelectionModel().getSelectedItem());
        options.setMobileTestingArt(cmbMobileTestingArt.getSelectionModel().getSelectedItem());
        options.setParallelThreadCounter(Integer.valueOf(cmbParallelThread.getSelectionModel().getSelectedItem()));
        options.setBrowserName(cmbWebBrowser.getSelectionModel().getSelectedItem());
        options.setServerName(cmbWebServer.getSelectionModel().getSelectedItem());
        options.setParallelTesting(togOnOffParallel.isSelected());
        options.setHubServer(txtHubServer.getText());
        options.setHubPort(txtHubPort.getText());
        options.setAppFilePath(txtAppPath.getText());
        options.setPackageBundleID(txtPackageBundleID.getText());
        options.setSaveResultPath(txtSaveResultPath.getText());

        if (! tabWebConfig.isDisable()) {
            options.setJsonConfigInUse(togJsonWeb.isSelected());
            options.setJsonConfigPath(txtJsonConfigWeb.getText());
        }

        if (! tabMobileConfig.isDisable()) {
            options.setJsonConfigInUse(togJsonMobile.isSelected());
            options.setJsonConfigPath(txtJsonConfigMobile.getText());
        }
    }

    public void updateOptionsView() {
        cmbPlatform.getSelectionModel().select(options.getPlatform());
        cmbMobilePlatform.getSelectionModel().select(options.getMobilePlatform());
        cmbMobileTestingArt.getSelectionModel().select(options.getMobileTestingArt());
        cmbParallelThread.getSelectionModel().select(Integer.toString(options.getParallelThreadCounter()));
        cmbWebServer.getSelectionModel().select(options.getServerName());
        cmbWebBrowser.getSelectionModel().select(options.getBrowserName());

        txtHubServer.setText(options.getHubServer());
        txtHubPort.setText(options.getHubPort());
        txtAppPath.setText(options.getAppFilePath());
        txtPackageBundleID.setText(options.getAppFilePath());
        txtSaveResultPath.setText(options.getSaveResultPath());

        if (options.getParallelTesting())
            cmbParallelThread.setDisable(false);

        togOnOffParallel.setSelected(options.getParallelTesting());

        if (options.getPlatform().contains("Web")) {
            togJsonWeb.setSelected(options.getJsonConfigInUse());
            txtJsonConfigWeb.setText(options.getJsonConfigPath());
            tabWebConfig.setDisable(false);
            if (options.getJsonConfigInUse()){
                txtJsonConfigWeb.setDisable(false);
                togJsonWeb.setText("On");
            }
        }

        if (options.getPlatform().contains("Mobile")) {
            togJsonMobile.setSelected(options.getJsonConfigInUse());
            txtJsonConfigMobile.setText(options.getJsonConfigPath());
            tabMobileConfig.setDisable(false);
            if (options.getJsonConfigInUse()){
                txtJsonConfigMobile.setDisable(false);
                togJsonMobile.setText("On");
            }
        }

        if(options.getParallelTesting()) {
            togOnOffParallel.setText("On");
        }
    }

    public void setDefaultConfig() {
        //cmbPlatform.getSelectionModel().select("Non");
        cmbMobilePlatform.getSelectionModel().select("Android");
        cmbMobileTestingArt.getSelectionModel().select("Mobile Web");
        cmbParallelThread.getSelectionModel().select("2");
        cmbWebServer.getSelectionModel().select("Non");
        cmbWebBrowser.getSelectionModel().select("FF - FireFox");
        txtJsonConfigWeb.setText("");
        txtJsonConfigMobile.setText("C:\\Users\\a.kutekidila\\Dev\\GitHub\\arrival-octo\\arrival_backend\\src\\main\\resources\\appiumNodeConfig\\AppiumNodeNote3.json");
        txtSaveResultPath.setText("");
        txtAppPath.setText("");
        txtPackageBundleID.setText("");
        txtHubServer.setText("");
        txtHubPort.setText("");

        togOnOffParallel.setSelected(false);
        togJsonWeb.setSelected(false);
        togJsonMobile.setSelected(false);
        togOnOffParallel.setText("Off");
        togJsonWeb.setText("Off");
        togJsonMobile.setText("Off");

        cmbWebBrowser.setDisable(false);
        cmbWebServer.setDisable(false);
        cmbParallelThread.setDisable(true);
        txtJsonConfigWeb.setDisable(true);
        txtJsonConfigMobile.setDisable(true);
        txtAppPath.setDisable(true);
        txtPackageBundleID.setDisable(true);
        btnJsonConfigWeb.setDisable(true);
        btnJsonConfigMobile.setDisable(true);

        tabWebConfig.setDisable(true);
        tabMobileConfig.setDisable(true);
    }
}
