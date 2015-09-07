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
    ObservableList<String> andTestingArt;
    ObservableList<String> iosTestingArt;
    ObservableList<String> maxParallel;
    ObservableList<String> webBrowser;
    ObservableList<String> webServer;


    @FXML
    private Tab tabWebConfig;
    @FXML
    private Tab tabIOSConfig;
    @FXML
    private Tab tabANDConfig;

    @FXML
    private ToggleButton togJsonIOS;
    @FXML
    private ToggleButton togJsonWeb;
    @FXML
    private ToggleButton togJsonAND;


    @FXML
    private Button btnJsonConfigWeb;
    @FXML
    private Button btnJsonConfigIOS;
    @FXML
    private Button btnJsonConfigAND;
    @FXML


    private TextField txtJsonConfigWeb;
    @FXML
    private TextField txtJsonConfigIOS;
    @FXML
    private TextField txtJsonConfigAND;
    @FXML
    private ComboBox<String> cmbPlatform;
    @FXML
    private ToggleButton togOnOffParallel;
    @FXML
    private ComboBox<String> cmbMaxParallel;
    @FXML
    private ComboBox<String> cmbWebBrowser;
    @FXML
    private ComboBox<String> cmbWebServer;
    @FXML
    private ComboBox<String> cmbIOSTestingArt;
    @FXML
    private ComboBox<String> cmbANDTestingArt;

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
        andTestingArt = FXCollections.observableArrayList(resources.getString("tab.and.testingArt").split(","));
        iosTestingArt = FXCollections.observableArrayList(resources.getString("tab.ios.testingArt").split(","));
        maxParallel = FXCollections.observableArrayList(resources.getString("tab.general.maxParallel").split(","));
        webBrowser = FXCollections.observableArrayList(resources.getString("tab.web.browser").split(","));
        webServer = FXCollections.observableArrayList(resources.getString("tab.web.server").split(","));

        cmbPlatform.getItems().addAll(platform);
        cmbANDTestingArt.getItems().addAll(andTestingArt);
        cmbIOSTestingArt.getItems().addAll(iosTestingArt);
        cmbMaxParallel.getItems().addAll(maxParallel);
        cmbWebServer.getItems().addAll(webServer);
        cmbWebBrowser.getItems().addAll(webBrowser);

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
            else if (actionEvent.getSource().equals(btnJsonConfigIOS))
                txtJsonConfigIOS.setText(selectedDirectory.getAbsolutePath());
            else if (actionEvent.getSource().equals(btnJsonConfigAND))
                txtJsonConfigAND.setText(selectedDirectory.getAbsolutePath());
            else if (actionEvent.getSource().equals(btnSaveResultPath))
                txtSaveResultPath.setText((selectedDirectory.getAbsolutePath()));
        } else {
            log.error("Count open or load directory!");
        }
    }

    @FXML
    public void saveConfigToTestsuite(ActionEvent actionEvent) {
        log.info(actionEvent.getSource());
        updateOptionsObject();

        FXMLArrivalTableViewController temp = (FXMLArrivalTableViewController)(((Node) actionEvent.getSource()).getScene().getUserData());
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
    public void resetConfig(ActionEvent actionEvent){
        log.info(actionEvent.getSource());
        setDefaultConfig();
    }

    @FXML
    public void generalController(ActionEvent actionEvent){
        if(actionEvent.getSource() == cmbPlatform) {
            log.info(actionEvent.getSource());
            String tempPlatform = cmbPlatform.getSelectionModel().getSelectedItem();

            switch (tempPlatform) {
                case"Web":
                    tabWebConfig.setDisable(false);
                    tabIOSConfig.setDisable(true);
                    tabANDConfig.setDisable(true);
                    break;
                case"IOS":
                    tabWebConfig.setDisable(true);
                    tabIOSConfig.setDisable(false);
                    tabANDConfig.setDisable(true);
                    break;
                case"Android":
                    tabWebConfig.setDisable(true);
                    tabIOSConfig.setDisable(true);
                    tabANDConfig.setDisable(false);
                    break;
                default:
                    tabWebConfig.setDisable(true);
                    tabIOSConfig.setDisable(true);
                    tabANDConfig.setDisable(true);
                    break;
            }
        }

        if(actionEvent.getSource() == togOnOffParallel) {
            log.info(actionEvent.getSource());
            if(togOnOffParallel.isSelected()) {
                cmbMaxParallel.setDisable(false);
                togOnOffParallel.setSelected(true);
                togOnOffParallel.setText("On");
            }
            else{
                cmbMaxParallel.setDisable(true);
                togOnOffParallel.setSelected(false);
                togOnOffParallel.setText("Off");
            }
        }
    }

    @FXML
    public void webConfigController(ActionEvent actionEvent){
        if(actionEvent.getSource() == togJsonWeb) {
            log.info(actionEvent.getSource());
            if(togJsonWeb.isSelected()) {
                txtJsonConfigWeb.setDisable(false);
                btnJsonConfigWeb.setDisable(false);
                togJsonWeb.setSelected(true);
                togJsonWeb.setText("On");
                cmbWebBrowser.setDisable(false);
                cmbWebServer.setDisable(false);
            }
            else{
                txtJsonConfigWeb.setDisable(true);
                btnJsonConfigWeb.setDisable(true);
                togJsonWeb.setSelected(false);
                togJsonWeb.setText("Off");
                cmbWebBrowser.setDisable(true);
                cmbWebServer.setDisable(true);
            }
        }
    }

    @FXML
    public void iosConfigController(ActionEvent actionEvent){
        if(actionEvent.getSource() == togJsonIOS) {
            log.info(actionEvent.getSource());
            if(togJsonIOS.isSelected()) {
                txtJsonConfigIOS.setDisable(false);
                btnJsonConfigIOS.setDisable(false);
                togJsonIOS.setSelected(true);
                togJsonIOS.setText("On");
            }
            else{
                cmbMaxParallel.setDisable(true);
                txtJsonConfigIOS.setDisable(true);
                btnJsonConfigIOS.setDisable(true);
                togJsonIOS.setSelected(false);
                togJsonIOS.setText("Off");
            }
        }
    }

    @FXML
    public void andConfigController(ActionEvent actionEvent){
        if(actionEvent.getSource() == togJsonAND) {
            log.info(actionEvent.getSource());
            if(togJsonAND.isSelected()) {
                txtJsonConfigAND.setDisable(false);
                btnJsonConfigAND.setDisable(false);
                togJsonAND.setSelected(true);
                togJsonAND.setText("On");

            }
            else{
                cmbMaxParallel.setDisable(true);
                txtJsonConfigAND.setDisable(true);
                btnJsonConfigAND.setDisable(true);
                togJsonAND.setSelected(false);
                togJsonAND.setText("Off");

            }
        }
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void updateOptionsObject(){
        options.setPlatform(cmbPlatform.getSelectionModel().getSelectedItem());
        options.setMobileTestingArt(cmbANDTestingArt.getSelectionModel().getSelectedItem());
        options.setMobileTestingArt(cmbIOSTestingArt.getSelectionModel().getSelectedItem());
        options.setParallelTestingCount(Integer.valueOf(cmbMaxParallel.getSelectionModel().getSelectedItem()));
        options.setBrowserName(cmbWebBrowser.getSelectionModel().getSelectedItem());
        options.setServerName(cmbWebServer.getSelectionModel().getSelectedItem());
        options.setSaveResultPath(txtSaveResultPath.getText());
        options.setParallelTesting(togOnOffParallel.isSelected());

        if(!tabWebConfig.isDisable()) {
            options.setJsonConfigInUse(togJsonWeb.isSelected());
            options.setJsonConfigPath(txtJsonConfigWeb.getText());
        }

        if(!tabANDConfig.isDisable()) {
            options.setJsonConfigInUse(togJsonAND.isSelected());
            options.setJsonConfigPath(txtJsonConfigAND.getText());
        }

        if(!tabIOSConfig.isDisable()) {
            options.setJsonConfigInUse(togJsonIOS.isSelected());
            options.setJsonConfigPath(txtJsonConfigIOS.getText());
        }
    }

    public void updateOptionsView(){
        cmbPlatform.getSelectionModel().select(options.getPlatform());
        cmbANDTestingArt.getSelectionModel().select(options.getMobileTestingArt());
        cmbIOSTestingArt.getSelectionModel().select(options.getMobileTestingArt());
        cmbMaxParallel.getSelectionModel().select(Integer.toString(options.getParallelTestingCount()));
        cmbWebServer.getSelectionModel().select(options.getServerName());
        cmbWebBrowser.getSelectionModel().select(options.getBrowserName());
        txtSaveResultPath.setText(options.getSaveResultPath());

        if(options.getParallelTesting())
            cmbMaxParallel.setDisable(false);

        togOnOffParallel.setSelected(options.getParallelTesting());

        if(options.getPlatform().contains("Web")){
            togJsonWeb.setSelected(options.getJsonConfigInUse());
            txtJsonConfigWeb.setText(options.getJsonConfigPath());
            tabWebConfig.setDisable(false);
            if(options.getJsonConfigInUse())
                txtJsonConfigWeb.setDisable(false);
        }
        if(options.getPlatform().contains("IOS")){
            togJsonIOS.setSelected(options.getJsonConfigInUse());
            txtJsonConfigIOS.setText(options.getJsonConfigPath());
            tabIOSConfig.setDisable(false);
            if(options.getJsonConfigInUse())
                txtJsonConfigIOS.setDisable(false);
        }
        if(options.getPlatform().contains("Android")){
            togJsonAND.setSelected(options.getJsonConfigInUse());
            txtJsonConfigAND.setText(options.getJsonConfigPath());
            tabANDConfig.setDisable(false);
            if(options.getJsonConfigInUse())
                txtJsonConfigAND.setDisable(false);
        }
    }


    public void setDefaultConfig() {
        cmbPlatform.getSelectionModel().select("Non");
        cmbANDTestingArt.getSelectionModel().select("Native");
        cmbIOSTestingArt.getSelectionModel().select("Native");
        cmbMaxParallel.getSelectionModel().select("2");
        cmbWebServer.getSelectionModel().select("Non");
        cmbWebBrowser.getSelectionModel().select("FF - FireFox");
        txtJsonConfigWeb.setText("");
        txtJsonConfigAND.setText("");
        txtJsonConfigIOS.setText("");
        txtSaveResultPath.setText("");

        togOnOffParallel.setSelected(false);
        togJsonWeb.setSelected(false);
        togJsonIOS.setSelected(false);
        togJsonAND.setSelected(false);
        togOnOffParallel.setText("Off");
        togJsonWeb.setText("Off");
        togJsonIOS.setText("Off");
        togJsonAND.setText("Off");

        cmbWebBrowser.setDisable(false);
        cmbWebServer.setDisable(false);
        cmbMaxParallel.setDisable(true);
        txtJsonConfigWeb.setDisable(true);
        txtJsonConfigAND.setDisable(true);
        txtJsonConfigIOS.setDisable(true);
        btnJsonConfigWeb.setDisable(true);
        btnJsonConfigAND.setDisable(true);
        btnJsonConfigIOS.setDisable(true);

        tabWebConfig.setDisable(true);
        tabIOSConfig.setDisable(true);
        tabANDConfig.setDisable(true);
    }
}
