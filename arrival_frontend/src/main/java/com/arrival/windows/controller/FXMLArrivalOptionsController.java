package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.Node;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalOptionsController implements Initializable {
    private static final Logger log =  LogManager.getLogger(FXMLArrivalOptionsController.class);

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
    private ComboBox<String> cmbPlattform;
    @FXML
    private ToggleButton togOnOffParallel;
    @FXML
    private ComboBox<String> cmbParallelRun;


    @FXML
    private ComboBox<String> cmbWebBrowser;
    @FXML
    private ComboBox<String> cmbWebServer;

    @FXML
    private ComboBox<String> cmbIOSTestingArt;
    @FXML
    private ComboBox<String> cmbANDTestingArt;




    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
   public void openDirectoryChooser(ActionEvent actionEvent){
    log.info(actionEvent.getSource());
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(((Node) actionEvent.getSource()).getScene().getWindow());
        if (selectedDirectory != null) {
            if(actionEvent.getSource().equals(btnJsonConfigWeb))
                txtJsonConfigWeb.setText(selectedDirectory.getAbsolutePath());
            else if (actionEvent.getSource().equals(btnJsonConfigIOS))
                txtJsonConfigIOS.setText(selectedDirectory.getAbsolutePath());
            else if (actionEvent.getSource().equals(btnJsonConfigAND))
                txtJsonConfigAND.setText(selectedDirectory.getAbsolutePath());
        }else {

        }
    }
}
